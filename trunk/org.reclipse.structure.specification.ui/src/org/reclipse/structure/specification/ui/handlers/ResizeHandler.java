package org.reclipse.structure.specification.ui.handlers;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.fujaba.commons.edit.commands.SetConstraintCommand;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSCombinedFragmentEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSPatternSpecificationEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSSpecificationConstraintEditPart;



/**
 * This handler is used to execute resizing of parts.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class ResizeHandler extends AbstractHandler
{
   protected String TO_MINIMUM = "org.reclipse.structure.specification.ui.commands.ResizeToMinimumSize";

   protected String TO_PREFERRED = "org.reclipse.structure.specification.ui.commands.ResizeToPreferredSize";


   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      // prepare action
      ResizeFigureAction action = new ResizeFigureAction(event, event
            .getCommand().getId());

      action.run();

      return null;
   }

   /**
    * This creation factory is used by the creation request.
    * 
    * @author harka
    * @author Last editor: $Author$
    * @version $Revision$ $Date$
    */
   private class ResizeFigureAction extends SelectionAction
   {

      private boolean minimum;

      private ExecutionEvent event;


      /**
       * Default constructor.
       * 
       * @param part The workbench part.
       */
      public ResizeFigureAction(ExecutionEvent event, String commandId)
      {
         super(HandlerUtil.getActivePart(event));
         this.event = event;
         if (commandId.equals(TO_MINIMUM))
         {
            minimum = true;
         }
      }


      @Override
      public void run()
      {
         // create a compound command
         CompoundCommand cc = new CompoundCommand();

         // get selection
         if (HandlerUtil.getCurrentSelection(event) instanceof IStructuredSelection)
         {
            IStructuredSelection selection = (IStructuredSelection) HandlerUtil
                  .getCurrentSelection(event);

            // collect resize commands
            for (final Object o : selection.toList())
            {
               if (o instanceof PSPatternSpecificationEditPart)
               {
                  PSPatternSpecificationEditPart root = (PSPatternSpecificationEditPart) o;
                  for (Object child : root.getChildren())
                  {
                     addCommands(cc, (AbstractNodeEditPart) child);
                  }
               }
            }

            execute(cc.unwrap());
         }
      }


      private CompoundCommand addCommands(CompoundCommand cc,
            AbstractNodeEditPart ep)
      {
         if (ep instanceof PSCombinedFragmentEditPart)
         {
            for (Object child : ep.getChildren())
            {
               addCommands(cc, (AbstractNodeEditPart) child);
            }
         }
         else
         {
            if (!(ep instanceof PSSpecificationConstraintEditPart))
               cc.add(createResizeCommand(ep, minimum));
         }
         return cc;
      }


      @Override
      protected boolean calculateEnabled()
      {
         return true;
      }
   }


   public Command createResizeCommand(AbstractNodeEditPart part, boolean minimum)
   {
      Dimension dim;
      if (!minimum)
      {
         dim = part.getPreferredSize();
      }
      else
      {
         dim = part.getMinimumSize();
      }

      int x = part.getModel().getX();
      int y = part.getModel().getY();

      return new SetConstraintCommand(part, new Rectangle(x, y, dim.width,
            dim.height));
   }
}
