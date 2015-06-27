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
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.descriptor.PSPatternSpecificationExtendersPropertyDescriptor;
import org.reclipse.structure.specification.descriptor.PSPatternSpecificationSuperTypesPropertyDescriptor;


/**
 * This is the item provider adapter for a {@link org.reclipse.structure.specification.PSPatternSpecification} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PSPatternSpecificationItemProvider
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
   public PSPatternSpecificationItemProvider(AdapterFactory adapterFactory)
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

         addSuperPatternPropertyDescriptor(object);
         addSubPatternsPropertyDescriptor(object);
         addTypePropertyDescriptor(object);
         addAbstractPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
   }

   /**
    * This adds a property descriptor for the Super Pattern feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   protected void addSuperPatternPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (new PSPatternSpecificationSuperTypesPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSPatternSpecification_superPattern_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSPatternSpecification_superPattern_feature", "_UI_PSPatternSpecification_type"),
             SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__SUPER_PATTERN,
             true,
             false,
             true,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Sub Patterns feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   protected void addSubPatternsPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (new PSPatternSpecificationExtendersPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSPatternSpecification_subPatterns_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSPatternSpecification_subPatterns_feature", "_UI_PSPatternSpecification_type"),
             SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__SUB_PATTERNS,
             true,
             false,
             true,
             null,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Type feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addTypePropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSPatternSpecification_type_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSPatternSpecification_type_feature", "_UI_PSPatternSpecification_type"),
             SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__TYPE,
             true,
             false,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Abstract feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addAbstractPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_PSPatternSpecification_abstract_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_PSPatternSpecification_abstract_feature", "_UI_PSPatternSpecification_type"),
             SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__ABSTRACT,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
         childrenFeatures.add(SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__CONNECTIONS);
         childrenFeatures.add(SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__NODES);
         childrenFeatures.add(SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS);
         childrenFeatures.add(SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__CONSTRAINTS);
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
    * This returns PSPatternSpecification.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/PSPatternSpecification"));
   }

   /**
    * This returns the label text for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   @Override
   public String getText(Object object)
   {
      String name = ((PSPatternSpecification) object).getName();
      return (name == null || name.length() < 1) ? "unnamed" : name;
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

      switch (notification.getFeatureID(PSPatternSpecification.class))
      {
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__TYPE:
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__ABSTRACT:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONNECTIONS:
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__NODES:
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS:
         case SpecificationPackage.PS_PATTERN_SPECIFICATION__CONSTRAINTS:
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
            (SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__CONNECTIONS,
             SpecificationFactory.eINSTANCE.createPSLink()));

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__CONNECTIONS,
             SpecificationFactory.eINSTANCE.createPSPath()));

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__NODES,
             SpecificationFactory.eINSTANCE.createPSObject()));

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__NODES,
             SpecificationFactory.eINSTANCE.createPSAnnotation()));

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__COMBINED_FRAGMENTS,
             SpecificationFactory.eINSTANCE.createPSCombinedFragment()));

      newChildDescriptors.add
         (createChildParameter
            (SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__CONSTRAINTS,
             SpecificationFactory.eINSTANCE.createPSSpecificationConstraint()));
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
