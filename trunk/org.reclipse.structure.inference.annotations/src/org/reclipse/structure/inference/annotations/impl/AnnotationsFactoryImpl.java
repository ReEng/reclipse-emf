/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.impl;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationEngine;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.inference.annotations.AnnotationsFactory;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint;
import org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint;
import org.reclipse.structure.inference.annotations.SetInstanceAnnotation;
import org.reclipse.structure.inference.annotations.SetResultSet;
import org.reclipse.structure.inference.annotations.TemporaryAnnotation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AnnotationsFactoryImpl extends EFactoryImpl implements AnnotationsFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static AnnotationsFactory init()
   {
      try
      {
         AnnotationsFactory theAnnotationsFactory = (AnnotationsFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.reclipse.structure.inference.annotations"); 
         if (theAnnotationsFactory != null)
         {
            return theAnnotationsFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new AnnotationsFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationsFactoryImpl()
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
         case AnnotationsPackage.ASG_ANNOTATION: return createASGAnnotation();
         case AnnotationsPackage.TEMPORARY_ANNOTATION: return createTemporaryAnnotation();
         case AnnotationsPackage.SET_INSTANCE_ANNOTATION: return createSetInstanceAnnotation();
         case AnnotationsPackage.ANNOTATION_SET: return createAnnotationSet();
         case AnnotationsPackage.SET_RESULT_SET: return createSetResultSet();
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP: return (EObject)createStringToEObjectMap();
         case AnnotationsPackage.ANNOTATION_ENGINE: return createAnnotationEngine();
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT: return createSatisfiedAttributeConstraint();
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT: return createSatisfiedSpecificationConstraint();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ASGAnnotation createASGAnnotation()
   {
      ASGAnnotationImpl asgAnnotation = new ASGAnnotationImpl();
      return asgAnnotation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TemporaryAnnotation createTemporaryAnnotation()
   {
      TemporaryAnnotationImpl temporaryAnnotation = new TemporaryAnnotationImpl();
      return temporaryAnnotation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SetInstanceAnnotation createSetInstanceAnnotation()
   {
      SetInstanceAnnotationImpl setInstanceAnnotation = new SetInstanceAnnotationImpl();
      return setInstanceAnnotation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationSet createAnnotationSet()
   {
      AnnotationSetImpl annotationSet = new AnnotationSetImpl();
      return annotationSet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SetResultSet createSetResultSet()
   {
      SetResultSetImpl setResultSet = new SetResultSetImpl();
      return setResultSet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Map.Entry<String, EList<EObject>> createStringToEObjectMap()
   {
      StringToEObjectMapImpl stringToEObjectMap = new StringToEObjectMapImpl();
      return stringToEObjectMap;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationEngine createAnnotationEngine()
   {
      AnnotationEngineImpl annotationEngine = new AnnotationEngineImpl();
      return annotationEngine;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SatisfiedAttributeConstraint createSatisfiedAttributeConstraint()
   {
      SatisfiedAttributeConstraintImpl satisfiedAttributeConstraint = new SatisfiedAttributeConstraintImpl();
      return satisfiedAttributeConstraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SatisfiedSpecificationConstraint createSatisfiedSpecificationConstraint()
   {
      SatisfiedSpecificationConstraintImpl satisfiedSpecificationConstraint = new SatisfiedSpecificationConstraintImpl();
      return satisfiedSpecificationConstraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationsPackage getAnnotationsPackage()
   {
      return (AnnotationsPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static AnnotationsPackage getPackage()
   {
      return AnnotationsPackage.eINSTANCE;
   }

} //AnnotationsFactoryImpl
