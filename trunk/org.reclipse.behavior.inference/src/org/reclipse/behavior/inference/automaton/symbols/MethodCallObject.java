package org.reclipse.behavior.inference.automaton.symbols;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4102 $ $Date: 2009-07-22 11:20:27 +0200 (Mi, 22 Jul 2009) $
 */
public class MethodCallObject
{

   /**
    * Please use SymbolFactory to create a new instance.
    */
   /* package */   MethodCallObject(String name, String typeName)
   {
      this.name = name;
      this.typeName = typeName;
   }


   protected final String name;


   public String getName()
   {
      return this.name;
   }


   protected final String typeName;


   public String getTypeName()
   {
      return this.typeName;
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append("(");
      buffer.append(this.name);
      if (this.typeName != null)
      {
         buffer.append(":");
         buffer.append(this.typeName);
      }
      buffer.append(")");

      return buffer.toString();
   }

}
