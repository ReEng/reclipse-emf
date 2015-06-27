package org.reclipse.structure.inference.ui.matching.handlers;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.fujaba.commons.notation.DiagramElement;
import org.reclipse.structure.inference.ui.matching.Constants;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSObjectEditPart;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSNode;



/**
 * This handler is used to open or close the selected set in the object matching view.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class ToggleSetHandler extends AbstractHandler
{


   private static List<?> getSelection(ExecutionEvent event)
   {
      ISelection selection = HandlerUtil.getCurrentSelection(event);

      // preconditions
      if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
      {
         return Collections.emptyList();
      }

      return ((IStructuredSelection) selection).toList();
   }


   private static boolean isShown(DiagramElement node)
   {
      // get or create annotation
      EAnnotation anno = node.getEAnnotation(Constants.KEY_SOURCE);
      if (anno == null)
      {
         anno = EcoreFactory.eINSTANCE.createEAnnotation();
         anno.setSource(Constants.KEY_SOURCE);

         node.getEAnnotations().add(anno);
      }

      // get or create detail
      String value = anno.getDetails().get(Constants.KEY_SHOW);
      if (value == null)
      {
         value = Boolean.FALSE.toString();

         anno.getDetails().put(Constants.KEY_SHOW, value);
      }

      return Boolean.valueOf(value);
   }


   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      // get selected set nodes
      Set<AbstractNodeEditPart> sets = new HashSet<AbstractNodeEditPart>();

      // search in selection for the right one
      for (Object selected : getSelection(event))
      {
         // collect edit parts
         if (selected instanceof AbstractNodeEditPart)
         {
            EObject model = ((AbstractNodeEditPart) selected).getRealModel();
            if (model instanceof PSNode
                  && ((PSNode) model).getModifier() == ModifierType.SET)
            {
               // annotation or object set
               sets.add(((AbstractNodeEditPart) selected));
            }
            else if (model instanceof PSCombinedFragment
                  && ((PSCombinedFragment) model).getKind() == ModifierType.SET)
            {
               // set fragment
               sets.add((AbstractNodeEditPart) selected);
            }
         }
      }

      // for all selected set elements
      for (AbstractNodeEditPart part : sets)
      {
         // check if set is shown
         if (isShown(part.getModel()))
         {
            // let the part save the elements
            if (part instanceof MatchingPSObjectEditPart)
            {
               ((MatchingPSObjectEditPart) part).closeSet();
            }
         }
         else
         {
            if (part instanceof MatchingPSObjectEditPart)
            {
               ((MatchingPSObjectEditPart) part).openSet();
            }
         }
      }

      return null;
   }
}
