/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;

import org.reclipse.structure.specification.PSObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BP Set Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * BPSetObject represents a set of objects.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.BPSetObject#getTypeReference <em>Type Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPSetObject()
 * @model
 * @generated
 */
public interface BPSetObject extends AbstractSequenceDiagramObject
{
   /**
    * Returns the value of the '<em><b>Type Reference</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type Reference</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Type Reference</em>' reference.
    * @see #setTypeReference(PSObject)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPSetObject_TypeReference()
    * @model
    * @generated
    */
   PSObject getTypeReference();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPSetObject#getTypeReference <em>Type Reference</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type Reference</em>' reference.
    * @see #getTypeReference()
    * @generated
    */
   void setTypeReference(PSObject value);

} // BPSetObject
