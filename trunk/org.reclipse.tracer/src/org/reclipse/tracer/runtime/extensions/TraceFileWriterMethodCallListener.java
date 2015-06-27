package org.reclipse.tracer.runtime.extensions;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.reclipse.tracer.extensionpoints.AbstractMethodCallListener;
import org.reclipse.tracer.model.tracegraph.xml.ITraceConstants;
import org.reclipse.tracer.runtime.MethodCallObserver;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4129 $ $Date: 2009-08-07 10:30:53 +0200 (Fr, 07 Aug 2009) $
 */
public class TraceFileWriterMethodCallListener extends
      AbstractMethodCallListener
{

   private static final String OUTPUT_FILE_NAME = "outputFileName";

   private static final String TRACE_NAME = "traceName";

   private static final String ZIPPED = "compressed";


   private Writer writer;

   private ZipOutputStream zipOutputStream;

   private boolean zipped;


   /**
    * @see org.reclipse.tracer.extensionpoints.IMethodCallListener#initialize()
    */
   public boolean initialize()
   {
      String fileName = getProperty(OUTPUT_FILE_NAME);
      if (fileName != null)
      {
         try
         {
            this.zipped = Boolean.valueOf(getProperty(ZIPPED));
            if (this.zipped)
            {
               if (!fileName.endsWith(ITraceConstants.ZIP_FILE_SUFFIX))
               {
                  fileName += ITraceConstants.ZIP_FILE_SUFFIX;
               }
               File file = new File(fileName);
               FileOutputStream fileOutputStream = new FileOutputStream(file);
               this.zipOutputStream = new ZipOutputStream(fileOutputStream);
               ZipEntry zipEntry = new ZipEntry(ITraceConstants.FILE_NAME);
               this.zipOutputStream.putNextEntry(zipEntry);

               OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                     this.zipOutputStream);
               this.writer = new BufferedWriter(outputStreamWriter);
            }
            else
            {
               if (!fileName.endsWith(ITraceConstants.XML_FILE_SUFFIX))
               {
                  fileName += ITraceConstants.XML_FILE_SUFFIX;
               }
               FileWriter fileWriter = new FileWriter(fileName);
               this.writer = new BufferedWriter(fileWriter);
            }

            this.classesLoaded = new HashSet<String>();

            // print header
            appendOutput("<?xml version=\"1.0\" standalone=\"no\"?>\n");

            appendOutput("<!DOCTYPE ");
            appendOutput(ITraceConstants.TRACE_TAG);
            appendOutput(" SYSTEM \"");
            appendOutput(ITraceConstants.SYSTEM_ID);
            appendOutput(">\n");

            appendOutput("\n<");
            appendOutput(ITraceConstants.TRACE_TAG);
            appendOutput(" ");
            appendOutput(ITraceConstants.DATE_ATTRIBUTE);
            appendOutput("=\"");
            appendOutput((new Date(System.currentTimeMillis())).toString());
            appendOutput("\" ");
            appendOutput(ITraceConstants.SYMBOLIC_EXECUTION_ATTR);
            appendOutput("=\"");
            appendOutput("false");                        
            appendOutput("\">\n");

            appendOutput("\n  <");
            appendOutput(ITraceConstants.TRACEPATH_START_TAG);
            appendOutput(" ");
            appendOutput(ITraceConstants.NAME_ATTR);
            appendOutput("=\"");
            appendOutput(getProperty(TRACE_NAME));
            appendOutput("\" ");
            appendOutput(ITraceConstants.TIME_ATTR);
            appendOutput("=\"");
            appendOutput(getTime());
            appendOutput("\"/>\n");

            this.writer.flush();
         }
         catch (IOException e)
         {
            System.err.println("Error opening output file '" + fileName + "': "
                  + e.getMessage());
            e.printStackTrace();

            return false;
         }
      }
      else
      {
         System.err.println("No output file name specified.");
         return false;
      }

      return true;
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.AbstractMethodCallListener#terminate()
    */
   public void terminate()
   {
      try
      {
         appendOutput("\n  <");
         appendOutput(ITraceConstants.TRACEPATH_END_TAG);
         appendOutput(" ");
         appendOutput(ITraceConstants.TIME_ATTR);
         appendOutput("=\"");
         appendOutput(getTime());
         appendOutput("\"/>\n");

         appendOutput("\n</");
         appendOutput(ITraceConstants.TRACE_TAG);
         appendOutput(">\n");

         this.writer.flush();
         this.writer.close();
         if (this.zipped)
         {
            this.zipOutputStream.close();
         }
      }
      catch (IOException e)
      {
         System.err.println("Error writing trace file: " + e.getMessage());
         e.printStackTrace();
      }
   }


   private String getTime()
   {
      Calendar calendar = Calendar.getInstance();

      return String.format("%1tc", calendar);
   }


   /**
    * @see org.reclipse.tracer.extensionpoints.IMethodCallListener#methodCalled(long,
    *      java.lang.Object, java.lang.Class, java.lang.Object, java.lang.Class, java.lang.String,
    *      java.lang.String[])
    */
   public void methodCalled(long methodCallId, Object caller,
         Class staticCallerType, Object callee, Class staticCalleeType,
         String methodName, String... argumentTypes)
   {
      if (!this.errorOccurred)
      {
         if (caller == MethodCallObserver.STATIC_OBJECT)
         {
            generateClassLoadedTag(staticCallerType);
         }
         else
         {
            generateClassLoadedTag(caller.getClass());
         }

         if (callee == MethodCallObserver.STATIC_OBJECT)
         {
            generateClassLoadedTag(staticCalleeType);
         }
         else
         {
            generateClassLoadedTag(callee.getClass());
         }

         generateMethodEntryTag(methodCallId, caller, staticCallerType, callee,
               staticCalleeType, methodName, argumentTypes);
      }
   }


   private Set<String> classesLoaded;

   private boolean errorOccurred = false;


   private void generateClassLoadedTag(Class type)
   {
      if (!this.classesLoaded.contains(type.getName()))
      {
         appendOutput("\n  <");
         appendOutput(ITraceConstants.CLASS_LOADED_TAG);
         appendOutput(" ");
         appendOutput(ITraceConstants.NAME_ATTR);
         appendOutput("=\"");
         appendOutput(type.getName());
         appendOutput("\">\n");

         List<Class> superTypes = new ArrayList<Class>();
         computeSuperTypes(type, superTypes);
         for (Class superType : superTypes)
         {
            generateSuperTypeTag(superType);
         }

         appendOutput("  </");
         appendOutput(ITraceConstants.CLASS_LOADED_TAG);
         appendOutput(">\n");

         this.classesLoaded.add(type.getName());

         flush();
      }
   }


   private void generateSuperTypeTag(Class superType)
   {
      appendOutput("    <");
      appendOutput(ITraceConstants.SUPER_TYPE_TAG);
      appendOutput(" ");
      appendOutput(ITraceConstants.NAME_ATTR);
      appendOutput("=\"");
      appendOutput(superType.getName());
      appendOutput("\"/>\n");
   }


   private void generateMethodEntryTag(long methodCallId, Object caller,
         Class staticCallerType, Object callee, Class staticCalleeType,
         String methodName, String... argumentTypes)
   {
      appendOutput("\n  <");
      appendOutput(ITraceConstants.METHOD_ENTRY_TAG);
      appendOutput(" ");
      appendOutput(ITraceConstants.ID_ATTR);
      appendOutput("=\"id");
      appendOutput(Long.toString(methodCallId));
      appendOutput("\" ");
      appendOutput(ITraceConstants.NAME_ATTR);
      appendOutput("=\"");
      appendOutput(methodName);
      appendOutput("\">\n");

      String id = Long.toString(MethodCallObserver.getObjectID(caller));
      String type = caller.getClass().getName();
      if (caller == MethodCallObserver.STATIC_OBJECT)
      {
         type = staticCallerType.getName();
         id = type;
      }
      generateObjectTag(id, type, ITraceConstants.CALLER_TAG);

      id = Long.toString(MethodCallObserver.getObjectID(callee));
      type = callee.getClass().getName();
      if (caller == MethodCallObserver.STATIC_OBJECT)
      {
         type = staticCalleeType.getName();
         id = type;
      }
      generateObjectTag(id, type, ITraceConstants.CALLEE_TAG);

      for (String argumentType : argumentTypes)
      {
         generateArgumentTag(argumentType);
      }

      appendOutput("  </");
      appendOutput(ITraceConstants.METHOD_ENTRY_TAG);
      appendOutput(">\n");

      flush();
   }


   private void generateObjectTag(String id, String type, String tag)
   {
      appendOutput("    <");
      appendOutput(tag);
      appendOutput(" ");
      appendOutput(ITraceConstants.ID_ATTR);
      appendOutput("=\"");
      appendOutput(id);
      appendOutput("\" ");
      appendOutput(ITraceConstants.TYPE_ATTR);
      appendOutput("=\"");
      appendOutput(type);
      appendOutput("\"/>\n");
   }


   private void generateArgumentTag(String argumentType)
   {
      appendOutput("    <");
      appendOutput(ITraceConstants.ARGUMENT_TAG);
      appendOutput(" ");
      appendOutput(ITraceConstants.TYPE_ATTR);
      appendOutput("=\"");
      appendOutput(argumentType);
      appendOutput("\"/>\n");
   }


   private void computeSuperTypes(Class type, List<Class> superTypes)
   {
      Class superClass = type.getSuperclass();
      if (superClass != null)
      {
         superTypes.add(superClass);
         computeSuperTypes(superClass, superTypes);
      }

      for (Class superInterface : type.getInterfaces())
      {
         superTypes.add(superInterface);
         computeSuperTypes(superInterface, superTypes);
      }
   }


   private void appendOutput(String string)
   {
      try
      {
         this.writer.write(string, 0, string.length());
      }
      catch (IOException e)
      {
         this.errorOccurred = true;
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
      catch (IOException e)
      {
         this.errorOccurred = true;
         System.err.println("Error writing trace file: " + e.getMessage());
         e.printStackTrace();
      }
   }

}
