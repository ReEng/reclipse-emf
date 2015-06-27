/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENamedElementImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BehavioralpatternPackage;

import org.reclipse.structure.specification.PSObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BP Argument</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPArgumentImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPArgumentImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.reclipse.behavior.specification.impl.BPArgumentImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPArgumentImpl extends IdentifierImpl implements BPArgument
{
   /**
    * The default value of the '{@link #getStatement() <em>Statement</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStatement()
    * @generated
    * @ordered
    */
   protected static final String STATEMENT_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getStatement() <em>Statement</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStatement()
    * @generated
    * @ordered
    */
   protected String statement = STATEMENT_EDEFAULT;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected PSObject type;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected BPArgumentImpl()
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
      return BehavioralpatternPackage.Literals.BP_ARGUMENT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPMessage getMessage()
   {
      if (eContainerFeatureID() != BehavioralpatternPackage.BP_ARGUMENT__MESSAGE) return null;
      return (BPMessage)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetMessage(BPMessage newMessage, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newMessage, BehavioralpatternPackage.BP_ARGUMENT__MESSAGE, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setMessage(BPMessage newMessage)
   {
      if (newMessage != eInternalContainer() || (eContainerFeatureID() != BehavioralpatternPackage.BP_ARGUMENT__MESSAGE && newMessage != null))
      {
         if (EcoreUtil.isAncestor(this, newMessage))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newMessage != null)
            msgs = ((InternalEObject)newMessage).eInverseAdd(this, BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS, BPMessage.class, msgs);
         msgs = basicSetMessage(newMessage, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_ARGUMENT__MESSAGE, newMessage, newMessage));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getStatement()
   {
      return statement;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setStatement(String newStatement)
   {
      String oldStatement = statement;
      statement = newStatement;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_ARGUMENT__STATEMENT, oldStatement, statement));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSObject getType()
   {
      if (type != null && type.eIsProxy())
      {
         InternalEObject oldType = (InternalEObject)type;
         type = (PSObject)eResolveProxy(oldType);
         if (type != oldType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralpatternPackage.BP_ARGUMENT__TYPE, oldType, type));
         }
      }
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSObject basicGetType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setType(PSObject newType)
   {
      PSObject oldType = type;
      type = newType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BehavioralpatternPackage.BP_ARGUMENT__TYPE, oldType, type));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case BehavioralpatternPackage.BP_ARGUMENT__MESSAGE:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetMessage((BPMessage)otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
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
         case BehavioralpatternPackage.BP_ARGUMENT__MESSAGE:
            return basicSetMessage(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
   {
      switch (eContainerFeatureID())
      {
         case BehavioralpatternPackage.BP_ARGUMENT__MESSAGE:
            return eInternalContainer().eInverseRemove(this, BehavioralpatternPackage.BP_MESSAGE__ARGUMENTS, BPMessage.class, msgs);
      }
      return super.eBasicRemoveFromContainerFeature(msgs);
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
         case BehavioralpatternPackage.BP_ARGUMENT__MESSAGE:
            return getMessage();
         case BehavioralpatternPackage.BP_ARGUMENT__STATEMENT:
            return getStatement();
         case BehavioralpatternPackage.BP_ARGUMENT__TYPE:
            if (resolve) return getType();
            return basicGetType();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case BehavioralpatternPackage.BP_ARGUMENT__MESSAGE:
            setMessage((BPMessage)newValue);
            return;
         case BehavioralpatternPackage.BP_ARGUMENT__STATEMENT:
            setStatement((String)newValue);
            return;
         case BehavioralpatternPackage.BP_ARGUMENT__TYPE:
            setType((PSObject)newValue);
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
         case BehavioralpatternPackage.BP_ARGUMENT__MESSAGE:
            setMessage((BPMessage)null);
            return;
         case BehavioralpatternPackage.BP_ARGUMENT__STATEMENT:
            setStatement(STATEMENT_EDEFAULT);
            return;
         case BehavioralpatternPackage.BP_ARGUMENT__TYPE:
            setType((PSObject)null);
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
         case BehavioralpatternPackage.BP_ARGUMENT__MESSAGE:
            return getMessage() != null;
         case BehavioralpatternPackage.BP_ARGUMENT__STATEMENT:
            return STATEMENT_EDEFAULT == null ? statement != null : !STATEMENT_EDEFAULT.equals(statement);
         case BehavioralpatternPackage.BP_ARGUMENT__TYPE:
            return type != null;
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
      result.append(" (statement: ");
      result.append(statement);
      result.append(')');
      return result.toString();
   }

} //BPArgumentImpl
