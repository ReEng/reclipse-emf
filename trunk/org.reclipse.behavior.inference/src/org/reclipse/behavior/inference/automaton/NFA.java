package org.reclipse.behavior.inference.automaton;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import org.reclipse.behavior.inference.automaton.symbols.Epsilon;


/**
 * This class represents a Nondeterministic Finite Automaton (NFA).
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4343 $ $Date: 2010-05-14 16:08:35 +0200 (Fr, 14 Mai 2010) $
 */
public class NFA extends AbstractAutomaton
{

   /**
    * @param value The new start state, must be instance of NFAState.
    * 
    * @see org.reclipse.behavior.inference.automaton.AbstractAutomaton#setStartState(org.reclipse.behavior.inference.automaton.AbstractState)
    * @throws IllegalArgumentException if value is not instance of NFAState
    */
   @Override
   public boolean setStartState(AbstractState value)
   {
      if (value == null || value instanceof NFAState)
      {
         return super.setStartState(value);
      }
      else
      {
         throw new IllegalArgumentException(
               "Argument must be instance of NFAState.");
      }
   }


   /**
    * @param value A new state, must be instance of NFAState.
    * 
    * @see org.reclipse.behavior.inference.automaton.AbstractAutomaton#addToStates(org.reclipse.behavior.inference.automaton.AbstractState)
    * @throws IllegalArgumentException if value is not instance of NFAState
    */
   @Override
   public boolean addToStates(AbstractState value)
   {
      if (value instanceof NFAState)
      {
         return super.addToStates(value);
      }
      else
      {
         throw new IllegalArgumentException(
               "Argument must be instance of NFAState.");
      }
   }
   
   


   /**
    * Converts this NFA into a DFA.
    * 
    * The algorithm is taken from "Compilers Principles, Techniques and Tools" A.V. Aho, R. Sethi,
    * and J.D. Ullman Addison-Wesley Publishing, 1996
    * 
    * Algorithm 3.2, pp. 117-119
    * 
    * @return Returns a new non-minimized DFA.
    */
   public DFA convertToDFA()
   {
      if (getStartState() == null)
      {
         throw new IllegalStateException("The NFA has no start state.");
      }

      DFA dfa = new DFA();
      
      dfa.setAssignments(this.getAssignments());

      HashSet startStateSet = new HashSet();
      startStateSet.add(getStartState());

      // initially, epsilon-closure(s0) is the only state in DStates
      // and is unmarked
      Set epsilonClosure = computeEpsilonClosure(startStateSet);

      DFAState startState = new DFAState();
      startState.addAllToNFAStates(epsilonClosure);
      dfa.setStartState(startState);
      dfa.addToStates(startState);
      dfa.setSymbols(getSymbols());

      // set of unmarked DFA states
      Stack unmarkedStates = new Stack();
      unmarkedStates.push(startState);

      // while there is a unmarked state T in Dstates
      while (!unmarkedStates.empty())
      {
         // mark T
         DFAState currentDFAState = (DFAState) unmarkedStates.pop();

         // for each input symbol a
         Iterator iter = iteratorOfSymbols();
         while (iter.hasNext())
         {
            AbstractSymbol symbol = (AbstractSymbol) iter.next();

            // U := epsilon-closure(move(T,a))
            epsilonClosure = computeEpsilonClosure(computeMove(currentDFAState,
                  symbol));

            if (!epsilonClosure.isEmpty())
            {
               // if U is not in Dstates
               DFAState unmarkedState = dfa.getFromStates(epsilonClosure);
               if (unmarkedState == null)
               {
                  unmarkedState = new DFAState();
                  unmarkedState.addAllToNFAStates(epsilonClosure);
                  dfa.addToStates(unmarkedState);

                  // add U as an unmarked state to Dstates
                  unmarkedStates.push(unmarkedState);
               }

               // Dtran[T,a]:=U
               Transition transition = new Transition();
               transition.setSymbol(symbol);
               if(symbol.getAssignment()!=null){
                   transition.setAssignment(symbol.getAssignment());
               }
               currentDFAState.addToOutgoingTransitions(transition);
               unmarkedState.addToIncomingTransitions(transition);
            }
         }
      }

      // mark accepting and rejecting states and name the dfa states
      Iterator iterDFAStates = dfa.iteratorOfStates();
      while (iterDFAStates.hasNext())
      {
         DFAState dfaState = (DFAState) iterDFAStates.next();

         char[] nfaStateNames = new char[dfaState.sizeOfNFAStates()];

         Iterator iterNFAStates = dfaState.iteratorOfNFAStates();
         int index = 0;
         while (iterNFAStates.hasNext())
         {
            NFAState nfaState = (NFAState) iterNFAStates.next();

            if (nfaState.getType() == AbstractState.ACCEPT)
            {
               dfaState.setType(AbstractState.ACCEPT);
            }
            else if (nfaState.getType() == AbstractState.REJECT)
            {
               dfaState.setType(AbstractState.REJECT);
            }

            nfaStateNames[index] = nfaState.getName().charAt(0);
            index++;
         }
         
         Arrays.sort(nfaStateNames);
         String dfaStateName = String.valueOf(nfaStateNames[0]);
         for(int i = 1; i < nfaStateNames.length; i++)
         {
            dfaStateName += ",";
            dfaStateName += nfaStateNames[i];
         }
         
         dfaState.setName(dfaStateName);
      }

      return dfa;
   }


