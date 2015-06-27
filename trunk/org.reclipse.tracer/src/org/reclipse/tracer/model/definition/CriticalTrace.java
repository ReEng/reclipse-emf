package org.reclipse.tracer.model.definition;



/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                 0..1       criticalTrace      0..1
 *  CriticalTrace ------------------------------------ TraceDefinition
 *                 criticalTrace       traceDefinition
 * </pre>
 * 
 * @author lowende
 * @author Last Editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class CriticalTrace extends AbstractTrace
{
   /**
    * <pre>
    *                 0..1     criticalTrace    0..1
    *  CriticalTrace -------------------------------- TraceDefinition
    *                 criticalTrace       traceDefinition
    * </pre>
    */
   private TraceDefinition traceDefinition;


   public TraceDefinition getTraceDefinition()
   {
      return this.traceDefinition;
   }


   public boolean setTraceDefinition(TraceDefinition value)
   {
      boolean changed = false;
      if (this.traceDefinition != value)
      {
         TraceDefinition oldValue = this.traceDefinition;

         if (this.traceDefinition != null)
         {
            this.traceDefinition = null;
            oldValue.setCriticalTrace(null);
         }
         this.traceDefinition = value;
         if (value != null)
         {
            value.setCriticalTrace(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTrace#addToClasses(java.lang.String,
    *      org.reclipse.tracer.input.AbstractTraceClass)
    */
   @Override
   protected boolean addToClasses(String key, AbstractTraceClass value)
   {
      if (value != null && !(value instanceof CriticalClass))
      {
         throw new IllegalArgumentException(
               "Argument must be instance of CriticalClass!");
      }
      return super.addToClasses(key, value);
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTrace#addToClasses(org.reclipse.tracer.input.AbstractTraceClass)
    */
   @Override
   public boolean addToClasses(AbstractTraceClass value)
   {
      if (value != null && !(value instanceof CriticalClass))
      {
         throw new IllegalArgumentException(
               "Argument must be instance of CriticalClass!");
      }
      return super.addToClasses(value);
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTrace#getFromClasses(java.lang.String)
    */
   @Override
   public CriticalClass getFromClasses(String key)
   {
      return (CriticalClass) super.getFromClasses(key);
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTrace#removeYou()
    */
   @Override
   public void removeYou()
   {
      TraceDefinition tmpTrace = getTraceDefinition();
      if (tmpTrace != null)
      {
         setTraceDefinition(null);
      }

      super.removeYou();
   }

}
