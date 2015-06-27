/**
 * 
 */
package org.reclipse.math.functions;


/**
 * Linear Function
 * 
 *  f(x) = gradient * (x - deltaX) + deltaY
 * 
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class LinearFunction extends MathematicalFunction
{
   public LinearFunction()
   {
      this.init();
   }
   
   private static final String DELTA_X = "deltaX";
   private static final String DELTA_Y = "deltaY";
   private static final String GRADIENT = "gradient";
   
   private void init()
   {      
      FunctionParameter param = new FunctionParameter();
      param.setName(DELTA_X);
      param.setValue(0);
      this.addToParams(DELTA_X, param);
      
      param = new FunctionParameter();
      param.setName(DELTA_Y);
      param.setValue(0);
      this.addToParams(DELTA_Y, param);
      
      param = new FunctionParameter();
      param.setName(GRADIENT);
      param.setValue(1);
      this.addToParams(GRADIENT, param);
   }

   /**
    * @see org.reclipse.math.functions.MathematicalFunction#value(double)
    */
   @Override
   public double value(double x)
   {
      FunctionParameter deltaX = this.getFromParams(DELTA_X);
      FunctionParameter deltaY = this.getFromParams(DELTA_Y);
      FunctionParameter gradient = this.getFromParams(GRADIENT);
      
      if (deltaX == null || deltaY == null || gradient == null)
      {
         throw new IllegalStateException("Function arguments are missing.");
      }
      
      double dX = deltaX.getValue();
      double dY = deltaY.getValue();
      double m = gradient.getValue();
      
      return m * (x - dX) + dY;
   }

   public static String getFunctionName()
   {
      return "Linear function";
   }
}