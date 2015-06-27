package org.reclipse.tracer.test;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.exceptions.TracerStartException;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionReader;
import org.reclipse.tracer.model.tracegraph.TGArgument;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGTracePath;
import org.reclipse.tracer.model.tracegraph.xml.TraceGraphReader;
import org.reclipse.tracer.output.TracingLogger;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3613 $ $Date: 2007-05-10 15:45:37 +0200 (Do, 10 Mai 2007) $
 * 
 *          Fixme This test class does not work correctly. A tracer is set up to test the different
 *          tracing possibilities but the tracer is not initialized correctly, thus causing an
 *          exception. The initialization however requires setting up a virtual machine which is not
 *          done here.
 */
public class TracerRunTest extends TestCase
{
   private static final String PATH_PREFIX = "tests/org/reclipse/tracer/test/testsetup/";

   private static final String CRITICAL_ABSTRACT_CALLER_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "CriticalTracingOfAbstractClassWithCaller.xml";

   private static final String CRITICAL_INTERFACE_CALLER_TRACE_DEFINITION = PATH_PREFIX
         + "CriticalTracingOfInterfaceWithCaller.xml";

   private static final String CONSIDER_ABSTRACT_CALLER_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "ConsiderTracingOfAbstractClassWithCaller.xml";

   private static final String CONSIDER_INTERFACE_CALLER_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "ConsiderTracingOfInterfaceWithCaller.xml";

   private static final String CRITICAL_NONMONITORED_ABSTRACT_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "CriticalTracingOfAbstractClassWithNonMonitoredSubClass.xml";

   private static final String CRITICAL_ABSTRACT_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "CriticalTracingOfAbstractClass.xml";

   private static final String CONSIDER_ABSTRACT_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "ConsiderTracingOfAbstractClass.xml";

   private static final String CRITICAL_INTERFACE_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "CriticalTracingOfInterface.xml";

   private static final String CONSIDER_INTERFACE_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "ConsiderTracingOfInterface.xml";

   private static final String CRITICAL_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "CriticalTracing.xml";

   private static final String CONSIDER_TRACE_DEFINITION_PATH = PATH_PREFIX
         + "ConsiderTracing.xml";

   private static final String VM_CONFIGURATION_PATH = PATH_PREFIX
         + "VMConfiguration.launch";

   private static final String TRACE_RESULT_FILE_NAME = "ConsiderTracing.trace";


