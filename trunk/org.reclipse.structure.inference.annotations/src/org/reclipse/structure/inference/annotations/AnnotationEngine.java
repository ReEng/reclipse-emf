/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotation Engine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.AnnotationEngine#getFailedApplications <em>Failed Applications</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.AnnotationEngine#getFoundAnnotations <em>Found Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getAnnotationEngine()
 * @model
 * @generated
 */
public interface AnnotationEngine extends EObject
{
   /**
    * Returns the value of the '<em><b>Failed Applications</b></em>' map.
    * The key is of type {@link java.lang.String},
    * and the value is of type list of {@link org.eclipse.emf.ecore.EObject},
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Failed Applications</em>' map isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Failed Applications</em>' map.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getAnnotationEngine_FailedApplications()
    * @model mapType="org.reclipse.structure.inference.annotations.StringToEObjectMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject>"
    * @generated
    */
   EMap<String, EList<EObject>> getFailedApplications();

   /**
    * Returns the value of the '<em><b>Found Annotations</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Found Annotations</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Found Annotations</em>' reference.
    * @see #setFoundAnnotations(AnnotationSet)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getAnnotationEngine_FoundAnnotations()
    * @model
    * @generated
    */
   AnnotationSet getFoundAnnotations();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.AnnotationEngine#getFoundAnnotations <em>Found Annotations</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Found Annotations</em>' reference.
    * @see #getFoundAnnotations()
    * @generated
    */
   void setFoundAnnotations(AnnotationSet value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   void removeFromFailedApplications(EObject context);

} // AnnotationEngine
