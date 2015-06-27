/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations;

import org.reclipse.structure.specification.PSSpecificationConstraint;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Satisfied Specification Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedSpecificationConstraint()
 * @model
 * @generated
 */
public interface SatisfiedSpecificationConstraint extends SatisfiedConstraint
{
   /**
    * Returns the value of the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Expression</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Expression</em>' attribute.
    * @see #setExpression(String)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedSpecificationConstraint_Expression()
    * @model
    * @generated
    */
   String getExpression();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint#getExpression <em>Expression</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Expression</em>' attribute.
    * @see #getExpression()
    * @generated
    */
   void setExpression(String value);

   /**
    * Returns the value of the '<em><b>Constraint</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Constraint</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Constraint</em>' reference.
    * @see #setConstraint(PSSpecificationConstraint)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedSpecificationConstraint_Constraint()
    * @model
    * @generated
    */
   PSSpecificationConstraint getConstraint();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint#getConstraint <em>Constraint</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Constraint</em>' reference.
    * @see #getConstraint()
    * @generated
    */
   void setConstraint(PSSpecificationConstraint value);

} // SatisfiedSpecificationConstraint
