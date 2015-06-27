package org.reclipse.tracer.runtime;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

//import org.reclipse.commons.logging.Logger;
import org.reclipse.tracer.extensionpoints.IMethodCallListener;
import org.reclipse.tracer.runtime.input.MethodCallListenersSaxHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3256 $ $Date: 2006-11-15 18:36:36 +0100 (Mi, 15 Nov 2006) $
 */
public class MethodCallObserver
{

   public static final Object STATIC_OBJECT = new Object();

   public static final String METHOD_CALL_LISTENERS_ENV_VAR = "MethodCallListeners";

   public static final String METHOD_CALL_LISTENERS_XML_FILE_NAME = "MethodCallListeners.xml";


   private static final Set<IMethodCallListener> listeners = new HashSet<IMethodCallListener>();


   /**
    * Static initializer to load the method call listeners.
    */
   static
   {
      try
      {
//         Logger.logInfo("Initializing MethodCallObserver...");

         InputStream inputStream = null;

         // try to get the path for MethodCallListeners.xml from environment variables
         String fileName = System.getenv(METHOD_CALL_LISTENERS_ENV_VAR);
         if (fileName != null)
         {
            File file = new File(fileName);
            if (file.exists())
            {
//               Logger.logInfo("Using configuration: " + fileName);
               inputStream = new FileInputStream(file);
            }
         }

         // try to load input stream from class path
         if (inputStream == null)
         {
            ClassLoader classLoader = MethodCallObserver.class.getClassLoader();
            if (classLoader == null)
            {
               classLoader = ClassLoader.getSystemClassLoader();
            }
            URL url = classLoader
                  .getResource(METHOD_CALL_LISTENERS_XML_FILE_NAME);
//            Logger.logInfo("Using configuration: " + url.getFile());
            inputStream = url.openStream();
         }

         MethodCallListenersSaxHandler handler = new MethodCallListenersSaxHandler();

         SAXParserFactory factory = SAXParserFactory.newInstance();
         factory.setValidating(true);
         factory.setNamespaceAware(true);

         XMLReader xmlReader = factory.newSAXParser().getXMLReader();
         xmlReader.setContentHandler(handler);
         xmlReader.setErrorHandler(handler);
         xmlReader.setEntityResolver(handler);
         xmlReader.parse(new InputSource(inputStream));
         inputStream.close();

         Iterator<IMethodCallListener> listenersIter = handler
               .iteratorOfMethodCallListeners();
         while (listenersIter.hasNext())
         {
            IMethodCallListener listener = listenersIter.next();
            addMethodCallListener(listener);
         }
      }
      catch (SAXException exception)
      {
//         Logger.logError("Error parsing " + METHOD_CALL_LISTENERS_XML_FILE_NAME
//               + ": ", exception);
      }
      catch (ParserConfigurationException exception)
      {
//         Logger.logError("Error parsing " + METHOD_CALL_LISTENERS_XML_FILE_NAME
//               + ": ", exception);
      }
      catch (IOException exception)
      {
//         Logger.logError("Error loading " + METHOD_CALL_LISTENERS_XML_FILE_NAME
//               + ": ", exception);
      }
   }


   private static long methodCallIDCounter = 0;


   public static void reportMethodCall(Object caller, Class staticCallerType,
         Object callee, Class staticCalleeType, String methodName,
         String... argumentTypes)
   {
      for (IMethodCallListener listener : listeners)
      {
         // catch every exception thrown within the injected code to not
         // interfere with the instrumented program
         try
         {
            listener.methodCalled(methodCallIDCounter, caller,
                  staticCallerType, callee, staticCalleeType, methodName,
                  argumentTypes);
         }
         catch (Exception exception)
         {
//            Logger.logError("Exception occurred during reporting "
//                  + "method call to listener '" + listener.getClass().getName()
//                  + "':", exception);
         }
      }

      methodCallIDCounter++;
   }


   public static void programTerminates()
   {
      // catch every exception thrown within the injected code to not interfere with the
      // instrumented program
      try
      {
         for (IMethodCallListener listener : listeners)
         {
            listener.terminate();
         }
      }
      catch (Exception exception)
      {
         // Logger.logError("Exception occurred during reporting "
         // + "method call to listeners: ", exception);
      }
   }


   public static void addMethodCallListener(IMethodCallListener listener)
   {
      if (!listeners.contains(listener))
      {
         listeners.add(listener);
      }
   }


   public static void removeMethodCallListener(IMethodCallListener listener)
   {
      if (listeners.contains(listener))
      {
         listeners.remove(listener);
      }
   }


   private static long objectIDCounter = 0;

   private static Map<Object, Long> objectIDs = new WeakHashMap<Object, Long>();


   public static long getObjectID(Object object)
   {
      Long id = objectIDs.get(object);
      if (id == null)
      {
         id = objectIDCounter;
         objectIDs.put(object, objectIDCounter);
         objectIDCounter++;
      }

      return id;
   }

}
