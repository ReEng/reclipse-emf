/**
 * 
 */
package org.reclipse.behavior.specification.ui.editparts;


import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.reclipse.behavior.specification.BPAnyObject;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BPEachFragment;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralPattern;

import de.uni_paderborn.basicSequenceDiagram.AlternativeFragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.Lifeline;
import de.uni_paderborn.basicSequenceDiagram.LoopFragment;
import de.uni_paderborn.basicSequenceDiagram.OptionalFragment;
import de.uni_paderborn.sequencediagram.viewer.editparts.InteractionOperandEditPart;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BPEditPartFactory implements EditPartFactory
{

   /**
    * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
    *      java.lang.Object)
    */
   @Override
   public EditPart createEditPart(EditPart context, Object model)
   {
      EditPart part = null;

      if (model instanceof BehavioralPattern)
      {
         part = new BehavioralPatternEditPart();
      }
      else if (model instanceof BPAnyObject)
      {
         part = new BPAnyObjectEditPart();
      }
      else if (model instanceof BPAssignment)
      {
         part = new BPAssignmentEditPart();
      }
      else if (model instanceof BPMessage)
      {
         part = new BPMessageEditPart();
      }
      else if (model instanceof BPObject)
      {
         part = new BPObjectEditPart();
      }
      else if (model instanceof BPSetObject)
      {
         part = new BPSetObjectEditPart();
      }
      else if (model instanceof Lifeline)
      {
         part = new BPLifelineEditPart();
      }
      else if (model instanceof LoopFragment)
      {
         part = new LoopFragmentEditPart();
      }
      else if (model instanceof AlternativeFragment)
      {
         part = new AlternativeFragmentEditPart();
      }
      else if (model instanceof OptionalFragment)
      {
         part = new OptionalFragmentEditPart();
      }
      else if (model instanceof BPEachFragment)
      {
         part = new BPEachFragmentEditPart();
      }
      else if (model instanceof InteractionOperand)
      {
         part = new InteractionOperandEditPart();
      }

      if(part!= null) {
         part.setModel(model);
      }
      return part;
   }

}
