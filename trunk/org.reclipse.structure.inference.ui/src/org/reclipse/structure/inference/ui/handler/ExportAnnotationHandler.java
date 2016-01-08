package org.reclipse.structure.inference.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;

public class ExportAnnotationHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IWorkbenchPart part = HandlerUtil.getActivePart(event);

        if (part instanceof AnnotationView) {
            final AnnotationView view = (AnnotationView) part;

            System.out.println("LEHRIG: ExportAnnotationHandler called for view \"" + view.getTitle() + "\"");
        }

        return null;
    }
}
