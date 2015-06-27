package org.reclipse.behavior.specification.ui.commands;


import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.reclipse.behavior.specification.ui.editparts.AbstractBPObjectEditPart;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.sequencediagram.viewer.ObjectLocationUtil;


public class MoveObjectCommand extends AbstractBPCommand
{

   private AbstractBPObjectEditPart object;

   private Rectangle constraint;


   public MoveObjectCommand(AbstractBPObjectEditPart object, Rectangle constraint)
   {
      super("Move Object", (BehavioralPatternEditPart) object.getParent());
      this.object = object;
      this.constraint = constraint;
   }


   @Override
   public void redo()
   {
      // We only need to manipulate the hashmap where the indices of the objects are stored. The
      // layout will be done by the BehavioralPatternLayout layout manager.

      int index = this.findIndexForConstraint();
      ObjectLocationUtil.removeIndexOfObject(this.object.getModel());
      ObjectLocationUtil.insertIndexForObject(index, this.object);
      ((ScalableFreeformRootEditPart) this.getBehavioralPatternEditPart().getRoot()).getFigure().repaint();
      super.redo();
   }


   private int findIndexForConstraint()
   {
      int index = 0;

      Map<AbstractSequenceDiagramObject, Integer> indexMap = ObjectLocationUtil.getIndexMap(this
            .getBehavioralPatternEditPart().getModel());
      Set<AbstractSequenceDiagramObject> keys = indexMap.keySet();

      for (AbstractSequenceDiagramObject object : keys)
      {
         AbstractGraphicalEditPart editPart = getEditPartForModel(object);
         Rectangle figureBounds = editPart.getFigure().getBounds();
         if (figureBounds.x < this.constraint.x)
         {
            // object is left of constraint
            if (index <= indexMap.get(object))
            {
               index = indexMap.get(object);
            }
         }
      }

      return index;
   }


   protected AbstractGraphicalEditPart getEditPartForModel(Object model)
   {
      EditPartViewer editPartViewer = this.object.getViewer();
      Map editPartRegistry = editPartViewer.getEditPartRegistry();

      return (AbstractGraphicalEditPart) editPartRegistry.get(model);
   }


   @Override
   public void undo()
   {
      // TODO Auto-generated method stub
      super.undo();
   }


}
