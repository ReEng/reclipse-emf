package org.reclipse.behavior.inference.extensions;


import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.reclipse.behavior.inference.BehavioralAnalysis;
import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.reclipse.tracer.extensionpoints.AbstractMethodCallListener;
import org.reclipse.tracer.model.tracegraph.TGArgument;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGObjectArgument;
import org.reclipse.tracer.model.tracegraph.TGType;
import org.reclipse.tracer.runtime.MethodCallObserver;


/**
 * @author mvdetten
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4348 $ $Date: 2010-05-21 15:46:06 +0200 (Fr, 21 Mai 2010) $
 */
public class InferenceMethodCallListener extends AbstractMethodCallListener
{

   public static final String ANNOTATIONS_FILE_NAME = "annotationsFileName";

   public static final String CATALOG_FILE_NAME = "catalogFileName";

   public static final String RESULTS_FILE_NAME = "resultsFileName";

   public static final String LOG_TRACES = "logTraces";


   private Map<String, TGType> types;

   private Map<Object, TGObject> objects;

   private Map<String, TGObject> staticObjects;

   private BehavioralAnalysis behavioralAnalysis;


   /**
    * @see org.reclipse.tracer.extensionpoints.IMethodCallListener#initialize()
    */
   public boolean initialize()
   {
      this.types = new HashMap<String, TGType>();
      this.objects = new WeakHashMap<Object, TGObject>();
      this.staticObjects = new HashMap<String, TGObject>();

      // configure BehavioralAnalysis
      this.behavioralAnalysis = new BehavioralAnalysis();
      final boolean initialized = this.behavioralAnalysis.initialize(
            getProperty(ANNOTATIONS_FILE_NAME), getProperty(CATALOG_FILE_NAME),
            getProperty(RESULTS_FILE_NAME));

      final boolean logTraces = Boolean.valueOf(getProperty(LOG_TRACES));
      this.behavioralAnalysis.setLogTraces(logTraces);

      // start BehavioralAnalysis
      if (initialized)
      {
         final Thread thread = new Thread(this.behavioralAnalysis);
         thread.setDaemon(false);
         thread.setName("Dynamic Inference");
         try
         {
            thread.start();
         }
         catch (final IllegalThreadStateException e)
         {
            e.printStackTrace();
            return false;
         }
      }

      return initialized;
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMethodCallListener#methodCalled(long,
    *      java.lang.Object, java.lang.Class, java.lang.Object, java.lang.Class, java.lang.String,
    *      java.lang.String[])
    */
   public void methodCalled(final long methodCallId, final Object caller,
         final Class staticCallerType, final Object callee,
         final Class staticCalleeType, final String methodName,
         final String... argumentTypes)
   {
      final TGMethodCall methodCall = new TGMethodCall();
      methodCall.setId(Long.toString(methodCallId));
      methodCall.setName(methodName);

      // add argument types
      for (final String argumentTypeName : argumentTypes)
      {
         final TGArgument argument = new TGObjectArgument();
         argument.setType(getTGType(argumentTypeName));
         methodCall.addToArguments(argument);
      }

      // add caller
      final TGObject tgCaller = getTGObject(caller, staticCallerType);
      methodCall.setCaller(tgCaller);

      // add callee
      final TGObject tgCallee = getTGObject(callee, staticCalleeType);
      methodCall.setCallee(tgCallee);

      this.behavioralAnalysis.enqueue(methodCall);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.AbstractMethodCallListener#terminate()
    */
   public void terminate()
   {
      // stop inference
      if (BehavioralAnalysisPlugin.LOG_INFO)
      {
         BehavioralAnalysisPlugin.logInfo("Stopping Dynamic Inference...");
      }

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

      if (BehavioralAnalysisPlugin.LOG_INFO)
      {
         BehavioralAnalysisPlugin.logInfo("...stopped");
      }
   }


   private TGObject getTGObject(final Object object, final Class staticType)
   {
      TGObject tgObject;
      if (object == MethodCallObserver.STATIC_OBJECT)
      {
         final String staticTypeName = staticType.getName();
         tgObject = this.staticObjects.get(staticTypeName);
         if (tgObject == null)
         {
            tgObject = new TGObject();
            tgObject.setId(staticTypeName);
            tgObject.setType(getTGType(staticType));

            this.staticObjects.put(staticTypeName, tgObject);
         }
      }
      else
      {
         tgObject = this.objects.get(object);
         if (tgObject == null)
         {
            tgObject = new TGObject();
            tgObject.setId(Long
                  .toString(MethodCallObserver.getObjectID(object)));
            tgObject.setType(getTGType(object.getClass()));

            this.objects.put(object, tgObject);
         }
      }

      return tgObject;
   }


   private TGType getTGType(final String typeName)
   {
      TGType tgType = this.types.get(typeName);
      if (tgType == null)
      {
         tgType = new TGType();
         tgType.setName(typeName);

         this.types.put(typeName, tgType);
      }

      return tgType;
   }


   private TGType getTGType(final Class type)
   {
      final String typeName = type.getName();

      TGType tgType = this.types.get(typeName);
      if (tgType == null)
      {
         // create type object
         tgType = new TGType();
         tgType.setName(typeName);

         this.types.put(typeName, tgType);
      }

      // the type may have been created by getTGType(String), be sure to compute hierarchy
      if (tgType.sizeOfSuperTypes() == 0)
      {
         // add super class to hierarchy
         final Class superType = type.getSuperclass();
         if (superType != null)
         {
            final TGType superTGType = getTGType(superType);
            tgType.addToSuperTypes(superTGType);
         }

         // add implemented interfaces to hierarchy
         for (final Class superInterface : type.getInterfaces())
         {
            final TGType superTGType = getTGType(superInterface);
            tgType.addToSuperTypes(superTGType);
         }
      }

      return tgType;
   }

}
