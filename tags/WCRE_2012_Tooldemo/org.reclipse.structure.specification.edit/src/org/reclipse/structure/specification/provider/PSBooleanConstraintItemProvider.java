/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.descriptor.PSAttributeConstraintOperatorPropertyDescriptor;
import org.reclipse.structure.specification.descriptor.PSAttributeConstraintValuePropertyDescriptor;

/**
 * This is the item provider adapter for a {@link org.reclipse.structure.specification.PSBooleanConstraint} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PSBooleanConstraintItemProvider
   extends PSNodeConstraintItemProvider
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
   public PSBooleanConstraintItemProvider(AdapterFactory adapterFactory)
   {
      super(adapterFactory);
   }

   /**
    * This returns the property descriptors for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
   {
      if (itemPropertyDescriptors == null)
      {
         super.getPropertyDescriptors(object);

         addAdditionalPropertyDescriptor(object);
         addValueExpressionPropertyDescriptor(object);
         addOperatorPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
   }

   /**
    * This adds a property descriptor for the Additional feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addAdditionalPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSBooleanConstraint_additional_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSBooleanConstraint_additional_feature", "_UI_PSBooleanConstraint_type"),
             SpecificationPackage.Literals.PS_BOOLEAN_CONSTRAINT__ADDITIONAL,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Value Expression feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   protected void addValueExpressionPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (new PSAttributeConstraintValuePropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSBooleanConstraint_valueExpression_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSBooleanConstraint_valueExpression_feature", "_UI_PSBooleanConstraint_type"),
             SpecificationPackage.Literals.PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Operator feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addOperatorPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (new PSAttributeConstraintOperatorPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSBooleanConstraint_operator_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSBooleanConstraint_operator_feature", "_UI_PSBooleanConstraint_type"),
             SpecificationPackage.Literals.PS_BOOLEAN_CONSTRAINT__OPERATOR,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This returns PSBooleanConstraint.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/PSBooleanConstraint"));
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
      String label = ((PSBooleanConstraint)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_PSBooleanConstraint_type") :
         getString("_UI_PSBooleanConstraint_type") + " " + label;
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

      switch (notification.getFeatureID(PSBooleanConstraint.class))
      {
         case SpecificationPackage.PS_BOOLEAN_CONSTRAINT__ADDITIONAL:
         case SpecificationPackage.PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION:
         case SpecificationPackage.PS_BOOLEAN_CONSTRAINT__OPERATOR:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
   }

}
