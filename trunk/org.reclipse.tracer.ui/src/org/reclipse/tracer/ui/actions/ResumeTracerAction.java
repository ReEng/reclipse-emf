package org.reclipse.tracer.ui.actions;


import org.eclipse.jface.action.Action;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.ui.TracerUIImages;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3786 $ $Date: 2007-09-09 23:44:20 +0200 (So, 09 Sep 2007) $
 */
public class ResumeTracerAction extends Action
{

   public ResumeTracerAction()
   {
      setText("Resume");
      setToolTipText("Resume Tracer");
      setImageDescriptor(TracerUIImages
            .getDescriptor(TracerUIImages.IMG_ENABLED_RESUME));
      setDisabledImageDescriptor(TracerUIImages
            .getDescriptor(TracerUIImages.IMG_DISABLED_RESUME));
      setEnabled(false);
   }


   /**
    * @see org.eclipse.jface.action.IAction#run()
    */
   @Override
   public void run()
   {
      Tracer.getCurrentTracer().resumeVM();
   }

}
