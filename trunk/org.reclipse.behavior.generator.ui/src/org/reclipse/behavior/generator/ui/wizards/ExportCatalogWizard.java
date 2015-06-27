/**
 * 
 */
package org.reclipse.behavior.generator.ui.wizards;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.fujaba.commons.wizards.NewResourceWizardPage;
import org.reclipse.behavior.generator.BehavioralPatternsCatalogWriter;
import org.reclipse.behavior.generator.CatalogGenerator;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.BPPlugin;
import org.reclipse.behavior.specification.ui.wizards.LoadBPCatalogWizardPage;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class ExportCatalogWizard extends Wizard implements INewWizard
{

   private IWorkbench workbench;

   protected IStructuredSelection selection;

   protected LoadBPCatalogWizardPage bpCatalogFilePage;

   protected NewResourceWizardPage targetFilePage;

   protected ExportCatalogWizardPage exportCatalogPage;

   protected Resource resource;

   private boolean openNewlyCreatedDiagramEditor = true;


   public IWorkbench getWorkbench()
   {
      return workbench;
   }


   public IStructuredSelection getSelection()
   {
      return selection;
   }


   public final Resource getDiagram()
   {
      return resource;
   }


   public final boolean isOpenNewlyCreatedDiagramEditor()
   {
      return openNewlyCreatedDiagramEditor;
   }


   public void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor)
   {
      this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
   }


   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.workbench = workbench;
      this.selection = selection;
      setWindowTitle("Creation Wizard Title");
      setNeedsProgressMonitor(true);
   }


   @Override
   public void addPages()
   {
      bpCatalogFilePage = new LoadBPCatalogWizardPage("BehavioralPatternCatalogFile"); //$NON-NLS-1$ //$NON-NLS-2$
      bpCatalogFilePage.setTitle("Select behavioral pattern catalog");
      bpCatalogFilePage.setDescription("Select the behavioral patterns catalog to export.");
      addPage(bpCatalogFilePage);

      exportCatalogPage = new ExportCatalogWizardPage("Select behavioral patterns");
      exportCatalogPage.setDescription("Select the behavioral patterns to be exported.");
      addPage(exportCatalogPage);

      // targetFilePage = new NewResourceWizardPage(
      //            "CatalogFile", getSelection(), "xml"); //$NON-NLS-1$ //$NON-NLS-2$
      // targetFilePage.setTitle("Create file");
      // targetFilePage
      // .setDescription("Create the file that will contain the exported behavioral patterns catalog.");
      // addPage(targetFilePage);

   }


   /**
    * @generated
    */
   @Override
   public boolean performFinish()
   {
      IRunnableWithProgress op = new WorkspaceModifyOperation(null)
      {

         @Override
         protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException
         {
            // start automaton generator and save automaton to file
            List<BehavioralPattern> diagrams = exportCatalogPage.getSelectedBPDiagrams();
            BehavioralPatternsCatalog catalog = CatalogGenerator.generateCatalog(diagrams);

            File file = new File(exportCatalogPage.getCatalogFileName());
            BehavioralPatternsCatalogWriter writer = new BehavioralPatternsCatalogWriter(catalog);
            writer.save(file);
         }

      };
      try
      {
         getContainer().run(false, true, op);
      }
      catch (InterruptedException e)
      {
         return false;
      }
      catch (InvocationTargetException e)
      {
         if (e.getTargetException() instanceof CoreException)
         {
            ErrorDialog.openError(getContainer().getShell(), "Creation Wizard Creation Error", null,
                  ((CoreException) e.getTargetException()).getStatus());
         }
         else
         {
            BPPlugin.getDefault().logError("Error creating catalog", e.getTargetException()); //$NON-NLS-1$
         }
         return false;
      }

      return true;
   }


   private Resource loadResourceFile(String filePath)
   {
      // Create a resource set.
      ResourceSet resourceSet = new ResourceSetImpl();

      // Get the URI of the model file.
      URI fileURI = URI.createPlatformResourceURI(filePath, true);

      // Get the resource.
      Resource resource = resourceSet.getResource(fileURI, true);
      return resource;
   }


   public BPCatalog getBPCatalog()
   {
      Resource resource = loadResourceFile(this.bpCatalogFilePage.getFilePath());
      return (BPCatalog) resource.getContents().get(0);
   }

}
