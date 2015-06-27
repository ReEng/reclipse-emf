package org.reclipse.tracer.test.testsetup;

/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4060 $ $Date: 2009-05-18 17:38:33 +0200 (Mo, 18 Mai 2009) $
 */
public class NonMonitoredAbstactTracerTestImpl extends AbstractTracerTest
{

   /**
    * @see org.reclipse.tracer.test.testsetup.AbstractTracerTest#abstractMethod(short, long)
    */
   @Override
   public void abstractMethod(short i, long j)
   {
      System.out.println("NonMonitoredAbstactTracerTestImpl.abstractMethod()");
   }

}
