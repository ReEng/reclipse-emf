package org.reclipse.structure.specification.ui.properties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractDoubleSection;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.SpecificationPackage;


public class PSMetricConstraintValueSection extends AbstractDoubleSection
{

   @Override
   protected PSMetricConstraint getElement()
   {
      return (PSMetricConstraint) super.getElement();
   }


   @Override
   protected String getErrorMessage()
   {
      return "The metric value has to be a valid double value.";
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION;
   }


   @Override
   protected Double getFeatureDouble()
   {
      // get value
      String value = getElement().getValueExpression();

      // check
      if (value != null && !value.isEmpty())
      {
         return Double.valueOf(value.trim());
      }
      else
      {
         return new Double(0);
      }
   }


   @Override
   protected String getLabelText()
   {
      return "Value";
   }


   @Override
   protected Object getNewFeatureValue(String newText)
   {
      return newText;
   }


   @Override
   protected String getOldFeatureValue()
   {
      return getElement().getValueExpression();
   }


   @Override
   protected String getTooltip()
   {
      // TODO: describe this option with more effort
      return "The value will be analyzed during an inference.";
   }
}
