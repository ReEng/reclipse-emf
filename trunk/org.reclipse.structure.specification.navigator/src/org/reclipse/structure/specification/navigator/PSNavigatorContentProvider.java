package org.reclipse.structure.specification.navigator;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.reclipse.structure.specification.PSPatternSpecification;

import org.fujaba.commons.notation.HierarchicalNode;


/**
 * This class provides the content for the navigator of a
 * {@link org.reclipse.structure.specification.PSCatalog PSCatalog}.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSNavigatorContentProvider implements ITreeContentProvider
{

   private Viewer viewer;

   private AdapterFactoryEditingDomain editingDomain;

   private WorkspaceSynchronizer synchronizer;

   private Runnable refresher;


   /**
    * The default constructor.
    */
   public PSNavigatorContentProvider()
   {
      TransactionalEditingDomain ted = WorkspaceEditingDomainFactory.INSTANCE
            .createEditingDomain();
      editingDomain = (AdapterFactoryEditingDomain) ted;
      editingDomain.setResourceToReadOnlyMap(new HashMap<Resource, Boolean>()
      {
         private static final long serialVersionUID = 1L;


         @Override
         public Boolean get(Object key)
         {
            if (key instanceof Resource && !containsKey(key))
            {
               put((Resource) key, Boolean.TRUE);
            }

            return super.get(key);
         }
      });

      refresher = new Runnable()
      {
         @Override
         public void run()
         {
            if (viewer != null)
            {
               viewer.refresh();
            }
         }
      };

      synchronizer = new WorkspaceSynchronizer(ted,
            new WorkspaceSynchronizer.Delegate()
            {
               @Override
               public void dispose()
               {
                  // nothing to dispose
               }


               @Override
               public boolean handleResourceChanged(final Resource res)
               {
                  unloadAllResources();
                  refresh();
                  return true;
               }


               @Override
               public boolean handleResourceDeleted(Resource res)
               {
                  unloadAllResources();
                  refresh();
                  return true;
               }


               @Override
               public boolean handleResourceMoved(Resource res, final URI uri)
               {
                  unloadAllResources();
                  refresh();
                  return true;
               }
            });
   }


   private Set<PSNavigatorItem> createNavigatorItems(List<EObject> contents,
         Object parent)
   {
      // prepare result
      Set<PSNavigatorItem> result = new HashSet<PSNavigatorItem>();

      // collect elements
      for (EObject object : contents)
      {
         // TODO: understand PSC file content, too!
         if (object instanceof HierarchicalNode
               && ((HierarchicalNode) object).getModel() instanceof PSPatternSpecification)
         {
            result.add(new PSNavigatorItem((HierarchicalNode) object, parent));
         }
         
         if (object instanceof PSPatternSpecification)
         {
            // FIXME: syso
            System.out.println("i am am a domain type");
         }
      }

      return result;
   }


   @Override
   public void dispose()
   {
      synchronizer.dispose();
      synchronizer = null;
      refresher = null;
      viewer = null;
      unloadAllResources();
      ((TransactionalEditingDomain) editingDomain).dispose();
      editingDomain = null;
   }


   @Override
   public Object[] getChildren(Object parent)
   {
      if (parent instanceof IFile)
      {
         // prepare file reading
         IFile file = (IFile) parent;
         URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(),
               true);
         Resource res = editingDomain.getResourceSet().getResource(uri, true);

         // return collected diagram and/or model elements
         return createNavigatorItems(res.getContents(), parent).toArray();
      }

      if (parent instanceof PSNavigatorItem)
      {
         return ((PSNavigatorItem) parent).getChildren().toArray();
      }

      return new Object[0];
   }


   @Override
   public Object[] getElements(Object input)
   {
      return getChildren(input);
   }


   @Override
   public Object getParent(Object element)
   {
      if (element instanceof PSNavigatorItem)
      {
         return ((PSNavigatorItem) element).getParent();
      }

      return null;
   }


   @Override
   public boolean hasChildren(Object element)
   {
      return element instanceof IFile || getChildren(element).length > 0;
   }


   @Override
   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      this.viewer = viewer;
   }


   protected void refresh()
   {
      if (viewer != null && !viewer.getControl().isDisposed())
      {
         viewer.getControl().getDisplay().asyncExec(refresher);
      }
   }


   protected void unloadAllResources()
   {
      for (Resource res : editingDomain.getResourceSet().getResources())
      {
         res.unload();
      }
   }
}
