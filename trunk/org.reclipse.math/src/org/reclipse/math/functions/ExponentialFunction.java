package org.reclipse.math.functions;




public abstract class ExponentialFunction extends MathematicalFunction
{

   public static final String DELTA_X = "deltaX";

   public static final String DELTA_Y = "deltaY";

   public static final String DELTA_EPSILON = "epsilon";

   public static final String DELTA_COMPRESSION = "compression";


   
   protected void init(double deltaX, double deltaY, double deltaEpsilon,
         double deltaCompression)
   {
      FunctionParameter param = new FunctionParameter();
      param.setName(DELTA_X);
      param.setValue(deltaX);
      this.addToParams(DELTA_X, param);

      param = new FunctionParameter();
      param.setName(DELTA_Y);
      param.setValue(deltaY);
      this.addToParams(DELTA_Y, param);

      param = new FunctionParameter();
      param.setName(DELTA_EPSILON);
      param.setValue(deltaEpsilon);
      this.addToParams(DELTA_EPSILON, param);

      param = new FunctionParameter();
      param.setName(DELTA_COMPRESSION);
      param.setValue(deltaCompression);
      this.addToParams(DELTA_COMPRESSION, param);
   }
   
   @Override
   public double value(double x)
   {
      FunctionParameter deltaX = this.getFromParams(DELTA_X);
      FunctionParameter deltaY = this.getFromParams(DELTA_Y);
      FunctionParameter epsilon = this.getFromParams(DELTA_EPSILON);
      FunctionParameter compression = this.getFromParams(DELTA_COMPRESSION);

      if (deltaX == null || deltaY == null || epsilon == null
            || compression == null)
      {
         throw new IllegalStateException("Function arguments are missing.");
      }

      double dX = deltaX.getValue();
      double dY = deltaY.getValue();
      double eps = epsilon.getValue();
      double compr = compression.getValue();

      if (compr == 0.0d)
      {
         return 0.0d; // otherwise: division by zero
      }

      double tmp = calculateTempValue(x, dX, compr);

      if (tmp == 0.0d)
      {
         return 0.0d; // otherwise: division by zero
      }

      tmp = ((1.0d + eps) / tmp) + dY;
      return tmp;
   }
   
   public static String getFunctionName()
   {
      return "Abstract Exponential Function";
   }
   
   abstract double calculateTempValue(double x, double dX, double compression);
  
}
