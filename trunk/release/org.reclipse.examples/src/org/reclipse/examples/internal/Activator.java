package org.reclipse.examples.internal;


import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

import org.fujaba.commons.AbstractFujabaPlugin;


public class Activator extends AbstractFujabaPlugin
{
   public final static String ID = "org.reclipse.examples"; //$NON-NLS-1$

   public static final String IMG_WIZ_EXAMPLE = "icons/example_banner.png"; //$NON-NLS-1$

   private static Activator instance;


   /**
    * Getter of the singleton instance.
    * 
    * @return Returns the singleton instance.
    */
   public static Activator getInstance()
   {
      return instance;
   }


   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      instance = this;

      // add image to cache
      addImageToCache(IMG_WIZ_EXAMPLE, IMG_WIZ_EXAMPLE);
   }


   @Override
   public void stop(BundleContext context) throws Exception
   {
      instance = null;
      super.stop(context);
   }


   @Override
   protected void log(int severity, String message, Throwable throwable)
   {
      getLog().log(new Status(severity, ID, message, throwable));
   }
}
