package org.reclipse.tracer.eventhandlers;


import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.VMMonitor;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.TraceDefinition;

import com.sun.jdi.ClassType;
import com.sun.jdi.InterfaceType;
import com.sun.jdi.Method;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.event.Event;
import com.sun.jdi.request.ClassPrepareRequest;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.EventRequestManager;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                                 traceDefinition     1 
 * AbstractMethodEventRequester -------------------------> TraceDefinition
 *                                       traceDefinition
 *                                        
 *                               1      methodEventHandler       1 
 * AbstractMethodEventHandler ------------------------------------- VMMonitor
 *                               methodEventHandler      vMMonitor 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public abstract class AbstractMethodEventHandler
{
   protected boolean methodExitEvents = ITracerConstants.DEFAULT_METHOD_EXIT_EVENTS;

   protected boolean ignoreJavaLangObject = ITracerConstants.DEFAULT_IGNORE_JAVA_LANG_OBJECT;

   protected boolean ignorePrivateMethods = ITracerConstants.DEFAULT_IGNORE_PRIVATE_METHODS;

   protected boolean ignoreConstructors = ITracerConstants.DEFAULT_IGNORE_CONSTRUCTORS;

   private final ILaunchConfiguration launchConfig;


   public AbstractMethodEventHandler(ILaunchConfiguration launchConfig)
   {
      this.launchConfig = launchConfig;

      initializeOptions();
   }


   private void initializeOptions()
   {
      try
      {
         this.methodExitEvents = this.launchConfig.getAttribute(
               ITracerConstants.METHOD_EXIT_EVENTS,
               ITracerConstants.DEFAULT_METHOD_EXIT_EVENTS);
         this.ignorePrivateMethods = this.launchConfig.getAttribute(
               ITracerConstants.IGNORE_PRIVATE_METHODS,
               ITracerConstants.DEFAULT_IGNORE_PRIVATE_METHODS);
         this.ignoreConstructors = this.launchConfig.getAttribute(
               ITracerConstants.IGNORE_CONSTRUCTORS,
               ITracerConstants.DEFAULT_IGNORE_CONSTRUCTORS);
         this.ignoreJavaLangObject = this.launchConfig.getAttribute(
               ITracerConstants.IGNORE_JAVA_LANG_OBJECT,
               ITracerConstants.DEFAULT_IGNORE_JAVA_LANG_OBJECT);
      }
      catch (CoreException e)
      {
      }
   }


   /**
    * <pre>
    *                                 traceDefinition     1 
    * AbstractMethodEventRequester -------------------------> TraceDefinition
    *                                       traceDefinition 
    * </pre>
    */
   private TraceDefinition traceDefinition;


   public TraceDefinition getTraceDefinition()
   {
      return this.traceDefinition;
   }


   public boolean setTraceDefinition(TraceDefinition value)
   {
      boolean changed = false;
      if (this.traceDefinition != value)
      {
         this.traceDefinition = value;
         changed = true;
      }
      return changed;
   }

   /**
    * <pre>
    *                               1      methodEventRequester       1 
    * AbstractMethodEventRequester ------------------------------------- VMMonitor
    *                               methodEventRequester      vMMonitor 
    * </pre>
    */
   private VMMonitor vMMonitor;


   public VMMonitor getVMMonitor()
   {
      return this.vMMonitor;
   }


   public boolean setVMMonitor(VMMonitor value)
   {
      boolean changed = false;
      if (this.vMMonitor != value)
      {
         VMMonitor oldValue = this.vMMonitor;

         if (this.vMMonitor != null)
         {
            this.vMMonitor = null;
            oldValue.setMethodEventHandler(null);
         }
         this.vMMonitor = value;
         if (value != null)
         {
            value.setMethodEventHandler(this);
         }
         changed = true;
      }
      return changed;
   }


   public void createClassPrepareRequests()
   {
      EventRequestManager eventRequestManager = getEventRequestManager();

      // TODO: also allow non-polymorphic tracing

      // add a request for all classes to monitor classes
      // that are derived from critical or consider classes
      ClassPrepareRequest classPrepareRequest = eventRequestManager
            .createClassPrepareRequest();
      classPrepareRequest.addClassFilter("*");
      classPrepareRequest.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD);
      classPrepareRequest.enable();
   }


   public abstract void handleVMEvent(Event event);


   private EventRequestManager eventRequestManager;


   public EventRequestManager getEventRequestManager()
   {
      if (this.eventRequestManager == null)
      {
         this.eventRequestManager = getVMMonitor().getVirtualMachine()
               .eventRequestManager();
      }

      return this.eventRequestManager;
   }


   @SuppressWarnings("unchecked")
   protected boolean isCriticalClass(ClassType loadedClass)
   {
      CriticalTrace criticalTrace = getTraceDefinition().getCriticalTrace();

      if (criticalTrace != null)
      {
         // check the class and its super classes
         ClassType superClassType = loadedClass;
         while (superClassType != null)
         {
            if (criticalTrace.hasKeyInClasses(superClassType.name()))
            {
               return true;
            }

            superClassType = superClassType.superclass();
         }

         // check all interfaces
         Iterator<InterfaceType> iter = loadedClass.allInterfaces().iterator();
         while (iter.hasNext())
         {
            InterfaceType interfaceType = iter.next();

            if (criticalTrace.hasKeyInClasses(interfaceType.name()))
            {
               return true;
            }
         }
      }

      return false;
   }


   @SuppressWarnings("unchecked")
   protected LinkedList<ReferenceType> getSuperTypes(ClassType classType)
   {
      LinkedList<ReferenceType> superTypes = new LinkedList<ReferenceType>();

      CriticalTrace criticalTrace = getTraceDefinition().getCriticalTrace();
      ConsiderTrace considerTrace = getTraceDefinition().getConsiderTrace();

      // check for super classes
      ClassType superClassType = classType.superclass();
      while (superClassType != null)
      {
         String superClassName = superClassType.name();

         if ((criticalTrace != null && criticalTrace
               .hasKeyInClasses(superClassName))
               || (considerTrace != null && considerTrace
                     .hasKeyInClasses(superClassName)))
         {
            superTypes.add(superClassType);
         }

         superClassType = superClassType.superclass();
      }

      // check for interfaces
      Iterator<InterfaceType> iter = classType.allInterfaces().iterator();
      while (iter.hasNext())
      {
         InterfaceType interfaceType = iter.next();
         String interfaceName = interfaceType.name();

         if ((criticalTrace != null && criticalTrace
               .hasKeyInClasses(interfaceName))
               || (considerTrace != null && considerTrace
                     .hasKeyInClasses(interfaceName)))
         {
            superTypes.add(interfaceType);
         }
      }

      return superTypes;
   }


   protected boolean ignoreCriticalMethod(Method method)
   {
      return (this.ignoreJavaLangObject && "java.lang.Object".equals(method
            .declaringType().name()))
            || (this.ignoreConstructors && method.isConstructor())
            || (this.ignorePrivateMethods && method.isPrivate());
   }


   public void removeYou()
   {
      TraceDefinition tmpTraceDefinition = getTraceDefinition();
      if (tmpTraceDefinition != null)
      {
         setTraceDefinition(null);
      }

      VMMonitor tmpVMMonitor = getVMMonitor();
      if (tmpVMMonitor != null)
      {
         setVMMonitor(null);
      }
   }

}
