package org.reclipse.tracer.ui;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3786 $ $Date: 2007-09-09 23:44:20 +0200 (So, 09 Sep 2007) $
 */
public class TracerUIImages
{

   public static final String IMG_CLEAR_TRACER_VIEW = "icons/clear_tracer_view.gif";

   public static final String IMG_COLLAPSE_ALL = "icons/collapseall.gif";

   public static final String IMG_CLASS = "icons/events/class.gif";

   public static final String IMG_METHOD_NOT_EXECUTED = "icons/events/method_not_executed.gif";

   public static final String IMG_METHOD_EXECUTED = "icons/events/method_executed.gif";

   public static final String IMG_EXPAND_ALL = "icons/expandall.gif";

   public static final String IMG_ENABLED_RESUME = "icons/enabled/resume.gif";

   public static final String IMG_DISABLED_RESUME = "icons/disabled/resume.gif";

   public static final String IMG_ENABLED_SUSPEND = "icons/enabled/suspend.gif";

   public static final String IMG_DISABLED_SUSPEND = "icons/disabled/suspend.gif";

   public static final String IMG_ENABLED_TERMINATE = "icons/enabled/terminate.gif";

   public static final String IMG_DISABLED_TERMINATE = "icons/disabled/terminate.gif";

   public static final String IMG_TRACE_DEFINTION = "icons/tracer_input.gif";

   public static final String IMG_PUBLIC_METHOD = "icons/events/public_method.gif";

   public static final String IMG_TRACER_CLASSPATH = "icons/tracer_classpath.gif";

   public static final String IMG_TRACER_OPTIONS = "icons/tracer_options.gif";

   public static final String IMG_TRACER_LISTENERS = "icons/tracer_listeners.gif";

   public static final String IMG_CONSOLE_VIEW = "icons/console_view.gif";

   public static final String IMG_ABSTRACT_CLASS = "icons/events/abstract_class.gif";

   public static final String IMG_FINAL_CLASS = "icons/events/final_class.gif";

   public static final String IMG_THREAD_STOPPED = "icons/events/thread_stopped.gif";

   public static final String IMG_VM_STARTED = "icons/events/vm_started.gif";

   public static final String IMG_VM_STOPPED = "icons/events/vm_stopped.gif";

   public static final String IMG_EXCEPTION_THROWN = "icons/events/exception_thrown.gif";

   public static final String IMG_TRACER_EXECPTION = "icons/events/tracer_exception.gif";

   public static final String IMG_PACKAGE_METHOD = "icons/events/package_method.gif";

   public static final String IMG_PROTECTED_METHOD = "icons/events/protected_method.gif";

   public static final String IMG_PRIVATE_METHOD = "icons/events/private_method.gif";


   private static TracerUIPlugin plugin;

   static
   {
      plugin = TracerUIPlugin.getDefault();
   }


   public static Image getImage(final String symbolicName)
   {
      return plugin.getImageRegistry().get(symbolicName);
   }


   public static ImageDescriptor getDescriptor(final String symbolicName)
   {
      return plugin.getImageRegistry().getDescriptor(symbolicName);
   }


   public static void initializeImageRegistry(final ImageRegistry registry)
   {
      addImageDescriptor(registry, IMG_CLEAR_TRACER_VIEW);
      addImageDescriptor(registry, IMG_COLLAPSE_ALL);
      addImageDescriptor(registry, IMG_CLASS);
      addImageDescriptor(registry, IMG_METHOD_NOT_EXECUTED);
      addImageDescriptor(registry, IMG_METHOD_EXECUTED);
      addImageDescriptor(registry, IMG_EXPAND_ALL);
      addImageDescriptor(registry, IMG_ENABLED_RESUME);
      addImageDescriptor(registry, IMG_DISABLED_RESUME);
      addImageDescriptor(registry, IMG_ENABLED_SUSPEND);
      addImageDescriptor(registry, IMG_DISABLED_SUSPEND);
      addImageDescriptor(registry, IMG_ENABLED_TERMINATE);
      addImageDescriptor(registry, IMG_DISABLED_TERMINATE);
      addImageDescriptor(registry, IMG_TRACE_DEFINTION);
      addImageDescriptor(registry, IMG_PUBLIC_METHOD);
      addImageDescriptor(registry, IMG_TRACER_CLASSPATH);
      addImageDescriptor(registry, IMG_TRACER_OPTIONS);
      addImageDescriptor(registry, IMG_TRACER_LISTENERS);
      addImageDescriptor(registry, IMG_CONSOLE_VIEW);
      addImageDescriptor(registry, IMG_ABSTRACT_CLASS);
      addImageDescriptor(registry, IMG_FINAL_CLASS);
      addImageDescriptor(registry, IMG_THREAD_STOPPED);
      addImageDescriptor(registry, IMG_VM_STARTED);
      addImageDescriptor(registry, IMG_VM_STOPPED);
      addImageDescriptor(registry, IMG_EXCEPTION_THROWN);
      addImageDescriptor(registry, IMG_TRACER_EXECPTION);
      addImageDescriptor(registry, IMG_PACKAGE_METHOD);
      addImageDescriptor(registry, IMG_PROTECTED_METHOD);
      addImageDescriptor(registry, IMG_PRIVATE_METHOD);
   }


   private static void addImageDescriptor(final ImageRegistry registry,
         final String iconPath)
   {
      final ImageDescriptor id = ImageDescriptor.createFromURL(plugin
            .getBundle().getEntry(iconPath));
      registry.put(iconPath, id);
   }


}
