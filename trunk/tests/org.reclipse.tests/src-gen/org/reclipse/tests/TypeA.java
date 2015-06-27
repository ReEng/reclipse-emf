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
 * A representation of the model object '<em><b>Type A</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.tests.TypeA#getSomeAs <em>Some As</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.tests.TestsPackage#getTypeA()
 * @model
 * @generated
 */
public interface TypeA extends Named
{

	/**
	 * Returns the value of the '<em><b>Some As</b></em>' reference list.
	 * The list contents are of type {@link org.reclipse.tests.TypeB}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Some As</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Some As</em>' reference list.
	 * @see org.reclipse.tests.TestsPackage#getTypeA_SomeAs()
	 * @model
	 * @generated
	 */
	EList<TypeB> getSomeAs();
} // TypeA
