package org.reclipse.behavior.inference;


import junit.framework.TestSuite;

import org.reclipse.behavior.inference.automaton.DFATest;
import org.reclipse.behavior.inference.automaton.NFATest;
import org.reclipse.behavior.inference.automaton.symbols.AbstractMethodCallSymbolTest;
import org.reclipse.behavior.inference.automaton.symbols.PermittedMethodCallSymbolTest;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedCallerSymbolTest;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedMethodCallSymbolTest;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3343 $ $Date: 2007-02-02 15:55:32 +0100 (Fr, 02 Feb 2007) $
 */
public class CompleteTestRunner
{
   public static TestSuite createTestSuite()
   {
      TestSuite testSuite = new TestSuite();

      testSuite.addTestSuite(DFATest.class);
      testSuite.addTestSuite(NFATest.class);
      testSuite.addTestSuite(AbstractMethodCallSymbolTest.class);
      testSuite.addTestSuite(PermittedMethodCallSymbolTest.class);
      testSuite.addTestSuite(ProhibitedMethodCallSymbolTest.class);
      testSuite.addTestSuite(ProhibitedCallerSymbolTest.class);

      return testSuite;
   }


   public static final void main(String[] args)
   {
      junit.textui.TestRunner.run(createTestSuite());
   }

}

/*
 * $Log$
 * Revision 1.1  2005/12/03 21:31:05  lowende
 * New package structure
 *
 * Revision 1.5  2005/12/01 02:11:16  lowende
 * Symbol again renamed, assoc renamed.
 *
 * Revision 1.4  2005/11/30 22:43:43  lowende
 * Test for ProhibitedMethodCallSymbol.accept added.
 *
 * Revision 1.3  2005/11/30 21:56:12  lowende
 * Test for PermittedMethodCallSymbol.accept added.
 *
 * Revision 1.2  2005/11/29 05:14:24  lowende
 * New tests added.
 *
 * Revision 1.1  2005/11/28 18:25:36  lowende
 * Plugin renamed.
 * AutomatonFactory moved from UI.
 * Test added for saving and loading BehavioralPatternsCatalog, not yet working.
 *
 * Revision 1.1  2005/11/21 00:38:36  lowende
 * Tests for NFA added. Test for converting NFA into DFA fails.
 *
 */
