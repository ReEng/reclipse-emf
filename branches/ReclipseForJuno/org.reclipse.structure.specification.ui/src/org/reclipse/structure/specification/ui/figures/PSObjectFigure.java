package org.reclipse.structure.specification.ui.figures;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.figures.LabelFigure;
import org.fujaba.commons.figures.LineBorderedRectangleWithCompartmentsFigure;
import org.fujaba.commons.figures.RectangleBorder;
import org.fujaba.commons.figures.ShadowDecoratorFigure;



/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSObjectFigure extends LineBorderedRectangleWithCompartmentsFigure
{

   public final static String COMPARTMENT_NAME = "name";

   public final static String COMPARTMENT_MATCHED = "matched";

   public final static String COMPARTMENT_ATTR_EXPR = "attributes";

   public final static String COMPARTMENT_METRIC_EXPR = "metrics";

   public final static String COMPARTMENT_SET_EXPR = "set";

   private LabelFigure myLabel;

   private LabelFigure weightLabel;

   private LabelFigure matchedLabel;

   private boolean negative;

   private ShadowDecoratorFigure shadowDecorator;


   public PSObjectFigure()
   {
      setBorder(new RectangleBorder(ColorConstants.black, 1));
      setBackgroundColor(ColorConstants.white);
      setForegroundColor(ColorConstants.black);

      addCompartment(COMPARTMENT_NAME, SWT.LINE_SOLID, 1);
      addCompartment(COMPARTMENT_MATCHED, SWT.LINE_SOLID, 1);
      addCompartment(COMPARTMENT_SET_EXPR, SWT.LINE_SOLID, 2);
      addCompartment(COMPARTMENT_ATTR_EXPR, SWT.LINE_SOLID, 1);
      addCompartment(COMPARTMENT_METRIC_EXPR, SWT.NONE, 0);

      this.myLabel = new LabelFigure("",
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_BOLD));
      this.myLabel.setUnderlined(true);

      MarginBorder border = new MarginBorder(2, 5, 2, 5);
      this.myLabel.setBorder(border);

      matchedLabel = new LabelFigure("",
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_NORMAL));
      matchedLabel.setLabelAlignment(PositionConstants.LEFT);
      matchedLabel.setBorder(new MarginBorder(2, 5, 2, 5));

      this.weightLabel = new LabelFigure("",
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_NORMAL));

      addFigureToCompartment(COMPARTMENT_NAME, this.myLabel);
   }


   public void addToAttributeConstraints(LabelFigure attrExprLabel)
   {
      addFigureToCompartment(COMPARTMENT_ATTR_EXPR, attrExprLabel);
   }


   public void addToMetricConstraints(LabelFigure metricExprLabel)
   {
      addFigureToCompartment(COMPARTMENT_METRIC_EXPR, metricExprLabel);
   }


   public void addToSetConstraints(LabelFigure setSizeExprLabel)
   {
      addFigureToCompartment(COMPARTMENT_SET_EXPR, setSizeExprLabel);
   }


   public String getName()
   {
      return this.myLabel.getText();
   }


   public boolean isDashedLine()
   {
      return (getBorderLineStyle() == SWT.LINE_DASH);
   }


   public boolean isNegative()
   {
      return this.negative;
   }


   @Override
   public void paint(Graphics graphics)
   {
      super.paint(graphics);

      if (this.isNegative())
      {
         Rectangle bounds = getBounds();
         graphics.drawLine(bounds.x, bounds.y + bounds.height, bounds.x
               + bounds.width, bounds.y);
         graphics.drawLine(bounds.x, bounds.y, bounds.x + bounds.width,
               bounds.y + bounds.height);
      }
   }


   public void removeFromAttributeConstraints(LabelFigure attrExprLabel)
   {
      removeFigureFromCompartment(COMPARTMENT_ATTR_EXPR, attrExprLabel);
   }


   public void removeFromMetricConstraints(LabelFigure metricExprLabel)
   {
      removeFigureFromCompartment(COMPARTMENT_METRIC_EXPR, metricExprLabel);
   }


   public void removeFromSetConstraints(LabelFigure setSizeExprLabel)
   {
      removeFigureFromCompartment(COMPARTMENT_SET_EXPR, setSizeExprLabel);
   }


   public void setDashedLine(boolean dashedLine)
   {
      if (dashedLine)
      {
         setBorderLineStyle(SWT.LINE_DASH);
      }
      else
      {
         setBorderLineStyle(SWT.LINE_SOLID);
      }
   }


   public void setName(String name)
   {
      this.myLabel.setText(name);
   }


   public void setNegative(boolean negative)
   {
      if (this.negative != negative)
      {
         this.negative = negative;

         repaint();
      }
   }


   public void setShadowed(boolean shadowed)
   {
      if (shadowed)
      {
         this.shadowDecorator = new ShadowDecoratorFigure(this,
               ShadowDecoratorFigure.DEFAULT_OFFSET,
               -ShadowDecoratorFigure.DEFAULT_OFFSET);
         this.shadowDecorator.setOpaque(true);
      }
      else
      {
         if (this.shadowDecorator != null)
         {
            this.shadowDecorator.removeDecorator();
            this.shadowDecorator = null;
         }
      }
   }


   public void setTrigger(boolean trigger)
   {
      if (trigger && getBorderWidth() != 2)
      {
         setBorderWidth(2);
         repaint();
      }
      else if (!trigger && getBorderWidth() != 1)
      {
         setBorderWidth(1);
         repaint();
      }
   }


   public void setWeightText(String text)
   {
      if (text != null && text.length() > 0)
      {
         this.addFigureToCompartment(COMPARTMENT_NAME, this.weightLabel);
      }
      else
      {
         this.removeFigureFromCompartment(COMPARTMENT_NAME, this.weightLabel);
      }
      this.weightLabel.setText(text);
   }


   public void setMatchingName(String text)
   {
      if (text != null && !text.trim().isEmpty())
      {
         addFigureToCompartment(COMPARTMENT_MATCHED, matchedLabel);
      }
      else
      {
         removeFigureFromCompartment(COMPARTMENT_MATCHED, matchedLabel);
      }
      matchedLabel.setText(text);
   }
}
