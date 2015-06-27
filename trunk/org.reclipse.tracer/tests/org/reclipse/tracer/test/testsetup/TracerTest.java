package org.reclipse.tracer.test.testsetup;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2358 $ $Date: 2006-06-01 14:31:42 +0200 (Do, 01 Jun 2006) $
 */
public class TracerTest
{

   public static final void main(String[] args)
   {
      String test = args[0];

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
      SimpleClass monitoredClass = new SimpleClass();
      monitoredClass.method1();
      monitoredClass.method2("test", 'x');
   }


   private static void runTest2()
   {
      TracerTestInterfaceImpl monitoredClass = new TracerTestInterfaceImpl();
      monitoredClass.interfaceMethod(0, 0.5f);
      byte b = 1;
      monitoredClass.ownMethod(b, 0.5);
   }


   private static void runTest3()
   {
      AbstractTracerTestImpl monitoredClass = new AbstractTracerTestImpl();
      short s = 10;
      monitoredClass.abstractMethod(s, 1000000000l);
      monitoredClass.overriddenMethod(true, monitoredClass);
   }


   private static void runTest4()
   {
      AbstractTracerTestImpl monitoredClass = new AbstractTracerTestImpl();
      short s = 10;
      monitoredClass.abstractMethod(s, 1000000000l);
      monitoredClass.overriddenMethod(true, monitoredClass);

      NonMonitoredAbstactTracerTestImpl nonMonitoredClass = new NonMonitoredAbstactTracerTestImpl();
      nonMonitoredClass.abstractMethod(s, 1l);
      nonMonitoredClass.overriddenMethod(false, monitoredClass);
   }


   private static void runTest5()
   {
      TracerTestInterfaceImpl monitoredClass = new TracerTestInterfaceImpl();
      monitoredClass.interfaceMethod(1, 0.5f);

      Caller callerClass = new Caller();
      callerClass.delegation(monitoredClass);
   }


   private static void runTest6()
   {
      AbstractTracerTestImpl monitoredClass = new AbstractTracerTestImpl();
      short s = 1;
      monitoredClass.abstractMethod(s, 1l);

      Caller callerClass = new Caller();
      callerClass.delegation(monitoredClass);
   }

}
