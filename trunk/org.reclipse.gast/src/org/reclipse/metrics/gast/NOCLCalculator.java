package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the number of classes (NOCL) for different elements:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>The total number of classes in all packages and its sub-packages inside a project.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>The total number of classes inside a package and its sub packages.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The number of inner classes inside a class.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class NOCLCalculator extends AbstractGASTMetricCalculator
{

   private boolean ignoreInnerClasses;


   public NOCLCalculator()
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
            // prepare value
            int nocl = 0;

            // summarize for contained packages
            for (Package p : ((Root) object).getPackages())
            {
               nocl += calculate(p);
            }

            return nocl;
         }

         if (object instanceof Package)
         {
            // prepare value
            int nocl = 0;

            // summarize for of all classes
            for (GASTClass c : ((Package) object).getClasses())
            {
               // ignore inner classes when necessary
               if ((c.isInner() && !ignoreInnerClasses) || !c.isInner())
               {
                  nocl += calculate(c);
               }
            }

            return nocl;
         }

         if (object instanceof GASTClass)
         {
            // prepare value
            int nocl = 0;

            // summarize for of all inner classes
            if (!ignoreInnerClasses)
            {
               for (GASTClass c : ((GASTClass) object).getInnerClasses())
               {
                  nocl += calculate(c);
               }
            }

            return nocl;
         }
      }

      return -1;
   }
}
