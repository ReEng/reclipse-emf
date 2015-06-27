package org.reclipse.tracer.ui.launching;


import java.io.File;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.model.definition.xml.ITraceDefinitionConstants;
import org.reclipse.tracer.ui.TracerUIImages;
import org.reclipse.tracer.ui.TracerUIPlugin;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4640 $ $Date: 2011-02-21 12:48:12 +0100 (Mo, 21 Feb 2011) $
 */
public class MainTab extends AbstractLaunchConfigurationTab implements SelectionListener, ModifyListener
{

   boolean initializing;

   private Text mainClassText;

   private Button mainClassButton;

   private Text programArgumentsText;

   private Text vmArgumentsText;

   private Text workingDirectoryText;

   private Button workingDirectoryFileButton;

   private Text definitionFileText;

   private Button browseDefinitionFileButton;


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
    */
   public void createControl(final Composite parent)
   {
      final Composite mainComposite = new Composite(parent, SWT.NONE);
      setControl(mainComposite);
      mainComposite.setFont(parent.getFont());
      final GridLayout layout = new GridLayout();
      final GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      mainComposite.setLayout(layout);
      mainComposite.setLayoutData(gridData);

      createMainClassComponent(mainComposite);
      this.programArgumentsText = createSingleLineTextComponent(mainComposite, "Program arguments:");
      this.vmArgumentsText = createSingleLineTextComponent(mainComposite, "VM arguments:");
      createWorkingDirectoryComponent(mainComposite);
      createTraceDefinitionFileComponent(mainComposite);
      createVerticalSpacer(mainComposite, 1);

      Dialog.applyDialogFont(parent);
   }


   private Text createSingleLineTextComponent(final Composite parent, final String title)
   {
      final Group group = new Group(parent, SWT.NONE);
      group.setText(title);
      final GridLayout layout = new GridLayout();
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      group.setLayout(layout);
      group.setLayoutData(gridData);

      final Text text = new Text(group, SWT.BORDER);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
      text.setLayoutData(gridData);
      text.addModifyListener(this);

      return text;
   }


   private void createMainClassComponent(final Composite parent)
   {
      final Group group = new Group(parent, SWT.NONE);
      group.setText("Main class:");
      final GridLayout layout = new GridLayout();
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      group.setLayout(layout);
      group.setLayoutData(gridData);

      this.mainClassText = new Text(group, SWT.BORDER);
      this.mainClassText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
      this.mainClassText.setLayoutData(gridData);

      this.mainClassButton = createPushButton(group, "Brows&e...", null);
      this.mainClassButton.addSelectionListener(this);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.RIGHT;
      this.mainClassButton.setLayoutData(gridData);
   }


