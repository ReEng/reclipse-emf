package org.reclipse.structure.specification.descriptor;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAttributeConstraint;


/**
 * This class provides the property descriptor for the {@link PSAttributeConstraint#getOperator()
 * operator} feature. It filters the values to interesting values for the
 * {@link PSAttributeConstraint}.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSAttributeConstraintOperatorPropertyDescriptor extends
      ItemPropertyDescriptor
{

   /**
    * The default constructor.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#ItemPropertyDescriptor(AdapterFactory,
    *      ResourceLocator, String, String, EStructuralFeature, boolean, boolean, boolean, Object,
    *      String, String[])
    */
   public PSAttributeConstraintOperatorPropertyDescriptor(
         AdapterFactory adapterFactory, ResourceLocator resourceLocator,
         String displayName, String description, EStructuralFeature feature,
         boolean isSettable, boolean multiLine, boolean sortChoices,
         Object staticImage, String category, String[] filterFlags)
   {
      super(adapterFactory, resourceLocator, displayName, description, feature,
            isSettable, multiLine, sortChoices, staticImage, category,
            filterFlags);
   }


   /**
    * This method filters all relevant operators.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#getChoiceOfValues(Object)
    */
   @Override
   public Collection<?> getComboBoxObjects(Object object)
   {
      if (object instanceof PSAttributeConstraint)
      {
         // cache data type
         EDataType attr = ((PSAttributeConstraint) object).getAttribute()
               .getEAttributeType();

         // collect type dependent
         if (attr.getName().equals("EBoolean") || attr.getName().equals("EString"))
         {
            Collection<OperatorType> list = new ArrayList<OperatorType>();

            list.add(OperatorType.EQUAL);
            list.add(OperatorType.UNEQUAL);
            list.add(OperatorType.REGULAR_EXPRESSION);

            return list;
         }
      }

      return super.getComboBoxObjects(object);
   }
}
