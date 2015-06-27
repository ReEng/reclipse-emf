package org.reclipse.tracer.ui.launching;


/**
 * @author Lothar
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
class MonitoredEventsListenerProperty
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

}
