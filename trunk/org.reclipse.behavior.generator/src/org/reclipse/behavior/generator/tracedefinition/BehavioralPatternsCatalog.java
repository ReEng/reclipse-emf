package org.reclipse.behavior.generator.tracedefinition;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4126 $ $Date: 2009-08-06 15:19:27 +0200 (Do, 06 Aug 2009) $
 */
public class BehavioralPatternsCatalog
{

   /**
    * <pre>
    *                           --------      patterns      1 
    * BehavioralPatternsCatalog | name |---------------------> BehavioralPatternVariable
    *                           --------             patterns 
    * </pre>
    */
   private HashMap<String, BehavioralPatternVariable> patterns;


   protected boolean addToPatterns(String key, BehavioralPatternVariable value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.patterns == null)
         {
            this.patterns = new HashMap<String, BehavioralPatternVariable>();
         }
         BehavioralPatternVariable oldValue = (BehavioralPatternVariable) this.patterns
               .put(key, value);
         if (oldValue != value)
         {
            changed = true;
         }
      }
      return changed;
   }


   public boolean addToPatterns(BehavioralPatternVariable value)
   {
      return addToPatterns(getKeyForPatterns(value), value);
   }


   public Iterator entriesOfPatterns()
   {
      return ((this.patterns == null) ? Collections.emptySet().iterator() : this.patterns
            .entrySet().iterator());
   }


   public BehavioralPatternVariable getFromPatterns(String key)
   {
      return (((this.patterns == null) || (key == null)) ? null
            : (BehavioralPatternVariable) this.patterns.get(key));
   }


   public String getKeyForPatterns(BehavioralPatternVariable value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInPatterns(String key, BehavioralPatternVariable value)
   {
      return ((this.patterns != null)
            && (value != null || this.patterns.containsKey(key))
            && (key != null) && (this.patterns.get(key) == value));
   }


   public boolean hasInPatterns(BehavioralPatternVariable value)
   {
      return (hasInPatterns(getKeyForPatterns(value), value));
   }


   public boolean hasKeyInPatterns(String key)
   {
      return ((this.patterns != null) && (key != null) && this.patterns
            .containsKey(key));
   }


   public Iterator iteratorOfPatterns()
   {
      return ((this.patterns == null) ? Collections.emptyList().iterator() : this.patterns
            .values().iterator());
   }


   public void keyChangedInPatterns(String oldKey,
         BehavioralPatternVariable value)
   {
      if ((this.patterns != null) && (oldKey != getKeyForPatterns(value)))
      {
         BehavioralPatternVariable oldValue = (BehavioralPatternVariable) this.patterns
               .get(oldKey);
         if (oldValue == value)
         {
            this.patterns.remove(oldKey);
            this.patterns.put(getKeyForPatterns(value), value);
         }
      }
   }


   public Iterator keysOfPatterns()
   {
      return ((this.patterns == null) ? Collections.emptySet().iterator() : this.patterns
            .keySet().iterator());
   }


   public void removeAllFromPatterns()
   {
      Iterator iter = entriesOfPatterns();
      Map.Entry entry;
      while (iter.hasNext())
      {
         entry = (Map.Entry) iter.next();
         removeFromPatterns((String) entry.getKey(),
               (BehavioralPatternVariable) entry.getValue());
      }
   }


   protected boolean removeFromPatterns(String key,
         BehavioralPatternVariable value)
   {
      boolean changed = false;
      if ((this.patterns != null) && (key != null))
      {
         BehavioralPatternVariable oldValue = (BehavioralPatternVariable) this.patterns
               .get(key);
         if (oldValue == value
               && (oldValue != null || this.patterns.containsKey(key)))
         {
            this.patterns.remove(key);
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromPatterns(BehavioralPatternVariable value)
   {
      return removeFromPatterns(getKeyForPatterns(value), value);
   }


   public boolean removeKeyFromPatterns(String key)
   {
      boolean changed = false;
      if ((this.patterns != null) && (key != null))
      {
         changed = this.patterns.containsKey(key);
         if (changed)
         {
            this.patterns.remove(key);
         }
      }
      return changed;
   }


   public int sizeOfPatterns()
   {
      return ((this.patterns == null) ? 0 : this.patterns.size());
   }


   public void removeYou()
   {
      removeAllFromPatterns();
   }

}
