/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification;

import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.fujaba.commons.identifier.IdentifierPackage;
import org.eclipse.emf.ecore.EcorePackage;

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
 * @see org.reclipse.behavior.specification.BehavioralpatternFactory
 * @model kind="package"
 * @generated
 */
public interface BehavioralpatternPackage extends EPackage
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
   String eNS_URI = "http://org.reclipse.behavior.specification";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "org.reclipse.behavior";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   BehavioralpatternPackage eINSTANCE = org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl.init();

   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BPAnyObjectImpl <em>BP Any Object</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BPAnyObjectImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPAnyObject()
    * @generated
    */
   int BP_ANY_OBJECT = 0;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ANY_OBJECT__EANNOTATIONS = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ANY_OBJECT__ID = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ANY_OBJECT__NAME = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__NAME;

   /**
    * The feature id for the '<em><b>Diagram</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ANY_OBJECT__DIAGRAM = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__DIAGRAM;

   /**
    * The feature id for the '<em><b>Lifeline</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ANY_OBJECT__LIFELINE = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__LIFELINE;

   /**
    * The number of structural features of the '<em>BP Any Object</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ANY_OBJECT_FEATURE_COUNT = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BPObjectImpl <em>BP Object</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BPObjectImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPObject()
    * @generated
    */
   int BP_OBJECT = 1;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_OBJECT__EANNOTATIONS = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_OBJECT__ID = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_OBJECT__NAME = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__NAME;

   /**
    * The feature id for the '<em><b>Diagram</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_OBJECT__DIAGRAM = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__DIAGRAM;

   /**
    * The feature id for the '<em><b>Lifeline</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_OBJECT__LIFELINE = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__LIFELINE;

   /**
    * The feature id for the '<em><b>Type Reference</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_OBJECT__TYPE_REFERENCE = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>BP Object</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_OBJECT_FEATURE_COUNT = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BPSetObjectImpl <em>BP Set Object</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BPSetObjectImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPSetObject()
    * @generated
    */
   int BP_SET_OBJECT = 2;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_SET_OBJECT__EANNOTATIONS = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_SET_OBJECT__ID = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_SET_OBJECT__NAME = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__NAME;

   /**
    * The feature id for the '<em><b>Diagram</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_SET_OBJECT__DIAGRAM = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__DIAGRAM;

   /**
    * The feature id for the '<em><b>Lifeline</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_SET_OBJECT__LIFELINE = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT__LIFELINE;

   /**
    * The feature id for the '<em><b>Type Reference</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_SET_OBJECT__TYPE_REFERENCE = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>BP Set Object</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_SET_OBJECT_FEATURE_COUNT = BasicSequenceDiagramPackage.ABSTRACT_SEQUENCE_DIAGRAM_OBJECT_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BPAssignmentImpl <em>BP Assignment</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BPAssignmentImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPAssignment()
    * @generated
    */
   int BP_ASSIGNMENT = 3;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT__EANNOTATIONS = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT__ID = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT__NAME = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT__NAME;

   /**
    * The feature id for the '<em><b>Parent Operand</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT__PARENT_OPERAND = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT__PARENT_OPERAND;

   /**
    * The feature id for the '<em><b>Lifeline</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT__LIFELINE = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT__LIFELINE;

   /**
    * The feature id for the '<em><b>Diagram</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT__DIAGRAM = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT__DIAGRAM;

   /**
    * The feature id for the '<em><b>Left Side</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT__LEFT_SIDE = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Right Side</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT__RIGHT_SIDE = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>BP Assignment</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ASSIGNMENT_FEATURE_COUNT = BasicSequenceDiagramPackage.LIFELINE_FRAGMENT_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BehavioralPatternImpl <em>Behavioral Pattern</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BehavioralPatternImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBehavioralPattern()
    * @generated
    */
   int BEHAVIORAL_PATTERN = 4;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__EANNOTATIONS = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__ID = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__NAME = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM__NAME;

   /**
    * The feature id for the '<em><b>Objects</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__OBJECTS = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM__OBJECTS;

   /**
    * The feature id for the '<em><b>Events</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__EVENTS = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM__EVENTS;

   /**
    * The feature id for the '<em><b>Root Fragment</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__ROOT_FRAGMENT = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM__ROOT_FRAGMENT;

   /**
    * The feature id for the '<em><b>Messages</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__MESSAGES = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM__MESSAGES;

   /**
    * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__FRAGMENTS = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM__FRAGMENTS;

   /**
    * The feature id for the '<em><b>Negative</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__NEGATIVE = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Ps Pattern Specification</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Catalog</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN__CATALOG = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>Behavioral Pattern</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BEHAVIORAL_PATTERN_FEATURE_COUNT = BasicSequenceDiagramPackage.SEQUENCE_DIAGRAM_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BPMessageImpl <em>BP Message</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BPMessageImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPMessage()
    * @generated
    */
   int BP_MESSAGE = 5;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__EANNOTATIONS = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__ID = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__NAME = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE__NAME;

   /**
    * The feature id for the '<em><b>Send Event</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__SEND_EVENT = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE__SEND_EVENT;

   /**
    * The feature id for the '<em><b>Receive Event</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__RECEIVE_EVENT = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE__RECEIVE_EVENT;

   /**
    * The feature id for the '<em><b>Diagram</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__DIAGRAM = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE__DIAGRAM;

   /**
    * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__ARGUMENTS = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Self Call</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__SELF_CALL = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Method Reference</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE__METHOD_REFERENCE = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>BP Message</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_MESSAGE_FEATURE_COUNT = BasicSequenceDiagramPackage.ABSTRACT_MESSAGE_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BPArgumentImpl <em>BP Argument</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BPArgumentImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPArgument()
    * @generated
    */
   int BP_ARGUMENT = 6;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ARGUMENT__EANNOTATIONS = IdentifierPackage.IDENTIFIER__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ARGUMENT__ID = IdentifierPackage.IDENTIFIER__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ARGUMENT__NAME = IdentifierPackage.IDENTIFIER__NAME;

   /**
    * The feature id for the '<em><b>Message</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ARGUMENT__MESSAGE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Statement</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ARGUMENT__STATEMENT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Type</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ARGUMENT__TYPE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>BP Argument</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_ARGUMENT_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BPEachFragmentImpl <em>BP Each Fragment</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BPEachFragmentImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPEachFragment()
    * @generated
    */
   int BP_EACH_FRAGMENT = 7;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_EACH_FRAGMENT__EANNOTATIONS = BasicSequenceDiagramPackage.COMBINED_FRAGMENT__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_EACH_FRAGMENT__ID = BasicSequenceDiagramPackage.COMBINED_FRAGMENT__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_EACH_FRAGMENT__NAME = BasicSequenceDiagramPackage.COMBINED_FRAGMENT__NAME;

   /**
    * The feature id for the '<em><b>Parent Operand</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_EACH_FRAGMENT__PARENT_OPERAND = BasicSequenceDiagramPackage.COMBINED_FRAGMENT__PARENT_OPERAND;

   /**
    * The feature id for the '<em><b>Operands</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_EACH_FRAGMENT__OPERANDS = BasicSequenceDiagramPackage.COMBINED_FRAGMENT__OPERANDS;

   /**
    * The number of structural features of the '<em>BP Each Fragment</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_EACH_FRAGMENT_FEATURE_COUNT = BasicSequenceDiagramPackage.COMBINED_FRAGMENT_FEATURE_COUNT + 0;


   /**
    * The meta object id for the '{@link org.reclipse.behavior.specification.impl.BPCatalogImpl <em>BP Catalog</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.behavior.specification.impl.BPCatalogImpl
    * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPCatalog()
    * @generated
    */
   int BP_CATALOG = 8;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_CATALOG__EANNOTATIONS = IdentifierPackage.IDENTIFIER__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_CATALOG__ID = IdentifierPackage.IDENTIFIER__ID;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_CATALOG__NAME = IdentifierPackage.IDENTIFIER__NAME;

   /**
    * The feature id for the '<em><b>Behavioral Patterns</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_CATALOG__BEHAVIORAL_PATTERNS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Ps Catalog</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_CATALOG__PS_CATALOG = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>BP Catalog</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int BP_CATALOG_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;


   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BPAnyObject <em>BP Any Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>BP Any Object</em>'.
    * @see org.reclipse.behavior.specification.BPAnyObject
    * @generated
    */
   EClass getBPAnyObject();

   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BPObject <em>BP Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>BP Object</em>'.
    * @see org.reclipse.behavior.specification.BPObject
    * @generated
    */
   EClass getBPObject();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.behavior.specification.BPObject#getTypeReference <em>Type Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Type Reference</em>'.
    * @see org.reclipse.behavior.specification.BPObject#getTypeReference()
    * @see #getBPObject()
    * @generated
    */
   EReference getBPObject_TypeReference();

   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BPSetObject <em>BP Set Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>BP Set Object</em>'.
    * @see org.reclipse.behavior.specification.BPSetObject
    * @generated
    */
   EClass getBPSetObject();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.behavior.specification.BPSetObject#getTypeReference <em>Type Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Type Reference</em>'.
    * @see org.reclipse.behavior.specification.BPSetObject#getTypeReference()
    * @see #getBPSetObject()
    * @generated
    */
   EReference getBPSetObject_TypeReference();

   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BPAssignment <em>BP Assignment</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>BP Assignment</em>'.
    * @see org.reclipse.behavior.specification.BPAssignment
    * @generated
    */
   EClass getBPAssignment();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.behavior.specification.BPAssignment#getLeftSide <em>Left Side</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Left Side</em>'.
    * @see org.reclipse.behavior.specification.BPAssignment#getLeftSide()
    * @see #getBPAssignment()
    * @generated
    */
   EReference getBPAssignment_LeftSide();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.behavior.specification.BPAssignment#getRightSide <em>Right Side</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Right Side</em>'.
    * @see org.reclipse.behavior.specification.BPAssignment#getRightSide()
    * @see #getBPAssignment()
    * @generated
    */
   EReference getBPAssignment_RightSide();

   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BehavioralPattern <em>Behavioral Pattern</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Behavioral Pattern</em>'.
    * @see org.reclipse.behavior.specification.BehavioralPattern
    * @generated
    */
   EClass getBehavioralPattern();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.behavior.specification.BehavioralPattern#isNegative <em>Negative</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Negative</em>'.
    * @see org.reclipse.behavior.specification.BehavioralPattern#isNegative()
    * @see #getBehavioralPattern()
    * @generated
    */
   EAttribute getBehavioralPattern_Negative();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.behavior.specification.BehavioralPattern#getPsPatternSpecification <em>Ps Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Ps Pattern Specification</em>'.
    * @see org.reclipse.behavior.specification.BehavioralPattern#getPsPatternSpecification()
    * @see #getBehavioralPattern()
    * @generated
    */
   EReference getBehavioralPattern_PsPatternSpecification();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.behavior.specification.BehavioralPattern#getCatalog <em>Catalog</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Catalog</em>'.
    * @see org.reclipse.behavior.specification.BehavioralPattern#getCatalog()
    * @see #getBehavioralPattern()
    * @generated
    */
   EReference getBehavioralPattern_Catalog();

   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BPMessage <em>BP Message</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>BP Message</em>'.
    * @see org.reclipse.behavior.specification.BPMessage
    * @generated
    */
   EClass getBPMessage();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.behavior.specification.BPMessage#getArguments <em>Arguments</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Arguments</em>'.
    * @see org.reclipse.behavior.specification.BPMessage#getArguments()
    * @see #getBPMessage()
    * @generated
    */
   EReference getBPMessage_Arguments();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.behavior.specification.BPMessage#isSelfCall <em>Self Call</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Self Call</em>'.
    * @see org.reclipse.behavior.specification.BPMessage#isSelfCall()
    * @see #getBPMessage()
    * @generated
    */
   EAttribute getBPMessage_SelfCall();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.behavior.specification.BPMessage#getMethodReference <em>Method Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Method Reference</em>'.
    * @see org.reclipse.behavior.specification.BPMessage#getMethodReference()
    * @see #getBPMessage()
    * @generated
    */
   EReference getBPMessage_MethodReference();

   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BPArgument <em>BP Argument</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>BP Argument</em>'.
    * @see org.reclipse.behavior.specification.BPArgument
    * @generated
    */
   EClass getBPArgument();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.behavior.specification.BPArgument#getMessage <em>Message</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Message</em>'.
    * @see org.reclipse.behavior.specification.BPArgument#getMessage()
    * @see #getBPArgument()
    * @generated
    */
   EReference getBPArgument_Message();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.behavior.specification.BPArgument#getStatement <em>Statement</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Statement</em>'.
    * @see org.reclipse.behavior.specification.BPArgument#getStatement()
    * @see #getBPArgument()
    * @generated
    */
   EAttribute getBPArgument_Statement();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.behavior.specification.BPArgument#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Type</em>'.
    * @see org.reclipse.behavior.specification.BPArgument#getType()
    * @see #getBPArgument()
    * @generated
    */
   EReference getBPArgument_Type();

   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BPEachFragment <em>BP Each Fragment</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>BP Each Fragment</em>'.
    * @see org.reclipse.behavior.specification.BPEachFragment
    * @generated
    */
   EClass getBPEachFragment();

   /**
    * Returns the meta object for class '{@link org.reclipse.behavior.specification.BPCatalog <em>BP Catalog</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>BP Catalog</em>'.
    * @see org.reclipse.behavior.specification.BPCatalog
    * @generated
    */
   EClass getBPCatalog();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.behavior.specification.BPCatalog#getBehavioralPatterns <em>Behavioral Patterns</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Behavioral Patterns</em>'.
    * @see org.reclipse.behavior.specification.BPCatalog#getBehavioralPatterns()
    * @see #getBPCatalog()
    * @generated
    */
   EReference getBPCatalog_BehavioralPatterns();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.behavior.specification.BPCatalog#getPsCatalog <em>Ps Catalog</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Ps Catalog</em>'.
    * @see org.reclipse.behavior.specification.BPCatalog#getPsCatalog()
    * @see #getBPCatalog()
    * @generated
    */
   EReference getBPCatalog_PsCatalog();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   BehavioralpatternFactory getBehavioralpatternFactory();

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
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BPAnyObjectImpl <em>BP Any Object</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BPAnyObjectImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPAnyObject()
       * @generated
       */
      EClass BP_ANY_OBJECT = eINSTANCE.getBPAnyObject();

      /**
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BPObjectImpl <em>BP Object</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BPObjectImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPObject()
       * @generated
       */
      EClass BP_OBJECT = eINSTANCE.getBPObject();

      /**
       * The meta object literal for the '<em><b>Type Reference</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_OBJECT__TYPE_REFERENCE = eINSTANCE.getBPObject_TypeReference();

      /**
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BPSetObjectImpl <em>BP Set Object</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BPSetObjectImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPSetObject()
       * @generated
       */
      EClass BP_SET_OBJECT = eINSTANCE.getBPSetObject();

      /**
       * The meta object literal for the '<em><b>Type Reference</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_SET_OBJECT__TYPE_REFERENCE = eINSTANCE.getBPSetObject_TypeReference();

      /**
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BPAssignmentImpl <em>BP Assignment</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BPAssignmentImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPAssignment()
       * @generated
       */
      EClass BP_ASSIGNMENT = eINSTANCE.getBPAssignment();

      /**
       * The meta object literal for the '<em><b>Left Side</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_ASSIGNMENT__LEFT_SIDE = eINSTANCE.getBPAssignment_LeftSide();

      /**
       * The meta object literal for the '<em><b>Right Side</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_ASSIGNMENT__RIGHT_SIDE = eINSTANCE.getBPAssignment_RightSide();

      /**
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BehavioralPatternImpl <em>Behavioral Pattern</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BehavioralPatternImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBehavioralPattern()
       * @generated
       */
      EClass BEHAVIORAL_PATTERN = eINSTANCE.getBehavioralPattern();

      /**
       * The meta object literal for the '<em><b>Negative</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute BEHAVIORAL_PATTERN__NEGATIVE = eINSTANCE.getBehavioralPattern_Negative();

      /**
       * The meta object literal for the '<em><b>Ps Pattern Specification</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION = eINSTANCE.getBehavioralPattern_PsPatternSpecification();

      /**
       * The meta object literal for the '<em><b>Catalog</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BEHAVIORAL_PATTERN__CATALOG = eINSTANCE.getBehavioralPattern_Catalog();

      /**
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BPMessageImpl <em>BP Message</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BPMessageImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPMessage()
       * @generated
       */
      EClass BP_MESSAGE = eINSTANCE.getBPMessage();

      /**
       * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_MESSAGE__ARGUMENTS = eINSTANCE.getBPMessage_Arguments();

      /**
       * The meta object literal for the '<em><b>Self Call</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute BP_MESSAGE__SELF_CALL = eINSTANCE.getBPMessage_SelfCall();

      /**
       * The meta object literal for the '<em><b>Method Reference</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_MESSAGE__METHOD_REFERENCE = eINSTANCE.getBPMessage_MethodReference();

      /**
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BPArgumentImpl <em>BP Argument</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BPArgumentImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPArgument()
       * @generated
       */
      EClass BP_ARGUMENT = eINSTANCE.getBPArgument();

      /**
       * The meta object literal for the '<em><b>Message</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_ARGUMENT__MESSAGE = eINSTANCE.getBPArgument_Message();

      /**
       * The meta object literal for the '<em><b>Statement</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute BP_ARGUMENT__STATEMENT = eINSTANCE.getBPArgument_Statement();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_ARGUMENT__TYPE = eINSTANCE.getBPArgument_Type();

      /**
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BPEachFragmentImpl <em>BP Each Fragment</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BPEachFragmentImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPEachFragment()
       * @generated
       */
      EClass BP_EACH_FRAGMENT = eINSTANCE.getBPEachFragment();

      /**
       * The meta object literal for the '{@link org.reclipse.behavior.specification.impl.BPCatalogImpl <em>BP Catalog</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.behavior.specification.impl.BPCatalogImpl
       * @see org.reclipse.behavior.specification.impl.BehavioralpatternPackageImpl#getBPCatalog()
       * @generated
       */
      EClass BP_CATALOG = eINSTANCE.getBPCatalog();

      /**
       * The meta object literal for the '<em><b>Behavioral Patterns</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_CATALOG__BEHAVIORAL_PATTERNS = eINSTANCE.getBPCatalog_BehavioralPatterns();

      /**
       * The meta object literal for the '<em><b>Ps Catalog</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference BP_CATALOG__PS_CATALOG = eINSTANCE.getBPCatalog_PsCatalog();

   }

} //BehavioralpatternPackage
