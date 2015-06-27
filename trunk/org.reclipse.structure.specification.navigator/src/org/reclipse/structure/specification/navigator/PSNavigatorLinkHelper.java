package org.reclipse.structure.specification.navigator;


import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;
import org.eclipse.ui.part.FileEditorInput;

import org.fujaba.commons.notation.HierarchicalNode;


public class PSNavigatorLinkHelper implements ILinkHelper
{

   private static IEditorInput getEditorInput(EObject item)
   {
      Resource res = item.eResource();
      for (EObject content : res.getContents())
      {
         if (content == item)
         {
            return new FileEditorInput(WorkspaceSynchronizer.getFile(res));
         }
         if (content instanceof HierarchicalNode)
         {
            break;
         }
      }

      URI uri = EcoreUtil.getURI(item);
      String name = uri.lastSegment() + '#'
            + item.eResource().getContents().indexOf(item);
      IEditorInput input = new URIEditorInput(uri, name);
      return input;
   }


   @Override
   public IStructuredSelection findSelection(IEditorInput anInput)
   {
      // IDiagramDocument document = PSDiagramEditorPlugin.getInstance()
      // .getDocumentProvider().getDiagramDocument(anInput);
      // if (document == null)
      // {
      // return StructuredSelection.EMPTY;
      // }
      // HierarchicalNode diagram = document.getDiagram();
      // IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
      // if (file != null)
      // {
      // PSNavigatorItem item = new PSNavigatorItem(diagram, file, false);
      // return new StructuredSelection(item);
      // }
      return StructuredSelection.EMPTY;
   }


   @Override
   public void activateEditor(IWorkbenchPage aPage,
         IStructuredSelection aSelection)
   {
      // check if something has been selected
      if (aSelection == null || aSelection.isEmpty())
      {
         return;
      }

      // check if selection can be handled
      if (aSelection.getFirstElement() instanceof PSNavigatorItem == false)
      {
         return;
      }

      EObject item = ((PSNavigatorItem) aSelection.getFirstElement()).getItem();

      // try to get correct editor input
      IEditorPart editor = aPage.findEditor(getEditorInput(item));
      if (editor == null)
      {
         return;
      }

      aPage.bringToTop(editor);
      // if (editor instanceof DiagramEditor)
      // {
      // DiagramEditor diagramEditor = (DiagramEditor) editor;
      // ResourceSet diagramEditorResourceSet = diagramEditor
      // .getEditingDomain().getResourceSet();
      // EObject selectedView = diagramEditorResourceSet.getEObject(
      // EcoreUtil.getURI(item), true);
      // if (selectedView == null)
      // {
      // return;
      // }
      // GraphicalViewer graphicalViewer = (GraphicalViewer) diagramEditor
      // .getAdapter(GraphicalViewer.class);
      // EditPart selectedEditPart = (EditPart) graphicalViewer
      // .getEditPartRegistry().get(selectedView);
      // if (selectedEditPart != null)
      // {
      // graphicalViewer.select(selectedEditPart);
      // }
      // }
   }
}
