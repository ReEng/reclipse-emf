package org.reclipse.structure.inference.ui.matching.actions;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.reclipse.structure.inference.ui.matching.edit.parts.object.MatchingPSPatternSpecificationEditPart;
import org.reclipse.structure.inference.ui.matching.views.ObjectMatchingView;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSItem;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationFactory;



public abstract class OpenSetAction extends AbstractAction implements
      IViewActionDelegate

{
   public final static int OPEN = 1;

   public final static int CLOSE = 2;

   public static int state = OPEN;

   ArrayList<PSItem> objectsInSet = new ArrayList<PSItem>();


   @Override
   public void run(IAction action)
   {
      openOrCloseObjectSet();

   }


   protected void openOrCloseObjectSet()
   {
      EditPart selectedEditPart = this.getSelectedEditParts().get(0);
      PSNode node = (PSNode) ((AbstractNodeEditPart) selectedEditPart)
            .getRealModel();

      if (node.getModifier() == ModifierType.SET)
      {
         EditPart psDiagramEditPart = selectedEditPart.getParent();
         if (psDiagramEditPart instanceof MatchingPSPatternSpecificationEditPart)
         {

            PSPatternSpecification oDiagram = ((MatchingPSPatternSpecificationEditPart) psDiagramEditPart)
                  .getRealModel();
            if (state == OPEN)
            {
               openObjectSet(node, oDiagram);
            }
            else if (state == CLOSE)
            {
               closeObjectSet(oDiagram);
            }
         }
      }
   }


   /**
    * Hides set content.
    * 
    * @param oDiagram
    */
   protected void closeObjectSet(PSPatternSpecification oDiagram)
   {
      for (PSItem obj : objectsInSet)
      {
         if (obj instanceof PSObject || obj instanceof PSAnnotation)
         {
            // TODO: translate from fujaba
//            oDiagram.removeFromElements(obj);
         }
         else
         {
            // FIXME: fujaba style
            // obj.removeYou();
         }
      }

      this.action.setText("Open Object Set");
      state = OPEN;
   }


   /**
    * Displays set content.
    * 
    * @param node
    * @param oDiagram
    */
   protected void openObjectSet(PSNode node, PSPatternSpecification oDiagram)
   {
      displayNewObjects(node, oDiagram);

      this.action.setText("Close Object Set");
      state = CLOSE;
   }


   /**
    * Creates new objects and adds them to the diagram.
    * 
    * @param node
    * @param oDiagram
    */
   protected void displayNewObjects(PSNode node, PSPatternSpecification oDiagram)
   {
      for (EObject element : ObjectMatchingView.getCurrent().getBoundObjects()
            .get(node.getName()))
      {
         PSNode newObject = createNewObject(node, element);
         addToDiagram(node, element, newObject, oDiagram);
      }
   }


   abstract protected PSNode createNewObject(PSNode node, EObject object);


   protected void addToDiagram(PSNode node, EObject object, PSNode newObject,
         PSPatternSpecification oDiagram)
   {
      // TODO: configure view of the link
      PSLink newLink = SpecificationFactory.eINSTANCE.createPSLink();
      newLink.setName(node.getName());
      newLink.setSource(node);
      newLink.setTarget(newObject);

      objectsInSet.add(newObject);
      objectsInSet.add(newLink);
      // TODO: translate from fujaba
//      oDiagram.addToElements(newObject);
   }


   public void init(IViewPart view)
   {
   }
}
