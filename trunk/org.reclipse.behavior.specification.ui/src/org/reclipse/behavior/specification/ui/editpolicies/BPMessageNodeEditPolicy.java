package org.reclipse.behavior.specification.ui.editpolicies;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.commands.CreateBPMessageCommand;
import org.reclipse.behavior.specification.ui.editparts.BPAssignmentEditPart;
import org.reclipse.behavior.specification.ui.editparts.BPMessageEditPart;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.Lifeline;
import de.uni_paderborn.basicSequenceDiagram.ReceiveEvent;
import de.uni_paderborn.basicSequenceDiagram.RootFragment;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BPMessageNodeEditPolicy extends GraphicalNodeEditPolicy
{

   private final BehavioralPatternEditPart diagramEditPart;


   public BPMessageNodeEditPolicy(BehavioralPatternEditPart diagramEditPart)
   {
      this.diagramEditPart = diagramEditPart;
   }


   protected CreateBPMessageCommand createCommand(
         AbstractSequenceDiagramObject modelElement)
   {
      return new CreateBPMessageCommand(modelElement, this.diagramEditPart);
   }


   protected BPMessageValidator getConnectionValidator()
   {
      return BPMessageValidator.get();
   }


   protected CreateBPMessageCommand getConnectionCreateCommand(
         CreateConnectionRequest request)
   {
      CreateBPMessageCommand command = null;
      Class connectionType = (Class) request.getNewObjectType();
      AbstractSequenceDiagramObject modelElement = null;
      if (getHost().getModel() instanceof Lifeline)
      {
         modelElement = (AbstractSequenceDiagramObject) ((Lifeline) getHost()
               .getModel()).getObject();
      }
      else if (getHost().getModel() instanceof AbstractSequenceDiagramObject)
      {
         modelElement = (AbstractSequenceDiagramObject) getHost().getModel();
      }


      if (getConnectionValidator().isValidSource(connectionType, modelElement))
      {
         command = createCommand(modelElement);
         command.setSource(modelElement);
         command.setStartLocation(request.getLocation());
         command.setDiagram((BehavioralPattern) modelElement.getDiagram());
      }

      if (command != null)
      {
         request.setStartCommand(command);
      }

      return command;
   }


   @Override
   protected Command getConnectionCompleteCommand(
         CreateConnectionRequest request)
   {
      CreateBPMessageCommand command = (CreateBPMessageCommand) request
            .getStartCommand();

      Class connectionType = (Class) request.getNewObjectType();
      AbstractSequenceDiagramObject target = null;
      if (getHost().getModel() instanceof Lifeline)
      {
         target = (AbstractSequenceDiagramObject) ((Lifeline) getHost()
               .getModel()).getObject();
      }
      else if (getHost().getModel() instanceof AbstractSequenceDiagramObject)
      {
         target = (AbstractSequenceDiagramObject) getHost().getModel();
      }

      if (getConnectionValidator().isValidConnection(connectionType,
            command.getSource(), target))
      {
         command.setTarget(target);
         command.setEndLocation(request.getLocation());
         command.setCombinedFragment(findSurroundingCombinedFragment(command,
               command.getStartLocation()));

         // when point is not within an operand, no assertion is possible
         if (findOperandForInsertion(command) == null)
            return null;

         command.setParentOperand(findOperandForInsertion(command));
         command.setPredecessor(findPredecessingFragment(command));

         return command;
      }

      return null;
   }


   private Fragment findPredecessingFragment(CreateBPMessageCommand command)
   {
      Fragment predecessor = null;

      // search for predecessor within the own operand
      predecessor = findLastFragmentWithinAnOperand(predecessor, command,
            command.getParentOperand());

      // this case happens, when one create the first message within an operand (in case of
      // alternative)
      // i.e. there is no predecessor in the own operand
      // so in this case, the search will be done in the previous operands of the same
      // combinedFragment
      // this would never goes to infinite loop.
      if (predecessor == null
            && !(command.getParentOperand().getParentFragment() instanceof RootFragment))
      {
         InteractionOperand operand = command.getParentOperand();
         CombinedFragment parentFragment = operand.getParentFragment();

         while (predecessor == null)
         {
            int index = parentFragment.getOperands().indexOf(operand);
            if (index - 1 < parentFragment.getOperands().size())
            {
               break;
            }
            operand = parentFragment.getOperands().get(index - 1);
            predecessor = findLastFragmentWithinAnOperand(predecessor, command,
                  operand);
         }
      }
      return predecessor;
   }


   protected Fragment findLastFragmentWithinAnOperand(Fragment predecessor,
         CreateBPMessageCommand command, InteractionOperand operand)
   {
      Fragment predecessorFragment = predecessor;

      int maxY = 0;
      List<Fragment> fragments = operand.getFragments();
      for (Fragment fragment : fragments)
      {
         int y = 0;
         if (fragment instanceof SendEvent)
         {
            BPMessage message = (BPMessage) ((SendEvent) fragment).getMessage();
            BPMessageEditPart editPart = (BPMessageEditPart) this
                  .getEditPartForModel(message);
            y = ((PolylineConnection) editPart.getFigure()).getStart().y;
         }
         else if (fragment instanceof ReceiveEvent)
         {
            BPMessage message = (BPMessage) ((ReceiveEvent) fragment)
                  .getMessage();
            BPMessageEditPart editPart = (BPMessageEditPart) this
                  .getEditPartForModel(message);
            y = ((PolylineConnection) editPart.getFigure()).getStart().y;
         }
         else if (fragment instanceof BPAssignment)
         {
            BPAssignmentEditPart editPart = (BPAssignmentEditPart) this
                  .getEditPartForModel((BPAssignment) fragment);
            Rectangle bounds = editPart.getFigure().getBounds();
            y = bounds.y + bounds.height;
         }
         else if (fragment instanceof CombinedFragment)
         {
            AbstractGraphicalEditPart editPart = getEditPartForModel(fragment);
            Rectangle bounds = editPart.getFigure().getBounds();
            y = bounds.y + bounds.height;
         }
         if (command.getStartLocation().y > y && y > maxY)
         {
            maxY = y;
            predecessorFragment = fragment;
         }
      }
      return predecessorFragment;
   }


   private InteractionOperand findOperandForInsertion(
         CreateBPMessageCommand command)
   {
      CombinedFragment combinedFragment = command.getCombinedFragment();

      InteractionOperand operand = findOperandForInsertion(combinedFragment,
            command.getStartLocation());

      return operand;
   }


   private InteractionOperand findOperandForInsertion(
         CombinedFragment combinedFragment, Point point)
   {
      InteractionOperand operand = null;

      if (combinedFragment instanceof RootFragment)
      {
         operand = combinedFragment.getOperands().get(0);
      }
      else
      {
         Iterator<InteractionOperand> operandsIter = combinedFragment
               .getOperands().iterator();
         while (operandsIter.hasNext() && operand == null)
         {
            InteractionOperand tmpOperand = operandsIter.next();
            AbstractGraphicalEditPart editPart = getEditPartForModel(tmpOperand);
            if (editPart.getFigure().getBounds().contains(point))
            {
               operand = tmpOperand;
            }
         }
      }

      return operand;
   }


   private CombinedFragment findSurroundingCombinedFragment(
         CreateBPMessageCommand command, Point point)
   {
      BehavioralPattern diagram = command.getDiagram();

      return findSurroundingCombinedFragment(diagram.getRootFragment(), point);
   }


   private CombinedFragment findSurroundingCombinedFragment(
         CombinedFragment combinedFragment, Point point)
   {
      CombinedFragment surroundingCombinedFragment = null;

      List<InteractionOperand> operands = combinedFragment.getOperands();
      for (InteractionOperand operand : operands)
      {
         surroundingCombinedFragment = findSurroundingCombinedFragment(operand,
               point);
      }

      if (surroundingCombinedFragment == null)
      {
         if (combinedFragment instanceof RootFragment)
         {
            surroundingCombinedFragment = combinedFragment;
         }
         else
         {
            AbstractGraphicalEditPart editPart = getEditPartForModel(combinedFragment);
            Rectangle fragmentBounds = editPart.getFigure().getBounds();
            if (fragmentBounds.contains(point))
            {
               surroundingCombinedFragment = combinedFragment;
            }
         }
      }
      return surroundingCombinedFragment;
   }


   private CombinedFragment findSurroundingCombinedFragment(
         InteractionOperand operand, Point point)
   {
      CombinedFragment surroundingCombinedFragment = null;

      List<Fragment> fragments = operand.getFragments();
      for (Fragment fragment : fragments)
      {
         if (fragment instanceof CombinedFragment)
         {
            CombinedFragment combinedFragment = (CombinedFragment) fragment;
            if (findSurroundingCombinedFragment(combinedFragment, point) != null)
            {
               surroundingCombinedFragment = findSurroundingCombinedFragment(
                     combinedFragment, point);
            }
         }
      }
      return surroundingCombinedFragment;
   }


   protected AbstractGraphicalEditPart getEditPartForModel(Object model)
   {
      EditPartViewer editPartViewer = this.diagramEditPart.getViewer();
      Map editPartRegistry = editPartViewer.getEditPartRegistry();

      return (AbstractGraphicalEditPart) editPartRegistry.get(model);
   }


   @Override
   protected Command getReconnectSourceCommand(ReconnectRequest request)
   {
      // TODO Auto-generated method stub
      return null;
   }


   @Override
   protected Command getReconnectTargetCommand(ReconnectRequest request)
   {
      // TODO Auto-generated method stub
      return null;
   }
}
