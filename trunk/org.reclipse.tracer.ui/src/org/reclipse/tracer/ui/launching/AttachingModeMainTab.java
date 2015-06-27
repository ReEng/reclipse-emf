package org.reclipse.tracer.ui.launching;


import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.model.definition.xml.ITraceDefinitionConstants;
import org.reclipse.tracer.ui.TracerUIImages;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class AttachingModeMainTab extends AbstractLaunchConfigurationTab
      implements SelectionListener, ModifyListener
{

   boolean initializing;

   private Text hostNameText;

   private Text portText;

   private Text timeOutText;

   private Button allowTerminationButton;

   private Text traceDefinitionFileText;

   private Button browseDefinitionFileButton;


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
    */
   public void createControl(final Composite parent)
   {
      final Composite composite = new Composite(parent, SWT.NONE);
      final GridLayout layout = new GridLayout();
      composite.setLayout(layout);

      final GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      createConnectionPropertiesComponent(composite);
      createTraceDefinitionFileComponent(composite);
      createVerticalSpacer(composite, 1);

      setControl(composite);
   }


   private void createConnectionPropertiesComponent(final Composite parent)
   {
      final Group group = new Group(parent, SWT.NONE);
      group.setText("Connection properties:");

      final GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 2;
      group.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      group.setLayoutData(gridData);

      Label label = new Label(group, SWT.NONE);
      label.setText("Host:");
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.LEFT;
      label.setLayoutData(gridData);

      this.hostNameText = new Text(group, SWT.SINGLE | SWT.BORDER);
      this.hostNameText.addModifyListener(this);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.hostNameText.setLayoutData(gridData);

      label = new Label(group, SWT.NONE);
      label.setText("Port:");
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.LEFT;
      label.setLayoutData(gridData);

      this.portText = new Text(group, SWT.SINGLE | SWT.BORDER);
      this.portText.addModifyListener(this);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.portText.setLayoutData(gridData);

      label = new Label(group, SWT.NONE);
      label.setText("Timeout:");
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.LEFT;
      label.setLayoutData(gridData);

      this.timeOutText = new Text(group, SWT.SINGLE | SWT.BORDER);
      this.timeOutText.addModifyListener(this);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.timeOutText.setLayoutData(gridData);

      this.allowTerminationButton = new Button(group, SWT.CHECK);
      this.allowTerminationButton.setText("Allow termination of remote VM");
      this.allowTerminationButton.addSelectionListener(this);
      gridData = new GridData();
      gridData.horizontalSpan = 2;
      gridData.horizontalAlignment = SWT.LEFT;
      this.allowTerminationButton.setLayoutData(gridData);
   }


   private void createTraceDefinitionFileComponent(final Composite parent)
   {
      final Group group = new Group(parent, SWT.NONE);
      group.setText("Trace definition file:");

      final GridLayout gridLayout = new GridLayout();
      // gridLayout.numColumns = 2;
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      group.setLayoutData(gridData);
      group.setLayout(gridLayout);

      this.traceDefinitionFileText = new Text(group, SWT.SINGLE | SWT.BORDER);
      this.traceDefinitionFileText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.traceDefinitionFileText.setLayoutData(gridData);

      this.browseDefinitionFileButton = new Button(group, SWT.PUSH);
      this.browseDefinitionFileButton.setText("Browse File System...");
      this.browseDefinitionFileButton.addSelectionListener(this);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.RIGHT;
      this.browseDefinitionFileButton.setLayoutData(gridData);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void setDefaults(final ILaunchConfigurationWorkingCopy configuration)
   {
      configuration.setAttribute(ITracerConstants.WORKING_DIRECTORY,
            ITracerConstants.DEFAULT_WORKING_DIRECTORY);

      update();
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
    */
   public void initializeFrom(final ILaunchConfiguration configuration)
   {
      this.initializing = true;
      initializeHostName(configuration);
      initializePort(configuration);
      initializeTimeOut(configuration);
      initializeAllowTermination(configuration);
      initializeDefinitionFile(configuration);
      this.initializing = false;

      update();
   }


   private void initializeHostName(final ILaunchConfiguration configuration)
   {
      String hostName = ITracerConstants.DEFAULT_HOST_NAME;
      try
      {
         hostName = configuration.getAttribute(ITracerConstants.HOST_NAME,
               ITracerConstants.DEFAULT_HOST_NAME);
      }
      catch (final CoreException exception)
      {
         // ignore exception
      }
      this.hostNameText.setText(hostName);
   }


   private void initializePort(final ILaunchConfiguration configuration)
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
      this.portText.setText(Integer.toString(port));
   }


   private void initializeTimeOut(final ILaunchConfiguration configuration)
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
      this.timeOutText.setText(Integer.toString(timeOut));
   }


   private void initializeAllowTermination(
         final ILaunchConfiguration configuration)
   {
      boolean allowTermination = true;
      try
      {
         allowTermination = configuration.getAttribute(
               IJavaLaunchConfigurationConstants.ATTR_ALLOW_TERMINATE, true);
      }
      catch (final CoreException exception)
      {
         // ignore exception
      }
      this.allowTerminationButton.setSelection(allowTermination);
   }


   /**
    * Initializes the trace definition file widget to match the state of the given launch
    * configuration.
    */
   private void initializeDefinitionFile(
         final ILaunchConfiguration configuration)
   {
      String resultFile = ITracerConstants.DEFAULT_TRACE_DEFINITION_FILE_NAME;
      try
      {
         resultFile = configuration.getAttribute(
               ITracerConstants.TRACE_DEFINITION_FILE_NAME,
               ITracerConstants.DEFAULT_TRACE_DEFINITION_FILE_NAME);
      }
      catch (final CoreException exception)
      {
         // ignore exception
      }
      this.traceDefinitionFileText.setText(resultFile);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void performApply(final ILaunchConfigurationWorkingCopy configuration)
   {
      final String hostName = this.hostNameText.getText().trim();
      configuration.setAttribute(ITracerConstants.HOST_NAME, hostName);

      try
      {
         final int port = Integer.valueOf(this.portText.getText());
         configuration.setAttribute(ITracerConstants.PORT, port);
      }
      catch (final NumberFormatException exception)
      {
         // ignore exception
      }

      try
      {
         final int timeOut = Integer.valueOf(this.timeOutText.getText());
         configuration.setAttribute(ITracerConstants.TIME_OUT, timeOut);
      }
      catch (final NumberFormatException exception)
      {
         // ignore exception
      }

      configuration.setAttribute(
            IJavaLaunchConfigurationConstants.ATTR_ALLOW_TERMINATE,
            this.allowTerminationButton.getSelection());

      final String definitionFile = this.traceDefinitionFileText.getText()
            .trim();
      configuration.setAttribute(ITracerConstants.TRACE_DEFINITION_FILE_NAME,
            definitionFile);
   }


   /**
    * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
    */
   @Override
   public boolean isValid(final ILaunchConfiguration configuration)
   {
      return validHostName() && validPort() && validTimeOut()
            && validTraceDefinitionFile();
   }


   private boolean validHostName()
   {
      if (this.hostNameText != null)
      {
         if (!this.hostNameText.getText().trim().equals(""))
         {
            return true;
         }
      }
      return false;
   }


   private boolean validPort()
   {
      try
      {
         final int port = Integer.valueOf(this.portText.getText());
         if (port < 1 || port > 65535)
         {
            return false;
         }
      }
      catch (final NumberFormatException exception)
      {
         return false;
      }
      return true;
   }


   private boolean validTimeOut()
   {
      try
      {
         final int timeout = Integer.valueOf(this.timeOutText.getText());
         if (timeout < 1)
         {
            return false;
         }
      }
      catch (final NumberFormatException exception)
      {
         return false;
      }
      return true;
   }


   private boolean validTraceDefinitionFile()
   {
      if (this.traceDefinitionFileText != null)
      {
         final File file = new File(this.traceDefinitionFileText.getText());

         return file.exists();
      }
      return false;
   }


   /**
    * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#canSave()
    */
   @Override
   public boolean canSave()
   {
      return validPort() && validTimeOut();
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
    */
   public String getName()
   {
      return "Main";
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
    */
   @Override
   public Image getImage()
   {
      return TracerUIImages.getImage(TracerUIImages.IMG_CLASS);
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetSelected(final SelectionEvent e)
   {
      final Object source = e.getSource();

      if (source == this.browseDefinitionFileButton)
      {
         handleBrowseDefinitionFileButton();
      }

      update();
   }


   private void handleBrowseDefinitionFileButton()
   {
      final FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
      dialog.setText("Choose a trace definition file:");
      dialog.setFileName(this.traceDefinitionFileText.getText());
      dialog.setFilterExtensions(ITraceDefinitionConstants.FILE_EXTENSIONS);
      dialog.setFilterNames(ITraceDefinitionConstants.FILE_DESCRIPTIONS);
      final String traceFile = dialog.open();
      if (traceFile != null)
      {
         this.traceDefinitionFileText.setText(traceFile);
      }
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetDefaultSelected(final SelectionEvent e)
   {
      // nothing to do
   }


   /**
    * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
    */
   public void modifyText(final ModifyEvent e)
   {
      if (!this.initializing)
      {
         update();
      }
   }


   private void update()
   {
      if (!validHostName())
      {
         setErrorMessage("Please provide a valid host.");
         updateLaunchConfigurationDialog();
         return;
      }
      if (!validPort())
      {
         setErrorMessage("Please provide a valid port between 1 and 65535.");
         updateLaunchConfigurationDialog();
         return;
      }
      if (!validTimeOut())
      {
         setErrorMessage("Please provide a valid time out value > 1.");
         updateLaunchConfigurationDialog();
         return;
      }
      if (!validTraceDefinitionFile())
      {
         setErrorMessage("Please provide a valid trace definition file.");
         updateLaunchConfigurationDialog();
         return;
      }

      setErrorMessage(null);
      updateLaunchConfigurationDialog();
   }
}
