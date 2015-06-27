package org.reclipse.tracer.model.definition.xml;


import java.io.IOException;
import java.net.URL;

//import org.reclipse.commons.logging.Logger;
import org.reclipse.tracer.model.definition.CallerClass;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalClass;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.Parameter;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TraceDefinitionSaxHandler extends DefaultHandler
{

   private static final String SYSTEM_ID_RESOURCE = "org/reclipse/tracer/model/dtds/TraceDefinition.dtd";


   private TraceDefinition currentTraceDefinition;

   private CriticalTrace currentCriticalTrace;

   private CriticalClass currentCriticalClass;

   private ConsiderTrace currentConsiderTrace;

   private ConsiderClass currentConsiderClass;

   private ConsiderMethod currentConsiderMethod;


   public TraceDefinition getTraceDefinition()
   {
      return this.currentTraceDefinition;
   }


   /**
    * @throws IOException
    * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
    */
   @Override
   public InputSource resolveEntity(final String publicId, final String systemId)
         throws SAXException,
            IOException
   {
      if (ITraceDefinitionConstants.SYSTEM_ID.equals(systemId))
      {
         ClassLoader classLoader = getClass().getClassLoader();
         if (classLoader == null)
         {
            classLoader = ClassLoader.getSystemClassLoader();
         }

         final URL systemIdURL = classLoader.getResource(SYSTEM_ID_RESOURCE);

         if (systemIdURL != null)
         {
            try
            {
               return new InputSource(systemIdURL.openStream());
            }
            catch (final Exception e)
            {
//               Logger
//                     .logInfo("Could not resolve SYSTEM or PUBLIC reference for DTD.");
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
   public void startElement(final String uri, final String name,
         final String qName, final Attributes attrs)
   {
      try
      {
         if (ITraceDefinitionConstants.TRACE_DEFINITION_TAG.equals(name))
         {
            this.currentTraceDefinition = createTraceDefinition();
         }
         else if (ITraceDefinitionConstants.CONSIDER_TRACE_TAG.equals(name))
         {
            this.currentConsiderTrace = createConsiderTrace();
         }
         else if (ITraceDefinitionConstants.CRITICAL_TRACE_TAG.equals(name))
         {
            this.currentCriticalTrace = createCriticalTrace();
         }
         else if (ITraceDefinitionConstants.CRITICAL_CLASS_TAG.equals(name))
         {
            this.currentCriticalClass = createCriticalClass(attrs);
         }
         else if (ITraceDefinitionConstants.CALLER_CLASS_TAG.equals(name))
         {
            createCallerClass(attrs);
         }
         else if (ITraceDefinitionConstants.CONSIDER_CLASS_TAG.equals(name))
         {
            this.currentConsiderClass = createConsiderClass(attrs);
         }
         else if (ITraceDefinitionConstants.CONSIDER_METHOD_TAG.equals(name))
         {
            this.currentConsiderMethod = createConsiderMethod(attrs);
         }
         else if (ITraceDefinitionConstants.PARAMETER_TAG.equals(name))
         {
            createParameter(attrs);
         }
      }
      catch (final Exception e)
      {
//         Logger.logError("Unexpected exception in parsing tracer input.", e);
      }
   }


   /**
    * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String,
    *      java.lang.String)
    */
   @Override
   public void endElement(final String uri, final String name,
         final String qName)
   {
      if (ITraceDefinitionConstants.CRITICAL_TRACE_TAG.equals(name))
      {
         this.currentCriticalTrace = null;
      }
      else if (ITraceDefinitionConstants.CRITICAL_CLASS_TAG.equals(name))
      {
         this.currentCriticalClass = null;
      }
      else if (ITraceDefinitionConstants.CONSIDER_TRACE_TAG.equals(name))
      {
         this.currentConsiderTrace = null;
      }
      else if (ITraceDefinitionConstants.CONSIDER_CLASS_TAG.equals(name))
      {
         this.currentConsiderClass = null;
      }
      else if (ITraceDefinitionConstants.CONSIDER_METHOD_TAG.equals(name))
      {
         // must be added to the considerClass after creating all parameter
         this.currentConsiderClass.addToMethods(this.currentConsiderMethod);
         this.currentConsiderMethod = null;
      }
   }


   /**
    * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
    */
   @Override
   public void warning(final SAXParseException exception)
   {
//      Logger.logError("XML Parse warning in line " + exception.getLineNumber()
//            + ":", exception);
   }


   /**
    * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
    */
   @Override
   public void error(final SAXParseException exception)
   {
//      Logger.logError("XML Parse Error in line " + exception.getLineNumber()
//            + ":", exception);
   }


   /**
    * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
    */
   @Override
   public void fatalError(final SAXParseException exception)
   {
      // Logger.logError("Fatal XML Parse Error in line "
      // + exception.getLineNumber() + ":", exception);
   }


   private TraceDefinition createTraceDefinition()
   {
      return new TraceDefinition();
   }


   private CriticalTrace createCriticalTrace()
   {
      final CriticalTrace criticalTrace = new CriticalTrace();

      this.currentTraceDefinition.setCriticalTrace(criticalTrace);

      return criticalTrace;
   }


   private CriticalClass createCriticalClass(final Attributes attrs)
   {
      final CriticalClass criticalClass = new CriticalClass();
      criticalClass.setName(attrs
            .getValue(ITraceDefinitionConstants.NAME_ATTRIBUTE));

      this.currentCriticalTrace.addToClasses(criticalClass);

      return criticalClass;
   }


   private CallerClass createCallerClass(final Attributes attrs)
         throws SAXException
   {
      final CallerClass callerClass = new CallerClass();
      callerClass.setName(attrs
            .getValue(ITraceDefinitionConstants.NAME_ATTRIBUTE));

      if (this.currentCriticalClass != null)
      {
         this.currentCriticalClass.addToCallers(callerClass);
      }
      else if (this.currentConsiderMethod != null)
      {
         this.currentConsiderMethod.addToCallers(callerClass);
      }
      else
      {
         throw new SAXException(
               "Unexpected SAX parser state: currentCriticalClass and currentConsiderMethod are null!");
      }

      return callerClass;
   }


   private ConsiderTrace createConsiderTrace()
   {
      final ConsiderTrace considerTrace = new ConsiderTrace();

      this.currentTraceDefinition.setConsiderTrace(considerTrace);

      return considerTrace;
   }


   private ConsiderClass createConsiderClass(final Attributes attrs)
   {
      final ConsiderClass considerClass = new ConsiderClass();
      considerClass.setName(attrs
            .getValue(ITraceDefinitionConstants.NAME_ATTRIBUTE));

      this.currentConsiderTrace.addToClasses(considerClass);

      return considerClass;
   }


   private ConsiderMethod createConsiderMethod(final Attributes attrs)
   {
      final ConsiderMethod considerMethod = new ConsiderMethod();
      considerMethod.setName(attrs
            .getValue(ITraceDefinitionConstants.NAME_ATTRIBUTE));

      final String constructor = attrs
            .getValue(ITraceDefinitionConstants.CONSTRUCTOR_ATTRIBUTE);
      considerMethod.setConstructor(Boolean.getBoolean(constructor));

      // fix for old files
      if (constructor == null)
      {
         final int index = this.currentConsiderClass.getName().lastIndexOf('.');
         final String className = this.currentConsiderClass.getName()
               .substring(index + 1);
         considerMethod.setConstructor(className.equals(considerMethod
               .getName()));
      }

      // considerMethod is added to the considerClass after creating all parameters

      return considerMethod;
   }


   private Parameter createParameter(final Attributes attrs)
   {
      final Parameter parameter = new Parameter();
      parameter.setType(attrs
            .getValue(ITraceDefinitionConstants.TYPE_ATTRIBUTE));

      this.currentConsiderMethod.addToParameters(parameter);

      return parameter;
   }

}
