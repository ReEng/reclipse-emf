package org.reclipse.tracer.model.definition.xml;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3789 $ $Date: 2007-09-10 22:07:05 +0200 (Mo, 10 Sep 2007) $
 */
public interface ITraceDefinitionConstants
{
   /**
    * URL used in TraceDefinition documents (XML-files) to identify the DTD. DTD given as "SYSTEM"
    * (non-public) definition.
    */
   public static final String SYSTEM_ID = "http://wwwcs.uni-paderborn.de/cs/fujaba/DTDs/TraceDefinition.dtd";


   public static final String XML_FILE_SUFFIX = ".xtracedefinition";

   public static final String ZIP_FILE_SUFFIX = ".ztracedefinition";

   public static final String[] FILE_EXTENSIONS = new String[] {
         "*" + ZIP_FILE_SUFFIX, "*" + XML_FILE_SUFFIX };

   public static final String[] FILE_DESCRIPTIONS = new String[] {
         "Compressed Trace Definition", "Trace Definition" };

   public static final String FILE_NAME = "TraceDefinition.xtracedefinition";


   public static final String TRACE_DEFINITION_TAG = "TraceDefinition";

   public static final String CRITICAL_TRACE_TAG = "CriticalTrace";

   public static final String CRITICAL_CLASS_TAG = "CriticalClass";

   public static final String CALLER_CLASS_TAG = "CallerClass";

   public static final String CONSIDER_TRACE_TAG = "ConsiderTrace";

   public static final String CONSIDER_CLASS_TAG = "ConsiderClass";

   public static final String CONSIDER_METHOD_TAG = "ConsiderMethod";

   public static final String PARAMETER_TAG = "Parameter";

   public static final String NAME_ATTRIBUTE = "name";

   public static final String CONSTRUCTOR_ATTRIBUTE = "constructor";

   public static final String TYPE_ATTRIBUTE = "type";


   public static final String VMCONFIG_TAG = "VMConfig";

   public static final String ATTRIBUTE_MAINCLASS = "mainClass";

   public static final String ATTRIBUTE_CLASSPATH = "classPath";

   public static final String ATTRIBUTE_CLASSPATHDELIMITER = "classPathDelimiter";

   public static final String ATTRIBUTE_VMPARAMS = "vmParams";

   public static final String ATTRIBUTE_PARAMETER = "parameter";

   public static final String ATTRIBUTE_TRACEFILE = "traceFile";

   public static final String ATTRIBUTE_METHODREQUESTMODE = "methodRequestMode";

   public static final String ATTRIBUTE_ALLVISIBLEMETHODS = "allVisibleMethods";

}
