package org.reclipse.behavior.specification.ui.commands;


import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;
import org.reclipse.behavior.specification.ui.editparts.BPLifelineEditPart;
import org.reclipse.behavior.specification.ui.wizards.CreateBPAssignmentWizard;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;


public class CreateBPAssignmentCommand extends AbstractBPCommand
{
   private BPArgument rightSide;

   BPAssignment assignment;

   InteractionOperand parentOperand;

   BPObject leftSide;


   public CreateBPAssignmentCommand(BPLifelineEditPart lifelineEditPart)
   {
      super("create assignment", (BehavioralPatternEditPart) lifelineEditPart
            .getParent());
      AbstractSequenceDiagramObject object = lifelineEditPart.getModel()
            .getObject();
      if (object instanceof BPObject)
      {
         this.leftSide = (BPObject) object;
         BehavioralPattern behavioralPattern = (BehavioralPattern) this
               .getBehavioralPatternEditPart().getModel();
         this.parentOperand = (InteractionOperand) behavioralPattern
               .getRootFragment().getOperands().get(0);
      }
   }


   @Override
   public void redo()
   {
      CreateBPAssignmentWizard wizard = new CreateBPAssignmentWizard(this);

      wizard.setObj(this.leftSide);

      WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow().getShell(), wizard);
      wizardDialog.create();
      wizardDialog.open();
      super.redo();
   }


   public void createAssignment(BPArgument rightSide)
   {
      this.rightSide = rightSide;
      createAssignment();
   }


   public void createAssignment()
   {
      this.assignment = BehavioralpatternFactory.eINSTANCE.createBPAssignment();
      this.assignment.setLeftSide(this.leftSide);
      this.assignment.setRightSide(this.rightSide);
      BehavioralPattern diagram = ((BehavioralPattern) getBehavioralPatternEditPart()
            .getModel());
      this.assignment.setDiagram(diagram);
      diagram.getFragments().add(this.assignment);
      this.assignment.setParentOperand(this.parentOperand);
      this.assignment.setLifeline(this.leftSide.getLifeline());

      getBehavioralPatternEditPart().relayout();
   }


   @Override
   public void undo()
   {
      this.assignment.setDiagram(null);
      BehavioralPattern diagram = ((BehavioralPattern) getBehavioralPatternEditPart()
            .getModel());
      diagram.getFragments().remove(this.assignment);
      this.assignment.setLeftSide(null);
      this.assignment.setRightSide(null);
      this.assignment.setParentOperand(null);
      this.assignment.setLifeline(null);
      super.undo();
   }


   @Override
   public boolean canExecute()
   {
      if (this.leftSide instanceof BPObject)
      {
         return true;
      }
      return false;
   }


}
