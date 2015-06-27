/**
 * 
 */
package org.reclipse.structure.inference.annotations.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.reclipse.structure.inference.annotations.AnnotationCollector;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class AnnotatedElementsEcoreEMap<K, V> extends EcoreEMap<K, V> implements Adapter
{
   /**
    * 
    */
   private static final long serialVersionUID = -1985307026771906409L;
   
   private ASGAnnotationImpl annotation;

   public AnnotatedElementsEcoreEMap(EClass entryEClass, Class<?> entryClass, ASGAnnotationImpl owner, int featureID)
   {
      super(entryEClass, entryClass, owner, featureID);
      this.annotation = owner;
      
   }

   @Override
   public V put(K key, V value)
   {
      V v = super.put(key, value);
      Object newValue = get(key);
      if(newValue instanceof EList)
      {
         EObjectResolvingEList<EObject> list = (EObjectResolvingEList<EObject>) newValue;
         list.getEObject().eAdapters().add(this);
         for(EObject o : list)
         {
            AnnotationCollector.get().put(o, this.annotation);
         }
      }
      else if(newValue != null && newValue instanceof EObject)
      {
         AnnotationCollector.get().put((EObject) newValue, this.annotation);
      }
      else
      {
         AnnotationCollector.get().put(null, this.annotation);
      }
      return v;
   }

   @Override
   public void notifyChanged(Notification notification)
   {
      if(notification.getFeature() == AnnotationsPackage.eINSTANCE.getStringToEObjectMap_Value())
      {
         Object newValue = notification.getNewValue();
         if(newValue != null)
         {
            if(newValue instanceof EObject)
            {
               AnnotationCollector.get().put((EObject) newValue, this.annotation);
            }
            else if(newValue instanceof List)
            {
               List<EObject> newList = (List<EObject>) newValue;
               for(EObject o : newList)
               {
                  AnnotationCollector.get().put(o, this.annotation);
               }
            }
         }
      }
      
   }

   @Override
   public Notifier getTarget()
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setTarget(Notifier newTarget)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public boolean isAdapterForType(Object type)
   {
      // TODO Auto-generated method stub
      return false;
   }

}
