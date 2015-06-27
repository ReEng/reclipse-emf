package org.reclipse.structure.specification.navigator;


import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

import org.fujaba.commons.AbstractFujabaPlugin;


/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSNavigatorPlugin extends AbstractFujabaPlugin
{

   public static final String ID = "org.reclipse.structure.specification.navigator"; //$NON-NLS-1$

   public static final String IMG_PATTERN = "pattern"; //$NON-NLS-1$

   private static final String IMG_PATTERN_PATH = "icons/pattern.png"; //$NON-NLS-1$

   private static PSNavigatorPlugin instance;


   /**
    * The default constructor.
    */
   public PSNavigatorPlugin()
   {
      super();
   }


   /**
    * Getter of the singleton instance.
    * 
    * @return Returns the instance.
    */
   public static PSNavigatorPlugin getInstance()
   {
      return instance;
   }


   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      instance = this;

      addImageToCache(IMG_PATTERN, IMG_PATTERN_PATH);
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
