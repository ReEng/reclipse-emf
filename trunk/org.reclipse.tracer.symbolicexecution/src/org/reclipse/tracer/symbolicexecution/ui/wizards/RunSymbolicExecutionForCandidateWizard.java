package org.reclipse.tracer.symbolicexecution.ui.wizards;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jface.wizard.Wizard;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.output.TraceFileLogger;
import org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants;
import org.reclipse.tracer.symbolicexecution.ui.launching.RunSymbolicExecutionForCandidateLaunchConfiguration;
import org.reclipse.tracer.symbolicexecution.ui.launching.SymbolicExecutionLaunchConfigurationDelegate;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class RunSymbolicExecutionForCandidateWizard extends Wizard
{
   RunSymbolicExecutionForCandidateWizardPage page;

   private String traceFileName = "Trace";

   private String traceFilePath = ""; // TODO define a path

   private ASGAnnotation annotation;


   public RunSymbolicExecutionForCandidateWizard(ASGAnnotation anno)
   {
      super();
      this.annotation = anno;
   }


   @Override
   public boolean performFinish()
   {
      ILaunchConfiguration configuration = createLaunchConfiguration();
      NullProgressMonitor monitor = new NullProgressMonitor();
      SymbolicExecutionLaunchConfigurationDelegate launcher = new SymbolicExecutionLaunchConfigurationDelegate();
      try
      {
         launcher.launch(configuration, "tracer", null, monitor);
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
      return true;
   }


   /**
    * Uses the users inputs from the RunSymbolicExecutionCandidateWizardPage to generate a launch
    * configuration that allows to reuse the SymbolicExecutionLaunchConfigurationDelegate.
    * 
    * @return ILaunchConfiguration
    */
   private ILaunchConfiguration createLaunchConfiguration()
   {
      RunSymbolicExecutionForCandidateLaunchConfiguration configuration = new RunSymbolicExecutionForCandidateLaunchConfiguration();
      // configuration.setAttribute(ITracerConstants.VM_ARGUMENTS, null); // not needed
      // configuration.setAttribute(ITracerConstants.PROGRAM_ARGUMENTS, null); // not needed
      configuration.setAttribute(ITracerConstants.MAIN_CLASS,
            this.page.getMainClassName());
      List<String> classPaths = new ArrayList<String>();
      classPaths.addAll(this.page.getClassPaths());
      String programArguments = this.page.getProgramArguments();
      configuration.setAttribute(ITracerConstants.PROGRAM_ARGUMENTS,
            programArguments);
      configuration.setAttribute(ITracerConstants.CLASS_PATH, classPaths);
      configuration.setAttribute(
            SymbolicExecutionTracerConstants.SYMBOLIC_METHOD,
            this.page.getSymbolicMethodString());
      Map<String, String> traceFileProperties = new HashMap<String, String>();
      traceFileProperties.put("compressed", "false");
      traceFileProperties.put("traceFileName", traceFileName);
      traceFileProperties.put("argumentValues", "true");
      traceFileProperties.put("timeStamps", "false");
      configuration.setAttribute(TraceFileLogger.class.getName()
            + ITracerConstants.PROPERTIES, traceFileProperties);
      configuration.setAttribute(ITracerConstants.TRACE_DEFINITION,
            this.page.getTraceDefinitionString());

      return configuration;
   }


   @Override
   public void addPages()
   {
      this.page = new RunSymbolicExecutionForCandidateWizardPage(
            "Run Symbolic Execution", this.annotation);
      this.addPage(this.page);
   }


   public String getTraceFileName()
   {
      return this.traceFilePath + this.traceFileName;
   }


   public BehavioralPatternsCatalog getCatalog()
   {
      return this.page.getBehavioralPatternsCatalog();
   }


   public ASGAnnotation getAnnotation()
   {
      return this.annotation;
   }


}
