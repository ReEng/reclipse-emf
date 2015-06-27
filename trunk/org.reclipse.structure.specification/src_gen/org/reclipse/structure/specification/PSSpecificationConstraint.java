/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Specification Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSSpecificationConstraint#isAdditional <em>Additional</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSSpecificationConstraint#getPatternSpecification <em>Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSSpecificationConstraint#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSSpecificationConstraint()
 * @model
 * @generated
 */
public interface PSSpecificationConstraint extends PSCombinedFragmentItem
{
   /**
    * Returns the value of the '<em><b>Additional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Additional</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Additional</em>' attribute.
    * @see #setAdditional(boolean)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSSpecificationConstraint_Additional()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   boolean isAdditional();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSSpecificationConstraint#isAdditional <em>Additional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Additional</em>' attribute.
    * @see #isAdditional()
    * @generated
    */
   void setAdditional(boolean value);

   /**
    * Returns the value of the '<em><b>Pattern Specification</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSPatternSpecification#getConstraints <em>Constraints</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Pattern Specification</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pattern Specification</em>' container reference.
    * @see #setPatternSpecification(PSPatternSpecification)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSSpecificationConstraint_PatternSpecification()
    * @see org.reclipse.structure.specification.PSPatternSpecification#getConstraints
    * @model opposite="constraints" keys="id" required="true" transient="false" ordered="false"
    * @generated
    */
   PSPatternSpecification getPatternSpecification();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSSpecificationConstraint#getPatternSpecification <em>Pattern Specification</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Pattern Specification</em>' container reference.
    * @see #getPatternSpecification()
    * @generated
    */
   void setPatternSpecification(PSPatternSpecification value);

   /**
    * Returns the value of the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Expression</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Expression</em>' attribute.
    * @see #setExpression(String)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSSpecificationConstraint_Expression()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   String getExpression();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSSpecificationConstraint#getExpression <em>Expression</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Expression</em>' attribute.
    * @see #getExpression()
    * @generated
    */
   void setExpression(String value);

} // PSSpecificationConstraint
