package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the number of all descent classes (WNOC) for different elements. Supported
 * elements are elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>The total WNOC in all packages and its sub-packages inside a project.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>The total WNOC inside a package and its sub packages.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The WNOC of a class.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class WNOCCalculator extends AbstractGASTMetricCalculator
{

   public WNOCCalculator()
   {
      super();

      addClass(getCore().getRoot());
      addClass(getCore().getPackage());
      addClass(getTypes().getGASTClass());
   }


   @Override
   public double calculate(EObject object)
   {
      if (object != null)
      {

         // FIXME: calculate WNOC
      }

      return -1;
   }
}
