/**
 * 
 */
package org.reclipse.behavior.specification.ui.wizards;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
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
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.ui.BPCatalogUtil;
import org.reclipse.behavior.specification.ui.BPPlugin;
import org.reclipse.structure.specification.PSCatalog;



/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class NewBPCatalogWizard extends Wizard implements INewWizard
{

   private IWorkbench workbench;

   protected IStructuredSelection selection;

   protected LoadPSCatalogWizardPage psCatalogFilePage;

   protected NewResourceWizardPage domainModelFilePage;

   protected Resource diagram;

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
      return diagram;
   }


   public final boolean isOpenNewlyCreatedDiagramEditor()
   {
      return openNewlyCreatedDiagramEditor;
   }


   public void setOpenNewlyCreatedDiagramEditor(
         boolean openNewlyCreatedDiagramEditor)
   {
      this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
   }


   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.workbench = workbench;
      this.selection = selection;
      setWindowTitle("Creation Wizard Title");
      //      setDefaultPageImageDescriptor(UMLDiagramEditorPlugin.getBundledImageDescriptor("icons/wizban/NewUMLWizard.gif")); //$NON-NLS-1$
      setNeedsProgressMonitor(true);
   }


   @Override
   public void addPages()
   {
      // page to select underlying structural pattern the behavioral pattern has a reference to
      psCatalogFilePage = new LoadPSCatalogWizardPage("PSCatalog"); //$NON-NLS-1$ //$NON-NLS-2$
      psCatalogFilePage.setTitle("Select structural patterns catalog");
      psCatalogFilePage
            .setDescription("Select an existing structural patterns catalog the created behavioral patterns catalog will belong to.");
      addPage(psCatalogFilePage);

      domainModelFilePage = new NewResourceWizardPage(
            "DomainModelFile", getSelection(), "bp"); //$NON-NLS-1$ //$NON-NLS-2$
      domainModelFilePage.setTitle("Create model");
      domainModelFilePage
            .setDescription("Select the file that will contain the behavioral patterns model.");
      addPage(domainModelFilePage);

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
         protected void execute(IProgressMonitor monitor)
               throws CoreException,
                  InterruptedException
         {
            BPCatalog bpCatalog = BehavioralpatternFactory.eINSTANCE
                  .createBPCatalog();
            bpCatalog.setPsCatalog(getPSCatalog());
            bpCatalog.setName(bpCatalog.getPsCatalog().getName());
            diagram = BPCatalogUtil.createDiagram(domainModelFilePage.getURI(),
                  bpCatalog, "UTF-8", monitor);
         }


         private PSCatalog getPSCatalog()
         {
            Resource resource = loadPSCatalogFile(psCatalogFilePage
                  .getFilePath());
            if (resource != null)
            {
               return ((PSCatalog) resource.getContents().get(0));
            }
            return null;
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
            ErrorDialog.openError(getContainer().getShell(),
                  "Creation Wizard Creation Error", null, ((CoreException) e
                        .getTargetException()).getStatus());
         }
         else
         {
            BPPlugin.getDefault().logError(
                  "Error creating diagram", e.getTargetException()); //$NON-NLS-1$
         }
         return false;
      }

      return diagram != null;
   }


   private Resource loadPSCatalogFile(String filePath)
   {
      // Create a resource set.
      ResourceSet resourceSet = new ResourceSetImpl();
      
      // Get the URI of the model file.
      IPath path = Path.fromOSString(filePath);
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
      if (file != null && file.isAccessible())
      {  
         path = file.getFullPath();
      }
      URI fileURI = URI.createFileURI(path.toOSString());
      URI normalized = resourceSet.getURIConverter().normalize(fileURI);

      // Get the resource.
      Resource resource = resourceSet.getResource(normalized, true);
      return resource;
   }

}
