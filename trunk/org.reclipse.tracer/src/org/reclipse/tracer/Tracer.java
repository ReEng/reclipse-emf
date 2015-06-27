package org.reclipse.tracer;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.reclipse.tracer.exceptions.TracerStartException;
import org.reclipse.tracer.extensionpoints.ExtensionPointsUtility;
import org.reclipse.tracer.extensionpoints.IMonitoredEventsListener;
import org.reclipse.tracer.extensionpoints.ITracerChangedListener;
import org.reclipse.tracer.extensionpoints.IVMStreamReceiver;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.streaming.VMSystemErrPoller;
import org.reclipse.tracer.streaming.VMSystemOutPoller;

import com.sun.jdi.VirtualMachine;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *              <<static>>       0..n 
 * Tracer ----------------------------> ITracerChangedListener
 *             tracerChangedListeners
 * 
 *              <<static>>       0..n 
 * Tracer ----------------------------> IVMStreamReceiver
 *                 systemOutReceivers
 * 
 *              <<static>>       0..n 
 * Tracer ----------------------------> IVMStreamReceiver
 *                 systemErrReceivers 
 * 
 *             traceDefinition   0..1 
 * Tracer ----------------------------> TraceDefinition
 *                    traceDefinition
 *  
 *            launchConfiguration   0..1 
 * Tracer -------------------------------> ILaunchConfiguration
 *                   launchConfiguration
 * 
 *                                   0..n 
 * VMMonitor -----------------------------> IMonitoredEventsListener
 *                monitoredEventListeners
 *  
 *         0..1   vmMonitor   0..1 
 * Tracer ------------------------- VMMonitor
 *         tracer        vMMonitor 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class Tracer
{

   public static final int VM_NONE = 0;

   public static final int VM_INITIALIZED = 1;

   public static final int VM_RUNNING = 2;

   public static final int VM_SUSPENDED = 3;

   public static final int VM_STOPPED = 4;


   /**
    * Exit code to be returned by the VM when VM is stopped.
    */
   protected static final int VM_EXIT_CODE = 1;


   /**
    * The "singleton" tracer.
    */
   private static Tracer currentTracer;


   static
   {
      for (final ITracerChangedListener listener : ExtensionPointsUtility
            .getTracerListeners())
      {
         addTracerChangedListener(listener);
      }

      for (final IVMStreamReceiver receiver : ExtensionPointsUtility
            .getVMStreamReceivers(ITracerConstants.VM_SYSTEM_OUT_RECEIVER_EXTENSION))
      {
         addSystemOutReceiver(receiver);
      }

      for (final IVMStreamReceiver receiver : ExtensionPointsUtility
            .getVMStreamReceivers(ITracerConstants.VM_SYSTEM_ERR_RECEIVER_EXTENSION))
      {
         addSystemErrReceiver(receiver);
      }
   }


   /**
    * This constructor is private, use the static createTracer method.
    */
   private Tracer(final TraceDefinition traceDefinition,
         final ILaunchConfiguration launchConfiguration)
   {
      this.vmState = VM_NONE;
      this.traceDefinition = traceDefinition;
      this.launchConfiguration = launchConfiguration;

      initializeListeners(launchConfiguration);
   }


   /**
    * This method is used to create a new tracer. There is only one tracer allowed at any time.
    * 
    * @param traceDefinition
    * @return a new preconfigured instance of the Tracer
    * 
    * @throws IllegalStateException if the current Tracer hasn't finishing monitoring yet, which
    *            ends with the target vm's death.
    */
   public static Tracer createTracer(final TraceDefinition traceDefinition,
         final ILaunchConfiguration launchConfiguration)
   {
      // the currentTracer must have finished its job
      if (currentTracer != null && currentTracer.getVMState() != VM_STOPPED)
      {
         throw new IllegalStateException(
               "The current tracer is still monitoring a running VM.\n"
                     + "Cannot start a new tracer until the current tracer finishes monitoring.");
      }

      final Tracer oldCurrentTracer = currentTracer;

      currentTracer = new Tracer(traceDefinition, launchConfiguration);

      // notify all listeners that a new Tracer was created
      fireCurrentTracerChanged(oldCurrentTracer, currentTracer);

      // remove the old tracer instance
      if (oldCurrentTracer != null)
      {
         oldCurrentTracer.removeYou();
      }

      return currentTracer;
   }


   /**
    * @return the current active tracer
    */
   public static Tracer getCurrentTracer()
   {
      return currentTracer;
   }


   /**
    * <pre>
    *              <<static>>       0..n 
    * Tracer ----------------------------> ITracerChangedListener
    *             tracerChangedListeners 
    * </pre>
    */
   private static HashSet<ITracerChangedListener> tracerChangedListeners;


   public static void addTracerChangedListener(
         final ITracerChangedListener listener)
   {
      if (listener != null)
      {
         if (tracerChangedListeners == null)
         {
            tracerChangedListeners = new HashSet<ITracerChangedListener>();
         }
         tracerChangedListeners.add(listener);
      }
   }


   public static void removeTracerChangedListener(
         final ITracerChangedListener listener)
   {
      if ((tracerChangedListeners != null) && (listener != null))
      {
         tracerChangedListeners.remove(listener);
      }
   }


   private static void fireCurrentTracerChanged(final Tracer oldTracer,
         final Tracer newTracer)
   {
      if (tracerChangedListeners != null)
      {
         final Iterator<ITracerChangedListener> iter = tracerChangedListeners
               .iterator();
         while (iter.hasNext())
         {
            final ITracerChangedListener listener = iter.next();
            try
            {
               listener.currentTracerChanged(oldTracer, newTracer);
            }
            catch (final Exception exception)
            {
               // catch every exception thrown by a listener to not interfere with the tracer.
               System.err.println("ITracerChangedListener '"
                     + listener.getClass().getName() + "' caused exception: "
                     + exception.getMessage());
               exception.printStackTrace();
            }
         }
      }
   }


   /**
    * <pre>
    *              <<static>>       0..n 
    * Tracer ----------------------------> IVMStreamReceiver
    *                 systemOutReceivers 
    * </pre>
    */
   private static HashSet<IVMStreamReceiver> systemOutReceivers;


   public static void addSystemOutReceiver(final IVMStreamReceiver value)
   {
      if (value != null)
      {
         if (systemOutReceivers == null)
         {
            systemOutReceivers = new HashSet<IVMStreamReceiver>();
         }
         systemOutReceivers.add(value);
      }
   }


   public static Iterator<IVMStreamReceiver> iteratorOfSystemOutReceivers()
   {
      return (systemOutReceivers == null) ? Collections.<IVMStreamReceiver>emptyList().iterator()
            : systemOutReceivers.iterator();
   }


   public static void removeSystemOutReceiver(final IVMStreamReceiver value)
   {
      if ((systemOutReceivers != null) && (value != null))
      {
         systemOutReceivers.remove(value);
      }
   }


   /**
    * <pre>
    *              <<static>>       0..n 
    * Tracer ----------------------------> IVMStreamReceiver
    *                 systemErrReceivers 
    * </pre>
    */
   private static HashSet<IVMStreamReceiver> systemErrReceivers;


   public static void addSystemErrReceiver(final IVMStreamReceiver value)
   {
      if (value != null)
      {
         if (systemErrReceivers == null)
         {
            systemErrReceivers = new HashSet<IVMStreamReceiver>();
         }
         systemErrReceivers.add(value);
      }
   }


   public static Iterator<IVMStreamReceiver> iteratorOfSystemErrReceivers()
   {
      return (systemErrReceivers == null) ? Collections.<IVMStreamReceiver>emptyList().iterator()
            : systemErrReceivers.iterator();
   }


   public static void removeSystemErrReceiver(final IVMStreamReceiver value)
   {
      if ((systemErrReceivers != null) && (value != null))
      {
         systemErrReceivers.remove(value);
      }
   }


   /**
    * The target's vm System.out stream poller.
    */
   private VMSystemOutPoller systemOutPoller;

   /**
    * The target's vm System.err stream poller.
    */
   private VMSystemErrPoller systemErrPoller;


   /**
    * <pre>
    *             traceDefinition   0..1 
    *  Tracer ---------------------------> TraceDefinition
    *                    traceDefinition 
    * </pre>
    */
   private TraceDefinition traceDefinition;


   public TraceDefinition getTraceDefinition()
   {
      return this.traceDefinition;
   }


   /**
    * <pre>
    *             launchConfiguration   0..1 
    *  Tracer -------------------------------> ILaunchConfiguration
    *                    launchConfiguration 
    * </pre>
    */
   private ILaunchConfiguration launchConfiguration;


   public ILaunchConfiguration getLaunchConfiguration()
   {
      return this.launchConfiguration;
   }


   /**
    * <pre>
    *         0..1   vmMonitor   0..1 
    * Tracer ------------------------- VMMonitor
    *         tracer        vMMonitor 
    * </pre>
    */
   private VMMonitor vMMonitor;


   public VMMonitor getVMMonitor()
   {
      return this.vMMonitor;
   }


   /* package */boolean setVMMonitor(final VMMonitor value)
   {
      boolean changed = false;
      if (this.vMMonitor != value)
      {
         final VMMonitor oldValue = this.vMMonitor;

         if (this.vMMonitor != null)
         {
            this.vMMonitor = null;
            oldValue.setTracer(null);
         }
         this.vMMonitor = value;
         if (value != null)
         {
            value.setTracer(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                                   0..n 
    * VMMonitor -----------------------------> IMonitoredEventsListener
    *                monitoredEventListeners 
    * </pre>
    */
   private HashSet<IMonitoredEventsListener> monitoredEventListeners;


   public void addMonitoredEventListener(final IMonitoredEventsListener listener)
   {
      if (listener != null)
      {
         if (this.monitoredEventListeners == null)
         {
            this.monitoredEventListeners = new HashSet<IMonitoredEventsListener>();
         }

         this.monitoredEventListeners.add(listener);
      }
   }


   public Iterator<IMonitoredEventsListener> iteratorOfMonitoredEventListeners()
   {
      return ((this.monitoredEventListeners == null) ? Collections.<IMonitoredEventsListener>emptyList().iterator()
            : this.monitoredEventListeners.iterator());
   }


   public void removeMonitoredEventListener(
         final IMonitoredEventsListener listener)
   {
      if ((this.monitoredEventListeners != null) && (listener != null))
      {
         this.monitoredEventListeners.remove(listener);
      }
   }


   private void fireVMStateChanged(final int state)
   {
      if (this.monitoredEventListeners != null)
      {
         final Iterator<IMonitoredEventsListener> iter = this.monitoredEventListeners.iterator();
         while (iter.hasNext())
         {
            final IMonitoredEventsListener listener = iter
                  .next();
            try
            {
               listener.vmStateChanged(state);
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


   private int vmState;


   public synchronized int getVMState()
   {
      return this.vmState;
   }


   /* package */synchronized void setVMState(final int state)
   {
      this.vmState = state;
      fireVMStateChanged(state);
   }


   @SuppressWarnings("unchecked")
   private void initializeListeners(final ILaunchConfiguration configuration)
   {
      for (final IMonitoredEventsListener listener : ExtensionPointsUtility
            .getMonitoredEventsListeners())
      {
         try
         {
            final String listenerClassName = listener.getClass().getName();
            final boolean enabled = configuration.getAttribute(
                  listenerClassName, false);
            boolean optional = ExtensionPointsUtility
                  .isListenerOptional(listenerClassName);

            if (!optional || (optional && enabled))
            {
               final Map<String, String> properties = configuration
                     .getAttribute(listenerClassName
                           + ITracerConstants.PROPERTIES,
                           new HashMap<String, String>());
               try
               {
                  final boolean initialized = listener.initialize(this,
                        properties);
                  if (initialized)
                  {
                     addMonitoredEventListener(listener);
                  }
                  else
                  {
                     System.err.println("IMonitoredEventsListener '"
                           + listenerClassName
                           + "' could not been initialized!");
                  }
               }
               catch (final Exception e)
               {
                  System.err.println("IMonitoredEventsListener '"
                        + listenerClassName
                        + "' caused exception during initialization!");
                  e.printStackTrace();
               }
            }
         }
         catch (final CoreException e)
         {
         }
      }
   }


   /**
    * Initializes the tracer by creating and attaching a VMMonitor to the given target virtual
    * machine.
    * 
    * @param virtualMachine the target virtual machine
    * @throws TracerStartException
    */
   public void initializeTracer(final VirtualMachine virtualMachine)
         throws TracerStartException
   {
      try
      {
         // attach output and error streams to stream pollers
         final Process process = virtualMachine.process();
         if (process != null)
         {
            // process is only available if VM is launched by tracer
            this.systemOutPoller = new VMSystemOutPoller(process
                  .getInputStream());
            this.systemErrPoller = new VMSystemErrPoller(process
                  .getErrorStream());
         }

         // create and attach a VMMonitor
         final VMMonitor vmMonitor = new VMMonitor(this.traceDefinition,
               this.launchConfiguration, virtualMachine);
         currentTracer.setVMMonitor(vmMonitor);

         currentTracer.setVMState(VM_INITIALIZED);
      }
      catch (final Exception e)
      {
         currentTracer.setVMState(VM_STOPPED);
         throw new TracerStartException("Could not initialize tracer: "
               + e.getMessage(), e);
      }
   }


   /**
    * Starts the tracing and resumes the target virtual machine.
    * 
    * @throws IllegalStateException if the tracer has not been initialized.
    * @throws TracerStartException if the vm monitor can not be started
    */
   public void startTracer() throws TracerStartException
   {
      if (getVMState() != VM_INITIALIZED)
      {
         throw new IllegalStateException("Tracer has not been initialized or "
               + "target virtual machine already running.");
      }

      try
      {
         // start monitoring thread
         getVMMonitor().start();

         // start vm streams poller threads
         if (this.systemOutPoller != null)
         {
            this.systemOutPoller.start();
         }
         if (this.systemErrPoller != null)
         {
            this.systemErrPoller.start();
         }

         // ok, let's resume the target vm
         getVMMonitor().getVirtualMachine().resume();
         setVMState(VM_RUNNING);
      }
      catch (final IllegalThreadStateException e)
      {
         currentTracer.setVMState(VM_STOPPED);
         throw new TracerStartException("Could not start VM monitor: "
               + e.getMessage(), e);
      }
   }


   /**
    * Suspends the target virtual machine.
    * 
    * @throws IllegalStateException if the target virtual machine is not running
    */
   public void suspendVM()
   {
      if (getVMState() != VM_RUNNING)
      {
         throw new IllegalStateException(
               "Target virtual machine is not running.");
      }

      getVMMonitor().getVirtualMachine().suspend();
      setVMState(VM_SUSPENDED);
   }


   /**
    * Resumes the target virtual machine
    * 
    * @throws IllegalStateException if the target virtual machine has not been suspended
    */
   public void resumeVM()
   {
      if (getVMState() != VM_SUSPENDED)
      {
         throw new IllegalStateException(
               "Target virtual machine has not been suspended.");
      }

      getVMMonitor().getVirtualMachine().resume();
      setVMState(VM_RUNNING);
   }


   /**
    * Stops the target virtual machine.
    * 
    * @throws IllegalStateException if the target virtual machine is not running
    */
   public void stopVM()
   {
      if (!(getVMState() == VM_RUNNING || getVMState() == VM_SUSPENDED))
      {
         throw new IllegalStateException(
               "Target virtual machine is not running.");
      }

      getVMMonitor().getVirtualMachine().exit(VM_EXIT_CODE);
      setVMState(VM_STOPPED);
   }


   private void removeYou()
   {
      final VMMonitor tmpVMMonitor = getVMMonitor();
      if (tmpVMMonitor != null)
      {
         setVMMonitor(null);
         tmpVMMonitor.removeYou();
      }

      this.traceDefinition.removeYou();
      this.traceDefinition = null;

      this.launchConfiguration = null;
   }

}
