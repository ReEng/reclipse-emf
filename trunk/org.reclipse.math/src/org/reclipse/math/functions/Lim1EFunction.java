package org.reclipse.math.functions;




public class Lim1EFunction extends ExponentialFunction
{
   public static final double DELTA_X_VALUE = 5;

   public static final double DELTA_Y_VALUE = -0.1;

   public static final double DELTA_EPSILON_VALUE = 0.1;

   public static final double DELTA_COMPRESSION_VALUE = 4;


   public Lim1EFunction()
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
      return "Exponential strictly increasing function with limit 1";
   }
}
