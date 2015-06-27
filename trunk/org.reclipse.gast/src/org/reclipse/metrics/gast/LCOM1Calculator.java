package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the lack of cohesion for different elements. Supported elements are
 * elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>The average LCOM of all packages inside the project.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>The average LCOM of all classes inside the package.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The lack of cohesion of methods of the class.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class LCOM1Calculator extends AbstractGASTMetricCalculator
{

   private boolean ignoreInnerClasses;


   public LCOM1Calculator()
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
            // prepare average building
            double sum = 0;
            double count = 0;

            // summarize for contained packages
            for (Package p : ((Root) object).getPackages())
            {
               sum += calculate(p);
               count++;
            }

            // avoid division by zero
            if (count > 0)
            {
               return sum / count;
            }
         }

         if (object instanceof Package)
         {
            // prepare average building
            double sum = 0;
            double count = 0;

            // summarize for of all classes
            for (GASTClass c : ((Package) object).getClasses())
            {
               // ignore interfaces and inner classes when necessary
               if ((c.isInner() && !ignoreInnerClasses)
                     || (!c.isInner() && !c.isInterface()))
               {
                  sum += calculate(c);
                  count++;
               }
            }

            // avoid division by zero
            if (count > 0)
            {
               return sum / count;
            }
         }

         if (object instanceof GASTClass)
         {
            // FIXME: calculate LCOM for the class
         }
      }

      return -1;
   }
}
