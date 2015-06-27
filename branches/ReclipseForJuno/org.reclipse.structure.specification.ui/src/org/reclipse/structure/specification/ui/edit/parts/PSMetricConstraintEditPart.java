package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.emf.common.notify.Notification;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.SpecificationPackage;



/**
 * This is the edit part for the model object {@link PSMetricConstraint}.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSMetricConstraintEditPart extends
      AbstractPSNodeConstraintEditPart
{

   @Override
   public PSMetricConstraint getRealModel()
   {
      return (PSMetricConstraint) super.getRealModel();
   }


   @Override
   public void notifyChanged(Notification notification)
   {
      int fid = notification.getFeatureID(PSMetricConstraint.class);

      if (SpecificationPackage.PS_METRIC_CONSTRAINT__ADDITIONAL == fid
            || SpecificationPackage.PS_METRIC_CONSTRAINT__METRIC_ACRONYM == fid
            || SpecificationPackage.PS_METRIC_CONSTRAINT__NAME == fid
            || SpecificationPackage.PS_METRIC_CONSTRAINT__OPERATOR == fid
            || SpecificationPackage.PS_METRIC_CONSTRAINT__VALUE_EXPRESSION == fid
            || SpecificationPackage.PS_METRIC_CONSTRAINT__WEIGHT == fid)
      {
         refreshVisuals();
      }

      super.notifyChanged(notification);
   }


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
}
