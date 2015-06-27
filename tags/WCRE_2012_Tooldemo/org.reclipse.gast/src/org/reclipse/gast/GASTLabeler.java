package org.reclipse.gast;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.reclipse.metamodel.AbstractElementLabeler;

import de.fzi.gast.accesses.BaseAccess;
import de.fzi.gast.accesses.CompositeAccess;
import de.fzi.gast.accesses.FunctionAccess;
import de.fzi.gast.accesses.TypeAccess;
import de.fzi.gast.accesses.VariableAccess;
import de.fzi.gast.core.Directory;
import de.fzi.gast.core.File;
import de.fzi.gast.core.NamedModelElement;
import de.fzi.gast.core.Package;
import de.fzi.gast.core.Position;
import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Constructor;
import de.fzi.gast.functions.Function;
import de.fzi.gast.functions.Method;
import de.fzi.gast.statements.Statement;
import de.fzi.gast.types.GASTArray;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.GASTEnumeration;
import de.fzi.gast.types.GASTType;
import de.fzi.gast.variables.Field;
import de.fzi.gast.variables.FormalParameter;
import de.fzi.gast.variables.Variable;


public class GASTLabeler extends AbstractElementLabeler
{
   @Override
   public String getText(EObject element)
   {
      if (element instanceof NamedModelElement)
      {
         return getText((NamedModelElement) element);
      }

      if (element instanceof BaseAccess)
      {
         return getText((BaseAccess) element);
      }

      if (element instanceof Position)
      {
         return getText((Position) element);
      }

      if (element instanceof Statement)
      {
         return "statement";
      }

      if (element instanceof File)
      {
         return getText((File) element);
      }

      if (element instanceof Root)
      {
         return getText((Root) element);
      }

      return element.toString();
   }


   @Override
   public String getFullText(EObject element)
   {
      if (element instanceof Directory)
      {
         return getFullText((Directory) element);
      }

      if (element instanceof Package)
      {
         return getFullText((Package) element);
      }

      if (element instanceof Function)
      {
         return getFullText((Function) element);
      }

      return getText(element);
   }


