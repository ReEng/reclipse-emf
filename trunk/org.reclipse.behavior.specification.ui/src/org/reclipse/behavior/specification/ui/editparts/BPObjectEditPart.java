package org.reclipse.behavior.specification.ui.editparts;


import org.fujaba.commons.figures.UMLObjectFigure;
import org.reclipse.behavior.specification.BPObject;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BPObjectEditPart extends AbstractBPObjectEditPart
{

   @Override
   public BPObject getModel()
   {
      return (BPObject) super.getModel();
   }


   /**
    * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
    */
   @Override
   protected void refreshVisuals()
   {
      UMLObjectFigure figure = (UMLObjectFigure) getFigure();
      BPObject object = (BPObject) getModel();
      if(object.getTypeReference()!=null) {
         figure.setName(object.getName() + ":"
               + object.getTypeReference().getName());
      }
   }


   /**
    * @see de.uni_paderborn.fujaba.sequencediagrams.ui.editparts.UMLSequenceDiagramObjectEditPart#getBehavioralPatternEditPart()
    */
   @Override
   public BehavioralPatternEditPart getDiagramEditPart()
   {
      return (BehavioralPatternEditPart) super.getDiagramEditPart();
   }

}
