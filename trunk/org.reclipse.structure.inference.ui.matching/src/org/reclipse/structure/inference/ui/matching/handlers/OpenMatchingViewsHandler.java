package org.reclipse.structure.inference.ui.matching.handlers;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.ui.matching.views.ObjectMatchingView;
import org.reclipse.structure.inference.ui.matching.views.PatternMatchingView;


public class OpenMatchingViewsHandler extends AbstractHandler
{

   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      IWorkbenchPage workbenchPage = HandlerUtil
            .getActiveWorkbenchWindowChecked(event).getActivePage();
      try
      {
         workbenchPage.showView(ObjectMatchingView.ID);
         workbenchPage.showView(PatternMatchingView.ID);
      }
      catch (PartInitException e)
      {
         MessageDialog.openWarning(Display.getCurrent().getActiveShell(),
               "Could not open view", e.getMessage());
         e.printStackTrace();
      }

      return null;
   }

}
