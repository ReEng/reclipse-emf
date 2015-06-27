package org.reclipse.behavior.specification.ui.providers;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.reclipse.behavior.specification.BehavioralpatternPackage;
import org.reclipse.behavior.specification.provider.BPObjectItemProvider;


public class AdvancedBPObjectItemProvider extends BPObjectItemProvider
{

   public AdvancedBPObjectItemProvider(AdapterFactory adapterFactory)
   {
      super(adapterFactory);
   }


   @Override
   protected void addTypeReferencePropertyDescriptor(Object object)
   {
      TypeReferencePropertyDescriptor typeReferencePropertyDescriptor = new TypeReferencePropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory)
                  .getRootAdapterFactory(), getResourceLocator(),
            getString("_UI_BPObject_typeReference_feature"), getString(
                  "_UI_PropertyDescriptor_description",
                  "_UI_BPObject_typeReference_feature", "_UI_BPObject_type"),
            BehavioralpatternPackage.Literals.BP_OBJECT__TYPE_REFERENCE, true,
            false, true, null, null, null);

      itemPropertyDescriptors.add(typeReferencePropertyDescriptor);
   }

}
