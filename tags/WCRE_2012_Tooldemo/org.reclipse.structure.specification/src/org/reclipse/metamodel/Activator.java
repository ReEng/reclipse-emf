package org.reclipse.metamodel;


import org.eclipse.core.runtime.Status;
import org.fujaba.commons.AbstractFujabaPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle and support logging of informations, warnings
 * and errors.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class Activator extends AbstractFujabaPlugin implements LabelerImages
{
   public final static String ID = "org.reclipse.structure.specification"; //$NON-NLS-1$

   private static Activator instance;


   /**
    * Getter of the singleton instance.
    * 
    * @return Returns the singleton instance.
    */
   public static Activator getInstance()
   {
      return instance;
   }


   @Override
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      instance = this;

      // add images to cache
      addImage(IMG_UNKNOWN);

      addImage(IMG_PROJECT);
      addImage(IMG_FOLDER);
      addImage(IMG_FILE);

      addImage(IMG_PACKAGE);
      addImage(IMG_ANNOTATION_DEFAULT);
      addImage(IMG_ANNOTATION_NONE);
      addImage(IMG_ANNOTATION_PRIVATE);
      addImage(IMG_ANNOTATION_PROTECTED);
      addImage(IMG_ANNOTATION_PUBLIC);

      addImage(IMG_CLASS_DEFAULT);
      addImage(IMG_CLASS_NONE);
      addImage(IMG_CLASS_PRIVATE);
      addImage(IMG_CLASS_PROTECTED);
      addImage(IMG_CLASS_PUBLIC);

      addImage(IMG_ENUM_DEFAULT);
      addImage(IMG_ENUM_NONE);
      addImage(IMG_ENUM_PRIVATE);
      addImage(IMG_ENUM_PROTECTED);
      addImage(IMG_ENUM_PUBLIC);

      addImage(IMG_FIELD_DEFAULT);
      addImage(IMG_FIELD_NONE);
      addImage(IMG_FIELD_PRIVATE);
      addImage(IMG_FIELD_PROTECTED);
      addImage(IMG_FIELD_PUBLIC);

      addImage(IMG_INTERFACE_DEFAULT);
      addImage(IMG_INTERFACE_NONE);
      addImage(IMG_INTERFACE_PRIVATE);
      addImage(IMG_INTERFACE_PROTECTED);
      addImage(IMG_INTERFACE_PUBLIC);

      addImage(IMG_METHOD_DEFAULT);
      addImage(IMG_METHOD_NONE);
      addImage(IMG_METHOD_PRIVATE);
      addImage(IMG_METHOD_PROTECTED);
      addImage(IMG_METHOD_PUBLIC);

      addImage(IMG_VARIABLE_LOCAL);
      addImage(IMG_VARIABLE_TYPE);

      addImage(IMG_OVL_ABSTRACT);
      addImage(IMG_OVL_CONSTRUCTOR);
      addImage(IMG_OVL_FINAL);
      addImage(IMG_OVL_IMPLEMENTED);
      addImage(IMG_OVL_MAIN);
      addImage(IMG_OVL_NATIVE);
      addImage(IMG_OVL_OVERRIDDEN);
      addImage(IMG_OVL_STATIC);
      addImage(IMG_OVL_SYNC);
      addImage(IMG_OVL_SYNC_IMPLEMENTED);
      addImage(IMG_OVL_SYNC_OVERRIDDEN);
      addImage(IMG_OVL_TRANSIENT);
      addImage(IMG_OVL_VOLATILE);
   }


   private void addImage(String path)
   {
      addImageToCache(path, path);
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
