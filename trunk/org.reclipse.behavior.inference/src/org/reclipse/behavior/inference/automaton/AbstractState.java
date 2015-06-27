package org.reclipse.behavior.inference.automaton;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                0..n     states     1 
 * AbstractState ----------------------- AbstractAutomaton
 *                states      automaton
 *                 
 *                1     incomingTransitions     0..n 
 * AbstractState ------------------------------------ Transition
 *                nextState      incomingTransitions
 *                 
 *                1       outgoingTransitions       0..n 
 * AbstractState ---------------------------------------- Transition
 *                previousState      outgoingTransitions 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2369 $ $Date: 2006-06-01 15:19:42 +0200 (Do, 01 Jun 2006) $
 */
public abstract class AbstractState
{

   public static final int NONE = 0;

   public static final int ACCEPT = 1;

   public static final int REJECT = 2;


   private String name;


   public String getName()
   {
      return this.name;
   }


   public void setName(String newValue)
   {
      this.name = newValue;
   }


   private int type = NONE;


   public int getType()
   {
      return this.type;
   }


   public void setType(int value)
   {
      this.type = value;
   }


   /**
    * <pre>
    *                0..n     states     1 
    * AbstractState ----------------------- AbstractAutomaton
    *                states      automaton 
    * </pre>
    */
   private AbstractAutomaton automaton;


   public AbstractAutomaton getAutomaton()
   {
      return this.automaton;
   }


   public boolean setAutomaton(AbstractAutomaton value)
   {
      boolean changed = false;
      if (this.automaton != value)
      {
         AbstractAutomaton oldValue = this.automaton;

         if (this.automaton != null)
         {
            this.automaton = null;
            oldValue.removeFromStates(this);
         }
         this.automaton = value;
         if (value != null)
         {
            value.addToStates(this);
         }
         changed = true;

      }
      return changed;
   }


   /**
    * <pre>
    *                1     incomingTransitions     0..n 
    * AbstractState ------------------------------------ Transition
    *                nextState      incomingTransitions 
    * </pre>
    */
   private HashSet<Transition> incomingTransitions;


   public boolean addToIncomingTransitions(Transition value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.incomingTransitions == null)
         {
            this.incomingTransitions = new HashSet<Transition>();
         }
         changed = this.incomingTransitions.add(value);
         if (changed)
         {
            value.setNextState(this);
         }
      }
      return changed;
   }


   public boolean hasInIncomingTransitions(Transition value)
   {
      return ((this.incomingTransitions != null) && (value != null) && this.incomingTransitions
            .contains(value));
   }


   public Iterator<Transition> iteratorOfIncomingTransitions()
   {
      return ((this.incomingTransitions == null) ? Collections.<Transition>emptyList().iterator()
            : this.incomingTransitions.iterator());
   }


   public int sizeOfIncomingTransitions()
   {
      return ((this.incomingTransitions == null) ? 0 : this.incomingTransitions
            .size());
   }


   public boolean removeFromIncomingTransitions(Transition value)
   {
      boolean changed = false;
      if ((this.incomingTransitions != null) && (value != null))
      {
         changed = this.incomingTransitions.remove(value);
         if (changed)
         {
            value.setNextState(null);

            // side effect
            value.removeYou();
         }
      }
      return changed;
   }


   public void removeAllFromIncomingTransitions()
   {
      Transition tmpValue;
      Iterator<Transition> iter = this.iteratorOfIncomingTransitions();
      while (iter.hasNext())
      {
         tmpValue = (Transition) iter.next();
         this.removeFromIncomingTransitions(tmpValue);
      }
   }


   /**
    * <pre>
    *                1       outgoingTransitions       0..n 
    * AbstractState ---------------------------------------- Transition
    *                previousState      outgoingTransitions 
    * </pre>
    */
   private HashSet<Transition> outgoingTransitions;


   public boolean addToOutgoingTransitions(Transition value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.outgoingTransitions == null)
         {
            this.outgoingTransitions = new HashSet<Transition>();
         }
         changed = this.outgoingTransitions.add(value);
         if (changed)
         {
            value.setPreviousState(this);
         }
      }
      return changed;
   }


   public boolean hasInOutgoingTransitions(Transition value)
   {
      return ((this.outgoingTransitions != null) && (value != null) && this.outgoingTransitions
            .contains(value));
   }


   public Iterator<Transition> iteratorOfOutgoingTransitions()
   {
      return ((this.outgoingTransitions == null) ? Collections.<Transition>emptyList().iterator()
            : this.outgoingTransitions.iterator());
   }


   public int sizeOfOutgoingTransitions()
   {
      return ((this.outgoingTransitions == null) ? 0 : this.outgoingTransitions
            .size());
   }


   public boolean removeFromOutgoingTransitions(Transition value)
   {
      boolean changed = false;
      if ((this.outgoingTransitions != null) && (value != null))
      {
         changed = this.outgoingTransitions.remove(value);
         if (changed)
         {
            value.setPreviousState(null);

            // side effect
            value.removeYou();
         }
      }
      return changed;
   }


   public void removeAllFromOutgoingTransitions()
   {
      Transition tmpValue;
      Iterator<Transition> iter = this.iteratorOfOutgoingTransitions();
      while (iter.hasNext())
      {
         tmpValue = (Transition) iter.next();
         this.removeFromOutgoingTransitions(tmpValue);
      }
   }


   /**
    * Sets all links to other objects to null and removes all incoming and outgoing transitions.
    */
   public void removeYou()
   {
      removeAllFromOutgoingTransitions();
      removeAllFromIncomingTransitions();

      AbstractAutomaton tmpAutomaton = getAutomaton();
      if (tmpAutomaton != null)
      {
         setAutomaton(null);
      }
   }

}
