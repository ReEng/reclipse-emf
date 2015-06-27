/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationEngine;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.inference.annotations.AnnotationsFactory;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint;
import org.reclipse.structure.inference.annotations.SatisfiedConstraint;
import org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint;
import org.reclipse.structure.inference.annotations.SetInstanceAnnotation;
import org.reclipse.structure.inference.annotations.SetResultSet;
import org.reclipse.structure.inference.annotations.TemporaryAnnotation;
import org.reclipse.structure.specification.SpecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AnnotationsPackageImpl extends EPackageImpl implements AnnotationsPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass asgAnnotationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass temporaryAnnotationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass setInstanceAnnotationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass annotationSetEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass setResultSetEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass stringToEObjectMapEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass annotationEngineEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass satisfiedConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass satisfiedAttributeConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass satisfiedSpecificationConstraintEClass = null;

   /**
    * Creates an instance of the model <b>Package</b>, registered with
    * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
    * package URI value.
    * <p>Note: the correct way to create the package is via the static
    * factory method {@link #init init()}, which also performs
    * initialization of the package, or returns the registered package,
    * if one already exists.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see org.reclipse.structure.inference.annotations.AnnotationsPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private AnnotationsPackageImpl()
   {
      super(eNS_URI, AnnotationsFactory.eINSTANCE);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static boolean isInited = false;

   /**
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    * 
    * <p>This method is used to initialize {@link AnnotationsPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static AnnotationsPackage init()
   {
      if (isInited) return (AnnotationsPackage)EPackage.Registry.INSTANCE.getEPackage(AnnotationsPackage.eNS_URI);

      // Obtain or create and register package
      AnnotationsPackageImpl theAnnotationsPackage = (AnnotationsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AnnotationsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AnnotationsPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      SpecificationPackage.eINSTANCE.eClass();

      // Create package meta-data objects
      theAnnotationsPackage.createPackageContents();

      // Initialize created meta-data
      theAnnotationsPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theAnnotationsPackage.freeze();

  
      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(AnnotationsPackage.eNS_URI, theAnnotationsPackage);
      return theAnnotationsPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getASGAnnotation()
   {
      return asgAnnotationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getASGAnnotation_AnnotatedElements()
   {
      return (EReference)asgAnnotationEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getASGAnnotation_AntecedentAnnos()
   {
      return (EReference)asgAnnotationEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getASGAnnotation_ConsequentAnnos()
   {
      return (EReference)asgAnnotationEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getASGAnnotation_BoundObjects()
   {
      return (EReference)asgAnnotationEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getASGAnnotation_Valid()
   {
      return (EAttribute)asgAnnotationEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getASGAnnotation_Pattern()
   {
      return (EReference)asgAnnotationEClass.getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getASGAnnotation_AnnotationRanking()
   {
      return (EAttribute)asgAnnotationEClass.getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getASGAnnotation_SatisfiedConstraints()
   {
      return (EReference)asgAnnotationEClass.getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getASGAnnotation_SetResultSet()
   {
      return (EReference)asgAnnotationEClass.getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getTemporaryAnnotation()
   {
      return temporaryAnnotationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getSetInstanceAnnotation()
   {
      return setInstanceAnnotationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getAnnotationSet()
   {
      return annotationSetEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getAnnotationSet_Annotations()
   {
      return (EReference)annotationSetEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getAnnotationSet_SetResultSet()
   {
      return (EReference)annotationSetEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getSetResultSet()
   {
      return setResultSetEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getSetResultSet_AnnotationSets()
   {
      return (EReference)setResultSetEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getSetResultSet_ParentAnnotation()
   {
      return (EReference)setResultSetEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getStringToEObjectMap()
   {
      return stringToEObjectMapEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getStringToEObjectMap_Key()
   {
      return (EAttribute)stringToEObjectMapEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getStringToEObjectMap_Value()
   {
      return (EReference)stringToEObjectMapEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getAnnotationEngine()
   {
      return annotationEngineEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getAnnotationEngine_FailedApplications()
   {
      return (EReference)annotationEngineEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getAnnotationEngine_FoundAnnotations()
   {
      return (EReference)annotationEngineEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getSatisfiedConstraint()
   {
      return satisfiedConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getSatisfiedConstraint_Annotation()
   {
      return (EReference)satisfiedConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getSatisfiedAttributeConstraint()
   {
      return satisfiedAttributeConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getSatisfiedAttributeConstraint_NodeID()
   {
      return (EAttribute)satisfiedAttributeConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getSatisfiedAttributeConstraint_AttributeIndex()
   {
      return (EAttribute)satisfiedAttributeConstraintEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getSatisfiedAttributeConstraint_Context()
   {
      return (EReference)satisfiedAttributeConstraintEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getSatisfiedAttributeConstraint_Constraint()
   {
      return (EReference)satisfiedAttributeConstraintEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getSatisfiedSpecificationConstraint()
   {
      return satisfiedSpecificationConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getSatisfiedSpecificationConstraint_Expression()
   {
      return (EAttribute)satisfiedSpecificationConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getSatisfiedSpecificationConstraint_Constraint()
   {
      return (EReference)satisfiedSpecificationConstraintEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationsFactory getAnnotationsFactory()
   {
      return (AnnotationsFactory)getEFactoryInstance();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private boolean isCreated = false;

   /**
    * Creates the meta-model objects for the package.  This method is
    * guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void createPackageContents()
   {
      if (isCreated) return;
      isCreated = true;

      // Create classes and their features
      asgAnnotationEClass = createEClass(ASG_ANNOTATION);
      createEReference(asgAnnotationEClass, ASG_ANNOTATION__ANNOTATED_ELEMENTS);
      createEReference(asgAnnotationEClass, ASG_ANNOTATION__ANTECEDENT_ANNOS);
      createEReference(asgAnnotationEClass, ASG_ANNOTATION__CONSEQUENT_ANNOS);
      createEReference(asgAnnotationEClass, ASG_ANNOTATION__BOUND_OBJECTS);
      createEAttribute(asgAnnotationEClass, ASG_ANNOTATION__VALID);
      createEReference(asgAnnotationEClass, ASG_ANNOTATION__PATTERN);
      createEAttribute(asgAnnotationEClass, ASG_ANNOTATION__ANNOTATION_RANKING);
      createEReference(asgAnnotationEClass, ASG_ANNOTATION__SATISFIED_CONSTRAINTS);
      createEReference(asgAnnotationEClass, ASG_ANNOTATION__SET_RESULT_SET);

      temporaryAnnotationEClass = createEClass(TEMPORARY_ANNOTATION);

      setInstanceAnnotationEClass = createEClass(SET_INSTANCE_ANNOTATION);

      annotationSetEClass = createEClass(ANNOTATION_SET);
      createEReference(annotationSetEClass, ANNOTATION_SET__ANNOTATIONS);
      createEReference(annotationSetEClass, ANNOTATION_SET__SET_RESULT_SET);

      setResultSetEClass = createEClass(SET_RESULT_SET);
      createEReference(setResultSetEClass, SET_RESULT_SET__ANNOTATION_SETS);
      createEReference(setResultSetEClass, SET_RESULT_SET__PARENT_ANNOTATION);

      stringToEObjectMapEClass = createEClass(STRING_TO_EOBJECT_MAP);
      createEAttribute(stringToEObjectMapEClass, STRING_TO_EOBJECT_MAP__KEY);
      createEReference(stringToEObjectMapEClass, STRING_TO_EOBJECT_MAP__VALUE);

      annotationEngineEClass = createEClass(ANNOTATION_ENGINE);
      createEReference(annotationEngineEClass, ANNOTATION_ENGINE__FAILED_APPLICATIONS);
      createEReference(annotationEngineEClass, ANNOTATION_ENGINE__FOUND_ANNOTATIONS);

      satisfiedConstraintEClass = createEClass(SATISFIED_CONSTRAINT);
      createEReference(satisfiedConstraintEClass, SATISFIED_CONSTRAINT__ANNOTATION);

      satisfiedAttributeConstraintEClass = createEClass(SATISFIED_ATTRIBUTE_CONSTRAINT);
      createEAttribute(satisfiedAttributeConstraintEClass, SATISFIED_ATTRIBUTE_CONSTRAINT__NODE_ID);
      createEAttribute(satisfiedAttributeConstraintEClass, SATISFIED_ATTRIBUTE_CONSTRAINT__ATTRIBUTE_INDEX);
      createEReference(satisfiedAttributeConstraintEClass, SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT);
      createEReference(satisfiedAttributeConstraintEClass, SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT);

      satisfiedSpecificationConstraintEClass = createEClass(SATISFIED_SPECIFICATION_CONSTRAINT);
      createEAttribute(satisfiedSpecificationConstraintEClass, SATISFIED_SPECIFICATION_CONSTRAINT__EXPRESSION);
      createEReference(satisfiedSpecificationConstraintEClass, SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private boolean isInitialized = false;

   /**
    * Complete the initialization of the package and its meta-model.  This
    * method is guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void initializePackageContents()
   {
      if (isInitialized) return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Obtain other dependent packages
      EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
      SpecificationPackage theSpecificationPackage = (SpecificationPackage)EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      asgAnnotationEClass.getESuperTypes().add(theEcorePackage.getEAnnotation());
      asgAnnotationEClass.getESuperTypes().add(theEcorePackage.getENamedElement());
      temporaryAnnotationEClass.getESuperTypes().add(this.getASGAnnotation());
      setInstanceAnnotationEClass.getESuperTypes().add(this.getASGAnnotation());
      satisfiedAttributeConstraintEClass.getESuperTypes().add(this.getSatisfiedConstraint());
      satisfiedSpecificationConstraintEClass.getESuperTypes().add(this.getSatisfiedConstraint());

      // Initialize classes and features; add operations and parameters
      initEClass(asgAnnotationEClass, ASGAnnotation.class, "ASGAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getASGAnnotation_AnnotatedElements(), this.getStringToEObjectMap(), null, "annotatedElements", null, 0, -1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getASGAnnotation_AntecedentAnnos(), this.getASGAnnotation(), this.getASGAnnotation_ConsequentAnnos(), "antecedentAnnos", null, 0, -1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getASGAnnotation_ConsequentAnnos(), this.getASGAnnotation(), this.getASGAnnotation_AntecedentAnnos(), "consequentAnnos", null, 0, -1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getASGAnnotation_BoundObjects(), this.getStringToEObjectMap(), null, "boundObjects", null, 0, -1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getASGAnnotation_Valid(), ecorePackage.getEBoolean(), "valid", null, 0, 1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getASGAnnotation_Pattern(), theSpecificationPackage.getPSPatternSpecification(), null, "pattern", null, 0, 1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getASGAnnotation_AnnotationRanking(), ecorePackage.getEDouble(), "annotationRanking", null, 0, 1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getASGAnnotation_SatisfiedConstraints(), this.getSatisfiedConstraint(), this.getSatisfiedConstraint_Annotation(), "satisfiedConstraints", null, 0, -1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getASGAnnotation_SetResultSet(), this.getSetResultSet(), this.getSetResultSet_ParentAnnotation(), "setResultSet", null, 0, -1, ASGAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(temporaryAnnotationEClass, TemporaryAnnotation.class, "TemporaryAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(setInstanceAnnotationEClass, SetInstanceAnnotation.class, "SetInstanceAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(annotationSetEClass, AnnotationSet.class, "AnnotationSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getAnnotationSet_Annotations(), this.getASGAnnotation(), null, "annotations", null, 0, -1, AnnotationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getAnnotationSet_SetResultSet(), this.getSetResultSet(), this.getSetResultSet_AnnotationSets(), "setResultSet", null, 0, 1, AnnotationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(setResultSetEClass, SetResultSet.class, "SetResultSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getSetResultSet_AnnotationSets(), this.getAnnotationSet(), this.getAnnotationSet_SetResultSet(), "annotationSets", null, 0, -1, SetResultSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getSetResultSet_ParentAnnotation(), this.getASGAnnotation(), this.getASGAnnotation_SetResultSet(), "parentAnnotation", null, 0, 1, SetResultSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(stringToEObjectMapEClass, Map.Entry.class, "StringToEObjectMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getStringToEObjectMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getStringToEObjectMap_Value(), theEcorePackage.getEObject(), null, "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(annotationEngineEClass, AnnotationEngine.class, "AnnotationEngine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getAnnotationEngine_FailedApplications(), this.getStringToEObjectMap(), null, "failedApplications", null, 0, -1, AnnotationEngine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getAnnotationEngine_FoundAnnotations(), this.getAnnotationSet(), null, "foundAnnotations", null, 0, 1, AnnotationEngine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      EOperation op = addEOperation(annotationEngineEClass, null, "removeFromFailedApplications", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, theEcorePackage.getEObject(), "context", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(satisfiedConstraintEClass, SatisfiedConstraint.class, "SatisfiedConstraint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getSatisfiedConstraint_Annotation(), this.getASGAnnotation(), this.getASGAnnotation_SatisfiedConstraints(), "annotation", null, 0, 1, SatisfiedConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(satisfiedAttributeConstraintEClass, SatisfiedAttributeConstraint.class, "SatisfiedAttributeConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getSatisfiedAttributeConstraint_NodeID(), ecorePackage.getEString(), "nodeID", null, 0, 1, SatisfiedAttributeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getSatisfiedAttributeConstraint_AttributeIndex(), ecorePackage.getEInt(), "attributeIndex", null, 0, 1, SatisfiedAttributeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getSatisfiedAttributeConstraint_Context(), theEcorePackage.getEObject(), null, "context", null, 0, 1, SatisfiedAttributeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getSatisfiedAttributeConstraint_Constraint(), theSpecificationPackage.getPSAttributeConstraint(), null, "constraint", null, 0, 1, SatisfiedAttributeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(satisfiedSpecificationConstraintEClass, SatisfiedSpecificationConstraint.class, "SatisfiedSpecificationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getSatisfiedSpecificationConstraint_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, SatisfiedSpecificationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getSatisfiedSpecificationConstraint_Constraint(), theSpecificationPackage.getPSSpecificationConstraint(), null, "constraint", null, 0, 1, SatisfiedSpecificationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} //AnnotationsPackageImpl
