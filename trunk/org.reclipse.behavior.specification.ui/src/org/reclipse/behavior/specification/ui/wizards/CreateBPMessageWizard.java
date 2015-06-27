package org.reclipse.behavior.specification.ui.wizards;


import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.reclipse.behavior.specification.ui.commands.CreateBPMessageCommand;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class CreateBPMessageWizard extends BasicNewResourceWizard
{

   private final CreateBPMessageCommand command;

   private CreateBPMessageWizardPage wizardPage;


   public CreateBPMessageWizard(CreateBPMessageCommand command)
   {
      this.command = command;
   }


   /**
    * @see org.eclipse.jface.wizard.IWizard#addPages()
    */
   @Override
   public void addPages()
   {
      this.wizardPage = new CreateBPMessageWizardPage(command);
      addPage(this.wizardPage);
   }


   /**
    * @see org.eclipse.jface.wizard.IWizard#performFinish()
    */
   @Override
   public boolean performFinish()
   {
      this.command.setPSObject(this.wizardPage.getSelectedPSObject());
      this.command.setArgNames(this.wizardPage.getArgumentNames());
      this.command.setArgObjects(this.wizardPage.getArguments());
      this.command.createMessage();
      return true;
   }


}
