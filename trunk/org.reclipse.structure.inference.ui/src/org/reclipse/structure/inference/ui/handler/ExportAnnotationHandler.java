package org.reclipse.structure.inference.ui.handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.annotations.ASGAnnotation;

public class ExportAnnotationHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);

        if (selection instanceof TreeSelection) {
            final TreeSelection treeSelection = (TreeSelection) selection;

            for (final Iterator<?> iter = treeSelection.iterator(); iter.hasNext();) {
                final Object node = iter.next();

                if (node instanceof ASGAnnotation) {
                    final ASGAnnotation annotation = (ASGAnnotation) node;
                    System.out.println("LEHRIG: ExportAnnotationHandler called for annotation \""
                            + annotation.getPattern().getName() + "\"");
                }
            }
        }

        return null;
    }
}
