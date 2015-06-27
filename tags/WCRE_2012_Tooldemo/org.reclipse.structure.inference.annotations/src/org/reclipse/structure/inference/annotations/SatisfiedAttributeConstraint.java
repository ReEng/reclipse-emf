/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations;

import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.specification.PSAttributeConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Satisfied Attribute Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getNodeID <em>Node ID</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getAttributeIndex <em>Attribute Index</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getContext <em>Context</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedAttributeConstraint()
 * @model
 * @generated
 */
public interface SatisfiedAttributeConstraint extends SatisfiedConstraint
{
   /**
    * Returns the value of the '<em><b>Node ID</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Node ID</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Node ID</em>' attribute.
    * @see #setNodeID(String)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedAttributeConstraint_NodeID()
    * @model
    * @generated
    */
   String getNodeID();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getNodeID <em>Node ID</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Node ID</em>' attribute.
    * @see #getNodeID()
    * @generated
    */
   void setNodeID(String value);

   /**
    * Returns the value of the '<em><b>Attribute Index</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Attribute Index</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Attribute Index</em>' attribute.
    * @see #setAttributeIndex(int)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedAttributeConstraint_AttributeIndex()
    * @model
    * @generated
    */
   int getAttributeIndex();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getAttributeIndex <em>Attribute Index</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Attribute Index</em>' attribute.
    * @see #getAttributeIndex()
    * @generated
    */
   void setAttributeIndex(int value);

   /**
    * Returns the value of the '<em><b>Context</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Context</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Context</em>' reference.
    * @see #setContext(EObject)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedAttributeConstraint_Context()
    * @model
    * @generated
    */
   EObject getContext();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getContext <em>Context</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Context</em>' reference.
    * @see #getContext()
    * @generated
    */
   void setContext(EObject value);

   /**
    * Returns the value of the '<em><b>Constraint</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Constraint</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Constraint</em>' reference.
    * @see #setConstraint(PSAttributeConstraint)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedAttributeConstraint_Constraint()
    * @model
    * @generated
    */
   PSAttributeConstraint getConstraint();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getConstraint <em>Constraint</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Constraint</em>' reference.
    * @see #getConstraint()
    * @generated
    */
   void setConstraint(PSAttributeConstraint value);

} // SatisfiedAttributeConstraint
