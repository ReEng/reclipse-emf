package org.reclipse.behavior.inference.extensions;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.reclipse.behavior.inference.BehavioralAnalysis;
import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.extensionpoints.IMonitoredEventsListener;
import org.reclipse.tracer.model.tracegraph.TGArgument;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGObjectArgument;
import org.reclipse.tracer.model.tracegraph.TGType;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.ClassNotLoadedException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.Location;
import com.sun.jdi.Method;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.Type;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3308 $ $Date: 2007-01-23 11:45:46 +0100 (Di, 23 Jan 2007) $
 */
public class InferenceMonitoredEventsListener implements
      IMonitoredEventsListener
{

   private static final String ANNOTATIONS_FILE_NAME = "annotationsFileName";

   private static final String CATALOG_FILE_NAME = "catalogFileName";

   private static final String RESULTS_FILE_NAME = "resultsFileName";

   private static final String LOG_TRACES = "logTraces";


   private boolean initialized = false;

   private BehavioralAnalysis behavioralAnalysis;

   private Map<String, TGType> types;

   private Map<Long, TGObject> objects;

   private Map<String, TGObject> staticObjects;


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#initialize(org.reclipse.tracer.Tracer,
    *      java.util.Map)
    */
   public boolean initialize(final Tracer tracer,
         final Map<String, String> properties)
   {
      this.types = new HashMap<String, TGType>();
      this.objects = new HashMap<Long, TGObject>();
      this.staticObjects = new HashMap<String, TGObject>();

      this.behavioralAnalysis = new BehavioralAnalysis();
      this.behavioralAnalysis.setLogTraces(Boolean.valueOf(properties
            .get(LOG_TRACES)));

      this.initialized = this.behavioralAnalysis.initialize(properties
            .get(ANNOTATIONS_FILE_NAME), properties.get(CATALOG_FILE_NAME),
            properties.get(RESULTS_FILE_NAME));

      return this.initialized;
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IVMStateListener#vmStateChanged(int)
    */
   public void vmStateChanged(final int newVMState)
   {
      switch (newVMState)
      {
         case Tracer.VM_INITIALIZED:
            startInference();
            break;
         case Tracer.VM_STOPPED:
            terminateInference();
            break;
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmStart(com.sun.jdi.ThreadReference)
    */
   public void vmStart(final ThreadReference thread)
   {
      // nothing to do
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#classLoaded(com.sun.jdi.ReferenceType,
    *      java.util.List)
    */
   public void classLoaded(final ReferenceType type, final List superTypes)
   {
      final TGType tgType = getTGType(type);

      final Iterator iter = superTypes.iterator();
      while (iter.hasNext())
      {
         final ReferenceType superType = (ReferenceType) iter.next();
         final TGType superTGType = getTGType(superType);
         tgType.addToSuperTypes(superTGType);
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodEventRequestCreated(com.sun.jdi.Method)
    */
   public void methodEventRequestCreated(final Method method)
   {
      // nothing to do
   }


   private long methodCallIdCounter = 0;


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodEntry(com.sun.jdi.ThreadReference)
    */
   public void methodEntry(final ThreadReference thread)
   {
      try
      {
         final StackFrame frame = thread.frame(0);
         final Method method = frame.location().method();

         if (!method.isAbstract() && !method.isNative()
               && !method.isSynthetic() && !method.isStaticInitializer())
         {
            final String methodName = method.name();

            final TGMethodCall methodCall = new TGMethodCall();
            methodCall.setName(methodName);
            methodCall.setId(Long.toString(this.methodCallIdCounter++));

            // add argument types
            final Iterator iter = method.arguments().iterator();
            while (iter.hasNext())
            {
               final LocalVariable localVariable = (LocalVariable) iter.next();

               final TGArgument argument = new TGObjectArgument();
               final TGType argumentType = getTGType(localVariable.type());
               argument.setType(argumentType);

               methodCall.addToArguments(argument);
            }

            // add callee
            final TGObject callee = getTGObject(thread, 0);
            methodCall.setCallee(callee);

            // add caller
            final TGObject caller = getTGObject(thread, 1);
            methodCall.setCaller(caller);

            // enqueue method call
            this.behavioralAnalysis.enqueue(methodCall);
         }
      }
      catch (final IncompatibleThreadStateException exception)
      {
         BehavioralAnalysisPlugin.logError("Error creating TGMethodCall.",
               exception);
      }
      catch (final AbsentInformationException exception)
      {
         BehavioralAnalysisPlugin.logError("Error creating TGMethodCall.",
               exception);
      }
      catch (final ClassNotLoadedException exception)
      {
         BehavioralAnalysisPlugin.logError("Error creating TGMethodCall.",
               exception);
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
      // nothing to do
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#threadDeath(com.sun.jdi.ThreadReference)
    */
   public void threadDeath(final ThreadReference thread)
   {
      // nothing to do
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmDisconnect()
    */
   public void vmDisconnect()
   {
      this.types.clear();
      this.objects.clear();
      this.staticObjects.clear();
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmDeath()
    */
   public void vmDeath()
   {
      this.types.clear();
      this.objects.clear();
      this.staticObjects.clear();
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#tracerException(java.lang.Exception)
    */
   public void tracerException(final Exception exception)
   {
      this.types.clear();
      this.objects.clear();
      this.staticObjects.clear();
   }


   private void startInference()
   {
      if (this.initialized)
      {
         final Thread thread = new Thread(this.behavioralAnalysis);
         thread.setDaemon(false);
         thread.setName("Dynamic Inference");
         try
         {
            thread.start();
            Thread.sleep(500);
         }
         catch (final IllegalThreadStateException e)
         {
            e.printStackTrace();
         }
         catch (final InterruptedException e)
         {
         }
      }
   }


   private void terminateInference()
   {
      this.behavioralAnalysis.terminate();

      // wait for inference to finish
      while (this.behavioralAnalysis.getState() != BehavioralAnalysis.FINISHED)
      {
         try
         {
            Thread.sleep(500);
         }
         catch (final InterruptedException e)
         {
         }
      }

      this.behavioralAnalysis.removeYou();
   }


   private TGType getTGType(final Type type)
   {
      final String className = type.name();
      TGType tgType = this.types.get(className);
      if (tgType == null)
      {
         tgType = new TGType();
         tgType.setName(className);

         this.types.put(className, tgType);
      }

      return tgType;
   }


   private TGObject getTGObject(final ThreadReference thread,
         final int frameIndex)
   {
      TGObject tgObject = null;

      try
      {
         final StackFrame frame = thread.frame(frameIndex);
         final ObjectReference object = frame.thisObject();

         if (object == null)
         {
            // static method call
            final Method method = frame.location().method();
            final ReferenceType referenceType = method.declaringType();

            final String staticTypeName = referenceType.name();
            tgObject = this.staticObjects.get(staticTypeName);
            if (tgObject == null)
            {
               tgObject = new TGObject();
               tgObject.setId("<<static>>");

               final TGType tgType = getTGType(referenceType);
               tgObject.setType(tgType);

               this.staticObjects.put(staticTypeName, tgObject);
            }
         }
         else
         {
            tgObject = this.objects.get(object.uniqueID());
            if (tgObject == null)
            {
               tgObject = new TGObject();
               tgObject.setId(Long.toString(object.uniqueID()));

               final TGType tgType = getTGType(object.referenceType());
               tgObject.setType(tgType);

               this.objects.put(object.uniqueID(), tgObject);
            }
         }
      }
      catch (final IncompatibleThreadStateException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Error creating trace graph object.", e);
      }

      return tgObject;
   }

}
