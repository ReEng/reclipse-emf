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
public class VMStateModel extends Model
{

   public static final String PROPERTY_VM_STATE = "VMStateModel.vmState";


   private int vmState;


   public int getVMState()
   {
      return this.vmState;
   }


   public void setState(int vmState)
   {
      int oldVMState = this.vmState;
      this.vmState = vmState;
      firePropertyChangeEvent(PROPERTY_VM_STATE, oldVMState,
            this.vmState);
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
    * @see org.reclipse.tracer.ui.models.Model#removeYou()
    */
   @Override
   public void removeYou()
   {
      super.removeYou();

      this.propertyChangeListeners.clear();
   }

}
