package org.reclipse.generator;


/**
 * @version $Revision$ $Date$
 * @author mm
 * @author Last editor: $Author$
 */
public interface IOutputListener
{
   /**
    * The method is called when a new message should be appended to the output.
    * 
    * @param message The message to append.
    */
   void appendOutput(String message);
}
