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
public class BehavioralPatternVariable
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


   /**
    * <pre>
    *                           -------       consideredClasses            1 
    * BehavioralPatternVariable | name |------------------------------------> ClassVariable
    *                           --------                   consideredClasses 
    * </pre>
    */
   private HashMap<String, ClassVariable> consideredClasses;


   protected boolean addToConsideredClasses(String key, ClassVariable value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.consideredClasses == null)
         {
            this.consideredClasses = new HashMap<String, ClassVariable>();
         }
         ClassVariable oldValue = (ClassVariable) this.consideredClasses.put(
               key, value);
         if (oldValue != value)
         {
            changed = true;
         }
      }
      return changed;
   }


   public boolean addToConsideredClasses(ClassVariable value)
   {
      return addToConsideredClasses(getKeyForConsideredClasses(value), value);
   }


   public Iterator entriesOfConsideredClasses()
   {
      return ((this.consideredClasses == null) ? Collections.emptySet().iterator()
            : this.consideredClasses.entrySet().iterator());
   }


   public ClassVariable getFromConsideredClasses(String key)
   {
      return (((this.consideredClasses == null) || (key == null)) ? null
            : (ClassVariable) this.consideredClasses.get(key));
   }


   public String getKeyForConsideredClasses(ClassVariable value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInConsideredClasses(String key, ClassVariable value)
   {
      return ((this.consideredClasses != null)
            && (value != null || this.consideredClasses.containsKey(key))
            && (key != null) && (this.consideredClasses.get(key) == value));
   }


   public boolean hasInConsideredClasses(ClassVariable value)
   {
      return (hasInConsideredClasses(getKeyForConsideredClasses(value), value));
   }


   public boolean hasKeyInConsideredClasses(String key)
   {
      return ((this.consideredClasses != null) && (key != null) && this.consideredClasses
            .containsKey(key));
   }


   public Iterator iteratorOfConsideredClasses()
   {
      return ((this.consideredClasses == null) ? Collections.emptyList().iterator()
            : this.consideredClasses.values().iterator());
   }


   public void keyChangedInConsideredClasses(String oldKey, ClassVariable value)
   {
      if ((this.consideredClasses != null)
            && (oldKey != getKeyForConsideredClasses(value)))
      {
         ClassVariable oldValue = (ClassVariable) this.consideredClasses
               .get(oldKey);
         if (oldValue == value)
         {
            this.consideredClasses.remove(oldKey);
            this.consideredClasses
                  .put(getKeyForConsideredClasses(value), value);
         }
      }
   }


   public Iterator keysOfConsideredClasses()
   {
      return ((this.consideredClasses == null) ? Collections.emptyList().iterator()
            : this.consideredClasses.keySet().iterator());
   }


   public void removeAllFromConsideredClasses()
   {
      Iterator iter = entriesOfConsideredClasses();
      Map.Entry entry;
      while (iter.hasNext())
      {
         entry = (Map.Entry) iter.next();
         removeFromConsideredClasses((String) entry.getKey(),
               (ClassVariable) entry.getValue());
      }
   }


   protected boolean removeFromConsideredClasses(String key, ClassVariable value)
   {
      boolean changed = false;
      if ((this.consideredClasses != null) && (key != null))
      {
         ClassVariable oldValue = (ClassVariable) this.consideredClasses
               .get(key);
         if (oldValue == value
               && (oldValue != null || this.consideredClasses.containsKey(key)))
         {
            this.consideredClasses.remove(key);
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromConsideredClasses(ClassVariable value)
   {
      return removeFromConsideredClasses(getKeyForConsideredClasses(value),
            value);
   }


   public boolean removeKeyFromConsideredClasses(String key)
   {
      boolean changed = false;
      if ((this.consideredClasses != null) && (key != null))
      {
         changed = this.consideredClasses.containsKey(key);
         if (changed)
         {
            this.consideredClasses.remove(key);
         }
      }
      return changed;
   }


   public int sizeOfConsideredClasses()
   {
      return ((this.consideredClasses == null) ? 0 : this.consideredClasses
            .size());
   }


   /**
    * <pre>
    *                           --------         criticalClasses           1 
    * BehavioralPatternVariable | name |------------------------------------> ClassVariable
    *                           --------                     criticalClasses 
    * </pre>
    */
   private HashMap<String, ClassVariable> criticalClasses;


   protected boolean addToCriticalClasses(Map.Entry<String, ClassVariable> entry)
   {
      return addToCriticalClasses((String) entry.getKey(),
            (ClassVariable) entry.getValue());
   }


   protected boolean addToCriticalClasses(String key, ClassVariable value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.criticalClasses == null)
         {
            this.criticalClasses = new HashMap<String, ClassVariable>();
         }
         ClassVariable oldValue = (ClassVariable) this.criticalClasses.put(key,
               value);
         if (oldValue != value)
         {
            changed = true;
         }
      }
      return changed;
   }


   public boolean addToCriticalClasses(ClassVariable value)
   {
      return addToCriticalClasses(getKeyForCriticalClasses(value), value);
   }


   public Iterator entriesOfCriticalClasses()
   {
      return ((this.criticalClasses == null) ? Collections.emptySet().iterator()
            : this.criticalClasses.entrySet().iterator());
   }


   public ClassVariable getFromCriticalClasses(String key)
   {
      return (((this.criticalClasses == null) || (key == null)) ? null
            : (ClassVariable) this.criticalClasses.get(key));
   }


   public String getKeyForCriticalClasses(ClassVariable value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInCriticalClasses(String key, ClassVariable value)
   {
      return ((this.criticalClasses != null)
            && (value != null || this.criticalClasses.containsKey(key))
            && (key != null) && (this.criticalClasses.get(key) == value));
   }


   public boolean hasInCriticalClasses(ClassVariable value)
   {
      return (hasInCriticalClasses(getKeyForCriticalClasses(value), value));
   }


   public boolean hasKeyInCriticalClasses(String key)
   {
      return ((this.criticalClasses != null) && (key != null) && this.criticalClasses
            .containsKey(key));
   }


   public Iterator iteratorOfCriticalClasses()
   {
      return ((this.criticalClasses == null) ? Collections.emptyList().iterator()
            : this.criticalClasses.values().iterator());
   }


   public void keyChangedInCriticalClasses(String oldKey, ClassVariable value)
   {
      if ((this.criticalClasses != null)
            && (oldKey != getKeyForCriticalClasses(value)))
      {
         ClassVariable oldValue = (ClassVariable) this.criticalClasses
               .get(oldKey);
         if (oldValue == value)
         {
            this.criticalClasses.remove(oldKey);
            this.criticalClasses.put(getKeyForCriticalClasses(value), value);
         }
      }
   }


   public Iterator keysOfCriticalClasses()
   {
      return ((this.criticalClasses == null) ? Collections.emptySet().iterator()
            : this.criticalClasses.keySet().iterator());
   }


   public void removeAllFromCriticalClasses()
   {
      Iterator iter = entriesOfCriticalClasses();
      Map.Entry entry;
      while (iter.hasNext())
      {
         entry = (Map.Entry) iter.next();
         removeFromCriticalClasses((String) entry.getKey(),
               (ClassVariable) entry.getValue());
      }
   }


   protected boolean removeFromCriticalClasses(String key, ClassVariable value)
   {
      boolean changed = false;
      if ((this.criticalClasses != null) && (key != null))
      {
         ClassVariable oldValue = (ClassVariable) this.criticalClasses.get(key);
         if (oldValue == value
               && (oldValue != null || this.criticalClasses.containsKey(key)))
         {
            this.criticalClasses.remove(key);
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromCriticalClasses(ClassVariable value)
   {
      return removeFromCriticalClasses(getKeyForCriticalClasses(value), value);
   }


   public boolean removeKeyFromCriticalClasses(String key)
   {
      boolean changed = false;
      if ((this.criticalClasses != null) && (key != null))
      {
         changed = this.criticalClasses.containsKey(key);
         if (changed)
         {
            this.criticalClasses.remove(key);
         }
      }
      return changed;
   }


   public int sizeOfCriticalClasses()
   {
      return ((this.criticalClasses == null) ? 0 : this.criticalClasses.size());
   }


   public void removeYou()
   {
      removeAllFromCriticalClasses();
      removeAllFromConsideredClasses();
   }

}
