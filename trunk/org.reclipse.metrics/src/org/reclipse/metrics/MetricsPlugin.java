package org.reclipse.metrics;


import org.eclipse.core.runtime.Status;
import org.fujaba.commons.AbstractFujabaPlugin;
import org.osgi.framework.BundleContext;



/**
 * The activator class controls the plug-in life cycle and support logging of informations, warnings
 * and errors.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class MetricsPlugin extends AbstractFujabaPlugin
{
   public final static String ID = "org.reclipse.metrics";

   private static MetricsPlugin instance;


   /**
    * Getter of the singleton instance.
    * 
    * @return Returns the singleton instance.
    */
   public static MetricsPlugin getInstance()
   {
      return instance;
   }


   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      instance = this;
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
