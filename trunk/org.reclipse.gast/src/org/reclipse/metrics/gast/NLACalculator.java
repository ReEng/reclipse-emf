package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.variables.Variable;


/**
 * This metric calculates the number of local accesses for different elements. Supported elements
 * are elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>The average NLA for all contained packages.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>The average NLA for all contained classes.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>The average NLA for the class.</dd>
 * <dt>{@link Variable Attribute}</dt>
 * <dd>The NLA for the attribute.</dd>
 * </dl>
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class NLACalculator extends
      AbstractGASTMetricCalculator
{

   public NLACalculator()
   {
      super();

      addClass(getCore().getRoot());
      addClass(getCore().getPackage());
      addClass(getTypes().getGASTClass());
      addClass(getVariables().getVariable());
   }


   @Override
   public double calculate(EObject object)
   {
      if (object != null)
      {
         // FIXME: calculate NLA
      }

      return -1;
   }
}
