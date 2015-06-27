package org.reclipse.tracer.symbolicexecution.callgraph;


import java.util.HashMap;

import org.eclipse.jdt.core.IMethod;


public class CallGraph
{
   HashMap<IMethod, CallGraphMethod> methods;

   CallGraphMethodSearcher searcher;


   public CallGraph(CallGraphMethodSearcher searcher)
   {
      this.searcher = searcher;
      this.methods = new HashMap<IMethod, CallGraphMethod>();
   }


   public HashMap<IMethod, CallGraphMethod> getMethods()
   {
      return this.methods;
   }


   public CallGraphMethodSearcher getSearcher()
   {
      return this.searcher;
   }


   public CallGraphMethod createCallGraphMethod(IMethod iMethod)
   {
      CallGraphMethod callGraphMethod = this.getMethods().get(iMethod);
      if (callGraphMethod == null)
      {
         callGraphMethod = new CallGraphMethod(iMethod, this);
      }
      return callGraphMethod;
   }
}
