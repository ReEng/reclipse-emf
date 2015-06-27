/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.specification.SpecificationPackage
 * @generated
 */
public interface SpecificationFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   SpecificationFactory eINSTANCE = org.reclipse.structure.specification.impl.SpecificationFactoryImpl.init();

   /**
    * Returns a new object of class '<em>PS Catalog</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Catalog</em>'.
    * @generated
    */
   PSCatalog createPSCatalog();

   /**
    * Returns a new object of class '<em>PS Pattern Specification</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Pattern Specification</em>'.
    * @generated
    */
   PSPatternSpecification createPSPatternSpecification();

   /**
    * Returns a new object of class '<em>PS Link</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Link</em>'.
    * @generated
    */
   PSLink createPSLink();

   /**
    * Returns a new object of class '<em>PS Path</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Path</em>'.
    * @generated
    */
   PSPath createPSPath();

   /**
    * Returns a new object of class '<em>PS Combined Fragment</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Combined Fragment</em>'.
    * @generated
    */
   PSCombinedFragment createPSCombinedFragment();

   /**
    * Returns a new object of class '<em>PS Specification Constraint</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Specification Constraint</em>'.
    * @generated
    */
   PSSpecificationConstraint createPSSpecificationConstraint();

   /**
    * Returns a new object of class '<em>PS Object</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Object</em>'.
    * @generated
    */
   PSObject createPSObject();

   /**
    * Returns a new object of class '<em>PS Annotation</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Annotation</em>'.
    * @generated
    */
   PSAnnotation createPSAnnotation();

   /**
    * Returns a new object of class '<em>PS Fuzzy Metric Constraint</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Fuzzy Metric Constraint</em>'.
    * @generated
    */
   PSFuzzyMetricConstraint createPSFuzzyMetricConstraint();

   /**
    * Returns a new object of class '<em>PS Fuzzy Set Rating Constraint</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Fuzzy Set Rating Constraint</em>'.
    * @generated
    */
   PSFuzzySetRatingConstraint createPSFuzzySetRatingConstraint();

   /**
    * Returns a new object of class '<em>PS Attribute Constraint</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Attribute Constraint</em>'.
    * @generated
    */
   PSAttributeConstraint createPSAttributeConstraint();

   /**
    * Returns a new object of class '<em>PS Metric Constraint</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Metric Constraint</em>'.
    * @generated
    */
   PSMetricConstraint createPSMetricConstraint();

   /**
    * Returns a new object of class '<em>PS Function Parameter</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>PS Function Parameter</em>'.
    * @generated
    */
   PSFunctionParameter createPSFunctionParameter();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   SpecificationPackage getSpecificationPackage();

} //SpecificationFactory
