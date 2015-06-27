package org.reclipse.tracer.ui.models;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2357 $ $Date: 2006-06-01 14:24:13 +0200 (Do, 01 Jun 2006) $
 */
public class EventsModel extends Model
{
   /**
    * <pre>
    *               1           events           0..n 
    *  EventsModel ----------------------------------- VMEvent
    *               eventsModel   {ordered}    events 
    * </pre>
    */
   private LinkedList<VMEvent> events;

   public static final String PROPERTY_EVENTS = "EventsModel.events";


   public boolean addToEvents(VMEvent value)
   {
      boolean changed = false;
      if (value != null && !hasInEvents(value))
      {
         if (this.events == null)
         {
            this.events = new LinkedList<VMEvent>();
         }
         changed = this.events.add(value);
         if (changed)
         {
            value.setEventsModel(this);

            fireElementAddedEvent(PROPERTY_EVENTS, value);
         }
      }
      return changed;
   }


   public VMEvent getEventsAt(int index)
   {
      if (index >= 0 && index < sizeOfEvents())
      {
         return (VMEvent) this.events.get(index);
      }
      else
      {
         throw new IllegalArgumentException("getEventsAt(" + index + ")");
      }
   }


   public boolean hasInEvents(VMEvent value)
   {
      return ((this.events != null) && (value != null) && this.events.contains(value));
   }


   public Iterator<VMEvent> iteratorOfEvents()
   {
      return ((this.events == null) ? Collections.<VMEvent> emptyList().iterator() : this.events.listIterator());
   }


   public Collection<VMEvent> valuesOfEvents()
   {
      return ((this.events == null) ? Collections.<VMEvent> emptyList() : this.events);
   }


   public int sizeOfEvents()
   {
      return ((this.events == null) ? 0 : this.events.size());
   }


   public boolean removeFromEvents(VMEvent value)
   {
      boolean changed = false;
      if ((this.events != null) && (value != null))
      {
         changed = this.events.remove(value);
         if (changed)
         {
            value.setEventsModel(null);

            // side effect
            value.removeYou();
         }
      }
      return changed;
   }


   public void removeAllFromEvents()
   {
      if (this.events != null)
      {
         this.events.clear();
      }
      // VMEvent tmpValue;
      // Iterator<VMEvent> iter = this.iteratorOfEvents();
      // while (iter.hasNext())
      // {
      // tmpValue = (VMEvent) iter.next();
      // this.removeFromEvents(tmpValue);
      // }

      fireAllElementsRemovedEvent(PROPERTY_EVENTS);
   }


   /**
    * @see org.reclipse.tracer.ui.models.Model#removeYou()
    */
   @Override
   public void removeYou()
   {
      super.removeYou();

      removeAllFromEvents();
   }

}
