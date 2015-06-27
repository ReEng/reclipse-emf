package org.reclipse.structure.specification.ui.properties;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.fujaba.commons.properties.util.AdapterUtil;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.provider.SpecificationItemProviderAdapterFactory;


public class PSTabbedPropertiesLabelProvider extends LabelProvider
{
   private static AdapterFactoryLabelProvider provider;


   private static Image getElementImage(Object object)
   {
      // get adapted element
      EObject element = AdapterUtil.adaptObject(object);

      // get provider
      if (provider == null)
      {
         provider = new AdapterFactoryLabelProvider(
               new SpecificationItemProviderAdapterFactory());
      }

      // let the provider return the image
      return provider.getImage(element);
   }


   private static String getElementText(Object object)
   {
      // get adapted element
      EObject element = AdapterUtil.adaptObject(object);

      if (element instanceof PSCatalog)
      {
         return "Pattern Catalog";
      }

      if (element instanceof PSPatternSpecification)
      {
         return "Pattern Specification";
      }

      if (element instanceof PSCombinedFragment)
      {
         // get type
         ModifierType type = ((PSCombinedFragment) element).getKind();

         switch (type)
         {
            case ADDITIONAL:
               return "Additional Fragment";
            case NEGATIVE:
               return "Negative Fragment";
            case SET:
               return "Set Fragment";
            default:
               return "Pattern Fragment";
         }
      }

      if (element instanceof PSAnnotation)
      {
         return "Pattern Annotation";
      }

      if (element instanceof PSObject)
      {
         return "Pattern Object";
      }

      if (element instanceof PSLink)
      {
         return "Pattern Link";
      }

      if (element instanceof PSPath)
      {
         return "Pattern Path";
      }

      if (element instanceof PSSpecificationConstraint)
      {
         return "Pattern Constraint";
      }

      if (element instanceof PSAttributeConstraint)
      {
         return "Attribute Constraint";
      }

      if (element instanceof PSMetricConstraint)
      {
         return "Metric Constraint";
      }

      if (element instanceof PSFuzzyMetricConstraint)
      {
         return "Fuzzy Metric Constraint";
      }

      if (element instanceof PSFuzzySetRatingConstraint)
      {
         return "Fuzzy Set Rating Constraint";
      }

      return object.toString();
   }


   @Override
   public Image getImage(Object object)
   {
      // check for structured selection
      if (object instanceof IStructuredSelection)
      {
         // get selected
         List<?> elements = ((IStructuredSelection) object).toList();

         // when only one element is selected
         if (elements.size() == 1)
         {
            return getElementImage(elements.get(0));
         }

         // otherwise return null
         return null;
      }

      return getElementImage(object);
   }


   @Override
   public String getText(Object object)
   {
      // check for structured selection
      if (object instanceof IStructuredSelection)
      {
         // get selected
         List<?> elements = ((IStructuredSelection) object).toList();

         // when only one element is selected
         if (elements.size() == 1)
         {
            return getElementText(elements.get(0));
         }

         // otherwise return the count
         return elements.size() + " items selected";
      }

      return object.toString();
   }
}
