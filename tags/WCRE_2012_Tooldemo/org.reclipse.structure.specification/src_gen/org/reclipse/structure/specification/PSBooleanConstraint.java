/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Boolean Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSBooleanConstraint#isAdditional <em>Additional</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSBooleanConstraint#getValueExpression <em>Value Expression</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSBooleanConstraint#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSBooleanConstraint()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface PSBooleanConstraint extends PSNodeConstraint
{
   /**
    * Returns the value of the '<em><b>Additional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Additional</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Additional</em>' attribute.
    * @see #setAdditional(boolean)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSBooleanConstraint_Additional()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   boolean isAdditional();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSBooleanConstraint#isAdditional <em>Additional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Additional</em>' attribute.
    * @see #isAdditional()
    * @generated
    */
   void setAdditional(boolean value);

   /**
    * Returns the value of the '<em><b>Value Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Value Expression</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Value Expression</em>' attribute.
    * @see #setValueExpression(String)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSBooleanConstraint_ValueExpression()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   String getValueExpression();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSBooleanConstraint#getValueExpression <em>Value Expression</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Value Expression</em>' attribute.
    * @see #getValueExpression()
    * @generated
    */
   void setValueExpression(String value);

   /**
    * Returns the value of the '<em><b>Operator</b></em>' attribute.
    * The literals are from the enumeration {@link org.reclipse.structure.specification.OperatorType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Operator</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Operator</em>' attribute.
    * @see org.reclipse.structure.specification.OperatorType
    * @see #setOperator(OperatorType)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSBooleanConstraint_Operator()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   OperatorType getOperator();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSBooleanConstraint#getOperator <em>Operator</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Operator</em>' attribute.
    * @see org.reclipse.structure.specification.OperatorType
    * @see #getOperator()
    * @generated
    */
   void setOperator(OperatorType value);

} // PSBooleanConstraint
