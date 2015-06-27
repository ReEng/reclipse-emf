package org.reclipse.tracer.test.testsetup;


/**
 * Test class for Reclipse Tracer tests
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2358 $ $Date: 2006-06-01 14:31:42 +0200 (Do, 01 Jun 2006) $
 */
public class TracerTestInterfaceImpl implements ITracerTestInterface
{

   /**
    * @see org.reclipse.tracer.test.testsetup.ITracerTestInterface#interfaceMethod(int, float)
    */
   public void interfaceMethod(int i, float x)
   {
      System.out.println("TracerTestInterfaceImpl.interfaceMethod(" + i + " ,"
            + x + ")");
   }


   public void ownMethod(byte b, double x)
   {
   }

}
