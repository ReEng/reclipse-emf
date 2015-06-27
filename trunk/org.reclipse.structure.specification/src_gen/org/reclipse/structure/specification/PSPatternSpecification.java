/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.common.util.EList;
import org.fujaba.commons.identifier.Identifier;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Pattern Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#getCombinedFragments <em>Combined Fragments</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#getSuperPattern <em>Super Pattern</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#getSubPatterns <em>Sub Patterns</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#getType <em>Type</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSPatternSpecification#isAbstract <em>Abstract</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification()
 * @model
 * @generated
 */
public interface PSPatternSpecification extends Identifier
{
   /**
    * Returns the value of the '<em><b>Catalog</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSCatalog#getPatternSpecifications <em>Pattern Specifications</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Catalog</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Catalog</em>' container reference.
    * @see #setCatalog(PSCatalog)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_Catalog()
    * @see org.reclipse.structure.specification.PSCatalog#getPatternSpecifications
    * @model opposite="patternSpecifications" keys="id" required="true" transient="false" ordered="false"
    * @generated
    */
   PSCatalog getCatalog();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSPatternSpecification#getCatalog <em>Catalog</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Catalog</em>' container reference.
    * @see #getCatalog()
    * @generated
    */
   void setCatalog(PSCatalog value);

   /**
    * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSConnection}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSConnection#getPatternSpecification <em>Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Connections</em>' containment reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_Connections()
    * @see org.reclipse.structure.specification.PSConnection#getPatternSpecification
    * @model opposite="patternSpecification" containment="true" keys="id"
    * @generated
    */
   EList<PSConnection> getConnections();

   /**
    * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSNode}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSNode#getPatternSpecification <em>Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Nodes</em>' containment reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_Nodes()
    * @see org.reclipse.structure.specification.PSNode#getPatternSpecification
    * @model opposite="patternSpecification" containment="true" keys="id"
    * @generated
    */
   EList<PSNode> getNodes();

   /**
    * Returns the value of the '<em><b>Combined Fragments</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSCombinedFragment}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSCombinedFragment#getPatternSpecification <em>Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Combined Fragments</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Combined Fragments</em>' containment reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_CombinedFragments()
    * @see org.reclipse.structure.specification.PSCombinedFragment#getPatternSpecification
    * @model opposite="patternSpecification" containment="true" keys="id"
    * @generated
    */
   EList<PSCombinedFragment> getCombinedFragments();

   /**
    * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSSpecificationConstraint}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSSpecificationConstraint#getPatternSpecification <em>Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Constraints</em>' containment reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_Constraints()
    * @see org.reclipse.structure.specification.PSSpecificationConstraint#getPatternSpecification
    * @model opposite="patternSpecification" containment="true" keys="id"
    * @generated
    */
   EList<PSSpecificationConstraint> getConstraints();

   /**
    * Returns the value of the '<em><b>Super Pattern</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSPatternSpecification#getSubPatterns <em>Sub Patterns</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Super Pattern</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Super Pattern</em>' reference.
    * @see #setSuperPattern(PSPatternSpecification)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_SuperPattern()
    * @see org.reclipse.structure.specification.PSPatternSpecification#getSubPatterns
    * @model opposite="subPatterns" keys="id" ordered="false"
    * @generated
    */
   PSPatternSpecification getSuperPattern();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSPatternSpecification#getSuperPattern <em>Super Pattern</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Super Pattern</em>' reference.
    * @see #getSuperPattern()
    * @generated
    */
   void setSuperPattern(PSPatternSpecification value);

   /**
    * Returns the value of the '<em><b>Sub Patterns</b></em>' reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSPatternSpecification}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSPatternSpecification#getSuperPattern <em>Super Pattern</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Sub Patterns</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Sub Patterns</em>' reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_SubPatterns()
    * @see org.reclipse.structure.specification.PSPatternSpecification#getSuperPattern
    * @model opposite="superPattern" keys="id"
    * @generated
    */
   EList<PSPatternSpecification> getSubPatterns();

   /**
    * Returns the value of the '<em><b>Type</b></em>' attribute.
    * The default value is <code>""</code>.
    * The literals are from the enumeration {@link org.reclipse.structure.specification.PatternType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' attribute.
    * @see org.reclipse.structure.specification.PatternType
    * @see #setType(PatternType)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_Type()
    * @model default="" unique="false" required="true" ordered="false"
    * @generated
    */
   PatternType getType();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSPatternSpecification#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' attribute.
    * @see org.reclipse.structure.specification.PatternType
    * @see #getType()
    * @generated
    */
   void setType(PatternType value);

   /**
    * Returns the value of the '<em><b>Abstract</b></em>' attribute.
    * The default value is <code>"false"</code>.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Abstract</em>' attribute.
    * @see #setAbstract(boolean)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSPatternSpecification_Abstract()
    * @model default="false" unique="false" required="true" ordered="false"
    * @generated
    */
   boolean isAbstract();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSPatternSpecification#isAbstract <em>Abstract</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Abstract</em>' attribute.
    * @see #isAbstract()
    * @generated
    */
   void setAbstract(boolean value);

} // PSPatternSpecification
