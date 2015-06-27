package org.reclipse.tracer.model.definition;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                 0..1       considerTrace       0..1
 *  ConsiderTrace ------------------------------------- TraceDefinition
 *                 considerTrace       traceDefinition
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class ConsiderTrace extends AbstractTrace
{
   /**
    * <pre>
    *                 0..1       considerTrace       0..1
    *  ConsiderTrace ------------------------------------- TraceDefinition
    *                 considerTrace       traceDefinition
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
            oldValue.setConsiderTrace(null);
         }
         this.traceDefinition = value;
         if (value != null)
         {
            value.setConsiderTrace(this);
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
      if (value != null && !(value instanceof ConsiderClass))
      {
         throw new IllegalArgumentException(
               "Argument must be instance of ConsiderClass!");
      }
      return super.addToClasses(key, value);
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTrace#addToClasses(org.reclipse.tracer.input.AbstractTraceClass)
    */
   @Override
   public boolean addToClasses(AbstractTraceClass value)
   {
      if (value != null && !(value instanceof ConsiderClass))
      {
         throw new IllegalArgumentException(
               "Argument must be instance of ConsiderClass!");
      }
      return super.addToClasses(value);
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTrace#getFromClasses(java.lang.String)
    */
   @Override
   public ConsiderClass getFromClasses(String key)
   {
      return (ConsiderClass) super.getFromClasses(key);
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
