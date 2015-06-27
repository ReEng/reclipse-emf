package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.fujaba.commons.figures.UMLConstraintFigure;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.policies.PSDeleteEditPolicy;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * This is the edit part for the model type {@link PSSpecificationConstraint}.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSSpecificationConstraintEditPart extends AbstractNodeEditPart
{

   @Override
   public PSSpecificationConstraint getRealModel()
   {
      return (PSSpecificationConstraint) super.getRealModel();
   }


   @Override
   protected IFigure createFigure()
   {
      return new UMLConstraintFigure(new String());
   }


   @Override
   public void notifyChanged(Notification notification)
   {
      int fid = notification.getFeatureID(PSSpecificationConstraint.class);

      if (SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__ADDITIONAL == fid
            || SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__EXPRESSION == fid
            || SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__NAME == fid
            || SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__WEIGHT == fid)
      {
         refreshVisuals();
      }

      super.notifyChanged(notification);
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new PSDeleteEditPolicy());
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSSpecificationConstraint model = getRealModel();
      UMLConstraintFigure figure = (UMLConstraintFigure) getFigure();

      String text;
      if (model.getName() != null && model.getName().length() > 0)
      {
         text = model.getName() + ": " + model.getExpression(); //$NON-NLS-1$
      }
      else
      {
         text = model.getExpression();
      }
      text += getConstraintSuffix();

      figure.setText(text);

      super.refreshVisuals();
   }


   /**
    * Creates the constraint suffix.
    * 
    * @return Returns the constraint suffix.
    */
   protected String getConstraintSuffix()
   {
      PSSpecificationConstraint model = getRealModel();

      StringBuffer text = new StringBuffer();

      if (model.isAdditional()
            || model.getWeight() != PSConstants.DEFAULT_WEIGHT)
      {
         text.append(" {"); //$NON-NLS-1$
         if (model.isAdditional())
         {
            text.append(PSConstants.LABEL_ADDITIONAL);
         }
         if (model.getWeight() != PSConstants.DEFAULT_WEIGHT)
         {
            if (model.isAdditional())
            {
               text.append(", "); //$NON-NLS-1$
            }
            text.append("w=" + model.getWeight()); //$NON-NLS-1$
         }
         text.append("}"); //$NON-NLS-1$
      }

      return text.toString();
   }
}
