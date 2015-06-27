/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.impl;

import de.uni_paderborn.basicSequenceDiagram.impl.AbstractSequenceDiagramObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BehavioralpatternPackage;

import org.reclipse.structure.specification.PSObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BP Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPObjectImpl#getTypeReference <em>Type Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPObjectImpl extends AbstractSequenceDiagramObjectImpl implements BPObject
{
   /**
    * The cached value of the '{@link #getTypeReference() <em>Type Reference</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTypeReference()
    * @generated
    * @ordered
    */
   protected PSObject typeReference;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected BPObjectImpl()
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
      return BehavioralpatternPackage.Literals.BP_OBJECT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSObject getTypeReference()
   {
      if (typeReference != null && typeReference.eIsProxy())
      {
         InternalEObject oldTypeReference = (InternalEObject)typeReference;
         typeReference = (PSObject)eResolveProxy(oldTypeReference);
         if (typeReference != oldTypeReference)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralpatternPackage.BP_OBJECT__TYPE_REFERENCE, oldTypeReference, typeReference));
         }
      }
      return typeReference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSObject basicGetTypeReference()
   {
      return typeReference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTypeReference(PSObject newTypeReference)
   {
      PSObject oldTypeReference = typeReference;
      typeReference = newTypeReference;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_OBJECT__TYPE_REFERENCE, oldTypeReference, typeReference));
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
         case BehavioralpatternPackage.BP_OBJECT__TYPE_REFERENCE:
            if (resolve) return getTypeReference();
            return basicGetTypeReference();
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
         case BehavioralpatternPackage.BP_OBJECT__TYPE_REFERENCE:
            setTypeReference((PSObject)newValue);
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
         case BehavioralpatternPackage.BP_OBJECT__TYPE_REFERENCE:
            setTypeReference((PSObject)null);
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
         case BehavioralpatternPackage.BP_OBJECT__TYPE_REFERENCE:
            return typeReference != null;
      }
      return super.eIsSet(featureID);
   }

} //BPObjectImpl
