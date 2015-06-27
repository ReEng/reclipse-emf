package org.reclipse.behavior.inference.automaton;


import java.util.Iterator;

import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallSetObject;
import org.reclipse.behavior.inference.automaton.symbols.PermittedMethodCallSymbol;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGObjectArgument;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * <h2>Association</h2>
 * 
 * <pre>
 *              0..n     incomingTransitions     1 
 *  Transition ------------------------------------ AbstractState
 *              incomingTransitions      nextState
 *               
 *              0..n       outgoingTransitions       1 
 *  Transition ---------------------------------------- AbstractState
 *              outgoingTransitions      previousState
 *               
 *              1..n      symbol      1 
 *  Transition ------------------------- AbstractSymbol
 *              transitions      symbol 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4343 $ $Date: 2010-05-14 16:08:35 +0200 (Fr, 14 Mai 2010) $
 */
public class Transition
{

   private Assignment assignment;


   public Assignment getAssignment()
   {
      return assignment;
   }


   public void setAssignment(Assignment assignment)
   {
      this.assignment = assignment;
   }


   /**
    * <pre>
    *              0..n     incomingTransitions     1 
    *  Transition ------------------------------------ AbstractState
    *              incomingTransitions      nextState 
    * </pre>
    */
   private AbstractState nextState;


   public AbstractState getNextState()
   {
      return this.nextState;
   }


