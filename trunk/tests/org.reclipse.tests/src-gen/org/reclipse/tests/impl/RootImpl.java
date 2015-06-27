/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.tests.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.reclipse.tests.Root;
import org.reclipse.tests.TestsPackage;
import org.reclipse.tests.TypeA;
import org.reclipse.tests.TypeB;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.tests.impl.RootImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.reclipse.tests.impl.RootImpl#getAllAs <em>All As</em>}</li>
 *   <li>{@link org.reclipse.tests.impl.RootImpl#getAllBs <em>All Bs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RootImpl extends EObjectImpl implements Root
{
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAllAs() <em>All As</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAs()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeA> allAs;

	/**
	 * The cached value of the '{@link #getAllBs() <em>All Bs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllBs()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeB> allBs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return TestsPackage.Literals.ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsPackage.ROOT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeA> getAllAs()
	{
		if (allAs == null)
		{
			allAs = new EObjectContainmentEList<TypeA>(TypeA.class, this, TestsPackage.ROOT__ALL_AS);
		}
		return allAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeB> getAllBs()
	{
		if (allBs == null)
		{
			allBs = new EObjectContainmentEList<TypeB>(TypeB.class, this, TestsPackage.ROOT__ALL_BS);
		}
		return allBs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case TestsPackage.ROOT__ALL_AS:
				return ((InternalEList<?>)getAllAs()).basicRemove(otherEnd, msgs);
			case TestsPackage.ROOT__ALL_BS:
				return ((InternalEList<?>)getAllBs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case TestsPackage.ROOT__NAME:
				return getName();
			case TestsPackage.ROOT__ALL_AS:
				return getAllAs();
			case TestsPackage.ROOT__ALL_BS:
				return getAllBs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case TestsPackage.ROOT__NAME:
				setName((String)newValue);
				return;
			case TestsPackage.ROOT__ALL_AS:
				getAllAs().clear();
				getAllAs().addAll((Collection<? extends TypeA>)newValue);
				return;
			case TestsPackage.ROOT__ALL_BS:
				getAllBs().clear();
				getAllBs().addAll((Collection<? extends TypeB>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case TestsPackage.ROOT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TestsPackage.ROOT__ALL_AS:
				getAllAs().clear();
				return;
			case TestsPackage.ROOT__ALL_BS:
				getAllBs().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case TestsPackage.ROOT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TestsPackage.ROOT__ALL_AS:
				return allAs != null && !allAs.isEmpty();
			case TestsPackage.ROOT__ALL_BS:
				return allBs != null && !allBs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //RootImpl
