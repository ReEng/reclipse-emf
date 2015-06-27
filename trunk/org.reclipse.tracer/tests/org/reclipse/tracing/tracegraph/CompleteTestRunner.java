package org.reclipse.tracing.tracegraph;


import junit.framework.TestSuite;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3789 $ $Date: 2007-09-10 22:07:05 +0200 (Mo, 10 Sep 2007) $
 */
public class CompleteTestRunner
{
   public static TestSuite createTestSuite()
   {
      TestSuite testSuite = new TestSuite();

      testSuite.addTestSuite(TGTypeTest.class);
      testSuite.addTestSuite(TGFactoryTest.class);

      return testSuite;
   }


   public static final void main(String[] args)
   {
      junit.textui.TestRunner.run(createTestSuite());
   }

}
