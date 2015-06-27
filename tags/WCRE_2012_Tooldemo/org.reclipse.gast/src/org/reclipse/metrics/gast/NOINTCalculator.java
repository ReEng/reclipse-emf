package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;


/**
 * This metric calculates the number of interfaces for packages and projects.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class NOINTCalculator extends AbstractGASTMetricCalculator
{

   public NOINTCalculator()
   {
      super();

      addClass(getCore().getRoot());
      addClass(getCore().getPackage());
   }


   @Override
   public double calculate(EObject object)
   {
      if (object != null)
      {
         if (object instanceof Root)
         {
            return ((Root) object).getAllInterfaces().size();
         }
         if (object instanceof Package)
         {
            return ((Package) object).getAllInterfaces().size();
         }
      }

      return -1;
   }
}
