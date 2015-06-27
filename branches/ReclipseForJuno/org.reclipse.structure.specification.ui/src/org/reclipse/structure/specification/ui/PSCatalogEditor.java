package org.reclipse.structure.specification.ui;


import java.io.IOException;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.fujaba.commons.FujabaCommonsPlugin;
import org.fujaba.commons.editor.AbstractSimpleEditorPart;
import org.fujaba.commons.editor.CommandStackDelegate;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.providers.ModelViewAdapterFactoryContentProvider;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.provider.SpecificationItemProviderAdapterFactory;
import org.reclipse.structure.specification.ui.edit.parts.PSEditPartFactory;
import org.reclipse.structure.specification.ui.utils.IFile2EditingDomainMapping;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSCatalogEditor extends AbstractSimpleEditorPart implements
      IEditingDomainProvider
{
   public final static String ID = "org.reclipse.structure.specification.ui.PSCatalogEditor";

   private ComposedAdapterFactory adapterFactory;

   private EditingDomain editingDomain;


   /**
    * Default constructor.
    */
   public PSCatalogEditor()
   {
      super();
      initializeEditingDomain();
   }


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

      // get a resource for this file.
      Resource diagramResource = getDiagram().eResource();
      Resource modelResource = getDiagramModel().eResource();// resourceSet.createResource(modelFileURI);

      // Add the book and writer objects to the contents.
      if (!diagramResource.getContents().contains(getDiagram()))
      {
         diagramResource.getContents().add(getDiagram());
      }

      // Save the contents of the resource to the file system.
      try
      {
         modelResource.save(Collections.EMPTY_MAP);
         diagramResource.save(Collections.EMPTY_MAP);

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
         setDiagram((HierarchicalNode) obj);
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
    * Loads all referenced EPackages from the meta model when possible.
    */
   private void checkMetaModel()
   {
      MetaModel mm = ModelHelper.getMetaModel(getDiagramModel());
      if (mm == null)
      {
         MessageDialog
               .openError(
                     getSite().getShell(),
                     "Error",
                     "No abstract syntax graph meta model resource specified. \nYou might not be able to specify a type to your objecs.");
      } else {
         // load all referenced packages
         ModelHelper.getTypes(mm);
      }
   }


   @Override
   protected void createEditPartFactory()
   {
      this.editPartFactory = new PSEditPartFactory();
   }


   @Override
   protected Image createEditorImage()
   {
      return PSImages.getImage(PSImages.IMAGE_OBJ_PATTERN_RULE_16);
   }


   @Override
   public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter)
   {
      if (adapter.equals(IEditingDomainProvider.class))
      {
         return this;
      }
      return super.getAdapter(adapter);
   }


   /**
    * At least, this is how the generated EMF tree editor initializes its editingdomain. Replaced
    * the BasicCommandStack by a command stack that delegates to the GEF command stack
    * implementation.
    */
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
      CommandStack commandStack = new CommandStackDelegate(
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


   /**
    * Customized, such that we can use the generated EMF.edit item providers and do not need to care
    * for manual property specification.
    * 
    * @see org.fujaba.commons.editor.AbstractSimpleEditorPart#getPropertySheetPage()
    */
   @Override
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


   @Override
   public EditingDomain getEditingDomain()
   {
      return editingDomain;
   }


   @Override
   public PSPatternSpecification getDiagramModel()
   {
      return (PSPatternSpecification) super.getDiagramModel();
   }
}
