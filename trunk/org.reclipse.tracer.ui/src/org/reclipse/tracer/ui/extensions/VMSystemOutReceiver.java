package org.reclipse.tracer.ui.extensions;


import java.io.OutputStream;

import org.eclipse.ui.console.IOConsoleOutputStream;
import org.reclipse.tracer.extensionpoints.IVMStreamReceiver;
import org.reclipse.tracer.ui.TracerUIPlugin;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2357 $ $Date: 2006-06-01 14:24:13 +0200 (Do, 01 Jun 2006) $
 */
public class VMSystemOutReceiver implements IVMStreamReceiver
{

   private IOConsoleOutputStream outputStream;


   public VMSystemOutReceiver()
   {
      this.outputStream = TracerUIPlugin.getDefault()
            .getTargetVMStreamConsole().newOutputStream();
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IVMStreamReceiver#getOutputStream()
    */
   public OutputStream getOutputStream()
   {
      return this.outputStream;
   }

}
