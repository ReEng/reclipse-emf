package org.reclipse.tracer.model.tracegraph;



/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                     0..n   object    0..1 
 *   TGObjectArgument ----------------------- TGObject
 *                     arguments      object 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4060 $ $Date: 2009-05-18 17:38:33 +0200 (Mo, 18 Mai 2009) $
 */
public class TGObjectArgument extends TGArgument
{

   /**
    * <pre>
    *                     0..n   object    0..1 
    *   TGObjectArgument ----------------------- TGObject
    *                     arguments      object 
    * </pre>
    */
   private TGObject object;


   public TGObject getObject()
   {
      return this.object;
   }


   public boolean setObject(TGObject value)
   {
      boolean changed = false;
      if (this.object != value)
      {
         TGObject oldValue = this.object;

         if (this.object != null)
         {
            this.object = null;
            oldValue.removeFromArguments(this);
         }
         this.object = value;
         if (value != null)
         {
            value.addToArguments(this);
         }
         changed = true;

      }
      return changed;
   }


   @Override
   public void removeYou()
   {
      TGObject tmpObject = getObject();
      if (tmpObject != null)
      {
         setObject(null);
      }

      super.removeYou();
   }

}
