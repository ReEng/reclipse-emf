/**
 * 
 */
package org.reclipse.structure.specification.ui.wizards;

import java.util.Iterator;

import org.eclipse.swt.widgets.Composite;
import org.reclipse.math.functions.FunctionParameter;
import org.reclipse.math.functions.MathematicalFunction;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.utils.MathFunctionHelper;


/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class EditPSFuzzyExpressionWizard extends AbstractPSFuzzyExpressionWizard
{
   private PSFuzzyConstraint constraint;
   
   public EditPSFuzzyExpressionWizard(PSFuzzyConstraint constraint)
   {
      super(!SpecificationPackage.eINSTANCE.getPSFuzzySetRatingConstraint().equals(constraint.eClass()));
      setWindowTitle("Edit Fuzzy Expression");
      this.constraint = constraint;
   }

   
   /**
    * @see org.eclipse.jface.wizard.IWizard#canFinish()
    */
   @Override
   public boolean canFinish()
   {
      MathematicalFunction mathFunction = this.page.getSelectedMathFunction();
      if (mathFunction == null)
      {
         return false;
      }
      
      return super.canFinish();
   }


   @Override
   public void createPageControls(Composite pageContainer)
   {
      super.createPageControls(pageContainer);
      if(this.constraint instanceof PSFuzzyMetricConstraint)
      {
         this.page.setMetricAcronym(((PSFuzzyMetricConstraint)constraint).getMetricAcronym());
      }
      MathematicalFunction mFct = MathFunctionHelper.getMathematicalFunction(constraint);
      this.page.setSelectedMathFunction(mFct);
   }
   
   
   @Override
   public boolean performFinish()
   {
      MathematicalFunction mathFunction = this.page.getSelectedMathFunction();
      String acronym = this.page.getMetricAcronym();

      this.constraint.setMathFunctionID(mathFunction.getClass().getCanonicalName());
      this.constraint.getParameters().clear();

      //replace parameters of the constraint function
      Iterator<?> iter = mathFunction.iteratorOfParams();
      while (iter.hasNext())
      {
         FunctionParameter parameter = (FunctionParameter) iter.next();
         PSFunctionParameter emfParameter = SpecificationFactory.eINSTANCE.createPSFunctionParameter();
         emfParameter.setName(parameter.getName());
         emfParameter.setValue(parameter.getValue());         
         constraint.getParameters().add(emfParameter);
      }
     
      
      if(this.constraint instanceof PSFuzzyMetricConstraint
         && acronym != null && !acronym.equals(((PSFuzzyMetricConstraint)this.constraint).getMetricAcronym()))
      {
         ((PSFuzzyMetricConstraint)this.constraint).setMetricAcronym(acronym);
      }
      return true;
   }
}
