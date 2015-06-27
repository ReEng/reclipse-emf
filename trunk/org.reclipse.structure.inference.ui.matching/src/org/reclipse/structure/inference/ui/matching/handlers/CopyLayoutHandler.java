package org.reclipse.structure.inference.ui.matching.handlers;


import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.fujaba.commons.edit.parts.AbstractEdgeEditPart;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.inference.ui.matching.views.AbstractMatchingView;



/**
 * This handler is used to copy the current layout to all other annotations.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CopyLayoutHandler extends AbstractHandler
{


   private static List<?> getSelection(ExecutionEvent event)
   {
      ISelection selection = HandlerUtil.getCurrentSelection(event);

      // preconditions
      if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
      {
         return Collections.emptyList();
      }

      return ((IStructuredSelection) selection).toList();
   }


   private static int getConfirmation(ExecutionEvent event)
   {
      // prepare attributes
      Shell shell = HandlerUtil.getActiveShell(event);
      String title = "Confirm layout copy";
      Image image = MessageDialog.getDefaultImage();
      String message = "Do you really want to discard the layouts of the other annotations?";
      String[] labels = new String[] { "Copy To All", "Copy To Non-Opened",
            "Abort" };

      // create dialog
      MessageDialog confirmation = new MessageDialog(shell, title, image,
            message, MessageDialog.CONFIRM, labels, 0);

      // return the result
      return confirmation.open();
   }


   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      // get selected node
      HierarchicalNode root = null;

      // search in selection for the right one
      for (Object selected : getSelection(event))
      {
         // save any node
         if (selected instanceof AbstractNodeEditPart)
         {
            Node possible = ((AbstractNodeEditPart) selected).getModel();
            if (possible instanceof HierarchicalNode)
            {
               root = (HierarchicalNode) possible;
            }
            else
            {
               root = possible.getParent();
            }
            break;
         }

         // save the parent of the edge
         if (selected instanceof AbstractEdgeEditPart)
         {
            root = ((AbstractEdgeEditPart) selected).getModel().getParent();
            break;
         }
      }

      if (root != null)
      {
         // search root node
         while (root.getParent() != null)
         {
            root = root.getParent();
         }

         // show confirmation dialog
         int result = getConfirmation(event);
         if (result == 0)
         {
            // copy to all
            AbstractMatchingView.setLayout(HandlerUtil.getActivePart(event),
                  root, true);
         }
         else if (result == 1)
         {
            // copy to non-opened
            AbstractMatchingView.setLayout(HandlerUtil.getActivePart(event),
                  root, false);
         }
      }

      return null;
   }
}