   public void testConsiderTracing()
   {
      String traceResultFileName = TRACE_RESULT_FILE_NAME;

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test1");

      TraceDefinition traceDefinition = loadTraceDefinition(CONSIDER_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(1, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall method1 = iter.next();
      assertMethodCall(method1, "method1",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.SimpleClass");
      assertArgumentTypes(method1, new String[] {});

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testCriticalTracing()
   {
      String traceResultFileName = "CriticalTracing.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test1");

      TraceDefinition traceDefinition = loadTraceDefinition(CRITICAL_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(3, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall constructor = iter.next();
      assertMethodCall(constructor, "<init>",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.SimpleClass");
      assertArgumentTypes(constructor, new String[] {});

      TGMethodCall method1 = iter.next();
      assertMethodCall(method1, "method1",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.SimpleClass");
      assertArgumentTypes(method1, new String[] {});

      TGMethodCall method2 = iter.next();
      assertMethodCall(method2, "method2",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.SimpleClass");
      assertArgumentTypes(method2, new String[] { "java.lang.String", "char" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testConsiderTracingOfInterface()
   {
      String traceResultFileName = "ConsiderTracingOfInterface.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test2");

      TraceDefinition traceDefinition = loadTraceDefinition(CONSIDER_INTERFACE_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(1, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "interfaceMethod",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.TracerTestInterfaceImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "int", "float" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testCriticalTracingOfInterface()
   {
      String traceResultFileName = "CriticalTracingOfInterface.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test2");

      TraceDefinition traceDefinition = loadTraceDefinition(CRITICAL_INTERFACE_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(3, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall constructor = iter.next();
      assertMethodCall(constructor, "<init>",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.TracerTestInterfaceImpl");
      assertArgumentTypes(constructor, new String[] {});

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "interfaceMethod",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.TracerTestInterfaceImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "int", "float" });

      TGMethodCall ownMethod = iter.next();
      assertMethodCall(ownMethod, "ownMethod",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.TracerTestInterfaceImpl");
      assertArgumentTypes(ownMethod, new String[] { "byte", "double" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testConsiderTracingOfAbstractClass()
   {
      String traceResultFileName = "ConsiderTracingOfAbstractClass.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test3");

      TraceDefinition traceDefinition = loadTraceDefinition(CONSIDER_ABSTRACT_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(1, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "abstractMethod",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "short", "long" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testCriticalTracingOfAbstractClass()
   {
      String traceResultFileName = "CriticalTracingOfAbstractClass.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test3");

      TraceDefinition traceDefinition = loadTraceDefinition(CRITICAL_ABSTRACT_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(4, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall constructor = iter.next();
      assertMethodCall(constructor, "<init>",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(constructor, new String[] {});

      constructor = iter.next();
      assertMethodCall(constructor, "<init>",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(constructor, new String[] {});

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "abstractMethod",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "short", "long" });

      TGMethodCall ownMethod = iter.next();
      assertMethodCall(ownMethod, "overriddenMethod",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(ownMethod, new String[] { "boolean",
            "java.lang.Object" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testCriticalTracingOfAbstractClassWithNonMonitoredSubClass()
   {
      String traceResultFileName = "CriticalTracingOfAbstractClassWithNonMonitoredSubClass.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test4");

      TraceDefinition traceDefinition = loadTraceDefinition(CRITICAL_NONMONITORED_ABSTRACT_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(4, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall constructor = iter.next();
      assertMethodCall(constructor, "<init>",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(constructor, new String[] {});

      constructor = iter.next();
      assertMethodCall(constructor, "<init>",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(constructor, new String[] {});

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "abstractMethod",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "short", "long" });

      TGMethodCall ownMethod = iter.next();
      assertMethodCall(ownMethod, "overriddenMethod",
            "org.reclipse.tracer.testsetup.TracerTest",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(ownMethod, new String[] { "boolean",
            "java.lang.Object" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testConsiderTracingOfInterfaceWithCaller()
   {
      String traceResultFileName = "ConsiderTracingOfInterfaceWithCaller.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test5");

      TraceDefinition traceDefinition = loadTraceDefinition(CONSIDER_INTERFACE_CALLER_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(1, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "interfaceMethod",
            "org.reclipse.tracer.testsetup.Caller",
            "org.reclipse.tracer.testsetup.TracerTestInterfaceImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "int", "float" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testConsiderTracingOfAbstractClassWithCaller()
   {
      String traceResultFileName = "ConsiderTracingOfAbstractClassWithCaller.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test6");

      TraceDefinition traceDefinition = loadTraceDefinition(CONSIDER_ABSTRACT_CALLER_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(1, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "abstractMethod",
            "org.reclipse.tracer.testsetup.Caller",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "short", "long" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testCriticalTracingOfInterfaceWithCaller()
   {
      String traceResultFileName = "CriticalTracingOfInterfaceWithCaller.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test5");

      TraceDefinition traceDefinition = loadTraceDefinition(CRITICAL_INTERFACE_CALLER_TRACE_DEFINITION);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(1, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "interfaceMethod",
            "org.reclipse.tracer.testsetup.Caller",
            "org.reclipse.tracer.testsetup.TracerTestInterfaceImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "int", "float" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   public void testCriticalTracingOfAbstractClassWithCaller()
   {
      String traceResultFileName = "CriticalTracingOfAbstractClassWithCaller.trace";

      // prepare the tracer input
      ILaunchConfiguration launchConfig = loadLaunchConfiguration(VM_CONFIGURATION_PATH);
      setTraceResultFileName(launchConfig, traceResultFileName);
      setProgramArguments(launchConfig, "Test6");

      TraceDefinition traceDefinition = loadTraceDefinition(CRITICAL_ABSTRACT_CALLER_TRACE_DEFINITION_PATH);

      // start the tracer
      Tracer tracer = startTracer(traceDefinition, launchConfig);
      assertNotNull(tracer);

      // wait for tracer to finish
      synchronizeWithTracer(tracer);

      // check the trace graph
      TGTracePath tracePath = TraceGraphReader.load(traceResultFileName).iteratorOfTracePaths().next();
      assertNotNull(tracePath);

      assertEquals(1, tracePath.sizeOfMethodCalls());

      Iterator<TGMethodCall> iter = tracePath.iteratorOfMethodCalls();

      TGMethodCall interfaceMethod = iter.next();
      assertMethodCall(interfaceMethod, "abstractMethod",
            "org.reclipse.tracer.testsetup.Caller",
            "org.reclipse.tracer.testsetup.AbstractTracerTestImpl");
      assertArgumentTypes(interfaceMethod, new String[] { "short", "long" });

      // delete tracer output file
      deleteFile(traceResultFileName);
   }


   private void setTraceResultFileName(ILaunchConfiguration launchConfig,
         String traceResultFileName)
   {
   }


   private void setProgramArguments(ILaunchConfiguration launchConfig,
         String string)
   {
   }


   private Tracer startTracer(TraceDefinition traceDefinition,
         ILaunchConfiguration launchConfig)
   {
      Tracer tracer = null;
      try
      {
         tracer = Tracer.createTracer(traceDefinition, launchConfig);
         tracer.addMonitoredEventListener(new TracingLogger());
         tracer.startTracer();
      }
      catch (TracerStartException e)
      {
         e.printStackTrace();
         fail("Tracer could not be started: " + e.getMessage());
      }

      return tracer;
   }


   private void synchronizeWithTracer(Tracer tracer)
   {
      while (tracer.getVMState() != Tracer.VM_STOPPED)
      {
         try
         {
            Thread.sleep(300);
         }
         catch (InterruptedException e)
         {
         }
      }
   }


   private TraceDefinition loadTraceDefinition(String traceDefinitionFileName)
   {
      File traceDefinitionFile = new File(traceDefinitionFileName);
      TraceDefinition traceDefinition = null;
      try
      {
         traceDefinition = TraceDefinitionReader.load(traceDefinitionFile);
      }
      catch (FileNotFoundException e)
      {
         fail("Tracer input could not be loaded: " + e.getMessage());
      }

      return traceDefinition;
   }


   private ILaunchConfiguration loadLaunchConfiguration(String vmConfigFileName)
   {
      File vmConfigFile = new File(vmConfigFileName);
      ILaunchConfiguration launchConfig = null;
      try
      {
         launchConfig = LaunchConfiguration.load(vmConfigFile);
      }
      catch (TracerStartException e)
      {
         fail("VM configuration could not be loaded: " + e.getMessage());
      }

      return launchConfig;
   }


   private void assertMethodCall(TGMethodCall methodCall, String methodName,
         String callerType, String calleeType)
   {
      assertEquals(methodName, methodCall.getName());

      TGObject caller = methodCall.getCaller();
      assertNotNull(caller);
      assertTrue(caller.isInstanceOf(callerType));

      TGObject callee = methodCall.getCallee();
      assertNotNull(callee);
      assertTrue(callee.isInstanceOf(calleeType));
   }


   private void assertArgumentTypes(TGMethodCall methodCall,
         String[] argumentTypes)
   {
      assertEquals(argumentTypes.length, methodCall.sizeOfArguments());

      int i = 0;
      Iterator<TGArgument> iter = methodCall.iteratorOfArguments();
      while (iter.hasNext())
      {
         TGArgument argument = iter.next();
         assertNotNull(argument.getType());
         assertEquals(argumentTypes[i], argument.getType().getName());
         i++;
      }
   }


   private void deleteFile(String fileName)
   {
      File file = new File(fileName);
      file.delete();
   }

}
