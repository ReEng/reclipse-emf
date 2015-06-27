package org.reclipse.structure.inference.ui.matching.edit.parts.object;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.notation.Edge;
import org.reclipse.structure.inference.ui.matching.Constants;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.util.BoundingHelper;
import org.reclipse.structure.inference.ui.matching.util.BoundingUtil;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.ui.edit.parts.PSObjectEditPart;
import org.reclipse.structure.specification.util.ModelHelper;


public class MatchingPSObjectSetElementEditPart extends PSObjectEditPart
{
	private EObject bound;

	private PSObject realNode;


	public MatchingPSObjectSetElementEditPart(PSObject realNode)
	{
		super();

		this.realNode = realNode;
	}


	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new MatchingSelectionEditPolicy(getFigure()));
	}


	@Override
	protected void refreshVisuals()
	{
		super.refreshVisuals();

		if (bound == null && getRealModel().getModifier() != ModifierType.SET)
		{
			// default index
			int index = 0;

			// get EAnnotated index when set
			EAnnotation anno = getModel().getEAnnotation(Constants.KEY_SOURCE);
			if (anno != null)
			{
				index = Integer.valueOf(anno.getDetails().get(Constants.KEY_BOUND));
			}

			bound = BoundingUtil.getBound(realNode).get(index);

			getFigure().setMatchingName("name = " + BoundingHelper.getName(bound));
		}
	}


	@Override
	protected List<Edge> getModelSourceConnections()
	{
		List<Edge> filtered = new ArrayList<Edge>();

		for (Edge edge : getModel().getOutgoing())
		{
			if (edge.getTarget() != null)
			{
				EObject target = edge.getTarget().getModel();

				if (target instanceof PSNode)
				{
					// filter negative nodes
					if (ModifierType.NEGATIVE.equals(((PSNode) target).getModifier()))
					{
						continue;
					}

					// filter non-bound additional nodes
					if (ModifierType.ADDITIONAL.equals(((PSNode) target).getModifier())
							&& !BoundingUtil.isBound((PSNode) target))
					{
						continue;
					}

					// filter annotations
					if (target instanceof PSAnnotation && !ModelHelper.isCreate((PSAnnotation) target))
					{
						continue;
					}
				}

				filtered.add(edge);
			}
		}

		return filtered;
	}


	@Override
	protected List<Edge> getModelTargetConnections()
	{
		List<Edge> filtered = new ArrayList<Edge>();

		for (Edge edge : getModel().getIncoming())
		{
			if (edge.getSource() != null)
			{
				EObject source = edge.getSource().getModel();

				if (source instanceof PSNode)
				{
					// filter negative nodes
					if (ModifierType.NEGATIVE.equals(((PSNode) source).getModifier()))
					{
						continue;
					}

					// filter non-bound additional nodes
					if (ModifierType.ADDITIONAL.equals(((PSNode) source).getModifier())
							&& !BoundingUtil.isBound((PSNode) source))
					{
						continue;
					}

					// filter annotations
					if (source instanceof PSAnnotation && !ModelHelper.isCreate((PSAnnotation) source))
					{
						continue;
					}
				}

				filtered.add(edge);
			}
		}

		return filtered;
	}
}
