package org.reclipse.behavior.specification.ui.commands;


import org.eclipse.emf.common.util.EList;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;
import org.reclipse.structure.specification.PSObject;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.Lifeline;
import de.uni_paderborn.basicSequenceDiagram.LifelineFragment;
import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;


public class DeleteBPObjectCommand extends AbstractBPCommand
{

   private AbstractSequenceDiagramObject object;

   private SequenceDiagram diagram;

   private Lifeline lifeline;

   private PSObject psObject;

   private EList<LifelineFragment> fragments;


   public DeleteBPObjectCommand(AbstractSequenceDiagramObject object, BehavioralPatternEditPart diagramEditPart)
   {
      super("delete object", diagramEditPart);
      this.object = object;
      this.diagram = object.getDiagram();
      this.lifeline = object.getLifeline();
      this.fragments = this.lifeline.getFragments();
      if (this.object instanceof BPObject)
      {
         this.psObject = ((BPObject) this.object).getTypeReference();
      }
      else if (this.object instanceof BPSetObject)
      {
         this.psObject = ((BPSetObject) this.object).getTypeReference();
      }
   }

   @Override
   public void undo()
   {
      AbstractSequenceDiagramObject model = this.getModel();
      model.setDiagram(this.diagram);
      model.setLifeline(this.lifeline);
      model.getLifeline().getFragments().addAll(this.fragments);
      if (model instanceof BPObject)
      {
         ((BPObject) model).setTypeReference(this.psObject);
      }
      else if (model instanceof BPSetObject)
      {
         ((BPSetObject) model).setTypeReference(this.psObject);
      }
      super.undo();
   }


   @Override
   public void redo()
   {
      AbstractSequenceDiagramObject model = this.getModel();
      model.getLifeline().getFragments().clear();
      model.getDiagram().getObjects().remove(model);
      model.setLifeline(null);
      super.redo();
   }


   protected AbstractSequenceDiagramObject getModel()
   {
      return (AbstractSequenceDiagramObject) this.object;
   }
}
