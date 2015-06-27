package org.reclipse.behavior.specification.ui.commands;


import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;


public class DeleteBPAssignmentCommand extends AbstractBPCommand
{

   private BPAssignment assignment;

   private SequenceDiagram diagram;

   private BPObject leftSide;

   private BPArgument rightSide;

   private InteractionOperand parentOperand;


   public DeleteBPAssignmentCommand(BPAssignment assignment,
         BehavioralPatternEditPart diagramEditPart)
   {
      super("delete assignment", diagramEditPart);
      this.assignment = assignment;
      this.diagram = this.assignment.getDiagram();
      this.leftSide = this.assignment.getLeftSide();
      this.rightSide = this.assignment.getRightSide();
      this.parentOperand = this.assignment.getParentOperand();
   }


   @Override
   public void undo()
   {
      BPAssignment model = this.getModel();
      model.setDiagram(this.diagram);
      this.diagram.getFragments().add(model);
      model.setLeftSide(this.leftSide);
      model.setRightSide(this.rightSide);
      model.setLifeline(this.leftSide.getLifeline());
      model.setParentOperand(this.parentOperand);
      super.undo();
   }


   @Override
   public void redo()
   {
      this.assignment.setDiagram(null);
      BehavioralPattern diagram = ((BehavioralPattern) getBehavioralPatternEditPart()
            .getModel());
      diagram.getFragments().remove(this.assignment);
      this.assignment.setLeftSide(null);
      this.assignment.setRightSide(null);
      this.assignment.setParentOperand(null);
      this.assignment.setLifeline(null);
      super.redo();
   }


   protected BPAssignment getModel()
   {
      return (BPAssignment) this.assignment;
   }
}
