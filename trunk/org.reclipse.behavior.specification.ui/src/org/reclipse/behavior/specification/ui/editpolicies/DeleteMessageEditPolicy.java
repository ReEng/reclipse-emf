
package org.reclipse.behavior.specification.ui.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.reclipse.behavior.specification.ui.commands.DeleteBPMessageCommand;
import org.reclipse.behavior.specification.ui.editparts.BPMessageEditPart;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.RootFragment;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class DeleteMessageEditPolicy extends ConnectionEditPolicy
{

   /**
    * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
    */
   @Override
   protected Command getDeleteCommand(GroupRequest request)
   {
      AbstractMessage message = (AbstractMessage) getHost().getModel();

         // check conditions
         InteractionOperand operand = message.getSendEvent().getParentOperand();
         CombinedFragment combinedFragment = operand.getParentFragment();
         if (!(combinedFragment instanceof RootFragment)
               && operand.getFragments().size() == 1)
         {
            // the surrounding InteractionOperand would become empty,
            // that's an illegal state, do nothing
            return null;
         }

         BehavioralPatternEditPart diagramEditPart = (BehavioralPatternEditPart) ((BPMessageEditPart)this.getHost()).getSource().getParent();
         DeleteBPMessageCommand deleteCommand = new DeleteBPMessageCommand(message, diagramEditPart);
         return deleteCommand;
      
   }

}
