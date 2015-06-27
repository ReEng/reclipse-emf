package org.reclipse.generator.ui.wizard;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.reclipse.generator.GeneratorConstants;


/**
 * @author lowende
 * @author Last editor: $Author: oleg82 $
 * @version $Revision: 4439 $ $Date: 2010-06-28 13:26:17 +0200 (Mo, 28 Jun 2010) $
 */
public class ExportOptionsPage extends WizardPage
{

   private static final String REMOVE_PROJECT = "removeProject";

   private static final String REMOVE_ELEMENTS = "removeElements";

   private static final String DEBUG_INFORMATION = "debugInformation";

   private static final String CODE_COMPLIANCE_LEVEL = "codeComplianceLevel";

   private Combo complianceCombo;

   private Button removeProjectCheckButton, removeElementsCheckButton,
         debugInformationCheckButton;


   public ExportOptionsPage()
   {
      super("ExportOptionsPage");

      setTitle("Export Catalog");
      setDescription("Set additional options.");
      setPageComplete(true);
   }


   /**
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
   public void createControl(Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());

      GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 1;
      composite.setLayout(gridLayout);

      Label label;
      createGenerationOptionsComponent(composite);

      label = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      createDebugOptionsComponent(composite);

      initialize();

      setControl(composite);
      Dialog.applyDialogFont(parent);
   }


   private void createGenerationOptionsComponent(Composite composite)
   {
      Label label = new Label(composite, SWT.NONE);
      label.setText("Catalog generation options:");

      Composite complianceComposite = new Composite(composite, SWT.NONE);
      complianceComposite.setFont(composite.getFont());
      GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 2;
      complianceComposite.setLayout(gridLayout);

      Label complianceLabel = new Label(complianceComposite, SWT.NONE);
      complianceLabel.setText("Code Compliance Level: ");

      complianceCombo = new Combo(complianceComposite, SWT.READ_ONLY);
      complianceCombo.add("1.3", GeneratorConstants.JAVA_1_3);
      complianceCombo.add("1.4", GeneratorConstants.JAVA_1_4);
      complianceCombo.add("1.5", GeneratorConstants.JAVA_1_5);
      complianceCombo.add("1.6", GeneratorConstants.JAVA_1_6);
   }


   /**
    * @param parent
    */
   private void createDebugOptionsComponent(Composite parent)
   {
      Label label = new Label(parent, SWT.NONE);
      label.setText("Debugging options:");
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.removeProjectCheckButton = new Button(parent, SWT.CHECK);
      this.removeProjectCheckButton.setText("Remove temporary "
            + "project after generation");
      this.removeProjectCheckButton.setToolTipText("Remove the "
            + "temporal Eclipse project after generating "
            + "and compiling the catalog.");
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.horizontalSpan = 2;
      this.removeProjectCheckButton.setLayoutData(gridData);

      this.removeElementsCheckButton = new Button(parent, SWT.CHECK);
      this.removeElementsCheckButton
            .setText("Remove temporary elements from model after generation");
      this.removeElementsCheckButton
            .setToolTipText("Remove the temporary elements from model after generation");

      this.debugInformationCheckButton = new Button(parent, SWT.CHECK);
      this.debugInformationCheckButton.setText("Generate "
            + "with debug information");
      this.debugInformationCheckButton
            .setToolTipText("Compile code with debug information.");
   }


   private void initialize()
   {
      IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         try
         {
            int complianceLevel = settings.getInt(CODE_COMPLIANCE_LEVEL);
            this.complianceCombo.select(complianceLevel);
         }
         catch (NumberFormatException nfe)
         {
            this.complianceCombo.select(GeneratorConstants.JAVA_1_5);
         }

         boolean removeProject = settings.getBoolean(REMOVE_PROJECT);
         this.removeProjectCheckButton.setSelection(removeProject);

         boolean removeElements = settings.getBoolean(REMOVE_ELEMENTS);
         this.removeElementsCheckButton.setSelection(removeElements);

         boolean debugInformation = settings.getBoolean(DEBUG_INFORMATION);
         this.debugInformationCheckButton.setSelection(debugInformation);
      }
   }


   public boolean isRemoveProject()
   {
      return this.removeProjectCheckButton.getSelection();
   }


   public int getSelectedComplianceLevel()
   {
      return this.complianceCombo.getSelectionIndex();
   }


   public boolean isRemoveTemporaryElements()
   {
      return this.removeElementsCheckButton.getSelection();
   }


   public boolean isDebugInformation()
   {
      return this.debugInformationCheckButton.getSelection();
   }


   public void finish()
   {
      IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         settings.put(CODE_COMPLIANCE_LEVEL, getSelectedComplianceLevel());
         settings.put(REMOVE_PROJECT, isRemoveProject());
         settings.put(REMOVE_ELEMENTS, isRemoveTemporaryElements());
         settings.put(DEBUG_INFORMATION, isDebugInformation());
      }
   }

}
