package org.reclipse.metrics.gast;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.AbstractGASTMetricCalculator;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.helpers.DerivationHelper;
import de.fzi.gast.types.GASTClass;


/**
 * This metric calculates the number of lines of in the program's source code for different
 * elements. Supported elements are elements or sub types of the following:
 * 
 * <dl>
 * <dt>{@link Root Project}</dt>
 * <dd>Summarizes the lines of code for all contained packages.</dd>
 * <dt>{@link Package Package}</dt>
 * <dd>Summarizes the lines of code for all contained classes.</dd>
 * <dt>{@link GASTClass Class}</dt>
 * <dd>Calculates the lines of code for the class.</dd>
 * </dl>
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class LOCCalculator extends AbstractGASTMetricCalculator
{

   public LOCCalculator()
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
         if (object instanceof Root)
         {
            DerivationHelper.getLinesOfCode((Root) object);
         }

         if (object instanceof Package)
         {
            DerivationHelper.getLinesOfCode((Package) object);
         }

         if (object instanceof GASTClass)
         {
            DerivationHelper.getLinesOfCode((GASTClass) object);
         }
      }

      return -1;
   }
}