   @Override
   public Image getImage(EObject element)
   {
      // prepare elements
      Element type = Element.UNKNOWN;
      int flags = 0;

      if (element instanceof Root)
      {
         type = Element.PROJECT;
      }
      else if (element instanceof Directory)
      {
         type = Element.FOLDER;
      }
      else if (element instanceof File)
      {
         type = Element.FILE;
      }
      else if (element instanceof Package)
      {
         type = Element.PACKAGE;
      }
      else if (element instanceof GASTClass)
      {
         // get element
         GASTClass gastClass = (GASTClass) element;

         // provide the type
         if (element instanceof GASTEnumeration)
         {
            type = Element.ENUM;
         }
         else if (((GASTClass) element).isInterface())
         {
            type = Element.INTERFACE;
         }
         else
         {
            // TODO: find @interface/annotation type?!?
            type = Element.CLASS;

            // check for modifiers
            if (gastClass.isAbstract())
            {
               flags |= MOD_ABSTRACT;
            }
            else if (gastClass.isFinal())
            {
               flags |= MOD_FINAL;
            }
         }

         // check static
         if (gastClass.isStatic())
         {
            flags |= MOD_STATIC;
         }

         // check visibility
         switch (gastClass.getVisibility())
         {
            case VISIBILITYPRIVAT:
               flags |= VIS_PRIVATE;
               break;
            case VISIBILITYPROTECTED:
               flags |= VIS_PROTECTED;
               break;
            case VISIBILITYPACKAGE:
               flags |= VIS_DEFAULT;
               break;
            case VISIBILITYPUBLIC:
               flags |= VIS_PUBLIC;
               break;
            default:
               break;
         }
      }
      else if (element instanceof Constructor)
      {
         // get element
         Constructor constructor = (Constructor) element;

         // provide type
         type = Element.METHOD;

         // set constructor
         flags |= FLAG_CONSTRUCTOR;

         // check visibility
         switch (constructor.getVisibility())
         {
            case VISIBILITYPRIVAT:
               flags |= VIS_PRIVATE;
               break;
            case VISIBILITYPROTECTED:
               flags |= VIS_PROTECTED;
               break;
            case VISIBILITYPACKAGE:
               flags |= VIS_DEFAULT;
               break;
            case VISIBILITYPUBLIC:
               flags |= VIS_PUBLIC;
               break;
            default:
               break;
         }
      }
      else if (element instanceof Method)
      {
         // get element
         Method method = (Method) element;

         // provide type
         type = Element.METHOD;

         // abstract or some others
         if (method.isAbstract())
         {
            flags |= MOD_ABSTRACT;
         }
         else
         {
            if (method.isStatic())
            {
               flags |= MOD_STATIC;
            }
            if (method.isFinal())
            {
               flags |= MOD_FINAL;
            }
         }
         // TODO: check for 'implemented' or 'overridden'

         // check visibility
         switch (method.getVisibility())
         {
            case VISIBILITYPRIVAT:
               flags |= VIS_PRIVATE;
               break;
            case VISIBILITYPROTECTED:
               flags |= VIS_PROTECTED;
               break;
            case VISIBILITYPACKAGE:
               flags |= VIS_DEFAULT;
               break;
            case VISIBILITYPUBLIC:
               flags |= VIS_PUBLIC;
               break;
            default:
               break;
         }
      }
      else if (element instanceof Variable)
      {
         if (element instanceof Field)
         {
            // get element
            Field field = (Field) element;

            // provide type
            type = Element.FIELD;

            if (field.isStatic())
            {
               flags |= MOD_STATIC;
            }
            if (field.isFinal())
            {
               flags |= MOD_FINAL;
            }

            // TODO: check for 'transient' or 'volatile'

            // check visibility
            switch (field.getVisibility())
            {
               case VISIBILITYPRIVAT:
                  flags |= VIS_PRIVATE;
                  break;
               case VISIBILITYPROTECTED:
                  flags |= VIS_PROTECTED;
                  break;
               case VISIBILITYPACKAGE:
                  flags |= VIS_DEFAULT;
                  break;
               case VISIBILITYPUBLIC:
                  flags |= VIS_PUBLIC;
                  break;
               default:
                  break;
            }
         }
         else
         {
            type = Element.VARIABLE;
         }
      }

      return getImage(type, flags);
   }

   /**
    * This switch turns on/off the following features:
    * <ul>
    * <li>Arrays: show the length</li>
    * <li>Anonymous classes: show the implementing/super class</li>
    * <li>Composite Accesses: show a list of the contained accesses</li>
    * <li>Generic types: show the type arguments</li>
    * <li>Methods: show parameter and return types</li>
    * </ul>
    */
   private static final boolean SHOW_ADVANCED = true;


   private static final String DIM_START = "["; //$NON-NLS-1$

   private static final String DIM_END = "]"; //$NON-NLS-1$


   private static final String ENUM_START = "("; //$NON-NLS-1$

   private static final String ENUM_END = ")"; //$NON-NLS-1$

   private static final String ENUM_SEP = ", "; //$NON-NLS-1$

   private static final String ENUM_SEP2 = "; "; //$NON-NLS-1$


   private static final String DIR_SEP = "/"; //$NON-NLS-1$


   private static final String PACKAGE_SEP = "."; //$NON-NLS-1$


   private static final String GENERIC_START = "<"; //$NON-NLS-1$

   private static final String GENERIC_END = ">"; //$NON-NLS-1$


   private static final String PREFIX_LOCAL = "$"; //$NON-NLS-1$

   private static final String RETURN_TYPE_SEP = ": "; //$NON-NLS-1$


