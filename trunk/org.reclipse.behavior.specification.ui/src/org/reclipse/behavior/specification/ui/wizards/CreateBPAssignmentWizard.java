package org.reclipse.behavior.specification.ui.wizards;

import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.ui.commands.CreateBPAssignmentCommand;

public class CreateBPAssignmentWizard extends BasicNewResourceWizard
{

   private final CreateBPAssignmentCommand command;

   private CreateBPAssignmentWizardPage wizardPage;

   private BPObject obj;


   public BPObject getObj()
   {
      return obj;
   }


   public void setObj(BPObject obj)
   {
      this.obj = obj;
   }


   public CreateBPAssignmentWizard(CreateBPAssignmentCommand command)
   {
      this.command = command;
   }


   /**
    * @see org.eclipse.jface.wizard.IWizard#addPages()
    */
   @Override
   public void addPages()
   {
      this.wizardPage = new CreateBPAssignmentWizardPage(command);
      addPage(this.wizardPage);
      this.wizardPage.setLifelineObject(this.obj);
   }


   /**
    * @see org.eclipse.jface.wizard.IWizard#performFinish()
    */
   @Override
   public boolean performFinish()
   {
      this.command.createAssignment(this.wizardPage.getRightSide());
      return true;
   }
   
   @Override
   public boolean canFinish()
   {
      if(this.wizardPage.getRightSide()==null) {
         return false;
      }
      return super.canFinish();
   }

}
