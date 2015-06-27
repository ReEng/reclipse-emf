package org.reclipse.behavior.specification.ui.editparts;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.reclipse.behavior.specification.ui.editpolicies.SelectAnotherFigureFromPrimaryLayerEditPolicy;

import de.uni_paderborn.sequencediagram.viewer.editparts.InteractionOperandEditPart;


public class BPInteractionOperandEditPart extends InteractionOperandEditPart
{

   /**
    * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
    */
   @Override
   protected void createEditPolicies()
   {
      // TODO
      // installEditPolicy(EditPolicy.COMPONENT_ROLE,
      // new UMLInteractionOperandComponentEditPolicy());

      // deactivated to allow select the Operand and edit his guard in the Property-view
      List excludeEditParts = new ArrayList();
      // excludeEditParts.add(AbstractCombinedFragmentEditPart.class);
      excludeEditParts.add(BPInteractionOperandEditPart.class);

      installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectAnotherFigureFromPrimaryLayerEditPolicy(
            excludeEditParts));
   }


   public BehavioralPatternEditPart getDiagramEditPart()
   {
      AbstractBPCombinedFragmentEditPart parent = (AbstractBPCombinedFragmentEditPart) getParent();
      return (BehavioralPatternEditPart) parent.getDiagramEditPart();
   }

}
