package org.reclipse.structure.inference.ui.matching.edit.parts.object;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.notation.Edge;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.notation.Node;
import org.fujaba.commons.notation.NotationFactory;
import org.reclipse.structure.inference.ui.matching.Constants;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.util.BoundingHelper;
import org.reclipse.structure.inference.ui.matching.util.BoundingUtil;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.ui.edit.parts.PSObjectEditPart;
import org.reclipse.structure.specification.util.ModelHelper;


public class MatchingPSObjectEditPart extends PSObjectEditPart
{

	private List<HierarchicalNode> setNodes;

	private List<Edge> setLinks;


	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new MatchingSelectionEditPolicy(getFigure()));
	}


	@Override
	protected void refreshVisuals()
	{
		super.refreshVisuals();

		// show name when non-set
		if (!ModifierType.SET.equals(getRealModel().getModifier()))
		{
			getFigure().setMatchingName("name = " + BoundingHelper.getName(BoundingUtil.getFirstBound(getRealModel())));
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


	public static boolean isSetElement(Node node)
	{
		// annotated node
		EAnnotation anno = node.getEAnnotation(Constants.KEY_SOURCE);
		if (anno != null)
		{
			// set element
			String show = anno.getDetails().get(Constants.KEY_BOUND);
			if (show != null)
			{
				return true;
			}
		}

		return false;
	}


	public void closeSet()
	{
		// collect the nodes and links
		if (setNodes == null && setLinks == null)
		{
			setNodes = new ArrayList<HierarchicalNode>();
			setLinks = new ArrayList<Edge>();

			for (Edge out : getModel().getOutgoing())
			{
				if (isSetElement(out.getTarget()))
				{
					setNodes.add((HierarchicalNode) out.getTarget());
					setLinks.add(out);
				}
			}
		}

		// remove the nodes and links from cache
		int count = BoundingUtil.getBound(getRealModel()).size();
		for (int i = 0; i < count; i++)
		{
			// remove edge
			setLinks.get(i).setParent(null);
			setLinks.get(i).setSource(null);
			setLinks.get(i).setTarget(null);

			// add node
			setNodes.get(i).setParent(null);
		}
		// save state in EAnnotation
		getModel().getEAnnotation(Constants.KEY_SOURCE).getDetails().put(Constants.KEY_SHOW, Boolean.FALSE.toString());
	}


	public void openSet()
	{
		int count = BoundingUtil.getBound(getRealModel()).size();

		// create lists when not done
		if (setNodes == null && setLinks == null)
		{
			setNodes = new ArrayList<HierarchicalNode>();
			setLinks = new ArrayList<Edge>();

			// get bounded elements
			PSNode model = getRealModel();

			// add nodes for all bounded
			for (int i = 0; i < count; i++)
			{
				// create new object
				PSObject bounded = SpecificationFactory.eINSTANCE.createPSObject();
				bounded.setInstanceOf(((PSObject) model).getInstanceOf());
				bounded.setModifier(ModifierType.NONE);
				bounded.setName(model.getName() + "[" + i + "]");

				// create the link
				PSLink link = SpecificationFactory.eINSTANCE.createPSLink();
				link.setSource(model);
				link.setTarget(bounded);

				// create view node
				int width = getModel().getWidth();
				HierarchicalNode boundedView = NotationFactory.eINSTANCE.createHierarchicalNode();
				boundedView.setModel(bounded);
				boundedView.setX(getModel().getX() + 20 + (width * i));
				boundedView.setY(getModel().getY() + 100);
				boundedView.setWidth(width);
				boundedView.setHeight(getModel().getHeight());

				// add EAnnotation
				EAnnotation anno = EcoreFactory.eINSTANCE.createEAnnotation();
				anno.setSource(Constants.KEY_SOURCE);
				anno.getDetails().put(Constants.KEY_BOUND, String.valueOf(i));

				boundedView.getEAnnotations().add(anno);

				// create view link
				Edge edge = NotationFactory.eINSTANCE.createEdge();
				edge.setModel(link);
				edge.setSource(getModel());
				edge.setTarget(boundedView);

				// add them into cache
				setNodes.add(boundedView);
				setLinks.add(edge);
			}
		}

		// add nodes from cache
		for (int i = 0; i < count; i++)
		{
			// add node
			setNodes.get(i).setParent(getModel().getParent());

			// add edge
			setLinks.get(i).setParent(getPatternSpecificationNode(getModel()));
			setLinks.get(i).setSource(getModel());
			setLinks.get(i).setTarget(setNodes.get(i));
		}

		// save state in EAnnotation
		getModel().getEAnnotation(Constants.KEY_SOURCE).getDetails().put(Constants.KEY_SHOW, Boolean.TRUE.toString());
	}


	private HierarchicalNode getPatternSpecificationNode(HierarchicalNode node)
	{
		HierarchicalNode parent = node.getParent();

		while (parent.getParent() != null)
		{
			parent = parent.getParent();
		}

		return parent;
	}
}
