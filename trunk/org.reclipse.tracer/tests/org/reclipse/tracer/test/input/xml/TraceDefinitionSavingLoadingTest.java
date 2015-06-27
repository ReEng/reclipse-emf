package org.reclipse.tracer.test.input.xml;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import junit.framework.TestCase;

import org.reclipse.tracer.model.definition.CallerClass;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalClass;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.Parameter;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionReader;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionWriter;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3593 $ $Date: 2007-04-12 13:59:28 +0200 (Do, 12 Apr 2007) $
 */
public class TraceDefinitionSavingLoadingTest extends TestCase
{

   private TraceDefinition traceDefinition;


   /**
    * @see junit.framework.TestCase#setUp()
    */
   @Override
   protected void setUp() throws Exception
   {
      super.setUp();

      traceDefinition = new TraceDefinition();

      CriticalTrace criticalTrace = new CriticalTrace();
      traceDefinition.setCriticalTrace(criticalTrace);

      CriticalClass criticalClass = new CriticalClass();
      criticalClass.setName("org.reclipse.CriticalClassA");
      criticalTrace.addToClasses(criticalClass);

      criticalClass = new CriticalClass();
      criticalClass.setName("org.reclipse.CriticalClassB");
      criticalTrace.addToClasses(criticalClass);

      CallerClass callerClassA = new CallerClass();
      callerClassA.setName("org.reclipse.CallerClassA");
      criticalClass.addToCallers(callerClassA);

      CallerClass callerClassB = new CallerClass();
      callerClassB.setName("org.reclipse.CallerClassB");
      criticalClass.addToCallers(callerClassB);

      ConsiderTrace considerTrace = new ConsiderTrace();
      traceDefinition.setConsiderTrace(considerTrace);

      ConsiderClass considerClass = new ConsiderClass();
      considerClass.setName("org.reclipse.ConsiderClassA");
      considerTrace.addToClasses(considerClass);

      ConsiderMethod considerMethod = new ConsiderMethod();
      considerMethod.setName("methodA");

      Parameter parameter = new Parameter();
      parameter.setType("java.lang.String");
      considerMethod.addToParameters(parameter);

      considerClass.addToMethods(considerMethod);

      considerClass = new ConsiderClass();
      considerClass.setName("org.reclipse.ConsiderClassB");
      considerTrace.addToClasses(considerClass);

      considerMethod = new ConsiderMethod();
      considerMethod.setName("methodB");

      callerClassB = new CallerClass();
      callerClassB.setName("org.reclipse.CallerClassB");
      considerMethod.addToCallers(callerClassB);
      considerClass.addToMethods(considerMethod);
   }


   /**
    * @see junit.framework.TestCase#tearDown()
    */
   @Override
   protected void tearDown() throws Exception
   {
      super.tearDown();

      this.traceDefinition = null;
   }


