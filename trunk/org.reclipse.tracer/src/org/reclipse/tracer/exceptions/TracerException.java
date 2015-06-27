package org.reclipse.tracer.exceptions;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4060 $
 */
public class TracerException extends RuntimeException
{

   /**
    * 
    */
   private static final long serialVersionUID = 694724772144486096L;


   public TracerException(String message)
   {
      super(message);
   }


   public TracerException(String message, Throwable cause)
   {
      super(message, cause);
   }

}
