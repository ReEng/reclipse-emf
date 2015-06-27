/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SetResultSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Set Result Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SetResultSetImpl#getAnnotationSets <em>Annotation Sets</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SetResultSetImpl#getParentAnnotation <em>Parent Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SetResultSetImpl extends EObjectImpl implements SetResultSet
{
   /**
    * The cached value of the '{@link #getAnnotationSets() <em>Annotation Sets</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAnnotationSets()
    * @generated
    * @ordered
    */
   protected EList<AnnotationSet> annotationSets;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected SetResultSetImpl()
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
      return AnnotationsPackage.Literals.SET_RESULT_SET;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<AnnotationSet> getAnnotationSets()
   {
      if (annotationSets == null)
      {
         annotationSets = new EObjectContainmentWithInverseEList<AnnotationSet>(AnnotationSet.class, this, AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS, AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET);
      }
      return annotationSets;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ASGAnnotation getParentAnnotation()
   {
      if (eContainerFeatureID() != AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION) return null;
      return (ASGAnnotation)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetParentAnnotation(ASGAnnotation newParentAnnotation, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newParentAnnotation, AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setParentAnnotation(ASGAnnotation newParentAnnotation)
   {
      if (newParentAnnotation != eInternalContainer() || (eContainerFeatureID() != AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION && newParentAnnotation != null))
      {
         if (EcoreUtil.isAncestor(this, newParentAnnotation))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newParentAnnotation != null)
            msgs = ((InternalEObject)newParentAnnotation).eInverseAdd(this, AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET, ASGAnnotation.class, msgs);
         msgs = basicSetParentAnnotation(newParentAnnotation, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION, newParentAnnotation, newParentAnnotation));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotationSets()).basicAdd(otherEnd, msgs);
         case AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetParentAnnotation((ASGAnnotation)otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
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
         case AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS:
            return ((InternalEList<?>)getAnnotationSets()).basicRemove(otherEnd, msgs);
         case AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION:
            return basicSetParentAnnotation(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
   {
      switch (eContainerFeatureID())
      {
         case AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION:
            return eInternalContainer().eInverseRemove(this, AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET, ASGAnnotation.class, msgs);
      }
      return super.eBasicRemoveFromContainerFeature(msgs);
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
         case AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS:
            return getAnnotationSets();
         case AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION:
            return getParentAnnotation();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS:
            getAnnotationSets().clear();
            getAnnotationSets().addAll((Collection<? extends AnnotationSet>)newValue);
            return;
         case AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION:
            setParentAnnotation((ASGAnnotation)newValue);
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
         case AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS:
            getAnnotationSets().clear();
            return;
         case AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION:
            setParentAnnotation((ASGAnnotation)null);
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
         case AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS:
            return annotationSets != null && !annotationSets.isEmpty();
         case AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION:
            return getParentAnnotation() != null;
      }
      return super.eIsSet(featureID);
   }

} //SetResultSetImpl
