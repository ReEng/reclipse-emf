package org.reclipse.behavior.specification.ui.wizards;


import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.ui.provider.CatalogTreeContentProvider;
import org.reclipse.structure.specification.ui.provider.CatalogTreeLabelProvider;


public class LoadStructuralPatternWizardPage extends WizardPage implements
      SelectionListener
{

   private Text structuralPatternFileText;

   private Button structuralPatternFileButton;

   LoadPSCatalogWizardPage psCatalogFilePage;

   TreeViewer treeViewer;


   protected LoadStructuralPatternWizardPage(String name)
   {
      super(name);
   }


   public LoadStructuralPatternWizardPage(String name,
         LoadPSCatalogWizardPage psCatalogFilePage)
   {
      this(name);
      this.psCatalogFilePage = psCatalogFilePage;
   }


   @Override
   public void createControl(Composite parent)
   {
      // setCatalog(psCatalogFilePage.getPSCatalog());

      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 2;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(gridLayout);

      createPatternsTreeComponent(composite);

      // GridData gridData = new GridData();
      // gridData.horizontalAlignment = SWT.FILL;
      // gridData.verticalAlignment = SWT.FILL;
      // composite.setLayoutData(gridData);
      //
      // gridData = new GridData();
      // gridData.horizontalSpan = 2;
      // Label structuralPatternsFileLabel = new Label(composite, SWT.LEFT);
      // structuralPatternsFileLabel.setText("Choose a structural pattern file:");
      // structuralPatternsFileLabel.setLayoutData(gridData);
      //
      // gridData = new GridData();
      // gridData.horizontalAlignment = SWT.FILL;
      // gridData.grabExcessHorizontalSpace = true;
      // this.structuralPatternFileText = new Text(composite, SWT.SINGLE
      // | SWT.BORDER);
      // this.structuralPatternFileText.setLayoutData(gridData);
      //
      // gridData = new GridData();
      // gridData.horizontalAlignment = SWT.FILL;
      // treeViewer.setLayoutData(gridData);

      setControl(composite);
   }


   @Override
   public void widgetDefaultSelected(SelectionEvent e)
   {
   }


   private void openFileDialog(String dialogText, String[] extensions, Text text)
   {
      FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot workspaceDirectory = workspace.getRoot();
      dialog.setFilterPath(workspaceDirectory.getLocation().toString());
      dialog.setFilterExtensions(extensions);
      dialog.setText(dialogText);
      String filePath = dialog.open();
      if (filePath != null)
      {
         text.setText(filePath);
      }
   }


   @Override
   public void widgetSelected(SelectionEvent e)
   {
      Object source = e.getSource();
      if (source == this.structuralPatternFileButton)
      {
         openFileDialog("Select a structural patterns file",
               new String[] { "*.psc" }, this.structuralPatternFileText);
      }
   }


   public String getFilePath()
   {
      return this.structuralPatternFileText.getText();
   }


   private void createPatternsTreeComponent(Composite parent)
   {
      Label label = new Label(parent, SWT.NONE);
      label.setText("Select the structural pattern:");
      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      gridData.horizontalAlignment = SWT.FILL;
      label.setLayoutData(gridData);

      this.treeViewer = new TreeViewer(parent);
      this.treeViewer.setContentProvider(new CatalogTreeContentProvider());
      this.treeViewer.setLabelProvider(new CatalogTreeLabelProvider());
      this.treeViewer.expandAll();
      this.treeViewer.addFilter(createViewerFilter());
      this.treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
   }


   private ViewerFilter createViewerFilter()
   {
      return new ViewerFilter()
      {
         @Override
         public boolean select(Viewer viewer, Object parentElement,
               Object element)
         {
            return element instanceof PSPatternSpecification;
         }

      };
   }


   private void initialize(PSCatalog psCatalog)
   {
      this.treeViewer.setInput(psCatalog);
   }


   /**
    * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
    */
   @Override
   public void setVisible(boolean visible)
   {
      if (visible)
      {
         initialize(((NewBehavioralPatternWizard) this.getWizard())
               .getPSCatalog());
      }

      super.setVisible(visible);
   }


   public PSPatternSpecification getPattern()
   {
      PSPatternSpecification pattern = (PSPatternSpecification) ((ITreeSelection)treeViewer.getSelection()).getFirstElement();
      return pattern;
   }

}
