/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.impl;

import de.uni_paderborn.basicSequenceDiagram.impl.LifelineFragmentImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BehavioralpatternPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BP Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPAssignmentImpl#getLeftSide <em>Left Side</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPAssignmentImpl#getRightSide <em>Right Side</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPAssignmentImpl extends LifelineFragmentImpl implements BPAssignment
{
   /**
    * The cached value of the '{@link #getLeftSide() <em>Left Side</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLeftSide()
    * @generated
    * @ordered
    */
   protected BPObject leftSide;

   /**
    * The cached value of the '{@link #getRightSide() <em>Right Side</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getRightSide()
    * @generated
    * @ordered
    */
   protected BPArgument rightSide;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected BPAssignmentImpl()
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
      return BehavioralpatternPackage.Literals.BP_ASSIGNMENT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPObject getLeftSide()
   {
      if (leftSide != null && leftSide.eIsProxy())
      {
         InternalEObject oldLeftSide = (InternalEObject)leftSide;
         leftSide = (BPObject)eResolveProxy(oldLeftSide);
         if (leftSide != oldLeftSide)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralpatternPackage.BP_ASSIGNMENT__LEFT_SIDE, oldLeftSide, leftSide));
         }
      }
      return leftSide;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPObject basicGetLeftSide()
   {
      return leftSide;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLeftSide(BPObject newLeftSide)
   {
      BPObject oldLeftSide = leftSide;
      leftSide = newLeftSide;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_ASSIGNMENT__LEFT_SIDE, oldLeftSide, leftSide));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPArgument getRightSide()
   {
      if (rightSide != null && rightSide.eIsProxy())
      {
         InternalEObject oldRightSide = (InternalEObject)rightSide;
         rightSide = (BPArgument)eResolveProxy(oldRightSide);
         if (rightSide != oldRightSide)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralpatternPackage.BP_ASSIGNMENT__RIGHT_SIDE, oldRightSide, rightSide));
         }
      }
      return rightSide;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPArgument basicGetRightSide()
   {
      return rightSide;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setRightSide(BPArgument newRightSide)
   {
      BPArgument oldRightSide = rightSide;
      rightSide = newRightSide;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_ASSIGNMENT__RIGHT_SIDE, oldRightSide, rightSide));
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
         case BehavioralpatternPackage.BP_ASSIGNMENT__LEFT_SIDE:
            if (resolve) return getLeftSide();
            return basicGetLeftSide();
         case BehavioralpatternPackage.BP_ASSIGNMENT__RIGHT_SIDE:
            if (resolve) return getRightSide();
            return basicGetRightSide();
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
         case BehavioralpatternPackage.BP_ASSIGNMENT__LEFT_SIDE:
            setLeftSide((BPObject)newValue);
            return;
         case BehavioralpatternPackage.BP_ASSIGNMENT__RIGHT_SIDE:
            setRightSide((BPArgument)newValue);
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
         case BehavioralpatternPackage.BP_ASSIGNMENT__LEFT_SIDE:
            setLeftSide((BPObject)null);
            return;
         case BehavioralpatternPackage.BP_ASSIGNMENT__RIGHT_SIDE:
            setRightSide((BPArgument)null);
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
         case BehavioralpatternPackage.BP_ASSIGNMENT__LEFT_SIDE:
            return leftSide != null;
         case BehavioralpatternPackage.BP_ASSIGNMENT__RIGHT_SIDE:
            return rightSide != null;
      }
      return super.eIsSet(featureID);
   }

} //BPAssignmentImpl
