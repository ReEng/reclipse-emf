package org.reclipse.structure.specification.ui.properties.sections;


import org.fujaba.commons.properties.section.AbstractIdentifierNameSection;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;


public class PSObjectNameSection extends AbstractIdentifierNameSection
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
      for (PSNode node : getElement().getPatternSpecification().getNodes())
      {
         if (!node.equals(getElement()) && node instanceof PSObject
               && name.equals(node.getName()))
         {
            return false;
         }
      }

      return true;
   }


   @Override
   protected PSObject getElement()
   {
      return (PSObject) super.getElement();
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
      return "This name is used to qualify the bounded elements.";
   }
}
