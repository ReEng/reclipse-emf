package org.reclipse.tracer.extensionpoints;


import org.reclipse.tracer.Tracer;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3314 $ $Date: 2007-01-23 21:47:50 +0100 (Di, 23 Jan 2007) $
 */
public interface ITracerChangedListener
{
   public void currentTracerChanged(Tracer oldTracer, Tracer newTracer);

}
