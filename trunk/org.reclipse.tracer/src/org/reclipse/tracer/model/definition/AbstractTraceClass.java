package org.reclipse.tracer.model.definition;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                      0..1   classes   0..1 --------
 *  AbstractTraceClass -----------------------| name | AbstractTrace
 *                      classes         trace --------
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3781 $ $Date: 2007-09-08 20:25:11 +0200 (Sa, 08 Sep 2007) $
 */
public abstract class AbstractTraceClass implements
      Comparable<AbstractTraceClass>
{

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


   /**
    * <pre>
    *                      0..1   classes   0..1 --------
    *  AbstractTraceClass -----------------------| name | AbstractTrace
    *                      classes         trace --------
    * </pre>
    */
   private AbstractTrace trace;


   public AbstractTrace getTrace()
   {
      return this.trace;
   }


   public boolean setTrace(final AbstractTrace value)
   {
      boolean changed = false;
      if (this.trace != value)
      {
         final AbstractTrace oldValue = this.trace;

         if (this.trace != null)
         {
            this.trace = null;
            oldValue.removeFromClasses(this);
         }
         this.trace = value;
         if (value != null)
         {
            value.addToClasses(this);
         }
         changed = true;
      }
      return changed;
   }


   public void removeYou()
   {
      final AbstractTrace tmpTrace = getTrace();
      if (tmpTrace != null)
      {
         setTrace(null);
      }
   }


   /**
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(final AbstractTraceClass other)
   {
      return getName().compareTo(other.getName());
   }

}
