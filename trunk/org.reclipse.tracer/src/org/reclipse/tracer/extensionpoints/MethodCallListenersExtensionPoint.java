package org.reclipse.tracer.extensionpoints;


import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.reclipse.tracer.runtime.input.MethodCallListenerClass;
import org.reclipse.tracer.runtime.input.MethodCallListenerProperty;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3197 $ $Date: 2006-11-07 01:08:15 +0100 (Di, 07 Nov 2006) $
 */
public class MethodCallListenersExtensionPoint
{

   public static final String PLUGIN_ID = "org.reclipse.tracer.runtime";

   private static final String METHOD_CALL_LISTENER_EXTENSION = "methodCallListeners";

   private static final String METHOD_CALL_LISTENER_ELEMENT = "methodCallListener";

   private static final String PROPERTY_KEY_ELEMENT = "propertyKey";

   private static final String CLASS_ATTRIBUTE = "class";

   private static final String NAME_ATTRIBUTE = "name";

   private static final String TYPE_ATTRIBUTE = "type";

   private static final String DEFAULT_VALUE_ATTRIBUTE = "defaultValue";


   public static Set<MethodCallListenerClass> getMethodCallListeners()
   {
      Set<MethodCallListenerClass> listeners = new HashSet<MethodCallListenerClass>();

      IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(PLUGIN_ID, METHOD_CALL_LISTENER_EXTENSION)
            .getExtensions();

      for (IExtension extension : extensions)
      {
         IConfigurationElement[] configElements = extension
               .getConfigurationElements();
         for (IConfigurationElement configElement : configElements)
         {
            if (METHOD_CALL_LISTENER_ELEMENT.equals(configElement.getName()))
            {
               MethodCallListenerClass listenerClass = new MethodCallListenerClass();
               listenerClass.setClassName(configElement
                     .getAttribute(CLASS_ATTRIBUTE));
               listenerClass
                     .setName(configElement.getAttribute(NAME_ATTRIBUTE));
               listenerClass.setChecked(false);

               listeners.add(listenerClass);

               IConfigurationElement[] children = configElement
                     .getChildren(PROPERTY_KEY_ELEMENT);
               for (IConfigurationElement propertyKey : children)
               {
                  MethodCallListenerProperty property = new MethodCallListenerProperty();
                  property.setKey(propertyKey.getAttribute(NAME_ATTRIBUTE));
                  property.setType(propertyKey.getAttribute(TYPE_ATTRIBUTE));

                  String defaultValue = propertyKey
                        .getAttribute(DEFAULT_VALUE_ATTRIBUTE);
                  if (defaultValue != null)
                  {
                     property.setValue(defaultValue);
                     property.setDefaultValue(defaultValue);
                  }
                  else
                  {
                     property.setValue("");
                     property.setDefaultValue("");
                  }

                  listenerClass.addProperty(property);
               }
            }
         }
      }

      return listeners;
   }

}
