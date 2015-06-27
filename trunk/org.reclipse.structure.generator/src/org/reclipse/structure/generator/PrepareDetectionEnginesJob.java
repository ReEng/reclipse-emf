package org.reclipse.structure.generator;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.fujaba.commons.console.AbstractProcessConsoleJob;
import org.fujaba.commons.console.ReportLevel;
import org.reclipse.structure.generator.preparationstrategies.AbstractEnginePreparationStrategy;
import org.reclipse.structure.generator.preparationstrategies.LoadExistingEnginesStrategy;
import org.reclipse.structure.generator.preparationstrategies.GenerateNewEnginesStrategy;


/**
 * {@link org.eclipse.core.runtime.jobs.Job Job} that prepares the pattern detection engines before
 * the actual detection. Engines are either loaded from an existing resource or generated from a
 * catalog. This is decided by the preparation strategy that is supplied upon construction of the
 * job.
 * 
 * @author mvdetten
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class PrepareDetectionEnginesJob extends AbstractProcessConsoleJob
{

   private final AbstractEnginePreparationStrategy preparationStrategy;


   /**
    * The constructor.
    * 
    * @param preparationStrategy The strategy that is used to prepare the engines, e.g. loading them
    *           from an existing resource ({@link LoadExistingEnginesStrategy}) or generating them
    *           from a pattern catalog ({@link GenerateNewEnginesStrategy}).
    * @param reportLevel The report level as specified by the user.
    */
   public PrepareDetectionEnginesJob(AbstractEnginePreparationStrategy preparationStrategy, ReportLevel reportLevel)
   {
      super(
            "Reclipse",
            "Prepare Detection Engines",
            "This activity loads the relevant models for the pattern detection and loads or generates the detection Engines.",
            reportLevel);
      this.preparationStrategy = preparationStrategy;
      this.preparationStrategy.setReporter(this);
   }


   @Override
   public IStatus start(IProgressMonitor monitor)
   {
      monitor.beginTask("Preparing pattern detection engines...", IProgressMonitor.UNKNOWN);
      return this.preparationStrategy.prepareEngines();
   }

}
