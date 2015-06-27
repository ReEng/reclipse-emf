package org.reclipse.tracer.streaming;


import java.io.InputStream;
import java.util.Iterator;

import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.extensionpoints.IVMStreamReceiver;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4138 $ $Date: 2009-09-04 10:28:46 +0200 (Fr, 04 Sep 2009) $
 */
public class VMSystemErrPoller extends AbstractVMStreamPoller
{

   public VMSystemErrPoller(InputStream inStream)
   {
      super(inStream);
      setName("Target VM's System.err poller");
   }


   /**
    * @see org.reclipse.tracer.streaming.AbstractVMStreamPoller#iteratorOfReceivers()
    */
   @Override
   protected Iterator<IVMStreamReceiver> iteratorOfReceivers()
   {
      return Tracer.iteratorOfSystemErrReceivers();
   }

}
