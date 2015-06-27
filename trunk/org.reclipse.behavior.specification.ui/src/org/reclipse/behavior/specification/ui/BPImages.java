package org.reclipse.behavior.specification.ui;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;



public abstract class BPImages
{
   
   public final static String IMG_BP_DIAGRAM_16 = "icons/sequenceDiagram.png";

   public final static String IMG_BP_OBJECT_16 = "icons/object16.gif";

   public final static String IMG_BP_MESSAGE_16 = "icons/message16.gif";

   public final static String IMG_BP_COMBINED_FRAGMENT_16 = "icons/combinedFragment16.gif";

   public final static String IMG_BP_ASSIGNMENT_16 = "icons/assignment16.gif";

   
   public static Image getImage(String symbolicName)
   {
      return BPPlugin.getDefault().getImageRegistry().get(
            symbolicName);
   }


   public static ImageDescriptor getDescriptor(String symbolicName)
   {
      return BPPlugin.getDefault().getImageRegistry()
            .getDescriptor(symbolicName);
   }

}
