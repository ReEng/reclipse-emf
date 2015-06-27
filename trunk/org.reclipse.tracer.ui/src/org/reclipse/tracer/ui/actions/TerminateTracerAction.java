package org.reclipse.tracer.ui.actions;


import org.eclipse.jface.action.Action;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.ui.TracerUIImages;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3786 $ $Date: 2007-09-09 23:44:20 +0200 (So, 09 Sep 2007) $
 */
public class TerminateTracerAction extends Action
{

   public TerminateTracerAction()
   {
      setText("Terminate");
      setToolTipText("Terminate Tracer");
      setImageDescriptor(TracerUIImages
            .getDescriptor(TracerUIImages.IMG_ENABLED_TERMINATE));
      setDisabledImageDescriptor(TracerUIImages
            .getDescriptor(TracerUIImages.IMG_DISABLED_TERMINATE));
      setEnabled(false);
   }


   /**
    * @see org.eclipse.jface.action.IAction#run()
    */
   @Override
   public void run()
   {
      Tracer.getCurrentTracer().stopVM();
   }

}
