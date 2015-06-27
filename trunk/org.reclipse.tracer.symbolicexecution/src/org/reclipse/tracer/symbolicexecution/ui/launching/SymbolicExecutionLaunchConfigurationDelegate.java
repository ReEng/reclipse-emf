package org.reclipse.tracer.symbolicexecution.ui.launching;


import gov.nasa.jpf.JPFException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.reclipse.tracer.TracerPlugin;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionReader;
import org.reclipse.tracer.output.TraceFileLogger;
import org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants;
import org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerPlugin;
import org.reclipse.tracer.symbolicexecution.jpf.JPFRunner;
import org.reclipse.tracer.symbolicexecution.jpf.listener.ExecutionMonitorListener;
import org.reclipse.tracer.symbolicexecution.jpf.listener.TraceTracker;
import org.reclipse.tracer.symbolicexecution.jpf.listener.TracerViewListener;
import org.reclipse.tracer.symbolicexecution.util.TraceDefinitionFilter;
import org.reclipse.tracer.ui.TracerUIPlugin;
import org.reclipse.tracer.ui.models.EventsModel;
import org.reclipse.tracer.ui.models.ExecutionMonitorModel;
import org.reclipse.tracer.ui.models.VMEvent;

import static org.reclipse.tracer.ITracerConstants.*;
import static org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants.*;


