package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.policies.PSDeleteEditPolicy;
import org.reclipse.structure.specification.ui.edit.policies.PSNodeEditPolicy;
import org.reclipse.structure.specification.ui.figures.PSAnnotationFigure;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This is the edit part for the model object {@link PSAnnotation}.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSAnnotationEditPart extends AbstractDiagramEditPart
{

   private LabelFigure create;


   @Override
   public PSAnnotation getRealModel()
   {
      return (PSAnnotation) super.getRealModel();
   }


   @Override
   public PSAnnotationFigure getFigure()
   {
      return (PSAnnotationFigure) super.getFigure();
   }


   @Override
   protected IFigure createFigure()
   {
      IFigure figure = new PSAnnotationFigure();
      anchor = new EllipseAnchor(figure);
      return figure;
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.NODE_ROLE, new PSNodeEditPolicy());

      if (!ModelHelper.isCreate(getRealModel()))
      {
         installEditPolicy(EditPolicy.COMPONENT_ROLE, new PSDeleteEditPolicy());
      }
   }


   @Override
   public void notifyChanged(Notification notification)
   {
      int id = notification.getFeatureID(PSAnnotation.class);

      if (id == SpecificationPackage.PS_ANNOTATION__NAME
            || id == SpecificationPackage.PS_ANNOTATION__WEIGHT
            || id == SpecificationPackage.PS_ANNOTATION__MODIFIER
            || id == SpecificationPackage.PS_ANNOTATION__TRIGGER
            || id == SpecificationPackage.PS_ANNOTATION__TYPE)
      {
         refreshVisuals();
      }
      else if (id == SpecificationPackage.PS_ANNOTATION__NODE_CONSTRAINTS)
      {
         refreshChildren();
      }

      super.notifyChanged(notification);
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      // deliver some
      PSAnnotationFigure figure = getFigure();
      PSAnnotation model = getRealModel();
      boolean isCreate = ModelHelper.isCreate(model);

      String name = ""; //$NON-NLS-1$
      String type = ""; //$NON-NLS-1$
      String weight = ""; //$NON-NLS-1$

      // get name
      if (!isCreate)
      {
         name = model.getName();
      }
      else
      {
         addCreateProperties(figure);
      }

      // get type
      if (model.getType() != null)
      {
         type = model.getType().getName();
      }

      // get weight
      if (model.getWeight() != PSConstants.DEFAULT_WEIGHT)
      {
         weight = "{w=" + model.getWeight() + "}"; //$NON-NLS-1$
      }

      boolean negative = model.getModifier() == ModifierType.NEGATIVE;
      boolean dashed = model.getModifier() == ModifierType.ADDITIONAL
            || model.getModifier() == ModifierType.SET;

      figure.setName(name + ":" + type); //$NON-NLS-1$
      figure.setWeightText(weight);
      if (model.getType() != null)
      {
         figure.setAbstract(model.getType().isAbstract());
      }
      figure.setCreate(isCreate);
      figure.setTrigger(model.isTrigger());
      figure.setNegative(negative);
      figure.setDashedLine(dashed);
      figure.setShadowed(model.getModifier() == ModifierType.SET);

      figure.revalidate();
   }


   public void addCreateProperties(PSAnnotationFigure figure)
   {
      if (create == null)
      {
         // create createLabel and locate it above the annotationFigure
         create = new LabelFigure(PSConstants.LABEL_CREATE,
               Commons4EclipseFonts
                     .getFont(Commons4EclipseFonts.FONT_DEFAULT_NORMAL));
         create.setForegroundColor(ColorConstants.darkGreen);

         figure.getParent().add(create);
      }

      if (figure.getParent() != null)
      {
         int w = create.getPreferredSize().width;
         int h = create.getPreferredSize().height;
         int x = getModel().getX() + (getModel().getWidth() - w) / 2;
         int y = getModel().getY() - h - 5;
         Rectangle rect = new Rectangle(x, y, w, h);

         figure.getParent().setConstraint(create, rect);
      }
   }


   @Override
   protected void addChildVisual(EditPart child, int index)
   {
      LabelFigure label = (LabelFigure) ((GraphicalEditPart) child).getFigure();
      getFigure().addToSetConstraints(label);
   }


   @Override
   protected void removeChildVisual(EditPart child)
   {
      LabelFigure label = (LabelFigure) ((GraphicalEditPart) child).getFigure();
      getFigure().removeFromSetConstraints((LabelFigure) label);
   }
}
