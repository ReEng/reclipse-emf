package org.reclipse.behavior.inference.automaton;

import java.util.Iterator;

import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.AbstractSymbol;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.NFAState;
import org.reclipse.behavior.inference.automaton.Transition;

import junit.framework.TestCase;

/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4126 $ $Date: 2009-08-06 15:19:27 +0200 (Do, 06 Aug 2009) $
 */
public abstract class StateTestCase extends TestCase
{

   protected Transition createTransition(AbstractState previousState,
         AbstractState nextState, AbstractSymbol symbol)
   {
      Transition transition = new Transition();
      transition.setSymbol(symbol);
      transition.setPreviousState(previousState);
      transition.setNextState(nextState);

      return transition;
   }


   protected void checkDFAState(DFAState state, DFA dfa, NFAState[] nfaStates,
         int type, int incomingTransitions, int outgoingTransitions)
   {
      assertTrue(dfa.hasInStates(state));
      assertTrue(state.getType() == type);

      assertEquals(incomingTransitions, state.sizeOfIncomingTransitions());
      assertEquals(outgoingTransitions, state.sizeOfOutgoingTransitions());

      assertEquals(nfaStates.length, state.sizeOfNFAStates());
      for (int i = 0; i < nfaStates.length; i++)
      {
         assertTrue(state.hasInNFAStates(nfaStates[i]));
      }
   }


   protected DFAState move(DFAState state, AbstractSymbol symbol)
   {
      Iterator iter = state.iteratorOfOutgoingTransitions();
      while (iter.hasNext())
      {
         Transition transition = (Transition) iter.next();
         if (transition.getSymbol() == symbol)
         {
            return (DFAState) transition.getNextState();
         }
      }
      
      return null;
   }

}

/*
 * $Log$
 * Revision 1.1  2005/12/03 21:30:59  lowende
 * New package structure
 *
 * Revision 1.1  2005/11/21 05:03:41  lowende
 * New minimize algorithm for DFA, test not yet passed.
 *
 */
