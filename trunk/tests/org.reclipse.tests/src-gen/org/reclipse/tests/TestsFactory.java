/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.tests;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.reclipse.tests.TestsPackage
 * @generated
 */
public interface TestsFactory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestsFactory eINSTANCE = org.reclipse.tests.impl.TestsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Type A</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type A</em>'.
	 * @generated
	 */
	TypeA createTypeA();

	/**
	 * Returns a new object of class '<em>Type B</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type B</em>'.
	 * @generated
	 */
	TypeB createTypeB();

	/**
	 * Returns a new object of class '<em>Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Root</em>'.
	 * @generated
	 */
	Root createRoot();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TestsPackage getTestsPackage();

} //TestsFactory