   public boolean setNextState(AbstractState value)
   {
      boolean changed = false;
      if (this.nextState != value)
      {
         AbstractState oldValue = this.nextState;
         if (this.nextState != null)
         {
            this.nextState = null;
            oldValue.removeFromIncomingTransitions(this);
         }
         this.nextState = value;
         if (value != null)
         {
            value.addToIncomingTransitions(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *              0..n       outgoingTransitions       1 
    *  Transition ---------------------------------------- AbstractState
    *              outgoingTransitions      previousState 
    * </pre>
    */
   private AbstractState previousState;


   public AbstractState getPreviousState()
   {
      return this.previousState;
   }


   public boolean setPreviousState(AbstractState value)
   {
      boolean changed = false;
      if (this.previousState != value)
      {
         AbstractState oldValue = this.previousState;
         if (this.previousState != null)
         {
            this.previousState = null;
            oldValue.removeFromOutgoingTransitions(this);
         }
         this.previousState = value;
         if (value != null)
         {
            value.addToOutgoingTransitions(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *              1..n      symbol      1 
    *  Transition ------------------------- AbstractSymbol
    *              transitions      symbol 
    * </pre>
    */
   private AbstractSymbol symbol;


   public AbstractSymbol getSymbol()
   {
      return this.symbol;
   }


   public boolean setSymbol(AbstractSymbol value)
   {
      boolean changed = false;
      if (this.symbol != value)
      {
         AbstractSymbol oldValue = this.symbol;
         if (this.symbol != null)
         {
            this.symbol = null;
            oldValue.removeFromTransitions(this);
         }
         this.symbol = value;
         if (value != null)
         {
            value.addToTransitions(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * If the symbol accepts, the state token will be moved to the next state.
    * 
    * @return true, if the symbol accepts
    * @see org.reclipse.behavior.inference.automaton.AbstractSymbol#accept(TGMethodCall,
    *      Token)
    */
   public boolean accept(TGMethodCall methodCall, Token token)
   {
      boolean accepted = getSymbol().accept(methodCall, token);
      if (accepted)
      {

         if (this.getSymbol() instanceof PermittedMethodCallSymbol)
         {
            MethodCallObject caller = ((PermittedMethodCallSymbol) this
                  .getSymbol()).getCaller();
            MethodCallObject callee = ((PermittedMethodCallSymbol) this
                  .getSymbol()).getCallee();
            // set operations:
            doSetOperations(methodCall.getCaller(), token, caller);
            doSetOperations(methodCall.getCallee(), token, callee);

            if (!nextMethodCallSimilar(methodCall, caller, callee))
            {
               if (!checkForEach(token, caller)
                     || (!checkForEach(token, callee)))
               {
                  return false;
               }

               if (this.getAssignment() != null)
               {
                  handleAssignment(methodCall, token);
               }

               DFAState nextState = (DFAState) getNextState();
               nextState.addToTokens(token);
               token.setMoved(true);

               if (nextState.getType() == AbstractState.ACCEPT)
               {
                  token.incrementPassedAcceptingState();
                  token.setLengthOfAcceptedTrace(token.sizeOfMatchedCalls());
               }
            }
         }
         else
         {
            DFAState nextState = (DFAState) getNextState();
            nextState.addToTokens(token);
            token.setMoved(true);

            if (nextState.getType() == AbstractState.ACCEPT)
            {
               token.incrementPassedAcceptingState();
               token.setLengthOfAcceptedTrace(token.sizeOfMatchedCalls());
            }
         }
         token.setMoved(true);
         token.addToMatchedCalls(methodCall);
      }

      return accepted;
   }


   private void handleAssignment(TGMethodCall methodCall, Token token)
   {
      Assignment assignment = this.getAssignment();
      Iterator iter = methodCall.iteratorOfArguments();
      String id = "0";
      while (iter.hasNext())
      {
         TGObjectArgument arg = (TGObjectArgument) iter.next();
         TGType argType = arg.getType();
         TGType lifeLineType = ((TGObject) token.getFromBindings(assignment
               .getLeftSide())).getType();
         if (argType.equals(lifeLineType))
         {
            id = arg.getObject().getId();
         }
      }
      token.changeBinding(assignment.getLeftSide(), assignment.getRightSide(),
            id);
   }


   private void doSetOperations(TGObject tracegraphObject, Token token,
         MethodCallObject automatonObject)
   {
      if (automatonObject instanceof MethodCallSetObject)
      {
         String setName = ((MethodCallSetObject) automatonObject).getSet();
         if (!((MethodCallSetObject) automatonObject).isForeach())
         {
            token.addToSet(setName, tracegraphObject);
         }
         else
         {
            token.addToForEach(setName, tracegraphObject);
         }
      }
   }


   private boolean checkForEach(Token token, MethodCallObject caller)
   {
      if (caller instanceof MethodCallSetObject)
      {
         if (((MethodCallSetObject) caller).isForeach())
         {
            String setName = ((MethodCallSetObject) caller).getSet();
            if (!token.checkForEach(setName))
            {
               DFAState nextState = (DFAState) getRejectingState();
               nextState.addToTokens(token);
               token.setMoved(true);
               return false;
            }
         }
      }
      return true;
   }


   private boolean nextMethodCallSimilar(TGMethodCall methodCall,
         MethodCallObject caller, MethodCallObject callee)
   {
      if (((caller instanceof MethodCallSetObject) && (((MethodCallSetObject) caller)
            .isForeach()))
            || ((callee instanceof MethodCallSetObject) && (((MethodCallSetObject) callee)
                  .isForeach())))
      {
         if ((methodCall.getNext() != null)
               && (methodCall.getName().equals(methodCall.getNext().getName())))
         {
            if (caller instanceof MethodCallSetObject)
            {
               if (methodCall.getCallee().equals(
                     methodCall.getNext().getCallee()))
               {
                  if (hasSameSuperType(methodCall.getCaller(), methodCall
                        .getNext().getCaller()))
                  {
                     return true;
                  }
               }
            }
            else if (callee instanceof MethodCallSetObject)
            {
               if (methodCall.getCaller().equals(
                     methodCall.getNext().getCaller()))
               {
                  if (hasSameSuperType(methodCall.getCallee(), methodCall
                        .getNext().getCallee()))
                  {
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }


   private DFAState getRejectingState()
   {
      DFA dfa = (DFA) ((DFAState) getNextState()).getAutomaton();
      DFAState rejectingState = dfa.getRejectingState();
      return rejectingState;
   }


   /*
    * returns true if objects have same super type or same interface
    */
   private boolean hasSameSuperType(TGObject obj1, TGObject obj2)
   {
      Iterator iter = ((TGType) obj2.getType()).iteratorOfSuperTypes();
      while (iter.hasNext())
      {
         TGType superType = (TGType) iter.next();
         if (((TGType) obj1.getType()).isCompatibleTo(superType.toString()))
         {
            return true;
         }
      }
      return false;
   }


   public boolean isConnectedToRejectingState()
   {
      if (this.getPreviousState() == this.getRejectingState()
            || this.getNextState() == this.getRejectingState())
      {
         return true;
      }
      return false;
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return getSymbol().toString();
   }


   public void removeYou()
   {
      AbstractState tmpPreviousState = getPreviousState();
      if (tmpPreviousState != null)
      {
         setPreviousState(null);
      }

      AbstractState tmpNextState = getNextState();
      if (tmpNextState != null)
      {
         setNextState(null);
      }

      AbstractSymbol tmpSymbol = getSymbol();
      if (tmpSymbol != null)
      {
         setSymbol(null);
      }
   }

}