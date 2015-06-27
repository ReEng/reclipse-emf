package org.reclipse.behavior.specification.ui.providers;


import org.eclipse.emf.common.notify.Adapter;
import org.reclipse.behavior.specification.provider.BehavioralpatternItemProviderAdapterFactory;


public class AdvancedBehavioralPatternItemProviderAdapterFactory extends
      BehavioralpatternItemProviderAdapterFactory
{
   @Override
   public Adapter createBPObjectAdapter()
   {
      if (bpObjectItemProvider == null)
      {
         bpObjectItemProvider = new AdvancedBPObjectItemProvider(this);
      }

      return bpObjectItemProvider;
   }


   @Override
   public Adapter createBPMessageAdapter()
   {
      if (bpMessageItemProvider == null)
      {
         bpMessageItemProvider = new AdvancedBPMessageItemProvider(this);
      }

      return bpMessageItemProvider;
   }


}
