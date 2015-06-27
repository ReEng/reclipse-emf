package org.reclipse.tracer.ui.extensions;

import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.extensionpoints.ITracerChangedListener;

/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3315 $ $Date: 2007-01-23 21:49:20 +0100 (Di, 23 Jan 2007) $
 */
public class UITracerChangedListener implements ITracerChangedListener
{

   /**
    * @see org.reclipse.tracer.extensionpoints.ITracerChangedListener#currentTracerChanged(org.reclipse.tracer.Tracer, org.reclipse.tracer.Tracer)
    */
   public void currentTracerChanged(Tracer oldTracer, Tracer newTracer)
   {
//      TracerUIPlugin tracerUIPlugin = TracerUIPlugin.getDefault();
//      tracerUIPlugin.getEventsModel().removeAllFromEvents();
//      tracerUIPlugin.getExecutionMonitorModel().removeAllFromClasses();
   }

}
