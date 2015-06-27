/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.reclipse.structure.specification.SpecificationPackage
 * @generated
 */
public class SpecificationAdapterFactory extends AdapterFactoryImpl
{
   /**
    * The cached model package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static SpecificationPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SpecificationAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = SpecificationPackage.eINSTANCE;
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
   protected SpecificationSwitch<Adapter> modelSwitch =
      new SpecificationSwitch<Adapter>()
      {
         @Override
         public Adapter casePSCatalog(PSCatalog object)
         {
            return createPSCatalogAdapter();
         }
         @Override
         public Adapter casePSPatternSpecification(PSPatternSpecification object)
         {
            return createPSPatternSpecificationAdapter();
         }
         @Override
         public Adapter casePSItem(PSItem object)
         {
            return createPSItemAdapter();
         }
         @Override
         public Adapter casePSConnection(PSConnection object)
         {
            return createPSConnectionAdapter();
         }
         @Override
         public Adapter casePSLink(PSLink object)
         {
            return createPSLinkAdapter();
         }
         @Override
         public Adapter casePSPath(PSPath object)
         {
            return createPSPathAdapter();
         }
         @Override
         public Adapter casePSCombinedFragmentItem(PSCombinedFragmentItem object)
         {
            return createPSCombinedFragmentItemAdapter();
         }
         @Override
         public Adapter casePSCombinedFragment(PSCombinedFragment object)
         {
            return createPSCombinedFragmentAdapter();
         }
         @Override
         public Adapter casePSSpecificationConstraint(PSSpecificationConstraint object)
         {
            return createPSSpecificationConstraintAdapter();
         }
         @Override
         public Adapter casePSNode(PSNode object)
         {
            return createPSNodeAdapter();
         }
         @Override
         public Adapter casePSObject(PSObject object)
         {
            return createPSObjectAdapter();
         }
         @Override
         public Adapter casePSAnnotation(PSAnnotation object)
         {
            return createPSAnnotationAdapter();
         }
         @Override
         public Adapter casePSNodeConstraint(PSNodeConstraint object)
         {
            return createPSNodeConstraintAdapter();
         }
         @Override
         public Adapter casePSFuzzyConstraint(PSFuzzyConstraint object)
         {
            return createPSFuzzyConstraintAdapter();
         }
         @Override
         public Adapter casePSFuzzyMetricConstraint(PSFuzzyMetricConstraint object)
         {
            return createPSFuzzyMetricConstraintAdapter();
         }
         @Override
         public Adapter casePSFuzzySetRatingConstraint(PSFuzzySetRatingConstraint object)
         {
            return createPSFuzzySetRatingConstraintAdapter();
         }
         @Override
         public Adapter casePSBooleanConstraint(PSBooleanConstraint object)
         {
            return createPSBooleanConstraintAdapter();
         }
         @Override
         public Adapter casePSAttributeConstraint(PSAttributeConstraint object)
         {
            return createPSAttributeConstraintAdapter();
         }
         @Override
         public Adapter casePSMetricConstraint(PSMetricConstraint object)
         {
            return createPSMetricConstraintAdapter();
         }
         @Override
         public Adapter casePSFunctionParameter(PSFunctionParameter object)
         {
            return createPSFunctionParameterAdapter();
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
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSCatalog <em>PS Catalog</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSCatalog
    * @generated
    */
   public Adapter createPSCatalogAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSPatternSpecification <em>PS Pattern Specification</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSPatternSpecification
    * @generated
    */
   public Adapter createPSPatternSpecificationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSItem <em>PS Item</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSItem
    * @generated
    */
   public Adapter createPSItemAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSConnection <em>PS Connection</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSConnection
    * @generated
    */
   public Adapter createPSConnectionAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSLink <em>PS Link</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSLink
    * @generated
    */
   public Adapter createPSLinkAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSPath <em>PS Path</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSPath
    * @generated
    */
   public Adapter createPSPathAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSCombinedFragmentItem <em>PS Combined Fragment Item</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSCombinedFragmentItem
    * @generated
    */
   public Adapter createPSCombinedFragmentItemAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSCombinedFragment <em>PS Combined Fragment</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSCombinedFragment
    * @generated
    */
   public Adapter createPSCombinedFragmentAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSSpecificationConstraint <em>PS Specification Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSSpecificationConstraint
    * @generated
    */
   public Adapter createPSSpecificationConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSNode <em>PS Node</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSNode
    * @generated
    */
   public Adapter createPSNodeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSObject <em>PS Object</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSObject
    * @generated
    */
   public Adapter createPSObjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSAnnotation <em>PS Annotation</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSAnnotation
    * @generated
    */
   public Adapter createPSAnnotationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSNodeConstraint <em>PS Node Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSNodeConstraint
    * @generated
    */
   public Adapter createPSNodeConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSFuzzyConstraint <em>PS Fuzzy Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSFuzzyConstraint
    * @generated
    */
   public Adapter createPSFuzzyConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSFuzzyMetricConstraint <em>PS Fuzzy Metric Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSFuzzyMetricConstraint
    * @generated
    */
   public Adapter createPSFuzzyMetricConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSFuzzySetRatingConstraint <em>PS Fuzzy Set Rating Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSFuzzySetRatingConstraint
    * @generated
    */
   public Adapter createPSFuzzySetRatingConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSBooleanConstraint <em>PS Boolean Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSBooleanConstraint
    * @generated
    */
   public Adapter createPSBooleanConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSAttributeConstraint <em>PS Attribute Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSAttributeConstraint
    * @generated
    */
   public Adapter createPSAttributeConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSMetricConstraint <em>PS Metric Constraint</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSMetricConstraint
    * @generated
    */
   public Adapter createPSMetricConstraintAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.reclipse.structure.specification.PSFunctionParameter <em>PS Function Parameter</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.reclipse.structure.specification.PSFunctionParameter
    * @generated
    */
   public Adapter createPSFunctionParameterAdapter()
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

} //SpecificationAdapterFactory
