package org.reclipse.behavior.generator.tracedefinition;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3800 $ $Date: 2007-09-12 20:47:45 +0200 (Mi, 12 Sep 2007) $
 */
/* package */class MethodVariable
{

   private String name;


   public String getName()
   {
      return this.name;
   }


   public void setName(String value)
   {
      if (this.name != value)
      {
         this.name = value;
      }
   }

}
