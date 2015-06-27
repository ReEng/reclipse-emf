package org.reclipse.tracer.model.tracegraph;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2856 $ $Date: 2006-10-12 14:00:41 +0200 (Do, 12 Okt 2006) $
 */
public class TGPrimitiveArgument extends TGArgument
{

   private String value;


   public String getValue()
   {
      return this.value;
   }


   public void setValue(String value)
   {
      if (this.value != value)
      {
         this.value = value;
      }
   }

}
