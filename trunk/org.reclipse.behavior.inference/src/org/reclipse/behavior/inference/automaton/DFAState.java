package org.reclipse.behavior.inference.automaton;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.reclipse.tracer.model.tracegraph.TGMethodCall;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                  nfaStates   0..n 
 * DFAState -------------------------> NFAState
 *                         nFAStates
 * 
 *           1   tokens   0..n 
 * DFAState ------------------- Token
 *           state      tokens 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4536 $ $Date: 2010-09-20 14:20:15 +0200 (Mo, 20 Sep 2010) $
 */
public class DFAState extends AbstractState
{

   /**
    * <pre>
    *                  nfaStates   0..n 
    * DFAState -------------------------> NFAState
    *                         nfaStates 
    * </pre>
    */
   private transient HashSet<NFAState> nfaStates;


   public boolean addToNFAStates(NFAState value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.nfaStates == null)
         {
            this.nfaStates = new HashSet<NFAState>();
         }
         changed = this.nfaStates.add(value);
      }
      return changed;
   }


   public boolean addAllToNFAStates(Set<NFAState> set)
   {
      boolean changed = false;
      if (set != null)
      {
         if (this.nfaStates == null)
         {
            this.nfaStates = new HashSet<NFAState>();
         }
         changed = this.nfaStates.addAll(set);
      }
      return changed;
   }


   public boolean hasInNFAStates(NFAState value)
   {
      return ((this.nfaStates != null) && (value != null) && this.nfaStates
            .contains(value));
   }


   public Iterator<NFAState> iteratorOfNFAStates()
   {
      return ((this.nfaStates == null) ? Collections.<NFAState>emptyList().iterator()
            : this.nfaStates.iterator());
   }


   public int sizeOfNFAStates()
   {
      return ((this.nfaStates == null) ? 0 : this.nfaStates.size());
   }


   public boolean removeFromNFAStates(NFAState value)
   {
      boolean changed = false;
      if ((this.nfaStates != null) && (value != null))
      {
         changed = this.nfaStates.remove(value);
      }
      return changed;
   }


   public void removeAllFromNFAStates()
   {
      if (this.nfaStates != null && this.nfaStates.size() > 0)
      {
         this.nfaStates.clear();
      }
   }


   public boolean equalsSetOfNFAStates(Set<NFAState> states)
   {
      return sizeOfNFAStates() == states.size()
            && this.nfaStates.containsAll(states);
   }


   /**
    * <pre>
    *           1   tokens   0..n 
    * DFAState ------------------- Token
    *           state      tokens 
    * </pre>
    */
   private HashSet<Token> tokens;


   public boolean addToTokens(Token value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.tokens == null)
         {
            this.tokens = new HashSet<Token>();
         }
         changed = this.tokens.add(value);
         if (changed)
         {
            value.setState(this);
         }
      }
      return changed;
   }


   public boolean hasInTokens(Token value)
   {
      return ((this.tokens != null) && (value != null) && this.tokens
            .contains(value));
   }


   public Iterator<Token> iteratorOfTokens()
   {
      return ((this.tokens == null) ? Collections.<Token>emptyList().iterator()
            : this.tokens.iterator());
   }


   public int sizeOfTokens()
   {
      return ((this.tokens == null) ? 0 : this.tokens.size());
   }


   public boolean removeFromTokens(Token value)
   {
      boolean changed = false;
      if ((this.tokens != null) && (value != null))
      {
         changed = this.tokens.remove(value);
         if (changed)
         {
            value.setState(null);
         }
      }
      return changed;
   }


   public void removeAllFromTokens()
   {
      Token tmpValue;
      Iterator<Token> iter = iteratorOfTokens();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         removeFromTokens(tmpValue);
      }
   }


   public void methodCalled(TGMethodCall methodCall)
   {
      // delegate to all combinations of tokens and transitions
      if (sizeOfTokens() > 0 && sizeOfOutgoingTransitions() > 0)
      {
         Iterator<Token> tokenIter = iteratorOfTokens();
         while (tokenIter.hasNext())
         {
            Token token = tokenIter.next();
            Iterator<Transition> transitionsIter = iteratorOfOutgoingTransitions();
            while (!token.isMoved() && transitionsIter.hasNext())
            {
               Transition transition = (Transition) transitionsIter.next();
               transition.accept(methodCall, token);

               resetTokensInNextState(transition);

            }
         }
      }

      resetTokensMoved();
   }


   /**
    * This method resets the 'moved' flag for tokens in the target state of a transition.
    * 
    * If the next state has already been processed, reset the
    * token. If not, don't touch the token so he does not get moved again.
    * 
    * Tokens in the rejecting state need not be reset because they cannot leave that state anymore.
    * 
    * @param transition
    */
   private void resetTokensInNextState(Transition transition)
   {

      if (!transition.isConnectedToRejectingState())
      {
         if ((new Integer(transition.getNextState().hashCode()))
               .compareTo(transition.getPreviousState().hashCode()) < 0)
         // see AbstractAutomaton.addToStates(AbstractState)
         {
            resetTokensMoved(((DFAState) transition.getNextState()));
         }
      }
   }


   private void resetTokensMoved(DFAState state)
   {
      Iterator<Token> iter = state.iteratorOfTokens();
      while (iter.hasNext())
      {
         Token token = iter.next();
         token.setMoved(false);
      }
   }


   private void resetTokensMoved()
   {
      resetTokensMoved(this);
   }


   /**
    * @see org.reclipse.behavior.inference.automaton.AbstractState#removeYou()
    */
   @Override
   public void removeYou()
   {
      removeAllFromNFAStates();
      removeAllFromTokens();

      super.removeYou();
   }

}