   private void createWorkingDirectoryComponent(final Composite parent)
   {
      final Group group = new Group(parent, SWT.NONE);
      group.setText("Working directory:");
      final GridLayout layout = new GridLayout();
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      group.setLayout(layout);
      group.setLayoutData(gridData);

      this.workingDirectoryText = new Text(group, SWT.BORDER);
      this.workingDirectoryText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
      this.workingDirectoryText.setLayoutData(gridData);

      this.workingDirectoryFileButton = createPushButton(group, "Brows&e File System...", null);
      this.workingDirectoryFileButton.addSelectionListener(this);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.RIGHT;
      this.workingDirectoryFileButton.setLayoutData(gridData);
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

      this.definitionFileText = new Text(group, SWT.SINGLE | SWT.BORDER);
      this.definitionFileText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.definitionFileText.setLayoutData(gridData);

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
      configuration.setAttribute(ITracerConstants.MAIN_CLASS, ITracerConstants.DEFAULT_MAIN_CLASS);
      configuration.setAttribute(ITracerConstants.PROGRAM_ARGUMENTS, ITracerConstants.DEFAULT_PROGRAM_ARGUMENTS);
      configuration.setAttribute(ITracerConstants.VM_ARGUMENTS, ITracerConstants.DEFAULT_VM_ARGUMENTS);
      configuration.setAttribute(ITracerConstants.WORKING_DIRECTORY, ITracerConstants.DEFAULT_WORKING_DIRECTORY);

      update();
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
    */
   public void initializeFrom(final ILaunchConfiguration configuration)
   {
      this.initializing = true;
      initializeMainClass(configuration);
      initializeProgramArguments(configuration);
      initializeVMArguments(configuration);
      initializeWorkingDirectory(configuration);
      initializeDefinitionFile(configuration);
      this.initializing = false;

      update();
   }


   /**
    * Initializes the main class widget to match the state of the given launch configuration.
    */
   private void initializeMainClass(final ILaunchConfiguration configuration)
   {
      String mainClass = ITracerConstants.DEFAULT_MAIN_CLASS;
      try
      {
         mainClass = configuration.getAttribute(ITracerConstants.MAIN_CLASS, ITracerConstants.DEFAULT_MAIN_CLASS);
      }
      catch (final CoreException exception)
      {
         TracerUIPlugin.logError("Error reading configuration", exception);
      }
      this.mainClassText.setText(mainClass);
   }


   /**
    * Initializes the program arguments widget to match the state of the given launch configuration.
    */
   private void initializeProgramArguments(final ILaunchConfiguration configuration)
   {
      String mainClass = ITracerConstants.DEFAULT_PROGRAM_ARGUMENTS;
      try
      {
         mainClass = configuration.getAttribute(ITracerConstants.PROGRAM_ARGUMENTS,
               ITracerConstants.DEFAULT_PROGRAM_ARGUMENTS);
      }
      catch (final CoreException exception)
      {
         TracerUIPlugin.logError("Error reading configuration", exception);
      }
      this.programArgumentsText.setText(mainClass);
   }


   /**
    * Initializes the VM arguments widget to match the state of the given launch configuration.
    */
   private void initializeVMArguments(final ILaunchConfiguration configuration)
   {
      String mainClass = ITracerConstants.DEFAULT_VM_ARGUMENTS;
      try
      {
         mainClass = configuration.getAttribute(ITracerConstants.VM_ARGUMENTS, ITracerConstants.DEFAULT_VM_ARGUMENTS);
      }
      catch (final CoreException exception)
      {
         TracerUIPlugin.logError("Error reading configuration", exception);
      }
      this.vmArgumentsText.setText(mainClass);
   }


   /**
    * Initializes the working directory widget to match the state of the given launch configuration.
    */
   private void initializeWorkingDirectory(final ILaunchConfiguration configuration)
   {
      String workingDirectory = ITracerConstants.DEFAULT_WORKING_DIRECTORY;
      try
      {
         workingDirectory = configuration.getAttribute(ITracerConstants.WORKING_DIRECTORY,
               ITracerConstants.DEFAULT_WORKING_DIRECTORY);
      }
      catch (final CoreException exception)
      {
         TracerUIPlugin.logError("Error reading configuration", exception);
      }
      this.workingDirectoryText.setText(workingDirectory);
   }


   /**
    * Initializes the trace definition file widget to match the state of the given launch
    * configuration.
    */
   private void initializeDefinitionFile(final ILaunchConfiguration configuration)
   {
      String resultFile = ITracerConstants.DEFAULT_TRACE_DEFINITION_FILE_NAME;
      try
      {
         resultFile = configuration.getAttribute(ITracerConstants.TRACE_DEFINITION_FILE_NAME,
               ITracerConstants.DEFAULT_TRACE_DEFINITION_FILE_NAME);
      }
      catch (final CoreException e)
      {
      }
      this.definitionFileText.setText(resultFile);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void performApply(final ILaunchConfigurationWorkingCopy configuration)
   {
      final String mainClass = this.mainClassText.getText().trim();
      configuration.setAttribute(ITracerConstants.MAIN_CLASS, mainClass);

      final String programArguments = this.programArgumentsText.getText().trim();
      configuration.setAttribute(ITracerConstants.PROGRAM_ARGUMENTS, programArguments);

      final String vmArguments = this.vmArgumentsText.getText().trim();
      configuration.setAttribute(ITracerConstants.VM_ARGUMENTS, vmArguments);

      final String workingDirectory = this.workingDirectoryText.getText().trim();
      configuration.setAttribute(ITracerConstants.WORKING_DIRECTORY, workingDirectory);

      final String definitionFile = this.definitionFileText.getText().trim();
      configuration.setAttribute(ITracerConstants.TRACE_DEFINITION_FILE_NAME, definitionFile);
   }


   /**
    * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
    */
   @Override
   public boolean isValid(final ILaunchConfiguration configuration)
   {
      return validMainClass() && validWorkingDirectory() && validTraceDefinitionFile();
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

      if (source == this.workingDirectoryFileButton)
      {
         handleWorkingDirectoryFileButtonSelected();
      }
      else if (source == this.browseDefinitionFileButton)
      {
         handleBrowseDefinitionFileButton();
      }
      else if (source == this.mainClassButton)
      {
         handleBrowseMainFileButton();
      }

      update();
   }


   private void handleBrowseMainFileButton()
   {
      SelectJavaProjectWizard wizard = new SelectJavaProjectWizard();
      WizardDialog wizardDialog = new WizardDialog(getShell(), wizard);
      wizardDialog.open();

      IJavaProject javaProject = wizard.getIJavaProject();
      IJavaElement[] elements = { javaProject };
      IJavaSearchScope scope = SearchEngine.createJavaSearchScope(elements);

      SelectionDialog dialog = JavaUI.createMainTypeDialog(getShell(), this.getLaunchConfigurationDialog(), scope, 0,
            false);
      dialog.setMessage("Select the class that contains the main method to start the execution of the program:");
      int result = dialog.open();
      if (result == Window.OK)
      {
         IType mainClass = (IType) dialog.getResult()[0];
         this.mainClassText.setText(mainClass.getElementName());
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


   private void handleWorkingDirectoryFileButtonSelected()
   {
      final DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.SAVE);
      dialog.setMessage("Choose a working directory:");
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot workspaceDirectory = workspace.getRoot();
      dialog.setFilterPath(workspaceDirectory.getLocation().toString());
      final String directory = dialog.open();
      if (directory != null)
      {
         this.workingDirectoryText.setText(directory);
      }
   }


   private void handleBrowseDefinitionFileButton()
   {
      final FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
      dialog.setText("Choose a trace definition file:");
      dialog.setFileName(this.definitionFileText.getText());
      dialog.setFilterExtensions(ITraceDefinitionConstants.FILE_EXTENSIONS);
      dialog.setFilterNames(ITraceDefinitionConstants.FILE_DESCRIPTIONS);
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot workspaceDirectory = workspace.getRoot();
      dialog.setFilterPath(workspaceDirectory.getLocation().toString());
      final String traceFile = dialog.open();
      if (traceFile != null)
      {
         this.definitionFileText.setText(traceFile);
      }
   }


   private boolean validMainClass()
   {
      if (this.mainClassText != null)
      {
         return !this.mainClassText.getText().trim().equals("");
      }
      return false;
   }


   private boolean validWorkingDirectory()
   {
      if (this.workingDirectoryFileButton != null)
      {
         final File file = new File(this.workingDirectoryText.getText());

         return file.exists() && file.isDirectory();
      }
      return false;
   }


   private boolean validTraceDefinitionFile()
   {
      if (this.definitionFileText != null)
      {
         final File file = new File(this.definitionFileText.getText());

         return file.exists();
      }
      return false;
   }


   private void update()
   {
      if (!validMainClass())
      {
         setErrorMessage("Please provide a main class.");
         updateLaunchConfigurationDialog();
         return;
      }
      if (!validWorkingDirectory())
      {
         setErrorMessage("Please provide a valid working directory.");
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
