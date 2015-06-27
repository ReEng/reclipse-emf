/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSLinkImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSLinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSLinkImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSLinkImpl#getPatternSpecification <em>Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSLinkImpl#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSLinkImpl#isNegative <em>Negative</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSLinkImpl#getInstanceOf <em>Instance Of</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSLinkImpl extends IdentifierImpl implements PSLink
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
    * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSource()
    * @generated
    * @ordered
    */
   protected PSNode source;

   /**
    * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTarget()
    * @generated
    * @ordered
    */
   protected PSNode target;

   /**
    * The default value of the '{@link #getQualifier() <em>Qualifier</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getQualifier()
    * @generated
    * @ordered
    */
   protected static final String QUALIFIER_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getQualifier()
    * @generated
    * @ordered
    */
   protected String qualifier = QUALIFIER_EDEFAULT;

   /**
    * The default value of the '{@link #isNegative() <em>Negative</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isNegative()
    * @generated
    * @ordered
    */
   protected static final boolean NEGATIVE_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isNegative() <em>Negative</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isNegative()
    * @generated
    * @ordered
    */
   protected boolean negative = NEGATIVE_EDEFAULT;

   /**
    * The cached value of the '{@link #getInstanceOf() <em>Instance Of</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInstanceOf()
    * @generated
    * @ordered
    */
   protected EReference instanceOf;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSLinkImpl()
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
      return SpecificationPackage.Literals.PS_LINK;
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
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__WEIGHT, oldWeight, weight));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSNode getSource()
   {
      if (source != null && source.eIsProxy())
      {
         InternalEObject oldSource = (InternalEObject)source;
         source = (PSNode)eResolveProxy(oldSource);
         if (source != oldSource)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecificationPackage.PS_LINK__SOURCE, oldSource, source));
         }
      }
      return source;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSNode basicGetSource()
   {
      return source;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetSource(PSNode newSource, NotificationChain msgs)
   {
      PSNode oldSource = source;
      source = newSource;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__SOURCE, oldSource, newSource);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSource(PSNode newSource)
   {
      if (newSource != source)
      {
         NotificationChain msgs = null;
         if (source != null)
            msgs = ((InternalEObject)source).eInverseRemove(this, SpecificationPackage.PS_NODE__OUTGOING, PSNode.class, msgs);
         if (newSource != null)
            msgs = ((InternalEObject)newSource).eInverseAdd(this, SpecificationPackage.PS_NODE__OUTGOING, PSNode.class, msgs);
         msgs = basicSetSource(newSource, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__SOURCE, newSource, newSource));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSNode getTarget()
   {
      if (target != null && target.eIsProxy())
      {
         InternalEObject oldTarget = (InternalEObject)target;
         target = (PSNode)eResolveProxy(oldTarget);
         if (target != oldTarget)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecificationPackage.PS_LINK__TARGET, oldTarget, target));
         }
      }
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSNode basicGetTarget()
   {
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetTarget(PSNode newTarget, NotificationChain msgs)
   {
      PSNode oldTarget = target;
      target = newTarget;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__TARGET, oldTarget, newTarget);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTarget(PSNode newTarget)
   {
      if (newTarget != target)
      {
         NotificationChain msgs = null;
         if (target != null)
            msgs = ((InternalEObject)target).eInverseRemove(this, SpecificationPackage.PS_NODE__INCOMING, PSNode.class, msgs);
         if (newTarget != null)
            msgs = ((InternalEObject)newTarget).eInverseAdd(this, SpecificationPackage.PS_NODE__INCOMING, PSNode.class, msgs);
         msgs = basicSetTarget(newTarget, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__TARGET, newTarget, newTarget));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification getPatternSpecification()
   {
      if (eContainerFeatureID() != SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION) return null;
      return (PSPatternSpecification)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetPatternSpecification(PSPatternSpecification newPatternSpecification, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newPatternSpecification, SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPatternSpecification(PSPatternSpecification newPatternSpecification)
   {
      if (newPatternSpecification != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION && newPatternSpecification != null))
      {
         if (EcoreUtil.isAncestor(this, newPatternSpecification))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newPatternSpecification != null)
            msgs = ((InternalEObject)newPatternSpecification).eInverseAdd(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS, PSPatternSpecification.class, msgs);
         msgs = basicSetPatternSpecification(newPatternSpecification, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION, newPatternSpecification, newPatternSpecification));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getQualifier()
   {
      return qualifier;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setQualifier(String newQualifier)
   {
      String oldQualifier = qualifier;
      qualifier = newQualifier;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__QUALIFIER, oldQualifier, qualifier));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isNegative()
   {
      return negative;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setNegative(boolean newNegative)
   {
      boolean oldNegative = negative;
      negative = newNegative;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__NEGATIVE, oldNegative, negative));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getInstanceOf()
   {
      if (instanceOf != null && instanceOf.eIsProxy())
      {
         InternalEObject oldInstanceOf = (InternalEObject)instanceOf;
         instanceOf = (EReference)eResolveProxy(oldInstanceOf);
         if (instanceOf != oldInstanceOf)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecificationPackage.PS_LINK__INSTANCE_OF, oldInstanceOf, instanceOf));
         }
      }
      return instanceOf;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference basicGetInstanceOf()
   {
      return instanceOf;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setInstanceOf(EReference newInstanceOf)
   {
      EReference oldInstanceOf = instanceOf;
      instanceOf = newInstanceOf;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_LINK__INSTANCE_OF, oldInstanceOf, instanceOf));
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
         case SpecificationPackage.PS_LINK__SOURCE:
            if (source != null)
               msgs = ((InternalEObject)source).eInverseRemove(this, SpecificationPackage.PS_NODE__OUTGOING, PSNode.class, msgs);
            return basicSetSource((PSNode)otherEnd, msgs);
         case SpecificationPackage.PS_LINK__TARGET:
            if (target != null)
               msgs = ((InternalEObject)target).eInverseRemove(this, SpecificationPackage.PS_NODE__INCOMING, PSNode.class, msgs);
            return basicSetTarget((PSNode)otherEnd, msgs);
         case SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetPatternSpecification((PSPatternSpecification)otherEnd, msgs);
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
         case SpecificationPackage.PS_LINK__SOURCE:
            return basicSetSource(null, msgs);
         case SpecificationPackage.PS_LINK__TARGET:
            return basicSetTarget(null, msgs);
         case SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION:
            return basicSetPatternSpecification(null, msgs);
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
         case SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION:
            return eInternalContainer().eInverseRemove(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS, PSPatternSpecification.class, msgs);
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
         case SpecificationPackage.PS_LINK__WEIGHT:
            return getWeight();
         case SpecificationPackage.PS_LINK__SOURCE:
            if (resolve) return getSource();
            return basicGetSource();
         case SpecificationPackage.PS_LINK__TARGET:
            if (resolve) return getTarget();
            return basicGetTarget();
         case SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION:
            return getPatternSpecification();
         case SpecificationPackage.PS_LINK__QUALIFIER:
            return getQualifier();
         case SpecificationPackage.PS_LINK__NEGATIVE:
            return isNegative();
         case SpecificationPackage.PS_LINK__INSTANCE_OF:
            if (resolve) return getInstanceOf();
            return basicGetInstanceOf();
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
         case SpecificationPackage.PS_LINK__WEIGHT:
            setWeight((Double)newValue);
            return;
         case SpecificationPackage.PS_LINK__SOURCE:
            setSource((PSNode)newValue);
            return;
         case SpecificationPackage.PS_LINK__TARGET:
            setTarget((PSNode)newValue);
            return;
         case SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION:
            setPatternSpecification((PSPatternSpecification)newValue);
            return;
         case SpecificationPackage.PS_LINK__QUALIFIER:
            setQualifier((String)newValue);
            return;
         case SpecificationPackage.PS_LINK__NEGATIVE:
            setNegative((Boolean)newValue);
            return;
         case SpecificationPackage.PS_LINK__INSTANCE_OF:
            setInstanceOf((EReference)newValue);
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
         case SpecificationPackage.PS_LINK__WEIGHT:
            setWeight(WEIGHT_EDEFAULT);
            return;
         case SpecificationPackage.PS_LINK__SOURCE:
            setSource((PSNode)null);
            return;
         case SpecificationPackage.PS_LINK__TARGET:
            setTarget((PSNode)null);
            return;
         case SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION:
            setPatternSpecification((PSPatternSpecification)null);
            return;
         case SpecificationPackage.PS_LINK__QUALIFIER:
            setQualifier(QUALIFIER_EDEFAULT);
            return;
         case SpecificationPackage.PS_LINK__NEGATIVE:
            setNegative(NEGATIVE_EDEFAULT);
            return;
         case SpecificationPackage.PS_LINK__INSTANCE_OF:
            setInstanceOf((EReference)null);
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
         case SpecificationPackage.PS_LINK__WEIGHT:
            return weight != WEIGHT_EDEFAULT;
         case SpecificationPackage.PS_LINK__SOURCE:
            return source != null;
         case SpecificationPackage.PS_LINK__TARGET:
            return target != null;
         case SpecificationPackage.PS_LINK__PATTERN_SPECIFICATION:
            return getPatternSpecification() != null;
         case SpecificationPackage.PS_LINK__QUALIFIER:
            return QUALIFIER_EDEFAULT == null ? qualifier != null : !QUALIFIER_EDEFAULT.equals(qualifier);
         case SpecificationPackage.PS_LINK__NEGATIVE:
            return negative != NEGATIVE_EDEFAULT;
         case SpecificationPackage.PS_LINK__INSTANCE_OF:
            return instanceOf != null;
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
      result.append(", qualifier: ");
      result.append(qualifier);
      result.append(", negative: ");
      result.append(negative);
      result.append(')');
      return result.toString();
   }

} //PSLinkImpl
