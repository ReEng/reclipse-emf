package org.reclipse.structure.inference.ui.matching.views;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.EditPartFactory;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.edit.parts.MatchedObjectsEditPartFactory;
import org.reclipse.structure.specification.PSPatternSpecification;

public class ObjectMatchingView extends AbstractMatchingView {

	public final static String ID = "org.reclipse.ui.views.structure.inference.matching.ast"; //$NON-NLS-1$

	/**
	 * This map holds the diagram root nodes for the annotations. It gets filled
	 * when an annotation of a pattern has been opened. It caches the diagram
	 * nodes for the patterns <strong>statically</strong> to get valid data
	 * faster.
	 */
	private static Map<ASGAnnotation, HierarchicalNode> annotations;

	/**
	 * This map holds the diagram root nodes for the patterns. It gets filled
	 * when an annotation of a pattern has been opened. It caches the diagram
	 * nodes for the patterns <strong>statically</strong> to get valid data
	 * faster.
	 */
	private static Map<PSPatternSpecification, HierarchicalNode> patterns;

	protected static final Map<ASGAnnotation, HierarchicalNode> getAnnotationCache() {
		// lazy initialize annotation diagram node cache
		if (annotations == null) {
			annotations = new HashMap<ASGAnnotation, HierarchicalNode>();
		}

		return annotations;
	}

	protected static final Map<PSPatternSpecification, HierarchicalNode> getPatternCache() {
		// lazy initialize pattern diagram node cache
		if (patterns == null) {
			patterns = new HashMap<PSPatternSpecification, HierarchicalNode>();
		}

		return patterns;
	}

	@Override
	protected EditPartFactory getEditPartFactory() {
		return MatchedObjectsEditPartFactory.INSTANCE;
	}

	@Override
	protected void clear() {
		getPatternCache().clear();
		getAnnotationCache().clear();
	}

	@Override
	protected String getId() {
		return ID;
	}
}
