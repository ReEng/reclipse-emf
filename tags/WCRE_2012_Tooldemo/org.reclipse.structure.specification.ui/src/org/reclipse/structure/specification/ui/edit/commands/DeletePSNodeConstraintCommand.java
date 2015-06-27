package org.reclipse.structure.specification.ui.edit.commands;


import org.fujaba.commons.edit.commands.AbstractDeleteNodeCommand;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;



/**
 * TODO: describe type
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class DeletePSNodeConstraintCommand extends AbstractDeleteNodeCommand
{

   private PSCombinedFragmentItem modelParent;


   /**
    * The default constructor.
    * 
    * @param node The (view) node to delete.
    * @param viewParent The parent of the (view) node.
    */
   public DeletePSNodeConstraintCommand(Node node, HierarchicalNode viewParent)
   {
      super("delete node constraint", node, viewParent);
      modelElement = node.getModel();

      modelParent = (PSCombinedFragmentItem) viewParent.getModel();
   }


   @Override
   protected void redoModel()
   {
      if (modelParent instanceof PSNode)
      {
         PSNode node = (PSNode) modelParent;
         node.getNodeConstraints().remove(getModel());
      } else if (modelParent instanceof PSCombinedFragment)
      {
         PSCombinedFragment fragment = (PSCombinedFragment) modelParent;
         fragment.setConstraint(null);
      }
   }


   @Override
   protected void undoModel()
   {
      if (modelParent instanceof PSNode)
      {
         PSNode node = (PSNode) modelParent;
         node.getNodeConstraints().add(getModel());
      } else if (modelParent instanceof PSCombinedFragment)
      {
         PSCombinedFragment fragment = (PSCombinedFragment) modelParent;
         fragment.setConstraint(getModel());
      }
   }


   @Override
   protected PSNodeConstraint getModel()
   {
      return (PSNodeConstraint) super.getModel();
   }
}