public class SymbolicExecutionLaunchConfigurationDelegate implements
      ILaunchConfigurationDelegate
{

   private JPFRunner jpf = null;

   private TraceDefinition traceDef = null;

   private Set<String> supportedConfigurations = new HashSet<String>();

   private String[] jpfArguments;


   public SymbolicExecutionLaunchConfigurationDelegate()
   {
   }


   public void launch(ILaunchConfiguration configuration, String mode,
         ILaunch launch, IProgressMonitor monitor) throws CoreException
   {
      try
      {
         monitor.subTask("Loading trace definition");
         this.loadTraceDefinition(configuration);
         monitor.worked(1);

         monitor.subTask("Configuring tracer");
         this.configureTracer(configuration, monitor);
         this.checkUnsupportedConfigurations(configuration);
         monitor.worked(1);
      }
      catch (FileNotFoundException e)
      {
         throw new CoreException(new Status(Status.ERROR,
               SymbolicExecutionTracerConstants.PLUGIN_ID, e.getMessage(), e));
      }


      monitor.subTask("Running tracer");
      try
      {
         this.runTracer();
      }
      catch (IOException e)
      {
         throw new CoreException(new Status(Status.ERROR,
               SymbolicExecutionTracerConstants.PLUGIN_ID, e.getMessage(), e));
      }
      monitor.done();

   }


   /**
    * Loads the trace definition
    * 
    * @param configuration
    * @throws FileNotFoundException
    * @throws CoreException
    */
   private void loadTraceDefinition(ILaunchConfiguration configuration)
         throws FileNotFoundException,
            CoreException
   {
      this.supportedConfigurations.add(TRACE_DEFINITION_FILE_NAME);
      String traceDefFileName = configuration.getAttribute(
            TRACE_DEFINITION_FILE_NAME, DEFAULT_TRACE_DEFINITION_FILE_NAME);
      if (traceDefFileName.isEmpty())
      {
         String traceDefString = configuration.getAttribute(TRACE_DEFINITION,
               DEFAULT_TRACE_DEFINITION);
         StringReader stringReader = new StringReader(traceDefString);
         BufferedReader reader = new BufferedReader(stringReader);
         this.traceDef = TraceDefinitionReader.load(reader);
      }
      else
      {
         this.traceDef = TraceDefinitionReader.load(traceDefFileName);
      }

   }


   /**
    * Configures the tracer
    * 
    * @param configuration
    * @param monitor
    * @throws FileNotFoundException
    * @throws CoreException
    */
   private void configureTracer(ILaunchConfiguration configuration,
         IProgressMonitor monitor) throws FileNotFoundException, CoreException
   {
      this.jpf = new JPFRunner();
      System.out.println(configuration.getAttributes());

      // JPF VM Arguments
      this.supportedConfigurations.add(VM_ARGUMENTS);
      this.jpfArguments = configuration.getAttribute(VM_ARGUMENTS,
            DEFAULT_VM_ARGUMENTS).split(" ");

      // Main class aka System Under Test
      this.supportedConfigurations.add(MAIN_CLASS);
      this.supportedConfigurations.add(PROGRAM_ARGUMENTS);
      final String mainClass = configuration.getAttribute(MAIN_CLASS,
            DEFAULT_MAIN_CLASS);
      final String[] arguments = configuration.getAttribute(PROGRAM_ARGUMENTS,
            DEFAULT_PROGRAM_ARGUMENTS).split(" ");
      this.jpf.setSUT(".", mainClass, arguments);

      // ClassPath for the SUT
      this.supportedConfigurations.add(CLASS_PATH);
      final List<String> classPaths = configuration.getAttribute(CLASS_PATH,
            DEFAULT_CLASS_PATH);
      this.jpf.addClassPath(classPaths);

      // Method to execute symbolically
      this.supportedConfigurations.add(SYMBOLIC_METHOD);
      final String symbolicMethod = configuration.getAttribute(SYMBOLIC_METHOD,
            DEFAULT_SYMBOLIC_METHOD);
      this.jpf.setSymbolicMethod(symbolicMethod);

      // Default (native) classpaths for JPF
      this.enableDefaultConfig(jpf);

      // Default (native) classpaths for jpf-symbc
      this.enableSymbolicConfig(jpf);
      
      // Default (native) classpaths for jpf-awt
      this.enableAWTConfig(jpf);


      // Properties from trace listener: TraceFileLogger
      this.supportedConfigurations.add(TraceFileLogger.class.getName());
      this.supportedConfigurations.add(TraceFileLogger.class.getName()
            + ITracerConstants.PROPERTIES);
      Map<String, String> traceFileProperties = configuration.getAttribute(
            TraceFileLogger.class.getName() + ITracerConstants.PROPERTIES,
            new HashMap<String, String>());

      this.jpf.setProgressMonitor(monitor);

      // Initialize TraceDefinitionFilter
      this.supportedConfigurations.add(IGNORE_CONSTRUCTORS);
      this.supportedConfigurations.add(IGNORE_JAVA_LANG_OBJECT);
      this.supportedConfigurations.add(IGNORE_PRIVATE_METHODS);
      TraceDefinitionFilter traceDefFilter = new TraceDefinitionFilter(
            this.traceDef);
      final boolean ingorePrivateMethods = configuration.getAttribute(
            IGNORE_PRIVATE_METHODS, DEFAULT_IGNORE_PRIVATE_METHODS);
      traceDefFilter.setIgnorePrivateMethods(ingorePrivateMethods);
      final boolean ingoreConstructors = configuration.getAttribute(
            IGNORE_CONSTRUCTORS, DEFAULT_IGNORE_CONSTRUCTORS);
      traceDefFilter.setIgnoreConstructors(ingoreConstructors);
      final boolean ingoreJavaLangObject = configuration.getAttribute(
            IGNORE_JAVA_LANG_OBJECT, DEFAULT_IGNORE_JAVA_LANG_OBJECT);
      traceDefFilter.setIgnoreJavaLangObject(ingoreJavaLangObject);

      // jpf listeners:

      // Main component: Creates the trace file
      this.supportedConfigurations.add(TraceFileLogger.class.getName()
            + ".timeStamps");
      this.supportedConfigurations.add(TraceFileLogger.class.getName()
            + ".traceFileName");
      this.supportedConfigurations.add(TraceFileLogger.class.getName()
            + ".compressed");
      this.supportedConfigurations.add(TraceFileLogger.class.getName()
            + ".argumentValues");
      final String traceFileName = traceFileProperties.get("traceFileName");
      final boolean traceCompressed = Boolean.parseBoolean(traceFileProperties
            .get("compressed"));
      final boolean timeStamps = Boolean.parseBoolean(traceFileProperties
            .get("timeStamps"));
      final boolean argumentValues = Boolean.parseBoolean(traceFileProperties
            .get("argumentValues"));
      final TraceTracker traceTracker = new TraceTracker();
      traceTracker.setTraceDefinitionFilter(traceDefFilter);
      traceTracker.setTraceFile(traceFileName);
      traceTracker.setTraceCompressed(traceCompressed);
      traceTracker.setTimeStamps(timeStamps);
      traceTracker.setArgumentValues(argumentValues);
      this.jpf.addJPFListener(traceTracker);

      // populate tracer view
      final EventsModel eventsModel = (EventsModel) TracerUIPlugin.getDefault()
            .getEventsModel();
      final TracerViewListener tracerViewListener = new TracerViewListener(
            eventsModel, traceDefFilter);
      this.jpf.addJPFListener(tracerViewListener);

      // populate execution monitor view
      final ExecutionMonitorModel executionMonitorModel = (ExecutionMonitorModel) TracerUIPlugin
            .getDefault().getExecutionMonitorModel();
      final ExecutionMonitorListener executionMonitorListener = new ExecutionMonitorListener(
            executionMonitorModel, traceDefFilter);
      this.jpf.addJPFListener(executionMonitorListener);

   }


   public void runTracer() throws IOException
   {
      try
      {
         this.jpf.run(this.jpfArguments);
      }
      catch (JPFException e)
      {
         VMEvent event = new VMEvent();
         event.setType(VMEvent.TRACER_EXCEPTION);
         event.setMessage(e.getMessage());
         ((EventsModel) TracerUIPlugin.getDefault().getEventsModel())
               .addToEvents(event);
         SymbolicExecutionTracerPlugin.log(Status.ERROR, e.getMessage(), e);
      }

   }


   /**
    * Adds the locations required for the jpf listeners to the native classpath
    * 
    * @param jpf
    * @throws FileNotFoundException
    */
   private void enableDefaultConfig(JPFRunner jpf) throws FileNotFoundException
   {
      jpf.addNativeClassPath(this
            .getPluginLocation(SymbolicExecutionTracerConstants.PLUGIN_ID)
            + "bin/");
      jpf.addNativeClassPath(this.getPluginLocation(TracerPlugin.PLUGIN_ID)
            + "bin/");
//      jpf.addNativeClassPath(this
//            .getPluginLocation("de.uni_paderborn.runtimetools")
//            + "target/classes");

      jpf.addClassPath(this
            .getPluginLocation(SymbolicExecutionTracerConstants.PLUGIN_ID)
            + "lib/jpf-classes.jar");
      
      jpf.addClassPath(this
            .getPluginLocation(SymbolicExecutionTracerConstants.PLUGIN_ID)
            + "lib/jpf-awt/jpf-awt-classes.jar");
   }


   /**
    * Adds the required jars for jpf-symbc to the native classpath
    * 
    * @param jpf
    * @throws FileNotFoundException
    */
   private void enableSymbolicConfig(JPFRunner jpf)
         throws FileNotFoundException
   {
      String native_classpaths[] = { "jpf-symbc.jar", "STPJNI.jar",
            "choco-1_2_04.jar", "hampi.jar", "scale.jar", "automaton.jar",
            "iasolver.jar", "string.jar", "commons-lang-2.4.jar", "libcvc3.jar" };
      for (String file : native_classpaths)
         jpf.addNativeClassPath(this
               .getPluginLocation(SymbolicExecutionTracerConstants.PLUGIN_ID)
               + "lib/jpf-symbc/" + file);

   }
   
   /**
    * Adds the required jars for jpf-awt to the native classpath
    * 
    * @param jpf
    * @throws FileNotFoundException
    */
   private void enableAWTConfig(JPFRunner jpf)
         throws FileNotFoundException
   {
      String native_classpaths[] = { "jpf-awt.jar", "jpf-awt-classes.jar"};
      for (String file : native_classpaths)
         jpf.addNativeClassPath(this
               .getPluginLocation(SymbolicExecutionTracerConstants.PLUGIN_ID)
               + "lib/jpf-awt/" + file);

   }


   private String getPluginLocation(String pluginID)
   {
      String location = Platform.getBundle(pluginID).getLocation()
            .replace("reference:file:", "");
      if (!new File(location).exists())
         SymbolicExecutionTracerPlugin.log(Status.WARNING,
               "Location for the plugin " + pluginID + " does not exist: "
                     + location);
      return location;
   }


   /**
    * Checks and reports unsupported configuration attributes
    * 
    * @param configuration
    * @throws CoreException
    */
   private void checkUnsupportedConfigurations(
         ILaunchConfiguration configuration) throws CoreException
   {
      for (Object attribute : configuration.getAttributes().keySet())
      {
         this.checkUnsupportedAttribute(attribute);
         // Tracer claims to support TraceFileLogger - check all properties
         if (attribute.equals(TraceFileLogger.class.getName()))
            this.checkUnsupportedPropertiesFromListener(configuration,
                  TraceFileLogger.class.getName());
      }

   }


   /**
    * Checks and reports whether the given configuration attribute is supported or not
    * 
    * @param attribute
    */
   private void checkUnsupportedAttribute(Object attribute)
   {
      if (!this.supportedConfigurations.contains(attribute))
      {
         SymbolicExecutionTracerPlugin.log(Status.WARNING,
               "Configuration attribute " + attribute
                     + " is not supported by this tracer.");
      }
   }


   /**
    * Checks and reports whether the properties/attributes of a listener are supported or not
    * 
    * @param configuration
    * @param listener
    * @throws CoreException
    */
   private void checkUnsupportedPropertiesFromListener(
         ILaunchConfiguration configuration, String listener)
         throws CoreException
   {
      for (Object attribute : configuration.getAttribute(listener + PROPERTIES,
            Collections.emptyMap()).keySet())
         this.checkUnsupportedAttribute(listener + "." + attribute);
   }


}
