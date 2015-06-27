package org.reclipse.structure.specification.ui.figures;


import java.text.DecimalFormat;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.fujaba.commons.figures.LineBorderedRectangleFigure;
import org.reclipse.math.functions.MathematicalFunction;




/**
 * This figure basically displays a mathematical function in some range that subclasses have to
 * determine.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class FuzzyFunctionFigure extends LineBorderedRectangleFigure
{
   protected MathematicalFunction function = null;

   private final int GAP = 10;

   protected double xValue = 0;

   protected double yValue = 0;

   private boolean valuesExist = false;

   protected String xName = null;

   // provide with initial values
   protected int xStart = 20;

   protected int xEnd = 180;

   protected int yStart = 20;

   protected int yEnd = 80;


   public FuzzyFunctionFigure(String xName)
   {
      this.xName = xName;
   }


   /**
    * @param value the value to set
    */
   public void setValues(double x, double y)
   {
      valuesExist = true;
      this.xValue = x;
      this.yValue = y;
   }


   @Override
   public void paintClientArea(Graphics gc)
   {
      super.paintClientArea(gc);

      // have to update those values as the can change with the figure size
      this.xEnd = bounds.width - 60;
      this.yEnd = bounds.height - 20;

      drawAxes(gc);
      if (this.function != null)
      {
         drawFunction(gc);

         if (valuesExist)
         {
            drawFunctionValue(gc);
         }
      }
   }


   /**
    * @param gc
    */
   private void drawAxes(Graphics gc)
   {
      gc.drawLine(xStart - GAP, yEnd, xEnd + 40, yEnd);
      gc.drawLine(xStart, yStart - GAP, xStart, yEnd + GAP);

      // draw arrows
      Color background = gc.getBackgroundColor();
      gc.setBackgroundColor(ColorConstants.black);
      gc.fillPolygon(new int[] { xStart - 3, yStart + 5 - GAP, xStart + 3,
            yStart + 5 - GAP, xStart, yStart - 5 - GAP });
      gc.fillPolygon(new int[] { xEnd + 35, yEnd - 3, xEnd + 35, yEnd + 3,
            xEnd + 45, yEnd });
      gc.setBackgroundColor(background);

      // draw axe names
      gc.setLineStyle(SWT.LINE_DOT);
      gc.setForegroundColor(ColorConstants.gray);
      gc.drawLine(xStart, yStart, xEnd, yStart);
      gc.drawLine(xEnd, yStart, xEnd, yEnd);
      gc.setForegroundColor(ColorConstants.black);
      gc.setLineStyle(SWT.LINE_SOLID);
      gc.drawText("0", xStart - GAP, yEnd);
      gc.drawText("1", xStart - GAP, yStart - 5);
      gc.drawText("" + '\u03BC', xStart - GAP, 0);
      double range = getFunctionRangeX();
      DecimalFormat df = new DecimalFormat("0.00");
      gc.drawText(df.format(range), xEnd - 10, yEnd);
      gc.drawText(this.xName, xEnd + 20, yEnd);
   }


   /**
    * @param gc
    */
   protected void drawFunction(Graphics gc)
   {
      // draw function values
      int x_pixel_count = xEnd - xStart;
      double x = 0;
      double y = 0;
      double width_of_1_pixel = getFunctionRangeX() / x_pixel_count;
      int currentY;

      int lastY = (int) Math.round(((double) (yEnd - yStart))
            * (1d - this.function.value(0d)));

      for (int i = 1; i < x_pixel_count; i++)
      {
         x = i * width_of_1_pixel;
         try
         {
            y = this.function.value(x);
         }
         catch (ArithmeticException e)
         {
            y = 0.0;
         }
         currentY = (int) Math.round(((double) (yEnd - yStart)) * (1d - y));
         gc.drawLine(xStart + i - 1, yStart + lastY, xStart + i, yStart
               + currentY);
         lastY = currentY;
      }
   }


   /**
    * @param gc
    */
   protected void drawFunctionValue(Graphics gc)
   {
      int x_pixel_count = xEnd - xStart;
      double range = getFunctionRangeX();
      double relX = xValue / range;
      int x = (int) Math.round(((double) x_pixel_count) * relX);

      gc.setBackgroundColor(ColorConstants.red);
      int xValueCoordinate = xStart + x;
      int yValueCoordinate = (int) Math.round((yEnd - yStart) * (1 - yValue)
            + yStart);
      gc.fillOval(xValueCoordinate - 3, yValueCoordinate - 3, 6, 6);
   }


   public void setFunction(MathematicalFunction function)
   {
      this.function = function;
      this.repaint();
   }


   /**
    * @return the function
    */
   public MathematicalFunction getFunction()
   {
      return function;
   }


   protected boolean valuesExist()
   {
      return valuesExist;
   }


   abstract protected double getFunctionRangeX();
}
