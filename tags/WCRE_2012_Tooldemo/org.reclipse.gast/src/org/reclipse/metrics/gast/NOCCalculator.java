package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the number of children (NOC) for different elements. Supported elements
 * are elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>The average NOC of all packages inside the project.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>The average NOC of all classes inside the package.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The NOC of the class.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class NOCCalculator extends AbstractGASTMetricCalculator
{

   public NOCCalculator()
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
         // FIXME: calculate NOC
      }
      return -1;
   }
}
