package org.reclipse.tracer.ui.launching;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Lothar
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
class MonitoredEventsListenerClass
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


   private boolean enabled = false;


   public void setEnabled(boolean enabled)
   {
      this.enabled = enabled;
   }


   public boolean isEnabled()
   {
      return this.enabled;
   }


   private Map<String, MonitoredEventsListenerProperty> properties = new HashMap<String, MonitoredEventsListenerProperty>();


   public void addProperty(MonitoredEventsListenerProperty property)
   {
      this.properties.put(property.getKey(), property);
   }


   public Collection<MonitoredEventsListenerProperty> getProperties()
   {
      return this.properties.values();
   }


   public MonitoredEventsListenerProperty getProperty(String key)
   {
      return this.properties.get(key);
   }

}
