/**
 * 
 */
package org.reclipse.behavior.specification.ui.wizards;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.ui.commands.CreateBehavioralPatternCommand;
import org.reclipse.behavior.specification.ui.editor.MultiPageBPCatalogEditor;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class NewBehavioralPatternWizard extends Wizard implements INewWizard
{

   private IWorkbench workbench;

   protected IStructuredSelection selection;

   protected LoadStructuralPatternWizardPage structuralPatternFilePage;

   protected LoadPSCatalogWizardPage psCatalogFilePage;

   protected LoadBPCatalogWizardPage behavioralCatalogFilePage;

   protected Resource diagram;

   private boolean openNewlyCreatedDiagramEditor = true;

   private BPCatalog catalog;

   private MultiPageBPCatalogEditor editor;


   public NewBehavioralPatternWizard(BPCatalog catalog,
         MultiPageBPCatalogEditor editor)
   {
      this.catalog = catalog;
      this.editor = editor;
   }


   public IWorkbench getWorkbench()
   {
      return this.workbench;
   }


   public IStructuredSelection getSelection()
   {
      return this.selection;
   }


   public final Resource getDiagram()
   {
      return this.diagram;
   }


   public final boolean isOpenNewlyCreatedDiagramEditor()
   {
      return this.openNewlyCreatedDiagramEditor;
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
      setNeedsProgressMonitor(true);
   }


   @Override
   public void addPages()
   {
      if (this.catalog == null)
      {
         // page to select the containing catalog
         this.behavioralCatalogFilePage = new LoadBPCatalogWizardPage(
               "CatalogFile"); //$NON-NLS-1$ //$NON-NLS-2$
         this.behavioralCatalogFilePage.setTitle("Select catalog");
         this.behavioralCatalogFilePage
               .setDescription("Select the behavioral patterns catalog the new behavioral pattern will be added to.");
         addPage(this.behavioralCatalogFilePage);
      }

      // page to select the corresponding psCatalog
      this.psCatalogFilePage = new LoadPSCatalogWizardPage("PSCatalogFile"); //$NON-NLS-1$ //$NON-NLS-2$
      this.psCatalogFilePage.setTitle("Select structural patterns catalog");
      this.psCatalogFilePage
            .setDescription("Select an existing structural patterns catalog.");
      addPage(psCatalogFilePage);

      // page to select underlying structural pattern the behavioral pattern has a reference to
      this.structuralPatternFilePage = new LoadStructuralPatternWizardPage(
            "StructuralPatternFile", this.psCatalogFilePage); //$NON-NLS-1$ //$NON-NLS-2$
      this.structuralPatternFilePage.setTitle("Select structural pattern");
      this.structuralPatternFilePage
            .setDescription("Select an existing structural pattern the created behavioral pattern will belong to.");
      addPage(this.structuralPatternFilePage);
   }


   public PSCatalog getPSCatalog()
   {
      Resource resource = loadResourceFile(this.psCatalogFilePage.getFilePath());
      return (PSCatalog) resource.getContents().get(0);
   }


   /**
    * @generated
    */
   @Override
   public boolean performFinish()
   {
      CreateBehavioralPatternCommand command = new CreateBehavioralPatternCommand(
            this.catalog, this.getStructuralPattern());
      this.editor.getCommandStack().execute(command);
      return true;
   }


   private PSPatternSpecification getStructuralPattern()
   {
      return this.structuralPatternFilePage.getPattern();
   }


   private Resource loadResourceFile(String filePath)
   {
      // Create a resource set.
      ResourceSet resourceSet = this.editor.getEditingDomain().getResourceSet();

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
