package org.reclipse.tracer.model.tracegraph;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *               0..*         arguments           1 
 *   TGArgument ------------------------------------ TGMethodCall
 *               arguments   {ordered}   methodCall
 *               
 *               0..n   type    0..1 
 *   TGArgument --------------------- TGType
 *               arguments      type 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2856 $ $Date: 2006-10-12 14:00:41 +0200 (Do, 12 Okt 2006) $
 */
public abstract class TGArgument
{
   
   /**
    * <pre>
    *               0..*         arguments           1 
    *   TGArgument ------------------------------------ TGMethodCall
    *               arguments   {ordered}   methodCall 
    * </pre>
    */
   private TGMethodCall methodCall;


   public TGMethodCall getMethodCall()
   {
      return this.methodCall;
   }


   public boolean setMethodCall(TGMethodCall value)
   {
      boolean changed = false;
      if (this.methodCall != value)
      {
         TGMethodCall oldValue = this.methodCall;

         if (this.methodCall != null)
         {
            this.methodCall = null;
            oldValue.removeFromArguments(this);
         }
         this.methodCall = value;
         if (value != null)
         {
            value.addToArguments(this);
         }
         changed = true;

      }
      return changed;
   }

   /**
    * <pre>
    *               0..n   type    0..1 
    *   TGArgument --------------------- TGType
    *               arguments      type 
    * </pre>
    */
   private TGType type;


   public TGType getType()
   {
      return this.type;
   }


   public boolean setType(TGType value)
   {
      boolean changed = false;
      if (this.type != value)
      {
         TGType oldValue = this.type;
         if (this.type != null)
         {
            this.type = null;
            oldValue.removeFromArguments(this);
         }
         this.type = value;
         if (value != null)
         {
            value.addToArguments(this);
         }
         changed = true;
      }

      return changed;
   }


   public void removeYou()
   {
      TGMethodCall tmpMethodCall = getMethodCall();
      if (tmpMethodCall != null)
      {
         setMethodCall(null);
      }

      TGType tmpType = getType();
      if (tmpType != null)
      {
         setType(null);
      }
   }

}
