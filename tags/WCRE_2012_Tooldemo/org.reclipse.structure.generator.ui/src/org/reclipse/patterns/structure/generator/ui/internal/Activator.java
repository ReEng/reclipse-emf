package org.reclipse.patterns.structure.generator.ui.internal;


import org.eclipse.core.runtime.Status;
import org.fujaba.commons.AbstractFujabaPlugin;
import org.osgi.framework.BundleContext;


public class Activator extends AbstractFujabaPlugin
{
   public final static String ID = "org.reclipse.patterns.structure.generator.ui"; //$NON-NLS-1$

   public static final String IMG_BANNER = "icons/banner.png"; //$NON-NLS-1$

   private static Activator instance;


   public static Activator getInstance()
   {
      return instance;
   }


   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      instance = this;

      addImageToCache(IMG_BANNER, IMG_BANNER);
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
