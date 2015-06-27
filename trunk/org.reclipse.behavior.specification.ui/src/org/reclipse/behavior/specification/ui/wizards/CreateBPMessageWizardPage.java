package org.reclipse.behavior.specification.ui.wizards;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.ui.commands.CreateBPMessageCommand;
import org.reclipse.behavior.specification.ui.util.ASTUtil;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;


public class CreateBPMessageWizardPage extends WizardPage
{

   private final CreateBPMessageCommand command;

   private Combo methodComboBox;

   private Composite comp;

   private List<PSObject> methodsList;

   private ArrayList<Text> paramTextBoxes;

   private ArrayList<Label> paramLabels;

   List<PSObject> parameters;


   public CreateBPMessageWizardPage(CreateBPMessageCommand command)
   {
      super("Create Behavioral Pattern Message");

      this.command = command;
      this.methodsList = new ArrayList<PSObject>();

      setTitle("Select a Method");
      setDescription("Choose a Method from the Structural Pattern to create a message.");
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

      this.comp = composite;
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      // method label
      Label label = new Label(composite, SWT.NONE);
      label.setText("Method:");

      gridData = new GridData();
      gridData.verticalAlignment = SWT.BEGINNING;
      label.setLayoutData(gridData);

      // method combobox
      this.methodComboBox = new Combo(composite, SWT.READ_ONLY);

      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.methodComboBox.setLayoutData(gridData);
      this.methodComboBox.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            CreateBPMessageWizardPage.this
                  .createParameterTextBoxes(CreateBPMessageWizardPage.this.comp);
         }
      });

      fillMethodComboBox();

      // parameters
      createParameterTextBoxes(composite);


      setControl(composite);
      Dialog.applyDialogFont(parent);
   }


   private void createParameterTextBoxes(Composite composite)
   {
      PSObject methodObject = getSelectedPSObject();
      if (methodObject != null)
      {
         if (paramTextBoxes != null)
         {
            for (Text box : paramTextBoxes)
            {
               box.dispose();
            }
            for (Label l : paramLabels)
            {
               l.dispose();
            }
         }
         paramTextBoxes = new ArrayList<Text>();
         paramLabels = new ArrayList<Label>();

         int i = 0;
         parameters = getParametersList(methodObject);
         for (PSObject param : parameters)
         {
            i++;
            Text newTextBox = new Text(composite, SWT.BORDER);
            newTextBox.setText("param" + i);
            Label newLabel = new Label(composite, SWT.NONE);
            if (param != null && ASTUtil.getParamType(param) != null)
            {
               newLabel.setText(":" + ASTUtil.getParamType(param).getName());
            }
            paramTextBoxes.add(newTextBox);
            paramLabels.add(newLabel);
         }
         composite.layout();
      }

   }


   public HashMap<String, PSObject> getArguments()
   {
      HashMap<String, PSObject> arguments = new HashMap<String, PSObject>();
      int i = 0;
      for (PSObject param : this.parameters)
      {
         arguments.put(getArgumentNames().get(i), param);
      }
      return arguments;
   }


   /**
    * Returns a list of all parameters of the given methodObject that have been specified in the
    * corresponding structural pattern.
    * 
    * @param methodObject
    * @return
    */
   public List<PSObject> getParametersList(PSObject methodObject)
   {
      List<PSObject> resultList = new ArrayList<PSObject>();
      List<PSConnection> outgoingConnections = methodObject.getOutgoing();
      for (PSConnection psConnection : outgoingConnections)
      {
         if (ASTUtil.connectionIsMethodToParamLink(psConnection))
         {
            PSNode targetNode = ((PSLink) psConnection).getTarget();
            if (ASTUtil.objectIsParam((PSObject) targetNode))
            {
               resultList.add((PSObject) targetNode);
            }
         }
      }
      return resultList;
   }


   private void fillMethodComboBox()
   {
      if (this.command.getDiagram() != null)
      {
         AbstractSequenceDiagramObject target = this.command.getTarget();
         PSObject type = null;
         if (target instanceof BPObject)
         {
            BPObject bpObject = (BPObject) target;
            type = bpObject.getTypeReference();
         }
         else if (target instanceof BPSetObject)
         {
            BPSetObject bpObject = (BPSetObject) target;
            type = bpObject.getTypeReference();
         }
         if (type != null)
         {
            List<PSObject> methods = getMethodReferences(type);
            for (PSObject method : methods)
            {
               this.methodsList.add(method);
               this.methodComboBox.add(method.getName());
            }
         }

         if (this.methodComboBox.getItemCount() > 0)
         {
            this.methodComboBox.select(0);
            setPageComplete(true);
         }
         else
         {
            setPageComplete(false);
         }
      }
      else
      {
         setPageComplete(false);
      }
   }


   /**
    * Returns a list of all methods that are connected to the given PSObject according to the
    * structural pattern that corresponds to the current behavioral pattern.
    * 
    * @param type
    * @return
    */
   private List<PSObject> getMethodReferences(PSObject type)
   {
      List<PSObject> list = new ArrayList<PSObject>();
      List<PSConnection> connections = type.getIncoming();
      for (PSConnection psConnection : connections)
      {
         if (psConnection.getSource() instanceof PSObject)
         {
            PSObject source = (PSObject) psConnection.getSource();
            if (ASTUtil.objectIsMethod(source))
            {
               list.add(source);
            }
         }
      }
      connections = type.getOutgoing();
      for (PSConnection psConnection : connections)
      {
         if (psConnection.getTarget() instanceof PSObject)
         {
            PSObject target = (PSObject) psConnection.getTarget();
            if (ASTUtil.objectIsMethod(target))
            {
               list.add(target);
            }
         }
      }
      return list;
   }


   public PSObject getSelectedPSObject()
   {
      if (this.methodsList.size() > 0)
      {
         return this.methodsList.get(this.methodComboBox.getSelectionIndex());
      }
      return null;
   }


   public ArrayList<String> getArgumentNames()
   {
      ArrayList<String> argNames = new ArrayList<String>();
      for (Text paramTextBox : paramTextBoxes)
      {
         argNames.add(paramTextBox.getText());
      }
      return argNames;
   }
}