/**
 * 
 */
package org.reclipse.structure.specification.ui.edit.commands;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.fujaba.commons.edit.commands.AbstractCreateNodeCommand;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.ui.wizards.NewPSFuzzyExpressionWizard;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreatePSFuzzyConstraintCommand extends AbstractCreateNodeCommand
{
   private EClass type;

   private String acronym;


   /**
    * @param parent
    * @param type
    * @param acronym if already known, may be null otherwise
    */
   public CreatePSFuzzyConstraintCommand(HierarchicalNode parent, EClass type,
         String acronym)
   {
      super("create fuzzy constraint/function", parent, false, null);
      this.type = type;
      this.acronym = acronym;
   }


   /**
    * @see org.fujaba.commons.edit.commands.AbstractViewCommand#redoModel()
    */
   @Override
   protected void redoModel()
   {
      if (this.modelElement == null)
      {
         NewPSFuzzyExpressionWizard wizard = new NewPSFuzzyExpressionWizard(
               type, acronym);
         WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench()
               .getActiveWorkbenchWindow().getShell(), wizard);
         wizardDialog.create();
         if (wizardDialog.open() == WizardDialog.CANCEL)
         {
            abort();
         }
         else
         {
            this.modelElement = wizard.getProduct();
            ((PSFuzzyConstraint) this.modelElement)
                  .setWeight(PSConstants.DEFAULT_WEIGHT);
         }
      }

      // add constraint to node
      if (viewParent.getModel() instanceof PSNode && modelElement != null)
      {
         ((PSNode) viewParent.getModel()).getNodeConstraints().add(
               (PSFuzzyConstraint) modelElement);
      }
      // add constraint to set fragment
      else if (viewParent.getModel() instanceof PSCombinedFragment && modelElement != null)
      {
         ((PSCombinedFragment) viewParent.getModel())
               .setConstraint((PSFuzzyConstraint) modelElement);
      }
   }


   /**
    * @see org.fujaba.commons.edit.commands.AbstractViewCommand#undoModel()
    */
   @Override
   protected void undoModel()
   {
      // remove constraint from node
      if (viewParent.getModel() instanceof PSNode)
      {
         PSNode node = (PSNode) viewParent.getModel();
         node.getNodeConstraints().remove((PSFuzzyConstraint) modelElement);
      }
      // remove constraint from set fragment
      else if (viewParent.getModel() instanceof PSCombinedFragment)
      {
         PSCombinedFragment frag = (PSCombinedFragment) viewParent.getModel();
         frag.setConstraint(null);
      }
   }
}
