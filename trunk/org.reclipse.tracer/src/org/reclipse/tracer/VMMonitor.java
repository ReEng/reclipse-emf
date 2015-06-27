package org.reclipse.tracer;


import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.reclipse.tracer.eventhandlers.AbstractMethodEventHandler;
import org.reclipse.tracer.eventhandlers.BreakpointMethodEventHandler;
import org.reclipse.tracer.extensionpoints.IMonitoredEventsListener;
import org.reclipse.tracer.model.definition.TraceDefinition;

import com.sun.jdi.Method;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.event.BreakpointEvent;
import com.sun.jdi.event.ClassPrepareEvent;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.EventIterator;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.ExceptionEvent;
import com.sun.jdi.event.MethodEntryEvent;
import com.sun.jdi.event.MethodExitEvent;
import com.sun.jdi.event.ThreadDeathEvent;
import com.sun.jdi.event.VMDeathEvent;
import com.sun.jdi.event.VMDisconnectEvent;
import com.sun.jdi.event.VMStartEvent;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *             0..1   methodEventHandler    0..1 
 *  VMMonitor ----------------------------------- AbstractMethodEventHandler
 *             vMMonitor      methodEventHandler
 *
 *                 monitorEventListeners    0..n 
 *  VMMonitor -----------------------------------> IMonitoredEventsListener
 *                         monitorEventListeners
 * 
 *             0..1   vmMonitor   0..1 
 *  VMMonitor ------------------------- Tracer
 *             vMMonitor        tracer
 *
 *                 virtualMachine    0..1 
 *  VMMonitor ----------------------------> VirtualMachine
 *                         virtualMachine 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class VMMonitor extends Thread
{

   public VMMonitor(final TraceDefinition traceDefinition,
         final ILaunchConfiguration launchConfig,
         final VirtualMachine virtualMachine)
   {
      this.virtualMachine = virtualMachine;
      this.vmConnected = true;

      setName("Target VM's event monitor");

      final AbstractMethodEventHandler methodEventHandler = new BreakpointMethodEventHandler(
            launchConfig);
      methodEventHandler.setTraceDefinition(traceDefinition);
      setMethodEventHandler(methodEventHandler);
   }


   private boolean vmDead = false;


   private boolean vmConnected;


   /* package */boolean isVmConnected()
   {
      return this.vmConnected;
   }


   /**
    * <pre>
    *             0..1   methodEventHandler    0..1 
    *  VMMonitor ----------------------------------- AbstractMethodEventHandler
    *             vMMonitor      methodEventHandler 
    * </pre>
    */
   private AbstractMethodEventHandler methodEventHandler;


   public AbstractMethodEventHandler getMethodEventHandler()
   {
      return this.methodEventHandler;
   }


   public boolean setMethodEventHandler(final AbstractMethodEventHandler value)
   {
      boolean changed = false;
      if (this.methodEventHandler != value)
      {
         final AbstractMethodEventHandler oldValue = this.methodEventHandler;

         if (this.methodEventHandler != null)
         {
            this.methodEventHandler = null;
            oldValue.setVMMonitor(null);
         }
         this.methodEventHandler = value;
         if (value != null)
         {
            value.setVMMonitor(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *             0..1   vmMonitor   0..1 
    *  VMMonitor ------------------------- Tracer
    *             vMMonitor        tracer 
    * </pre>
    */
   private Tracer tracer;


   public Tracer getTracer()
   {
      return this.tracer;
   }


   /* package */boolean setTracer(final Tracer value)
   {
      boolean changed = false;
      if (this.tracer != value)
      {
         final Tracer oldValue = this.tracer;

         if (this.tracer != null)
         {
            this.tracer = null;
            oldValue.setVMMonitor(null);
         }
         this.tracer = value;
         if (value != null)
         {
            value.setVMMonitor(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                 virtualMachine    0..1 
    *  VMMonitor ----------------------------> VirtualMachine
    *                         virtualMachine 
    * </pre>
    */
   private VirtualMachine virtualMachine;


   public VirtualMachine getVirtualMachine()
   {
      return this.virtualMachine;
   }


   @Override
   public void run()
   {
      getMethodEventHandler().createClassPrepareRequests();

      final EventQueue eventQueue = this.virtualMachine.eventQueue();
      while (this.vmConnected)
      {
         try
         {
            final EventSet eventSet = eventQueue.remove();
            final EventIterator iter = eventSet.eventIterator();
            while (iter.hasNext())
            {
               handleVMEvent(iter.nextEvent());
            }
            eventSet.resume();
         }
         catch (final InterruptedException e)
         {
         }
         catch (final Exception e)
         {
            handleException(e);
         }
      }
   }


   private void handleVMEvent(final Event event)
   {
      if (event instanceof BreakpointEvent)
      {
         this.getMethodEventHandler().handleVMEvent(event);
      }
      else if (event instanceof ClassPrepareEvent)
      {
         this.getMethodEventHandler().handleVMEvent(event);
      }
      else if (event instanceof MethodEntryEvent
            || event instanceof MethodExitEvent)
      {
         this.getMethodEventHandler().handleVMEvent(event);
      }
      else if (event instanceof ExceptionEvent)
      {
         fireExceptionThrown((ExceptionEvent) event);
      }
      else if (event instanceof ThreadDeathEvent)
      {
         fireThreadDeath((ThreadDeathEvent) event);
      }
      else if (event instanceof VMStartEvent)
      {
         fireVMStart((VMStartEvent) event);
      }
      else if (event instanceof VMDisconnectEvent)
      {
         handleVMDisconnect((VMDisconnectEvent) event);
      }
      else if (event instanceof VMDeathEvent)
      {
         handleVMDeath((VMDeathEvent) event);
      }
      else
      {
         TracerPlugin.logWarning("Unexpected virtual machine event!");
      }
   }


   private void handleVMDisconnect(final VMDisconnectEvent event)
   {
      this.vmConnected = false;
      this.getTracer().setVMState(Tracer.VM_STOPPED);

      fireVMDisconnect(event);
   }


   private void handleVMDeath(final VMDeathEvent event)
   {
      this.vmConnected = false;
      this.vmDead = true;
      this.getTracer().setVMState(Tracer.VM_STOPPED);

      fireVMDeath(event);
   }


   private void handleException(final Exception e)
   {
      // stop tracer
      this.getTracer().setVMState(Tracer.VM_STOPPED);

      // stop the target vm
      getVirtualMachine().exit(Tracer.VM_EXIT_CODE);

      fireTracerException(e);
   }


   private void fireVMStart(final VMStartEvent event)
   {
      if (event.thread() != null)
      {
         this.getTracer().setVMState(Tracer.VM_RUNNING);
         final Iterator<IMonitoredEventsListener> iter = getTracer()
               .iteratorOfMonitoredEventListeners();
         while (iter.hasNext())
         {
            final IMonitoredEventsListener listener = iter.next();
            try
            {
               listener.vmStart(event.thread());
            }
            catch (final Exception exception)
            {
               // catch every exception thrown by a listener to not interfere with the tracer.
               System.err.println("IMonitoredEventsListener '"
                     + listener.getClass().getName() + "' caused exception: "
                     + exception.getMessage());
               exception.printStackTrace();
            }
         }
      }
   }


   public void fireClassLoaded(final ReferenceType type,
         final LinkedList<ReferenceType> superTypes)
   {
      final Iterator<IMonitoredEventsListener> iter = getTracer()
            .iteratorOfMonitoredEventListeners();
      while (iter.hasNext())
      {
         final IMonitoredEventsListener listener = iter.next();
         try
         {
            listener.classLoaded(type, superTypes);
         }
         catch (final Exception exception)
         {
            // catch every exception thrown by a listener to not interfere with the tracer.
            System.err.println("IMonitoredEventsListener '"
                  + listener.getClass().getName() + "' caused exception: "
                  + exception.getMessage());
            exception.printStackTrace();
         }
      }
   }


   public void fireMethodEventRequestCreated(final Method method)
   {
      final Iterator<IMonitoredEventsListener> iter = getTracer()
            .iteratorOfMonitoredEventListeners();
      while (iter.hasNext())
      {
         final IMonitoredEventsListener listener = iter.next();
         try
         {
            listener.methodEventRequestCreated(method);
         }
         catch (final Exception exception)
         {
            // catch every exception thrown by a listener to not interfere with the tracer.
            System.err.println("IMonitoredEventsListener '"
                  + listener.getClass().getName() + "' caused exception: "
                  + exception.getMessage());
            exception.printStackTrace();
         }
      }
   }


   public void fireMethodEntry(final ThreadReference thread)
   {
      final Iterator<IMonitoredEventsListener> iter = getTracer()
            .iteratorOfMonitoredEventListeners();
      while (iter.hasNext())
      {
         final IMonitoredEventsListener listener = iter.next();
         try
         {
            listener.methodEntry(thread);
         }
         catch (final Exception exception)
         {
            // catch every exception thrown by a listener to not interfere with the tracer.
            System.err.println("IMonitoredEventsListener '"
                  + listener.getClass().getName() + "' caused exception: "
                  + exception.getMessage());
            exception.printStackTrace();
         }
      }
   }


   public void fireMethodExit(final ThreadReference thread)
   {
      final Iterator<IMonitoredEventsListener> iter = getTracer()
            .iteratorOfMonitoredEventListeners();
      while (iter.hasNext())
      {
         final IMonitoredEventsListener listener = iter.next();
         try
         {
            listener.methodExit(thread);
         }
         catch (final Exception exception)
         {
            // catch every exception thrown by a listener to not interfere with the tracer.
            System.err.println("IMonitoredEventsListener '"
                  + listener.getClass().getName() + "' caused exception: "
                  + exception.getMessage());
            exception.printStackTrace();
         }
      }
   }


   public void fireExceptionThrown(final ExceptionEvent event)
   {
      final Iterator<IMonitoredEventsListener> iter = getTracer()
            .iteratorOfMonitoredEventListeners();
      while (iter.hasNext())
      {
         final IMonitoredEventsListener listener = iter.next();
         try
         {
            listener.exceptionThrown(event.thread(), event.catchLocation(),
                  event.exception());
         }
         catch (final Exception exception)
         {
            // catch every exception thrown by a listener to not interfere with the tracer.
            System.err.println("IMonitoredEventsListener '"
                  + listener.getClass().getName() + "' caused exception: "
                  + exception.getMessage());
            exception.printStackTrace();
         }
      }
   }


   private void fireThreadDeath(final ThreadDeathEvent event)
   {
      final Iterator<IMonitoredEventsListener> iter = getTracer()
            .iteratorOfMonitoredEventListeners();
      while (iter.hasNext())
      {
         final IMonitoredEventsListener listener = iter.next();
         try
         {
            listener.threadDeath(event.thread());
         }
         catch (final Exception exception)
         {
            // catch every exception thrown by a listener to not interfere with the tracer.
            System.err.println("IMonitoredEventsListener '"
                  + listener.getClass().getName() + "' caused exception: "
                  + exception.getMessage());
            exception.printStackTrace();
         }
      }
   }


   private void fireVMDisconnect(final VMDisconnectEvent event)
   {
      if (!this.vmDead)
      {
         final Iterator<IMonitoredEventsListener> iter = getTracer()
               .iteratorOfMonitoredEventListeners();
         while (iter.hasNext())
         {
            final IMonitoredEventsListener listener = iter.next();
            try
            {
               listener.vmDisconnect();
            }
            catch (final Exception exception)
            {
               // catch every exception thrown by a listener to not interfere with the tracer.
               System.err.println("IMonitoredEventsListener '"
                     + listener.getClass().getName() + "' caused exception: "
                     + exception.getMessage());
               exception.printStackTrace();
            }
         }
      }
   }


   private void fireVMDeath(final VMDeathEvent event)
   {
      final Iterator<IMonitoredEventsListener> iter = getTracer()
            .iteratorOfMonitoredEventListeners();
      while (iter.hasNext())
      {
         final IMonitoredEventsListener listener = iter.next();
         try
         {
            listener.vmDeath();
         }
         catch (final Exception exception)
         {
            // catch every exception thrown by a listener to not interfere with the tracer.
            System.err.println("IMonitoredEventsListener '"
                  + listener.getClass().getName() + "' caused exception: "
                  + exception.getMessage());
            exception.printStackTrace();
         }
      }
   }


   private void fireTracerException(final Exception exception)
   {
      final Iterator<IMonitoredEventsListener> iter = getTracer()
            .iteratorOfMonitoredEventListeners();
      while (iter.hasNext())
      {
         final IMonitoredEventsListener listener = iter.next();
         try
         {
            listener.tracerException(exception);
         }
         catch (final Exception e)
         {
            // catch every exception thrown by a listener to not interfere with the tracer.
            System.err.println("IMonitoredEventsListener '"
                  + listener.getClass().getName() + "' caused exception: "
                  + e.getMessage());
            e.printStackTrace();
         }
      }
   }


   public void removeYou()
   {
      final Tracer tmpTracer = getTracer();
      if (tmpTracer != null)
      {
         setTracer(null);
      }

      final AbstractMethodEventHandler tmpMethodEventHandler = getMethodEventHandler();
      if (tmpMethodEventHandler != null)
      {
         setMethodEventHandler(null);
         tmpMethodEventHandler.removeYou();
      }

      this.virtualMachine = null;
   }

}
