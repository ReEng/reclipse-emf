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
 * A representation of the model object '<em><b>PS Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSCatalog#getPatternSpecifications <em>Pattern Specifications</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSCatalog#getMetamodel <em>Metamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSCatalog()
 * @model
 * @generated
 */
public interface PSCatalog extends Identifier
{
   /**
    * Returns the value of the '<em><b>Pattern Specifications</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSPatternSpecification}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSPatternSpecification#getCatalog <em>Catalog</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Pattern Specifications</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pattern Specifications</em>' containment reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSCatalog_PatternSpecifications()
    * @see org.reclipse.structure.specification.PSPatternSpecification#getCatalog
    * @model opposite="catalog" containment="true" keys="id"
    * @generated
    */
   EList<PSPatternSpecification> getPatternSpecifications();

   /**
    * Returns the value of the '<em><b>Metamodel</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Metamodel</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Metamodel</em>' attribute.
    * @see #setMetamodel(String)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSCatalog_Metamodel()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   String getMetamodel();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSCatalog#getMetamodel <em>Metamodel</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Metamodel</em>' attribute.
    * @see #getMetamodel()
    * @generated
    */
   void setMetamodel(String value);

} // PSCatalog
