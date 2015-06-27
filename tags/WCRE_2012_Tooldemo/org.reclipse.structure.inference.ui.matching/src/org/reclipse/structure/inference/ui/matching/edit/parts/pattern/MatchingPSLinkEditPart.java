package org.reclipse.structure.inference.ui.matching.edit.parts.pattern;


import org.eclipse.gef.EditPart;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.ui.edit.parts.PSLinkEditPart;



public class MatchingPSLinkEditPart extends PSLinkEditPart
{

   @Override
   public void refreshVisuals()
   {
      super.refreshVisuals();

      refreshColor(getTarget());
      refreshColor(getSource());
   }


   private void refreshColor(EditPart editPart)
   {
      if (editPart != null)
      {
         Object model = editPart.getModel();
         if (model instanceof HierarchicalNode
               && ((HierarchicalNode) model).getModel() instanceof PSNode)
         {
            // TODO
            // PSNode real = (PSNode) ((HierarchicalNode) model).getModel();
            // IFigure figure = getFigure();
            //
            // ASGAnnotation annotation = PatternMatchingView.getCurrent();
            // String satisfaction = SatisfactionUtil.getSatisfaction(real,
            // annotation);
            // SatisfactionVisualizationUtil.setColor((PSNode) model, figure, editPart.getParent(),
            // false, satisfaction);
            // SatisfactionVisualizationUtil.setColor((PSNode) model, readingDirectionArrow,
            // editPart.getParent(), true, satisfaction);
         }
      }
   }
}
