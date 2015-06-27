package org.reclipse.structure.inference.ui.matching.edit.parts.object;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.metrics.extensionpoints.MetricUtil;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.util.BoundingUtil;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.ui.edit.parts.PSMetricConstraintEditPart;


public class MatchingPSMetricConstraintEditPart extends PSMetricConstraintEditPart
{
   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSMetricConstraint model = getRealModel();
      LabelFigure figure = (LabelFigure) getFigure();

      StringBuilder text = new StringBuilder();
      if (model.getName() != null && !model.getName().trim().isEmpty())
      {
         text.append(model.getName());
         text.append(": ");
      }

      EObject bound = BoundingUtil.getFirstBound(model.getNode());
      double value = MetricUtil.getMetricValue(bound, model.getMetricAcronym());

		if ("SIZE".equals(model.getMetricAcronym()))
		{
			value = BoundingUtil.getBound(model.getNode()).size();
		}

      text.append(model.getMetricAcronym());
      text.append(" = ");
      text.append(String.valueOf(value));

      figure.setText(text.toString());
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new MatchingSelectionEditPolicy(getFigure()));
   }
}
