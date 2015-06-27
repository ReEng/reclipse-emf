/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage
 * @generated
 */
public interface AnnotationsFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   AnnotationsFactory eINSTANCE = org.reclipse.structure.inference.annotations.impl.AnnotationsFactoryImpl.init();

   /**
    * Returns a new object of class '<em>ASG Annotation</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>ASG Annotation</em>'.
    * @generated
    */
   ASGAnnotation createASGAnnotation();

   /**
    * Returns a new object of class '<em>Temporary Annotation</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Temporary Annotation</em>'.
    * @generated
    */
   TemporaryAnnotation createTemporaryAnnotation();

   /**
    * Returns a new object of class '<em>Set Instance Annotation</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Set Instance Annotation</em>'.
    * @generated
    */
   SetInstanceAnnotation createSetInstanceAnnotation();

   /**
    * Returns a new object of class '<em>Annotation Set</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Annotation Set</em>'.
    * @generated
    */
   AnnotationSet createAnnotationSet();

   /**
    * Returns a new object of class '<em>Set Result Set</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Set Result Set</em>'.
    * @generated
    */
   SetResultSet createSetResultSet();

   /**
    * Returns a new object of class '<em>Annotation Engine</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Annotation Engine</em>'.
    * @generated
    */
   AnnotationEngine createAnnotationEngine();

   /**
    * Returns a new object of class '<em>Satisfied Attribute Constraint</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Satisfied Attribute Constraint</em>'.
    * @generated
    */
   SatisfiedAttributeConstraint createSatisfiedAttributeConstraint();

   /**
    * Returns a new object of class '<em>Satisfied Specification Constraint</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Satisfied Specification Constraint</em>'.
    * @generated
    */
   SatisfiedSpecificationConstraint createSatisfiedSpecificationConstraint();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   AnnotationsPackage getAnnotationsPackage();

} //AnnotationsFactory
