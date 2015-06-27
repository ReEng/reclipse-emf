package org.reclipse.behavior.inference.ui;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * @author Lothar
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3401 $ $Date: 2007-02-22 20:08:01 +0100 (Do, 22. Feb 2007) $
 */
public class BehavioralInferenceImages
{

   public final static String IMG_OPEN_BEHAVIORAL_ANALYSIS_RESULT = "icons/openBehavioralAnalysisResult.gif";

   public final static String IMG_NOT_ACCEPTED = "icons/notAccepted.gif";

   public final static String IMG_ACCEPTED = "icons/accepted.gif";

   public final static String IMG_REJECTED = "icons/rejected.gif";


   private static AbstractUIPlugin plugin;


   public static Image getImage(String symbolicName)
   {
      return BehaviorInferenceUIPlugin.getDefault().getImageRegistry().get(
            symbolicName);
   }


   public static ImageDescriptor getDescriptor(String symbolicName)
   {
      return BehaviorInferenceUIPlugin.getDefault().getImageRegistry()
            .getDescriptor(symbolicName);
   }


   public static void initializeImageRegistry(AbstractUIPlugin plugin,
         ImageRegistry registry)
   {
      BehavioralInferenceImages.plugin = plugin;

      addImageDescriptor(registry,
            BehavioralInferenceImages.IMG_OPEN_BEHAVIORAL_ANALYSIS_RESULT);
      addImageDescriptor(registry, BehavioralInferenceImages.IMG_NOT_ACCEPTED);
      addImageDescriptor(registry, BehavioralInferenceImages.IMG_ACCEPTED);
      addImageDescriptor(registry, BehavioralInferenceImages.IMG_REJECTED);
   }


   private static void addImageDescriptor(ImageRegistry registry,
         String iconPath)
   {
      ImageDescriptor id = ImageDescriptor.createFromURL(plugin.getBundle()
            .getEntry(iconPath));
      registry.put(iconPath, id);
   }

}
