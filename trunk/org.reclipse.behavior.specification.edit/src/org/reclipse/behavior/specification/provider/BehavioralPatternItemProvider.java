/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.behavior.specification.provider;


import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramPackage;

import de.uni_paderborn.basicSequenceDiagram.provider.SequenceDiagramItemProvider;

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

import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.BehavioralpatternPackage;

/**
 * This is the item provider adapter for a {@link org.reclipse.behavior.specification.BehavioralPattern} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BehavioralPatternItemProvider
   extends SequenceDiagramItemProvider
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
   public BehavioralPatternItemProvider(AdapterFactory adapterFactory)
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

         addNegativePropertyDescriptor(object);
         addPsPatternSpecificationPropertyDescriptor(object);
      }
      return itemPropertyDescriptors;
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
             getString("_UI_BehavioralPattern_negative_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_BehavioralPattern_negative_feature", "_UI_BehavioralPattern_type"),
             BehavioralpatternPackage.Literals.BEHAVIORAL_PATTERN__NEGATIVE,
             true,
             false,
             false,
             ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
             null,
             null));
   }

   /**
    * This adds a property descriptor for the Ps Pattern Specification feature.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected void addPsPatternSpecificationPropertyDescriptor(Object object)
   {
      itemPropertyDescriptors.add
         (createItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_BehavioralPattern_psPatternSpecification_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_BehavioralPattern_psPatternSpecification_feature", "_UI_BehavioralPattern_type"),
             BehavioralpatternPackage.Literals.BEHAVIORAL_PATTERN__PS_PATTERN_SPECIFICATION,
             true,
             false,
             true,
             null,
             null,
             null));
   }

   /**
    * This returns BehavioralPattern.gif.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object getImage(Object object)
   {
      return overlayImage(object, getResourceLocator().getImage("full/obj16/BehavioralPattern"));
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
      String label = ((BehavioralPattern)object).getName();
      return label == null || label.length() == 0 ?
         getString("_UI_BehavioralPattern_type") :
         getString("_UI_BehavioralPattern_type") + " " + label;
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

      switch (notification.getFeatureID(BehavioralPattern.class))
      {
         case BehavioralpatternPackage.BEHAVIORAL_PATTERN__NEGATIVE:
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

      newChildDescriptors.add
         (createChildParameter
            (BasicSequenceDiagramPackage.Literals.SEQUENCE_DIAGRAM__OBJECTS,
             BehavioralpatternFactory.eINSTANCE.createBPAnyObject()));

      newChildDescriptors.add
         (createChildParameter
            (BasicSequenceDiagramPackage.Literals.SEQUENCE_DIAGRAM__OBJECTS,
             BehavioralpatternFactory.eINSTANCE.createBPObject()));

      newChildDescriptors.add
         (createChildParameter
            (BasicSequenceDiagramPackage.Literals.SEQUENCE_DIAGRAM__OBJECTS,
             BehavioralpatternFactory.eINSTANCE.createBPSetObject()));

      newChildDescriptors.add
         (createChildParameter
            (BasicSequenceDiagramPackage.Literals.SEQUENCE_DIAGRAM__MESSAGES,
             BehavioralpatternFactory.eINSTANCE.createBPMessage()));

      newChildDescriptors.add
         (createChildParameter
            (BasicSequenceDiagramPackage.Literals.SEQUENCE_DIAGRAM__FRAGMENTS,
             BehavioralpatternFactory.eINSTANCE.createBPAssignment()));

      newChildDescriptors.add
         (createChildParameter
            (BasicSequenceDiagramPackage.Literals.SEQUENCE_DIAGRAM__FRAGMENTS,
             BehavioralpatternFactory.eINSTANCE.createBPEachFragment()));
   }

   /**
    * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
   {
      Object childFeature = feature;
      Object childObject = child;

      boolean qualify =
         childFeature == BasicSequenceDiagramPackage.Literals.SEQUENCE_DIAGRAM__ROOT_FRAGMENT ||
         childFeature == BasicSequenceDiagramPackage.Literals.SEQUENCE_DIAGRAM__FRAGMENTS;

      if (qualify)
      {
         return getString
            ("_UI_CreateChild_text2",
             new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
      }
      return super.getCreateChildText(owner, feature, child, selection);
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
      return BehavioralpatternEditPlugin.INSTANCE;
   }

}
