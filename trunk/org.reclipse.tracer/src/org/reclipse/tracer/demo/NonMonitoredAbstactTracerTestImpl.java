package org.reclipse.tracer.demo;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3752 $
 */
public class NonMonitoredAbstactTracerTestImpl extends AbstractTracerTest
{

   /**
    * @see org.reclipse.tracer.demo.AbstractTracerTest#abstractMethod(short, long)
    */
   @Override
   public void abstractMethod(final short i, final long j)
   {
      System.out.println("NonMonitoredAbstactTracerTestImpl.abstractMethod()");
   }

}
