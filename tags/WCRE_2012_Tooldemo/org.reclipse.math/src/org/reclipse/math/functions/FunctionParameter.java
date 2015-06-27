/**
 * 
 */
package org.reclipse.math.functions;


/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class FunctionParameter
{
   private String name;

   public String getName()
   {
      return this.name;
   }

   public void setName(String value)
   {
      if (this.name != value)
      {
//         String oldValue = this.name;
         this.name = value;
//         firePropertyChange (FElement.NAME_PROPERTY, oldValue, value) ;
      }
   }

   private double value;
   
   public static String PROPERTY_VALUE = "value";

   public double getValue()
   {
      return this.value;
   }

   public void setValue(double value)
   {
      if (this.value != value)
      {
//         double oldValue = this.value;
         this.value = value;
//         firePropertyChange (PROPERTY_VALUE, new Double(oldValue), new Double(value)) ;
      }
   }

   /**
    * <pre>
    *                    0..n    params     1 -------------
    * FunctionParameter ----------------------| paramName | MathematicalFunction
    *                    params      function -------------
    * </pre>
    */
   private MathematicalFunction function;
   
   public static String PROPERTY_FUNCTION = "function";

   public MathematicalFunction getFunction()
   {
      return this.function;
   }

   public boolean setFunction(String partnerKey, MathematicalFunction value)
   {
      boolean changed = false;
      if (this.function != value)
      {
         MathematicalFunction oldValue = this.function;
         
         if (this.function != null)
         {
            this.function = null;
            oldValue.removeFromParams (this);
         }
         this.function = value;
         if (value != null)
         {
            value.addToParams (partnerKey, this);
         }
         changed = true;
//         firePropertyChange (PROPERTY_FUNCTION, oldValue, value) ;
      }
      return changed;
   }
}
