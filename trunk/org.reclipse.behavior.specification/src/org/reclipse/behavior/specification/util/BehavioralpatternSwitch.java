/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.util;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;
import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.LifelineFragment;
import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

import org.fujaba.commons.identifier.Identifier;
import org.reclipse.behavior.specification.*;

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
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage
 * @generated
 */
public class BehavioralpatternSwitch<T>
{
   /**
    * The cached model package
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static BehavioralpatternPackage modelPackage;

   /**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BehavioralpatternSwitch()
   {
      if (modelPackage == null)
      {
         modelPackage = BehavioralpatternPackage.eINSTANCE;
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
         case BehavioralpatternPackage.BP_ANY_OBJECT:
         {
            BPAnyObject bpAnyObject = (BPAnyObject)theEObject;
            T result = caseBPAnyObject(bpAnyObject);
            if (result == null) result = caseAbstractSequenceDiagramObject(bpAnyObject);
            if (result == null) result = caseIdentifier(bpAnyObject);
            if (result == null) result = caseEModelElement(bpAnyObject);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BehavioralpatternPackage.BP_OBJECT:
         {
            BPObject bpObject = (BPObject)theEObject;
            T result = caseBPObject(bpObject);
            if (result == null) result = caseAbstractSequenceDiagramObject(bpObject);
            if (result == null) result = caseIdentifier(bpObject);
            if (result == null) result = caseEModelElement(bpObject);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BehavioralpatternPackage.BP_SET_OBJECT:
         {
            BPSetObject bpSetObject = (BPSetObject)theEObject;
            T result = caseBPSetObject(bpSetObject);
            if (result == null) result = caseAbstractSequenceDiagramObject(bpSetObject);
            if (result == null) result = caseIdentifier(bpSetObject);
            if (result == null) result = caseEModelElement(bpSetObject);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BehavioralpatternPackage.BP_ASSIGNMENT:
         {
            BPAssignment bpAssignment = (BPAssignment)theEObject;
            T result = caseBPAssignment(bpAssignment);
            if (result == null) result = caseLifelineFragment(bpAssignment);
            if (result == null) result = caseFragment(bpAssignment);
            if (result == null) result = caseIdentifier(bpAssignment);
            if (result == null) result = caseEModelElement(bpAssignment);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN:
         {
            BehavioralPattern behavioralPattern = (BehavioralPattern)theEObject;
            T result = caseBehavioralPattern(behavioralPattern);
            if (result == null) result = caseSequenceDiagram(behavioralPattern);
            if (result == null) result = caseIdentifier(behavioralPattern);
            if (result == null) result = caseEModelElement(behavioralPattern);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BehavioralpatternPackage.BP_MESSAGE:
         {
            BPMessage bpMessage = (BPMessage)theEObject;
            T result = caseBPMessage(bpMessage);
            if (result == null) result = caseAbstractMessage(bpMessage);
            if (result == null) result = caseIdentifier(bpMessage);
            if (result == null) result = caseEModelElement(bpMessage);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BehavioralpatternPackage.BP_ARGUMENT:
         {
            BPArgument bpArgument = (BPArgument)theEObject;
            T result = caseBPArgument(bpArgument);
            if (result == null) result = caseIdentifier(bpArgument);
            if (result == null) result = caseEModelElement(bpArgument);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BehavioralpatternPackage.BP_EACH_FRAGMENT:
         {
            BPEachFragment bpEachFragment = (BPEachFragment)theEObject;
            T result = caseBPEachFragment(bpEachFragment);
            if (result == null) result = caseCombinedFragment(bpEachFragment);
            if (result == null) result = caseFragment(bpEachFragment);
            if (result == null) result = caseIdentifier(bpEachFragment);
            if (result == null) result = caseEModelElement(bpEachFragment);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case BehavioralpatternPackage.BP_CATALOG:
         {
            BPCatalog bpCatalog = (BPCatalog)theEObject;
            T result = caseBPCatalog(bpCatalog);
            if (result == null) result = caseIdentifier(bpCatalog);
            if (result == null) result = caseEModelElement(bpCatalog);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         default: return defaultCase(theEObject);
      }
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>BP Any Object</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>BP Any Object</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBPAnyObject(BPAnyObject object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>BP Object</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>BP Object</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBPObject(BPObject object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>BP Set Object</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>BP Set Object</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBPSetObject(BPSetObject object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>BP Assignment</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>BP Assignment</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBPAssignment(BPAssignment object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Behavioral Pattern</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Behavioral Pattern</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBehavioralPattern(BehavioralPattern object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>BP Message</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>BP Message</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBPMessage(BPMessage object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>BP Argument</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>BP Argument</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBPArgument(BPArgument object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>BP Each Fragment</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>BP Each Fragment</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBPEachFragment(BPEachFragment object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>BP Catalog</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>BP Catalog</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseBPCatalog(BPCatalog object)
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
    * Returns the result of interpreting the object as an instance of '<em>Abstract Sequence Diagram Object</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Abstract Sequence Diagram Object</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseAbstractSequenceDiagramObject(AbstractSequenceDiagramObject object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Fragment</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Fragment</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseFragment(Fragment object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Lifeline Fragment</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Lifeline Fragment</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseLifelineFragment(LifelineFragment object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Sequence Diagram</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Sequence Diagram</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseSequenceDiagram(SequenceDiagram object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Abstract Message</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Abstract Message</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseAbstractMessage(AbstractMessage object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Combined Fragment</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Combined Fragment</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseCombinedFragment(CombinedFragment object)
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

} //BehavioralpatternSwitch
