package org.reclipse.tracer.output;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.TracerPlugin;
import org.reclipse.tracer.extensionpoints.IMonitoredEventsListener;
import org.reclipse.tracer.model.tracegraph.xml.ITraceConstants;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.BooleanValue;
import com.sun.jdi.ByteValue;
import com.sun.jdi.CharValue;
import com.sun.jdi.DoubleValue;
import com.sun.jdi.FloatValue;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.IntegerValue;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.Location;
import com.sun.jdi.LongValue;
import com.sun.jdi.Method;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.ShortValue;
import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.Value;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
public class TraceFileLogger implements IMonitoredEventsListener
{

   private static final String TRACE_FILE_NAME = "traceFileName";

   private static final String TIME_STAMPS = "timeStamps";

   private static final String ARGUMENTS_VALUES = "argumentValues";

   private static final String ZIPPED = "compressed";


   private static final byte NONE = 0;

   private static final byte INITILIZED = 1;

   private static final byte ERROR = 2;


   private byte state = NONE;

   private boolean timeStamps;

   private boolean argumentValues;

   private boolean zipped;

   private String mainClass = "";

   private Writer writer;

   private ZipOutputStream zipOutputStream;


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#initialize(org.reclipse.tracer.Tracer,
    *      java.util.Map)
    */
   public boolean initialize(final Tracer tracer,
         final Map<String, String> properties)
   {
      try
      {
         this.timeStamps = Boolean.valueOf(properties.get(TIME_STAMPS));
         this.argumentValues = Boolean
               .valueOf(properties.get(ARGUMENTS_VALUES));
         this.zipped = Boolean.valueOf(properties.get(ZIPPED));

         String traceFileName = properties.get(TRACE_FILE_NAME);
         if (traceFileName == null)
         {
            TracerPlugin.logError("No trace output file name provided.");
            return false;
         }
         else
         {
            if (this.zipped)
            {
               if (!traceFileName.endsWith(ITraceConstants.ZIP_FILE_SUFFIX))
               {
                  traceFileName += ITraceConstants.ZIP_FILE_SUFFIX;
               }
               final File file = new File(traceFileName);
               final FileOutputStream fileOutputStream = new FileOutputStream(
                     file);
               this.zipOutputStream = new ZipOutputStream(fileOutputStream);
               final ZipEntry zipEntry = new ZipEntry(ITraceConstants.FILE_NAME);
               this.zipOutputStream.putNextEntry(zipEntry);

               final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                     this.zipOutputStream);
               this.writer = new BufferedWriter(outputStreamWriter);
            }
            else
            {
               if (!traceFileName.endsWith(ITraceConstants.XML_FILE_SUFFIX))
               {
                  traceFileName += ITraceConstants.XML_FILE_SUFFIX;
               }
               final FileWriter fileWriter = new FileWriter(traceFileName);
               this.writer = new BufferedWriter(fileWriter);
            }
         }
      }
      catch (final IOException e)
      {
         TracerPlugin.logError("Trace output file cannot be opened.", e);
         return false;
      }

      final ILaunchConfiguration launchConfig = tracer.getLaunchConfiguration();
      try
      {
         this.mainClass = launchConfig
               .getAttribute(ITracerConstants.MAIN_CLASS,
                     ITracerConstants.DEFAULT_MAIN_CLASS);
      }
      catch (final CoreException e)
      {
         // just ignore
      }

      this.state = INITILIZED;

      return true;
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmStateChanged(int)
    */
   public void vmStateChanged(final int newVMState)
   {
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmStart(com.sun.jdi.ThreadReference)
    */
   public void vmStart(final ThreadReference thread)
   {
      if (this.state == INITILIZED)
      {
         // header
         appendOutput("<?xml version=\"1.0\" standalone=\"no\"?>\n");

         appendOutput("<!DOCTYPE ");
         appendOutput(ITraceConstants.TRACE_TAG);
         appendOutput(" SYSTEM \"");
         appendOutput(ITraceConstants.SYSTEM_ID);
         appendOutput("\">\n");

         // <Trace>
         appendOutput("\n  <");
         appendOutput(ITraceConstants.TRACE_TAG);
         appendOutput("\n   ");
         appendOutput(ITraceConstants.MAIN_CLASS_ATTRIBUTE);
         appendOutput("=\"");
         appendOutput(this.escapeString(this.mainClass));
         appendOutput("\"\n   ");
         appendOutput(ITraceConstants.SYMBOLIC_EXECUTION_ATTR);
         appendOutput("=\"");
         appendOutput("false");
         appendOutput("\"\n   ");
         appendOutput(ITraceConstants.DATE_ATTRIBUTE);
         appendOutput("=\"");
         appendOutput((new Date(System.currentTimeMillis())).toString());
         appendOutput("\">\n");

         // <TracePathStart name="" time=""/>
         appendOutput("\n  <");
         appendOutput(ITraceConstants.TRACEPATH_START_TAG);

         // tracePath name
         appendNameAttribute(thread.name());

         // time stamp
         if (this.timeStamps)
         {
            appendTimeAttribute();
         }

         appendOutput("/>\n");

         flush();
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#classLoaded(com.sun.jdi.ReferenceType,
    *      java.util.List)
    */
   @SuppressWarnings("unchecked")
   public void classLoaded(final ReferenceType type, final List superTypes)
   {
      if (this.state == INITILIZED)
      {
         appendOutput("\n  <");
         appendOutput(ITraceConstants.CLASS_LOADED_TAG);

         // class name
         appendNameAttribute(type.name());

         appendOutput(">\n");

         final Iterator<?> iter = superTypes.iterator();
         while (iter.hasNext())
         {
            final ReferenceType superType = (ReferenceType) iter.next();

            appendOutput("    <");
            appendOutput(ITraceConstants.SUPER_TYPE_TAG);

            // class name
            appendNameAttribute(superType.name());

            appendOutput("/>\n");
         }

         appendOutput("  </");
         appendOutput(ITraceConstants.CLASS_LOADED_TAG);
         appendOutput(">\n");

         flush();
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodEventRequestCreated(com.sun.jdi.Method)
    */
   public void methodEventRequestCreated(final Method method)
   {
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodEntry(com.sun.jdi.ThreadReference)
    */
   public void methodEntry(final ThreadReference thread)
   {
      if (this.state == INITILIZED)
      {
         appendOutput("\n  <");
         appendOutput(ITraceConstants.METHOD_ENTRY_TAG);

         // method call id
         appendIDAttribute(Long.toString(getNewID(thread)));

         // method call name
         appendNameAttribute(getMethodName(thread, 0));

         // thread name
         appendOutput(" ");
         appendOutput(ITraceConstants.THREAD_ATTR);
         appendOutput("=\"");
         appendOutput(this.escapeString(thread.name()));
         appendOutput("\"");

         // time stamp
         if (this.timeStamps)
         {
            appendTimeAttribute();
         }

         appendOutput(">\n");

         // caller tag
         appendObjectTag(thread, ITraceConstants.CALLER_TAG, 1);

         // callee tag
         appendObjectTag(thread, ITraceConstants.CALLEE_TAG, 0);

         // parameter tag
         appendArgumentsTags(thread);

         appendOutput("  </");
         appendOutput(ITraceConstants.METHOD_ENTRY_TAG);
         appendOutput(">\n");

         flush();
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#methodExit(com.sun.jdi.ThreadReference)
    */
   public void methodExit(final ThreadReference thread)
   {
      if (this.state == INITILIZED)
      {
         appendOutput("\n  <");
         appendOutput(ITraceConstants.METHOD_EXIT_TAG);

         // method call id
         appendIDAttribute(Long.toString(popID(thread)));

         // time stamp
         if (this.timeStamps)
         {
            appendTimeAttribute();
         }

         appendOutput("/>\n");

         flush();
      }
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#exceptionThrown(com.sun.jdi.ThreadReference,
    *      com.sun.jdi.Location, com.sun.jdi.ObjectReference)
    */
   public void exceptionThrown(final ThreadReference thread,
         final Location catchLocation, final ObjectReference exception)
   {
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#threadDeath(com.sun.jdi.ThreadReference)
    */
   public void threadDeath(final ThreadReference thread)
   {
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmDisconnect()
    */
   public void vmDisconnect()
   {
      processEnd();
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#vmDeath()
    */
   public void vmDeath()
   {
      processEnd();
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMonitoredEventsListener#tracerException(Exception)
    */
   public void tracerException(final Exception exception)
   {
   }


   private void processEnd()
   {
      if (this.state == INITILIZED)
      {
         appendOutput("\n  <");
         appendOutput(ITraceConstants.TRACEPATH_END_TAG);

         if (this.timeStamps)
         {
            appendTimeAttribute();
         }

         appendOutput("/>\n\n</");
         appendOutput(ITraceConstants.TRACE_TAG);
         appendOutput(">\n");

         closeFile();
      }
   }


   private void appendObjectTag(final ThreadReference thread, final String tag,
         final int frameIndex)
   {
      appendOutput("    <");
      appendOutput(tag);

      try
      {
         final StackFrame frame = thread.frame(frameIndex);
         final ObjectReference object = frame.thisObject();
         ReferenceType referenceType;

         // object id
         if (object == null)
         {
            appendIDAttribute(ITraceConstants.STATIC_OBJECT);

            final Method method = frame.location().method();
            referenceType = method.declaringType();
         }
         else
         {
            appendIDAttribute(Long.toString(object.uniqueID()));

            referenceType = object.referenceType();
         }

         // object type
         appendTypeAttribute(referenceType.name());
      }
      catch (final IncompatibleThreadStateException e)
      {
         appendExceptionAttr(e.getMessage());
      }

      appendOutput("/>\n");
   }


   private void appendArgumentsTags(final ThreadReference thread)
   {
      final boolean argumentsExist = false;

      try
      {
         final StackFrame stackFrame = thread.frame(0);
         final Method method = stackFrame.location().method();

         if (!method.isAbstract() && !method.isNative()
               && !method.isSynthetic() && !method.isStaticInitializer())
         {
            // throws AbsentInformationException if method is abstract or native
            final List<?> arguments = method.arguments();

            final Iterator<?> iter = arguments.iterator();
            while (iter.hasNext())
            {
               appendOutput("    <");
               appendOutput(ITraceConstants.ARGUMENT_TAG);

               final LocalVariable localVariable = (LocalVariable) iter.next();
               if (stackFrame != null && localVariable != null)
               {
                  if (this.argumentValues)
                  {
                     appendArgumentValue(localVariable, stackFrame);
                  }
                  appendTypeAttribute(localVariable.typeName());
               }

               appendOutput("/>\n");
            }
         }
      }
      catch (final IncompatibleThreadStateException e)
      {
         if (argumentsExist)
         {
            appendExceptionAttr(e.getMessage());
         }
      }
      catch (final AbsentInformationException e)
      {
      }
   }


   private void appendTimeAttribute()
   {
      appendOutput(" ");
      appendOutput(ITraceConstants.TIME_ATTR);
      appendOutput("=\"");
      appendOutput(this.escapeString(Long.toString(Calendar.getInstance().getTimeInMillis())));
      appendOutput("\"");
   }


   private void appendNameAttribute(final String name)
   {
      appendOutput(" ");
      appendOutput(ITraceConstants.NAME_ATTR);
      appendOutput("=\"");
      appendOutput(this.escapeString(name));
      appendOutput("\"");
   }


   private void appendIDAttribute(final String id)
   {
      appendOutput(" ");
      appendOutput(ITraceConstants.ID_ATTR);
      appendOutput("=\"");
      appendOutput(this.escapeString(id));
      appendOutput("\"");
   }


   private void appendTypeAttribute(final String type)
   {
      appendOutput(" ");
      appendOutput(ITraceConstants.TYPE_ATTR);
      appendOutput("=\"");
      appendOutput(this.escapeString(type));
      appendOutput("\"");
   }


   private void appendArgumentValue(final LocalVariable localVar,
         final StackFrame frame)
   {
      try
      {
         final Value value = frame.getValue(localVar);

         appendOutput(" ");

         if (value instanceof ObjectReference)
         {
            if ("java.lang.String".equals(value.type().name()))
            {
               appendOutput(ITraceConstants.VALUE_ATTR);
               appendOutput("=");
               // TODO string must be correctly encoded
               appendOutput(this.escapeString(value.toString()));
            }
            else
            {
               appendOutput(ITraceConstants.ID_ATTR);
               appendOutput("=\"");
               appendOutput(this.escapeString(Long.toString(((ObjectReference) value).uniqueID())));
               appendOutput("\"");
            }
         }
         else
         {
            appendOutput(ITraceConstants.VALUE_ATTR);
            appendOutput("=\"");

            if (value instanceof BooleanValue)
            {
               appendOutput(this.escapeString(((BooleanValue) value).toString()));
            }
            else if (value instanceof CharValue)
            {
               appendOutput(this.escapeString(((CharValue) value).toString()));
            }
            else if (value instanceof ShortValue)
            {
               appendOutput(this.escapeString(((ShortValue) value).toString()));
            }
            else if (value instanceof ByteValue)
            {
               appendOutput(this.escapeString(((ByteValue) value).toString()));
            }
            else if (value instanceof IntegerValue)
            {
               appendOutput(this.escapeString(((IntegerValue) value).toString()));
            }
            else if (value instanceof LongValue)
            {
               appendOutput(this.escapeString(((LongValue) value).toString()));
            }
            else if (value instanceof FloatValue)
            {
               appendOutput(this.escapeString(((FloatValue) value).toString()));
            }
            else if (value instanceof DoubleValue)
            {
               appendOutput(this.escapeString(((DoubleValue) value).toString()));
            }
            else
            {
               appendOutput(ITraceConstants.UNKNOWN_VALUE);
            }

            appendOutput("\"");
         }
      }
      catch (final IllegalArgumentException e)
      {
         appendOutput(ITraceConstants.VALUE_ATTR);
         appendOutput("=\"");
         appendOutput(ITraceConstants.UNKNOWN_VALUE);
         appendOutput("\"");
      }
   }


   private void appendExceptionAttr(final String message)
   {
      appendOutput(" ");
      appendOutput(ITraceConstants.EXCEPTION_ATTR);
      appendOutput("=\"");
      appendOutput(this.escapeString(message));
      appendOutput("\"");
   }


   private String getMethodName(final ThreadReference thread,
         final int frameIndex)
   {
      String methodName = null;
      try
      {
         final StackFrame frame = thread.frame(frameIndex);
         final Method method = frame.location().method();
         methodName = method.name();

         if (methodName.startsWith("<") && methodName.endsWith(">"))
         {
            final StringBuffer buffer = new StringBuffer();
            appendOutput(ITraceConstants.XML_LESS);
            appendOutput(this.escapeString(methodName.substring(1, methodName.length() - 1)));
            appendOutput(ITraceConstants.XML_GREATER);

            methodName = buffer.toString();
         }
      }
      catch (final IncompatibleThreadStateException e)
      {
         methodName = ITraceConstants.UNKNOWN_VALUE;
      }

      return methodName;
   }


   private Map<Long, List<Long>> methodIDs = new HashMap<Long, List<Long>>();

   private long lastMethodID;


   private synchronized long getNewID(final ThreadReference thread)
   {
      final Long threadID = thread.uniqueID();
      List<Long> methodIDList = this.methodIDs.get(threadID);
      if (methodIDList == null)
      {
         methodIDList = new LinkedList<Long>();
         this.methodIDs.put(threadID, methodIDList);
      }

      methodIDList.add(++this.lastMethodID);
      return this.lastMethodID;
   }


   private synchronized long popID(final ThreadReference thread)
   {
      final Long threadID = thread.uniqueID();
      final List<Long> methodIDList = this.methodIDs.get(threadID);
      if (methodIDList == null)
      {
         return -2;
      }

      if (methodIDList.size() == 0)
      {
         return -3;
      }

      return methodIDList.remove(methodIDList.size() - 1);
   }


   private void appendOutput(final String string)
   {
      try
      {
         this.writer.write(string, 0, string.length());
      }
      catch (final IOException e)
      {
         this.state = ERROR;
         System.err.println("Error writing trace file: " + e.getMessage());
         e.printStackTrace();
      }
   }


   private void flush()
   {
      try
      {
         this.writer.flush();
      }
      catch (final IOException e)
      {
         this.state = ERROR;
         System.err.println("Error writing trace file: " + e.getMessage());
         e.printStackTrace();
      }
   }


   private void closeFile()
   {
      try
      {
         this.writer.flush();
         this.writer.close();

         if (this.zipped)
         {
            this.zipOutputStream.close();
         }
      }
      catch (final IOException e)
      {
         this.state = ERROR;
         System.err.println("Error writing trace file: " + e.getMessage());
         e.printStackTrace();
      }
   }
   
   /**
    * Escapes the given string. Replaces <,> and " by their entities.
    * @param value
    * @return escaped value
    */
   private String escapeString(String value)
   {
      return value.replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;");
   }

}
