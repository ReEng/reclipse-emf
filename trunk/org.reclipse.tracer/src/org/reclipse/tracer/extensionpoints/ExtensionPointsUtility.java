package org.reclipse.tracer.extensionpoints;


import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.TracerPlugin;


/**
 * @author Lothar
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3787 $ $Date: 2007-09-09 23:46:09 +0200 (So, 09 Sep 2007) $
 */
public class ExtensionPointsUtility
{

   public static Set<IMonitoredEventsListener> getMonitoredEventsListeners()
   {
      final Set<IMonitoredEventsListener> listeners = new HashSet<IMonitoredEventsListener>();

      final IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(ITracerConstants.PLUGIN_ID,
                  ITracerConstants.MONITORED_EVENTS_LISTENERS_EXTENSION)
            .getExtensions();

      for (int i = 0; i < extensions.length; i++)
      {
         final IConfigurationElement[] configElements = extensions[i]
               .getConfigurationElements();
         for (int j = 0; j < configElements.length; j++)
         {
            if (ITracerConstants.MONITORED_EVENTS_LISTENER_ELEMENT
                  .equals(configElements[j].getName()))
            {
               try
               {
                  final IMonitoredEventsListener listener = (IMonitoredEventsListener) configElements[j]
                        .createExecutableExtension(ITracerConstants.CLASS_ATTRIBUTE);
                  listeners.add(listener);
               }
               catch (final CoreException e)
               {
                  TracerPlugin
                        .logError(
                              "IMonitoredEventsListener extension could not be loaded.",
                              e);
               }
            }
         }
      }

      return listeners;
   }


   public static Set<ITracerChangedListener> getTracerListeners()
   {
      final Set<ITracerChangedListener> listeners = new HashSet<ITracerChangedListener>();

      final IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(ITracerConstants.PLUGIN_ID,
                  ITracerConstants.TRACER_CHANGED_LISTENERS_EXTENSION)
            .getExtensions();

      for (int i = 0; i < extensions.length; i++)
      {
         final IConfigurationElement[] configElements = extensions[i]
               .getConfigurationElements();
         for (int j = 0; j < configElements.length; j++)
         {
            if (ITracerConstants.TRACER_CHANGED_LISTENER_ELEMENT
                  .equals(configElements[j].getName()))
            {
               try
               {
                  final ITracerChangedListener listener = (ITracerChangedListener) configElements[j]
                        .createExecutableExtension(ITracerConstants.CLASS_ATTRIBUTE);
                  listeners.add(listener);
               }
               catch (final CoreException e)
               {
                  TracerPlugin
                        .logError(
                              "ITracerChangedListener extension could not be loaded.",
                              e);
               }
            }
         }
      }

      return listeners;
   }


   public static Set<IVMStreamReceiver> getVMStreamReceivers(
         final String element)
   {
      final Set<IVMStreamReceiver> listeners = new HashSet<IVMStreamReceiver>();

      final IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(ITracerConstants.PLUGIN_ID,
                  ITracerConstants.VM_STREAM_RECEIVERS_EXTENSION)
            .getExtensions();

      for (int i = 0; i < extensions.length; i++)
      {
         final IConfigurationElement[] configElements = extensions[i]
               .getConfigurationElements();
         for (int j = 0; j < configElements.length; j++)
         {
            if (configElements[j].getName().equals(element))
            {
               try
               {
                  final IVMStreamReceiver receiver = (IVMStreamReceiver) configElements[j]
                        .createExecutableExtension(ITracerConstants.CLASS_ATTRIBUTE);
                  listeners.add(receiver);
               }
               catch (final CoreException e)
               {
                  TracerPlugin.logError(
                        "IVMStreamReceiver extension could not be loaded.", e);
               }
            }
         }
      }

      return listeners;
   }


   public static boolean isListenerOptional(final String listenerClassName)
   {
      final IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(ITracerConstants.PLUGIN_ID,
                  ITracerConstants.MONITORED_EVENTS_LISTENERS_EXTENSION)
            .getExtensions();

      for (final IExtension extension : extensions)
      {
         final IConfigurationElement[] configElements = extension
               .getConfigurationElements();
         for (final IConfigurationElement configElement : configElements)
         {
            if (ITracerConstants.MONITORED_EVENTS_LISTENER_ELEMENT
                  .equals(configElement.getName()))
            {
               if (listenerClassName.equals(configElement
                     .getAttribute(ITracerConstants.CLASS_ATTRIBUTE)))
               {
                  return Boolean.valueOf(configElement
                        .getAttribute(ITracerConstants.OPTIONAL_ATTRIBUTE));
               }
            }
         }
      }

      return false;
   }

}
