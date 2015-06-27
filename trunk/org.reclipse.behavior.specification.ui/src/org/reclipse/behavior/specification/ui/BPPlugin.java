package org.reclipse.behavior.specification.ui;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.reclipse.behavior.specification.ui.util.ModelRegistrator;


/**
 * The activator class controls the plug-in life cycle
 */
public class BPPlugin extends AbstractUIPlugin
{

   // The plug-in ID
   public static final String ID = "org.reclipse.behavior.specification.ui";


   // The shared instance
   private static BPPlugin plugin;


   /**
    * The constructor
    */
   public BPPlugin()
   {
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
      plugin = this;
      ModelRegistrator.initialize();
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
   public static BPPlugin getDefault()
   {
      return plugin;
   }


   public void logInfo(String message)
   {
      logInfo(message, null);
   }


   /**
    * @param throwable actual error or null could be passed
    * @generated
    */
   public void logInfo(String message, Throwable throwable)
   {
      if (message == null && throwable != null)
      {
         message = throwable.getMessage();
      }
      getLog()
            .log(new Status(IStatus.INFO, ID, IStatus.OK, message, throwable));
   }


   public void logError(String error)
   {
      logError(error, null);
   }


   /**
    * @param throwable actual error or null could be passed
    * @generated
    */
   public void logError(String error, Throwable throwable)
   {
      if (error == null && throwable != null)
      {
         error = throwable.getMessage();
      }
      getLog().log(new Status(IStatus.ERROR, ID, IStatus.OK, error, throwable));
   }


   /**
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
    */
   @Override
   protected void initializeImageRegistry(ImageRegistry registry)
   {
      super.initializeImageRegistry(registry);

      addImageDescriptor(registry, BPImages.IMG_BP_OBJECT_16,
            BPImages.IMG_BP_OBJECT_16);

      addImageDescriptor(registry, BPImages.IMG_BP_MESSAGE_16,
            BPImages.IMG_BP_MESSAGE_16);

      addImageDescriptor(registry, BPImages.IMG_BP_DIAGRAM_16,
            BPImages.IMG_BP_DIAGRAM_16);

      addImageDescriptor(registry, BPImages.IMG_BP_COMBINED_FRAGMENT_16,
            BPImages.IMG_BP_COMBINED_FRAGMENT_16);

      addImageDescriptor(registry, BPImages.IMG_BP_ASSIGNMENT_16,
            BPImages.IMG_BP_ASSIGNMENT_16);

   }


   private void addImageDescriptor(ImageRegistry registry, String key,
         String imagePath)
   {
      ImageDescriptor id = ImageDescriptor.createFromURL(getBundle().getEntry(
            imagePath));
      registry.put(key, id);
   }

}
