/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.fujaba.commons.identifier.Identifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSItem#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSItem()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface PSItem extends Identifier
{
   /**
    * Returns the value of the '<em><b>Weight</b></em>' attribute.
    * The default value is <code>"1"</code>.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Weight</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Weight</em>' attribute.
    * @see #setWeight(double)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSItem_Weight()
    * @model default="1" unique="false" required="true" ordered="false"
    * @generated
    */
   double getWeight();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSItem#getWeight <em>Weight</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Weight</em>' attribute.
    * @see #getWeight()
    * @generated
    */
   void setWeight(double value);

} // PSItem
