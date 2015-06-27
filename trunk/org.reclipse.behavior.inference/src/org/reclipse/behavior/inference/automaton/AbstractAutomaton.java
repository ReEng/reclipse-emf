package org.reclipse.behavior.inference.automaton;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import org.reclipse.behavior.inference.automaton.symbols.Epsilon;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                     startState      1 
 * AbstractAutomaton --------------------> AbstractState
 *                            startState
 *                             
 *                    1     states     0..n 
 * AbstractAutomaton ----------------------- AbstractState
 *                    automaton      states
 *                      
 *                   ---------------        symbols     1 
 * AbstractAutomaton | getSymbol() |----------------------> AbstractInputSymbol
 *                   ---------------              symbols 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4536 $ $Date: 2010-09-20 14:20:15 +0200 (Mo, 20 Sep 2010) $
 */
public abstract class AbstractAutomaton
{

   private HashSet<Assignment> assignments;


   public HashSet<Assignment> getAssignments()
   {
      return assignments;
   }


   public void setAssignments(HashSet<Assignment> assignments)
   {
      this.assignments = assignments;
   }


   public boolean addToAssignments(Assignment value)
   {
      boolean changed = false;
      if (this.assignments == null)
      {
         this.assignments = new HashSet<Assignment>();
      }
      changed = this.assignments.add(value);
      if (changed)
      {
         value.setAutomaton(this);
      }
      return changed;
   }


   public boolean hasInAssignments(Assignment value)
   {
      return ((this.assignments != null) && (value != null) && this.assignments
            .contains(value));
   }


   public Iterator<Assignment> iteratorOfAssignments()
   {
      return ((this.assignments == null) ? Collections.<Assignment>emptyList().iterator()
            : this.assignments.iterator());
   }


