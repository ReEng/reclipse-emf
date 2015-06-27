package org.reclipse.metrics.extensionpoints;


import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;


/**
 * This interface is used on metrics calculations. Implementing classes do the stuff.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public interface IMetricCalculator
{
   /**
    * This operation is called from the calculator to calculate the value of the given object.
    * 
    * @param object The object for which the metric will be calculated.
    * @return Returns the calculated metric value.
    */
   public double calculate(EObject object);


   /**
    * This method decides whether a metric can be calculated for the given class.
    * 
    * @param clazz The class to check.
    * @return Returns <code>true</code> when the class is applicable for the metric.
    */
   public boolean isApplicableTo(EClass clazz);


   /**
    * This method returns the set of {org.eclipse.emf.ecore.EClass classes} for which the
    * calculation can be done.
    */
   public Set<EClass> getAppliableClasses();
}
