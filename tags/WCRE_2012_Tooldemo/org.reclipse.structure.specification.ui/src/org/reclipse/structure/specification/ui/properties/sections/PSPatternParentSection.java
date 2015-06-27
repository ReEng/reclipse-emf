package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IFilter;
import org.fujaba.commons.properties.section.AbstractObjectSelectionComboSection;
import org.fujaba.commons.properties.util.AdapterUtil;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSPatternParentSection extends AbstractObjectSelectionComboSection
{
   @Override
   protected String getAdvancedButtonText()
   {
      return "advanced here";
   }
   public static class PSPatternParentFilter implements IFilter
   {
      @Override
      public boolean select(Object object)
      {
         // get adapted element
         EObject element = AdapterUtil.adaptObject(object);

         // check type
         if (element instanceof PSPatternSpecification)
         {
            // get pattern
            PSPatternSpecification pattern = (PSPatternSpecification) element;

            // check if there are possible parent types
            List<PSPatternSpecification> parents = ModelHelper
                  .getAllValidParents(pattern);
            if (parents != null && parents.size() > 1)
            {
               return true;
            }
         }

         return false;
      }
   }


   @Override
   protected List<PSPatternSpecification> getPossibilities()
   {
      return ModelHelper.getAllValidParents(getElement());
   }


   @Override
   protected PSPatternSpecification getElement()
   {
      return (PSPatternSpecification) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__SUPER_PATTERN;
   }


   @Override
   protected String getLabelText()
   {
      return "Super Pattern";
   }


   @Override
   protected String getValueText(Object element)
   {
      return element == null ? "" : ((PSPatternSpecification) element)
            .getName();
   }


   @Override
   protected PSPatternSpecification getCurrentValue()
   {
      return getElement().getSuperPattern();
   }
}
