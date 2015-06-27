package org.reclipse.metrics;


import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.extensionpoints.IMetricCalculator;


/**
 * This class can be used for metric calculators. It a provides default implementation for the
 * {@link IMetricCalculator} interface.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class AbstractMetricCalculator implements IMetricCalculator
{

   private Set<EClass> supported;


   /**
    * The default constructor. Initializes the collection for applicable classes as HashSet.
    */
   public AbstractMetricCalculator()
   {
      supported = new HashSet<EClass>();
   }


   /**
    * Adds the given class to the set of applicable classes for this calculator.
    * 
    * @param clazz The class to be added to the applicable set.
    * @return Returns if the collection changed during the operation.
    */
   protected boolean addClass(EClass clazz)
   {
      return supported.add(clazz);
   }


   @Override
   public abstract double calculate(EObject element);


   @Override
   public Set<EClass> getAppliableClasses()
   {
      return supported;
   }


   @Override
   public boolean isApplicableTo(EClass clazz)
   {
      return getAppliableClasses().contains(clazz);
   }
}
