package org.reclipse.structure.specification.ui.properties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractBooleanSection;
import org.reclipse.structure.specification.SpecificationPackage;


public class PSSpecificationConstraintAdditionalSection extends
      AbstractBooleanSection
{
   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_SPECIFICATION_CONSTRAINT__ADDITIONAL;
   }


   @Override
   protected String getLabelText()
   {
      return "Additional";
   }
}
