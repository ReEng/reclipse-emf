/**
 * 
 */
package org.reclipse.behavior.generator.ui.wizards;


import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
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
import org.reclipse.behavior.generator.tracedefinition.TraceDefinitionGenerator;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.BPPlugin;
import org.reclipse.behavior.specification.ui.wizards.LoadBPCatalogWizardPage;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.model.definition.xml.ITraceDefinitionConstants;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionWriter;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class ExportTraceDefinitionWizard extends Wizard implements INewWizard
{

   private IWorkbench workbench;

   protected IStructuredSelection selection;

   protected LoadBPCatalogWizardPage bpCatalogFilePage;

   protected NewResourceWizardPage targetFilePage;

   protected ExportTraceDefinitionWizardPage exportTraceDefinitionPage;

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

      exportTraceDefinitionPage = new ExportTraceDefinitionWizardPage("Select behavioral patterns and annotations");
      exportTraceDefinitionPage
            .setDescription("Select the behavioral patterns to be exported and a file that contains the annotations from the structural inference.");
      addPage(exportTraceDefinitionPage);

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
            // start trace definition generator and save to file
            if (canFinish())
            {
               try
               {
                  List<BehavioralPattern> diagrams = exportTraceDefinitionPage.getSelectedBPDiagrams();
                  List<ASGAnnotation> annotations = exportTraceDefinitionPage.getAnnotations();
                  TraceDefinition traceDefinition = createTraceDefinitionGenerator(diagrams, annotations)
                        .generateTraceDefinition();
                  TraceDefinitionWriter writer = new TraceDefinitionWriter(traceDefinition);
                  String traceDefinitionFileName = exportTraceDefinitionPage.getTraceDefinitionFileName();
                  boolean zipped = traceDefinitionFileName.endsWith(ITraceDefinitionConstants.ZIP_FILE_SUFFIX);
                  boolean saved = writer.save(traceDefinitionFileName, zipped);

                  if (saved)
                  {
                     exportTraceDefinitionPage.finish();
                  }
               }
               catch (FileNotFoundException e)
               {
               }
               try
               {
                  ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
               }
               catch (CoreException e)
               {
                  e.printStackTrace();
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
            ErrorDialog.openError(getContainer().getShell(), "Creation Wizard Creation Error", null,
                  ((CoreException) e.getTargetException()).getStatus());
         }
         else
         {
            BPPlugin.getDefault().logError("Error creating trace definition", e.getTargetException()); //$NON-NLS-1$
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


   protected TraceDefinitionGenerator createTraceDefinitionGenerator(List<BehavioralPattern> patterns,
         List<ASGAnnotation> annotations)
   {
      TraceDefinitionGenerator generator = new TraceDefinitionGenerator(patterns, annotations);
      return generator;
   }
}
