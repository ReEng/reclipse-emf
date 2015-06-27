/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification;

import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;

import org.reclipse.structure.specification.PSPatternSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavioral Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A BehavioralPattern is a SequenceDiagram. It specifies the behavior of a pattern at run-time. If the property "negative" is true, it specifies behavior that is not allowed. A BehavioralPattern has a reference on a PSPatternSpecification.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.BehavioralPattern#isNegative <em>Negative</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.BehavioralPattern#getPsPatternSpecification <em>Ps Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.BehavioralPattern#getCatalog <em>Catalog</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBehavioralPattern()
 * @model
 * @generated
 */
public interface BehavioralPattern extends SequenceDiagram
{
   /**
    * Returns the value of the '<em><b>Negative</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Negative</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Negative</em>' attribute.
    * @see #setNegative(boolean)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBehavioralPattern_Negative()
    * @model
    * @generated
    */
   boolean isNegative();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BehavioralPattern#isNegative <em>Negative</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Negative</em>' attribute.
    * @see #isNegative()
    * @generated
    */
   void setNegative(boolean value);

   /**
    * Returns the value of the '<em><b>Ps Pattern Specification</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Ps Pattern Specification</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Ps Pattern Specification</em>' reference.
    * @see #setPsPatternSpecification(PSPatternSpecification)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBehavioralPattern_PsPatternSpecification()
    * @model
    * @generated
    */
   PSPatternSpecification getPsPatternSpecification();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BehavioralPattern#getPsPatternSpecification <em>Ps Pattern Specification</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Ps Pattern Specification</em>' reference.
    * @see #getPsPatternSpecification()
    * @generated
    */
   void setPsPatternSpecification(PSPatternSpecification value);

   /**
    * Returns the value of the '<em><b>Catalog</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.behavior.specification.BPCatalog#getBehavioralPatterns <em>Behavioral Patterns</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Catalog</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Catalog</em>' container reference.
    * @see #setCatalog(BPCatalog)
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#getBehavioralPattern_Catalog()
    * @see org.reclipse.behavior.specification.BPCatalog#getBehavioralPatterns
    * @model opposite="behavioralPatterns" transient="false"
    * @generated
    */
   BPCatalog getCatalog();

   /**
    * Sets the value of the '{@link org.reclipse.behavior.specification.BehavioralPattern#getCatalog <em>Catalog</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Catalog</em>' container reference.
    * @see #getCatalog()
    * @generated
    */
   void setCatalog(BPCatalog value);

} // BehavioralPattern
