package org.reclipse.structure.inference.ui.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;


/**
 * This handler is used to change the name representation of the elements of the annotation view.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class ToggleNamesHandler extends AbstractHandler
{

   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      IWorkbenchPart part = HandlerUtil.getActivePart(event);

      if (part != null && part instanceof AnnotationView)
      {
         ((AnnotationView) part).toggleNames();
      }

      return null;
   }
}
