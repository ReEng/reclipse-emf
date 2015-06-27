package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.policies.PSDeleteEditPolicy;
import org.reclipse.structure.specification.ui.edit.policies.PSLayoutEditPolicy;
import org.reclipse.structure.specification.ui.figures.PSCombinedFragmentFigure;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This is the edit part for the model type {@link PSCombinedFragment}.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSCombinedFragmentEditPart extends AbstractDiagramEditPart
{

   @Override
   public PSCombinedFragment getRealModel()
   {
      return (PSCombinedFragment) super.getRealModel();
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.LAYOUT_ROLE, new PSLayoutEditPolicy());

      installEditPolicy(EditPolicy.COMPONENT_ROLE, new PSDeleteEditPolicy());
   }


   @Override
   protected IFigure createFigure()
   {
      return new PSCombinedFragmentFigure();
   }


   @Override
   public void notifyChanged(Notification notification)
   {
      int fid = notification.getFeatureID(PSCombinedFragment.class);

      if (SpecificationPackage.PS_COMBINED_FRAGMENT__KIND == fid
            || SpecificationPackage.PS_COMBINED_FRAGMENT__NAME == fid
            || SpecificationPackage.PS_COMBINED_FRAGMENT__WEIGHT == fid)
      {
         refreshVisuals();
      }

      super.notifyChanged(notification);
   }


   @Override
   public Dimension getPreferredSize()
   {
      int height = getFigure().getPreferredSize().height;
      int width = getFigure().getPreferredSize().width;

      for (Object child : getChildren())
      {
         int right = ((AbstractNodeEditPart) child).getFigure().getBounds()
               .right();
         int bottom = ((AbstractNodeEditPart) child).getFigure().getBounds()
               .bottom();

         height = Math.max(height, bottom + 10 - getFigure().getBounds().y);
         width = Math.max(width, right + 10 - getFigure().getBounds().x);
      }

      return new Dimension(width, height);
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSCombinedFragment model = getRealModel();
      PSCombinedFragmentFigure figure = (PSCombinedFragmentFigure) getFigure();

      StringBuilder text = new StringBuilder();

      switch (model.getKind())
      {
         case ADDITIONAL:
            text.append(PSConstants.LABEL_ADDITIONAL);
            break;
         case NEGATIVE:
            text.append(PSConstants.LABEL_NEGATIVE);
            break;
         case SET:
            text.append(PSConstants.LABEL_SET);
            figure.setOpaque(false);
            break;
         default:
            text.append(PSConstants.LABEL_NONE);
            break;
      }

      if (model.getWeight() != PSConstants.DEFAULT_WEIGHT)
      {
         text.append(" {w=" + model.getWeight() + "}");
      }

      if (model.getConstraint() != null)
      {
         if (model.getConstraint() instanceof PSMetricConstraint)
         {
            PSMetricConstraint c = (PSMetricConstraint) model.getConstraint();
            text.append(" (size ");
            text.append(ModelHelper.getReadable(c.getOperator()));
            text.append(" " + c.getValueExpression());
            text.append(")");
         }
         else if (model.getConstraint() instanceof PSFuzzyMetricConstraint)
         {
            text.append(" (fuzzy constrained size)");
         }
      }

      figure.setText(text.toString());
      figure.revalidate();
   }


   @Override
   protected void addChildVisual(EditPart child, int index)
   {
      if (child instanceof PSFuzzyConstraintEditPart
            || child instanceof PSMetricConstraintEditPart)
      {
         PSCombinedFragmentFigure figure = (PSCombinedFragmentFigure) getFigure();
         LabelFigure childFigure = (LabelFigure) ((AbstractNodeEditPart) child)
               .getFigure();
         figure.addConstraint(childFigure);
      }
      else
      {
         super.addChildVisual(child, index);
      }
   }


   @Override
   protected void removeChildVisual(EditPart child)
   {
      if (child instanceof PSFuzzyConstraintEditPart
            || child instanceof PSMetricConstraintEditPart)
      {
         PSCombinedFragmentFigure figure = (PSCombinedFragmentFigure) getFigure();
         LabelFigure constraint = (LabelFigure) ((AbstractNodeEditPart) child)
               .getFigure();
         figure.removeConstraint(constraint);
      }
      else
      {
         super.removeChildVisual(child);
      }
   }
}
