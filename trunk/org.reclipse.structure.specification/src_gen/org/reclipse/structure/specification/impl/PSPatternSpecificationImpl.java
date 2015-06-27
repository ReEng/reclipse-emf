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
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.PatternType;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PS Pattern Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#getCombinedFragments <em>Combined Fragments</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#getSuperPattern <em>Super Pattern</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#getSubPatterns <em>Sub Patterns</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl#isAbstract <em>Abstract</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PSPatternSpecificationImpl extends IdentifierImpl implements PSPatternSpecification
{
   /**
    * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConnections()
    * @generated
    * @ordered
    */
   protected EList<PSConnection> connections;

   /**
    * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getNodes()
    * @generated
    * @ordered
    */
   protected EList<PSNode> nodes;

   /**
    * The cached value of the '{@link #getCombinedFragments() <em>Combined Fragments</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getCombinedFragments()
    * @generated
    * @ordered
    */
   protected EList<PSCombinedFragment> combinedFragments;

   /**
    * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConstraints()
    * @generated
    * @ordered
    */
   protected EList<PSSpecificationConstraint> constraints;

   /**
    * The cached value of the '{@link #getSuperPattern() <em>Super Pattern</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSuperPattern()
    * @generated
    * @ordered
    */
   protected PSPatternSpecification superPattern;

   /**
    * The cached value of the '{@link #getSubPatterns() <em>Sub Patterns</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSubPatterns()
    * @generated
    * @ordered
    */
   protected EList<PSPatternSpecification> subPatterns;

   /**
    * The default value of the '{@link #getType() <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected static final PatternType TYPE_EDEFAULT = PatternType.DESIGN_PATTERN;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
   protected PatternType type = TYPE_EDEFAULT;

   /**
    * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isAbstract()
    * @generated
    * @ordered
    */
   protected static final boolean ABSTRACT_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isAbstract()
    * @generated
    * @ordered
    */
   protected boolean abstract_ = ABSTRACT_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PSPatternSpecificationImpl()
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
      return SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSCatalog getCatalog()
   {
      if (eContainerFeatureID() != SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG) return null;
      return (PSCatalog)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetCatalog(PSCatalog newCatalog, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newCatalog, SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setCatalog(PSCatalog newCatalog)
   {
      if (newCatalog != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG && newCatalog != null))
      {
         if (EcoreUtil.isAncestor(this, newCatalog))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newCatalog != null)
            msgs = ((InternalEObject)newCatalog).eInverseAdd(this, SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS, PSCatalog.class, msgs);
         msgs = basicSetCatalog(newCatalog, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG, newCatalog, newCatalog));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSConnection> getConnections()
   {
      if (connections == null)
      {
         connections = new EObjectContainmentWithInverseEList<PSConnection>(PSConnection.class, this, SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS, SpecificationPackage.PS_CONNECTION__PATTERN_SPECIFICATION);
      }
      return connections;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSNode> getNodes()
   {
      if (nodes == null)
      {
         nodes = new EObjectContainmentWithInverseEList<PSNode>(PSNode.class, this, SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES, SpecificationPackage.PS_NODE__PATTERN_SPECIFICATION);
      }
      return nodes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSCombinedFragment> getCombinedFragments()
   {
      if (combinedFragments == null)
      {
         combinedFragments = new EObjectContainmentWithInverseEList<PSCombinedFragment>(PSCombinedFragment.class, this, SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS, SpecificationPackage.PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION);
      }
      return combinedFragments;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSSpecificationConstraint> getConstraints()
   {
      if (constraints == null)
      {
         constraints = new EObjectContainmentWithInverseEList<PSSpecificationConstraint>(PSSpecificationConstraint.class, this, SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS, SpecificationPackage.PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION);
      }
      return constraints;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification getSuperPattern()
   {
      if (superPattern != null && superPattern.eIsProxy())
      {
         InternalEObject oldSuperPattern = (InternalEObject)superPattern;
         superPattern = (PSPatternSpecification)eResolveProxy(oldSuperPattern);
         if (superPattern != oldSuperPattern)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN, oldSuperPattern, superPattern));
         }
      }
      return superPattern;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification basicGetSuperPattern()
   {
      return superPattern;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetSuperPattern(PSPatternSpecification newSuperPattern, NotificationChain msgs)
   {
      PSPatternSpecification oldSuperPattern = superPattern;
      superPattern = newSuperPattern;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN, oldSuperPattern, newSuperPattern);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSuperPattern(PSPatternSpecification newSuperPattern)
   {
      if (newSuperPattern != superPattern)
      {
         NotificationChain msgs = null;
         if (superPattern != null)
            msgs = ((InternalEObject)superPattern).eInverseRemove(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS, PSPatternSpecification.class, msgs);
         if (newSuperPattern != null)
            msgs = ((InternalEObject)newSuperPattern).eInverseAdd(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS, PSPatternSpecification.class, msgs);
         msgs = basicSetSuperPattern(newSuperPattern, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN, newSuperPattern, newSuperPattern));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PSPatternSpecification> getSubPatterns()
   {
      if (subPatterns == null)
      {
         subPatterns = new EObjectWithInverseResolvingEList<PSPatternSpecification>(PSPatternSpecification.class, this, SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS, SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN);
      }
      return subPatterns;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PatternType getType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setType(PatternType newType)
   {
      PatternType oldType = type;
      type = newType == null ? TYPE_EDEFAULT : newType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_PATTERN_SPECIFICATION__TYPE, oldType, type));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isAbstract()
   {
      return abstract_;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAbstract(boolean newAbstract)
   {
      boolean oldAbstract = abstract_;
      abstract_ = newAbstract;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.PS_PATTERN_SPECIFICATION__ABSTRACT, oldAbstract, abstract_));
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
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetCatalog((PSCatalog)otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnections()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getNodes()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getCombinedFragments()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getConstraints()).basicAdd(otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN:
            if (superPattern != null)
               msgs = ((InternalEObject)superPattern).eInverseRemove(this, SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS, PSPatternSpecification.class, msgs);
            return basicSetSuperPattern((PSPatternSpecification)otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubPatterns()).basicAdd(otherEnd, msgs);
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
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG:
            return basicSetCatalog(null, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS:
            return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES:
            return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS:
            return ((InternalEList<?>)getCombinedFragments()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS:
            return ((InternalEList<?>)getConstraints()).basicRemove(otherEnd, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN:
            return basicSetSuperPattern(null, msgs);
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS:
            return ((InternalEList<?>)getSubPatterns()).basicRemove(otherEnd, msgs);
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
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG:
            return eInternalContainer().eInverseRemove(this, SpecificationPackage.PS_CATALOG__PATTERN_SPECIFICATIONS, PSCatalog.class, msgs);
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
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG:
            return getCatalog();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS:
            return getConnections();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES:
            return getNodes();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS:
            return getCombinedFragments();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS:
            return getConstraints();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN:
            if (resolve) return getSuperPattern();
            return basicGetSuperPattern();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS:
            return getSubPatterns();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__TYPE:
            return getType();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__ABSTRACT:
            return isAbstract();
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
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG:
            setCatalog((PSCatalog)newValue);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS:
            getConnections().clear();
            getConnections().addAll((Collection<? extends PSConnection>)newValue);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES:
            getNodes().clear();
            getNodes().addAll((Collection<? extends PSNode>)newValue);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS:
            getCombinedFragments().clear();
            getCombinedFragments().addAll((Collection<? extends PSCombinedFragment>)newValue);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS:
            getConstraints().clear();
            getConstraints().addAll((Collection<? extends PSSpecificationConstraint>)newValue);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN:
            setSuperPattern((PSPatternSpecification)newValue);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS:
            getSubPatterns().clear();
            getSubPatterns().addAll((Collection<? extends PSPatternSpecification>)newValue);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__TYPE:
            setType((PatternType)newValue);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__ABSTRACT:
            setAbstract((Boolean)newValue);
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
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG:
            setCatalog((PSCatalog)null);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS:
            getConnections().clear();
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES:
            getNodes().clear();
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS:
            getCombinedFragments().clear();
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS:
            getConstraints().clear();
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN:
            setSuperPattern((PSPatternSpecification)null);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS:
            getSubPatterns().clear();
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__TYPE:
            setType(TYPE_EDEFAULT);
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__ABSTRACT:
            setAbstract(ABSTRACT_EDEFAULT);
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
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CATALOG:
            return getCatalog() != null;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS:
            return connections != null && !connections.isEmpty();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES:
            return nodes != null && !nodes.isEmpty();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS:
            return combinedFragments != null && !combinedFragments.isEmpty();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS:
            return constraints != null && !constraints.isEmpty();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUPER_PATTERN:
            return superPattern != null;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__SUB_PATTERNS:
            return subPatterns != null && !subPatterns.isEmpty();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__TYPE:
            return type != TYPE_EDEFAULT;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__ABSTRACT:
            return abstract_ != ABSTRACT_EDEFAULT;
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
      result.append(" (type: ");
      result.append(type);
      result.append(", abstract: ");
      result.append(abstract_);
      result.append(')');
      return result.toString();
   }

} //PSPatternSpecificationImpl
