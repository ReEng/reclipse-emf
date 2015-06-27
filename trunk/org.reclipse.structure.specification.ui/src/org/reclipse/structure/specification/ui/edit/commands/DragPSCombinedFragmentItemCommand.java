package org.reclipse.structure.specification.ui.edit.commands;


import org.eclipse.emf.ecore.EObject;
import org.fujaba.commons.edit.commands.AbstractViewCommand;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;



/**
 * This command is used to move a combined fragment item from its current fragment to the parent
 * (pattern spec. or an other fragment).
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class DragPSCombinedFragmentItemCommand extends AbstractViewCommand
{

   private PSCombinedFragment modelParent;

   private EObject newParent;

   private HierarchicalNode newViewParent;


   /**
    * The default constructor.
    * 
    * @param node The (view) node to move.
    * @param parent The parent of the (view) node.
    */
   public DragPSCombinedFragmentItemCommand(Node node, HierarchicalNode parent)
   {
      super("move item to next parent", parent);
      viewElement = node;

      modelParent = (PSCombinedFragment) parent.getModel();
      modelElement = node.getModel();
   }


   @Override
   protected void redoModel()
   {
      if (newParent == null)
      {
         // save parent fragment
         if (!modelParent.getParents().isEmpty())
         {
            newParent = modelParent.getParents().get(0);
         }
         else
         {
            newParent = modelParent.getPatternSpecification();
         }
      }

      getModel().getParents().remove(modelParent);

      if (newParent instanceof PSCombinedFragment)
      {
         getModel().getParents().add((PSCombinedFragment) newParent);
      }
   }


   @Override
   protected void undoModel()
   {
      if (newParent instanceof PSCombinedFragment)
      {
         getModel().getParents().remove((PSCombinedFragment) newParent);
      }

      getModel().getParents().add(modelParent);
   }


   @Override
   protected void redoView()
   {
      if (newViewParent == null)
      {
         newViewParent = viewParent.getParent();
      }

      getView().setParent(newViewParent);
   }


   @Override
   protected void undoView()
   {
      getView().setParent(viewParent);
   }


   @Override
   protected PSCombinedFragmentItem getModel()
   {
      return (PSCombinedFragmentItem) super.getModel();
   }


   @Override
   protected HierarchicalNode getView()
   {
      return (HierarchicalNode) super.getView();
   }
}
