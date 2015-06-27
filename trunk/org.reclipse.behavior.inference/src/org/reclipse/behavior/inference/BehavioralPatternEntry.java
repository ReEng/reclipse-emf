package org.reclipse.behavior.inference;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.reclipse.behavior.inference.automaton.DFA;


/**
 * This class represents a behavioral pattern. It defines a DFA and a trigger for the pattern.
 * 
 * <h2>Associations</h2>
 * 
 * <pre>
 *                               automaton      1 
 * BehavioralPatternEntry ------------------------> DFA
 *                                      automaton 
 *                     
 *                            0..1   entries   1..n 
 * BehavioralPatternsCatalog ----------------------- BehavioralPatternEntry
 *                            catalog       entries 
 *
 *                         1     triggers      n 
 * BehavioralPatternEntry ----------------------- Trigger
 *                         pattern      triggers 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3374 $ $Date: 2007-02-14 18:33:35 +0100 (Mi, 14 Feb 2007) $
 */
public class BehavioralPatternEntry
{

   private String name;


   public String getName()
   {
      return this.name;
   }


   public void setName(String value)
   {
      if (this.name != value)
      {
         this.name = value;
      }
   }

   private boolean negative = false;


   public boolean isNegative()
   {
      return this.negative;
   }


   public void setNegative(boolean value)
   {
      if (this.negative != value)
      {
         this.negative = value;
      }
   }


   /**
    * <pre>
    *                               automaton      1 
    * BehavioralPatternEntry ------------------------> DFA
    *                                      automaton 
    * </pre>
    */
   private DFA automaton;


   public DFA getAutomaton()
   {
      return this.automaton;
   }


   public boolean setAutomaton(DFA value)
   {
      boolean changed = false;
      if (this.automaton != value)
      {
         this.automaton = value;
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                            0..1   entries   1..n 
    * BehavioralPatternsCatalog ----------------------- BehavioralPatternEntry
    *                            catalog       entries 
    * </pre>
    */
   private BehavioralPatternsCatalog catalog;


   public BehavioralPatternsCatalog getCatalog()
   {
      return this.catalog;
   }


   public boolean setCatalog(BehavioralPatternsCatalog value)
   {
      boolean changed = false;
      if (this.catalog != value)
      {
         BehavioralPatternsCatalog oldValue = this.catalog;
         if (this.catalog != null)
         {
            this.catalog = null;
            oldValue.removeFromEntries(this);
         }
         this.catalog = value;
         if (value != null)
         {
            value.addToEntries(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                         1     triggers      n 
    * BehavioralPatternEntry ----------------------- Trigger
    *                         pattern      triggers 
    * </pre>
    */
   private HashSet<Trigger> triggers;


   public boolean addToTriggers(Trigger value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.triggers == null)
         {
            this.triggers = new HashSet<Trigger>();
         }
         changed = this.triggers.add(value);
         if (changed)
         {
            value.setPattern(this);
         }
      }
      return changed;
   }


   public boolean hasInTriggers(Trigger value)
   {
      return ((this.triggers != null) && (value != null) && this.triggers
            .contains(value));
   }


   public Iterator<Trigger> iteratorOfTriggers()
   {
      return ((this.triggers == null) ? Collections.<Trigger>emptyList().iterator() : this.triggers
            .iterator());
   }


   public int sizeOfTriggers()
   {
      return ((this.triggers == null) ? 0 : this.triggers.size());
   }


   public boolean removeFromTriggers(Trigger value)
   {
      boolean changed = false;
      if ((this.triggers != null) && (value != null))
      {
         changed = this.triggers.remove(value);
         if (changed)
         {
            value.setPattern(null);
         }
      }
      return changed;
   }


   public void removeAllFromTriggers()
   {
      Trigger tmpValue;
      Iterator<Trigger> iter = this.iteratorOfTriggers();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         this.removeFromTriggers(tmpValue);
      }
   }


   public void removeYou()
   {
      removeAllFromTriggers();

      DFA tmpAutomaton = getAutomaton();
      if (tmpAutomaton != null)
      {
         setAutomaton(null);
      }

      BehavioralPatternsCatalog tmpCatalog = getCatalog();
      if (tmpCatalog != null)
      {
         setCatalog(null);
      }
   }

}
