package org.reclipse.behavior.specification.ui.editparts;


import org.eclipse.gef.EditPolicy;
import org.reclipse.behavior.specification.ui.editpolicies.CombinedFragmentWithMultipleOperandsEditPolicy;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class AlternativeFragmentEditPart extends AbstractBPCombinedFragmentEditPart
{

   @Override
   public String getFragmentLabelText()
   {
      return "alternative";
   }


   @Override
   protected void createEditPolicies()
   {
      super.createEditPolicies();

      this.installEditPolicy(EditPolicy.CONTAINER_ROLE, new CombinedFragmentWithMultipleOperandsEditPolicy(
            (BehavioralPatternEditPart) getDiagramEditPart()));
   }

}
