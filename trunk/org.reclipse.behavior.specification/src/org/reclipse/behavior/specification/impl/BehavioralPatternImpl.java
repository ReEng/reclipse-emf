/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.impl;

import de.uni_paderborn.basicSequenceDiagram.impl.SequenceDiagramImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternPackage;

import org.reclipse.structure.specification.PSPatternSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behavioral Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.impl.BehavioralPatternImpl#isNegative <em>Negative</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.impl.BehavioralPatternImpl#getPsPatternSpecification <em>Ps Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.impl.BehavioralPatternImpl#getCatalog <em>Catalog</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BehavioralPatternImpl extends SequenceDiagramImpl implements BehavioralPattern
{
   /**
    * The default value of the '{@link #isNegative() <em>Negative</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isNegative()
    * @generated
    * @ordered
    */
   protected static final boolean NEGATIVE_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isNegative() <em>Negative</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isNegative()
    * @generated
    * @ordered
    */
   protected boolean negative = NEGATIVE_EDEFAULT;

   /**
    * The cached value of the '{@link #getPsPatternSpecification() <em>Ps Pattern Specification</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPsPatternSpecification()
    * @generated
    * @ordered
    */
   protected PSPatternSpecification psPatternSpecification;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected BehavioralPatternImpl()
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
      return BehavioralpatternPackage.Literals.BEHAVIORAL_PATTERN;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isNegative()
   {
      return negative;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setNegative(boolean newNegative)
   {
      boolean oldNegative = negative;
      negative = newNegative;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BEHAVIORAL_PATTERN__NEGATIVE, oldNegative, negative));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification getPsPatternSpecification()
   {
      if (psPatternSpecification != null && psPatternSpecification.eIsProxy())
      {
         InternalEObject oldPsPatternSpecification = (InternalEObject)psPatternSpecification;
         psPatternSpecification = (PSPatternSpecification)eResolveProxy(oldPsPatternSpecification);
         if (psPatternSpecification != oldPsPatternSpecification)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralpatternPackage.BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION, oldPsPatternSpecification, psPatternSpecification));
         }
      }
      return psPatternSpecification;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification basicGetPsPatternSpecification()
   {
      return psPatternSpecification;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPsPatternSpecification(PSPatternSpecification newPsPatternSpecification)
   {
      PSPatternSpecification oldPsPatternSpecification = psPatternSpecification;
      psPatternSpecification = newPsPatternSpecification;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION, oldPsPatternSpecification, psPatternSpecification));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPCatalog getCatalog()
   {
      if (eContainerFeatureID() != BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG) return null;
      return (BPCatalog)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetCatalog(BPCatalog newCatalog, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newCatalog, BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setCatalog(BPCatalog newCatalog)
   {
      if (newCatalog != eInternalContainer() || (eContainerFeatureID() != BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG && newCatalog != null))
      {
         if (EcoreUtil.isAncestor(this, newCatalog))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newCatalog != null)
            msgs = ((InternalEObject)newCatalog).eInverseAdd(this, BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS, BPCatalog.class, msgs);
         msgs = basicSetCatalog(newCatalog, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG, newCatalog, newCatalog));
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
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetCatalog((BPCatalog)otherEnd, msgs);
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
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG:
            return basicSetCatalog(null, msgs);
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
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG:
            return eInternalContainer().eInverseRemove(this, BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS, BPCatalog.class, msgs);
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
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__NEGATIVE:
            return isNegative();
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION:
            if (resolve) return getPsPatternSpecification();
            return basicGetPsPatternSpecification();
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG:
            return getCatalog();
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
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__NEGATIVE:
            setNegative((Boolean)newValue);
            return;
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION:
            setPsPatternSpecification((PSPatternSpecification)newValue);
            return;
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG:
            setCatalog((BPCatalog)newValue);
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
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__NEGATIVE:
            setNegative(NEGATIVE_EDEFAULT);
            return;
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION:
            setPsPatternSpecification((PSPatternSpecification)null);
            return;
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG:
            setCatalog((BPCatalog)null);
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
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__NEGATIVE:
            return negative != NEGATIVE_EDEFAULT;
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION:
            return psPatternSpecification != null;
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG:
            return getCatalog() != null;
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
      result.append(" (negative: ");
      result.append(negative);
      result.append(')');
      return result.toString();
   }

} //BehavioralPatternImpl
