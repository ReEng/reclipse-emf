package org.reclipse.behavior.inference.ui.wizard;


import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.window.Window;
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
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.fujaba.commons.ui.WorkbenchFileTreeSelectionDialog;
import org.reclipse.tracer.model.tracegraph.xml.ITraceConstants;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10. Mrz 2010) $
 */
public class BehaviorRecognitionConfigurationPage extends WizardPage implements SelectionListener, ModifyListener
{

   private static final String ANNOTATIONS_FILE = "annotationsFile";

   private static final String TRACE_FILE = "traceFile";

   private static final String CATALOG_FILE = "catalogFile";

   private static final String RESULT_FILE = "resultFile";

   private static final String LOG_TRACES = "logTraces";


   private Text annotationsFileText;

   private Button annotationsFileButton;

   private Text traceFileText;

   private Button traceFileButton;

   private Text catalogFileText;

   private Button catalogFileButton;

   private Text resultFileText;

   private Button resultFileButton;

   private Button logTracesButton;

   private ElementTreeSelectionDialog annotationsFileDialog;


   public BehaviorRecognitionConfigurationPage()
   {
      super("BehaviorDetectionConfigurationPage");

      setTitle("Configure the behavioral patterns detection");
      setDescription("Select the trace graph file, the annotations file,"
            + " the behavioral patterns file, and the output file.");
      setPageComplete(false);
   }


   /**
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
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
      Label annotationsFileLabel = new Label(composite, SWT.LEFT);
      annotationsFileLabel.setText("Choose a structural annotations file:");
      annotationsFileLabel.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.annotationsFileText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      this.annotationsFileText.addModifyListener(this);
      this.annotationsFileText.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.annotationsFileButton = new Button(composite, SWT.PUSH);
      this.annotationsFileButton.setText("Browse...");
      this.annotationsFileButton.addSelectionListener(this);
      this.annotationsFileButton.setLayoutData(gridData);

      this.annotationsFileDialog = new WorkbenchFileTreeSelectionDialog(getShell(), "Annotations File",
            "Select a file with annotations", "psa");

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      Label traceFileLabel = new Label(composite, SWT.LEFT);
      traceFileLabel.setText("Choose a trace file:");
      traceFileLabel.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.traceFileText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      this.traceFileText.addModifyListener(this);
      this.traceFileText.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.traceFileButton = new Button(composite, SWT.PUSH);
      this.traceFileButton.setText("Browse...");
      this.traceFileButton.addSelectionListener(this);
      this.traceFileButton.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      Label catalogFileLabel = new Label(composite, SWT.LEFT);
      catalogFileLabel.setText("Choose a behavioral patterns catalog file:");
      catalogFileLabel.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.catalogFileText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      this.catalogFileText.addModifyListener(this);
      this.catalogFileText.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.catalogFileButton = new Button(composite, SWT.PUSH);
      this.catalogFileButton.setText("Browse...");
      this.catalogFileButton.addSelectionListener(this);
      this.catalogFileButton.setLayoutData(gridData);

      Label separatorLabel = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.horizontalSpan = 2;
      gridData.verticalIndent = 8;
      separatorLabel.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      Label resultFileLabel = new Label(composite, SWT.LEFT);
      resultFileLabel.setText("Choose a result file:");
      resultFileLabel.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.resultFileText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      this.resultFileText.addModifyListener(this);
      this.resultFileText.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.resultFileButton = new Button(composite, SWT.PUSH);
      this.resultFileButton.setText("Browse...");
      this.resultFileButton.addSelectionListener(this);
      this.resultFileButton.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      gridData.verticalIndent = 5;
      this.logTracesButton = new Button(composite, SWT.CHECK);
      this.logTracesButton.setText("Log matched traces");
      this.logTracesButton.addSelectionListener(this);
      this.logTracesButton.setSelection(true);
      this.logTracesButton.setLayoutData(gridData);

      initialize();

      setControl(composite);
   }


   private void initialize()
   {
      IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         String annotationsFile = settings.get(ANNOTATIONS_FILE);
         if (annotationsFile != null)
         {
            this.annotationsFileText.setText(annotationsFile);
         }
         String traceFile = settings.get(TRACE_FILE);
         if (traceFile != null)
         {
            this.traceFileText.setText(traceFile);
         }
         String catalogFile = settings.get(CATALOG_FILE);
         if (catalogFile != null)
         {
            this.catalogFileText.setText(catalogFile);
         }
         String resultFile = settings.get(RESULT_FILE);
         if (resultFile != null)
         {
            this.resultFileText.setText(resultFile);
         }
         boolean logTraces = settings.getBoolean(LOG_TRACES);
         this.logTracesButton.setSelection(logTraces);
      }
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetDefaultSelected(SelectionEvent e)
   {
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetSelected(SelectionEvent event)
   {
      Object source = event.getSource();
      if (source == this.annotationsFileButton)
      {
         if (this.annotationsFileDialog.open() == Window.OK)
         {
            IResource resource = (IResource) this.annotationsFileDialog.getFirstResult();
            this.annotationsFileText.setText(resource.getFullPath().toString());
         }
      }
      else if (source == this.traceFileButton)
      {
         openFileDialog("Select a trace file", ITraceConstants.FILE_EXTENSIONS, ITraceConstants.FILE_DESCRIPTIONS,
               this.traceFileText);
      }
      else if (source == this.catalogFileButton)
      {
         openFileDialog("Select a behavioral patterns catalog file", new String[] { "*.xml" },
               new String[] { "Behavioral Patterns Catalog" }, this.catalogFileText);
      }
      else if (source == this.resultFileButton)
      {
         openFileDialog("Select a behavioral analysis result file", new String[] { "*.xml" },
               new String[] { "Behavioral Analysis Result" }, this.resultFileText);
      }

      checkPageComplete();
   }


   /**
    * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
    */
   public void modifyText(ModifyEvent event)
   {
      Object source = event.getSource();
      if (source == this.annotationsFileText)
      {
         if (!checkFileName(this.annotationsFileText.getText()))
         {
            setErrorMessage("Please select a valid structural annotations file.");
            setPageComplete(false);
            return;
         }
      }
      else if (source == this.traceFileText)
      {
         if (!checkFileName(this.traceFileText.getText()))
         {
            setErrorMessage("Please select a valid trace file.");
            setPageComplete(false);
            return;
         }
      }
      else if (source == this.catalogFileText)
      {
         if (!checkFileName(this.catalogFileText.getText()))
         {
            setErrorMessage("Please select a valid behavioral patterns catalog file.");
            setPageComplete(false);
            return;
         }
      }

      setErrorMessage(null);
      checkPageComplete();
   }


