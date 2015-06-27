/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.tests;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.reclipse.tests.TestsFactory
 * @model kind="package"
 * @generated
 */
public interface TestsPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tests";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.reclipse.org/ns/tests/2012";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tests";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestsPackage eINSTANCE = org.reclipse.tests.impl.TestsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.reclipse.tests.Named <em>Named</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.reclipse.tests.Named
	 * @see org.reclipse.tests.impl.TestsPackageImpl#getNamed()
	 * @generated
	 */
	int NAMED = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.reclipse.tests.impl.TypeAImpl <em>Type A</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.reclipse.tests.impl.TypeAImpl
	 * @see org.reclipse.tests.impl.TestsPackageImpl#getTypeA()
	 * @generated
	 */
	int TYPE_A = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_A__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>Some As</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_A__SOME_AS = NAMED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type A</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_A_FEATURE_COUNT = NAMED_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link org.reclipse.tests.impl.TypeBImpl <em>Type B</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.reclipse.tests.impl.TypeBImpl
	 * @see org.reclipse.tests.impl.TestsPackageImpl#getTypeB()
	 * @generated
	 */
	int TYPE_B = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_B__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>Some As</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_B__SOME_AS = NAMED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type B</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_B_FEATURE_COUNT = NAMED_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.reclipse.tests.impl.RootImpl <em>Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.reclipse.tests.impl.RootImpl
	 * @see org.reclipse.tests.impl.TestsPackageImpl#getRoot()
	 * @generated
	 */
	int ROOT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>All As</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__ALL_AS = NAMED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>All Bs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__ALL_BS = NAMED_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_FEATURE_COUNT = NAMED_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link org.reclipse.tests.TypeA <em>Type A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type A</em>'.
	 * @see org.reclipse.tests.TypeA
	 * @generated
	 */
	EClass getTypeA();

	/**
	 * Returns the meta object for the reference list '{@link org.reclipse.tests.TypeA#getSomeAs <em>Some As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Some As</em>'.
	 * @see org.reclipse.tests.TypeA#getSomeAs()
	 * @see #getTypeA()
	 * @generated
	 */
	EReference getTypeA_SomeAs();

	/**
	 * Returns the meta object for class '{@link org.reclipse.tests.TypeB <em>Type B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type B</em>'.
	 * @see org.reclipse.tests.TypeB
	 * @generated
	 */
	EClass getTypeB();

	/**
	 * Returns the meta object for the reference list '{@link org.reclipse.tests.TypeB#getSomeAs <em>Some As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Some As</em>'.
	 * @see org.reclipse.tests.TypeB#getSomeAs()
	 * @see #getTypeB()
	 * @generated
	 */
	EReference getTypeB_SomeAs();

	/**
	 * Returns the meta object for class '{@link org.reclipse.tests.Named <em>Named</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named</em>'.
	 * @see org.reclipse.tests.Named
	 * @generated
	 */
	EClass getNamed();

	/**
	 * Returns the meta object for the attribute '{@link org.reclipse.tests.Named#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.reclipse.tests.Named#getName()
	 * @see #getNamed()
	 * @generated
	 */
	EAttribute getNamed_Name();

	/**
	 * Returns the meta object for class '{@link org.reclipse.tests.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root</em>'.
	 * @see org.reclipse.tests.Root
	 * @generated
	 */
	EClass getRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link org.reclipse.tests.Root#getAllAs <em>All As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>All As</em>'.
	 * @see org.reclipse.tests.Root#getAllAs()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_AllAs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.reclipse.tests.Root#getAllBs <em>All Bs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>All Bs</em>'.
	 * @see org.reclipse.tests.Root#getAllBs()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_AllBs();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestsFactory getTestsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link org.reclipse.tests.impl.TypeAImpl <em>Type A</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.reclipse.tests.impl.TypeAImpl
		 * @see org.reclipse.tests.impl.TestsPackageImpl#getTypeA()
		 * @generated
		 */
		EClass TYPE_A = eINSTANCE.getTypeA();
		/**
		 * The meta object literal for the '<em><b>Some As</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_A__SOME_AS = eINSTANCE.getTypeA_SomeAs();
		/**
		 * The meta object literal for the '{@link org.reclipse.tests.impl.TypeBImpl <em>Type B</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.reclipse.tests.impl.TypeBImpl
		 * @see org.reclipse.tests.impl.TestsPackageImpl#getTypeB()
		 * @generated
		 */
		EClass TYPE_B = eINSTANCE.getTypeB();
		/**
		 * The meta object literal for the '<em><b>Some As</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_B__SOME_AS = eINSTANCE.getTypeB_SomeAs();
		/**
		 * The meta object literal for the '{@link org.reclipse.tests.Named <em>Named</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.reclipse.tests.Named
		 * @see org.reclipse.tests.impl.TestsPackageImpl#getNamed()
		 * @generated
		 */
		EClass NAMED = eINSTANCE.getNamed();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED__NAME = eINSTANCE.getNamed_Name();
		/**
		 * The meta object literal for the '{@link org.reclipse.tests.impl.RootImpl <em>Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.reclipse.tests.impl.RootImpl
		 * @see org.reclipse.tests.impl.TestsPackageImpl#getRoot()
		 * @generated
		 */
		EClass ROOT = eINSTANCE.getRoot();
		/**
		 * The meta object literal for the '<em><b>All As</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__ALL_AS = eINSTANCE.getRoot_AllAs();
		/**
		 * The meta object literal for the '<em><b>All Bs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__ALL_BS = eINSTANCE.getRoot_AllBs();

	}

} //TestsPackage
