package org.reclipse.tracer.model.definition;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                -------- 1         callers         1 
 * ConsiderMethod | name |----------------------------- CallerClass
 *                -------- considerMethod      callers 
 * 
 *                  0..1   parameters   0..n
 *  ConsiderMethod -------------------------- Parameter
 *                  method        parameters
 * 
 *                  1     methods     1 -------------------
 *  ConsiderMethod ---------------------| methodSignature | ConsiderClass
 *                  methods      parent -------------------
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class ConsiderMethod implements Comparable<ConsiderMethod>
{

   private String name;


   public String getName()
   {
      return this.name;
   }


   public void setName(final String value)
   {
      if (this.name != value)
      {
         this.name = value;
      }
   }


   private boolean constructor;


   public boolean isConstructor()
   {
      return this.constructor;
   }


   public void setConstructor(final boolean constructor)
   {
      this.constructor = constructor;
   }

   /**
    * <pre>
    *                -------- 1         callers         1 
    * ConsiderMethod | name |----------------------------- CallerClass
    *                -------- considerMethod      callers 
    * </pre>
    */
   private HashMap<String, CallerClass> callers;


   protected boolean addToCallers(final String key, final CallerClass value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.callers == null)
         {
            this.callers = new HashMap<String, CallerClass>();
         }

         final CallerClass oldValue = this.callers.put(key, value);
         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setConsiderMethod(null);
            }
            if (value != null)
            {
               value.setConsiderMethod(this);
            }
            changed = true;
         }

      }
      return changed;
   }


   public boolean addToCallers(final CallerClass value)
   {
      return this.addToCallers(getKeyForCallers(value), value);
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


   public CallerClass getFromCallers(final String key)
   {
      return (((this.callers == null) || (key == null)) ? null
            : (CallerClass) this.callers.get(key));
   }


   public String getKeyForCallers(final CallerClass value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInCallers(final String key, final CallerClass value)
   {
      return ((this.callers != null)
            && (value != null || this.callers.containsKey(key))
            && (key != null) && (this.callers.get(key) == value));
   }


   public boolean hasInCallers(final CallerClass value)
   {
      return (hasInCallers(this.getKeyForCallers(value), value));
   }


   public boolean hasKeyInCallers(final String key)
   {
      return ((this.callers != null) && (key != null) && this.callers
            .containsKey(key));
   }


   public Iterator<CallerClass> iteratorOfCallers()
   {
      return ((this.callers == null) ? Collections.<CallerClass>emptyList().iterator()
            : this.callers.values().iterator());
   }


   public void keyChangedInCallers(final String oldKey, final CallerClass value)
   {
      if ((this.callers != null) && (oldKey != getKeyForCallers(value)))
      {
         CallerClass oldValue = this.callers.get(oldKey);
         if (oldValue == value)
         {
            this.callers.remove(oldKey);
            oldValue = this.callers.put(this.getKeyForCallers(value), value);
            if (oldValue != null)
            {
               oldValue.setConsiderMethod(null);
            }
         }
      }
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


   protected boolean removeFromCallers(final String key, final CallerClass value)
   {
      boolean changed = false;
      if ((this.callers != null) && (key != null))
      {
         final CallerClass oldValue = this.callers.get(key);
         if (oldValue == value
               && (oldValue != null || this.callers.containsKey(key)))
         {
            this.callers.remove(key);
            if (value != null)
            {
               value.setConsiderMethod(null);

               // side effect
               value.removeYou();
            }
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromCallers(final CallerClass value)
   {
      return removeFromCallers(getKeyForCallers(value), value);
   }


   public boolean removeKeyFromCallers(final String key)
   {
      boolean changed = false;
      if ((this.callers != null) && (key != null))
      {
         changed = this.callers.containsKey(key);
         if (changed)
         {

            final CallerClass tmpValue = this.callers.remove(key);
            if (tmpValue != null)
            {
               tmpValue.setConsiderMethod(null);
            }

         }
      }
      return changed;
   }


   public void removeAllFromCallers()
   {
      final Iterator<Entry<String, CallerClass>> iter = entriesOfCallers();
      Map.Entry<String, CallerClass> entry;
      while (iter.hasNext())
      {
         entry = iter.next();
         removeFromCallers(entry.getKey(), entry.getValue());
      }
   }


   /**
    * <pre>
    *                   0..1   parameters   0..n
    *   ConsiderMethod -------------------------- Parameter
    *                   method        parameters
    * </pre>
    */
   private LinkedList<Parameter> parameters;


   public boolean addToParameters(final Parameter value)
   {
      boolean changed = false;
      if (value != null && !hasInParameters(value))
      {
         if (this.parameters == null)
         {
            this.parameters = new LinkedList<Parameter>();
         }
         changed = this.parameters.add(value);
         if (changed)
         {
            value.setMethod(this);

            // side effect: invalid signature
            this.signature = null;
         }
      }
      return changed;
   }


   public boolean hasInParameters(final Parameter value)
   {
      return ((this.parameters != null) && (value != null) && this.parameters
            .contains(value));
   }


   public Iterator<Parameter> iteratorOfParameters()
   {
      return ((this.parameters == null) ? Collections.<Parameter>emptyList().iterator()
            : this.parameters.iterator());
   }


   public List<Parameter> valuesOfParameters()
   {
      return ((this.parameters == null) ? Collections.<Parameter>emptyList()
            : (List<Parameter>) this.parameters.clone());
   }


   public void removeAllFromParameters()
   {
      if (this.parameters != null)
      {
         this.parameters.clear();
      }
   }


   public boolean removeFromParameters(final Parameter value)
   {
      boolean changed = false;
      if ((this.parameters != null) && (value != null))
      {
         changed = this.parameters.remove(value);
         if (changed)
         {
            value.setMethod(null);

            // side effect
            value.removeYou();
         }
      }
      return changed;
   }


   public int sizeOfParameters()
   {
      return ((this.parameters == null) ? 0 : this.parameters.size());
   }


   /**
    * <pre>
    *                  1     methods     1 -------------------
    *  ConsiderMethod ---------------------| methodSignature | ConsiderClass
    *                  methods      parent -------------------
    * </pre>
    */
   private ConsiderClass parent;


   public ConsiderClass getParent()
   {
      return this.parent;
   }


   public boolean setParent(final String partnerKey, final ConsiderClass value)
   {
      boolean changed = false;
      if (this.parent != value)
      {
         final ConsiderClass oldValue = this.parent;

         if (this.parent != null)
         {
            this.parent = null;
            oldValue.removeFromMethods(this);
         }
         this.parent = value;
         if (value != null)
         {
            value.addToMethods(partnerKey, this);
         }
         changed = true;
      }
      return changed;
   }


   private String signature;


   public String getSignature()
   {
      if (this.signature == null)
      {
         final StringBuffer buffer = new StringBuffer(getName());
         buffer.append("(");

         final Iterator<Parameter> iter = iteratorOfParameters();
         while (iter.hasNext())
         {
            final Parameter parameter = iter.next();
            buffer.append(parameter.getType());

            if (iter.hasNext())
            {
               buffer.append(", ");
            }
         }

         buffer.append(")");

         this.signature = buffer.toString();
      }

      return this.signature;
   }


   public void removeYou()
   {
      removeAllFromCallers();
      removeAllFromParameters();

      final ConsiderClass tmpParent = getParent();
      if (tmpParent != null)
      {
         setParent(null, null);
      }
   }


   /**
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(final ConsiderMethod other)
   {
      return getSignature().compareTo(other.getSignature());
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return getSignature();
   }

}
