package org.reclipse.behavior.inference;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
public interface IBehaviorRecognitionProgressListener
{

   public void setAmountOfTasks(int amount);


   public void taskDone();


   public void finished();

}
