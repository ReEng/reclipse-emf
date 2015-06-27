package org.reclipse.tracer.symbolicexecution.jpf;


import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.JPFListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerPlugin;
import org.reclipse.tracer.symbolicexecution.jpf.listener.ProgressMonitorListener;


public class JPFRunner
{
   private Vector<String> classPaths = new Vector<String>();

   private Vector<String> nativeClassPaths = new Vector<String>();

   private String mainClass = "";

   private String[] arguments;

   private IProgressMonitor monitor = null;

   private String symbolicMethod = "";

   private Vector<JPFListener> jpfListener = new Vector<JPFListener>();


   /**
    * Classes for the SUT
    * 
    * @param classPath
    * @throws FileNotFoundException
    */
   public void addClassPath(String classPath) throws FileNotFoundException
   {
      if (!new File(classPath).exists())
         throw new FileNotFoundException(classPath);
      this.classPaths.add(classPath);
   }


   public void addClassPath(List<String> classPaths)
         throws FileNotFoundException
   {
      for (String classPath : classPaths)
         this.addClassPath(classPath);
   }


   public void addJPFListener(JPFListener l)
   {
      this.jpfListener.add(l);
   }


   /**
    * Classes for JPF VM
    * 
    * @param classPath
    * @throws FileNotFoundException
    */
   public void addNativeClassPath(String classPath)
         throws FileNotFoundException
   {
      if (!new File(classPath).exists())
         throw new FileNotFoundException(classPath);
      this.nativeClassPaths.add(classPath);
   }


   private void addClassPathsTo(Config cfg)
   {
      this.appendVectorToProperty(cfg, "classpath", this.classPaths, ";");
   }


   private void appendVectorToProperty(Config cfg, String property,
         Vector<String> entries, String separator)
   {
      StringBuilder sb = new StringBuilder();
      if (cfg.hasValue(property))
         sb.append(cfg.getProperty(property).trim());
      // remove trailing separator - if available
      if (sb.toString().endsWith(separator))
         sb.delete(sb.lastIndexOf(separator), sb.length());

      // append all entries
      for (String entry : entries)
         sb.append(separator).append(entry);

      // remove leading separator -if available
      if (sb.toString().startsWith(separator))
         sb.delete(0, separator.length() - 1);

      cfg.setProperty(property, sb.toString());
   }


   private void addNativeClassPathsTo(Config cfg)
   {
      this.appendVectorToProperty(cfg, "native_classpath",
            this.nativeClassPaths, ";");
   }


   private Config initConfig(String[] args) throws IOException
   {
      Config cfg = JPF.createConfig(args);

      // Add fake entries - jpf config has null as default values. if it includes
      // a new properties file, it pops this values and pushes them again. Unfortunately
      // it throws a NullPointerException
      cfg.put("config", ".");
      cfg.put("config_path", ".");

      URL propertiesFile = FileLocator.resolve(SymbolicExecutionTracerPlugin
            .getDefault().getBundle().getEntry("/jpf.properties"));
      cfg.put("@include", propertiesFile.getFile());
      URL symbcPropertiesFile = FileLocator
            .resolve(SymbolicExecutionTracerPlugin.getDefault().getBundle()
                  .getEntry("/jpf-symbc.properties"));
      cfg.put("@include", symbcPropertiesFile.getFile());
      URL awtPropertiesFile = FileLocator.resolve(SymbolicExecutionTracerPlugin
            .getDefault().getBundle().getEntry("/jpf-awt.properties"));
      cfg.put("@include", awtPropertiesFile.getFile());

      // Register own log handler. Publishes to the TracerView
      cfg.put(
            "log.handler.class",
            org.reclipse.tracer.symbolicexecution.ui.util.EventsModelLogHandler.class
                  .getName());

      this.addNativeClassPathsTo(cfg);
      this.addClassPathsTo(cfg);


      if (!this.mainClass.isEmpty())
         cfg.setTarget(this.mainClass);

      if (this.arguments.length > 0)
         cfg.setTargetArgs(this.arguments);

      // symbc (+ awt)
      cfg.put("peer_packages",
            "gov.nasa.jpf.jvm,<model>,<default>,gov.nasa.jpf.symbc,gov.nasa.jpf.awt");
      cfg.put("vm.insn_factory.class",
            "gov.nasa.jpf.symbc.SymbolicInstructionFactory");

      cfg.put(
            "symbolic.method",
            this.symbolicMethod);

      return cfg;
   }


   public void run(String[] addOptions) throws IOException
   {
      Config cfg = this.initConfig(addOptions);

      JPF jpf = new JPF(cfg);
      cfg.print(new PrintWriter(System.out));

      if (this.monitor != null)
         jpf.addSearchListener(new ProgressMonitorListener(this.monitor));

      for (JPFListener jpfListener : this.jpfListener)
         jpf.addListener(jpfListener);

      jpf.run();
   }


   /**
    * Sets the System Under Test
    * 
    * @param classPath Path to the SUT binaries
    * @param mainClass Main class to execute. Needs a static main(String[]) method.
    * @throws FileNotFoundException
    */
   public void setSUT(String classPath, String mainClass, String... args)
         throws FileNotFoundException
   {
      this.addClassPath(classPath);
      this.mainClass = mainClass;
      this.arguments = args;
   }


   public void setProgressMonitor(IProgressMonitor monitor)
   {
      this.monitor = monitor;
   }


   public void setSymbolicMethod(String method)
   {
      this.symbolicMethod = method;
   }
}
