package org.reclipse.structure.specification.ui.wizards;


import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.reclipse.math.functions.FunctionParameter;
import org.reclipse.math.functions.MathematicalFunction;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class NewPSFuzzyExpressionWizard extends AbstractPSFuzzyExpressionWizard
{
   private PSFuzzyConstraint product = null;
   private EClass type;
   private String acronym;
   
   /**
    * The default constructor.
    *
    * @param command The command to execute.
    */
   public NewPSFuzzyExpressionWizard(EClass type, String acronym)
   {
      super(!SpecificationPackage.eINSTANCE.getPSFuzzySetRatingConstraint().equals(type));
      this.type = type;
      this.acronym = acronym;
      setWindowTitle("New Fuzzy Expression");
   }

   
   

   @Override
   public void addPages()
   {
      super.addPages();
      this.page.setMetricAcronym(this.acronym);
   }




   @Override
   public boolean performFinish()
   {
      MathematicalFunction mathFunction = page.getSelectedMathFunction();

      if (mathFunction == null)
      {
         return false;
      }

      PSFuzzyConstraint constraint = null;
      String acronym = page.getMetricAcronym();
      if (SpecificationPackage.eINSTANCE.getPSFuzzySetRatingConstraint().equals(this.type))
      {
         constraint = SpecificationFactory.eINSTANCE.createPSFuzzySetRatingConstraint();
      }
      else
      {
         constraint = SpecificationFactory.eINSTANCE.createPSFuzzyMetricConstraint();
         ((PSFuzzyMetricConstraint) constraint).setMetricAcronym(acronym);
      }
      
      if (constraint == null)
      {
         return false;
      }
      
      constraint.setMathFunctionID(mathFunction.getClass().getCanonicalName());

      Iterator<?> iter = mathFunction.iteratorOfParams();
      while (iter.hasNext())
      {
         FunctionParameter parameter = (FunctionParameter) iter.next();

         PSFunctionParameter param = SpecificationFactory.eINSTANCE.createPSFunctionParameter();
         param.setConstraint(constraint);
         param.setName(parameter.getName());
         param.setValue(parameter.getValue());
      }
      this.product = constraint;
      return true;
   }


   public PSFuzzyConstraint getProduct()
   {
      return this.product;
   }
}
