package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Button;
import org.fujaba.commons.properties.section.AbstractEnumRadioSelectionSection;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSAttributeConstraintOperatorSection extends
      AbstractEnumRadioSelectionSection
{

   @Override
   protected void handleModelChanged(Notification msg)
   {
      if (msg.getFeature() != null
            && msg.getFeature()
                  .equals(
                        SpecificationPackage.Literals.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE))
      {
         // check new value
         if (msg.getNewValue() != null
               && msg.getNewValue() instanceof EAttribute)
         {
            // refresh to hide specific buttons
            refresh();
         }
      }

      super.handleModelChanged(msg);
   }


   @Override
   public void refresh()
   {
      super.refresh();

      // get attribute
      EAttribute attribute = getElement().getAttribute();

      // check type
      if (isBoolean(attribute))
      {
         // only show '=' and '!='
         for (Button button : getButtons())
         {
            // get literal
            Object literal = getObject(button);

            // check it
            if (literal.equals(OperatorType.EQUAL)
                  || literal.equals(OperatorType.UNEQUAL))
            {
               button.setEnabled(true);
            }
            else
            {
               button.setEnabled(false);
            }
         }
      }
      else if (isEnum(attribute) || isDouble(attribute) || isInteger(attribute))
      {
         // only all but regex
         for (Button button : getButtons())
         {
            // get literal
            Object literal = getObject(button);

            // check it
            if (!literal.equals(OperatorType.REGULAR_EXPRESSION))
            {
               button.setEnabled(true);
            }
            else
            {
               button.setEnabled(false);
            }
         }
      }
      else
      {
         // only regex, =, !=
         for (Button button : getButtons())
         {
            // get literal
            Object literal = getObject(button);

            // check it
            if (literal.equals(OperatorType.REGULAR_EXPRESSION)
                  || literal.equals(OperatorType.EQUAL)
                  || literal.equals(OperatorType.UNEQUAL))
            {
               button.setEnabled(true);
            }
            else
            {
               button.setEnabled(false);
            }
         }
      }
   }


   @Override
   protected PSAttributeConstraint getElement()
   {
      return (PSAttributeConstraint) super.getElement();
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
      return OperatorType.VALUES;
   }
}
