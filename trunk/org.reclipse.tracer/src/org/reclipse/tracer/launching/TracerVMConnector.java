package org.reclipse.tracer.launching;


import java.io.IOException;
import java.util.ArrayList;
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
import org.eclipse.jdt.launching.IVMConnector;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.exceptions.TracerStartException;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4138 $ $Date: 2009-09-04 10:28:46 +0200 (Fr, 04 Sep 2009) $
 */
public class TracerVMConnector implements IVMConnector
{

   public static final String PORT = "port";

   public static final String TIME_OUT = "timeout";

   public static final String HOST_NAME = "hostname";


   private AttachingConnector connector;


   /**
    * @see org.eclipse.jdt.launching.IVMConnector#connect(java.util.Map,
    *      org.eclipse.core.runtime.IProgressMonitor, org.eclipse.debug.core.ILaunch)
    */
   @SuppressWarnings("unchecked")
   public void connect(final Map arguments, IProgressMonitor monitor,
         final ILaunch launch) throws CoreException
   {
      IProgressMonitor progressMonitor = monitor;
      
      if (progressMonitor == null)
      {
         progressMonitor = new NullProgressMonitor();
      }

      final IProgressMonitor subMonitor = new SubProgressMonitor(progressMonitor, 1);
      subMonitor.beginTask("Connecting to target VM...", 3);

      VirtualMachine virtualMachine;
      final AttachingConnector attachingConnector = getAttachingConnector();
      try
      {
         virtualMachine = attachingConnector.attach(arguments);
      }
      catch (final IOException exception)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               ITracerConstants.PLUGIN_ID,
               ITracerConstants.STATUS_CODE_VM_ATTACHING_FAILED,
               "Attaching to target virtual machine failed.", exception));
      }
      catch (final IllegalConnectorArgumentsException exception)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               ITracerConstants.PLUGIN_ID,
               ITracerConstants.STATUS_CODE_VM_ATTACHING_FAILED,
               "Attaching to target virtual machine failed.", exception));
      }

      subMonitor.worked(1);
      subMonitor.subTask("Initializing Tracer...");

      try
      {
         final Tracer tracer = Tracer.getCurrentTracer();
         tracer.initializeTracer(virtualMachine);

         subMonitor.worked(1);
         subMonitor.subTask("Starting Tracer...");
         tracer.startTracer();
      }
      catch (final TracerStartException e)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               ITracerConstants.PLUGIN_ID,
               ITracerConstants.STATUS_CODE_TRACER_START_FAILED,
               "Starting Tracer failed.", e));
      }

      subMonitor.done();
   }


   /**
    * @see org.eclipse.jdt.launching.IVMConnector#getArgumentOrder()
    */
   public List<String> getArgumentOrder()
   {
      final List<String> list = new ArrayList<String>(3);
      list.add(HOST_NAME);
      list.add(PORT);
      list.add(TIME_OUT);
      return list;
   }


   /**
    * @see org.eclipse.jdt.launching.IVMConnector#getDefaultArguments()
    */
   @SuppressWarnings("unchecked")
   public Map<String, Argument> getDefaultArguments() throws CoreException
   {
      return getAttachingConnector().defaultArguments();
   }


   /**
    * @see org.eclipse.jdt.launching.IVMConnector#getIdentifier()
    */
   public String getIdentifier()
   {
      return "org.reclipse.tracer.launching.TracerVMConnector";
   }


   /**
    * @see org.eclipse.jdt.launching.IVMConnector#getName()
    */
   public String getName()
   {
      return "Reclipse Tracer Remote Connector";
   }


   @SuppressWarnings("unchecked")
   private AttachingConnector getAttachingConnector() throws CoreException
   {
      if (this.connector == null)
      {
         try
         {
            final List<AttachingConnector> connectors = Bootstrap.virtualMachineManager()
                  .attachingConnectors();
            final Iterator<AttachingConnector> iter = connectors.iterator();
            while (iter.hasNext())
            {
               final AttachingConnector connector = iter
                     .next();
               if (connector.name().equals("com.sun.jdi.SocketAttach"))
               {
                  this.connector = connector;
               }
            }
         }
         catch (final Exception e)
         {
            throw new CoreException(new Status(IStatus.ERROR,
                  ITracerConstants.PLUGIN_ID,
                  ITracerConstants.STATUS_CODE_CONNECTOR_NOT_FOUND,
                  "Unable to get LaunchingConnector", null));
         }
      }

      return this.connector;
   }


   public static class StringArgument extends Argument implements
         Connector.StringArgument
   {

      /**
       * 
       */
      private static final long serialVersionUID = -6845225634721454561L;
      private String value;


      /**
       * @see org.reclipse.tracer.launching.TracerVMConnector.Argument#setValue(java.lang.String)
       */
      @Override
      public void setValue(final String value)
      {
         this.value = value;
      }


      /**
       * @see org.reclipse.tracer.launching.TracerVMConnector.Argument#value()
       */
      @Override
      public String value()
      {
         return this.value;
      }

   }


   public static class IntegerArgument extends Argument implements
         Connector.IntegerArgument
   {

      /**
       * 
       */
      private static final long serialVersionUID = 8630606805850137506L;
      private int value;


      /**
       * @see com.sun.jdi.connect.Connector$IntegerArgument#intValue()
       */
      public int intValue()
      {
         return this.value;
      }


      /**
       * @see com.sun.jdi.connect.Connector$IntegerArgument#isValid(int)
       */
      public boolean isValid(final int value)
      {
         return true;
      }


      /**
       * @see com.sun.jdi.connect.Connector$IntegerArgument#max()
       */
      public int max()
      {
         return Integer.MAX_VALUE;
      }


      /**
       * @see com.sun.jdi.connect.Connector$IntegerArgument#min()
       */
      public int min()
      {
         return Integer.MIN_VALUE;
      }


      /**
       * @see com.sun.jdi.connect.Connector$IntegerArgument#setValue(int)
       */
      public void setValue(final int value)
      {
         this.value = value;
      }


      /**
       * @see com.sun.jdi.connect.Connector$IntegerArgument#stringValueOf(int)
       */
      public String stringValueOf(final int value)
      {
         return null;
      }

   }

   static abstract class Argument implements Connector.Argument
   {

      /**
       * 
       */
      private static final long serialVersionUID = 2554001519866124342L;


      /**
       * @see com.sun.jdi.connect.Connector$Argument#description()
       */
      public String description()
      {
         return null;
      }


      /**
       * @see com.sun.jdi.connect.Connector$Argument#isValid(java.lang.String)
       */
      public boolean isValid(final String value)
      {
         return true;
      }


      /**
       * @see com.sun.jdi.connect.Connector$Argument#label()
       */
      public String label()
      {
         return null;
      }


      /**
       * @see com.sun.jdi.connect.Connector$Argument#mustSpecify()
       */
      public boolean mustSpecify()
      {
         return false;
      }


      /**
       * @see com.sun.jdi.connect.Connector$Argument#name()
       */
      public String name()
      {
         return null;
      }


      /**
       * @see com.sun.jdi.connect.Connector$Argument#setValue(java.lang.String)
       */
      public void setValue(final String value)
      {
      }


      /**
       * @see com.sun.jdi.connect.Connector$Argument#value()
       */
      public String value()
      {
         return null;
      }

   }

}
