package org.reclipse.tracedefinition.editor.wizards;


import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.input.StructuralAnnotationsReader;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4129 $ $Date: 2009-08-07 10:30:53 +0200 (Fr, 07 Aug 2009) $
 */
public class AnnotationsWizardPage extends WizardPage implements
      SelectionListener, ISelectionChangedListener
{

   private static final String SPLIT_BY_SIZE = "splitBySize";

   private static final String SPLIT_BY_TYPE = "splitByType";

   private static final String SPLIT_BY_INSTANCE = "splitByInstance";

   private static final String SPLIT_SIZE = "splitSize";

   private static final String ANNOTATION_TYPES = "annotationTypes";


   private Button splitBySizeButton;

   private Spinner splitBySizeSpinner;


   private Button splitByTypeButton;

   private CheckboxTableViewer annotationTypesCheckboxTable;

   private Button selectAnnotationTypesButton;

   private Button deselectAnnotationTypesButton;


   private Button splitByInstanceButton;

   private CheckboxTableViewer annotationInstancesCheckboxTable;

   private Button selectAnnotationInstancesButton;

   private Button deselectAnnotationInstancesButton;

   private Set<StructuralAnnotation> structuralAnnotations; // TODO: was SortedSet


   public AnnotationsWizardPage()
   {
      super("AnnotationsWizardPage");

      setTitle("Specify Trace Definition Splitting");
      setDescription("Specify how to split the trace definition");

      this.structuralAnnotations = new TreeSet<StructuralAnnotation>();

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

      createSplitBySizeControl(composite);
      createSeparatorLabel(composite);
      createSplitByTypeControl(composite);
      createSeparatorLabel(composite);
      createSplitByInstanceControl(composite);

      setControl(composite);
      checkPageComplete();
   }


   private void createSeparatorLabel(final Composite parent)
   {
      final Label label = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);

      final GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);
   }


   private void createSplitBySizeControl(final Composite parent)
   {
      this.splitBySizeButton = new Button(parent, SWT.CHECK);
      this.splitBySizeButton.setText("Split into pieces of same size");
      this.splitBySizeButton.addSelectionListener(this);

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      this.splitBySizeButton.setLayoutData(gridData);

      this.splitBySizeSpinner = new Spinner(parent, SWT.BORDER);
      this.splitBySizeSpinner.setSelection(20);
      this.splitBySizeSpinner.setIncrement(1);
      this.splitBySizeSpinner.setPageIncrement(10);
      this.splitBySizeSpinner.setMinimum(1);

      gridData = new GridData();
      gridData.horizontalIndent = 16;
      gridData.horizontalSpan = 2;
      this.splitBySizeSpinner.setLayoutData(gridData);
   }


   private void createSplitByTypeControl(final Composite parent)
   {
      this.splitByTypeButton = new Button(parent, SWT.CHECK);
      this.splitByTypeButton.setText("Split by annotation type");
      this.splitByTypeButton.addSelectionListener(this);

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      this.splitByTypeButton.setLayoutData(gridData);

      final AnnotationTypesProvider annotationTypesProvider = new AnnotationTypesProvider();
      this.annotationTypesCheckboxTable = CheckboxTableViewer.newCheckList(
            parent, SWT.BORDER);
      this.annotationTypesCheckboxTable.addSelectionChangedListener(this);
      this.annotationTypesCheckboxTable
            .setContentProvider(annotationTypesProvider);
      this.annotationTypesCheckboxTable
            .setLabelProvider(annotationTypesProvider);

      final Table table = this.annotationTypesCheckboxTable.getTable();
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalIndent = 16;
      gridData.verticalSpan = 2;
      table.setLayoutData(gridData);

      this.selectAnnotationTypesButton = new Button(parent, SWT.PUSH);
      this.selectAnnotationTypesButton.setText("Select All");
      this.selectAnnotationTypesButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.selectAnnotationTypesButton.setLayoutData(gridData);

      this.deselectAnnotationTypesButton = new Button(parent, SWT.PUSH);
      this.deselectAnnotationTypesButton.setText("Deselect All");
      this.deselectAnnotationTypesButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.TOP;
      gridData.grabExcessVerticalSpace = true;
      this.deselectAnnotationTypesButton.setLayoutData(gridData);
   }


   private void createSplitByInstanceControl(final Composite parent)
   {
      this.splitByInstanceButton = new Button(parent, SWT.CHECK);
      this.splitByInstanceButton.addSelectionListener(this);
      this.splitByInstanceButton.setText("Split by annotation instance");

      GridData gridData = new GridData();
      gridData.horizontalSpan = 2;
      this.splitByInstanceButton.setLayoutData(gridData);

      final AnnotationInstancesProvider annotationInstancesProvider = new AnnotationInstancesProvider();
      this.annotationInstancesCheckboxTable = CheckboxTableViewer.newCheckList(
            parent, SWT.BORDER);
      this.annotationInstancesCheckboxTable.addSelectionChangedListener(this);
      this.annotationInstancesCheckboxTable
            .setContentProvider(annotationInstancesProvider);
      this.annotationInstancesCheckboxTable
            .setLabelProvider(annotationInstancesProvider);

      final Table table = this.annotationInstancesCheckboxTable.getTable();
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalIndent = 16;
      gridData.verticalSpan = 2;
      table.setLayoutData(gridData);

      this.selectAnnotationInstancesButton = new Button(parent, SWT.PUSH);
      this.selectAnnotationInstancesButton.setText("Select All");
      this.selectAnnotationInstancesButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.selectAnnotationInstancesButton.setLayoutData(gridData);

      this.deselectAnnotationInstancesButton = new Button(parent, SWT.PUSH);
      this.deselectAnnotationInstancesButton.setText("Deselect All");
      this.deselectAnnotationInstancesButton.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.TOP;
      gridData.grabExcessVerticalSpace = true;
      this.deselectAnnotationInstancesButton.setLayoutData(gridData);
   }


   /**
    * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
    */
   @Override
   public void setVisible(final boolean visible)
   {
      super.setVisible(visible);

      if (visible)
      {
         final SplitTraceDefinitionWizard wizard = (SplitTraceDefinitionWizard) getWizard();
         final FilesWizardPage filesWizardPage = wizard.getFilesWizardPage();

         boolean annotationsProvided = !filesWizardPage
               .getSourceAnnotationsFile().equals("");
         if (annotationsProvided)
         {
            annotationsProvided = loadSourceAnnotationsFile(filesWizardPage
                  .getSourceAnnotationsFile());
         }

         this.splitBySizeButton.setEnabled(annotationsProvided);
         this.splitBySizeButton.setSelection(!annotationsProvided);
         setEnablingOfSplitBySize(!annotationsProvided);

         this.splitByTypeButton.setEnabled(annotationsProvided);
         this.splitByTypeButton.setSelection(false);
         setEnablingOfSplitByType(false);

         this.splitByInstanceButton.setEnabled(annotationsProvided);
         this.splitByInstanceButton.setSelection(false);
         setEnablingOfSplitByInstance(false);

         initialize(annotationsProvided);
      }

      checkPageComplete();
   }


   private void initialize(final boolean annotationsProvided)
   {
      final IDialogSettings settings = getDialogSettings();
      if (settings != null)
      {
         try
         {
            final int splitSize = settings.getInt(SPLIT_SIZE);
            if (splitSize > 0)
            {
               this.splitBySizeSpinner.setSelection(splitSize);
            }
         }
         catch (final NumberFormatException e)
         {
         }

         final boolean splitBySize = settings.getBoolean(SPLIT_BY_SIZE);
         if (splitBySize)
         {
            this.splitBySizeButton.setSelection(true);
            splitBySizeSelected();
            return;
         }

         if (annotationsProvided)
         {
            final boolean splitByType = settings.getBoolean(SPLIT_BY_TYPE);
            if (splitByType)
            {
               this.splitByTypeButton.setSelection(true);
               splitByTypeSelected();

               final Object[] selection = settings.getArray(ANNOTATION_TYPES);
               if (selection != null)
               {
                  this.annotationTypesCheckboxTable
                        .setCheckedElements(selection);
                  this.annotationInstancesCheckboxTable
                        .setInput(filterAnnotationTypes());
               }
            }

            final boolean splitByInstance = settings
                  .getBoolean(SPLIT_BY_INSTANCE);
            if (splitByInstance)
            {
               this.splitByInstanceButton.setSelection(true);
               splitByInstanceSelected();
            }
         }
      }
   }


   private boolean loadSourceAnnotationsFile(final String sourceAnnotationsFile)
   {
      try
      {
         this.structuralAnnotations = StructuralAnnotationsReader.load(sourceAnnotationsFile);
      }
      catch (final FileNotFoundException exception)
      {
         this.annotationTypesCheckboxTable.setInput(null);
         this.annotationInstancesCheckboxTable.setInput(null);
         return false;
      }

      this.annotationTypesCheckboxTable.setInput(this.structuralAnnotations);
      this.annotationInstancesCheckboxTable
            .setInput(this.structuralAnnotations);
      return true;
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
      if (source == this.splitBySizeButton)
      {
         splitBySizeSelected();
      }
      else if (source == this.splitByTypeButton)
      {
         splitByTypeSelected();
      }
      else if (source == this.splitByInstanceButton)
      {
         splitByInstanceSelected();
      }
      else if (source == this.selectAnnotationTypesButton)
      {
         this.annotationTypesCheckboxTable.setAllChecked(true);
      }
      else if (source == this.deselectAnnotationTypesButton)
      {
         this.annotationTypesCheckboxTable.setAllChecked(false);
      }
      else if (source == this.selectAnnotationInstancesButton)
      {
         this.annotationInstancesCheckboxTable.setAllChecked(true);
      }
      else if (source == this.deselectAnnotationInstancesButton)
      {
         this.annotationInstancesCheckboxTable.setAllChecked(false);
      }

      checkPageComplete();
   }


   private void splitBySizeSelected()
   {
      boolean selected = this.splitBySizeButton.getSelection();
      setEnablingOfSplitBySize(selected);

      this.splitByTypeButton.setEnabled(!selected);
      this.splitByInstanceButton.setEnabled(!selected);

      if (selected)
      {
         setEnablingOfSplitByType(false);
         setEnablingOfSplitByInstance(false);
      }
   }


   private void splitByTypeSelected()
   {
      boolean selected = this.splitByTypeButton.getSelection();
      setEnablingOfSplitByType(selected);

      if (selected)
      {
         this.splitBySizeButton.setEnabled(false);
         setEnablingOfSplitBySize(false);
      }
      else if (!selected && !this.splitByInstanceButton.getSelection())
      {
         this.splitBySizeButton.setEnabled(true);
      }
   }


   private void splitByInstanceSelected()
   {
      boolean selected = this.splitByInstanceButton.getSelection();
      setEnablingOfSplitByInstance(selected);


      if (selected)
      {
         this.splitBySizeButton.setEnabled(false);
         setEnablingOfSplitBySize(false);
      }
      else if (!selected && !this.splitByTypeButton.getSelection())
      {
         this.splitBySizeButton.setEnabled(true);
      }
   }


   private void setEnablingOfSplitBySize(final boolean enable)
   {
      this.splitBySizeSpinner.setEnabled(enable);
   }


   private void setEnablingOfSplitByType(final boolean enable)
   {
      this.annotationTypesCheckboxTable.getTable().setEnabled(enable);
      this.selectAnnotationTypesButton.setEnabled(enable);
      this.deselectAnnotationTypesButton.setEnabled(enable);
   }


   private void setEnablingOfSplitByInstance(final boolean enable)
   {
      this.annotationInstancesCheckboxTable.getTable().setEnabled(enable);
      this.selectAnnotationInstancesButton.setEnabled(enable);
      this.deselectAnnotationInstancesButton.setEnabled(enable);
   }


   /**
    * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
    */
   public void selectionChanged(final SelectionChangedEvent event)
   {
      if (event.getSource() == this.annotationTypesCheckboxTable)
      {
         this.annotationInstancesCheckboxTable
               .setInput(filterAnnotationTypes());
      }

      checkPageComplete();
   }


   private Set<StructuralAnnotation> filterAnnotationTypes()
   {
      final Object[] selection = this.annotationTypesCheckboxTable
            .getCheckedElements();

      Set<StructuralAnnotation> filteredAnnotations;
      if (selection.length > 0)
      {
         final Set<String> selectionSet = new HashSet<String>(selection.length);
         for (int i = 0; i < selection.length; i++)
         {
            selectionSet.add(selection[i].toString());
         }

         filteredAnnotations = new TreeSet<StructuralAnnotation>();
         for (final StructuralAnnotation annotation : this.structuralAnnotations)
         {
            if (selectionSet.contains(annotation.getType()))
            {
               filteredAnnotations.add(annotation);
            }
         }
      }
      else
      {
         filteredAnnotations = this.structuralAnnotations;
      }

      return filteredAnnotations;
   }


   public List<Set<StructuralAnnotation>> getSplittedAnnotations()
   {
      final List<Set<StructuralAnnotation>> splittedAnnotationsList = new ArrayList<Set<StructuralAnnotation>>();

      if (this.splitBySizeButton.getSelection())
      {
         final int splitSize = this.splitBySizeSpinner.getSelection();
         assert splitSize > 0;
         Set<StructuralAnnotation> annotationsSet = new HashSet<StructuralAnnotation>(
               splitSize);
         int number = 0;
         for (final StructuralAnnotation annotation : this.structuralAnnotations)
         {
            if (number == splitSize)
            {
               splittedAnnotationsList.add(annotationsSet);
               annotationsSet = new HashSet<StructuralAnnotation>(splitSize);
               number = 0;
            }
            annotationsSet.add(annotation);
            number++;
         }
         if (number != 0)
         {
            splittedAnnotationsList.add(annotationsSet);
         }
      }
      else if (this.splitByInstanceButton.getSelection())
      {
         // check split by instance before split by type
         final Object[] selection = this.annotationInstancesCheckboxTable
               .getCheckedElements();
         assert selection.length > 0;
         final Set<StructuralAnnotation> annotationsSet = new HashSet<StructuralAnnotation>(
               selection.length);
         for (int i = 0; i < selection.length; i++)
         {
            annotationsSet.add((StructuralAnnotation) selection[i]);
         }
         splittedAnnotationsList.add(annotationsSet);
      }
      else if (this.splitByTypeButton.getSelection())
      {
         splittedAnnotationsList.add(filterAnnotationTypes());
      }

      return splittedAnnotationsList;
   }


   /* package */void finish()
   {
      final IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         settings.put(SPLIT_BY_SIZE, this.splitBySizeButton.getSelection());
         settings.put(SPLIT_BY_TYPE, this.splitByTypeButton.getSelection());
         settings.put(SPLIT_BY_INSTANCE, this.splitByInstanceButton
               .getSelection());

         if (this.splitBySizeButton.getSelection())
         {
            settings.put(SPLIT_SIZE, this.splitBySizeSpinner.getSelection());
         }

         if (this.splitByTypeButton.getSelection())
         {
            final Object[] selection = this.annotationTypesCheckboxTable
                  .getCheckedElements();

            if (selection.length > 0)
            {
               final String[] strings = (String[]) Array.newInstance(
                     String.class, selection.length);
               for (int i = 0; i < selection.length; i++)
               {
                  strings[i] = selection[i].toString();
               }

               settings.put(ANNOTATION_TYPES, strings);
            }
         }
      }
   }


   private void checkPageComplete()
   {
      if (!(this.splitBySizeButton.getSelection() || this.splitByTypeButton
            .getSelection()
            || this.splitByInstanceButton.getSelection()))
      {
         setPageComplete(false);
         setErrorMessage("Please select at least one splitting method.");
         return;
      }

      if (this.splitByTypeButton.getSelection()
            && this.annotationTypesCheckboxTable.getCheckedElements().length == 0)
      {
         setPageComplete(false);
         setErrorMessage("Please select at least one annotation type.");
         return;
      }

      if (this.splitByInstanceButton.getSelection()
            && this.annotationInstancesCheckboxTable.getCheckedElements().length == 0)
      {
         setPageComplete(false);
         setErrorMessage("Please select at least one annotation instance.");
         return;
      }

      setPageComplete(true);
      setErrorMessage(null);
   }


   static class AnnotationTypesProvider extends LabelProvider implements
         IStructuredContentProvider, ITableLabelProvider
   {

      private Set<String> annotationTypes = new TreeSet<String>();


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
       *      java.lang.Object, java.lang.Object)
       */
      public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput)
      {
         this.annotationTypes.clear();

         if (newInput != null)
         {
            final Set<StructuralAnnotation> annotations = (Set<StructuralAnnotation>) newInput;
            for (final StructuralAnnotation annotation : annotations)
            {
               this.annotationTypes.add(annotation.getType());
            }
         }
      }


      /**
       * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
       */
      public Object[] getElements(final Object inputElement)
      {
         return this.annotationTypes.toArray();
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
       */
      public Image getColumnImage(final Object element, final int columnIndex)
      {
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
       */
      public String getColumnText(final Object element, final int columnIndex)
      {
         return (String) element;
      }

   }


   static class AnnotationInstancesProvider extends LabelProvider implements
         IStructuredContentProvider, ITableLabelProvider
   {

      private Set<StructuralAnnotation> annotations;


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
       *      java.lang.Object, java.lang.Object)
       */
      public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput)
      {
         this.annotations = (Set<StructuralAnnotation>) newInput;
      }


      /**
       * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
       */
      public Object[] getElements(final Object inputElement)
      {
         return this.annotations != null ? this.annotations.toArray()
               : new Object[] {};
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
       */
      public Image getColumnImage(final Object element, final int columnIndex)
      {
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
       */
      public String getColumnText(final Object element, final int columnIndex)
      {
         return element.toString();
      }

   }

}
