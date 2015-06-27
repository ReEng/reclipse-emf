package org.reclipse.tracer.test.testsetup;


/**
 * Test class for Reclipse Tracer tests
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2358 $ $Date: 2006-06-01 14:31:42 +0200 (Do, 01 Jun 2006) $
 */
public abstract class AbstractTracerTest
{
   public abstract void abstractMethod(short i, long j);


   public void overriddenMethod(boolean bool, Object object)
   {
      System.out.println("AbstractTracerTest.overriddenMethod()");
   }

}
