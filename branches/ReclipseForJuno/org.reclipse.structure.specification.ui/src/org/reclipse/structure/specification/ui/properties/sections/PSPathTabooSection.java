package org.reclipse.structure.specification.ui.properties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.fujaba.commons.properties.section.AbstractSection;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.SpecificationPackage;


public class PSPathTabooSection extends AbstractSection
{
   private CLabel label;


   @Override
   protected void createWidgets(Composite parent)
   {
      // create label
      label = getWidgetFactory()
            .createCLabel(parent, getLabelText(), SWT.TRAIL);
   }


   @Override
   public void refresh()
   {

   }


   @Override
   protected void setSectionData(Composite parent)
   {
   }


   @Override
   protected void hookListeners()
   {
   }


   @Override
   protected PSPath getElement()
   {
      return (PSPath) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_PATH__TABOO_CLASSES;
   }


   @Override
   protected String getLabelText()
   {
      return "At the moment you have to use the advanced tab to configure the pattern path.";
   }
}
