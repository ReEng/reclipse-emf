/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENamedElementImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternPackage;

import org.reclipse.structure.specification.PSCatalog;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BP Catalog</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPCatalogImpl#getBehavioralPatterns <em>Behavioral Patterns</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPCatalogImpl#getPsCatalog <em>Ps Catalog</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPCatalogImpl extends IdentifierImpl implements BPCatalog
{
   /**
    * The cached value of the '{@link #getBehavioralPatterns() <em>Behavioral Patterns</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getBehavioralPatterns()
    * @generated
    * @ordered
    */
   protected EList<BehavioralPattern> behavioralPatterns;

   /**
    * The cached value of the '{@link #getPsCatalog() <em>Ps Catalog</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPsCatalog()
    * @generated
    * @ordered
    */
   protected PSCatalog psCatalog;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected BPCatalogImpl()
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
      return BehavioralpatternPackage.Literals.BP_CATALOG;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<BehavioralPattern> getBehavioralPatterns()
   {
      if (behavioralPatterns == null)
      {
         behavioralPatterns = new EObjectContainmentWithInverseEList<BehavioralPattern>(BehavioralPattern.class, this, BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS, BehavioralpatternPackage.BEHAVIORAL_PATTERN__CATALOG);
      }
      return behavioralPatterns;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSCatalog getPsCatalog()
   {
      if (psCatalog != null && psCatalog.eIsProxy())
      {
         InternalEObject oldPsCatalog = (InternalEObject)psCatalog;
         psCatalog = (PSCatalog)eResolveProxy(oldPsCatalog);
         if (psCatalog != oldPsCatalog)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralpatternPackage.BP_CATALOG__PS_CATALOG, oldPsCatalog, psCatalog));
         }
      }
      return psCatalog;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSCatalog basicGetPsCatalog()
   {
      return psCatalog;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPsCatalog(PSCatalog newPsCatalog)
   {
      PSCatalog oldPsCatalog = psCatalog;
      psCatalog = newPsCatalog;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_CATALOG__PS_CATALOG, oldPsCatalog, psCatalog));
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
         case BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getBehavioralPatterns()).basicAdd(otherEnd, msgs);
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
         case BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS:
            return ((InternalEList<?>)getBehavioralPatterns()).basicRemove(otherEnd, msgs);
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
         case BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS:
            return getBehavioralPatterns();
         case BehavioralpatternPackage.BP_CATALOG__PS_CATALOG:
            if (resolve) return getPsCatalog();
            return basicGetPsCatalog();
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
         case BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS:
            getBehavioralPatterns().clear();
            getBehavioralPatterns().addAll((Collection<? extends BehavioralPattern>)newValue);
            return;
         case BehavioralpatternPackage.BP_CATALOG__PS_CATALOG:
            setPsCatalog((PSCatalog)newValue);
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
         case BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS:
            getBehavioralPatterns().clear();
            return;
         case BehavioralpatternPackage.BP_CATALOG__PS_CATALOG:
            setPsCatalog((PSCatalog)null);
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
         case BehavioralpatternPackage.BP_CATALOG__BEHAVIORAL_PATTERNS:
            return behavioralPatterns != null && !behavioralPatterns.isEmpty();
         case BehavioralpatternPackage.BP_CATALOG__PS_CATALOG:
            return psCatalog != null;
      }
      return super.eIsSet(featureID);
   }

} //BPCatalogImpl
