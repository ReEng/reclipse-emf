package org.reclipse.structure.inference.ui.matching.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.edit.parts.pattern.MatchingPSAnnotationEditPart;
import org.reclipse.structure.inference.ui.matching.views.AbstractMatchingView;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;
import org.reclipse.structure.specification.PSAnnotation;

public class OpenMatchedAnnotationHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (!selection.isEmpty() && selection instanceof IStructuredSelection
				&& ((IStructuredSelection) selection).size() == 1) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			if (selected instanceof MatchingPSAnnotationEditPart) {
				PSAnnotation model = ((MatchingPSAnnotationEditPart) selected).getRealModel();
				ASGAnnotation currentAnnotation = AbstractMatchingView.getCurrent();

				List<EObject> elements = currentAnnotation.getBoundObjects().get(model.getName());
				if (elements != null && elements.size() == 1 && elements.get(0) instanceof ASGAnnotation) {
					ASGAnnotation matchedAnnotation = (ASGAnnotation) elements.get(0);

					AnnotationView view = getAnnotationView(event);
					if (view != null) {
						view.select(currentAnnotation, matchedAnnotation);
					}
				}
			}
		}

		return null;
	}

	private AnnotationView getAnnotationView(ExecutionEvent event) {
		IViewPart part = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(AnnotationView.ID);
		if (part instanceof AnnotationView) {
			return (AnnotationView) part;
		}

		return null;
	}
}
