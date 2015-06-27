package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Constructor;
import de.fzi.gast.functions.Function;
import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the number of parameters (NOP) for different elements. Supported elements
 * are elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>The average NOP of all packages inside the project.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>The average NOP of all classes inside the package.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The average NOP of all methods inside the class.</dd>
 * <dt>{@link Function Method}</dt>
 * <dd>The NOP of the method.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class NOPCalculator extends AbstractGASTMetricCalculator
{

   private boolean ignoreInnerClasses;

   private boolean ignoreStaticMethods;

   private boolean ignoreConstructors;


   public NOPCalculator()
   {
      super();

      // TODO: move to preference store
      ignoreInnerClasses = true;
      ignoreStaticMethods = true;
      ignoreConstructors = true;

      addClass(getCore().getRoot());
      addClass(getCore().getPackage());
      addClass(getTypes().getGASTClass());
      addClass(getFunctions().getFunction());
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
            // prepare average building
            double sum = 0;
            double count = 0;

            // summarize for of all methods
            for (Method m : ((GASTClass) object).getMethods())
            {
               // ignore static methods when necessary
               if ((m.isStatic() && !ignoreStaticMethods) || !m.isStatic())
               {
                  sum += calculate(m);
                  count++;
               }
            }

            // summarize for constructors
            if (!ignoreConstructors)
            {
               for (Constructor c : ((GASTClass) object).getConstructors())
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

         if (object instanceof Function)
         {
            // FIXME: calculate NOP for the method or constructor
         }
      }

      return -1;
   }
}
