package org.reclipse.tracer.runtime.input;


import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Lothar
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3197 $ $Date: 2006-11-07 01:08:15 +0100 (Di, 07 Nov 2006) $
 */
public class MethodCallListenerClass
{

   private String className;


   public String getClassName()
   {
      return this.className;
   }


   public void setClassName(String value)
   {
      this.className = value;
   }


   private String name;


   public void setName(String name)
   {
      this.name = name;
   }


   public String getName()
   {
      return this.name;
   }


   private boolean checked = false;


   public void setChecked(boolean checked)
   {
      this.checked = checked;
   }


   public boolean isChecked()
   {
      return this.checked;
   }


   private Map<String, MethodCallListenerProperty> properties = new HashMap<String, MethodCallListenerProperty>();


   public void addProperty(MethodCallListenerProperty property)
   {
      this.properties.put(property.getKey(), property);
   }


   public Collection<MethodCallListenerProperty> getProperties()
   {
      return this.properties.values();
   }


   public MethodCallListenerProperty getProperty(String key)
   {
      return this.properties.get(key);
   }


   public void generateXMLTag(Writer writer) throws IOException
   {
      writer.write("\n  <");
      writer.write(IMethodCallListenersConstants.I_METHOD_CALL_LISTENER_TAG);
      writer.write(" ");
      writer.write(IMethodCallListenersConstants.CLASS_ATTRIBUTE);
      writer.write("=\"");
      writer.write(getClassName());
      writer.write("\">\n");

      for (MethodCallListenerProperty property : getProperties())
      {
         property.generatePropertyTag(writer);
      }

      writer.write("\n  </");
      writer.write(IMethodCallListenersConstants.I_METHOD_CALL_LISTENER_TAG);
      writer.write(">\n");
   }
}
