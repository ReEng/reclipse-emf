package org.reclipse.structure.specification.ui.figures;


/**
 * This figure represents a lim1e function.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class Lim1EFunctionFigure extends FuzzyFunctionFigure
{

   public Lim1EFunctionFigure(String xName)
   {
      super(xName);
   }


   @Override
   protected double getFunctionRangeX()
   {
      double i = Double.MIN_NORMAL;
      double deviation = 2d;
      double lastDeviation = 2.1d;

      while (deviation > 1.1d && deviation != lastDeviation)
      {
         double y = this.function.value(i);
         y = Math.abs(y);
         lastDeviation = deviation;
         deviation = 1d / y;
         i *= 1.3d;
      }

      // make sure we see the value
      if (valuesExist())
      {
         if (xValue > i)
         {
            return xValue;
         }
      }
      return i;
   }
}
