/**
 * 
 */
package org.reclipse.behavior.specification.ui.editor;

import java.io.IOException;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.fujaba.commons.FujabaCommonsPlugin;
import org.fujaba.commons.editor.AbstractPersistableModelViewMultiPageEditor;
import org.fujaba.commons.editor.CommandStackDelegate;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.BPPlugin;
import org.reclipse.behavior.specification.ui.providers.AdvancedBehavioralPatternItemProviderAdapterFactory;
import org.reclipse.behavior.specification.ui.util.BPCatalogContentProvider;
import org.reclipse.behavior.specification.ui.util.IFile2EditingDomainMapping;
import org.reclipse.metamodel.MetaModel;

import de.uni_paderborn.basicSequenceDiagram.provider.BasicSequenceDiagramItemProviderAdapterFactory;

/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class MultiPageBPCatalogEditor extends AbstractPersistableModelViewMultiPageEditor
{
   private BPCatalogOverviewPage overviewPage;
   
   private ComposedAdapterFactory adapterFactory;
   
   private BPCatalog catalog;
   
   private MetaModel metaModel;

   public final static String ID = "org.reclipse.behavior.specification.ui.BPCatalogEditor";
   
   
   
   public MultiPageBPCatalogEditor()
   {
      super();
      initializeEditingDomain();
   }


   /**
    * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
    */
   @Override
   protected void addPages()
   {
      overviewPage = new BPCatalogOverviewPage(this);
      try
      {
         this.addPage(overviewPage);
      }
      catch (PartInitException e)
      {
         BPPlugin.getDefault().logError("Exception while adding overview page", e);
      }

   }
   
   public void addPageFor(BehavioralPattern pattern)
   {
      if(!this.catalog.getBehavioralPatterns().contains(pattern))
      {
         //this pattern does not belong to this catalog
         return;
      }
      
      IEditorInput input = provideEditorInput(pattern);
      try
      {
         int pageIndex = findEditor(input);
         if(pageIndex < 0)
         {
            BPCatalogEditorPage page = new BPCatalogEditorPage(this);
            pageIndex = addPage(page, input);
            setTabsToCloseable(pageIndex, page);
            setPageText(pageIndex, pattern.getName());
         }
         this.setActivePage(pageIndex);
      }
      catch (PartInitException e)
      {
         BPPlugin.getDefault().logError("Could not create a new editor page", e);
      }
   }
   
   
   @Override
   protected void setInput(IEditorInput input)
   {
      setInputWithNotify(input);
      if (input instanceof IFileEditorInput)
      {
         IFileEditorInput fileInput = (IFileEditorInput) input;
         IFile2EditingDomainMapping.register(fileInput.getFile(),
               this.editingDomain);
      }

      setPartName(input.getName());
      loadModel();
   }


   /**
    * This is the method called to load a resource into the editing domain's resource set based on
    * the editor's input. <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated
    */
   public void loadModel()
   {
      URI resourceURI = EditUIUtil.getURI(getEditorInput());
      URI normalizedURI = editingDomain.getResourceSet().getURIConverter().normalize(resourceURI);
      Exception exception = null;
      Resource resource = null;
      
      try
      {
         // Load the resource through the editing domain.
         resource = editingDomain.getResourceSet().getResource(normalizedURI,
               true);
      }
      catch (Exception e)
      {
         exception = e;
         BPPlugin.getDefault().logError("load on demand failed", exception);
         resource = editingDomain.getResourceSet().getResource(normalizedURI,
               false);
      }
      EcoreUtil.resolveAll(editingDomain.getResourceSet());

      EObject obj = resource.getContents().get(0);
      if (obj instanceof BPCatalog)
      {
         this.catalog = (BPCatalog) obj;
         this.modelResource = this.catalog.eResource();
      }
      else
      {
         MessageDialog.openError(
               getSite().getShell(),
               "Error",
               "Problems occured during loading of resource "
                     + resourceURI.path());
      }
   }

   /**
    * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
    */
   @Override
   public Object getAdapter(@SuppressWarnings("unchecked") Class adapter)
   {
      if (adapter == IPropertySheetPage.class)
      {
         return getPropertySheetPage();
      }
      else
      {
         return super.getAdapter(adapter);
      }
   }

   
   /**
    * At least, this is how the generated EMF tree editor initializes its editingdomain. Replaced
    * the BasicCommandStack by a command stack that delegates to the GEF command stack
    * implementation.
    */
   @Override
   protected void initializeEditingDomain()
   {
      // Create an adapter factory that yields item providers.
      //
      adapterFactory = new ComposedAdapterFactory(
            ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
      adapterFactory
            .addAdapterFactory(new ResourceItemProviderAdapterFactory());
      adapterFactory
            .addAdapterFactory(new AdvancedBehavioralPatternItemProviderAdapterFactory());
      adapterFactory
            .addAdapterFactory(new BasicSequenceDiagramItemProviderAdapterFactory());
      adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
      adapterFactory
            .addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

      // Create the command stack that will notify this editor as commands are executed.
      //
      org.eclipse.emf.common.command.CommandStack commandStack = new CommandStackDelegate(
            this.getCommandStack());

      // Add a listener to set the most recent command's affected objects to be the selection of the
      // viewer with focus.
      //
      commandStack.addCommandStackListener(new CommandStackListener()
      {
         public void commandStackChanged(final EventObject event)
         {
            getSite().getShell().getDisplay().asyncExec(new Runnable()
            {
               public void run()
               {
                  firePropertyChange(PROP_DIRTY);

                  if (propertySheetPage != null
                        && !propertySheetPage.getControl().isDisposed())
                  {
                     propertySheetPage.refresh();
                  }
               }
            });
         }
      });

      // Create the editing domain with a special command stack.
      //
      editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
            commandStack, new HashMap<Resource, Boolean>());
   }
   
   
   
   protected PropertySheetPage propertySheetPage;
   
   
   /**
    * Customized, such that we can use the generated EMF.edit item providers and do not need to care
    * for manual property specification.
    * 
    * @see org.fujaba.commons.editor.AbstractSimpleEditorPart#getPropertySheetPage()
    */
   protected PropertySheetPage getPropertySheetPage()
   {
      if (propertySheetPage == null)
      {
         propertySheetPage = new PropertySheetPage();
         propertySheetPage
               .setPropertySourceProvider(new BPCatalogContentProvider(
                     adapterFactory));
      }
      return propertySheetPage;
   }
   
   
   public BPCatalog getCatalog()
   {
      return this.catalog;
   }

   
   @Override
   protected void performSaveAs(IFile diagramFile)
   {
      // Create a resource set.
      ResourceSet resourceSet = editingDomain.getResourceSet();

      // Register the default resource factory -- only needed for stand-alone!
      resourceSet
            .getResourceFactoryRegistry()
            .getExtensionToFactoryMap()
            .put(Resource.Factory.Registry.DEFAULT_EXTENSION,
                  new XMIResourceFactoryImpl());
      
      // Save the contents of the resource to the file system.
      try
      {
         modelResource.save(Collections.EMPTY_MAP);
      }
      catch (IOException e)
      {
         MessageDialog.openError(Display.getCurrent().getActiveShell(),
               "Save Error",
               "During saving of the latest changes an exception occurred: "
                     + e.getMessage() + "\nsee Error Log for Stacktrace");
         FujabaCommonsPlugin.getDefault().logError(e.getMessage(), e);
      }
   }
}
