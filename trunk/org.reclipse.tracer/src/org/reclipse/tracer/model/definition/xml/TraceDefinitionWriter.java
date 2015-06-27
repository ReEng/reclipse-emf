package org.reclipse.tracer.model.definition.xml;


import java.io.BufferedWriter;
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
import org.reclipse.tracer.model.definition.AbstractTraceClass;
import org.reclipse.tracer.model.definition.CallerClass;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalClass;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.Parameter;
import org.reclipse.tracer.model.definition.TraceDefinition;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TraceDefinitionWriter extends Visitor
{

   private final TraceDefinition traceDefinition;


   public TraceDefinitionWriter(TraceDefinition traceDefinition)
   {
      this.traceDefinition = traceDefinition;
   }


   /**
    * @param fileName The file to save the document in.
    * @return true, if file has been written
    */
   public boolean save(String fileName)
   {
      return save(fileName, false);
   }


   /**
    * @param fName The file to save the document in.
    * @param zipped if true, the trace definition will be saved in a zipped file and the zip entry
    *           is named TraceDefinition.xtracedefinition
    * @return true, if file has been written
    */
   public boolean save(String fName, boolean zipped)
   {
      String fileName = fName;
      try
      {
         if (zipped)
         {
            if (!fileName.endsWith(ITraceDefinitionConstants.ZIP_FILE_SUFFIX))
            {
               fileName += ITraceDefinitionConstants.ZIP_FILE_SUFFIX;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ZipOutputStream zipOutputStream = new ZipOutputStream(
                  fileOutputStream);
            ZipEntry zipEntry = new ZipEntry(
                  ITraceDefinitionConstants.FILE_NAME);
            zipOutputStream.putNextEntry(zipEntry);

            OutputStreamWriter writer = new OutputStreamWriter(zipOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            generateXMLDocument(bufferedWriter);
            bufferedWriter.flush();
            zipOutputStream.closeEntry();
            bufferedWriter.close();
            zipOutputStream.close();
         }
         else
         {
            if (!fileName.endsWith(ITraceDefinitionConstants.XML_FILE_SUFFIX))
            {
               fileName += ITraceDefinitionConstants.XML_FILE_SUFFIX;
            }
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            generateXMLDocument(bufferedWriter);
            bufferedWriter.flush();
            bufferedWriter.close();
         }
      }
      catch (IOException exception)
      {
         // Logger.logError("Error writing Trace Definition file '" + fileName
         // + "':", exception);
         return false;
      }

      return true;
   }


   public void generateXMLDocument(Writer writer) throws IOException
   {
      writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n\n");
      writer.write("<!DOCTYPE ");
      writer.write(ITraceDefinitionConstants.TRACE_DEFINITION_TAG);
      writer.write(" SYSTEM \"");
      writer.write(ITraceDefinitionConstants.SYSTEM_ID);
      writer.write("\">\n\n");

      visit(this.traceDefinition, writer);
   }


   public void visitTraceDefinition(TraceDefinition tracingInput,
         Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("<");
      writer.write(ITraceDefinitionConstants.TRACE_DEFINITION_TAG);
      writer.write(">\n");

      if (tracingInput.getCriticalTrace() != null)
      {
         visit(tracingInput.getCriticalTrace(), writer);
         writer.write("\n");
      }
      if (tracingInput.getConsiderTrace() != null)
      {
         visit(tracingInput.getConsiderTrace(), writer);
         writer.write("\n");
      }

      writer.write("</");
      writer.write(ITraceDefinitionConstants.TRACE_DEFINITION_TAG);
      writer.write(">\n\n");
   }


   public void visitConsiderTrace(ConsiderTrace considerTrace,
         Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("\n  <");
      writer.write(ITraceDefinitionConstants.CONSIDER_TRACE_TAG);
      writer.write(">\n");

      Iterator<AbstractTraceClass> iter = considerTrace.iteratorOfClasses();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("\n  </");
      writer.write(ITraceDefinitionConstants.CONSIDER_TRACE_TAG);
      writer.write(">\n");
   }


   public void visitCriticalTrace(CriticalTrace criticalTrace,
         Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("\n  <");
      writer.write(ITraceDefinitionConstants.CRITICAL_TRACE_TAG);
      writer.write(">\n");

      Iterator<AbstractTraceClass> iter = criticalTrace.iteratorOfClasses();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("\n  </");
      writer.write(ITraceDefinitionConstants.CRITICAL_TRACE_TAG);
      writer.write(">\n");
   }


   public void visitConsiderClass(ConsiderClass considerClass,
         Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("\n    <");
      writer.write(ITraceDefinitionConstants.CONSIDER_CLASS_TAG);
      writer.write(" ");
      writer.write(ITraceDefinitionConstants.NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(this.escapeString(considerClass.getName()));
      writer.write("\">\n");

      Iterator<ConsiderMethod> iter = considerClass.iteratorOfMethods();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("\n    </");
      writer.write(ITraceDefinitionConstants.CONSIDER_CLASS_TAG);
      writer.write(">\n");
   }


   public void visitCriticalClass(CriticalClass criticalClass,
         Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("\n    <");
      writer.write(ITraceDefinitionConstants.CRITICAL_CLASS_TAG);
      writer.write(" ");
      writer.write(ITraceDefinitionConstants.NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(this.escapeString(criticalClass.getName()));
      writer.write("\">\n");

      Iterator<CallerClass> iter = criticalClass.iteratorOfCallers();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("    </");
      writer.write(ITraceDefinitionConstants.CRITICAL_CLASS_TAG);
      writer.write(">\n");
   }


   public void visitCallerClass(CallerClass callerClass, Object... arguments)
         throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("        <");
      writer.write(ITraceDefinitionConstants.CALLER_CLASS_TAG);
      writer.write(" ");
      writer.write(ITraceDefinitionConstants.NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(this.escapeString(callerClass.getName()));
      writer.write("\"/>\n");
   }


   public void visitConsiderMethod(ConsiderMethod considerMethod,
         Object... arguments) throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("\n      <");
      writer.write(ITraceDefinitionConstants.CONSIDER_METHOD_TAG);
      writer.write(" ");
      writer.write(ITraceDefinitionConstants.NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(this.escapeString(considerMethod.getName()));
      writer.write("\">\n");

      Iterator<Parameter> iter = considerMethod.iteratorOfParameters();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      Iterator<CallerClass> callerIter = considerMethod.iteratorOfCallers();
      while (callerIter.hasNext())
      {
         visit(callerIter.next(), writer);
      }

      writer.write("      </");
      writer.write(ITraceDefinitionConstants.CONSIDER_METHOD_TAG);
      writer.write(">\n");
   }


   public void visitParameter(Parameter parameter, Object... arguments)
         throws IOException
   {
      Writer writer = (Writer) arguments[0];

      writer.write("        <");
      writer.write(ITraceDefinitionConstants.PARAMETER_TAG);
      writer.write(" ");
      writer.write(ITraceDefinitionConstants.TYPE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(this.escapeString(parameter.getType()));
      writer.write("\"/>\n");
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
