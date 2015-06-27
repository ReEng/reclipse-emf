package org.reclipse.behavior.inference.automaton;


import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.AbstractSymbol;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.NFAState;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.SymbolFactory;


public class DFATest extends StateTestCase
{


   /**
    * Test method for 'org.reclipse.patterns.behavior.automaton.DFA.setStartState(AbstractState)'
    */
   public void testSetStartState()
   {
      DFA dfa = new DFA();
      DFAState dfaState = new DFAState();

      assertNull(dfa.getStartState());
      dfa.setStartState(dfaState);
      assertEquals(dfaState, dfa.getStartState());

      dfa.setStartState(null);
      assertNull(dfa.getStartState());

      try
      {
         NFAState nfaState = new NFAState();
         dfa.setStartState(nfaState);
         fail("IllegalArgumentException was expected to be thrown.");
      }
      catch (IllegalArgumentException e)
      {
      }
   }


   /**
    * Test method for 'org.reclipse.patterns.behavior.automaton.DFA.addToStates(AbstractState)'
    */
   public void testAddToStates()
   {
      DFA dfa = new DFA();
      DFAState dfaState = new DFAState();

      assertEquals(0, dfa.sizeOfStates());
      dfa.addToStates(dfaState);
      assertEquals(1, dfa.sizeOfStates());

      try
      {
         NFAState nfaState = new NFAState();
         dfa.addToStates(nfaState);
         fail("IllegalArgumentException was expected to be thrown.");
      }
      catch (IllegalArgumentException e)
      {
      }

      assertEquals(1, dfa.sizeOfStates());
   }


   /*
    * Test method for 'org.reclipse.patterns.behavior.automaton.DFA.minimize()'
    */
   public void doNottestMinimize()
   {
      DFA dfa = new DFA();

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject caller = factory.provideMethodCallObject("caller",
            "Caller");
      MethodCallObject callee = factory.provideMethodCallObject("callee",
            "Callee");

      AbstractSymbol symbolA = factory.providePermittedMethodCallSymbol(caller,
            callee, "a()");
      dfa.addToSymbols(symbolA);

      AbstractSymbol symbolB = factory.providePermittedMethodCallSymbol(caller,
            callee, "b()");
      dfa.addToSymbols(symbolB);

      DFAState stateA = createDFAState(dfa);
      dfa.setStartState(stateA);

      DFAState stateB = createDFAState(dfa);
      createTransition(stateA, stateB, symbolA);
      createTransition(stateB, stateB, symbolA);

      DFAState stateC = createDFAState(dfa);
      createTransition(stateA, stateC, symbolB);
      createTransition(stateC, stateC, symbolB);
      createTransition(stateC, stateB, symbolA);

      DFAState stateD = createDFAState(dfa);
      createTransition(stateB, stateD, symbolB);
      createTransition(stateD, stateB, symbolA);

      DFAState stateE = createDFAState(dfa);
      stateE.setType(AbstractState.ACCEPT);
      createTransition(stateD, stateE, symbolB);
      createTransition(stateE, stateB, symbolA);
      createTransition(stateE, stateC, symbolB);

      // minimize dfa
      DFA minimizedDFA = dfa.minimize();

      // check symbols
      assertEquals(2, minimizedDFA.sizeOfSymbols());
      assertTrue(minimizedDFA.hasInSymbols(symbolA));
      assertTrue(minimizedDFA.hasInSymbols(symbolB));

      // check states and transitions
      assertEquals(4, minimizedDFA.sizeOfStates());

      NFAState[] emptyNFAStates = new NFAState[] {};

      DFAState state0 = (DFAState) minimizedDFA.getStartState();
      checkDFAState(state0, minimizedDFA, emptyNFAStates, AbstractState.NONE,
            2, 2);
      assertEquals(state0, move(state0, symbolB));

      DFAState state1 = move(state0, symbolA);
      checkDFAState(state1, minimizedDFA, emptyNFAStates, AbstractState.NONE,
            4, 2);
      assertEquals(state1, move(state1, symbolA));

      DFAState state2 = move(state1, symbolB);
      checkDFAState(state2, minimizedDFA, emptyNFAStates, AbstractState.NONE,
            1, 2);
      assertEquals(state1, move(state2, symbolA));
      assertEquals(state2, move(state1, symbolB));

      DFAState state3 = move(state2, symbolB);
      checkDFAState(state3, minimizedDFA, emptyNFAStates, AbstractState.ACCEPT,
            1, 2);
      assertEquals(state3, move(state2, symbolB));
      assertEquals(state1, move(state3, symbolA));
      assertEquals(state0, move(state3, symbolB));
   }


   private DFAState createDFAState(DFA dfa)
   {
      DFAState state = new DFAState();
      dfa.addToStates(state);

      return state;
   }

}
