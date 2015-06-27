package org.reclipse.tracedefinition.editor.actions;


import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.reclipse.tracedefinition.editor.wizards.SplitTraceDefinitionWizard;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3637 $ $Date: 2007-06-13 16:12:15 +0200 (Mi, 13 Jun 2007) $
 */
public class FilterTraceDefinitionAction implements IObjectActionDelegate
{

   private IWorkbenchPart targetPart;

   private ISelection selection;


   /**
    * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
    *      org.eclipse.ui.IWorkbenchPart)
    */
   public void setActivePart(IAction action, IWorkbenchPart targetPart)
   {
      this.targetPart = targetPart;
   }


   /**
    * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
    */
   public void run(IAction action)
   {
      SplitTraceDefinitionWizard wizard = new SplitTraceDefinitionWizard();
      wizard.init(PlatformUI.getWorkbench(),
            (IStructuredSelection) this.selection);

      WizardDialog dialog = new WizardDialog(this.targetPart.getSite()
            .getShell(), wizard);

      dialog.open();
   }


   /**
    * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
    *      org.eclipse.jface.viewers.ISelection)
    */
   public void selectionChanged(IAction action, ISelection selection)
   {
      this.selection = selection;
   }

}
