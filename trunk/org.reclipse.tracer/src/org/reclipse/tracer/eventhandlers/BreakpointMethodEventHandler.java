package org.reclipse.tracer.eventhandlers;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.reclipse.tracer.TracerPlugin;
import org.reclipse.tracer.exceptions.TracerException;
import org.reclipse.tracer.model.definition.CallerClass;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalClass;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.Parameter;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.ClassType;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.InterfaceType;
import com.sun.jdi.Location;
import com.sun.jdi.Method;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.event.BreakpointEvent;
import com.sun.jdi.event.ClassPrepareEvent;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.MethodEntryEvent;
import com.sun.jdi.event.MethodExitEvent;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.EventRequestManager;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class BreakpointMethodEventHandler extends AbstractMethodEventHandler
{
   private static final String BREAKPOINT_TYPE = "BreakpointType";

   private static final String ENTRY_BREAKPOINT = "MethodEntry";

   private static final String EXIT_BREAKPOINT = "MethodExit";

   private static final String ENTRY_AND_EXIT_BREAKPOINT = "MethodEntryAndExit";


   /**
    * Used in cache for monitored classes.
    */
   private static final String CRITICAL_CLASS = "CriticalClass";

   /**
    * Used in cache for monitored classes.
    */
   private static final String CONSIDER_CLASS = "ConsiderClass";

   private static final Object CONSTRUCTOR_NAME = "<init>";


   /**
    * Cache for monitored classes: key = ReferenceType referenceType value = CRITICAL_CLASS or
    * CONSIDER_CLASS
    */
   private HashMap<ReferenceType, String> monitoredClasses = new HashMap<ReferenceType, String>();

   /**
    * Cache for monitored methods
    */
   private HashSet<Method> monitoredMethods = new HashSet<Method>();

   /**
    * Cache for method signatures: key = Method method value = String signature
    */
   private HashMap<Method, String> methodSignatures = new HashMap<Method, String>();


   public BreakpointMethodEventHandler(final ILaunchConfiguration launchConfig)
   {
      super(launchConfig);
   }


   /**
    * Strategy interface for handling method events.
    * 
    * @param event the VM event to handle
    * @see org.reclipse.tracer.eventhandlers.AbstractMethodEventHandler#handleVMEvent(com.sun.jdi.event.Event)
    */
   @Override
   public void handleVMEvent(final Event event)
   {
      if (event instanceof BreakpointEvent)
      {
         handleBreakpointEvent((BreakpointEvent) event);
      }
      else if (event instanceof ClassPrepareEvent)
      {
         handleClassPrepareEvent((ClassPrepareEvent) event);
      }
      else if (event instanceof MethodEntryEvent
            || event instanceof MethodExitEvent)
      {
         // do nothing, ignore event
      }
   }


   private void handleBreakpointEvent(final BreakpointEvent event)
   {
      if (!filterEvent(event))
      {
         final ThreadReference thread = event.thread();
         final EventRequest eventRequest = event.request();

         final String breakpointType = (String) eventRequest
               .getProperty(BREAKPOINT_TYPE);
         if (breakpointType.equals(ENTRY_BREAKPOINT))
         {
            getVMMonitor().fireMethodEntry(thread);
         }
         else if (breakpointType.equals(EXIT_BREAKPOINT))
         {
            getVMMonitor().fireMethodExit(thread);
         }
         else if (breakpointType.equals(ENTRY_AND_EXIT_BREAKPOINT))
         {
            getVMMonitor().fireMethodEntry(thread);
            getVMMonitor().fireMethodExit(thread);
         }
      }
   }


   /**
    * Filters some breakpoint events, but if there is any doubt, return true.
    * 
    * Check the caller type. The actual type may not need to be monitored, since the executed method
    * may be in a super class and may be monitored for another sub class.
    * 
    * Check the callee type. Some critical classes or consider methods are monitored for certain
    * caller classes only.
    * 
    * @param event the breakpoint event
    * @return false, if the callee has to be monitored, true otherwise
    */
   private boolean filterEvent(final BreakpointEvent event)
   {
      final ThreadReference thread = event.thread();
      StackFrame calleeFrame;
      StackFrame callerFrame;
      try
      {
         calleeFrame = thread.frame(0);
         callerFrame = thread.frame(1);
      }
      catch (final IncompatibleThreadStateException e)
      {
         // in dubio pro reo...
         return false;
      }

      // get callee type and method
      final ReferenceType calleeType = getReferenceType(calleeFrame);
      final Method calleeMethod = calleeFrame.location().method();

      // get caller type
      final ReferenceType callerType = getReferenceType(callerFrame);

      // check, if class is monitored at all
      final String traceMode = this.monitoredClasses.get(calleeType);
      if (traceMode != null)
      {
         if (traceMode == CRITICAL_CLASS)
         {
            return filterEventForCriticalClass(calleeType, callerType);
         }
         else if (traceMode == CONSIDER_CLASS)
         {
            return filterEventForConsiderClass(calleeType, calleeMethod,
                  callerType);
         }
      }

      // the called method belongs to a super class
      // that is monitored for another sub class
      return true;
   }


   /**
    * Filter event if there is no critical class in the hierarchy of the calleeType with the
    * following criteria: 1. It has no callers specified 2. It has the callerType defined as caller
    * 
    * @return true, if event is filtered due to the given criteria
    */
   @SuppressWarnings("unchecked")
   private boolean filterEventForCriticalClass(final ReferenceType calleeType,
         final ReferenceType callerType)
   {
      final CriticalTrace criticalTrace = getTraceDefinition()
            .getCriticalTrace();

      // TODO could calleeType also be an ArrayType?
      final ClassType calleeClassType = (ClassType) calleeType;

      // check all super types
      ClassType superClassType = calleeClassType;
      while (superClassType != null)
      {
         final CriticalClass criticalClass = criticalTrace
               .getFromClasses(superClassType.name());

         if (criticalClass != null)
         {
            if (criticalClass.sizeOfCallers() == 0)
            {
               // there is at least one critical class in the hierarchy
               // that should be monitored for all callers => do not filter
               return false;
            }
            else
            {
               if (criticalClass.hasKeyInCallers(callerType.name()))
               {
                  // there is a critical class with the callerType as caller
                  return false;
               }
            }
         }

         superClassType = superClassType.superclass();
      }

      // check all interfaces
      final Iterator<InterfaceType> iter = calleeClassType.allInterfaces().iterator();
      while (iter.hasNext())
      {
         final InterfaceType interfaceType = iter.next();
         final CriticalClass criticalClass = criticalTrace
               .getFromClasses(interfaceType.name());

         if (criticalClass != null)
         {
            if (criticalClass.sizeOfCallers() == 0)
            {
               // there is at least one critical class in the hierarchy
               // that should be monitored for all callers => do not filter
               return false;
            }
            else
            {
               if (criticalClass.hasKeyInCallers(callerType.name()))
               {
                  // there is a critical class with the callerType as caller
                  return false;
               }
            }
         }
      }

      return true;
   }


   @SuppressWarnings("unchecked")
   private boolean filterEventForConsiderClass(final ReferenceType calleeType,
         final Method calleeMethod, final ReferenceType callerType)
   {
      final ConsiderTrace considerTrace = getTraceDefinition()
            .getConsiderTrace();
      String signature = getSignature(calleeMethod);

      // TODO could calleeType also be an ArrayType?
      final ClassType calleeClassType = (ClassType) calleeType;

      // check super types
      ClassType superClassType = calleeClassType;
      while (superClassType != null)
      {
         final ConsiderClass considerClass = considerTrace
               .getFromClasses(superClassType.name());
         
         if (considerClass != null && considerClass.hasKeyInMethods(signature))
         {
            final ConsiderMethod considerMethod = considerClass
                  .getFromMethods(signature);

            if (considerMethod.sizeOfCallers() == 0)
            {
               // no caller specified, do not filter
               return false;
            }
            else
            {
               if (considerMethod.hasKeyInCallers(callerType.name()))
               {
                  // caller is specified, callerType fits caller, do not filter
                  return false;
               }
            }
         }

         superClassType = superClassType.superclass();
      }

      // check interfaces
      final Iterator<InterfaceType> iter = calleeClassType.allInterfaces().iterator();
      while (iter.hasNext())
      {
         final InterfaceType interfaceType = iter.next();
         final ConsiderClass considerClass = considerTrace
               .getFromClasses(interfaceType.name());

         if (considerClass != null && considerClass.hasKeyInMethods(signature))
         {
            final ConsiderMethod considerMethod = considerClass
                  .getFromMethods(signature);

            if (considerMethod.sizeOfCallers() == 0)
            {
               // no caller specified, do not filter
               return false;
            }
            else
            {
               final Iterator<CallerClass> callersIter = considerMethod.iteratorOfCallers();
               while (callersIter.hasNext())
               {
                  final CallerClass callerClass = callersIter
                        .next();
                  if (callerClass.getName().equals(callerType.name()))
                  {
                     // caller is specified, callerType fits caller, do not filter
                     return false;
                  }
               }
            }
         }
      }

      return true;
   }


   private void handleClassPrepareEvent(final ClassPrepareEvent event)
   {
      final ReferenceType referenceType = event.referenceType();

      // TODO could also be an InterfaceType or ArrayType?
      if (referenceType instanceof ClassType)
      {
         boolean monitored = false;
         final ClassType loadedClass = (ClassType) referenceType;

         // if loadedClass is critical, add breakpoints to all methods
         if (isCriticalClass(loadedClass))
         {
            monitored = createCriticalBreakpoints(loadedClass);
            if (monitored)
            {
               this.monitoredClasses.put(loadedClass, CRITICAL_CLASS);
               getVMMonitor().fireClassLoaded(loadedClass,
                     getSuperTypes(loadedClass));
            }
         }
         else
         {
            monitored = createConsiderBreakpoints(loadedClass);
            if (monitored)
            {
               this.monitoredClasses.put(loadedClass, CONSIDER_CLASS);
               getVMMonitor().fireClassLoaded(loadedClass,
                     getSuperTypes(loadedClass));
            }
         }
      }
   }


   @SuppressWarnings("unchecked")
   private boolean createCriticalBreakpoints(final ClassType loadedClass)
   {
      boolean monitored = false;

      final List<Method> methods = loadedClass.allMethods();
      if (methods != null)
      {
         final Iterator<Method> iter = methods.iterator();
         while (iter.hasNext())
         {
            final Method method = iter.next();
            if (!method.isAbstract() && !method.isNative()
                  && !ignoreCriticalMethod(method))
            {
               createMethodBreakpoints(method);
               monitored = true;
            }
         }
      }

      return monitored;
   }


   @SuppressWarnings("unchecked")
   private boolean createConsiderBreakpoints(final ClassType loadedClass)
   {
      final ConsiderTrace considerTrace = getTraceDefinition()
            .getConsiderTrace();
      boolean monitored = false;

      if (considerTrace != null)
      {
         // just add breakpoints to methods that have to be monitored
         // check for super classes
         ClassType superClassType = loadedClass;
         while (superClassType != null)
         {
            final String superClassName = superClassType.name();

            if (considerTrace.hasKeyInClasses(superClassName))
            {
               createConsiderBreakpoints(loadedClass, superClassType);
               monitored = true;
            }

            superClassType = superClassType.superclass();
         }

         // check for interfaces
         final Iterator<InterfaceType> iter = loadedClass.allInterfaces().iterator();
         while (iter.hasNext())
         {
            final InterfaceType interfaceType = iter.next();
            final String interfaceName = interfaceType.name();

            if (considerTrace.hasKeyInClasses(interfaceName))
            {
               createConsiderBreakpoints(loadedClass, interfaceType);
               monitored = true;
            }
         }
      }

      return monitored;
   }


   @SuppressWarnings("unchecked")
   private void createConsiderBreakpoints(final ClassType loadedClass,
         final ReferenceType superClassType)
   {
      final ConsiderTrace considerTrace = getTraceDefinition()
            .getConsiderTrace();
      final ConsiderClass considerClass = considerTrace
            .getFromClasses(superClassType.name());

      final Iterator<ConsiderMethod> iter = considerClass.iteratorOfMethods();
      while (iter.hasNext())
      {
         final ConsiderMethod considerMethod = iter.next();
         final List<Method> methodsList = loadedClass.allMethods();
         boolean methodFound = false;
         for (final Method method : methodsList)
         {
            // System.out.println(" " + method.name() + method.signature());
            if (nameMatches(considerMethod, method)
                  && signatureMatches(considerMethod, method))
            {
               createMethodBreakpoints(method);
               methodFound = true;
               break;
            }
         }
         if (!methodFound)
         {
            TracerPlugin.logWarning("Method '" + considerClass.getName() + "."
                  + considerMethod.getSignature() + "' not found in class '"
                  + loadedClass.name() + "'.");
         }
      }
   }


   private boolean nameMatches(final ConsiderMethod considerMethod,
         final Method method)
   {
      if (considerMethod.isConstructor())
      {
         return CONSTRUCTOR_NAME.equals(method.name());
      }
      else
      {
         return method.name().equals(considerMethod.getName());
      }
   }


   @SuppressWarnings("unchecked")
   private boolean signatureMatches(final ConsiderMethod considerMethod,
         final Method method)
   {
      final List<String> argumentTypesList = method.argumentTypeNames();
      final List<Parameter> consParamsList = considerMethod.valuesOfParameters();

      // check, if number of parameters equals
      if (argumentTypesList.size() != consParamsList.size())
      {
         return false;
      }

      // check types of params
      for (int i = 0; i < argumentTypesList.size(); i++)
      {
         final String argumentType = argumentTypesList.get(i);
         final Parameter consParam = consParamsList.get(i);
         if (!argumentType.equals(consParam.getType()))
         {
            return false;
         }
      }

      return true;
   }


   @SuppressWarnings("unchecked")
   private void createMethodBreakpoints(final Method method)
   {
      if (this.monitoredMethods.contains(method))
      {
         // method is already monitored
         return;
      }

      try
      {
         final List<Location> locations = method.allLineLocations();
         if (locations.size() > 0)
         {
            Location location = locations.get(0);
            final int entry = location.lineNumber();
            final BreakpointRequest entryBreakpointRequest = createBreakpoint(
                  location, ENTRY_BREAKPOINT);

            if (this.methodExitEvents)
            {
               location = locations.get(locations.size() - 1);
               final int exit = location.lineNumber();

               if (exit == entry)
               {
                  entryBreakpointRequest.putProperty(BREAKPOINT_TYPE,
                        ENTRY_AND_EXIT_BREAKPOINT);
               }
               else
               {
                  createBreakpoint(location, EXIT_BREAKPOINT);
               }
            }

            this.monitoredMethods.add(method);
            getVMMonitor().fireMethodEventRequestCreated(method);
         }
      }
      catch (final AbsentInformationException e)
      {
         throw new TracerException("Cannot get line locations for method " + method.name()+ ". " + e.getMessage(), e);
      }
   }


   /**
    * Creates a breakpoint of the given type at the given location.
    * 
    * @param location the location to add a breakpoint to
    * @param breakpointType ENTRY_BREAKPOINT or EXIT_BREAKPOINT
    * @return a BreakpointRequest
    */
   private BreakpointRequest createBreakpoint(final Location location,
         final String breakpointType)
   {
      final EventRequestManager eventRequestManager = getEventRequestManager();

      final BreakpointRequest breakpointRequest = eventRequestManager
            .createBreakpointRequest(location);
      breakpointRequest.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD);
      breakpointRequest.putProperty(BREAKPOINT_TYPE, breakpointType);
      breakpointRequest.enable();

      return breakpointRequest;
   }


   private ReferenceType getReferenceType(final StackFrame stackFrame)
   {
      ReferenceType referenceType;
      final ObjectReference thisOject = stackFrame.thisObject();
      if (thisOject != null)
      {
         referenceType = thisOject.referenceType();
      }
      else
      {
         final Method method = stackFrame.location().method();
         referenceType = method.declaringType();
      }

      return referenceType;
   }


   /**
    * Returns the cached signature for the given method
    * 
    * @param method the method to get the signature for
    * @return the signature of the given method
    */
   @SuppressWarnings("unchecked")
   private String getSignature(final Method method)
   {
      String signature = this.methodSignatures.get(method);

      if (signature == null)
      {
         final StringBuffer buffer = new StringBuffer();
         buffer.append(method.name());
         buffer.append("(");

         final Iterator<String> paramsIter = method.argumentTypeNames().iterator();
         while (paramsIter.hasNext())
         {
            buffer.append(paramsIter.next());

            if (paramsIter.hasNext())
            {
               buffer.append(", ");
            }
         }

         buffer.append(")");

         signature = buffer.toString();
         this.methodSignatures.put(method, signature);
      }

      return signature;
   }

}
