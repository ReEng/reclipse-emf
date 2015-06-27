package org.reclipse.structure.inference.ui.matching;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.edit.parts.pattern.MatchingPSAnnotationEditPart;
import org.reclipse.structure.inference.ui.matching.views.AbstractMatchingView;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;

public class PropertyTester extends org.eclipse.core.expressions.PropertyTester {
	private static final String HAS_MATCHING_ANNOTATION = "hasMatchingAnnotation"; //$NON-NLS-1$

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expected) {
		if (receiver != null) {
			if (HAS_MATCHING_ANNOTATION.equals(property)) {
				return hasMatchingAnnotation(receiver);
			}
		}

		return false;
	}

	private boolean hasMatchingAnnotation(Object receiver) {
		if (receiver instanceof MatchingPSAnnotationEditPart) {
			PSAnnotation model = ((MatchingPSAnnotationEditPart) receiver).getRealModel();

			if (ModifierType.NONE.equals(model.getModifier()) || ModifierType.ADDITIONAL.equals(model.getModifier())) {
				ASGAnnotation annotation = AbstractMatchingView.getCurrent();

				Collection<EObject> elements = annotation.getBoundObjects().get(model.getName());
				return elements != null && !elements.isEmpty();
			}
		}

		return false;
	}
}
