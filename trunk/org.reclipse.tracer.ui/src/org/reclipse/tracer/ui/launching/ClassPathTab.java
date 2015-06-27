package org.reclipse.tracer.ui.launching;


import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.ui.TracerUIImages;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4645 $ $Date: 2011-02-26 10:51:31 +0100 (Sa, 26 Feb 2011) $
 */
public class ClassPathTab extends AbstractLaunchConfigurationTab implements SelectionListener, ModifyListener
{

   private List classpathList;

   private Button upButton;

   private Button downButton;

   private Button removeButton;

   private Button addButton;

   private Button addJarButton;


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

      final Label classpathLabel = new Label(mainComposite, SWT.NONE);
      classpathLabel.setText("Classpath:");

      createClassPathComponent(mainComposite);

      Dialog.applyDialogFont(parent);
   }


   private void createClassPathComponent(final Composite parent)
   {
      final Composite composite = new Composite(parent, SWT.NONE);
      final GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 2;
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      this.classpathList = new List(composite, SWT.SINGLE | SWT.BORDER);
      this.classpathList.addSelectionListener(this);

      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      this.classpathList.setLayoutData(gridData);

      final Composite buttonComposite = new Composite(composite, SWT.NONE);
      buttonComposite.setLayout(new FillLayout(SWT.VERTICAL));
      gridData = new GridData();
      gridData.verticalAlignment = SWT.TOP;
      buttonComposite.setLayoutData(gridData);

      this.upButton = new Button(buttonComposite, SWT.PUSH);
      this.upButton.setText("Up");
      this.upButton.addSelectionListener(this);
      this.upButton.setEnabled(false);

      this.downButton = new Button(buttonComposite, SWT.PUSH);
      this.downButton.setText("Down");
      this.downButton.addSelectionListener(this);
      this.downButton.setEnabled(false);

      this.removeButton = new Button(buttonComposite, SWT.PUSH);
      this.removeButton.setText("Remove");
      this.removeButton.addSelectionListener(this);
      this.removeButton.setEnabled(false);

      this.addButton = new Button(buttonComposite, SWT.PUSH);
      this.addButton.setText("Add Directory...");
      this.addButton.addSelectionListener(this);

      this.addJarButton = new Button(buttonComposite, SWT.PUSH);
      this.addJarButton.setText("Add JARs...");
      this.addJarButton.addSelectionListener(this);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void setDefaults(final ILaunchConfigurationWorkingCopy configuration)
   {
      configuration.setAttribute(ITracerConstants.CLASS_PATH, ITracerConstants.DEFAULT_CLASS_PATH);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
    */
   public void initializeFrom(final ILaunchConfiguration configuration)
   {
      this.classpathList.removeAll();

      java.util.List classpath = ITracerConstants.DEFAULT_CLASS_PATH;
      try
      {
         classpath = configuration.getAttribute(ITracerConstants.CLASS_PATH, ITracerConstants.DEFAULT_CLASS_PATH);
      }
      catch (final CoreException e)
      {
      }

      final Iterator iter = classpath.iterator();
      while (iter.hasNext())
      {
         final String entry = (String) iter.next();
         this.classpathList.add(entry);
      }
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void performApply(final ILaunchConfigurationWorkingCopy configuration)
   {
      final ArrayList arrayList = new ArrayList();
      final String[] listItems = this.classpathList.getItems();
      for (int i = 0; i < listItems.length; i++)
      {
         arrayList.add(listItems[i]);
      }
      configuration.setAttribute(ITracerConstants.CLASS_PATH, arrayList);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
    */
   public String getName()
   {
      return "Classpath";
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
    */
   @Override
   public Image getImage()
   {
      return TracerUIImages.getImage(TracerUIImages.IMG_TRACER_CLASSPATH);
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetSelected(final SelectionEvent e)
   {
      final Object source = e.getSource();

      if (source == this.upButton)
      {
         handleUpButtonSelected();
      }
      else if (source == this.downButton)
      {
         handleDownButtonSelected();
      }
      else if (source == this.addButton)
      {
         handleAddButtonSelected();
      }
      else if (source == this.addJarButton)
      {
         handleAddJarButtonSelected();
      }
      else if (source == this.removeButton)
      {
         handleRemoveButtonSelected();
      }

      update();
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
      update();
   }


   private void handleUpButtonSelected()
   {
      final int selectedIndex = this.classpathList.getSelectionIndex();
      if (selectedIndex >= 1)
      {
         final String selectedString = this.classpathList.getItem(selectedIndex);
         this.classpathList.remove(selectedIndex);
         this.classpathList.add(selectedString, selectedIndex - 1);
         this.classpathList.setSelection(selectedIndex - 1);
      }
   }


   private void handleDownButtonSelected()
   {
      final int selectedIndex = this.classpathList.getSelectionIndex();
      if (selectedIndex < this.classpathList.getItemCount() - 1)
      {
         final String selectedString = this.classpathList.getItem(selectedIndex);
         this.classpathList.remove(selectedIndex);
         this.classpathList.add(selectedString, selectedIndex + 1);
         this.classpathList.setSelection(selectedIndex + 1);
      }
   }


   private void handleAddButtonSelected()
   {
      final DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.SAVE);
      dialog.setMessage("Select a classpath directory:");
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot workspaceDirectory = workspace.getRoot();
      dialog.setFilterPath(workspaceDirectory.getLocation().toString());
      final String directory = dialog.open();
      if (directory != null)
      {
         this.classpathList.add(directory);
      }
   }


   private void handleAddJarButtonSelected()
   {
      final FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
      dialog.setText("Select a Jar file:");
      dialog.setFilterExtensions(new String[] { "*.jar" });
      dialog.setFilterNames(new String[] { "Jar files" });
      final String jarFile = dialog.open();
      if (jarFile != null)
      {
         this.classpathList.add(jarFile);
      }
   }


   private void handleRemoveButtonSelected()
   {
      final int selectedIndex = this.classpathList.getSelectionIndex();
      if (selectedIndex > -1)
      {
         this.classpathList.remove(selectedIndex);
      }
   }


   /**
    * Updates the buttons and message in this page's launch configuration dialog.
    */
   private void update()
   {
      final int selectedIndex = this.classpathList.getSelectionIndex();
      this.upButton.setEnabled(selectedIndex >= 1);
      this.downButton.setEnabled(selectedIndex > -1 && selectedIndex < this.classpathList.getItemCount() - 1);
      this.removeButton.setEnabled(selectedIndex > -1);

      updateLaunchConfigurationDialog();
   }

}
