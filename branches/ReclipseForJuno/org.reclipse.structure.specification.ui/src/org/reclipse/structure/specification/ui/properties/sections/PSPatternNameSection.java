package org.reclipse.structure.specification.ui.properties.sections;


import org.fujaba.commons.properties.section.AbstractIdentifierNameSection;
import org.reclipse.structure.specification.PSPatternSpecification;


public class PSPatternNameSection extends AbstractIdentifierNameSection
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

      // go through the patterns
      for (PSPatternSpecification pattern : getElement().getCatalog()
            .getPatternSpecifications())
      {
         if (!pattern.equals(getElement()) && name.equals(pattern.getName()))
         {
            return false;
         }
      }

      return true;
   }


   @Override
   protected PSPatternSpecification getElement()
   {
      return (PSPatternSpecification) super.getElement();
   }


   @Override
   protected String getErrorMessage()
   {
      return "The name has to be unique for the catalog!";
   }


   @Override
   protected String getTooltip()
   {
      // TODO: describe this option with more effort
      return "This name is used as (unique) name for the pattern.";
   }
}
