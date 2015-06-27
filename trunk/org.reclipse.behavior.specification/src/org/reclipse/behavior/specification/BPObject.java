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
 * A representation of the model object '<em><b>BP Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * BPObject represents an object within a BehavioralPattern. A BPObject has a reference to an SPObject from the structure specification plugin.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.BPObject#getTypeReference <em>Type Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPObject()
 * @model
 * @generated
 */
public interface BPObject extends AbstractSequenceDiagramObject
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
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPObject_TypeReference()
    * @model
    * @generated
    */
   PSObject getTypeReference();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPObject#getTypeReference <em>Type Reference</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type Reference</em>' reference.
    * @see #getTypeReference()
    * @generated
    */
   void setTypeReference(PSObject value);

} // BPObject
