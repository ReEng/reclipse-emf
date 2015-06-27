package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractObjectSelectionComboSection;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSAnnotationTypeSection extends
      AbstractObjectSelectionComboSection
{
   @Override
   protected Object getCurrentValue()
   {
      return getElement().getType();
   }


   @Override
   protected PSAnnotation getElement()
   {
      return (PSAnnotation) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_ANNOTATION__TYPE;
   }


   @Override
   protected String getLabelText()
   {
      return "Type";
   }


   @Override
   protected List<PSPatternSpecification> getPossibilities()
   {
      return ModelHelper.getAllValidTypes(getElement());
   }


   @Override
   protected String getValueText(Object element)
   {
      return ((PSPatternSpecification) element).getName();
   }
}
