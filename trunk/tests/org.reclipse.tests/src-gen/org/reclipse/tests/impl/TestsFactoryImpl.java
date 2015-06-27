/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.tests.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.reclipse.tests.Root;
import org.reclipse.tests.TestsFactory;
import org.reclipse.tests.TestsPackage;
import org.reclipse.tests.TypeA;
import org.reclipse.tests.TypeB;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestsFactoryImpl extends EFactoryImpl implements TestsFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TestsFactory init()
	{
		try
		{
			TestsFactory theTestsFactory = (TestsFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.reclipse.org/ns/tests/2012"); 
			if (theTestsFactory != null)
			{
				return theTestsFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TestsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID())
		{
			case TestsPackage.TYPE_A: return createTypeA();
			case TestsPackage.TYPE_B: return createTypeB();
			case TestsPackage.ROOT: return createRoot();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeA createTypeA()
	{
		TypeAImpl typeA = new TypeAImpl();
		return typeA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeB createTypeB()
	{
		TypeBImpl typeB = new TypeBImpl();
		return typeB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root createRoot()
	{
		RootImpl root = new RootImpl();
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsPackage getTestsPackage()
	{
		return (TestsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TestsPackage getPackage()
	{
		return TestsPackage.eINSTANCE;
	}

} //TestsFactoryImpl
