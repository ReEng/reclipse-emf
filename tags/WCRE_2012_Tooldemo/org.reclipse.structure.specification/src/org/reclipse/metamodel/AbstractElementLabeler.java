package org.reclipse.metamodel;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;


public abstract class AbstractElementLabeler
{
   protected static enum Element
   {
      UNKNOWN, PROJECT, FOLDER, FILE, PACKAGE, CLASS, INTERFACE, ENUM, ANNOTATION, FIELD, METHOD, VARIABLE;
   }

   protected static final int VIS_PRIVATE = 1;

   protected static final int VIS_PROTECTED = 2;

   protected static final int VIS_DEFAULT = 4;

   protected static final int VIS_PUBLIC = 8;


   protected static final int MOD_ABSTRACT = 16;

   protected static final int MOD_FINAL = 32;

   protected static final int MOD_NATIVE = 64;

   protected static final int MOD_STATIC = 128;

   protected static final int MOD_SYNC = 256;

   protected static final int MOD_TRANSIENT = 512;

   protected static final int MOD_VOLATILE = 1024;


   /**
    * Indicating that the {@link Element#METHOD method} overwrites a method required by the
    * implemented interface.
    */
   protected static final int FLAG_IMPLEMENTED = 2048;

   /**
    * Indicating that the {@link Element#METHOD method} overwrites one inherited from the extended
    * class.
    */
   protected static final int FLAG_OVERRIDDEN = 4096;

   /**
    * Indicating that the {@link Element#METHOD method} is a constructor.
    */
   protected static final int FLAG_CONSTRUCTOR = 8192;

   /**
    * Indicating that the {@link Element#CLASS class} contains a
    * <code>public static main(String[])</code> method.
    */
   protected static final int FLAG_MAIN = 16384;


