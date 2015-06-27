package org.reclipse.structure.specification.ui.properties.sections;


import org.fujaba.commons.properties.section.AbstractIdentifierNameSection;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSNode;


public class PSAnnotationNameSection extends AbstractIdentifierNameSection
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
         if (!node.equals(getElement()) && node instanceof PSAnnotation
               && name.equals(node.getName()))
         {
            return false;
         }
      }

      return true;
   }


   @Override
   protected PSAnnotation getElement()
   {
      return (PSAnnotation) super.getElement();
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
