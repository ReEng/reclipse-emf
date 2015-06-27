package org.reclipse.tracer.extensionpoints;


import java.util.HashMap;
import java.util.Map;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3230 $ $Date: 2006-11-10 16:40:51 +0100 (Fr, 10 Nov 2006) $
 */
public abstract class AbstractMethodCallListener implements IMethodCallListener
{

   private Map<String, String> properties = new HashMap<String, String>();


   /**
    * @see org.reclipse.tracer.extensionpoints.IMethodCallListener#setProperty(java.lang.String,
    *      java.lang.String)
    */
   public void setProperty(String key, String value)
   {
      this.properties.put(key, value);
   }


   public String getProperty(String key)
   {
      return this.properties.get(key);
   }

}
