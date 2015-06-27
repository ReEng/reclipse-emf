package org.reclipse.tracer.ui.launching;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.ui.TracerUIImages;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3786 $ $Date: 2007-09-09 23:44:20 +0200 (So, 09 Sep 2007) $
 */
public class OptionsTab extends AbstractLaunchConfigurationTab
{

   private Button methodExitEventsCheckBox;

   private Button ignorePrivateMethodsCheckBox;

   private Button ignoreConstructorsCheckBox;

   private Button ignoreObjectCheckBox;


   private WidgetListener listener = new WidgetListener();


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
    */
   public void createControl(final Composite parent)
   {
      final Composite mainComposite = new Composite(parent, SWT.NONE);
      mainComposite.setFont(parent.getFont());
      setControl(mainComposite);

      final GridLayout layout = new GridLayout();
      final GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      mainComposite.setLayout(layout);
      mainComposite.setLayoutData(gridData);

      createOptionsComponent(mainComposite);
      createCriticalTracingComponent(mainComposite);
      createVerticalSpacer(mainComposite, 1);

      Dialog.applyDialogFont(parent);
   }


   private void createOptionsComponent(final Composite parent)
   {
      final Group group = new Group(parent, SWT.NONE);
      group.setText("Optional tracing information:");

      final GridLayout gridLayout = new GridLayout();
      group.setLayout(gridLayout);

      final GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      group.setLayoutData(gridData);

      this.methodExitEventsCheckBox = new Button(group, SWT.CHECK);
      this.methodExitEventsCheckBox.setText("Method exit events");
      this.methodExitEventsCheckBox
            .setToolTipText("Record method exit events.");
      this.methodExitEventsCheckBox.addSelectionListener(this.listener);
   }


   private void createCriticalTracingComponent(final Composite parent)
   {
      final Group group = new Group(parent, SWT.NONE);
      group.setText("Critical tracing:");

      final GridLayout gridLayout = new GridLayout();
      group.setLayout(gridLayout);

      final GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      group.setLayoutData(gridData);

      this.ignorePrivateMethodsCheckBox = new Button(group, SWT.CHECK);
      this.ignorePrivateMethodsCheckBox.setText("Ignore private methods");
      this.ignorePrivateMethodsCheckBox
            .setToolTipText("Ignore private methods during critical tracing.");
      this.ignorePrivateMethodsCheckBox.addSelectionListener(this.listener);

      this.ignoreConstructorsCheckBox = new Button(group, SWT.CHECK);
      this.ignoreConstructorsCheckBox.setText("Ignore constructors");
      this.ignoreConstructorsCheckBox
            .setToolTipText("Ignore constructors during critical tracing.");
      this.ignoreConstructorsCheckBox.addSelectionListener(this.listener);

      this.ignoreObjectCheckBox = new Button(group, SWT.CHECK);
      this.ignoreObjectCheckBox
            .setText("Ignore all methods of java.lang.Object");
      this.ignoreObjectCheckBox.addSelectionListener(this.listener);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void setDefaults(final ILaunchConfigurationWorkingCopy configuration)
   {
      configuration.setAttribute(ITracerConstants.TRACE_DEFINITION_FILE_NAME,
            ITracerConstants.DEFAULT_TRACE_DEFINITION_FILE_NAME);

      configuration.setAttribute(ITracerConstants.METHOD_EXIT_EVENTS,
            ITracerConstants.DEFAULT_METHOD_EXIT_EVENTS);

      configuration.setAttribute(ITracerConstants.IGNORE_PRIVATE_METHODS,
            ITracerConstants.DEFAULT_IGNORE_PRIVATE_METHODS);
      configuration.setAttribute(ITracerConstants.IGNORE_CONSTRUCTORS,
            ITracerConstants.DEFAULT_IGNORE_CONSTRUCTORS);
      configuration.setAttribute(ITracerConstants.IGNORE_JAVA_LANG_OBJECT,
            ITracerConstants.DEFAULT_IGNORE_JAVA_LANG_OBJECT);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
    */
   public void initializeFrom(final ILaunchConfiguration configuration)
   {
      updateOptions(configuration);
      updateCriticalTracing(configuration);
   }


   private void updateOptions(final ILaunchConfiguration configuration)
   {
      boolean methodExitEvents = ITracerConstants.DEFAULT_METHOD_EXIT_EVENTS;
      try
      {
         methodExitEvents = configuration.getAttribute(
               ITracerConstants.METHOD_EXIT_EVENTS,
               ITracerConstants.DEFAULT_METHOD_EXIT_EVENTS);
      }
      catch (final CoreException e)
      {
      }
      this.methodExitEventsCheckBox.setSelection(methodExitEvents);
   }


   private void updateCriticalTracing(final ILaunchConfiguration configuration)
   {
      boolean ignorePrivMethods = ITracerConstants.DEFAULT_IGNORE_PRIVATE_METHODS;
      try
      {
         ignorePrivMethods = configuration.getAttribute(
               ITracerConstants.IGNORE_PRIVATE_METHODS,
               ITracerConstants.DEFAULT_IGNORE_PRIVATE_METHODS);
      }
      catch (final CoreException e)
      {
      }
      this.ignorePrivateMethodsCheckBox.setSelection(ignorePrivMethods);

      boolean ignoreConstructors = ITracerConstants.DEFAULT_IGNORE_CONSTRUCTORS;
      try
      {
         ignoreConstructors = configuration.getAttribute(
               ITracerConstants.IGNORE_CONSTRUCTORS,
               ITracerConstants.DEFAULT_IGNORE_CONSTRUCTORS);
      }
      catch (final CoreException e)
      {
      }
      this.ignoreConstructorsCheckBox.setSelection(ignoreConstructors);

      boolean ignoreObject = ITracerConstants.DEFAULT_IGNORE_JAVA_LANG_OBJECT;
      try
      {
         ignoreObject = configuration.getAttribute(
               ITracerConstants.IGNORE_JAVA_LANG_OBJECT,
               ITracerConstants.DEFAULT_IGNORE_JAVA_LANG_OBJECT);
      }
      catch (final CoreException e)
      {
      }
      this.ignoreObjectCheckBox.setSelection(ignoreObject);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void performApply(final ILaunchConfigurationWorkingCopy configuration)
   {
      configuration.setAttribute(ITracerConstants.METHOD_EXIT_EVENTS,
            this.methodExitEventsCheckBox.getSelection());

      configuration.setAttribute(ITracerConstants.IGNORE_PRIVATE_METHODS,
            this.ignorePrivateMethodsCheckBox.getSelection());
      configuration.setAttribute(ITracerConstants.IGNORE_CONSTRUCTORS,
            this.ignoreConstructorsCheckBox.getSelection());
      configuration.setAttribute(ITracerConstants.IGNORE_JAVA_LANG_OBJECT,
            this.ignoreObjectCheckBox.getSelection());
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
    */
   @Override
   public boolean isValid(final ILaunchConfiguration launchConfig)
   {
      return true;
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
    */
   public String getName()
   {
      return "Options";
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
    */
   @Override
   public Image getImage()
   {
      return TracerUIImages.getImage(TracerUIImages.IMG_TRACER_OPTIONS);
   }


   /**
    * Updates the buttons and message in this page's launch configuration dialog.
    */
   void update()
   {
      updateLaunchConfigurationDialog();
   }


   class WidgetListener extends SelectionAdapter
   {

      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(final SelectionEvent e)
      {
         update();
      }
   }

}
