/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Fuzzy Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSFuzzyConstraint#getMathFunctionID <em>Math Function ID</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSFuzzyConstraint#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSFuzzyConstraint()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface PSFuzzyConstraint extends PSNodeConstraint
{
   /**
    * Returns the value of the '<em><b>Math Function ID</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Math Function ID</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Math Function ID</em>' attribute.
    * @see #setMathFunctionID(String)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSFuzzyConstraint_MathFunctionID()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   String getMathFunctionID();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSFuzzyConstraint#getMathFunctionID <em>Math Function ID</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Math Function ID</em>' attribute.
    * @see #getMathFunctionID()
    * @generated
    */
   void setMathFunctionID(String value);

   /**
    * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSFunctionParameter}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSFunctionParameter#getConstraint <em>Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Parameters</em>' containment reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSFuzzyConstraint_Parameters()
    * @see org.reclipse.structure.specification.PSFunctionParameter#getConstraint
    * @model opposite="constraint" containment="true" keys="id"
    * @generated
    */
   EList<PSFunctionParameter> getParameters();

} // PSFuzzyConstraint
