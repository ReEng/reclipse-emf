package org.reclipse.structure.specification.ui.figures;


/**
 * This figure represents a lim0e function.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class Lim0EFunctionFigure extends FuzzyFunctionFigure
{

   public Lim0EFunctionFigure(String xName)
   {
      super(xName);
   }


   @Override
   protected double getFunctionRangeX()
   {
      double i = Double.MIN_NORMAL;
      double lim = 1d;
      double deviation = 2d;
      double lastDeviation = 2.1d;

      while (deviation > 1.1d && deviation != lastDeviation)
      {
         double y = this.function.value(i);
         lastDeviation = deviation;
         deviation = lim / (1d - y);
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
