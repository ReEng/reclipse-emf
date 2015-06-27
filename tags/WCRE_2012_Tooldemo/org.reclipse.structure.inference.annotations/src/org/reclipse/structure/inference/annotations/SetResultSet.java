/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Result Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.SetResultSet#getAnnotationSets <em>Annotation Sets</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.SetResultSet#getParentAnnotation <em>Parent Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSetResultSet()
 * @model
 * @generated
 */
public interface SetResultSet extends EObject
{
   /**
    * Returns the value of the '<em><b>Annotation Sets</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.inference.annotations.AnnotationSet}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.inference.annotations.AnnotationSet#getSetResultSet <em>Set Result Set</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Annotation Sets</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Annotation Sets</em>' containment reference list.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSetResultSet_AnnotationSets()
    * @see org.reclipse.structure.inference.annotations.AnnotationSet#getSetResultSet
    * @model opposite="setResultSet" containment="true"
    * @generated
    */
   EList<AnnotationSet> getAnnotationSets();

   /**
    * Returns the value of the '<em><b>Parent Annotation</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getSetResultSet <em>Set Result Set</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parent Annotation</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Parent Annotation</em>' container reference.
    * @see #setParentAnnotation(ASGAnnotation)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getSetResultSet_ParentAnnotation()
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getSetResultSet
    * @model opposite="setResultSet" transient="false"
    * @generated
    */
   ASGAnnotation getParentAnnotation();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.SetResultSet#getParentAnnotation <em>Parent Annotation</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Parent Annotation</em>' container reference.
    * @see #getParentAnnotation()
    * @generated
    */
   void setParentAnnotation(ASGAnnotation value);

} // SetResultSet
