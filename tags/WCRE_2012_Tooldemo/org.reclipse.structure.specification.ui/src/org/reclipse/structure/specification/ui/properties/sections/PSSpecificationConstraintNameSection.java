package org.reclipse.structure.specification.ui.properties.sections;


import org.fujaba.commons.properties.section.AbstractIdentifierNameSection;
import org.reclipse.structure.specification.PSSpecificationConstraint;


public class PSSpecificationConstraintNameSection extends
      AbstractIdentifierNameSection
{
   @Override
   protected boolean isTextValid()
   {
      // get name
      String name = getText().getText();

      // simple check
      if (name == null || name.trim().isEmpty())
      {
         return false;
      }

      // go through the pattern nodes
      for (PSSpecificationConstraint constraint : getElement()
            .getPatternSpecification().getConstraints())
      {
         if (!constraint.equals(getElement())
               && name.equals(constraint.getName()))
         {
            return false;
         }
      }

      return true;
   }


   @Override
   protected PSSpecificationConstraint getElement()
   {
      return (PSSpecificationConstraint) super.getElement();
   }


   @Override
   protected String getErrorMessage()
   {
      return "The name has to be unique for the pattern!";
   }


   @Override
   protected String getTooltip()
   {
      // TODO: describe this option with more effort
      return "This name is used to qualify the pattern constraints.";
   }
}
