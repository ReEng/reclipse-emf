/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.specification.PSPatternSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ASG Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getAnnotatedElements <em>Annotated Elements</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getAntecedentAnnos <em>Antecedent Annos</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getConsequentAnnos <em>Consequent Annos</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getBoundObjects <em>Bound Objects</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#isValid <em>Valid</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getAnnotationRanking <em>Annotation Ranking</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getSatisfiedConstraints <em>Satisfied Constraints</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getSetResultSet <em>Set Result Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation()
 * @model
 * @generated
 */
public interface ASGAnnotation extends EAnnotation, ENamedElement
{
   /**
    * Returns the value of the '<em><b>Annotated Elements</b></em>' map.
    * The key is of type {@link java.lang.String},
    * and the value is of type list of {@link org.eclipse.emf.ecore.EObject},
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Annotated Elements</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Annotated Elements</em>' map.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_AnnotatedElements()
    * @model mapType="org.reclipse.structure.inference.annotations.StringToEObjectMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject>"
    * @generated
    */
   EMap<String, EList<EObject>> getAnnotatedElements();

   /**
    * Returns the value of the '<em><b>Antecedent Annos</b></em>' reference list.
    * The list contents are of type {@link org.reclipse.structure.inference.annotations.ASGAnnotation}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getConsequentAnnos <em>Consequent Annos</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Antecedent Annos</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Antecedent Annos</em>' reference list.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_AntecedentAnnos()
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getConsequentAnnos
    * @model opposite="consequentAnnos"
    * @generated
    */
   EList<ASGAnnotation> getAntecedentAnnos();

   /**
    * Returns the value of the '<em><b>Consequent Annos</b></em>' reference list.
    * The list contents are of type {@link org.reclipse.structure.inference.annotations.ASGAnnotation}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getAntecedentAnnos <em>Antecedent Annos</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Consequent Annos</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Consequent Annos</em>' reference list.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_ConsequentAnnos()
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getAntecedentAnnos
    * @model opposite="antecedentAnnos"
    * @generated
    */
   EList<ASGAnnotation> getConsequentAnnos();

   /**
    * Returns the value of the '<em><b>Bound Objects</b></em>' map.
    * The key is of type {@link java.lang.String},
    * and the value is of type list of {@link org.eclipse.emf.ecore.EObject},
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Bound Objects</em>' map isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Bound Objects</em>' map.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_BoundObjects()
    * @model mapType="org.reclipse.structure.inference.annotations.StringToEObjectMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject>"
    * @generated
    */
   EMap<String, EList<EObject>> getBoundObjects();

   /**
    * Returns the value of the '<em><b>Valid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Valid</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Valid</em>' attribute.
    * @see #setValid(boolean)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_Valid()
    * @model
    * @generated
    */
   boolean isValid();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#isValid <em>Valid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Valid</em>' attribute.
    * @see #isValid()
    * @generated
    */
   void setValid(boolean value);

   /**
    * Returns the value of the '<em><b>Pattern</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Pattern</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pattern</em>' reference.
    * @see #setPattern(PSPatternSpecification)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_Pattern()
    * @model
    * @generated
    */
   PSPatternSpecification getPattern();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getPattern <em>Pattern</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Pattern</em>' reference.
    * @see #getPattern()
    * @generated
    */
   void setPattern(PSPatternSpecification value);

   /**
    * Returns the value of the '<em><b>Annotation Ranking</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Annotation Ranking</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Annotation Ranking</em>' attribute.
    * @see #setAnnotationRanking(double)
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_AnnotationRanking()
    * @model
    * @generated
    */
   double getAnnotationRanking();

   /**
    * Sets the value of the '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getAnnotationRanking <em>Annotation Ranking</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Annotation Ranking</em>' attribute.
    * @see #getAnnotationRanking()
    * @generated
    */
   void setAnnotationRanking(double value);

   /**
    * Returns the value of the '<em><b>Satisfied Constraints</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.inference.annotations.SatisfiedConstraint}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.inference.annotations.SatisfiedConstraint#getAnnotation <em>Annotation</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Satisfied Constraints</em>' attribute list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Satisfied Constraints</em>' containment reference list.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_SatisfiedConstraints()
    * @see org.reclipse.structure.inference.annotations.SatisfiedConstraint#getAnnotation
    * @model opposite="annotation" containment="true"
    * @generated
    */
   EList<SatisfiedConstraint> getSatisfiedConstraints();

   /**
    * Returns the value of the '<em><b>Set Result Set</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.inference.annotations.SetResultSet}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.inference.annotations.SetResultSet#getParentAnnotation <em>Parent Annotation</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Set Result Set</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Set Result Set</em>' containment reference list.
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#getASGAnnotation_SetResultSet()
    * @see org.reclipse.structure.inference.annotations.SetResultSet#getParentAnnotation
    * @model opposite="parentAnnotation" containment="true"
    * @generated
    */
   EList<SetResultSet> getSetResultSet();

} // ASGAnnotation
