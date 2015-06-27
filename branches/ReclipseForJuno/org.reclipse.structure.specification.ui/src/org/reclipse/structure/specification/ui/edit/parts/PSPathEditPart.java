package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.edit.parts.AbstractEdgeEditPart;
import org.fujaba.commons.figures.DoublePolylineConnection;
import org.fujaba.commons.figures.utils.ConnectionDecorationFactory;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.policies.PSDeleteEditPolicy;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSPathEditPart extends AbstractEdgeEditPart
{

   @Override
   public void notifyChanged(Notification notification)
   {
      int featureId = notification.getFeatureID(PSPath.class);

      if (featureId == SpecificationPackage.PS_PATH__NAME
            || featureId == SpecificationPackage.PS_PATH__TABOO_CLASSES
            || featureId == SpecificationPackage.PS_PATH__WEIGHT)
      {
         refreshVisuals();
      }

      super.notifyChanged(notification);
   }


   @Override
   protected void createEditPolicies()
   {
      super.createEditPolicies();

      installEditPolicy(EditPolicy.COMPONENT_ROLE, new PSDeleteEditPolicy());
   }


   @Override
   protected IFigure createFigure()
   {
      DoublePolylineConnection connection = new DoublePolylineConnection();
      connection.setForegroundColor(ColorConstants.black);
      connection.setTargetDecoration(ConnectionDecorationFactory
            .createDecoration(ConnectionDecorationFactory.OPEN_ARROW));
      this.connection = connection;
      return this.connection;
   }


   @Override
   public void refreshVisuals()
   {
      super.refreshVisuals();

      PSPath model = (PSPath) this.getRealModel();

      if (model.getTabooClasses().isEmpty())
      {
         this.placeLabel(new String());
      }
      else
      {
         StringBuffer labelBuffer = new StringBuffer("( "
               + PSConstants.LABEL_TABOO_CLASSES + " ");
         for (EClass taboo : model.getTabooClasses())
         {
            labelBuffer.append(taboo.getName() + ", ");
         }
         this.placeLabel(labelBuffer.delete(labelBuffer.length() - 2,
               labelBuffer.length() - 1) + ")");
      }
   }
}
