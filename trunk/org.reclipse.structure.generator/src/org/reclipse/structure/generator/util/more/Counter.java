package org.reclipse.structure.generator.util.more;


/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2949 $ $Date: 2006-10-24 15:39:26 +0200 (Di, 24 Okt 2006) $
 */
public class Counter
{

   private int counter = 1;


   /**
    * @return number to create unique object names.
    */
   public /* package */int incCounter()
   {
      return this.counter++;
   }

}
