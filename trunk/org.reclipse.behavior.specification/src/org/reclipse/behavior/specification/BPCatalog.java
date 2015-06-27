/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification;

import org.eclipse.emf.common.util.EList;

import org.fujaba.commons.identifier.Identifier;
import org.eclipse.emf.ecore.ENamedElement;

import org.reclipse.structure.specification.PSCatalog;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BP Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.BPCatalog#getBehavioralPatterns <em>Behavioral Patterns</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.BPCatalog#getPsCatalog <em>Ps Catalog</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPCatalog()
 * @model
 * @generated
 */
public interface BPCatalog extends Identifier
{
   /**
    * Returns the value of the '<em><b>Behavioral Patterns</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.behavior.specification.BehavioralPattern}.
    * It is bidirectional and its opposite is '{@link org.reclipse.behavior.specification.BehavioralPattern#getCatalog <em>Catalog</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Behavioral Patterns</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Behavioral Patterns</em>' containment reference list.
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPCatalog_BehavioralPatterns()
    * @see org.reclipse.behavior.specification.BehavioralPattern#getCatalog
    * @model opposite="catalog" containment="true"
    * @generated
    */
   EList<BehavioralPattern> getBehavioralPatterns();

   /**
    * Returns the value of the '<em><b>Ps Catalog</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Ps Catalog</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Ps Catalog</em>' reference.
    * @see #setPsCatalog(PSCatalog)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBPCatalog_PsCatalog()
    * @model required="true"
    * @generated
    */
   PSCatalog getPsCatalog();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BPCatalog#getPsCatalog <em>Ps Catalog</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Ps Catalog</em>' reference.
    * @see #getPsCatalog()
    * @generated
    */
   void setPsCatalog(PSCatalog value);

} // BPCatalog
