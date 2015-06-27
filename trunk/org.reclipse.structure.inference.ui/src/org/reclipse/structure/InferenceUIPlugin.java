package org.reclipse.structure;


import org.eclipse.core.runtime.Status;
import org.fujaba.commons.AbstractFujabaPlugin;
import org.osgi.framework.BundleContext;
import org.reclipse.structure.inference.ui.InferenceConstants;



/**
 * The activator class controls the plug-in life cycle and support logging of informations, warnings
 * and errors.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class InferenceUIPlugin extends AbstractFujabaPlugin
{
   public final static String ID = "org.reclipse.inference.ui"; //$NON-NLS-1$

   private static InferenceUIPlugin instance;


   /**
    * Getter of the singleton instance.
    * 
    * @return Returns the singleton instance.
    */
   public static InferenceUIPlugin getInstance()
   {
      return instance;
   }


   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      instance = this;

      // StartInferenceWizard
      addImageToCache(InferenceConstants.IMG_START_INFERENCE_WIZARD, "icons/wizards/start.png");
      addImageToCache(InferenceConstants.IMG_START_INFERENCE_WIZARD_BANNER, "icons/wizards/start_banner.png");
      
      // InferenceConsole
      addImageToCache(InferenceConstants.IMG_CONSOLE, "icons/console.png");
      addImageToCache(InferenceConstants.IMG_SUSPEND_INFERENCE, "icons/commands/suspend_enabled.png");
      addImageToCache(InferenceConstants.IMG_SUSPEND_INFERENCE_DIS, "icons/commands/suspend_disabled.png");
      
      // AnnotationView
      addImageToCache(InferenceConstants.IMG_ANNOTATED_ELEMENTS_ACTION, "icons/commands/group/annotated.gif");
      addImageToCache(InferenceConstants.IMG_ANTECEDENT_ANNOTATIONS_ACTION, "icons/commands/group/antecedent.gif");
      addImageToCache(InferenceConstants.IMG_CONSEQUENT_ANNOTATIONS_ACTION, "icons/commands/group/consequent.gif");
      addImageToCache(InferenceConstants.IMG_FILTER_ACTION, "icons/commands/filter.gif");
      
      addImageToCache(InferenceConstants.IMG_ANNOTATION_COLLECTION, "icons/anno_set.gif");
      addImageToCache(InferenceConstants.IMG_ANNOTATION_VISIBLE, "icons/anno_vis.gif");
      addImageToCache(InferenceConstants.IMG_ANNOTATION_INVISIBLE, "icons/anno_nvis.gif");

      addImageToCache(InferenceConstants.IMG_ANTECEDENT_COLLECTION, "icons/commands/group/antecedent.gif");
      addImageToCache(InferenceConstants.IMG_CONSEQUENT_COLLECTION, "icons/commands/group/consequent.gif");

      // AnnotationFilterDialog
      addImageToCache(InferenceConstants.IMG_FILTER_CHECKED, "icons/views/checked.png");
      addImageToCache(InferenceConstants.IMG_FILTER_UNCHECKED, "icons/views/unchecked.png");
      addImageToCache(InferenceConstants.IMG_FILTER_AVAILABLE, "icons/anno_vis.gif");
      addImageToCache(InferenceConstants.IMG_FILTER_UNAVAILABLE, "icons/anno_nvis.gif");
   }


   @Override
   public void stop(BundleContext context) throws Exception
   {
      instance = null;
      super.stop(context);
   }


   @Override
   protected void log(int severity, String message, Throwable throwable)
   {
      getLog().log(new Status(severity, ID, message, throwable));
   }
}
