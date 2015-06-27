/**
 * 
 */
package org.reclipse.behavior.specification.ui;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.ui.editor.MultiPageBPCatalogEditor;


public class BPCatalogUtil
{
   public static boolean openDiagram(Resource diagram) throws PartInitException
   {
      String pathString = diagram.getURI().toFileString();
      IPath path = new Path(pathString);

      IResource workspaceResource = ((IWorkspaceRoot) ResourcesPlugin
            .getWorkspace().getRoot()).getFileForLocation(path);

      if (workspaceResource instanceof IFile)
      {
         IWorkbenchPage page = PlatformUI.getWorkbench()
               .getActiveWorkbenchWindow().getActivePage();
         return null != page.openEditor(new FileEditorInput(
               (IFile) workspaceResource), MultiPageBPCatalogEditor.ID);
      }
      return false;
   }


   /**
    * This method should be called within a workspace modify operation since it creates resources.
    * 
    */
   public static Resource createDiagram(URI modelURI,
         final BPCatalog initialObject, final String encoding,
         IProgressMonitor progressMonitor)
   {
      TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
            .createEditingDomain();
      progressMonitor.beginTask("Create Diagram Progress Task", 3);
      final Resource modelResource = editingDomain.getResourceSet()
            .createResource(modelURI);
      AbstractTransactionalCommand command = new AbstractTransactionalCommand(
            editingDomain, "Create Diagram Command Label",
            Collections.EMPTY_LIST)
      {

         @Override
         protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
               IAdaptable info) throws ExecutionException
         {
            BPCatalog model = initialObject;

            attachModelToResource(model, modelResource);

            try
            {
               modelResource.save(BPCatalogUtil.getSaveOptions(encoding));
            }
            catch (IOException e)
            {

               BPPlugin.getDefault().logError(
                     "Unable to store model and diagram resources", e); //$NON-NLS-1$
            }
            return CommandResult.newOKCommandResult();
         }
      };
      try
      {
         OperationHistoryFactory.getOperationHistory().execute(command,
               new SubProgressMonitor(progressMonitor, 1), null);
      }
      catch (ExecutionException e)
      {
         BPPlugin.getDefault().logError("Unable to create model", e); //$NON-NLS-1$
      }
      setCharset(WorkspaceSynchronizer.getFile(modelResource));
      return modelResource;
   }


   public static Map<String, String> getSaveOptions()
   {
      return getSaveOptions("UTF-8");
   }


   public static Map<String, String> getSaveOptions(String encoding)
   {
      Map<String, String> saveOptions = new HashMap<String, String>();
      saveOptions.put(XMLResource.OPTION_ENCODING,
            encoding == null ? "UTF-8" : encoding); //$NON-NLS-1$
      saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
            Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
      return saveOptions;
   }


   private static void attachModelToResource(BPCatalog model, Resource resource)
   {
      resource.getContents().add(model);
   }


   public static void setCharset(IFile file)
   {
      if (file == null)
      {
         return;
      }
      try
      {
         file.setCharset("UTF-8", new NullProgressMonitor()); //$NON-NLS-1$
      }
      catch (CoreException e)
      {
         BPPlugin.getDefault().logError(
               "Unable to set charset for file " + file.getFullPath(), e); //$NON-NLS-1$
      }
   }
}
