/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification;

import org.fujaba.commons.identifier.Identifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

import org.reclipse.structure.specification.PSObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BP Argument</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A BPArgument belongs to a BPMessage. It has a reference to a PSObject from the structure specification plugin.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.BPArgument#getMessage <em>Message</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.BPArgument#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.BPArgument#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPArgument()
 * @model
 * @generated
 */
public interface BPArgument extends Identifier
{
   /**
    * Returns the value of the '<em><b>Message</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.behavior.specification.BPMessage#getArguments <em>Arguments</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Message</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Message</em>' container reference.
    * @see #setMessage(BPMessage)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPArgument_Message()
    * @see org.reclipse.behavior.specification.BPMessage#getArguments
    * @model opposite="arguments" required="true" transient="false"
    * @generated
    */
   BPMessage getMessage();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPArgument#getMessage <em>Message</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Message</em>' container reference.
    * @see #getMessage()
    * @generated
    */
   void setMessage(BPMessage value);

   /**
    * Returns the value of the '<em><b>Statement</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Statement</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Statement</em>' attribute.
    * @see #setStatement(String)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPArgument_Statement()
    * @model
    * @generated
    */
   String getStatement();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPArgument#getStatement <em>Statement</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Statement</em>' attribute.
    * @see #getStatement()
    * @generated
    */
   void setStatement(String value);

   /**
    * Returns the value of the '<em><b>Type</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' reference.
    * @see #setType(PSObject)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPArgument_Type()
    * @model
    * @generated
    */
   PSObject getType();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPArgument#getType <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' reference.
    * @see #getType()
    * @generated
    */
   void setType(PSObject value);

} // BPArgument
