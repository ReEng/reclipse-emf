/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Attribute Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl#isAdditional <em>Additional</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl#getValueExpression <em>Value Expression</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSAttributeConstraintImpl extends IdentifierImpl implements PSAttributeConstraint
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
    * The default value of the '{@link #getValueExpression() <em>Value Expression</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValueExpression()
    * @generated
    * @ordered
    */
   protected static final String VALUE_EXPRESSION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getValueExpression() <em>Value Expression</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getValueExpression()
    * @generated
    * @ordered
    */
   protected String valueExpression = VALUE_EXPRESSION_EDEFAULT;

   /**
    * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOperator()
    * @generated
    * @ordered
    */
   protected static final OperatorType OPERATOR_EDEFAULT = OperatorType.LESS;

   /**
    * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOperator()
    * @generated
    * @ordered
    */
   protected OperatorType operator = OPERATOR_EDEFAULT;

   /**
    * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAttribute()
    * @generated
    * @ordered
    */
   protected EAttribute attribute;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSAttributeConstraintImpl()
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
      return SpecificationPackage.Literals.PS_ATTRIBUTE_CONSTRAINT;
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
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__WEIGHT, oldWeight, weight));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSNode getNode()
   {
      if (eContainerFeatureID() != SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE) return null;
      return (PSNode)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetNode(PSNode newNode, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newNode, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setNode(PSNode newNode)
   {
      if (newNode != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE && newNode != null))
      {
         if (EcoreUtil.isAncestor(this, newNode))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newNode != null)
            msgs = ((InternalEObject)newNode).eInverseAdd(this, SpecificationPackage.PS_NODE__NODE_CONSTRAINTS, PSNode.class, msgs);
         msgs = basicSetNode(newNode, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE, newNode, newNode));
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
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__EXPRESSION, oldExpression, expression));
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
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ADDITIONAL, oldAdditional, additional));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getValueExpression()
   {
      return valueExpression;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setValueExpression(String newValueExpression)
   {
      String oldValueExpression = valueExpression;
      valueExpression = newValueExpression;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__VALUE_EXPRESSION, oldValueExpression, valueExpression));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public OperatorType getOperator()
   {
      return operator;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setOperator(OperatorType newOperator)
   {
      OperatorType oldOperator = operator;
      operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__OPERATOR, oldOperator, operator));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttribute()
   {
      if (attribute != null && attribute.eIsProxy())
      {
         InternalEObject oldAttribute = (InternalEObject)attribute;
         attribute = (EAttribute)eResolveProxy(oldAttribute);
         if (attribute != oldAttribute)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE, oldAttribute, attribute));
         }
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute basicGetAttribute()
   {
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAttribute(EAttribute newAttribute)
   {
      EAttribute oldAttribute = attribute;
      attribute = newAttribute;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE, oldAttribute, attribute));
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
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetNode((PSNode)otherEnd, msgs);
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
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE:
            return basicSetNode(null, msgs);
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
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE:
            return eInternalContainer().eInverseRemove(this, SpecificationPackage.PS_NODE__NODE_CONSTRAINTS, PSNode.class, msgs);
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
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__WEIGHT:
            return getWeight();
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE:
            return getNode();
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__EXPRESSION:
            return getExpression();
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ADDITIONAL:
            return isAdditional();
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__VALUE_EXPRESSION:
            return getValueExpression();
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__OPERATOR:
            return getOperator();
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE:
            if (resolve) return getAttribute();
            return basicGetAttribute();
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
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__WEIGHT:
            setWeight((Double)newValue);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE:
            setNode((PSNode)newValue);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__EXPRESSION:
            setExpression((String)newValue);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ADDITIONAL:
            setAdditional((Boolean)newValue);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__VALUE_EXPRESSION:
            setValueExpression((String)newValue);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__OPERATOR:
            setOperator((OperatorType)newValue);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE:
            setAttribute((EAttribute)newValue);
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
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__WEIGHT:
            setWeight(WEIGHT_EDEFAULT);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE:
            setNode((PSNode)null);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__EXPRESSION:
            setExpression(EXPRESSION_EDEFAULT);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ADDITIONAL:
            setAdditional(ADDITIONAL_EDEFAULT);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__VALUE_EXPRESSION:
            setValueExpression(VALUE_EXPRESSION_EDEFAULT);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__OPERATOR:
            setOperator(OPERATOR_EDEFAULT);
            return;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE:
            setAttribute((EAttribute)null);
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
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__WEIGHT:
            return weight != WEIGHT_EDEFAULT;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__NODE:
            return getNode() != null;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__EXPRESSION:
            return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ADDITIONAL:
            return additional != ADDITIONAL_EDEFAULT;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__VALUE_EXPRESSION:
            return VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__OPERATOR:
            return operator != OPERATOR_EDEFAULT;
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE:
            return attribute != null;
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
      result.append(", expression: ");
      result.append(expression);
      result.append(", additional: ");
      result.append(additional);
      result.append(", valueExpression: ");
      result.append(valueExpression);
      result.append(", operator: ");
      result.append(operator);
      result.append(')');
      return result.toString();
   }

} //PSAttributeConstraintImpl
