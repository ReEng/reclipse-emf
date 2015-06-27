/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PS Metric Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.reclipse.structure.specification.PSMetricConstraint#getMetricAcronym <em>Metric Acronym</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.reclipse.structure.specification.SpecificationPackage#getPSMetricConstraint()
 * @model
 * @generated
 */
public interface PSMetricConstraint extends PSBooleanConstraint
{
   /**
    * Returns the value of the '<em><b>Metric Acronym</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Metric Acronym</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Metric Acronym</em>' attribute.
    * @see #setMetricAcronym(String)
    * @see org.reclipse.structure.specification.SpecificationPackage#getPSMetricConstraint_MetricAcronym()
    * @model unique="false" required="true" ordered="false"
    * @generated
    */
   String getMetricAcronym();

   /**
    * Sets the value of the '{@link org.reclipse.structure.specification.PSMetricConstraint#getMetricAcronym <em>Metric Acronym</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Metric Acronym</em>' attribute.
    * @see #getMetricAcronym()
    * @generated
    */
   void setMetricAcronym(String value);

} // PSMetricConstraint
