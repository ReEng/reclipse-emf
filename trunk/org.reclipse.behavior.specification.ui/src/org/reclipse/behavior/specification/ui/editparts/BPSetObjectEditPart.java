package org.reclipse.behavior.specification.ui.editparts;

import org.fujaba.commons.figures.UMLObjectFigure;
import org.reclipse.behavior.specification.BPSetObject;



/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BPSetObjectEditPart extends AbstractBPObjectEditPart
{

   /**
    * @see org.eclipse.gef.EditPart#getModel()
    */
   @Override
   public BPSetObject getModel()
   {
      return (BPSetObject) super.getModel();
   }


   /**
    * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
    */
   @Override
   protected void refreshVisuals()
   {
      UMLObjectFigure figure = (UMLObjectFigure) getFigure();
      figure.setIsSet(true);
      BPSetObject object = (BPSetObject) getModel();
      figure.setName(object.getName() + ":"
            + object.getTypeReference().getName());
   }


   @Override
   public BehavioralPatternEditPart getDiagramEditPart()
   {
      return (BehavioralPatternEditPart) super.getDiagramEditPart();
   }

}
