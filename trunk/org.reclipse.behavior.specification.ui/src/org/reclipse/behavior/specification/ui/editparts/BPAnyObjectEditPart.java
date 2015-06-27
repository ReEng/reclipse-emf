package org.reclipse.behavior.specification.ui.editparts;


import org.fujaba.commons.figures.UMLObjectFigure;
import org.reclipse.behavior.specification.BPAnyObject;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BPAnyObjectEditPart extends AbstractBPObjectEditPart
{

   /**
    * @see org.eclipse.gef.EditPart#getModel()
    */
   @Override
   public BPAnyObject getModel()
   {
      return (BPAnyObject) super.getModel();
   }


   /**
    * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
    */
   @Override
   protected void refreshVisuals()
   {
      UMLObjectFigure figure = (UMLObjectFigure) getFigure();
      BPAnyObject object = (BPAnyObject) getModel();
      figure.setName(object.getName());
   }

  
   @Override
   public BehavioralPatternEditPart getDiagramEditPart()
   {
      return (BehavioralPatternEditPart) super.getDiagramEditPart();
   }

}
