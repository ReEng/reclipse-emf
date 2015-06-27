package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractEnumRadioSelectionSection;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PatternType;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSPatternTypeSection extends AbstractEnumRadioSelectionSection
{
   @Override
   protected PSPatternSpecification getElement()
   {
      return (PSPatternSpecification) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__TYPE;
   }


   @Override
   protected String getLabelText()
   {
      return "Type";
   }


   @Override
   protected String getLiteralText(Object element)
   {
      return ModelHelper.getReadable((PatternType) element);
   }


   @Override
   protected PatternType getValue()
   {
      return getElement().getType();
   }


   @Override
   protected List<PatternType> getLiterals()
   {
      return PatternType.VALUES;
   }
}
