package org.reclipse.behavior.inference.automaton;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.reclipse.behavior.inference.automaton.symbols.SymbolFactory;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;


/**
 * This class represents a Deterministic Finite Automaton (DFA).
 * 
 * <h2>Associations</h2>
 * 
 * <pre>
 *         rejectingState       1 
 * DFA ---------------------------> DFAState
 *                 rejectingState
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class DFA extends AbstractAutomaton
{

   /**
    * @param value The new start state, must be instance of DFAState.
    * 
    * @see org.reclipse.behavior.inference.automaton.AbstractAutomaton#setStartState(org.reclipse.behavior.inference.automaton.AbstractState)
    * @throws IllegalArgumentException if value is not instance of DFAState
    */
   @Override
   public boolean setStartState(AbstractState value)
   {
      if (value == null || value instanceof DFAState)
      {
         return super.setStartState(value);
      }
      else
      {
         throw new IllegalArgumentException(
               "Argument must be instance of DFAState.");
      }
   }


   /**
    * <pre>
    *         rejectingState       1 
    * DFA ---------------------------> DFAState
    *                 rejectingState
    * </pre>
    */
   private DFAState rejectingState;


   public void setRejectingState(DFAState value)
   {
      this.rejectingState = value;
   }


   public DFAState getRejectingState()
   {
      return this.rejectingState;
   }


   /**
    * @param value A new state, must be instance of DFAState.
    * 
    * @see org.reclipse.behavior.inference.automaton.AbstractAutomaton#addToStates(org.reclipse.behavior.inference.automaton.AbstractState)
    * @throws IllegalArgumentException if value is not instance of DFAState
    */
   @Override
   public boolean addToStates(AbstractState value)
   {
      if (value instanceof DFAState)
      {
         return super.addToStates(value);
      }
      else
      {
         throw new IllegalArgumentException(
               "Argument must be instance of DFAState.");
      }
   }


   /* package */DFAState getFromStates(Set set)
   {
      Iterator iter = iteratorOfStates();
      while (iter.hasNext())
      {
         DFAState currentState = (DFAState) iter.next();
         if (currentState.equalsSetOfNFAStates(set))
         {
            return currentState;
         }
      }

      return null;
   }


   public void methodCalled(TGMethodCall methodCall)
   {
      // delegate the method call event to all states
      Iterator iter = iteratorOfStates();
      while (iter.hasNext())
      {
         DFAState dfaState = (DFAState) iter.next();
         dfaState.methodCalled(methodCall);
      }

      checkBindings();
   }


   private void checkBindings()
   {
      Iterator statesIter = iteratorOfStates();
      while (statesIter.hasNext())
      {
         DFAState state = (DFAState) statesIter.next();

         Iterator tokensIter = state.iteratorOfTokens();
         while (tokensIter.hasNext())
         {
            Token token = (Token) tokensIter.next();

            for (String key : token.keysOfBindings())
            {
               TGObject binding = token.getFromBindings(key);
               Set<TGObject> possibleBindings = token.getPossibleBindings(key);
               if (possibleBindings.contains(binding))
               {
                  // reject trace, since object participated in prohibited method call
                  token.setState(getRejectingState());
               }
            }
         }
      }
   }


   public DFA minimize()
   {
      NFA nfa = reverse(this);
      DFA dfa = nfa.convertToDFA();
      nfa.removeYou();

      nfa = reverse(dfa);
      DFA minimizedDFA = nfa.convertToDFA();
      dfa.removeYou();
      nfa.removeYou();

      return minimizedDFA;
   }


   public NFA reverse(DFA dfa)
   {
      NFA nfa = new NFA();
      nfa.setSymbols(dfa.getSymbols());

      NFAState startState = new NFAState();
      nfa.addToStates(startState);
      nfa.setStartState(startState);

      HashMap mapping = new HashMap();

      // copy states and invert type
      // add epsilon transitions from new start state to all
      // former accepting states from DFA
      Iterator iterStates = dfa.iteratorOfStates();
      while (iterStates.hasNext())
      {
         DFAState dfaState = (DFAState) iterStates.next();
         NFAState nfaState = new NFAState();
         nfaState.setName(dfaState.getName());
         nfa.addToStates(nfaState);

         mapping.put(dfaState, nfaState);

         if (dfaState.getType() == AbstractState.NONE)
         {
            nfaState.setType(AbstractState.ACCEPT);
         }
         else if (dfaState.getType() == AbstractState.ACCEPT)
         {
            nfaState.setType(AbstractState.NONE);
            createTransition(startState, nfaState, SymbolFactory.EPSILON);
         }
      }

      // revert all transitions
      iterStates = dfa.iteratorOfStates();
      while (iterStates.hasNext())
      {
         DFAState dfaState = (DFAState) iterStates.next();
         Iterator iterTransitions = dfaState.iteratorOfOutgoingTransitions();
         while (iterTransitions.hasNext())
         {
            Transition transition = (Transition) iterTransitions.next();
            NFAState previousState = (NFAState) mapping.get(transition
                  .getNextState());
            NFAState nextState = (NFAState) mapping.get(transition
                  .getPreviousState());

            createTransition(previousState, nextState, transition.getSymbol());
         }
      }

      return nfa;
   }


   private Transition createTransition(AbstractState previousState,
         AbstractState nextState, AbstractSymbol symbol)
   {
      Transition transition = new Transition();
      transition.setSymbol(symbol);
      transition.setPreviousState(previousState);
      transition.setNextState(nextState);

      return transition;
   }


   /**
    * @see org.reclipse.behavior.inference.automaton.AbstractAutomaton#removeYou()
    */
   @Override
   public void removeYou()
   {
      setRejectingState(null);

      super.removeYou();
   }

}
