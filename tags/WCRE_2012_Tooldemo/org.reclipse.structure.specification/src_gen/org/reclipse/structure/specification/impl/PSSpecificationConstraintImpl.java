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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Specification Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl#getParents <em>Parents</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl#isAdditional <em>Additional</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl#getPatternSpecification <em>Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSSpecificationConstraintImpl extends IdentifierImpl implements PSSpecificationConstraint
{
   /**
    * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getWeight()
    * @generated
    * @ordered
    */
   protected static final double WEIGHT_EDEFAULT = 1.0;

   /**
    * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getWeight()
    * @generated
    * @ordered
    */
   protected double weight = WEIGHT_EDEFAULT;

   /**
    * The cached value of the '{@link #getParents() <em>Parents</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParents()
    * @generated
    * @ordered
    */
   protected EList<PSCombinedFragment> parents;

   /**
    * The default value of the '{@link #isAdditional() <em>Additional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isAdditional()
    * @generated
    * @ordered
    */
   protected static final boolean ADDITIONAL_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isAdditional() <em>Additional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isAdditional()
    * @generated
    * @ordered
    */
   protected boolean additional = ADDITIONAL_EDEFAULT;

   /**
    * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExpression()
    * @generated
    * @ordered
    */
   protected static final String EXPRESSION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExpression()
    * @generated
    * @ordered
    */
   protected String expression = EXPRESSION_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSSpecificationConstraintImpl()
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
      return SpecificationPackage.Literals.PS_SPECIFICATION_CONSTRAINT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public double getWeight()
   {
      return weight;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setWeight(double newWeight)
   {
      double oldWeight = weight;
      weight = newWeight;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__WEIGHT, oldWeight, weight));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSCombinedFragment> getParents()
   {
      if (parents == null)
      {
         parents = new EObjectWithInverseResolvingEList.ManyInverse<PSCombinedFragment>(PSCombinedFragment.class, this, SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PARENTS, SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN);
      }
      return parents;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isAdditional()
   {
      return additional;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAdditional(boolean newAdditional)
   {
      boolean oldAdditional = additional;
      additional = newAdditional;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__ADDITIONAL, oldAdditional, additional));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification getPatternSpecification()
   {
      if (eContainerFeatureID() != SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION) return null;
      return (PSPatternSpecification)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetPatternSpecification(PSPatternSpecification newPatternSpecification, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newPatternSpecification, SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPatternSpecification(PSPatternSpecification newPatternSpecification)
   {
      if (newPatternSpecification != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION && newPatternSpecification != null))
      {
         if (EcoreUtil.isAncestor(this, newPatternSpecification))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newPatternSpecification != null)
            msgs = ((InternalEObject)newPatternSpecification).eInverseAdd(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS, PSPatternSpecification.class, msgs);
         msgs = basicSetPatternSpecification(newPatternSpecification, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION, newPatternSpecification, newPatternSpecification));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getExpression()
   {
      return expression;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setExpression(String newExpression)
   {
      String oldExpression = expression;
      expression = newExpression;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__EXPRESSION, oldExpression, expression));
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
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PARENTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getParents()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetPatternSpecification((PSPatternSpecification)otherEnd, msgs);
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
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PARENTS:
            return ((InternalEList<?>)getParents()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION:
            return basicSetPatternSpecification(null, msgs);
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
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION:
            return eInternalContainer().eInverseRemove(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS, PSPatternSpecification.class, msgs);
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
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__WEIGHT:
            return getWeight();
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PARENTS:
            return getParents();
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__ADDITIONAL:
            return isAdditional();
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION:
            return getPatternSpecification();
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__EXPRESSION:
            return getExpression();
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
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__WEIGHT:
            setWeight((Double)newValue);
            return;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PARENTS:
            getParents().clear();
            getParents().addAll((Collection<? extends PSCombinedFragment>)newValue);
            return;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__ADDITIONAL:
            setAdditional((Boolean)newValue);
            return;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION:
            setPatternSpecification((PSPatternSpecification)newValue);
            return;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__EXPRESSION:
            setExpression((String)newValue);
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
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__WEIGHT:
            setWeight(WEIGHT_EDEFAULT);
            return;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PARENTS:
            getParents().clear();
            return;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__ADDITIONAL:
            setAdditional(ADDITIONAL_EDEFAULT);
            return;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION:
            setPatternSpecification((PSPatternSpecification)null);
            return;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__EXPRESSION:
            setExpression(EXPRESSION_EDEFAULT);
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
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__WEIGHT:
            return weight != WEIGHT_EDEFAULT;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PARENTS:
            return parents != null && !parents.isEmpty();
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__ADDITIONAL:
            return additional != ADDITIONAL_EDEFAULT;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION:
            return getPatternSpecification() != null;
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__EXPRESSION:
            return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
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
      result.append(" (weight: ");
      result.append(weight);
      result.append(", additional: ");
      result.append(additional);
      result.append(", expression: ");
      result.append(expression);
      result.append(')');
      return result.toString();
   }

} //PSSpecificationConstraintImpl
