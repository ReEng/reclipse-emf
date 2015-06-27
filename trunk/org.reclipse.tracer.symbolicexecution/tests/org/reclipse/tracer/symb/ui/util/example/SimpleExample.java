package org.reclipse.tracer.symb.ui.util.example;


public class SimpleExample
{
   public static void main(String[] args)
   {
      symbolicMethod();
   }


   private static void symbolicMethod()
   {
      anotherMethod1();
      anotherMethod2();
      anotherMethod3();
   }


   private static void anotherMethod3()
   {
   }


   private static void anotherMethod2()
   {
      methodInTraceDef1();
   }


   private static void methodInTraceDef1()
   {
   }


   private static void anotherMethod1()
   {
      anotherMethod4();
   }


   private static void anotherMethod4()
   {
      methodInTraceDef2();
   }


   private static void methodInTraceDef2()
   {
      methodInTraceDef3();
   }


   private static void methodInTraceDef3()
   {
   }
}
