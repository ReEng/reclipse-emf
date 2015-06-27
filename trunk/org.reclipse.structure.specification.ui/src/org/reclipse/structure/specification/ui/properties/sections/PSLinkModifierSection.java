package org.reclipse.structure.specification.ui.properties.sections;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.fujaba.commons.properties.section.AbstractEnumRadioSelectionSection;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSLinkModifierSection extends AbstractEnumRadioSelectionSection
{
   @Override
   protected PSLink getElement()
   {
      return (PSLink) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      // XXX: implement the feature in model
      return SpecificationPackage.Literals.PS_LINK__NEGATIVE;
   }


   @Override
   protected void handleSelection(SelectionEvent e)
   {
      // XXX: this can be deleted when the meta model has been changed
      if (e.getSource() instanceof Button)
      {
         Object selected = getObject((Button) e.getSource());
         if (selected instanceof ModifierType)
         {
            switch ((ModifierType) selected)
            {
               case NEGATIVE:
                  createCommand(getElement().isNegative(), true);
                  break;

               default:
                  createCommand(getElement().isNegative(), false);
                  break;
            }
         }
      }
   }


   @Override
   protected String getLabelText()
   {
      return "Modifier";
   }


   @Override
   protected List<ModifierType> getLiterals()
   {
      // create list
      List<ModifierType> list = new ArrayList<ModifierType>();

      // add only some of them
      list.add(ModifierType.NONE);
      // list.add(ModifierType.ADDITIONAL);
      list.add(ModifierType.NEGATIVE);

      return list;
   }


   @Override
   protected String getLiteralText(Object element)
   {
      return ModelHelper.getReadable((ModifierType) element);
   }


   @Override
   protected ModifierType getValue()
   {
      // XXX: implement the feature in model
      if (getElement().isNegative())
      {
         return ModifierType.NEGATIVE;
      }
      else
      {
         return ModifierType.NONE;
      }
   }
}
