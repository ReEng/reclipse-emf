package org.reclipse.behavior.specification.ui.providers;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.ui.util.ASTUtil;
import org.reclipse.structure.specification.PSObject;


public class TypeReferencePropertyDescriptor extends ItemPropertyDescriptor
{


   public TypeReferencePropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator,
         String displayName, String description, EStructuralFeature feature, boolean isSettable, boolean multiLine,
         boolean sortChoices, Object staticImage, String category, String[] filterFlags)
   {
      super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine, sortChoices,
            staticImage, category, filterFlags);
   }


   @Override
   public Collection<?> getChoiceOfValues(Object object)
   {
      ArrayList<PSObject> filteredPSObjects = new ArrayList<PSObject>();
      Collection<?> availablePSObjects = getComboBoxObjects(object);
      for (Object o : availablePSObjects)
      {
         if (o != null)
         {
            PSObject psObject = (PSObject) o;
            if (ASTUtil.objectIsClass(psObject) && objectIsInRightPattern(object, psObject))
            {
               filteredPSObjects.add(psObject);
            }
         }
      }
      return filteredPSObjects;
   }


   private boolean objectIsInRightPattern(Object object, PSObject psObject)
   {
      if (object instanceof BPObject)
      {
         BPObject bpObject = (BPObject) object;
         String behavioralPatternName = bpObject.getDiagram().getName();
         String structuralPatternName = psObject.getPatternSpecification().getName();
         if (behavioralPatternName.equals(structuralPatternName))
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      return false;
   }


}
