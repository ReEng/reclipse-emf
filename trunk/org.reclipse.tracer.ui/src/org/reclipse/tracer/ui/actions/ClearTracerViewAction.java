package org.reclipse.tracer.ui.actions;


import org.eclipse.jface.action.Action;
import org.reclipse.tracer.ui.TracerUIImages;
import org.reclipse.tracer.ui.views.TracerView;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3786 $ $Date: 2007-09-09 23:44:20 +0200 (So, 09 Sep 2007) $
 */
public class ClearTracerViewAction extends Action
{

   private final TracerView tracerView;


   public ClearTracerViewAction(final TracerView tracerView)
   {
      this.tracerView = tracerView;

      setText("Clear");
      setToolTipText("Clear Tracer View");
      setImageDescriptor(TracerUIImages
            .getDescriptor(TracerUIImages.IMG_CLEAR_TRACER_VIEW));
   }


   /**
    * @see org.eclipse.jface.action.IAction#run()
    */
   @Override
   public void run()
   {
      this.tracerView.clear();
   }

}
