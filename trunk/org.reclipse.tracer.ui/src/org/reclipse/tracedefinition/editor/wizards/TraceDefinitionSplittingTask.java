package org.reclipse.tracedefinition.editor.wizards;


import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.input.IStructuralAnnotationsConstants;
import org.reclipse.behavior.inference.input.StructuralAnnotationsWriter;
import org.reclipse.tracer.model.definition.CallerClass;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalClass;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.Parameter;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.model.definition.xml.ITraceDefinitionConstants;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionReader;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionWriter;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3799 $ $Date: 2007-09-12 20:39:42 +0200 (Mi, 12 Sep 2007) $
 */
public class TraceDefinitionSplittingTask implements IRunnableWithProgress
{

   private String sourceTraceDefinitionFile;


   public void setSourceTraceDefinitionFile(final String fileName)
   {
      this.sourceTraceDefinitionFile = fileName;
   }


   private String targetTraceDefinitionFile;


   public void setTargetTraceDefinitionFile(final String fileName)
   {
      this.targetTraceDefinitionFile = fileName;
   }


   private boolean traceDefinitionFileZipped = true;


   public void setTraceDefinitionFileZipped(final boolean zipped)
   {
      this.traceDefinitionFileZipped = zipped;
   }

   private boolean saveAnnotationFiles = true;


   public void setSaveAnnotationFiles(final boolean saveAnnotationFiles)
   {
      this.saveAnnotationFiles = saveAnnotationFiles;
   }

   private String targetAnnotationsFile;


   public void setTargetAnnotationsFile(final String fileName)
   {
      this.targetAnnotationsFile = fileName;
   }


   private boolean annotationsFileZipped = true;


   public void setAnnotationsFileZipped(final boolean zipped)
   {
      this.annotationsFileZipped = zipped;
   }


   private List<Set<StructuralAnnotation>> splittedAnnotations;


   public void setSplittedAnnotations(
         final List<Set<StructuralAnnotation>> splittedAnnotations)
   {
      this.splittedAnnotations = splittedAnnotations;
   }


   /**
    * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
    */
   public void run(final IProgressMonitor monitor)
         throws InvocationTargetException,
            InterruptedException
   {
      // calculate task size
      int taskSize = this.splittedAnnotations.size();
      for (final Set<StructuralAnnotation> annotations : this.splittedAnnotations)
      {
         taskSize += annotations.size();
      }
      monitor.beginTask("Trace Definition Filtering", taskSize);

      // load trace definition
      TraceDefinition sourceTraceDefinition = null;
      try
      {
         sourceTraceDefinition = TraceDefinitionReader
               .load(this.sourceTraceDefinitionFile);
      }
      catch (final FileNotFoundException exception)
      {
         exception.printStackTrace();
         monitor.done();
         return;
      }
      if (sourceTraceDefinition == null)
      {
         monitor.done();
         return;
      }

      // split and save trace definitions for each set of annotations
      final int splits = 0;
      String traceDefinitionFileName = getFileNameWithoutSuffix(
            this.targetTraceDefinitionFile,
            ITraceDefinitionConstants.XML_FILE_SUFFIX,
            ITraceDefinitionConstants.ZIP_FILE_SUFFIX,
            this.traceDefinitionFileZipped);
      String annotationsFileName = getFileNameWithoutSuffix(
            this.targetAnnotationsFile,
            IStructuralAnnotationsConstants.XML_FILE_SUFFIX,
            IStructuralAnnotationsConstants.ZIP_FILE_SUFFIX,
            this.annotationsFileZipped);
      for (final Set<StructuralAnnotation> annotations : this.splittedAnnotations)
      {
         // split trace definition
         final TraceDefinition targetTraceDefinition = splitTraceDefinition(
               sourceTraceDefinition, annotations, monitor);

         // Calculates the filenames in case of several trace definition files
         if (this.splittedAnnotations.size() > 1)
         {
            traceDefinitionFileName += String.valueOf(splits);
            annotationsFileName += String.valueOf(splits);
         }

         // save filtered trace definition
         final TraceDefinitionWriter writer = new TraceDefinitionWriter(
               targetTraceDefinition);
         writer.save(traceDefinitionFileName, this.traceDefinitionFileZipped);

         // save filtered annotations file
         if (this.saveAnnotationFiles)
         {
            saveStructuralAnnotations(annotations, annotationsFileName);
         }

         monitor.worked(1);
      }

      monitor.done();
   }


