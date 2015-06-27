package org.reclipse.tracer.demo;


/**
 * Test class for Reclipse Tracer tests
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3752 $
 */
public abstract class AbstractTracerTest
{

   public abstract void abstractMethod(short i, long j);


   public void overriddenMethod(final boolean bool, final Object object)
   {
      System.out.println("AbstractTracerTest.overriddenMethod()");
   }

}
