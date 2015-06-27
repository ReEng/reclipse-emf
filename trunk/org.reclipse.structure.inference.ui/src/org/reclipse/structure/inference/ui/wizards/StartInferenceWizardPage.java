package org.reclipse.structure.inference.ui.wizards;


import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.fujaba.commons.console.ReportLevel;
import org.fujaba.commons.ui.WorkbenchFileTreeSelectionDialog;
import org.fujaba.commons.utils.IDUsingResourceFactory;
import org.reclipse.structure.inference.IAnnotationEvaluator;
import org.reclipse.structure.inference.util.InferenceExtensionsHelper;
import org.reclipse.structure.inference.util.InferenceExtensionsHelper.AnnotationEvaluatorItem;


/**
 * This wizard page is used to configure all relevant data to run a structural patterns detection.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class StartInferenceWizardPage extends WizardPage implements ModifyListener, SelectionListener
{

   // settings keys
   private static final String SETT_CATALOG = "catalog"; //$NON-NLS-1$

   private static final String SETT_ROOT = "root"; //$NON-NLS-1$

   private static final String SETT_USEENGINES = "use_engines"; //$NON-NLS-1$

   private static final String SETT_ENGINES = "engines"; //$NON-NLS-1$

   private static final String SETT_EVALUATOR = "evaluator"; //$NON-NLS-1$

   private static final String SETT_REPORTING = "reporting"; //$NON-NLS-1$

   private static final String SETT_ADDITIONALS = "additionals"; //$NON-NLS-1$

   // ui representators
   private Text textCatalog;

   private Text textHostGraph;

   private Text textEngines;

   private Combo selectorEvaluators;

   private Label evaluatorDescription;

   private Combo selectorReporting;

   private Label reportingDescription;

   private Button additionals;

   private Button useExistingEngines;

   private Button browseEngines;

   // real elements
   private Resource catalogResource;

   private Resource hostGraphResource;

   private Resource enginesResource;

   private AnnotationEvaluatorItem evaluator;

   // other stuff
   private ElementTreeSelectionDialog dialogCatalog;

   private ElementTreeSelectionDialog dialogCode;

   private ElementTreeSelectionDialog dialogEngines;

   private List<AnnotationEvaluatorItem> evaluators;

   private ResourceSet resourceSet = new ResourceSetImpl();


   protected StartInferenceWizardPage(String name)
   {
      super(name);
      setTitle("Structural Pattern Detection");
      setDescription("Select the input resources and the settings to start a structural pattern detection.");
   }


   public void initializeResourceSet()
   {
      resourceSet = new ResourceSetImpl();
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
            .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new IDUsingResourceFactory());
   }


   public boolean isSearchForAdditionals()
   {
      return additionals.getSelection();
   }


   public Resource getCatalogResource()
   {
      if (catalogResource == null)
      {
         if (textCatalog.getText() != null && textCatalog.getText().length() > 0)
         {
            catalogResource = resourceSet.getResource(URI.createPlatformResourceURI(textCatalog.getText(), true), true);
         }
         else
         {
            catalogResource = null;
         }
      }
      return catalogResource;
   }


   public Resource getHostGraphResource()
   {
      if (hostGraphResource == null)
      {
         if (textHostGraph.getText() != null && textHostGraph.getText().length() > 0)
         {
            try
            {
               hostGraphResource = resourceSet.getResource(
                     URI.createPlatformResourceURI(textHostGraph.getText(), true), true);
            }
            catch (WrappedException e)
            {
               e.printStackTrace();
               hostGraphResource = null;
            }
         }
         else
         {
            hostGraphResource = null;
         }
      }
      return hostGraphResource;
   }


   public Resource getEnginesResource()
   {
      if (enginesResource == null)
      {
         if (useExistingEngines.getSelection() && textEngines.getText() != null && textEngines.getText().length() > 0)
         {
            enginesResource = resourceSet.getResource(URI.createPlatformResourceURI(textEngines.getText(), true), true);
         }
         else
         {
            enginesResource = resourceSet.createResource(URI.createPlatformResourceURI(
                  textCatalog.getText() + ".ecore", true));
         }
      }
      return enginesResource;
   }


   public IAnnotationEvaluator getEvaluator()
   {
      return evaluator.getEvaluator();
   }


   public ReportLevel getReportLevel()
   {
      ReportLevel[] values = ReportLevel.values();
      return values[selectorReporting.getSelectionIndex()];
   }


   public void saveSettings()
   {
      IDialogSettings s = getDialogSettings();
      if (s != null)
      {
         s.put(SETT_CATALOG, textCatalog.getText());
         s.put(SETT_ROOT, textHostGraph.getText());
         s.put(SETT_USEENGINES, isUseExistingEngines());
         s.put(SETT_ENGINES, textEngines.getText());
         s.put(SETT_EVALUATOR, evaluator == null ? null : evaluator.getID());
         s.put(SETT_ADDITIONALS, isSearchForAdditionals());
         s.put(SETT_REPORTING, selectorReporting.getSelectionIndex());
      }
   }


   protected String getHostGraphFileExt()
   {
      return "sourcecodedecorator";
   }


   @Override
   public void createControl(Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(new GridLayout());

      configureDialogs();

      evaluators = InferenceExtensionsHelper.getRegisteredEvaluators();

      Group inputs = new Group(composite, SWT.SHADOW_IN);
      inputs.setText("Input");
      inputs.setLayout(new GridLayout(3, false));
      inputs.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

      String tooltip = "A catalog file has a \".psc\" extension";
      textCatalog = addLabeledResourceSelection(inputs, "Pattern Specifications:", tooltip, dialogCatalog);

      tooltip = "A host graph model file has a \"." + getHostGraphFileExt() + "\" extension";
      textHostGraph = addLabeledResourceSelection(inputs, "Host Graph Model:", tooltip, dialogCode);

      // search engines
      tooltip = "Check to use some specific generated search engines (\".ecore\")."
            + "\nOtherwise, engines will be generated from the selected pattern catalog."
            + "\nThe search engines will be stored in a file <catalog name>.ecore in the the catalog's parent folder.";
      useExistingEngines = new Button(inputs, SWT.CHECK);
      useExistingEngines.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
      useExistingEngines.setText("Search Engines:");
      useExistingEngines.setToolTipText(tooltip);

      textEngines = new Text(inputs, SWT.BORDER);
      textEngines.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
      textEngines.addModifyListener(this);
      textEngines.setToolTipText(tooltip);

      browseEngines = new Button(inputs, SWT.PUSH);
      browseEngines.setText("Select...");
      browseEngines.setToolTipText(tooltip);
      browseEngines.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            if (dialogEngines.open() == Window.OK)
            {
               IResource resource = (IResource) dialogEngines.getFirstResult();
               textEngines.setText(resource.getFullPath().toString());
            }
         }
      });

      useExistingEngines.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            textEngines.setEnabled(useExistingEngines.getSelection());
            browseEngines.setEnabled(useExistingEngines.getSelection());
         }
      });

      // evaluation
      tooltip = "Annotations are evaluated by the chosen evaluator.\nThe resulting rating indicates how many of a patterns conditions could be matched.";
      Group evaluation = new Group(composite, SWT.SHADOW_IN);
      evaluation.setText("Evaluation");
      evaluation.setLayout(new GridLayout(2, false));
      evaluation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
      evaluation.setToolTipText(tooltip);

      Label evaluationName = new Label(evaluation, SWT.LEFT);
      evaluationName.setText("Annotation Evaluator:");
      evaluationName.setToolTipText(tooltip);

      selectorEvaluators = new Combo(evaluation, SWT.READ_ONLY | SWT.DROP_DOWN);
      selectorEvaluators.setItems(getEvaluatorNames(""));
      selectorEvaluators.addSelectionListener(this);
      selectorEvaluators.setToolTipText(tooltip);

      Composite evaluatorDesc = new Composite(evaluation, SWT.NONE);
      evaluatorDesc.setLayout(new GridLayout(2, false));
      evaluatorDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 0));
      evaluatorDesc.setToolTipText(tooltip);

      Label tmp = new Label(evaluatorDesc, SWT.LEFT);
      tmp.setImage(Dialog.getImage(Dialog.DLG_IMG_MESSAGE_INFO));
      tmp.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
      tmp.setToolTipText(tooltip);

      evaluatorDescription = new Label(evaluatorDesc, SWT.LEFT | SWT.WRAP);
      evaluatorDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
      evaluatorDescription.setToolTipText(tooltip);

      // reporting
      tooltip = "Choose a level of detail for informations, which will be provided to you during the matching process.";
      Group reporting = new Group(composite, SWT.SHADOW_IN);
      reporting.setText("Reporting");
      reporting.setLayout(new GridLayout(2, false));
      reporting.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
      reporting.setToolTipText(tooltip);

      Label reportLeveLabel = new Label(reporting, SWT.NONE);
      reportLeveLabel.setText("Report Level:");
      reportLeveLabel.setToolTipText(tooltip);

      selectorReporting = new Combo(reporting, SWT.READ_ONLY | SWT.DROP_DOWN);
      selectorReporting.setItems(ReportLevel.getItems());
      selectorReporting.addSelectionListener(this);
      selectorReporting.setToolTipText(tooltip);

      Composite reportingDesc = new Composite(reporting, SWT.NONE);
      reportingDesc.setLayout(new GridLayout(2, false));
      reportingDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 0));
      reportingDesc.setToolTipText(tooltip);

      tmp = new Label(reportingDesc, SWT.LEFT);
      tmp.setImage(Dialog.getImage(Dialog.DLG_IMG_MESSAGE_INFO));
      tmp.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
      tmp.setToolTipText(tooltip);
      reportingDescription = new Label(reportingDesc, SWT.LEFT | SWT.WRAP);
      reportingDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
      reportingDescription.setToolTipText(tooltip);

      // search additionals
      tooltip = "Some patterns may have parts, which do need not to be matched necessarily."
            + "\nUncheck to avoid matching of those parts. Unchecking this also accelerates the matching process.";
      additionals = new Button(composite, SWT.CHECK);
      additionals.setText("Search for additional elements");
      additionals.setToolTipText(tooltip);

      setControl(composite);

      setPageComplete(false);

      loadSettings();
   }


   @Override
   public void modifyText(ModifyEvent e)
   {
      if (e.getSource() == textCatalog)
      {
         dialogCatalog.setInitialSelection(ResourcesPlugin.getWorkspace().getRoot().findMember(textCatalog.getText()));
      }
      else if (e.getSource() == textHostGraph)
      {
         dialogCode.setInitialSelection(ResourcesPlugin.getWorkspace().getRoot().findMember(textHostGraph.getText()));
      }
      else if (e.getSource() == textEngines)
      {
         dialogEngines.setInitialSelection(ResourcesPlugin.getWorkspace().getRoot().findMember(textEngines.getText()));
      }

      setPageComplete(isValid());
   }


   @Override
   public void widgetSelected(SelectionEvent e)
   {
      widgetDefaultSelected(e);
   }


   @Override
   public void widgetDefaultSelected(SelectionEvent e)
   {
      checkSelection(e.getSource());

      setPageComplete(isValid());
   }


   private void loadSettings()
   {
      IDialogSettings s = getDialogSettings();
      if (s != null)
      {
         textCatalog.setText(s.get(SETT_CATALOG) == null ? "" : s.get(SETT_CATALOG));
         textHostGraph.setText(s.get(SETT_ROOT) == null ? "" : s.get(SETT_ROOT));
         useExistingEngines.setSelection(s.getBoolean(SETT_USEENGINES));
         textEngines.setEnabled(useExistingEngines.getSelection());
         browseEngines.setEnabled(useExistingEngines.getSelection());
         textEngines.setText(s.get(SETT_ENGINES) == null ? "" : s.get(SETT_ENGINES));

         selectorEvaluators.select(getEvaluatorIndex(s.get(SETT_EVALUATOR)));
         additionals.setSelection(s.getBoolean(SETT_ADDITIONALS));
         selectorReporting.select(s.get(SETT_REPORTING) == null ? 2 : s.getInt(SETT_REPORTING));

         checkSelection(selectorEvaluators);
         checkSelection(selectorReporting);
      }
   }


   private int getEvaluatorIndex(String id)
   {
      for (int i = 0; i < evaluators.size(); i++)
      {
         if (evaluators.get(i).getID().equals(id))
         {
            return i;
         }
      }

      return 0;
   }


   private String[] getEvaluatorNames(String model)
   {
      String[] result = new String[evaluators.size()];
      for (int i = 0; i < result.length; i++)
      {
         result[i] = evaluators.get(i).getName();
      }

      return result;
   }


   private void configureDialogs()
   {
      dialogCatalog = new WorkbenchFileTreeSelectionDialog(getShell(), "Pattern Specification",
            "Select a structural pattern specification", "psc");

      dialogCode = new WorkbenchFileTreeSelectionDialog(getShell(), "Host Graph Model",
            "Select the resource containing the host graph to analyze.", getHostGraphFileExt());

      dialogEngines = new WorkbenchFileTreeSelectionDialog(getShell(), "Search Engines",
            "Select the resource containing the generated pattern search engines.", "ecore");
   }


   private boolean isValid()
   {
      if (textCatalog.getText() == null)
      {
         setErrorMessage("A resource with the Pattern Specification Catalog has to be given.");
      }

      if (textHostGraph.getText() == null)
      {
         setErrorMessage("A resource with the parsed Source Code has to be given.");
      }

      if (textEngines.getText() == null)
      {
         setErrorMessage("A resource with the generated Story Engines has to be given.");
      }


      if (textCatalog.getText() != null && textHostGraph.getText() != null && textEngines.getText() != null)
      {
         setErrorMessage(null);
         return true;
      }

      return false;
   }


   private void checkSelection(Object source)
   {
      if (source == selectorEvaluators)
      {
         if (!evaluators.isEmpty())
         {
            evaluator = evaluators.get(selectorEvaluators.getSelectionIndex());
            evaluatorDescription.setText(evaluator.getDescription());
         }
         int offset = evaluatorDescription.getSize().y
               - evaluatorDescription.computeSize(evaluatorDescription.getSize().x, -1, true).y;
         resize(evaluatorDescription.getParent().getParent(), offset);
      }
      else if (source == selectorReporting)
      {
         reportingDescription.setText(ReportLevel.getDescription(getReportLevel()));
      }
   }


   private void resize(Composite parent, int offset)
   {
      getShell().setSize(getShell().getSize().x, getShell().getSize().y - offset);
   }


   private Text addLabeledResourceSelection(Composite parent, String label, String tooltip,
         final ElementTreeSelectionDialog dialog)
   {
      Label select = new Label(parent, SWT.LEFT);
      select.setText(label);
      select.setToolTipText(tooltip);

      final Text input = new Text(parent, SWT.BORDER);
      input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
      input.addModifyListener(this);
      input.setToolTipText(tooltip);

      Button browse = new Button(parent, SWT.PUSH);
      browse.setToolTipText(tooltip);
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


   public boolean isUseExistingEngines()
   {
      return useExistingEngines.getSelection();
   }


   public String getCatalogPath()
   {
      return getDialogSettings().get(SETT_CATALOG);
   }


   public String getHostPath()
   {
      return getDialogSettings().get(SETT_ROOT);
   }


   public String getEnginesPath()
   {
      return getDialogSettings().get(SETT_ENGINES);
   }


   /**
    * @return Returns the resourceSet.
    */
   public ResourceSet getResourceSet()
   {
      return this.resourceSet;
   }


   /**
    * @param resourceSet The resourceSet to set.
    */
   public void setResourceSet(ResourceSet resourceSet)
   {
      this.resourceSet = resourceSet;
   }

}
