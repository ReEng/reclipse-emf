/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.ecore.EAttribute;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Attribute Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSAttributeConstraint#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSAttributeConstraint()
 * @model
 * @generated
 */
public interface PSAttributeConstraint extends PSBooleanConstraint
{
   /**
    * Returns the value of the '<em><b>Attribute</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Attribute</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Attribute</em>' reference.
    * @see #setAttribute(EAttribute)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSAttributeConstraint_Attribute()
    * @model keys="name" ordered="false"
    * @generated
    */
   EAttribute getAttribute();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSAttributeConstraint#getAttribute <em>Attribute</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Attribute</em>' reference.
    * @see #getAttribute()
    * @generated
    */
   void setAttribute(EAttribute value);

} // PSAttributeConstraint
