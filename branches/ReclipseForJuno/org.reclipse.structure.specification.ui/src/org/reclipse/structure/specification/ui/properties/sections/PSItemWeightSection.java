package org.reclipse.structure.specification.ui.properties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractDoubleSection;
import org.reclipse.structure.specification.PSItem;
import org.reclipse.structure.specification.SpecificationPackage;


public class PSItemWeightSection extends AbstractDoubleSection
{
   @Override
   protected String getErrorMessage()
   {
      return "The weight has to be a valid double value.";
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_ITEM__WEIGHT;
   }


   @Override
   protected Double getFeatureDouble()
   {
      return getElement().getWeight();
   }


   @Override
   protected PSItem getElement()
   {
      return (PSItem) super.getElement();
   }


   @Override
   protected String getLabelText()
   {
      return "Weight";
   }


   @Override
   protected String getTooltip()
   {
      // TODO: describe this option with more effort
      return "The weight will be used to weight additional elements.";
   }
}
