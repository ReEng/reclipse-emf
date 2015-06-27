package org.reclipse.structure.inference.ui.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;


public class ChangeAnnotationsFilterHandler extends AbstractHandler
{
   private static final String VAL_ANNOTATED = "annotated"; //$NON-NLS-1$

   private static final String VAL_ANTECEDENT = "antecedent"; //$NON-NLS-1$

   private static final String VAL_CONSEQUENT = "consequent"; //$NON-NLS-1$

   private static final String PARAMETER = "org.reclipse.ui.commands.parameters.annotations.showing"; //$NON-NLS-1$


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

         // check parameter
         String parameter = event.getParameter(PARAMETER);

         if (VAL_ANNOTATED.equals(parameter))
         {
            view.setFiltering(0);
         }
         else if (VAL_ANTECEDENT.equals(parameter))
         {
            view.setFiltering(1);
         }
         else if (VAL_CONSEQUENT.equals(parameter))
         {
            view.setFiltering(2);
         }
      }

      return null;
   }
}
