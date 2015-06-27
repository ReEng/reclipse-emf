package org.reclipse.structure.inference.ui.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;
import org.reclipse.structure.inference.ui.wizards.LoadAnnotationsWizard;


public class LoadAnnotationsHandler extends AbstractHandler
{
   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      // get some
      Shell shell = HandlerUtil.getActiveShell(event);
      IWorkbenchPart part = HandlerUtil.getActivePart(event);

      // only create when there is a view
      if (part instanceof AnnotationView)
      {
         // create wizard
         IWizard wizard = new LoadAnnotationsWizard((AnnotationView) part);
         WizardDialog dialog = new WizardDialog(shell, wizard);

         // open it
         dialog.open();
      }

      return null;
   }
}
