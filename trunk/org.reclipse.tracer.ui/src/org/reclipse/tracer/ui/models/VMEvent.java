package org.reclipse.tracer.ui.models;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2357 $ $Date: 2006-06-01 14:24:13 +0200 (Do, 01 Jun 2006) $
 */
public class VMEvent
{
   public static final int CLASS_LOADED = 1;

   public static final int EXCEPTION_THROWN = 3;

   public static final int METHOD_REQUEST_CREATED = 2;

   public static final int THREAD_DEATH = 4;

   public static final int TRACER_EXCEPTION = 7;

   public static final int VM_DEATH = 6;

   public static final int VM_DISCONNECT = 5;

   public static final int VM_START = 0;


   private String message;


   public String getMessage()
   {
      return this.message;
   }


   public void setMessage(String value)
   {
      if (this.message != value)
      {
         this.message = value;
      }
   }


   private int type;


   public int getType()
   {
      return this.type;
   }


   public void setType(int value)
   {
      if (this.type != value)
      {
         this.type = value;
      }
   }


   /**
    * <pre>
    *           0..n           events           1 
    *  VMEvent ----------------------------------- EventsModel
    *           events   {ordered}    eventsmodel 
    * </pre>
    */
   private EventsModel eventsModel;


   public EventsModel getEventsModel()
   {
      return this.eventsModel;
   }


   public boolean setEventsModel(EventsModel value)
   {
      boolean changed = false;
      if (this.eventsModel != value)
      {
         EventsModel oldValue = this.eventsModel;

         if (this.eventsModel != null)
         {
            this.eventsModel = null;
            oldValue.removeFromEvents(this);
         }
         this.eventsModel = value;
         if (value != null)
         {
            value.addToEvents(this);
         }
         changed = true;

      }
      return changed;
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return getMessage();
   }


   public void removeYou()
   {
      EventsModel tmpModel = getEventsModel();
      if (tmpModel != null)
      {
         setEventsModel(null);
      }
   }

}
