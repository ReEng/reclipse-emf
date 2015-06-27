/**
 * 
 */
package org.reclipse.generator.ui.wizard;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class SelectFileWizardPage extends WizardPage implements SelectionListener, ModifyListener
{
   private static final String CATALOG_FILE_NAME = "catalogFileName";
   
   private Text catalogFileNameText;

   private Button browseCatalogFileButton;
   
   private boolean initializing = false;

   protected SelectFileWizardPage(String pageName)
   {
      super(pageName);
   }

   public void createControl(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 2;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      Label label = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);
      
      
      label = new Label(composite, SWT.NONE);
      label.setText("Choose a catalog file:");

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.catalogFileNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      this.catalogFileNameText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.catalogFileNameText.setLayoutData(gridData);

      this.browseCatalogFileButton = new Button(composite, SWT.PUSH);
      this.browseCatalogFileButton.setText("Browse...");
      this.browseCatalogFileButton.addSelectionListener(this);

      setControl(composite);
      Dialog.applyDialogFont(parent);
   }

   public void modifyText(ModifyEvent e)
   {
      checkPageComplete();
   }

   public void widgetSelected(SelectionEvent e)
   {
      Object source = e.getSource();
      if (source == this.browseCatalogFileButton) {
         FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
         dialog.setText("Select a Structural Patterns Catalog file:");
         dialog.setFileName(this.catalogFileNameText.getText());
         dialog.setFilterExtensions(new String[] { "*.ecore" });
         dialog
               .setFilterNames(new String[] { "Structural Patterns Catalog" });
         String traceFile = dialog.open();
         if (traceFile != null) {
            this.catalogFileNameText.setText(traceFile);
         }
      }

      checkPageComplete();
   }

   public void widgetDefaultSelected(SelectionEvent e)
   {      
   }

   
   private void checkPageComplete() {
      if (!this.initializing) {
         setPageComplete(false);

         if (this.catalogFileNameText.getText().trim().equals("")) {
            setErrorMessage("Please provide a file name for the structural patterns catalog.");
            return;
         }

         setPageComplete(true);
         setErrorMessage(null);
      }
   }
  

   private void initialize() {
      IDialogSettings settings = getDialogSettings();

      this.initializing = true;
      if (settings != null) {
         String catalogFileName = settings.get(CATALOG_FILE_NAME);
         if (catalogFileName != null) {
            this.catalogFileNameText.setText(catalogFileName);
         }
      }

      this.initializing = false;
      checkPageComplete();
   }
   
   
   /**
    * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
    */
   @Override
   public void setVisible(boolean visible) {
      if (visible) {
//         ExportWizard wizard = (ExportWizard) getWizard();
         initialize();
         checkPageComplete();
      }

      super.setVisible(visible);
   }
   

   public void finish() {
      IDialogSettings settings = getDialogSettings();

      if (settings != null) {
         settings.put(CATALOG_FILE_NAME, getExportFileName());
      }
   }

   public String getExportFileName()
   {
      return this.catalogFileNameText.getText().trim();
   }
}
