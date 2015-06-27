package org.reclipse.behavior.inference.automaton.symbols;


/**
 * @author mcp
 */
public class MethodCallSetObject extends MethodCallObject
{

   String set;

   boolean foreach;


   MethodCallSetObject(String name, String typeName, String set, boolean foreach)
   {
      super(name, typeName);
      this.set = set;
      this.foreach = foreach;
   }


   public boolean isForeach()
   {
      return foreach;
   }


   public void setForeach(boolean foreach)
   {
      this.foreach = foreach;
   }


   public String getSet()
   {
      return set;
   }


   public void setSet(String set)
   {
      this.set = set;
   }


}
