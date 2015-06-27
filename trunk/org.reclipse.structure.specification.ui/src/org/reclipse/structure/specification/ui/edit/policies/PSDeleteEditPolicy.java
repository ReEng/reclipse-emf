package org.reclipse.structure.specification.ui.edit.policies;


import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.fujaba.commons.edit.commands.SetConstraintCommand;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.edit.parts.AbstractEdgeEditPart;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.fujaba.commons.notation.Edge;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.ui.edit.commands.DeletePSCombinedFragmentItemCommand;
import org.reclipse.structure.specification.ui.edit.commands.DeletePSConnectionCommand;
import org.reclipse.structure.specification.ui.edit.commands.DeletePSNodeConstraintCommand;
import org.reclipse.structure.specification.ui.edit.commands.DragPSCombinedFragmentItemCommand;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This edit policy delivers a delete command for multiple selected elements.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSDeleteEditPolicy extends ComponentEditPolicy
{

   @Override
   protected Command createDeleteCommand(GroupRequest request)
   {
      if (request.getEditParts().size() > 0)
      {
         // summarize delete commands for all selected parts
         CompoundCommand compound = new CompoundCommand();

         for (Object part : request.getEditParts())
         {
            if (part instanceof AbstractDiagramEditPart)
            {
               // all container parts
               HierarchicalNode view = ((AbstractDiagramEditPart) part)
                     .getModel();
               HierarchicalNode parent = view.getParent();
               EObject model = view.getModel();

               if (model instanceof PSCombinedFragment)
               {
                  compound
                        .add(getPSCombinedFragmentDeleteCommand((AbstractDiagramEditPart) part, ((AbstractDiagramEditPart) part).getParent()));
               }
               else if (model instanceof PSNode
                     && !(model instanceof PSAnnotation && ModelHelper
                           .isCreate((PSAnnotation) model)))
               {
                  compound.add(getPSNodeDeleteCommand(view, parent));
               }
            }
            else if (part instanceof AbstractNodeEditPart)
            {
               // all non-container parts
               Node view = ((AbstractNodeEditPart) part).getModel();
               HierarchicalNode parent = view.getParent();
               EObject model = view.getModel();

               if (model instanceof PSSpecificationConstraint)
               {
                  compound.add(getPSSpecificationConstraintDeleteCommand(view,
                        parent));
               }
               else if (model instanceof PSNodeConstraint)
               {
                  compound.add(getPSNodeConstraintDeleteCommand(view, parent));
               }
            }
            else if (part instanceof AbstractEdgeEditPart)
            {
               Edge view = ((AbstractEdgeEditPart) part).getModel();
               compound.add(getPSConnectionDeleteCommand(view));
            }
         }

         return compound.unwrap();
      }

      return null;
   }


   /**
    * @param view The view model.
    * @param parent The model element.
    * @return Returns the command to delete the node constraint.
    */
   private Command getPSNodeConstraintDeleteCommand(Node view,
         HierarchicalNode parent)
   {
      return new DeletePSNodeConstraintCommand(view, parent);
   }


   /**
    * @param view The view model.
    * @param modelElement The model element.
    * @return Returns the command to delete the connection.
    */
   private Command getPSConnectionDeleteCommand(Edge view)
   {
      return new DeletePSConnectionCommand(view);
   }


   /**
    * @param node The view model.
    * @param modelElement The model element.
    * @return Returns the command to delete the combined fragment.
    */
   private Command getPSCombinedFragmentDeleteCommand(AbstractDiagramEditPart part,
         EditPart parent)
   {
      CompoundCommand compound = new CompoundCommand();

      for (Object child : part.getChildren())
      {
         if (child instanceof AbstractNodeEditPart)
         {
            AbstractNodeEditPart cPart = (AbstractNodeEditPart) child;

            // filter fragment constraint
            if (!(cPart.getRealModel() instanceof PSNodeConstraint))
            {
               // move visual
               Rectangle nRect = getRectangle(cPart.getModel());
               nRect.x += (part.getModel().getX() + 1);
               nRect.y += (part.getModel().getY() + 20);
               compound.add(new DragPSCombinedFragmentItemCommand(cPart
                     .getModel(), part.getModel()));
               compound.add(new SetConstraintCommand(cPart, nRect));
            }
         }
      }

      // delete fragment
      if (parent != null && parent instanceof AbstractDiagramEditPart)
      {
         compound.add(new DeletePSCombinedFragmentItemCommand(part.getModel(),
               ((AbstractDiagramEditPart) parent).getModel()));
      }

      return compound;
   }

   private Rectangle getRectangle(Node model)
   {
      int x = model.getX();
      int y = model.getY();
      int w = model.getWidth();
      int h = model.getHeight();

      return new Rectangle(x, y, w, h);
   }

   /**
    * @param node The view model.
    * @param modelElement The model element.
    * @return Returns the command to delete the fragment constraint.
    */
   private Command getPSSpecificationConstraintDeleteCommand(Node node,
         HierarchicalNode parent)
   {
      return new DeletePSCombinedFragmentItemCommand(node, parent);
   }


   /**
    * @param node The view model.
    * @param modelElement The model element.
    * @return Returns the command to delete the pattern node.
    */
   private Command getPSNodeDeleteCommand(HierarchicalNode node,
         HierarchicalNode parent)
   {
      CompoundCommand cmd = new CompoundCommand();

      for (Node constraints : node.getNodes())
      {
         cmd.add(getPSNodeConstraintDeleteCommand(constraints, node));
      }

      // add source edges
      for (Edge source : node.getIncoming())
      {
         cmd.add(getPSConnectionDeleteCommand(source));
      }

      // add target edges
      for (Edge target : node.getOutgoing())
      {
         cmd.add(getPSConnectionDeleteCommand(target));
      }

      // add node
      cmd.add(new DeletePSCombinedFragmentItemCommand(node, parent));

      return cmd.unwrap();
   }
}
