package org.reclipse.behavior.specification.ui.editpolicies;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.reclipse.behavior.specification.BPAnyObject;
import org.reclipse.behavior.specification.BPEachFragment;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.ui.commands.AbstractCreateCombinedFragmentCommand;
import org.reclipse.behavior.specification.ui.commands.AbstractCreateObjectCommand;
import org.reclipse.behavior.specification.ui.commands.CreateAlternativeFragmentCommand;
import org.reclipse.behavior.specification.ui.commands.CreateBPAnyObjectCommand;
import org.reclipse.behavior.specification.ui.commands.CreateBPEachFragmentCommand;
import org.reclipse.behavior.specification.ui.commands.CreateBPObjectCommand;
import org.reclipse.behavior.specification.ui.commands.CreateBPSetObjectCommand;
import org.reclipse.behavior.specification.ui.commands.CreateLoopFragmentCommand;
import org.reclipse.behavior.specification.ui.commands.CreateOptionalFragmentCommand;
import org.reclipse.behavior.specification.ui.commands.MoveObjectCommand;
import org.reclipse.behavior.specification.ui.editparts.AbstractBPObjectEditPart;
import org.reclipse.behavior.specification.ui.editparts.BPAssignmentEditPart;
import org.reclipse.behavior.specification.ui.editparts.BPMessageEditPart;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;
import org.reclipse.behavior.specification.ui.editparts.BPLifelineEditPart;

