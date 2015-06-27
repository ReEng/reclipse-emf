package org.reclipse.tracedefinition.editor.wizards;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.reclipse.tracedefinition.editor.TraceDefinitionEditorPlugin;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3735 $ $Date: 2007-09-04 21:05:25 +0200 (Di, 04 Sep 2007) $
 */
public class SplitTraceDefinitionWizard extends Wizard implements
      IWorkbenchWizard
{

   private static final String SPLIT_TRACE_DEFINITION_WIZARD_SECTION = "SplitTraceDefinitionWizard";


   private String selectedFile;

   private FilesWizardPage filesPage;

   private AnnotationsWizardPage annotationsPage;


   public SplitTraceDefinitionWizard()
   {
      setWindowTitle("Trace Definition Splitting Wizard");
      setNeedsProgressMonitor(true);

      final IDialogSettings workbenchSettings = TraceDefinitionEditorPlugin
            .getDefault().getDialogSettings();
      IDialogSettings section = workbenchSettings
            .getSection(SPLIT_TRACE_DEFINITION_WIZARD_SECTION);
      if (section == null)
      {
         section = workbenchSettings
               .addNewSection(SPLIT_TRACE_DEFINITION_WIZARD_SECTION);
      }
      setDialogSettings(section);
   }


   /**
    * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
    *      org.eclipse.jface.viewers.IStructuredSelection)
    */
   public void init(final IWorkbench workbench,
         final IStructuredSelection selection)
   {
      if (selection == null)
      {
         throw new IllegalArgumentException(
               "Argument 'selection' must not be null!");
      }

      final Object selectedElement = selection.getFirstElement();
      if (selectedElement instanceof IFile)
      {
         this.selectedFile = ((IFile) selectedElement).getLocation()
               .toOSString();
      }
   }


   /**
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages()
   {
      this.filesPage = new FilesWizardPage();
      addPage(this.filesPage);

      this.annotationsPage = new AnnotationsWizardPage();
      addPage(this.annotationsPage);
   }


   public FilesWizardPage getFilesWizardPage()
   {
      return this.filesPage;
   }


   public String getSelectedFile()
   {
      return this.selectedFile;
   }


   /**
    * @see org.eclipse.jface.wizard.Wizard#performFinish()
    */
   @Override
   public boolean performFinish()
   {
      try
      {
         this.filesPage.finish();
         this.annotationsPage.finish();

         final TraceDefinitionSplittingTask filterTask = new TraceDefinitionSplittingTask();
         filterTask.setSourceTraceDefinitionFile(this.filesPage
               .getSourceTraceDefinitionFile());
         filterTask.setTargetAnnotationsFile(this.filesPage
               .getTargetAnnotationsFile());
         filterTask.setTargetTraceDefinitionFile(this.filesPage
               .getTargetTraceDefinitionFile());
         filterTask.setTraceDefinitionFileZipped(this.filesPage
               .isTraceDefinitionFileZipped());
         filterTask.setSaveAnnotationFiles(this.filesPage
               .isSaveAnnotationsFile());
         filterTask.setAnnotationsFileZipped(this.filesPage
               .isTargetAnnotationsFileZipped());
         filterTask.setSplittedAnnotations(this.annotationsPage
               .getSplittedAnnotations());

         getContainer().run(true, true, filterTask);
      }
      catch (final InvocationTargetException exception)
      {
         exception.printStackTrace();
      }
      catch (final InterruptedException exception)
      {
         exception.printStackTrace();
      }

      return true;
   }

}
