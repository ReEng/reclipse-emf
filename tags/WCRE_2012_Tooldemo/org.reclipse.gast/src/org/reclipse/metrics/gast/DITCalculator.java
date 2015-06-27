package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.functions.Function;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.variables.Variable;


/**
 * This metric calculates the depth of inheritance for different elements. It calculates the DIT for
 * the following elements:
 * 
 * <dl>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>Calculating the maximum distance from the root class in the inheritance tree.</dd>
 * <dt>{@link Function Method}</dt>
 * <dd>Calculating the maximum distance from the first declaring class to the last found.</dd>
 * <dt>{@link Variable Attribute}</dt>
 * <dd>Calculating the maximum distance from the first declaring class to the last found.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class DITCalculator extends AbstractGASTMetricCalculator
{

   private boolean ignoreInterfaces;


   public DITCalculator()
   {
      super();

      // TODO: move to preference store
      ignoreInterfaces = true;

      addClass(getTypes().getGASTClass());
      addClass(getFunctions().getFunction());
      addClass(getVariables().getVariable());
   }


   @Override
   public double calculate(EObject object)
   {
      if (object != null)
      {
         if (object instanceof GASTClass)
         {
            return getCDIT((GASTClass) object);
         }
         if (object instanceof Function)
         {
            return getMDIT((Function) object);
         }
         if (object instanceof Variable)
         {
            return getADIT((Variable) object);
         }
      }

      return -1;
   }


   /**
    * Recursively calculates the depth of inheritance for the given class by returning the maximum
    * path length to the root element ({@link Object java.lang.Object}).
    * 
    * @param clazz The class for which to calculate.
    * @return Returns the maximum path length.
    */
   private int getCDIT(GASTClass clazz)
   {
      // return 0 when interface counting is off
      if (ignoreInterfaces && clazz.isInterface())
      {
         return 0;
      }

      // no more super types to count
      if (clazz.getSuperTypes().isEmpty())
      {
         return 0;
      }

      // search longest path to root
      int max = 0;
      for (GASTClass sup : clazz.getSuperTypes())
      {
         int supCdit = getCDIT(sup);
         if (supCdit > max)
         {
            max = supCdit;
         }
      }

      return max + 1;
   }


   /**
    * Recursively calculates the depth of inheritance for the given method by returning the maximum
    * path length to the 'highest' defining class.
    * 
    * @param method The method for which to calculate.
    * @return Returns the maximum path length.
    */
   private double getMDIT(Function method)
   {
      // FIXME: calculate MDIT
      return -1;
   }


   /**
    * Recursively calculates the depth of inheritance for the given method by returning the maximum
    * path length to the 'highest' defining class.
    * 
    * @param attr The attribute for which to calculate.
    * @return Returns the maximum path length.
    */
   private double getADIT(Variable attr)
   {
      // FIXME: calculate ADIT
      return -1;
   }
}
