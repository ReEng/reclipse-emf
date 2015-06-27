package org.reclipse.tracer.model.definition;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *             0..n   parameters   0..1
 *  Parameter -------------------------- ConsiderMethod
 *             parameters        method
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2856 $ $Date: 2006-10-12 14:00:41 +0200 (Do, 12 Okt 2006) $
 */
public class Parameter
{

   private String type;


   public String getType()
   {
      return this.type;
   }


   public void setType(String value)
   {
      if (this.type != value)
      {
         this.type = value;
      }
   }


   /**
    * <pre>
    *             0..n   parameters   0..1
    *  Parameter -------------------------- ConsiderMethod
    *             parameters        method
    * </pre>
    */
   private ConsiderMethod method;


   public ConsiderMethod getMethod()
   {
      return this.method;
   }


   public boolean setMethod(ConsiderMethod value)
   {
      boolean changed = false;
      if (this.method != value)
      {
         ConsiderMethod oldValue = this.method;

         if (this.method != null)
         {
            this.method = null;
            oldValue.removeFromParameters(this);
         }
         this.method = value;
         if (value != null)
         {
            value.addToParameters(this);
         }
         changed = true;
      }
      return changed;
   }


   public void removeYou()
   {
      ConsiderMethod tmpMethod = getMethod();
      if (tmpMethod != null)
      {
         setMethod(null);
      }
   }

}
