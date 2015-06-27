package org.reclipse.structure.specification.ui.editor;


import java.util.EventObject;
import java.util.HashMap;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.fujaba.commons.editor.AbstractPersistableModelViewMultiPageEditor;
import org.fujaba.commons.editor.CommandStackDelegate;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.providers.ModelViewAdapterFactoryContentProvider;
import org.fujaba.commons.utils.IDUsingResourceFactory;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.provider.SpecificationItemProviderAdapterFactory;
import org.reclipse.structure.specification.ui.PSPlugin;
import org.reclipse.structure.specification.ui.properties.PSTabbedPropertySheetPage;
import org.reclipse.structure.specification.ui.utils.IFile2EditingDomainMapping;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class MultiPagePSCatalogEditor extends
      AbstractPersistableModelViewMultiPageEditor implements
      ITabbedPropertySheetPageContributor
{
   private PSCatalogOverviewPage overviewPage;

   private ComposedAdapterFactory adapterFactory;

   private PSCatalog catalog;

   public final static String ID = "org.reclipse.structure.specification.ui.PSCatalogEditor";

   public static final String TABBED_PROPERTY_CONTRIBUTOR_ID = "org.reclipse.structure.specification.ui.property.contributor"; //$NON-NLS-1$


   public MultiPagePSCatalogEditor()
   {
      super();
      initializeEditingDomain();
   }

	public void save() {
		performSaveAs(null);
	}

   /**
    * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
    */
   @Override
   protected void addPages()
   {
      overviewPage = new PSCatalogOverviewPage(this);
      try
      {
         this.addPage(overviewPage);
      }
      catch (PartInitException e)
      {
         PSPlugin.getDefault().logError("Exception while adding overview page",
               e);
      }

   }


   public void removePageOf(PSPatternSpecification pattern)
   {
      // search the correct page to be remove
      int index = -1;
      int count = 0;
      for (Object page : pages)
      {
         if (page != null && !page.equals(overviewPage)
               && page instanceof PSCatalogEditorPage)
         {
            PSCatalogEditorPage thePage = (PSCatalogEditorPage) page;

            if (pattern.equals(thePage.getDiagramModel()))
            {
               // store the index
               thePage.dispose();
               thePage.getGraphicalViewer().setContents(null);
               index = count;
               break;
            }
         }

         count++;
      }

      // remove the page
      if (index > 0 && index < getPageCount())
      {
         removePage(index);
      }
   }


   public void addPageFor(PSPatternSpecification pattern)
   {
      if (!this.catalog.getPatternSpecifications().contains(pattern))
      {
         // this pattern does not belong to this catalog
         return;
      }

      IEditorInput input = provideEditorInput(pattern);
      try
      {
         int pageIndex = findEditor(input);
         if (pageIndex < 0)
         {
            PSCatalogEditorPage page = new PSCatalogEditorPage(this);
            pageIndex = addPage(page, input);
            setTabsToCloseable(pageIndex, page);
            setPageText(pageIndex, pattern.getName());
            setPageImage(pageIndex, page.getTitleImage());
         }
         this.setActivePage(pageIndex);
      }
      catch (PartInitException e)
      {
         PSPlugin.getDefault()
               .logError("Could not create a new editor page", e);
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
      Exception exception = null;
      Resource resource = null;
      try
      {
         // Load the resource through the editing domain.
         //
         resource = editingDomain.getResourceSet().getResource(resourceURI,
               true);
      }
      catch (Exception e)
      {
         exception = e;
         PSPlugin.getDefault().logError("load on demand failed", exception);
         resource = editingDomain.getResourceSet().getResource(resourceURI,
               false);
      }

      EObject obj = resource.getContents().get(0);
      if (obj instanceof HierarchicalNode)
      {
         PSPatternSpecification pattern = (PSPatternSpecification) ((HierarchicalNode) obj)
               .getModel();
         ;
         this.catalog = pattern.getCatalog();
         this.modelResource = pattern.eResource();
         this.diagramResource = resource;
         checkMetaModel();
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
   public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter)
   {
      if (adapter == IPropertySheetPage.class)
      {
         if (adapter != null && getContributorId() != null)
         {
            return new PSTabbedPropertySheetPage(this);
         }
      }

      return super.getAdapter(adapter);
   }


   private void checkMetaModel()
   {
      MetaModel mm = ModelHelper.getMetaModel(catalog);
      if (mm == null)
      {
         MessageDialog
               .openError(
                     getSite().getShell(),
                     "Error",
                     "No abstract syntax graph meta model resource specified. \nYou might not be able to specify a type to your objecs.");
      }
      else
      {
         // load all referenced packages
         ModelHelper.getTypes(mm);
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
            .addAdapterFactory(new SpecificationItemProviderAdapterFactory());
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

      editingDomain
            .getResourceSet()
            .getResourceFactoryRegistry()
            .getExtensionToFactoryMap()
            .put(Resource.Factory.Registry.DEFAULT_EXTENSION,
                  new IDUsingResourceFactory());
   }


   protected PropertySheetPage propertySheetPage;


   @Override
   public AdapterFactoryEditingDomain getEditingDomain()
   {
      return (AdapterFactoryEditingDomain) super.getEditingDomain();
   }


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
               .setPropertySourceProvider(new ModelViewAdapterFactoryContentProvider(
                     adapterFactory));
      }
      return propertySheetPage;
   }


   public PSCatalog getCatalog()
   {
      return this.catalog;
   }


   @Override
   public String getContributorId()
   {
      return TABBED_PROPERTY_CONTRIBUTOR_ID;
   }
}