   /* package */String getStructuralAnnotationsFileName()
   {
      return this.annotationsFileText.getText();
   }


   /* package */String getTraceFileName()
   {
      return this.traceFileText.getText();
   }


   /* package */String getBehavioralPatternsCatalogFileName()
   {
      return this.catalogFileText.getText();
   }


   /* package */String getBehavioralAnalysisResultFileName()
   {
      return this.resultFileText.getText();
   }


   /* package */boolean isLogTraces()
   {
      return this.logTracesButton.getSelection();
   }


   /* package */void finish()
   {
      IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         settings.put(ANNOTATIONS_FILE, getStructuralAnnotationsFileName());
         settings.put(TRACE_FILE, getTraceFileName());
         settings.put(CATALOG_FILE, getBehavioralPatternsCatalogFileName());
         settings.put(RESULT_FILE, getBehavioralAnalysisResultFileName());
         settings.put(LOG_TRACES, isLogTraces());
      }
   }


   private void openFileDialog(String dialogText, String[] extensions, String[] filterNames, Text text)
   {
      FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
      dialog.setFilterExtensions(extensions);
      dialog.setText(dialogText);
      dialog.setFilterNames(filterNames);
      String filePath = dialog.open();
      if (filePath != null)
      {
         text.setText(filePath);
      }
   }


   private boolean checkFileName(String fileName)
   {
      File file = new File(fileName);
      return file.isFile();
   }


   private void checkPageComplete()
   {
      setPageComplete(false);
      if ((!this.annotationsFileText.getText().trim().equals("")) && (!this.traceFileText.getText().trim().equals(""))
            && (!this.catalogFileText.getText().trim().equals(""))
            && (!this.resultFileText.getText().trim().equals("")))
      {
         setPageComplete(true);
      }
   }

}
