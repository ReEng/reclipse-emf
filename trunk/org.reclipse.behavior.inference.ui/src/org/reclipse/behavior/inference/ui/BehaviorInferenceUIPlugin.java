package org.reclipse.behavior.inference.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.reclipse.behavior.inference.ui.BehaviorInferenceUIPlugin;
import org.reclipse.behavior.inference.ui.BehavioralInferenceImages;

/**
 * The activator class controls the plug-in life cycle
 */
public class BehaviorInferenceUIPlugin extends AbstractUIPlugin {

   public static final boolean LOG_INFO = true;

   /**
    * The plug-in ID
    */
   public static final String PLUGIN_ID = "org.reclipse.behavior.inference.ui";

   /**
    * The shared instance
    */
   private static BehaviorInferenceUIPlugin plugin;


   /**
    * The constructor
    */
   public BehaviorInferenceUIPlugin()
   {
      plugin = this;
   }


   /**
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
    */
   @Override
   public void start(final BundleContext context) throws Exception
   {
      super.start(context);
   }


   /**
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
    */
   @Override
   public void stop(final BundleContext context) throws Exception
   {
      plugin = null;
      super.stop(context);
   }


   /**
    * Returns the shared instance
    * 
    * @return the shared instance
    */
   public static BehaviorInferenceUIPlugin getDefault()
   {
      return plugin;
   }


   /**
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
    */
   @Override
   protected void initializeImageRegistry(final ImageRegistry registry)
   {
      super.initializeImageRegistry(registry);

      BehavioralInferenceImages.initializeImageRegistry(this, registry);
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
