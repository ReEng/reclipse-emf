package org.reclipse.structure.specification.ui;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.archive.ArchiveURLConnection;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.notation.NotationFactory;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.ui.editor.MultiPagePSCatalogEditor;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSCatalogUtil
{

   /**
    * Opens the given diagram.
    * 
    * @param diagram The diagram to open
    * @return Returns <code>null</code> when opening failed, otherwise the IEditorPart of the opened
    *         editor.
    * @throws PartInitException
    */
   public static IEditorPart openDiagram(Resource diagram) throws PartInitException
   {
      String path = diagram.getURI().toPlatformString(true);
      IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));

      if (workspaceResource instanceof IFile)
      {
         IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
         return page.openEditor(new FileEditorInput((IFile) workspaceResource), MultiPagePSCatalogEditor.ID);
      }

      return null;
   }


   /**
    * This method should be called within a workspace modify operation since it creates resources.
    * 
    * @param diagramURI The URI of the diagram.
    * @param modelURI The URI of the model.
    * @param initialObject The initial object.
    * @param encoding The file encoding.
    * @param mon The progress monitor.
    * @return Returns the prepared resource.
    */
   public static Resource createDiagram(URI diagramURI, URI modelURI,
         final PSCatalog catalog, final String encoding, IProgressMonitor mon)
   {
      TransactionalEditingDomain editingDomain = WorkspaceEditingDomainFactory.INSTANCE
            .createEditingDomain();
      ResourceSet resources = editingDomain.getResourceSet();
      mon.beginTask("Create Diagram Progress Task", 3);
      final Resource diagramResource = resources.createResource(diagramURI);
      final Resource modelResource = resources.createResource(modelURI);
      AbstractEMFOperation command = new AbstractEMFOperation(editingDomain,
            "Create Diagram Command Label")
      {
         @Override
         protected IStatus doExecute(IProgressMonitor arg0, IAdaptable arg1)
               throws ExecutionException
         {
            // add catalog to resource
            modelResource.getContents().add(catalog);

            if (catalog.getPatternSpecifications().size() == 1)
            {
               // create and config pattern diagram model
               PSPatternSpecification patternModel = catalog
                     .getPatternSpecifications().get(0);
               HierarchicalNode patternView = NotationFactory.eINSTANCE
                     .createHierarchicalNode();
               patternView.setModel(patternModel);

               if (patternModel.getNodes().size() == 1)
               {
                  HierarchicalNode annoView = NotationFactory.eINSTANCE
                        .createHierarchicalNode();
                  annoView.setModel(patternModel.getNodes().get(0));
                  annoView.setPersistent(true);
                  annoView.setVisible(true);
                  annoView.setHeight(80);
                  annoView.setWidth(180);
                  annoView.setX(60);
                  annoView.setY(40);

                  patternView.getNodes().add(annoView);
               }

               diagramResource.getContents().add(patternView);
            }

            try
            {
               modelResource.save(PSCatalogUtil.getSaveOptions(encoding));
               diagramResource.save(PSCatalogUtil.getSaveOptions(encoding));
            }
            catch (IOException e)
            {
               PSPlugin.getDefault().logError(
                     "Unable to store model and diagram resources", e); //$NON-NLS-1$
            }

            return Status.OK_STATUS;
         }
      };
      try
      {
         OperationHistoryFactory.getOperationHistory().execute(command,
               new SubProgressMonitor(mon, 1), null);
      }
      catch (ExecutionException e)
      {
         PSPlugin.getDefault()
               .logError("Unable to create model and diagram", e); //$NON-NLS-1$
      }
      setCharset(getFile(modelResource));
      setCharset(getFile(diagramResource));
      return diagramResource;
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
         PSPlugin.getDefault().logError(
               "Unable to set charset for file " + file.getFullPath(), e); //$NON-NLS-1$
      }
   }


   /**
    * This is a copy from WorkspaceSynchronizer in GMF <br>
    * <br>
    * 
    * Obtains the workspace file corresponding to the specified resource, if it has a
    * platform-resource URI. Note that the resulting file, if not <code>null</code>, may nonetheless
    * not actually exist (as the file is just a handle).
    * <p>
    * Note that, if the <tt>resource</tt> is in an archive (such as a ZIP file) then it does not map
    * to a workspace file. In this case, however, the workspace file (if any) corresponding to the
    * containing archive can be obtained via the {@link #getUnderlyingFile(Resource)} method.
    * </p>
    * 
    * @param resource an EMF resource
    * 
    * @return the corresponding workspace file, or <code>null</code> if the resource's URI is not a
    *         platform-resource URI
    * 
    * @see #getUnderlyingFile(Resource)
    */
   public static IFile getFile(Resource resource)
   {
      if (resource == null)
      {
         return null;
      }
      ResourceSet rset = resource.getResourceSet();

      return getFile(resource.getURI(), (rset != null) ? rset.getURIConverter()
            : null, false);
   }


   /**
    * 
    * This is a copy from WorkspaceSynchronizer in GMF <br>
    * <br>
    * 
    * 
    * Finds the file corresponding to the specified URI, using a URI converter if necessary (and
    * provided) to normalize it. Creates the file if does not exist.
    * 
    * @param uri a URI
    * @param converter an optional URI converter (may be <code>null</code>)
    * 
    * @return the file, if available in the workspace
    */
   private static IFile getFile(URI uri, URIConverter converter,
         boolean considerArchives)
   {
      IFile result = null;

      if (considerArchives && uri.isArchive())
      {
         class MyArchiveURLConnection extends ArchiveURLConnection
         {
            public MyArchiveURLConnection(String url)
            {
               super(url);
            }


            public String getNestedURI()
            {
               try
               {
                  return getNestedURL();
               }
               catch (IOException exception)
               {
                  return ""; //$NON-NLS-1$
               }
            }
         }
         MyArchiveURLConnection archiveURLConnection = new MyArchiveURLConnection(
               uri.toString());
         result = getFile(URI.createURI(archiveURLConnection.getNestedURI()),
               converter, considerArchives);
      }
      else if (uri.isPlatformResource())
      {
         IPath path = new Path(uri.toPlatformString(true));
         result = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
      }
      else if (uri.isFile() && !uri.isRelative())
      {
         result = ResourcesPlugin.getWorkspace().getRoot()
               .getFileForLocation(new Path(uri.toFileString()));
      }
      else
      {
         // normalize, to see whether may we can resolve it this time
         if (converter != null)
         {
            URI normalized = converter.normalize(uri);

            if (!uri.equals(normalized))
            {
               // recurse on the new URI
               result = getFile(normalized, converter, considerArchives);
            }
         }
      }

      if ((result == null) && !uri.isRelative())
      {
         try
         {
            IFile[] files = ResourcesPlugin.getWorkspace().getRoot()
                  .findFilesForLocationURI(new java.net.URI(uri.toString()));
            if (files.length > 0)
            {
               // set the result to be the first file found
               result = files[0];
            }
         }
         catch (URISyntaxException e)
         {
            // won't get this because EMF provides a well-formed URI
         }
      }

      return result;
   }


   protected static void configure(final TransactionalEditingDomain domain)
   {
      final ResourceSet rset = domain.getResourceSet();
      // Set up a delegating resource factory registry that ensures that
      // the pathmap URI is normalized before finding a resource factory.
      final Registry existingRegistry = rset.getResourceFactoryRegistry();

      rset.setResourceFactoryRegistry(new Registry()
      {
         private Registry delegateRegistry = existingRegistry;


         @Override
         public Map<String, Object> getContentTypeToFactoryMap()
         {
            return delegateRegistry.getContentTypeToFactoryMap();
         }


         @Override
         public Map<String, Object> getExtensionToFactoryMap()
         {
            return delegateRegistry.getExtensionToFactoryMap();
         }


         @Override
         public Factory getFactory(URI uri, String contentType)
         {
            if (uri != null && uri.scheme() != null)
            {
               uri = rset.getURIConverter().normalize(uri);
            }
            return delegateRegistry.getFactory(uri, contentType);
         }


         @Override
         public Factory getFactory(URI uri)
         {
            if (uri != null && uri.scheme() != null)
            {
               uri = rset.getURIConverter().normalize(uri);
            }
            return delegateRegistry.getFactory(uri);
         }


         @Override
         public Map<String, Object> getProtocolToFactoryMap()
         {
            return delegateRegistry.getProtocolToFactoryMap();
         }
      });
   }
}
