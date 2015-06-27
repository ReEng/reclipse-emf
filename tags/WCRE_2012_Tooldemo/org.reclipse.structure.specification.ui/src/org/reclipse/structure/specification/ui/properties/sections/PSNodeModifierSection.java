package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractEnumRadioSelectionSection;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSNodeModifierSection extends AbstractEnumRadioSelectionSection
{
   @Override
   protected PSNode getElement()
   {
      return (PSNode) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_NODE__MODIFIER;
   }


   @Override
   protected String getLabelText()
   {
      return "Modifier";
   }


   @Override
   protected String getLiteralText(Object element)
   {
      return ModelHelper.getReadable((ModifierType) element);
   }


   @Override
   protected ModifierType getValue()
   {
      return getElement().getModifier();
   }


   @Override
   protected List<ModifierType> getLiterals()
   {
      return ModifierType.VALUES;
   }
}
