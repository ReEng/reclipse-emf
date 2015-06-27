package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;
import org.fujaba.commons.edit.parts.AbstractEdgeEditPart;
import org.fujaba.commons.figures.CrossFigure;
import org.fujaba.commons.figures.utils.ConnectionDecorationFactory;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.policies.PSDeleteEditPolicy;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This is the edit part for the model object {@link PSLink}.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSLinkEditPart extends AbstractEdgeEditPart
{
   private CrossFigure crossFigure = null;

   private ConnectionLocator crossLocator = null;


   @Override
   public void notifyChanged(Notification notification)
   {
      int featureId = notification.getFeatureID(PSLink.class);

      if (featureId == SpecificationPackage.PS_LINK__INSTANCE_OF
            || featureId == SpecificationPackage.PS_LINK__NAME
            || featureId == SpecificationPackage.PS_LINK__NEGATIVE
            || featureId == SpecificationPackage.PS_LINK__QUALIFIER
            || featureId == SpecificationPackage.PS_LINK__WEIGHT)
      {
         refreshVisuals();
      }

      super.notifyChanged(notification);
   }


   @Override
   protected void createEditPolicies()
   {
      super.createEditPolicies();

      installEditPolicy(EditPolicy.CONNECTION_ROLE, new PSDeleteEditPolicy());
   }


   @Override
   public void refreshVisuals()
   {
      super.refreshVisuals();

      StringBuffer text = new StringBuffer();
      PSLink model = (PSLink) this.getRealModel();

      placeReadingDirectionArrow(true);
      connection.setForegroundColor(ColorConstants.black);

      if (model.getSource() instanceof PSAnnotation)
      {
         if (ModelHelper.isCreate((PSAnnotation) model.getSource()))
         {
            Color createColor = PSConstants.COLOR__CREATE;
            connection.setForegroundColor(createColor);
            placeReadingDirectionArrow(true, createColor, createColor);
            text.append(PSConstants.LABEL_CREATE);
            text.append("\n");
         }
         // append qualifier name
         if (model.getQualifier() != null && model.getQualifier().length() > 0)
         {
            text.append(model.getQualifier());
         }
         else
         {
            text.append(PSConstants.LABEL_NO_RANGE);
         }
      }
      else
      {
         // append reference name
         if (model.getInstanceOf() != null)
         {
            // create arrow for unidirectional links
            if (model.getInstanceOf().getEOpposite() == null)
            {
               connection.setTargetDecoration(ConnectionDecorationFactory
                     .createDecoration(ConnectionDecorationFactory.OPEN_ARROW));
            }
            else
            {
               connection.setTargetDecoration(null);
            }

            text.append(model.getInstanceOf().getName());
         }
         else
         {
            text.append(PSConstants.LABEL_NO_RANGE);
         }

         // append qualifier
         if (model.getQualifier() != null && model.getQualifier().length() > 0)
         {
            text.append(" [" + model.getQualifier() + "]");
         }
      }

      if (text.length() > 0)
      {
         placeLabel(text.toString());
      }

      if (model.isNegative())
      {
         if (crossFigure == null)
         {
            crossFigure = new CrossFigure();
            crossLocator = new ConnectionLocator(connection,
                  ConnectionLocator.MIDDLE);
         }
         connection.add(crossFigure, crossLocator);
      }
      else if (crossFigure != null && crossFigure.getParent() == connection)
      {
         connection.remove(crossFigure);
      }
   }


   @Override
   public PSLink getRealModel()
   {
      return (PSLink) super.getRealModel();
   }
}
