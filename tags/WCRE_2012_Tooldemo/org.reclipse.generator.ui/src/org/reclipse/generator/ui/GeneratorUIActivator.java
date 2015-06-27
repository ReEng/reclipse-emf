package org.reclipse.generator.ui;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class GeneratorUIActivator extends AbstractUIPlugin
{

   // The plug-in ID
   public static final String PLUGIN_ID = "org.reclipse.generator.ui";

   // The shared instance
   private static GeneratorUIActivator plugin;


   /**
    * The constructor
    */
   public GeneratorUIActivator()
   {
      plugin = this;
   }


   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
    */
   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
   }


   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
    */
   @Override
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
   public static GeneratorUIActivator getDefault()
   {
      return plugin;
   }


   public static ImageDescriptor getImageDescriptor(String path)
   {
      return AbstractUIPlugin.imageDescriptorFromPlugin(getDefault().getBundle().getSymbolicName(), path);
   }
}
