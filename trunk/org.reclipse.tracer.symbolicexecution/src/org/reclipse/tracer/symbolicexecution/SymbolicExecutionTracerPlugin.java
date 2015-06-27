package org.reclipse.tracer.symbolicexecution;


import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class SymbolicExecutionTracerPlugin extends AbstractUIPlugin
{

   // The shared instance
   private static SymbolicExecutionTracerPlugin plugin;


   /**
    * The constructor
    */
   public SymbolicExecutionTracerPlugin()
   {
   }


   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
    */
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      plugin = this;
   }


   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
    */
   public void stop(BundleContext context) throws Exception
   {
      plugin = null;
      super.stop(context);
   }


   /**
    * Returns the shared instance
    * 
    * @return the shared instance
    */
   public static SymbolicExecutionTracerPlugin getDefault()
   {
      return plugin;
   }
   
   public static void log(int level, String message)
   {
      SymbolicExecutionTracerPlugin.log(level, message, null);     
   }
   
   public static void log(int level, String message, Throwable exception)
   {
      SymbolicExecutionTracerPlugin.getDefault().getLog().log(new Status(level, SymbolicExecutionTracerConstants.PLUGIN_ID, message, exception));      
   }


   /**
    * Returns an image descriptor for the image file at the given plug-in relative path
    * 
    * @param path the path
    * @return the image descriptor
    */
   public static ImageDescriptor getImageDescriptor(String path)
   {
      return imageDescriptorFromPlugin(SymbolicExecutionTracerConstants.PLUGIN_ID, path);
   }
}
