package org.reclipse.structure.specification.ui.wizards;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.fujaba.commons.wizards.NewResourceWizardPage;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.ui.PSCatalogUtil;
import org.reclipse.structure.specification.ui.PSPlugin;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class NewPSCatalogWizard extends Wizard implements INewWizard
{
   protected IStructuredSelection selection;

   protected NewResourceWizardPage diagramModelFilePage;

   protected NewResourceWizardPage domainModelFilePage;

   protected CreateCatalogPage selectMetaModelPage;

   protected Resource diagram;

   private IWorkbench workbench;

   private boolean openNewlyCreatedDiagramEditor = true;

   private PSCatalog catalog;

   private PSPatternSpecification pattern;

   private PSAnnotation annotation;


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


   @Override
   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.workbench = workbench;
      this.selection = selection;
      setWindowTitle("New Pattern Catalog");
      setNeedsProgressMonitor(true);
   }


   @Override
   public void addPages()
   {
      domainModelFilePage = new NewResourceWizardPage(
            "DomainModelFile", getSelection(), "psc"); //$NON-NLS-1$ //$NON-NLS-2$
      domainModelFilePage.setTitle("Domain Model File");
      domainModelFilePage
            .setDescription("Select the file that will contain the domain model.");
      addPage(domainModelFilePage);

      diagramModelFilePage = new NewResourceWizardPage(
            "DiagramModelFile", getSelection(), "pscdiagram"); //$NON-NLS-1$ //$NON-NLS-2$
      diagramModelFilePage.setTitle("Diagram File");
      diagramModelFilePage
            .setDescription("Select the file that will contain the diagram.");
      addPage(diagramModelFilePage);

      selectMetaModelPage = new CreateCatalogPage("CreateCatalog");
      selectMetaModelPage.setTitle("Pattern Specification Catalog");
      selectMetaModelPage
            .setDescription("Select the options for the new pattern catalog.");
      addPage(selectMetaModelPage);
   }


   @Override
   public boolean performFinish()
   {
      IRunnableWithProgress op = new WorkspaceModifyOperation()
      {
         @Override
         protected void execute(IProgressMonitor monitor)
               throws CoreException,
                  InterruptedException
         {
            diagram = PSCatalogUtil.createDiagram(
                  diagramModelFilePage.getURI(), domainModelFilePage.getURI(),
                  getCatalog(), "UTF-8", monitor);

            if (isOpenNewlyCreatedDiagramEditor() && diagram != null)
            {
               try
               {
                  PSCatalogUtil.openDiagram(diagram);
               }
               catch (PartInitException e)
               {
                  ErrorDialog.openError(getContainer().getShell(),
                        "Creation Wizard Open Editor Error", null,
                        e.getStatus());
               }
            }
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
                  "Creation Wizard Creation Error", null,
                  ((CoreException) e.getTargetException()).getStatus());
         }
         else
         {
            PSPlugin.getDefault().logError(
                  "Error creating diagram", e.getTargetException()); //$NON-NLS-1$
         }
         return false;
      }
      return diagram != null;
   }


   /**
    * 
    * @return Returns the constructed catalog.
    */
   protected PSCatalog getCatalog()
   {
      // create catalog
      if (catalog == null)
      {
         catalog = SpecificationFactory.eINSTANCE.createPSCatalog();
      }

      // configure catalog
      catalog.setName(selectMetaModelPage.getCatalogName());
      catalog.setMetamodel(selectMetaModelPage.getSelectedMetaModel().getId());

      // create pattern
      if (pattern == null)
      {
         pattern = SpecificationFactory.eINSTANCE
               .createPSPatternSpecification();
         pattern.setCatalog(catalog);

         annotation = SpecificationFactory.eINSTANCE.createPSAnnotation();
         annotation.setType(pattern);
         annotation.setWeight(PSConstants.DEFAULT_WEIGHT);
         annotation.setPatternSpecification(pattern);
      }

      // configure pattern
      pattern.setName("Pattern1");

      return catalog;
   }
}