   public void testSavingLoadingCycle()
   {
      // create temporary file
      String fileName = System.getProperty("java.io.tmpdir") + File.separator
            + "Test.xtracedefinition";

      // create xml document
      TraceDefinitionWriter traceDefinitionWriter = new TraceDefinitionWriter(
            this.traceDefinition);
      boolean saved = traceDefinitionWriter.save(fileName, false);
      assertTrue(saved);

      // parse xml document
      TraceDefinition actualTraceDefinition = null;
      try
      {
         actualTraceDefinition = TraceDefinitionReader.load(fileName);
      }
      catch (FileNotFoundException e)
      {
         fail(e.getMessage());
      }
      assertNotNull(actualTraceDefinition);

      // check critical trace
      CriticalTrace expectedCriticalTrace = this.traceDefinition
            .getCriticalTrace();
      CriticalTrace actualCriticalTrace = actualTraceDefinition
            .getCriticalTrace();
      assertNotNull(actualCriticalTrace);

      // check critical classes (qualified assoc)
      assertEquals(expectedCriticalTrace.sizeOfClasses(), actualCriticalTrace
            .sizeOfClasses());
      Iterator iter = expectedCriticalTrace.iteratorOfClasses();
      while (iter.hasNext())
      {
         CriticalClass expectedCriticalClass = (CriticalClass) iter.next();
         CriticalClass actualCriticalClass = actualCriticalTrace
               .getFromClasses(expectedCriticalClass.getName());
         assertNotNull(actualCriticalClass);
         assertEquals(expectedCriticalClass.getName(), actualCriticalClass
               .getName());

         // check caller classes (qualified assoc)
         assertEquals(expectedCriticalClass.sizeOfCallers(),
               actualCriticalClass.sizeOfCallers());
         Iterator callerIter = expectedCriticalClass.iteratorOfCallers();
         while (callerIter.hasNext())
         {
            CallerClass expectedCallerClass = (CallerClass) callerIter.next();
            CallerClass actualCallerClass = actualCriticalClass
                  .getFromCallers(expectedCallerClass.getName());
            assertNotNull(actualCallerClass);
            assertEquals(expectedCallerClass.getName(), actualCallerClass
                  .getName());
         }
      }


      // check consider trace
      ConsiderTrace expectedConsiderTrace = this.traceDefinition
            .getConsiderTrace();
      ConsiderTrace actualConsiderTrace = actualTraceDefinition
            .getConsiderTrace();
      assertNotNull(actualConsiderTrace);

      // check consider classes (qualified assoc)
      iter = expectedConsiderTrace.iteratorOfClasses();
      while (iter.hasNext())
      {
         ConsiderClass expectedConsiderClass = (ConsiderClass) iter.next();
         ConsiderClass actualConsiderClass = actualConsiderTrace
               .getFromClasses(expectedConsiderClass.getName());
         assertNotNull(actualConsiderClass);
         assertEquals(expectedConsiderClass.getName(), actualConsiderClass
               .getName());

         // check methods (qualified assoc)
         assertEquals(expectedConsiderClass.sizeOfMethods(),
               actualConsiderClass.sizeOfMethods());
         Iterator methodsIter = expectedConsiderClass.iteratorOfMethods();
         while (methodsIter.hasNext())
         {
            ConsiderMethod expectedConsiderMethod = (ConsiderMethod) methodsIter
                  .next();
            ConsiderMethod actualConsiderMethod = actualConsiderClass
                  .getFromMethods(expectedConsiderMethod.getSignature());
            assertNotNull(actualConsiderMethod);
            assertEquals(expectedConsiderMethod.getName(), actualConsiderMethod
                  .getName());

            // check parameter (ordered assoc)
            assertEquals(expectedConsiderMethod.sizeOfParameters(),
                  actualConsiderMethod.sizeOfParameters());
            Iterator expectedParamsIter = expectedConsiderMethod
                  .iteratorOfParameters();
            Iterator actualParamsIter = actualConsiderMethod
                  .iteratorOfParameters();
            while (expectedParamsIter.hasNext())
            {
               Parameter expectedParameter = (Parameter) expectedParamsIter
                     .next();
               Parameter actualParameter = (Parameter) actualParamsIter.next();
               assertNotNull(actualParameter.getType());
               assertEquals(expectedParameter.getType(), actualParameter
                     .getType());
            }

            // check caller classes (to-many assoc)
            assertEquals(expectedConsiderMethod.sizeOfCallers(),
                  actualConsiderMethod.sizeOfCallers());
            Iterator callerIter = expectedConsiderMethod.iteratorOfCallers();
            while (callerIter.hasNext())
            {
               CallerClass expectedCallerClass = (CallerClass) callerIter
                     .next();

               boolean found = false;
               Iterator actualCallerIter = actualConsiderMethod
                     .iteratorOfCallers();
               while (actualCallerIter.hasNext())
               {
                  CallerClass actualCallerClass = (CallerClass) actualCallerIter
                        .next();
                  if (expectedCallerClass.getName().equals(
                        actualCallerClass.getName()))
                  {
                     found = true;
                     break;
                  }
               }
               assertTrue(found);
            }
         }
      }

      // delete temporary file
      File file = new File(fileName);
      file.delete();
   }

}
