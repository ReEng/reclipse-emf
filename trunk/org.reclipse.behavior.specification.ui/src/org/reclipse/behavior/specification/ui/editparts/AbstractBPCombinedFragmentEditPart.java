package org.reclipse.behavior.specification.ui.editparts;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.fujaba.commons.figures.UMLFragmentFigure;
import org.reclipse.behavior.specification.ui.editpolicies.DeleteCombinedFragmentEditPolicy;

import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.sequencediagram.viewer.editparts.AbstractCombinedFragmentEditPart;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public abstract class AbstractBPCombinedFragmentEditPart extends AbstractCombinedFragmentEditPart
{

   /**
    * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
    */
   @Override
   protected void createEditPolicies()
   {
      super.createEditPolicies();
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new DeleteCombinedFragmentEditPolicy((BehavioralPatternEditPart)getDiagramEditPart()));
   }



}
