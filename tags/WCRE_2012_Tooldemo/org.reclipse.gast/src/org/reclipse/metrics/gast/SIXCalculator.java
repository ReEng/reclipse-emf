package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the Specialization Index - defined as
 * {@link NMOCalculator NMO} * {@link DITCalculator DIT} /
 * {@link NOMCalculator NOM}. For a package it calculates the average of all included
 * classes. So the supported elements are elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Package}</dt>
 * <dd>The average SIX for all containing classes.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The SIX for the class.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class SIXCalculator extends AbstractGASTMetricCalculator
{

   private boolean ignoreInterfaces;


   public SIXCalculator()
   {
      super();

      // TODO: move to preference store
      ignoreInterfaces = true;

      addClass(getCore().getPackage());
      addClass(getTypes().getGASTClass());
   }


   @Override
   public double calculate(EObject object)
   {
      if (object != null)
      {
         if (object instanceof Package)
         {
            // prepare average building
            double six = 0;
            double count = 0;

            // count SIX of all sub packages
            for (Package p : ((Package) object).getSubPackages())
            {
               six += calculate(p);
               count++;
            }

            // count SIX of all classes
            for (GASTClass c : ((Package) object).getClasses())
            {
               // ignore interfaces when necessary
               if (!c.isInterface() || (c.isInterface() && !ignoreInterfaces))
               {
                  six += calculate(c);
                  count++;
               }
            }

            // avoid division by zero
            if (count > 0)
            {
               return six / count;
            }
         }

         if (object instanceof GASTClass)
         {
            // FIXME: return SIX for class
         }
      }

      return -1;
   }
}
