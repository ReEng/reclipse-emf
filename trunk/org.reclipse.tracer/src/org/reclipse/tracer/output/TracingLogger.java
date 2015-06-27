package org.reclipse.tracer.output;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.TracerPlugin;
import org.reclipse.tracer.extensionpoints.IMonitoredEventsListener;

import com.sun.jdi.Location;
import com.sun.jdi.Method;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.ThreadReference;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4137 $ $Date: 2009-09-03 15:51:28 +0200 (Do, 03 Sep 2009) $
 */
public class TracingLogger implements IMonitoredEventsListener
{

   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmStateChanged(int)
    */
   public void vmStateChanged(final int newVMState)
   {
      if (TracerPlugin.LOG_INFO)
      {
         switch (newVMState)
         {
            case Tracer.VM_INITIALIZED:
               TracerPlugin.logInfo("VM initialized.");
               break;
            case Tracer.VM_RUNNING:
               TracerPlugin.logInfo("VM running.");
               break;
            case Tracer.VM_STOPPED:
               TracerPlugin.logInfo("VM stopped.");
               break;
            case Tracer.VM_SUSPENDED:
               TracerPlugin.logInfo("VM suspended.");
               break;
         }
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#initialize(org.reclipse.tracer.Tracer,
    *      java.util.Map)
    */
   public boolean initialize(final Tracer tracer,
         final Map<String, String> properties)
   {
      return true;
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmStart(com.sun.jdi.ThreadReference)
    */
   public void vmStart(final ThreadReference thread)
   {
      if (TracerPlugin.LOG_INFO)
      {
         TracerPlugin.logInfo("VM started.");
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#classLoaded(com.sun.jdi.ReferenceType,
    *      java.util.List)
    */
   @SuppressWarnings("unchecked")
   public void classLoaded(final ReferenceType type, final List superTypes)
   {
      if (TracerPlugin.LOG_INFO)
      {
         TracerPlugin.logInfo("Class '" + type.name() + "' loaded.");
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodEventRequestCreated(com.sun.jdi.Method)
    */
   public void methodEventRequestCreated(final Method method)
   {
      if (TracerPlugin.LOG_INFO)
      {
         final StringBuffer buffer = new StringBuffer();
         buffer.append("Method '");
         buffer.append(method.declaringType().name());
         buffer.append(".");
         buffer.append(method.name());
         buffer.append("(");

         final Iterator<?> iter = method.argumentTypeNames().iterator();
         while (iter.hasNext())
         {
            buffer.append((String) iter.next());

            if (iter.hasNext())
            {
               buffer.append(", ");
            }
         }
         buffer.append(")' is monitored.");

         TracerPlugin.logInfo(buffer.toString());
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodEntry(com.sun.jdi.ThreadReference)
    */
   public void methodEntry(final ThreadReference thread)
   {
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodExit(com.sun.jdi.ThreadReference)
    */
   public void methodExit(final ThreadReference thread)
   {
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#exceptionThrown(com.sun.jdi.ThreadReference,
    *      com.sun.jdi.Location, com.sun.jdi.ObjectReference)
    */
   public void exceptionThrown(final ThreadReference thread,
         final Location location, final ObjectReference exceptionReference)
   {
      // TODO exceptionReference should be accessed to print exception message and stack trace
      TracerPlugin.logWarning("Exception in debuggee.");
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#threadDeath(com.sun.jdi.ThreadReference)
    */
   public void threadDeath(final ThreadReference thread)
   {
      if (TracerPlugin.LOG_INFO)
      {
         TracerPlugin.logInfo("Thread '" + thread.name() + "' stopped.");
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmDisconnect()
    */
   public void vmDisconnect()
   {
      if (TracerPlugin.LOG_INFO)
      {
         TracerPlugin.logInfo("VM disconnected.");
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmDeath()
    */
   public void vmDeath()
   {
      if (TracerPlugin.LOG_INFO)
      {
         TracerPlugin.logInfo("VM stopped.");
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#tracerException(Exception)
    */
   public void tracerException(final Exception exception)
   {
      TracerPlugin.logError("Tracer exception: " + exception.getMessage(),
            exception);
   }

}
