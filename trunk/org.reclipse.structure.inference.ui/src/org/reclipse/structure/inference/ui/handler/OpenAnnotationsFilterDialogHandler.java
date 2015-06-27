package org.reclipse.structure.inference.ui.handler;


import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;
import org.reclipse.structure.inference.ui.views.annotations.filtering.AnnotationFilterDialog;
import org.reclipse.structure.specification.PSPatternSpecification;


public class OpenAnnotationsFilterDialogHandler extends AbstractHandler
{

   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      // get some
      IWorkbenchPart part = HandlerUtil.getActivePart(event);

      // only create when on correct part
      if (part instanceof AnnotationView)
      {
         // get view
         AnnotationView view = (AnnotationView) part;

         // create dialog
         AnnotationFilterDialog dialog = new AnnotationFilterDialog(view);
         Set<String> availables = new HashSet<String>();
         for (PSPatternSpecification pattern : view.getAnnotations().keySet())
         {
            availables.add(pattern.getName());
         }
         dialog.loadFilters(availables);

         // open it
         if (dialog.open() == Window.OK)
         {
            view.setFiltering(dialog.getFiltering());
         }
      }

      return null;
   }
}
