package org.reclipse.structure.inference.strategy;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.fujaba.commons.console.ReportLevel;
import org.reclipse.structure.inference.InferenceEngine;
import org.reclipse.structure.inference.InferenceStrategy;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.notification.InferenceProgressProvider;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.SpecificationUtil;
import org.reclipse.structure.specification.util.TriggerManager;


/**
 * @version $Revision$ $Date$
 * @author Last Editor: $Author$
 * @author harka
 */
public class BottomUpStrategy extends InferenceStrategy
{
   private BottomUpQueue queue;

   private TriggerManager triggerManager;


   public BottomUpStrategy(InferenceEngine engine, PSCatalog catalog)
   {
      super(engine, catalog);

      this.triggerManager = new TriggerManager(catalog.getPatternSpecifications());

      this.queue = new BottomUpQueue(this, true);
   }


   @Override
   protected void processInference(IProgressMonitor monitor)
   {
      // cache progress provider
      InferenceProgressProvider progress = getEngine().getInferenceProgressProvider();

      // fill axioms into queue
      task("Creating context queue");
      fillAxiomPairsIntoQueue();

      if (!queue.isEmpty())
      {
         monitor.subTask("Analyzing Host Graph...");
         task("Starting to detect patterns");
         progress.incrementMaximum(queue.size());
      }

      // run while queue is not empty
      PSPatternSpecification currentPattern = null;
      while (!queue.isEmpty() && !getEngine().isAborted() && !monitor.isCanceled())
      {
         // check if inference is halted
         if (!getEngine().isPaused())
         {
            // get next from queue
            ContextPatternPair pair = queue.dequeue();

            if (!pair.getPattern().equals(currentPattern))
            {
               currentPattern = pair.getPattern();
               monitor.subTask(String.format("Searching for Pattern '%1s'...", currentPattern.getName()));
            }

            // report annotate intention
            reportAnnotate(pair);

            // annotate
            Collection<ASGAnnotation> annotated = getEngine().annotate(pair);
            progress.increment();

            // fill queue on new annotations
            if (!annotated.isEmpty())
            {
               for (ASGAnnotation annotation : annotated)
               {
                  for (PSPatternSpecification patternToTrigger : triggerManager
                        .getPatternThatShouldBeTriggeredOnFoundAnnotation(currentPattern))
                  {
                     // already contained or abstract pattern?
                     if (!patternToTrigger.isAbstract())
                     {
                        PSNode triggerNode = triggerManager.getTrigger(patternToTrigger);
                        if (triggerNode instanceof PSAnnotation)
                        {
                           if (SpecificationUtil.getInstancePatterns((PSAnnotation) triggerNode).contains(
                                 currentPattern)
                                 && !getFactory().contains(annotation, patternToTrigger))
                           {
                              ContextPatternPair nextPair = getFactory().create(annotation, patternToTrigger);

                              // insert
                              queue.enqueue(nextPair);

                              // report
                              debug("Enqueued pattern '%1s' to be searched from '%2s'.", patternToTrigger.getName(),
                                    annotation);
                           }
                        }
                        else if (triggerNode instanceof PSObject)
                        {
                           Collection<EObject> elements = collectHostGraphElementsOfTriggerType(((PSObject) triggerNode));

                           for (EObject context : elements)
                           {
                              if (!getFactory().contains(context, patternToTrigger))
                              {
                                 ContextPatternPair nextPair = getFactory().create(context, patternToTrigger);

                                 // insert
                                 queue.enqueue(nextPair);

                                 // report
                                 debug("Enqueued pattern '%1s' to be searched from '%2s'.", patternToTrigger.getName(),
                                       context);
                              }
                           }
                        }
                     }
                  }
               }

               progress.incrementMaximum(queue.size());
            }
         }
         else
         {
            // inference is halted, sleep and check again
            try
            {
               Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
            }
         }
      }
   }


   /**
    * Enqueues all pattern 'axiom' pairs into queue.
    */
   private void fillAxiomPairsIntoQueue()
   {
      task("Collecting axiom elements");

      // go through all patterns
      for (PSPatternSpecification pattern : getCatalog().getPatternSpecifications())
      {
         // don't build pair of abstract patterns
         if (!pattern.isAbstract())
         {
            PSNode triggerNode = triggerManager.getTrigger(pattern);
            if (triggerNode instanceof PSObject)
            {
               // search adequate elements in AST
               Set<EObject> current = collectHostGraphElementsOfTriggerType((PSObject) triggerNode);
               for (EObject element : current)
               {
                  if (!getFactory().contains(element, pattern))
                  {
                     // create pair
                     ContextPatternPair pair = getFactory().create(element, pattern);
                     queue.enqueue(pair);
                  }
               }

               // report
               debug("Collected %1s axiom elements for pattern '%2s'.", current.size(), pattern.getName());
            }
         }
      }

      info("Axiom elements collected.", ReportLevel.TASK);
   }


   /**
    * Collects all adequate host graph elements of the same type as the trigger object.
    * 
    * @param axiom The type to collect for.
    * @return Returns all found instance elements.
    */
   private Set<EObject> collectHostGraphElementsOfTriggerType(PSObject trigger)
   {
      Set<EObject> result = new HashSet<EObject>();
      
      EClass triggerType = trigger.getInstanceOf();

      // iterate over all contents of AST root
      TreeIterator<Notifier> contents = EcoreUtil.getAllContents(getEngine().getHost());
      while (contents.hasNext())
      {
         // get next
         Notifier element = contents.next();

         if (triggerType.isInstance(element))
         {
            // we know it must be an EObject
            result.add((EObject) element);
         }
      }

      return result;
   }


   /**
    * Calculates the rank of a pattern according to its dependencies.
    * 
    * @param pattern The pattern to rank.
    * @return Returns the rank of the pattern.
    */
   public int getRank(PSPatternSpecification pattern)
   {
      return triggerManager.getRank(pattern);
   }


   private void reportAnnotate(ContextPatternPair pair)
   {
      StringBuilder message = new StringBuilder();

      message.append("Searching for pattern '");
      message.append(pair.getPattern().getName());
      message.append("' from context '");
      message.append(pair.getContext());
      message.append("'.");

      debug(message.toString());
   }
}
