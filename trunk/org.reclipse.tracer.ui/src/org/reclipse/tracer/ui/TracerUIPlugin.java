package org.reclipse.tracer.ui;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.reclipse.tracer.ui.models.EventsModel;
import org.reclipse.tracer.ui.models.ExecutionMonitorModel;
import org.reclipse.tracer.ui.models.Model;
import org.reclipse.tracer.ui.models.VMStateModel;


/**
 * The main plugin class to be used in the desktop.
 */
public class TracerUIPlugin extends AbstractUIPlugin
{

   public static final boolean LOG_INFO = true;


   public static final String PLUGIN_ID = "org.reclipse.tracer.ui";


   /**
    * The singleton instance
    */
   private static TracerUIPlugin plugin;


   /**
    * Returns the shared instance.
    */
   public static TracerUIPlugin getDefault()
   {
      return plugin;
   }


   /**
    * The constructor.
    */
   public TracerUIPlugin()
   {
      plugin = this;
      setExecutionMonitorModel(new ExecutionMonitorModel());
      setEventsModel(new EventsModel());
      setVMStateModel(new VMStateModel());

      final ImageDescriptor image = ConsolePlugin
            .getImageDescriptor(IConsoleConstants.IMG_VIEW_CONSOLE);
      this.targetVMStreamsConsole = new IOConsole(
            "Reclipse Tracer: Target VM's output", image);
      final IConsoleManager consoleManager = ConsolePlugin.getDefault()
            .getConsoleManager();
      consoleManager
            .addConsoles(new IOConsole[] { this.targetVMStreamsConsole });
   }


   /**
    * <pre>
    *                        eventsModel      1 
    * TracerUIPlugin ---------------------------> Model
    *                               eventsModel 
    * </pre>
    */
   private Model eventsModel;


   public Model getEventsModel()
   {
      return this.eventsModel;
   }


   public void setEventsModel(final Model value)
   {
      if (this.eventsModel != value)
      {
         this.eventsModel = value;
      }
   }


   /**
    * <pre>
    *                      executionMonitorModel      1 
    *  TracerUIPlugin ----------------------------------> Model
    *                             executionMonitorModel 
    * </pre>
    */
   private Model executionMonitorModel;


   public Model getExecutionMonitorModel()
   {
      return this.executionMonitorModel;
   }


   public void setExecutionMonitorModel(final Model value)
   {
      if (this.executionMonitorModel != value)
      {
         this.executionMonitorModel = value;
      }
   }


   /**
    * <pre>
    *                        vmStateModel      1 
    * TracerUIPlugin ----------------------------> Model
    *                               vmStateModel 
    * </pre>
    */
   private Model vmStateModel;


   public Model getVMStateModel()
   {
      return this.vmStateModel;
   }


   public void setVMStateModel(final Model value)
   {
      if (this.vmStateModel != value)
      {
         this.vmStateModel = value;
      }
   }


   private IOConsole targetVMStreamsConsole;


   public IOConsole getTargetVMStreamConsole()
   {
      return this.targetVMStreamsConsole;
   }


   /**
    * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
    */
   @Override
   public void stop(final BundleContext context) throws Exception
   {
      super.stop(context);

      removeYou();
      plugin = null;
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


   /**
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
    */
   @Override
   protected void initializeImageRegistry(final ImageRegistry registry)
   {
      super.initializeImageRegistry(registry);

      TracerUIImages.initializeImageRegistry(registry);
   }


   protected Map colorTable = new HashMap(10);


   public Color getColor(final RGB rgb)
   {
      Color color = (Color) this.colorTable.get(rgb);
      if (color == null)
      {
         color = new Color(Display.getCurrent(), rgb);
         this.colorTable.put(rgb, color);
      }
      return color;
   }


   public void removeYou()
   {
      final Model tmpEventsModel = getEventsModel();
      if (tmpEventsModel != null)
      {
         setEventsModel(null);
      }

      final Model tmpExecutionMonitorModel = getExecutionMonitorModel();
      if (tmpExecutionMonitorModel != null)
      {
         setExecutionMonitorModel(null);
      }

      final Model tmpVmStateModel = getVMStateModel();
      if (tmpVmStateModel != null)
      {
         setVMStateModel(null);
      }

      final Iterator iter = this.colorTable.values().iterator();
      while (iter.hasNext())
      {
         ((Color) iter.next()).dispose();
      }

   }

}
