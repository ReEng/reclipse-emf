package org.reclipse.structure.inference.ui.matching.edit.parts.pattern;


import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionUtil;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionVisualizationUtil;
import org.reclipse.structure.inference.ui.matching.views.PatternMatchingView;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.ui.edit.parts.PSSpecificationConstraintEditPart;



public class MatchingPSSpecificationConstraintEditPart extends
      PSSpecificationConstraintEditPart
{

   // FIXME
   String satisfaction = "dissatisfied";


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSSpecificationConstraint model = getRealModel();
      LabelFigure figure = (LabelFigure) getFigure();

      ASGAnnotation annotation = PatternMatchingView.getCurrent();
      satisfaction = SatisfactionUtil.getSatisfaction(model, annotation);

      SatisfactionVisualizationUtil.setColor(model, figure, getParent(),
            satisfaction);
   }


   @Override
   protected String getConstraintSuffix()
   {
      PSSpecificationConstraint model = getRealModel();
      String suffix = super.getConstraintSuffix();

      if (model.isAdditional())
      {
         // FIXME
         // suffix = SuffixExtender.extend(suffix, satisfaction);
      }

      return suffix;
   }
}
