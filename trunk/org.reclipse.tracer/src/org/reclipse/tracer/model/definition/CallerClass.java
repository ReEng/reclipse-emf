package org.reclipse.tracer.model.definition;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *               1         callers         1 --------
 *  CallerClass -----------------------------| name | ConsiderMethod
 *               callers      considerMethod --------
 *  
 *               1        callers         1 --------
 *  CallerClass ----------------------------| name | CriticalClass
 *               callers      criticalClass --------
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2856 $ $Date: 2006-10-12 14:00:41 +0200 (Do, 12 Okt 2006) $
 */
public class CallerClass extends AbstractTraceClass
{
   /**
    * <pre>
    *               1         callers         1 --------
    *  CallerClass -----------------------------| name | ConsiderMethod
    *               callers      considerMethod --------
    * </pre>
    */
   private ConsiderMethod considerMethod;


   public ConsiderMethod getConsiderMethod()
   {
      return this.considerMethod;
   }


   public boolean setConsiderMethod(ConsiderMethod value)
   {
      boolean changed = false;
      if (this.considerMethod != value)
      {
         ConsiderMethod oldValue = this.considerMethod;

         if (this.considerMethod != null)
         {
            this.considerMethod = null;
            oldValue.removeFromCallers(this);
         }
         this.considerMethod = value;
         if (value != null)
         {
            value.addToCallers(this);
         }
         changed = true;
      }
      return changed;
   }

   /**
    * <pre>
    *               1        callers         1 --------
    *  CallerClass ----------------------------| name | CriticalClass
    *               callers      criticalClass --------
    * </pre>
    */
   private CriticalClass criticalClass;


   public CriticalClass getCriticalClass()
   {
      return this.criticalClass;
   }


   public boolean setCriticalClass(CriticalClass value)
   {
      boolean changed = false;
      if (this.criticalClass != value)
      {
         CriticalClass oldValue = this.criticalClass;

         if (this.criticalClass != null)
         {
            this.criticalClass = null;
            oldValue.removeFromCallers(this);
         }
         this.criticalClass = value;
         if (value != null)
         {
            value.addToCallers(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * @see org.reclipse.tracer.input.AbstractTraceClass#removeYou()
    */
   @Override
   public void removeYou()
   {
      CriticalClass tmpCriticalClass = getCriticalClass();
      if (tmpCriticalClass != null)
      {
         setCriticalClass(null);
      }

      ConsiderMethod tmpConsiderMethod = getConsiderMethod();
      if (tmpConsiderMethod != null)
      {
         setConsiderMethod(null);
      }

      super.removeYou();
   }

}
