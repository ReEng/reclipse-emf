package org.reclipse.tracer.launching;


import java.io.FileNotFoundException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.exceptions.TracerStartException;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionReader;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public abstract class AbstractTracerLaunchConfigurationDelegate implements
      ILaunchConfigurationDelegate
{

   protected Tracer prepareTracer(final ILaunchConfiguration configuration)
         throws TracerStartException,
            CoreException
   {
      final String fileName = configuration.getAttribute(
            ITracerConstants.TRACE_DEFINITION_FILE_NAME,
            ITracerConstants.DEFAULT_TRACE_DEFINITION_FILE_NAME);

      TraceDefinition traceDefinition;
      try
      {
         traceDefinition = TraceDefinitionReader.load(fileName);
      }
      catch (final FileNotFoundException e)
      {
         throw new TracerStartException("File not found!", e);
      }

      final Tracer tracer = Tracer.createTracer(traceDefinition, configuration);

      return tracer;
   }


   /**
    * Throws a core exception with an error status object built from the given message, lower level
    * exception, and error code.
    * 
    * @param message the status message
    * @param code error code
    * @throws CoreException the "abort" core exception
    */
   protected void abort(final String message, final int code,
         final Throwable exception) throws CoreException
   {
      throw new CoreException(new Status(IStatus.ERROR,
            ITracerConstants.PLUGIN_ID, code, message, exception));
   }

}
