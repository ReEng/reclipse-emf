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
/* package */class ClassVariable
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
    *               --------          methodVariables         1 
    * ClassVariable | name |-----------------------------------> MethodVariable
    *               --------                    methodVariables 
    * </pre>
    */
   private HashMap<String, MethodVariable> methodVariables;


   protected boolean addToMethodVariables(String key, MethodVariable value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.methodVariables == null)
         {
            this.methodVariables = new HashMap<String, MethodVariable>();
         }
         MethodVariable oldValue = (MethodVariable) this.methodVariables.put(
               key, value);
         if (oldValue != value)
         {
            changed = true;
         }
      }
      return changed;
   }


   public boolean addToMethodVariables(MethodVariable value)
   {
      return this.addToMethodVariables(getKeyForMethodVariables(value), value);
   }


   public Iterator entriesOfMethodVariables()
   {
      return ((this.methodVariables == null) ? Collections.emptySet().iterator()
            : this.methodVariables.entrySet().iterator());
   }


   public MethodVariable getFromMethodVariables(String key)
   {
      return (((this.methodVariables == null) || (key == null)) ? null
            : (MethodVariable) this.methodVariables.get(key));
   }


   public String getKeyForMethodVariables(MethodVariable value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInMethodVariables(String key, MethodVariable value)
   {
      return ((this.methodVariables != null)
            && (value != null || this.methodVariables.containsKey(key))
            && (key != null) && (this.methodVariables.get(key) == value));
   }


   public boolean hasInMethodVariables(MethodVariable value)
   {
      return (hasInMethodVariables(getKeyForMethodVariables(value), value));
   }


   public boolean hasKeyInMethodVariables(String key)
   {
      return ((this.methodVariables != null) && (key != null) && this.methodVariables
            .containsKey(key));
   }


   public Iterator iteratorOfMethodVariables()
   {
      return ((this.methodVariables == null) ? Collections.emptyList().iterator()
            : this.methodVariables.values().iterator());
   }


   public void keyChangedInMethodVariables(String oldKey, MethodVariable value)
   {
      if ((this.methodVariables != null)
            && (oldKey != getKeyForMethodVariables(value)))
      {
         MethodVariable oldValue = (MethodVariable) this.methodVariables
               .get(oldKey);
         if (oldValue == value)
         {
            this.methodVariables.remove(oldKey);
            this.methodVariables.put(getKeyForMethodVariables(value), value);
         }
      }
   }


   public Iterator keysOfMethodVariables()
   {
      return ((this.methodVariables == null) ? Collections.emptySet().iterator()
            : this.methodVariables.keySet().iterator());
   }


   public void removeAllFromMethodVariables()
   {
      Iterator iter = entriesOfMethodVariables();
      Map.Entry entry;
      while (iter.hasNext())
      {
         entry = (Map.Entry) iter.next();
         removeFromMethodVariables((String) entry.getKey(),
               (MethodVariable) entry.getValue());
      }
   }


   protected boolean removeFromMethodVariables(String key, MethodVariable value)
   {
      boolean changed = false;
      if ((this.methodVariables != null) && (key != null))
      {
         MethodVariable oldValue = (MethodVariable) this.methodVariables
               .get(key);
         if (oldValue == value
               && (oldValue != null || this.methodVariables.containsKey(key)))
         {
            this.methodVariables.remove(key);
            changed = true;
         }
      }
      return changed;
   }


   public boolean removeFromMethodVariables(MethodVariable value)
   {
      return removeFromMethodVariables(getKeyForMethodVariables(value), value);
   }


   public boolean removeKeyFromMethodVariables(String key)
   {
      boolean changed = false;
      if ((this.methodVariables != null) && (key != null))
      {
         changed = this.methodVariables.containsKey(key);
         if (changed)
         {
            this.methodVariables.remove(key);
         }
      }
      return changed;
   }


   public int sizeOfMethodVariables()
   {
      return ((this.methodVariables == null) ? 0 : this.methodVariables.size());
   }


   public void removeYou()
   {
      removeAllFromMethodVariables();
   }

}
