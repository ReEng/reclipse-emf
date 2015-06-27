package org.reclipse.tracer.model.definition;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                -------- 0..1   classes   0..1
 *  AbstractTrace | name |----------------------- AbstractTraceClass
 *                -------- trace         classes
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public abstract class AbstractTrace
{

   /**
    * <pre>
    *                -------- 0..1   classes   0..1
    *  AbstractTrace | name |----------------------- AbstractTraceClass
    *                -------- trace         classes
    * </pre>
    */
   private HashMap<String, AbstractTraceClass> classes;


   protected boolean addToClasses(String key, AbstractTraceClass value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.classes == null)
         {
            this.classes = new HashMap<String, AbstractTraceClass>();
         }
         AbstractTraceClass oldValue = this.classes.put(
               key, value);
         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setTrace(null);
            }
            if (value != null)
            {
               value.setTrace(this);
            }
            changed = true;
         }
      }
      return changed;
   }


   public boolean addToClasses(AbstractTraceClass value)
   {
      return this.addToClasses(getKeyForClasses(value), value);
   }


   public Iterator<Entry<String, AbstractTraceClass>> entriesOfClasses()
   {
      return ((this.classes == null) ? Collections.<Entry<String, AbstractTraceClass>>emptyList().iterator() : this.classes
            .entrySet().iterator());
   }


   public Collection<AbstractTraceClass> valuesOfClasses()
   {
      return ((this.classes == null) ? Collections.<AbstractTraceClass>emptyList() : this.classes
            .values());
   }


   public AbstractTraceClass getFromClasses(String key)
   {
      return (((this.classes == null) || (key == null)) ? null
            : (AbstractTraceClass) this.classes.get(key));
   }


   public String getKeyForClasses(AbstractTraceClass value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInClasses(String key, AbstractTraceClass value)
   {
      return ((this.classes != null)
            && (value != null || this.classes.containsKey(key))
            && (key != null) && (this.classes.get(key) == value));
   }


   public boolean hasInClasses(AbstractTraceClass value)
   {
      return (this.hasInClasses(this.getKeyForClasses(value), value));
   }


   public boolean hasKeyInClasses(String key)
   {
      return ((this.classes != null) && (key != null) && this.classes
            .containsKey(key));
   }


   public Iterator<AbstractTraceClass> iteratorOfClasses()
   {
      return ((this.classes == null) ? Collections.<AbstractTraceClass>emptyList().iterator() : this.classes
            .values().iterator());
   }


   public Iterator<String> keysOfClasses()
   {
      return ((this.classes == null) ? Collections.<String>emptyList().iterator() : this.classes
            .keySet().iterator());
   }


   public void removeAllFromClasses()
   {
      Iterator<Entry<String, AbstractTraceClass>> iter = entriesOfClasses();
      Map.Entry<String, AbstractTraceClass> entry;
      while (iter.hasNext())
      {
         entry = iter.next();
         removeFromClasses(entry.getKey(), entry
               .getValue());
      }
   }


   protected boolean removeFromClasses(String key, AbstractTraceClass value)
   {
      boolean changed = false;
      if ((this.classes != null) && (key != null))
      {
         AbstractTraceClass oldValue = this.classes
               .get(key);
         if (oldValue == value
               && (oldValue != null || this.classes.containsKey(key)))
         {
            this.classes.remove(key);
            if (value != null)
            {
               value.setTrace(null);

               // side effect
               value.removeYou();
            }
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromClasses(AbstractTraceClass value)
   {
      return removeFromClasses(getKeyForClasses(value), value);
   }


   public boolean removeKeyFromClasses(String key)
   {
      boolean changed = false;
      if ((this.classes != null) && (key != null))
      {
         changed = this.classes.containsKey(key);
         if (changed)
         {
            AbstractTraceClass tmpValue = this.classes
                  .remove(key);
            if (tmpValue != null)
            {
               tmpValue.setTrace(null);
            }
         }
      }
      return changed;
   }


   public int sizeOfClasses()
   {
      return ((this.classes == null) ? 0 : this.classes.size());
   }


   public void removeYou()
   {
      removeAllFromClasses();
   }

}
