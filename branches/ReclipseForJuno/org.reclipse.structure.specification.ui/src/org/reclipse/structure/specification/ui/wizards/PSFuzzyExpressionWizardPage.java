package org.reclipse.structure.specification.ui.wizards;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.reclipse.math.functions.FunctionParameter;
import org.reclipse.math.functions.Lim0EFunction;
import org.reclipse.math.functions.Lim1EFunction;
import org.reclipse.math.functions.Lim1EFunctionNOA;
import org.reclipse.math.functions.Lim1EFunctionNOM;
import org.reclipse.math.functions.Lim1EFunctionWLOC;
import org.reclipse.math.functions.LinearFunction;
import org.reclipse.math.functions.MathFunctionsManager;
import org.reclipse.math.functions.MathematicalFunction;
import org.reclipse.metrics.extensionpoints.Metric;
import org.reclipse.metrics.extensionpoints.MetricUtil;
import org.reclipse.structure.specification.ui.PSImages;
import org.reclipse.structure.specification.ui.PSPlugin;
import org.reclipse.structure.specification.ui.figures.FuzzyFunctionFigure;
import org.reclipse.structure.specification.ui.figures.Lim0EFunctionFigure;
import org.reclipse.structure.specification.ui.figures.Lim1EFunctionFigure;
import org.reclipse.structure.specification.ui.figures.LinearFunctionFigure;


/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author: oleg82 $
 * @version $Revision: 4331 $ $Date: 2010-04-15 12:51:43 +0200 (Do, 15 Apr 2010) $
 */
