package org.reclipse.tracer.test.input;


import org.reclipse.commons.tests.ReferencesTestCase;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.Parameter;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2599 $ $Date: 2006-07-29 03:29:34 +0200 (Sa, 29 Jul 2006) $
 */
public class ConsiderMethodTest extends ReferencesTestCase
{

   /*
    * Test method for 'org.reclipse.tracer.input.ConsiderMethod.getSignature()'
    */
   public void testGetSignature()
   {
      // no parameters
      ConsiderMethod considerMethodA = new ConsiderMethod();
      considerMethodA.setName("methodA");
      assertEquals("methodA", considerMethodA.getName());

      assertEquals("methodA()", considerMethodA.getSignature());

      // two parameters
      ConsiderMethod considerMethodB = new ConsiderMethod();
      considerMethodB.setName("methodB");

      Parameter firstParameter = new Parameter();
      firstParameter.setType("java.lang.Object");
      assertEquals("java.lang.Object", firstParameter.getType());
      firstParameter.setMethod(considerMethodB);
      assertEquals(considerMethodB, firstParameter.getMethod());

      Parameter secondParameter = new Parameter();
      secondParameter.setType("java.lang.String");
      secondParameter.setMethod(considerMethodB);

      assertEquals("methodB(java.lang.Object, java.lang.String)",
            considerMethodB.getSignature());
   }


   /*
    * Test method for 'parameters' reference
    */
   public void testParametersReference()
   {
      ConsiderMethod considerMethod = new ConsiderMethod();

      setTargetClass(ConsiderMethod.class);
      setTargetObject(considerMethod);

      setPropertyClass(Parameter.class);
      setPropertyName("parameters");
      testToManyReference(new Parameter(), new Parameter());
   }

}
