package org.reclipse.tracer.model.tracegraph.xml;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

//import org.reclipse.commons.logging.Logger;
import org.reclipse.tracer.model.tracegraph.TGArgument;
import org.reclipse.tracer.model.tracegraph.TGFactory;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGObjectArgument;
import org.reclipse.tracer.model.tracegraph.TGPrimitiveArgument;
import org.reclipse.tracer.model.tracegraph.TGTrace;
import org.reclipse.tracer.model.tracegraph.TGTracePath;
import org.reclipse.tracer.model.tracegraph.TGThread;
import org.reclipse.tracer.model.tracegraph.TGType;
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
public class TraceGraphSaxHandler extends DefaultHandler
{

   private static final String SYSTEM_ID_RESOURCE = "org/reclipse/tracer/model/dtds/Trace.dtd";


   private TGFactory factory;

   private TGTrace trace;
   
   private TGTracePath currentTracePath;

   private TGMethodCall currentMethodCall;

   private HashMap<TGThread, TGMethodCall> lastMethodCallOfThread;

   private TGType currentType;


   /**
    * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
    */
   @Override
   public InputSource resolveEntity(String publicId, String systemId)
         throws SAXException,
            IOException
   {
      if (ITraceConstants.SYSTEM_ID.equals(systemId))
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
//               Logger
//                     .logInfo("Could not resolve SYSTEM or PUBLIC reference for DTD.");
               throw new SAXException(e);
            }
         }
      }

      return super.resolveEntity(publicId, systemId);
   }


   /**
    * @see org.xml.sax.ContentHandler#startDocument()
    */
   @Override
   public void startDocument()
   {
      this.lastMethodCallOfThread = new HashMap<TGThread, TGMethodCall>();      
   }


   /**
    * @see org.xml.sax.ContentHandler#endDocument()
    */
   @Override
   public void endDocument()
   {
      this.factory = null;
   }


   /**
    * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String,
    *      java.lang.String, org.xml.sax.Attributes)
    */
   @Override
   public void startElement(String uri, String name, String qName,
         Attributes atts) throws SAXException
   {
      //Prevent loading deprecated trace files
      if(ITraceConstants.PROCESS_START_TAG.equals(name))
         throw new SAXException("Loading old trace files is no longer supported.");
         
      try
      {
         if(ITraceConstants.TRACE_TAG.equals(name))
         {
            startTraceTag(atts);
         }
         else if (ITraceConstants.TRACEPATH_START_TAG.equals(name))
         {
            startTracePathStartTag(atts);
         }
         else if (ITraceConstants.CLASS_LOADED_TAG.equals(name))
         {
            startClassLoadedTag(atts);
         }
         else if (ITraceConstants.SUPER_TYPE_TAG.equals(name))
         {
            startSuperTypeTag(atts);
         }
         else if (ITraceConstants.METHOD_ENTRY_TAG.equals(name))
         {
            startMethodEntryTag(atts);
         }
         else if (ITraceConstants.CALLER_TAG.equals(name))
         {
            startCallerTag(atts);
         }
         else if (ITraceConstants.CALLEE_TAG.equals(name))
         {
            startCalleeTag(atts);
         }
         else if (ITraceConstants.ARGUMENT_TAG.equals(name))
         {
            startArgumentTag(atts);
         }
         else if (ITraceConstants.TRACEPATH_END_TAG.equals(name))
         {
            startTracePathEndTag(atts);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   /**
    * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String,
    *      java.lang.String)
    */
   @Override
   public void endElement(String uri, String name, String qName)
   {
      if (ITraceConstants.METHOD_ENTRY_TAG.equals(name))
      {
         this.currentMethodCall = null;
      }
      else if (ITraceConstants.CLASS_LOADED_TAG.equals(name))
      {
         this.currentType = null;
      }
   }


   /**
    * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
    */
   @Override
   public void warning(SAXParseException exception)
   {
//      Logger.logError("XML Parse warning in line " + exception.getLineNumber()
//            + ":");
//      Logger.logError("  " + exception.getMessage());
   }


   /**
    * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
    */
   @Override
   public void error(SAXParseException exception)
   {
//      Logger.logError("XML Parse Error in line " + exception.getLineNumber()
//            + ":");
//      Logger.logError("  " + exception.getMessage());
   }


   /**
    * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
    */
   @Override
   public void fatalError(SAXParseException exception)
   {
      // Logger.logError("Fatal XML Parse Error in line "
      // + exception.getLineNumber() + ":");
      // Logger.logError("  " + exception.getMessage());
   }


   public TGTrace getTGTrace()
   {
      return this.trace;
   }


   private void startTraceTag(Attributes atts)
   {
      this.trace = new TGTrace();
      this.trace.setDate(atts.getValue(ITraceConstants.DATE_ATTRIBUTE));
      this.trace.setMainClass(atts.getValue(ITraceConstants.MAIN_CLASS_ATTRIBUTE));
      this.trace.setSymbolicExecution(Boolean.parseBoolean(atts.getValue(ITraceConstants.SYMBOLIC_EXECUTION_ATTR)));
   }   
   
   private void startTracePathStartTag(Attributes atts)
   {
      //Reset factory to prevent side effects to other tracepaths
      this.factory = new TGFactory();
      
      this.currentTracePath = new TGTracePath();
      this.currentTracePath.setName(atts.getValue(ITraceConstants.NAME_ATTR));

      String timeStamp = atts.getValue(ITraceConstants.TIME_ATTR);
      if (timeStamp != null)
      {
         this.currentTracePath.setStartTime(Long.parseLong(timeStamp));
      }
      
      //Add to trace
      this.trace.addToTracePaths(this.currentTracePath);
   }


   private void startClassLoadedTag(Attributes atts)
   {
      String typeName = atts.getValue(ITraceConstants.NAME_ATTR);
      this.currentType = this.factory.getTGType(typeName);
      if (this.currentType == null)
      {
         this.currentType = this.factory.createTGType(typeName);
      }
   }


   private void startSuperTypeTag(Attributes atts)
   {
      String superTypeName = atts.getValue(ITraceConstants.NAME_ATTR);
      TGType superType = this.factory.getTGType(superTypeName);

      if (superType == null)
      {
         superType = this.factory.createTGType(superTypeName);
      }

      this.currentType.addToSuperTypes(superType);
   }


   private void startMethodEntryTag(Attributes atts)
   {
      this.currentMethodCall = new TGMethodCall();
      this.currentMethodCall.setId(atts.getValue(ITraceConstants.ID_ATTR));
      this.currentMethodCall.setName(atts.getValue(ITraceConstants.NAME_ATTR));

      String timeStamp = atts.getValue(ITraceConstants.TIME_ATTR);
      if (timeStamp != null)
      {
         this.currentMethodCall.setTime(Long.parseLong(timeStamp));
      }

      // add thread
      String threadName = atts.getValue(ITraceConstants.THREAD_ATTR);
      if (threadName != null)
      {
         TGThread mcgThread = this.factory.getTGThread(threadName);
         if (mcgThread == null)
         {
            mcgThread = this.factory.createTGThread(threadName);
            this.currentTracePath.addToThreads(mcgThread);
         }
         this.currentMethodCall.setOwningThread(mcgThread);

         // link with last method call from thread
         TGMethodCall lastMethodCall = this.lastMethodCallOfThread
               .get(mcgThread);
         if (lastMethodCall != null)
         {
            lastMethodCall.setNext(this.currentMethodCall);
         }
         this.lastMethodCallOfThread.put(mcgThread, this.currentMethodCall);
      }

      // add method call to tracePath
      this.currentTracePath.addToMethodCalls(this.currentMethodCall);
   }


   private void startCallerTag(Attributes atts)
   {
      String callerId = atts.getValue(ITraceConstants.ID_ATTR);
      TGObject caller = this.factory.getTGObject(callerId);

      if (caller == null)
      {
         caller = this.factory.createTGObject(callerId);
         this.currentTracePath.addToObjects(caller);

         // set type
         String typeName = atts.getValue(ITraceConstants.TYPE_ATTR);
         TGType type = this.factory.getTGType(typeName);
         if (type == null)
         {
            type = this.factory.createTGType(typeName);
         }
         caller.setType(type);
      }

      this.currentMethodCall.setCaller(caller);
   }


   private void startCalleeTag(Attributes atts)
   {
      String calleeId = atts.getValue(ITraceConstants.ID_ATTR);
      TGObject callee = this.factory.getTGObject(calleeId);
      if (callee == null)
      {
         callee = this.factory.createTGObject(calleeId);
         this.currentTracePath.addToObjects(callee);

         // set type
         String typeName = atts.getValue(ITraceConstants.TYPE_ATTR);
         TGType type = this.factory.getTGType(typeName);
         callee.setType(type);
      }

      this.currentMethodCall.setCallee(callee);
   }


   private void startArgumentTag(Attributes atts)
   {
      TGArgument mcgArgument;

      // get type
      String typeName = atts.getValue(ITraceConstants.TYPE_ATTR);
      TGType type = this.factory.getTGType(typeName);
      if (type == null)
      {
         type = this.factory.createTGType(typeName);
      }

      if (atts.getValue(ITraceConstants.ID_ATTR) != null)
      {
         TGObjectArgument mcgObjectArgument = new TGObjectArgument();

         String objectId = atts.getValue(ITraceConstants.ID_ATTR);
         TGObject object = this.factory.getTGObject(objectId);
         if (object == null)
         {
            object = this.factory.createTGObject(objectId);
            object.setType(type);
            this.currentTracePath.addToObjects(object);
         }
         mcgObjectArgument.setObject(object);

         mcgArgument = mcgObjectArgument;
      }
      else
      {
         TGPrimitiveArgument mcgPrimitiveObject = new TGPrimitiveArgument();
         String value = atts.getValue(ITraceConstants.VALUE_ATTR);
         if (value != null)
         {
            mcgPrimitiveObject.setValue(value);
         }
         mcgArgument = mcgPrimitiveObject;
      }

      mcgArgument.setType(type);
      this.currentMethodCall.addToArguments(mcgArgument);
   }


   private void startTracePathEndTag(Attributes atts)
   {
      String timeStamp = atts.getValue(ITraceConstants.TIME_ATTR);
      if (timeStamp != null)
      {
         this.currentTracePath.setEndTime(Long.parseLong(timeStamp));
      }
   }

}