public class PSFuzzyExpressionWizardPage extends WizardPage implements
      SelectionListener
{   
   private ArrayList<String> functionNames;

   private int selectedFunctionsIndex = -1; // no selection
   
   private String selectedAcronym;

   private Composite topLevel;

   private Combo acronymComboBox;
   
   private StyledText acronymExplanation;

   private Combo functionComboBox;

   private TableViewer tableViewer;

   private Table table;

   private Canvas canvas;
   
   private Label functionImage;
   
   private LightweightSystem lws;
   
   private FuzzyFunctionFigure figure;
   
   private boolean displayAcronym = true;
   private boolean sizeAcronymSelectable = true;


   public PSFuzzyExpressionWizardPage()
   {
      super("Fuzzy Expression", "Define the Fuzzy Expression", null);
      setPageComplete(false);
   }


   /**
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
   public void createControl(Composite parent)
   {
      initializeDialogUnits(parent);

      this.topLevel = new Composite(parent, SWT.NONE);
      FormLayout layout = new FormLayout();
      layout.marginHeight = 5;
      layout.marginWidth = 5;
      this.topLevel.setLayout(layout);
      this.topLevel.setFont(parent.getFont());

      FormData canvasData = new FormData();
      canvasData.left = new FormAttachment(0, 0);
      canvasData.right = new FormAttachment(100, 0);
      canvasData.top = new FormAttachment(0, 0);
      canvasData.bottom = new FormAttachment(0, 100);
      this.canvas = new Canvas(this.topLevel, SWT.NO_FOCUS | SWT.BORDER);
      this.canvas.setLayoutData(canvasData);
      this.lws = new LightweightSystem(this.canvas);
      
      
      FormData imageData = new FormData();
      imageData.left = new FormAttachment(0, 40);
      imageData.top = new FormAttachment(this.canvas, 10);
      this.functionImage = new Label(this.topLevel, SWT.CENTER);
      this.functionImage.setLayoutData(imageData);
      this.functionImage.setSize(450, 150);

      FormData acronymLabelData = new FormData();
      acronymLabelData.left = new FormAttachment(0, 0);
      acronymLabelData.top = new FormAttachment(this.canvas,165);
      Label acronymLabel = new Label(this.topLevel, SWT.NONE);
      acronymLabel.setText("Metric Acronym: ");
      acronymLabel.setLayoutData(acronymLabelData);
      
      FormData acronymComboBoxData = new FormData();
      acronymComboBoxData.left = new FormAttachment(acronymLabel, 10);
      acronymComboBoxData.top = new FormAttachment(this.canvas, 165);
      this.acronymComboBox = new Combo(this.topLevel, SWT.READ_ONLY);
      this.acronymComboBox.setLayoutData(acronymComboBoxData);
      this.acronymComboBox.addSelectionListener(this);
      this.acronymComboBox.setEnabled(this.displayAcronym);
      
      FormData acronymExplanationData = new FormData();
      acronymExplanationData.left = new FormAttachment(0, 0);
      acronymExplanationData.top = new FormAttachment(this.acronymComboBox, 10);
      acronymExplanationData.width = 500;
      acronymExplanationData.height = 80;
      this.acronymExplanation = new StyledText(this.topLevel, SWT.READ_ONLY);
      this.acronymExplanation.setLayoutData(acronymExplanationData);
      this.acronymExplanation.addSelectionListener(this);
      this.acronymExplanation.setWordWrap(true);
      this.acronymExplanation.setText("");


      FormData functionLabelData = new FormData();
      functionLabelData.left = new FormAttachment(0, 0);
      functionLabelData.top = new FormAttachment(this.acronymExplanation, 10);
      Label functionLabel = new Label(this.topLevel, SWT.NONE);
      functionLabel.setText("Function: ");
      functionLabel.setLayoutData(functionLabelData);

      FormData typeComboBoxData = new FormData();
      typeComboBoxData.left = new FormAttachment(functionLabel, 10);
      typeComboBoxData.top = new FormAttachment(this.acronymExplanation, 10);
      this.functionComboBox = new Combo(this.topLevel, SWT.READ_ONLY);
      this.functionComboBox.setLayoutData(typeComboBoxData);
      this.functionComboBox.addSelectionListener(this);

      FormData tableData = new FormData();
      tableData.left = new FormAttachment(0, 0);
      tableData.top = new FormAttachment(this.functionComboBox, 10);
      tableData.right = new FormAttachment(100, 0);
      this.tableViewer = this.createParametersTable(this.topLevel);
      this.table = this.tableViewer.getTable();
      this.table.setLayoutData(tableData);

      Collection<Metric> metrics = MetricUtil.getAllMetrics();
      for (Metric metric : metrics)
      {
         this.acronymComboBox.add(metric.getAcronym());
      }
      
      
      this.functionNames = MathFunctionsManager.get()
            .getAllKnownMathFunctionsNames();
      for (String name : this.functionNames)
      {
         this.functionComboBox.add(name);
      }

      if (!this.functionNames.isEmpty())
      {
         this.functionComboBox.select(0);
         this.selectedFunctionsIndex = 0;
         updateFunctionViewer();
      }

      setControl(this.topLevel);

      setDescription("Select the membership function.");
   }


   public boolean isValidState()
   {
      if (this.functionComboBox != null
            && this.functionComboBox.getSelectionIndex() > -1
            && this.acronymComboBox != null
            && (!this.acronymComboBox.isEnabled() || !this.acronymComboBox.getText().equals("")))
      {
         return true;
      }
      return false;
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetSelected(SelectionEvent e)
   {
      if (e.getSource() == this.functionComboBox)
      {
         this.selectedFunctionsIndex = this.functionComboBox
               .getSelectionIndex();
      }
      else if(e.getSource() == this.acronymComboBox)
      {
         this.selectedAcronym = this.acronymComboBox.getText();
         updateAcronymSelection();
      }

      updateFunctionViewer();
      

      getWizard().getContainer().updateButtons();
   }


   private void updateAcronymSelection()
   {

      // check also if the explanation box exists already
      if(MetricUtil.getAllMetrics().isEmpty() && acronymExplanation != null)
      {
         this.acronymExplanation.setText("No metric configuration found. Please, register a metric configuration first.");
         return;
      }
      
      String selected = getMetricAcronym();
      if (selected == null)
      {
         return;
      }

      if (!selected.equals(""))
      {
         Collection<Metric> metrics = MetricUtil.getAllMetrics();
         Iterator<Metric> mIter = metrics.iterator();
         while (mIter.hasNext())
         {
            Metric metric = mIter.next();
            if (selected.equals(metric.getAcronym()))
            {
               String explanation = metric.getName() + "\n";
               explanation += metric.getDescription() != null ? metric
                     .getDescription()
                     + "\n" : "";
               this.acronymExplanation.setText(explanation);
               this.acronymExplanation.setToolTipText(metric.getDescription());
               this.acronymExplanation.redraw();
               break;
            }
         }
      }
      else
      {
         this.acronymExplanation.setText("");
         this.acronymExplanation.setToolTipText("");
         this.acronymExplanation.redraw();
      }
   }


   private void updateFunctionViewer()
   {
      MathematicalFunction mathFunction = this.getSelectedMathFunction();
      if (mathFunction != null)
      {
         this.tableViewer.setInput(mathFunction);

         this.table.getColumn(0).pack();
         this.table.getColumn(1).pack();
         
         ImageDescriptor desc = null;
         if(mathFunction instanceof LinearFunction || this.selectedFunctionsIndex == 0)
         {
            desc = PSImages.getDescriptor(PSImages.IMAGE_FUNCTION_LINEAR);
            this.figure = new LinearFunctionFigure("X");
            this.figure.setFunction(mathFunction);
         }
         else if(mathFunction instanceof Lim0EFunction)
         {
            desc = PSImages.getDescriptor(PSImages.IMAGE_FUNCTION_LIM0);
            this.figure = new Lim0EFunctionFigure("X");
            this.figure.setFunction(mathFunction);
         }
         else if(mathFunction instanceof Lim1EFunction
               || mathFunction instanceof Lim1EFunctionWLOC
               || mathFunction instanceof Lim1EFunctionNOM
               || mathFunction instanceof Lim1EFunctionNOA)
         {
            desc = PSImages.getDescriptor(PSImages.IMAGE_FUNCTION_LIM1);
            this.figure = new Lim1EFunctionFigure("X");
            this.figure.setFunction(mathFunction);
         }
         this.figure.setBackgroundColor(ColorConstants.white);
         this.lws.setContents(this.figure);
         if(desc != null)
         {
            this.functionImage.setImage(desc.createImage());
            this.functionImage.pack();
         }
      }
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetDefaultSelected(SelectionEvent e)
   {
   }


   private TableViewer createParametersTable(Composite parent)
   {
      final TableViewer tableViewer = new TableViewer(parent, SWT.SINGLE
            | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.BORDER);
      final Table table = tableViewer.getTable();
      table.setHeaderVisible(true);
      table.setLinesVisible(true);

      TableColumn column1 = new TableColumn(table, SWT.NULL);
      column1.setText("parameter");
      column1.pack();

      TableColumn column2 = new TableColumn(table, SWT.NULL);
      column2.setText("value");
      column2.pack();

      tableViewer.setColumnProperties(new String[] { "parameter", "value" });
      tableViewer.setCellEditors(new CellEditor[] { null,
            new TextCellEditor(table) });
      tableViewer.setCellModifier(new ICellModifier()
      {
         public boolean canModify(Object element, String property)
         {
            if (element instanceof FunctionParameter
                  && property.equals("value"))
            {
               return true;
            }
            return false;
         }


         public Object getValue(Object element, String property)
         {
            if (element instanceof FunctionParameter
                  && property.equals("value"))
            {
               return Double.toString(((FunctionParameter) element).getValue());
            }
            return null;
         }


         public void modify(Object element, String property, Object value)
         {
            if (element instanceof TableItem && property.equals("value")
                  && value instanceof String)
            {
               TableItem item = (TableItem) element;
               Object data = item.getData();
               if (data instanceof FunctionParameter)
               {
                  FunctionParameter parameter = (FunctionParameter) data;
                  double oldValue = parameter.getValue();
                  try
                  {
                     double newValue = Double.parseDouble((String) value);

                     if (oldValue != newValue)
                     {
                        parameter.setValue(newValue);
                        tableViewer.refresh(parameter);
                        updateFunctionViewer();
                     }
                  }
                  catch (NumberFormatException e)
                  {
                     e.printStackTrace();
                  }
               }
            }
         }
      });

      tableViewer.setContentProvider(new IStructuredContentProvider()
      {
         public Object[] getElements(Object inputElement)
         {
            if (inputElement instanceof MathematicalFunction)
            {
               MathematicalFunction mathFunction = (MathematicalFunction) inputElement;

               ArrayList<FunctionParameter> list = new ArrayList<FunctionParameter>();
               Iterator<Entry<String, FunctionParameter>> iter = mathFunction.entriesOfParams();
               while (iter.hasNext())
               {
                  Entry<String, FunctionParameter> entry = iter.next();
                  list.add(entry.getValue());
               }
               return list.toArray();
            }
            return null;
         }


         public void dispose()
         {
         }


         public void inputChanged(Viewer viewer, Object oldInput,
               Object newInput)
         {
         }
      });
      tableViewer.setLabelProvider(new ITableLabelProvider()
      {
         public Image getColumnImage(Object element, int columnIndex)
         {
            return null;
         }


         public String getColumnText(Object element, int columnIndex)
         {
            if (element instanceof FunctionParameter)
            {
               FunctionParameter param = (FunctionParameter) element;
               if (columnIndex == 0)
               {
                  return param.getName();
               }
               else if (columnIndex == 1)
               {
                  return Double.toString(param.getValue());
               }
            }
            return null;
         }


         public void addListener(ILabelProviderListener listener)
         {
         }


         public void dispose()
         {
         }


         public boolean isLabelProperty(Object element, String property)
         {
            return false;
         }


         public void removeListener(ILabelProviderListener listener)
         {
         }
      });

      return tableViewer;
   }

   private HashMap<Integer, MathematicalFunction> cachedMathFunctions = new HashMap<Integer, MathematicalFunction>();


   public MathematicalFunction getSelectedMathFunction()
   {
      int index = this.getSelectedFunctionsIndex();
      if (index >= 0)
      {
         MathematicalFunction mathFunction = this.cachedMathFunctions
               .get(index);

         if (mathFunction == null)
         {
            ArrayList<Class<? extends MathematicalFunction>> functionClasses = MathFunctionsManager
                  .get().getAllKnownMathFunctions();
            if (index < functionClasses.size())
            {
               Class<? extends MathematicalFunction> functionClass = functionClasses
                     .get(this.selectedFunctionsIndex);

               try
               {
                  mathFunction = functionClass.newInstance();
               }
               catch (InstantiationException e)
               {
                  PSPlugin.getDefault().logError(
                        "Could not create new math function instance", e);
               }
               catch (IllegalAccessException e)
               {
                  PSPlugin.getDefault().logError(
                        "Could not create new math function instance", e);
               }

               this.cachedMathFunctions.put(index, mathFunction);
            }
         }

         return mathFunction;
      }
      return null;
   }
   
   public void setSelectedMathFunction(MathematicalFunction function)
   {
      ArrayList<Class<? extends MathematicalFunction>> fctList = MathFunctionsManager.get().getAllKnownMathFunctions();
      ArrayList<String> fctNamesList = MathFunctionsManager.get().getAllKnownMathFunctionsNames();
      
      String functionName = null;
      int functionIndex = -1;
      
      for (int i = 0; i < fctList.size(); i++)
      {
         if(function.getClass().equals(fctList.get(i)))
         {
            functionName = fctNamesList.get(i);
            functionIndex = i;
            break;
         }
      }
      
      if(functionName != null)
      {
         this.selectedFunctionsIndex = functionIndex;
         this.functionComboBox.select(functionIndex);
         
         this.cachedMathFunctions.put(functionIndex, function);
      }
      this.updateFunctionViewer();
      
   }


   public int getSelectedFunctionsIndex()
   {
      return this.selectedFunctionsIndex;
   }


   public String getMetricAcronym()
   {
      return this.selectedAcronym;
   }
   
   public void setMetricAcronym(String text)
   {
      if(this.acronymComboBox != null && text != null)
      {
         String[] acros = this.acronymComboBox.getItems();
         for (int i = 0; i < acros.length; i++)
         {
            if(text.equals(acros[i]))
            {
               this.acronymComboBox.select(i);
               this.selectedAcronym = this.acronymComboBox.getText();
            }
         }
      }
      updateAcronymSelection();
   }


   @Override
   public void dispose()
   {
      super.dispose();

      if (!this.cachedMathFunctions.isEmpty())
      {
         this.cachedMathFunctions.clear();
      }
   }


   /**
    * @return Returns the displayAcronym.
    */
   public boolean isDisplayAcronym()
   {
      return this.displayAcronym;
   }


   /**
    * @param displayAcronym The displayAcronym to set.
    */
   public void setDisplayAcronym(boolean displayAcronym)
   {
      this.displayAcronym = displayAcronym;
      if(this.acronymComboBox != null)
      {
         this.acronymComboBox.setEnabled(displayAcronym);
      }
   }


   /**
    * @return Returns the sizeAcronymSelectable.
    */
   public boolean isSizeAcronymSelectable()
   {
      return this.sizeAcronymSelectable;
   }


   /**
    * @param sizeAcronymSelectable The sizeAcronymSelectable to set.
    */
   public void setSizeAcronymSelectable(boolean sizeAcronymSelectable)
   {
      this.sizeAcronymSelectable = sizeAcronymSelectable;
      if (this.acronymComboBox != null
            && "SIZE".equals(this.acronymComboBox.getItem(0)))// SIZE is always be at the first position
      {
         this.acronymComboBox.remove(0);
      }
   }
}
