package org.reclipse.tracer.model.tracegraph;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *             1          contains           0..* 
 *  TGTracePath ------------------------------------ TGMethodCall
 *             tracePath   {ordered}    methodCalls
 * 
 *            ------ 1      contains       0..* 
 *  TGTracePath | id |---------------------------- TGObject
 *            ------ owningtracePath     objects 
 *
 *            -------- 1   contains    0..* 
 *  TGTracePath | name |---------------------- TGThread
 *            -------- tracePath      threads 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TGTracePath
{
   private long endTime;


   public long getEndTime()
   {
      return this.endTime;
   }


   public void setEndTime(long value)
   {
      if (this.endTime != value)
      {
         this.endTime = value;
      }
   }


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


   private long startTime;


   public long getStartTime()
   {
      return this.startTime;
   }


   public void setStartTime(long value)
   {
      if (this.startTime != value)
      {
         this.startTime = value;
      }
   }

   private TGTrace trace = null;


   public TGTrace getTrace()
   {
      return this.trace;
   }


   public boolean setTrace(TGTrace trace)
   {
      boolean changed = false;
      if (trace != this.trace)
      {
         TGTrace oldTrace = this.trace;
         if (oldTrace != null)
         {
            this.trace = null;
            oldTrace.removeFromTracePath(this);
         }
         this.trace = trace;
         if (trace != null)
         {
            trace.addToTracePaths(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *             1          contains           0..* 
    *  TGTracePath ------------------------------------ TGMethodCall
    *             tracePath   {ordered}    methodCalls 
    * </pre>
    */
   private LinkedList<TGMethodCall> methodCalls;


   public boolean addToMethodCalls(TGMethodCall value)
   {
      boolean changed = false;
      if (value != null && !hasInMethodCalls(value))
      {
         if (this.methodCalls == null)
         {
            this.methodCalls = new LinkedList<TGMethodCall>();
         }
         changed = this.methodCalls.add(value);
         if (changed)
         {
            value.setTracePath(this);
         }
      }
      return changed;
   }


   public boolean hasInMethodCalls(TGMethodCall value)
   {
      return ((this.methodCalls != null) && (value != null) && this.methodCalls
            .contains(value));
   }


   public Iterator<TGMethodCall> iteratorOfMethodCalls()
   {
      return ((this.methodCalls == null) ? Collections
            .<TGMethodCall> emptyList().iterator() : this.methodCalls
            .listIterator());
   }


   public void removeAllFromMethodCalls()
   {
      TGMethodCall tmpValue;
      Iterator<TGMethodCall> iter = this.iteratorOfMethodCalls();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         this.removeFromMethodCalls(tmpValue);
      }
   }


   public boolean removeFromMethodCalls(TGMethodCall value)
   {
      boolean changed = false;
      if ((this.methodCalls != null) && (value != null))
      {
         changed = this.methodCalls.remove(value);
         if (changed)
         {
            value.setTracePath(null);
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
    *           ------ 1        contains        1 
    * TGProcess | id |---------------------------- TGObject
    *           ------ owningProcess      objects 
    * </pre>
    */
   private HashMap<String, TGObject> objects;


   protected boolean addToObjects(Map.Entry<String, TGObject> entry)
   {
      return addToObjects((String) entry.getKey(), (TGObject) entry.getValue());
   }


   protected boolean addToObjects(String key, TGObject value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.objects == null)
         {
            this.objects = new HashMap<String, TGObject>();
         }

         TGObject oldValue = (TGObject) this.objects.put(key, value);
         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setOwningTracePath(null);
            }
            if (value != null)
            {
               value.setOwningTracePath(this);
            }
            changed = true;
         }

      }
      return changed;
   }


   public boolean addToObjects(TGObject value)
   {
      return this.addToObjects(getKeyForObjects(value), value);
   }


   public Iterator<Map.Entry<String, TGObject>> entriesOfObjects()
   {
      return ((this.objects == null) ? Collections
            .<Map.Entry<String, TGObject>> emptyList().iterator()
            : this.objects.entrySet().iterator());
   }


   public TGObject getFromObjects(String key)
   {
      return (((this.objects == null) || (key == null)) ? null
            : (TGObject) this.objects.get(key));
   }


   public String getKeyForObjects(TGObject value)
   {
      return (value == null ? null : value.getId());
   }


   protected boolean hasInObjects(String key, TGObject value)
   {
      return ((this.objects != null)
            && (value != null || this.objects.containsKey(key))
            && (key != null) && (this.objects.get(key) == value));
   }


   public boolean hasInObjects(TGObject value)
   {
      return (this.hasInObjects(this.getKeyForObjects(value), value));
   }


   public boolean hasKeyInObjects(String key)
   {
      return ((this.objects != null) && (key != null) && this.objects
            .containsKey(key));
   }


   public Iterator<TGObject> iteratorOfObjects()
   {
      return ((this.objects == null) ? Collections.<TGObject> emptyList()
            .iterator() : this.objects.values().iterator());
   }


   public void keyChangedInObjects(String oldKey, TGObject value)
   {
      if ((this.objects != null) && (oldKey != getKeyForObjects(value)))
      {
         TGObject oldValue = (TGObject) this.objects.get(oldKey);
         if (oldValue == value)
         {
            this.objects.remove(oldKey);
            oldValue = (TGObject) this.objects.put(
                  this.getKeyForObjects(value), value);
            if (oldValue != null)
            {
               oldValue.setOwningTracePath(null);
            }
         }
      }
   }


   public Iterator<String> keysOfObjects()
   {
      return ((this.objects == null) ? Collections.<String> emptyList()
            .iterator() : this.objects.keySet().iterator());
   }


   public void removeAllFromObjects()
   {
      Iterator<Map.Entry<String, TGObject>> iter = entriesOfObjects();
      Map.Entry<String, TGObject> entry;
      while (iter.hasNext())
      {
         entry = iter.next();
         removeFromObjects((String) entry.getKey(), (TGObject) entry.getValue());
      }
   }


   protected boolean removeFromObjects(String key, TGObject value)
   {
      boolean changed = false;
      if ((this.objects != null) && (key != null))
      {
         TGObject oldValue = (TGObject) this.objects.get(key);
         if (oldValue == value
               && (oldValue != null || this.objects.containsKey(key)))
         {

            this.objects.remove(key);
            if (value != null)
            {
               value.setOwningTracePath(null);
            }
            changed = true;

         }
      }
      return changed;
   }


   public boolean removeFromObjects(TGObject value)
   {
      return removeFromObjects(getKeyForObjects(value), value);
   }


   public boolean removeKeyFromObjects(String key)
   {
      boolean changed = false;
      if ((this.objects != null) && (key != null))
      {
         changed = this.objects.containsKey(key);
         if (changed)
         {

            TGObject tmpValue = (TGObject) this.objects.remove(key);
            if (tmpValue != null)
            {
               tmpValue.setOwningTracePath(null);
            }

         }
      }
      return changed;
   }


   public int sizeOfObjects()
   {
      return ((this.objects == null) ? 0 : this.objects.size());
   }


   /**
    * <pre>
    *           -------- 1   contains    0..1 
    * TGTracePath | name |---------------------- TGThread
    *           -------- process      threads 
    * </pre>
    */
   private HashMap<String, TGThread> threads;


   protected boolean addToThreads(Map.Entry<String, TGThread> entry)
   {
      return addToThreads((String) entry.getKey(), (TGThread) entry.getValue());
   }


   protected boolean addToThreads(String key, TGThread value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.threads == null)
         {
            this.threads = new HashMap<String, TGThread>();
         }

         TGThread oldValue = (TGThread) this.threads.put(key, value);
         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setTracePath(null);
            }
            if (value != null)
            {
               value.setTracePath(this);
            }
            changed = true;
         }

      }
      return changed;
   }


   public boolean addToThreads(TGThread value)
   {
      return this.addToThreads(getKeyForThreads(value), value);
   }


   public Iterator<Map.Entry<String, TGThread>> entriesOfThreads()
   {
      return ((this.threads == null) ? Collections
            .<Map.Entry<String, TGThread>> emptyList().iterator()
            : this.threads.entrySet().iterator());
   }


   public TGThread getFromThreads(String key)
   {
      return (((this.threads == null) || (key == null)) ? null
            : (TGThread) this.threads.get(key));
   }


   public String getKeyForThreads(TGThread value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInThreads(String key, TGThread value)
   {
      return ((this.threads != null)
            && (value != null || this.threads.containsKey(key))
            && (key != null) && (this.threads.get(key) == value));
   }


   public boolean hasInThreads(TGThread value)
   {
      return (this.hasInThreads(this.getKeyForThreads(value), value));
   }


   public boolean hasKeyInThreads(String key)
   {
      return ((this.threads != null) && (key != null) && this.threads
            .containsKey(key));
   }


   public Iterator<TGThread> iteratorOfThreads()
   {
      return ((this.threads == null) ? Collections.<TGThread> emptyList()
            .iterator() : this.threads.values().iterator());
   }


   public void keyChangedInThreads(String oldKey, TGThread value)
   {
      if ((this.threads != null) && (oldKey != getKeyForThreads(value)))
      {
         TGThread oldValue = (TGThread) this.threads.get(oldKey);
         if (oldValue == value)
         {
            this.threads.remove(oldKey);
            oldValue = (TGThread) this.threads.put(
                  this.getKeyForThreads(value), value);
            if (oldValue != null)
            {
               oldValue.setTracePath(null);
            }
         }
      }
   }


   public Iterator<String> keysOfThreads()
   {
      return ((this.threads == null) ? Collections.<String> emptyList()
            .iterator() : this.threads.keySet().iterator());
   }


   public void removeAllFromThreads()
   {
      Iterator<Map.Entry<String, TGThread>> iter = entriesOfThreads();
      Map.Entry<String, TGThread> entry;
      while (iter.hasNext())
      {
         entry = iter.next();
         removeFromThreads((String) entry.getKey(), (TGThread) entry.getValue());
      }
   }


   protected boolean removeFromThreads(String key, TGThread value)
   {
      boolean changed = false;
      if ((this.threads != null) && (key != null))
      {
         TGThread oldValue = (TGThread) this.threads.get(key);
         if (oldValue == value
               && (oldValue != null || this.threads.containsKey(key)))
         {

            this.threads.remove(key);
            if (value != null)
            {
               value.setTracePath(null);
            }
            changed = true;

         }
      }
      return changed;
   }


   public boolean removeFromThreads(TGThread value)
   {
      return removeFromThreads(getKeyForThreads(value), value);
   }


   public boolean removeKeyFromThreads(String key)
   {
      boolean changed = false;
      if ((this.threads != null) && (key != null))
      {
         changed = this.threads.containsKey(key);
         if (changed)
         {

            TGThread tmpValue = (TGThread) this.threads.remove(key);
            if (tmpValue != null)
            {
               tmpValue.setTracePath(null);
            }

         }
      }
      return changed;
   }


   public int sizeOfThreads()
   {
      return ((this.threads == null) ? 0 : this.threads.size());
   }


   // Types for ClassLoaded
   private List<TGType> loadedClasses = new LinkedList<TGType>();


   public boolean addToLoadedClasses(TGType type)
   {
      if (type != null)
         return this.loadedClasses.add(type);
      return false;
   }


   public boolean hasInLoadedClasses(TGType type)
   {
      return (type != null && this.loadedClasses.contains(type));
   }


   public Iterator<TGType> iteratorOfLoadedClasses()
   {
      return this.loadedClasses.iterator();
   }


   public void removeAllFromLoadedClasses()
   {
      for (Iterator<TGType> typeIter = this.iteratorOfLoadedClasses(); typeIter
            .hasNext();)
         this.removeFromLoadedClasses(typeIter.next());

   }


   public boolean removeFromLoadedClasses(TGType type)
   {
      if (type != null)
         return this.loadedClasses.remove(type);
      return false;
   }


   public int sizeOfLoadedClasses()
   {
      return this.loadedClasses.size();
   }


   public void removeYou()
   {
      removeAllFromObjects();
      removeAllFromThreads();
      removeAllFromMethodCalls();
      removeAllFromLoadedClasses();

      if (this.getTrace() != null)
         this.setTrace(null);
   }

}