   /**
    * 
    * @param element The element type.
    * @param style The flags indicating the overlay decorations.
    * @return Returns the image decorated with the given options.
    */
   protected final Image getImage(Element element, int style)
   {
      String key;

      switch (element)
      {
         case PROJECT:
            key = LabelerImages.IMG_PROJECT;
            break;
         case FOLDER:
            key = LabelerImages.IMG_FOLDER;
            break;
         case FILE:
            key = LabelerImages.IMG_FILE;
            break;
         case PACKAGE:
            key = LabelerImages.IMG_PACKAGE;
            break;
         case CLASS:
            // decide upon the visibility
            if ((style & VIS_PRIVATE) == VIS_PRIVATE)
            {
               key = LabelerImages.IMG_CLASS_PRIVATE;
            }
            else if ((style & VIS_PROTECTED) == VIS_PROTECTED)
            {
               key = LabelerImages.IMG_CLASS_PROTECTED;
            }
            else if ((style & VIS_DEFAULT) == VIS_DEFAULT)
            {
               key = LabelerImages.IMG_CLASS_DEFAULT;
            }
            else if ((style & VIS_PUBLIC) == VIS_PUBLIC)
            {
               key = LabelerImages.IMG_CLASS_PUBLIC;
            }
            else
            {
               key = LabelerImages.IMG_CLASS_NONE;
            }
            break;
         case INTERFACE:
            // decide upon the visibility
            if ((style & VIS_PRIVATE) == VIS_PRIVATE)
            {
               key = LabelerImages.IMG_INTERFACE_PRIVATE;
            }
            else if ((style & VIS_PROTECTED) == VIS_PROTECTED)
            {
               key = LabelerImages.IMG_INTERFACE_PROTECTED;
            }
            else if ((style & VIS_DEFAULT) == VIS_DEFAULT)
            {
               key = LabelerImages.IMG_INTERFACE_DEFAULT;
            }
            else if ((style & VIS_PUBLIC) == VIS_PUBLIC)
            {
               key = LabelerImages.IMG_INTERFACE_PUBLIC;
            }
            else
            {
               key = LabelerImages.IMG_INTERFACE_NONE;
            }
            break;
         case ENUM:
            // decide upon the visibility
            if ((style & VIS_PRIVATE) == VIS_PRIVATE)
            {
               key = LabelerImages.IMG_ENUM_PRIVATE;
            }
            else if ((style & VIS_PROTECTED) == VIS_PROTECTED)
            {
               key = LabelerImages.IMG_ENUM_PROTECTED;
            }
            else if ((style & VIS_DEFAULT) == VIS_DEFAULT)
            {
               key = LabelerImages.IMG_ENUM_DEFAULT;
            }
            else if ((style & VIS_PUBLIC) == VIS_PUBLIC)
            {
               key = LabelerImages.IMG_ENUM_PUBLIC;
            }
            else
            {
               key = LabelerImages.IMG_ENUM_NONE;
            }
            break;
         case ANNOTATION:
            // decide upon the visibility
            if ((style & VIS_PRIVATE) == VIS_PRIVATE)
            {
               key = LabelerImages.IMG_ANNOTATION_PRIVATE;
            }
            else if ((style & VIS_PROTECTED) == VIS_PROTECTED)
            {
               key = LabelerImages.IMG_ANNOTATION_PROTECTED;
            }
            else if ((style & VIS_DEFAULT) == VIS_DEFAULT)
            {
               key = LabelerImages.IMG_ANNOTATION_DEFAULT;
            }
            else if ((style & VIS_PUBLIC) == VIS_PUBLIC)
            {
               key = LabelerImages.IMG_ANNOTATION_PUBLIC;
            }
            else
            {
               key = LabelerImages.IMG_ANNOTATION_NONE;
            }
            break;
         case FIELD:
            // decide upon the visibility
            if ((style & VIS_PRIVATE) == VIS_PRIVATE)
            {
               key = LabelerImages.IMG_FIELD_PRIVATE;
            }
            else if ((style & VIS_PROTECTED) == VIS_PROTECTED)
            {
               key = LabelerImages.IMG_FIELD_PROTECTED;
            }
            else if ((style & VIS_DEFAULT) == VIS_DEFAULT)
            {
               key = LabelerImages.IMG_FIELD_DEFAULT;
            }
            else if ((style & VIS_PUBLIC) == VIS_PUBLIC)
            {
               key = LabelerImages.IMG_FIELD_PUBLIC;
            }
            else
            {
               key = LabelerImages.IMG_FIELD_NONE;
            }
            break;
         case METHOD:
            // decide upon the visibility
            if ((style & VIS_PRIVATE) == VIS_PRIVATE)
            {
               key = LabelerImages.IMG_METHOD_PRIVATE;
            }
            else if ((style & VIS_PROTECTED) == VIS_PROTECTED)
            {
               key = LabelerImages.IMG_METHOD_PROTECTED;
            }
            else if ((style & VIS_DEFAULT) == VIS_DEFAULT)
            {
               key = LabelerImages.IMG_METHOD_DEFAULT;
            }
            else if ((style & VIS_PUBLIC) == VIS_PUBLIC)
            {
               key = LabelerImages.IMG_METHOD_PUBLIC;
            }
            else
            {
               key = LabelerImages.IMG_METHOD_NONE;
            }
            break;
         case VARIABLE:
            key = LabelerImages.IMG_VARIABLE_LOCAL;
            break;
         default:
            key = LabelerImages.IMG_UNKNOWN;
            break;
      }

      // TODO: apply decorations

      return Activator.getInstance().getImage(key);
   }


   protected final Image getImage(Element type)
   {
      return getImage(type, 0);
   }


   /**
    * This method should return an image representation for the given AST element.
    * 
    * @param element The element to represent.
    * @return Returns the image for the element.
    */
   public abstract Image getImage(EObject element);


   /**
    * This method should return a simple text representation for the given AST element. In case of a
    * package this could be its (simple) name for example, for method only its name, etc.
    * 
    * @param element The element to label.
    * @return Returns the text representation for the element.
    */
   public abstract String getText(EObject element);


   /**
    * This method should return a text representing the given AST element. A package element for
    * example could be represented fully qualified, a method could show its parameters and return
    * type, etc.
    * 
    * @param element The element to label.
    * @return Returns the (full) text representation for the element.
    */
   public abstract String getFullText(EObject element);
}
