package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.SpecificationPackage;



/**
 * This is the edit part for the model object {@link PSAttributeConstraint}.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSAttributeConstraintEditPart extends
      AbstractPSNodeConstraintEditPart
{

   @Override
   public PSAttributeConstraint getRealModel()
   {
      return (PSAttributeConstraint) super.getRealModel();
   }


   @Override
   public void notifyChanged(Notification notification)
   {
      int fid = notification.getFeatureID(PSAttributeConstraint.class);

      if (SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ADDITIONAL == fid
            || SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE == fid
            || SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NAME == fid
            || SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__OPERATOR == fid
            || SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__VALUE_EXPRESSION == fid
            || SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__WEIGHT == fid)
      {
         refreshVisuals();
      }

      super.notifyChanged(notification);
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSAttributeConstraint model = getRealModel();
      LabelFigure figure = (LabelFigure) getFigure();
      StringBuilder text = new StringBuilder();

      if (model.getAttribute() != null)
      {
         EAttribute attr = model.getAttribute();
         text.append(attr.getName());
         text.append(": ");
         text.append(getAttributeType(attr));
      }
      text.append(" ");
      text.append(getOperatorString(model.getOperator()));
      text.append(" ");
      text.append(model.getValueExpression());
      text.append(getBooleanConstraintSuffix());

      figure.setText(text.toString());

//      ((AbstractNodeEditPart) getParent()).setLayoutConstraint(this, figure,
//            figure.getPreferredSize());
   }


   /**
    * This method creates a readable name for the given attribute type.
    * 
    * @param type The {@link EDataType} to translate.
    * @return Returns a readable name for the attribute.
    */
   private String getAttributeType(EAttribute attr)
   {
      String ret = "null";
      ret = attr.getEGenericType().getEClassifier().getName();

      return ret;
   }
}
