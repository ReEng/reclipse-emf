package org.reclipse.behavior.specification.ui.commands;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;


public class DeleteCombinedFragmentCommand extends AbstractBPCommand
{

   private CombinedFragment fragment;

   private InteractionOperand parentOperand;

   private EList<InteractionOperand> operands;


   public DeleteCombinedFragmentCommand(CombinedFragment fragment,
         BehavioralPatternEditPart diagramEditPart)
   {
      super("delete combined fragment", diagramEditPart);
      this.fragment = fragment;
      this.parentOperand = fragment.getParentOperand();
      this.operands = fragment.getOperands();
   }


   @Override
   public void undo()
   {
      CombinedFragment model = this.getModel();
      model.setParentOperand(this.parentOperand);
      model.getOperands().addAll(this.operands);
      super.undo();
   }


   @Override
   public void redo()
   {
      CombinedFragment model = this.getModel();
      ((SequenceDiagram) model.eContainer()).getFragments().remove(model);
      InteractionOperand parentOperand = model.getParentOperand();

      int index = parentOperand.getFragments().indexOf(model);
      parentOperand.getFragments().remove(model);
      EList<InteractionOperand> operands = model.getOperands();

      List<Fragment> containedFragments = new ArrayList<Fragment>();
      for (InteractionOperand interactionOperand : operands)
      {
         EList<Fragment> fragments = interactionOperand.getFragments();
         for (Fragment fragment : fragments)
         {
            containedFragments.add(fragment);
         }
      }
      for (Fragment fragment : containedFragments)
      {
         // each contained fragment will change it's parent operand to the parent of the combined
         // fragment to be deleted
         parentOperand.getFragments().add(index, fragment);
         index++;
      }
      model.getOperands().clear();
      super.redo();
   }


   protected CombinedFragment getModel()
   {
      return (CombinedFragment) this.fragment;
   }
}
