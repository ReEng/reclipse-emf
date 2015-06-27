package org.reclipse.tracer.exceptions;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4060 $
 */
public class TracerStartException extends Exception
{

   /**
    * 
    */
   private static final long serialVersionUID = -2224673022721463470L;


   public TracerStartException(String message)
   {
      super(message);
   }


   public TracerStartException(String message, Throwable cause)
   {
      super(message, cause);
   }

}
