/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationEngine;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint;
import org.reclipse.structure.inference.annotations.SatisfiedConstraint;
import org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint;
import org.reclipse.structure.inference.annotations.SetInstanceAnnotation;
import org.reclipse.structure.inference.annotations.SetResultSet;
import org.reclipse.structure.inference.annotations.TemporaryAnnotation;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage
 * @generated
 */
public class AnnotationsAdapterFactory extends AdapterFactoryImpl
{
   /**
    * The cached model package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static AnnotationsPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationsAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = AnnotationsPackage.eINSTANCE;
      }
   }

   /**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
    * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
    * <!-- end-user-doc -->
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
   @Override
   public boolean isFactoryForType(Object object)
   {
      if (object == modelPackage)
      {
         return true;
      }
      if (object instanceof EObject)
      {
         return ((EObject)object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

   /**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected AnnotationsSwitch<Adapter> modelSwitch =
      new AnnotationsSwitch<Adapter>()
      {
         @Override
         public Adapter caseASGAnnotation(ASGAnnotation object)
         {
            return createASGAnnotationAdapter();
         }
         @Override
         public Adapter caseTemporaryAnnotation(TemporaryAnnotation object)
         {
            return createTemporaryAnnotationAdapter();
         }
         @Override
         public Adapter caseSetInstanceAnnotation(SetInstanceAnnotation object)
         {
            return createSetInstanceAnnotationAdapter();
         }
         @Override
         public Adapter caseAnnotationSet(AnnotationSet object)
         {
            return createAnnotationSetAdapter();
         }
         @Override
         public Adapter caseSetResultSet(SetResultSet object)
         {
            return createSetResultSetAdapter();
         }
         @Override
         public Adapter caseStringToEObjectMap(Map.Entry<String, EList<EObject>> object)
         {
            return createStringToEObjectMapAdapter();
         }
         @Override
         public Adapter caseAnnotationEngine(AnnotationEngine object)
         {
            return createAnnotationEngineAdapter();
         }
         @Override
         public Adapter caseSatisfiedConstraint(SatisfiedConstraint object)
         {
            return createSatisfiedConstraintAdapter();
         }
         @Override
         public Adapter caseSatisfiedAttributeConstraint(SatisfiedAttributeConstraint object)
         {
            return createSatisfiedAttributeConstraintAdapter();
         }
         @Override
         public Adapter caseSatisfiedSpecificationConstraint(SatisfiedSpecificationConstraint object)
         {
            return createSatisfiedSpecificationConstraintAdapter();
         }
         @Override
         public Adapter caseEModelElement(EModelElement object)
         {
            return createEModelElementAdapter();
         }
         @Override
         public Adapter caseEAnnotation(EAnnotation object)
         {
            return createEAnnotationAdapter();
         }
         @Override
         public Adapter caseENamedElement(ENamedElement object)
         {
            return createENamedElementAdapter();
         }
         @Override
         public Adapter defaultCase(EObject object)
         {
            return createEObjectAdapter();
         }
      };

   /**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
   @Override
   public Adapter createAdapter(Notifier target)
   {
      return modelSwitch.doSwitch((EObject)target);
   }


   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.ASGAnnotation <em>ASG Annotation</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.ASGAnnotation
    * @generated
    */
   public Adapter createASGAnnotationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.TemporaryAnnotation <em>Temporary Annotation</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.TemporaryAnnotation
    * @generated
    */
   public Adapter createTemporaryAnnotationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.SetInstanceAnnotation <em>Set Instance Annotation</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.SetInstanceAnnotation
    * @generated
    */
   public Adapter createSetInstanceAnnotationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.AnnotationSet <em>Annotation Set</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.AnnotationSet
    * @generated
    */
   public Adapter createAnnotationSetAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.SetResultSet <em>Set Result Set</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.SetResultSet
    * @generated
    */
   public Adapter createSetResultSetAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To EObject Map</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see java.util.Map.Entry
    * @generated
    */
   public Adapter createStringToEObjectMapAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.AnnotationEngine <em>Annotation Engine</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.AnnotationEngine
    * @generated
    */
   public Adapter createAnnotationEngineAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.SatisfiedConstraint <em>Satisfied Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.SatisfiedConstraint
    * @generated
    */
   public Adapter createSatisfiedConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint <em>Satisfied Attribute Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint
    * @generated
    */
   public Adapter createSatisfiedAttributeConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint <em>Satisfied Specification Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.inference.annotations.SatisfiedSpecificationConstraint
    * @generated
    */
   public Adapter createSatisfiedSpecificationConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.emf.ecore.EModelElement
    * @generated
    */
   public Adapter createEModelElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EAnnotation <em>EAnnotation</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.emf.ecore.EAnnotation
    * @generated
    */
   public Adapter createEAnnotationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>ENamed Element</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.emf.ecore.ENamedElement
    * @generated
    */
   public Adapter createENamedElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
    * This default implementation returns null.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @generated
    */
   public Adapter createEObjectAdapter()
   {
      return null;
   }

} //AnnotationsAdapterFactory
