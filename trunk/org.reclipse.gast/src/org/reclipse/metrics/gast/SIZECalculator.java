package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractMetricCalculator;


/**
 * This calculator counts the number of elements of a set.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class SIZECalculator extends AbstractMetricCalculator
{

   public SIZECalculator()
   {
      super();
   }


   @Override
   public double calculate(EObject object)
   {
      // FIXME: calculate set size
      return -1;
   }


   /**
    * Always returns <code>true</code> due to special handling of the SIZE metric.
    * 
    * @param clazz The class to check for.
    * @return Returns <code>true</code>.
    */
   @Override
   public boolean isApplicableTo(EClass clazz)
   {
      return true;
   }
}
