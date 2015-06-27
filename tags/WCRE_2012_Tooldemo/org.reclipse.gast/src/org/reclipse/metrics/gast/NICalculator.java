package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Function;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the number of invocations (NI) for different elements. Supported elements
 * are elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>The total NI for all contained packages.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>The total NI for all contained classes.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The total NI for the class.</dd>
 * <dt>{@link Function Method}</dt>
 * <dd>The NI of the method.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class NICalculator extends AbstractGASTMetricCalculator
{

   public NICalculator()
   {
      super();

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
         // FIXME: calculate NI
      }

      return -1;
   }
}
