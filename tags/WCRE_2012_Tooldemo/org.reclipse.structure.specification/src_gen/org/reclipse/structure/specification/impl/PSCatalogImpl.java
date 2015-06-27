/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Catalog</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSCatalogImpl#getPatternSpecifications <em>Pattern Specifications</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSCatalogImpl#getMetamodel <em>Metamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSCatalogImpl extends IdentifierImpl implements PSCatalog
{
   /**
    * The cached value of the '{@link #getPatternSpecifications() <em>Pattern Specifications</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPatternSpecifications()
    * @generated
    * @ordered
    */
   protected EList<PSPatternSpecification> patternSpecifications;

   /**
    * The default value of the '{@link #getMetamodel() <em>Metamodel</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMetamodel()
    * @generated
    * @ordered
    */
   protected static final String METAMODEL_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getMetamodel() <em>Metamodel</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMetamodel()
    * @generated
    * @ordered
    */
   protected String metamodel = METAMODEL_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSCatalogImpl()
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
      return SpecificationPackage.Literals.PS_CATALOG;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSPatternSpecification> getPatternSpecifications()
   {
      if (patternSpecifications == null)
      {
         patternSpecifications = new EObjectContainmentWithInverseEList<PSPatternSpecification>(PSPatternSpecification.class, this, SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS, SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG);
      }
      return patternSpecifications;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getMetamodel()
   {
      return metamodel;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setMetamodel(String newMetamodel)
   {
      String oldMetamodel = metamodel;
      metamodel = newMetamodel;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_CATALOG__METAMODEL, oldMetamodel, metamodel));
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
         case SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getPatternSpecifications()).basicAdd(otherEnd, msgs);
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
         case SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS:
            return ((InternalEList<?>)getPatternSpecifications()).basicRemove(otherEnd, msgs);
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
         case SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS:
            return getPatternSpecifications();
         case SpecificationPackage.PS_CATALOG__METAMODEL:
            return getMetamodel();
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
         case SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS:
            getPatternSpecifications().clear();
            getPatternSpecifications().addAll((Collection<? extends PSPatternSpecification>)newValue);
            return;
         case SpecificationPackage.PS_CATALOG__METAMODEL:
            setMetamodel((String)newValue);
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
         case SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS:
            getPatternSpecifications().clear();
            return;
         case SpecificationPackage.PS_CATALOG__METAMODEL:
            setMetamodel(METAMODEL_EDEFAULT);
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
         case SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS:
            return patternSpecifications != null && !patternSpecifications.isEmpty();
         case SpecificationPackage.PS_CATALOG__METAMODEL:
            return METAMODEL_EDEFAULT == null ? metamodel != null : !METAMODEL_EDEFAULT.equals(metamodel);
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
      result.append(" (metamodel: ");
      result.append(metamodel);
      result.append(')');
      return result.toString();
   }

} //PSCatalogImpl
