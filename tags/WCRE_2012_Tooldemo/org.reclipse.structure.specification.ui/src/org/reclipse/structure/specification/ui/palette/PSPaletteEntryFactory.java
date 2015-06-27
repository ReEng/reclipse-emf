package org.reclipse.structure.specification.ui.palette;


import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.fujaba.commons.FujabaCommonsPlugin;
import org.fujaba.commons.editor.palette.PaletteEntryFactory;
import org.fujaba.commons.extensionpoints.util.ExtensionpointTools;
import org.osgi.framework.Bundle;
import org.reclipse.structure.specification.ModifierType;


/**
 * TODO: describe type
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSPaletteEntryFactory extends PaletteEntryFactory
{

   /**
    * configuration element specified in the manifest (plugin.xml)
    */
   private IConfigurationElement config;


   @Override
   public void setInitializationData(IConfigurationElement configElement,
         String propertyName, Object data)
   {
      config = configElement;
   }


   @Override
   public PaletteEntry createPaletteEntry()
   {
      // load product class (model element to be created by the entry)
      Bundle bundle = Platform.getBundle(config.getContributor().getName());
      if (bundle == null)
      {
         return null;
      }
      Class<?> product;
      try
      {
         product = bundle.loadClass(config.getAttribute("product"));
      }
      catch (ClassNotFoundException e)
      {
         FujabaCommonsPlugin.getDefault().logError(e.getMessage(), e);
         return null;
      }

      // load entry icons (use 16x16 pixel icon as fallback for the 24x24 pixel icon)
      ImageDescriptor icon16 = ExtensionpointTools.getImage(config, "icon16");
      ImageDescriptor icon24 = ExtensionpointTools.getImage(config, "icon24",
            true);
      if (icon24 == null)
      {
         icon24 = icon16;
      }

      // construct a default tooltip using the label
      String label = config.getAttribute("label");
      String tooltip = config.getAttribute("tooltip");
      if (tooltip == null && label != null && label.length() > 0)
      {
         if ("aAoOuUiI".contains(label.substring(0, 1)))
         {
            tooltip = "Create an " + label;
         }
         else
         {
            tooltip = "Create a " + label;
         }
      }

      // get fragment kind
      ModifierType kind = ModifierType.NONE;
      if (label.startsWith("Additional"))
      {
         kind = ModifierType.ADDITIONAL;
      }
      else if (label.startsWith("Negative"))
      {
         kind = ModifierType.NEGATIVE;
      }
      else if (label.startsWith("Set"))
      {
         kind = ModifierType.SET;
      }

      CreationFactory factory = new PSCombinedFragmentCreationFactory(product,
            kind);

      // create a CreationEntry depending on entry type
      if ("element".equals(config.getAttribute("type")))
      {
         return new CreationToolEntry(label, tooltip, factory, icon16, icon24);
      }
      return null;
   }
}
