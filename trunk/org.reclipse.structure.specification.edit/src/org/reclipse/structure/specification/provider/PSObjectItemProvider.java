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
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.descriptor.PSObjectInstanceOfPropertyDescriptor;


/**
 * This is the item provider adapter for a {@link org.reclipse.structure.specification.PSObject} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PSObjectItemProvider
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
   public PSObjectItemProvider(AdapterFactory adapterFactory)
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

         addModifierPropertyDescriptor(object);
         addNamePropertyDescriptor(object);
         addInstanceOfPropertyDescriptor(object);
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
    * This adds a property descriptor for the Parents feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addParentsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSCombinedFragmentItem_parents_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSCombinedFragmentItem_parents_feature", "_UI_PSCombinedFragmentItem_type"),
             SpecificationPackage.Literals.PS_COMBINED_FRAGMENT_ITEM__PARENTS,
             true,
             false,
             true,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Modifier feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addModifierPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSNode_modifier_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSNode_modifier_feature", "_UI_PSNode_type"),
             SpecificationPackage.Literals.PS_NODE__MODIFIER,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Instance Of feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   protected void addInstanceOfPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (new PSObjectInstanceOfPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSObject_instanceOf_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSObject_instanceOf_feature", "_UI_PSObject_type"),
             SpecificationPackage.Literals.PS_OBJECT__INSTANCE_OF,
             true,
             false,
             true,
             null,
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
         childrenFeatures.add(SpecificationPackage.Literals.PS_NODE__NODE_CONSTRAINTS);
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
    * This returns PSObject.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/PSObject"));
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
      String label = ((PSObject)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_PSObject_type") :
         getString("_UI_PSObject_type") + " " + label;
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

      switch (notification.getFeatureID(PSObject.class))
      {
         case SpecificationPackage.PS_OBJECT__WEIGHT:
         case SpecificationPackage.PS_OBJECT__TRIGGER:
         case SpecificationPackage.PS_OBJECT__MODIFIER:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
         case SpecificationPackage.PS_OBJECT__NODE_CONSTRAINTS:
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
            (SpecificationPackage.Literals.PS_NODE__NODE_CONSTRAINTS,
             SpecificationFactory.eINSTANCE.createPSFuzzyMetricConstraint()));

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_NODE__NODE_CONSTRAINTS,
             SpecificationFactory.eINSTANCE.createPSFuzzySetRatingConstraint()));

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_NODE__NODE_CONSTRAINTS,
             SpecificationFactory.eINSTANCE.createPSAttributeConstraint()));

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_NODE__NODE_CONSTRAINTS,
             SpecificationFactory.eINSTANCE.createPSMetricConstraint()));
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
