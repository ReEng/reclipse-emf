package org.reclipse.behavior.specification.ui.commands;


import org.reclipse.behavior.specification.BPAnyObject;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;


public class CreateBPAnyObjectCommand extends AbstractCreateObjectCommand
{

   public CreateBPAnyObjectCommand(BehavioralPatternEditPart diagramEditPart)
   {
      super(diagramEditPart);
   }


   @Override
   public void redo()
   {
      this.object = BehavioralpatternFactory.eINSTANCE.createBPAnyObject();

      this.object.setDiagram(this.getBehavioralPatternEditPart().getModel());

      initializeObject();

      super.redo();
   }


   protected BPAnyObject getModel()
   {
      return (BPAnyObject) this.object;
   }
}
