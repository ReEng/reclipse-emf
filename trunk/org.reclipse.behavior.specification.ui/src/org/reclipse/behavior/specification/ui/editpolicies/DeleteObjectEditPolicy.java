package org.reclipse.behavior.specification.ui.editpolicies;


import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.ui.commands.DeleteBPAssignmentCommand;
import org.reclipse.behavior.specification.ui.commands.DeleteBPMessageCommand;
import org.reclipse.behavior.specification.ui.commands.DeleteBPObjectCommand;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;
import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.LifelineFragment;
import de.uni_paderborn.basicSequenceDiagram.ReceiveEvent;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class DeleteObjectEditPolicy extends ComponentEditPolicy
{

   /**
    * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
    */
   @Override
   protected Command createDeleteCommand(GroupRequest request)
   {
      CompoundCommand result = new CompoundCommand();

      AbstractSequenceDiagramObject element = (AbstractSequenceDiagramObject) this
            .getHost().getModel();
      // also delete all messages from or to this object and all assignments associated with the
      // object's lifeline
      List<LifelineFragment> lifeline = element.getLifeline().getFragments();
      for (LifelineFragment lifelineFragment : lifeline)
      {
         AbstractMessage message = null;
         if (lifelineFragment instanceof SendEvent)
         {
            message = ((SendEvent) lifelineFragment).getMessage();
         }
         else if (lifelineFragment instanceof ReceiveEvent)
         {
            message = ((ReceiveEvent) lifelineFragment).getMessage();
         }
         else if (lifelineFragment instanceof BPAssignment)
         {
            DeleteBPAssignmentCommand deleteAssignmentCommand = new DeleteBPAssignmentCommand(
                  (BPAssignment) lifelineFragment,
                  (BehavioralPatternEditPart) this.getHost().getParent());
            result.add(deleteAssignmentCommand);
         }
         if (message != null)
         {
            DeleteBPMessageCommand deleteMessageCommand = new DeleteBPMessageCommand(
                  message, (BehavioralPatternEditPart) this.getHost()
                        .getParent());
            result.add(deleteMessageCommand);
         }
      }
      DeleteBPObjectCommand deleteCommand = new DeleteBPObjectCommand(element,
            (BehavioralPatternEditPart) this.getHost().getParent());
      result.add(deleteCommand);
      return result.unwrap();
   }


}
