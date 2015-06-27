/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSConnection#getSource <em>Source</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSConnection#getTarget <em>Target</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSConnection#getPatternSpecification <em>Pattern Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSConnection()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface PSConnection extends PSItem
{
   /**
    * Returns the value of the '<em><b>Source</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSNode#getOutgoing <em>Outgoing</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Source</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Source</em>' reference.
    * @see #setSource(PSNode)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSConnection_Source()
    * @see org.reclipse.structure.specification.PSNode#getOutgoing
    * @model opposite="outgoing" keys="id" required="true" ordered="false"
    * @generated
    */
   PSNode getSource();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSConnection#getSource <em>Source</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Source</em>' reference.
    * @see #getSource()
    * @generated
    */
   void setSource(PSNode value);

   /**
    * Returns the value of the '<em><b>Target</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSNode#getIncoming <em>Incoming</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Target</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Target</em>' reference.
    * @see #setTarget(PSNode)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSConnection_Target()
    * @see org.reclipse.structure.specification.PSNode#getIncoming
    * @model opposite="incoming" keys="id" required="true" ordered="false"
    * @generated
    */
   PSNode getTarget();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSConnection#getTarget <em>Target</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Target</em>' reference.
    * @see #getTarget()
    * @generated
    */
   void setTarget(PSNode value);

   /**
    * Returns the value of the '<em><b>Pattern Specification</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSPatternSpecification#getConnections <em>Connections</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Pattern Specification</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pattern Specification</em>' container reference.
    * @see #setPatternSpecification(PSPatternSpecification)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSConnection_PatternSpecification()
    * @see org.reclipse.structure.specification.PSPatternSpecification#getConnections
    * @model opposite="connections" keys="id" required="true" transient="false" ordered="false"
    * @generated
    */
   PSPatternSpecification getPatternSpecification();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSConnection#getPatternSpecification <em>Pattern Specification</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Pattern Specification</em>' container reference.
    * @see #getPatternSpecification()
    * @generated
    */
   void setPatternSpecification(PSPatternSpecification value);

} // PSConnection
