/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SatisfiedConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Satisfied Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SatisfiedConstraintImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SatisfiedConstraintImpl extends EObjectImpl implements SatisfiedConstraint
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected SatisfiedConstraintImpl()
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
      return AnnotationsPackage.Literals.SATISFIED_CONSTRAINT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ASGAnnotation getAnnotation()
   {
      if (eContainerFeatureID() != AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION) return null;
      return (ASGAnnotation)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetAnnotation(ASGAnnotation newAnnotation, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newAnnotation, AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAnnotation(ASGAnnotation newAnnotation)
   {
      if (newAnnotation != eInternalContainer() || (eContainerFeatureID() != AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION && newAnnotation != null))
      {
         if (EcoreUtil.isAncestor(this, newAnnotation))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newAnnotation != null)
            msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS, ASGAnnotation.class, msgs);
         msgs = basicSetAnnotation(newAnnotation, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION, newAnnotation, newAnnotation));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetAnnotation((ASGAnnotation)otherEnd, msgs);
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
         case AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION:
            return basicSetAnnotation(null, msgs);
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
         case AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION:
            return eInternalContainer().eInverseRemove(this, AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS, ASGAnnotation.class, msgs);
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
         case AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION:
            return getAnnotation();
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
         case AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION:
            setAnnotation((ASGAnnotation)newValue);
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
         case AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION:
            setAnnotation((ASGAnnotation)null);
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
         case AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION:
            return getAnnotation() != null;
      }
      return super.eIsSet(featureID);
   }

} //SatisfiedConstraintImpl
