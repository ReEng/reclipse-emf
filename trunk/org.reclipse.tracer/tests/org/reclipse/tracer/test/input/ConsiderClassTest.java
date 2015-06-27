package org.reclipse.tracer.test.input;


import org.reclipse.commons.tests.ReferencesTestCase;
import org.reclipse.tracer.model.definition.AbstractTrace;
import org.reclipse.tracer.model.definition.AbstractTraceClass;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.Parameter;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2599 $ $Date: 2006-07-29 03:29:34 +0200 (Sa, 29 Jul 2006) $
 */
public class ConsiderClassTest extends ReferencesTestCase
{

   /**
    * Test method for 'trace' reference
    */
   public void testTraceReference()
   {
      ConsiderClass considerClass = new ConsiderClass();
      considerClass.setName("org.reclipse.Test");

      setTargetClass(AbstractTraceClass.class);
      setTargetObject(considerClass);

      setPropertyClass(AbstractTrace.class);
      setPropertyName("trace");
      testToOneReference(new ConsiderTrace(), new ConsiderTrace());
   }


   /**
    * Test method for 'methods' reference
    */
   public void testMethodsReference()
   {
      ConsiderClass considerClass = new ConsiderClass();

      setTargetClass(ConsiderClass.class);
      setTargetObject(considerClass);

      setPropertyClass(ConsiderMethod.class);
      setPropertyName("methods");

      ConsiderMethod firstConsiderClass = new ConsiderMethod();
      firstConsiderClass.setName("methodA");

      ConsiderMethod secondConsiderClass = new ConsiderMethod();
      secondConsiderClass.setName("methodB");

      testToManyReference(firstConsiderClass, secondConsiderClass);
   }


   /**
    * Test method for 'org.reclipse.tracer.input.ConsiderClass.addToMethods(ConsiderMethod)'
    */
   public void testAddToMethodsConsiderMethod()
   {
      ConsiderClass considerClass = new ConsiderClass();

      // method without parameter
      ConsiderMethod expectedConsiderMethod = new ConsiderMethod();
      expectedConsiderMethod.setName("methodA");

      considerClass.addToMethods(expectedConsiderMethod);
      ConsiderMethod actualConsiderMethod = considerClass
            .getFromMethods(expectedConsiderMethod.getSignature());
      assertEquals(expectedConsiderMethod, actualConsiderMethod);

      // method with two parameter
      expectedConsiderMethod = new ConsiderMethod();
      expectedConsiderMethod.setName("methodB");

      Parameter firstParameter = new Parameter();
      firstParameter.setType("java.lang.Object");
      firstParameter.setMethod(expectedConsiderMethod);

      Parameter secondParameter = new Parameter();
      secondParameter.setType("java.lang.String");
      secondParameter.setMethod(expectedConsiderMethod);

      considerClass.addToMethods(expectedConsiderMethod);
      assertEquals(2, considerClass.sizeOfMethods());

      actualConsiderMethod = considerClass
            .getFromMethods(expectedConsiderMethod.getSignature());
      assertEquals(expectedConsiderMethod, actualConsiderMethod);
   }

}
