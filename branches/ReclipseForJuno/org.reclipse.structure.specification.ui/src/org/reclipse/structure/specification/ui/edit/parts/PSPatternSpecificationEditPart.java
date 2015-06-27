package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.figures.UMLFragmentLabelFigure;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PatternType;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.policies.PSLayoutEditPolicy;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This is the edit part for the model element {@link PSPatternSpecification}.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSPatternSpecificationEditPart extends AbstractDiagramEditPart
{

   private PSAnnotationEditPart createAnnotationEditPart;

   protected UMLFragmentLabelFigure labelFigure;


   @Override
   protected IFigure createFigure()
   {
      // create label figure
      String prefix = PSConstants.DIAGRAM_PREFIX_PATTERNSPECIFICATION;
      labelFigure = new UMLFragmentLabelFigure(prefix, new String());

      // change parent figure
      IFigure f = super.createFigure();
      f.setBorder(new LineBorder(ColorConstants.black, 1));
      f.add(labelFigure);
      f.setConstraint(labelFigure, new Rectangle(1, 1, -1, -1));

      return f;
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      StringBuilder text = new StringBuilder();

      if (getRealModel().getName() != null)
      {
         text.append(getRealModel().getName());
      }

      if (getRealModel().getSuperPattern() != null)
      {
         text.append(" extends ");
         text.append(getRealModel().getSuperPattern().getName());
      }

      if (getRealModel().getType() == PatternType.ANTI_PATTERN)
      {
         text.append(" (" + ModelHelper.getReadable(getRealModel().getType())
               + ")");
      }

      labelFigure.setText(1, text.toString());

      if (createAnnotationEditPart == null)
      {
         for (Object child : getChildren())
         {
            if (child instanceof PSAnnotationEditPart)
            {
               PSAnnotationEditPart anno = (PSAnnotationEditPart) child;
               if (ModelHelper.isCreate(anno.getRealModel()))
               {
                  createAnnotationEditPart = anno;
               }
            }
         }
      }

      if (createAnnotationEditPart != null)
      {
         createAnnotationEditPart.refreshVisuals();
      }
   }


   @Override
   public PSPatternSpecification getRealModel()
   {
      return (PSPatternSpecification) super.getRealModel();
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE,
            new RootComponentEditPolicy());
      installEditPolicy(EditPolicy.LAYOUT_ROLE, new PSLayoutEditPolicy());
   }


   @Override
   public void notifyChanged(Notification notification)
   {
      int id = notification.getFeatureID(PSPatternSpecification.class);

      if (id == SpecificationPackage.PS_PATTERN_SPECIFICATION__NAME
            || id == SpecificationPackage.PS_PATTERN_SPECIFICATION__TYPE
            || id == SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN
            || id == SpecificationPackage.PS_PATTERN_SPECIFICATION__ABSTRACT)
      {
         refreshVisuals();
      }

      super.notifyChanged(notification);
   }
}