   public void removeAllFromAssignments()
   {
      Assignment tmpValue;
      Iterator<Assignment> iter = this.iteratorOfAssignments();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         this.removeFromAssignments(tmpValue);

         // side effect
         tmpValue.removeYou();
      }
   }


   public boolean removeFromAssignments(Assignment value)
   {
      boolean changed = false;
      if ((this.assignments != null) && (value != null))
      {
         changed = this.assignments.remove(value);
         if (changed)
         {
            value.setAutomaton(null);
         }
      }
      return changed;
   }


   public int sizeOfAssignments()
   {
      return ((this.assignments == null) ? 0 : this.assignments.size());
   }

   /**
    * <pre>
    *                     startState      1 
    * AbstractAutomaton --------------------> AbstractState
    *                            startState 
    * </pre>
    */
   private AbstractState startState;


   public AbstractState getStartState()
   {
      return this.startState;
   }


   public boolean setStartState(AbstractState value)
   {
      boolean changed = false;
      if (this.startState != value)
      {
         this.startState = value;
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                     1     states     0..n 
    *  AbstractAutomaton ----------------------- AbstractState
    *                     automaton      states 
    * </pre>
    */
   private TreeSet<AbstractState> states;


   public boolean addToStates(AbstractState value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.states == null)
         {
            this.states = new TreeSet<AbstractState>(
                  new Comparator<AbstractState>()
                  {
                     public int compare(AbstractState o1, AbstractState o2)
                     {
                        return (new Integer(o1.hashCode())).compareTo(o2
                              .hashCode());
                     }
                  });
         }
         changed = this.states.add(value);
         if (changed)
         {
            value.setAutomaton(this);
         }
      }
      return changed;
   }


   public boolean hasInStates(AbstractState value)
   {
      return ((this.states != null) && (value != null) && this.states
            .contains(value));
   }


   public Iterator<AbstractState> iteratorOfStates()
   {
      return ((this.states == null) ? Collections.<AbstractState>emptyList().iterator()
            : this.states.iterator());
   }


   public void removeAllFromStates()
   {
      AbstractState tmpValue;
      Iterator<AbstractState> iter = this.iteratorOfStates();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         this.removeFromStates(tmpValue);

         // side effect
         tmpValue.removeYou();
      }
   }


   public boolean removeFromStates(AbstractState value)
   {
      boolean changed = false;
      if ((this.states != null) && (value != null))
      {
         changed = this.states.remove(value);
         if (changed)
         {
            value.setAutomaton(null);
         }
      }
      return changed;
   }


   public int sizeOfStates()
   {
      return ((this.states == null) ? 0 : this.states.size());
   }


   /**
    * <pre>
    *                   ---------------        symbols     1 
    * AbstractAutomaton | getSymbol() |----------------------> AbstractInputSymbol
    *                   ---------------              symbols 
    * </pre>
    */
   private HashMap<String, AbstractSymbol> symbols;


   protected void setSymbols(HashMap<String, AbstractSymbol> symbols)
   {
      this.symbols = symbols;
   }


   /**
    * @return a clone of the symbols hashmap
    */
   @SuppressWarnings("unchecked")
   protected HashMap<String, AbstractSymbol> getSymbols()
   {
      if (this.symbols == null)
      {
         this.symbols = new HashMap<String, AbstractSymbol>();
      }

      return (HashMap<String, AbstractSymbol>) this.symbols.clone();
   }


   /**
    * The Epsilon symbol is not allowed to be added to the symbols of the automaton.
    * 
    * @throws IllegalArgumentException if Epsilon symbol is given as argument.
    */
   public boolean addToSymbols(AbstractSymbol value)
   {
      if (value instanceof Epsilon)
      {
         throw new IllegalArgumentException(
               "The Epsilon symbol is not allowed to be added.");
      }

      boolean changed = false;
      if (this.symbols == null)
      {
         this.symbols = new HashMap<String, AbstractSymbol>();
      }
      AbstractSymbol oldValue = this.symbols.put(value.getSymbolText(), value);
      if (oldValue != value)
      {
         changed = true;
      }
      return changed;
   }


   public Iterator<Map.Entry<String, AbstractSymbol>> entriesOfSymbols()
   {
      return ((this.symbols == null) ? Collections.<Map.Entry<String, AbstractSymbol>>emptyList().iterator()
            : this.symbols.entrySet().iterator());
   }


   public AbstractSymbol getFromSymbols(String key)
   {
      return (((this.symbols == null) || (key == null)) ? null
            : (AbstractSymbol) this.symbols.get(key));
   }


   public boolean hasInSymbols(String key, AbstractSymbol value)
   {
      return ((this.symbols != null)
            && (value != null || this.symbols.containsKey(key))
            && (key != null) && (this.symbols.get(key) == value));
   }


   public boolean hasInSymbols(AbstractSymbol value)
   {
      return ((this.symbols != null) && this.symbols.containsValue(value));
   }


   public boolean hasKeyInSymbols(String key)
   {
      return ((this.symbols != null) && (key != null) && this.symbols
            .containsKey(key));
   }


   public Iterator<AbstractSymbol> iteratorOfSymbols()
   {
      return ((this.symbols == null) ? Collections.<AbstractSymbol>emptyList().iterator()
            : this.symbols.values().iterator());
   }


   public Iterator<String> keysOfSymbols()
   {
      return ((this.symbols == null) ? Collections.<String>emptyList().iterator()
            : this.symbols.keySet().iterator());
   }


   public int sizeOfSymbols()
   {
      return ((this.symbols == null) ? 0 : this.symbols.size());
   }


   public boolean removeFromSymbols(String key, AbstractSymbol value)
   {
      boolean changed = false;
      if ((this.symbols != null) && (key != null))
      {
         AbstractSymbol oldValue = this.symbols.get(key);
         if (oldValue == value
               && (oldValue != null || this.symbols.containsKey(key)))
         {
            this.symbols.remove(key);
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromSymbols(AbstractSymbol value)
   {
      boolean changed = false;
      if (this.symbols != null)
      {
         Iterator<?> iter = this.entriesOfSymbols();
         Map.Entry<?, ?> entry;
         while (iter.hasNext())
         {
            entry = (Map.Entry<?, ?>) iter.next();
            if (entry.getValue() == value)
            {
               if (this.removeFromSymbols((String) entry.getKey(), value))
               {
                  changed = true;
               }
            }
         }
      }
      return changed;
   }


   public void removeAllFromSymbols()
   {
      Iterator<?> iter = entriesOfSymbols();
      Map.Entry<?, ?> entry;
      while (iter.hasNext())
      {
         entry = (Map.Entry<?, ?>) iter.next();
         removeFromSymbols((String) entry.getKey(), (AbstractSymbol) entry
               .getValue());
      }
   }


   public void removeYou()
   {
      removeAllFromAssignments();
      removeAllFromStates();
      removeAllFromSymbols();

      AbstractState tmpStartState = getStartState();
      if (tmpStartState != null)
      {
         setStartState(null);
      }
   }

}
