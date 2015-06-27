package org.reclipse.behavior.specification.ui.wizards;


import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.fujaba.commons.ui.WorkbenchFileTreeSelectionDialog;


public class LoadBPCatalogWizardPage extends WizardPage implements SelectionListener, ModifyListener
{

   private Text behavioralCatalogFileText;

   private WorkbenchFileTreeSelectionDialog bpCatalogDialog;


   public LoadBPCatalogWizardPage(String name)
   {
      super(name);
   }


   @Override
   public void createControl(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 2;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(gridLayout);

      this.bpCatalogDialog = new WorkbenchFileTreeSelectionDialog(getShell(), "BPCatalog", "Select BPCatalog", "bp");

      Group inputs = new Group(composite, SWT.SHADOW_IN);
      inputs.setText("Input");
      inputs.setLayout(new GridLayout(3, false));
      inputs.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

      this.behavioralCatalogFileText = addLabeledResourceSelection(inputs, "BPCatalog:", bpCatalogDialog);


      setControl(composite);
   }


   protected Text addLabeledResourceSelection(Composite parent, String label, final ElementTreeSelectionDialog dialog)
   {
      Label select = new Label(parent, SWT.LEFT);
      select.setText(label);

      final Text input = new Text(parent, SWT.BORDER);
      input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
      input.addModifyListener(this);

      Button browse = new Button(parent, SWT.PUSH);
      browse.setText("Select...");
      browse.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            if (dialog.open() == Window.OK)
            {
               IResource resource = (IResource) dialog.getFirstResult();
               input.setText(resource.getFullPath().toString());
            }
         }
      });

      return input;
   }


   @Override
   public void widgetSelected(SelectionEvent e)
   {
      widgetDefaultSelected(e);
   }


   @Override
   public void widgetDefaultSelected(SelectionEvent e)
   {
      setPageComplete(isValid());
   }


   protected boolean isValid()
   {
      if (this.behavioralCatalogFileText.getText() == null)
      {
         setErrorMessage("A resource with the BPCatalog has to be given.");
      }
      if (this.behavioralCatalogFileText.getText() != null)
      {
         setErrorMessage(null);
         return true;
      }

      return false;
   }


   public String getFilePath()
   {
      return this.behavioralCatalogFileText.getText();
   }


   @Override
   public void modifyText(ModifyEvent e)
   {
      if (e.getSource() == this.behavioralCatalogFileText)
      {
         this.bpCatalogDialog.setInitialSelection(ResourcesPlugin.getWorkspace().getRoot()
               .findMember(this.behavioralCatalogFileText.getText()));
      }

      setPageComplete(isValid());
   }


}
