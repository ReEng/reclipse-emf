package org.reclipse.tracedefinition.editor;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3784 $ $Date: 2007-09-09 23:41:26 +0200 (So, 09 Sep 2007) $
 */
public class TraceDefinitionEditorImages
{

   public static final String IMG_TRACE_DEFINTION = "icons/traceDefinition.gif";

   public static final String IMG_CRITICAL_TRACE = "icons/critical_trace.gif";

   public static final String IMG_CONSIDER_TRACE = "icons/consider_trace.gif";

   public static final String IMG_CLASS = "icons/class.gif";

   public static final String IMG_PUBLIC_METHOD = "icons/public_method.gif";

   public static final String IMG_PARAMETER = "icons/parameter.gif";


   private static TraceDefinitionEditorPlugin plugin;

   static
   {
      plugin = TraceDefinitionEditorPlugin.getDefault();
   }


   public static Image getImage(final String symbolicName)
   {
      return TraceDefinitionEditorPlugin.getDefault().getImageRegistry().get(
            symbolicName);
   }


   public static ImageDescriptor getDescriptor(final String symbolicName)
   {
      return TraceDefinitionEditorPlugin.getDefault().getImageRegistry()
            .getDescriptor(symbolicName);
   }


   public static void initializeImageRegistry(final ImageRegistry registry)
   {
      addImageDescriptor(registry, IMG_TRACE_DEFINTION);
      addImageDescriptor(registry, IMG_CRITICAL_TRACE);
      addImageDescriptor(registry, IMG_CONSIDER_TRACE);
      addImageDescriptor(registry, IMG_CLASS);
      addImageDescriptor(registry, IMG_PUBLIC_METHOD);
      addImageDescriptor(registry, IMG_PARAMETER);
   }


   private static void addImageDescriptor(final ImageRegistry registry,
         final String iconPath)
   {
      final ImageDescriptor id = ImageDescriptor.createFromURL(plugin
            .getBundle().getEntry(iconPath));
      registry.put(iconPath, id);
   }

}
