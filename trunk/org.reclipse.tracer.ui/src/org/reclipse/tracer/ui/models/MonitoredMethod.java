package org.reclipse.tracer.ui.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4129 $ $Date: 2009-08-07 10:30:53 +0200 (Fr, 07 Aug 2009) $
 */
public class MonitoredMethod extends VMEvent
{
   public static final int PRIVATE = 0;

   public static final int PACKAGE = 1;

   public static final int PROTECTED = 2;

   public static final int PUBLIC = 3;


   public MonitoredMethod()
   {
      setType(VMEvent.METHOD_REQUEST_CREATED);
   }
   
   
   private int visibility;

   public int getVisibility()
   {
      return this.visibility;
   }

   public void setVisibility(int value)
   {
      if (this.visibility != value)
      {
         this.visibility = value;
      }
   }


   private int executions = 0;

   public static final String PROPERTY_EXECUTIONS = "MonitoredMethod.executions";

   public int getExecutions()
   {
      return this.executions;
   }

   public void setExecutions(int value)
   {
      if (this.executions != value)
      {
         int oldValue = this.executions;
         this.executions = value;
         
         firePropertyChangeEvent(PROPERTY_EXECUTIONS, oldValue,
               value);
      }
   }

   
   private String signature;

   public String getSignature()
   {
      return this.signature;
   }

   public void setSignature(String value)
   {
      if (this.signature != value)
      {
         this.signature = value;
      }
   }

   
   /**
    * <pre>
    *                  0..1   methods    1 -------------
    * MonitoredMethod ---------------------| signature | MonitoredClass
    *                  methods      parent -------------
    * </pre>
    */
   private MonitoredClass parent;

   public MonitoredClass getParent()
   {
      return this.parent;
   }

   public boolean setParent(MonitoredClass value)
   {
      boolean changed = false;
      if (this.parent != value)
      {
         MonitoredClass oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.removeFromMethods (this);
         }
         this.parent = value;
         if (value != null)
         {
            value.addToMethods (this);
         }
         changed = true;
         
      }
      return changed;
   }

 
   public HashSet propertyChangeListeners = new HashSet();


   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      this.propertyChangeListeners.add(listener);
   }


   public void removePropertyChangeListener(PropertyChangeListener listener)
   {
      this.propertyChangeListeners.remove(listener);
   }


   private void firePropertyChangeEvent(String propertyName, Object oldValue,
         Object newValue)
   {
      PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName,
            oldValue, newValue);
      Iterator iter = this.propertyChangeListeners.iterator();
      while (iter.hasNext())
      {
         PropertyChangeListener listener = (PropertyChangeListener) iter.next();
         listener.propertyChange(event);
      }
   }
   
   
   /**
    * @see org.reclipse.tracer.ui.models.VMEvent#removeYou()
    */
   @Override
   public void removeYou()
   {
      MonitoredClass tmpParent = getParent ();
      if (tmpParent != null)
      {
         setParent (null);
      }
      
      this.propertyChangeListeners.clear();
   }

}
