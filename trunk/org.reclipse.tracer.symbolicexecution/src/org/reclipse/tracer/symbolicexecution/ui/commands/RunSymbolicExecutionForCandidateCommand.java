package org.reclipse.tracer.symbolicexecution.ui.commands;


import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.input.StructuralAnnotationsReader;
import org.reclipse.behavior.inference.ui.BehaviorInferenceUIPlugin;
import org.reclipse.behavior.inference.ui.views.BehavioralAnalysisResultView;
import org.reclipse.behavior.inference.ui.wizard.BehaviorRecognitionTask;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.tracer.model.tracegraph.xml.ITraceConstants;
import org.reclipse.tracer.symbolicexecution.ui.wizards.RunSymbolicExecutionForCandidateWizard;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class RunSymbolicExecutionForCandidateCommand extends AbstractHandler
{
   public static final String BEHAVIORAL_ANALYSIS_RESULT_FILE_NAME = "BehavioralAnalysisResult.xml";

   public static final String ANNOTATIONS_FILE_NAME = "Annotations";


   private void startDynamicAnalysis(
         IWorkbenchWindow workbenchWindow,
         RunSymbolicExecutionForCandidateWizard runSymbolicExecutionForCandidateWizard)
   {
      ASGAnnotation annotation = runSymbolicExecutionForCandidateWizard
            .getAnnotation();
      BehaviorRecognitionTask behaviorRecognitionTask = null;
      Set<StructuralAnnotation> annotations = StructuralAnnotationsReader
            .load(annotation);
      BehavioralPatternsCatalog catalog = runSymbolicExecutionForCandidateWizard
            .getCatalog();
      boolean logTraces = true;
      String defaultResultFileName = BEHAVIORAL_ANALYSIS_RESULT_FILE_NAME;
      String traceFileName = runSymbolicExecutionForCandidateWizard
            .getTraceFileName() + ITraceConstants.XML_FILE_SUFFIX;
      behaviorRecognitionTask = new BehaviorRecognitionTask(annotations,
            traceFileName, catalog, defaultResultFileName, logTraces);
      try
      {
         workbenchWindow.run(true, true, behaviorRecognitionTask);
      }
      catch (InvocationTargetException e)
      {
         e.printStackTrace();
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }

      openResultView(behaviorRecognitionTask);
      try
      {
         ResourcesPlugin.getWorkspace().getRoot()
               .refreshLocal(IResource.DEPTH_INFINITE, null);
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
   }


   private void openResultView(BehaviorRecognitionTask behaviorRecognitionTask)
   {
      BehavioralAnalysisResultView resultView = null;

      final IWorkbenchWindow window = PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow();
      if (window != null)
      {
         final IWorkbenchPage activePage = window.getActivePage();
         if (activePage != null)
         {
            try
            {
               resultView = (BehavioralAnalysisResultView) activePage
                     .showView("org.reclipse.ui.views.behavior.inference");
               resultView.setModel(behaviorRecognitionTask.getAnnotations());
            }
            catch (final PartInitException exception)
            {
               BehaviorInferenceUIPlugin.logError("Problems occured while "
                     + "opening Behavioral Analysis Result view.", exception);
            }
         }
      }
   }


   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      // start wizard for symbolic execution
      ASGAnnotation anno = (ASGAnnotation) ((TreeSelection) HandlerUtil
            .getActiveMenuSelection(event)).getFirstElement();
      RunSymbolicExecutionForCandidateWizard runSymbolicExecutionForCandidateWizard = new RunSymbolicExecutionForCandidateWizard(
            anno);
      WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event),
            runSymbolicExecutionForCandidateWizard);
      int runSymbolicExecutionForCandidateWizardResult = dialog.open();
      if (runSymbolicExecutionForCandidateWizardResult == WizardDialog.OK)
      {
         // ask for dynamic analysis
         String titel = "Start Dynamic Analysis";
         String message = "Do you want to start a dynamic analysis for this candidate? ";
         MessageDialog askForDynamicAnalysisDialog = new MessageDialog(
               HandlerUtil.getActiveShell(event), titel, null, message,
               MessageDialog.QUESTION, new String[] { "Yes", "No" }, 0);
         int askForDynamicAnalysisDialogResult = askForDynamicAnalysisDialog
               .open();
         if (askForDynamicAnalysisDialogResult == MessageDialog.OK)
         {
            // start dynamic analysis
            startDynamicAnalysis(HandlerUtil.getActiveWorkbenchWindow(event),
                  runSymbolicExecutionForCandidateWizard);

         }
      }

      return null;
   }


}
