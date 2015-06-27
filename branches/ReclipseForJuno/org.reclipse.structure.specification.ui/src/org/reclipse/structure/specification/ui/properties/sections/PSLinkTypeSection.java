package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.fujaba.commons.properties.section.AbstractObjectSelectionComboSection;
import org.fujaba.commons.properties.util.SingleReferenceSelectionDialog;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSLinkTypeSection extends AbstractObjectSelectionComboSection
{
   private SingleReferenceSelectionDialog dialog;


   public PSLinkTypeSection()
   {
      dialog = new SingleReferenceSelectionDialog();
      dialog.setTitle("Pattern Link Type");
   }


   @Override
   protected Object getCurrentValue()
   {
      return getElement().getInstanceOf();
   }


   @Override
   protected PSLink getElement()
   {
      return (PSLink) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_LINK__INSTANCE_OF;
   }


   @Override
   protected String getLabelText()
   {
      return "Type";
   }


   @Override
   protected List<EReference> getPossibilities()
   {
      return ModelHelper.getAllValidTypes(getElement());
   }


   @Override
   protected String getValueText(Object element)
   {
      // get element
      EReference pattern = (EReference) element;

      // build name
      StringBuilder name = new StringBuilder();

      name.append(pattern.getName());
      name.append(": ");
      EClass type = pattern.getEReferenceType();
      if (type != null)
      {
         name.append(type.getName());
      }

      return name.toString();
   }


   @Override
   protected void handleAdvancedButtonClicked()
   {
      // set input
      dialog.setInput(getPossibilities(), getElement().getInstanceOf());

      // open it
      if (dialog.open() == IDialogConstants.OK_ID)
      {
         list.select(list.indexOf(getValueText(dialog.getSelected())));
         createCommand(getCurrentValue(), dialog.getSelected());
      }
   }


   @Override
   protected boolean showAdvancedButton()
   {
      return true;
   }
}
