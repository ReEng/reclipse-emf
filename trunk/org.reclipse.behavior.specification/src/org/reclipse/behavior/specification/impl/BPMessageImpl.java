/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.impl;

import de.uni_paderborn.basicSequenceDiagram.impl.AbstractMessageImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BehavioralpatternPackage;

import org.reclipse.structure.specification.PSObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BP Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPMessageImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPMessageImpl#isSelfCall <em>Self Call</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPMessageImpl#getMethodReference <em>Method Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPMessageImpl extends AbstractMessageImpl implements BPMessage
{
   /**
    * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getArguments()
    * @generated
    * @ordered
    */
   protected EList<BPArgument> arguments;

   /**
    * The default value of the '{@link #isSelfCall() <em>Self Call</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSelfCall()
    * @generated
    * @ordered
    */
   protected static final boolean SELF_CALL_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isSelfCall() <em>Self Call</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSelfCall()
    * @generated
    * @ordered
    */
   protected boolean selfCall = SELF_CALL_EDEFAULT;

   /**
    * The cached value of the '{@link #getMethodReference() <em>Method Reference</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMethodReference()
    * @generated
    * @ordered
    */
   protected PSObject methodReference;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected BPMessageImpl()
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
      return BehavioralpatternPackage.Literals.BP_MESSAGE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<BPArgument> getArguments()
   {
      if (arguments == null)
      {
         arguments = new EObjectContainmentWithInverseEList<BPArgument>(BPArgument.class, this, BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS, BehavioralpatternPackage.BP_ARGUMENT__MESSAGE);
      }
      return arguments;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSelfCall()
   {
      return selfCall;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSelfCall(boolean newSelfCall)
   {
      boolean oldSelfCall = selfCall;
      selfCall = newSelfCall;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_MESSAGE__SELF_CALL, oldSelfCall, selfCall));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSObject getMethodReference()
   {
      if (methodReference != null && methodReference.eIsProxy())
      {
         InternalEObject oldMethodReference = (InternalEObject)methodReference;
         methodReference = (PSObject)eResolveProxy(oldMethodReference);
         if (methodReference != oldMethodReference)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralpatternPackage.BP_MESSAGE__METHOD_REFERENCE, oldMethodReference, methodReference));
         }
      }
      return methodReference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSObject basicGetMethodReference()
   {
      return methodReference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setMethodReference(PSObject newMethodReference)
   {
      PSObject oldMethodReference = methodReference;
      methodReference = newMethodReference;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_MESSAGE__METHOD_REFERENCE, oldMethodReference, methodReference));
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
         case BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getArguments()).basicAdd(otherEnd, msgs);
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
         case BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS:
            return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
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
         case BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS:
            return getArguments();
         case BehavioralpatternPackage.BP_MESSAGE__SELF_CALL:
            return isSelfCall();
         case BehavioralpatternPackage.BP_MESSAGE__METHOD_REFERENCE:
            if (resolve) return getMethodReference();
            return basicGetMethodReference();
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
         case BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS:
            getArguments().clear();
            getArguments().addAll((Collection<? extends BPArgument>)newValue);
            return;
         case BehavioralpatternPackage.BP_MESSAGE__SELF_CALL:
            setSelfCall((Boolean)newValue);
            return;
         case BehavioralpatternPackage.BP_MESSAGE__METHOD_REFERENCE:
            setMethodReference((PSObject)newValue);
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
         case BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS:
            getArguments().clear();
            return;
         case BehavioralpatternPackage.BP_MESSAGE__SELF_CALL:
            setSelfCall(SELF_CALL_EDEFAULT);
            return;
         case BehavioralpatternPackage.BP_MESSAGE__METHOD_REFERENCE:
            setMethodReference((PSObject)null);
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
         case BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS:
            return arguments != null && !arguments.isEmpty();
         case BehavioralpatternPackage.BP_MESSAGE__SELF_CALL:
            return selfCall != SELF_CALL_EDEFAULT;
         case BehavioralpatternPackage.BP_MESSAGE__METHOD_REFERENCE:
            return methodReference != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (selfCall: ");
      result.append(selfCall);
      result.append(')');
      return result.toString();
   }

} //BPMessageImpl
