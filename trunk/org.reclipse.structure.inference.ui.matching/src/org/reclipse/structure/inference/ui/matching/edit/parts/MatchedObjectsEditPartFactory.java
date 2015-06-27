package org.reclipse.structure.inference.ui.matching.edit.parts;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.fujaba.commons.notation.DiagramElement;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSAnnotationEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSAttributeConstraintEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSFuzzyConstraintEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSLinkEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSMetricConstraintEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSObjectEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSObjectSetElementEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSPatternSpecificationEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.pattern.MatchingPSCombinedFragmentEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.pattern.MatchingPSSpecificationConstraintEditPart;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.ui.edit.parts.PSPathEditPart;


/**
 * This is the edit part factory for the pattern specification matching view.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public enum MatchedObjectsEditPartFactory implements EditPartFactory
{

	/**
	 * The singleton instance (Java 1.6 style).
	 */
	INSTANCE;

	@Override
	public EditPart createEditPart(EditPart context, Object element)
	{
		DiagramElement view = null;
		EObject model = null;
		EditPart part = null;

		if (element instanceof DiagramElement)
		{
			view = (DiagramElement) element;
			model = view.getModel();
		}

		if (model instanceof PSPatternSpecification)
		{
			part = new MatchingPSPatternSpecificationEditPart();
		}
		else if (model instanceof PSAnnotation)
		{
			part = new MatchingPSAnnotationEditPart();
		}
		else if (model instanceof PSObject)
		{
			if (MatchingPSObjectEditPart.isSetElement((Node) view))
			{
				PSObject realNode = null;
				for (PSNode node : ((PSPatternSpecification) ((Node) view).getParent().getModel()).getNodes())
				{
					if (node instanceof PSObject && node.getName().equals(((PSObject) model).getName().split("\\[")[0]))
					{
						realNode = (PSObject) node;
					}
				}
				part = new MatchingPSObjectSetElementEditPart(realNode);
			}
			else
			{
				part = new MatchingPSObjectEditPart();
			}
		}
		else if (model instanceof PSAttributeConstraint)
		{
			part = new MatchingPSAttributeConstraintEditPart();
		}
		else if (model instanceof PSMetricConstraint)
		{
			part = new MatchingPSMetricConstraintEditPart();
		}
		else if (model instanceof PSFuzzyConstraint)
		{
			part = new MatchingPSFuzzyConstraintEditPart();
		}
		else if (model instanceof PSSpecificationConstraint)
		{
			part = new MatchingPSSpecificationConstraintEditPart();
		}
		else if (model instanceof PSCombinedFragment)
		{
			part = new MatchingPSCombinedFragmentEditPart();
		}
		else if (model instanceof PSLink)
		{
			part = new MatchingPSLinkEditPart();
		}
		else if (model instanceof PSPath)
		{
			part = new PSPathEditPart();
		}

		if (view != null && part != null)
		{
			part.setModel(view);
		}

		return part;
	}
}
