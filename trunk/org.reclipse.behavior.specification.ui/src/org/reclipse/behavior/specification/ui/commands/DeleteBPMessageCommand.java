package org.reclipse.behavior.specification.ui.commands;


import java.util.List;

import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;
import de.uni_paderborn.basicSequenceDiagram.ReceiveEvent;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;
import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;


public class DeleteBPMessageCommand extends AbstractBPCommand
{

   private AbstractMessage message;

   private SequenceDiagram diagram;

   private SendEvent sendEvent;

   private ReceiveEvent receiveEvent;

   private List<BPArgument> arguments;


   public DeleteBPMessageCommand(AbstractMessage message,
         BehavioralPatternEditPart diagramEditPart)
   {
      super("delete message", diagramEditPart);
      this.message = message;
      this.diagram = message.getDiagram();
      this.sendEvent = message.getSendEvent();
      this.receiveEvent = message.getReceiveEvent();
      if (message instanceof BPMessage)
      {
         this.arguments = ((BPMessage) message).getArguments();
      }
   }


   @Override
   public void undo()
   {
      AbstractMessage model = this.getModel();
      model.setDiagram(this.diagram);
      model.setSendEvent(this.sendEvent);
      model.setReceiveEvent(this.receiveEvent);
      if (model instanceof BPMessage)
      {
         ((BPMessage) model).getArguments().addAll(this.arguments);
      }
      super.undo();
   }


   @Override
   public void redo()
   {
      AbstractMessage model = this.getModel();
      if (model instanceof BPMessage)
      {
         ((BPMessage) model).getArguments().clear();
      }
      model.getDiagram().getFragments().remove(model.getSendEvent());
      model.getDiagram().getFragments().remove(model.getReceiveEvent());
      model.getDiagram().getEvents().remove(model.getSendEvent());
      model.getDiagram().getEvents().remove(model.getReceiveEvent());
      model.getSendEvent().setLifeline(null);
      model.getReceiveEvent().setLifeline(null);
      model.getSendEvent().setParentOperand(null);
      model.getReceiveEvent().setParentOperand(null);
      model.setSendEvent(null);
      model.setReceiveEvent(null);
      model.getDiagram().getMessages().remove(model);
      super.redo();
   }


   protected AbstractMessage getModel()
   {
      return (AbstractMessage) this.message;
   }
}
