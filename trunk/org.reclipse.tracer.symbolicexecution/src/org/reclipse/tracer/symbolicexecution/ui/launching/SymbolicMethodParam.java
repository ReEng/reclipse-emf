package org.reclipse.tracer.symbolicexecution.ui.launching;


public class SymbolicMethodParam
{

   private String name = null;

   private String value = null;


   public SymbolicMethodParam(String name, String value)
   {
      super();
      this.name = name;
      this.value = value;
   }


   public String getName()
   {
      return this.name;
   }


   public void setName(String name)
   {
      this.name = name;
   }


   public String getValue()
   {
      return this.value;
   }


   public void setValue(String value)
   {
      this.value = value;
   }


}
