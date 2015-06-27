package org.reclipse.tracer.symbolicexecution.util;


import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.MethodInfo;

import org.reclipse.tracer.model.definition.AbstractTrace;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.symbolicexecution.ui.util.TracerUIModelCreator;


/**
 * This class does not filter the trace definition itself, but it filters the method entries to be
 * traced with the help of the trace definition! [mcp]
 * 
 * @author avolk
 * @author Last editor: $Author: avolk $
 * @version $Revision: 198 $ $Date: 2010-10-12 21:04:16 +0200 (Di, 12 Okt 2010) $
 */
public class TraceDefinitionFilter
{
   private TraceDefinition traceDefinition = null;

   private boolean ignorePrivateMethods = false;

   private boolean ignoreJavaLangObject = false;

   private boolean ignoreConstructors = false;


   /**
    * Constructor
    * 
    * @param traceDef
    */
   public TraceDefinitionFilter(TraceDefinition traceDef)
   {
      this.traceDefinition = traceDef;
   }


   /**
    * Do we have to handle this class? Is it specified in the TraceDefinition file?
    * 
    * @param ci
    * @return true|false
    */
   public boolean accept(ClassInfo ci)
   {
      if (ci == null)
         return false;
      return this.isCritical(ci) || this.isConsidered(ci);
   }


   /**
    * Do we have to handle this method? Is it specified in the TraceDefinition file?
    * 
    * @param mi
    * @return true|false
    */
   public boolean accept(MethodInfo mi)
   {
      if (mi == null)
         return false;
      return this.isCritical(mi) || this.isConsidered(mi);
   }


   protected boolean isCritical(ClassInfo ci)
   {
      if (this.ignoreJavaLangObject && ci.getName().equals("java.lang.Object"))
         return false;
      return this.findClassInTrace(ci, this.traceDefinition.getCriticalTrace()) != null;
   }


   protected boolean isCritical(MethodInfo mi)
   {
      // Method [clinit]<clinit>()V has no classInfo
      if (mi.getClassInfo() == null)
         return false;

      if (!this.isCritical(mi.getClassInfo()))
         return false;

      if (this.ignoreConstructors && mi.isCtor())
         return false;

      if (this.ignorePrivateMethods && mi.isPrivate())
         return false;

      // By default all methods from critical classes
      return true;
   }


   protected boolean isConsidered(ClassInfo ci)
   {
      return this.findClassInTrace(ci, this.traceDefinition.getConsiderTrace()) != null;
   }


   protected boolean isConsidered(MethodInfo mi)
   {
      // Method [clinit]<clinit>()V has no classInfo
      if (mi.getClassInfo() == null)
         return false;

      // Check class considered
      String className = this.findClassInTrace(mi.getClassInfo(),
            this.traceDefinition.getConsiderTrace());
      if (className == null)
         return false;

      // Find matching method
      ConsiderClass considerClass = this.traceDefinition.getConsiderTrace()
            .getFromClasses(className);
      return considerClass.hasKeyInMethods(TracerUIModelCreator
            .createSignature(mi));
   }


   /**
    * Checks whether a class (or its superclass/implemented interfaced) is specified in a part of
    * the TraceDefiniton.
    * 
    * @param ci
    * @param trace ConsiderTrace|CriticalTrace
    * @return null|String null if the class was not found or the name of a class the given class
    *         inherits or implements and was specified in the TraceDefinition
    */
   private String findClassInTrace(ClassInfo ci, AbstractTrace trace)
   {
      if (trace == null || ci == null)
         return null;

      // Class directly specified
      String name = ci.getName();
      if (trace.hasKeyInClasses(name))
         return name;
      String[] tempArr = name.split("\\.");
      if (tempArr.length > 0)
      {
         name = tempArr[tempArr.length - 1];
         if (trace.hasKeyInClasses(name))
            return name;
      }

      if (!ci.isSystemClass())
      {
         // check super class
         String superClass = this.findClassInTrace(ci.getSuperClass(), trace);
         if (superClass != null)
            return superClass;

         // check interfaces
         for (ClassInfo interfaceInfo : ci.getAllInterfaceClassInfos())
         {
            String iface = this.findClassInTrace(interfaceInfo, trace);
            if (iface != null)
               return iface;
         }
      }
      return null;
   }


   /**
    * Ignore private methods during critical tracing
    * 
    * @param ignore
    */
   public void setIgnorePrivateMethods(boolean ignore)
   {
      this.ignorePrivateMethods = ignore;
   }


   /**
    * Ignore constructors during critical tracing
    * 
    * @param ignore
    */
   public void setIgnoreConstructors(boolean ignore)
   {
      this.ignoreConstructors = ignore;
   }


   /**
    * Ignore Methods from java.lang.Object during critical tracing
    * 
    * @param ignore
    */
   public void setIgnoreJavaLangObject(boolean ignore)
   {
      this.ignoreJavaLangObject = ignore;
   }
}
