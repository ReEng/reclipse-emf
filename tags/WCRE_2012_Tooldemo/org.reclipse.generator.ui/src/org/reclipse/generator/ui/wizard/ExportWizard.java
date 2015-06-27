package org.reclipse.generator.ui.wizard;


import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.reclipse.generator.generation.AbstractGenerator;
import org.reclipse.generator.ui.GeneratorUIActivator;
import org.reclipse.structure.specification.PSCatalog;


/**
 * @author mm
 * @author Last editor: $Author: oleg82 $
 * @version $Revision: 4520 $ $Date: 2010-08-26 19:56:11 +0200 (Do, 26 Aug 2010) $
 */
public abstract class ExportWizard extends Wizard implements IExportWizard
{

   private static final String EXPORT_WIZARD_SECTION = "ExportWizard";


   // protected GenericFujabaWizardPage modelPage;

   protected SelectCatalogWizardPage modelPage;

   protected SelectFileWizardPage filePage;

   private IStructuredSelection selection;

   private PSCatalog catalog;


   public ExportWizard()
   {
      IDialogSettings workbenchSettings = GeneratorUIActivator.getDefault()
            .getDialogSettings();
      IDialogSettings section = workbenchSettings
            .getSection(EXPORT_WIZARD_SECTION);
      if (section == null)
      {
         section = workbenchSettings.addNewSection(EXPORT_WIZARD_SECTION);
      }
      setDialogSettings(section);

      this.filePage = new SelectFileWizardPage("Select file for output.");
   }


   /**
    * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
    *      org.eclipse.jface.viewers.IStructuredSelection)
    */
   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.selection = selection;
   }


   /**
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages()
   {
      // this.modelPage = new GenericFujabaWizardPage("Select a psc file", this.selection);
      // this.modelPage.setExplanation("Choose the pattern specification catalog file.");
      // this.modelPage.addSelector("Pattern Catalog File", IFile.class,
      // ElementSelection.TRAVERSAL_UP, new FileFilter("psc"));
      // this.modelPage.enableElementSelector("model", 1, 1);
      // FujabaExplorerViewerFilter filter = new FujabaExplorerViewerFilter();
      // filter.addFilter(new FileFilter("psc"));
      // this.modelPage.setFilter(filter);
      this.modelPage = new SelectCatalogWizardPage("Select a psc file",
            selection);
      this.modelPage
            .setDescription("Choose the pattern specification catalog file.");
      addPage(this.modelPage);

      addPage(this.filePage);
   }


   public PSCatalog getSelectedModel()
   {
      if (this.catalog != null)
      {
         return this.catalog;
      }
      IFile selectedProject = this.modelPage.getFirstSelection();
      URI resourceURI = URI.createFileURI(selectedProject.getFullPath()
            .toOSString());
      ResourceSet resourceSet = new ResourceSetImpl();
      Resource resource = null;
      try
      {
         // Load the resource through the editing domain.
         resource = resourceSet.getResource(resourceURI, true);
      }
      catch (Exception e)
      {
         resource = resourceSet.getResource(resourceURI, false);
      }
      EcoreUtil.resolveAll(resourceSet);

      EObject obj = resource.getContents().get(0);
      if (obj instanceof PSCatalog)
      {
         EcoreUtil.resolveAll(resourceSet);
         this.catalog = (PSCatalog) obj;
         return this.catalog;
      }
      else
      {
         MessageDialog.openError(
               this.getShell(),
               "Error",
               "Problems occured during loading of resource "
                     + resourceURI.path());
      }
      return null;
   }


   /**
    * @see de.uni_paderborn.fujaba4eclipse.wizard.AbstractFujabaWizard#performFinish()
    */
   @Override
   public boolean performFinish()
   {
      this.filePage.finish();

      final AbstractGenerator generator = provideGenerator();

      generator.addToElementsToProcess(getSelectedModel());

      // start generator in its own thread
      final File catalogFile = new File(this.filePage.getExportFileName());

      generator.setOutputTo(catalogFile);

      generator.schedule();

      return true;
   }


   private void refreshWorkspace(final File catalogFile)
   {
      IPath path = null;
      try
      {
         path = new Path(catalogFile.getCanonicalPath());
      }
      catch (IOException e)
      {
         // do nothing, if this thing does not work, we don't care
         // it's only a refresh and the file must not be within the workspace
      }
      if (path != null)
      {
         IContainer iFile = ResourcesPlugin.getWorkspace().getRoot()
               .getContainerForLocation(path);
         if (iFile != null && iFile.getParent() != null)
         {
            try
            {
               iFile.getParent().refreshLocal(IFile.DEPTH_ONE, null);
            }
            catch (CoreException e)
            {
               e.printStackTrace();
            }
         }
      }
   }


   /**
    * Implement this method. Here you can set the plug-ins you need to compile the generated
    * sources.<br>
    * A String representing an id may look like this:<br>
    * "de.uni_paderborn.runtimetools"<br>
    * 
    * @return a String array containing plug-in ids
    */
   protected abstract Collection<String> getRequiredPlugins();


   protected abstract AbstractGenerator provideGenerator();
}
