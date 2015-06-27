package org.reclipse.structure.specification.ui.figures;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.figures.LabelFigure;
import org.fujaba.commons.figures.LineBorderedRectangleWithCompartmentsFigure;



/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSAnnotationRectangleFigure extends
      LineBorderedRectangleWithCompartmentsFigure
{

   public final static String COMPARTMENT_NAME = "name";

   public final static String COMPARTMENT_SET_EXPR = "set";

   private LabelFigure myLabel;

   private LabelFigure weightLabel;

   private boolean create;


   public PSAnnotationRectangleFigure()
   {
      setBorderColor(ColorConstants.white);

      addCompartment(COMPARTMENT_NAME, SWT.NONE, 1);
      addCompartment(COMPARTMENT_SET_EXPR, SWT.NONE, 0);

      this.myLabel = new LabelFigure(new String(),
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_BOLD));
      this.myLabel.setUnderlined(true);

      this.weightLabel = new LabelFigure(new String(),
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_NORMAL));

      addFigureToCompartment(COMPARTMENT_NAME, this.myLabel);
   }


   public void addToSetConstraints(LabelFigure setSizeExprLabel)
   {
      addFigureToCompartment(COMPARTMENT_SET_EXPR, setSizeExprLabel);
   }


   public String getName()
   {
      return this.myLabel.getText();
   }


   public void removeFromSetSizeExpressions(LabelFigure setSizeExprLabel)
   {
      removeFigureFromCompartment(COMPARTMENT_SET_EXPR, setSizeExprLabel);
   }


   public void setAbstract(boolean isAbstract)
   {
      if (isAbstract)
      {
         this.myLabel.setFont(Commons4EclipseFonts
               .getFont(Commons4EclipseFonts.FONT_DEFAULT_BOLD_ITALIC));
      }
      else
      {
         this.myLabel.setFont(Commons4EclipseFonts
               .getFont(Commons4EclipseFonts.FONT_DEFAULT_BOLD));
      }
   }


   public void setCreate(boolean create)
   {
      if (this.create != create)
      {
         this.create = create;

         if (create)
         {
            this.myLabel.setForegroundColor(ColorConstants.darkGreen);
         }
         else
         {
            this.myLabel.setForegroundColor(ColorConstants.black);
         }
      }
   }


   public void setName(String name)
   {
      this.myLabel.setText(name);
   }


   public void setWeightText(String text)
   {
      if (text != null && !text.equals(""))
      {
         addFigureToCompartment(COMPARTMENT_NAME, this.weightLabel);
      }
      else
      {
         removeFigureFromCompartment(COMPARTMENT_NAME, this.weightLabel);
      }
      weightLabel.setText(text);
   }
}
