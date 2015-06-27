package org.reclipse.tracer.symbolicexecution.callgraph;


import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.core.IMethod;


public class CallGraphMethod
{

   HashSet<CallGraphMethod> callers;

   IMethod iMethod;

   boolean callersExpanded;

   CallGraph callGraph;


   public CallGraphMethod(IMethod iMethod, CallGraph callGraph)
   {
      this.callers = new HashSet<CallGraphMethod>();
      this.callersExpanded = false;
      this.iMethod = iMethod;
      this.callGraph = callGraph;
      this.callGraph.getMethods().put(this.iMethod, this);
   }


   public HashSet<CallGraphMethod> getCallers()
   {
      if (!this.callersExpanded)
      {
         expandCallers();
      }
      return this.callers;
   }


   private void expandCallers()
   {
      System.out.println("Expand callers of " + this.iMethod.getKey());
      List<IMethod> methods = this.callGraph.getSearcher().getCallerMethods(
            this.iMethod);
      for (IMethod iMethod : methods)
      {
         CallGraphMethod callGraphMethod = this.callGraph.getMethods().get(
               iMethod);
         if (callGraphMethod == null)
         {
            callGraphMethod = this.callGraph.createCallGraphMethod(iMethod);
         }
         if (!this.callers.contains(callGraphMethod))
         {
            this.callers.add(callGraphMethod);
         }
      }
      this.callersExpanded = true;
   }


   public IMethod getIMethod()
   {
      return this.iMethod;
   }


   public String toString()
   {
      return this.iMethod.toString();
   }

}
