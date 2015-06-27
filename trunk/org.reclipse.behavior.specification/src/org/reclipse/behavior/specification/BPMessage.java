/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;

import org.eclipse.emf.common.util.EList;

import org.reclipse.structure.specification.PSObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BP Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A BPMessage represents messages that are sent between objects within a BehavioralPattern. It has reference to a PSObject from the structure specification plugin. The selfCall property determines if the sending and the receiving object is the same for messages from/to PSSetObjects. A BPMessage can have an arbitrary number of BPArguments.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.BPMessage#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.BPMessage#isSelfCall <em>Self Call</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.BPMessage#getMethodReference <em>Method Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPMessage()
 * @model
 * @generated
 */
public interface BPMessage extends AbstractMessage
{
   /**
    * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.behavior.specification.BPArgument}.
    * It is bidirectional and its opposite is '{@link org.reclipse.behavior.specification.BPArgument#getMessage <em>Message</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Arguments</em>' containment reference list.
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPMessage_Arguments()
    * @see org.reclipse.behavior.specification.BPArgument#getMessage
    * @model opposite="message" containment="true"
    * @generated
    */
   EList<BPArgument> getArguments();

   /**
    * Returns the value of the '<em><b>Self Call</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Self Call</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Self Call</em>' attribute.
    * @see #setSelfCall(boolean)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPMessage_SelfCall()
    * @model
    * @generated
    */
   boolean isSelfCall();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPMessage#isSelfCall <em>Self Call</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Self Call</em>' attribute.
    * @see #isSelfCall()
    * @generated
    */
   void setSelfCall(boolean value);

   /**
    * Returns the value of the '<em><b>Method Reference</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Method Reference</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Method Reference</em>' reference.
    * @see #setMethodReference(PSObject)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPMessage_MethodReference()
    * @model
    * @generated
    */
   PSObject getMethodReference();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPMessage#getMethodReference <em>Method Reference</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Method Reference</em>' reference.
    * @see #getMethodReference()
    * @generated
    */
   void setMethodReference(PSObject value);

} // BPMessage
