package org.reclipse.behavior.generator.ui.wizards;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.fujaba.commons.ui.WorkbenchFileTreeSelectionDialog;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.tracer.model.definition.xml.ITraceDefinitionConstants;


public class ExportTraceDefinitionWizardPage extends WizardPage implements SelectionListener, ModifyListener,
      ISelectionChangedListener
{

   private static final String TRACE_DEFINITION_FILE_NAME = "traceDefinitionFileName";

   private Text traceDefinitionFileNameText;

   private Text annotationsFileNameText;

   CheckboxTableViewer tableViewer;

   private Button selectAllButton;

   private Button deselectAllButton;

   private Button browseTraceDefinitionFileButton;

   private Button browseAnnotationsFileButton;

   private ElementTreeSelectionDialog annotationsFileDialog;


   protected ExportTraceDefinitionWizardPage(String pageName)
   {
      super(pageName);
      setTitle("Specify the TraceDefinition");
      setDescription("Choose the behavioral patterns that contain the methods to be exported, "
            + "a file with the results from the structural inference, and a file name to save the tracedefinition.");
      setPageComplete(false);
   }


   @Override
   public void widgetDefaultSelected(SelectionEvent e)
   {
   }


   @Override
   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetSelected(SelectionEvent event)
   {
      Object source = event.getSource();
      if (source == this.selectAllButton)
      {
         this.tableViewer.setAllChecked(true);
      }
      else if (source == this.deselectAllButton)
      {
         this.tableViewer.setAllChecked(false);
      }
      else if (source == this.browseTraceDefinitionFileButton)
      {
         FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
         dialog.setText("Select a Trace Definition file:");
         dialog.setFileName(this.traceDefinitionFileNameText.getText());
         dialog.setFilterExtensions(new String[] { "*" + ITraceDefinitionConstants.XML_FILE_SUFFIX,
               "*" + ITraceDefinitionConstants.ZIP_FILE_SUFFIX });
         dialog.setFilterNames(new String[] { "Trace Definition" });
         String traceFile = dialog.open();
         if (traceFile != null)
         {
            this.traceDefinitionFileNameText.setText(traceFile);
         }
      }
      else if (source == this.browseAnnotationsFileButton)
      {
         if (this.annotationsFileDialog.open() == Window.OK)
         {
            IResource resource = (IResource) this.annotationsFileDialog.getFirstResult();
            this.annotationsFileNameText.setText(resource.getFullPath().toString());
         }
      }
      checkPageComplete();
   }


   private void initialize(BPCatalog bpCatalog)
   {
      this.tableViewer.setInput(bpCatalog);
   }


   /**
    * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
    */
   public void modifyText(ModifyEvent e)
   {
      checkPageComplete();
   }


   /* package */String getTraceDefinitionFileName()
   {
      return this.traceDefinitionFileNameText.getText();
   }


   public List<ASGAnnotation> getAnnotations()
   {
      URI annotationsFileUri = URI.createPlatformResourceURI(this.annotationsFileNameText.getText(), true);
      ResourceSet annoRess = new ResourceSetImpl();
      URI normalized = annoRess.getURIConverter().normalize(annotationsFileUri);
      Resource annoRes = annoRess.getResource(normalized, true);
      List<ASGAnnotation> annotations = new ArrayList<ASGAnnotation>();
      for (EObject object : annoRes.getContents())
      {
         if (object instanceof ASGAnnotation)
         {
            ASGAnnotation anno = (ASGAnnotation) object;
            annotations.add(anno);
         }
      }
      return annotations;
   }


   /**
    * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
    */
   @Override
   public void setVisible(boolean visible)
   {
      if (visible)
      {
         ExportTraceDefinitionWizard wizard = (ExportTraceDefinitionWizard) getWizard();
         this.tableViewer.getTable().getColumn(0).pack();
         initialize(wizard.getBPCatalog());
         checkPageComplete();
      }

      super.setVisible(visible);
   }


   @Override
   public void createControl(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 2;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      createPatternsTableComponent(composite);

      Label label = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      createAnnotationsFileNameComponent(composite);
      createCatalogFileNameComponent(composite);

      setControl(composite);
      Dialog.applyDialogFont(parent);
   }


   private void createCatalogFileNameComponent(Composite parent)
   {
      Label label = new Label(parent, SWT.NONE);
      label.setText("Choose a trace definition file, where the result will be saved:");

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.traceDefinitionFileNameText = new Text(parent, SWT.SINGLE | SWT.BORDER);
      this.traceDefinitionFileNameText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.traceDefinitionFileNameText.setLayoutData(gridData);

      this.browseTraceDefinitionFileButton = new Button(parent, SWT.PUSH);
      this.browseTraceDefinitionFileButton.setText("Browse...");
      this.browseTraceDefinitionFileButton.addSelectionListener(this);
   }


   private void createAnnotationsFileNameComponent(Composite parent)
   {
      Label label = new Label(parent, SWT.NONE);
      label.setText("Choose a file, that contains the structural annotations:");

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.annotationsFileDialog = new WorkbenchFileTreeSelectionDialog(getShell(), "Annotations File",
            "Select a file with annotations", "psa");

      this.annotationsFileNameText = new Text(parent, SWT.SINGLE | SWT.BORDER);
      this.annotationsFileNameText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.annotationsFileNameText.setLayoutData(gridData);

      this.browseAnnotationsFileButton = new Button(parent, SWT.PUSH);
      this.browseAnnotationsFileButton.setText("Browse...");
      this.browseAnnotationsFileButton.addSelectionListener(this);
   }


   private void createPatternsTableComponent(Composite parent)
   {
      Label label = new Label(parent, SWT.NONE);
      label.setText("Select the behavioral patterns:");
      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      gridData.horizontalAlignment = SWT.FILL;
      label.setLayoutData(gridData);

      this.tableViewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER);
      Table table = this.tableViewer.getTable();
      table.setHeaderVisible(true);
      table.setLinesVisible(true);

      TableColumn tableColumn1 = new TableColumn(table, SWT.NONE);
      tableColumn1.setText("Name");
      tableColumn1.setWidth(100);

      TableColumn tableColumn2 = new TableColumn(table, SWT.NONE);
      tableColumn2.setText("Negative");
      tableColumn2.setResizable(false);

      this.tableViewer.setContentProvider(new PatternsListContentProvider());
      this.tableViewer.setLabelProvider(new PatternsListLabelProvider());
      this.tableViewer.addFilter(createViewerFilter());
      this.tableViewer.addSelectionChangedListener(this);
      this.tableViewer.setAllChecked(true);

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      this.tableViewer.getTable().setLayoutData(gridData);

      Composite buttonComposite = new Composite(parent, SWT.NONE);
      RowLayout rowLayout = new RowLayout();
      rowLayout.marginLeft = 0;
      buttonComposite.setLayout(rowLayout);
      gridData = new GridData();
      gridData.horizontalSpan = 2;
      buttonComposite.setLayoutData(gridData);

      this.selectAllButton = new Button(buttonComposite, SWT.PUSH);
      this.selectAllButton.setText("Select All");
      this.selectAllButton.addSelectionListener(this);

      this.deselectAllButton = new Button(buttonComposite, SWT.PUSH);
      this.deselectAllButton.setText("Deselect All");
      this.deselectAllButton.addSelectionListener(this);

      tableColumn1.pack();
      tableColumn2.pack();
   }


   private ViewerFilter createViewerFilter()
   {
      return new ViewerFilter()
      {
         @Override
         public boolean select(Viewer viewer, Object parentElement, Object element)
         {
            return element instanceof BehavioralPattern;
         }

      };
   }


   @Override
   public void selectionChanged(SelectionChangedEvent event)
   {
      checkPageComplete();
   }


   private void checkPageComplete()
   {
      setPageComplete(false);

      if (this.tableViewer.getCheckedElements().length == 0)
      {
         setErrorMessage("Please select at least one Behavioral Pattern.");
         return;
      }
      else if (this.traceDefinitionFileNameText.getText().trim().equals(""))
      {
         setErrorMessage("Please provide a file name for the Behavioral Patterns Catalog.");
         return;
      }
      setPageComplete(true);
      setErrorMessage(null);
   }


   public List<BehavioralPattern> getSelectedBPDiagrams()
   {
      Object[] selection = this.tableViewer.getCheckedElements();
      List<BehavioralPattern> diagrams = new ArrayList<BehavioralPattern>(selection.length);
      for (Object object : selection)
      {
         diagrams.add((BehavioralPattern) object);
      }

      return diagrams;
   }


   static class PatternsListContentProvider implements IStructuredContentProvider
   {

      private BPCatalog bpCatalog;


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
       *      java.lang.Object, java.lang.Object)
       */
      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
         this.bpCatalog = (BPCatalog) newInput;
      }


      /**
       * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
       */
      public Object[] getElements(Object inputElement)
      {
         List<BehavioralPattern> patterns = this.bpCatalog.getBehavioralPatterns();
         return patterns.toArray();
      }

   }


   static class PatternsListLabelProvider extends LabelProvider implements ITableLabelProvider
   {

      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
       */
      public Image getColumnImage(Object element, int columnIndex)
      {
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
       */
      public String getColumnText(Object element, int columnIndex)
      {
         BehavioralPattern diagram = (BehavioralPattern) element;
         switch (columnIndex)
         {
            case 0:
               return diagram.getName();
            case 1:
               return Boolean.toString(diagram.isNegative());
            default:
               return "unknown";
         }
      }

   }


   /* package */void finish()
   {
      IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         settings.put(TRACE_DEFINITION_FILE_NAME, getTraceDefinitionFileName());
      }
   }

}
