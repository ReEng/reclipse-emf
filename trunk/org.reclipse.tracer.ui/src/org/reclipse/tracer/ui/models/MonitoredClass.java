package org.reclipse.tracer.ui.models;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3779 $ $Date: 2007-09-07 18:33:49 +0200 (Fr, 07 Sep 2007) $
 */
public class MonitoredClass extends VMEvent implements
      Comparable<MonitoredClass>
{
   public static final int NONE = 0;

   public static final int ABSTRACT = 1;

   public static final int FINAL = 2;


   public MonitoredClass()
   {
      setType(VMEvent.CLASS_LOADED);
      setModifier(NONE);
   }


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


   private int modifier;


   public int getModifier()
   {
      return this.modifier;
   }


   public void setModifier(final int modifier)
   {
      this.modifier = modifier;
   }


   /**
    * <pre>
    *                 ------------- 1   methods    0..1 
    *  MonitoredClass | signature |--------------------- MonitoredMethod
    *                 ------------- parent      methods 
    * </pre>
    */
   private HashMap<String, MonitoredMethod> methods;

   public static final String PROPERTY_METHODS = "MonitoredClass.methods";


   public boolean addToMethods(final MonitoredMethod value)
   {
      final String key = getKeyForMethods(value);
      boolean changed = false;
      if (key != null)
      {
         if (this.methods == null)
         {
            this.methods = new HashMap<String, MonitoredMethod>();
         }
         final MonitoredMethod oldValue = (MonitoredMethod) this.methods.put(
               key, value);

         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setParent(null);
            }
            if (value != null)
            {
               value.setParent(this);
            }

            changed = true;
            fireElementAddedEvent(PROPERTY_METHODS, value);
         }
      }
      return changed;
   }


   public Iterator<Map.Entry<String, MonitoredMethod>> entriesOfMethods()
   {
      return ((this.methods == null) ? Collections.<Map.Entry<String, MonitoredMethod>>emptySet().iterator() : this.methods
            .entrySet().iterator());
   }


   public Collection<MonitoredMethod> valuesOfMethods()
   {
      return ((this.methods == null) ? Collections.<MonitoredMethod>emptyList() : this.methods
            .values());
   }


   public MonitoredMethod getFromMethods(final String key)
   {
      return (((this.methods == null) || (key == null)) ? null
            : (MonitoredMethod) this.methods.get(key));
   }


   public String getKeyForMethods(final MonitoredMethod value)
   {
      return (value == null ? null : value.getSignature());
   }


   protected boolean hasInMethods(final String key, final MonitoredMethod value)
   {
      return ((this.methods != null)
            && (value != null || this.methods.containsKey(key))
            && (key != null) && (this.methods.get(key) == value));
   }


   public boolean hasInMethods(final MonitoredMethod value)
   {
      return (this.hasInMethods(this.getKeyForMethods(value), value));
   }


   public boolean hasKeyInMethods(final String key)
   {
      return ((this.methods != null) && (key != null) && this.methods
            .containsKey(key));
   }


   public Iterator<MonitoredMethod> iteratorOfMethods()
   {
      return ((this.methods == null) ? Collections.<MonitoredMethod>emptyList().iterator() : this.methods
            .values().iterator());
   }


   public Iterator<String> keysOfMethods()
   {
      return ((this.methods == null) ? Collections.<String>emptyList().iterator() : this.methods
            .keySet().iterator());
   }


   public int sizeOfMethods()
   {
      return ((this.methods == null) ? 0 : this.methods.size());
   }


   public boolean removeFromMethods(final MonitoredMethod value)
   {
      final String key = getKeyForMethods(value);
      boolean changed = false;
      if ((this.methods != null) && (key != null))
      {
         final MonitoredMethod oldValue = (MonitoredMethod) this.methods
               .get(key);
         if (oldValue == value
               && (oldValue != null || this.methods.containsKey(key)))
         {
            this.methods.remove(key);
            if (value != null)
            {
               value.setParent(null);

               // side effect
               value.removeYou();
            }
            changed = true;
         }
      }
      return changed;
   }


   public void removeAllFromMethods()
   {
      final Iterator<Map.Entry<String, MonitoredMethod>> iter = entriesOfMethods();
      Map.Entry<String, MonitoredMethod> entry;
      while (iter.hasNext())
      {
         entry = iter.next();
         removeFromMethods((MonitoredMethod) entry.getValue());
      }

      fireAllElementsRemovedEvent(PROPERTY_METHODS);
   }


   /**
    * <pre>
    *                  0..1         classes          1 --------
    *  MonitoredClass ---------------------------------| name | ExecutionMonitorModel
    *                  classes   executionMonitorModel --------
    * </pre>
    */
   private ExecutionMonitorModel executionMonitorModel;


   public ExecutionMonitorModel getExecutionMonitorModel()
   {
      return this.executionMonitorModel;
   }


   public boolean setExecutionMonitorModel(final ExecutionMonitorModel value)
   {
      boolean changed = false;
      if (this.executionMonitorModel != value)
      {
         final ExecutionMonitorModel oldValue = this.executionMonitorModel;

         if (this.executionMonitorModel != null)
         {
            this.executionMonitorModel = null;
            oldValue.removeFromClasses(this);
         }
         this.executionMonitorModel = value;
         if (value != null)
         {
            value.addToClasses(this);
         }
         changed = true;
      }
      return changed;
   }


   public HashSet<CollectionListener> collectionListeners = new HashSet<CollectionListener>();


   public void addCollectionListener(final CollectionListener listener)
   {
      this.collectionListeners.add(listener);
   }


   public void removeCollectionListener(final CollectionListener listener)
   {
      this.collectionListeners.remove(listener);
   }


   private void fireElementAddedEvent(final String collectionName,
         final Object newValue)
   {
      final Iterator<CollectionListener> iter = this.collectionListeners.iterator();
      while (iter.hasNext())
      {
         final CollectionListener listener = iter.next();
         listener.elementAdded(collectionName, newValue);
      }
   }


   private void fireAllElementsRemovedEvent(final String collectionName)
   {
      final Iterator<CollectionListener> iter = this.collectionListeners.iterator();
      while (iter.hasNext())
      {
         final CollectionListener listener = iter.next();
         listener.allElementsRemoved(collectionName);
      }
   }


   /**
    * @see org.reclipse.tracer.ui.models.VMEvent#removeYou()
    */
   @Override
   public void removeYou()
   {
      final ExecutionMonitorModel tmpModel = getExecutionMonitorModel();
      if (tmpModel != null)
      {
         setExecutionMonitorModel(null);
      }

      removeAllFromMethods();

      this.collectionListeners.clear();
   }


   /**
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(final MonitoredClass other)
   {
      return getName().compareTo(other.getName());
   }

}
