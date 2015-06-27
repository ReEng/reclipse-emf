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
 * A representation of the model object '<em><b>PS Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSNode#isTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSNode#getModifier <em>Modifier</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSNode#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSNode#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSNode#getPatternSpecification <em>Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSNode#getNodeConstraints <em>Node Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSNode()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface PSNode extends PSCombinedFragmentItem
{
   /**
    * Returns the value of the '<em><b>Trigger</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Trigger</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Trigger</em>' attribute.
    * @see #setTrigger(boolean)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSNode_Trigger()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   boolean isTrigger();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSNode#isTrigger <em>Trigger</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Trigger</em>' attribute.
    * @see #isTrigger()
    * @generated
    */
   void setTrigger(boolean value);

   /**
    * Returns the value of the '<em><b>Modifier</b></em>' attribute.
    * The literals are from the enumeration {@link org.reclipse.structure.specification.ModifierType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Modifier</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Modifier</em>' attribute.
    * @see org.reclipse.structure.specification.ModifierType
    * @see #setModifier(ModifierType)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSNode_Modifier()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   ModifierType getModifier();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSNode#getModifier <em>Modifier</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Modifier</em>' attribute.
    * @see org.reclipse.structure.specification.ModifierType
    * @see #getModifier()
    * @generated
    */
   void setModifier(ModifierType value);

   /**
    * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSConnection}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSConnection#getSource <em>Source</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Outgoing</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Outgoing</em>' reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSNode_Outgoing()
    * @see org.reclipse.structure.specification.PSConnection#getSource
    * @model opposite="source" keys="id"
    * @generated
    */
   EList<PSConnection> getOutgoing();

   /**
    * Returns the value of the '<em><b>Incoming</b></em>' reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSConnection}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSConnection#getTarget <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Incoming</em>' reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSNode_Incoming()
    * @see org.reclipse.structure.specification.PSConnection#getTarget
    * @model opposite="target" keys="id"
    * @generated
    */
   EList<PSConnection> getIncoming();

   /**
    * Returns the value of the '<em><b>Pattern Specification</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSPatternSpecification#getNodes <em>Nodes</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Pattern Specification</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pattern Specification</em>' container reference.
    * @see #setPatternSpecification(PSPatternSpecification)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSNode_PatternSpecification()
    * @see org.reclipse.structure.specification.PSPatternSpecification#getNodes
    * @model opposite="nodes" keys="id" required="true" transient="false" ordered="false"
    * @generated
    */
   PSPatternSpecification getPatternSpecification();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSNode#getPatternSpecification <em>Pattern Specification</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Pattern Specification</em>' container reference.
    * @see #getPatternSpecification()
    * @generated
    */
   void setPatternSpecification(PSPatternSpecification value);

   /**
    * Returns the value of the '<em><b>Node Constraints</b></em>' containment reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSNodeConstraint}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSNodeConstraint#getNode <em>Node</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Node Constraints</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Node Constraints</em>' containment reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSNode_NodeConstraints()
    * @see org.reclipse.structure.specification.PSNodeConstraint#getNode
    * @model opposite="node" containment="true" keys="id"
    * @generated
    */
   EList<PSNodeConstraint> getNodeConstraints();

} // PSNode
