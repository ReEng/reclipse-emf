package org.reclipse.tracer.launching;


import java.io.File;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.ExecutionArguments;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.exceptions.TracerStartException;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4138 $ $Date: 2009-09-04 10:28:46 +0200 (Fr, 04 Sep 2009) $
 */
public class TracerLocalLaunchConfigurationDelegate extends
      AbstractTracerLaunchConfigurationDelegate
{

   /**
    * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration,
    *      java.lang.String, org.eclipse.debug.core.ILaunch,
    *      org.eclipse.core.runtime.IProgressMonitor)
    */
   public void launch(final ILaunchConfiguration configuration,
         final String mode, final ILaunch launch, IProgressMonitor monitor)
         throws CoreException
   {
      IProgressMonitor progressMonitor = monitor;
      
      if (progressMonitor == null)
      {
         progressMonitor = new NullProgressMonitor();
      }

      progressMonitor.beginTask("Loading trace definition...", 3);

      // prepare the tracer
      try
      {
         prepareTracer(configuration);
      }
      catch (final TracerStartException e)
      {
         abort("Starting the tracer failed.",
               ITracerConstants.STATUS_CODE_TRACER_START_FAILED, e);
      }

      progressMonitor.worked(1);
      progressMonitor.subTask("Preparing launch configuration...");

      // collect configuration
      final String mainTypeName = verifyMainTypeName(configuration);
      final String[] classpath = getClasspath(configuration);
      final String workingDirectory = getWorkingDirectory(configuration);

      // Program & VM arguments
      final String programArguments = getProgramArguments(configuration);
      final String vmArguments = getVMArguments(configuration);
      final ExecutionArguments execArgs = new ExecutionArguments(vmArguments,
            programArguments);

      final Map<?, ?> vmSpecificAttributesMap = configuration
            .getAttribute(
                  IJavaLaunchConfigurationConstants.ATTR_VM_INSTALL_TYPE_SPECIFIC_ATTRS_MAP,
                  (Map<?, ?>) null);

      // create VMRunnerConfiguration
      final VMRunnerConfiguration runConfig = new VMRunnerConfiguration(
            mainTypeName, classpath);
      runConfig.setProgramArguments(execArgs.getProgramArgumentsArray());
      runConfig.setVMArguments(execArgs.getVMArgumentsArray());
      runConfig.setWorkingDirectory(workingDirectory);
      runConfig.setResumeOnStartup(false);
      runConfig.setVMSpecificAttributesMap(vmSpecificAttributesMap);

      progressMonitor.worked(1);
      progressMonitor.subTask("Starting the target VM runner...");

      // start the IVMRunner
      final IVMRunner runner = getVMRunner(configuration, mode);
      runner.run(runConfig, launch, progressMonitor);

      progressMonitor.done();
   }


   /**
    * Returns the VM runner for the given launch mode to use when launching the given configuration.
    * 
    * @param configuration launch configuration
    * @param mode launch node
    * @return VM runner to use when launching the given configuration in the given mode
    * @throws CoreException if a VM runner cannot be determined
    */
   private IVMRunner getVMRunner(final ILaunchConfiguration configuration,
         final String mode) throws CoreException
   {
      final IVMInstall vm = verifyVMInstall(configuration);

      return new TracerVMRunner(vm);
   }


   /**
    * Verifies the VM install specified by the given launch configuration exists and returns the VM
    * install.
    * 
    * @param configuration launch configuration
    * @return the VM install specified by the given launch configuration
    * @exception CoreException if unable to retrieve the attribute, the attribute is unspecified, or
    *               if the home location is unspecified or does not exist
    */
   private IVMInstall verifyVMInstall(final ILaunchConfiguration configuration)
         throws CoreException
   {
      final IVMInstall vm = JavaRuntime.computeVMInstall(configuration);
      if (vm == null)
      {
         abort("The specified JRE installation does not exist",
               IJavaLaunchConfigurationConstants.ERR_VM_INSTALL_DOES_NOT_EXIST,
               null);
      }
      final File location = vm.getInstallLocation();
      if (location == null)
      {
         abort(MessageFormat.format("JRE home directory not specified for {0}",
               new Object[] { vm.getName() }),
               IJavaLaunchConfigurationConstants.ERR_VM_INSTALL_DOES_NOT_EXIST,
               null);
      }
      if (!location.exists())
      {
         abort(MessageFormat.format(
               "JRE home directory for {0} does not exist: {1}", new Object[] {
                     vm.getName(), location.getAbsolutePath() }),
               IJavaLaunchConfigurationConstants.ERR_VM_INSTALL_DOES_NOT_EXIST,
               null);
      }
      return vm;
   }


   /**
    * Verifies a main type name is specified by the given launch configuration, and returns the main
    * type name.
    * 
    * @param configuration launch configuration
    * @return the main type name specified by the given launch configuration
    * @exception CoreException if unable to retrieve the attribute or the attribute is unspecified
    */
   private String verifyMainTypeName(final ILaunchConfiguration configuration)
         throws CoreException
   {
      final String name = configuration.getAttribute(
            ITracerConstants.MAIN_CLASS, ITracerConstants.DEFAULT_MAIN_CLASS);

      if (name.equals(ITracerConstants.DEFAULT_MAIN_CLASS))
      {
         abort("Main type not specified",
               IJavaLaunchConfigurationConstants.ERR_UNSPECIFIED_MAIN_TYPE,
               null);
      }

      return name;
   }


   /**
    * Verifies the working directory specified by the given launch configuration exists, and returns
    * the working directory, or <code>null</code> if none is specified.
    * 
    * @param configuration launch configuration
    * @return the working directory path specified by the given launch configuration, or
    *         <code>null</code> if none
    * @exception CoreException if unable to retrieve the attribute
    */
   private String getWorkingDirectory(final ILaunchConfiguration configuration)
         throws CoreException
   {
      final String workingDirectory = configuration.getAttribute(
            ITracerConstants.WORKING_DIRECTORY,
            ITracerConstants.DEFAULT_WORKING_DIRECTORY);
      if (workingDirectory != ITracerConstants.DEFAULT_WORKING_DIRECTORY)
      {
         return workingDirectory;
      }

      return null;
   }


   /**
    * Returns the entries that should appear on the user portion of the classpath as specified by
    * the given launch configuration, as an array of resolved strings. The returned array is empty
    * if no classpath is specified.
    * 
    * @param configuration launch configuration
    * @return the classpath specified by the given launch configuration, possibly an empty array
    * @exception CoreException if unable to retrieve the attribute
    */
   @SuppressWarnings("unchecked")
   private String[] getClasspath(final ILaunchConfiguration configuration)
         throws CoreException
   {
      final List<String> classpathList = configuration.getAttribute(
            ITracerConstants.CLASS_PATH, ITracerConstants.DEFAULT_CLASS_PATH);

      final String[] array = new String[classpathList.size()];
      int i = 0;
      final Iterator<String> iter = classpathList.iterator();
      while (iter.hasNext())
      {
         array[i] = iter.next();
         i++;
      }

      return array;
   }


   /**
    * Returns the program arguments specified by the given launch configuration, as a string. The
    * returned string is empty if no program arguments are specified.
    * 
    * @param configuration launch configuration
    * @return the program arguments specified by the given launch configuration, possibly an empty
    *         string
    * @exception CoreException if unable to retrieve the attribute
    */
   private String getProgramArguments(final ILaunchConfiguration configuration)
         throws CoreException
   {
      return configuration.getAttribute(ITracerConstants.PROGRAM_ARGUMENTS,
            ITracerConstants.DEFAULT_PROGRAM_ARGUMENTS);
   }


   /**
    * Returns the VM arguments specified by the given launch configuration, as a string. The
    * returned string is empty if no VM arguments are specified.
    * 
    * @param configuration launch configuration
    * @return the VM arguments specified by the given launch configuration, possibly an empty string
    * @exception CoreException if unable to retrieve the attribute
    */
   private String getVMArguments(final ILaunchConfiguration configuration)
         throws CoreException
   {
      return configuration.getAttribute(ITracerConstants.VM_ARGUMENTS,
            ITracerConstants.DEFAULT_VM_ARGUMENTS);
   }

}
