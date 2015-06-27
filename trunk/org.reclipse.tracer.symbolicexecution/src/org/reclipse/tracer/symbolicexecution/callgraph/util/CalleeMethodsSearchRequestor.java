package org.reclipse.tracer.symbolicexecution.callgraph.util;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.search.MethodDeclarationMatch;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;


public class CalleeMethodsSearchRequestor extends SearchRequestor
{

   private List<IMethod> calleeMethods = null;


   public CalleeMethodsSearchRequestor()
   {
      super();
      this.calleeMethods = new ArrayList<IMethod>();
   }


   public List<IMethod> getCalleeMethods()
   {
      return this.calleeMethods;
   }


   @Override
   public void acceptSearchMatch(SearchMatch match) throws CoreException
   {
      if (match instanceof MethodDeclarationMatch)
      {
         MethodDeclarationMatch methodMatch = (MethodDeclarationMatch) match;
         if (methodMatch.getElement() instanceof IMethod && ((IMethod)methodMatch.getElement()).getCompilationUnit()!=null)
         { // do not regard binary classes here
            IMethod element = (IMethod) methodMatch.getElement();
            this.calleeMethods.add(element);
         }
      }
   }

}
