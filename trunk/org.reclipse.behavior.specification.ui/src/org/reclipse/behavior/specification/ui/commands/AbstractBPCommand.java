package org.reclipse.behavior.specification.ui.commands;


import org.eclipse.gef.commands.Command;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;


public abstract class AbstractBPCommand extends Command
{

   private BehavioralPatternEditPart diagramEditPart;


   public BehavioralPatternEditPart getBehavioralPatternEditPart()
   {
      return this.diagramEditPart;
   }


   public void setDiagramEditPart(BehavioralPatternEditPart diagramEditPart)
   {
      this.diagramEditPart = diagramEditPart;
   }


   public AbstractBPCommand(String label,
         BehavioralPatternEditPart diagramEditPart)
   {
      super(label);
      this.diagramEditPart = diagramEditPart;
   }


   @Override
   public void execute()
   {
      redo();
   }
   
   @Override
   public void redo()
   {
      getBehavioralPatternEditPart().relayout();
   }

   
   @Override
   public void undo()
   {
      getBehavioralPatternEditPart().relayout();
   }
   
   


}
