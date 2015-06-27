package org.reclipse.tracer.symbolicexecution.ui.launching;


import static org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants.DEFAULT_SYMBOLIC_METHOD;
import static org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants.METHODS;
import static org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants.PARAM_CON;
import static org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants.PARAM_CON_SHORT;
import static org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants.PARAM_SYM;
import static org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants.PARAM_SYM_SHORT;
import static org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerConstants.SYMBOLIC_METHOD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.reclipse.tracer.symbolicexecution.ui.util.SymbolicMethodStringCreator;


public class SymbolicExecutionLaunchConfigurationTab extends
      AbstractLaunchConfigurationTab implements SelectionListener,
      ModifyListener
{
   private List selectedMethodsList = null;

   private Button selectMethodButton = null;

   private Button removeMethodButton = null;

   private Map<String, SymbolicMethod> methods = null;

   private Composite mainComposite = null;

   private Group paramGroup = null;

   private String resultString = null;

   private Text resultText = null;


   public void createControl(Composite parent)
   {
      this.methods = new HashMap<String, SymbolicMethod>();

      Composite composite = new Composite(parent, SWT.NONE);
      final GridLayout compLayout = new GridLayout();
      final GridData compGridData = new GridData();
      compGridData.grabExcessHorizontalSpace = true;
      compGridData.horizontalAlignment = SWT.FILL;
      compGridData.verticalAlignment = SWT.UP;
      composite.setLayout(compLayout);
      composite.setLayoutData(compGridData);

      createMainComposite(parent, composite);
      createResultComposite(composite);

      setControl(composite);
      Dialog.applyDialogFont(parent);

      update();
   }


   public void createMainComposite(Composite parent, Composite composite)
   {
      this.mainComposite = new Composite(composite, SWT.NONE);
      this.mainComposite.setFont(parent.getFont());
      final GridLayout layout = new GridLayout();
      final GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.mainComposite.setLayout(layout);
      this.mainComposite.setLayoutData(gridData);
      this.createSelectMethodGroup(this.mainComposite);
      createVerticalSpacer(this.mainComposite, 1);
   }


   public void createResultComposite(Composite composite)
   {
      Composite resultComposite = new Composite(composite, SWT.NONE);
      final GridLayout resultCompLayout = new GridLayout();
      resultCompLayout.numColumns = 2;
      final GridData resultCompGridData = new GridData();
      resultCompGridData.grabExcessVerticalSpace = true;
      resultCompGridData.verticalAlignment = SWT.DOWN;
      resultComposite.setLayout(resultCompLayout);
      resultComposite.setLayoutData(resultCompGridData);
      Label resultLabel = new Label(resultComposite, SWT.NONE);
      resultLabel.setText("Result String: ");
      final GridData resultGridData1 = new GridData();
      resultGridData1.horizontalAlignment = SWT.LEFT;
      resultLabel.setLayoutData(resultGridData1);
      this.resultText = new Text(resultComposite, SWT.NONE);
      final GridData resultGridData2 = new GridData();
      resultGridData2.horizontalAlignment = SWT.LEFT;
      this.resultText.setLayoutData(resultGridData2);
      this.resultText.setEditable(false);
      this.resultText.setEnabled(false);
   }


   public String getName()
   {
      return "Symbolic Execution";
   }


   public void initializeFrom(ILaunchConfiguration configuration)
   {
      try
      {
         this.selectedMethodsList.removeAll();
         this.methods.clear();
         String symbMethodString = configuration.getAttribute(SYMBOLIC_METHOD,
               DEFAULT_SYMBOLIC_METHOD);
         this.resultString = symbMethodString;

         java.util.List<String> methods = configuration.getAttribute(METHODS,
               new ArrayList<String>());
         for (String methodString : methods)
         {
            String[] arr = methodString.split("_");
            String methodSignature = arr[0];
            String symbolicMethodString = arr[1];
            this.methods.put(methodSignature, new SymbolicMethod(
                  symbolicMethodString, methodSignature));
            this.selectedMethodsList.add(methodSignature);
         }
         update();
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
   }


   public void performApply(ILaunchConfigurationWorkingCopy configuration)
   {
      configuration.setAttribute(SYMBOLIC_METHOD, this.getSymbMethodString());
      java.util.List<String> methodList = new ArrayList<String>();
      Set<Entry<String, SymbolicMethod>> methodsEntrySet = this.methods
            .entrySet();
      for (Entry<String, SymbolicMethod> entry : methodsEntrySet)
      {
         String methodSignature = entry.getKey();
         String symbolicMethodString = entry.getValue().toString();
         methodList.add(methodSignature + "_" + symbolicMethodString);
      }
      configuration.setAttribute(METHODS, methodList);
   }


   private void calculateSymbMethodString()
   {
      StringBuffer stringBuffer = new StringBuffer();
      Set<Entry<String, SymbolicMethod>> entrySet = this.methods.entrySet();
      for (Entry<String, SymbolicMethod> entry : entrySet)
      {
         stringBuffer.append(entry.getValue().getSymbolicMethodString());
         stringBuffer.append(",");
      }
      String result = stringBuffer.toString();
      if (result.length() > 0)
      {
         result = result.substring(0, result.length() - 1); // cut last ","
      }
      this.resultString = result;
   }


   public String getSymbMethodString()
   {
      return this.resultString;
   }


   public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
   {
      configuration.setAttribute(SYMBOLIC_METHOD, DEFAULT_SYMBOLIC_METHOD);
      configuration.setAttribute(METHODS, new ArrayList<String>());
   }


   private void createSelectMethodGroup(Composite parent)
   {
      final Group group = new Group(parent, SWT.NONE);
      group.setText("Symbolic Methods");
      final GridLayout layout = new GridLayout();
      layout.numColumns = 2;
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      group.setLayout(layout);
      group.setLayoutData(gridData);

      this.selectedMethodsList = new List(group, SWT.SINGLE | SWT.BORDER);
      this.selectedMethodsList.addSelectionListener(this);

      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      this.selectedMethodsList.setLayoutData(gridData);

      createButtonComposite(group);

   }


   public void createButtonComposite(final Group group)
   {
      GridData gridData;
      Composite buttonComposite = new Composite(group, SWT.NONE);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.RIGHT;
      buttonComposite.setLayoutData(gridData);

      final GridLayout buttonCompositeLayout = new GridLayout();
      buttonCompositeLayout.numColumns = 1;
      GridData buttonCompositeGridData = new GridData();
      buttonComposite.setLayout(buttonCompositeLayout);
      buttonComposite.setLayoutData(buttonCompositeGridData);

      this.selectMethodButton = createPushButton(buttonComposite, "Add Method",
            null);
      this.selectMethodButton.addSelectionListener(this);
      this.selectMethodButton.setSize(IDialogConstants.BUTTON_WIDTH,
            IDialogConstants.BUTTON_WIDTH);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.selectMethodButton.setLayoutData(gridData);

      this.removeMethodButton = createPushButton(buttonComposite, "Remove",
            null);
      this.removeMethodButton.addSelectionListener(this);
      this.removeMethodButton.setSize(IDialogConstants.BUTTON_WIDTH,
            IDialogConstants.BUTTON_WIDTH);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.DOWN;
      this.removeMethodButton.setLayoutData(gridData);
   }


   public void modifyText(ModifyEvent e)
   {
      this.setDirty(true);
      this.updateLaunchConfigurationDialog();
   }


   public void widgetSelected(SelectionEvent e)
   {
      final Object source = e.getSource();

      if (source == this.selectMethodButton)
      {
         // "add method" button is selected
         handleSelectMethodButtonSelected();
      }
      else if (source == this.removeMethodButton)
      {
         // "remove" button is selected
         handleRemoveMethodButtonSelected();
      }
      else if (source == this.selectedMethodsList)
      {
         // methods list is selected
         createParamGroup();
      }
      else if (source instanceof Button)
      {
         Button button = (Button) source;
         if (button.getText().equals(PARAM_SYM)
               || button.getText().equals(PARAM_CON))
         {
            // radio button is selected
            handleSelectRadioButtonSelected(button);
         }
      }

      update();

   }


   public void handleSelectRadioButtonSelected(Button button)
   {
      java.util.List<SymbolicMethodParam> params = this.getSelectedMethod()
            .getParams();
      for (SymbolicMethodParam param : params)
      {
         Composite parentGroup = button.getParent();
         Label label = (Label) parentGroup.getChildren()[0];
         if (param.getName().equals(label.getText()))
         {
            if (button.getText().equals(PARAM_SYM))
            {
               param.setValue(PARAM_SYM_SHORT);
            }
            else if (button.getText().equals(PARAM_CON))
            {
               param.setValue(PARAM_CON_SHORT);
            }
            this.getSelectedMethod().refreshString();
         }
      }
      calculateSymbMethodString();
   }


   private void createParamGroup()
   {
      if (this.paramGroup != null)
      {
         this.paramGroup.dispose();
      }

      this.paramGroup = new Group(this.mainComposite, SWT.NONE);
      this.paramGroup.setText("Define Parameters");
      final GridLayout layout = new GridLayout();
      layout.numColumns = 1;
      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.paramGroup.setLayout(layout);
      this.paramGroup.setLayoutData(gridData);

      String[] paramNames = getSelectedMethod().getParameterNames();
      int i = 0;
      for (String name : paramNames)
      {
         createRadioGroupForParam(i, name);
         i++;
      }

   }


   public void createRadioGroupForParam(int i, String name)
   {
      Composite radioGroup = new Composite(this.paramGroup, SWT.NONE);
      final GridLayout groupLayout = new GridLayout();
      groupLayout.numColumns = 3;
      groupLayout.marginBottom = 0;
      groupLayout.marginTop = 0;
      groupLayout.makeColumnsEqualWidth = true;
      GridData groupGridData = new GridData();
      groupGridData.grabExcessHorizontalSpace = true;
      groupGridData.horizontalAlignment = SWT.FILL;
      radioGroup.setLayout(groupLayout);
      radioGroup.setLayoutData(groupGridData);
      Label nameLabel = new Label(radioGroup, SWT.NONE);
      String labelText = name;// + ":" + selectedMethod.getParameterTypes()[i];
      nameLabel.setText(labelText);
      Button symButton = new Button(radioGroup, SWT.RADIO);
      symButton.setText(PARAM_SYM);
      if (getSelectedMethod().paramIsAlreadyContained(labelText))
      {
         symButton.setSelection(getSelectedMethod().paramIsSym(labelText));
      }
      else
      {
         symButton.setSelection(true);
      }
      symButton.addSelectionListener(this);
      Button conButton = new Button(radioGroup, SWT.RADIO);
      conButton.setText(PARAM_CON);
      conButton.addSelectionListener(this);
      conButton.setSelection(!symButton.getSelection());

      getSelectedMethod().addParam(
            new SymbolicMethodParam(labelText, PARAM_SYM_SHORT));
   }


   public SymbolicMethod getSelectedMethod()
   {
      return this.methods.get(this.selectedMethodsList.getSelection()[0]);
   }


   private void handleRemoveMethodButtonSelected()
   {
      int[] selectionIndices = this.selectedMethodsList.getSelectionIndices();
      for (int i : selectionIndices)
      {
         this.methods.remove(this.selectedMethodsList.getItem(i));
         this.selectedMethodsList.remove(i);
      }

      calculateSymbMethodString();
      update();
      if (this.paramGroup != null)
      {
         this.paramGroup.dispose();
      }
   }


   private void update()
   {
      if (this.resultString != null)
      {
         this.resultText.setText(this.resultString);
      }
      if (!isValidResultString())
      {
         setErrorMessage("Please select at least one method and define it's parameters.");
         updateLaunchConfigurationDialog();
         return;
      }

      setErrorMessage(null);
      updateLaunchConfigurationDialog();
   }


   /**
    * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
    */
   @Override
   public boolean isValid(final ILaunchConfiguration configuration)
   {
      return isValidResultString();
   }


   private boolean isValidResultString()
   {
      SymbolicMethodStringCreator stringCreator = new SymbolicMethodStringCreator(null);
      return stringCreator
            .isValidSymbolicMethodString(this.getSymbMethodString());
   }


   private void handleSelectMethodButtonSelected()
   {
      // create types dialog from jdt
      SelectionDialog dialog = createTypesDialog();
      if (dialog.getResult() != null)
      {
         final IType type = (IType) dialog.getResult()[0];

         // create dialog that takes the type and lists all its methods
         ListDialog dialog2 = createMethodsDialog(type);
         if (dialog.getResult() != null)
         {
            IMethod iMethod = (IMethod) dialog2.getResult()[0];
            String methodString = getMethodLabel(iMethod);
            this.selectedMethodsList.add(methodString);
            this.methods.put(methodString, new SymbolicMethod(methodString));
         }
      }

      this.selectedMethodsList.setSelection(this.selectedMethodsList
            .getItemCount() - 1);
      createParamGroup();

      calculateSymbMethodString();
   }


   public SelectionDialog createTypesDialog()
   {
      SelectionDialog dialog = null;
      IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
      try
      {
         dialog = JavaUI.createTypeDialog(getShell(),
               this.getLaunchConfigurationDialog(), scope,
               IJavaElementSearchConstants.CONSIDER_ALL_TYPES, false);
      }
      catch (JavaModelException e)
      {
         e.printStackTrace();
      }

      dialog.setTitle("Select class");
      dialog.setMessage("Select the containing class.");
      dialog.open();
      return dialog;
   }


   public ListDialog createMethodsDialog(final IType type)
   {
      ListDialog dialog2 = new ListDialog(getShell());
      dialog2.setTitle("Select a method to be executed");
      dialog2.setMessage("Select a method from the type "
            + type.getFullyQualifiedName() + ":");
      dialog2.setInput(type);
      dialog2.setContentProvider(new SelectMethodDialogContentProvider());
      dialog2.setLabelProvider(new SelectMethodDialogLabelProvider());
      dialog2.open();
      return dialog2;
   }


   public String getMethodLabel(IMethod iMethod)
   {
      StringBuffer label = new StringBuffer();
      try
      {
         label.append(iMethod.getDeclaringType().getElementName());
         label.append(".");
         if (iMethod.isConstructor())
         {
            label.append("<init>");
         }
         else
         {
            label.append(iMethod.getElementName());
         }
         label.append("(");
         String[] paramNames = iMethod.getParameterNames();
         for (int i = 0; i < paramNames.length; i++)
         {
            label.append(paramNames[i]);
            label.append(":");
            label.append(Signature.getSignatureSimpleName(iMethod
                  .getParameterTypes()[i]));
            if (i + 1 < paramNames.length)
            {
               label.append(",");
            }
         }
         label.append(")");
         label.append(":");
         label.append(Signature.getSignatureSimpleName(iMethod.getReturnType()));
      }
      catch (JavaModelException e)
      {
         e.printStackTrace();
      }
      return label.toString();
   }


   public void widgetDefaultSelected(SelectionEvent e)
   {
   }


}
