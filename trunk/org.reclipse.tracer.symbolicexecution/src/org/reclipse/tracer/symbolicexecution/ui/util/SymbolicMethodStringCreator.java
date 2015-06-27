package org.reclipse.tracer.symbolicexecution.ui.util;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;

import de.fzi.gast.functions.Method;

public class SymbolicMethodStringCreator
{


   private IJavaProject javaProject;


   public SymbolicMethodStringCreator(IJavaProject javaProject)
   {
      this.javaProject = javaProject;
   }


   public String addSymbolicMethodStringForTrigger(String triggerMethod,
         EObject actualMethod,
         List<Method> methodsFromTraceDefinition,
         IJavaSearchScope iJavaSearchScope)
   {
      StringBuffer symbolicMethodString = new StringBuffer();
      SymbolicMethodFinder symbolicMethodFinder = new SymbolicMethodFinder(this.javaProject,
            iJavaSearchScope);
      symbolicMethodString
            .append(addSymbolicMethodStringForMethod(symbolicMethodFinder
                  .findSymbolicMethod((Method)actualMethod, methodsFromTraceDefinition)));

      return symbolicMethodString.toString();
   }


   /**
    * Not used at the moment
    * @param method
    * @return
    */
   private String addSymbolicMethodStringForMethod(IMethod method)
   {
      StringBuffer symbolicMethodString = new StringBuffer();
      try
      {
         if (method != null)
         {
            if (method.getDeclaringType().getPackageFragment().getElementName()
                  .length() > 0)
            {
               symbolicMethodString.append(method.getDeclaringType()
                     .getPackageFragment().getElementName());
               symbolicMethodString.append(".");
            }
            symbolicMethodString.append(method.getDeclaringType()
                  .getElementName());
            symbolicMethodString.append(".");
            if (method.isConstructor())
            {
               symbolicMethodString.append("<init>");
            }
            else
            {
               symbolicMethodString.append(method.getElementName());
            }
            String[] params = null;

            params = method.getParameterNames();

            if (params != null && params.length > 0)
            {
               symbolicMethodString.append("(");
               for (int i = 0; i < params.length; i++)
               {
                  symbolicMethodString.append("sym");
                  if (i < params.length - 1)
                  {
                     symbolicMethodString.append("#");
                  }
               }
               symbolicMethodString.append(")");
            }
         }
      }
      catch (JavaModelException e)
      {
         e.printStackTrace();
      }

      return symbolicMethodString.toString();
   }


   public boolean isValidSymbolicMethodString(String symbMethodString)
   {
      if (symbMethodString != null)
      {
         String javaIdentifierRegex = "[a-zA-Z$_][a-zA-Z0-9$_.]*";
         String classNameRegex = javaIdentifierRegex;
         String methodNameRegex = javaIdentifierRegex;
         String constructor = "<init>";
         String methodOrConstructorRegex = "(" + methodNameRegex + "|"
               + constructor + ")";
         String paramRegex = "((sym)|(con))";
         String paramsRegex = "\\(" + "(" + paramRegex + "(#" + paramRegex
               + ")*" + ")?" + "\\)";
         String symbMethodRegex = classNameRegex + "\\."
               + methodOrConstructorRegex + "" + "(" + paramsRegex + ")?";
         String symbMethodsRegex = symbMethodRegex + "(," + symbMethodRegex
               + ")*";

         boolean stringMatches = symbMethodString.matches(symbMethodsRegex);

         if (!stringMatches)
         {
            return false;
         }
         return true;
      }
      else
      {
         return false;
      }
   }
}
