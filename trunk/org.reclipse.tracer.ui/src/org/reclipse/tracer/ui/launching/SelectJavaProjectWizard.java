package org.reclipse.tracer.ui.launching;


import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.wizard.Wizard;


public class SelectJavaProjectWizard extends Wizard
{
   SelectJavaProjectWizardPage selectProjectPage = null;


   @Override
   public boolean performFinish()
   {
      return true;
   }


   @Override
   public void addPages()
   {
      this.selectProjectPage = new SelectJavaProjectWizardPage("Select a Java Project");
      this.selectProjectPage.setTitle("Select a Java Project");
      this.selectProjectPage.setDescription("Select the IJavaProject that contains the Main Class");
      addPage(this.selectProjectPage);
   }


   public IJavaProject getIJavaProject()
   {
      return this.selectProjectPage.getSelectedProject();
   }


}
