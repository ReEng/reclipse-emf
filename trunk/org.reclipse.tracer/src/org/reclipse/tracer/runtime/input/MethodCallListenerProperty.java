package org.reclipse.tracer.runtime.input;


import java.io.IOException;
import java.io.Writer;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3197 $ $Date: 2006-11-07 01:08:15 +0100 (Di, 07 Nov 2006) $
 */
public class MethodCallListenerProperty
{

   private String key;


   public void setKey(String key)
   {
      this.key = key;
   }


   public String getKey()
   {
      return this.key;
   }


   private String value;


   public void setValue(String value)
   {
      this.value = value;
   }


   public String getValue()
   {
      return this.value;
   }


   private String defaultValue;


   public void setDefaultValue(String defaultValue)
   {
      this.defaultValue = defaultValue;
   }


   public String getDefaultValue()
   {
      return this.defaultValue;
   }


   private String type;


   public void setType(String type)
   {
      this.type = type;
   }


   public String getType()
   {
      return this.type;
   }


   public void generatePropertyTag(Writer writer) throws IOException
   {
      writer.write("\n    <");
      writer.write(IMethodCallListenersConstants.PROPERTY_TAG);
      writer.write(" ");
      writer.write(IMethodCallListenersConstants.KEY_ATTRIBUTE);
      writer.write("=\"");
      writer.write(getKey());
      writer.write("\"");
      writer.write("\n              ");
      writer.write(IMethodCallListenersConstants.VALUE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(getValue());
      writer.write("\"/>\n");
   }

}
