package org.reclipse.metrics;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import de.fzi.gast.accesses.accessesPackage;
import de.fzi.gast.annotations.annotationsPackage;
import de.fzi.gast.core.corePackage;
import de.fzi.gast.functions.functionsPackage;
import de.fzi.gast.statements.statementsPackage;
import de.fzi.gast.types.typesPackage;
import de.fzi.gast.variables.variablesPackage;


/**
 * This abstracts class delivers access to all G-AST packages.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class AbstractGASTMetricCalculator extends
      AbstractMetricCalculator
{

   /**
    * Checks whether a {@link EClass} is applicable for the calculator. When the class is a type or
    * a {@link EClass#getEAllSuperTypes() sub type} of the supported types, it returns
    * <code>true</code>.
    * 
    * @param clazz The {@link EClass} to check.
    * @return Returns <code>false</code> when the class is not supported.
    */
   @Override
   public boolean isApplicableTo(EClass clazz)
   {
      // go through the supported types
      for (EClass c : getAppliableClasses())
      {
         // check if the class is a super type of or the type of the class
         if (c.isSuperTypeOf(clazz))
         {
            return true;
         }
      }

      return super.isApplicableTo(clazz);
   }


   /**
    * Calculates the the size of an annotated set element.
    * 
    * @param element The element for which to calculate the size.
    * @return Returns the size of the set.
    */
   protected double calculateSetSize(EObject element)
   {
      // FIXME: implement set size calculation
      return -1;
   }


   /**
    * Getter of the gast.statements package.
    * 
    * @return Returns the package.
    */
   protected statementsPackage getStatements()
   {
      return statementsPackage.eINSTANCE;
   }


   /**
    * Getter of the gast.core package.
    * 
    * @return Returns the package.
    */
   protected corePackage getCore()
   {
      return corePackage.eINSTANCE;
   }


   /**
    * Getter of the gast.annotations package.
    * 
    * @return Returns the package.
    */
   protected annotationsPackage getAnnotations()
   {
      return annotationsPackage.eINSTANCE;
   }


   /**
    * Getter of the gast.types package.
    * 
    * @return Returns the package.
    */
   protected typesPackage getTypes()
   {
      return typesPackage.eINSTANCE;
   }


   /**
    * Getter of the gast.accesses package.
    * 
    * @return Returns the package.
    */
   protected accessesPackage getAccesses()
   {
      return accessesPackage.eINSTANCE;
   }


   /**
    * Getter of the gast.functions package.
    * 
    * @return Returns the package.
    */
   protected functionsPackage getFunctions()
   {
      return functionsPackage.eINSTANCE;
   }


   /**
    * Getter of the gast.variables package.
    * 
    * @return Returns the package.
    */
   protected variablesPackage getVariables()
   {
      return variablesPackage.eINSTANCE;
   }
}
