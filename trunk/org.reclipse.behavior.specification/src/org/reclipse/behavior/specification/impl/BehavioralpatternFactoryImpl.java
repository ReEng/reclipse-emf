/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.reclipse.behavior.specification.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BehavioralpatternFactoryImpl extends EFactoryImpl implements BehavioralpatternFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static BehavioralpatternFactory init()
   {
      try
      {
         BehavioralpatternFactory theBehavioralpatternFactory = (BehavioralpatternFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.reclipse.behavior.specification"); 
         if (theBehavioralpatternFactory != null)
         {
            return theBehavioralpatternFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new BehavioralpatternFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BehavioralpatternFactoryImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case BehavioralpatternPackage.BP_ANY_OBJECT: return createBPAnyObject();
         case BehavioralpatternPackage.BP_OBJECT: return createBPObject();
         case BehavioralpatternPackage.BP_SET_OBJECT: return createBPSetObject();
         case BehavioralpatternPackage.BP_ASSIGNMENT: return createBPAssignment();
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN: return createBehavioralPattern();
         case BehavioralpatternPackage.BP_MESSAGE: return createBPMessage();
         case BehavioralpatternPackage.BP_ARGUMENT: return createBPArgument();
         case BehavioralpatternPackage.BP_EACH_FRAGMENT: return createBPEachFragment();
         case BehavioralpatternPackage.BP_CATALOG: return createBPCatalog();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPAnyObject createBPAnyObject()
   {
      BPAnyObjectImpl bpAnyObject = new BPAnyObjectImpl();
      return bpAnyObject;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPObject createBPObject()
   {
      BPObjectImpl bpObject = new BPObjectImpl();
      return bpObject;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPSetObject createBPSetObject()
   {
      BPSetObjectImpl bpSetObject = new BPSetObjectImpl();
      return bpSetObject;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPAssignment createBPAssignment()
   {
      BPAssignmentImpl bpAssignment = new BPAssignmentImpl();
      return bpAssignment;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BehavioralPattern createBehavioralPattern()
   {
      BehavioralPatternImpl behavioralPattern = new BehavioralPatternImpl();
      return behavioralPattern;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPMessage createBPMessage()
   {
      BPMessageImpl bpMessage = new BPMessageImpl();
      return bpMessage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPArgument createBPArgument()
   {
      BPArgumentImpl bpArgument = new BPArgumentImpl();
      return bpArgument;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPEachFragment createBPEachFragment()
   {
      BPEachFragmentImpl bpEachFragment = new BPEachFragmentImpl();
      return bpEachFragment;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BPCatalog createBPCatalog()
   {
      BPCatalogImpl bpCatalog = new BPCatalogImpl();
      return bpCatalog;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BehavioralpatternPackage getBehavioralpatternPackage()
   {
      return (BehavioralpatternPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static BehavioralpatternPackage getPackage()
   {
      return BehavioralpatternPackage.eINSTANCE;
   }

} //BehavioralpatternFactoryImpl
