package org.reclipse.structure.inference.ui.matching.edit.parts.pattern;

import java.text.DecimalFormat;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionUtil;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionVisualizationUtil;
import org.reclipse.structure.inference.ui.matching.views.PatternMatchingView;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.ui.edit.parts.PSAnnotationEditPart;
import org.reclipse.structure.specification.ui.figures.PSAnnotationFigure;
import org.reclipse.structure.specification.util.ModelHelper;

public class MatchingPSAnnotationEditPart extends PSAnnotationEditPart {

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();

		PSAnnotation model = getRealModel();
		PSAnnotationFigure figure = (PSAnnotationFigure) getFigure();
		ASGAnnotation annotation = PatternMatchingView.getCurrent();

		if (model.getModifier() == ModifierType.ADDITIONAL || !model.getParents().isEmpty()) {

			String satisfaction = "";
			if (annotation != null) {
				satisfaction = SatisfactionUtil.getSatisfaction(model, annotation);
			}

			String weight = "?";

			if (ModelHelper.isCreate(model)) {
				weight = SatisfactionUtil.getWeight(model.getType());
			} else {
				weight = SatisfactionUtil.getWeight(model);
				SatisfactionVisualizationUtil.setColor(model, figure, getParent(), satisfaction);
			}

			String tag = "{" + satisfaction + ", total=" + weight + "}";

			figure.setWeightText(SatisfactionUtil.extend(getWeightText(), tag));
		} else if (ModifierType.NONE.equals(model.getModifier()) && !ModelHelper.isCreate(model)) {
			String name = getRealModel().getName();

			List<EObject> bound = annotation.getBoundObjects().get(name);
			if (bound != null && bound.size() == 1 && bound.get(0) instanceof ASGAnnotation) {
				ASGAnnotation boundAnno = (ASGAnnotation) bound.get(0);
				figure.setWeightText(new DecimalFormat("0.00").format(boundAnno.getAnnotationRanking()) + " %");
			}
		}
	}

	private String getWeightText() {
		// TODO Auto-generated method stub
		return "weight = " + getRealModel().getWeight();
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new MatchingSelectionEditPolicy(getFigure()));
	}

	public ASGAnnotation getAnnotation() {
		String name = getRealModel().getName();
		ASGAnnotation annotation = PatternMatchingView.getCurrent();
		List<EObject> bound = annotation.getBoundObjects().get(name);
		if (bound != null && bound.size() == 1 && bound.get(0) instanceof ASGAnnotation) {
			return (ASGAnnotation) bound.get(0);
		}
		return null;
	}

	public boolean hasBoundAnnotation() {
		return getAnnotation() != null;
	}
}
