/**
 * 
 */
package org.reclipse.math.functions;


/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class FuzzySet extends MathematicalFunction
{
   
   public boolean containsValue(double value)
   {
      return this.value(value) > 0;
   }
   
   public double membership(double value)
   {
      double tmpResult = this.value(value);
      if (tmpResult > 1)
      {
         return 1;
      }
      else if (tmpResult < 0)
      {
         return 0;
      }
      else
      {
         return tmpResult;
      }
   }
}
