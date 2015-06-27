package org.reclipse.structure.inference.ui.matching.edit.parts.pattern;


import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionUtil;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionVisualizationUtil;
import org.reclipse.structure.inference.ui.matching.views.PatternMatchingView;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.ui.edit.parts.PSAttributeConstraintEditPart;
import org.reclipse.structure.specification.ui.utils.PSConstants;



public class MatchingPSAttributeConstraintEditPart extends PSAttributeConstraintEditPart
{

   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSAttributeConstraint model = getRealModel();
      LabelFigure figure = (LabelFigure) getFigure();

      ASGAnnotation annotation = PatternMatchingView.getCurrent();
      String satisfaction = SatisfactionUtil.getSatisfaction(model, annotation);

      SatisfactionVisualizationUtil.setColor(model, figure, getParent(), satisfaction);
      figure.setSize(figure.getPreferredSize());
   }


   @Override
   protected String getBooleanConstraintSuffix()
   {
      PSAttributeConstraint model = getRealModel();

      StringBuilder text = new StringBuilder();
      if (model.isAdditional() || model.getWeight() != PSConstants.DEFAULT_WEIGHT)
      {
         text.append(" {");
         if (model.isAdditional())
         {
            text.append(PSConstants.LABEL_ADDITIONAL);
         }
         if (model.getWeight() != PSConstants.DEFAULT_WEIGHT)
         {
            if (model.isAdditional())
            {
               text.append(", ");
            }
            text.append("w=" + model.getWeight());
         }

         // get satisfaction
         ASGAnnotation annotation = PatternMatchingView.getCurrent();
         String satisfaction = SatisfactionUtil.getSatisfaction(getRealModel(), annotation);

         text.append(", ");
         text.append(satisfaction);
         text.append("}");
      }

      return text.toString();
   }


   @Override
   protected void createEditPolicies()
   {
      super.createEditPolicies();

      installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new MatchingSelectionEditPolicy(getFigure()));
   }
}
