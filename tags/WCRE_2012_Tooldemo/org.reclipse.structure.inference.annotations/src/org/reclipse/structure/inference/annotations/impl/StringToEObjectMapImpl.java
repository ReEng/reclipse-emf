/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String To EObject Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.StringToEObjectMapImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.StringToEObjectMapImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringToEObjectMapImpl extends EObjectImpl implements BasicEMap.Entry<String,EList<EObject>>
{
   /**
    * The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTypedKey()
    * @generated
    * @ordered
    */
   protected static final String KEY_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTypedKey()
    * @generated
    * @ordered
    */
   protected String key = KEY_EDEFAULT;

   /**
    * The cached value of the '{@link #getTypedValue() <em>Value</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTypedValue()
    * @generated
    * @ordered
    */
   protected EList<EObject> value;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected StringToEObjectMapImpl()
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
      return AnnotationsPackage.Literals.STRING_TO_EOBJECT_MAP;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getTypedKey()
   {
      return key;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTypedKey(String newKey)
   {
      String oldKey = key;
      key = newKey;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.STRING_TO_EOBJECT_MAP__KEY, oldKey, key));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<EObject> getTypedValue()
   {
      if (value == null)
      {
         value = new EObjectResolvingEList<EObject>(EObject.class, this, AnnotationsPackage.STRING_TO_EOBJECT_MAP__VALUE);
      }
      return value;
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
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP__KEY:
            return getTypedKey();
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP__VALUE:
            return getTypedValue();
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
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP__KEY:
            setTypedKey((String)newValue);
            return;
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP__VALUE:
            getTypedValue().clear();
            getTypedValue().addAll((Collection<? extends EObject>)newValue);
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
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP__KEY:
            setTypedKey(KEY_EDEFAULT);
            return;
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP__VALUE:
            getTypedValue().clear();
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
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP__KEY:
            return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP__VALUE:
            return value != null && !value.isEmpty();
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
      result.append(" (key: ");
      result.append(key);
      result.append(')');
      return result.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected int hash = -1;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getHash()
   {
      if (hash == -1)
      {
         Object theKey = getKey();
         hash = (theKey == null ? 0 : theKey.hashCode());
      }
      return hash;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setHash(int hash)
   {
      this.hash = hash;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getKey()
   {
      return getTypedKey();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setKey(String key)
   {
      setTypedKey(key);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<EObject> getValue()
   {
      return getTypedValue();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<EObject> setValue(EList<EObject> value)
   {
      EList<EObject> oldValue = getValue();
      getTypedValue().clear();
      getTypedValue().addAll(value);
      return oldValue;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   public EMap<String, EList<EObject>> getEMap()
   {
      EObject container = eContainer();
      return container == null ? null : (EMap<String, EList<EObject>>)container.eGet(eContainmentFeature());
   }

} //StringToEObjectMapImpl
