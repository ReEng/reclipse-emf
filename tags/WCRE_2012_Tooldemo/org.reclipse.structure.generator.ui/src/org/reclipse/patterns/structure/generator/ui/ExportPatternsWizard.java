/**
 * Copyright 2011 University of Paderborn. All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors: Aljoscha Hark - initial API and implementation.
 */
package org.reclipse.patterns.structure.generator.ui;


import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.reclipse.patterns.structure.generator.ui.internal.Activator;


public class ExportPatternsWizard extends Wizard implements IExportWizard
{
   private ExportPatternsWizardPage page;


   public ExportPatternsWizard()
   {
      super();

      // get settings
      String sectionName = ExportPatternsWizard.class.getSimpleName();
      IDialogSettings plugin = Activator.getInstance().getDialogSettings();
      IDialogSettings settings = plugin.getSection(sectionName);
      if (settings == null)
      {
         settings = plugin.addNewSection(sectionName);
      }

      // configure wizard
      setWindowTitle("Export Pattern Engines");
      ImageDescriptor imageDescriptor = Activator.getInstance().getImageDescriptor(Activator.IMG_BANNER);
      setDefaultPageImageDescriptor(imageDescriptor);
      setDialogSettings(settings);

      page = new ExportPatternsWizardPage();
   }


   @Override
   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      page.init(workbench, selection);
   }


   @Override
   public void addPages()
   {
      super.addPages();

      addPage(page);
   }


   @Override
   public boolean performFinish()
   {
      page.saveSettings();

      ExportPatternsJob job = new ExportPatternsJob(page.getInputPath(), page.getOutputPath(), page.getReportLevel());
      job.schedule();

      return true;
   }
}
