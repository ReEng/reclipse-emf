package org.reclipse.behavior.specification.ui.commands;


import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramFactory;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;


/**
 * @author Dietrich Travkin
 * @author Last Editor: $Author: alhawash $
 * @version $Revision: 440 $ $Date: 2008-06-30 14:51:07 +0200 (Mo, 30 Jun 2008) $
 */
public class AddInteractionOperandCommand extends AbstractBPCommand
{

   private CombinedFragment fragment = null;

   private InteractionOperand operand;


   public AddInteractionOperandCommand(CombinedFragment fragment,
         BehavioralPatternEditPart diagramEditPart)
   {
      super("add Interaction Operand", diagramEditPart);
      this.fragment = fragment;
   }


   @Override
   public void redo()
   {
      operand = BasicSequenceDiagramFactory.eINSTANCE
            .createInteractionOperand();
      fragment.getOperands().add(operand);

      super.redo();
   }


   @Override
   public void undo()
   {
      this.fragment.getOperands().remove(this.operand);
      super.undo();
   }


}
