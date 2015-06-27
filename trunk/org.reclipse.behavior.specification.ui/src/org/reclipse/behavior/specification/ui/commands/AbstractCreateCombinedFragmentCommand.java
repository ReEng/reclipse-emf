package org.reclipse.behavior.specification.ui.commands;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramFactory;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;
import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;


public abstract class AbstractCreateCombinedFragmentCommand extends AbstractBPCommand
{

   private SequenceDiagram diagram;

   protected InteractionOperand parentOperand;


   public void setParentOperand(InteractionOperand operand)
   {
      this.parentOperand = operand;
   }


   private List<Fragment> children = null;


   public AbstractCreateCombinedFragmentCommand(BehavioralPatternEditPart diagramEditPart)
   {
      super("create combined fragment", diagramEditPart);
   }


   public void setDiagram(SequenceDiagram diagram)
   {
      this.diagram = diagram;
   }


   protected SequenceDiagram getDiagram()
   {
      return this.diagram;
   }


   public void addChild(Fragment child)
   {
      if (this.children == null)
      {
         this.children = new ArrayList<Fragment>();
      }
      this.children.add(child);
   }


   public boolean hasInChildren(Fragment child)
   {
      if (this.children != null)
      {
         return this.children.contains(child);
      }
      return false;
   }


   public List<Fragment> getChildren()
   {
      if (this.children != null)
      {
         return new ArrayList<Fragment>(this.children);
      }
      else
      {
         return new ArrayList<Fragment>(0); // empty list
      }
   }


   protected abstract CombinedFragment createCombinedFragment();

   private CombinedFragment createdFragment;


   @Override
   public void redo()
   {
      this.createdFragment = createCombinedFragment();

      if (this.children != null && this.children.size() > 0)
      {
         InteractionOperand operand = BasicSequenceDiagramFactory.eINSTANCE.createInteractionOperand();
         this.createdFragment.getOperands().add(operand);
         BehavioralPattern behavioralPattern = (BehavioralPattern) this.getBehavioralPatternEditPart().getModel();

         // add new combined fragment to the diagram's and to the parent operand's lists of
         // fragments at the right position
         int index = behavioralPattern.getFragments().indexOf(this.children.get(0));
         if (this.children.get(0) instanceof SendEvent)
         {
            index++;
            // do not store combined fragment between receive events and send events but
            // behind (a send event follows each receive event)
         }
         behavioralPattern.getFragments().add(index, this.createdFragment);
         index = this.parentOperand.getFragments().indexOf(this.children.get(0));
         if (this.children.get(0) instanceof SendEvent)
         {
            index++;
         }
         this.parentOperand.getFragments().add(index, this.createdFragment);

         // add children to operand's fragments
         for (Fragment fragment : this.diagram.getFragments())
         {
            if (this.children.contains(fragment) && !operand.getFragments().contains(fragment))
            {
               operand.getFragments().add(fragment);
            }
         }
      }

      super.redo();
   }


   @Override
   public void undo()
   {
      CombinedFragment model = this.createdFragment;
      ((SequenceDiagram) model.eContainer()).getFragments().remove(model);
      InteractionOperand parentOperand = model.getParentOperand();
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
         fragment.setParentOperand(parentOperand);
      }
      model.getOperands().clear();

      super.undo();
   }


}
