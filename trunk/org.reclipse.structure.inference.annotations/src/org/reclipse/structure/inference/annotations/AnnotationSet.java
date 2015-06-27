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
 * A representation of the model object '<em><b>Annotation Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.AnnotationSet#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.AnnotationSet#getSetResultSet <em>Set Result Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getAnnotationSet()
 * @model
 * @generated
 */
public interface AnnotationSet extends EObject
{
   /**
    * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.inference.annotations.ASGAnnotation}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Annotations</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Annotations</em>' containment reference list.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getAnnotationSet_Annotations()
    * @model containment="true"
    * @generated
    */
   EList<ASGAnnotation> getAnnotations();

   /**
    * Returns the value of the '<em><b>Set Result Set</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.inference.annotations.SetResultSet#getAnnotationSets <em>Annotation Sets</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Set Result Set</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Set Result Set</em>' container reference.
    * @see #setSetResultSet(SetResultSet)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getAnnotationSet_SetResultSet()
    * @see org.reclipse.structure.inference.annotations.SetResultSet#getAnnotationSets
    * @model opposite="annotationSets" transient="false"
    * @generated
    */
   SetResultSet getSetResultSet();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.AnnotationSet#getSetResultSet <em>Set Result Set</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Set Result Set</em>' container reference.
    * @see #getSetResultSet()
    * @generated
    */
   void setSetResultSet(SetResultSet value);

} // AnnotationSet
