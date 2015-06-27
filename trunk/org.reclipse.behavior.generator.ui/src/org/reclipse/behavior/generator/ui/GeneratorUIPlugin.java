package org.reclipse.behavior.generator.ui;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4126 $ $Date: 2009-08-06 15:19:27 +0200 (Do, 06 Aug 2009) $
 */
public class GeneratorUIPlugin extends AbstractUIPlugin
{

   // The shared instance.
   private static GeneratorUIPlugin plugin;


   public GeneratorUIPlugin()
   {
      plugin = this;
   }


   /**
    * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
    */
   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
   }


   /**
    * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
    */
   @Override
   public void stop(BundleContext context) throws Exception
   {
      super.stop(context);
      plugin = null;
   }


   /**
    * Returns the shared instance.
    */
   public static GeneratorUIPlugin getDefault()
   {
      return plugin;
   }


   /**
    * Returns an image descriptor for the image file at the given plug-in relative path.
    * 
    * @param path the path
    * @return the image descriptor
    */
   public static ImageDescriptor getImageDescriptor(String path)
   {
      return AbstractUIPlugin.imageDescriptorFromPlugin(
            "org.reclipse.patterns.behavior.generator.ui", path);
   }

}
