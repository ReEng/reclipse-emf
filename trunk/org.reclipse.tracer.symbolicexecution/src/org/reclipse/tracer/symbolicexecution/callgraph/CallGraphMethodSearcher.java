package org.reclipse.tracer.symbolicexecution.callgraph;


import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.reclipse.tracer.symbolicexecution.callgraph.util.CalleeMethodsSearchRequestor;
import org.reclipse.tracer.symbolicexecution.callgraph.util.CallerMethodsSearchRequestor;
import org.reclipse.tracer.symbolicexecution.callgraph.util.ConstructorCallsSearchRequestor;


public class CallGraphMethodSearcher
{
   IJavaSearchScope searchScope;

   public CallGraphMethodSearcher(IJavaSearchScope searchScope)
   {
      this.searchScope = searchScope;
   }


   /**
    * Returns a list of methods that invoke the given method.
    * 
    * @param method
    * @return List<IMethod>
    */
   public List<IMethod> getCallerMethods(IMethod method)
   {
      SearchPattern pattern = SearchPattern.createPattern(method,
            IJavaSearchConstants.REFERENCES);
      CallerMethodsSearchRequestor requestor = new CallerMethodsSearchRequestor();
      SearchEngine searchEngine = new SearchEngine();
      try
      {
         searchEngine.search(pattern, new SearchParticipant[] { SearchEngine
               .getDefaultSearchParticipant() }, this.searchScope, requestor,
               null);
         return requestor.getCallerMethods();
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
      return null;
   }


   /**
    * Not used at the moment.
    * 
    * Returns a list of methods that are invoked by the given method.
    * 
    * @param method
    * @return List<IMethod>
    */
   public List<IMethod> getCalleeMethods(IMethod method)
   {
      CalleeMethodsSearchRequestor requestor = new CalleeMethodsSearchRequestor();
      SearchEngine searchEngine = new SearchEngine();
      try
      {
         searchEngine.searchDeclarationsOfSentMessages(method, requestor, null);
         List<IMethod> callees = requestor.getCalleeMethods();
         callees.addAll(getConstructorCalls(method));
         return callees;
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
      return null;
   }


   /**
    * Not used at the moment.
    * 
    * Returns a list of constructors that are invoked by the given method.
    * 
    * @param method
    * @return List<IMethod>
    */
   private List<IMethod> getConstructorCalls(IMethod method)
   {
      ConstructorCallsSearchRequestor requestor = new ConstructorCallsSearchRequestor();
      requestor.setEnclosingMethod(method);
      SearchEngine searchEngine = new SearchEngine();
      try
      {
         searchEngine.searchDeclarationsOfReferencedTypes(method, requestor,
               null);
         return requestor.getConstructors();
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
      return null;
   }
}
