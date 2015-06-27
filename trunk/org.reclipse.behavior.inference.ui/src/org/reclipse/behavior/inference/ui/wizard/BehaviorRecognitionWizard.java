package org.reclipse.behavior.inference.ui.wizard;


import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.reclipse.behavior.inference.ui.BehaviorInferenceUIPlugin;
import org.reclipse.behavior.inference.ui.views.BehavioralAnalysisResultView;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4186 $ $Date: 2009-12-15 11:42:55 +0100 (Di, 15. Dez 2009) $
 */
public class BehaviorRecognitionWizard extends Wizard implements IWorkbenchWizard
{

   private static final String BEHAVIOR_DETECTION_WIZARD_SECTION = "BehaviorDetectionWizard";


   private BehaviorRecognitionConfigurationPage configurationPage;


   public BehaviorRecognitionWizard()
   {
      setWindowTitle("Start Behavioral Patterns Detection");
      setNeedsProgressMonitor(true);

      final IDialogSettings workbenchSettings = BehaviorInferenceUIPlugin.getDefault().getDialogSettings();
      IDialogSettings section = workbenchSettings.getSection(BEHAVIOR_DETECTION_WIZARD_SECTION);
      if (section == null)
      {
         section = workbenchSettings.addNewSection(BEHAVIOR_DETECTION_WIZARD_SECTION);
      }
      setDialogSettings(section);
   }


   /**
    * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
    *      org.eclipse.jface.viewers.IStructuredSelection)
    */
   public void init(final IWorkbench workbench, final IStructuredSelection selection)
   {
   }


   /**
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages()
   {
      this.configurationPage = new BehaviorRecognitionConfigurationPage();
      addPage(this.configurationPage);
   }


   /**
    * @see org.eclipse.jface.wizard.Wizard#performFinish()
    */
   @Override
   public boolean performFinish()
   {
      try
      {
         this.configurationPage.finish();

         final BehaviorRecognitionTask behaviorRecognitionTask = new BehaviorRecognitionTask(
               this.configurationPage.getStructuralAnnotationsFileName(), this.configurationPage.getTraceFileName(),
               this.configurationPage.getBehavioralPatternsCatalogFileName(),
               this.configurationPage.getBehavioralAnalysisResultFileName(), this.configurationPage.isLogTraces());
         getContainer().run(true, true, behaviorRecognitionTask);

         BehavioralAnalysisResultView resultView = null;

         final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
         if (window != null)
         {
            final IWorkbenchPage activePage = window.getActivePage();
            if (activePage != null)
            {
               try
               {
                  resultView = (BehavioralAnalysisResultView) activePage.showView(BehavioralAnalysisResultView.ID);
                  resultView.setModel(behaviorRecognitionTask.getAnnotations());
               }
               catch (final PartInitException exception)
               {
                  BehaviorInferenceUIPlugin.logError("Problems occured while "
                        + "opening Behavioral Analysis Result view.", exception);
               }
            }
         }
         ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

         return true;
      }
      catch (final Exception exception)
      {
         final IStatus status = new Status(IStatus.ERROR, BehaviorInferenceUIPlugin.PLUGIN_ID, IStatus.ERROR,
               "Exception occured during behavioral patterns detection: ", exception);
         ErrorDialog.openError(getShell(), "Problems occured while " + "running the behavioral patterns detection.",
               null, status);
         BehaviorInferenceUIPlugin.logError("Problems occured while " + "running the behavioral patterns detection.",
               exception);
      }

      return true;
   }

}
