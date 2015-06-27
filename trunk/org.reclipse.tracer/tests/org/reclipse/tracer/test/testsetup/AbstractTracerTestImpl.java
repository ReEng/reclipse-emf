package org.reclipse.tracer.test.testsetup;


/**
 * Test class for Reclipse Tracer tests
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4060 $ $Date: 2009-05-18 17:38:33 +0200 (Mo, 18 Mai 2009) $
 */
public class AbstractTracerTestImpl extends AbstractTracerTest
{

   /**
    * @see org.reclipse.tracer.test.testsetup.AbstractTracerTest#abstractMethod(short i, long j)
    */
   @Override
   public void abstractMethod(short i, long j)
   {
      System.out.println("AbstractTracerTestImpl.abstractMethod()");
   }


   /**
    * @see org.reclipse.tracer.test.testsetup.AbstractTracerTest#overriddenMethod(boolean, java.lang.Object)
    */
   @Override
   public void overriddenMethod(boolean bool, Object object)
   {
      System.out.println("AbstractTracerTestImpl.overriddenMethod()");
   }

}
