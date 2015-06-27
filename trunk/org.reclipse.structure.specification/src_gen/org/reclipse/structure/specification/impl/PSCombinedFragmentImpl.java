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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.fujaba.commons.identifier.impl.IdentifierImpl;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Combined Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSCombinedFragmentImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSCombinedFragmentImpl#getParents <em>Parents</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSCombinedFragmentImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSCombinedFragmentImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSCombinedFragmentImpl#getPatternSpecification <em>Pattern Specification</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSCombinedFragmentImpl#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSCombinedFragmentImpl extends IdentifierImpl implements PSCombinedFragment
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
    * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getChildren()
    * @generated
    * @ordered
    */
   protected EList<PSCombinedFragmentItem> children;

   /**
    * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getKind()
    * @generated
    * @ordered
    */
   protected static final ModifierType KIND_EDEFAULT = ModifierType.NONE;

   /**
    * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getKind()
    * @generated
    * @ordered
    */
   protected ModifierType kind = KIND_EDEFAULT;

   /**
    * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConstraint()
    * @generated
    * @ordered
    */
   protected PSNodeConstraint constraint;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSCombinedFragmentImpl()
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
      return SpecificationPackage.Literals.PS_COMBINED_FRAGMENT;
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
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_COMBINED_FRAGMENT__WEIGHT, oldWeight, weight));
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
         parents = new EObjectWithInverseResolvingEList.ManyInverse<PSCombinedFragment>(PSCombinedFragment.class, this, SpecificationPackage.PS_COMBINED_FRAGMENT__PARENTS, SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN);
      }
      return parents;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSCombinedFragmentItem> getChildren()
   {
      if (children == null)
      {
         children = new EObjectWithInverseResolvingEList.ManyInverse<PSCombinedFragmentItem>(PSCombinedFragmentItem.class, this, SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN, SpecificationPackage.PS_COMBINED_FRAGMENT_ITEM__PARENTS);
      }
      return children;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ModifierType getKind()
   {
      return kind;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setKind(ModifierType newKind)
   {
      ModifierType oldKind = kind;
      kind = newKind == null ? KIND_EDEFAULT : newKind;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_COMBINED_FRAGMENT__KIND, oldKind, kind));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification getPatternSpecification()
   {
      if (eContainerFeatureID() != SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION) return null;
      return (PSPatternSpecification)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetPatternSpecification(PSPatternSpecification newPatternSpecification, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newPatternSpecification, SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPatternSpecification(PSPatternSpecification newPatternSpecification)
   {
      if (newPatternSpecification != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION && newPatternSpecification != null))
      {
         if (EcoreUtil.isAncestor(this, newPatternSpecification))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newPatternSpecification != null)
            msgs = ((InternalEObject)newPatternSpecification).eInverseAdd(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS, PSPatternSpecification.class, msgs);
         msgs = basicSetPatternSpecification(newPatternSpecification, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION, newPatternSpecification, newPatternSpecification));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSNodeConstraint getConstraint()
   {
      return constraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetConstraint(PSNodeConstraint newConstraint, NotificationChain msgs)
   {
      PSNodeConstraint oldConstraint = constraint;
      constraint = newConstraint;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT, oldConstraint, newConstraint);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setConstraint(PSNodeConstraint newConstraint)
   {
      if (newConstraint != constraint)
      {
         NotificationChain msgs = null;
         if (constraint != null)
            msgs = ((InternalEObject)constraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT, null, msgs);
         if (newConstraint != null)
            msgs = ((InternalEObject)newConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT, null, msgs);
         msgs = basicSetConstraint(newConstraint, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT, newConstraint, newConstraint));
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
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PARENTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getParents()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION:
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
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PARENTS:
            return ((InternalEList<?>)getParents()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN:
            return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION:
            return basicSetPatternSpecification(null, msgs);
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT:
            return basicSetConstraint(null, msgs);
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
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION:
            return eInternalContainer().eInverseRemove(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS, PSPatternSpecification.class, msgs);
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
         case SpecificationPackage.PS_COMBINED_FRAGMENT__WEIGHT:
            return getWeight();
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PARENTS:
            return getParents();
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN:
            return getChildren();
         case SpecificationPackage.PS_COMBINED_FRAGMENT__KIND:
            return getKind();
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION:
            return getPatternSpecification();
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT:
            return getConstraint();
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
         case SpecificationPackage.PS_COMBINED_FRAGMENT__WEIGHT:
            setWeight((Double)newValue);
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PARENTS:
            getParents().clear();
            getParents().addAll((Collection<? extends PSCombinedFragment>)newValue);
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN:
            getChildren().clear();
            getChildren().addAll((Collection<? extends PSCombinedFragmentItem>)newValue);
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__KIND:
            setKind((ModifierType)newValue);
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION:
            setPatternSpecification((PSPatternSpecification)newValue);
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT:
            setConstraint((PSNodeConstraint)newValue);
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
         case SpecificationPackage.PS_COMBINED_FRAGMENT__WEIGHT:
            setWeight(WEIGHT_EDEFAULT);
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PARENTS:
            getParents().clear();
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN:
            getChildren().clear();
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__KIND:
            setKind(KIND_EDEFAULT);
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION:
            setPatternSpecification((PSPatternSpecification)null);
            return;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT:
            setConstraint((PSNodeConstraint)null);
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
         case SpecificationPackage.PS_COMBINED_FRAGMENT__WEIGHT:
            return weight != WEIGHT_EDEFAULT;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PARENTS:
            return parents != null && !parents.isEmpty();
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CHILDREN:
            return children != null && !children.isEmpty();
         case SpecificationPackage.PS_COMBINED_FRAGMENT__KIND:
            return kind != KIND_EDEFAULT;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION:
            return getPatternSpecification() != null;
         case SpecificationPackage.PS_COMBINED_FRAGMENT__CONSTRAINT:
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
      result.append(" (weight: ");
      result.append(weight);
      result.append(", kind: ");
      result.append(kind);
      result.append(')');
      return result.toString();
   }

} //PSCombinedFragmentImpl
