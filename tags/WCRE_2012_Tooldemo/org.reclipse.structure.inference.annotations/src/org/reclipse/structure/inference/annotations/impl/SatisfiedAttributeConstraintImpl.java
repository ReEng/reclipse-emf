/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint;
import org.reclipse.structure.specification.PSAttributeConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Satisfied Attribute Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SatisfiedAttributeConstraintImpl#getNodeID <em>Node ID</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SatisfiedAttributeConstraintImpl#getAttributeIndex <em>Attribute Index</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SatisfiedAttributeConstraintImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.SatisfiedAttributeConstraintImpl#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SatisfiedAttributeConstraintImpl extends SatisfiedConstraintImpl implements SatisfiedAttributeConstraint
{
   /**
    * The default value of the '{@link #getNodeID() <em>Node ID</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getNodeID()
    * @generated
    * @ordered
    */
   protected static final String NODE_ID_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getNodeID() <em>Node ID</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getNodeID()
    * @generated
    * @ordered
    */
   protected String nodeID = NODE_ID_EDEFAULT;

   /**
    * The default value of the '{@link #getAttributeIndex() <em>Attribute Index</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAttributeIndex()
    * @generated
    * @ordered
    */
   protected static final int ATTRIBUTE_INDEX_EDEFAULT = 0;

   /**
    * The cached value of the '{@link #getAttributeIndex() <em>Attribute Index</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAttributeIndex()
    * @generated
    * @ordered
    */
   protected int attributeIndex = ATTRIBUTE_INDEX_EDEFAULT;

   /**
    * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getContext()
    * @generated
    * @ordered
    */
   protected EObject context;

   /**
    * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConstraint()
    * @generated
    * @ordered
    */
   protected PSAttributeConstraint constraint;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected SatisfiedAttributeConstraintImpl()
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
      return AnnotationsPackage.Literals.SATISFIED_ATTRIBUTE_CONSTRAINT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getNodeID()
   {
      return nodeID;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setNodeID(String newNodeID)
   {
      String oldNodeID = nodeID;
      nodeID = newNodeID;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__NODE_ID, oldNodeID, nodeID));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getAttributeIndex()
   {
      return attributeIndex;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAttributeIndex(int newAttributeIndex)
   {
      int oldAttributeIndex = attributeIndex;
      attributeIndex = newAttributeIndex;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__ATTRIBUTE_INDEX, oldAttributeIndex, attributeIndex));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EObject getContext()
   {
      if (context != null && context.eIsProxy())
      {
         InternalEObject oldContext = (InternalEObject)context;
         context = eResolveProxy(oldContext);
         if (context != oldContext)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT, oldContext, context));
         }
      }
      return context;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EObject basicGetContext()
   {
      return context;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setContext(EObject newContext)
   {
      EObject oldContext = context;
      context = newContext;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT, oldContext, context));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSAttributeConstraint getConstraint()
   {
      if (constraint != null && constraint.eIsProxy())
      {
         InternalEObject oldConstraint = (InternalEObject)constraint;
         constraint = (PSAttributeConstraint)eResolveProxy(oldConstraint);
         if (constraint != oldConstraint)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT, oldConstraint, constraint));
         }
      }
      return constraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSAttributeConstraint basicGetConstraint()
   {
      return constraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setConstraint(PSAttributeConstraint newConstraint)
   {
      PSAttributeConstraint oldConstraint = constraint;
      constraint = newConstraint;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT, oldConstraint, constraint));
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
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__NODE_ID:
            return getNodeID();
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__ATTRIBUTE_INDEX:
            return getAttributeIndex();
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT:
            if (resolve) return getContext();
            return basicGetContext();
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT:
            if (resolve) return getConstraint();
            return basicGetConstraint();
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
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__NODE_ID:
            setNodeID((String)newValue);
            return;
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__ATTRIBUTE_INDEX:
            setAttributeIndex((Integer)newValue);
            return;
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT:
            setContext((EObject)newValue);
            return;
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT:
            setConstraint((PSAttributeConstraint)newValue);
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
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__NODE_ID:
            setNodeID(NODE_ID_EDEFAULT);
            return;
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__ATTRIBUTE_INDEX:
            setAttributeIndex(ATTRIBUTE_INDEX_EDEFAULT);
            return;
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT:
            setContext((EObject)null);
            return;
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT:
            setConstraint((PSAttributeConstraint)null);
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
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__NODE_ID:
            return NODE_ID_EDEFAULT == null ? nodeID != null : !NODE_ID_EDEFAULT.equals(nodeID);
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__ATTRIBUTE_INDEX:
            return attributeIndex != ATTRIBUTE_INDEX_EDEFAULT;
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT:
            return context != null;
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT:
            return constraint != null;
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
      result.append(" (nodeID: ");
      result.append(nodeID);
      result.append(", attributeIndex: ");
      result.append(attributeIndex);
      result.append(')');
      return result.toString();
   }

} //SatisfiedAttributeConstraintImpl
