/**
 * 
 */
package org.reclipse.structure.specification.ui.edit.commands;


import java.util.ArrayList;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.ui.wizards.EditPSFuzzyExpressionWizard;


/**
 * @author Oleg
 */
public class EditPSFuzzyExpressionCommand extends Command
{

   private PSFuzzyConstraint constraint = null;
   private String oldMathFct, newMathFct;
   private ArrayList<PSFunctionParameter> oldParams = new ArrayList<PSFunctionParameter>(); 
   private ArrayList<PSFunctionParameter> newParams = new ArrayList<PSFunctionParameter>();


   public EditPSFuzzyExpressionCommand(PSFuzzyConstraint constraint)
   {
      super("edit fuzzy constraint/function");
      this.constraint = constraint;
   }


   @Override
   public void execute()
   {
      redo();
   }


   @Override
   public void redo()
   {
      if(oldMathFct == null)
      {
         oldMathFct = constraint.getMathFunctionID();
         oldParams.addAll(constraint.getParameters());
         
         EditPSFuzzyExpressionWizard wizard = new EditPSFuzzyExpressionWizard(constraint);

         WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench()
               .getActiveWorkbenchWindow().getShell(), wizard);
         wizardDialog.create();
         wizardDialog.open();
         
         newParams.addAll(constraint.getParameters());
         newMathFct = constraint.getMathFunctionID();
      }
      else
      {
         constraint.setMathFunctionID(newMathFct);
         constraint.getParameters().clear();
         constraint.getParameters().addAll(newParams);
      }
   }


   @Override
   public void undo()
   {
      constraint.setMathFunctionID(oldMathFct);
      constraint.getParameters().clear();
      constraint.getParameters().addAll(oldParams);
   }

}
