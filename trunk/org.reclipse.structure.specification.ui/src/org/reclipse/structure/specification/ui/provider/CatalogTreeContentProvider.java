package org.reclipse.structure.specification.ui.provider;


import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.fujaba.commons.editor.AbstractSimpleEditorPart;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.ui.utils.IFile2EditingDomainMapping;
import org.reclipse.structure.specification.ui.utils.PSComposedAdapterFactory;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CatalogTreeContentProvider extends AdapterFactoryContentProvider
      implements ITreeContentProvider
{
   private IDoubleClickListener listener = new IDoubleClickListener()
   {

      @Override
      public void doubleClick(DoubleClickEvent event)
      {
         if (event.getSource() instanceof CommonViewer
               && event.getSelection() instanceof TreeSelection)
         {
            Object o = ((TreeSelection) event.getSelection())
                  .getFirstElement();
            if (o instanceof HierarchicalNode)
            {
               HierarchicalNode diagram = (HierarchicalNode) o;
               IWorkbenchPage page = PlatformUI.getWorkbench()
                     .getActiveWorkbenchWindow().getActivePage();
               IEditorPart part = page.getActiveEditor();
               if (part instanceof AbstractSimpleEditorPart)
               {
                  ((AbstractSimpleEditorPart) part).setDiagram(diagram);
               }
            }
         }
      }
   };
   
   /**
    * Default constructor.
    */
   public CatalogTreeContentProvider()
   {
      super(PSComposedAdapterFactory.getAdapterFactory());
   }


   @Override
   public Object[] getChildren(Object parentElement)
   {
      if (parentElement instanceof IFile)
      {

         IFile file = ((IFile) parentElement);
         String path = file.getFullPath().toString();
         URI uri = URI.createPlatformResourceURI(path, true);
         EditingDomain editingDomain = IFile2EditingDomainMapping.getDomain(file);
         if(editingDomain != null)
         {
            parentElement = editingDomain.getResourceSet().getResource(uri, true);
         }
      }
      if (parentElement instanceof PSCatalog
            || parentElement instanceof Resource)
      {
         return super.getChildren(parentElement);
      }
      else
      {
         return Collections.emptyList().toArray();
      }
   }


   @Override
   public Object getParent(Object element)
   {
      if (element instanceof IFile)
         return ((IResource) element).getParent();
      return super.getParent(element);
   }


   @Override
   public boolean hasChildren(Object element)
   {
      if (element instanceof IFile || element instanceof PSCatalog
            || element instanceof Resource)
         return true;
      return false;
   }


   @Override
   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      super.inputChanged(viewer, oldInput, newInput);

      // FIXME work out this idea and stop adding further listener
      if (viewer instanceof CommonViewer)
      {
         CommonViewer cv = (CommonViewer) viewer;
         cv.addDoubleClickListener(this.listener);
      }
   }
}
