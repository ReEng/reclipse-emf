package org.reclipse.structure.specification.ui;


import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.fujaba.commons.AbstractFujabaPlugin;
import org.osgi.framework.BundleContext;
import org.reclipse.structure.specification.ui.utils.ModelRegistrator;



/**
 * The activator class controls the plug-in life cycle
 */
public class PSPlugin extends AbstractFujabaPlugin
{

   public final static String ID = "org.reclipse.structure.specification.ui";

   private static PSPlugin instance;


   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      instance = this;
      ModelRegistrator.initialize();
   }


   @Override
   public void stop(BundleContext context) throws Exception
   {
      instance = null;
      super.stop(context);
   }


   /**
    * Getter of the singleton instance.
    * 
    * @return Returns the singleton instance.
    */
   public static PSPlugin getDefault()
   {
      return instance;
   }


   /**
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
    */
   @Override
   protected void initializeImageRegistry(ImageRegistry registry)
   {
      super.initializeImageRegistry(registry);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_ANNOTATION_16,
            PSImages.IMAGE_ETOOL_EDIT_ANNOTATION_16);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_ANNOTATION_24,
            PSImages.IMAGE_ETOOL_EDIT_ANNOTATION_24);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_ATTREXPRPAIR_16,
            PSImages.IMAGE_ETOOL_EDIT_ATTREXPRPAIR_16);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_ATTREXPRPAIR_24,
            PSImages.IMAGE_ETOOL_EDIT_ATTREXPRPAIR_24);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_CONSTRAINT_16,
            PSImages.IMAGE_ETOOL_EDIT_CONSTRAINT_16);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_CONSTRAINT_24,
            PSImages.IMAGE_ETOOL_EDIT_CONSTRAINT_24);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_GENERALIZATION_16,
            PSImages.IMAGE_ETOOL_EDIT_GENERALIZATION_16);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_GENERALIZATION_24,
            PSImages.IMAGE_ETOOL_EDIT_GENERALIZATION_24);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_LINK_16,
            PSImages.IMAGE_ETOOL_EDIT_LINK_16);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_LINK_24,
            PSImages.IMAGE_ETOOL_EDIT_LINK_24);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_OBJECT_16,
            PSImages.IMAGE_ETOOL_EDIT_OBJECT_16);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_OBJECT_24,
            PSImages.IMAGE_ETOOL_EDIT_OBJECT_24);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_OPT_FRAGMENT_16,
            PSImages.IMAGE_ETOOL_EDIT_OPT_FRAGMENT_16);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_PATH_16,
            PSImages.IMAGE_ETOOL_EDIT_PATH_16);

      addImageDescriptor(registry, PSImages.IMAGE_ETOOL_EDIT_PATH_24,
            PSImages.IMAGE_ETOOL_EDIT_PATH_24);

      addImageDescriptor(registry, PSImages.IMAGE_OBJ_PATTERN_RULE_16,
            PSImages.IMAGE_OBJ_PATTERN_RULE_16);
      
      // add function images
      addImageDescriptor(registry, PSImages.IMAGE_FUNCTION_LINEAR,
            PSImages.IMAGE_FUNCTION_LINEAR);
      addImageDescriptor(registry, PSImages.IMAGE_FUNCTION_LIM0,
            PSImages.IMAGE_FUNCTION_LIM0);
      addImageDescriptor(registry, PSImages.IMAGE_FUNCTION_LIM1,
            PSImages.IMAGE_FUNCTION_LIM1);

      addImageDescriptor(registry, PSImages.IMAGE_HELP, PSImages.IMAGE_HELP);
   }


   private void addImageDescriptor(ImageRegistry registry, String key,
         String imagePath)
   {
      ImageDescriptor id = ImageDescriptor.createFromURL(getBundle().getEntry(
            imagePath));
      registry.put(key, id);
   }


   @Override
   protected void log(int severity, String message, Throwable throwable)
   {
      getLog().log(new Status(severity, ID, message, throwable));
   }
}
