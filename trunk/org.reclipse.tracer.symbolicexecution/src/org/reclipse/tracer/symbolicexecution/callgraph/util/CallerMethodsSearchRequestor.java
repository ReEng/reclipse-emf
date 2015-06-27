package org.reclipse.tracer.symbolicexecution.callgraph.util;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.search.MethodReferenceMatch;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;


public class CallerMethodsSearchRequestor extends SearchRequestor
{

   private List<IMethod> callerMethods = null;


   public CallerMethodsSearchRequestor()
   {
      super();
      this.callerMethods = new ArrayList<IMethod>();
   }


   public List<IMethod> getCallerMethods()
   {
      return this.callerMethods;
   }


   @Override
   public void acceptSearchMatch(SearchMatch match) throws CoreException
   {
      if (match instanceof MethodReferenceMatch)
      {
         MethodReferenceMatch methodMatch = (MethodReferenceMatch) match;
         if (methodMatch.getElement() instanceof IMethod)
         { // do not regard BinaryType objects here
            IMethod element = (IMethod) methodMatch.getElement();
            this.callerMethods.add(element);
         }
      }
   }

}
