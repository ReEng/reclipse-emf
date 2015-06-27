package org.reclipse.behavior.specification.ui.editpolicies;


import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.reclipse.behavior.specification.ui.actions.AddInteractionOperandAction;
import org.reclipse.behavior.specification.ui.commands.AddInteractionOperandCommand;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;


/**
 * @author Dietrich Travkin
 * @author Last Editor: $Author: lowende $
 * @version $Revision: 366 $ $Date: 2006-10-09 18:59:20 +0200 (Mo, 09 Okt 2006) $
 */
public class CombinedFragmentWithMultipleOperandsEditPolicy extends
      XYLayoutEditPolicy
{

   private final BehavioralPatternEditPart diagramEditPart;


   public CombinedFragmentWithMultipleOperandsEditPolicy(
         BehavioralPatternEditPart diagramEditPart)
   {
      this.diagramEditPart = diagramEditPart;
   }


   /**
    * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#getCommand(org.eclipse.gef.Request)
    */
   @Override
   public Command getCommand(Request request)
   {
      if (AddInteractionOperandAction.REQUEST_NAME.equals(request.getType()))
      {
         CombinedFragment fragment = (CombinedFragment) this.getHost()
               .getModel();
         AddInteractionOperandCommand command = new AddInteractionOperandCommand(
               fragment, this.diagramEditPart);
         return command;
      }
      return super.getCommand(request);
   }


   /**
    * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart,
    *      java.lang.Object)
    */
   @Override
   protected Command createAddCommand(EditPart child, Object constraint)
   {
      return null;
   }


   /**
    * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
    *      java.lang.Object)
    */
   @Override
   protected Command createChangeConstraintCommand(EditPart child,
         Object constraint)
   {
      return null;
   }


   /**
    * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
    */
   @Override
   protected Command getCreateCommand(CreateRequest request)
   {
      return null;
   }


   /**
    * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
    */
   @Override
   protected Command getDeleteDependantCommand(Request request)
   {
      return null;
   }

}
