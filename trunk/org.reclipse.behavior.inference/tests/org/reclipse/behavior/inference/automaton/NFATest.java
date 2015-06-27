package org.reclipse.behavior.inference.automaton;


import java.util.HashSet;
import java.util.Set;

import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.AbstractSymbol;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.NFA;
import org.reclipse.behavior.inference.automaton.NFAState;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.SymbolFactory;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4550 $ $Date: 2010-09-27 17:22:14 +0200 (Mo, 27 Sep 2010) $
 */
public class NFATest extends StateTestCase
{

   /**
    * Test method for 'org.reclipse.patterns.behavior.automaton.NFA.setStartState(AbstractState)'
    */
   public void testSetStartState()
   {
      NFA nfa = new NFA();
      NFAState nfaState = new NFAState();

      assertNull(nfa.getStartState());
      nfa.setStartState(nfaState);
      assertEquals(nfaState, nfa.getStartState());

      nfa.setStartState(null);
      assertNull(nfa.getStartState());

      try
      {
         DFAState dfaState = new DFAState();
         nfa.setStartState(dfaState);
         fail("IllegalArgumentException was expected to be thrown.");
      }
      catch (IllegalArgumentException e)
      {
      }
   }


   /**
    * Test method for 'org.reclipse.patterns.behavior.automaton.NFA.addToStates(AbstractState)'
    */
   public void testAddToStates()
   {
      NFA nfa = new NFA();
      NFAState nfaState = new NFAState();

      assertEquals(0, nfa.sizeOfStates());
      nfa.addToStates(nfaState);
      assertEquals(1, nfa.sizeOfStates());

      try
      {
         DFAState dfaState = new DFAState();
         nfa.addToStates(dfaState);
         fail("IllegalArgumentException was expected to be thrown.");
      }
      catch (IllegalArgumentException e)
      {
      }

      assertEquals(1, nfa.sizeOfStates());
   }


   /**
    * Test method for 'org.reclipse.patterns.behavior.automaton.NFA.convertToDFA()'
    * 
    * The NFA is taken from Fig. 3.27, pp.120, "Compilers Principles, Techniques and Tools" A.V.
    * Aho, R. Sethi, and J.D. Ullman Addison-Wesley Publishing, 1996
    * 
    * The converted NFA should result in the DFA described in Fig. 3.29, p.121.
    */
   public void testConvertToDFA()
   {
      // construct NFA that accepts (a|b)*abb
      NFA nfa = new NFA();

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject caller = factory.provideMethodCallObject("caller",
            "Caller");
      MethodCallObject callee = factory.provideMethodCallObject("callee",
            "Callee");

      AbstractSymbol symbolA = factory.providePermittedMethodCallSymbol(caller,
            callee, "a()");
      nfa.addToSymbols(symbolA);

      AbstractSymbol symbolB = factory.providePermittedMethodCallSymbol(caller,
            callee, "b()");
      nfa.addToSymbols(symbolB);

      NFAState state0 = createNFAState(nfa);
      state0.setName("0");
      nfa.setStartState(state0);

      NFAState state1 = createNFAState(nfa);
      state1.setName("1");
      createTransition(state0, state1, SymbolFactory.EPSILON);

      NFAState state2 = createNFAState(nfa);
      state2.setName("2");
      createTransition(state1, state2, SymbolFactory.EPSILON);

      NFAState state3 = createNFAState(nfa);
      state3.setName("3");
      createTransition(state2, state3, symbolA);

      NFAState state4 = createNFAState(nfa);
      state4.setName("4");
      createTransition(state1, state4, SymbolFactory.EPSILON);

      NFAState state5 = createNFAState(nfa);
      state5.setName("5");
      createTransition(state4, state5, symbolB);

      NFAState state6 = createNFAState(nfa);
      state6.setName("6");
      createTransition(state3, state6, SymbolFactory.EPSILON);
      createTransition(state5, state6, SymbolFactory.EPSILON);
      createTransition(state6, state1, SymbolFactory.EPSILON);

      NFAState state7 = createNFAState(nfa);
      state7.setName("7");
      createTransition(state6, state7, SymbolFactory.EPSILON);
      createTransition(state0, state7, SymbolFactory.EPSILON);

      NFAState state8 = createNFAState(nfa);
      state8.setName("8");
      createTransition(state7, state8, symbolA);

      NFAState state9 = createNFAState(nfa);
      state9.setName("9");
      createTransition(state8, state9, symbolB);

      NFAState state10 = createNFAState(nfa);
      state10.setName("10");
      state10.setType(AbstractState.ACCEPT);
      createTransition(state9, state10, symbolB);

      // convert to DFA and check DFA states and transitions
      DFA dfa = nfa.convertToDFA();

      // check symbols
      assertEquals(2, dfa.sizeOfSymbols());
      assertTrue(dfa.hasInSymbols(symbolA));
      assertTrue(dfa.hasInSymbols(symbolB));

      // check states and transitions
      assertEquals(5, dfa.sizeOfStates());

      DFAState stateA = (DFAState) dfa.getStartState();
      checkDFAState(stateA, dfa, new NFAState[] { state0, state1, state2,
            state4, state7 }, AbstractState.NONE, 0, 2);

      DFAState stateB = move(stateA, symbolA);
      checkDFAState(stateB, dfa, new NFAState[] { state1, state2, state3,
            state4, state6, state7, state8 }, AbstractState.NONE, 5, 2);
      assertEquals(stateB, move(stateB, symbolA));

      DFAState stateC = move(stateA, symbolB);
      checkDFAState(stateC, dfa, new NFAState[] { state1, state2, state4,
            state5, state6, state7 }, AbstractState.NONE, 3, 2);
      assertEquals(stateB, move(stateC, symbolA));
      assertEquals(stateC, move(stateC, symbolB));

      DFAState stateD = move(stateB, symbolB);
      checkDFAState(stateD, dfa, new NFAState[] { state1, state2, state4,
            state5, state6, state7, state9 }, AbstractState.NONE, 1, 2);
      assertEquals(stateB, move(stateD, symbolA));

      DFAState stateE = move(stateD, symbolB);
      checkDFAState(stateE, dfa, new NFAState[] { state1, state2, state4,
            state5, state6, state7, state10 }, AbstractState.ACCEPT, 1, 2);
      assertEquals(stateB, move(stateE, symbolA));
      assertEquals(stateC, move(stateE, symbolB));
   }


