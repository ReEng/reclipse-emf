package org.reclipse.behavior.specification.ui.editpolicies;


import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.ui.commands.DeleteBPAssignmentCommand;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class DeleteBPAssignmentEditPolicy extends ComponentEditPolicy
{

   /**
    * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
    */
   @Override
   protected Command createDeleteCommand(GroupRequest request)
   {
      CompoundCommand result = new CompoundCommand();
      
      BPAssignment element = (BPAssignment) this
            .getHost().getModel();
      DeleteBPAssignmentCommand deleteCommand = new DeleteBPAssignmentCommand(element,
            (BehavioralPatternEditPart) this.getHost().getParent());
      result.add(deleteCommand);
      return result.unwrap();
   }


}
