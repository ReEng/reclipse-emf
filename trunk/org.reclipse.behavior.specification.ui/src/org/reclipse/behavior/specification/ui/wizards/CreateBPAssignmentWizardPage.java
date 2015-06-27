package org.reclipse.behavior.specification.ui.wizards;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.commands.CreateBPAssignmentCommand;

import de.uni_paderborn.basicSequenceDiagram.LifelineFragment;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;


public class CreateBPAssignmentWizardPage extends WizardPage
{

   public CreateBPAssignmentWizardPage(CreateBPAssignmentCommand command)
   {
      super("Create Assignment");

      this.command = command;

      setTitle("Select an argument");
      setDescription("Choose an argument for the right side of the assignment");
   }

   private final CreateBPAssignmentCommand command;

   private Label label = null;

   private Text leftVar = null;

   private Combo rightVar = null;

   private BPObject lifelineObject = null;

   private List<BPArgument> args = null;


   public BPArgument getRightSide()
   {
      for (BPArgument a : args)
      {
         if (a.getName().equals(rightVar.getText()))
         {
            return a;
         }
      }
      return null;
   }


   /**
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createControl(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 3;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setFont(parent.getFont());
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      this.leftVar = new Text(composite, SWT.BORDER);
      this.leftVar.setText(this.lifelineObject.getName());
      this.leftVar.setEditable(false);
      this.label = new Label(composite, SWT.NONE);
      this.label.setText(":=");
      this.rightVar = new Combo(composite, SWT.NONE);

      fillVarComboBox();

      setControl(composite);
      Dialog.applyDialogFont(parent);
   }


   public void setLifelineObject(BPObject object)
   {
      this.lifelineObject = object;
   }


   /**
    * Fills the combo box with all available variables. Available variables are arguments that have
    * been specified in the current behavioral pattern.
    */
   private void fillVarComboBox()
   {
      args = new ArrayList<BPArgument>();
      BehavioralPattern behavioralPattern = (BehavioralPattern) this.command
            .getBehavioralPatternEditPart().getModel();
      List<LifelineFragment> events = behavioralPattern.getEvents();
      for (LifelineFragment event : events)
      {
         if (event instanceof SendEvent)
         {
            BPMessage message = (BPMessage) ((SendEvent) event).getMessage();
            List<BPArgument> arguments = message.getArguments();
            boolean add = true;
            for (BPArgument argument : arguments)
            {
               String name = argument.getName();
               if (this.rightVar.getItemCount() > 0)
               {
                  String[] items = this.rightVar.getItems();
                  for (String s : items)
                  {
                     if (s.equals(name))
                     {
                        add = false; // no duplicate entries
                     }
                  }
               }
               if (add)
               {
                  if (!name.equals(this.lifelineObject.getName()))
                  {
                     this.rightVar.add(name);
                     this.args.add(argument);
                  }
               }

            }
         }
      }
      if (this.rightVar.getItemCount() > 0)
      {
         this.rightVar.select(0);
      }
   }
}
