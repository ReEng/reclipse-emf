/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.fujaba.commons.identifier.provider.IdentifierItemProvider;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.SpecificationPackage;


/**
 * This is the item provider adapter for a {@link org.reclipse.structure.specification.PSFuzzySetRatingConstraint} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PSFuzzySetRatingConstraintItemProvider
   extends IdentifierItemProvider
   implements
      IEditingDomainItemProvider,
      IStructuredItemContentProvider,
      ITreeItemContentProvider,
      IItemLabelProvider,
      IItemPropertySource
{
   /**
    * This constructs an instance from a factory and a notifier.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PSFuzzySetRatingConstraintItemProvider(AdapterFactory adapterFactory)
   {
      super(adapterFactory);
   }

   /**
    * This returns the property descriptors for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   @Override
   public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
   {
      if (itemPropertyDescriptors == null)
      {
         itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();

         addWeightPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
   }

   /**
    * This adds a property descriptor for the Weight feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addWeightPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSItem_weight_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSItem_weight_feature", "_UI_PSItem_type"),
             SpecificationPackage.Literals.PS_ITEM__WEIGHT,
             true,
             false,
             false,
             ItemPropertyDescriptor.REAL_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Expression feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addExpressionPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSNodeConstraint_expression_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSNodeConstraint_expression_feature", "_UI_PSNodeConstraint_type"),
             SpecificationPackage.Literals.PS_NODE_CONSTRAINT__EXPRESSION,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Math Function ID feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addMathFunctionIDPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSFuzzyConstraint_mathFunctionID_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSFuzzyConstraint_mathFunctionID_feature", "_UI_PSFuzzyConstraint_type"),
             SpecificationPackage.Literals.PS_FUZZY_CONSTRAINT__MATH_FUNCTION_ID,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
    * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
    * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
   {
      if (childrenFeatures == null)
      {
         super.getChildrenFeatures(object);
         childrenFeatures.add(SpecificationPackage.Literals.PS_FUZZY_CONSTRAINT__PARAMETERS);
      }
      return childrenFeatures;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EStructuralFeature getChildFeature(Object object, Object child)
   {
      // Check the type of the specified child object and return the proper feature to use for
      // adding (see {@link AddCommand}) it as a child.

      return super.getChildFeature(object, child);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean hasChildren(Object object)
   {
      return hasChildren(object, false);
   }

   /**
    * This returns PSFuzzySetRatingConstraint.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/PSFuzzySetRatingConstraint"));
   }

   /**
    * This returns the label text for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getText(Object object)
   {
      String label = ((PSFuzzySetRatingConstraint)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_PSFuzzySetRatingConstraint_type") :
         getString("_UI_PSFuzzySetRatingConstraint_type") + " " + label;
   }

   /**
    * This handles model notifications by calling {@link #updateChildren} to update any cached
    * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void notifyChanged(Notification notification)
   {
      updateChildren(notification);

      switch (notification.getFeatureID(PSFuzzySetRatingConstraint.class))
      {
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__WEIGHT:
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__EXPRESSION:
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__MATH_FUNCTION_ID:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
         case SpecificationPackage.PS_FUZZY_SET_RATING_CONSTRAINT__PARAMETERS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
      }
      super.notifyChanged(notification);
   }

   /**
    * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
    * that can be created under this object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
   {
      super.collectNewChildDescriptors(newChildDescriptors, object);

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_FUZZY_CONSTRAINT__PARAMETERS,
             SpecificationFactory.eINSTANCE.createPSFunctionParameter()));
   }

   /**
    * Return the resource locator for this item provider's resources.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public ResourceLocator getResourceLocator()
   {
      return SpecificationEditPlugin.INSTANCE;
   }

}
