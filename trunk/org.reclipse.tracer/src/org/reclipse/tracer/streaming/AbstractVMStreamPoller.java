package org.reclipse.tracer.streaming;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.reclipse.tracer.extensionpoints.IVMStreamReceiver;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4138 $ $Date: 2009-09-04 10:28:46 +0200 (Fr, 04 Sep 2009) $
 */
public abstract class AbstractVMStreamPoller extends Thread
{
   private final static int BUFFER_SIZE = 16384;


   private BufferedInputStream inputStream;

   private boolean stopped = false;


   /**
    * Creates a new instance of StreamOutputPanel
    * 
    * @param inStream No description provided
    */
   public AbstractVMStreamPoller(InputStream inStream)
   {
      this.inputStream = new BufferedInputStream(inStream);

      setPriority(Thread.NORM_PRIORITY);
      setDaemon(true);
   }


   public synchronized void stopThread()
   {
      this.stopped = false;
   }


   /**
    * @see java.lang.Runnable#run()
    */
   @Override
   public void run()
   {
      byte[] readBuffer = new byte[BUFFER_SIZE];
      try
      {
         int count = 0;
         while (!this.stopped && count > -1)
         {
            count = this.inputStream.read(readBuffer, 0, BUFFER_SIZE);
            if (count > 0)
            {
               Iterator<IVMStreamReceiver> iter = iteratorOfReceivers();
               while (iter.hasNext())
               {
                  IVMStreamReceiver streamReceiver = iter.next();
                  streamReceiver.getOutputStream().write(readBuffer, 0, count);
               }
            }
         }
      }
      catch (IOException exc)
      {
      }
   }


   protected abstract Iterator<IVMStreamReceiver> iteratorOfReceivers();

}
