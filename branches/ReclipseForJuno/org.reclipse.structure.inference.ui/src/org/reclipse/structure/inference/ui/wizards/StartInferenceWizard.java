package org.reclipse.structure.inference.ui.wizards;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.fujaba.commons.console.ReportLevel;
import org.reclipse.structure.InferenceUIPlugin;
import org.reclipse.structure.generator.PrepareDetectionEnginesJob;
import org.reclipse.structure.generator.preparationstrategies.AbstractEnginePreparationStrategy;
import org.reclipse.structure.generator.preparationstrategies.GenerateNewEnginesStrategy;
import org.reclipse.structure.generator.preparationstrategies.LoadExistingEnginesStrategy;
import org.reclipse.structure.inference.DetectPatternsJob;
import org.reclipse.structure.inference.IAnnotationEvaluator;
import org.reclipse.structure.inference.notification.InferenceProgressListener;
import org.reclipse.structure.inference.ui.InferenceConstants;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;


/**
 * This class creates the wizard which is used to configure and start a structural patterns
 * detection in Reclipse.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class StartInferenceWizard extends Wizard
{
   private static final String VID_MATCHED_PATTERNS = "org.reclipse.ui.views.structure.inference.matching.pattern"; //$NON-NLS-1$

   private static final String VID_MATCHED_OBJECTS = "org.reclipse.ui.views.structure.inference.matching.ast"; //$NON-NLS-1$

   protected StartInferenceWizardPage mainWizardPage;


   public StartInferenceWizard()
   {
      super();

      setNeedsProgressMonitor(true);
      setHelpAvailable(false);
      setWindowTitle("Structural Patterns Detection");
      setDefaultPageImageDescriptor(InferenceUIPlugin.getInstance().getImageDescriptor(
            InferenceConstants.IMG_START_INFERENCE_WIZARD_BANNER));

      IDialogSettings settings = InferenceUIPlugin.getInstance().getDialogSettings();
      IDialogSettings section = settings.getSection(getClass().getCanonicalName());
      if (section == null)
      {
         section = settings.addNewSection(getClass().getCanonicalName());
      }
      setDialogSettings(section);
   }


   @Override
   public void addPages()
   {
      mainWizardPage = new StartInferenceWizardPage("Structural Patterns Detection");
      addPage(mainWizardPage);
   }


   /**
    * The wizard creates and starts two different jobs. First the detection engines are prepared
    * (i.e. loaded or generated, depending on the user's choices. Afterwards, the detection is
    * started with the previously prepared engines. The two jobs are run sequentially because the
    * engines have to be prepared before the detection can begin. Before the detection job is
    * started, the appropriate detection views are opened.
    * 
    * @see org.eclipse.jface.wizard.Wizard#performFinish()
    */
   @Override
   public boolean performFinish()
   {
      storePageSettings();

      // let the user confirm annotation result overwriting
      if (abortStartDueToExistingAnnotations())
      {
         return false;
      }

      final PrepareDetectionEnginesJob prepareEnginesJob = createPrepareEnginesJob();

      IProgressMonitor monitor = Job.getJobManager().createProgressGroup();

      try
      {
         monitor.beginTask("Preparing detection engines", 10);
         prepareEnginesJob.setProgressGroup(monitor, 20);
         prepareEnginesJob.schedule();
         prepareEnginesJob.join();

         final DetectPatternsJob detectionJob = createPatternDetectionJob();
         monitor.beginTask("Detecting patterns", 70);
         
         configureAnnotationsView(detectionJob);
         configureMatchingViews();
         detectionJob.schedule();
         detectionJob.join();
      }
      catch (PartInitException e)
      {
         e.printStackTrace();
         return false;
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
         return false;
      }
      finally {
         monitor.done();
      }

      return true;
   }


   /**
    * Creates the job that prepares the detection engines and configures it with the appropriate
    * strategy. If the user selected to use existing detection engines, the job is configured with a
    * strategy that loads these engines. If no existing engines should be used, a strategy that
    * generates new engines is chosen.
    * 
    * @return A {@link org.eclipse.core.runtime.jobs.Job Job} that prepares the pattern detection
    *         engines according to a chosen strategy.
    */
   protected PrepareDetectionEnginesJob createPrepareEnginesJob()
   {
      AbstractEnginePreparationStrategy strategy;

      if (mainWizardPage.isUseExistingEngines())
      {
         strategy = new LoadExistingEnginesStrategy(mainWizardPage.getCatalogResource(),
               mainWizardPage.getEnginesResource());
      }
      else
      {
         strategy = new GenerateNewEnginesStrategy(mainWizardPage.getCatalogResource());
      }

      return new PrepareDetectionEnginesJob(strategy, mainWizardPage.getReportLevel());
   }


   /**
    * Shorthand for creating a pattern detection job with the configuration selected by the user.
    * 
    * @return A {@link org.eclipse.core.runtime.jobs.Job Job} that detects patterns in a host graph
    *         by executing the previously prepared engines.
    */
   protected DetectPatternsJob createPatternDetectionJob()
   {
      return createPatternDetectionJob(mainWizardPage.isSearchForAdditionals(), mainWizardPage.getEvaluator(),
            mainWizardPage.getReportLevel());
   }


   /**
    * @see {@link #createPatternDetectionJob()}
    * 
    * @param searchForAdditionals Indicates if additional elements should be searched for.
    * @param evaluator The evaluator that calculates the resemblance of the detected occurrences to
    *           the actual patterns.
    * @param reportLevel The report level, as selected by the user.
    * @return A {@link org.eclipse.core.runtime.jobs.Job Job} that detects patterns in a host graph
    *         by executing the previously prepared engines.
    */
   protected DetectPatternsJob createPatternDetectionJob(boolean searchForAdditionals, IAnnotationEvaluator evaluator,
         ReportLevel reportLevel)
   {

      final DetectPatternsJob job = new DetectPatternsJob(mainWizardPage.getCatalogResource(),
            mainWizardPage.getEnginesResource(), mainWizardPage.getHostGraphResource(), reportLevel);

      job.setAnnotateAdditionalElements(searchForAdditionals);
      job.setEvaluator(evaluator);
      return job;
   }


   /**
    * Tries to open the annotations view when the DetectPatternsJob is run.
    * 
    * @param job The DetectPatternsJob
    * @throws PartInitException If the view that is registered under the corresponding ID is not the
    *            correct one.
    */
   protected void configureAnnotationsView(final DetectPatternsJob job) throws PartInitException
   {
      IViewPart part = null;
      part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(AnnotationView.ID);

      // check for existing annotations view
      if (part == null || !(part instanceof AnnotationView))
      {
         throw new PartInitException("Annotations View can not be initialized correctly.");
      }

      final AnnotationView annotations = (AnnotationView) part;
      PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
      {
         @Override
         public void run()
         {
            if (annotations != null)
            {
               annotations.switchToInference(job.getEngine());
            }
         }
      });
   }


   /**
    * Configures the matching views that visualize the matched patterns and the matched objects
    * after the detection.
    */
   protected void configureMatchingViews()
   {
      final InferenceProgressListener mPatternView = (InferenceProgressListener) getMatchingView(VID_MATCHED_PATTERNS);
      if (mPatternView != null)
      {
         mPatternView.init();
      }

      final InferenceProgressListener mObjectsView = (InferenceProgressListener) getMatchingView(VID_MATCHED_OBJECTS);
      if (mObjectsView != null)
      {
         mObjectsView.init();
      }
   }


   /**
    * Checks whether there are annotations to save and requests the user to confirm the overwrite of
    * those.
    * 
    * @return Returns whether to abort the start of the inference.
    */
   protected boolean abortStartDueToExistingAnnotations()
   {
      // check if there are annotations in the view
      AnnotationView annotations = (AnnotationView) getMatchingView(AnnotationView.ID);
      if (annotations != null && !annotations.isEmpty())
      {
         // configuration data
         Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
         String title = "Overwrite Results";
         String message = "There are already annotated results. Do you want to overwrite them?";
         String[] labels = new String[] { "Save", "Overwrite", "Abort" };

         // create the dialog
         MessageDialog dialog = new MessageDialog(shell, title, null, message, MessageDialog.WARNING, labels, 2);

         int result = dialog.open();

         switch (result)
         {
            case 0:
               // save
               IWizard wizard = new SaveAnnotationsWizard(annotations);
               if (new WizardDialog(shell, wizard).open() == Window.OK)
               {
                  return false;
               }
               else
               {
                  return abortStartDueToExistingAnnotations();
               }
            case 1:
               // start
               return false;
            default:
               // abort
               return true;
         }
      }

      return false;
   }


   protected void storePageSettings()
   {
      // save page settings
      mainWizardPage.saveSettings();
   }


   protected IViewPart getMatchingView(String id)
   {
      return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(id);
   }
}
