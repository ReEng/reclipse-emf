/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.PatternType;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.SpecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SpecificationFactoryImpl extends EFactoryImpl implements SpecificationFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static SpecificationFactory init()
   {
      try
      {
         SpecificationFactory theSpecificationFactory = (SpecificationFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.reclipse.org/ns/specification"); 
         if (theSpecificationFactory != null)
         {
            return theSpecificationFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new SpecificationFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SpecificationFactoryImpl()
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
         case SpecificationPackage.PS_CATALOG: return createPSCatalog();
         case SpecificationPackage.PS_PATTERN_SPECIFICATION: return createPSPatternSpecification();
         case SpecificationPackage.PS_LINK: return createPSLink();
         case SpecificationPackage.PS_PATH: return createPSPath();
         case SpecificationPackage.PS_COMBINED_FRAGMENT: return createPSCombinedFragment();
         case SpecificationPackage.PS_SPECIFICATION_CONSTRAINT: return createPSSpecificationConstraint();
         case SpecificationPackage.PS_OBJECT: return createPSObject();
         case SpecificationPackage.PS_ANNOTATION: return createPSAnnotation();
         case SpecificationPackage.PS_FUZZY_METRIC_CONSTRAINT: return createPSFuzzyMetricConstraint();
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT: return createPSFuzzySetRatingConstraint();
         case SpecificationPackage.PS_ATTRIBUTE_CONSTRAINT: return createPSAttributeConstraint();
         case SpecificationPackage.PS_METRIC_CONSTRAINT: return createPSMetricConstraint();
         case SpecificationPackage.PS_FUNCTION_PARAMETER: return createPSFunctionParameter();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object createFromString(EDataType eDataType, String initialValue)
   {
      switch (eDataType.getClassifierID())
      {
         case SpecificationPackage.PATTERN_TYPE:
            return createPatternTypeFromString(eDataType, initialValue);
         case SpecificationPackage.MODIFIER_TYPE:
            return createModifierTypeFromString(eDataType, initialValue);
         case SpecificationPackage.OPERATOR_TYPE:
            return createOperatorTypeFromString(eDataType, initialValue);
         default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String convertToString(EDataType eDataType, Object instanceValue)
   {
      switch (eDataType.getClassifierID())
      {
         case SpecificationPackage.PATTERN_TYPE:
            return convertPatternTypeToString(eDataType, instanceValue);
         case SpecificationPackage.MODIFIER_TYPE:
            return convertModifierTypeToString(eDataType, instanceValue);
         case SpecificationPackage.OPERATOR_TYPE:
            return convertOperatorTypeToString(eDataType, instanceValue);
         default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSCatalog createPSCatalog()
   {
      PSCatalogImpl psCatalog = new PSCatalogImpl();
      return psCatalog;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPatternSpecification createPSPatternSpecification()
   {
      PSPatternSpecificationImpl psPatternSpecification = new PSPatternSpecificationImpl();
      return psPatternSpecification;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSLink createPSLink()
   {
      PSLinkImpl psLink = new PSLinkImpl();
      return psLink;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSPath createPSPath()
   {
      PSPathImpl psPath = new PSPathImpl();
      return psPath;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSCombinedFragment createPSCombinedFragment()
   {
      PSCombinedFragmentImpl psCombinedFragment = new PSCombinedFragmentImpl();
      return psCombinedFragment;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSSpecificationConstraint createPSSpecificationConstraint()
   {
      PSSpecificationConstraintImpl psSpecificationConstraint = new PSSpecificationConstraintImpl();
      return psSpecificationConstraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSObject createPSObject()
   {
      PSObjectImpl psObject = new PSObjectImpl();
      return psObject;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSAnnotation createPSAnnotation()
   {
      PSAnnotationImpl psAnnotation = new PSAnnotationImpl();
      return psAnnotation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSFuzzyMetricConstraint createPSFuzzyMetricConstraint()
   {
      PSFuzzyMetricConstraintImpl psFuzzyMetricConstraint = new PSFuzzyMetricConstraintImpl();
      return psFuzzyMetricConstraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSFuzzySetRatingConstraint createPSFuzzySetRatingConstraint()
   {
      PSFuzzySetRatingConstraintImpl psFuzzySetRatingConstraint = new PSFuzzySetRatingConstraintImpl();
      return psFuzzySetRatingConstraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSAttributeConstraint createPSAttributeConstraint()
   {
      PSAttributeConstraintImpl psAttributeConstraint = new PSAttributeConstraintImpl();
      return psAttributeConstraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSMetricConstraint createPSMetricConstraint()
   {
      PSMetricConstraintImpl psMetricConstraint = new PSMetricConstraintImpl();
      return psMetricConstraint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSFunctionParameter createPSFunctionParameter()
   {
      PSFunctionParameterImpl psFunctionParameter = new PSFunctionParameterImpl();
      return psFunctionParameter;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PatternType createPatternTypeFromString(EDataType eDataType, String initialValue)
   {
      PatternType result = PatternType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertPatternTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ModifierType createModifierTypeFromString(EDataType eDataType, String initialValue)
   {
      ModifierType result = ModifierType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertModifierTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public OperatorType createOperatorTypeFromString(EDataType eDataType, String initialValue)
   {
      OperatorType result = OperatorType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertOperatorTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SpecificationPackage getSpecificationPackage()
   {
      return (SpecificationPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static SpecificationPackage getPackage()
   {
      return SpecificationPackage.eINSTANCE;
   }

} //SpecificationFactoryImpl
