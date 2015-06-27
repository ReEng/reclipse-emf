package org.reclipse.structure.specification.navigator;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.part.FileEditorInput;
import org.reclipse.structure.specification.PSPatternSpecification;

import org.fujaba.commons.notation.HierarchicalNode;


public class PSNavigatorActionProvider extends CommonActionProvider
{

   private boolean isContribute;

   private OpenDiagramAction action;


   @Override
   public void init(ICommonActionExtensionSite site)
   {
      super.init(site);

      if (site.getViewSite() instanceof ICommonViewerWorkbenchSite)
      {
         isContribute = true;
         makeActions((ICommonViewerWorkbenchSite) site.getViewSite());
      }
      else
      {
         isContribute = false;
      }
   }


   private void makeActions(ICommonViewerWorkbenchSite viewerSite)
   {
      action = new OpenDiagramAction(viewerSite);
   }


   @Override
   public void fillActionBars(IActionBars bars)
   {
      if (!isContribute)
      {
         return;
      }
      IStructuredSelection sel = (IStructuredSelection) getContext()
            .getSelection();
      action.selectionChanged(sel);
      if (action.isEnabled())
      {
         bars.setGlobalActionHandler(ICommonActionConstants.OPEN, action);
      }
   }


   @Override
   public void fillContextMenu(IMenuManager menu)
   {
      // nothing to fill
   }

   /**
    * This action opens the diagram in the adequate editor.
    * 
    * @author harka
    * @author Last editor: $Author$
    * @version $Revision$ $Date$
    */
   private static class OpenDiagramAction extends Action
   {

      private HierarchicalNode node;

      private ICommonViewerWorkbenchSite site;


      public OpenDiagramAction(ICommonViewerWorkbenchSite site)
      {
         // TODO: rename this action
         super("NavigatorActionProvider_OpenDiagramActionName");
         this.site = site;
      }


      public final void selectionChanged(IStructuredSelection sel)
      {
         node = null;
         if (sel.size() == 1)
         {
            Object element = sel.getFirstElement();
            if (element instanceof PSNavigatorItem)
            {
               element = ((PSNavigatorItem) element).getItem();
            }
            else if (element instanceof IAdaptable)
            {
               element = ((IAdaptable) element)
                     .getAdapter(HierarchicalNode.class);
            }

            if (element instanceof HierarchicalNode)
            {
               HierarchicalNode diagram = (HierarchicalNode) element;
               if (diagram.getModel() instanceof PSPatternSpecification)
               {
                  node = diagram;
               }
            }
         }

         setEnabled(node != null);
      }


      @Override
      public void run()
      {
         if (node == null || node.eResource() == null)
         {
            return;
         }

         IEditorInput input = getEditorInput(node);
         IWorkbenchPage page = site.getPage();
         try
         {
            page.openEditor(input,
                  "org.reclipse.structure.specification.ui.PSCatalogEditor");
         }
         catch (PartInitException e)
         {
            PSNavigatorPlugin.getInstance().logError(
                  "Exception while opening diagram", e);
         }
      }


      private static IEditorInput getEditorInput(HierarchicalNode diagram)
      {
         Resource res = diagram.eResource();
         for (EObject content : res.getContents())
         {
            if (content == diagram)
            {
               return new FileEditorInput(WorkspaceSynchronizer.getFile(res));
            }
            if (content instanceof HierarchicalNode)
            {
               break;
            }
         }
         URI uri = EcoreUtil.getURI(diagram);
         String editorName = uri.lastSegment() + '#'
               + diagram.eResource().getContents().indexOf(diagram);
         IEditorInput editorInput = new URIEditorInput(uri, editorName);
         return editorInput;
      }
   }
}
