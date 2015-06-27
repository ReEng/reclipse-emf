package org.reclipse.structure.specification.ui.handlers;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.MultiPageEditorSite;
import org.reclipse.structure.specification.ui.edit.parts.PSAnnotationEditPart;
import org.reclipse.structure.specification.ui.editor.MultiPagePSCatalogEditor;
import org.reclipse.structure.specification.ui.editor.PSCatalogEditorPage;


/**
 * This handler opens the related pattern specification from the selected annotation.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class OpenPatternSpecificationHandler extends AbstractHandler
{

   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      if (HandlerUtil.getCurrentSelection(event) instanceof IStructuredSelection)
      {
         IStructuredSelection selection = (IStructuredSelection) HandlerUtil
               .getCurrentSelection(event);

         if (selection.size() == 1)
         {
            if (selection.getFirstElement() instanceof PSAnnotationEditPart)
            {
               PSAnnotationEditPart anno = (PSAnnotationEditPart) selection
                     .getFirstElement();

               if (anno.getViewer().getEditDomain() instanceof DefaultEditDomain)
               {
                  DefaultEditDomain ed = (DefaultEditDomain) anno.getViewer()
                        .getEditDomain();
                  if (ed.getEditorPart() instanceof PSCatalogEditorPage)
                  {
                     PSCatalogEditorPage page = (PSCatalogEditorPage) ed
                           .getEditorPart();
                     if (page.getEditorSite() instanceof MultiPageEditorSite)
                     {
                        MultiPageEditorSite site = (MultiPageEditorSite) page
                              .getEditorSite();

                        if (site.getMultiPageEditor() instanceof MultiPagePSCatalogEditor)
                        {
                           MultiPagePSCatalogEditor editor = (MultiPagePSCatalogEditor) site
                                 .getMultiPageEditor();

                           editor.addPageFor(anno.getRealModel().getType());
                        }
                     }
                  }
               }
            }
         }
      }

      return null;
   }
}