import de.uni_paderborn.basicSequenceDiagram.AlternativeFragment;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.LifelineFragment;
import de.uni_paderborn.basicSequenceDiagram.LoopFragment;
import de.uni_paderborn.basicSequenceDiagram.OptionalFragment;
import de.uni_paderborn.basicSequenceDiagram.RootFragment;
import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BehavioralPatternLayoutEditPolicy extends XYLayoutEditPolicy
{

   protected BehavioralPatternEditPart diagramEditPart;


   public BehavioralPatternLayoutEditPolicy(
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
      if (REQ_RESIZE_CHILDREN.equals(request.getType()))
      {
         return getResizeChildrenCommand((ChangeBoundsRequest) request);
      }

      return super.getCommand(request);
   }


   /**
    * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
    */
   @Override
   protected EditPolicy createChildEditPolicy(EditPart child)
   {
      return null;
   }


   /**
    * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
    */
   @Override
   protected Command getCreateCommand(CreateRequest request)
   {
      Class type = (Class) request.getNewObjectType();
      if (BPObject.class.isAssignableFrom(type))
      {
         return getCreateObjectCommand(request,
               getCreateBPObjectCommand(request));
      }
      else if (BPSetObject.class.isAssignableFrom(type))
      {
         return getCreateObjectCommand(request,
               getCreateBPSetObjectCommand(request));
      }
      else if (BPAnyObject.class.isAssignableFrom(type))
      {
         return getCreateObjectCommand(request,
               getCreateBPAnyObjectCommand(request));
      }
      else if (CombinedFragment.class.isAssignableFrom(type))
      {
         return getCreateCombinedFragmentCommand(request);
      }

      return null;
   }

   protected Rectangle getConstraintFor(CreateRequest request)
   {
      IFigure figure = this.getLayoutContainer();

      Point location = request.getLocation().getCopy();
      Dimension size = request.getSize();

      figure.translateToRelative(location);
      figure.translateFromParent(location);
      location.translate(this.getLayoutContainer().getClientArea()
            .getLocation().getNegated());

      if (size == null || size.isEmpty())
      {
         return new Rectangle(location.x, location.y, -1, -1);
      }
      else
      {
         size = size.getCopy();
         figure.translateToRelative(size);
         figure.translateFromParent(size);
         return new Rectangle(location, size);
      }
   }


   protected AbstractCreateObjectCommand getCreateBPObjectCommand(
         CreateRequest request)
   {
      return new CreateBPObjectCommand(this.diagramEditPart);
   }


   protected AbstractCreateObjectCommand getCreateBPSetObjectCommand(
         CreateRequest request)
   {
      return new CreateBPSetObjectCommand(this.diagramEditPart);
   }


   protected AbstractCreateObjectCommand getCreateBPAnyObjectCommand(
         CreateRequest request)
   {
      return new CreateBPAnyObjectCommand(this.diagramEditPart);
   }


   /**
    * Places the feedback rectangle where the user indicated.
    * 
    * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#showSizeOnDropFeedback(org.eclipse.gef.requests.CreateRequest)
    */
   @Override
   protected void showSizeOnDropFeedback(CreateRequest request)
   {
      if (request.getNewObjectType() instanceof Class)
      {
         Class newObjectClass = (Class) request.getNewObjectType();
         if (CombinedFragment.class.isAssignableFrom(newObjectClass))
         {
            Point p = new Point(request.getLocation().getCopy());
            IFigure feedback = getSizeOnDropFeedback(request);
            feedback.translateToRelative(p);
            Dimension size = request.getSize().getCopy();
            feedback.translateToRelative(size);
            feedback.setBounds(new Rectangle(p, size)
                  .expand(getCreationFeedbackOffset(request)));
         }
      }
   }


   protected BehavioralPatternEditPart getDiagramEditPart()
   {
      return this.diagramEditPart;
   }


   private Command getCreateObjectCommand(CreateRequest request,
         AbstractCreateObjectCommand createCommand)
   {
      Rectangle constraint = getConstraintFor(request);

      CompoundCommand result = new CompoundCommand();
      // createCommand.setParent((BehavioralPattern) getHost().getModel());
      result.add(createCommand);

      // find the new index, where to insert the new object and change the other objects' indices if
      // necessary
      BehavioralPatternEditPart diagramEditPart = (BehavioralPatternEditPart) getHost();
      List childrenEditParts = diagramEditPart.getChildren();
      if (childrenEditParts.size() == 0)
      {
         // only x is needed
         createCommand.setConstraint(new Rectangle(0, 0, -1, -1));
      }
      else
      {
         // find the predecessor and successor objects
         AbstractBPObjectEditPart predecessor = null, successor = null;
         Iterator iter = childrenEditParts.iterator();
         while (iter.hasNext())
         {
            Object obj = iter.next();
            if (obj instanceof AbstractBPObjectEditPart)
            {
               AbstractBPObjectEditPart object = (AbstractBPObjectEditPart) obj;
               int x = object.getFigure().getBounds().x;
               if (x <= constraint.x)
               {
                  if (predecessor == null
                        || predecessor.getFigure().getBounds().x < x)
                  {
                     predecessor = object;
                  }
               }
               else
               // x > constraint.x
               {
                  if (successor == null
                        || successor.getFigure().getBounds().x > x)
                  {
                     successor = object;
                  }
               }
            }
         }

         // do not create a new object, if its x-coordinate is not between the other objects
         if (predecessor != null
               && predecessor.getFigure().getBounds().x
                     + predecessor.getFigure().getBounds().width > constraint.x)
         {
            return null;
         }

         // set the indices
         if (predecessor == null && successor == null)
         {
            // only x is needed
            createCommand.setConstraint(new Rectangle(0, 0, -1, -1));
         }
         else
         {
            int index = 0;
            if (predecessor != null)
            {
               // index = predecessor.getCurrentIndexConstraint() + 1;
            }
            // only x is needed
            createCommand.setConstraint(new Rectangle(index, 0, -1, -1));

            // increase the indices of the following objects
            iter = childrenEditParts.iterator();
            while (iter.hasNext())
            {
               Object obj = iter.next();
               if (obj instanceof AbstractBPObjectEditPart)
               {
                  AbstractBPObjectEditPart object = (AbstractBPObjectEditPart) obj;
                  int x = object.getFigure().getBounds().x;
               }
            }
         }
      }

      return result.unwrap();
   }


   private IFigure getFigure(EObject element)
   {
      EditPart editPart = (EditPart) this.getHost().getViewer()
            .getEditPartRegistry().get(element);
      if (editPart != null && editPart instanceof GraphicalEditPart)
      {
         return ((GraphicalEditPart) editPart).getFigure();
      }

      return null;
   }


   private Rectangle getFigureBounds(EObject element)
   {
      IFigure figure = this.getFigure(element);
      if (figure != null)
      {
         return figure.getBounds();
      }

      return null;
   }


   /**
    * @return returns the parent combined fragment if it's not the root fragment, otherwise null
    */
   private CombinedFragment getParentCombinedFragment(CombinedFragment fragment)
   {
      InteractionOperand parentOperand = fragment.getParentOperand();
      CombinedFragment parentFragment = parentOperand.getParentFragment();
      if (!(parentFragment instanceof RootFragment))
      {
         return parentFragment;
      }

      return null;
   }


   protected Command getCreateCombinedFragmentCommand(CreateRequest request)
   {
      Rectangle constraint = this.getConstraintFor(request);
      if (constraint == null || constraint.width == -1
            || constraint.height == -1)
      {
         return null;
      }

      List<LifelineFragment> lifelineFragmentsWithinConstraints = collectLifelineFragmentsWithinConstraints(constraint);

      // create the command
      if (lifelineFragmentsWithinConstraints.size() > 0)
      {
         AbstractCreateCombinedFragmentCommand createCommand = createCombinedFragmentCommand(request);

         BehavioralPatternEditPart diagramEditPart = (BehavioralPatternEditPart) getHost();
         createCommand.setDiagram((SequenceDiagram) diagramEditPart.getModel());

         // parent combined fragment of the new combined fragment
         CombinedFragment parentFragment = null;

         // collect all messages and combined fragments to be inserted
         // and determine the parent combined fragment
         Iterator<LifelineFragment> iter = lifelineFragmentsWithinConstraints
               .iterator();
         while (iter.hasNext())
         {
            LifelineFragment lifelineFragment = iter.next();
            InteractionOperand parentOperand = lifelineFragment
                  .getParentOperand();
            CombinedFragment fragment = parentOperand.getParentFragment();
            if (!(fragment instanceof RootFragment))
            {
               // message is contained in a combined fragment
               CombinedFragment nextAncestorFragment = null;

               Rectangle fragmentFigureBounds = getFigureBounds(fragment);
               if (fragmentFigureBounds == null || // something has gone wrong, do not create
                     // anything
                     (!constraint.contains(fragmentFigureBounds) && !fragmentFigureBounds
                           .contains(constraint))) // no intersection allowed
               {
                  return null;
               }

               if (constraint.contains(fragmentFigureBounds)) // fragment figure is contained in
               // constraint rectangle
               {
                  // find the ancestor combined fragment such that it is within request constraints,
                  // but has no other
                  // ancestor combined fragment that is within request constraints (this fragment is
                  // the child to insert)
                  nextAncestorFragment = getParentCombinedFragment(fragment);
                  boolean foundFragment = false;
                  while (nextAncestorFragment != null && !foundFragment)
                  {
                     fragmentFigureBounds = getFigureBounds(nextAncestorFragment);
                     if (fragmentFigureBounds == null || // something has gone wrong, do not create
                           // anything
                           (!constraint.contains(fragmentFigureBounds) && !fragmentFigureBounds
                                 .contains(constraint))) // no intersection allowed
                     {
                        return null;
                     }

                     if (!constraint.contains(fragmentFigureBounds))
                     {
                        foundFragment = true;
                     }
                     else
                     // constraint.contains(fragmentFigureBounds)
                     {
                        fragment = nextAncestorFragment;
                        nextAncestorFragment = getParentCombinedFragment(fragment);
                     }
                  }

                  if (!createCommand.hasInChildren(fragment))
                  {
                     createCommand.addChild(fragment);
                  }

                  if (nextAncestorFragment != null) // found parent combined fragment
                  {
                     parentFragment = nextAncestorFragment;
                  }
               }
               else
               // (fragmentFigureBounds.contains(constraint)) fragment figure is surrounding
               // constraint rectangle
               {
                  if (parentFragment != null && parentFragment != fragment) // something has gone
                  // wrong, do not
                  // create anything
                  {
                     return null;
                  }
                  parentFragment = fragment;

                  createCommand.addChild(lifelineFragment);
               }
            }
            else
            // (parentOperand == null) no parent combined fragment for the new combined fragment
            {
               if (parentFragment != null) // something has gone wrong, do not create anything
               {
                  return null;
               }

               createCommand.addChild(lifelineFragment);
            }
         } // end while

         // find the parentFragment's child interaction operand such that
         // it contains the request constraints (Rectangle)
         InteractionOperand parentOperand = null;

         if (parentFragment != null)
         {
            InteractionOperand firstOperand = parentFragment.getOperands().get(
                  0);
            Iterator<InteractionOperand> childOperandIter = parentFragment
                  .getOperands().iterator();
            while (parentOperand == null && childOperandIter.hasNext())
            {
               InteractionOperand operand = childOperandIter.next();
               Rectangle operandBounds = getFigureBounds(operand);
               if (operandBounds == null) // something has gone wrong, do not create anything
               {
                  return null;
               }

               if (operandBounds.contains(constraint)) // found interaction operand
               {
                  parentOperand = operand;
               }
               else if (operand == firstOperand)
               {
                  Rectangle parentFragmentBounds = getFigureBounds(parentFragment);
                  Rectangle bounds = new Rectangle(parentFragmentBounds
                        .getLocation(), new Point(operandBounds.x
                        + operandBounds.width, operandBounds.y
                        + operandBounds.height));
                  if (bounds.contains(constraint)) // found interaction operand
                  {
                     parentOperand = operand;
                  }
               }
               else if (!operandBounds.contains(constraint)
                     && !constraint.contains(operandBounds)) // intersection not allowed except
               // for the first operand
               {
                  return null;
               }
            }

            if (parentOperand == null) // something has gone wrong, do not create anything
            {
               return null;
            }

            createCommand.setParentOperand(parentOperand);
         }
         else
         {
            // no parent fragment was found, add to root fragment
            SequenceDiagram diagram = (SequenceDiagram) diagramEditPart
                  .getModel();
            RootFragment rootFragment = diagram.getRootFragment();
            parentOperand = rootFragment.getOperands().get(0);
            createCommand.setParentOperand(parentOperand);
         }

         // findPredecessingFragment(createCommand, parentOperand, constraint);

         return createCommand;
      }

      return null;
   }


   private List<LifelineFragment> collectLifelineFragmentsWithinConstraints(
         Rectangle constraint)
   {
      // collect lifelineFragments within fragment constraints
      ArrayList<LifelineFragment> lifelineFragmentsWithinConstraints = new ArrayList<LifelineFragment>();
      BehavioralPatternEditPart editPart = (BehavioralPatternEditPart) getHost();
      List<BPMessageEditPart> messages = getAllMessages(editPart);
      Iterator<BPMessageEditPart> iter = messages.iterator();
      // add messages
      while (iter.hasNext())
      {
         BPMessageEditPart msgEditPart = iter.next();
         if (msgEditPart != null)
         {
            LifelineFragment msg = (LifelineFragment) ((BPMessage) msgEditPart
                  .getModel()).getSendEvent();
            PolylineConnection messageFigure = (PolylineConnection) msgEditPart
                  .getFigure();
            Point startPoint = messageFigure.getStart();
            Point endPoint = messageFigure.getEnd();
            if (constraint.contains(startPoint)
                  && constraint.contains(endPoint))
            {
               lifelineFragmentsWithinConstraints.add(msg);
            }
         }
      }
      List<BPAssignmentEditPart> assignments = getAllAssignments(editPart);
      // add assignments
      for (BPAssignmentEditPart bpAssignmentEditPart : assignments)
      {
         if (constraint.contains(bpAssignmentEditPart.getFigure().getBounds()))
         {
            lifelineFragmentsWithinConstraints.add(bpAssignmentEditPart
                  .getModel());
         }
      }
      return lifelineFragmentsWithinConstraints;
   }


   private List<BPAssignmentEditPart> getAllAssignments(
         BehavioralPatternEditPart editPart)
   {
      List<BPAssignmentEditPart> assignments = new ArrayList<BPAssignmentEditPart>();

      List diagramChildren = editPart.getChildren();
      for (Object child : diagramChildren)
      {
         if (child instanceof BPAssignmentEditPart)
         {
            assignments.add((BPAssignmentEditPart) child);
         }
      }
      return assignments;
   }


   private List<BPMessageEditPart> getAllMessages(
         BehavioralPatternEditPart editPart)
   {
      List<BPMessageEditPart> messages = new ArrayList<BPMessageEditPart>();

      List diagramChildren = editPart.getChildren();
      for (Object child : diagramChildren)
      {
         if (child instanceof BPLifelineEditPart)
         {
            List sourceConnections = ((BPLifelineEditPart) child)
                  .getSourceConnections();
            for (Object connectionEditPart : sourceConnections)
            {
               if (connectionEditPart instanceof BPMessageEditPart)
               {
                  messages.add((BPMessageEditPart) connectionEditPart);
               }
            }
         }
      }
      return messages;
   }


   protected AbstractCreateCombinedFragmentCommand createCombinedFragmentCommand(
         CreateRequest request)
   {
      AbstractCreateCombinedFragmentCommand createCommand = null;
      if (request.getNewObjectType().equals(AlternativeFragment.class))
      {
         createCommand = new CreateAlternativeFragmentCommand(
               this.diagramEditPart);
      }
      else if (request.getNewObjectType().equals(LoopFragment.class))
      {
         createCommand = new CreateLoopFragmentCommand(this.diagramEditPart);
      }
      else if (request.getNewObjectType().equals(OptionalFragment.class))
      {
         createCommand = new CreateOptionalFragmentCommand(this.diagramEditPart);
      }
      else if (request.getNewObjectType().equals(BPEachFragment.class))
      {
         createCommand = new CreateBPEachFragmentCommand(this.diagramEditPart);
      }
      return createCommand;
   }
   //
   //
   // private void findPredecessingFragment(
   // AbstractCreateUMLCombinedFragmentCommand command,
   // UMLInteractionOperand parentOperand, Rectangle constraint)
   // {
   // UMLInteractionFragment predecessor = null;
   // UMLSequenceDiagramEditPart diagramEditPart = (UMLSequenceDiagramEditPart) getHost();
   // Map editPartRegistry = diagramEditPart.getViewer().getEditPartRegistry();
   //
   // Iterator<UMLInteractionFragment> iter = parentOperand.iteratorOfInteractionFragments();
   // while (iter.hasNext())
   // {
   // UMLInteractionFragment fragment = iter.next();
   // int y;
   // if (fragment instanceof UMLAbstractMessage)
   // {
   // UMLMessageEditPart editPart = (UMLMessageEditPart) editPartRegistry
   // .get(fragment);
   // y = ((PolylineConnection) editPart.getFigure()).getStart().y;
   // }
   // else
   // {
   // AbstractGraphicalEditPart editPart = (AbstractGraphicalEditPart) editPartRegistry
   // .get(fragment);
   // Rectangle bounds = editPart.getFigure().getBounds();
   // y = bounds.y + bounds.height;
   // }
   //
   // if (constraint.y > y)
   // {
   // predecessor = fragment;
   // }
   // }
   //
   // command.setPredecessor(predecessor);
   // }


   @Override
   protected Command createChangeConstraintCommand(EditPart child,
         Object constraint)
   {
      if (child instanceof AbstractBPObjectEditPart)
      {
         return new MoveObjectCommand((AbstractBPObjectEditPart) child, (Rectangle) constraint);
      }
      return null;
   }

}
