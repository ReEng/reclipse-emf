/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.fujaba.commons.identifier.IdentifierPackage;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSItem;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.PatternType;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SpecificationPackageImpl extends EPackageImpl implements SpecificationPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psCatalogEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psPatternSpecificationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psItemEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psConnectionEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psLinkEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psPathEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psCombinedFragmentItemEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psCombinedFragmentEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psSpecificationConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psNodeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psObjectEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psAnnotationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psNodeConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psFuzzyConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psFuzzyMetricConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psFuzzySetRatingConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psBooleanConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psAttributeConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psMetricConstraintEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass psFunctionParameterEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EEnum patternTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EEnum modifierTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EEnum operatorTypeEEnum = null;

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
    * @see org.reclipse.structure.specification.SpecificationPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private SpecificationPackageImpl()
   {
      super(eNS_URI, SpecificationFactory.eINSTANCE);
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
    * <p>This method is used to initialize {@link SpecificationPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static SpecificationPackage init()
   {
      if (isInited) return (SpecificationPackage)EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI);

      // Obtain or create and register package
      SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SpecificationPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      IdentifierPackage.eINSTANCE.eClass();

      // Create package meta-data objects
      theSpecificationPackage.createPackageContents();

      // Initialize created meta-data
      theSpecificationPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theSpecificationPackage.freeze();

  
      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(SpecificationPackage.eNS_URI, theSpecificationPackage);
      return theSpecificationPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSCatalog()
   {
      return psCatalogEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSCatalog_PatternSpecifications()
   {
      return (EReference)psCatalogEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSCatalog_Metamodel()
   {
      return (EAttribute)psCatalogEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSPatternSpecification()
   {
      return psPatternSpecificationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSPatternSpecification_Catalog()
   {
      return (EReference)psPatternSpecificationEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSPatternSpecification_Connections()
   {
      return (EReference)psPatternSpecificationEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSPatternSpecification_Nodes()
   {
      return (EReference)psPatternSpecificationEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSPatternSpecification_CombinedFragments()
   {
      return (EReference)psPatternSpecificationEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSPatternSpecification_Constraints()
   {
      return (EReference)psPatternSpecificationEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSPatternSpecification_SuperPattern()
   {
      return (EReference)psPatternSpecificationEClass.getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSPatternSpecification_SubPatterns()
   {
      return (EReference)psPatternSpecificationEClass.getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSPatternSpecification_Type()
   {
      return (EAttribute)psPatternSpecificationEClass.getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSPatternSpecification_Abstract()
   {
      return (EAttribute)psPatternSpecificationEClass.getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSItem()
   {
      return psItemEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSItem_Weight()
   {
      return (EAttribute)psItemEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSConnection()
   {
      return psConnectionEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSConnection_Source()
   {
      return (EReference)psConnectionEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSConnection_Target()
   {
      return (EReference)psConnectionEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSConnection_PatternSpecification()
   {
      return (EReference)psConnectionEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSLink()
   {
      return psLinkEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSLink_Qualifier()
   {
      return (EAttribute)psLinkEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSLink_Negative()
   {
      return (EAttribute)psLinkEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSLink_InstanceOf()
   {
      return (EReference)psLinkEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSPath()
   {
      return psPathEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSPath_TabooClasses()
   {
      return (EReference)psPathEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSCombinedFragmentItem()
   {
      return psCombinedFragmentItemEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSCombinedFragmentItem_Parents()
   {
      return (EReference)psCombinedFragmentItemEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSCombinedFragment()
   {
      return psCombinedFragmentEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSCombinedFragment_Children()
   {
      return (EReference)psCombinedFragmentEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSCombinedFragment_Kind()
   {
      return (EAttribute)psCombinedFragmentEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSCombinedFragment_PatternSpecification()
   {
      return (EReference)psCombinedFragmentEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSCombinedFragment_Constraint()
   {
      return (EReference)psCombinedFragmentEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSSpecificationConstraint()
   {
      return psSpecificationConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSSpecificationConstraint_Additional()
   {
      return (EAttribute)psSpecificationConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSSpecificationConstraint_PatternSpecification()
   {
      return (EReference)psSpecificationConstraintEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSSpecificationConstraint_Expression()
   {
      return (EAttribute)psSpecificationConstraintEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSNode()
   {
      return psNodeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSNode_Trigger()
   {
      return (EAttribute)psNodeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSNode_Modifier()
   {
      return (EAttribute)psNodeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSNode_Outgoing()
   {
      return (EReference)psNodeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSNode_Incoming()
   {
      return (EReference)psNodeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSNode_PatternSpecification()
   {
      return (EReference)psNodeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSNode_NodeConstraints()
   {
      return (EReference)psNodeEClass.getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSObject()
   {
      return psObjectEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSObject_InstanceOf()
   {
      return (EReference)psObjectEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSAnnotation()
   {
      return psAnnotationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSAnnotation_Type()
   {
      return (EReference)psAnnotationEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSNodeConstraint()
   {
      return psNodeConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSNodeConstraint_Node()
   {
      return (EReference)psNodeConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSNodeConstraint_Expression()
   {
      return (EAttribute)psNodeConstraintEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSFuzzyConstraint()
   {
      return psFuzzyConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSFuzzyConstraint_MathFunctionID()
   {
      return (EAttribute)psFuzzyConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSFuzzyConstraint_Parameters()
   {
      return (EReference)psFuzzyConstraintEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSFuzzyMetricConstraint()
   {
      return psFuzzyMetricConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSFuzzyMetricConstraint_MetricAcronym()
   {
      return (EAttribute)psFuzzyMetricConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSFuzzySetRatingConstraint()
   {
      return psFuzzySetRatingConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSBooleanConstraint()
   {
      return psBooleanConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSBooleanConstraint_Additional()
   {
      return (EAttribute)psBooleanConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSBooleanConstraint_ValueExpression()
   {
      return (EAttribute)psBooleanConstraintEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSBooleanConstraint_Operator()
   {
      return (EAttribute)psBooleanConstraintEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSAttributeConstraint()
   {
      return psAttributeConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSAttributeConstraint_Attribute()
   {
      return (EReference)psAttributeConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSMetricConstraint()
   {
      return psMetricConstraintEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSMetricConstraint_MetricAcronym()
   {
      return (EAttribute)psMetricConstraintEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPSFunctionParameter()
   {
      return psFunctionParameterEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPSFunctionParameter_Value()
   {
      return (EAttribute)psFunctionParameterEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getPSFunctionParameter_Constraint()
   {
      return (EReference)psFunctionParameterEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EEnum getPatternType()
   {
      return patternTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EEnum getModifierType()
   {
      return modifierTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EEnum getOperatorType()
   {
      return operatorTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SpecificationFactory getSpecificationFactory()
   {
      return (SpecificationFactory)getEFactoryInstance();
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
      psCatalogEClass = createEClass(PS_CATALOG);
      createEReference(psCatalogEClass, PS_CATALOG__PATTERN_SPECIFICATIONS);
      createEAttribute(psCatalogEClass, PS_CATALOG__METAMODEL);

      psPatternSpecificationEClass = createEClass(PS_PATTERN_SPECIFICATION);
      createEReference(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__CATALOG);
      createEReference(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__CONNECTIONS);
      createEReference(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__NODES);
      createEReference(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS);
      createEReference(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__CONSTRAINTS);
      createEReference(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__SUPER_PATTERN);
      createEReference(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__SUB_PATTERNS);
      createEAttribute(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__TYPE);
      createEAttribute(psPatternSpecificationEClass, PS_PATTERN_SPECIFICATION__ABSTRACT);

      psItemEClass = createEClass(PS_ITEM);
      createEAttribute(psItemEClass, PS_ITEM__WEIGHT);

      psConnectionEClass = createEClass(PS_CONNECTION);
      createEReference(psConnectionEClass, PS_CONNECTION__SOURCE);
      createEReference(psConnectionEClass, PS_CONNECTION__TARGET);
      createEReference(psConnectionEClass, PS_CONNECTION__PATTERN_SPECIFICATION);

      psLinkEClass = createEClass(PS_LINK);
      createEAttribute(psLinkEClass, PS_LINK__QUALIFIER);
      createEAttribute(psLinkEClass, PS_LINK__NEGATIVE);
      createEReference(psLinkEClass, PS_LINK__INSTANCE_OF);

      psPathEClass = createEClass(PS_PATH);
      createEReference(psPathEClass, PS_PATH__TABOO_CLASSES);

      psCombinedFragmentItemEClass = createEClass(PS_COMBINED_FRAGMENT_ITEM);
      createEReference(psCombinedFragmentItemEClass, PS_COMBINED_FRAGMENT_ITEM__PARENTS);

      psCombinedFragmentEClass = createEClass(PS_COMBINED_FRAGMENT);
      createEReference(psCombinedFragmentEClass, PS_COMBINED_FRAGMENT__CHILDREN);
      createEAttribute(psCombinedFragmentEClass, PS_COMBINED_FRAGMENT__KIND);
      createEReference(psCombinedFragmentEClass, PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION);
      createEReference(psCombinedFragmentEClass, PS_COMBINED_FRAGMENT__CONSTRAINT);

      psSpecificationConstraintEClass = createEClass(PS_SPECIFICATION_CONSTRAINT);
      createEAttribute(psSpecificationConstraintEClass, PS_SPECIFICATION_CONSTRAINT__ADDITIONAL);
      createEReference(psSpecificationConstraintEClass, PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION);
      createEAttribute(psSpecificationConstraintEClass, PS_SPECIFICATION_CONSTRAINT__EXPRESSION);

      psNodeEClass = createEClass(PS_NODE);
      createEAttribute(psNodeEClass, PS_NODE__TRIGGER);
      createEAttribute(psNodeEClass, PS_NODE__MODIFIER);
      createEReference(psNodeEClass, PS_NODE__OUTGOING);
      createEReference(psNodeEClass, PS_NODE__INCOMING);
      createEReference(psNodeEClass, PS_NODE__PATTERN_SPECIFICATION);
      createEReference(psNodeEClass, PS_NODE__NODE_CONSTRAINTS);

      psObjectEClass = createEClass(PS_OBJECT);
      createEReference(psObjectEClass, PS_OBJECT__INSTANCE_OF);

      psAnnotationEClass = createEClass(PS_ANNOTATION);
      createEReference(psAnnotationEClass, PS_ANNOTATION__TYPE);

      psNodeConstraintEClass = createEClass(PS_NODE_CONSTRAINT);
      createEReference(psNodeConstraintEClass, PS_NODE_CONSTRAINT__NODE);
      createEAttribute(psNodeConstraintEClass, PS_NODE_CONSTRAINT__EXPRESSION);

      psFuzzyConstraintEClass = createEClass(PS_FUZZY_CONSTRAINT);
      createEAttribute(psFuzzyConstraintEClass, PS_FUZZY_CONSTRAINT__MATH_FUNCTION_ID);
      createEReference(psFuzzyConstraintEClass, PS_FUZZY_CONSTRAINT__PARAMETERS);

      psFuzzyMetricConstraintEClass = createEClass(PS_FUZZY_METRIC_CONSTRAINT);
      createEAttribute(psFuzzyMetricConstraintEClass, PS_FUZZY_METRIC_CONSTRAINT__METRIC_ACRONYM);

      psFuzzySetRatingConstraintEClass = createEClass(PS_FUZZY_SET_RATING_CONSTRAINT);

      psBooleanConstraintEClass = createEClass(PS_BOOLEAN_CONSTRAINT);
      createEAttribute(psBooleanConstraintEClass, PS_BOOLEAN_CONSTRAINT__ADDITIONAL);
      createEAttribute(psBooleanConstraintEClass, PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION);
      createEAttribute(psBooleanConstraintEClass, PS_BOOLEAN_CONSTRAINT__OPERATOR);

      psAttributeConstraintEClass = createEClass(PS_ATTRIBUTE_CONSTRAINT);
      createEReference(psAttributeConstraintEClass, PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE);

      psMetricConstraintEClass = createEClass(PS_METRIC_CONSTRAINT);
      createEAttribute(psMetricConstraintEClass, PS_METRIC_CONSTRAINT__METRIC_ACRONYM);

      psFunctionParameterEClass = createEClass(PS_FUNCTION_PARAMETER);
      createEAttribute(psFunctionParameterEClass, PS_FUNCTION_PARAMETER__VALUE);
      createEReference(psFunctionParameterEClass, PS_FUNCTION_PARAMETER__CONSTRAINT);

      // Create enums
      patternTypeEEnum = createEEnum(PATTERN_TYPE);
      modifierTypeEEnum = createEEnum(MODIFIER_TYPE);
      operatorTypeEEnum = createEEnum(OPERATOR_TYPE);
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
      IdentifierPackage theIdentifierPackage = (IdentifierPackage)EPackage.Registry.INSTANCE.getEPackage(IdentifierPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      psCatalogEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
      psPatternSpecificationEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
      psItemEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
      psConnectionEClass.getESuperTypes().add(this.getPSItem());
      psLinkEClass.getESuperTypes().add(this.getPSConnection());
      psPathEClass.getESuperTypes().add(this.getPSConnection());
      psCombinedFragmentItemEClass.getESuperTypes().add(this.getPSItem());
      psCombinedFragmentEClass.getESuperTypes().add(this.getPSCombinedFragmentItem());
      psSpecificationConstraintEClass.getESuperTypes().add(this.getPSCombinedFragmentItem());
      psNodeEClass.getESuperTypes().add(this.getPSCombinedFragmentItem());
      psObjectEClass.getESuperTypes().add(this.getPSNode());
      psAnnotationEClass.getESuperTypes().add(this.getPSNode());
      psNodeConstraintEClass.getESuperTypes().add(this.getPSItem());
      psFuzzyConstraintEClass.getESuperTypes().add(this.getPSNodeConstraint());
      psFuzzyMetricConstraintEClass.getESuperTypes().add(this.getPSFuzzyConstraint());
      psFuzzySetRatingConstraintEClass.getESuperTypes().add(this.getPSFuzzyConstraint());
      psBooleanConstraintEClass.getESuperTypes().add(this.getPSNodeConstraint());
      psAttributeConstraintEClass.getESuperTypes().add(this.getPSBooleanConstraint());
      psMetricConstraintEClass.getESuperTypes().add(this.getPSBooleanConstraint());
      psFunctionParameterEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

      // Initialize classes and features; add operations and parameters
      initEClass(psCatalogEClass, PSCatalog.class, "PSCatalog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSCatalog_PatternSpecifications(), this.getPSPatternSpecification(), this.getPSPatternSpecification_Catalog(), "patternSpecifications", null, 0, -1, PSCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSCatalog_PatternSpecifications().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEAttribute(getPSCatalog_Metamodel(), ecorePackage.getEString(), "metamodel", null, 1, 1, PSCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

      initEClass(psPatternSpecificationEClass, PSPatternSpecification.class, "PSPatternSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSPatternSpecification_Catalog(), this.getPSCatalog(), this.getPSCatalog_PatternSpecifications(), "catalog", null, 1, 1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSPatternSpecification_Catalog().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSPatternSpecification_Connections(), this.getPSConnection(), this.getPSConnection_PatternSpecification(), "connections", null, 0, -1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSPatternSpecification_Connections().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSPatternSpecification_Nodes(), this.getPSNode(), this.getPSNode_PatternSpecification(), "nodes", null, 0, -1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSPatternSpecification_Nodes().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSPatternSpecification_CombinedFragments(), this.getPSCombinedFragment(), this.getPSCombinedFragment_PatternSpecification(), "combinedFragments", null, 0, -1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSPatternSpecification_CombinedFragments().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSPatternSpecification_Constraints(), this.getPSSpecificationConstraint(), this.getPSSpecificationConstraint_PatternSpecification(), "constraints", null, 0, -1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSPatternSpecification_Constraints().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSPatternSpecification_SuperPattern(), this.getPSPatternSpecification(), this.getPSPatternSpecification_SubPatterns(), "superPattern", null, 0, 1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSPatternSpecification_SuperPattern().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSPatternSpecification_SubPatterns(), this.getPSPatternSpecification(), this.getPSPatternSpecification_SuperPattern(), "subPatterns", null, 0, -1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSPatternSpecification_SubPatterns().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEAttribute(getPSPatternSpecification_Type(), this.getPatternType(), "type", "", 1, 1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEAttribute(getPSPatternSpecification_Abstract(), ecorePackage.getEBoolean(), "abstract", "false", 1, 1, PSPatternSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

      initEClass(psItemEClass, PSItem.class, "PSItem", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSItem_Weight(), ecorePackage.getEDouble(), "weight", "1", 1, 1, PSItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

      initEClass(psConnectionEClass, PSConnection.class, "PSConnection", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSConnection_Source(), this.getPSNode(), this.getPSNode_Outgoing(), "source", null, 1, 1, PSConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSConnection_Source().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSConnection_Target(), this.getPSNode(), this.getPSNode_Incoming(), "target", null, 1, 1, PSConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSConnection_Target().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSConnection_PatternSpecification(), this.getPSPatternSpecification(), this.getPSPatternSpecification_Connections(), "patternSpecification", null, 1, 1, PSConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSConnection_PatternSpecification().getEKeys().add(theIdentifierPackage.getIdentifier_Id());

      initEClass(psLinkEClass, PSLink.class, "PSLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSLink_Qualifier(), ecorePackage.getEString(), "qualifier", null, 1, 1, PSLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEAttribute(getPSLink_Negative(), ecorePackage.getEBoolean(), "negative", null, 1, 1, PSLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEReference(getPSLink_InstanceOf(), ecorePackage.getEReference(), null, "instanceOf", null, 0, 1, PSLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSLink_InstanceOf().getEKeys().add(ecorePackage.getENamedElement_Name());

      initEClass(psPathEClass, PSPath.class, "PSPath", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSPath_TabooClasses(), ecorePackage.getEClass(), null, "tabooClasses", null, 0, -1, PSPath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSPath_TabooClasses().getEKeys().add(ecorePackage.getENamedElement_Name());

      initEClass(psCombinedFragmentItemEClass, PSCombinedFragmentItem.class, "PSCombinedFragmentItem", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSCombinedFragmentItem_Parents(), this.getPSCombinedFragment(), this.getPSCombinedFragment_Children(), "parents", null, 0, -1, PSCombinedFragmentItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSCombinedFragmentItem_Parents().getEKeys().add(theIdentifierPackage.getIdentifier_Id());

      initEClass(psCombinedFragmentEClass, PSCombinedFragment.class, "PSCombinedFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSCombinedFragment_Children(), this.getPSCombinedFragmentItem(), this.getPSCombinedFragmentItem_Parents(), "children", null, 0, -1, PSCombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSCombinedFragment_Children().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEAttribute(getPSCombinedFragment_Kind(), this.getModifierType(), "kind", "1", 1, 1, PSCombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEReference(getPSCombinedFragment_PatternSpecification(), this.getPSPatternSpecification(), this.getPSPatternSpecification_CombinedFragments(), "patternSpecification", null, 1, 1, PSCombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSCombinedFragment_PatternSpecification().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSCombinedFragment_Constraint(), this.getPSNodeConstraint(), null, "constraint", null, 0, 1, PSCombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSCombinedFragment_Constraint().getEKeys().add(theIdentifierPackage.getIdentifier_Id());

      initEClass(psSpecificationConstraintEClass, PSSpecificationConstraint.class, "PSSpecificationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSSpecificationConstraint_Additional(), ecorePackage.getEBoolean(), "additional", null, 1, 1, PSSpecificationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEReference(getPSSpecificationConstraint_PatternSpecification(), this.getPSPatternSpecification(), this.getPSPatternSpecification_Constraints(), "patternSpecification", null, 1, 1, PSSpecificationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSSpecificationConstraint_PatternSpecification().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEAttribute(getPSSpecificationConstraint_Expression(), ecorePackage.getEString(), "expression", null, 1, 1, PSSpecificationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

      initEClass(psNodeEClass, PSNode.class, "PSNode", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSNode_Trigger(), ecorePackage.getEBoolean(), "trigger", null, 1, 1, PSNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEAttribute(getPSNode_Modifier(), this.getModifierType(), "modifier", null, 1, 1, PSNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEReference(getPSNode_Outgoing(), this.getPSConnection(), this.getPSConnection_Source(), "outgoing", null, 0, -1, PSNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSNode_Outgoing().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSNode_Incoming(), this.getPSConnection(), this.getPSConnection_Target(), "incoming", null, 0, -1, PSNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSNode_Incoming().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSNode_PatternSpecification(), this.getPSPatternSpecification(), this.getPSPatternSpecification_Nodes(), "patternSpecification", null, 1, 1, PSNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSNode_PatternSpecification().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEReference(getPSNode_NodeConstraints(), this.getPSNodeConstraint(), this.getPSNodeConstraint_Node(), "nodeConstraints", null, 0, -1, PSNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSNode_NodeConstraints().getEKeys().add(theIdentifierPackage.getIdentifier_Id());

      initEClass(psObjectEClass, PSObject.class, "PSObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSObject_InstanceOf(), ecorePackage.getEClass(), null, "instanceOf", null, 0, 1, PSObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSObject_InstanceOf().getEKeys().add(ecorePackage.getENamedElement_Name());

      initEClass(psAnnotationEClass, PSAnnotation.class, "PSAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSAnnotation_Type(), this.getPSPatternSpecification(), null, "type", null, 0, 1, PSAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSAnnotation_Type().getEKeys().add(theIdentifierPackage.getIdentifier_Id());

      initEClass(psNodeConstraintEClass, PSNodeConstraint.class, "PSNodeConstraint", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSNodeConstraint_Node(), this.getPSNode(), this.getPSNode_NodeConstraints(), "node", null, 0, 1, PSNodeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSNodeConstraint_Node().getEKeys().add(theIdentifierPackage.getIdentifier_Id());
      initEAttribute(getPSNodeConstraint_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, PSNodeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(psFuzzyConstraintEClass, PSFuzzyConstraint.class, "PSFuzzyConstraint", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSFuzzyConstraint_MathFunctionID(), ecorePackage.getEString(), "mathFunctionID", null, 1, 1, PSFuzzyConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEReference(getPSFuzzyConstraint_Parameters(), this.getPSFunctionParameter(), this.getPSFunctionParameter_Constraint(), "parameters", null, 0, -1, PSFuzzyConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      getPSFuzzyConstraint_Parameters().getEKeys().add(theIdentifierPackage.getIdentifier_Id());

      initEClass(psFuzzyMetricConstraintEClass, PSFuzzyMetricConstraint.class, "PSFuzzyMetricConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSFuzzyMetricConstraint_MetricAcronym(), ecorePackage.getEString(), "metricAcronym", null, 1, 1, PSFuzzyMetricConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

      initEClass(psFuzzySetRatingConstraintEClass, PSFuzzySetRatingConstraint.class, "PSFuzzySetRatingConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(psBooleanConstraintEClass, PSBooleanConstraint.class, "PSBooleanConstraint", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSBooleanConstraint_Additional(), ecorePackage.getEBoolean(), "additional", null, 1, 1, PSBooleanConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEAttribute(getPSBooleanConstraint_ValueExpression(), ecorePackage.getEString(), "valueExpression", null, 1, 1, PSBooleanConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEAttribute(getPSBooleanConstraint_Operator(), this.getOperatorType(), "operator", null, 1, 1, PSBooleanConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

      initEClass(psAttributeConstraintEClass, PSAttributeConstraint.class, "PSAttributeConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPSAttributeConstraint_Attribute(), ecorePackage.getEAttribute(), null, "attribute", null, 0, 1, PSAttributeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSAttributeConstraint_Attribute().getEKeys().add(ecorePackage.getENamedElement_Name());

      initEClass(psMetricConstraintEClass, PSMetricConstraint.class, "PSMetricConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSMetricConstraint_MetricAcronym(), ecorePackage.getEString(), "metricAcronym", null, 1, 1, PSMetricConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

      initEClass(psFunctionParameterEClass, PSFunctionParameter.class, "PSFunctionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPSFunctionParameter_Value(), ecorePackage.getEDouble(), "value", null, 1, 1, PSFunctionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      initEReference(getPSFunctionParameter_Constraint(), this.getPSFuzzyConstraint(), this.getPSFuzzyConstraint_Parameters(), "constraint", null, 1, 1, PSFunctionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
      getPSFunctionParameter_Constraint().getEKeys().add(theIdentifierPackage.getIdentifier_Id());

      // Initialize enums and add enum literals
      initEEnum(patternTypeEEnum, PatternType.class, "PatternType");
      addEEnumLiteral(patternTypeEEnum, PatternType.DESIGN_PATTERN);
      addEEnumLiteral(patternTypeEEnum, PatternType.ANTI_PATTERN);

      initEEnum(modifierTypeEEnum, ModifierType.class, "ModifierType");
      addEEnumLiteral(modifierTypeEEnum, ModifierType.NONE);
      addEEnumLiteral(modifierTypeEEnum, ModifierType.ADDITIONAL);
      addEEnumLiteral(modifierTypeEEnum, ModifierType.NEGATIVE);
      addEEnumLiteral(modifierTypeEEnum, ModifierType.SET);

      initEEnum(operatorTypeEEnum, OperatorType.class, "OperatorType");
      addEEnumLiteral(operatorTypeEEnum, OperatorType.LESS);
      addEEnumLiteral(operatorTypeEEnum, OperatorType.LESS_OR_EQUAL);
      addEEnumLiteral(operatorTypeEEnum, OperatorType.GREATER);
      addEEnumLiteral(operatorTypeEEnum, OperatorType.GREATER_OR_EQUAL);
      addEEnumLiteral(operatorTypeEEnum, OperatorType.EQUAL);
      addEEnumLiteral(operatorTypeEEnum, OperatorType.REGULAR_EXPRESSION);
      addEEnumLiteral(operatorTypeEEnum, OperatorType.UNEQUAL);

      // Create resource
      createResource(eNS_URI);
   }

} //SpecificationPackageImpl
