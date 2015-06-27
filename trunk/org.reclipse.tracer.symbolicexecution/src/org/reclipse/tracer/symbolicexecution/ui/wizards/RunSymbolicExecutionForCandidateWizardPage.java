package org.reclipse.tracer.symbolicexecution.ui.wizards;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.fujaba.commons.ui.WorkbenchFileTreeSelectionDialog;
import org.reclipse.behavior.generator.CatalogGenerator;
import org.reclipse.behavior.inference.BehavioralPatternEntry;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.reclipse.behavior.inference.Trigger;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.tracer.model.definition.AbstractTraceClass;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionWriter;
import org.reclipse.tracer.symbolicexecution.ui.util.SymbolicMethodStringCreator;
import org.reclipse.tracer.symbolicexecution.util.RunSymbolicExecutionForCandidateTraceDefinitionGenerator;

import de.fzi.gast.functions.Method;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class RunSymbolicExecutionForCandidateWizardPage extends WizardPage
      implements SelectionListener, ModifyListener
{

   private org.eclipse.swt.widgets.List classpathList;

   private Text catalogText;

   private Text mainClassText;

   private Text symbolicMethodText;

   private Text programArgumentsText;

   private Button browseCatalogFileButton;

   private Button browseMainFileButton;

   private BehavioralPatternEntry behavioralPatternEntry;

   private String symbolicMethodString;

   private String mainClassName;

   private TraceDefinition traceDefinition;

   private Button removeButton;

   private Button addButton;

   private Button addJarButton;

   private ASGAnnotation annotation;

   private BehavioralPatternsCatalog behavioralPatternsCatalog;

   private List<BehavioralPattern> patterns;

   private BPCatalog catalog;

   private IType mainClass;


   public String getMainClassName()
   {
      return this.mainClassName;
   }


   public BPCatalog getCatalog()
   {
      if (this.catalog == null)
      {

         if (this.catalogText.getText().isEmpty())
         {
            return null;
         }
         URI catalogFileUri = URI.createPlatformResourceURI(
               this.catalogText.getText(), true);
         ResourceSet catalogRess = new ResourceSetImpl();
         Resource catalogRes = catalogRess.getResource(catalogFileUri, true);

         this.catalog = (BPCatalog) catalogRes.getContents().get(0);
      }
      return this.catalog;
   }


   protected RunSymbolicExecutionForCandidateWizardPage(String pageName,
         ASGAnnotation anno)
   {
      super(pageName);
      this.setTitle("Run Symbolic Execution For Candidate");
      this.setDescription("Set the main class and the classpaths for "
            + "the program under analysis, \nto launch a symbolic execution for the selected candidate.");
      this.annotation = anno;
   }


   public void createControl(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 1;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      Composite catalogComposite = createSelectCatalogSection(composite);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      catalogComposite.setLayoutData(gridData);

      Composite mainClassComposite = createSelectMainClassSection(composite);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      mainClassComposite.setLayoutData(gridData);

      Composite classPathComposite = createClassPathComponent(composite);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      classPathComposite.setLayoutData(gridData);

      Composite programArgumentsComposite = createProgramArgumentsSection(composite);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      programArgumentsComposite.setLayoutData(gridData);

      Composite symbolicMethodComposite = createSymbolicMethodSection(composite);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      symbolicMethodComposite.setLayoutData(gridData);

      setControl(mainClassComposite);
      Dialog.applyDialogFont(parent);

      initializeData();
   }


   private void initializeData()
   {
      loadBehavioralPattern();
      if (this.mainClass != null)
      {
         loadTraceDefinition();
         calculateSymbolicMethodString();
      }
   }


   private BehavioralPatternsCatalog findBehavioralPatternsCatalog()
   {
      if (getCatalog() != null)
      {
         List<BehavioralPattern> filteredPatterns = new ArrayList<BehavioralPattern>();
         List<BehavioralPattern> patterns = getCatalog()
               .getBehavioralPatterns();
         for (BehavioralPattern behavioralPattern : patterns)
         {
            if (behavioralPattern.getPsPatternSpecification().getName()
                  .equals(this.annotation.getPattern().getName()))
            {
               filteredPatterns.add(behavioralPattern);
            }
         }
         this.patterns = filteredPatterns;
         return CatalogGenerator.generateCatalog(this.patterns);
      }
      return null;
   }


   private Composite createClassPathComponent(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 1;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      Label label = new Label(composite, SWT.NONE);
      label.setText("Add classpaths:");

      gridData = new GridData();
      // gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      Composite composite2 = new Composite(composite, SWT.NONE);
      gridLayout = new GridLayout();
      gridLayout.numColumns = 2;
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      composite2.setLayout(gridLayout);

      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      composite2.setLayoutData(gridData);

      this.classpathList = new org.eclipse.swt.widgets.List(composite2,
            SWT.SINGLE | SWT.BORDER);
      this.classpathList.addSelectionListener(this);

      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      this.classpathList.setLayoutData(gridData);

      final Composite buttonComposite = new Composite(composite2, SWT.NONE);
      buttonComposite.setLayout(new FillLayout(SWT.VERTICAL));
      gridData = new GridData();
      gridData.verticalAlignment = SWT.TOP;
      buttonComposite.setLayoutData(gridData);

      this.removeButton = new Button(buttonComposite, SWT.PUSH);
      this.removeButton.setText("Remove");
      this.removeButton.addSelectionListener(this);
      this.removeButton.setEnabled(false);

      this.addButton = new Button(buttonComposite, SWT.PUSH);
      this.addButton.setText("Add Directory...");
      this.addButton.addSelectionListener(this);

      this.addJarButton = new Button(buttonComposite, SWT.PUSH);
      this.addJarButton.setText("Add JARs...");
      this.addJarButton.addSelectionListener(this);

      return composite;
   }


   private Composite createSymbolicMethodSection(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 1;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      Label label = new Label(composite, SWT.NONE);
      label.setText("Symbolic Method:");
      label.setEnabled(false);

      gridData = new GridData();
      // gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      this.symbolicMethodText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      this.symbolicMethodText.setEnabled(false);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.symbolicMethodText.setLayoutData(gridData);

      return composite;
   }


   private Composite createProgramArgumentsSection(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 1;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      Label label = new Label(composite, SWT.NONE);
      label.setText("Program Arguments (optional):");

      gridData = new GridData();
      label.setLayoutData(gridData);

      this.programArgumentsText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.programArgumentsText.setLayoutData(gridData);

      return composite;
   }


   private Composite createSelectMainClassSection(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 1;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      Label label = new Label(composite, SWT.NONE);
      label.setText("Select main class:");

      gridData = new GridData();
      // gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      GridLayout gridLayout2 = new GridLayout();
      gridLayout2.marginHeight = 0;
      gridLayout2.marginWidth = 0;
      gridLayout2.numColumns = 2;

      Composite composite2 = new Composite(composite, SWT.NONE);
      composite2.setFont(parent.getFont());
      composite2.setLayout(gridLayout2);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite2.setLayoutData(gridData);

      this.mainClassText = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      this.mainClassText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.mainClassText.setLayoutData(gridData);

      this.browseMainFileButton = new Button(composite2, SWT.PUSH);
      this.browseMainFileButton.setText("Browse...");
      this.browseMainFileButton.addSelectionListener(this);

      return composite;
   }


   private Composite createSelectCatalogSection(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 1;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      Label label = new Label(composite, SWT.NONE);
      label.setText("Select behavioral patterns catalog:");

      gridData = new GridData();
      // gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      GridLayout gridLayout2 = new GridLayout();
      gridLayout2.marginHeight = 0;
      gridLayout2.marginWidth = 0;
      gridLayout2.numColumns = 2;

      Composite composite2 = new Composite(composite, SWT.NONE);
      composite2.setFont(parent.getFont());
      composite2.setLayout(gridLayout2);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite2.setLayoutData(gridData);

      this.catalogText = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      this.catalogText.addModifyListener(this);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.catalogText.setLayoutData(gridData);

      this.browseCatalogFileButton = new Button(composite2, SWT.PUSH);
      this.browseCatalogFileButton.setText("Browse...");
      this.browseCatalogFileButton.addSelectionListener(this);

      return composite;
   }


   public void widgetSelected(SelectionEvent e)
   {
      Object source = e.getSource();
      if (source == this.browseMainFileButton)
      {
         handleMainFileButtonSelected();
         initializeData();
      }
      else if (source == this.addButton)
      {
         handleAddButtonSelected();
      }
      else if (source == this.addJarButton)
      {
         handleAddJarButtonSelected();
      }
      else if (source == this.removeButton)
      {
         handleRemoveButtonSelected();
      }
      else if (source == this.browseCatalogFileButton)
      {
         handleCatalogFileButtonSelected();
         initializeData();
      }

      checkPageComplete();
   }


   private void handleCatalogFileButtonSelected()
   {
      ElementTreeSelectionDialog dialog = new WorkbenchFileTreeSelectionDialog(
            getShell(), "Behavioral Patterns Catalog",
            "Select the behavioral patterns catalog", "bp");
      if (dialog.open() == Window.OK)
      {
         IResource resource = (IResource) dialog.getFirstResult();
         this.catalogText.setText(resource.getFullPath().toString());
      }
   }


   private void handleMainFileButtonSelected()
   {
      IJavaSearchScope scope = getProjectSearchScope();
      SelectionDialog dialog = JavaUI.createMainTypeDialog(getShell(), this
            .getWizard().getContainer(), scope, 0, false);
      dialog.setMessage("Select the class that contains the main method to start the execution of the program:");
      int result = dialog.open();
      if (result == Window.OK)
      {
         this.mainClass = (IType) dialog.getResult()[0];
         this.mainClassName = "";
         if (this.mainClass.getPackageFragment().getElementName().length() > 0)
         {
            this.mainClassName += this.mainClass.getPackageFragment()
                  .getElementName();
            this.mainClassName += ".";
         }
         this.mainClassName += this.mainClass.getElementName();
         this.mainClassText.setText(this.mainClassName);
      }
   }


   private void handleAddButtonSelected()
   {
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot workspaceDirectory = workspace.getRoot();
      final DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.SAVE);
      dialog.setFilterPath(workspaceDirectory.getLocation().toString());
      dialog.setMessage("Select a classpath directory:");
      final String directory = dialog.open();

      if (directory != null)
      {
         this.classpathList.add(directory);
         File file = new File(directory);
         File[] files = file.listFiles();
         addSubdirectories(files);
      }
   }


   private void addSubdirectories(File[] files)
   {
      for (int i = 0; i < files.length; i++)
      {
         if (files[i].isDirectory() && !files[i].isHidden())
         {
            this.classpathList.add(files[i].getAbsolutePath());
            addSubdirectories(files[i].listFiles());
         }
      }
   }


   private void handleAddJarButtonSelected()
   {
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot workspaceDirectory = workspace.getRoot();
      final FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
      dialog.setText("Select a Jar file:");
      dialog.setFilterExtensions(new String[] { "*.jar" });
      dialog.setFilterNames(new String[] { "Jar files" });
      dialog.setFilterPath(workspaceDirectory.getLocation().toString());
      final String jarFile = dialog.open();
      if (jarFile != null)
      {
         this.classpathList.add(jarFile);
      }
   }


   private void handleRemoveButtonSelected()
   {
      final int selectedIndex = this.classpathList.getSelectionIndex();
      if (selectedIndex > -1)
      {
         this.classpathList.remove(selectedIndex);
      }
   }


   private void loadTraceDefinition()
   {
      List<ASGAnnotation> annos = new ArrayList<ASGAnnotation>();
      annos.add(this.annotation);
      if (this.patterns != null)
      {
         RunSymbolicExecutionForCandidateTraceDefinitionGenerator traceDefGenerator = new RunSymbolicExecutionForCandidateTraceDefinitionGenerator(
               this.patterns, annos);
         traceDefGenerator.setASGAnnotation(this.annotation);
         try
         {
            this.traceDefinition = traceDefGenerator.generateTraceDefinition();
         }
         catch (FileNotFoundException e)
         {
            e.printStackTrace();
         }
      }
   }


   public void widgetDefaultSelected(SelectionEvent e)
   {
   }


   private void checkPageComplete()
   {
      setPageComplete(false);

      if (this.mainClassText.getText().equals(""))
      {
         setErrorMessage("Please provide a main class.");
         return;
      }
      else if (this.classpathList.getItemCount() == 0)
      {
         setErrorMessage("Please provide a at least one classpath.");
         return;
      }

      setPageComplete(true);
      setErrorMessage(null);
   }


   /**
    * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
    */
   public void modifyText(ModifyEvent e)
   {
      checkPageComplete();
   }


   /**
    * Takes the behavioralPatternEntry and calculates the symbolic method string by using the method
    * that invokes the trigger method from the behavioral pattern.
    */
   private void calculateSymbolicMethodString()
   {
      StringBuffer symbolicMethodsString = new StringBuffer();
      if (this.behavioralPatternEntry != null && this.traceDefinition != null)
      {
         Iterator<Trigger> triggers = this.behavioralPatternEntry
               .iteratorOfTriggers();
         while (triggers.hasNext())
         {
            Trigger trigger = (Trigger) triggers.next();
            String triggerMethod = trigger.getMethodName();
            SymbolicMethodStringCreator stringCreator = new SymbolicMethodStringCreator(
                  getJavaProject());
            IMethod mainMethod = getMainMethod();
            // String symbolicMethod = mainMethod.getElementName()+"(sym)"; // stringCreator
            String symbolicMethod = stringCreator
                  .addSymbolicMethodStringForTrigger(triggerMethod,
                        getActualMethod(triggerMethod),
                        getMethodsFromTraceDefinition(),
                        getProjectSearchScope());
            // filter duplicate methods
            if (!symbolicMethodsString.toString().contains(symbolicMethod))
            {
               symbolicMethodsString.append(symbolicMethod);
               symbolicMethodsString.append(",");
            }
         }
         this.symbolicMethodString = symbolicMethodsString.toString();
         if (this.symbolicMethodString.length() > 0)
         {
            this.symbolicMethodString = this.symbolicMethodString.substring(0,
                  this.symbolicMethodString.length() - 1);
         }
         this.symbolicMethodText.setText(this.symbolicMethodString);
      }
   }


   private List<Method> getMethodsFromTraceDefinition()
   {
      List<Method> methodsList = new ArrayList<Method>();
      Iterator<AbstractTraceClass> classes = this.traceDefinition
            .getConsiderTrace().iteratorOfClasses();
      while (classes.hasNext())
      {
         AbstractTraceClass abstractTraceClass = (AbstractTraceClass) classes
               .next();
         if (abstractTraceClass instanceof ConsiderClass)
         {
            ConsiderClass considerClass = (ConsiderClass) abstractTraceClass;
            Iterator<ConsiderMethod> methods = considerClass
                  .iteratorOfMethods();
            while (methods.hasNext())
            {
               ConsiderMethod considerMethod = methods.next();
               Method actualMethod = getActualMethod(considerMethod);
               methodsList.add(actualMethod);
            }
         }
      }
      return methodsList;
   }


   private Method getActualMethod(ConsiderMethod considerMethod)
   {
      Set<Entry<String, EList<EObject>>> entrySet = this.annotation
            .getBoundObjects().entrySet();
      for (Entry<String, EList<EObject>> entry : entrySet)
      {
         EObject object = entry.getValue().get(0);
         if (object instanceof Method)
         {
            Method method = (Method) object;
            if (method.getSimpleName().equals(considerMethod.getName()))
            {
               return getActualMethod(entry.getKey());
            }
         }
      }
      return null;
   }


   private Method getActualMethod(String method)
   {
      EObject actualMethod = this.annotation.getBoundObjects().get(method)
            .get(0);
      return (Method) actualMethod;
   }


   private IMethod getMainMethod()
   {
      try
      {
         for (IMethod iMethod : this.mainClass.getMethods())
         {
            if (iMethod.isMainMethod())
            {
               return iMethod;
            }
         }
      }
      catch (JavaModelException e)
      {
         e.printStackTrace();
      }
      return null;
   }


   // private List<AMethodDeclaration> getMethodsFromTraceDefinition()
   // {
   // List<AMethodDeclaration> methodsList = new ArrayList<AMethodDeclaration>();
   // Iterator<AbstractTraceClass> classes = this.traceDefinition
   // .getConsiderTrace().iteratorOfClasses();
   // while (classes.hasNext())
   // {
   // AbstractTraceClass abstractTraceClass = (AbstractTraceClass) classes
   // .next();
   // if (abstractTraceClass instanceof ConsiderClass)
   // {
   // ConsiderClass considerClass = (ConsiderClass) abstractTraceClass;
   // Iterator<ConsiderMethod> methods = considerClass
   // .iteratorOfMethods();
   // while (methods.hasNext())
   // {
   // ConsiderMethod considerMethod = methods.next();
   // AMethodDeclaration actualMethod = getActualMethod(considerMethod);
   // methodsList.add(actualMethod);
   // }
   // }
   // }
   // return methodsList;
   // }


   /**
    * Returns the method from the selected candidate that is bound to the given (trigger-) method.
    * 
    * @param method
    * @return AMethodDeclaration
    */
   // private AMethodDeclaration getActualMethod(String method)
   // {
   // Iterator<FElement> boundObjectsIter = this.annotation
   // .iteratorOfBoundObjects(method);
   // while (boundObjectsIter.hasNext())
   // {
   // FElement fElement = (FElement) boundObjectsIter.next();
   // return (AMethodDeclaration) fElement;
   // }
   // return null;
   // }


   /**
    * Returns the method from the selected candidate that is bound to the given consider method.
    * 
    * @param method
    * @return AMethodDeclaration
    */
   // private AMethodDeclaration getActualMethod(ConsiderMethod method)
   // {
   // Iterator<FElement> boundObjectsIter = this.annotation
   // .iteratorOfBoundObjects();
   // while (boundObjectsIter.hasNext())
   // {
   // FElement fElement = (FElement) boundObjectsIter.next();
   // if (fElement instanceof AMethodDeclaration
   // && fElement.getName().equals(method.getName()))
   // {
   // return (AMethodDeclaration) fElement;
   // }
   // }
   // return null;
   // }


   /**
    * Loads the behavioral patterns catalog from the given catalogFile and scans it for the
    * BehavioralPatternEntry that corresponds to the selected candidate.
    * 
    * @param catalogFile
    */
   private void loadBehavioralPattern()
   {
      this.patterns = null; // TODO
      this.behavioralPatternsCatalog = findBehavioralPatternsCatalog();
      if (this.behavioralPatternsCatalog != null)
      {
         Iterator<BehavioralPatternEntry> entries = this.behavioralPatternsCatalog
               .iteratorOfEntries();
         while (entries.hasNext())
         {
            BehavioralPatternEntry behavioralPatternEntry = (BehavioralPatternEntry) entries
                  .next();
            if (behavioralPatternEntry.getName().equals(
                  this.annotation.getPattern().getName()))
            {
               this.behavioralPatternEntry = behavioralPatternEntry;
            }
         }
      }
   }


   public String getTraceDefinitionString()
   {
      TraceDefinitionWriter tdWriter = new TraceDefinitionWriter(
            this.traceDefinition);
      StringWriter writer = new StringWriter();
      BufferedWriter bufferedWriter = new BufferedWriter(writer);
      try
      {
         tdWriter.generateXMLDocument(bufferedWriter);
         bufferedWriter.flush();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return writer.toString();
   }


   public String getSymbolicMethodString()
   {
      return this.symbolicMethodString;
   }


   public List<String> getClassPaths()
   {
      List<String> arrayList = new ArrayList<String>();
      String[] listItems = this.classpathList.getItems();
      for (int i = 0; i < listItems.length; i++)
      {
         arrayList.add(listItems[i]);
      }
      return arrayList;
   }


   protected IJavaSearchScope getProjectSearchScope()
   {
      IJavaProject javaProject = getJavaProject();
      if (javaProject != null)
      {
         IJavaElement[] elements = { javaProject };
         IJavaSearchScope scope = SearchEngine.createJavaSearchScope(elements);
         return scope;
      }
      return SearchEngine.createWorkspaceScope();
   }


   private IJavaProject getJavaProject()
   {
      if (this.mainClass != null)
      {
         return this.mainClass.getJavaProject();
      }
      return null;
   }


   public String getProgramArguments()
   {
      return this.programArgumentsText.getText();
   }


   public BehavioralPatternsCatalog getBehavioralPatternsCatalog()
   {
      return this.behavioralPatternsCatalog;
   }


   public ASGAnnotation getCurrentAnnotation()
   {
      return this.annotation;
   }

}
