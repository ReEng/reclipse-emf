package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the number of abstract methods (NAM) for different elements. Supported
 * elements are elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>The total NAM of all packages inside the project.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>The total NAM of all classes inside the package.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The NAM inside the class.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class NAMCalculator extends AbstractGASTMetricCalculator
{

   private boolean ignoreInnerClasses;


   public NAMCalculator()
   {
      super();

      // TODO: move to preference store
      ignoreInnerClasses = true;

      addClass(getCore().getRoot());
      addClass(getCore().getPackage());
      addClass(getTypes().getGASTClass());
   }


   @Override
   public double calculate(EObject object)
   {
      if (object != null)
      {
         if (object instanceof Root)
         {
            // prepare summarizing
            double sum = 0;

            // summarize for contained packages
            for (Package p : ((Root) object).getPackages())
            {
               sum += calculate(p);
            }

            return sum;
         }

         if (object instanceof Package)
         {
            // prepare average building
            double sum = 0;

            // summarize for of all classes
            for (GASTClass c : ((Package) object).getClasses())
            {
               // ignore inner classes when necessary
               if ((c.isInner() && !ignoreInnerClasses) || !c.isInner())
               {
                  sum += calculate(c);
               }
            }

            return sum;
         }

         if (object instanceof GASTClass)
         {
            double count = 0;

            for (Method m : ((GASTClass) object).getMethods())
            {
               if (m.isAbstract())
               {
                  count++;
               }
            }

            return count;
         }
      }

      return -1;
   }
}
