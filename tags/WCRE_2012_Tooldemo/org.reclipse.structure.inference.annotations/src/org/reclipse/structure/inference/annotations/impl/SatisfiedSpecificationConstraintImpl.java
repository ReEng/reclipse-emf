/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint;
import org.reclipse.structure.specification.PSSpecificationConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Satisfied Specification Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SatisfiedSpecificationConstraintImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SatisfiedSpecificationConstraintImpl#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SatisfiedSpecificationConstraintImpl extends SatisfiedConstraintImpl implements SatisfiedSpecificationConstraint
{
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
    * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConstraint()
    * @generated
    * @ordered
    */
   protected PSSpecificationConstraint constraint;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected SatisfiedSpecificationConstraintImpl()
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
      return AnnotationsPackage.Literals.SATISFIED_SPECIFICATION_CONSTRAINT;
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
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__EXPRESSION, oldExpression, expression));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSSpecificationConstraint getConstraint()
   {
      if (constraint != null && constraint.eIsProxy())
      {
         InternalEObject oldConstraint = (InternalEObject)constraint;
         constraint = (PSSpecificationConstraint)eResolveProxy(oldConstraint);
         if (constraint != oldConstraint)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT, oldConstraint, constraint));
         }
      }
      return constraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSSpecificationConstraint basicGetConstraint()
   {
      return constraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setConstraint(PSSpecificationConstraint newConstraint)
   {
      PSSpecificationConstraint oldConstraint = constraint;
      constraint = newConstraint;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT, oldConstraint, constraint));
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
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__EXPRESSION:
            return getExpression();
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT:
            if (resolve) return getConstraint();
            return basicGetConstraint();
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
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__EXPRESSION:
            setExpression((String)newValue);
            return;
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT:
            setConstraint((PSSpecificationConstraint)newValue);
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
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__EXPRESSION:
            setExpression(EXPRESSION_EDEFAULT);
            return;
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT:
            setConstraint((PSSpecificationConstraint)null);
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
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__EXPRESSION:
            return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT:
            return constraint != null;
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
      result.append(" (expression: ");
      result.append(expression);
      result.append(')');
      return result.toString();
   }

} //SatisfiedSpecificationConstraintImpl
