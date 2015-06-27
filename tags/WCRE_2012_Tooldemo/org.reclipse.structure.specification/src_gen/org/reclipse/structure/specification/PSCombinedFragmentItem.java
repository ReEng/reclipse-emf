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
 * A representation of the model object '<em><b>PS Combined Fragment Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSCombinedFragmentItem#getParents <em>Parents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSCombinedFragmentItem()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface PSCombinedFragmentItem extends PSItem
{
   /**
    * Returns the value of the '<em><b>Parents</b></em>' reference list.
    * The list contents are of type {@link org.reclipse.structure.specification.PSCombinedFragment}.
    * It is bidirectional and its opposite is '{@link org.reclipse.structure.specification.PSCombinedFragment#getChildren <em>Children</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parents</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Parents</em>' reference list.
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSCombinedFragmentItem_Parents()
    * @see org.reclipse.structure.specification.PSCombinedFragment#getChildren
    * @model opposite="children" keys="id" ordered="false"
    * @generated
    */
   EList<PSCombinedFragment> getParents();

} // PSCombinedFragmentItem
