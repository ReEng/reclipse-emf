package org.reclipse.structure.inference.ui.matching.edit.parts.pattern;


import org.eclipse.gef.EditPolicy;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionUtil;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionVisualizationUtil;
import org.reclipse.structure.inference.ui.matching.views.PatternMatchingView;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.ui.edit.parts.PSObjectEditPart;
import org.reclipse.structure.specification.ui.figures.PSObjectFigure;
import org.reclipse.structure.specification.ui.utils.PSConstants;


public class MatchingPSObjectEditPart extends PSObjectEditPart
{

   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSObject model = getRealModel();
      PSObjectFigure figure = (PSObjectFigure) getFigure();

      if (model.getModifier() == ModifierType.ADDITIONAL
            || SatisfactionVisualizationUtil.isParentDissatisfied(getParent()))
      {
         ASGAnnotation annotation = PatternMatchingView.getCurrent();
         String satisfaction = SatisfactionUtil.getSatisfaction(getRealModel(),
               annotation);

         String tag = "{" + satisfaction + "}";

         String weight = "";
         if (getRealModel().getWeight() != PSConstants.DEFAULT_WEIGHT)
         {
            weight = "{w=" + getRealModel().getWeight() + "}";
         }

         figure.setWeightText(SatisfactionUtil.extend(weight, tag));

         SatisfactionVisualizationUtil.setColor(model, figure, getParent(),
               satisfaction);
      }
   }


   @Override
   protected void createEditPolicies()
   {
      super.createEditPolicies();

      installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
            new MatchingSelectionEditPolicy(getFigure()));
   }
}
