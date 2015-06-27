/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSLink#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSLink#isNegative <em>Negative</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSLink#getInstanceOf <em>Instance Of</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSLink()
 * @model
 * @generated
 */
public interface PSLink extends PSConnection
{
   /**
    * Returns the value of the '<em><b>Qualifier</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Qualifier</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Qualifier</em>' attribute.
    * @see #setQualifier(String)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSLink_Qualifier()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   String getQualifier();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSLink#getQualifier <em>Qualifier</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Qualifier</em>' attribute.
    * @see #getQualifier()
    * @generated
    */
   void setQualifier(String value);

   /**
    * Returns the value of the '<em><b>Negative</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Negative</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Negative</em>' attribute.
    * @see #setNegative(boolean)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSLink_Negative()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   boolean isNegative();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSLink#isNegative <em>Negative</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Negative</em>' attribute.
    * @see #isNegative()
    * @generated
    */
   void setNegative(boolean value);

   /**
    * Returns the value of the '<em><b>Instance Of</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Instance Of</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Instance Of</em>' reference.
    * @see #setInstanceOf(EReference)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSLink_InstanceOf()
    * @model keys="name" ordered="false"
    * @generated
    */
   EReference getInstanceOf();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSLink#getInstanceOf <em>Instance Of</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Instance Of</em>' reference.
    * @see #getInstanceOf()
    * @generated
    */
   void setInstanceOf(EReference value);

} // PSLink
