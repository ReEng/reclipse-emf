package org.reclipse.tracer.demo;


/**
 * Test class for Reclipse Tracer tests
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3752 $
 */
public class TracerTestInterfaceImpl implements ITracerTestInterface
{

   public void interfaceMethod(final int i, final float x)
   {
      System.out.println("TracerTestInterfaceImpl.interfaceMethod(" + i + " ,"
            + x + ")");
   }


   public void ownMethod(final byte b, final double x)
   {
   }

}
