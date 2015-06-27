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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SetResultSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.AnnotationSetImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.AnnotationSetImpl#getSetResultSet <em>Set Result Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnnotationSetImpl extends EObjectImpl implements AnnotationSet
{
   /**
    * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAnnotations()
    * @generated
    * @ordered
    */
   protected EList<ASGAnnotation> annotations;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected AnnotationSetImpl()
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
      return AnnotationsPackage.Literals.ANNOTATION_SET;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ASGAnnotation> getAnnotations()
   {
      if (annotations == null)
      {
         annotations = new EObjectContainmentEList<ASGAnnotation>(ASGAnnotation.class, this, AnnotationsPackage.ANNOTATION_SET__ANNOTATIONS);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SetResultSet getSetResultSet()
   {
      if (eContainerFeatureID() != AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET) return null;
      return (SetResultSet)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetSetResultSet(SetResultSet newSetResultSet, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newSetResultSet, AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSetResultSet(SetResultSet newSetResultSet)
   {
      if (newSetResultSet != eInternalContainer() || (eContainerFeatureID() != AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET && newSetResultSet != null))
      {
         if (EcoreUtil.isAncestor(this, newSetResultSet))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newSetResultSet != null)
            msgs = ((InternalEObject)newSetResultSet).eInverseAdd(this, AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS, SetResultSet.class, msgs);
         msgs = basicSetSetResultSet(newSetResultSet, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET, newSetResultSet, newSetResultSet));
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
         case AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetSetResultSet((SetResultSet)otherEnd, msgs);
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
         case AnnotationsPackage.ANNOTATION_SET__ANNOTATIONS:
            return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
         case AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET:
            return basicSetSetResultSet(null, msgs);
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
         case AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET:
            return eInternalContainer().eInverseRemove(this, AnnotationsPackage.SET_RESULT_SET__ANNOTATION_SETS, SetResultSet.class, msgs);
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
         case AnnotationsPackage.ANNOTATION_SET__ANNOTATIONS:
            return getAnnotations();
         case AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET:
            return getSetResultSet();
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
         case AnnotationsPackage.ANNOTATION_SET__ANNOTATIONS:
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends ASGAnnotation>)newValue);
            return;
         case AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET:
            setSetResultSet((SetResultSet)newValue);
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
         case AnnotationsPackage.ANNOTATION_SET__ANNOTATIONS:
            getAnnotations().clear();
            return;
         case AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET:
            setSetResultSet((SetResultSet)null);
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
         case AnnotationsPackage.ANNOTATION_SET__ANNOTATIONS:
            return annotations != null && !annotations.isEmpty();
         case AnnotationsPackage.ANNOTATION_SET__SET_RESULT_SET:
            return getSetResultSet() != null;
      }
      return super.eIsSet(featureID);
   }

} //AnnotationSetImpl
