package org.reclipse.structure.specification.ui.properties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractStringSection;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.SpecificationPackage;


public class PSCatalogModelSection extends AbstractStringSection
{
   @Override
   protected boolean isTextValid()
   {
      return false;
   }


   @Override
   protected PSCatalog getElement()
   {
      return (PSCatalog) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_CATALOG__METAMODEL;
   }


   @Override
   protected String getLabelText()
   {
      return "Meta Model";
   }


   @Override
   protected String getErrorMessage()
   {
      return null;
   }


   @Override
   protected String getTooltip()
   {
      return null;
   }
}