   /**
    * Test method for 'org.reclipse.patterns.behavior.automaton.NFA.computeEpsilonClosure(Set)'
    */
   public void testComputeEpsilonClosure()
   {
      NFA nfa = new NFA();
      HashSet<NFAState> nfaStates = new HashSet<NFAState>();

      NFAState state0 = new NFAState();
      nfaStates.add(state0);

      Set actualEpsilonClosure = nfa.computeEpsilonClosure(nfaStates);
      assertEquals(1, actualEpsilonClosure.size());
      assertTrue(actualEpsilonClosure.contains(state0));

      NFAState state1 = new NFAState();
      createTransition(state0, state1, SymbolFactory.EPSILON);

      actualEpsilonClosure = nfa.computeEpsilonClosure(nfaStates);
      assertEquals(2, actualEpsilonClosure.size());
      assertTrue(actualEpsilonClosure.contains(state0));
      assertTrue(actualEpsilonClosure.contains(state1));

      NFAState state2 = new NFAState();
      createTransition(state1, state2, SymbolFactory.EPSILON);

      actualEpsilonClosure = nfa.computeEpsilonClosure(nfaStates);
      assertEquals(3, actualEpsilonClosure.size());
      assertTrue(actualEpsilonClosure.contains(state0));
      assertTrue(actualEpsilonClosure.contains(state1));
      assertTrue(actualEpsilonClosure.contains(state2));

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject caller = factory.provideMethodCallObject("caller",
            "Caller");
      MethodCallObject callee = factory.provideMethodCallObject("callee",
            "Callee");

      NFAState state3 = new NFAState();
      AbstractSymbol symbol = factory.providePermittedMethodCallSymbol(caller,
            callee, "test()");
      createTransition(state0, state3, symbol);

      actualEpsilonClosure = nfa.computeEpsilonClosure(nfaStates);
      assertEquals(3, actualEpsilonClosure.size());
      assertTrue(actualEpsilonClosure.contains(state0));
      assertTrue(actualEpsilonClosure.contains(state1));
      assertTrue(actualEpsilonClosure.contains(state2));
      assertFalse(actualEpsilonClosure.contains(state3));

      createTransition(state2, state3, SymbolFactory.EPSILON);

      actualEpsilonClosure = nfa.computeEpsilonClosure(nfaStates);
      assertEquals(4, actualEpsilonClosure.size());
      assertTrue(actualEpsilonClosure.contains(state0));
      assertTrue(actualEpsilonClosure.contains(state1));
      assertTrue(actualEpsilonClosure.contains(state2));
      assertTrue(actualEpsilonClosure.contains(state3));
   }


   /**
    * Test method for 'org.reclipse.patterns.behavior.automaton.NFA.computeMove(DFAState,
    * AbstractSymbol)'
    */
   public void testComputeMove()
   {
      NFA nfa = new NFA();

      DFAState dfaState = new DFAState();
      NFAState state0 = new NFAState();
      state0.setName("0");
      dfaState.addToNFAStates(state0);

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject caller = factory.provideMethodCallObject("caller",
            "Caller");
      MethodCallObject callee = factory.provideMethodCallObject("callee",
            "Callee");

      AbstractSymbol moveSymbol = factory.providePermittedMethodCallSymbol(
            caller, callee, "test()");

      Set actualMoveSet = nfa.computeMove(dfaState, moveSymbol);
      assertEquals(0, actualMoveSet.size());

      NFAState state1 = new NFAState();
      state1.setName("1");
      createTransition(state0, state1, moveSymbol);

      actualMoveSet = nfa.computeMove(dfaState, moveSymbol);
      assertEquals(1, actualMoveSet.size());
      assertTrue(actualMoveSet.contains(state1));

      NFAState state2 = new NFAState();
      state2.setName("2");
      createTransition(state0, state2, moveSymbol);

      actualMoveSet = nfa.computeMove(dfaState, moveSymbol);
      assertEquals(2, actualMoveSet.size());
      assertTrue(actualMoveSet.contains(state1));
      assertTrue(actualMoveSet.contains(state2));

      NFAState state3 = new NFAState();
      state3.setName("3");
      AbstractSymbol differentSymbol = factory
            .providePermittedMethodCallSymbol(caller, callee, "test2()");
      createTransition(state0, state3, differentSymbol);

      actualMoveSet = nfa.computeMove(dfaState, moveSymbol);
      assertEquals(2, actualMoveSet.size());
      assertTrue(actualMoveSet.contains(state1));
      assertTrue(actualMoveSet.contains(state2));
      assertFalse(actualMoveSet.contains(state3));
   }


   private NFAState createNFAState(NFA nfa)
   {
      NFAState state = new NFAState();
      nfa.addToStates(state);

      return state;
   }

}
