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
 *                -------- 0..n    callers    1
 *  CriticalClass | name |---------------------- CallerClass
 *                -------- callees      callers
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class CriticalClass extends AbstractTraceClass
{
   /**
    * <pre>
    *                -------- 0..n    callers    1
    *  CriticalClass | name |---------------------- CallerClass
    *                -------- callees      callers
    * </pre>
    */
   private HashMap<String, CallerClass> callers;


   public boolean addToCallers(CallerClass value)
   {
      return this.addToCallers(getKeyForCallers(value), value);
   }


   protected boolean addToCallers(String key, CallerClass value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.callers == null)
         {
            this.callers = new HashMap<String, CallerClass>();
         }

         CallerClass oldValue = this.callers.put(key, value);
         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setCriticalClass(null);
            }
            if (value != null)
            {
               value.setCriticalClass(this);
            }
            changed = true;
         }
      }
      return changed;
   }


   public Iterator<Entry<String, CallerClass>> entriesOfCallers()
   {
      return ((this.callers == null) ? Collections
            .<Entry<String, CallerClass>> emptySet().iterator() : this.callers.entrySet()
            .iterator());
   }


   public Collection<CallerClass> valuesOfCallers()
   {
      return ((this.callers == null) ? Collections.<CallerClass>emptyList() : this.callers
            .values());
   }


   public CallerClass getFromCallers(String key)
   {
      return (((this.callers == null) || (key == null)) ? null
            : (CallerClass) this.callers.get(key));
   }


   public String getKeyForCallers(CallerClass value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInCallers(String key, CallerClass value)
   {
      return ((this.callers != null)
            && (value != null || this.callers.containsKey(key))
            && (key != null) && (this.callers.get(key) == value));
   }


   public boolean hasInCallers(CallerClass value)
   {
      return (this.hasInCallers(this.getKeyForCallers(value), value));
   }


   public boolean hasKeyInCallers(String key)
   {
      return ((this.callers != null) && (key != null) && this.callers
            .containsKey(key));
   }


   public Iterator<CallerClass> iteratorOfCallers()
   {
      return ((this.callers == null) ? Collections.<CallerClass>emptyList().iterator()
            : this.callers.values().iterator());
   }


   public Iterator<String> keysOfCallers()
   {
      return ((this.callers == null) ? Collections.<String>emptyList().iterator()
            : this.callers.keySet().iterator());
   }


   public int sizeOfCallers()
   {
      return ((this.callers == null) ? 0 : this.callers.size());
   }


   protected boolean removeFromCallers(String key, CallerClass value)
   {
      boolean changed = false;
      if ((this.callers != null) && (key != null))
      {
         CallerClass oldValue = this.callers.get(key);
         if (oldValue == value
               && (oldValue != null || this.callers.containsKey(key)))
         {
            this.callers.remove(key);
            if (value != null)
            {
               value.setCriticalClass(null);

               // side effect
               value.removeYou();
            }
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromCallers(CallerClass value)
   {
      return removeFromCallers(getKeyForCallers(value), value);
   }


   public boolean removeKeyFromCallers(String key)
   {
      boolean changed = false;
      if ((this.callers != null) && (key != null))
      {
         changed = this.callers.containsKey(key);
         if (changed)
         {
            CallerClass tmpValue = this.callers.remove(key);
            if (tmpValue != null)
            {
               tmpValue.setCriticalClass(null);
            }
         }
      }
      return changed;
   }


   public void removeAllFromCallers()
   {
      if (this.callers != null)
      {
         this.callers.clear();
      }
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTraceClass#setTrace(org.reclipse.tracer.input.AbstractTrace)
    */
   @Override
   public boolean setTrace(AbstractTrace value)
   {
      if (value != null && !(value instanceof CriticalTrace))
      {
         throw new IllegalArgumentException(
               "Argument must be instanceof CriticalTrace!");
      }
      return super.setTrace(value);
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTraceClass#removeYou()
    */
   @Override
   public void removeYou()
   {
      removeAllFromCallers();

      super.removeYou();
   }

}
