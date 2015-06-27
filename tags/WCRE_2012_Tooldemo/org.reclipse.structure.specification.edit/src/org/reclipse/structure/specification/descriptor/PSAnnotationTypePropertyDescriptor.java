package org.reclipse.structure.specification.descriptor;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * This class provides the property descriptor for the {@link PSAnnotation#getType() type} feature.
 * It filters the values and sets the property to read only on 'create' annotations.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSAnnotationTypePropertyDescriptor extends ItemPropertyDescriptor
{

   /**
    * The default constructor.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#ItemPropertyDescriptor(AdapterFactory,
    *      ResourceLocator, String, String, EStructuralFeature, boolean, boolean, boolean, Object,
    *      String, String[])
    */
   public PSAnnotationTypePropertyDescriptor(AdapterFactory adapterFactory,
         ResourceLocator resourceLocator, String displayName,
         String description, EStructuralFeature feature, boolean isSettable,
         boolean multiLine, boolean sortChoices, Object staticImage,
         String category, String[] filterFlags)
   {
      super(adapterFactory, resourceLocator, displayName, description, feature,
            isSettable, multiLine, sortChoices, staticImage, category,
            filterFlags);
   }


   /**
    * This returns <code>false</code> when the object is a create annotation to deny the editing of
    * the type value.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#canSetProperty(java.lang.Object)
    */
   @Override
   public boolean canSetProperty(Object object)
   {
      // check if annotation is a 'create'
      if (object instanceof PSAnnotation
            && ModelHelper.isCreate((PSAnnotation) object))
      {
         return false;
      }

      return super.canSetProperty(object);
   }


   /**
    * Getter of the possible type properties of an annotation. Filters its own pattern to stay
    * valid.
    * 
    * @param object The object to fill the combo box.
    * @return Returns all relevant values.
    */
   @Override
   protected Collection<PSPatternSpecification> getComboBoxObjects(Object object)
   {
      // check preconditions
      if (object instanceof PSAnnotation && object != null)
      {
         PSAnnotation anno = (PSAnnotation) object;

         HashSet<PSPatternSpecification> filtered = new HashSet<PSPatternSpecification>();
         filtered.addAll(((PSAnnotation) object).getPatternSpecification()
               .getCatalog().getPatternSpecifications());
         filtered.remove(anno.getPatternSpecification());

         return filtered;
      }

      return Collections.emptySet();
   }
}
