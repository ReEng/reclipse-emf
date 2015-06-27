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
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.descriptor.PSLinkInstanceOfPropertyDescriptor;
import org.reclipse.structure.specification.descriptor.PSLinkQualifierPropertyDescriptor;


/**
 * This is the item provider adapter for a {@link org.reclipse.structure.specification.PSLink} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PSLinkItemProvider
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
   public PSLinkItemProvider(AdapterFactory adapterFactory)
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

         addInstanceOfPropertyDescriptor(object);
         addNegativePropertyDescriptor(object);
         addQualifierPropertyDescriptor(object);
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
    * This adds a property descriptor for the Qualifier feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   protected void addQualifierPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (new PSLinkQualifierPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSLink_qualifier_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSLink_qualifier_feature", "_UI_PSLink_type"),
             SpecificationPackage.Literals.PS_LINK__QUALIFIER,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Negative feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addNegativePropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSLink_negative_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSLink_negative_feature", "_UI_PSLink_type"),
             SpecificationPackage.Literals.PS_LINK__NEGATIVE,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
      (new PSLinkInstanceOfPropertyDescriptor
         (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSLink_instanceOf_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSLink_instanceOf_feature", "_UI_PSLink_type"),
             SpecificationPackage.Literals.PS_LINK__INSTANCE_OF,
             true,
             false,
             true,
             null,
             null,
             null));
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
    * This returns PSLink.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/PSLink"));
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
      String label = ((PSLink)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_PSLink_type") :
         getString("_UI_PSLink_type") + " " + label;
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

      switch (notification.getFeatureID(PSLink.class))
      {
         case SpecificationPackage.PS_LINK__WEIGHT:
         case SpecificationPackage.PS_LINK__QUALIFIER:
         case SpecificationPackage.PS_LINK__NEGATIVE:
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
