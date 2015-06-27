/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.tests;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.tests.Root#getAllAs <em>All As</em>}</li>
 *   <li>{@link org.reclipse.tests.Root#getAllBs <em>All Bs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.tests.TestsPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends Named
{
	/**
	 * Returns the value of the '<em><b>All As</b></em>' containment reference list.
	 * The list contents are of type {@link org.reclipse.tests.TypeA}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All As</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All As</em>' containment reference list.
	 * @see org.reclipse.tests.TestsPackage#getRoot_AllAs()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeA> getAllAs();

	/**
	 * Returns the value of the '<em><b>All Bs</b></em>' containment reference list.
	 * The list contents are of type {@link org.reclipse.tests.TypeB}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Bs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Bs</em>' containment reference list.
	 * @see org.reclipse.tests.TestsPackage#getRoot_AllBs()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeB> getAllBs();

} // Root
