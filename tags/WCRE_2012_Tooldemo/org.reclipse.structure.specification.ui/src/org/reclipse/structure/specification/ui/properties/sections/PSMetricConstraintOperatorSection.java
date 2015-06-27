package org.reclipse.structure.specification.ui.properties.sections;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractEnumRadioSelectionSection;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSMetricConstraintOperatorSection extends
      AbstractEnumRadioSelectionSection
{

   @Override
   protected PSBooleanConstraint getElement()
   {
      return (PSBooleanConstraint) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_BOOLEAN_CONSTRAINT__OPERATOR;
   }


   @Override
   protected String getLabelText()
   {
      return "Operator";
   }


   @Override
   protected String getLiteralText(Object element)
   {
      return ModelHelper.getReadable((OperatorType) element);
   }


   @Override
   protected OperatorType getValue()
   {
      return getElement().getOperator();
   }


   @Override
   protected List<OperatorType> getLiterals()
   {
      List<OperatorType> list = new ArrayList<OperatorType>();

      for (OperatorType type : OperatorType.VALUES)
      {
         if (!type.equals(OperatorType.REGULAR_EXPRESSION))
         {
            list.add(type);
         }
      }

      return list;
   }
}
