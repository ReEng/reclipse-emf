/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification;

import de.uni_paderborn.basicSequenceDiagram.LifelineFragment;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BP Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A BPAssignment represents assignments with a BPObject on the leftSide and a BPArgument on the right side. An assignment means that the object's identity is changed. BPAssignment is visualized at the lifeline of the left-side-object.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.BPAssignment#getLeftSide <em>Left Side</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.BPAssignment#getRightSide <em>Right Side</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPAssignment()
 * @model
 * @generated
 */
public interface BPAssignment extends LifelineFragment
{
   /**
    * Returns the value of the '<em><b>Left Side</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Left Side</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Left Side</em>' reference.
    * @see #setLeftSide(BPObject)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPAssignment_LeftSide()
    * @model required="true"
    * @generated
    */
   BPObject getLeftSide();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPAssignment#getLeftSide <em>Left Side</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Left Side</em>' reference.
    * @see #getLeftSide()
    * @generated
    */
   void setLeftSide(BPObject value);

   /**
    * Returns the value of the '<em><b>Right Side</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Right Side</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Right Side</em>' reference.
    * @see #setRightSide(BPArgument)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPAssignment_RightSide()
    * @model required="true"
    * @generated
    */
   BPArgument getRightSide();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPAssignment#getRightSide <em>Right Side</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Right Side</em>' reference.
    * @see #getRightSide()
    * @generated
    */
   void setRightSide(BPArgument value);

} // BPAssignment
