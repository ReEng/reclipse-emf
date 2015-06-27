package org.reclipse.behavior.specification.ui.commands;


import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;


public class CreateBPObjectCommand extends AbstractCreateObjectCommand
{

   public CreateBPObjectCommand(BehavioralPatternEditPart diagramEditPart)
   {
      super(diagramEditPart);
   }


   @Override
   public void redo()
   {
      this.object = BehavioralpatternFactory.eINSTANCE.createBPObject();

      this.object.setDiagram(this.getBehavioralPatternEditPart().getModel());

      PSPatternSpecification patternSpecification = ((BehavioralPattern) this.object
            .getDiagram()).getPsPatternSpecification();
      PSObject tempPSObject = getAvailablePSObject(patternSpecification);
      ((BPObject) this.object).setTypeReference(tempPSObject);

      initializeObject();

      super.redo();
   }


   protected BPObject getModel()
   {
      return (BPObject) this.object;
   }
}
