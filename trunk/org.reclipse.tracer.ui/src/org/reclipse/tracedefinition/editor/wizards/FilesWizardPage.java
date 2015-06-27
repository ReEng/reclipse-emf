package org.reclipse.tracedefinition.editor.wizards;


import java.io.File;

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
import org.reclipse.behavior.inference.input.IStructuralAnnotationsConstants;
import org.reclipse.tracer.model.definition.xml.ITraceDefinitionConstants;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3731 $ $Date: 2007-09-04 12:43:55 +0200 (Di, 04 Sep 2007) $
 */
public class FilesWizardPage extends WizardPage implements SelectionListener,
      ModifyListener
{

   private final static String SOURCE_ANNOTATIONS_FILE = "sourceAnnotationsFile";

   private final static String TARGET_TRACE_DEFINITION_FILE = "targetTraceDefinitionFile";

   private final static String ZIPPED_TRACE_DEFINITION = "zippedTraceDefinition";

   private final static String SAVE_ANNOTATIONS = "saveAnnotations";

   private final static String TARGET_ANNOTATIONS_FILE = "targetAnnotationsFile";

   private final static String ZIPPED_ANNOTATIONS = "zippedAnnotations";


   private Text sourceTraceDefinitionFileText;

   private Button sourceTraceDefinitionButton;

   private Text sourceAnnotationsFileText;

   private Button sourceAnnotationsFileButton;

   private Text targetTraceDefinitionFileText;

   private Button targetTraceDefinitionButton;

   private Button zippedTraceDefinitionButton;

   private Button saveAnnotationsButton;

   private Text targetAnnotationsFileText;

   private Button targetAnnotationsFileButton;

   private Button zippedAnnotationsButton;


   public FilesWizardPage()
   {
      super("FilesWizardPage");

      setTitle("Files for Splitting");
      setDescription("Provide the source and target files for splitting.");
      setPageComplete(false);
   }


   /**
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
   public void createControl(final Composite parent)
   {
      final GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 2;

      final Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(gridLayout);

      final GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      createSourceTraceDefintionFileControl(composite);
      createSeparatorLabel(composite);
      createSourceAnnotationsFileControl(composite);
      createSeparatorLabel(composite);
      createTargetTraceDefinitionFileControl(composite);
      createSeparatorLabel(composite);
      createTargetAnnotationsFileControl(composite);

      setControl(composite);
      initialize();
      checkPageComplete();
   }


   private void createSeparatorLabel(final Composite composite)
   {
      GridData gridData;
      final Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);
   }


   private void createSourceTraceDefintionFileControl(final Composite composite)
   {
      final Label label = new Label(composite, SWT.NONE);
      label.setText("Source trace definition:");

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.sourceTraceDefinitionFileText = new Text(composite, SWT.SINGLE
            | SWT.BORDER);
      this.sourceTraceDefinitionFileText.addModifyListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.sourceTraceDefinitionFileText.setLayoutData(gridData);

      this.sourceTraceDefinitionButton = new Button(composite, SWT.PUSH);
      this.sourceTraceDefinitionButton.setText("Browse...");
      this.sourceTraceDefinitionButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.sourceTraceDefinitionButton.setLayoutData(gridData);
   }


   private void createSourceAnnotationsFileControl(final Composite composite)
   {
      final Label label = new Label(composite, SWT.NONE);
      label.setText("Source structural annotations:");

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.sourceAnnotationsFileText = new Text(composite, SWT.SINGLE
            | SWT.BORDER);
      this.sourceAnnotationsFileText.addModifyListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.sourceAnnotationsFileText.setLayoutData(gridData);

      this.sourceAnnotationsFileButton = new Button(composite, SWT.PUSH);
      this.sourceAnnotationsFileButton.setText("Browse...");
      this.sourceAnnotationsFileButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.sourceAnnotationsFileButton.setLayoutData(gridData);
   }


   private void createTargetTraceDefinitionFileControl(final Composite composite)
   {
      final Label label = new Label(composite, SWT.NONE);
      label.setText("Target trace definition:");

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.targetTraceDefinitionFileText = new Text(composite, SWT.SINGLE
            | SWT.BORDER);
      this.targetTraceDefinitionFileText.addModifyListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.targetTraceDefinitionFileText.setLayoutData(gridData);

      this.targetTraceDefinitionButton = new Button(composite, SWT.PUSH);
      this.targetTraceDefinitionButton.setText("Browse...");
      this.targetTraceDefinitionButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.targetTraceDefinitionButton.setLayoutData(gridData);

      this.zippedTraceDefinitionButton = new Button(composite, SWT.CHECK);
      this.zippedTraceDefinitionButton
            .setText("Compress trace definition file");

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      this.zippedTraceDefinitionButton.setLayoutData(gridData);
   }


   private void createTargetAnnotationsFileControl(final Composite composite)
   {
      final Label label = new Label(composite, SWT.NONE);
      label.setText("Target structural annotations:");

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.saveAnnotationsButton = new Button(composite, SWT.CHECK);
      this.saveAnnotationsButton
            .setText("Save splitted structural annotations");
      this.saveAnnotationsButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      this.saveAnnotationsButton.setLayoutData(gridData);

      this.targetAnnotationsFileText = new Text(composite, SWT.SINGLE
            | SWT.BORDER);
      this.targetAnnotationsFileText.addModifyListener(this);
      this.targetAnnotationsFileText.setEnabled(false);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalIndent = 16;
      this.targetAnnotationsFileText.setLayoutData(gridData);

      this.targetAnnotationsFileButton = new Button(composite, SWT.PUSH);
      this.targetAnnotationsFileButton.setText("Browse...");
      this.targetAnnotationsFileButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.targetAnnotationsFileButton.setLayoutData(gridData);

      this.zippedAnnotationsButton = new Button(composite, SWT.CHECK);
      this.zippedAnnotationsButton
            .setText("Compress structural annotations file");

      gridData = new GridData();
      gridData.horizontalIndent = 16;
      gridData.horizontalSpan = 2;
      this.zippedAnnotationsButton.setLayoutData(gridData);
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetDefaultSelected(final SelectionEvent event)
   {
      // nothing to do
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetSelected(final SelectionEvent event)
   {
      final Object source = event.getSource();
      if (source == this.sourceTraceDefinitionButton)
      {
         openFileDialog("Open a trace definition file",
               ITraceDefinitionConstants.FILE_EXTENSIONS,
               ITraceDefinitionConstants.FILE_DESCRIPTIONS,
               this.sourceTraceDefinitionFileText);
      }
      else if (source == this.sourceAnnotationsFileButton)
      {
         openFileDialog("Open a structural annotations file",
               IStructuralAnnotationsConstants.FILE_EXTENSIONS,
               IStructuralAnnotationsConstants.FILE_DESCRIPTIONS,
               this.sourceAnnotationsFileText);
      }
      else if (source == this.targetTraceDefinitionButton)
      {
         openFileDialog("Provide a trace definition file",
               ITraceDefinitionConstants.FILE_EXTENSIONS,
               ITraceDefinitionConstants.FILE_DESCRIPTIONS,
               this.targetTraceDefinitionFileText);
      }
      else if (source == this.saveAnnotationsButton)
      {
         final boolean enabled = this.saveAnnotationsButton.getSelection();
         this.targetAnnotationsFileText.setEnabled(enabled);
         this.targetAnnotationsFileButton.setEnabled(enabled);
         this.zippedAnnotationsButton.setEnabled(enabled);
      }
      else if (source == this.targetAnnotationsFileButton)
      {
         openFileDialog("Provide a structural annotations file",
               IStructuralAnnotationsConstants.FILE_EXTENSIONS,
               IStructuralAnnotationsConstants.FILE_DESCRIPTIONS,
               this.targetAnnotationsFileText);
      }

      checkPageComplete();
   }


   /**
    * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
    */
   public void modifyText(final ModifyEvent event)
   {
      checkPageComplete();
   }


   private void initialize()
   {
      final IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         final String sourceAnnotationsFile = settings
               .get(SOURCE_ANNOTATIONS_FILE);
         if (sourceAnnotationsFile != null)
         {
            this.sourceAnnotationsFileText.setText(sourceAnnotationsFile);
         }

         final String targetTraceDefinitionFile = settings
               .get(TARGET_TRACE_DEFINITION_FILE);
         if (targetTraceDefinitionFile != null)
         {
            this.targetTraceDefinitionFileText
                  .setText(targetTraceDefinitionFile);
         }

         final boolean zippedTraceDefinition = settings
               .getBoolean(ZIPPED_TRACE_DEFINITION);
         this.zippedTraceDefinitionButton.setSelection(zippedTraceDefinition);

         final boolean saveAnnotations = settings.getBoolean(SAVE_ANNOTATIONS);
         this.saveAnnotationsButton.setSelection(saveAnnotations);

         this.targetAnnotationsFileText.setEnabled(saveAnnotations);
         this.targetAnnotationsFileButton.setEnabled(saveAnnotations);
         this.zippedAnnotationsButton.setEnabled(saveAnnotations);
         if (saveAnnotations)
         {
            final String targetAnnotationsFile = settings
                  .get(TARGET_ANNOTATIONS_FILE);
            if (targetAnnotationsFile != null)
            {
               this.targetAnnotationsFileText.setText(targetAnnotationsFile);
            }

            final boolean zippedAnnotations = settings
                  .getBoolean(ZIPPED_ANNOTATIONS);
            this.zippedAnnotationsButton.setSelection(zippedAnnotations);
         }
      }
   }


   /**
    * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
    */
   @Override
   public void setVisible(final boolean visible)
   {
      final SplitTraceDefinitionWizard wizard = (SplitTraceDefinitionWizard) getWizard();
      final String selectedFile = wizard.getSelectedFile();

      if (selectedFile != null && !selectedFile.trim().equals(""))
      {
         this.sourceTraceDefinitionFileText.setText(selectedFile);
      }

      super.setVisible(visible);
   }


   public String getSourceTraceDefinitionFile()
   {
      return this.sourceTraceDefinitionFileText.getText().trim();
   }


   public String getSourceAnnotationsFile()
   {
      return this.sourceAnnotationsFileText.getText().trim();
   }


   public String getTargetTraceDefinitionFile()
   {
      return this.targetTraceDefinitionFileText.getText().trim();
   }


   public boolean isTraceDefinitionFileZipped()
   {
      return this.zippedTraceDefinitionButton.getSelection();
   }


   public boolean isSaveAnnotationsFile()
   {
      return this.saveAnnotationsButton.getSelection();
   }


   public String getTargetAnnotationsFile()
   {
      return this.targetAnnotationsFileText.getText().trim();
   }


   public boolean isTargetAnnotationsFileZipped()
   {
      return this.zippedAnnotationsButton.getSelection();
   }


   private void openFileDialog(final String dialogText,
         final String[] extensions, final String[] filterNames, final Text text)
   {
      final FileDialog dialog = new FileDialog(getShell(), SWT.OPEN
            | SWT.SINGLE);
      dialog.setFilterExtensions(extensions);
      dialog.setText(dialogText);
      dialog.setFilterNames(filterNames);
      final String filePath = dialog.open();
      if (filePath != null)
      {
         text.setText(filePath);
      }
   }


   /* package */void finish()
   {
      final IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         settings.put(SOURCE_ANNOTATIONS_FILE, getSourceAnnotationsFile());
         settings.put(TARGET_TRACE_DEFINITION_FILE,
               getTargetTraceDefinitionFile());
         settings.put(ZIPPED_TRACE_DEFINITION, isTraceDefinitionFileZipped());
         settings.put(SAVE_ANNOTATIONS, isSaveAnnotationsFile());

         if (isSaveAnnotationsFile())
         {
            settings.put(TARGET_ANNOTATIONS_FILE, getTargetAnnotationsFile());
            settings.put(ZIPPED_ANNOTATIONS, isTargetAnnotationsFileZipped());
         }
         else
         {
            settings.put(TARGET_ANNOTATIONS_FILE, "");
            settings.put(ZIPPED_ANNOTATIONS, false);
         }
      }
   }


   private void checkPageComplete()
   {
      final File sourceTraceDefinitionFile = new File(
            getSourceTraceDefinitionFile());
      if (!sourceTraceDefinitionFile.exists())
      {
         setPageComplete(false);
         setErrorMessage("Please provide a valid source trace definition file.");
         return;
      }

      if (!getSourceAnnotationsFile().equals(""))
      {
         final File file = new File(getSourceAnnotationsFile());
         if (!file.exists())
         {
            setPageComplete(false);
            setErrorMessage("Please provide a valid source structural annotations file.");
            return;
         }
      }

      if (getTargetTraceDefinitionFile().equals(""))
      {
         setPageComplete(false);
         setErrorMessage("Please provide a target trace definition file.");
         return;
      }

      if (isSaveAnnotationsFile() && getTargetAnnotationsFile().equals(""))
      {
         setPageComplete(false);
         setErrorMessage("Please provide a target structural annotations file.");
         return;
      }

      setPageComplete(true);
      setErrorMessage(null);
   }

}
