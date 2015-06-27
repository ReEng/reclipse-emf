package org.reclipse.structure.specification.ui.edit.commands;


import java.util.ArrayList;
import java.util.List;

import org.fujaba.commons.edit.commands.AbstractDeleteNodeCommand;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;



/**
 * TODO: describe type
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class DeletePSCombinedFragmentItemCommand extends
      AbstractDeleteNodeCommand
{

   private PSPatternSpecification pattern;

   private List<PSCombinedFragment> fragments;


   /**
    * The default constructor.
    * 
    * @param node The (view) node to delete.
    * @param viewParent The parent of the (view) node.
    */
   public DeletePSCombinedFragmentItemCommand(Node node,
         HierarchicalNode viewParent)
   {
      super("delete element", node, viewParent);
      modelElement = node.getModel();

      // cache pattern
      if (modelElement instanceof PSNode)
      {
         PSNode psn = (PSNode) modelElement;
         pattern = psn.getPatternSpecification();
      }
      else if (modelElement instanceof PSSpecificationConstraint)
      {
         PSSpecificationConstraint pssc = (PSSpecificationConstraint) modelElement;
         pattern = pssc.getPatternSpecification();
      }
      else if (modelElement instanceof PSCombinedFragment)
      {
         PSCombinedFragment pscf = (PSCombinedFragment) modelElement;
         pattern = pscf.getPatternSpecification();
      }

      // cache fragments
      if (getModel().getParents().size() > 0)
      {
         fragments = new ArrayList<PSCombinedFragment>();
         for (PSCombinedFragment parent : getModel().getParents())
         {
            fragments.add(parent);
         }
      }
   }


   @Override
   protected void redoModel()
   {
      // remove fragment containing
      if (fragments != null)
      {
         for (PSCombinedFragment frag : fragments)
         {
            frag.getChildren().remove(getModel());
         }
      }

      // remove pattern containing
      if (modelElement instanceof PSNode)
      {
         pattern.getNodes().remove(getModel());
      }
      else if (modelElement instanceof PSSpecificationConstraint)
      {
         pattern.getConstraints().remove(getModel());
      }
      else if (modelElement instanceof PSCombinedFragment)
      {
         pattern.getCombinedFragments().remove(getModel());
      }
   }


   @Override
   protected void undoModel()
   {
      // re-add to pattern
      if (modelElement instanceof PSNode)
      {
         // FIXME: re-add connections as well
         pattern.getNodes().add((PSNode) getModel());
      }
      else if (modelElement instanceof PSSpecificationConstraint)
      {
         pattern.getConstraints().add((PSSpecificationConstraint) getModel());
      }
      else if (modelElement instanceof PSCombinedFragment)
      {
         pattern.getCombinedFragments().add((PSCombinedFragment) getModel());
      }

      // re-add to fragments
      if (fragments != null)
      {
         for (PSCombinedFragment frag : fragments)
         {
            frag.getChildren().add(getModel());
         }
      }
   }


   @Override
   protected PSCombinedFragmentItem getModel()
   {
      return (PSCombinedFragmentItem) super.getModel();
   }
}
