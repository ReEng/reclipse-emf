package org.reclipse.behavior.specification.ui.editpolicies;


import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.reclipse.behavior.specification.BPAnyObject;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.commands.CreateBPAssignmentCommand;
import org.reclipse.behavior.specification.ui.commands.CreateBPMessageCommand;
import org.reclipse.behavior.specification.ui.commands.CreateBPObjectCommand;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;
import org.reclipse.behavior.specification.ui.editparts.BPLifelineEditPart;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Lifeline;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class CreateBPAssignmentEditPolicy extends LayoutEditPolicy
{



   private BPLifelineEditPart lifelineEditPart;


   public CreateBPAssignmentEditPolicy(BPLifelineEditPart lifelineEditPart)
   {
      super();
      this.lifelineEditPart = lifelineEditPart;
   }


   @Override
   protected EditPolicy createChildEditPolicy(EditPart child)
   {
      // TODO Auto-generated method stub
      return null;
   }


   @Override
   protected Command getCreateCommand(CreateRequest request)
   {
      Class type = (Class) request.getNewObjectType();
      if (BPAssignment.class.isAssignableFrom(type))
      {
         return getCreateBPAssignmentCommand(request);
      }
      return null;
   }


   private Command getCreateBPAssignmentCommand(CreateRequest request)
   {
      return new CreateBPAssignmentCommand(this.lifelineEditPart);
   }


   @Override
   protected Command getMoveChildrenCommand(Request request)
   {
      // TODO Auto-generated method stub
      return null;
   }
   
   
   
   
}
