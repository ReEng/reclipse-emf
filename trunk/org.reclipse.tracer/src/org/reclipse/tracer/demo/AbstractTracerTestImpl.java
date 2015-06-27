package org.reclipse.tracer.demo;


/**
 * Test class for Reclipse Tracer tests
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3752 $
 */
public class AbstractTracerTestImpl extends AbstractTracerTest
{

   /**
    * @see org.reclipse.tracer.demo.AbstractTracerTest#abstractMethod(short i, long j)
    */
   @Override
   public void abstractMethod(final short i, final long j)
   {
      System.out.println("AbstractTracerTestImpl.abstractMethod()");
   }


   @Override
   public void overriddenMethod(final boolean bool, final Object object)
   {
      System.out.println("AbstractTracerTestImpl.overriddenMethod()");
   }

}
