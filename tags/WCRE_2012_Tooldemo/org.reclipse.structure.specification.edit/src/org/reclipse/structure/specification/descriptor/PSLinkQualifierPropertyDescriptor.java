package org.reclipse.structure.specification.descriptor;


import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * This class provides the property descriptor for the {@link PSLink#getQualifier() qualifier}
 * feature. It delivers the values for related and inherited pattern annotation links.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSLinkQualifierPropertyDescriptor extends ItemPropertyDescriptor
{


   /**
    * The default constructor.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor
    */
   public PSLinkQualifierPropertyDescriptor(AdapterFactory adapterFactory,
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
    * Filters the values for the property descriptor. When the link's target is a {@link PSObject}
    * and the source is a {@link PSAnnotation} annotating an other pattern specification, it filters
    * all possible qualifier names from that specification.
    * 
    * @param object The object for which to filter.
    * @return Returns the filtered choices.
    */
   @Override
   public Collection<String> getComboBoxObjects(Object object)
   {
      PSLink link = (PSLink) object;

      // annotation from an other pattern --> object
      if (link.getSource() instanceof PSAnnotation
            && link.getTarget() instanceof PSObject
            && !ModelHelper.isCreate((PSAnnotation) link.getSource()))
      {
         PSAnnotation anno = (PSAnnotation) link.getSource();
         PSObject target = (PSObject) link.getTarget();

         HashSet<String> filtered = new HashSet<String>();

         // go through the source's targets
         for (PSConnection con : ModelHelper.getCreateAnnotation(anno)
               .getOutgoing())
         {
            // filter links with PSObject as target
            if (con instanceof PSLink && con.getTarget() instanceof PSObject)
            {
               // filter PSObject with adequate type
               if (((PSObject) con.getTarget()).getInstanceOf() == target
                     .getInstanceOf())
               {
                  String qualifier = ((PSLink) con).getQualifier();
                  if (qualifier != null && qualifier.length() > 0)
                  {
                     filtered.add(qualifier);
                  }
               }
            }
         }

         return filtered;
      }

      // create annotation (inherited) --> object
      if (link.getSource() instanceof PSAnnotation
            && link.getTarget() instanceof PSObject
            && ModelHelper.isCreate((PSAnnotation) link.getSource())
            && link.getPatternSpecification().getSuperPattern() != null)
      {
         PSObject target = (PSObject) link.getTarget();

         HashSet<String> filtered = new HashSet<String>();

         // go through the source's targets
         for (PSConnection con : ModelHelper.getCreateAnnotation(
               link.getPatternSpecification().getSuperPattern()).getOutgoing())
         {
            // filter links with PSObject as target
            if (con instanceof PSLink && con.getTarget() instanceof PSObject)
            {
               // filter PSObject with adequate type
               if (((PSObject) con.getTarget()).getInstanceOf().isSuperTypeOf(target
                     .getInstanceOf()))
               {
                  String qualifier = ((PSLink) con).getQualifier();
                  if (qualifier != null && !qualifier.isEmpty())
                  {
                     filtered.add(qualifier);
                  }
               }
            }
         }
         // FIXME: make the combo box elements selectable AND let the value be editable manually!
          return null;
      }

      return null;
   }
   
   
   /**
    * When removing a qualifier, the value is set to the empty string. During inference processing 
    * this causes links to be treated like qualified ones. To avoid this, the empty string is replaced by the null value.
    *  
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#setPropertyValue(java.lang.Object, java.lang.Object)
    */
   @Override
   public void setPropertyValue(Object object, Object value)
   {
      if("".equals(value))
      {
         super.setPropertyValue(object, null);
      }
      else
      {
         super.setPropertyValue(object, value);
      }
   }
}
