package org.reclipse.tracer.demo;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3752 $
 */
public class TracerTest
{

   public static final void main(final String[] args)
   {
      final String test = args[0];

      System.out.println(test);

      if ("Test1".equals(test))
      {
         runTest1();
      }
      else if ("Test2".equals(test))
      {
         runTest2();
      }
      else if ("Test3".equals(test))
      {
         runTest3();
      }
      else if ("Test4".equals(test))
      {
         runTest4();
      }
      else if ("Test5".equals(test))
      {
         runTest5();
      }
      else if ("Test6".equals(test))
      {
         runTest6();
      }
   }


   private static void runTest1()
   {
      final SimpleClass monitoredClass = new SimpleClass();
      monitoredClass.method1();
      monitoredClass.method2("test", 'x');
   }


   private static void runTest2()
   {
      final TracerTestInterfaceImpl monitoredClass = new TracerTestInterfaceImpl();
      monitoredClass.interfaceMethod(0, 0.5f);
      final byte b = 1;
      monitoredClass.ownMethod(b, 0.5);
   }


   private static void runTest3()
   {
      final AbstractTracerTestImpl monitoredClass = new AbstractTracerTestImpl();
      final short s = 10;
      monitoredClass.abstractMethod(s, 1000000000l);
      monitoredClass.overriddenMethod(true, monitoredClass);
   }


   private static void runTest4()
   {
      final AbstractTracerTestImpl monitoredClass = new AbstractTracerTestImpl();
      final short s = 10;
      monitoredClass.abstractMethod(s, 1000000000l);
      monitoredClass.overriddenMethod(true, monitoredClass);

      final NonMonitoredAbstactTracerTestImpl nonMonitoredClass = new NonMonitoredAbstactTracerTestImpl();
      nonMonitoredClass.abstractMethod(s, 1l);
      nonMonitoredClass.overriddenMethod(false, monitoredClass);
   }


   private static void runTest5()
   {
      final TracerTestInterfaceImpl monitoredClass = new TracerTestInterfaceImpl();
      monitoredClass.interfaceMethod(1, 0.5f);

      final Caller callerClass = new Caller();
      callerClass.delegation(monitoredClass);
   }


   private static void runTest6()
   {
      final AbstractTracerTestImpl monitoredClass = new AbstractTracerTestImpl();
      final short s = 1;
      monitoredClass.abstractMethod(s, 1l);

      final Caller callerClass = new Caller();
      callerClass.delegation(monitoredClass);
   }

}
