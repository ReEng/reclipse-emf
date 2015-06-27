package org.reclipse.structure.inference.ui.matching.edit.parts.object;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.inference.ui.matching.Constants;
import org.reclipse.structure.inference.ui.matching.util.BoundingUtil;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.ui.edit.parts.PSPatternSpecificationEditPart;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * This is a specialized edit part for the object matching view of a structural inference.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author oleg82
 */
public class MatchingPSPatternSpecificationEditPart extends PSPatternSpecificationEditPart
{
	private DragTracker dragTracker;


	@Override
	public DragTracker getDragTracker(Request request)
	{
		if (dragTracker == null)
		{
			dragTracker = new MarqueeDragTracker();
		}

		return dragTracker;
	}


	@Override
	protected List<Node> getModelChildren()
	{
		List<Node> fitered = new ArrayList<Node>();

		for (Node node : getModel().getNodes())
		{
			EObject model = node.getModel();

			// filter specification constraints
			if (model instanceof PSSpecificationConstraint)
			{
				continue;
			}

			if (model instanceof PSNode)
			{
				// filter negative nodes
				if (ModifierType.NEGATIVE.equals(((PSNode) model).getModifier()))
				{
					continue;
				}

				// filter non-bound additional nodes
				if (ModifierType.ADDITIONAL.equals(((PSNode) model).getModifier()) && !BoundingUtil.isBound((PSNode) model))
				{
					continue;
				}

				// filter normal annotations
				if (model instanceof PSAnnotation && !ModelHelper.isCreate((PSAnnotation) model))
				{
					continue;
				}
			}

			fitered.add(node);
		}

		return fitered;
	}


	@Override
	protected void refreshVisuals()
	{
		super.refreshVisuals();

		labelFigure.setText(0, Constants.OBJECT_DIAGRAM_LABEL_PREFIX);
		labelFigure.setText(1, String.format("Matched '%1s' Pattern", getRealModel().getName()));
	}
}
