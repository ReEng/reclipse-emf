package org.reclipse.structure.specification.ui.properties.sections;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.fujaba.commons.properties.section.AbstractObjectSelectionComboSection;
import org.fujaba.commons.properties.util.SingleTypeSelectionDialog;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSObjectTypeSection extends AbstractObjectSelectionComboSection
{
   private SingleTypeSelectionDialog dialog;


   public PSObjectTypeSection()
   {
      super();

      dialog = new SingleTypeSelectionDialog();
      dialog.setTitle("Pattern Object Type");
   }


   @Override
   protected Object getCurrentValue()
   {
      return getElement().getInstanceOf();
   }


   @Override
   protected PSObject getElement()
   {
      return (PSObject) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_OBJECT__INSTANCE_OF;
   }


   @Override
   protected String getLabelText()
   {
      return "Type";
   }


   @Override
   protected List<EClass> getPossibilities()
   {
      return ModelHelper.getAllValidTypes(getElement());
   }


   @Override
   protected String getValueText(Object element)
   {
      // get type
      EClass type = (EClass) element;
      String name = type.getName();

      // create the full text
      StringBuilder text = new StringBuilder(name);

      text.append(" [");
      text.append(type.getInstanceClass().getName());
      text.delete(text.length() - name.length() - 1, text.length());
      text.append("]");

      return text.toString();
   }


   @Override
   protected void handleAdvancedButtonClicked()
   {
      Collection<? extends EClassifier> input = ModelHelper.getAllValidTypes(getElement());

      dialog.setInput(input, getElement().getInstanceOf());

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
