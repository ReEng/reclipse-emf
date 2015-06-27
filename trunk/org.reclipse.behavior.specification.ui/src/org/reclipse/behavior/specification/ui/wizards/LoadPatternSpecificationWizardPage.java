package org.reclipse.behavior.specification.ui.wizards;


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


public class LoadPatternSpecificationWizardPage extends WizardPage implements
      SelectionListener
{

   private Text structuralPatternFileText;

   private Button structuralPatternFileButton;


   protected LoadPatternSpecificationWizardPage(String name)
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

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      Label structuralPatternsFileLabel = new Label(composite, SWT.LEFT);
      structuralPatternsFileLabel.setText("Choose a structural pattern file:");
      structuralPatternsFileLabel.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.structuralPatternFileText = new Text(composite, SWT.SINGLE
            | SWT.BORDER);
      this.structuralPatternFileText.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.structuralPatternFileButton = new Button(composite, SWT.PUSH);
      this.structuralPatternFileButton.setText("Browse...");
      this.structuralPatternFileButton.addSelectionListener(this);
      this.structuralPatternFileButton.setLayoutData(gridData);

      setControl(composite);
   }


   @Override
   public void widgetDefaultSelected(SelectionEvent e)
   {
   }


   private void openFileDialog(String dialogText, String[] extensions, Text text)
   {
      FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
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
         openFileDialog("Select a structural annotations file",
               new String[] { "*.psc" }, this.structuralPatternFileText);
      }
   }


   public String getFilePath()
   {
      return this.structuralPatternFileText.getText();
   }


}
