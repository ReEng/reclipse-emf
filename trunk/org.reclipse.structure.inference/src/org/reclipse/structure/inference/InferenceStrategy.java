package org.reclipse.structure.inference;


import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.fujaba.commons.console.IReportListener;
import org.reclipse.metamodel.ITriggerChooser;
import org.reclipse.structure.inference.strategy.ContextPatternPairFactory;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ContainmentWeightedTriggerChooser;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * @version $Revision$ $Date$
 * @author Last Editor: $Author$
 * @author Dietrich Travkin
 */
public abstract class InferenceStrategy implements IReportListener
{
   private PSCatalog catalog;

   private ITriggerChooser chooser;

   private ContextPatternPairFactory factory;

   private InferenceEngine engine;


   /**
    * The default constructor. Don't forget to call <code>super()</code> in sub classes.
    * 
    * @param engine The engine from which the strategy is controlled.
    * @param catalog The pattern catalog which to process.
    */
   public InferenceStrategy(InferenceEngine engine, PSCatalog catalog)
   {
      this.engine = engine;
      this.catalog = catalog;
      // this.chooser = determineChooser();
      chooser = new ContainmentWeightedTriggerChooser();
      this.factory = new ContextPatternPairFactory();
   }


   protected EClass getTrigger(PSPatternSpecification pattern)
   {

      Set<PSObject> objects = new HashSet<PSObject>();

      for (PSNode node : pattern.getNodes())
      {
         if (node instanceof PSObject)
         {
            objects.add((PSObject) node);
         }
      }

      // prepare set
      Set<EClass> types = new HashSet<EClass>();

      // collect types
      for (PSObject object : objects)
      {
         if (!ModelHelper.isContainedInAdditionalFragment(object) && !ModelHelper.isContainedInNegativeFragment(object)
               && !ModifierType.ADDITIONAL.equals(object.getModifier())
               && !ModifierType.NEGATIVE.equals(object.getModifier()))
         {
            types.add(object.getInstanceOf());
         }
      }

      // check for the best type (lowest number of occurences)
      return chooser.getTrigger(types);
   }


   protected InferenceEngine getEngine()
   {
      return engine;
   }


   protected PSCatalog getCatalog()
   {
      return this.catalog;
   }


   protected ITriggerChooser getChooser()
   {
      return this.chooser;
   }


   protected ContextPatternPairFactory getFactory()
   {
      return this.factory;
   }


   /**
    * Runs the inference process implemented by this strategy class on this strategy instance's pattern rule catalog.
    * <p>
    * Before the inference process is started {@link #preProcess()} is called. The inference process itself is started
    * by a call of the method {@link #processInference()}.
    * 
    * @see #preProcess()
    * @see #processInference()
    */
   public final void startInference(IProgressMonitor monitor)
   {
      preProcess(monitor);
      processInference(monitor);
   }


   /**
    * Runs all the necessary processes before the inference is started.
    */
   protected void preProcess(IProgressMonitor monitor)
   {
      // feel free to override
   }


   /**
    * Runs the inference process with the strategy that is implemented by this class.
    */
   protected abstract void processInference(IProgressMonitor monitor);


   @Override
   public IStatus error(String message, Object... args)
   {
      return engine.error(message, args);
   }


   @Override
   public void warn(String message, Object... args)
   {
      engine.warn(message, args);
   }


   @Override
   public void append(String message, Object... args)
   {
      engine.append(message, args);
   }


   @Override
   public void task(String message, Object... args)
   {
      engine.task(message, args);
   }


   @Override
   public void info(String message, Object... args)
   {
      engine.info(message, args);
   }


   @Override
   public void debug(String message, Object... args)
   {
      engine.debug(message, args);
   }
}
