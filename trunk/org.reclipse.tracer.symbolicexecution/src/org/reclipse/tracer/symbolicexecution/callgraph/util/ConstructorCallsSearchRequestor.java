package org.reclipse.tracer.symbolicexecution.callgraph.util;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jdt.core.search.TypeDeclarationMatch;


public class ConstructorCallsSearchRequestor extends SearchRequestor
{

   private List<IMethod> constructors = null;

   private IMethod enclosingMethod = null;


   public void setEnclosingMethod(IMethod enclosingMethod)
   {
      this.enclosingMethod = enclosingMethod;
   }


   public ConstructorCallsSearchRequestor()
   {
      super();
      this.constructors = new ArrayList<IMethod>();
   }


   public List<IMethod> getConstructors()
   {
      return this.constructors;
   }


   @Override
   public void acceptSearchMatch(SearchMatch match) throws CoreException
   {
      if (match instanceof TypeDeclarationMatch)
      {
         TypeDeclarationMatch typeMatch = (TypeDeclarationMatch) match;
         if (typeMatch.getElement() instanceof IType
               && ((IType) typeMatch.getElement()).getCompilationUnit() != null)
         { // do not regard BinaryType objects here
            IType element = (IType) typeMatch.getElement();
            for (IMethod method : element.getMethods())
            {
               if (method.isConstructor())
               {
                  if (containsCall(this.enclosingMethod, method))
                  {
                     this.constructors.add(method);
                  }
               }
            }
         }
      }
   }


   private boolean containsCall(IMethod enclosingMethod2, IMethod method)
   {
      try
      {
         StringBuffer callString = new StringBuffer();
         callString.append("new ");
         callString.append(method.getElementName());
         String methodSource = enclosingMethod2.getSource();
         if (methodSource.contains(callString.toString()))
         {
            return true;
         }
      }
      catch (JavaModelException e)
      {
         e.printStackTrace();
      }
      return false;
   }

}
