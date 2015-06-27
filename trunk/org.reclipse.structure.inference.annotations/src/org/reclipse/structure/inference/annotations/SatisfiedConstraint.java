/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Satisfied Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.SatisfiedConstraint#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedConstraint()
 * @model abstract="true"
 * @generated
 */
public interface SatisfiedConstraint extends EObject
{
   /**
    * Returns the value of the '<em><b>Annotation</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getSatisfiedConstraints <em>Satisfied Constraints</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Annotation</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Annotation</em>' container reference.
    * @see #setAnnotation(ASGAnnotation)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSatisfiedConstraint_Annotation()
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getSatisfiedConstraints
    * @model opposite="satisfiedConstraints" transient="false"
    * @generated
    */
   ASGAnnotation getAnnotation();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.SatisfiedConstraint#getAnnotation <em>Annotation</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Annotation</em>' container reference.
    * @see #getAnnotation()
    * @generated
    */
   void setAnnotation(ASGAnnotation value);

} // SatisfiedConstraint
