/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Node Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSNodeConstraint#getNode <em>Node</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.PSNodeConstraint#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSNodeConstraint()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface PSNodeConstraint extends PSItem
{
   /**
    * Returns the value of the '<em><b>Node</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSNode#getNodeConstraints <em>Node Constraints</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Node</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Node</em>' container reference.
    * @see #setNode(PSNode)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSNodeConstraint_Node()
    * @see org.reclipse.structure.specification.PSNode#getNodeConstraints
    * @model opposite="nodeConstraints" keys="id" transient="false" ordered="false"
    * @generated
    */
   PSNode getNode();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSNodeConstraint#getNode <em>Node</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Node</em>' container reference.
    * @see #getNode()
    * @generated
    */
   void setNode(PSNode value);

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
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSNodeConstraint_Expression()
    * @model
    * @generated
    */
   String getExpression();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSNodeConstraint#getExpression <em>Expression</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Expression</em>' attribute.
    * @see #getExpression()
    * @generated
    */
   void setExpression(String value);

} // PSNodeConstraint
