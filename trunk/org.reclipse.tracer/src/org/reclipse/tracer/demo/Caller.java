package org.reclipse.tracer.demo;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3752 $
 * 
 */
public class Caller
{

   public void delegation(final ITracerTestInterface monitoredClass)
   {
      monitoredClass.interfaceMethod(2, 0.5f);
   }


   public void delegation(final AbstractTracerTest monitoredClass)
   {
      final short s = 2;
      monitoredClass.abstractMethod(s, 2l);
   }

}
