package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.edit.parts.AbstractNodeViewEditPart;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.policies.PSDeleteEditPolicy;
import org.reclipse.structure.specification.ui.edit.policies.PSNodeEditPolicy;
import org.reclipse.structure.specification.ui.figures.PSObjectFigure;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * This is the edit part for the model object {@link PSObject}.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSObjectEditPart extends AbstractDiagramEditPart
{

   @Override
   public PSObject getRealModel()
   {
      return (PSObject) super.getRealModel();
   }


   @Override
   public PSObjectFigure getFigure()
   {
      return (PSObjectFigure) super.getFigure();
   }


   @Override
   protected IFigure createFigure()
   {
      return new PSObjectFigure();
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.NODE_ROLE, new PSNodeEditPolicy());
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new PSDeleteEditPolicy());
   }


   @Override
   public void notifyChanged(Notification notification)
   {
      int featureId = notification.getFeatureID(PSObject.class);

      if (featureId == SpecificationPackage.PS_OBJECT__NAME
            || featureId == SpecificationPackage.PS_OBJECT__WEIGHT
            || featureId == SpecificationPackage.PS_OBJECT__MODIFIER
            || featureId == SpecificationPackage.PS_OBJECT__INSTANCE_OF)
      {
         refreshVisuals();
      }
      else if (featureId == SpecificationPackage.PS_OBJECT__NODE_CONSTRAINTS)
      {
         refreshChildren();
      }

      super.notifyChanged(notification);
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      // get type
      String type = "null";
      if (getRealModel().getInstanceOf() != null)
      {
         type = getRealModel().getInstanceOf().getName();
      }

      // get weight
      String weight = new String();
      if (getRealModel().getWeight() != PSConstants.DEFAULT_WEIGHT)
      {
         weight = "{w=" + getRealModel().getWeight() + "}";
      }

      boolean negative = getRealModel().getModifier() == ModifierType.NEGATIVE;
      boolean dashed = getRealModel().getModifier() == ModifierType.ADDITIONAL
            || getRealModel().getModifier() == ModifierType.SET;

      getFigure().setName(getRealModel().getName() + ":" + type);
      getFigure().setWeightText(weight);
      getFigure().setNegative(negative);
      getFigure().setDashedLine(dashed);
      getFigure().setTrigger(getRealModel().isTrigger());
      getFigure().setShadowed(getRealModel().getModifier() == ModifierType.SET);

      getFigure().revalidate();
   }


   @Override
   protected void addChildVisual(EditPart child, int index)
   {
      PSNodeConstraint model = (PSNodeConstraint) ((AbstractNodeViewEditPart) child)
            .getRealModel();
      LabelFigure figure = (LabelFigure) ((AbstractGraphicalEditPart) child)
            .getFigure();

      addConstraint(figure, model);
   }


   @Override
   protected void removeChildVisual(EditPart child)
   {
      PSNodeConstraint model = (PSNodeConstraint) ((AbstractNodeViewEditPart) child)
            .getRealModel();
      LabelFigure figure = (LabelFigure) ((AbstractGraphicalEditPart) child)
            .getFigure();

      removeConstraint(figure, model);
   }


   /**
    * @param figure
    * @param model
    */
   private void removeConstraint(LabelFigure child, PSNodeConstraint type)
   {
      PSObjectFigure figure = getFigure();

      if (type instanceof PSAttributeConstraint)
      {
         figure.removeFromAttributeConstraints(child);
      }
      else if (type instanceof PSMetricConstraint)
      {
         // check if it is a size constraint
         String acronym = ((PSMetricConstraint) type).getMetricAcronym();
         if (acronym != null
               && acronym.equals(PSConstants.CONSTRAINT_TEXT_SIZE_ATTR))
         {
            figure.removeFromSetConstraints(child);
         }
         else
         {
            figure.removeFromMetricConstraints(child);
         }
      }
      else if (type instanceof PSFuzzyMetricConstraint)
      {
         // check if it is a size constraint
         if (((PSFuzzyMetricConstraint) type).getMetricAcronym().equals(
               PSConstants.CONSTRAINT_TEXT_SIZE_ATTR))
         {
            figure.removeFromSetConstraints(child);
         }
         else
         {
            figure.removeFromMetricConstraints(child);
         }
      }
      else if (type instanceof PSFuzzySetRatingConstraint)
      {
         figure.removeFromSetConstraints((LabelFigure) child);
      }
   }


   /**
    * Adds the given child to the node container.
    * 
    * @param child The child figure to add.
    * @param type The model element.
    */
   public void addConstraint(LabelFigure child, PSNodeConstraint type)
   {
      PSObjectFigure figure = getFigure();

      if (type instanceof PSAttributeConstraint)
      {
         figure.addToAttributeConstraints(child);
      }
      else if (type instanceof PSMetricConstraint)
      {
         // check if it is a size constraint
         String acronym = ((PSMetricConstraint) type).getMetricAcronym();
         if (acronym != null
               && acronym.equals(PSConstants.CONSTRAINT_TEXT_SIZE_ATTR))
         {
            figure.addToSetConstraints(child);
         }
         else
         {
            figure.addToMetricConstraints(child);
         }
      }
      else if (type instanceof PSFuzzyMetricConstraint)
      {
         // check if it is a size constraint
         if (((PSFuzzyMetricConstraint) type).getMetricAcronym().equals(
               PSConstants.CONSTRAINT_TEXT_SIZE_ATTR))
         {
            figure.addToSetConstraints(child);
         }
         else
         {
            figure.addToMetricConstraints(child);
         }
      }
      else if (type instanceof PSFuzzySetRatingConstraint)
      {
         figure.addToSetConstraints((LabelFigure) child);
      }
   }


   @Override
   public Dimension getMinimumSize()
   {
      int gapH = 6;
      int gapW = 14;

      if (getRealModel().isTrigger())
      {
         gapW += 2;
      }

      // resize
      StringBuffer text = new StringBuffer(getRealModel().getName() + ":"); //$NON-NLS-1$
      if (getRealModel().getInstanceOf() != null)
      {
         text.append(getRealModel().getInstanceOf().getName());
      }
      else
      {
         text.append("null"); //$NON-NLS-1$
      }
      Dimension min = TextUtilities.INSTANCE.getStringExtents(text.toString(),
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_BOLD));
      return new Dimension(min.width + gapW, min.height + gapH);
   }
}
