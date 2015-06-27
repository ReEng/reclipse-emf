/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.fujaba.commons.identifier.Identifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Function Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSFunctionParameter#getValue <em>Value</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSFunctionParameter#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSFunctionParameter()
 * @model
 * @generated
 */
public interface PSFunctionParameter extends Identifier
{
   /**
    * Returns the value of the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Value</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Value</em>' attribute.
    * @see #setValue(double)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSFunctionParameter_Value()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   double getValue();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSFunctionParameter#getValue <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Value</em>' attribute.
    * @see #getValue()
    * @generated
    */
   void setValue(double value);

   /**
    * Returns the value of the '<em><b>Constraint</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSFuzzyConstraint#getParameters <em>Parameters</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Constraint</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Constraint</em>' container reference.
    * @see #setConstraint(PSFuzzyConstraint)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSFunctionParameter_Constraint()
    * @see org.reclipse.structure.specification.PSFuzzyConstraint#getParameters
    * @model opposite="parameters" keys="id" required="true" transient="false" ordered="false"
    * @generated
    */
   PSFuzzyConstraint getConstraint();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSFunctionParameter#getConstraint <em>Constraint</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Constraint</em>' container reference.
    * @see #getConstraint()
    * @generated
    */
   void setConstraint(PSFuzzyConstraint value);

} // PSFunctionParameter
