/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.reclipse.structure.inference.annotations.AnnotationEngine;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation Engine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.AnnotationEngineImpl#getFailedApplications <em>Failed Applications</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.AnnotationEngineImpl#getFoundAnnotations <em>Found Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnnotationEngineImpl extends EObjectImpl implements AnnotationEngine
{
   /**
    * The cached value of the '{@link #getFailedApplications() <em>Failed Applications</em>}' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getFailedApplications()
    * @generated
    * @ordered
    */
   protected EMap<String, EList<EObject>> failedApplications;

   /**
    * The cached value of the '{@link #getFoundAnnotations() <em>Found Annotations</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getFoundAnnotations()
    * @generated
    * @ordered
    */
   protected AnnotationSet foundAnnotations;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected AnnotationEngineImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return AnnotationsPackage.Literals.ANNOTATION_ENGINE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EMap<String, EList<EObject>> getFailedApplications()
   {
      if (failedApplications == null)
      {
         failedApplications = new EcoreEMap<String,EList<EObject>>(AnnotationsPackage.Literals.STRING_TO_EOBJECT_MAP, StringToEObjectMapImpl.class, this, AnnotationsPackage.ANNOTATION_ENGINE__FAILED_APPLICATIONS);
      }
      return failedApplications;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationSet getFoundAnnotations()
   {
      if (foundAnnotations != null && foundAnnotations.eIsProxy())
      {
         InternalEObject oldFoundAnnotations = (InternalEObject)foundAnnotations;
         foundAnnotations = (AnnotationSet)eResolveProxy(oldFoundAnnotations);
         if (foundAnnotations != oldFoundAnnotations)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnnotationsPackage.ANNOTATION_ENGINE__FOUND_ANNOTATIONS, oldFoundAnnotations, foundAnnotations));
         }
      }
      return foundAnnotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationSet basicGetFoundAnnotations()
   {
      return foundAnnotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setFoundAnnotations(AnnotationSet newFoundAnnotations)
   {
      AnnotationSet oldFoundAnnotations = foundAnnotations;
      foundAnnotations = newFoundAnnotations;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.ANNOTATION_ENGINE__FOUND_ANNOTATIONS, oldFoundAnnotations, foundAnnotations));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public void removeFromFailedApplications(EObject context)
   {
      if (getFailedApplications().values().contains(context)) {
         getFailedApplications().remove(context);
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case AnnotationsPackage.ANNOTATION_ENGINE__FAILED_APPLICATIONS:
            return ((InternalEList<?>)getFailedApplications()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case AnnotationsPackage.ANNOTATION_ENGINE__FAILED_APPLICATIONS:
            if (coreType) return getFailedApplications();
            else return getFailedApplications().map();
         case AnnotationsPackage.ANNOTATION_ENGINE__FOUND_ANNOTATIONS:
            if (resolve) return getFoundAnnotations();
            return basicGetFoundAnnotations();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case AnnotationsPackage.ANNOTATION_ENGINE__FAILED_APPLICATIONS:
            ((EStructuralFeature.Setting)getFailedApplications()).set(newValue);
            return;
         case AnnotationsPackage.ANNOTATION_ENGINE__FOUND_ANNOTATIONS:
            setFoundAnnotations((AnnotationSet)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case AnnotationsPackage.ANNOTATION_ENGINE__FAILED_APPLICATIONS:
            getFailedApplications().clear();
            return;
         case AnnotationsPackage.ANNOTATION_ENGINE__FOUND_ANNOTATIONS:
            setFoundAnnotations((AnnotationSet)null);
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case AnnotationsPackage.ANNOTATION_ENGINE__FAILED_APPLICATIONS:
            return failedApplications != null && !failedApplications.isEmpty();
         case AnnotationsPackage.ANNOTATION_ENGINE__FOUND_ANNOTATIONS:
            return foundAnnotations != null;
      }
      return super.eIsSet(featureID);
   }

} //AnnotationEngineImpl
