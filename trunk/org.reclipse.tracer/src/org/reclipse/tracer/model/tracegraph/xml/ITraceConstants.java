package org.reclipse.tracer.model.tracegraph.xml;

/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4040 $ $Date: 2009-05-07 15:01:48 +0200 (Do, 07 Mai 2009) $
 */
public interface ITraceConstants
{
   /**
    * URL used in TraceDefinition documents (XML-files) to identify the DTD.
    * DTD given as "SYSTEM" (non-public) definition.
    */
   public static final String SYSTEM_ID = "http://wwwcs.uni-paderborn.de/cs/fujaba/DTDs/Trace.dtd";
   
   
   public static final String XML_FILE_SUFFIX = ".xtrace";

   public static final String ZIP_FILE_SUFFIX = ".ztrace";

   public static final String[] FILE_EXTENSIONS = new String[] {
         "*" + ZIP_FILE_SUFFIX + "; *" + XML_FILE_SUFFIX };

   public static final String[] FILE_DESCRIPTIONS = new String[] {
         "(Compressed) Trace"};

   public static final String FILE_NAME = "Trace.xtrace";
   

   public final static String TRACE_TAG = "Trace";

   @Deprecated
   public final static String PROCESS_START_TAG = "ProcessStart";
   
   @Deprecated
   public final static String PROCESS_END_TAG = "ProcessEnd";
   
   public final static String TRACEPATH_START_TAG = "TracePathStart";
   
   public final static String TRACEPATH_END_TAG = "TracePathEnd";

   public final static String CLASS_LOADED_TAG = "ClassLoaded";

   public final static String SUPER_TYPE_TAG = "SuperType";

   public final static String METHOD_ENTRY_TAG = "MethodEntry";

   public final static String CALLEE_TAG = "Callee";

   public final static String CALLER_TAG = "Caller";

   public final static String METHOD_EXIT_TAG = "MethodExit";

   public final static String ARGUMENT_TAG = "Argument";

   public static final String MAIN_CLASS_ATTRIBUTE = "mainclass";

   public static final String DATE_ATTRIBUTE = "date";

   public final static String NAME_ATTR = "name";

   public final static String TIME_ATTR = "time";

   public final static String ID_ATTR = "id";

   public final static String TYPE_ATTR = "type";

   public final static String THREAD_ATTR = "thread";

   public final static String VALUE_ATTR = "value";

   public final static String EXCEPTION_ATTR = "exception";

   public final static String XML_LESS = "&lt;";

   public final static String XML_GREATER = "&gt;";

   public final static String UNKNOWN_VALUE = "N/A";

   public final static String STATIC_OBJECT = "static";
   
   public final static String SYMBOLIC_EXECUTION_ATTR = "symbolicexecution";

}
