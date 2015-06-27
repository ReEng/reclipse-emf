package org.reclipse.behavior.specification.ui.editparts;


import org.eclipse.gef.EditPolicy;
import org.reclipse.behavior.specification.ui.editpolicies.BPMessageNodeEditPolicy;
import org.reclipse.behavior.specification.ui.editpolicies.CreateBPAssignmentEditPolicy;

import de.uni_paderborn.sequencediagram.viewer.editparts.LifelineEditPart;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BPLifelineEditPart extends LifelineEditPart
{

   /**
    * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
    */
   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new BPMessageNodeEditPolicy(
            (BehavioralPatternEditPart) getDiagramEditPart()));

      installEditPolicy(EditPolicy.LAYOUT_ROLE, new CreateBPAssignmentEditPolicy(this));

   }


}
