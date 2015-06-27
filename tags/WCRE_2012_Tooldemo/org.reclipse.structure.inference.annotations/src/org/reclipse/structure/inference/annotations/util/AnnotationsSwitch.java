/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.inference.annotations.util;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.inference.annotations.AnnotationsPackage
 * @generated
 */
public class AnnotationsSwitch<T>
{
   /**
    * The cached model package
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static AnnotationsPackage modelPackage;

   /**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public AnnotationsSwitch()
   {
      if (modelPackage == null)
      {
         modelPackage = AnnotationsPackage.eINSTANCE;
      }
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   public T doSwitch(EObject theEObject)
   {
      return doSwitch(theEObject.eClass(), theEObject);
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   protected T doSwitch(EClass theEClass, EObject theEObject)
   {
      if (theEClass.eContainer() == modelPackage)
      {
         return doSwitch(theEClass.getClassifierID(), theEObject);
      }
      else
      {
         List<EClass> eSuperTypes = theEClass.getESuperTypes();
         return
            eSuperTypes.isEmpty() ?
               defaultCase(theEObject) :
               doSwitch(eSuperTypes.get(0), theEObject);
      }
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   protected T doSwitch(int classifierID, EObject theEObject)
   {
      switch (classifierID)
      {
         case AnnotationsPackage.ASG_ANNOTATION:
         {
            ASGAnnotation asgAnnotation = (ASGAnnotation)theEObject;
            T result = caseASGAnnotation(asgAnnotation);
            if (result == null) result = caseEAnnotation(asgAnnotation);
            if (result == null) result = caseENamedElement(asgAnnotation);
            if (result == null) result = caseEModelElement(asgAnnotation);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.TEMPORARY_ANNOTATION:
         {
            TemporaryAnnotation temporaryAnnotation = (TemporaryAnnotation)theEObject;
            T result = caseTemporaryAnnotation(temporaryAnnotation);
            if (result == null) result = caseASGAnnotation(temporaryAnnotation);
            if (result == null) result = caseEAnnotation(temporaryAnnotation);
            if (result == null) result = caseENamedElement(temporaryAnnotation);
            if (result == null) result = caseEModelElement(temporaryAnnotation);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.SET_INSTANCE_ANNOTATION:
         {
            SetInstanceAnnotation setInstanceAnnotation = (SetInstanceAnnotation)theEObject;
            T result = caseSetInstanceAnnotation(setInstanceAnnotation);
            if (result == null) result = caseASGAnnotation(setInstanceAnnotation);
            if (result == null) result = caseEAnnotation(setInstanceAnnotation);
            if (result == null) result = caseENamedElement(setInstanceAnnotation);
            if (result == null) result = caseEModelElement(setInstanceAnnotation);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.ANNOTATION_SET:
         {
            AnnotationSet annotationSet = (AnnotationSet)theEObject;
            T result = caseAnnotationSet(annotationSet);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.SET_RESULT_SET:
         {
            SetResultSet setResultSet = (SetResultSet)theEObject;
            T result = caseSetResultSet(setResultSet);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.STRING_TO_EOBJECT_MAP:
         {
            @SuppressWarnings("unchecked") Map.Entry<String, EList<EObject>> stringToEObjectMap = (Map.Entry<String, EList<EObject>>)theEObject;
            T result = caseStringToEObjectMap(stringToEObjectMap);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.ANNOTATION_ENGINE:
         {
            AnnotationEngine annotationEngine = (AnnotationEngine)theEObject;
            T result = caseAnnotationEngine(annotationEngine);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.SATISFIED_CONSTRAINT:
         {
            SatisfiedConstraint satisfiedConstraint = (SatisfiedConstraint)theEObject;
            T result = caseSatisfiedConstraint(satisfiedConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.SATISFIED_ATTRIBUTE_CONSTRAINT:
         {
            SatisfiedAttributeConstraint satisfiedAttributeConstraint = (SatisfiedAttributeConstraint)theEObject;
            T result = caseSatisfiedAttributeConstraint(satisfiedAttributeConstraint);
            if (result == null) result = caseSatisfiedConstraint(satisfiedAttributeConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case AnnotationsPackage.SATISFIED_SPECIFICATION_CONSTRAINT:
         {
            SatisfiedSpecificationConstraint satisfiedSpecificationConstraint = (SatisfiedSpecificationConstraint)theEObject;
            T result = caseSatisfiedSpecificationConstraint(satisfiedSpecificationConstraint);
            if (result == null) result = caseSatisfiedConstraint(satisfiedSpecificationConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         default: return defaultCase(theEObject);
      }
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>ASG Annotation</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>ASG Annotation</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseASGAnnotation(ASGAnnotation object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Temporary Annotation</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Temporary Annotation</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseTemporaryAnnotation(TemporaryAnnotation object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Set Instance Annotation</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Set Instance Annotation</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseSetInstanceAnnotation(SetInstanceAnnotation object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Annotation Set</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Annotation Set</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseAnnotationSet(AnnotationSet object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Set Result Set</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Set Result Set</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseSetResultSet(SetResultSet object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>String To EObject Map</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>String To EObject Map</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseStringToEObjectMap(Map.Entry<String, EList<EObject>> object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Annotation Engine</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Annotation Engine</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseAnnotationEngine(AnnotationEngine object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Satisfied Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Satisfied Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseSatisfiedConstraint(SatisfiedConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Satisfied Attribute Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Satisfied Attribute Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseSatisfiedAttributeConstraint(SatisfiedAttributeConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Satisfied Specification Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Satisfied Specification Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseSatisfiedSpecificationConstraint(SatisfiedSpecificationConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseEModelElement(EModelElement object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>EAnnotation</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>EAnnotation</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseEAnnotation(EAnnotation object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseENamedElement(ENamedElement object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch, but this is the last case anyway.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject)
    * @generated
    */
   public T defaultCase(EObject object)
   {
      return null;
   }

} //AnnotationsSwitch
