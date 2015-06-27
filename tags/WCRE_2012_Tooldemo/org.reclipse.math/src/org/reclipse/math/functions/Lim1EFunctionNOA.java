/**
 * 
 */
package org.reclipse.math.functions;




/**
 * A strictly increasing function with limes_{x --> infinity} f(x) = 1.
 * 
 * f(x) = (1 + epsilon) / (1 + exp((-x + deltaX)/compression)) + deltaY
 * 
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class Lim1EFunctionNOA extends ExponentialFunction
{

   public static final double DELTA_X_VALUE = 25;

   public static final double DELTA_Y_VALUE = -0.01;

   public static final double DELTA_EPSILON_VALUE = 0.01;

   public static final double DELTA_COMPRESSION_VALUE = 4;


   public Lim1EFunctionNOA()
   {
      this.init(DELTA_X_VALUE, DELTA_Y_VALUE, DELTA_EPSILON_VALUE,
            DELTA_COMPRESSION_VALUE);
   }


   @Override
   double calculateTempValue(double x, double dX, double compr)
   {
      return (1.0d + Math.exp((-x + dX) / compr));
   }


   public static String getFunctionName()
   {
      return "Exponential strictly increasing function with limit 1 (NOA)";
   }
}