   private static String getText(NamedModelElement element)
   {
      if (SHOW_ADVANCED)
      {
         // local classes do not have names
         if (element instanceof GASTClass)
         {
            GASTClass clazz = (GASTClass) element;
            if (clazz.isLocal())
            {
               // take first name
               for (GASTClass supClass : clazz.getSuperTypes())
               {
                  return PREFIX_LOCAL + getText(supClass);
               }
            }
         }

         // array handling
         if (element instanceof GASTArray)
         {
            GASTArray arr = (GASTArray) element;

            StringBuilder text = new StringBuilder();

            text.append(getText(arr.getBaseType()));
            text.append(DIM_START);
            text.append(DIM_END);

            return text.toString();
         }
      }

      return element.getSimpleName();
   }


   private static String getText(final BaseAccess element)
   {
      if (element instanceof FunctionAccess)
      {
         return getText(((FunctionAccess) element).getTargetFunction());
      }

      if (element instanceof VariableAccess)
      {
         return getText(((VariableAccess) element).getTargetVariable());
      }

      if (element instanceof TypeAccess)
      {
         TypeAccess acc = (TypeAccess) element;

         StringBuilder text = new StringBuilder();

         text.append(getText(acc.getTargetType()));

         // type arguments
         if (!acc.getTypeArguments().isEmpty())
         {
            int i = 0;
            text.append(GENERIC_START);
            for (GASTType param : acc.getTypeArguments())
            {
               text.append(getText(param));
               i++;
               if (i < acc.getTypeArguments().size())
               {
                  text.append(ENUM_SEP);
               }
            }
            text.append(GENERIC_END);
         }

         return text.toString();
      }

      if (SHOW_ADVANCED && element instanceof CompositeAccess)
      {
         List<BaseAccess> accesses = ((CompositeAccess) element).getAccesses();

         StringBuilder text = new StringBuilder();

         int i = 0;
         text.append(ENUM_START);
         for (BaseAccess access : accesses)
         {
            text.append(getText(access));
            i++;
            if (i < accesses.size())
            {
               text.append(ENUM_SEP);
            }
         }
         text.append(ENUM_END);

         return text.toString();
      }

      return String.valueOf(element);
   }


   private static String getText(final Position element)
   {
      StringBuilder text = new StringBuilder();

      text.append(ENUM_START);
      text.append(element.getStartLine());
      text.append(ENUM_SEP);
      text.append(element.getStartColumn());
      text.append(ENUM_SEP2);
      text.append(element.getEndLine());
      text.append(ENUM_SEP);
      text.append(element.getEndColumn());
      text.append(ENUM_END);

      return text.toString();
   }


   private static String getText(File element)
   {
      return element.getFileSystemPath();
   }


   private static String getText(Root element)
   {
      return Root.class.getSimpleName();
   }


   private static String getFullText(Function element)
   {
      StringBuilder text = new StringBuilder();

      text.append(element.getSimpleName());

      // parameters
      text.append(ENUM_START);
      int i = 1;
      for (FormalParameter param : element.getFormalParameters())
      {
         text.append(getText(param.getTypeDeclaration()));
         if (i < element.getFormalParameters().size())
         {
            text.append(ENUM_SEP);
         }
         i++;
      }
      text.append(ENUM_END);

      // return type
      if (!(element instanceof Constructor))
      {
         text.append(RETURN_TYPE_SEP);
         text.append(getText(element.getReturnTypeDeclaration()));
      }

      return text.toString();
   }


   private static String getFullText(Package element)
   {
      StringBuilder text = new StringBuilder();

      Package parent = element.getSurroundingPackage();
      while (parent != null)
      {
         text.insert(0, PACKAGE_SEP);
         text.insert(0, parent.getSimpleName());
         parent = parent.getSurroundingPackage();
      }

      text.append(element.getSimpleName());

      return element.getQualifiedName();
   }


   private static String getFullText(Directory element)
   {
      StringBuilder text = new StringBuilder();

      Directory parent = element.getParentDirectory();
      while (parent != null)
      {
         text.insert(0, DIR_SEP);
         text.insert(0, parent.getSimpleName());
         parent = parent.getParentDirectory();
      }

      text.append(element.getSimpleName());

      return text.toString();
   }
}
