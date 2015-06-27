/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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
 * @see org.reclipse.structure.inference.annotations.AnnotationsFactory
 * @model kind="package"
 * @generated
 */
public interface AnnotationsPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "annotations";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http://org.reclipse.structure.inference.annotations";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "org.reclipse.structure.inference";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   AnnotationsPackage eINSTANCE = org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl.init();

   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl <em>ASG Annotation</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getASGAnnotation()
    * @generated
    */
   int ASG_ANNOTATION = 0;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__EANNOTATIONS = EcorePackage.EANNOTATION__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Source</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__SOURCE = EcorePackage.EANNOTATION__SOURCE;

   /**
    * The feature id for the '<em><b>Details</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__DETAILS = EcorePackage.EANNOTATION__DETAILS;

   /**
    * The feature id for the '<em><b>EModel Element</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__EMODEL_ELEMENT = EcorePackage.EANNOTATION__EMODEL_ELEMENT;

   /**
    * The feature id for the '<em><b>Contents</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__CONTENTS = EcorePackage.EANNOTATION__CONTENTS;

   /**
    * The feature id for the '<em><b>References</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__REFERENCES = EcorePackage.EANNOTATION__REFERENCES;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__NAME = EcorePackage.EANNOTATION_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Annotated Elements</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__ANNOTATED_ELEMENTS = EcorePackage.EANNOTATION_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Antecedent Annos</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__ANTECEDENT_ANNOS = EcorePackage.EANNOTATION_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Consequent Annos</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__CONSEQUENT_ANNOS = EcorePackage.EANNOTATION_FEATURE_COUNT + 3;

   /**
    * The feature id for the '<em><b>Bound Objects</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__BOUND_OBJECTS = EcorePackage.EANNOTATION_FEATURE_COUNT + 4;

   /**
    * The feature id for the '<em><b>Valid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__VALID = EcorePackage.EANNOTATION_FEATURE_COUNT + 5;

   /**
    * The feature id for the '<em><b>Pattern</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__PATTERN = EcorePackage.EANNOTATION_FEATURE_COUNT + 6;

   /**
    * The feature id for the '<em><b>Annotation Ranking</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__ANNOTATION_RANKING = EcorePackage.EANNOTATION_FEATURE_COUNT + 7;

   /**
    * The feature id for the '<em><b>Satisfied Constraints</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__SATISFIED_CONSTRAINTS = EcorePackage.EANNOTATION_FEATURE_COUNT + 8;

   /**
    * The feature id for the '<em><b>Set Result Set</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION__SET_RESULT_SET = EcorePackage.EANNOTATION_FEATURE_COUNT + 9;

   /**
    * The number of structural features of the '<em>ASG Annotation</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ASG_ANNOTATION_FEATURE_COUNT = EcorePackage.EANNOTATION_FEATURE_COUNT + 10;

   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.TemporaryAnnotationImpl <em>Temporary Annotation</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.TemporaryAnnotationImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getTemporaryAnnotation()
    * @generated
    */
   int TEMPORARY_ANNOTATION = 1;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__EANNOTATIONS = ASG_ANNOTATION__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Source</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__SOURCE = ASG_ANNOTATION__SOURCE;

   /**
    * The feature id for the '<em><b>Details</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__DETAILS = ASG_ANNOTATION__DETAILS;

   /**
    * The feature id for the '<em><b>EModel Element</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__EMODEL_ELEMENT = ASG_ANNOTATION__EMODEL_ELEMENT;

   /**
    * The feature id for the '<em><b>Contents</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__CONTENTS = ASG_ANNOTATION__CONTENTS;

   /**
    * The feature id for the '<em><b>References</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__REFERENCES = ASG_ANNOTATION__REFERENCES;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__NAME = ASG_ANNOTATION__NAME;

   /**
    * The feature id for the '<em><b>Annotated Elements</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__ANNOTATED_ELEMENTS = ASG_ANNOTATION__ANNOTATED_ELEMENTS;

   /**
    * The feature id for the '<em><b>Antecedent Annos</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__ANTECEDENT_ANNOS = ASG_ANNOTATION__ANTECEDENT_ANNOS;

   /**
    * The feature id for the '<em><b>Consequent Annos</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__CONSEQUENT_ANNOS = ASG_ANNOTATION__CONSEQUENT_ANNOS;

   /**
    * The feature id for the '<em><b>Bound Objects</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__BOUND_OBJECTS = ASG_ANNOTATION__BOUND_OBJECTS;

   /**
    * The feature id for the '<em><b>Valid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__VALID = ASG_ANNOTATION__VALID;

   /**
    * The feature id for the '<em><b>Pattern</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__PATTERN = ASG_ANNOTATION__PATTERN;

   /**
    * The feature id for the '<em><b>Annotation Ranking</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__ANNOTATION_RANKING = ASG_ANNOTATION__ANNOTATION_RANKING;

   /**
    * The feature id for the '<em><b>Satisfied Constraints</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__SATISFIED_CONSTRAINTS = ASG_ANNOTATION__SATISFIED_CONSTRAINTS;

   /**
    * The feature id for the '<em><b>Set Result Set</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION__SET_RESULT_SET = ASG_ANNOTATION__SET_RESULT_SET;

   /**
    * The number of structural features of the '<em>Temporary Annotation</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORARY_ANNOTATION_FEATURE_COUNT = ASG_ANNOTATION_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.SetInstanceAnnotationImpl <em>Set Instance Annotation</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.SetInstanceAnnotationImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSetInstanceAnnotation()
    * @generated
    */
   int SET_INSTANCE_ANNOTATION = 2;

   /**
    * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__EANNOTATIONS = ASG_ANNOTATION__EANNOTATIONS;

   /**
    * The feature id for the '<em><b>Source</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__SOURCE = ASG_ANNOTATION__SOURCE;

   /**
    * The feature id for the '<em><b>Details</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__DETAILS = ASG_ANNOTATION__DETAILS;

   /**
    * The feature id for the '<em><b>EModel Element</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__EMODEL_ELEMENT = ASG_ANNOTATION__EMODEL_ELEMENT;

   /**
    * The feature id for the '<em><b>Contents</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__CONTENTS = ASG_ANNOTATION__CONTENTS;

   /**
    * The feature id for the '<em><b>References</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__REFERENCES = ASG_ANNOTATION__REFERENCES;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__NAME = ASG_ANNOTATION__NAME;

   /**
    * The feature id for the '<em><b>Annotated Elements</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__ANNOTATED_ELEMENTS = ASG_ANNOTATION__ANNOTATED_ELEMENTS;

   /**
    * The feature id for the '<em><b>Antecedent Annos</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__ANTECEDENT_ANNOS = ASG_ANNOTATION__ANTECEDENT_ANNOS;

   /**
    * The feature id for the '<em><b>Consequent Annos</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__CONSEQUENT_ANNOS = ASG_ANNOTATION__CONSEQUENT_ANNOS;

   /**
    * The feature id for the '<em><b>Bound Objects</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__BOUND_OBJECTS = ASG_ANNOTATION__BOUND_OBJECTS;

   /**
    * The feature id for the '<em><b>Valid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__VALID = ASG_ANNOTATION__VALID;

   /**
    * The feature id for the '<em><b>Pattern</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__PATTERN = ASG_ANNOTATION__PATTERN;

   /**
    * The feature id for the '<em><b>Annotation Ranking</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__ANNOTATION_RANKING = ASG_ANNOTATION__ANNOTATION_RANKING;

   /**
    * The feature id for the '<em><b>Satisfied Constraints</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__SATISFIED_CONSTRAINTS = ASG_ANNOTATION__SATISFIED_CONSTRAINTS;

   /**
    * The feature id for the '<em><b>Set Result Set</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION__SET_RESULT_SET = ASG_ANNOTATION__SET_RESULT_SET;

   /**
    * The number of structural features of the '<em>Set Instance Annotation</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_INSTANCE_ANNOTATION_FEATURE_COUNT = ASG_ANNOTATION_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.AnnotationSetImpl <em>Annotation Set</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationSetImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getAnnotationSet()
    * @generated
    */
   int ANNOTATION_SET = 3;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ANNOTATION_SET__ANNOTATIONS = 0;

   /**
    * The feature id for the '<em><b>Set Result Set</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ANNOTATION_SET__SET_RESULT_SET = 1;

   /**
    * The number of structural features of the '<em>Annotation Set</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ANNOTATION_SET_FEATURE_COUNT = 2;

   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.SetResultSetImpl <em>Set Result Set</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.SetResultSetImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSetResultSet()
    * @generated
    */
   int SET_RESULT_SET = 4;

   /**
    * The feature id for the '<em><b>Annotation Sets</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_RESULT_SET__ANNOTATION_SETS = 0;

   /**
    * The feature id for the '<em><b>Parent Annotation</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_RESULT_SET__PARENT_ANNOTATION = 1;

   /**
    * The number of structural features of the '<em>Set Result Set</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SET_RESULT_SET_FEATURE_COUNT = 2;

   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.StringToEObjectMapImpl <em>String To EObject Map</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.StringToEObjectMapImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getStringToEObjectMap()
    * @generated
    */
   int STRING_TO_EOBJECT_MAP = 5;

   /**
    * The feature id for the '<em><b>Key</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int STRING_TO_EOBJECT_MAP__KEY = 0;

   /**
    * The feature id for the '<em><b>Value</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int STRING_TO_EOBJECT_MAP__VALUE = 1;

   /**
    * The number of structural features of the '<em>String To EObject Map</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int STRING_TO_EOBJECT_MAP_FEATURE_COUNT = 2;


   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.AnnotationEngineImpl <em>Annotation Engine</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationEngineImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getAnnotationEngine()
    * @generated
    */
   int ANNOTATION_ENGINE = 6;

   /**
    * The feature id for the '<em><b>Failed Applications</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ANNOTATION_ENGINE__FAILED_APPLICATIONS = 0;

   /**
    * The feature id for the '<em><b>Found Annotations</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ANNOTATION_ENGINE__FOUND_ANNOTATIONS = 1;

   /**
    * The number of structural features of the '<em>Annotation Engine</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ANNOTATION_ENGINE_FEATURE_COUNT = 2;


   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.SatisfiedConstraintImpl <em>Satisfied Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.SatisfiedConstraintImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSatisfiedConstraint()
    * @generated
    */
   int SATISFIED_CONSTRAINT = 7;

   /**
    * The feature id for the '<em><b>Annotation</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_CONSTRAINT__ANNOTATION = 0;

   /**
    * The number of structural features of the '<em>Satisfied Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_CONSTRAINT_FEATURE_COUNT = 1;

   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.SatisfiedAttributeConstraintImpl <em>Satisfied Attribute Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.SatisfiedAttributeConstraintImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSatisfiedAttributeConstraint()
    * @generated
    */
   int SATISFIED_ATTRIBUTE_CONSTRAINT = 8;

   /**
    * The feature id for the '<em><b>Annotation</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_ATTRIBUTE_CONSTRAINT__ANNOTATION = SATISFIED_CONSTRAINT__ANNOTATION;

   /**
    * The feature id for the '<em><b>Node ID</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_ATTRIBUTE_CONSTRAINT__NODE_ID = SATISFIED_CONSTRAINT_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Attribute Index</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_ATTRIBUTE_CONSTRAINT__ATTRIBUTE_INDEX = SATISFIED_CONSTRAINT_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Context</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT = SATISFIED_CONSTRAINT_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Constraint</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT = SATISFIED_CONSTRAINT_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>Satisfied Attribute Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_ATTRIBUTE_CONSTRAINT_FEATURE_COUNT = SATISFIED_CONSTRAINT_FEATURE_COUNT + 4;

   /**
    * The meta object id for the '{@link org.reclipse.structure.inference.annotations.impl.SatisfiedSpecificationConstraintImpl <em>Satisfied Specification Constraint</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.reclipse.structure.inference.annotations.impl.SatisfiedSpecificationConstraintImpl
    * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSatisfiedSpecificationConstraint()
    * @generated
    */
   int SATISFIED_SPECIFICATION_CONSTRAINT = 9;

   /**
    * The feature id for the '<em><b>Annotation</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_SPECIFICATION_CONSTRAINT__ANNOTATION = SATISFIED_CONSTRAINT__ANNOTATION;

   /**
    * The feature id for the '<em><b>Expression</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_SPECIFICATION_CONSTRAINT__EXPRESSION = SATISFIED_CONSTRAINT_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Constraint</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT = SATISFIED_CONSTRAINT_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Satisfied Specification Constraint</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int SATISFIED_SPECIFICATION_CONSTRAINT_FEATURE_COUNT = SATISFIED_CONSTRAINT_FEATURE_COUNT + 2;


   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.ASGAnnotation <em>ASG Annotation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>ASG Annotation</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation
    * @generated
    */
   EClass getASGAnnotation();

   /**
    * Returns the meta object for the map '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getAnnotatedElements <em>Annotated Elements</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the map '<em>Annotated Elements</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getAnnotatedElements()
    * @see #getASGAnnotation()
    * @generated
    */
   EReference getASGAnnotation_AnnotatedElements();

   /**
    * Returns the meta object for the reference list '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getAntecedentAnnos <em>Antecedent Annos</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Antecedent Annos</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getAntecedentAnnos()
    * @see #getASGAnnotation()
    * @generated
    */
   EReference getASGAnnotation_AntecedentAnnos();

   /**
    * Returns the meta object for the reference list '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getConsequentAnnos <em>Consequent Annos</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Consequent Annos</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getConsequentAnnos()
    * @see #getASGAnnotation()
    * @generated
    */
   EReference getASGAnnotation_ConsequentAnnos();

   /**
    * Returns the meta object for the map '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getBoundObjects <em>Bound Objects</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the map '<em>Bound Objects</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getBoundObjects()
    * @see #getASGAnnotation()
    * @generated
    */
   EReference getASGAnnotation_BoundObjects();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#isValid <em>Valid</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Valid</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#isValid()
    * @see #getASGAnnotation()
    * @generated
    */
   EAttribute getASGAnnotation_Valid();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getPattern <em>Pattern</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Pattern</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getPattern()
    * @see #getASGAnnotation()
    * @generated
    */
   EReference getASGAnnotation_Pattern();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getAnnotationRanking <em>Annotation Ranking</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Annotation Ranking</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getAnnotationRanking()
    * @see #getASGAnnotation()
    * @generated
    */
   EAttribute getASGAnnotation_AnnotationRanking();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getSatisfiedConstraints <em>Satisfied Constraints</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Satisfied Constraints</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getSatisfiedConstraints()
    * @see #getASGAnnotation()
    * @generated
    */
   EReference getASGAnnotation_SatisfiedConstraints();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.inference.annotations.ASGAnnotation#getSetResultSet <em>Set Result Set</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Set Result Set</em>'.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation#getSetResultSet()
    * @see #getASGAnnotation()
    * @generated
    */
   EReference getASGAnnotation_SetResultSet();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.TemporaryAnnotation <em>Temporary Annotation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Temporary Annotation</em>'.
    * @see org.reclipse.structure.inference.annotations.TemporaryAnnotation
    * @generated
    */
   EClass getTemporaryAnnotation();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.SetInstanceAnnotation <em>Set Instance Annotation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Set Instance Annotation</em>'.
    * @see org.reclipse.structure.inference.annotations.SetInstanceAnnotation
    * @generated
    */
   EClass getSetInstanceAnnotation();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.AnnotationSet <em>Annotation Set</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Annotation Set</em>'.
    * @see org.reclipse.structure.inference.annotations.AnnotationSet
    * @generated
    */
   EClass getAnnotationSet();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.inference.annotations.AnnotationSet#getAnnotations <em>Annotations</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Annotations</em>'.
    * @see org.reclipse.structure.inference.annotations.AnnotationSet#getAnnotations()
    * @see #getAnnotationSet()
    * @generated
    */
   EReference getAnnotationSet_Annotations();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.inference.annotations.AnnotationSet#getSetResultSet <em>Set Result Set</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Set Result Set</em>'.
    * @see org.reclipse.structure.inference.annotations.AnnotationSet#getSetResultSet()
    * @see #getAnnotationSet()
    * @generated
    */
   EReference getAnnotationSet_SetResultSet();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.SetResultSet <em>Set Result Set</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Set Result Set</em>'.
    * @see org.reclipse.structure.inference.annotations.SetResultSet
    * @generated
    */
   EClass getSetResultSet();

   /**
    * Returns the meta object for the containment reference list '{@link org.reclipse.structure.inference.annotations.SetResultSet#getAnnotationSets <em>Annotation Sets</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Annotation Sets</em>'.
    * @see org.reclipse.structure.inference.annotations.SetResultSet#getAnnotationSets()
    * @see #getSetResultSet()
    * @generated
    */
   EReference getSetResultSet_AnnotationSets();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.inference.annotations.SetResultSet#getParentAnnotation <em>Parent Annotation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Parent Annotation</em>'.
    * @see org.reclipse.structure.inference.annotations.SetResultSet#getParentAnnotation()
    * @see #getSetResultSet()
    * @generated
    */
   EReference getSetResultSet_ParentAnnotation();

   /**
    * Returns the meta object for class '{@link java.util.Map.Entry <em>String To EObject Map</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>String To EObject Map</em>'.
    * @see java.util.Map.Entry
    * @model keyDataType="org.eclipse.emf.ecore.EString"
    *        valueType="org.eclipse.emf.ecore.EObject" valueMany="true"
    * @generated
    */
   EClass getStringToEObjectMap();

   /**
    * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Key</em>'.
    * @see java.util.Map.Entry
    * @see #getStringToEObjectMap()
    * @generated
    */
   EAttribute getStringToEObjectMap_Key();

   /**
    * Returns the meta object for the reference list '{@link java.util.Map.Entry <em>Value</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Value</em>'.
    * @see java.util.Map.Entry
    * @see #getStringToEObjectMap()
    * @generated
    */
   EReference getStringToEObjectMap_Value();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.AnnotationEngine <em>Annotation Engine</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Annotation Engine</em>'.
    * @see org.reclipse.structure.inference.annotations.AnnotationEngine
    * @generated
    */
   EClass getAnnotationEngine();

   /**
    * Returns the meta object for the map '{@link org.reclipse.structure.inference.annotations.AnnotationEngine#getFailedApplications <em>Failed Applications</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the map '<em>Failed Applications</em>'.
    * @see org.reclipse.structure.inference.annotations.AnnotationEngine#getFailedApplications()
    * @see #getAnnotationEngine()
    * @generated
    */
   EReference getAnnotationEngine_FailedApplications();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.inference.annotations.AnnotationEngine#getFoundAnnotations <em>Found Annotations</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Found Annotations</em>'.
    * @see org.reclipse.structure.inference.annotations.AnnotationEngine#getFoundAnnotations()
    * @see #getAnnotationEngine()
    * @generated
    */
   EReference getAnnotationEngine_FoundAnnotations();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.SatisfiedConstraint <em>Satisfied Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Satisfied Constraint</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedConstraint
    * @generated
    */
   EClass getSatisfiedConstraint();

   /**
    * Returns the meta object for the container reference '{@link org.reclipse.structure.inference.annotations.SatisfiedConstraint#getAnnotation <em>Annotation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Annotation</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedConstraint#getAnnotation()
    * @see #getSatisfiedConstraint()
    * @generated
    */
   EReference getSatisfiedConstraint_Annotation();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint <em>Satisfied Attribute Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Satisfied Attribute Constraint</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint
    * @generated
    */
   EClass getSatisfiedAttributeConstraint();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getNodeID <em>Node ID</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Node ID</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getNodeID()
    * @see #getSatisfiedAttributeConstraint()
    * @generated
    */
   EAttribute getSatisfiedAttributeConstraint_NodeID();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getAttributeIndex <em>Attribute Index</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Attribute Index</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getAttributeIndex()
    * @see #getSatisfiedAttributeConstraint()
    * @generated
    */
   EAttribute getSatisfiedAttributeConstraint_AttributeIndex();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getContext <em>Context</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Context</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getContext()
    * @see #getSatisfiedAttributeConstraint()
    * @generated
    */
   EReference getSatisfiedAttributeConstraint_Context();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getConstraint <em>Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Constraint</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint#getConstraint()
    * @see #getSatisfiedAttributeConstraint()
    * @generated
    */
   EReference getSatisfiedAttributeConstraint_Constraint();

   /**
    * Returns the meta object for class '{@link org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint <em>Satisfied Specification Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Satisfied Specification Constraint</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint
    * @generated
    */
   EClass getSatisfiedSpecificationConstraint();

   /**
    * Returns the meta object for the attribute '{@link org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint#getExpression <em>Expression</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Expression</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint#getExpression()
    * @see #getSatisfiedSpecificationConstraint()
    * @generated
    */
   EAttribute getSatisfiedSpecificationConstraint_Expression();

   /**
    * Returns the meta object for the reference '{@link org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint#getConstraint <em>Constraint</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Constraint</em>'.
    * @see org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint#getConstraint()
    * @see #getSatisfiedSpecificationConstraint()
    * @generated
    */
   EReference getSatisfiedSpecificationConstraint_Constraint();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   AnnotationsFactory getAnnotationsFactory();

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
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl <em>ASG Annotation</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.ASGAnnotationImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getASGAnnotation()
       * @generated
       */
      EClass ASG_ANNOTATION = eINSTANCE.getASGAnnotation();

      /**
       * The meta object literal for the '<em><b>Annotated Elements</b></em>' map feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ASG_ANNOTATION__ANNOTATED_ELEMENTS = eINSTANCE.getASGAnnotation_AnnotatedElements();

      /**
       * The meta object literal for the '<em><b>Antecedent Annos</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ASG_ANNOTATION__ANTECEDENT_ANNOS = eINSTANCE.getASGAnnotation_AntecedentAnnos();

      /**
       * The meta object literal for the '<em><b>Consequent Annos</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ASG_ANNOTATION__CONSEQUENT_ANNOS = eINSTANCE.getASGAnnotation_ConsequentAnnos();

      /**
       * The meta object literal for the '<em><b>Bound Objects</b></em>' map feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ASG_ANNOTATION__BOUND_OBJECTS = eINSTANCE.getASGAnnotation_BoundObjects();

      /**
       * The meta object literal for the '<em><b>Valid</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute ASG_ANNOTATION__VALID = eINSTANCE.getASGAnnotation_Valid();

      /**
       * The meta object literal for the '<em><b>Pattern</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ASG_ANNOTATION__PATTERN = eINSTANCE.getASGAnnotation_Pattern();

      /**
       * The meta object literal for the '<em><b>Annotation Ranking</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute ASG_ANNOTATION__ANNOTATION_RANKING = eINSTANCE.getASGAnnotation_AnnotationRanking();

      /**
       * The meta object literal for the '<em><b>Satisfied Constraints</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ASG_ANNOTATION__SATISFIED_CONSTRAINTS = eINSTANCE.getASGAnnotation_SatisfiedConstraints();

      /**
       * The meta object literal for the '<em><b>Set Result Set</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ASG_ANNOTATION__SET_RESULT_SET = eINSTANCE.getASGAnnotation_SetResultSet();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.TemporaryAnnotationImpl <em>Temporary Annotation</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.TemporaryAnnotationImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getTemporaryAnnotation()
       * @generated
       */
      EClass TEMPORARY_ANNOTATION = eINSTANCE.getTemporaryAnnotation();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.SetInstanceAnnotationImpl <em>Set Instance Annotation</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.SetInstanceAnnotationImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSetInstanceAnnotation()
       * @generated
       */
      EClass SET_INSTANCE_ANNOTATION = eINSTANCE.getSetInstanceAnnotation();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.AnnotationSetImpl <em>Annotation Set</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationSetImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getAnnotationSet()
       * @generated
       */
      EClass ANNOTATION_SET = eINSTANCE.getAnnotationSet();

      /**
       * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ANNOTATION_SET__ANNOTATIONS = eINSTANCE.getAnnotationSet_Annotations();

      /**
       * The meta object literal for the '<em><b>Set Result Set</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ANNOTATION_SET__SET_RESULT_SET = eINSTANCE.getAnnotationSet_SetResultSet();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.SetResultSetImpl <em>Set Result Set</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.SetResultSetImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSetResultSet()
       * @generated
       */
      EClass SET_RESULT_SET = eINSTANCE.getSetResultSet();

      /**
       * The meta object literal for the '<em><b>Annotation Sets</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference SET_RESULT_SET__ANNOTATION_SETS = eINSTANCE.getSetResultSet_AnnotationSets();

      /**
       * The meta object literal for the '<em><b>Parent Annotation</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference SET_RESULT_SET__PARENT_ANNOTATION = eINSTANCE.getSetResultSet_ParentAnnotation();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.StringToEObjectMapImpl <em>String To EObject Map</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.StringToEObjectMapImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getStringToEObjectMap()
       * @generated
       */
      EClass STRING_TO_EOBJECT_MAP = eINSTANCE.getStringToEObjectMap();

      /**
       * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute STRING_TO_EOBJECT_MAP__KEY = eINSTANCE.getStringToEObjectMap_Key();

      /**
       * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference STRING_TO_EOBJECT_MAP__VALUE = eINSTANCE.getStringToEObjectMap_Value();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.AnnotationEngineImpl <em>Annotation Engine</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationEngineImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getAnnotationEngine()
       * @generated
       */
      EClass ANNOTATION_ENGINE = eINSTANCE.getAnnotationEngine();

      /**
       * The meta object literal for the '<em><b>Failed Applications</b></em>' map feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ANNOTATION_ENGINE__FAILED_APPLICATIONS = eINSTANCE.getAnnotationEngine_FailedApplications();

      /**
       * The meta object literal for the '<em><b>Found Annotations</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference ANNOTATION_ENGINE__FOUND_ANNOTATIONS = eINSTANCE.getAnnotationEngine_FoundAnnotations();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.SatisfiedConstraintImpl <em>Satisfied Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.SatisfiedConstraintImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSatisfiedConstraint()
       * @generated
       */
      EClass SATISFIED_CONSTRAINT = eINSTANCE.getSatisfiedConstraint();

      /**
       * The meta object literal for the '<em><b>Annotation</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference SATISFIED_CONSTRAINT__ANNOTATION = eINSTANCE.getSatisfiedConstraint_Annotation();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.SatisfiedAttributeConstraintImpl <em>Satisfied Attribute Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.SatisfiedAttributeConstraintImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSatisfiedAttributeConstraint()
       * @generated
       */
      EClass SATISFIED_ATTRIBUTE_CONSTRAINT = eINSTANCE.getSatisfiedAttributeConstraint();

      /**
       * The meta object literal for the '<em><b>Node ID</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute SATISFIED_ATTRIBUTE_CONSTRAINT__NODE_ID = eINSTANCE.getSatisfiedAttributeConstraint_NodeID();

      /**
       * The meta object literal for the '<em><b>Attribute Index</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute SATISFIED_ATTRIBUTE_CONSTRAINT__ATTRIBUTE_INDEX = eINSTANCE.getSatisfiedAttributeConstraint_AttributeIndex();

      /**
       * The meta object literal for the '<em><b>Context</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference SATISFIED_ATTRIBUTE_CONSTRAINT__CONTEXT = eINSTANCE.getSatisfiedAttributeConstraint_Context();

      /**
       * The meta object literal for the '<em><b>Constraint</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference SATISFIED_ATTRIBUTE_CONSTRAINT__CONSTRAINT = eINSTANCE.getSatisfiedAttributeConstraint_Constraint();

      /**
       * The meta object literal for the '{@link org.reclipse.structure.inference.annotations.impl.SatisfiedSpecificationConstraintImpl <em>Satisfied Specification Constraint</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.reclipse.structure.inference.annotations.impl.SatisfiedSpecificationConstraintImpl
       * @see org.reclipse.structure.inference.annotations.impl.AnnotationsPackageImpl#getSatisfiedSpecificationConstraint()
       * @generated
       */
      EClass SATISFIED_SPECIFICATION_CONSTRAINT = eINSTANCE.getSatisfiedSpecificationConstraint();

      /**
       * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute SATISFIED_SPECIFICATION_CONSTRAINT__EXPRESSION = eINSTANCE.getSatisfiedSpecificationConstraint_Expression();

      /**
       * The meta object literal for the '<em><b>Constraint</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference SATISFIED_SPECIFICATION_CONSTRAINT__CONSTRAINT = eINSTANCE.getSatisfiedSpecificationConstraint_Constraint();

   }

} //AnnotationsPackage
