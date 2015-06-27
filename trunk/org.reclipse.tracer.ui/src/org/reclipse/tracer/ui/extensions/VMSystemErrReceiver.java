package org.reclipse.tracer.ui.extensions;


import java.io.OutputStream;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.reclipse.tracer.extensionpoints.IVMStreamReceiver;
import org.reclipse.tracer.ui.TracerUIPlugin;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2357 $ $Date: 2006-06-01 14:24:13 +0200 (Do, 01 Jun 2006) $
 */
public class VMSystemErrReceiver implements IVMStreamReceiver
{

   static final RGB RED = new RGB(255, 0, 0);


   IOConsoleOutputStream outputStream;


   public VMSystemErrReceiver()
   {
      this.outputStream = TracerUIPlugin.getDefault()
            .getTargetVMStreamConsole().newOutputStream();

      Display.getDefault().syncExec(new Runnable()
      {
         /**
          * @see java.lang.Runnable#run()
          */
         public void run()
         {
            Color red = TracerUIPlugin.getDefault().getColor(RED);
            outputStream.setColor(red);
         }
      });
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IVMStreamReceiver#getOutputStream()
    */
   public OutputStream getOutputStream()
   {
      return this.outputStream;
   }

}
