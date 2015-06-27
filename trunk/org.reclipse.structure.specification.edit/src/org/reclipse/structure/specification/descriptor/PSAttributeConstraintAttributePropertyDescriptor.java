package org.reclipse.structure.specification.descriptor;


import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSObject;


/**
 * This class provides the property descriptor for the {@link PSAttributeConstraint#getAttribute()
 * attribute} feature. It filters the values to valid values of the PSObject.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSAttributeConstraintAttributePropertyDescriptor extends
      ItemPropertyDescriptor
{

   /**
    * The default constructor.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#ItemPropertyDescriptor(AdapterFactory,
    *      ResourceLocator, String, String, EStructuralFeature, boolean, boolean, boolean, Object,
    *      String, String[])
    */
   public PSAttributeConstraintAttributePropertyDescriptor(
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
    * This method filters all possible attributes from the container pattern object.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#getChoiceOfValues(Object)
    */
   @Override
   public Collection<EAttribute> getComboBoxObjects(Object object)
   {
      PSAttributeConstraint con = (PSAttributeConstraint) object;
      if (con.getNode() instanceof PSObject)
      {
         PSObject node = (PSObject) con.getNode();
         return node.getInstanceOf().getEAllAttributes();
      }

      return Collections.emptySet();
   }
}
