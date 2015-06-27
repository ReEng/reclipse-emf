/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSObject#getInstanceOf <em>Instance Of</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSObject()
 * @model
 * @generated
 */
public interface PSObject extends PSNode
{
   /**
    * Returns the value of the '<em><b>Instance Of</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Instance Of</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Instance Of</em>' reference.
    * @see #setInstanceOf(EClass)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSObject_InstanceOf()
    * @model keys="name" ordered="false"
    * @generated
    */
   EClass getInstanceOf();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSObject#getInstanceOf <em>Instance Of</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Instance Of</em>' reference.
    * @see #getInstanceOf()
    * @generated
    */
   void setInstanceOf(EClass value);

} // PSObject
