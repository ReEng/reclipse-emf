package org.reclipse.tracer;


import java.util.ArrayList;
import java.util.List;

import org.reclipse.tracer.launching.TracerVMConnector;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4645 $ $Date: 2011-02-26 10:51:31 +0100 (Sa, 26 Feb 2011) $
 */
public interface ITracerConstants
{
   public static final String PLUGIN_ID = "org.reclipse.tracer";


   public static final String TRACE_MODE = "trace";


   public static final String DEFAULT_MAIN_CLASS = "";

   public static final String DEFAULT_WORKING_DIRECTORY = "";

   public static final List<String> DEFAULT_CLASS_PATH = new ArrayList<String>();

   public static final String DEFAULT_PROGRAM_ARGUMENTS = "";

   public static final String DEFAULT_VM_ARGUMENTS = "";

   public static final String DEFAULT_HOST_NAME = "localhost";

   public static final int DEFAULT_PORT = 10000;

   public static final int DEFAULT_TIME_OUT = 10;

   public static final boolean DEFAULT_METHOD_EXIT_EVENTS = false;

   public static final boolean DEFAULT_IGNORE_PRIVATE_METHODS = true;

   public static final boolean DEFAULT_IGNORE_CONSTRUCTORS = true;

   public static final boolean DEFAULT_IGNORE_JAVA_LANG_OBJECT = true;

   public static final String DEFAULT_TRACE_DEFINITION_FILE_NAME = "";

   public static final String DEFAULT_TRACE_DEFINITION = "";

   public static final String MAIN_CLASS = PLUGIN_ID + ".MAIN_CLASS";

   public static final String CLASS_PATH = PLUGIN_ID + ".CLASSPATH";

   public static final String WORKING_DIRECTORY = PLUGIN_ID + ".WORKING_DIRECTORY";

   public static final String PROGRAM_ARGUMENTS = PLUGIN_ID + ".PROGRAM_ARGUMENTS";

   public static final String VM_ARGUMENTS = PLUGIN_ID + ".VM_ARGUMENTS";

   public static final String PORT = PLUGIN_ID + "." + TracerVMConnector.PORT;

   public static final String HOST_NAME = PLUGIN_ID + "." + TracerVMConnector.HOST_NAME;

   public static final String TIME_OUT = PLUGIN_ID + "." + TracerVMConnector.TIME_OUT;

   public static final String METHOD_EXIT_EVENTS = PLUGIN_ID + ".METHOD_EXIT_EVENTS";

   public static final String IGNORE_PRIVATE_METHODS = PLUGIN_ID + ".IGNORE_PRIVATE_METHODS";

   public static final String IGNORE_CONSTRUCTORS = PLUGIN_ID + ".IGNORE_CONSTRUCTORS";

   public static final String IGNORE_JAVA_LANG_OBJECT = PLUGIN_ID + ".IGNORE_JAVA_LANG_OBJECT";

   public static final String TRACE_DEFINITION_FILE_NAME = PLUGIN_ID + ".TRACE_DEFINITION_FILE_NAME";

   public static final String TRACE_DEFINITION = PLUGIN_ID + ".TRACE_DEFINITION";


   public static final int STATUS_CODE_OK = 0;

   public static final int STATUS_CODE_TRACER_START_FAILED = 1;

   public static final int STATUS_CODE_CONNECTOR_NOT_FOUND = 2;

   public static final int STATUS_CODE_VM_START_FAILED = 3;

   public static final int STATUS_CODE_JAVA_EXE_NOT_FOUND = 4;

   public static final int STATUS_CODE_VM_ATTACHING_FAILED = 5;


   public static final String MONITORED_EVENTS_LISTENERS_EXTENSION = "monitoredEventsListeners";

   public static final String MONITORED_EVENTS_LISTENER_ELEMENT = "monitoredEventsListener";

   public static final String PROPERTY_KEY_ELEMENT = "propertyKey";

   public static final String TRACER_CHANGED_LISTENERS_EXTENSION = "tracerChangedListeners";

   public static final String TRACER_CHANGED_LISTENER_ELEMENT = "tracerChangedListener";

   public static final String VM_STREAM_RECEIVERS_EXTENSION = "vmStreamReceivers";

   public static final String VM_SYSTEM_OUT_RECEIVER_EXTENSION = "systemOutReceiver";

   public static final String VM_SYSTEM_ERR_RECEIVER_EXTENSION = "systemErrReceiver";

   public static final String CLASS_ATTRIBUTE = "class";

   public static final String NAME_ATTRIBUTE = "name";

   public static final String OPTIONAL_ATTRIBUTE = "optional";

   public static final String ENABLED_ATTRIBUTE = "enabled";

   public static final String TYPE_ATTRIBUTE = "type";

   public static final String DEFAULT_VALUE_ATTRIBUTE = "defaultValue";

   public static final String PROPERTIES = ":properties";

}
