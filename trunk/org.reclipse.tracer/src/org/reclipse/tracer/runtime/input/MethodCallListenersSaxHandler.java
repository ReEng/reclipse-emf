package org.reclipse.tracer.runtime.input;


import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.reclipse.tracer.extensionpoints.IMethodCallListener;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4060 $ $Date: 2009-05-18 17:38:33 +0200 (Mo, 18 Mai 2009) $
 */
public class MethodCallListenersSaxHandler extends DefaultHandler
{

   private static final String SYSTEM_ID_RESOURCE = "org/reclipse/instrumentation/runtime/input/MethodCallListeners.dtd";


   private Set<IMethodCallListener> listeners;


   private IMethodCallListener currentMethodCallListener;


   public Iterator<IMethodCallListener> iteratorOfMethodCallListeners()
   {
      return this.listeners.iterator();
   }


   /**
    * @throws IOException
    * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
    */
   @Override
   public InputSource resolveEntity(String publicId, String systemId)
         throws SAXException,
            IOException
   {
      if (IMethodCallListenersConstants.SYSTEM_ID.equals(systemId))
      {
         ClassLoader classLoader = getClass().getClassLoader();
         if (classLoader == null)
         {
            classLoader = ClassLoader.getSystemClassLoader();
         }

         URL systemIdURL = classLoader.getResource(SYSTEM_ID_RESOURCE);

         if (systemIdURL != null)
         {
            try
            {
               return new InputSource(systemIdURL.openStream());
            }
            catch (Exception e)
            {
               System.err.println("Could not resolve SYSTEM or PUBLIC"
                     + " reference for DTD.");
               throw new SAXException(e);
            }
         }
      }

      return super.resolveEntity(publicId, systemId);
   }


   /**
    * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String,
    *      java.lang.String, org.xml.sax.Attributes)
    */
   @Override
   public void startElement(String uri, String name, String qName,
         Attributes attrs)
   {
      try
      {
         if (IMethodCallListenersConstants.METHOD_CALL_LISTENERS_TAG
               .equals(name))
         {
            this.listeners = new HashSet<IMethodCallListener>();
         }
         else if (IMethodCallListenersConstants.I_METHOD_CALL_LISTENER_TAG
               .equals(name))
         {
            this.currentMethodCallListener = loadMethodCallListener(attrs);
         }
         else if (IMethodCallListenersConstants.PROPERTY_TAG.equals(name))
         {
            if (this.currentMethodCallListener != null)
            {
               addProperty(this.currentMethodCallListener, attrs);
            }
         }
      }
      catch (Exception exception)
      {
         System.err.println("Unexpected exception in parsing tracer input: "
               + exception.getMessage());
      }
   }


   @Override
   public void endElement(String uri, String name, String qName)
   {
      try
      {
         if (IMethodCallListenersConstants.I_METHOD_CALL_LISTENER_TAG
               .equals(name)
               && this.currentMethodCallListener != null)
         {
            if (this.currentMethodCallListener.initialize())
            {
               this.listeners.add(this.currentMethodCallListener);
            }
            else
            {
               System.err.println("Initializing method call listener "
                     + this.currentMethodCallListener.getClass().getName()
                     + " failed!");
            }
         }
      }
      catch (Exception exception)
      {
         System.err.println("Initializing method call listener "
               + this.currentMethodCallListener.getClass().getName()
               + " failed: " + exception.getMessage());
         exception.printStackTrace();
      }
   }


   /**
    * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
    */
   @Override
   public void warning(SAXParseException exception)
   {
      System.err.println("XML Parse warning in line "
            + exception.getLineNumber() + ": " + exception.getMessage());
   }


   /**
    * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
    */
   @Override
   public void error(SAXParseException exception)
   {
      System.err.println("XML Parse Error in line " + exception.getLineNumber()
            + ": " + exception.getMessage());
   }


   /**
    * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
    */
   @Override
   public void fatalError(SAXParseException exception)
   {
      System.err.println("Fatal XML Parse Error in line "
            + exception.getLineNumber() + ": " + exception.getMessage());
   }


   private IMethodCallListener loadMethodCallListener(Attributes attrs)
   {
      IMethodCallListener methodCallListener = null;

      String className = attrs
            .getValue(IMethodCallListenersConstants.CLASS_ATTRIBUTE);
      try
      {
         Class methodCallListenerClass = Class.forName(className);
         methodCallListener = (IMethodCallListener) methodCallListenerClass
               .newInstance();
      }
      catch (ClassNotFoundException e)
      {
         System.err.println("MethodCallListener " + className
               + " could not be loaded!");
         e.printStackTrace();
      }
      catch (InstantiationException e)
      {
         System.err.println("MethodCallListener " + className
               + " could not be loaded!");
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         System.err.println("MethodCallListener " + className
               + " could not be loaded!");
         e.printStackTrace();
      }

      return methodCallListener;
   }


   private void addProperty(IMethodCallListener methodCallListener,
         Attributes attrs)
   {
      String key = attrs.getValue(IMethodCallListenersConstants.KEY_ATTRIBUTE);
      String value = attrs
            .getValue(IMethodCallListenersConstants.VALUE_ATTRIBUTE);

      if (key != null && value != null)
      {
         methodCallListener.setProperty(key, value);
      }
   }

}
