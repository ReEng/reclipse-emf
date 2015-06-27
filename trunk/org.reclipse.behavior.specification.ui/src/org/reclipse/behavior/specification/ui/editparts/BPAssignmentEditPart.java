package org.reclipse.behavior.specification.ui.editparts;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.swt.graphics.Font;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.figures.LabelFigure;
import org.fujaba.commons.figures.RoundedRectangleBorder;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.ui.editpolicies.DeleteBPAssignmentEditPolicy;

import de.uni_paderborn.sequencediagram.viewer.editparts.AbstractSequenceDiagramEditPart;


public class BPAssignmentEditPart extends AbstractSequenceDiagramEditPart
{

   private LabelFigure labelFigure = null;


   public BPAssignmentEditPart()
   {
      super();
   }


   @Override
   /* protected */public void refreshVisuals()
   {
      updateLabel();
   }


   private String getLabelText()
   {
      StringBuffer text = new StringBuffer();
      if (((BPAssignment) this.getModel()).getLeftSide() != null)
      {
         text.append(((BPAssignment) this.getModel()).getLeftSide().getName());
      }
      text.append(":=");
      if (((BPAssignment) this.getModel()).getRightSide() != null)
      {
         text.append(((BPAssignment) this.getModel()).getRightSide().getName());
      }
      return text.toString();
   }


   protected void updateLabel()
   {
      String text = getLabelText();
      if (this.labelFigure == null)
      {
         this.labelFigure = new LabelFigure(text,
               Commons4EclipseFonts.getFont(Commons4EclipseFonts.FONT_DEFAULT_NORMAL));
         RoundedRectangleBorder border = new RoundedRectangleBorder(ColorConstants.black, 1);
         border.setArc(10);
         this.labelFigure.setBorder(border);
         this.labelFigure.setOpaque(true);
         this.labelFigure.setSize(this.labelFigure.getPreferredSize());
      }
      this.labelFigure.setText(text);

      Font font = Commons4EclipseFonts.getFont(Commons4EclipseFonts.FONT_DEFAULT_NORMAL);
      this.labelFigure.setFont(font);
   }


   /**
    * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
    */
   @Override
   protected IFigure createFigure()
   {
      updateLabel();
      return this.labelFigure;
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new DeleteBPAssignmentEditPolicy());

      installEditPolicy(EditPolicy.NODE_ROLE, new NonResizableEditPolicy());
   }


   @Override
   public BPAssignment getModel()
   {
      return (BPAssignment) super.getModel();
   }


}
