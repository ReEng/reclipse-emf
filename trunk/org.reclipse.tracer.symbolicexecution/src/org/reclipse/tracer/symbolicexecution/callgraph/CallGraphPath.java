package org.reclipse.tracer.symbolicexecution.callgraph;


import java.util.ArrayList;
import java.util.List;


public class CallGraphPath
{
   List<CallGraphMethod> methods;

   boolean isCyclic;


   public CallGraphPath()
   {
      this.methods = new ArrayList<CallGraphMethod>();
      this.isCyclic = false;
   }


   public void addMethod(CallGraphMethod method)
   {
      if (this.methods.contains(method))
      {
         this.isCyclic = true;
      }
      else
      {
         this.methods.add(method);
      }
   }


   public boolean isCyclic()
   {
      return this.isCyclic;
   }


   public String toString()
   {
      return this.methods.toString();
   }


   /**
    * Creates a new path with the same methods as the current one. Is needed where the callerTree
    * splits.
    * 
    * @return
    */
   public CallGraphPath getCopy()
   {
      CallGraphPath newPath = new CallGraphPath();
      for (CallGraphMethod method : this.methods)
      {
         newPath.addMethod(method);
      }
      return newPath;
   }


   public void removeMethod(CallGraphMethod callerMethod)
   {
      this.methods.remove(callerMethod);
   }
}
