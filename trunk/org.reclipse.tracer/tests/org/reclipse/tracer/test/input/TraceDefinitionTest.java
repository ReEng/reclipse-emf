package org.reclipse.tracer.test.input;


import org.reclipse.commons.tests.ReferencesTestCase;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.TraceDefinition;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2599 $ $Date: 2006-07-29 03:29:34 +0200 (Sa, 29 Jul 2006) $
 */
public class TraceDefinitionTest extends ReferencesTestCase
{

   /**
    * @see junit.framework.TestCase#setUp()
    */
   @Override
   protected void setUp() throws Exception
   {
      super.setUp();

      setTargetClass(TraceDefinition.class);
      setTargetObject(new TraceDefinition());
   }


   /**
    * @see junit.framework.TestCase#tearDown()
    */
   @Override
   protected void tearDown() throws Exception
   {
      super.tearDown();

      setTargetClass(null);
      setTargetObject(null);
   }


   /*
    * Test method for 'org.reclipse.tracer.input.TraceDefinition.setConsiderTrace(ConsiderTrace)'
    */
   public void testSetConsiderTrace()
   {
      setPropertyClass(ConsiderTrace.class);
      setPropertyName("considerTrace");
      testToOneReference(new ConsiderTrace(), new ConsiderTrace());
   }


   /*
    * Test method for 'org.reclipse.tracer.input.TraceDefinition.setCriticalTrace(CriticalTrace)'
    */
   public void testSetCriticalTrace()
   {
      setPropertyClass(CriticalTrace.class);
      setPropertyName("criticalTrace");
      testToOneReference(new CriticalTrace(), new CriticalTrace());
   }

}
