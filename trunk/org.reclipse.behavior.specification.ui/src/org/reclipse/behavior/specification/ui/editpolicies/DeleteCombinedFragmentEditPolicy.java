
package org.reclipse.behavior.specification.ui.editpolicies;


import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.reclipse.behavior.specification.ui.commands.DeleteCombinedFragmentCommand;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;



/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class DeleteCombinedFragmentEditPolicy extends ComponentEditPolicy
{

   private final BehavioralPatternEditPart diagramEditPart;


   public DeleteCombinedFragmentEditPolicy(
         BehavioralPatternEditPart diagramEditPart)
   {
      this.diagramEditPart = diagramEditPart;
   }


   /**
    * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
    */
   @Override
   public Command getCommand(Request request)
   {
      if (REQ_CREATE.equals(request.getType()))
      {
         return getCreateCommand((CreateRequest) request);
      }

      return super.getCommand(request);
   }


   /**
    * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
    */
   @Override
   protected Command createDeleteCommand(GroupRequest deleteRequest)
   {
      CombinedFragment fragment = (CombinedFragment) getHost().getModel();

         DeleteCombinedFragmentCommand deleteCmd = new DeleteCombinedFragmentCommand(
               fragment, diagramEditPart);
         return deleteCmd;
      
   }


   protected Command getCreateCommand(CreateRequest request)
   {
      EditPart parent = this.getHost().getParent();
      while (parent != null && !(parent instanceof BehavioralPatternEditPart))
      {
         parent = parent.getParent();
      }

      if (parent == null)
      {
         return null;
      }

      return parent.getCommand(request);
   }

}
