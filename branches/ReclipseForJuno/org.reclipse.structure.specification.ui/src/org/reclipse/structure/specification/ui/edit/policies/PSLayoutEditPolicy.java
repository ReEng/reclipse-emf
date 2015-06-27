package org.reclipse.structure.specification.ui.edit.policies;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.fujaba.commons.edit.commands.SetConstraintCommand;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.fujaba.commons.edit.parts.AbstractNodeViewEditPart;
import org.fujaba.commons.edit.policies.AbstractLayoutEditPolicy;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSAnnotationCommand;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSCombinedFragmentCommand;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSObjectCommand;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSSpecificationConstraintCommand;
import org.reclipse.structure.specification.ui.edit.parts.PSCombinedFragmentEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSPatternSpecificationEditPart;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This edit policy delivers a creation command for all elements on the root or on fragments.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSLayoutEditPolicy extends AbstractLayoutEditPolicy
{

   @Override
   protected Command createChangeConstraintCommand(EditPart child,
         Object constraint)
   {
      // change constraint of the children in fragments
      if (getHost() instanceof PSCombinedFragmentEditPart
            && child instanceof AbstractNodeViewEditPart)
      {
         // get constraint rectangle
         Rectangle rect = (Rectangle) constraint;

         // create collecting command
         CompoundCommand cc = new CompoundCommand();

         // add direct set constraint
         cc.add(new SetConstraintCommand((AbstractNodeViewEditPart) child, rect));

         if (child instanceof PSCombinedFragmentEditPart)
         {
            PSCombinedFragmentEditPart part = (PSCombinedFragmentEditPart) child;
            for (Object partChild : part.getChildren())
            {
               AbstractNodeViewEditPart realChild = (AbstractNodeViewEditPart) partChild;

               Rectangle childRect = new Rectangle(getRectangle(realChild.getModel()));
               // children will be positioned on old pos.
               cc.add(new SetConstraintCommand(realChild, childRect));
            }
         }

         return cc.unwrap();
      }

      return super.createChangeConstraintCommand(child, constraint);
   }


   @Override
   protected Command getCreateCommand(CreateRequest request)
   {
      if (getHost() instanceof AbstractDiagramEditPart
            && request.getNewObjectType() instanceof Class<?>)
      {
         // deliver those
         AbstractDiagramEditPart host = (AbstractDiagramEditPart) getHost();
         Class<?> type = (Class<?>) request.getNewObjectType();

         // decide which element to create
         if (PSObject.class.isAssignableFrom(type))
         {
            return createPSObjectCommand(host, request);
         }
         if (PSAnnotation.class.isAssignableFrom(type))
         {
            return createPSAnnotationCommand(host, request);
         }
         if (PSCombinedFragment.class.isAssignableFrom(type))
         {
            return createPSCombinedFragmentCommand(host, request);
         }
         if (PSSpecificationConstraint.class.isAssignableFrom(type))
         {
            return createPSSpecificationConstraintCommand(host, request);
         }
      }
      return null;
   }


   /**
    * This method creates the command to create a combined fragment with the specified parameters if
    * possible and returns it.
    * 
    * @param host The host on which to create the element.
    * @param req The create request.
    * @return Returns the created command or <code>null</code>.
    */
   private Command createPSSpecificationConstraintCommand(
         AbstractDiagramEditPart host, CreateRequest req)
   {
      Rectangle bounds = (Rectangle) getConstraintFor(req);

      return new CreatePSSpecificationConstraintCommand(host, bounds);
   }


   /**
    * This method creates the command to create a combined fragment with the specified parameters if
    * possible and returns it.
    * 
    * @param host The host on which to create the element.
    * @param req The create request.
    * @return Returns the created command or <code>null</code>.
    */
   private Command createPSAnnotationCommand(AbstractDiagramEditPart host,
         CreateRequest req)
   {
      Rectangle bounds = (Rectangle) getConstraintFor(req);

      return new CreatePSAnnotationCommand(host, bounds);
   }


   /**
    * This method creates the command to create a combined fragment with the specified parameters if
    * possible and returns it.
    * 
    * @param host The host on which to create the element.
    * @param req The create request.
    * @return Returns the created command or <code>null</code>.
    */
   private Command createPSObjectCommand(AbstractDiagramEditPart host,
         CreateRequest req)
   {
      Rectangle bounds = (Rectangle) getConstraintFor(req);

      return new CreatePSObjectCommand(host, bounds);
   }


   /**
    * This method creates the command to create a combined fragment with the specified parameters if
    * possible and returns it.
    * 
    * @param host The host on which to create the element.
    * @param req The create request.
    * @return Returns the created command or <code>null</code>.
    */
   private Command createPSCombinedFragmentCommand(
         AbstractDiagramEditPart host, CreateRequest req)
   {
      Rectangle bounds = (Rectangle) getConstraintFor(req);

      List<Node> elements = new ArrayList<Node>();
      addPartsInside(elements, host, bounds);
      PSCombinedFragment newFragment = (PSCombinedFragment) req.getNewObject();

      CompoundCommand cc = new CompoundCommand();
      if (isValidFragment(host.getRealModel(), newFragment, elements))
      {
         if (newFragment.getKind() == ModifierType.SET)
         {
            // behaviour on set fragments
            cc.add(new CreatePSCombinedFragmentCommand(host, newFragment,
                  bounds, elements, true));
         } else {
            // add change constraint commands for the elements
            List<AbstractNodeEditPart> children = getDirectPartsInside(host, bounds);
            List<Node> cNodes = new ArrayList<Node>();
            for (AbstractNodeEditPart child : children)
            {
               Rectangle constraint = getRectangle(child.getModel());
               constraint.x -= (bounds.x + 1);
               constraint.y -= (bounds.y + 20);
               cc.add(new SetConstraintCommand(child, constraint));
               cNodes.add(child.getModel());
            }
            
            // add create fragment command
            cc.add(new CreatePSCombinedFragmentCommand(host, newFragment,
                  bounds, cNodes, false));
         }
      }

      return cc;
   }


   private boolean isValidFragment(EObject hostModel,
         PSCombinedFragment newFrag, List<Node> nodes)
   {
      // collect real model items
      List<PSCombinedFragmentItem> items = new ArrayList<PSCombinedFragmentItem>();
      for (Node node : nodes)
      {
         if (node.getModel() instanceof PSCombinedFragmentItem)
         {
            items.add((PSCombinedFragmentItem) node.getModel());
         }
      }

      // break when empty
      if (items.isEmpty())
      {
         return false;
      }


      // NEGATIVE FRAGMENTS
      if (newFrag.getKind() == ModifierType.NEGATIVE)
      {
         // disallow on other negative fragments
         if (hostModel instanceof PSCombinedFragment
               && ((PSCombinedFragment) hostModel).getKind() == ModifierType.NEGATIVE)
         {
            return false;
         }

         // check children
         for (PSCombinedFragmentItem item : items)
         {
            // no other fragments as child
            if (item instanceof PSCombinedFragment)
            {
               return false;
            }

            // no element with a negative fragment as parent
            for (PSCombinedFragment parent : item.getParents())
            {
               // dont allow a chain of negative fragments
               if (parent.getKind() == ModifierType.NEGATIVE)
               {
                  return false;
               }

               // dont allow nested items
               if (!parent.getParents().isEmpty())
               {
                  return false;
               }
            }
         }
      }

      // ADDITIONAL FRAGMENTS
      else if (newFrag.getKind() == ModifierType.ADDITIONAL)
      {
         // check children
         for (PSCombinedFragmentItem item : items)
         {
            // no other fragment with deep > 1 as child
            if (item instanceof PSCombinedFragment)
            {
               if (getDepth((PSCombinedFragment) item) > 1)
               {
                  return false;
               }
            }

            // check existing parents
            for (PSCombinedFragment parent : item.getParents())
            {
               if (!parent.getParents().isEmpty())
               {
                  return false;
               }
            }
         }
      }

      return true;
   }


   private int getDepth(PSCombinedFragment item)
   {
      int depth = 0;
      for (PSCombinedFragmentItem child : item.getChildren())
      {
         if (child instanceof PSCombinedFragment)
         {
            depth += getDepth((PSCombinedFragment) child);
         }
      }

      return depth + 1;
   }


   private List<AbstractNodeEditPart> getDirectPartsInside(AbstractDiagramEditPart host,
         Rectangle bounds)
   {
      List<AbstractNodeEditPart> list = new ArrayList<AbstractNodeEditPart>();
      for (Object child : host.getChildren())
      {
         if (child instanceof AbstractNodeEditPart)
         {
            AbstractNodeEditPart cPart = (AbstractNodeEditPart) child;

            // check if figure is inside the bounds
            if (bounds.contains(getRectangle(cPart.getModel())))
            {
               // dont add a create annotation
               if (!(cPart.getRealModel() instanceof PSAnnotation && ModelHelper
                     .isCreate((PSAnnotation) cPart.getRealModel())))
               {
                  list.add(cPart);
               }
            }
         }
      }

      return list;
   }


   private Rectangle getRectangle(Node model)
   {
      int x = model.getX();
      int y = model.getY();
      int w = model.getWidth();
      int h = model.getHeight();

      return new Rectangle(x, y, w, h);
   }


   /**
    * Searches recursively for elements within the specified constraint and collects them.
    * 
    * @param items The list to add to.
    * @param part The host on which to start the search.
    * @param bounds The constraint in which to search for adequate elements.
    * @return Returns a list of the contained elements.
    */
   private List<Node> addPartsInside(List<Node> items,
         AbstractDiagramEditPart part, Rectangle bounds)
   {
      // check if figure is inside the bounds
      if (bounds.contains(getRectangle(part.getModel())))
      {
         // dont add a create annotation
         if (!(part.getRealModel() instanceof PSAnnotation && ModelHelper
               .isCreate((PSAnnotation) part.getRealModel())))
         {
            items.add(part.getModel());
         }
      }

      // add children as well
      if (part instanceof PSCombinedFragmentEditPart
            || part instanceof PSPatternSpecificationEditPart)
      {
         for (Object child : part.getChildren())
         {
            if (child instanceof AbstractDiagramEditPart)
            {
               addPartsInside(items, (AbstractDiagramEditPart) child, bounds);
            }
         }
      }

      return items;
   }
}
