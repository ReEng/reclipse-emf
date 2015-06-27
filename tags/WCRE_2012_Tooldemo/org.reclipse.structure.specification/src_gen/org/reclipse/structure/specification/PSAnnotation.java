/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSAnnotation#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSAnnotation()
 * @model
 * @generated
 */
public interface PSAnnotation extends PSNode
{
   /**
    * Returns the value of the '<em><b>Type</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' reference.
    * @see #setType(PSPatternSpecification)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSAnnotation_Type()
    * @model keys="id" ordered="false"
    * @generated
    */
   PSPatternSpecification getType();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSAnnotation#getType <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' reference.
    * @see #getType()
    * @generated
    */
   void setType(PSPatternSpecification value);

} // PSAnnotation
