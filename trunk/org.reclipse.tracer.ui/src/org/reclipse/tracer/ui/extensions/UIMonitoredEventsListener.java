package org.reclipse.tracer.ui.extensions;


import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.extensionpoints.IMonitoredEventsListener;
import org.reclipse.tracer.ui.TracerUIPlugin;
import org.reclipse.tracer.ui.models.EventsModel;
import org.reclipse.tracer.ui.models.ExecutionMonitorModel;
import org.reclipse.tracer.ui.models.MonitoredClass;
import org.reclipse.tracer.ui.models.MonitoredMethod;
import org.reclipse.tracer.ui.models.VMEvent;
import org.reclipse.tracer.ui.models.VMStateModel;

import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.Location;
import com.sun.jdi.Method;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4185 $ $Date: 2009-12-15 11:08:24 +0100 (Di, 15 Dez 2009) $
 */
public class UIMonitoredEventsListener implements IMonitoredEventsListener
{

   private VMStateModel vmStateModel;

   private ExecutionMonitorModel executionMonitorModel;

   private EventsModel eventsModel;


   public UIMonitoredEventsListener()
   {
      this.vmStateModel = (VMStateModel) TracerUIPlugin.getDefault()
            .getVMStateModel();
      this.executionMonitorModel = (ExecutionMonitorModel) TracerUIPlugin
            .getDefault().getExecutionMonitorModel();
      this.eventsModel = (EventsModel) TracerUIPlugin.getDefault()
            .getEventsModel();
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
    * @see org.reclipse.tracer.extensionpoints.IVMStateListener#vmStateChanged(int)
    */
   public void vmStateChanged(final int newVMState)
   {
      this.vmStateModel.setState(newVMState);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmStart(com.sun.jdi.ThreadReference)
    */
   public void vmStart(final ThreadReference thread)
   {
      // clear both models
      this.executionMonitorModel.removeAllFromClasses();
      this.eventsModel.removeAllFromEvents();

      // add new event
      final VMEvent event = new VMEvent();
      event.setMessage("[" + Calendar.getInstance().getTime() +  "] Virtual Machine started.");
      event.setType(VMEvent.VM_START);

      this.eventsModel.addToEvents(event);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#classLoaded(com.sun.jdi.ReferenceType,
    *      java.util.List)
    */
   public void classLoaded(final ReferenceType type, final List superTypes)
   {
      MonitoredClass monitoredClass = this.executionMonitorModel
            .getFromClasses(type.name());
      if (monitoredClass == null)
      {
         monitoredClass = new MonitoredClass();
         monitoredClass.setName(type.name());

         // add class to execution model
         this.executionMonitorModel.addToClasses(monitoredClass);
      }

      if (type.isAbstract())
      {
         monitoredClass.setModifier(MonitoredClass.ABSTRACT);
      }
      if (type.isFinal())
      {
         monitoredClass.setModifier(MonitoredClass.FINAL);
      }

      monitoredClass.setMessage("[" + Calendar.getInstance().getTime() +  "] Class '" + type.name() + "' loaded.");
      monitoredClass.setType(VMEvent.CLASS_LOADED);

      // add new event
      this.eventsModel.addToEvents(monitoredClass);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodEventRequestCreated(com.sun.jdi.Method)
    */
   public void methodEventRequestCreated(final Method method)
   {
      final MonitoredMethod monitoredMethod = new MonitoredMethod();

      final String signature = createMethodSignature(method);
      monitoredMethod.setSignature(signature);

      if (method.isPrivate())
      {
         monitoredMethod.setVisibility(MonitoredMethod.PRIVATE);
      }
      else if (method.isPackagePrivate())
      {
         monitoredMethod.setVisibility(MonitoredMethod.PACKAGE);
      }
      else if (method.isProtected())
      {
         monitoredMethod.setVisibility(MonitoredMethod.PROTECTED);
      }
      else
      {
         monitoredMethod.setVisibility(MonitoredMethod.PUBLIC);
      }

      monitoredMethod.setMessage("[" + Calendar.getInstance().getTime() +  "] Method '" + method.declaringType().name() + "." + signature + "' is monitored.");
      monitoredMethod.setType(VMEvent.METHOD_REQUEST_CREATED);


      // add method to execution model
      final String parentClassName = method.declaringType().name();
      MonitoredClass parentClass = this.executionMonitorModel
            .getFromClasses(parentClassName);
      if (parentClass == null)
      {
         parentClass = new MonitoredClass();
         parentClass.setName(parentClassName);

         this.executionMonitorModel.addToClasses(parentClass);
      }

      parentClass.addToMethods(monitoredMethod);

      // add new event
      this.eventsModel.addToEvents(monitoredMethod);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodEntry(com.sun.jdi.ThreadReference)
    */
   public void methodEntry(final ThreadReference thread)
   {
      // increment number of executions in execution model
      try
      {
         final StackFrame frame = thread.frame(0);
         final Method method = frame.location().method();
         final ReferenceType type = method.declaringType();

         final MonitoredClass parentClass = this.executionMonitorModel
               .getFromClasses(type.name());
         if (parentClass != null)
         {
            final String signature = createMethodSignature(method);
            final MonitoredMethod executedMethod = parentClass
                  .getFromMethods(signature);
            if (executedMethod != null)
            {
               executedMethod.setExecutions(executedMethod.getExecutions() + 1);
            }
         }
      }
      catch (final IncompatibleThreadStateException e)
      {
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodExit(com.sun.jdi.ThreadReference)
    */
   public void methodExit(final ThreadReference thread)
   {
      // nothing to do
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#exceptionThrown(com.sun.jdi.ThreadReference,
    *      com.sun.jdi.Location, com.sun.jdi.ObjectReference)
    */
   public void exceptionThrown(final ThreadReference thread,
         final Location location, final ObjectReference object)
   {
      // add new event
      final VMEvent event = new VMEvent();
      event.setMessage("[" + Calendar.getInstance().getTime() +  "] Exception in debuggee.");
      event.setType(VMEvent.EXCEPTION_THROWN);

      this.eventsModel.addToEvents(event);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#threadDeath(com.sun.jdi.ThreadReference)
    */
   public void threadDeath(final ThreadReference thread)
   {
      // add new event
      final VMEvent event = new VMEvent();
      event.setMessage("[" + Calendar.getInstance().getTime() +  "] Thread stopped.");
      event.setType(VMEvent.THREAD_DEATH);

      this.eventsModel.addToEvents(event);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmDisconnect()
    */
   public void vmDisconnect()
   {
      // add new event
      final VMEvent event = new VMEvent();
      event.setMessage("[" + Calendar.getInstance().getTime() +  "] Virtual Machine disconnected.");
      event.setType(VMEvent.VM_DISCONNECT);

      this.eventsModel.addToEvents(event);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmDeath()
    */
   public void vmDeath()
   {
      // add new event
      final VMEvent event = new VMEvent();
      event.setMessage("[" + Calendar.getInstance().getTime() +  "] Virtual Machine stopped.");
      event.setType(VMEvent.VM_DEATH);

      this.eventsModel.addToEvents(event);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#tracerException(java.lang.Exception)
    */
   public void tracerException(final Exception exception)
   {
      TracerUIPlugin.logError("Tracer exception: " + exception.getMessage(),
            exception);

      // add new event
      final VMEvent event = new VMEvent();
      event.setMessage("[" + Calendar.getInstance().getTime() +  "] Tracer exception: " + exception.getMessage());
      event.setType(VMEvent.TRACER_EXCEPTION);

      this.eventsModel.addToEvents(event);
   }


   private String createMethodSignature(final Method method)
   {
      final StringBuffer buffer = new StringBuffer();
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
      buffer.append(")");

      return buffer.toString();
   }

}
