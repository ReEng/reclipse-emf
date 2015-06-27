/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#getParents <em>Parents</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#isTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#getModifier <em>Modifier</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#getPatternSpecification <em>Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#getNodeConstraints <em>Node Constraints</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSAnnotationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSAnnotationImpl extends IdentifierImpl implements PSAnnotation
{
   /**
    * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getWeight()
    * @generated
    * @ordered
    */
   protected static final double WEIGHT_EDEFAULT = 1.0;

   /**
    * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getWeight()
    * @generated
    * @ordered
    */
   protected double weight = WEIGHT_EDEFAULT;

   /**
    * The cached value of the '{@link #getParents() <em>Parents</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParents()
    * @generated
    * @ordered
    */
   protected EList<PSCombinedFragment> parents;

   /**
    * The default value of the '{@link #isTrigger() <em>Trigger</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isTrigger()
    * @generated
    * @ordered
    */
   protected static final boolean TRIGGER_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isTrigger() <em>Trigger</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isTrigger()
    * @generated
    * @ordered
    */
   protected boolean trigger = TRIGGER_EDEFAULT;

   /**
    * The default value of the '{@link #getModifier() <em>Modifier</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getModifier()
    * @generated
    * @ordered
    */
   protected static final ModifierType MODIFIER_EDEFAULT = ModifierType.NONE;

   /**
    * The cached value of the '{@link #getModifier() <em>Modifier</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getModifier()
    * @generated
    * @ordered
    */
   protected ModifierType modifier = MODIFIER_EDEFAULT;

   /**
    * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOutgoing()
    * @generated
    * @ordered
    */
   protected EList<PSConnection> outgoing;

   /**
    * The cached value of the '{@link #getIncoming() <em>Incoming</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getIncoming()
    * @generated
    * @ordered
    */
   protected EList<PSConnection> incoming;

   /**
    * The cached value of the '{@link #getNodeConstraints() <em>Node Constraints</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getNodeConstraints()
    * @generated
    * @ordered
    */
   protected EList<PSNodeConstraint> nodeConstraints;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected PSPatternSpecification type;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSAnnotationImpl()
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
      return SpecificationPackage.Literals.PS_ANNOTATION;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public double getWeight()
   {
      return weight;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setWeight(double newWeight)
   {
      double oldWeight = weight;
      weight = newWeight;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ANNOTATION__WEIGHT, oldWeight, weight));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSCombinedFragment> getParents()
   {
      if (parents == null)
      {
         parents = new EObjectWithInverseResolvingEList.ManyInverse<PSCombinedFragment>(PSCombinedFragment.class, this, SpecificationPackage.PS_ANNOTATION__PARENTS, SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN);
      }
      return parents;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isTrigger()
   {
      return trigger;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTrigger(boolean newTrigger)
   {
      boolean oldTrigger = trigger;
      trigger = newTrigger;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ANNOTATION__TRIGGER, oldTrigger, trigger));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ModifierType getModifier()
   {
      return modifier;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setModifier(ModifierType newModifier)
   {
      ModifierType oldModifier = modifier;
      modifier = newModifier == null ? MODIFIER_EDEFAULT : newModifier;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ANNOTATION__MODIFIER, oldModifier, modifier));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSConnection> getOutgoing()
   {
      if (outgoing == null)
      {
         outgoing = new EObjectWithInverseResolvingEList<PSConnection>(PSConnection.class, this, SpecificationPackage.PS_ANNOTATION__OUTGOING, SpecificationPackage.PS_CONNECTION__SOURCE);
      }
      return outgoing;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSConnection> getIncoming()
   {
      if (incoming == null)
      {
         incoming = new EObjectWithInverseResolvingEList<PSConnection>(PSConnection.class, this, SpecificationPackage.PS_ANNOTATION__INCOMING, SpecificationPackage.PS_CONNECTION__TARGET);
      }
      return incoming;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification getPatternSpecification()
   {
      if (eContainerFeatureID() != SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION) return null;
      return (PSPatternSpecification)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetPatternSpecification(PSPatternSpecification newPatternSpecification, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newPatternSpecification, SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPatternSpecification(PSPatternSpecification newPatternSpecification)
   {
      if (newPatternSpecification != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION && newPatternSpecification != null))
      {
         if (EcoreUtil.isAncestor(this, newPatternSpecification))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newPatternSpecification != null)
            msgs = ((InternalEObject)newPatternSpecification).eInverseAdd(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES, PSPatternSpecification.class, msgs);
         msgs = basicSetPatternSpecification(newPatternSpecification, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION, newPatternSpecification, newPatternSpecification));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSNodeConstraint> getNodeConstraints()
   {
      if (nodeConstraints == null)
      {
         nodeConstraints = new EObjectContainmentWithInverseEList<PSNodeConstraint>(PSNodeConstraint.class, this, SpecificationPackage.PS_ANNOTATION__NODE_CONSTRAINTS, SpecificationPackage.PS_NODE_CONSTRAINT__NODE);
      }
      return nodeConstraints;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification getType()
   {
      if (type != null && type.eIsProxy())
      {
         InternalEObject oldType = (InternalEObject)type;
         type = (PSPatternSpecification)eResolveProxy(oldType);
         if (type != oldType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecificationPackage.PS_ANNOTATION__TYPE, oldType, type));
         }
      }
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification basicGetType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setType(PSPatternSpecification newType)
   {
      PSPatternSpecification oldType = type;
      type = newType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_ANNOTATION__TYPE, oldType, type));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case SpecificationPackage.PS_ANNOTATION__PARENTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getParents()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_ANNOTATION__OUTGOING:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoing()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_ANNOTATION__INCOMING:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncoming()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetPatternSpecification((PSPatternSpecification)otherEnd, msgs);
         case SpecificationPackage.PS_ANNOTATION__NODE_CONSTRAINTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getNodeConstraints()).basicAdd(otherEnd, msgs);
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
         case SpecificationPackage.PS_ANNOTATION__PARENTS:
            return ((InternalEList<?>)getParents()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_ANNOTATION__OUTGOING:
            return ((InternalEList<?>)getOutgoing()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_ANNOTATION__INCOMING:
            return ((InternalEList<?>)getIncoming()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION:
            return basicSetPatternSpecification(null, msgs);
         case SpecificationPackage.PS_ANNOTATION__NODE_CONSTRAINTS:
            return ((InternalEList<?>)getNodeConstraints()).basicRemove(otherEnd, msgs);
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
         case SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION:
            return eInternalContainer().eInverseRemove(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES, PSPatternSpecification.class, msgs);
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
         case SpecificationPackage.PS_ANNOTATION__WEIGHT:
            return getWeight();
         case SpecificationPackage.PS_ANNOTATION__PARENTS:
            return getParents();
         case SpecificationPackage.PS_ANNOTATION__TRIGGER:
            return isTrigger();
         case SpecificationPackage.PS_ANNOTATION__MODIFIER:
            return getModifier();
         case SpecificationPackage.PS_ANNOTATION__OUTGOING:
            return getOutgoing();
         case SpecificationPackage.PS_ANNOTATION__INCOMING:
            return getIncoming();
         case SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION:
            return getPatternSpecification();
         case SpecificationPackage.PS_ANNOTATION__NODE_CONSTRAINTS:
            return getNodeConstraints();
         case SpecificationPackage.PS_ANNOTATION__TYPE:
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
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case SpecificationPackage.PS_ANNOTATION__WEIGHT:
            setWeight((Double)newValue);
            return;
         case SpecificationPackage.PS_ANNOTATION__PARENTS:
            getParents().clear();
            getParents().addAll((Collection<? extends PSCombinedFragment>)newValue);
            return;
         case SpecificationPackage.PS_ANNOTATION__TRIGGER:
            setTrigger((Boolean)newValue);
            return;
         case SpecificationPackage.PS_ANNOTATION__MODIFIER:
            setModifier((ModifierType)newValue);
            return;
         case SpecificationPackage.PS_ANNOTATION__OUTGOING:
            getOutgoing().clear();
            getOutgoing().addAll((Collection<? extends PSConnection>)newValue);
            return;
         case SpecificationPackage.PS_ANNOTATION__INCOMING:
            getIncoming().clear();
            getIncoming().addAll((Collection<? extends PSConnection>)newValue);
            return;
         case SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION:
            setPatternSpecification((PSPatternSpecification)newValue);
            return;
         case SpecificationPackage.PS_ANNOTATION__NODE_CONSTRAINTS:
            getNodeConstraints().clear();
            getNodeConstraints().addAll((Collection<? extends PSNodeConstraint>)newValue);
            return;
         case SpecificationPackage.PS_ANNOTATION__TYPE:
            setType((PSPatternSpecification)newValue);
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
         case SpecificationPackage.PS_ANNOTATION__WEIGHT:
            setWeight(WEIGHT_EDEFAULT);
            return;
         case SpecificationPackage.PS_ANNOTATION__PARENTS:
            getParents().clear();
            return;
         case SpecificationPackage.PS_ANNOTATION__TRIGGER:
            setTrigger(TRIGGER_EDEFAULT);
            return;
         case SpecificationPackage.PS_ANNOTATION__MODIFIER:
            setModifier(MODIFIER_EDEFAULT);
            return;
         case SpecificationPackage.PS_ANNOTATION__OUTGOING:
            getOutgoing().clear();
            return;
         case SpecificationPackage.PS_ANNOTATION__INCOMING:
            getIncoming().clear();
            return;
         case SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION:
            setPatternSpecification((PSPatternSpecification)null);
            return;
         case SpecificationPackage.PS_ANNOTATION__NODE_CONSTRAINTS:
            getNodeConstraints().clear();
            return;
         case SpecificationPackage.PS_ANNOTATION__TYPE:
            setType((PSPatternSpecification)null);
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
         case SpecificationPackage.PS_ANNOTATION__WEIGHT:
            return weight != WEIGHT_EDEFAULT;
         case SpecificationPackage.PS_ANNOTATION__PARENTS:
            return parents != null && !parents.isEmpty();
         case SpecificationPackage.PS_ANNOTATION__TRIGGER:
            return trigger != TRIGGER_EDEFAULT;
         case SpecificationPackage.PS_ANNOTATION__MODIFIER:
            return modifier != MODIFIER_EDEFAULT;
         case SpecificationPackage.PS_ANNOTATION__OUTGOING:
            return outgoing != null && !outgoing.isEmpty();
         case SpecificationPackage.PS_ANNOTATION__INCOMING:
            return incoming != null && !incoming.isEmpty();
         case SpecificationPackage.PS_ANNOTATION__PATTERN_SPECIFICATION:
            return getPatternSpecification() != null;
         case SpecificationPackage.PS_ANNOTATION__NODE_CONSTRAINTS:
            return nodeConstraints != null && !nodeConstraints.isEmpty();
         case SpecificationPackage.PS_ANNOTATION__TYPE:
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
      result.append(" (weight: ");
      result.append(weight);
      result.append(", trigger: ");
      result.append(trigger);
      result.append(", modifier: ");
      result.append(modifier);
      result.append(')');
      return result.toString();
   }

} //PSAnnotationImpl
