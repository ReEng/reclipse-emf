package org.reclipse.structure.inference.ui.matching.actions;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationFactory;


public class OpenObjectSetAction extends OpenSetAction
{
   @Override
   protected PSNode createNewObject(PSNode node, EObject object)
   {
      PSObject newObject = SpecificationFactory.eINSTANCE.createPSObject();
      // FIXME: we need values from the object.
//      newObject.setName(object.getName());
//      newObject.setInstanceOf("node.getType()");
      return newObject;
   }
}
