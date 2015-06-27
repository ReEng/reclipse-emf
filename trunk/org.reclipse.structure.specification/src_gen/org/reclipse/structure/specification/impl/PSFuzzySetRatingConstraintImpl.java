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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Fuzzy Set Rating Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl#getMathFunctionID <em>Math Function ID</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSFuzzySetRatingConstraintImpl extends IdentifierImpl implements PSFuzzySetRatingConstraint
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
    * The default value of the '{@link #getMathFunctionID() <em>Math Function ID</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMathFunctionID()
    * @generated
    * @ordered
    */
   protected static final String MATH_FUNCTION_ID_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getMathFunctionID() <em>Math Function ID</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMathFunctionID()
    * @generated
    * @ordered
    */
   protected String mathFunctionID = MATH_FUNCTION_ID_EDEFAULT;

   /**
    * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParameters()
    * @generated
    * @ordered
    */
   protected EList<PSFunctionParameter> parameters;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSFuzzySetRatingConstraintImpl()
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
      return SpecificationPackage.Literals.PS_FUZZY_SET_RATING_CONSTRAINT;
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
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__WEIGHT, oldWeight, weight));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSNode getNode()
   {
      if (eContainerFeatureID() != SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE) return null;
      return (PSNode)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetNode(PSNode newNode, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newNode, SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setNode(PSNode newNode)
   {
      if (newNode != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE && newNode != null))
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
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE, newNode, newNode));
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
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__EXPRESSION, oldExpression, expression));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getMathFunctionID()
   {
      return mathFunctionID;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setMathFunctionID(String newMathFunctionID)
   {
      String oldMathFunctionID = mathFunctionID;
      mathFunctionID = newMathFunctionID;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__MATH_FUNCTION_ID, oldMathFunctionID, mathFunctionID));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSFunctionParameter> getParameters()
   {
      if (parameters == null)
      {
         parameters = new EObjectContainmentWithInverseEList<PSFunctionParameter>(PSFunctionParameter.class, this, SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS, SpecificationPackage.PS_FUNCTION_PARAMETER__CONSTRAINT);
      }
      return parameters;
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
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetNode((PSNode)otherEnd, msgs);
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameters()).basicAdd(otherEnd, msgs);
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
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE:
            return basicSetNode(null, msgs);
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS:
            return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE:
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
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__WEIGHT:
            return getWeight();
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE:
            return getNode();
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__EXPRESSION:
            return getExpression();
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__MATH_FUNCTION_ID:
            return getMathFunctionID();
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS:
            return getParameters();
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
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__WEIGHT:
            setWeight((Double)newValue);
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE:
            setNode((PSNode)newValue);
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__EXPRESSION:
            setExpression((String)newValue);
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__MATH_FUNCTION_ID:
            setMathFunctionID((String)newValue);
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS:
            getParameters().clear();
            getParameters().addAll((Collection<? extends PSFunctionParameter>)newValue);
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
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__WEIGHT:
            setWeight(WEIGHT_EDEFAULT);
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE:
            setNode((PSNode)null);
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__EXPRESSION:
            setExpression(EXPRESSION_EDEFAULT);
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__MATH_FUNCTION_ID:
            setMathFunctionID(MATH_FUNCTION_ID_EDEFAULT);
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS:
            getParameters().clear();
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
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__WEIGHT:
            return weight != WEIGHT_EDEFAULT;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__NODE:
            return getNode() != null;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__EXPRESSION:
            return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__MATH_FUNCTION_ID:
            return MATH_FUNCTION_ID_EDEFAULT == null ? mathFunctionID != null : !MATH_FUNCTION_ID_EDEFAULT.equals(mathFunctionID);
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS:
            return parameters != null && !parameters.isEmpty();
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
      result.append(", mathFunctionID: ");
      result.append(mathFunctionID);
      result.append(')');
      return result.toString();
   }

} //PSFuzzySetRatingConstraintImpl
