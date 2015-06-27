/**
 * Copyright 2011 University of Paderborn. All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors: Aljoscha Hark - initial API and implementation.
 */
package org.reclipse.patterns.structure.generator.ui;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.fujaba.commons.console.ReportLevel;
import org.fujaba.commons.ui.WorkbenchFileTreeSelectionDialog;
import org.reclipse.patterns.structure.generator.ui.internal.Constants;


public class ExportPatternsWizardPage extends WizardPage
{
   private final WorkbenchFileTreeSelectionDialog dialog;


   private String inputPath;

   private String outputContainer;

   private String outputName;


   protected ReportLevel reportLevel;


   private Combo reportCombo;


   protected ExportPatternsWizardPage()
   {
      super(ExportPatternsWizardPage.class.getSimpleName());

      setTitle("Generate Pattern Detection Engines");
      setDescription("Select the location to which the pattern detection engines should be exported.");

      // create catalog selection dialog
      Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
      String title = "Select Catalog";
      String message = "Select the catalog file which should be exported.";

      dialog = new WorkbenchFileTreeSelectionDialog(shell, title, message, Constants.EXT_CATALOG);
   }


   @Override
   public void createControl(Composite parent)
   {
      // load settings
      if (inputPath == null || outputContainer == null || outputName == null || reportLevel == null)
      {
         inputPath = getDialogSettings().get(Constants.KEY_INPUTPATH);
         inputPath = inputPath == null ? "" : inputPath; //$NON-NLS-1$

         outputContainer = getDialogSettings().get(Constants.KEY_OUTPUT_CONTAINER);
         outputContainer = outputContainer == null ? "" : outputContainer; //$NON-NLS-1$

         outputName = getDialogSettings().get(Constants.KEY_OUTPUT_NAME);
         outputName = outputName == null ? "" : outputName; //$NON-NLS-1$

         reportLevel = ReportLevel.getItem(getDialogSettings().get(Constants.KEY_REPORT_LEVEL));
      }

      // create interface
      Composite main = new Composite(parent, SWT.NONE);
      GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(main);
      main.setLayout(new GridLayout(1, false));

      Composite input = new Composite(main, SWT.NONE);
      GridDataFactory.fillDefaults().grab(true, false).applyTo(input);
      GridLayoutFactory.fillDefaults().numColumns(3).applyTo(input);

      Label inputLabel = new Label(input, SWT.TRAIL);

      final Text inputText = new Text(input, SWT.LEAD | SWT.BORDER | SWT.SINGLE);
      GridDataFactory.fillDefaults().grab(true, false).applyTo(inputText);

      Button inputButton = new Button(input, SWT.PUSH);
      GridDataFactory.fillDefaults().applyTo(inputButton);

      Group output = new Group(main, SWT.NONE);
      GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(output);
      GridDataFactory.fillDefaults().grab(true, true).applyTo(output);

      final TreeViewer outputContainerViewer = new TreeViewer(output, SWT.BORDER | SWT.SINGLE);
      GridDataFactory.fillDefaults().grab(true, true).applyTo(outputContainerViewer.getTree());
      outputContainerViewer.setContentProvider(new BaseWorkbenchContentProvider());
      outputContainerViewer.setLabelProvider(new WorkbenchLabelProvider());
      outputContainerViewer.setComparator(new ViewerComparator());
      outputContainerViewer.addFilter(new ViewerFilter()
      {
         @Override
         public boolean select(Viewer viewer, Object parentElement, Object element)
         {
            if (element instanceof IContainer)
            {
               return ((IContainer) element).getName().charAt(0) != '.';
            }
            return false;
         }
      });
      IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
      outputContainerViewer.setInput(root);

      Composite outputFile = new Composite(output, SWT.NONE);
      GridDataFactory.fillDefaults().grab(true, false).applyTo(outputFile);
      GridLayoutFactory.fillDefaults().numColumns(2).applyTo(outputFile);

      Label outputFileLabel = new Label(outputFile, SWT.TRAIL);

      final Text outputFileText = new Text(outputFile, SWT.LEAD | SWT.BORDER | SWT.SINGLE);
      GridDataFactory.fillDefaults().grab(true, false).applyTo(outputFileText);

      // hook listeners
      inputText.addModifyListener(new ModifyListener()
      {
         @Override
         public void modifyText(ModifyEvent e)
         {
            inputPath = inputText.getText();
            setPageComplete(isValid());
         }
      });

      inputButton.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            if (dialog.open() == Window.OK)
            {
               Object selected = dialog.getFirstResult();
               if (selected instanceof IFile)
               {
                  inputText.setText(((IFile) selected).getFullPath().toString());
               }
            }
         }
      });

      outputContainerViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            if (!outputContainerViewer.getSelection().isEmpty())
            {
               Object selected = ((IStructuredSelection) outputContainerViewer.getSelection()).getFirstElement();
               if (selected instanceof IContainer)
               {
                  outputContainer = ((IContainer) selected).getFullPath().toString();
               }
               else
               {
                  outputContainer = null;
               }
               setPageComplete(isValid());
            }
         }
      });

      outputFileText.addModifyListener(new ModifyListener()
      {
         @Override
         public void modifyText(ModifyEvent e)
         {
            outputName = outputFileText.getText();
            setPageComplete(isValid());
         }
      });

      // set defaults
      inputLabel.setText("Catalog:");
      inputButton.setText("Select");
      output.setText("Output");
      outputFileLabel.setText("File Name:");

      // load settings
      inputText.setText(inputPath);
      outputFileText.setText(outputName);
      IResource container = root.findMember(inputPath);
      if (container instanceof IFile)
      {
         outputContainerViewer.setSelection(new StructuredSelection(container.getParent()));
      }

      setControl(main);

      Composite reportComposite = new Composite(main, SWT.NONE);
      reportComposite.setLayout(new GridLayout(2, false));

      Label reportLabel = new Label(reportComposite, SWT.NONE);
      reportLabel.setText("Report Level:");

      reportCombo = new Combo(reportComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
      reportCombo.setItems(ReportLevel.getItems());
      reportCombo.select(ReportLevel.getIndex(reportLevel));

      reportCombo.addModifyListener(new ModifyListener()
      {
         @Override
         public void modifyText(ModifyEvent e)
         {
            reportLevel = ReportLevel.getItem(reportCombo.getSelectionIndex());
         }
      });
   }


   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      // search for a selected file
      if (selection != null && !selection.isEmpty())
      {
         for (Object selected : selection.toArray())
         {
            if (selected instanceof IFile)
            {
               IFile file = (IFile) selected;
               if (Constants.EXT_CATALOG.equalsIgnoreCase(file.getFileExtension()))
               {
                  inputPath = file.getFullPath().toString();
                  outputContainer = file.getParent().getFullPath().toString();
                  outputName = file.getFullPath().addFileExtension(Constants.OUTPUT_FILE_EXTENSION).toString();
                  reportLevel = ReportLevel.INFO;
                  break;
               }
            }
         }
      }
   }


   public void saveSettings()
   {
      getDialogSettings().put(Constants.KEY_INPUTPATH, inputPath);
      getDialogSettings().put(Constants.KEY_OUTPUT_CONTAINER, outputContainer);
      getDialogSettings().put(Constants.KEY_OUTPUT_NAME, outputName);
      getDialogSettings().put(Constants.KEY_REPORT_LEVEL, ReportLevel.getIndex(reportLevel));
   }


   public String getInputPath()
   {
      return inputPath;
   }


   public String getOutputPath()
   {
      return outputContainer + IPath.SEPARATOR + outputName;
   }


   public ReportLevel getReportLevel()
   {
      return reportLevel;
   }


   protected boolean isValid()
   {
      IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

      // check input path
      if (inputPath == null || !(root.findMember(inputPath) instanceof IFile))
      {
         setErrorMessage("Select a valid input resource!");
         return false;
      }
      else
      {
         // set initial selection to dialog
         dialog.setInitialSelection(root.findMember(inputPath));
      }

      // check output container
      if (outputContainer == null || !(root.findMember(outputContainer) instanceof IContainer))
      {
         setErrorMessage("Select a valid output container!");
         return false;
      }

      setMessage(null);
      setErrorMessage(null);
      return true;
   }
}
