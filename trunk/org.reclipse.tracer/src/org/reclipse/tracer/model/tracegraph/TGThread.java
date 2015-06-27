package org.reclipse.tracer.model.tracegraph;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TGThread
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
    *          ------ 1              contains               1 
    * TGThread | id |----------------------------------------- TGMethodCall
    *          ------ owningThread   {ordered,}   methodCalls 
    * </pre>
    */
   private HashMap<String, TGMethodCall> methodCalls;


   protected boolean addToMethodCalls(Map.Entry<String, TGMethodCall> entry)
   {
      return addToMethodCalls((String) entry.getKey(),
            (TGMethodCall) entry.getValue());
   }


   protected boolean addToMethodCalls(String key, TGMethodCall value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.methodCalls == null)
         {
            this.methodCalls = new HashMap<String, TGMethodCall>();
         }

         TGMethodCall oldValue = (TGMethodCall) this.methodCalls
               .put(key, value);
         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setOwningThread(null);
            }
            if (value != null)
            {
               value.setOwningThread(this);
            }
            changed = true;
         }

      }
      return changed;
   }


   public boolean addToMethodCalls(TGMethodCall value)
   {
      return this.addToMethodCalls(getKeyForMethodCalls(value), value);
   }


   public Iterator<Map.Entry<String, TGMethodCall>> entriesOfMethodCalls()
   {
      return ((this.methodCalls == null) ? Collections
            .<Map.Entry<String, TGMethodCall>> emptyList().iterator()
            : this.methodCalls.entrySet().iterator());
   }


   public TGMethodCall getFromMethodCalls(String key)
   {
      return (((this.methodCalls == null) || (key == null)) ? null
            : (TGMethodCall) this.methodCalls.get(key));
   }


   public String getKeyForMethodCalls(TGMethodCall value)
   {
      return (value == null ? null : value.getId());
   }


   protected boolean hasInMethodCalls(String key, TGMethodCall value)
   {
      return ((this.methodCalls != null)
            && (value != null || this.methodCalls.containsKey(key))
            && (key != null) && (this.methodCalls.get(key) == value));
   }


   public boolean hasInMethodCalls(TGMethodCall value)
   {
      return (this.hasInMethodCalls(this.getKeyForMethodCalls(value), value));
   }


   public boolean hasKeyInMethodCalls(String key)
   {
      return ((this.methodCalls != null) && (key != null) && this.methodCalls
            .containsKey(key));
   }


   public Iterator<TGMethodCall> iteratorOfMethodCalls()
   {
      return ((this.methodCalls == null) ? Collections
            .<TGMethodCall> emptyList().iterator() : this.methodCalls.values()
            .iterator());
   }


   public void keyChangedInMethodCalls(String oldKey, TGMethodCall value)
   {
      if ((this.methodCalls != null) && (oldKey != getKeyForMethodCalls(value)))
      {
         TGMethodCall oldValue = (TGMethodCall) this.methodCalls.get(oldKey);
         if (oldValue == value)
         {
            this.methodCalls.remove(oldKey);
            oldValue = (TGMethodCall) this.methodCalls.put(
                  this.getKeyForMethodCalls(value), value);
            if (oldValue != null)
            {
               oldValue.setOwningThread(null);
            }
         }
      }
   }


   public Iterator<String> keysOfMethodCalls()
   {
      return ((this.methodCalls == null) ? Collections.<String>emptyList().iterator()
            : this.methodCalls.keySet().iterator());
   }


   public void removeAllFromMethodCalls()
   {
      Iterator<Map.Entry<String, TGMethodCall>> iter = entriesOfMethodCalls();
      Map.Entry<String, TGMethodCall> entry;
      while (iter.hasNext())
      {
         entry = iter.next();
         removeFromMethodCalls((String) entry.getKey(),
               (TGMethodCall) entry.getValue());
      }
   }


   protected boolean removeFromMethodCalls(String key, TGMethodCall value)
   {
      boolean changed = false;
      if ((this.methodCalls != null) && (key != null))
      {
         TGMethodCall oldValue = (TGMethodCall) this.methodCalls.get(key);
         if (oldValue == value
               && (oldValue != null || this.methodCalls.containsKey(key)))
         {

            this.methodCalls.remove(key);
            if (value != null)
            {
               value.setOwningThread(null);
            }
            changed = true;

         }
      }
      return changed;
   }


   public boolean removeFromMethodCalls(TGMethodCall value)
   {
      return removeFromMethodCalls(getKeyForMethodCalls(value), value);
   }


   public boolean removeKeyFromMethodCalls(String key)
   {
      boolean changed = false;
      if ((this.methodCalls != null) && (key != null))
      {
         changed = this.methodCalls.containsKey(key);
         if (changed)
         {

            TGMethodCall tmpValue = (TGMethodCall) this.methodCalls.remove(key);
            if (tmpValue != null)
            {
               tmpValue.setOwningThread(null);
            }

         }
      }
      return changed;
   }


   public int sizeOfMethodCalls()
   {
      return ((this.methodCalls == null) ? 0 : this.methodCalls.size());
   }

   /**
    * <pre>
    *           0..1   contains    1 --------
    * TGThread ----------------------| name | TGTracePath
    *           threads      process --------
    * </pre>
    */
   private TGTracePath tracePath;


   public TGTracePath getTracePath()
   {
      return this.tracePath;
   }


   public boolean setTracePath(TGTracePath value)
   {
      boolean changed = false;
      if (this.tracePath != value)
      {
         TGTracePath oldValue = this.tracePath;

         if (this.tracePath != null)
         {
            this.tracePath = null;
            oldValue.removeFromThreads(this);
         }
         this.tracePath = value;
         if (value != null)
         {
            value.addToThreads(this);
         }
         changed = true;

      }
      return changed;
   }


   public void removeYou()
   {
      removeAllFromMethodCalls();
      TGTracePath tmpTracePath = getTracePath();
      if (tmpTracePath != null)
      {
         setTracePath(null);
      }
   }

}
