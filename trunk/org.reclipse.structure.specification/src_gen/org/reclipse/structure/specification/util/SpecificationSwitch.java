/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.fujaba.commons.identifier.Identifier;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSItem;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.SpecificationPackage;


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
 * @see org.reclipse.structure.specification.SpecificationPackage
 * @generated
 */
public class SpecificationSwitch<T>
{
   /**
    * The cached model package
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static SpecificationPackage modelPackage;

   /**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SpecificationSwitch()
   {
      if (modelPackage == null)
      {
         modelPackage = SpecificationPackage.eINSTANCE;
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
         case SpecificationPackage.PS_CATALOG:
         {
            PSCatalog psCatalog = (PSCatalog)theEObject;
            T result = casePSCatalog(psCatalog);
            if (result == null) result = caseIdentifier(psCatalog);
            if (result == null) result = caseEModelElement(psCatalog);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_PATTERN_SPECIFICATION:
         {
            PSPatternSpecification psPatternSpecification = (PSPatternSpecification)theEObject;
            T result = casePSPatternSpecification(psPatternSpecification);
            if (result == null) result = caseIdentifier(psPatternSpecification);
            if (result == null) result = caseEModelElement(psPatternSpecification);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_ITEM:
         {
            PSItem psItem = (PSItem)theEObject;
            T result = casePSItem(psItem);
            if (result == null) result = caseIdentifier(psItem);
            if (result == null) result = caseEModelElement(psItem);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_CONNECTION:
         {
            PSConnection psConnection = (PSConnection)theEObject;
            T result = casePSConnection(psConnection);
            if (result == null) result = casePSItem(psConnection);
            if (result == null) result = caseIdentifier(psConnection);
            if (result == null) result = caseEModelElement(psConnection);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_LINK:
         {
            PSLink psLink = (PSLink)theEObject;
            T result = casePSLink(psLink);
            if (result == null) result = casePSConnection(psLink);
            if (result == null) result = casePSItem(psLink);
            if (result == null) result = caseIdentifier(psLink);
            if (result == null) result = caseEModelElement(psLink);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_PATH:
         {
            PSPath psPath = (PSPath)theEObject;
            T result = casePSPath(psPath);
            if (result == null) result = casePSConnection(psPath);
            if (result == null) result = casePSItem(psPath);
            if (result == null) result = caseIdentifier(psPath);
            if (result == null) result = caseEModelElement(psPath);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_COMBINED_FRAGMENT_ITEM:
         {
            PSCombinedFragmentItem psCombinedFragmentItem = (PSCombinedFragmentItem)theEObject;
            T result = casePSCombinedFragmentItem(psCombinedFragmentItem);
            if (result == null) result = casePSItem(psCombinedFragmentItem);
            if (result == null) result = caseIdentifier(psCombinedFragmentItem);
            if (result == null) result = caseEModelElement(psCombinedFragmentItem);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_COMBINED_FRAGMENT:
         {
            PSCombinedFragment psCombinedFragment = (PSCombinedFragment)theEObject;
            T result = casePSCombinedFragment(psCombinedFragment);
            if (result == null) result = casePSCombinedFragmentItem(psCombinedFragment);
            if (result == null) result = casePSItem(psCombinedFragment);
            if (result == null) result = caseIdentifier(psCombinedFragment);
            if (result == null) result = caseEModelElement(psCombinedFragment);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT:
         {
            PSSpecificationConstraint psSpecificationConstraint = (PSSpecificationConstraint)theEObject;
            T result = casePSSpecificationConstraint(psSpecificationConstraint);
            if (result == null) result = casePSCombinedFragmentItem(psSpecificationConstraint);
            if (result == null) result = casePSItem(psSpecificationConstraint);
            if (result == null) result = caseIdentifier(psSpecificationConstraint);
            if (result == null) result = caseEModelElement(psSpecificationConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_NODE:
         {
            PSNode psNode = (PSNode)theEObject;
            T result = casePSNode(psNode);
            if (result == null) result = casePSCombinedFragmentItem(psNode);
            if (result == null) result = casePSItem(psNode);
            if (result == null) result = caseIdentifier(psNode);
            if (result == null) result = caseEModelElement(psNode);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_OBJECT:
         {
            PSObject psObject = (PSObject)theEObject;
            T result = casePSObject(psObject);
            if (result == null) result = casePSNode(psObject);
            if (result == null) result = casePSCombinedFragmentItem(psObject);
            if (result == null) result = casePSItem(psObject);
            if (result == null) result = caseIdentifier(psObject);
            if (result == null) result = caseEModelElement(psObject);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_ANNOTATION:
         {
            PSAnnotation psAnnotation = (PSAnnotation)theEObject;
            T result = casePSAnnotation(psAnnotation);
            if (result == null) result = casePSNode(psAnnotation);
            if (result == null) result = casePSCombinedFragmentItem(psAnnotation);
            if (result == null) result = casePSItem(psAnnotation);
            if (result == null) result = caseIdentifier(psAnnotation);
            if (result == null) result = caseEModelElement(psAnnotation);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_NODE_CONSTRAINT:
         {
            PSNodeConstraint psNodeConstraint = (PSNodeConstraint)theEObject;
            T result = casePSNodeConstraint(psNodeConstraint);
            if (result == null) result = casePSItem(psNodeConstraint);
            if (result == null) result = caseIdentifier(psNodeConstraint);
            if (result == null) result = caseEModelElement(psNodeConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_FUZZY_CONSTRAINT:
         {
            PSFuzzyConstraint psFuzzyConstraint = (PSFuzzyConstraint)theEObject;
            T result = casePSFuzzyConstraint(psFuzzyConstraint);
            if (result == null) result = casePSNodeConstraint(psFuzzyConstraint);
            if (result == null) result = casePSItem(psFuzzyConstraint);
            if (result == null) result = caseIdentifier(psFuzzyConstraint);
            if (result == null) result = caseEModelElement(psFuzzyConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_FUZZY_METRIC_CONSTRAINT:
         {
            PSFuzzyMetricConstraint psFuzzyMetricConstraint = (PSFuzzyMetricConstraint)theEObject;
            T result = casePSFuzzyMetricConstraint(psFuzzyMetricConstraint);
            if (result == null) result = casePSFuzzyConstraint(psFuzzyMetricConstraint);
            if (result == null) result = casePSNodeConstraint(psFuzzyMetricConstraint);
            if (result == null) result = casePSItem(psFuzzyMetricConstraint);
            if (result == null) result = caseIdentifier(psFuzzyMetricConstraint);
            if (result == null) result = caseEModelElement(psFuzzyMetricConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT:
         {
            PSFuzzySetRatingConstraint psFuzzySetRatingConstraint = (PSFuzzySetRatingConstraint)theEObject;
            T result = casePSFuzzySetRatingConstraint(psFuzzySetRatingConstraint);
            if (result == null) result = casePSFuzzyConstraint(psFuzzySetRatingConstraint);
            if (result == null) result = casePSNodeConstraint(psFuzzySetRatingConstraint);
            if (result == null) result = casePSItem(psFuzzySetRatingConstraint);
            if (result == null) result = caseIdentifier(psFuzzySetRatingConstraint);
            if (result == null) result = caseEModelElement(psFuzzySetRatingConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_BOOLEAN_CONSTRAINT:
         {
            PSBooleanConstraint psBooleanConstraint = (PSBooleanConstraint)theEObject;
            T result = casePSBooleanConstraint(psBooleanConstraint);
            if (result == null) result = casePSNodeConstraint(psBooleanConstraint);
            if (result == null) result = casePSItem(psBooleanConstraint);
            if (result == null) result = caseIdentifier(psBooleanConstraint);
            if (result == null) result = caseEModelElement(psBooleanConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT:
         {
            PSAttributeConstraint psAttributeConstraint = (PSAttributeConstraint)theEObject;
            T result = casePSAttributeConstraint(psAttributeConstraint);
            if (result == null) result = casePSBooleanConstraint(psAttributeConstraint);
            if (result == null) result = casePSNodeConstraint(psAttributeConstraint);
            if (result == null) result = casePSItem(psAttributeConstraint);
            if (result == null) result = caseIdentifier(psAttributeConstraint);
            if (result == null) result = caseEModelElement(psAttributeConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_METRIC_CONSTRAINT:
         {
            PSMetricConstraint psMetricConstraint = (PSMetricConstraint)theEObject;
            T result = casePSMetricConstraint(psMetricConstraint);
            if (result == null) result = casePSBooleanConstraint(psMetricConstraint);
            if (result == null) result = casePSNodeConstraint(psMetricConstraint);
            if (result == null) result = casePSItem(psMetricConstraint);
            if (result == null) result = caseIdentifier(psMetricConstraint);
            if (result == null) result = caseEModelElement(psMetricConstraint);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case SpecificationPackage.PS_FUNCTION_PARAMETER:
         {
            PSFunctionParameter psFunctionParameter = (PSFunctionParameter)theEObject;
            T result = casePSFunctionParameter(psFunctionParameter);
            if (result == null) result = caseIdentifier(psFunctionParameter);
            if (result == null) result = caseEModelElement(psFunctionParameter);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         default: return defaultCase(theEObject);
      }
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Catalog</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Catalog</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSCatalog(PSCatalog object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Pattern Specification</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Pattern Specification</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSPatternSpecification(PSPatternSpecification object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Item</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Item</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSItem(PSItem object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Connection</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Connection</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSConnection(PSConnection object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Link</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Link</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSLink(PSLink object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Path</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Path</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSPath(PSPath object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Combined Fragment Item</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Combined Fragment Item</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSCombinedFragmentItem(PSCombinedFragmentItem object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Combined Fragment</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Combined Fragment</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSCombinedFragment(PSCombinedFragment object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Specification Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Specification Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSSpecificationConstraint(PSSpecificationConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Node</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Node</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSNode(PSNode object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Object</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Object</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSObject(PSObject object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Annotation</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Annotation</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSAnnotation(PSAnnotation object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Node Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Node Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSNodeConstraint(PSNodeConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Fuzzy Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Fuzzy Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSFuzzyConstraint(PSFuzzyConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Fuzzy Metric Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Fuzzy Metric Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSFuzzyMetricConstraint(PSFuzzyMetricConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Fuzzy Set Rating Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Fuzzy Set Rating Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSFuzzySetRatingConstraint(PSFuzzySetRatingConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Boolean Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Boolean Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSBooleanConstraint(PSBooleanConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Attribute Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Attribute Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSAttributeConstraint(PSAttributeConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Metric Constraint</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Metric Constraint</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSMetricConstraint(PSMetricConstraint object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PS Function Parameter</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PS Function Parameter</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePSFunctionParameter(PSFunctionParameter object)
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
    * Returns the result of interpreting the object as an instance of '<em>Identifier</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Identifier</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseIdentifier(Identifier object)
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

} //SpecificationSwitch
