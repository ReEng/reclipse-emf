package org.reclipse.tracer.model.definition;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                   0..1   considerTrace   0..1
 *  TraceDefinition ----------------------------- ConsiderTrace
 *                   trace         considerTrace
 * 
 *                   0..1   criticalTrace   0..1
 *  TraceDefinition ----------------------------- CriticalTrace
 *                   trace         criticalTrace
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3089 $ $Date: 2006-11-01 23:23:57 +0100 (Mi, 01 Nov 2006) $
 */
public class TraceDefinition
{

   /**
    * <pre>
    *                  0..1   considerTrace   0..1
    * TraceDefinition ----------------------------- ConsiderTrace
    *                  trace         considerTrace
    * </pre>
    */
   private ConsiderTrace considerTrace;


   public ConsiderTrace getConsiderTrace()
   {
      return this.considerTrace;
   }


   public boolean setConsiderTrace(ConsiderTrace value)
   {
      boolean changed = false;
      if (this.considerTrace != value)
      {
         ConsiderTrace oldValue = this.considerTrace;

         if (this.considerTrace != null)
         {
            this.considerTrace = null;
            oldValue.setTraceDefinition(null);
         }
         this.considerTrace = value;
         if (value != null)
         {
            value.setTraceDefinition(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                  0..1   criticalTrace   0..1
    * TraceDefinition ----------------------------- CriticalTrace
    *                  trace         criticalTrace
    * </pre>
    */
   private CriticalTrace criticalTrace;


   public CriticalTrace getCriticalTrace()
   {
      return this.criticalTrace;
   }


   public boolean setCriticalTrace(CriticalTrace value)
   {
      boolean changed = false;
      if (this.criticalTrace != value)
      {
         CriticalTrace oldValue = this.criticalTrace;

         if (this.criticalTrace != null)
         {
            this.criticalTrace = null;
            oldValue.setTraceDefinition(null);
         }
         this.criticalTrace = value;
         if (value != null)
         {
            value.setTraceDefinition(this);
         }
         changed = true;
      }
      return changed;
   }


   public void removeYou()
   {
      CriticalTrace tmpCriticalTrace = getCriticalTrace();
      if (tmpCriticalTrace != null)
      {
         setCriticalTrace(null);
         tmpCriticalTrace.removeYou();
      }

      ConsiderTrace tmpConsiderTrace = getConsiderTrace();
      if (tmpConsiderTrace != null)
      {
         setConsiderTrace(null);
         tmpConsiderTrace.removeYou();
      }
   }

}
