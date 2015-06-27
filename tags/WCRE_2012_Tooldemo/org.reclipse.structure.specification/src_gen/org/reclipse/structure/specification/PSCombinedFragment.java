/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Combined Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSCombinedFragment#getChildren <em>Children</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSCombinedFragment#getKind <em>Kind</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSCombinedFragment#getPatternSpecification <em>Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSCombinedFragment#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSCombinedFragment()
 * @model
 * @generated
 */
public interface PSCombinedFragment extends PSCombinedFragmentItem
{
   /**
    * Returns the value of the '<em><b>Children</b></em>' reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSCombinedFragmentItem}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSCombinedFragmentItem#getParents <em>Parents</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Children</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Children</em>' reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSCombinedFragment_Children()
    * @see org.reclipse.structure.specification.PSCombinedFragmentItem#getParents
    * @model opposite="parents" keys="id" ordered="false"
    * @generated
    */
   EList<PSCombinedFragmentItem> getChildren();

   /**
    * Returns the value of the '<em><b>Kind</b></em>' attribute.
    * The default value is <code>"1"</code>.
    * The literals are from the enumeration {@link org.reclipse.structure.specification.ModifierType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Kind</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Kind</em>' attribute.
    * @see org.reclipse.structure.specification.ModifierType
    * @see #setKind(ModifierType)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSCombinedFragment_Kind()
    * @model default="1" unique="false" required="true" ordered="false"
    * @generated
    */
   ModifierType getKind();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSCombinedFragment#getKind <em>Kind</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Kind</em>' attribute.
    * @see org.reclipse.structure.specification.ModifierType
    * @see #getKind()
    * @generated
    */
   void setKind(ModifierType value);

   /**
    * Returns the value of the '<em><b>Pattern Specification</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSPatternSpecification#getCombinedFragments <em>Combined Fragments</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Pattern Specification</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pattern Specification</em>' container reference.
    * @see #setPatternSpecification(PSPatternSpecification)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSCombinedFragment_PatternSpecification()
    * @see org.reclipse.structure.specification.PSPatternSpecification#getCombinedFragments
    * @model opposite="combinedFragments" keys="id" required="true" transient="false" ordered="false"
    * @generated
    */
   PSPatternSpecification getPatternSpecification();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSCombinedFragment#getPatternSpecification <em>Pattern Specification</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Pattern Specification</em>' container reference.
    * @see #getPatternSpecification()
    * @generated
    */
   void setPatternSpecification(PSPatternSpecification value);

   /**
    * Returns the value of the '<em><b>Constraint</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Constraint</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Constraint</em>' containment reference.
    * @see #setConstraint(PSNodeConstraint)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSCombinedFragment_Constraint()
    * @model containment="true" keys="id" ordered="false"
    * @generated
    */
   PSNodeConstraint getConstraint();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSCombinedFragment#getConstraint <em>Constraint</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Constraint</em>' containment reference.
    * @see #getConstraint()
    * @generated
    */
   void setConstraint(PSNodeConstraint value);

} // PSCombinedFragment
