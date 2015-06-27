package org.reclipse.structure.inference.ui.matching.edit.parts.pattern;


import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.ui.edit.parts.PSMetricConstraintEditPart;



public class MatchingPSMetricConstraintEditPart extends
      PSMetricConstraintEditPart
{

   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSMetricConstraint model = getRealModel();
      LabelFigure figure = (LabelFigure) getFigure();
      StringBuilder text = new StringBuilder();

      // add name
      if (model.getName() != null && model.getName().length() > 0)
      {
         text.append(model.getName() + ": ");
      }

      text.append(model.getMetricAcronym());
      text.append(" " + getOperatorString(model.getOperator()) + " ");
      text.append((model.getValueExpression() != null && model
            .getValueExpression().length() > 0) ? model.getValueExpression()
            : "null");
      text.append(getBooleanConstraintSuffix());

      figure.setText(text.toString());
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
            new MatchingSelectionEditPolicy(getFigure()));
   }
}
