package org.reclipse.structure.inference;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.fujaba.commons.console.IControllableReportListener;
import org.reclipse.structure.generator.GeneratorConstants;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.storydriven.core.expressions.Expression;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityEdge;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.OperationExtension;
import org.storydriven.storydiagrams.interpreter.eclipse.StoryDrivenEclipseInterpreter;
import org.storydriven.storydiagrams.patterns.AbstractLinkVariable;
import org.storydriven.storydiagrams.patterns.AbstractVariable;
import org.storydriven.storydiagrams.patterns.StoryPattern;

import de.mdelab.sdm.interpreter.core.SDMException;
import de.mdelab.sdm.interpreter.core.notifications.NotificationEmitter;
import de.mdelab.sdm.interpreter.core.notifications.OutputStreamNotificationReceiver;
import de.mdelab.sdm.interpreter.core.variables.Variable;


public class InterpreterInferenceEngine extends InferenceEngine
{
   private StoryDrivenEclipseInterpreter interpreter = null;


   public InterpreterInferenceEngine(IControllableReportListener reporter)
   {
      this(reporter, false);
   }


   public InterpreterInferenceEngine(IControllableReportListener reporter, boolean isDebug)
   {
      super(reporter, isDebug);
   }


   @Override
   protected Collection<ASGAnnotation> applyPatternSearch(PSPatternSpecification pattern, EObject context)
   {
      // get engine
      EObject engine = engines.get(pattern);
      if (engine == null)
      {
         String message = "Exception occurred on search for '%1s': Could not find an engine class.";
         warn(String.format(message, pattern.getName()));

         return Collections.emptyList();
      }
      // prepare parameters
      Collection<Variable<EClassifier>> params = new ArrayList<Variable<EClassifier>>();
      params.add(new Variable<EClassifier>(GeneratorConstants.PARAMETER_THIS, engine.eClass(), engine));
      params.add(new Variable<EClassifier>(GeneratorConstants.PARAMETER_CONTEXT, context.eClass(), context));
      params.add(new Variable<EClassifier>(GeneratorConstants.PARAMETER_SEARCH_ADDITIONALS,
            EcorePackage.Literals.EBOOLEAN, searchForAdditionalElements));

      Activity activity = getAnnotateActivity(pattern);
      if (activity != null)
      {
         try
         {
            if (isDebug())
            {
               //ConsoleNotificationReceiver notificationReceiver = new ConsoleNotificationReceiver(new InterpreterConsole());
               NotificationEmitter<Activity, ActivityNode, ActivityEdge, StoryPattern, AbstractVariable, AbstractLinkVariable, EClassifier, EStructuralFeature, Expression> emitter = new NotificationEmitter<Activity, ActivityNode, ActivityEdge, StoryPattern, AbstractVariable, AbstractLinkVariable, EClassifier, EStructuralFeature, Expression>();
               //emitter.addNotificationReceiver(notificationReceiver);
               interpreter = new StoryDrivenEclipseInterpreter(getClass().getClassLoader(), emitter);
               emitter.addNotificationReceiver(new OutputStreamNotificationReceiver<>(interpreter.getFacadeFactory()));
            }
            else
            {
               interpreter = new StoryDrivenEclipseInterpreter(getClass().getClassLoader());
            }

            Map<String, Variable<EClassifier>> executionResult = interpreter.executeActivity(activity, params);
            if (executionResult != null)
            {
               Variable<EClassifier> v = executionResult.get(activity.getOutParameters().get(0).getName());

               if (v.getValue() instanceof AnnotationSet)
               {
                  return ((AnnotationSet) v.getValue()).getAnnotations();
               }
            }
         }
         catch (SDMException e)
         {
            String message = "Exception occurred on search for '%1$s'!";
            warn(String.format(message, pattern.getName()));
            e.printStackTrace();
         }
      }

      return Collections.emptyList();
   }


   /**
    * Fetches the activity for the AnnotationEngine's annotate operation.
    * 
    * @param pattern, the pattern specification, for which the activity is needed
    * @return the annotate activity or null, if not found
    */
   private Activity getAnnotateActivity(PSPatternSpecification pattern)
   {
      EObject engine = engines.get(pattern);
      EOperation annotateOperation = null;
      for (EOperation operation : engine.eClass().getEOperations())
      {
         if (GeneratorConstants.METHOD.equals(operation.getName()))
         {
            annotateOperation = operation;
            break;
         }
      }

      if (annotateOperation != null)
      {
         OperationExtension operExt = null;
         for (EAnnotation anno : annotateOperation.getEAnnotations())
         {
            for (EObject o : anno.getContents())
            {
               if (o instanceof OperationExtension)
               {
                  operExt = (OperationExtension) o;
                  break;
               }
            }
         }

         if (operExt != null)
         {
            return operExt.getOwnedActivity();
         }
      }

      String message = "Exception occurred on search for '%1s': Could not find annotate operation!";
      warn(String.format(message, pattern.getName()));

      return null;
   }

}
