package org.reclipse.tracer.launching;


import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.exceptions.TracerStartException;


/**
 * @author Lothar
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4138 $ $Date: 2009-09-04 10:28:46 +0200 (Fr, 04 Sep 2009) $
 */
public class TracerRemoteLaunchConfigurationDelegate extends
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

      final TracerVMConnector tracerVMConnector = new TracerVMConnector();

      // configure arguments map for connector
      final Map<String, TracerVMConnector.Argument> arguments = tracerVMConnector.getDefaultArguments();
      arguments.put(TracerVMConnector.HOST_NAME, getHostName(configuration));
      arguments.put(TracerVMConnector.PORT, getPort(configuration));
      arguments.put(TracerVMConnector.TIME_OUT, getTimeOut(configuration));

      progressMonitor.worked(1);
      progressMonitor.subTask("Connecting to target VM...");

      // connecting
      tracerVMConnector.connect(arguments, progressMonitor, launch);

      progressMonitor.done();
   }


   private TracerVMConnector.StringArgument getHostName(
         final ILaunchConfiguration configuration) throws CoreException
   {
      final String hostName = configuration.getAttribute(
            ITracerConstants.HOST_NAME, ITracerConstants.DEFAULT_HOST_NAME);
      final TracerVMConnector.StringArgument argument = new TracerVMConnector.StringArgument();
      argument.setValue(hostName);

      return argument;
   }


   private TracerVMConnector.IntegerArgument getPort(
         final ILaunchConfiguration configuration)
   {
      int port = ITracerConstants.DEFAULT_PORT;
      try
      {
         port = configuration.getAttribute(ITracerConstants.PORT,
               ITracerConstants.DEFAULT_PORT);
      }
      catch (final CoreException exception)
      {
         // ignore exception
      }

      final TracerVMConnector.IntegerArgument argument = new TracerVMConnector.IntegerArgument();
      argument.setValue(port);

      return argument;
   }


   private TracerVMConnector.IntegerArgument getTimeOut(
         final ILaunchConfiguration configuration)
   {
      int timeOut = ITracerConstants.DEFAULT_TIME_OUT;
      try
      {
         timeOut = configuration.getAttribute(ITracerConstants.TIME_OUT,
               ITracerConstants.DEFAULT_TIME_OUT);
      }
      catch (final CoreException exception)
      {
         // ignore exception
      }

      final TracerVMConnector.IntegerArgument argument = new TracerVMConnector.IntegerArgument();
      argument.setValue(timeOut);

      return argument;
   }

}
