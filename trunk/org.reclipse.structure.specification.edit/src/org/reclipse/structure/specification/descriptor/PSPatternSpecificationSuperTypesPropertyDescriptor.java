package org.reclipse.structure.specification.descriptor;


import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.structure.specification.PSPatternSpecification;


/**
 * This class provides a property descriptor for the
 * {@link PSPatternSpecification#getSuperPattern() super} feature by filtering out the self and the
 * sub pattern references.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class PSPatternSpecificationSuperTypesPropertyDescriptor extends
      ItemPropertyDescriptor
{

   public PSPatternSpecificationSuperTypesPropertyDescriptor(
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
    * Filters the values for the property descriptor. It only filters it self from the list.
    * 
    * @param object The object for which to filter.
    * @return Returns the filtered choices.
    */
   @Override
   public Collection<PSPatternSpecification> getComboBoxObjects(Object object)
   {
      PSPatternSpecification pattern = (PSPatternSpecification) object;

      HashSet<PSPatternSpecification> filtered = new HashSet<PSPatternSpecification>();

      filtered.addAll(pattern.getCatalog().getPatternSpecifications());

      filtered.remove(pattern);

      filtered.removeAll(pattern.getSubPatterns());

      return filtered;
   }
}
