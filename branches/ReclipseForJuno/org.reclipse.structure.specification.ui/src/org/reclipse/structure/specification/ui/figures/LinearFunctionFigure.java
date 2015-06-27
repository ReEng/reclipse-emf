package org.reclipse.structure.specification.ui.figures;


/**
 * This function figure represents a linear function.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class LinearFunctionFigure extends FuzzyFunctionFigure
{

   public LinearFunctionFigure(String xName)
   {
      super(xName);
   }


   @Override
   protected double getFunctionRangeX()
   {
      double i = 1E-10;
      double deltaY = function.value(0d);
      double x1 = function.value(i);
      double x2 = function.value(2d * i);
      double rising = (x2 - x1) / (2 * i - i);

      if (rising < 0)
      {
         // descending
         i = deltaY / Math.abs(rising);
      }
      else if (rising > 0)
      {
         // ascending
         i = (1d - deltaY) / rising;
      }

      // make sure it's positive to not cause any displaying exceptions
      i = Math.abs(i);

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
