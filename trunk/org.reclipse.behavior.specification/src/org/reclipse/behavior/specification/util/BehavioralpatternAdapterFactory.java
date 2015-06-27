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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

import org.fujaba.commons.identifier.Identifier;
import org.reclipse.behavior.specification.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.reclipse.behavior.specification.BehavioralpatternPackage
 * @generated
 */
public class BehavioralpatternAdapterFactory extends AdapterFactoryImpl
{
   /**
    * The cached model package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static BehavioralpatternPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BehavioralpatternAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = BehavioralpatternPackage.eINSTANCE;
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
   protected BehavioralpatternSwitch<Adapter> modelSwitch =
      new BehavioralpatternSwitch<Adapter>()
      {
         @Override
         public Adapter caseBPAnyObject(BPAnyObject object)
         {
            return createBPAnyObjectAdapter();
         }
         @Override
         public Adapter caseBPObject(BPObject object)
         {
            return createBPObjectAdapter();
         }
         @Override
         public Adapter caseBPSetObject(BPSetObject object)
         {
            return createBPSetObjectAdapter();
         }
         @Override
         public Adapter caseBPAssignment(BPAssignment object)
         {
            return createBPAssignmentAdapter();
         }
         @Override
         public Adapter caseBehavioralPattern(BehavioralPattern object)
         {
            return createBehavioralPatternAdapter();
         }
         @Override
         public Adapter caseBPMessage(BPMessage object)
         {
            return createBPMessageAdapter();
         }
         @Override
         public Adapter caseBPArgument(BPArgument object)
         {
            return createBPArgumentAdapter();
         }
         @Override
         public Adapter caseBPEachFragment(BPEachFragment object)
         {
            return createBPEachFragmentAdapter();
         }
         @Override
         public Adapter caseBPCatalog(BPCatalog object)
         {
            return createBPCatalogAdapter();
         }
         @Override
         public Adapter caseEModelElement(EModelElement object)
         {
            return createEModelElementAdapter();
         }
         @Override
         public Adapter caseIdentifier(Identifier object)
         {
            return createIdentifierAdapter();
         }
         @Override
         public Adapter caseAbstractSequenceDiagramObject(AbstractSequenceDiagramObject object)
         {
            return createAbstractSequenceDiagramObjectAdapter();
         }
         @Override
         public Adapter caseFragment(Fragment object)
         {
            return createFragmentAdapter();
         }
         @Override
         public Adapter caseLifelineFragment(LifelineFragment object)
         {
            return createLifelineFragmentAdapter();
         }
         @Override
         public Adapter caseSequenceDiagram(SequenceDiagram object)
         {
            return createSequenceDiagramAdapter();
         }
         @Override
         public Adapter caseAbstractMessage(AbstractMessage object)
         {
            return createAbstractMessageAdapter();
         }
         @Override
         public Adapter caseCombinedFragment(CombinedFragment object)
         {
            return createCombinedFragmentAdapter();
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
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BPAnyObject <em>BP Any Object</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BPAnyObject
    * @generated
    */
   public Adapter createBPAnyObjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BPObject <em>BP Object</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BPObject
    * @generated
    */
   public Adapter createBPObjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BPSetObject <em>BP Set Object</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BPSetObject
    * @generated
    */
   public Adapter createBPSetObjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BPAssignment <em>BP Assignment</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BPAssignment
    * @generated
    */
   public Adapter createBPAssignmentAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BehavioralPattern <em>Behavioral Pattern</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BehavioralPattern
    * @generated
    */
   public Adapter createBehavioralPatternAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BPMessage <em>BP Message</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BPMessage
    * @generated
    */
   public Adapter createBPMessageAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BPArgument <em>BP Argument</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BPArgument
    * @generated
    */
   public Adapter createBPArgumentAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BPEachFragment <em>BP Each Fragment</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BPEachFragment
    * @generated
    */
   public Adapter createBPEachFragmentAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.behavior.specification.BPCatalog <em>BP Catalog</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.behavior.specification.BPCatalog
    * @generated
    */
   public Adapter createBPCatalogAdapter()
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
    * Creates a new adapter for an object of class '{@link org.fujaba.commons.identifier.Identifier <em>Identifier</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.fujaba.commons.identifier.Identifier
    * @generated
    */
   public Adapter createIdentifierAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject <em>Abstract Sequence Diagram Object</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject
    * @generated
    */
   public Adapter createAbstractSequenceDiagramObjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.uni_paderborn.basicSequenceDiagram.Fragment <em>Fragment</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.uni_paderborn.basicSequenceDiagram.Fragment
    * @generated
    */
   public Adapter createFragmentAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.uni_paderborn.basicSequenceDiagram.LifelineFragment <em>Lifeline Fragment</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.uni_paderborn.basicSequenceDiagram.LifelineFragment
    * @generated
    */
   public Adapter createLifelineFragmentAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.uni_paderborn.basicSequenceDiagram.SequenceDiagram <em>Sequence Diagram</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.uni_paderborn.basicSequenceDiagram.SequenceDiagram
    * @generated
    */
   public Adapter createSequenceDiagramAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.uni_paderborn.basicSequenceDiagram.AbstractMessage <em>Abstract Message</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.uni_paderborn.basicSequenceDiagram.AbstractMessage
    * @generated
    */
   public Adapter createAbstractMessageAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.uni_paderborn.basicSequenceDiagram.CombinedFragment <em>Combined Fragment</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.uni_paderborn.basicSequenceDiagram.CombinedFragment
    * @generated
    */
   public Adapter createCombinedFragmentAdapter()
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

} //BehavioralpatternAdapterFactory
