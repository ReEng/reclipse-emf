/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage
 * @generated
 */
public interface BehavioralpatternFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   BehavioralpatternFactory eINSTANCE = org.reclipse.behavior.specification.impl.BehavioralpatternFactoryImpl.init();

   /**
    * Returns a new object of class '<em>BP Any Object</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>BP Any Object</em>'.
    * @generated
    */
   BPAnyObject createBPAnyObject();

   /**
    * Returns a new object of class '<em>BP Object</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>BP Object</em>'.
    * @generated
    */
   BPObject createBPObject();

   /**
    * Returns a new object of class '<em>BP Set Object</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>BP Set Object</em>'.
    * @generated
    */
   BPSetObject createBPSetObject();

   /**
    * Returns a new object of class '<em>BP Assignment</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>BP Assignment</em>'.
    * @generated
    */
   BPAssignment createBPAssignment();

   /**
    * Returns a new object of class '<em>Behavioral Pattern</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Behavioral Pattern</em>'.
    * @generated
    */
   BehavioralPattern createBehavioralPattern();

   /**
    * Returns a new object of class '<em>BP Message</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>BP Message</em>'.
    * @generated
    */
   BPMessage createBPMessage();

   /**
    * Returns a new object of class '<em>BP Argument</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>BP Argument</em>'.
    * @generated
    */
   BPArgument createBPArgument();

   /**
    * Returns a new object of class '<em>BP Each Fragment</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>BP Each Fragment</em>'.
    * @generated
    */
   BPEachFragment createBPEachFragment();

   /**
    * Returns a new object of class '<em>BP Catalog</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>BP Catalog</em>'.
    * @generated
    */
   BPCatalog createBPCatalog();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   BehavioralpatternPackage getBehavioralpatternPackage();

} //BehavioralpatternFactory
