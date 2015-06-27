package org.reclipse.tracer.ui.models;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3779 $ $Date: 2007-09-07 18:33:49 +0200 (Fr, 07 Sep 2007) $
 */
public class ExecutionMonitorModel extends Model
{
   /**
    * <pre>
    *                        -------- 1   classes   0..1 
    *  ExecutionMonitorModel | name |-------------------- MonitoredClass
    *                        -------- model      classes 
    * </pre>
    */
   private TreeMap<String, MonitoredClass> classes;

   public static final String PROPERTY_CLASSES = "ExecutionMonitorModel.classes";


   public boolean addToClasses(final MonitoredClass value)
   {
      final String key = getKeyForClasses(value);
      boolean changed = false;
      if (key != null)
      {
         if (this.classes == null)
         {
            this.classes = new TreeMap<String, MonitoredClass>();
         }
         final MonitoredClass oldValue = (MonitoredClass) this.classes.put(key,
               value);

         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setExecutionMonitorModel(null);
            }
            if (value != null)
            {
               value.setExecutionMonitorModel(this);
            }

            changed = true;
            fireElementAddedEvent(PROPERTY_CLASSES, value);
         }
      }
      return changed;
   }


   public Iterator<Map.Entry<String, MonitoredClass>> entriesOfClasses()
   {
      return ((this.classes == null) ? Collections.<Map.Entry<String, MonitoredClass>>emptySet().iterator() : this.classes
            .entrySet().iterator());
   }


   public MonitoredClass getFromClasses(final String key)
   {
      return (((this.classes == null) || (key == null)) ? null
            : (MonitoredClass) this.classes.get(key));
   }


   public String getKeyForClasses(final MonitoredClass value)
   {
      return (value == null ? null : value.getName());
   }


   protected boolean hasInClasses(final String key, final MonitoredClass value)
   {
      return ((this.classes != null)
            && (value != null || this.classes.containsKey(key))
            && (key != null) && (this.classes.get(key) == value));
   }


   public boolean hasInClasses(final MonitoredClass value)
   {
      return (this.hasInClasses(this.getKeyForClasses(value), value));
   }


   public boolean hasKeyInClasses(final String key)
   {
      return ((this.classes != null) && (key != null) && this.classes
            .containsKey(key));
   }


   public Iterator<MonitoredClass> iteratorOfClasses()
   {
      return ((this.classes == null) ? Collections.<MonitoredClass>emptyList().iterator() : this.classes
            .values().iterator());
   }


   public Iterator<String> keysOfClasses()
   {
      return ((this.classes == null) ? Collections.<String>emptyList().iterator() : this.classes
            .keySet().iterator());
   }


   public Collection<MonitoredClass> valuesOfClasses()
   {
      return ((this.classes == null) ? Collections.<MonitoredClass>emptyList() : this.classes
            .values());
   }


   public int sizeOfClasses()
   {
      return ((this.classes == null) ? 0 : this.classes.size());
   }


   public boolean removeFromClasses(final MonitoredClass value)
   {
      final String key = getKeyForClasses(value);
      boolean changed = false;
      if ((this.classes != null) && (key != null))
      {
         final MonitoredClass oldValue = (MonitoredClass) this.classes.get(key);
         if (oldValue == value
               && (oldValue != null || this.classes.containsKey(key)))
         {
            this.classes.remove(key);
            if (value != null)
            {
               value.setExecutionMonitorModel(null);

               // side effect
               value.removeYou();
            }
            changed = true;
         }
      }
      return changed;
   }


   public void removeAllFromClasses()
   {
      if(this.classes!=null){
         this.classes.clear();
      }

      fireAllElementsRemovedEvent(PROPERTY_CLASSES);
   }


   /**
    * @see org.reclipse.tracer.ui.models.Model#removeYou()
    */
   @Override
   public void removeYou()
   {
      super.removeYou();

      removeAllFromClasses();
   }

}
