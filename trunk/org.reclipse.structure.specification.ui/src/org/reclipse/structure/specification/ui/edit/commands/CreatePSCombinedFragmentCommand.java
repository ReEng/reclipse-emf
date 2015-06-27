package org.reclipse.structure.specification.ui.edit.commands;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreatePSCombinedFragmentCommand extends
      AbstractCreatePSCombinedFragmentItemCommand
{

   private List<PSCombinedFragmentItem> modelChildren;

   private boolean add;


   public CreatePSCombinedFragmentCommand(AbstractDiagramEditPart host,
         PSCombinedFragment fragment, Rectangle bound, List<Node> children,
         boolean add)
   {
      super(host, bound, "Create Combined Fragment", true, children);

      this.add = add;
      modelElement = fragment;

      // cache model children
      modelChildren = new ArrayList<PSCombinedFragmentItem>();
      for (Node node : children)
      {
         if (node.getModel() instanceof PSCombinedFragmentItem)
         {
            modelChildren.add((PSCombinedFragmentItem) node.getModel());
         }
      }
   }


   @Override
   public void redoModel()
   {
      // add to pattern
      getModel().setPatternSpecification(pattern);
      getModel().setName(PSConstants.DEFAULT__MODEL_NAME_PREFIX__FRAGMENT + pattern.getCombinedFragments().size());

      // add to direct parent fragment
      if (fragment != null && !add)
      {
         // add parent
         getModel().getParents().add(fragment);

         // remove old parents
         fragment.getChildren().removeAll(modelChildren);
      }
      
      // add children
      getModel().getChildren().addAll(modelChildren);
   }


   @Override
   public void undoModel()
   {
      // remove children
      getModel().getChildren().removeAll(modelChildren);

      // remove from direct parent fragment
      if (fragment != null && !add)
      {
         // add old parents
         fragment.getChildren().addAll(modelChildren);

         // remove parent
         getModel().getParents().remove(fragment);
      }

      // remove from pattern specification
      getModel().setPatternSpecification(null);
   }


   @Override
   protected PSCombinedFragment getModel()
   {
      return (PSCombinedFragment) modelElement;
   }
}
