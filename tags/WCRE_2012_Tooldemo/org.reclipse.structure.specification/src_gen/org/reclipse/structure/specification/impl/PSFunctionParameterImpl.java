/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Function Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSFunctionParameterImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSFunctionParameterImpl#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSFunctionParameterImpl extends IdentifierImpl implements PSFunctionParameter
{
   /**
    * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValue()
    * @generated
    * @ordered
    */
   protected static final double VALUE_EDEFAULT = 0.0;

   /**
    * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValue()
    * @generated
    * @ordered
    */
   protected double value = VALUE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSFunctionParameterImpl()
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
      return SpecificationPackage.Literals.PS_FUNCTION_PARAMETER;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public double getValue()
   {
      return value;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setValue(double newValue)
   {
      double oldValue = value;
      value = newValue;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_FUNCTION_PARAMETER__VALUE, oldValue, value));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSFuzzyConstraint getConstraint()
   {
      if (eContainerFeatureID() != SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT) return null;
      return (PSFuzzyConstraint)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetConstraint(PSFuzzyConstraint newConstraint, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newConstraint, SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setConstraint(PSFuzzyConstraint newConstraint)
   {
      if (newConstraint != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT && newConstraint != null))
      {
         if (EcoreUtil.isAncestor(this, newConstraint))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newConstraint != null)
            msgs = ((InternalEObject)newConstraint).eInverseAdd(this, SpecificationPackage.PS_FUZZY_CONSTRAINT__PARAMETERS, PSFuzzyConstraint.class, msgs);
         msgs = basicSetConstraint(newConstraint, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT, newConstraint, newConstraint));
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
         case SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetConstraint((PSFuzzyConstraint)otherEnd, msgs);
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
         case SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT:
            return basicSetConstraint(null, msgs);
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
         case SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT:
            return eInternalContainer().eInverseRemove(this, SpecificationPackage.PS_FUZZY_CONSTRAINT__PARAMETERS, PSFuzzyConstraint.class, msgs);
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
         case SpecificationPackage.PS_FUNCTION_PARAMETER__VALUE:
            return getValue();
         case SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT:
            return getConstraint();
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
         case SpecificationPackage.PS_FUNCTION_PARAMETER__VALUE:
            setValue((Double)newValue);
            return;
         case SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT:
            setConstraint((PSFuzzyConstraint)newValue);
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
         case SpecificationPackage.PS_FUNCTION_PARAMETER__VALUE:
            setValue(VALUE_EDEFAULT);
            return;
         case SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT:
            setConstraint((PSFuzzyConstraint)null);
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
         case SpecificationPackage.PS_FUNCTION_PARAMETER__VALUE:
            return value != VALUE_EDEFAULT;
         case SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT:
            return getConstraint() != null;
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
      result.append(" (value: ");
      result.append(value);
      result.append(')');
      return result.toString();
   }

} //PSFunctionParameterImpl
