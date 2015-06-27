package org.reclipse.tracer.launching;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.jdi.Bootstrap;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.exceptions.TracerStartException;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.VMStartException;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4138 $ $Date: 2009-09-04 10:28:46 +0200 (Fr, 04 Sep 2009) $
 */
public class TracerVMRunner implements IVMRunner
{

   private final IVMInstall vmInstall;

   private VMRunnerConfiguration configuration;


   public TracerVMRunner(IVMInstall vmInstall)
   {
      this.vmInstall = vmInstall;
   }


   /**
    * @see org.eclipse.jdt.launching.IVMRunner#run(org.eclipse.jdt.launching.VMRunnerConfiguration,
    *      org.eclipse.debug.core.ILaunch, org.eclipse.core.runtime.IProgressMonitor)
    */
   public void run(VMRunnerConfiguration configuration, ILaunch launch,
         IProgressMonitor monitor) throws CoreException
   {
      this.configuration = configuration;

      IProgressMonitor progressMonitor = monitor;
      if (progressMonitor == null)
      {
         progressMonitor = new NullProgressMonitor();
      }

      IProgressMonitor subMonitor = new SubProgressMonitor(progressMonitor, 1);
      subMonitor.beginTask("Launching and connecting to target VM...", 3);

      LaunchingConnector connector = getLaunchingConnector();
      Map<String, Connector.Argument> connectorArguments = createConnectorArguments(connector);

      VirtualMachine vm;

      try
      {
         vm = connector.launch(connectorArguments);
      }
      catch (IOException e)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               ITracerConstants.PLUGIN_ID,
               ITracerConstants.STATUS_CODE_VM_START_FAILED,
               "Launching target virtual machine failed.", e));
      }
      catch (IllegalConnectorArgumentsException e)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               ITracerConstants.PLUGIN_ID,
               ITracerConstants.STATUS_CODE_VM_START_FAILED,
               "Launching target virtual machine failed.", e));
      }
      catch (VMStartException e)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               ITracerConstants.PLUGIN_ID,
               ITracerConstants.STATUS_CODE_VM_START_FAILED,
               "Launching target virtual machine failed.", e));
      }

      subMonitor.worked(1);
      subMonitor.subTask("Initializing Tracer...");

      try
      {
         Tracer tracer = Tracer.getCurrentTracer();
         tracer.initializeTracer(vm);

         subMonitor.worked(1);
         subMonitor.subTask("Starting Tracer...");
         tracer.startTracer();
      }
      catch (TracerStartException e)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               ITracerConstants.PLUGIN_ID,
               ITracerConstants.STATUS_CODE_TRACER_START_FAILED,
               "Starting Tracer failed.", e));
      }

      subMonitor.done();
   }


   @SuppressWarnings("unchecked")
   private LaunchingConnector getLaunchingConnector() throws CoreException
   {
      try
      {
         List<LaunchingConnector> connectors = Bootstrap
               .virtualMachineManager().launchingConnectors();
         Iterator<LaunchingConnector> iter = connectors.iterator();
         while (iter.hasNext())
         {
            LaunchingConnector connector = iter.next();
            if (connector.name().equals("com.sun.jdi.CommandLineLaunch"))
            {
               return connector;
            }
         }
      }
      catch (Exception e)
      {
      }

      throw new CoreException(new Status(IStatus.ERROR,
            ITracerConstants.PLUGIN_ID,
            ITracerConstants.STATUS_CODE_CONNECTOR_NOT_FOUND,
            "Unable to get LaunchingConnector", null));
   }


   @SuppressWarnings("unchecked")
   private Map<String, Connector.Argument> createConnectorArguments(
         Connector connector)
   {
      Map<String, Connector.Argument> arguments = connector.defaultArguments();

      Connector.Argument javaHomeArg = arguments.get("home");
      String newJavaHome = this.vmInstall.getInstallLocation()
            .getAbsolutePath();
      if (!newJavaHome.trim().equals("") && javaHomeArg.isValid(newJavaHome))
      {
         javaHomeArg.setValue(newJavaHome);
      }

      Connector.Argument optionsArg = arguments.get("options");
      String vmArguments = getVMArguments();
      if (!vmArguments.trim().equals("") && optionsArg.isValid(vmArguments))
      {
         optionsArg.setValue(vmArguments);
      }

      Connector.Argument mainArg = arguments.get("main");
      String programArguments = getProgramArguments();
      if (!programArguments.trim().equals("")
            && mainArg.isValid(programArguments))
      {
         mainArg.setValue(programArguments);
      }

      Connector.Argument vmExecArg = arguments.get("vmexec");
      String userVMExec = getUserDefinedJavaExecutable();
      if (userVMExec != null && vmExecArg.isValid(userVMExec))
      {
         vmExecArg.setValue(userVMExec);
      }

      return arguments;
   }


   @SuppressWarnings("unchecked")
   private String getUserDefinedJavaExecutable()
   {
      // Look for the user-specified java executable command
      String javaExe = null;
      Map<String, String> map = this.configuration.getVMSpecificAttributesMap();
      if (map != null)
      {
         javaExe = map.get(IJavaLaunchConfigurationConstants.ATTR_JAVA_COMMAND);
      }

      return javaExe;
   }


   private String getVMArguments()
   {
      StringBuffer buffer = new StringBuffer();

      String[] classpath = this.configuration.getClassPath();
      if (classpath.length > 0)
      {
         buffer.append("-cp \"");

         for (int i = 0; i < classpath.length; i++)
         {
            buffer.append(classpath[i]);

            if (i + 1 < classpath.length)
            {
               buffer.append(File.pathSeparatorChar);
            }
         }

         buffer.append("\" ");
      }

      String[] vmArgs = this.configuration.getVMArguments();
      for (int i = 0; i < vmArgs.length; i++)
      {
         buffer.append(vmArgs[i]);
         buffer.append(" ");
      }

      return buffer.toString();
   }


   private String getProgramArguments()
   {
      StringBuffer buffer = new StringBuffer();

      buffer.append(this.configuration.getClassToLaunch());
      buffer.append(" ");

      String[] programArgs = this.configuration.getProgramArguments();
      for (int i = 0; i < programArgs.length; i++)
      {
         buffer.append(programArgs[i]);
         buffer.append(" ");
      }

      return buffer.toString();
   }

}
