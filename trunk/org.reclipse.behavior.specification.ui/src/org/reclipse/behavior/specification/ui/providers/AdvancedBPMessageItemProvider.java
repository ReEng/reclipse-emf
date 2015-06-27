package org.reclipse.behavior.specification.ui.providers;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.reclipse.behavior.specification.BehavioralpatternPackage;
import org.reclipse.behavior.specification.provider.BPMessageItemProvider;


public class AdvancedBPMessageItemProvider extends BPMessageItemProvider
{

   public AdvancedBPMessageItemProvider(AdapterFactory adapterFactory)
   {
      super(adapterFactory);
   }


   @Override
   protected void addMethodReferencePropertyDescriptor(Object object)
   {
      MethodReferencePropertyDescriptor methodReferencePropertyDescriptor = new MethodReferencePropertyDescriptor(
            ((ComposeableAdapterFactory) adapterFactory)
                  .getRootAdapterFactory(), getResourceLocator(),
            getString("_UI_BPMessage_methodReference_feature"), getString(
                  "_UI_PropertyDescriptor_description",
                  "_UI_BPMessage_methodReference_feature", "_UI_BPObject_type"),
            BehavioralpatternPackage.Literals.BP_MESSAGE__METHOD_REFERENCE, true,
            false, true, null, null, null);
      
      itemPropertyDescriptors.add(methodReferencePropertyDescriptor);
   }

   
}
