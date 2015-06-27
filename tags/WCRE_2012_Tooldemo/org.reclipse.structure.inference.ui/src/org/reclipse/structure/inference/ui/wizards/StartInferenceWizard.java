package org.reclipse.structure.inference.ui.wizards;


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
import org.reclipse.structure.InferenceUIPlugin;
import org.reclipse.structure.inference.DetectPatternsJob;
import org.reclipse.structure.inference.notification.InferenceProgressListener;
import org.reclipse.structure.inference.ui.InferenceConstants;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;


/**
 * This class creates the wizard which is used to configure and start a structural patterns detection.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class StartInferenceWizard extends Wizard
{
   private static final String VID_MATCHED_PATTERNS = "org.reclipse.ui.views.structure.inference.matching.pattern"; //$NON-NLS-1$

   private static final String VID_MATCHED_OBJECTS = "org.reclipse.ui.views.structure.inference.matching.ast"; //$NON-NLS-1$

   protected StartInferenceWizardPage page;


   public StartInferenceWizard()
   {
      super();

      // configure
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
      page = new StartInferenceWizardPage("Structural Patterns Detection");
      addPage(page);
   }


   @Override
   public boolean performFinish()
   {
      // let the user confirm annotation result overwriting
      if (abortStarting())
      {
         return false;
      }

      // save settings
      storePageSettings();

      // cache views
      IViewPart part = null;
      try
      {
         part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(AnnotationView.ID);
      }
      catch (PartInitException e)
      {
         e.printStackTrace();
         return false;
      }

      // check for existing annotations view
      if (part == null || !(part instanceof AnnotationView))
      {
         return false;
      }

      final AnnotationView annotations = (AnnotationView) part;
      final InferenceProgressListener mPatternView = (InferenceProgressListener) getMatchingView(VID_MATCHED_PATTERNS);
      final InferenceProgressListener mObjectsView = (InferenceProgressListener) getMatchingView(VID_MATCHED_OBJECTS);

      // get paths
      String catalogPath = page.getCatalogPath();
      String hostPath = page.getHostPath();
      String enginesPath = page.getEnginesPath();

      // create and configure the job
      final DetectPatternsJob job = new DetectPatternsJob(catalogPath, hostPath, enginesPath, page.getReportLevel());

      job.setAnnotateAdditionalElements(page.isSearchForAdditionals());
      job.setCreateEnginesResource(false);
      job.setEvaluator(page.getEvaluator());
      job.setUseExistingEngines(false);

      // configure view
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

      job.schedule();

      // configure matching views when available
      if (mPatternView != null)
      {
         mPatternView.init();
      }
      if (mObjectsView != null)
      {
         mObjectsView.init();
      }

      return true;
   }


   /**
    * Checks whether there are annotations to save and requests the user to confirm the overwrite of those.
    * 
    * @return Returns whether to abort the start of the inference.
    */
   private boolean abortStarting()
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
                  return abortStarting();
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
      page.saveSettings();
   }


   private IViewPart getMatchingView(String id)
   {
      return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(id);
   }
}
