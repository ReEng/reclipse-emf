package org.reclipse.structure.specification.ui.properties;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.fujaba.commons.properties.CustomTabbedPropertySheetPage;
import org.reclipse.structure.specification.provider.SpecificationItemProviderAdapterFactory;


/**
 * The property sheet page to implement to provide the tabbed UI.
 * 
 * @author harka
 */
public class PSTabbedPropertySheetPage extends CustomTabbedPropertySheetPage
{
   public PSTabbedPropertySheetPage(
         ITabbedPropertySheetPageContributor contributor)
   {
      super(contributor);
   }


   /**
    * This method creates a list of factories used by the editor. Resource, Specification and Ecore
    * adapter factories are added here.
    * 
    * @return The list of factories.
    */
   @Override
   public List<? extends AdapterFactory> getPrincipalAdapterFactories()
   {
      List<AdapterFactory> factories = new ArrayList<AdapterFactory>();

      factories.add(new ResourceItemProviderAdapterFactory());
      factories.add(new SpecificationItemProviderAdapterFactory());
      factories.add(new EcoreItemProviderAdapterFactory());

      return factories;
   }
}
