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
 *                ------------------- 1     methods     1
 *  ConsiderClass | methodSignature |--------------------- ConsiderMethod
 *                ------------------- parent      methods
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class ConsiderClass extends AbstractTraceClass
{
   /**
    * <pre>
    *                ------------------- 1     methods     1
    *  ConsiderClass | methodSignature |--------------------- ConsiderMethod
    *                ------------------- parent      methods
    * </pre>
    */
   private HashMap<String, ConsiderMethod> methods;


   public boolean addToMethods(ConsiderMethod value)
   {
      // use the signature of the method as key
      return addToMethods(value.getSignature(), value);
   }


   public boolean addToMethods(String key, ConsiderMethod value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.methods == null)
         {
            this.methods = new HashMap<String, ConsiderMethod>();
         }
         ConsiderMethod oldValue = this.methods.put(key, value);
         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setParent(null, null);
            }
            if (value != null)
            {
               value.setParent(key, this);
            }
            changed = true;
         }
      }
      return changed;
   }


   public Iterator<Entry<String, ConsiderMethod>> entriesOfMethods()
   {
      return ((this.methods == null) ? Collections
            .<Entry<String, ConsiderMethod>> emptySet().iterator()
            : this.methods.entrySet().iterator());
   }


   public Collection<ConsiderMethod> valuesOfMethods()
   {
      return ((this.methods == null) ? Collections.<ConsiderMethod> emptySet()
            : this.methods.values());
   }


   public ConsiderMethod getFromMethods(String key)
   {
      return (((this.methods == null) || (key == null)) ? null
            : (ConsiderMethod) this.methods.get(key));
   }


   public boolean hasInMethods(String key, ConsiderMethod value)
   {
      return ((this.methods != null)
            && (value != null || this.methods.containsKey(key))
            && (key != null) && (this.methods.get(key) == value));
   }


   public boolean hasInMethods(ConsiderMethod value)
   {
      return ((this.methods != null) && this.methods.containsValue(value));
   }


   public boolean hasKeyInMethods(String key)
   {
      return ((this.methods != null) && (key != null) && this.methods
            .containsKey(key));
   }


   public Iterator<ConsiderMethod> iteratorOfMethods()
   {
      return ((this.methods == null) ? Collections.<ConsiderMethod> emptyList()
            .iterator() : this.methods.values().iterator());
   }


   public Iterator<String> keysOfMethods()
   {
      return ((this.methods == null) ? Collections.<String> emptyList()
            .iterator() : this.methods.keySet().iterator());
   }


   public void removeAllFromMethods()
   {
      if (this.methods != null)
      {
         this.methods.clear();
      }
   }


   public boolean removeFromMethods(String key, ConsiderMethod value)
   {
      boolean changed = false;
      if ((this.methods != null) && (key != null))
      {
         ConsiderMethod oldValue = this.methods.get(key);
         if (oldValue == value
               && (oldValue != null || this.methods.containsKey(key)))
         {
            this.methods.remove(key);
            if (value != null)
            {
               value.setParent(null, null);

               // side effect
               value.removeYou();
            }
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromMethods(ConsiderMethod value)
   {
      boolean changed = false;
      if (this.methods != null)
      {
         Iterator<Entry<String, ConsiderMethod>> iter = this.entriesOfMethods();
         Map.Entry<String, ConsiderMethod> entry;
         while (iter.hasNext())
         {
            entry = iter.next();
            if (entry.getValue() == value)
            {
               if (this.removeFromMethods(entry.getKey(), value))
               {
                  changed = true;
               }
            }
         }
      }
      return changed;
   }


   public boolean removeKeyFromMethods(String key)
   {
      boolean changed = false;
      if ((this.methods != null) && (key != null))
      {
         changed = this.methods.containsKey(key);
         if (changed)
         {
            ConsiderMethod tmpValue = this.methods.remove(key);
            if (tmpValue != null)
            {
               tmpValue.setParent(null, null);
            }
         }
      }
      return changed;
   }


   public int sizeOfMethods()
   {
      return ((this.methods == null) ? 0 : this.methods.size());
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTraceClass#setTrace(org.reclipse.tracer.input.AbstractTrace)
    */
   @Override
   public boolean setTrace(AbstractTrace value)
   {
      if (value != null && !(value instanceof ConsiderTrace))
      {
         throw new IllegalArgumentException(
               "Argument must be instanceof ConsiderTrace!");
      }
      return super.setTrace(value);
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTraceClass#removeYou()
    */
   @Override
   public void removeYou()
   {
      removeAllFromMethods();

      super.removeYou();
   }

}