   /**
    * This method computes a set of states transitively reachable from any state in nfaStates via
    * transitions on the empty symbol.
    * 
    * The algorithm is taken from "Compilers Principles, Techniques and Tools" A.V. Aho, R. Sethi,
    * and J.D. Ullman Addison-Wesley Publishing, 1996
    * 
    * Algorithm 3.2, pp. 117-119
    * 
    * @param nfaStates the starting set of NFA states
    * @return a set of states reachable via transitions on the empty symbol
    */
   /* package */Set computeEpsilonClosure(Set nfaStates)
   {
      // push all in nfaStates onto stack
      Stack stack = new Stack();
      stack.addAll(nfaStates);

      // initialize epsilonClosure(nfaStates) to nfaStates
      HashSet epsilonClosure = new HashSet();
      epsilonClosure.addAll(nfaStates);

      // while stack is not empty
      while (!stack.empty())
      {
         // pop currentState, the top element, off of stack
         NFAState currentState = (NFAState) stack.pop();

         // for each state nextState with an edge from currentState
         // to nextState labeled epsilon
         Iterator iter = currentState.iteratorOfOutgoingTransitions();
         while (iter.hasNext())
         {
            Transition transition = (Transition) iter.next();
            if (transition.getSymbol() instanceof Epsilon)
            {
               NFAState nextState = (NFAState) transition.getNextState();

               // if nfaState is not in epsilonClosure(nfaStates)
               if (!epsilonClosure.contains(nextState))
               {
                  // add nfaState to epsilon-closure
                  epsilonClosure.add(nextState);

                  // push nfaState onto stack
                  stack.push(nextState);
               }
            }
         }
      }

      return epsilonClosure;
   }


   /**
    * Computes a set of NFA states reachable from the NFA states of dfaState via transitions on the
    * given symbol.
    * 
    * @param dfaState The DFA state to compute the move for.
    * @param symbol The common symbol for the transitions
    * @return Returns a set of NFA states
    */
   /* package */Set computeMove(DFAState dfaState, AbstractSymbol symbol)
   {
      HashSet move = new HashSet();

      Iterator iterNFAStates = dfaState.iteratorOfNFAStates();
      while (iterNFAStates.hasNext())
      {
         NFAState nfaState = (NFAState) iterNFAStates.next();

         Iterator iterTransitions = nfaState.iteratorOfOutgoingTransitions();
         while (iterTransitions.hasNext())
         {
            Transition transition = (Transition) iterTransitions.next();
            if (transition.getSymbol() == symbol)
            {
               move.add(transition.getNextState());
            }
         }
      }

      return move;
   }

}
