package org.reclipse.behavior.specification.ui.layouts;


import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.ui.editparts.BPAssignmentEditPart;
import org.reclipse.behavior.specification.ui.editparts.BPLifelineEditPart;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;
import de.uni_paderborn.sequencediagram.viewer.layouts.SequenceDiagramLayout;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BehavioralPatternLayout extends SequenceDiagramLayout
{


   private static final int ASSIGNMENT_HEIGHT = 22;


   public BehavioralPatternLayout(BehavioralPatternEditPart bpEditPart)
   {
      super(bpEditPart);
   }


   @Override
   protected Rectangle handleFragment(Rectangle bounds, boolean firstFragment, Fragment fragment)
   {
      Rectangle childBounds;
      if (fragment instanceof SendEvent)
      {
         super.handleFragment(bounds, firstFragment, fragment);
      }
      else if (fragment instanceof CombinedFragment)
      {
         super.handleFragment(bounds, firstFragment, fragment);
      }
      else if (fragment instanceof BPAssignment)
      {
         BPAssignment assignment = (BPAssignment) fragment;
         childBounds = layoutAssignment(assignment, bounds);

         bounds = calculateBounds(bounds, childBounds, OPERAND_INSET_LEFT, OPERAND_INSET_LEFT, OPERAND_INSET_RIGHT, 0);
      }
      return bounds;
   }


   private Rectangle layoutAssignment(BPAssignment assignment, Rectangle lastBounds) throws IllegalStateException
   {
      int y = lastBounds.y + lastBounds.height + MESSAGE_DISTANCE;

      Rectangle bounds = null;
      BPAssignmentEditPart assignmentEditPart = (BPAssignmentEditPart) getEditPartForModel(assignment);
      if (assignmentEditPart != null)
      {
         Rectangle assignmentBounds = assignmentEditPart.getFigure().getBounds();
         bounds = new Rectangle(getXCoordinateForAssignment(assignment, assignmentEditPart.getFigure()), y,
               assignmentBounds.width, ASSIGNMENT_HEIGHT);
         assignmentEditPart.getFigure().setBounds(bounds);
      }

      Rectangle newBounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height + 10);

      return newBounds;
   }


   private int getXCoordinateForAssignment(BPAssignment assignment, IFigure figure)
   {
      BPLifelineEditPart lifelineEditPart = (BPLifelineEditPart) getEditPartForModel(assignment.getLifeline());
      Rectangle lifelineBounds = lifelineEditPart.getFigure().getBounds();
      int x = lifelineBounds.x;
      x = x - (figure.getBounds().width / 2);
      return x;
   }


}
