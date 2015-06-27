package org.reclipse.tracer.model.tracegraph.xml;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.fujaba.commons.utils.Visitor;
//import org.reclipse.commons.logging.Logger;
import org.reclipse.tracer.model.tracegraph.TGArgument;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGObjectArgument;
import org.reclipse.tracer.model.tracegraph.TGPrimitiveArgument;
import org.reclipse.tracer.model.tracegraph.TGTrace;
import org.reclipse.tracer.model.tracegraph.TGTracePath;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * @author mvdetten
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TraceGraphWriter extends Visitor
{

   private final TGTrace trace;


   public TraceGraphWriter(TGTrace trace)
   {
      this.trace = trace;
   }


   /**
    * @param file The file to save the document in.
    * @return true, if file has been written
    */
   public boolean save(File file)
   {
      try
      {
         FileWriter fileWriter;
         fileWriter = new FileWriter(file);
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         generateXMLDocument(bufferedWriter);
         bufferedWriter.flush();
         bufferedWriter.close();
      }
      catch (IOException e)
      {
         // Logger.logError(e);
         return false;
      }

      return true;
   }


   /**
    * Saves the file zip compressed
    * 
    * @param file
    * @return true if the file has been written
    */
   public boolean saveCompressed(File file)
   {
      try
      {
         FileOutputStream fileOutputStream = new FileOutputStream(file);
         ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
         ZipEntry zipEntry = new ZipEntry(ITraceConstants.FILE_NAME);
         zipOutputStream.putNextEntry(zipEntry);

         OutputStreamWriter outputStreamWriter = new OutputStreamWriter(zipOutputStream);
         Writer writer = new BufferedWriter(outputStreamWriter);
         this.generateXMLDocument(writer);
         writer.flush();
         writer.close();
      }
      catch (IOException e)
      {
         // Logger.logError(e);
         return false;
      }

      return true;
   }


   public void generateXMLDocument(Writer writer) throws IOException
   {
      writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n\n");
      writer.write("<!DOCTYPE ");
      writer.write(ITraceConstants.TRACE_TAG);
      writer.write(" SYSTEM \"");
      writer.write(ITraceConstants.SYSTEM_ID);
      writer.write("\">\n\n");

      visit(this.trace, writer);
   }


   public void visitTGTrace(TGTrace trace, Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("<");
      writer.write(ITraceConstants.TRACE_TAG);
      writer.write(" ");

      // mainclass CDATA #IMPLIED
      if (trace.getMainClass() != null)
      {
         writer.write(ITraceConstants.MAIN_CLASS_ATTRIBUTE);
         writer.write("=\"");
         writer.write(this.escapeString(trace.getMainClass()));
         writer.write("\" ");
      }
      // date CDATA #IMPLIED
      if (trace.getDate() != null)
      {
         writer.write(ITraceConstants.DATE_ATTRIBUTE);
         writer.write("=\"");
         writer.write(this.escapeString(trace.getDate()));
         writer.write("\" ");
      }
      // symbolicexecution CDATA #IMPLIED
      writer.write(ITraceConstants.SYMBOLIC_EXECUTION_ATTR);
      writer.write("=\"");
      writer.write(this.escapeString(Boolean.toString(trace.getSymbolicExecution())));
      writer.write("\" ");

      writer.write(">\n\n");

      for (Iterator<TGTracePath> tracePathIter = trace.iteratorOfTracePaths(); tracePathIter.hasNext();)
         visit(tracePathIter.next(), writer);

      writer.write("</");
      writer.write(ITraceConstants.TRACE_TAG);
      writer.write(">\n\n");
   }


   public void visitTGTracePath(TGTracePath tracingInput, Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("  <");
      writer.write(ITraceConstants.TRACEPATH_START_TAG);
      writer.write(" ");

      // name CDATA #REQUIRED
      writer.write(ITraceConstants.NAME_ATTR);
      writer.write("=\"");
      writer.write(this.escapeString(tracingInput.getName()));
      writer.write("\"");
      writer.write(" ");

      // time CDATA #IMPLIED
      if (tracingInput.getStartTime() != 0)
      {
         writer.write(ITraceConstants.TIME_ATTR);
         writer.write("=\"");
         writer.write(this.escapeString(Long.toString(tracingInput.getStartTime())));
         writer.write("\"");
      }

      writer.write("/>\n\n");

      Iterator<TGType> classLoadedIter = tracingInput.iteratorOfLoadedClasses();
      while (classLoadedIter.hasNext())
      {
         TGType type = classLoadedIter.next();
         writer.write("    <");
         writer.write(ITraceConstants.CLASS_LOADED_TAG);
         writer.write(" ");
         writer.write(ITraceConstants.NAME_ATTR);
         writer.write("=\"");
         writer.write(this.escapeString(type.getName()));
         writer.write("\">\n");

         Iterator<TGType> superTypeIter = type.iteratorOfSuperTypes();
         while (superTypeIter.hasNext())
         {
            TGType superType = superTypeIter.next();
            writer.write("      <");
            writer.write(ITraceConstants.SUPER_TYPE_TAG);
            writer.write(" ");
            writer.write(ITraceConstants.NAME_ATTR);
            writer.write("=\"");
            writer.write(this.escapeString(superType.getName()));
            writer.write("\"/>\n");
         }

         writer.write("    </");
         writer.write(ITraceConstants.CLASS_LOADED_TAG);
         writer.write(">\n\n");
      }

      Iterator<TGMethodCall> methodCallsIter = tracingInput.iteratorOfMethodCalls();
      while (methodCallsIter.hasNext())
      {
         visit(methodCallsIter.next(), writer);
      }

      writer.write("  <");
      writer.write(ITraceConstants.TRACEPATH_END_TAG);
      writer.write(" ");

      // time CDATA #IMPLIED
      if (tracingInput.getEndTime() != 0)
      {
         writer.write(ITraceConstants.TIME_ATTR);
         writer.write("=\"");
         writer.write(this.escapeString(Long.toString(tracingInput.getEndTime())));
         writer.write("\"");
      }
      writer.write("/>\n\n");

   }


   public void visitTGMethodCall(TGMethodCall tgmCall, Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("    <");
      writer.write(ITraceConstants.METHOD_ENTRY_TAG);
      writer.write(" ");

      // id CDATA #REQUIRED
      writer.write(ITraceConstants.ID_ATTR);
      writer.write("=\"");
      writer.write(this.escapeString(tgmCall.getId()));
      writer.write("\"");
      writer.write(" ");

      // name CDATA #REQUIRED
      writer.write(ITraceConstants.NAME_ATTR);
      writer.write("=\"");
      writer.write(this.escapeString(tgmCall.getName()));
      writer.write("\"");
      writer.write(" ");

      // time CDATA #IMPLIED
      if (tgmCall.getTime() != 0)
      {
         writer.write(ITraceConstants.TIME_ATTR);
         writer.write("=\"");
         writer.write(this.escapeString(Long.toString(tgmCall.getTime())));
         writer.write("\"");
      }

      writer.write(">\n");

      visit(tgmCall.getCaller(), writer, ITraceConstants.CALLER_TAG);
      visit(tgmCall.getCallee(), writer, ITraceConstants.CALLEE_TAG);

      Iterator<TGArgument> argumentsIter = tgmCall.iteratorOfArguments();
      while (argumentsIter.hasNext())
      {
         visit(argumentsIter.next(), writer);
      }

      writer.write("    </");
      writer.write(ITraceConstants.METHOD_ENTRY_TAG);
      writer.write(">\n\n");
   }


   public void visitTGObject(TGObject tgObject, Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      if (ITraceConstants.CALLER_TAG.equals(arguments[1]))
      {
         writer.write("      <");
         writer.write(ITraceConstants.CALLER_TAG);
         writer.write(" ");
         writer.write(ITraceConstants.ID_ATTR);
         writer.write("=\"");
         writer.write(this.escapeString(tgObject.getId()));
         writer.write("\"");
         writer.write(" ");
         writer.write(ITraceConstants.TYPE_ATTR);
         writer.write("=\"");
         writer.write(this.escapeString(tgObject.getType().getName()));
         writer.write("\"/>\n");
      }
      else if (ITraceConstants.CALLEE_TAG.equals(arguments[1]))
      {
         writer.write("      <");
         writer.write(ITraceConstants.CALLEE_TAG);
         writer.write(" ");
         writer.write(ITraceConstants.ID_ATTR);
         writer.write("=\"");
         writer.write(this.escapeString(tgObject.getId()));
         writer.write("\"");
         writer.write(" ");
         writer.write(ITraceConstants.TYPE_ATTR);
         writer.write("=\"");
         writer.write(this.escapeString(tgObject.getType().getName()));
         writer.write("\"/>\n");
      }

   }


   public void visitTGArgument(TGArgument tgArgument, Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("      <");
      writer.write(ITraceConstants.ARGUMENT_TAG);
      writer.write(" ");

      // id CDATA #IMPLIED
      if (tgArgument instanceof TGObjectArgument)
      {
         TGObject object = ((TGObjectArgument) tgArgument).getObject();
         if (object != null)
         {
            writer.write(ITraceConstants.ID_ATTR);
            writer.write("=\"");
            writer.write(this.escapeString(object.getId()));
            writer.write("\" ");
         }
      }

      // value CDATA #REQUIRED
      if (tgArgument instanceof TGPrimitiveArgument)
      {
         String value = ((TGPrimitiveArgument) tgArgument).getValue();
         if (value != null)
         {
            writer.write(ITraceConstants.VALUE_ATTR);
            writer.write("=\"");
            writer.write(this.escapeString(value));
            writer.write("\" ");
         }
      }

      // type CDATA #REQUIRED
      writer.write(ITraceConstants.TYPE_ATTR);
      writer.write("=\"");
      writer.write(this.escapeString(tgArgument.getType().getName()));
      writer.write("\"/>\n");
   }


   /**
    * Escapes the given string. Replaces <,> and " by their entities.
    * 
    * @param value
    * @return escaped value
    */
   private String escapeString(String value)
   {
      return value.replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;");
   }


}
