/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.fujaba.commons.identifier.IdentifierPackage;


/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.specification.SpecificationFactory
 * @model kind="package"
 * @generated
 */
public interface SpecificationPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "specification";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http://www.reclipse.org/ns/specification";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "specification";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   SpecificationPackage eINSTANCE = org.reclipse.structure.specification.impl.SpecificationPackageImpl.init();

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSCatalogImpl <em>PS Catalog</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSCatalogImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSCatalog()
    * @generated
    */
   int PS_CATALOG = 0;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CATALOG__EANNOTATIONS = IdentifierPackage.IDENTIFIER__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CATALOG__ID = IdentifierPackage.IDENTIFIER__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CATALOG__NAME = IdentifierPackage.IDENTIFIER__NAME;

   /**
    * The feature id for the '<em><b>Pattern Specifications</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CATALOG__PATTERN_SPECIFICATIONS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Metamodel</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CATALOG__METAMODEL = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>PS Catalog</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CATALOG_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl <em>PS Pattern Specification</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSPatternSpecificationImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSPatternSpecification()
    * @generated
    */
   int PS_PATTERN_SPECIFICATION = 1;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__EANNOTATIONS = IdentifierPackage.IDENTIFIER__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__ID = IdentifierPackage.IDENTIFIER__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__NAME = IdentifierPackage.IDENTIFIER__NAME;

   /**
    * The feature id for the '<em><b>Catalog</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__CATALOG = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Connections</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__CONNECTIONS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__NODES = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Combined Fragments</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

   /**
    * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__CONSTRAINTS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 4;

   /**
    * The feature id for the '<em><b>Super Pattern</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__SUPER_PATTERN = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 5;

   /**
    * The feature id for the '<em><b>Sub Patterns</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__SUB_PATTERNS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 6;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__TYPE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 7;

   /**
    * The feature id for the '<em><b>Abstract</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION__ABSTRACT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 8;

   /**
    * The number of structural features of the '<em>PS Pattern Specification</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATTERN_SPECIFICATION_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 9;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.PSItem <em>PS Item</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.PSItem
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSItem()
    * @generated
    */
   int PS_ITEM = 2;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ITEM__EANNOTATIONS = IdentifierPackage.IDENTIFIER__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ITEM__ID = IdentifierPackage.IDENTIFIER__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ITEM__NAME = IdentifierPackage.IDENTIFIER__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ITEM__WEIGHT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PS Item</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ITEM_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.PSConnection <em>PS Connection</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.PSConnection
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSConnection()
    * @generated
    */
   int PS_CONNECTION = 3;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CONNECTION__EANNOTATIONS = PS_ITEM__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CONNECTION__ID = PS_ITEM__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CONNECTION__NAME = PS_ITEM__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CONNECTION__WEIGHT = PS_ITEM__WEIGHT;

   /**
    * The feature id for the '<em><b>Source</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CONNECTION__SOURCE = PS_ITEM_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CONNECTION__TARGET = PS_ITEM_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Pattern Specification</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CONNECTION__PATTERN_SPECIFICATION = PS_ITEM_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>PS Connection</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_CONNECTION_FEATURE_COUNT = PS_ITEM_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSLinkImpl <em>PS Link</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSLinkImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSLink()
    * @generated
    */
   int PS_LINK = 4;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__EANNOTATIONS = PS_CONNECTION__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__ID = PS_CONNECTION__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__NAME = PS_CONNECTION__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__WEIGHT = PS_CONNECTION__WEIGHT;

   /**
    * The feature id for the '<em><b>Source</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__SOURCE = PS_CONNECTION__SOURCE;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__TARGET = PS_CONNECTION__TARGET;

   /**
    * The feature id for the '<em><b>Pattern Specification</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__PATTERN_SPECIFICATION = PS_CONNECTION__PATTERN_SPECIFICATION;

   /**
    * The feature id for the '<em><b>Qualifier</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__QUALIFIER = PS_CONNECTION_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Negative</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__NEGATIVE = PS_CONNECTION_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Instance Of</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK__INSTANCE_OF = PS_CONNECTION_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>PS Link</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_LINK_FEATURE_COUNT = PS_CONNECTION_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSPathImpl <em>PS Path</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSPathImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSPath()
    * @generated
    */
   int PS_PATH = 5;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH__EANNOTATIONS = PS_CONNECTION__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH__ID = PS_CONNECTION__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH__NAME = PS_CONNECTION__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH__WEIGHT = PS_CONNECTION__WEIGHT;

   /**
    * The feature id for the '<em><b>Source</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH__SOURCE = PS_CONNECTION__SOURCE;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH__TARGET = PS_CONNECTION__TARGET;

   /**
    * The feature id for the '<em><b>Pattern Specification</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH__PATTERN_SPECIFICATION = PS_CONNECTION__PATTERN_SPECIFICATION;

   /**
    * The feature id for the '<em><b>Taboo Classes</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH__TABOO_CLASSES = PS_CONNECTION_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PS Path</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_PATH_FEATURE_COUNT = PS_CONNECTION_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.PSCombinedFragmentItem <em>PS Combined Fragment Item</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.PSCombinedFragmentItem
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSCombinedFragmentItem()
    * @generated
    */
   int PS_COMBINED_FRAGMENT_ITEM = 6;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT_ITEM__EANNOTATIONS = PS_ITEM__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT_ITEM__ID = PS_ITEM__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT_ITEM__NAME = PS_ITEM__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT_ITEM__WEIGHT = PS_ITEM__WEIGHT;

   /**
    * The feature id for the '<em><b>Parents</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT_ITEM__PARENTS = PS_ITEM_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PS Combined Fragment Item</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT = PS_ITEM_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSCombinedFragmentImpl <em>PS Combined Fragment</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSCombinedFragmentImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSCombinedFragment()
    * @generated
    */
   int PS_COMBINED_FRAGMENT = 7;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__EANNOTATIONS = PS_COMBINED_FRAGMENT_ITEM__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__ID = PS_COMBINED_FRAGMENT_ITEM__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__NAME = PS_COMBINED_FRAGMENT_ITEM__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__WEIGHT = PS_COMBINED_FRAGMENT_ITEM__WEIGHT;

   /**
    * The feature id for the '<em><b>Parents</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__PARENTS = PS_COMBINED_FRAGMENT_ITEM__PARENTS;

   /**
    * The feature id for the '<em><b>Children</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__CHILDREN = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Kind</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__KIND = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Pattern Specification</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Constraint</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT__CONSTRAINT = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>PS Combined Fragment</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_COMBINED_FRAGMENT_FEATURE_COUNT = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 4;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl <em>PS Specification Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSSpecificationConstraint()
    * @generated
    */
   int PS_SPECIFICATION_CONSTRAINT = 8;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT__EANNOTATIONS = PS_COMBINED_FRAGMENT_ITEM__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT__ID = PS_COMBINED_FRAGMENT_ITEM__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT__NAME = PS_COMBINED_FRAGMENT_ITEM__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT__WEIGHT = PS_COMBINED_FRAGMENT_ITEM__WEIGHT;

   /**
    * The feature id for the '<em><b>Parents</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT__PARENTS = PS_COMBINED_FRAGMENT_ITEM__PARENTS;

   /**
    * The feature id for the '<em><b>Additional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT__ADDITIONAL = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Pattern Specification</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT__EXPRESSION = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>PS Specification Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_SPECIFICATION_CONSTRAINT_FEATURE_COUNT = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.PSNode <em>PS Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.PSNode
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSNode()
    * @generated
    */
   int PS_NODE = 9;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__EANNOTATIONS = PS_COMBINED_FRAGMENT_ITEM__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__ID = PS_COMBINED_FRAGMENT_ITEM__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__NAME = PS_COMBINED_FRAGMENT_ITEM__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__WEIGHT = PS_COMBINED_FRAGMENT_ITEM__WEIGHT;

   /**
    * The feature id for the '<em><b>Parents</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__PARENTS = PS_COMBINED_FRAGMENT_ITEM__PARENTS;

   /**
    * The feature id for the '<em><b>Trigger</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__TRIGGER = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Modifier</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__MODIFIER = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Outgoing</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__OUTGOING = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Incoming</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__INCOMING = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 3;

   /**
    * The feature id for the '<em><b>Pattern Specification</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__PATTERN_SPECIFICATION = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 4;

   /**
    * The feature id for the '<em><b>Node Constraints</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE__NODE_CONSTRAINTS = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 5;

   /**
    * The number of structural features of the '<em>PS Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE_FEATURE_COUNT = PS_COMBINED_FRAGMENT_ITEM_FEATURE_COUNT + 6;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSObjectImpl <em>PS Object</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSObjectImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSObject()
    * @generated
    */
   int PS_OBJECT = 10;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__EANNOTATIONS = PS_NODE__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__ID = PS_NODE__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__NAME = PS_NODE__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__WEIGHT = PS_NODE__WEIGHT;

   /**
    * The feature id for the '<em><b>Parents</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__PARENTS = PS_NODE__PARENTS;

   /**
    * The feature id for the '<em><b>Trigger</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__TRIGGER = PS_NODE__TRIGGER;

   /**
    * The feature id for the '<em><b>Modifier</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__MODIFIER = PS_NODE__MODIFIER;

   /**
    * The feature id for the '<em><b>Outgoing</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__OUTGOING = PS_NODE__OUTGOING;

   /**
    * The feature id for the '<em><b>Incoming</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__INCOMING = PS_NODE__INCOMING;

   /**
    * The feature id for the '<em><b>Pattern Specification</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__PATTERN_SPECIFICATION = PS_NODE__PATTERN_SPECIFICATION;

   /**
    * The feature id for the '<em><b>Node Constraints</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__NODE_CONSTRAINTS = PS_NODE__NODE_CONSTRAINTS;

   /**
    * The feature id for the '<em><b>Instance Of</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT__INSTANCE_OF = PS_NODE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PS Object</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_OBJECT_FEATURE_COUNT = PS_NODE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSAnnotationImpl <em>PS Annotation</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSAnnotationImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSAnnotation()
    * @generated
    */
   int PS_ANNOTATION = 11;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__EANNOTATIONS = PS_NODE__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__ID = PS_NODE__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__NAME = PS_NODE__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__WEIGHT = PS_NODE__WEIGHT;

   /**
    * The feature id for the '<em><b>Parents</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__PARENTS = PS_NODE__PARENTS;

   /**
    * The feature id for the '<em><b>Trigger</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__TRIGGER = PS_NODE__TRIGGER;

   /**
    * The feature id for the '<em><b>Modifier</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__MODIFIER = PS_NODE__MODIFIER;

   /**
    * The feature id for the '<em><b>Outgoing</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__OUTGOING = PS_NODE__OUTGOING;

   /**
    * The feature id for the '<em><b>Incoming</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__INCOMING = PS_NODE__INCOMING;

   /**
    * The feature id for the '<em><b>Pattern Specification</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__PATTERN_SPECIFICATION = PS_NODE__PATTERN_SPECIFICATION;

   /**
    * The feature id for the '<em><b>Node Constraints</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__NODE_CONSTRAINTS = PS_NODE__NODE_CONSTRAINTS;

   /**
    * The feature id for the '<em><b>Type</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION__TYPE = PS_NODE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PS Annotation</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ANNOTATION_FEATURE_COUNT = PS_NODE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.PSNodeConstraint <em>PS Node Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.PSNodeConstraint
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSNodeConstraint()
    * @generated
    */
   int PS_NODE_CONSTRAINT = 12;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE_CONSTRAINT__EANNOTATIONS = PS_ITEM__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE_CONSTRAINT__ID = PS_ITEM__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE_CONSTRAINT__NAME = PS_ITEM__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE_CONSTRAINT__WEIGHT = PS_ITEM__WEIGHT;

   /**
    * The feature id for the '<em><b>Node</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE_CONSTRAINT__NODE = PS_ITEM_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE_CONSTRAINT__EXPRESSION = PS_ITEM_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>PS Node Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_NODE_CONSTRAINT_FEATURE_COUNT = PS_ITEM_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.PSFuzzyConstraint <em>PS Fuzzy Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.PSFuzzyConstraint
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSFuzzyConstraint()
    * @generated
    */
   int PS_FUZZY_CONSTRAINT = 13;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT__EANNOTATIONS = PS_NODE_CONSTRAINT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT__ID = PS_NODE_CONSTRAINT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT__NAME = PS_NODE_CONSTRAINT__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT__WEIGHT = PS_NODE_CONSTRAINT__WEIGHT;

   /**
    * The feature id for the '<em><b>Node</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT__NODE = PS_NODE_CONSTRAINT__NODE;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT__EXPRESSION = PS_NODE_CONSTRAINT__EXPRESSION;

   /**
    * The feature id for the '<em><b>Math Function ID</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT__MATH_FUNCTION_ID = PS_NODE_CONSTRAINT_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT__PARAMETERS = PS_NODE_CONSTRAINT_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>PS Fuzzy Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_CONSTRAINT_FEATURE_COUNT = PS_NODE_CONSTRAINT_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSFuzzyMetricConstraintImpl <em>PS Fuzzy Metric Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSFuzzyMetricConstraintImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSFuzzyMetricConstraint()
    * @generated
    */
   int PS_FUZZY_METRIC_CONSTRAINT = 14;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__EANNOTATIONS = PS_FUZZY_CONSTRAINT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__ID = PS_FUZZY_CONSTRAINT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__NAME = PS_FUZZY_CONSTRAINT__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__WEIGHT = PS_FUZZY_CONSTRAINT__WEIGHT;

   /**
    * The feature id for the '<em><b>Node</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__NODE = PS_FUZZY_CONSTRAINT__NODE;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__EXPRESSION = PS_FUZZY_CONSTRAINT__EXPRESSION;

   /**
    * The feature id for the '<em><b>Math Function ID</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__MATH_FUNCTION_ID = PS_FUZZY_CONSTRAINT__MATH_FUNCTION_ID;

   /**
    * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__PARAMETERS = PS_FUZZY_CONSTRAINT__PARAMETERS;

   /**
    * The feature id for the '<em><b>Metric Acronym</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT__METRIC_ACRONYM = PS_FUZZY_CONSTRAINT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PS Fuzzy Metric Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_METRIC_CONSTRAINT_FEATURE_COUNT = PS_FUZZY_CONSTRAINT_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl <em>PS Fuzzy Set Rating Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSFuzzySetRatingConstraint()
    * @generated
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT = 15;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT__EANNOTATIONS = PS_FUZZY_CONSTRAINT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT__ID = PS_FUZZY_CONSTRAINT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT__NAME = PS_FUZZY_CONSTRAINT__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT__WEIGHT = PS_FUZZY_CONSTRAINT__WEIGHT;

   /**
    * The feature id for the '<em><b>Node</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT__NODE = PS_FUZZY_CONSTRAINT__NODE;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT__EXPRESSION = PS_FUZZY_CONSTRAINT__EXPRESSION;

   /**
    * The feature id for the '<em><b>Math Function ID</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT__MATH_FUNCTION_ID = PS_FUZZY_CONSTRAINT__MATH_FUNCTION_ID;

   /**
    * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS = PS_FUZZY_CONSTRAINT__PARAMETERS;

   /**
    * The number of structural features of the '<em>PS Fuzzy Set Rating Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUZZY_SET_RATING_CONSTRAINT_FEATURE_COUNT = PS_FUZZY_CONSTRAINT_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.PSBooleanConstraint <em>PS Boolean Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.PSBooleanConstraint
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSBooleanConstraint()
    * @generated
    */
   int PS_BOOLEAN_CONSTRAINT = 16;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__EANNOTATIONS = PS_NODE_CONSTRAINT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__ID = PS_NODE_CONSTRAINT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__NAME = PS_NODE_CONSTRAINT__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__WEIGHT = PS_NODE_CONSTRAINT__WEIGHT;

   /**
    * The feature id for the '<em><b>Node</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__NODE = PS_NODE_CONSTRAINT__NODE;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__EXPRESSION = PS_NODE_CONSTRAINT__EXPRESSION;

   /**
    * The feature id for the '<em><b>Additional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__ADDITIONAL = PS_NODE_CONSTRAINT_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Value Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION = PS_NODE_CONSTRAINT_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Operator</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT__OPERATOR = PS_NODE_CONSTRAINT_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>PS Boolean Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_BOOLEAN_CONSTRAINT_FEATURE_COUNT = PS_NODE_CONSTRAINT_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl <em>PS Attribute Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSAttributeConstraintImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSAttributeConstraint()
    * @generated
    */
   int PS_ATTRIBUTE_CONSTRAINT = 17;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__EANNOTATIONS = PS_BOOLEAN_CONSTRAINT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__ID = PS_BOOLEAN_CONSTRAINT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__NAME = PS_BOOLEAN_CONSTRAINT__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__WEIGHT = PS_BOOLEAN_CONSTRAINT__WEIGHT;

   /**
    * The feature id for the '<em><b>Node</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__NODE = PS_BOOLEAN_CONSTRAINT__NODE;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__EXPRESSION = PS_BOOLEAN_CONSTRAINT__EXPRESSION;

   /**
    * The feature id for the '<em><b>Additional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__ADDITIONAL = PS_BOOLEAN_CONSTRAINT__ADDITIONAL;

   /**
    * The feature id for the '<em><b>Value Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__VALUE_EXPRESSION = PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION;

   /**
    * The feature id for the '<em><b>Operator</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__OPERATOR = PS_BOOLEAN_CONSTRAINT__OPERATOR;

   /**
    * The feature id for the '<em><b>Attribute</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE = PS_BOOLEAN_CONSTRAINT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PS Attribute Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_ATTRIBUTE_CONSTRAINT_FEATURE_COUNT = PS_BOOLEAN_CONSTRAINT_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSMetricConstraintImpl <em>PS Metric Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSMetricConstraintImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSMetricConstraint()
    * @generated
    */
   int PS_METRIC_CONSTRAINT = 18;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__EANNOTATIONS = PS_BOOLEAN_CONSTRAINT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__ID = PS_BOOLEAN_CONSTRAINT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__NAME = PS_BOOLEAN_CONSTRAINT__NAME;

   /**
    * The feature id for the '<em><b>Weight</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__WEIGHT = PS_BOOLEAN_CONSTRAINT__WEIGHT;

   /**
    * The feature id for the '<em><b>Node</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__NODE = PS_BOOLEAN_CONSTRAINT__NODE;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__EXPRESSION = PS_BOOLEAN_CONSTRAINT__EXPRESSION;

   /**
    * The feature id for the '<em><b>Additional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__ADDITIONAL = PS_BOOLEAN_CONSTRAINT__ADDITIONAL;

   /**
    * The feature id for the '<em><b>Value Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__VALUE_EXPRESSION = PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION;

   /**
    * The feature id for the '<em><b>Operator</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__OPERATOR = PS_BOOLEAN_CONSTRAINT__OPERATOR;

   /**
    * The feature id for the '<em><b>Metric Acronym</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT__METRIC_ACRONYM = PS_BOOLEAN_CONSTRAINT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PS Metric Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_METRIC_CONSTRAINT_FEATURE_COUNT = PS_BOOLEAN_CONSTRAINT_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.impl.PSFunctionParameterImpl <em>PS Function Parameter</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.impl.PSFunctionParameterImpl
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSFunctionParameter()
    * @generated
    */
   int PS_FUNCTION_PARAMETER = 19;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUNCTION_PARAMETER__EANNOTATIONS = IdentifierPackage.IDENTIFIER__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUNCTION_PARAMETER__ID = IdentifierPackage.IDENTIFIER__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUNCTION_PARAMETER__NAME = IdentifierPackage.IDENTIFIER__NAME;

   /**
    * The feature id for the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUNCTION_PARAMETER__VALUE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Constraint</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUNCTION_PARAMETER__CONSTRAINT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>PS Function Parameter</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PS_FUNCTION_PARAMETER_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.PatternType <em>Pattern Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.PatternType
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPatternType()
    * @generated
    */
   int PATTERN_TYPE = 20;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.ModifierType <em>Modifier Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.ModifierType
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getModifierType()
    * @generated
    */
   int MODIFIER_TYPE = 21;

   /**
    * The meta object id for the '{@link org.reclipse.structure.specification.OperatorType <em>Operator Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.specification.OperatorType
    * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getOperatorType()
    * @generated
    */
   int OPERATOR_TYPE = 22;


   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSCatalog <em>PS Catalog</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Catalog</em>'.
    * @see org.reclipse.structure.specification.PSCatalog
    * @generated
    */
   EClass getPSCatalog();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.specification.PSCatalog#getPatternSpecifications <em>Pattern Specifications</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Pattern Specifications</em>'.
    * @see org.reclipse.structure.specification.PSCatalog#getPatternSpecifications()
    * @see #getPSCatalog()
    * @generated
    */
   EReference getPSCatalog_PatternSpecifications();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSCatalog#getMetamodel <em>Metamodel</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Metamodel</em>'.
    * @see org.reclipse.structure.specification.PSCatalog#getMetamodel()
    * @see #getPSCatalog()
    * @generated
    */
   EAttribute getPSCatalog_Metamodel();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSPatternSpecification <em>PS Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Pattern Specification</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification
    * @generated
    */
   EClass getPSPatternSpecification();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.specification.PSPatternSpecification#getCatalog <em>Catalog</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Catalog</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#getCatalog()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EReference getPSPatternSpecification_Catalog();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.specification.PSPatternSpecification#getConnections <em>Connections</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Connections</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#getConnections()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EReference getPSPatternSpecification_Connections();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.specification.PSPatternSpecification#getNodes <em>Nodes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Nodes</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#getNodes()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EReference getPSPatternSpecification_Nodes();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.specification.PSPatternSpecification#getCombinedFragments <em>Combined Fragments</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Combined Fragments</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#getCombinedFragments()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EReference getPSPatternSpecification_CombinedFragments();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.specification.PSPatternSpecification#getConstraints <em>Constraints</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Constraints</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#getConstraints()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EReference getPSPatternSpecification_Constraints();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.specification.PSPatternSpecification#getSuperPattern <em>Super Pattern</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Super Pattern</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#getSuperPattern()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EReference getPSPatternSpecification_SuperPattern();

   /**
    * Returns the meta object for the reference list '{@link org.reclipse.structure.specification.PSPatternSpecification#getSubPatterns <em>Sub Patterns</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Sub Patterns</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#getSubPatterns()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EReference getPSPatternSpecification_SubPatterns();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSPatternSpecification#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Type</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#getType()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EAttribute getPSPatternSpecification_Type();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSPatternSpecification#isAbstract <em>Abstract</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Abstract</em>'.
    * @see org.reclipse.structure.specification.PSPatternSpecification#isAbstract()
    * @see #getPSPatternSpecification()
    * @generated
    */
   EAttribute getPSPatternSpecification_Abstract();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSItem <em>PS Item</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Item</em>'.
    * @see org.reclipse.structure.specification.PSItem
    * @generated
    */
   EClass getPSItem();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSItem#getWeight <em>Weight</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Weight</em>'.
    * @see org.reclipse.structure.specification.PSItem#getWeight()
    * @see #getPSItem()
    * @generated
    */
   EAttribute getPSItem_Weight();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSConnection <em>PS Connection</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Connection</em>'.
    * @see org.reclipse.structure.specification.PSConnection
    * @generated
    */
   EClass getPSConnection();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.specification.PSConnection#getSource <em>Source</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Source</em>'.
    * @see org.reclipse.structure.specification.PSConnection#getSource()
    * @see #getPSConnection()
    * @generated
    */
   EReference getPSConnection_Source();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.specification.PSConnection#getTarget <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Target</em>'.
    * @see org.reclipse.structure.specification.PSConnection#getTarget()
    * @see #getPSConnection()
    * @generated
    */
   EReference getPSConnection_Target();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.specification.PSConnection#getPatternSpecification <em>Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Pattern Specification</em>'.
    * @see org.reclipse.structure.specification.PSConnection#getPatternSpecification()
    * @see #getPSConnection()
    * @generated
    */
   EReference getPSConnection_PatternSpecification();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSLink <em>PS Link</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Link</em>'.
    * @see org.reclipse.structure.specification.PSLink
    * @generated
    */
   EClass getPSLink();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSLink#getQualifier <em>Qualifier</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Qualifier</em>'.
    * @see org.reclipse.structure.specification.PSLink#getQualifier()
    * @see #getPSLink()
    * @generated
    */
   EAttribute getPSLink_Qualifier();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSLink#isNegative <em>Negative</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Negative</em>'.
    * @see org.reclipse.structure.specification.PSLink#isNegative()
    * @see #getPSLink()
    * @generated
    */
   EAttribute getPSLink_Negative();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.specification.PSLink#getInstanceOf <em>Instance Of</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Instance Of</em>'.
    * @see org.reclipse.structure.specification.PSLink#getInstanceOf()
    * @see #getPSLink()
    * @generated
    */
   EReference getPSLink_InstanceOf();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSPath <em>PS Path</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Path</em>'.
    * @see org.reclipse.structure.specification.PSPath
    * @generated
    */
   EClass getPSPath();

   /**
    * Returns the meta object for the reference list '{@link org.reclipse.structure.specification.PSPath#getTabooClasses <em>Taboo Classes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Taboo Classes</em>'.
    * @see org.reclipse.structure.specification.PSPath#getTabooClasses()
    * @see #getPSPath()
    * @generated
    */
   EReference getPSPath_TabooClasses();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSCombinedFragmentItem <em>PS Combined Fragment Item</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Combined Fragment Item</em>'.
    * @see org.reclipse.structure.specification.PSCombinedFragmentItem
    * @generated
    */
   EClass getPSCombinedFragmentItem();

   /**
    * Returns the meta object for the reference list '{@link org.reclipse.structure.specification.PSCombinedFragmentItem#getParents <em>Parents</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Parents</em>'.
    * @see org.reclipse.structure.specification.PSCombinedFragmentItem#getParents()
    * @see #getPSCombinedFragmentItem()
    * @generated
    */
   EReference getPSCombinedFragmentItem_Parents();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSCombinedFragment <em>PS Combined Fragment</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Combined Fragment</em>'.
    * @see org.reclipse.structure.specification.PSCombinedFragment
    * @generated
    */
   EClass getPSCombinedFragment();

   /**
    * Returns the meta object for the reference list '{@link org.reclipse.structure.specification.PSCombinedFragment#getChildren <em>Children</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Children</em>'.
    * @see org.reclipse.structure.specification.PSCombinedFragment#getChildren()
    * @see #getPSCombinedFragment()
    * @generated
    */
   EReference getPSCombinedFragment_Children();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSCombinedFragment#getKind <em>Kind</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Kind</em>'.
    * @see org.reclipse.structure.specification.PSCombinedFragment#getKind()
    * @see #getPSCombinedFragment()
    * @generated
    */
   EAttribute getPSCombinedFragment_Kind();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.specification.PSCombinedFragment#getPatternSpecification <em>Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Pattern Specification</em>'.
    * @see org.reclipse.structure.specification.PSCombinedFragment#getPatternSpecification()
    * @see #getPSCombinedFragment()
    * @generated
    */
   EReference getPSCombinedFragment_PatternSpecification();

   /**
    * Returns the meta object for the containment reference '{@link org.reclipse.structure.specification.PSCombinedFragment#getConstraint <em>Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Constraint</em>'.
    * @see org.reclipse.structure.specification.PSCombinedFragment#getConstraint()
    * @see #getPSCombinedFragment()
    * @generated
    */
   EReference getPSCombinedFragment_Constraint();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSSpecificationConstraint <em>PS Specification Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Specification Constraint</em>'.
    * @see org.reclipse.structure.specification.PSSpecificationConstraint
    * @generated
    */
   EClass getPSSpecificationConstraint();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSSpecificationConstraint#isAdditional <em>Additional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Additional</em>'.
    * @see org.reclipse.structure.specification.PSSpecificationConstraint#isAdditional()
    * @see #getPSSpecificationConstraint()
    * @generated
    */
   EAttribute getPSSpecificationConstraint_Additional();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.specification.PSSpecificationConstraint#getPatternSpecification <em>Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Pattern Specification</em>'.
    * @see org.reclipse.structure.specification.PSSpecificationConstraint#getPatternSpecification()
    * @see #getPSSpecificationConstraint()
    * @generated
    */
   EReference getPSSpecificationConstraint_PatternSpecification();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSSpecificationConstraint#getExpression <em>Expression</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Expression</em>'.
    * @see org.reclipse.structure.specification.PSSpecificationConstraint#getExpression()
    * @see #getPSSpecificationConstraint()
    * @generated
    */
   EAttribute getPSSpecificationConstraint_Expression();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSNode <em>PS Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Node</em>'.
    * @see org.reclipse.structure.specification.PSNode
    * @generated
    */
   EClass getPSNode();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSNode#isTrigger <em>Trigger</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Trigger</em>'.
    * @see org.reclipse.structure.specification.PSNode#isTrigger()
    * @see #getPSNode()
    * @generated
    */
   EAttribute getPSNode_Trigger();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSNode#getModifier <em>Modifier</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Modifier</em>'.
    * @see org.reclipse.structure.specification.PSNode#getModifier()
    * @see #getPSNode()
    * @generated
    */
   EAttribute getPSNode_Modifier();

   /**
    * Returns the meta object for the reference list '{@link org.reclipse.structure.specification.PSNode#getOutgoing <em>Outgoing</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Outgoing</em>'.
    * @see org.reclipse.structure.specification.PSNode#getOutgoing()
    * @see #getPSNode()
    * @generated
    */
   EReference getPSNode_Outgoing();

   /**
    * Returns the meta object for the reference list '{@link org.reclipse.structure.specification.PSNode#getIncoming <em>Incoming</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Incoming</em>'.
    * @see org.reclipse.structure.specification.PSNode#getIncoming()
    * @see #getPSNode()
    * @generated
    */
   EReference getPSNode_Incoming();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.specification.PSNode#getPatternSpecification <em>Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Pattern Specification</em>'.
    * @see org.reclipse.structure.specification.PSNode#getPatternSpecification()
    * @see #getPSNode()
    * @generated
    */
   EReference getPSNode_PatternSpecification();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.specification.PSNode#getNodeConstraints <em>Node Constraints</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Node Constraints</em>'.
    * @see org.reclipse.structure.specification.PSNode#getNodeConstraints()
    * @see #getPSNode()
    * @generated
    */
   EReference getPSNode_NodeConstraints();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSObject <em>PS Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Object</em>'.
    * @see org.reclipse.structure.specification.PSObject
    * @generated
    */
   EClass getPSObject();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.specification.PSObject#getInstanceOf <em>Instance Of</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Instance Of</em>'.
    * @see org.reclipse.structure.specification.PSObject#getInstanceOf()
    * @see #getPSObject()
    * @generated
    */
   EReference getPSObject_InstanceOf();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSAnnotation <em>PS Annotation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Annotation</em>'.
    * @see org.reclipse.structure.specification.PSAnnotation
    * @generated
    */
   EClass getPSAnnotation();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.specification.PSAnnotation#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Type</em>'.
    * @see org.reclipse.structure.specification.PSAnnotation#getType()
    * @see #getPSAnnotation()
    * @generated
    */
   EReference getPSAnnotation_Type();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSNodeConstraint <em>PS Node Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Node Constraint</em>'.
    * @see org.reclipse.structure.specification.PSNodeConstraint
    * @generated
    */
   EClass getPSNodeConstraint();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.specification.PSNodeConstraint#getNode <em>Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Node</em>'.
    * @see org.reclipse.structure.specification.PSNodeConstraint#getNode()
    * @see #getPSNodeConstraint()
    * @generated
    */
   EReference getPSNodeConstraint_Node();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSNodeConstraint#getExpression <em>Expression</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Expression</em>'.
    * @see org.reclipse.structure.specification.PSNodeConstraint#getExpression()
    * @see #getPSNodeConstraint()
    * @generated
    */
   EAttribute getPSNodeConstraint_Expression();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSFuzzyConstraint <em>PS Fuzzy Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Fuzzy Constraint</em>'.
    * @see org.reclipse.structure.specification.PSFuzzyConstraint
    * @generated
    */
   EClass getPSFuzzyConstraint();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSFuzzyConstraint#getMathFunctionID <em>Math Function ID</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Math Function ID</em>'.
    * @see org.reclipse.structure.specification.PSFuzzyConstraint#getMathFunctionID()
    * @see #getPSFuzzyConstraint()
    * @generated
    */
   EAttribute getPSFuzzyConstraint_MathFunctionID();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.specification.PSFuzzyConstraint#getParameters <em>Parameters</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Parameters</em>'.
    * @see org.reclipse.structure.specification.PSFuzzyConstraint#getParameters()
    * @see #getPSFuzzyConstraint()
    * @generated
    */
   EReference getPSFuzzyConstraint_Parameters();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSFuzzyMetricConstraint <em>PS Fuzzy Metric Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Fuzzy Metric Constraint</em>'.
    * @see org.reclipse.structure.specification.PSFuzzyMetricConstraint
    * @generated
    */
   EClass getPSFuzzyMetricConstraint();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSFuzzyMetricConstraint#getMetricAcronym <em>Metric Acronym</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Metric Acronym</em>'.
    * @see org.reclipse.structure.specification.PSFuzzyMetricConstraint#getMetricAcronym()
    * @see #getPSFuzzyMetricConstraint()
    * @generated
    */
   EAttribute getPSFuzzyMetricConstraint_MetricAcronym();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSFuzzySetRatingConstraint <em>PS Fuzzy Set Rating Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Fuzzy Set Rating Constraint</em>'.
    * @see org.reclipse.structure.specification.PSFuzzySetRatingConstraint
    * @generated
    */
   EClass getPSFuzzySetRatingConstraint();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSBooleanConstraint <em>PS Boolean Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Boolean Constraint</em>'.
    * @see org.reclipse.structure.specification.PSBooleanConstraint
    * @generated
    */
   EClass getPSBooleanConstraint();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSBooleanConstraint#isAdditional <em>Additional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Additional</em>'.
    * @see org.reclipse.structure.specification.PSBooleanConstraint#isAdditional()
    * @see #getPSBooleanConstraint()
    * @generated
    */
   EAttribute getPSBooleanConstraint_Additional();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSBooleanConstraint#getValueExpression <em>Value Expression</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Value Expression</em>'.
    * @see org.reclipse.structure.specification.PSBooleanConstraint#getValueExpression()
    * @see #getPSBooleanConstraint()
    * @generated
    */
   EAttribute getPSBooleanConstraint_ValueExpression();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSBooleanConstraint#getOperator <em>Operator</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Operator</em>'.
    * @see org.reclipse.structure.specification.PSBooleanConstraint#getOperator()
    * @see #getPSBooleanConstraint()
    * @generated
    */
   EAttribute getPSBooleanConstraint_Operator();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSAttributeConstraint <em>PS Attribute Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Attribute Constraint</em>'.
    * @see org.reclipse.structure.specification.PSAttributeConstraint
    * @generated
    */
   EClass getPSAttributeConstraint();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.specification.PSAttributeConstraint#getAttribute <em>Attribute</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Attribute</em>'.
    * @see org.reclipse.structure.specification.PSAttributeConstraint#getAttribute()
    * @see #getPSAttributeConstraint()
    * @generated
    */
   EReference getPSAttributeConstraint_Attribute();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSMetricConstraint <em>PS Metric Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Metric Constraint</em>'.
    * @see org.reclipse.structure.specification.PSMetricConstraint
    * @generated
    */
   EClass getPSMetricConstraint();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSMetricConstraint#getMetricAcronym <em>Metric Acronym</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Metric Acronym</em>'.
    * @see org.reclipse.structure.specification.PSMetricConstraint#getMetricAcronym()
    * @see #getPSMetricConstraint()
    * @generated
    */
   EAttribute getPSMetricConstraint_MetricAcronym();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.specification.PSFunctionParameter <em>PS Function Parameter</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PS Function Parameter</em>'.
    * @see org.reclipse.structure.specification.PSFunctionParameter
    * @generated
    */
   EClass getPSFunctionParameter();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.specification.PSFunctionParameter#getValue <em>Value</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Value</em>'.
    * @see org.reclipse.structure.specification.PSFunctionParameter#getValue()
    * @see #getPSFunctionParameter()
    * @generated
    */
   EAttribute getPSFunctionParameter_Value();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.specification.PSFunctionParameter#getConstraint <em>Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Constraint</em>'.
    * @see org.reclipse.structure.specification.PSFunctionParameter#getConstraint()
    * @see #getPSFunctionParameter()
    * @generated
    */
   EReference getPSFunctionParameter_Constraint();

   /**
    * Returns the meta object for enum '{@link org.reclipse.structure.specification.PatternType <em>Pattern Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Pattern Type</em>'.
    * @see org.reclipse.structure.specification.PatternType
    * @generated
    */
   EEnum getPatternType();

   /**
    * Returns the meta object for enum '{@link org.reclipse.structure.specification.ModifierType <em>Modifier Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Modifier Type</em>'.
    * @see org.reclipse.structure.specification.ModifierType
    * @generated
    */
   EEnum getModifierType();

   /**
    * Returns the meta object for enum '{@link org.reclipse.structure.specification.OperatorType <em>Operator Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Operator Type</em>'.
    * @see org.reclipse.structure.specification.OperatorType
    * @generated
    */
   EEnum getOperatorType();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   SpecificationFactory getSpecificationFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    *   <li>each class,</li>
    *   <li>each feature of each class,</li>
    *   <li>each enum,</li>
    *   <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * @generated
    */
   interface Literals
   {
      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSCatalogImpl <em>PS Catalog</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSCatalogImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSCatalog()
       * @generated
       */
      EClass PS_CATALOG = eINSTANCE.getPSCatalog();

      /**
       * The meta object literal for the '<em><b>Pattern Specifications</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_CATALOG__PATTERN_SPECIFICATIONS = eINSTANCE.getPSCatalog_PatternSpecifications();

      /**
       * The meta object literal for the '<em><b>Metamodel</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_CATALOG__METAMODEL = eINSTANCE.getPSCatalog_Metamodel();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSPatternSpecificationImpl <em>PS Pattern Specification</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSPatternSpecificationImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSPatternSpecification()
       * @generated
       */
      EClass PS_PATTERN_SPECIFICATION = eINSTANCE.getPSPatternSpecification();

      /**
       * The meta object literal for the '<em><b>Catalog</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_PATTERN_SPECIFICATION__CATALOG = eINSTANCE.getPSPatternSpecification_Catalog();

      /**
       * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_PATTERN_SPECIFICATION__CONNECTIONS = eINSTANCE.getPSPatternSpecification_Connections();

      /**
       * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_PATTERN_SPECIFICATION__NODES = eINSTANCE.getPSPatternSpecification_Nodes();

      /**
       * The meta object literal for the '<em><b>Combined Fragments</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS = eINSTANCE.getPSPatternSpecification_CombinedFragments();

      /**
       * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_PATTERN_SPECIFICATION__CONSTRAINTS = eINSTANCE.getPSPatternSpecification_Constraints();

      /**
       * The meta object literal for the '<em><b>Super Pattern</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_PATTERN_SPECIFICATION__SUPER_PATTERN = eINSTANCE.getPSPatternSpecification_SuperPattern();

      /**
       * The meta object literal for the '<em><b>Sub Patterns</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_PATTERN_SPECIFICATION__SUB_PATTERNS = eINSTANCE.getPSPatternSpecification_SubPatterns();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_PATTERN_SPECIFICATION__TYPE = eINSTANCE.getPSPatternSpecification_Type();

      /**
       * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_PATTERN_SPECIFICATION__ABSTRACT = eINSTANCE.getPSPatternSpecification_Abstract();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.PSItem <em>PS Item</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.PSItem
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSItem()
       * @generated
       */
      EClass PS_ITEM = eINSTANCE.getPSItem();

      /**
       * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_ITEM__WEIGHT = eINSTANCE.getPSItem_Weight();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.PSConnection <em>PS Connection</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.PSConnection
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSConnection()
       * @generated
       */
      EClass PS_CONNECTION = eINSTANCE.getPSConnection();

      /**
       * The meta object literal for the '<em><b>Source</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_CONNECTION__SOURCE = eINSTANCE.getPSConnection_Source();

      /**
       * The meta object literal for the '<em><b>Target</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_CONNECTION__TARGET = eINSTANCE.getPSConnection_Target();

      /**
       * The meta object literal for the '<em><b>Pattern Specification</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_CONNECTION__PATTERN_SPECIFICATION = eINSTANCE.getPSConnection_PatternSpecification();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSLinkImpl <em>PS Link</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSLinkImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSLink()
       * @generated
       */
      EClass PS_LINK = eINSTANCE.getPSLink();

      /**
       * The meta object literal for the '<em><b>Qualifier</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_LINK__QUALIFIER = eINSTANCE.getPSLink_Qualifier();

      /**
       * The meta object literal for the '<em><b>Negative</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_LINK__NEGATIVE = eINSTANCE.getPSLink_Negative();

      /**
       * The meta object literal for the '<em><b>Instance Of</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_LINK__INSTANCE_OF = eINSTANCE.getPSLink_InstanceOf();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSPathImpl <em>PS Path</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSPathImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSPath()
       * @generated
       */
      EClass PS_PATH = eINSTANCE.getPSPath();

      /**
       * The meta object literal for the '<em><b>Taboo Classes</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_PATH__TABOO_CLASSES = eINSTANCE.getPSPath_TabooClasses();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.PSCombinedFragmentItem <em>PS Combined Fragment Item</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.PSCombinedFragmentItem
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSCombinedFragmentItem()
       * @generated
       */
      EClass PS_COMBINED_FRAGMENT_ITEM = eINSTANCE.getPSCombinedFragmentItem();

      /**
       * The meta object literal for the '<em><b>Parents</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_COMBINED_FRAGMENT_ITEM__PARENTS = eINSTANCE.getPSCombinedFragmentItem_Parents();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSCombinedFragmentImpl <em>PS Combined Fragment</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSCombinedFragmentImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSCombinedFragment()
       * @generated
       */
      EClass PS_COMBINED_FRAGMENT = eINSTANCE.getPSCombinedFragment();

      /**
       * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_COMBINED_FRAGMENT__CHILDREN = eINSTANCE.getPSCombinedFragment_Children();

      /**
       * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_COMBINED_FRAGMENT__KIND = eINSTANCE.getPSCombinedFragment_Kind();

      /**
       * The meta object literal for the '<em><b>Pattern Specification</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_COMBINED_FRAGMENT__PATTERN_SPECIFICATION = eINSTANCE.getPSCombinedFragment_PatternSpecification();

      /**
       * The meta object literal for the '<em><b>Constraint</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_COMBINED_FRAGMENT__CONSTRAINT = eINSTANCE.getPSCombinedFragment_Constraint();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl <em>PS Specification Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSSpecificationConstraint()
       * @generated
       */
      EClass PS_SPECIFICATION_CONSTRAINT = eINSTANCE.getPSSpecificationConstraint();

      /**
       * The meta object literal for the '<em><b>Additional</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_SPECIFICATION_CONSTRAINT__ADDITIONAL = eINSTANCE.getPSSpecificationConstraint_Additional();

      /**
       * The meta object literal for the '<em><b>Pattern Specification</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_SPECIFICATION_CONSTRAINT__PATTERN_SPECIFICATION = eINSTANCE.getPSSpecificationConstraint_PatternSpecification();

      /**
       * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_SPECIFICATION_CONSTRAINT__EXPRESSION = eINSTANCE.getPSSpecificationConstraint_Expression();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.PSNode <em>PS Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.PSNode
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSNode()
       * @generated
       */
      EClass PS_NODE = eINSTANCE.getPSNode();

      /**
       * The meta object literal for the '<em><b>Trigger</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_NODE__TRIGGER = eINSTANCE.getPSNode_Trigger();

      /**
       * The meta object literal for the '<em><b>Modifier</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_NODE__MODIFIER = eINSTANCE.getPSNode_Modifier();

      /**
       * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_NODE__OUTGOING = eINSTANCE.getPSNode_Outgoing();

      /**
       * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_NODE__INCOMING = eINSTANCE.getPSNode_Incoming();

      /**
       * The meta object literal for the '<em><b>Pattern Specification</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_NODE__PATTERN_SPECIFICATION = eINSTANCE.getPSNode_PatternSpecification();

      /**
       * The meta object literal for the '<em><b>Node Constraints</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_NODE__NODE_CONSTRAINTS = eINSTANCE.getPSNode_NodeConstraints();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSObjectImpl <em>PS Object</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSObjectImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSObject()
       * @generated
       */
      EClass PS_OBJECT = eINSTANCE.getPSObject();

      /**
       * The meta object literal for the '<em><b>Instance Of</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_OBJECT__INSTANCE_OF = eINSTANCE.getPSObject_InstanceOf();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSAnnotationImpl <em>PS Annotation</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSAnnotationImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSAnnotation()
       * @generated
       */
      EClass PS_ANNOTATION = eINSTANCE.getPSAnnotation();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_ANNOTATION__TYPE = eINSTANCE.getPSAnnotation_Type();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.PSNodeConstraint <em>PS Node Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.PSNodeConstraint
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSNodeConstraint()
       * @generated
       */
      EClass PS_NODE_CONSTRAINT = eINSTANCE.getPSNodeConstraint();

      /**
       * The meta object literal for the '<em><b>Node</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_NODE_CONSTRAINT__NODE = eINSTANCE.getPSNodeConstraint_Node();

      /**
       * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_NODE_CONSTRAINT__EXPRESSION = eINSTANCE.getPSNodeConstraint_Expression();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.PSFuzzyConstraint <em>PS Fuzzy Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.PSFuzzyConstraint
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSFuzzyConstraint()
       * @generated
       */
      EClass PS_FUZZY_CONSTRAINT = eINSTANCE.getPSFuzzyConstraint();

      /**
       * The meta object literal for the '<em><b>Math Function ID</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_FUZZY_CONSTRAINT__MATH_FUNCTION_ID = eINSTANCE.getPSFuzzyConstraint_MathFunctionID();

      /**
       * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_FUZZY_CONSTRAINT__PARAMETERS = eINSTANCE.getPSFuzzyConstraint_Parameters();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSFuzzyMetricConstraintImpl <em>PS Fuzzy Metric Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSFuzzyMetricConstraintImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSFuzzyMetricConstraint()
       * @generated
       */
      EClass PS_FUZZY_METRIC_CONSTRAINT = eINSTANCE.getPSFuzzyMetricConstraint();

      /**
       * The meta object literal for the '<em><b>Metric Acronym</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_FUZZY_METRIC_CONSTRAINT__METRIC_ACRONYM = eINSTANCE.getPSFuzzyMetricConstraint_MetricAcronym();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl <em>PS Fuzzy Set Rating Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSFuzzySetRatingConstraintImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSFuzzySetRatingConstraint()
       * @generated
       */
      EClass PS_FUZZY_SET_RATING_CONSTRAINT = eINSTANCE.getPSFuzzySetRatingConstraint();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.PSBooleanConstraint <em>PS Boolean Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.PSBooleanConstraint
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSBooleanConstraint()
       * @generated
       */
      EClass PS_BOOLEAN_CONSTRAINT = eINSTANCE.getPSBooleanConstraint();

      /**
       * The meta object literal for the '<em><b>Additional</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_BOOLEAN_CONSTRAINT__ADDITIONAL = eINSTANCE.getPSBooleanConstraint_Additional();

      /**
       * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION = eINSTANCE.getPSBooleanConstraint_ValueExpression();

      /**
       * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_BOOLEAN_CONSTRAINT__OPERATOR = eINSTANCE.getPSBooleanConstraint_Operator();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSAttributeConstraintImpl <em>PS Attribute Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSAttributeConstraintImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSAttributeConstraint()
       * @generated
       */
      EClass PS_ATTRIBUTE_CONSTRAINT = eINSTANCE.getPSAttributeConstraint();

      /**
       * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE = eINSTANCE.getPSAttributeConstraint_Attribute();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSMetricConstraintImpl <em>PS Metric Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSMetricConstraintImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSMetricConstraint()
       * @generated
       */
      EClass PS_METRIC_CONSTRAINT = eINSTANCE.getPSMetricConstraint();

      /**
       * The meta object literal for the '<em><b>Metric Acronym</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_METRIC_CONSTRAINT__METRIC_ACRONYM = eINSTANCE.getPSMetricConstraint_MetricAcronym();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.impl.PSFunctionParameterImpl <em>PS Function Parameter</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.impl.PSFunctionParameterImpl
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPSFunctionParameter()
       * @generated
       */
      EClass PS_FUNCTION_PARAMETER = eINSTANCE.getPSFunctionParameter();

      /**
       * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PS_FUNCTION_PARAMETER__VALUE = eINSTANCE.getPSFunctionParameter_Value();

      /**
       * The meta object literal for the '<em><b>Constraint</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PS_FUNCTION_PARAMETER__CONSTRAINT = eINSTANCE.getPSFunctionParameter_Constraint();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.PatternType <em>Pattern Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.PatternType
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getPatternType()
       * @generated
       */
      EEnum PATTERN_TYPE = eINSTANCE.getPatternType();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.ModifierType <em>Modifier Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.ModifierType
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getModifierType()
       * @generated
       */
      EEnum MODIFIER_TYPE = eINSTANCE.getModifierType();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.specification.OperatorType <em>Operator Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.specification.OperatorType
       * @see org.reclipse.structure.specification.impl.SpecificationPackageImpl#getOperatorType()
       * @generated
       */
      EEnum OPERATOR_TYPE = eINSTANCE.getOperatorType();

   }

} //SpecificationPackage
