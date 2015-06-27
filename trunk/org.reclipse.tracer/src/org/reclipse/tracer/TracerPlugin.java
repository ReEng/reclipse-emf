package org.reclipse.tracer;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3787 $ $Date: 2007-09-09 23:46:09 +0200 (So, 09 Sep 2007) $
 */
public class TracerPlugin extends Plugin
{

   public static final boolean LOG_INFO = true;


   public static final String PLUGIN_ID = "org.reclipse.tracer";


   /**
    * The singleton instance
    */
   private static TracerPlugin plugin;


   /**
    * Returns the shared instance.
    */
   public static TracerPlugin getDefault()
   {
      return plugin;
   }


   public TracerPlugin()
   {
      plugin = this;
   }


   public static void logInfo(final String message)
   {
      if (LOG_INFO)
      {
         final IStatus status = new Status(IStatus.INFO, PLUGIN_ID, message);
         getDefault().getLog().log(status);
      }
   }


   public static void logWarning(final String message)
   {
      final IStatus status = new Status(IStatus.WARNING, PLUGIN_ID, message);
      getDefault().getLog().log(status);
   }


   public static void logError(final String message)
   {
      final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message);
      getDefault().getLog().log(status);
   }


   public static void logError(final String message, final Throwable throwable)
   {
      final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message,
            throwable);
      getDefault().getLog().log(status);
   }

}
