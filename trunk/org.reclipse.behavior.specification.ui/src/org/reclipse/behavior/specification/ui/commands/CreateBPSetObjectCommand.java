package org.reclipse.behavior.specification.ui.commands;


import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;


public class CreateBPSetObjectCommand extends AbstractCreateObjectCommand
{

   public CreateBPSetObjectCommand(BehavioralPatternEditPart diagramEditPart)
   {
      super(diagramEditPart);
   }


   @Override
   public void redo()
   {
      this.object = BehavioralpatternFactory.eINSTANCE.createBPSetObject();

      this.object.setDiagram(this.getBehavioralPatternEditPart().getModel());

      PSPatternSpecification patternSpecification = ((BehavioralPattern) this.object
            .getDiagram()).getPsPatternSpecification();

      PSObject tempPSObject = getAvailablePSObject(patternSpecification);

      ((BPSetObject) this.object).setTypeReference(tempPSObject);

      initializeObject();

      super.redo();
   }


   protected BPSetObject getModel()
   {
      return (BPSetObject) this.object;
   }
}
