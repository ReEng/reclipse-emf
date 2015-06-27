package org.reclipse.tracer.test;


import junit.framework.TestSuite;

import org.reclipse.tracer.test.input.ConsiderClassTest;
import org.reclipse.tracer.test.input.ConsiderMethodTest;
import org.reclipse.tracer.test.input.CriticalClassTest;
import org.reclipse.tracer.test.input.TraceDefinitionTest;
import org.reclipse.tracer.test.input.xml.TraceDefinitionSavingLoadingTest;
import org.reclipse.tracing.tracegraph.TGFactoryTest;
import org.reclipse.tracing.tracegraph.TGTypeTest;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2358 $ $Date: 2006-06-01 14:31:42 +0200 (Do, 01 Jun 2006) $
 */
public class CompleteTestRunner
{
   public static TestSuite createTestSuite()
   {
      TestSuite testSuite = new TestSuite();

      testSuite.addTestSuite(ConsiderClassTest.class);
      testSuite.addTestSuite(ConsiderMethodTest.class);
      testSuite.addTestSuite(CriticalClassTest.class);
      testSuite.addTestSuite(TraceDefinitionTest.class);
      testSuite.addTestSuite(TraceDefinitionSavingLoadingTest.class);
      testSuite.addTestSuite(TGFactoryTest.class);
      testSuite.addTestSuite(TGTypeTest.class);

      return testSuite;
   }


   public static final void main(String[] args)
   {
      junit.textui.TestRunner.run(createTestSuite());
   }

}
