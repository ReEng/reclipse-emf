package org.reclipse.behavior.specification.ui.commands;


import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;
import org.reclipse.behavior.specification.ui.util.ASTUtil;
import org.reclipse.behavior.specification.ui.wizards.CreateBPMessageWizard;
import org.reclipse.structure.specification.PSObject;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;
import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramFactory;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.ReceiveEvent;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;


public class CreateBPMessageCommand extends AbstractBPCommand
{
   private BehavioralPattern diagram;

   private AbstractSequenceDiagramObject source;

   private AbstractSequenceDiagramObject target;

   private BPMessage bpMessage;

   private PSObject psObject;

   private List<String> argNames;

   private HashMap<String, PSObject> argObjects;

   private InteractionOperand parentOperand;

   private CombinedFragment combinedFragment;

   private Fragment predecessor;


   public Fragment getPredecessor()
   {
      return this.predecessor;
   }


   public void setPredecessor(Fragment predecessor)
   {
      this.predecessor = predecessor;
   }


   public CombinedFragment getCombinedFragment()
   {
      return this.combinedFragment;
   }


   public void setCombinedFragment(CombinedFragment combinedFragment)
   {
      this.combinedFragment = combinedFragment;
   }


   public void setParentOperand(InteractionOperand parentOperand)
   {
      this.parentOperand = parentOperand;
   }


   public InteractionOperand getParentOperand()
   {
      return this.parentOperand;
   }


   public PSObject getPSObject()
   {
      return this.psObject;
   }


   public void setPSObject(PSObject psObject)
   {
      this.psObject = psObject;
   }


   public CreateBPMessageCommand(AbstractSequenceDiagramObject object, BehavioralPatternEditPart diagramEditPart)
   {
      super("create message", diagramEditPart);
      this.source = object;
   }


   public BPMessage getBpMessage()
   {
      return this.bpMessage;
   }


   public void setBpMessage(BPMessage bpMessage)
   {
      this.bpMessage = bpMessage;
   }


   public void setDiagram(BehavioralPattern diagram)
   {
      this.diagram = diagram;
   }


   public BehavioralPattern getDiagram()
   {
      return diagram;
   }


   public AbstractSequenceDiagramObject getSource()
   {
      return this.source;
   }


   public void setSource(AbstractSequenceDiagramObject source)
   {
      this.source = source;
   }


   public AbstractSequenceDiagramObject getTarget()
   {
      return this.target;
   }


   public void setTarget(AbstractSequenceDiagramObject target)
   {
      this.target = target;
   }


   @Override
   public void redo()
   {
      CreateBPMessageWizard wizard = new CreateBPMessageWizard(this);
      WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
            wizard);
      wizardDialog.create();
      wizardDialog.open();
      super.redo();
   }


   public void createMessage()
   {
      this.bpMessage = BehavioralpatternFactory.eINSTANCE.createBPMessage();

      this.createArguments();

      this.bpMessage.setDiagram(this.diagram);
      int index = 0;

      // insert into list of fragments
      SendEvent sendEvent = BasicSequenceDiagramFactory.eINSTANCE.createSendEvent();
      if (this.predecessor != null)
      {
         index = this.diagram.getFragments().indexOf(this.predecessor) + 1;
         if (this.predecessor instanceof ReceiveEvent)
         {
            index++;
         }
      }
      this.diagram.getFragments().add(index, sendEvent);
      sendEvent.setLifeline(source.getLifeline());
      ReceiveEvent receiveEvent = BasicSequenceDiagramFactory.eINSTANCE.createReceiveEvent();
      this.diagram.getFragments().add(index, receiveEvent);

      // insert into list of events
      if (this.predecessor != null)
      {
         index = this.diagram.getEvents().indexOf(this.predecessor) + 1;
         if (this.predecessor instanceof ReceiveEvent)
         {
            index++;
         }
      }
      this.diagram.getEvents().add(index, sendEvent);
      this.diagram.getEvents().add(index, receiveEvent);


      this.bpMessage.setSelfCall(false);
      this.bpMessage.setSendEvent(sendEvent);
      this.bpMessage.setReceiveEvent(receiveEvent);
      this.bpMessage.setMethodReference(psObject);
      this.bpMessage.setName(psObject.getName());
      receiveEvent.setLifeline(target.getLifeline());

      if (this.predecessor != null)
      {
         index = this.parentOperand.getFragments().indexOf(this.predecessor) + 1;
         if (this.predecessor instanceof ReceiveEvent)
         {
            index++;
         }
      }
      this.parentOperand.getFragments().add(index, sendEvent);
      this.parentOperand.getFragments().add(index, receiveEvent);
   }


   private void createArguments()
   {
      for (String argName : this.argNames)
      {
         BPArgument argument = BehavioralpatternFactory.eINSTANCE.createBPArgument();
         argument.setMessage(this.bpMessage);
         argument.setType(ASTUtil.getParamType(argObjects.get(argName)));
         argument.setName(argName);
      }
   }


   public void setArgNames(List<String> argumentNames)
   {
      this.argNames = argumentNames;
   }


   public void setArgObjects(HashMap<String, PSObject> objects)
   {
      this.argObjects = objects;
   }


   protected Point startLocation = null;


   public void setStartLocation(Point startLocation)
   {
      this.startLocation = startLocation;
   }


   public Point getStartLocation()
   {
      return this.startLocation;
   }


   protected Point endLocation = null;


   public void setEndLocation(Point endLocation)
   {
      this.endLocation = endLocation;
   }


   public Point getEndLocation()
   {
      return this.endLocation;
   }


   @Override
   public void undo()
   {
      AbstractMessage model = this.bpMessage;
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
      super.undo();
   }
}
