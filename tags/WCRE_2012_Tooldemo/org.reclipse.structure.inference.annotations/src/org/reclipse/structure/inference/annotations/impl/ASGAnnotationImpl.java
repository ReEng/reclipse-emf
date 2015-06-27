/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SatisfiedConstraint;
import org.reclipse.structure.inference.annotations.SetResultSet;
import org.reclipse.structure.specification.PSPatternSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ASG Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getAnnotatedElements <em>Annotated Elements</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getAntecedentAnnos <em>Antecedent Annos</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getConsequentAnnos <em>Consequent Annos</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getBoundObjects <em>Bound Objects</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#isValid <em>Valid</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getAnnotationRanking <em>Annotation Ranking</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getSatisfiedConstraints <em>Satisfied Constraints</em>}</li>
 *   <li>{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl#getSetResultSet <em>Set Result Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ASGAnnotationImpl extends EAnnotationImpl implements ASGAnnotation
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
    * The cached value of the '{@link #getAnnotatedElements() <em>Annotated Elements</em>}' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAnnotatedElements()
    * @generated
    * @ordered
    */
   protected EMap<String, EList<EObject>> annotatedElements;

   /**
    * The cached value of the '{@link #getAntecedentAnnos() <em>Antecedent Annos</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAntecedentAnnos()
    * @generated
    * @ordered
    */
   protected EList<ASGAnnotation> antecedentAnnos;

   /**
    * The cached value of the '{@link #getConsequentAnnos() <em>Consequent Annos</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConsequentAnnos()
    * @generated
    * @ordered
    */
   protected EList<ASGAnnotation> consequentAnnos;

   /**
    * The cached value of the '{@link #getBoundObjects() <em>Bound Objects</em>}' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getBoundObjects()
    * @generated
    * @ordered
    */
   protected EMap<String, EList<EObject>> boundObjects;

   /**
    * The default value of the '{@link #isValid() <em>Valid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isValid()
    * @generated
    * @ordered
    */
   protected static final boolean VALID_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isValid() <em>Valid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isValid()
    * @generated
    * @ordered
    */
   protected boolean valid = VALID_EDEFAULT;

   /**
    * The cached value of the '{@link #getPattern() <em>Pattern</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPattern()
    * @generated
    * @ordered
    */
   protected PSPatternSpecification pattern;

   /**
    * The default value of the '{@link #getAnnotationRanking() <em>Annotation Ranking</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAnnotationRanking()
    * @generated
    * @ordered
    */
   protected static final double ANNOTATION_RANKING_EDEFAULT = 0.0;

   /**
    * The cached value of the '{@link #getAnnotationRanking() <em>Annotation Ranking</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAnnotationRanking()
    * @generated
    * @ordered
    */
   protected double annotationRanking = ANNOTATION_RANKING_EDEFAULT;

   /**
    * The cached value of the '{@link #getSatisfiedConstraints() <em>Satisfied Constraints</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSatisfiedConstraints()
    * @generated
    * @ordered
    */
   protected EList<SatisfiedConstraint> satisfiedConstraints;

   /**
    * The cached value of the '{@link #getSetResultSet() <em>Set Result Set</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSetResultSet()
    * @generated
    * @ordered
    */
   protected EList<SetResultSet> setResultSet;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ASGAnnotationImpl()
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
      return AnnotationsPackage.Literals.ASG_ANNOTATION;
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
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.ASG_ANNOTATION__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public EMap<String, EList<EObject>> getAnnotatedElements()
   {
      if (annotatedElements == null)
      {
//         annotatedElements = new EcoreEMap<String,EList<EObject>>(AnnotationsPackage.Literals.STRING_TO_EOBJECT_MAP, StringToEObjectMapImpl.class, this, AnnotationsPackage.ASG_ANNOTATION__ANNOTATED_ELEMENTS);
         annotatedElements = new AnnotatedElementsEcoreEMap<String,EList<EObject>>(AnnotationsPackage.Literals.STRING_TO_EOBJECT_MAP, StringToEObjectMapImpl.class, this, AnnotationsPackage.ASG_ANNOTATION__ANNOTATED_ELEMENTS);
      }
      return annotatedElements;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ASGAnnotation> getAntecedentAnnos()
   {
      if (antecedentAnnos == null)
      {
         antecedentAnnos = new EObjectWithInverseResolvingEList.ManyInverse<ASGAnnotation>(ASGAnnotation.class, this, AnnotationsPackage.ASG_ANNOTATION__ANTECEDENT_ANNOS, AnnotationsPackage.ASG_ANNOTATION__CONSEQUENT_ANNOS);
      }
      return antecedentAnnos;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ASGAnnotation> getConsequentAnnos()
   {
      if (consequentAnnos == null)
      {
         consequentAnnos = new EObjectWithInverseResolvingEList.ManyInverse<ASGAnnotation>(ASGAnnotation.class, this, AnnotationsPackage.ASG_ANNOTATION__CONSEQUENT_ANNOS, AnnotationsPackage.ASG_ANNOTATION__ANTECEDENT_ANNOS);
      }
      return consequentAnnos;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EMap<String, EList<EObject>> getBoundObjects()
   {
      if (boundObjects == null)
      {
         boundObjects = new EcoreEMap<String,EList<EObject>>(AnnotationsPackage.Literals.STRING_TO_EOBJECT_MAP, StringToEObjectMapImpl.class, this, AnnotationsPackage.ASG_ANNOTATION__BOUND_OBJECTS);
      }
      return boundObjects;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isValid()
   {
      return valid;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setValid(boolean newValid)
   {
      boolean oldValid = valid;
      valid = newValid;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.ASG_ANNOTATION__VALID, oldValid, valid));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification getPattern()
   {
      if (pattern != null && pattern.eIsProxy())
      {
         InternalEObject oldPattern = (InternalEObject)pattern;
         pattern = (PSPatternSpecification)eResolveProxy(oldPattern);
         if (pattern != oldPattern)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnnotationsPackage.ASG_ANNOTATION__PATTERN, oldPattern, pattern));
         }
      }
      return pattern;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification basicGetPattern()
   {
      return pattern;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setPattern(PSPatternSpecification newPattern)
   {
      PSPatternSpecification oldPattern = pattern;
      pattern = newPattern;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.ASG_ANNOTATION__PATTERN, oldPattern, pattern));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public double getAnnotationRanking()
   {
      return annotationRanking;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setAnnotationRanking(double newAnnotationRanking)
   {
      double oldAnnotationRanking = annotationRanking;
      annotationRanking = newAnnotationRanking;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, AnnotationsPackage.ASG_ANNOTATION__ANNOTATION_RANKING, oldAnnotationRanking, annotationRanking));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<SatisfiedConstraint> getSatisfiedConstraints()
   {
      if (satisfiedConstraints == null)
      {
         satisfiedConstraints = new EObjectContainmentWithInverseEList<SatisfiedConstraint>(SatisfiedConstraint.class, this, AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS, AnnotationsPackage.SATISFIED_CONSTRAINT__ANNOTATION);
      }
      return satisfiedConstraints;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<SetResultSet> getSetResultSet()
   {
      if (setResultSet == null)
      {
         setResultSet = new EObjectContainmentWithInverseEList<SetResultSet>(SetResultSet.class, this, AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET, AnnotationsPackage.SET_RESULT_SET__PARENT_ANNOTATION);
      }
      return setResultSet;
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
         case AnnotationsPackage.ASG_ANNOTATION__ANTECEDENT_ANNOS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getAntecedentAnnos()).basicAdd(otherEnd, msgs);
         case AnnotationsPackage.ASG_ANNOTATION__CONSEQUENT_ANNOS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getConsequentAnnos()).basicAdd(otherEnd, msgs);
         case AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getSatisfiedConstraints()).basicAdd(otherEnd, msgs);
         case AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getSetResultSet()).basicAdd(otherEnd, msgs);
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
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATED_ELEMENTS:
            return ((InternalEList<?>)getAnnotatedElements()).basicRemove(otherEnd, msgs);
         case AnnotationsPackage.ASG_ANNOTATION__ANTECEDENT_ANNOS:
            return ((InternalEList<?>)getAntecedentAnnos()).basicRemove(otherEnd, msgs);
         case AnnotationsPackage.ASG_ANNOTATION__CONSEQUENT_ANNOS:
            return ((InternalEList<?>)getConsequentAnnos()).basicRemove(otherEnd, msgs);
         case AnnotationsPackage.ASG_ANNOTATION__BOUND_OBJECTS:
            return ((InternalEList<?>)getBoundObjects()).basicRemove(otherEnd, msgs);
         case AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS:
            return ((InternalEList<?>)getSatisfiedConstraints()).basicRemove(otherEnd, msgs);
         case AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET:
            return ((InternalEList<?>)getSetResultSet()).basicRemove(otherEnd, msgs);
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
         case AnnotationsPackage.ASG_ANNOTATION__NAME:
            return getName();
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATED_ELEMENTS:
            if (coreType) return getAnnotatedElements();
            else return getAnnotatedElements().map();
         case AnnotationsPackage.ASG_ANNOTATION__ANTECEDENT_ANNOS:
            return getAntecedentAnnos();
         case AnnotationsPackage.ASG_ANNOTATION__CONSEQUENT_ANNOS:
            return getConsequentAnnos();
         case AnnotationsPackage.ASG_ANNOTATION__BOUND_OBJECTS:
            if (coreType) return getBoundObjects();
            else return getBoundObjects().map();
         case AnnotationsPackage.ASG_ANNOTATION__VALID:
            return isValid();
         case AnnotationsPackage.ASG_ANNOTATION__PATTERN:
            if (resolve) return getPattern();
            return basicGetPattern();
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATION_RANKING:
            return getAnnotationRanking();
         case AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS:
            return getSatisfiedConstraints();
         case AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET:
            return getSetResultSet();
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
         case AnnotationsPackage.ASG_ANNOTATION__NAME:
            setName((String)newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATED_ELEMENTS:
            ((EStructuralFeature.Setting)getAnnotatedElements()).set(newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__ANTECEDENT_ANNOS:
            getAntecedentAnnos().clear();
            getAntecedentAnnos().addAll((Collection<? extends ASGAnnotation>)newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__CONSEQUENT_ANNOS:
            getConsequentAnnos().clear();
            getConsequentAnnos().addAll((Collection<? extends ASGAnnotation>)newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__BOUND_OBJECTS:
            ((EStructuralFeature.Setting)getBoundObjects()).set(newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__VALID:
            setValid((Boolean)newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__PATTERN:
            setPattern((PSPatternSpecification)newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATION_RANKING:
            setAnnotationRanking((Double)newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS:
            getSatisfiedConstraints().clear();
            getSatisfiedConstraints().addAll((Collection<? extends SatisfiedConstraint>)newValue);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET:
            getSetResultSet().clear();
            getSetResultSet().addAll((Collection<? extends SetResultSet>)newValue);
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
         case AnnotationsPackage.ASG_ANNOTATION__NAME:
            setName(NAME_EDEFAULT);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATED_ELEMENTS:
            getAnnotatedElements().clear();
            return;
         case AnnotationsPackage.ASG_ANNOTATION__ANTECEDENT_ANNOS:
            getAntecedentAnnos().clear();
            return;
         case AnnotationsPackage.ASG_ANNOTATION__CONSEQUENT_ANNOS:
            getConsequentAnnos().clear();
            return;
         case AnnotationsPackage.ASG_ANNOTATION__BOUND_OBJECTS:
            getBoundObjects().clear();
            return;
         case AnnotationsPackage.ASG_ANNOTATION__VALID:
            setValid(VALID_EDEFAULT);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__PATTERN:
            setPattern((PSPatternSpecification)null);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATION_RANKING:
            setAnnotationRanking(ANNOTATION_RANKING_EDEFAULT);
            return;
         case AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS:
            getSatisfiedConstraints().clear();
            return;
         case AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET:
            getSetResultSet().clear();
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
         case AnnotationsPackage.ASG_ANNOTATION__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATED_ELEMENTS:
            return annotatedElements != null && !annotatedElements.isEmpty();
         case AnnotationsPackage.ASG_ANNOTATION__ANTECEDENT_ANNOS:
            return antecedentAnnos != null && !antecedentAnnos.isEmpty();
         case AnnotationsPackage.ASG_ANNOTATION__CONSEQUENT_ANNOS:
            return consequentAnnos != null && !consequentAnnos.isEmpty();
         case AnnotationsPackage.ASG_ANNOTATION__BOUND_OBJECTS:
            return boundObjects != null && !boundObjects.isEmpty();
         case AnnotationsPackage.ASG_ANNOTATION__VALID:
            return valid != VALID_EDEFAULT;
         case AnnotationsPackage.ASG_ANNOTATION__PATTERN:
            return pattern != null;
         case AnnotationsPackage.ASG_ANNOTATION__ANNOTATION_RANKING:
            return annotationRanking != ANNOTATION_RANKING_EDEFAULT;
         case AnnotationsPackage.ASG_ANNOTATION__SATISFIED_CONSTRAINTS:
            return satisfiedConstraints != null && !satisfiedConstraints.isEmpty();
         case AnnotationsPackage.ASG_ANNOTATION__SET_RESULT_SET:
            return setResultSet != null && !setResultSet.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == ENamedElement.class)
      {
         switch (derivedFeatureID)
         {
            case AnnotationsPackage.ASG_ANNOTATION__NAME: return EcorePackage.ENAMED_ELEMENT__NAME;
            default: return -1;
         }
      }
      return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == ENamedElement.class)
      {
         switch (baseFeatureID)
         {
            case EcorePackage.ENAMED_ELEMENT__NAME: return AnnotationsPackage.ASG_ANNOTATION__NAME;
            default: return -1;
         }
      }
      return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
      result.append(", valid: ");
      result.append(valid);
      result.append(", annotationRanking: ");
      result.append(annotationRanking);
      result.append(')');
      return result.toString();
   }

} //ASGAnnotationImpl