   private TraceDefinition splitTraceDefinition(
         final TraceDefinition sourceTraceDefinition,
         final Set<StructuralAnnotation> annotations,
         final IProgressMonitor monitor)
   {
      final TraceDefinition targetTraceDefinition = new TraceDefinition();
      final ConsiderTrace targetConsiderTrace = new ConsiderTrace();
      final CriticalTrace targetCriticalTrace = new CriticalTrace();

      final ConsiderTrace sourceConsiderTrace = sourceTraceDefinition
            .getConsiderTrace();
      final CriticalTrace sourceCriticalTrace = sourceTraceDefinition
            .getCriticalTrace();

      final Set<ConsiderClass> considerClasses = new HashSet<ConsiderClass>();
      final Set<CriticalClass> criticalClasses = new HashSet<CriticalClass>();
      final Set<String> methods = new HashSet<String>();

      for (final StructuralAnnotation annotation : annotations)
      {
         // clear sets
         considerClasses.clear();
         criticalClasses.clear();
         methods.clear();

         // sort for classes and methods
         for (final String key : annotation.keysOfNodes())
         {
            final String name = annotation.getFromNodes(key);
            if (isClass(name))
            {
               ConsiderClass considerClass = null;
               if (sourceConsiderTrace != null)
               {
                  considerClass = sourceConsiderTrace.getFromClasses(name);
               }
               if (considerClass != null)
               {
                  considerClasses.add(considerClass);
               }
               else
               {
                  CriticalClass criticalClass = null;
                  if (sourceCriticalTrace != null)
                  {
                     criticalClass = sourceCriticalTrace.getFromClasses(name);
                  }
                  if (criticalClass != null)
                  {
                     criticalClasses.add(criticalClass);
                  }
               }
            }
            else if (isMethod(name))
            {
               methods.add(name);
            }
         }

         // copy classes to target trace definition
         for (final ConsiderClass sourceClass : considerClasses)
         {
            deepCloneConsiderClass(sourceClass, methods, targetConsiderTrace);
         }

         for (final CriticalClass sourceClass : criticalClasses)
         {
            deepCloneCriticalClass(sourceClass, methods, targetCriticalTrace);
         }

         monitor.worked(1);
      }

      if (targetConsiderTrace.sizeOfClasses() > 0)
      {
         targetTraceDefinition.setConsiderTrace(targetConsiderTrace);
      }

      if (targetCriticalTrace.sizeOfClasses() > 0)
      {
         targetTraceDefinition.setCriticalTrace(targetCriticalTrace);
      }

      return targetTraceDefinition;
   }


   private boolean isClass(final String name)
   {
      return !name.contains("(");
   }


   private boolean isMethod(final String name)
   {
      return name.contains("(");
   }


   private void deepCloneConsiderClass(final ConsiderClass sourceClass,
         final Set<String> methods, final ConsiderTrace targetConsiderTrace)
   {
      ConsiderClass targetClass = targetConsiderTrace
            .getFromClasses(sourceClass.getName());
      if (targetClass == null)
      {
         targetClass = new ConsiderClass();
         targetClass.setName(sourceClass.getName());
         targetConsiderTrace.addToClasses(targetClass);
      }

      // deep clone methods
      for (final String method : methods)
      {
         final ConsiderMethod sourceMethod = sourceClass.getFromMethods(method);
         if (sourceMethod != null)
         {
            if (!targetClass.hasKeyInMethods(method))
            {
               deepCloneMethod(sourceMethod, targetClass);
            }
         }
      }
   }


   private void deepCloneCriticalClass(final CriticalClass sourceClass,
         final Set<String> methods, final CriticalTrace targetCriticalTrace)
   {
      CriticalClass targetClass = targetCriticalTrace
            .getFromClasses(sourceClass.getName());
      if (targetClass == null)
      {
         targetClass = new CriticalClass();
         targetClass.setName(sourceClass.getName());
         targetCriticalTrace.addToClasses(targetClass);
      }

      // clone caller classes
      final Iterator callersIter = sourceClass.iteratorOfCallers();
      while (callersIter.hasNext())
      {
         final CallerClass sourceCallerClass = (CallerClass) callersIter.next();
         final CallerClass targetCallerClass = new CallerClass();
         targetCallerClass.setName(sourceCallerClass.getName());
         targetClass.addToCallers(targetCallerClass);
      }
   }


   private void deepCloneMethod(final ConsiderMethod sourceMethod,
         final ConsiderClass targetClass)
   {
      final ConsiderMethod targetMethod = new ConsiderMethod();
      targetMethod.setName(sourceMethod.getName());
      targetClass.addToMethods(targetMethod);

      // clone parameters
      final Iterator paramIter = sourceMethod.iteratorOfParameters();
      while (paramIter.hasNext())
      {
         final Parameter sourceParam = (Parameter) paramIter.next();
         final Parameter targetParam = new Parameter();
         targetParam.setType(sourceParam.getType());
         targetMethod.addToParameters(targetParam);
      }

      // clone caller classes
      final Iterator callersIter = sourceMethod.iteratorOfCallers();
      while (callersIter.hasNext())
      {
         final CallerClass sourceCallerClass = (CallerClass) callersIter.next();
         final CallerClass targetCallerClass = new CallerClass();
         targetCallerClass.setName(sourceCallerClass.getName());
         targetMethod.addToCallers(targetCallerClass);
      }
   }


   private String getFileNameWithoutSuffix(final String fileName,
         final String xmlSuffix, final String zippedSuffix, final boolean zipped)
   {
      if (zipped && fileName.endsWith(zippedSuffix))
      {
         return fileName
               .substring(0, fileName.length() - zippedSuffix.length());
      }
      else if (!zipped && fileName.endsWith(xmlSuffix))
      {
         return fileName.substring(0, fileName.length() - xmlSuffix.length());
      }
      else
      {
         return fileName;
      }
   }


   private void saveStructuralAnnotations(
         final Set<StructuralAnnotation> annotations, final String fileName)
   {
      final StructuralAnnotationsWriter writer = new StructuralAnnotationsWriter(
            annotations);
      writer.save(fileName, this.annotationsFileZipped);
   }

}
