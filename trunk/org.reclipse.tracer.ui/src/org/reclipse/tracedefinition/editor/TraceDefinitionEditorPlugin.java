package org.reclipse.tracedefinition.editor;


import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3784 $ $Date: 2007-09-09 23:41:26 +0200 (So, 09 Sep 2007) $
 */
public class TraceDefinitionEditorPlugin extends AbstractUIPlugin
{
   /**
    * The singleton instance.
    */
   private static TraceDefinitionEditorPlugin plugin;


   public TraceDefinitionEditorPlugin()
   {
      plugin = this;
   }


   /**
    * Returns the singleton instance.
    * 
    * @return plugin
    */
   public static TraceDefinitionEditorPlugin getDefault()
   {
      return plugin;
   }


   public static String getPluginId()
   {
      return getDefault().getBundle().getSymbolicName();
   }


   /**
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
    */
   @Override
   protected void initializeImageRegistry(final ImageRegistry registry)
   {
      super.initializeImageRegistry(registry);

      TraceDefinitionEditorImages.initializeImageRegistry(registry);
   }


}
