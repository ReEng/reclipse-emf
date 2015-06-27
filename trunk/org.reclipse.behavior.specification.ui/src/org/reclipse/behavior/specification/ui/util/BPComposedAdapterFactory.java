/**
 * 
 */
package org.reclipse.behavior.specification.ui.util;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class BPComposedAdapterFactory
{
   private static ComposedAdapterFactory bpCompAdapterFactory;

   public final static ComposedAdapterFactory getAdapterFactory()
   {
       if (bpCompAdapterFactory == null)
           bpCompAdapterFactory = new ComposedAdapterFactory(createFactoryList());
       return bpCompAdapterFactory;
   }

   public final static ArrayList<AdapterFactory> createFactoryList()
   {
       ArrayList<AdapterFactory> factories = new ArrayList<AdapterFactory>();
       factories.add(new ResourceItemProviderAdapterFactory());
       factories.add(new EcoreItemProviderAdapterFactory());
       factories.add(new ReflectiveItemProviderAdapterFactory());
       return factories;
   }

}
