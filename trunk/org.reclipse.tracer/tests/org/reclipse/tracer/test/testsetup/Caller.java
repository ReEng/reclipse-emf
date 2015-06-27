package org.reclipse.tracer.test.testsetup;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2358 $ $Date: 2006-06-01 14:31:42 +0200 (Do, 01 Jun 2006) $
 */
public class Caller
{
   public void delegation(ITracerTestInterface monitoredClass)
   {
      monitoredClass.interfaceMethod(2, 0.5f);
   }


   public void delegation(AbstractTracerTest monitoredClass)
   {
      short s = 2;
      monitoredClass.abstractMethod(s, 2l);
   }

}
