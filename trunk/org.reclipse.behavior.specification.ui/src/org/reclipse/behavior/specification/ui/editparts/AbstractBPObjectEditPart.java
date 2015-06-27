package org.reclipse.behavior.specification.ui.editparts;


import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.reclipse.behavior.specification.ui.editpolicies.BPMessageNodeEditPolicy;
import org.reclipse.behavior.specification.ui.editpolicies.DeleteObjectEditPolicy;

import de.uni_paderborn.sequencediagram.viewer.editparts.AbstractSequenceDiagramObjectEditPart;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class AbstractBPObjectEditPart extends AbstractSequenceDiagramObjectEditPart implements NodeEditPart
{

   /**
    * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
    */
   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new BPMessageNodeEditPolicy(
            (BehavioralPatternEditPart) getDiagramEditPart()));

      installEditPolicy(EditPolicy.COMPONENT_ROLE, new DeleteObjectEditPolicy());

      super.createEditPolicies();
   }


}
