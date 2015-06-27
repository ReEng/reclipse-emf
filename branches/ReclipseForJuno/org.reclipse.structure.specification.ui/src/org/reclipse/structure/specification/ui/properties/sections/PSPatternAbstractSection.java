package org.reclipse.structure.specification.ui.properties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractBooleanSection;
import org.reclipse.structure.specification.SpecificationPackage;


public class PSPatternAbstractSection extends AbstractBooleanSection
{
   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__ABSTRACT;
   }


   @Override
   protected String getLabelText()
   {
      return "Abstract";
   }
}
