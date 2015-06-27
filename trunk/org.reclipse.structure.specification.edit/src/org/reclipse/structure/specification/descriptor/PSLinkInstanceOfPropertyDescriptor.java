package org.reclipse.structure.specification.descriptor;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSObject;


/**
 * This class provides the property descriptor for the {@link PSLink#getInstanceOf() instance of}
 * feature of a link. It filters the values and sets the property to read only on annotations.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSLinkInstanceOfPropertyDescriptor extends ItemPropertyDescriptor
{

   /**
    * The default constructor.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor
    */
   public PSLinkInstanceOfPropertyDescriptor(AdapterFactory adapterFactory,
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
    * When the source of a link is an annotation, it returns <code>false</code>, otherwise the
    * result of the super class method.
    * 
    * @return Returns whether the property is writable.
    */
   @Override
   public boolean canSetProperty(Object object)
   {
      if (((PSLink) object).getSource() instanceof PSAnnotation)
      {
         return false;
      }

      return super.canSetProperty(object);
   }


   /**
    * Filters the values for the property descriptor. When the link's source and target are both
    * {@link PSObject}s, it filters all possible {@link EReference}s -- the reference type has to be
    * the type of the target and the reference has to be contained in the sources type chain.
    * 
    * @param object The object for which to filter.
    * @return Returns the filtered choices.
    */
   @Override
   public Collection<EReference> getComboBoxObjects(Object object)
   {
      PSLink link = (PSLink) object;

      // object --> object
      if (link.getSource() instanceof PSObject
            && link.getTarget() instanceof PSObject)
      {
         // get source and target
         EClass sourceType = ((PSObject) link.getSource()).getInstanceOf();
         EClass targetType = ((PSObject) link.getTarget()).getInstanceOf();

         // prepare elements
         HashSet<EReference> filtered = new HashSet<EReference>();

         // go through the source's references
         for (EReference ref : sourceType.getEAllReferences())
         {
            // add reference to set when applicable
            if (ref.getEReferenceType().isSuperTypeOf(targetType))
            {
               filtered.add(ref);
            }
         }
         return filtered;
      }

      return Collections.emptySet();
   }
}
