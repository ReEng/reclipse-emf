package org.reclipse.tracer.test.input;


import org.reclipse.commons.tests.ReferencesTestCase;
import org.reclipse.tracer.model.definition.AbstractTrace;
import org.reclipse.tracer.model.definition.AbstractTraceClass;
import org.reclipse.tracer.model.definition.CallerClass;
import org.reclipse.tracer.model.definition.CriticalClass;
import org.reclipse.tracer.model.definition.CriticalTrace;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2599 $ $Date: 2006-07-29 03:29:34 +0200 (Sa, 29 Jul 2006) $
 */
public class CriticalClassTest extends ReferencesTestCase
{

   /*
    * Test method for 'org.reclipse.tracer.input.CriticalClass.setTrace(AbstractTrace)'
    */
   public void testSetTrace()
   {
      CriticalClass criticalClass = new CriticalClass();
      criticalClass.setName("org.reclipse.Test");

      setTargetClass(AbstractTraceClass.class);
      setTargetObject(criticalClass);

      setPropertyClass(AbstractTrace.class);
      setPropertyName("trace");
      testToOneReference(new CriticalTrace(), new CriticalTrace());
   }


   /*
    * Test method for 'callers' reference
    */
   public void testCallersReference()
   {
      CriticalClass criticalClass = new CriticalClass();

      setTargetClass(CriticalClass.class);
      setTargetObject(criticalClass);

      setPropertyClass(CallerClass.class);
      setPropertyName("callers");

      CallerClass firstCallerClass = new CallerClass();
      firstCallerClass.setName("org.reclipse.test.CallerClassA");

      CallerClass secondCallerClass = new CallerClass();
      secondCallerClass.setName("org.reclipse.test.CallerClassB");

      testToManyReference(firstCallerClass, secondCallerClass);
   }

}
