/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.impl;

import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.fujaba.commons.identifier.IdentifierPackage;
import org.reclipse.behavior.specification.BPAnyObject;
import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BPEachFragment;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.BehavioralpatternPackage;

import org.reclipse.structure.specification.SpecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BehavioralpatternPackageImpl extends EPackageImpl implements BehavioralpatternPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass bpAnyObjectEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass bpObjectEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass bpSetObjectEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass bpAssignmentEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass behavioralPatternEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass bpMessageEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass bpArgumentEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass bpEachFragmentEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass bpCatalogEClass = null;

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
    * @see org.reclipse.behavior.specification.BehavioralpatternPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private BehavioralpatternPackageImpl()
   {
      super(eNS_URI, BehavioralpatternFactory.eINSTANCE);
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
    * <p>This method is used to initialize {@link BehavioralpatternPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static BehavioralpatternPackage init()
   {
      if (isInited) return (BehavioralpatternPackage)EPackage.Registry.INSTANCE.getEPackage(BehavioralpatternPackage.eNS_URI);

      // Obtain or create and register package
      BehavioralpatternPackageImpl theBehavioralpatternPackage = (BehavioralpatternPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BehavioralpatternPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BehavioralpatternPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      BasicSequenceDiagramPackage.eINSTANCE.eClass();
      SpecificationPackage.eINSTANCE.eClass();

      // Create package meta-data objects
      theBehavioralpatternPackage.createPackageContents();

      // Initialize created meta-data
      theBehavioralpatternPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theBehavioralpatternPackage.freeze();

  
      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(BehavioralpatternPackage.eNS_URI, theBehavioralpatternPackage);
      return theBehavioralpatternPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBPAnyObject()
   {
      return bpAnyObjectEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBPObject()
   {
      return bpObjectEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPObject_TypeReference()
   {
      return (EReference)bpObjectEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBPSetObject()
   {
      return bpSetObjectEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPSetObject_TypeReference()
   {
      return (EReference)bpSetObjectEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBPAssignment()
   {
      return bpAssignmentEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPAssignment_LeftSide()
   {
      return (EReference)bpAssignmentEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPAssignment_RightSide()
   {
      return (EReference)bpAssignmentEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBehavioralPattern()
   {
      return behavioralPatternEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getBehavioralPattern_Negative()
   {
      return (EAttribute)behavioralPatternEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBehavioralPattern_PsPatternSpecification()
   {
      return (EReference)behavioralPatternEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBehavioralPattern_Catalog()
   {
      return (EReference)behavioralPatternEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBPMessage()
   {
      return bpMessageEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPMessage_Arguments()
   {
      return (EReference)bpMessageEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getBPMessage_SelfCall()
   {
      return (EAttribute)bpMessageEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPMessage_MethodReference()
   {
      return (EReference)bpMessageEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBPArgument()
   {
      return bpArgumentEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPArgument_Message()
   {
      return (EReference)bpArgumentEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getBPArgument_Statement()
   {
      return (EAttribute)bpArgumentEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPArgument_Type()
   {
      return (EReference)bpArgumentEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBPEachFragment()
   {
      return bpEachFragmentEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getBPCatalog()
   {
      return bpCatalogEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPCatalog_BehavioralPatterns()
   {
      return (EReference)bpCatalogEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getBPCatalog_PsCatalog()
   {
      return (EReference)bpCatalogEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BehavioralpatternFactory getBehavioralpatternFactory()
   {
      return (BehavioralpatternFactory)getEFactoryInstance();
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
      bpAnyObjectEClass = createEClass(BP_ANY_OBJECT);

      bpObjectEClass = createEClass(BP_OBJECT);
      createEReference(bpObjectEClass, BP_OBJECT__TYPE_REFERENCE);

      bpSetObjectEClass = createEClass(BP_SET_OBJECT);
      createEReference(bpSetObjectEClass, BP_SET_OBJECT__TYPE_REFERENCE);

      bpAssignmentEClass = createEClass(BP_ASSIGNMENT);
      createEReference(bpAssignmentEClass, BP_ASSIGNMENT__LEFT_SIDE);
      createEReference(bpAssignmentEClass, BP_ASSIGNMENT__RIGHT_SIDE);

      behavioralPatternEClass = createEClass(BEHAVIORAL_PATTERN);
      createEAttribute(behavioralPatternEClass, BEHAVIORAL_PATTERN__NEGATIVE);
      createEReference(behavioralPatternEClass, BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION);
      createEReference(behavioralPatternEClass, BEHAVIORAL_PATTERN__CATALOG);

      bpMessageEClass = createEClass(BP_MESSAGE);
      createEReference(bpMessageEClass, BP_MESSAGE__ARGUMENTS);
      createEAttribute(bpMessageEClass, BP_MESSAGE__SELF_CALL);
      createEReference(bpMessageEClass, BP_MESSAGE__METHOD_REFERENCE);

      bpArgumentEClass = createEClass(BP_ARGUMENT);
      createEReference(bpArgumentEClass, BP_ARGUMENT__MESSAGE);
      createEAttribute(bpArgumentEClass, BP_ARGUMENT__STATEMENT);
      createEReference(bpArgumentEClass, BP_ARGUMENT__TYPE);

      bpEachFragmentEClass = createEClass(BP_EACH_FRAGMENT);

      bpCatalogEClass = createEClass(BP_CATALOG);
      createEReference(bpCatalogEClass, BP_CATALOG__BEHAVIORAL_PATTERNS);
      createEReference(bpCatalogEClass, BP_CATALOG__PS_CATALOG);
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
      BasicSequenceDiagramPackage theBasicSequenceDiagramPackage = (BasicSequenceDiagramPackage)EPackage.Registry.INSTANCE.getEPackage(BasicSequenceDiagramPackage.eNS_URI);
      SpecificationPackage theSpecificationPackage = (SpecificationPackage)EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI);
      IdentifierPackage theIdentifierPackage = (IdentifierPackage)EPackage.Registry.INSTANCE.getEPackage(IdentifierPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      bpAnyObjectEClass.getESuperTypes().add(theBasicSequenceDiagramPackage.getAbstractSequenceDiagramObject());
      bpObjectEClass.getESuperTypes().add(theBasicSequenceDiagramPackage.getAbstractSequenceDiagramObject());
      bpSetObjectEClass.getESuperTypes().add(theBasicSequenceDiagramPackage.getAbstractSequenceDiagramObject());
      bpAssignmentEClass.getESuperTypes().add(theBasicSequenceDiagramPackage.getLifelineFragment());
      behavioralPatternEClass.getESuperTypes().add(theBasicSequenceDiagramPackage.getSequenceDiagram());
      bpMessageEClass.getESuperTypes().add(theBasicSequenceDiagramPackage.getAbstractMessage());
      bpArgumentEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
      bpEachFragmentEClass.getESuperTypes().add(theBasicSequenceDiagramPackage.getCombinedFragment());
      bpCatalogEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

      // Initialize classes and features; add operations and parameters
      initEClass(bpAnyObjectEClass, BPAnyObject.class, "BPAnyObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(bpObjectEClass, BPObject.class, "BPObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBPObject_TypeReference(), theSpecificationPackage.getPSObject(), null, "typeReference", null, 0, 1, BPObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(bpSetObjectEClass, BPSetObject.class, "BPSetObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBPSetObject_TypeReference(), theSpecificationPackage.getPSObject(), null, "typeReference", null, 0, 1, BPSetObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(bpAssignmentEClass, BPAssignment.class, "BPAssignment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBPAssignment_LeftSide(), this.getBPObject(), null, "leftSide", null, 1, 1, BPAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBPAssignment_RightSide(), this.getBPArgument(), null, "rightSide", null, 1, 1, BPAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(behavioralPatternEClass, BehavioralPattern.class, "BehavioralPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getBehavioralPattern_Negative(), ecorePackage.getEBoolean(), "negative", null, 0, 1, BehavioralPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBehavioralPattern_PsPatternSpecification(), theSpecificationPackage.getPSPatternSpecification(), null, "psPatternSpecification", null, 0, 1, BehavioralPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBehavioralPattern_Catalog(), this.getBPCatalog(), this.getBPCatalog_BehavioralPatterns(), "catalog", null, 0, 1, BehavioralPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(bpMessageEClass, BPMessage.class, "BPMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBPMessage_Arguments(), this.getBPArgument(), this.getBPArgument_Message(), "arguments", null, 0, -1, BPMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBPMessage_SelfCall(), ecorePackage.getEBoolean(), "selfCall", null, 0, 1, BPMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBPMessage_MethodReference(), theSpecificationPackage.getPSObject(), null, "methodReference", null, 0, 1, BPMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(bpArgumentEClass, BPArgument.class, "BPArgument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBPArgument_Message(), this.getBPMessage(), this.getBPMessage_Arguments(), "message", null, 1, 1, BPArgument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBPArgument_Statement(), ecorePackage.getEString(), "statement", null, 0, 1, BPArgument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBPArgument_Type(), theSpecificationPackage.getPSObject(), null, "type", null, 0, 1, BPArgument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(bpEachFragmentEClass, BPEachFragment.class, "BPEachFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(bpCatalogEClass, BPCatalog.class, "BPCatalog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBPCatalog_BehavioralPatterns(), this.getBehavioralPattern(), this.getBehavioralPattern_Catalog(), "behavioralPatterns", null, 0, -1, BPCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBPCatalog_PsCatalog(), theSpecificationPackage.getPSCatalog(), null, "psCatalog", null, 1, 1, BPCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} //BehavioralpatternPackageImpl
