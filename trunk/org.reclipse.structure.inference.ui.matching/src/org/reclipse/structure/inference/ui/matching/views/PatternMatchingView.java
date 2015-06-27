package org.reclipse.structure.inference.ui.matching.views;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.AbstractGroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.edit.parts.MatchedPatternEditPartFactory;
import org.reclipse.structure.specification.PSPatternSpecification;

public class PatternMatchingView extends AbstractMatchingView {

	public static final String ID = "org.reclipse.ui.views.structure.inference.matching.pattern"; //$NON-NLS-1$

	public static final String MATCHED_ANNOTATION_KEY = "icons/commands/openRelated.png"; //$NON-NLS-1$

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
		// lazy initialize annotation diagram node cache
		if (patterns == null) {
			patterns = new HashMap<PSPatternSpecification, HierarchicalNode>();
		}

		return patterns;
	}

	@Override
	protected MenuManager createContextMenuManager(ScrollingGraphicalViewer viewer) {
		ContextMenuProvider contextMenu = new ContextMenuProvider(viewer) {
			@Override
			public void buildContextMenu(IMenuManager menu) {
				// rest comes from command framework
			}

			@Override
			protected boolean allowItem(IContributionItem item) {
				// allow groups
				if (item instanceof AbstractGroupMarker) {
					return true;
				}

				// allow all reclipse items
				if (item.getId() != null) {
					return item.getId().startsWith("org.reclipse");
				}

				return false;
			}
		};

		return contextMenu;
	}

	@Override
	protected EditPartFactory getEditPartFactory() {
		return MatchedPatternEditPartFactory.INSTANCE;
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
