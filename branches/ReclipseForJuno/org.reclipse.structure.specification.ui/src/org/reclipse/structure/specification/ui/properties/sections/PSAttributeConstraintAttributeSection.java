package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.fujaba.commons.properties.section.AbstractObjectSelectionComboSection;
import org.fujaba.commons.properties.util.SingleAttributeSelectionDialog;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSAttributeConstraintAttributeSection extends
      AbstractObjectSelectionComboSection
{
   private SingleAttributeSelectionDialog dialog;


   public PSAttributeConstraintAttributeSection()
   {
      dialog = new SingleAttributeSelectionDialog();
      dialog.setTitle("Pattern Object Attribute");
   }


   @Override
   protected EAttribute getCurrentValue()
   {
      return getElement().getAttribute();
   }


   @Override
   protected PSAttributeConstraint getElement()
   {
      return (PSAttributeConstraint) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE;
   }


   @Override
   protected String getLabelText()
   {
      return "Attribute";
   }


   @Override
   protected List<EAttribute> getPossibilities()
   {
      return ModelHelper.getAllValidAttributes((PSObject) getElement()
            .getNode());
   }


   @Override
   protected String getValueText(Object element)
   {
      // get element
      EAttribute attribute = (EAttribute) element;

      // create builder
      StringBuilder name = new StringBuilder();

      name.append(attribute.getName());

      // add type when existing
      if (attribute.getEAttributeType() != null)
      {
         name.append(": "); //$NON-NLS-1$
         name.append(attribute.getEAttributeType().getName());
      }

      return name.toString();
   }


   @Override
   protected void handleAdvancedButtonClicked()
   {
      // set input
      dialog.setInput(getPossibilities(), getElement().getAttribute());

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
