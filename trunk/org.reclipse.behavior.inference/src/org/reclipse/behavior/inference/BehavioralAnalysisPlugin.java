package org.reclipse.behavior.inference;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3805 $ $Date: 2007-09-13 19:57:44 +0200 (Do, 13 Sep 2007) $
 */
public class BehavioralAnalysisPlugin extends Plugin
{

   public static final boolean LOG_INFO = true;

   public static final String PLUGIN_ID = "org.reclipse.patterns.behavior.inference";


   /**
    * The singleton instance
    */
   private static BehavioralAnalysisPlugin plugin;


   /**
    * Returns the shared instance.
    */
   public static BehavioralAnalysisPlugin getDefault()
   {
      return plugin;
   }


   public BehavioralAnalysisPlugin()
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
