package org.reclipse.tracer.symbolicexecution.ui.util;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.reclipse.tracer.symbolicexecution.callgraph.CallGraph;
import org.reclipse.tracer.symbolicexecution.callgraph.CallGraphMethod;
import org.reclipse.tracer.symbolicexecution.callgraph.CallGraphMethodSearcher;
import org.reclipse.tracer.symbolicexecution.callgraph.CallGraphPath;

import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.variables.FormalParameter;


/**
 * This class is not used at the moment.
 * 
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class SymbolicMethodFinder
{

   public final class MethodSearchRequestor extends SearchRequestor
   {
      private IMethod iMethod;


      public IMethod getIMethod()
      {
         return iMethod;
      }


      public void acceptSearchMatch(SearchMatch match)
      {
         if (match.getElement() instanceof IMethod)
         {
            iMethod = (IMethod) match.getElement();
         }
      }
   }


   IJavaSearchScope searchScope;

   private IJavaProject javaProject;


   public SymbolicMethodFinder(IJavaProject javaProject,
         IJavaSearchScope iJavaSearchScope)
   {
      this.searchScope = iJavaSearchScope;
      this.javaProject = javaProject;
   }


   public IMethod findSymbolicMethod(Method triggerMethod,
         List<Method> methodDeclarationsFromTraceDefinition)
   {
      String dateTime = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
            DateFormat.MEDIUM).format(new Date());
      System.out.println("Start SymbolicMethodFinder: " + dateTime);
      Long startTimeMillis = System.currentTimeMillis();

      IMethod iMethod = getIMethodFromAMethodDeclaration(triggerMethod);
      CallGraphMethodSearcher searcher = new CallGraphMethodSearcher(
            this.searchScope);
      CallGraph callGraph = new CallGraph(searcher);
      CallGraphMethod callGraphMethod = callGraph
            .createCallGraphMethod(iMethod);
      IMethod result = findSymbolicMethod(
            callGraphMethod,
            getMethodsFromTraceDefinition(
                  methodDeclarationsFromTraceDefinition, callGraph));

      dateTime = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
            DateFormat.MEDIUM).format(new Date());
      System.out.println("End SymbolicMethodFinder: " + dateTime);
      Long endTimeMillis = System.currentTimeMillis();
      Long durationMillis = endTimeMillis - startTimeMillis;
      System.out.println("Duration: " + durationMillis / 1000 + " seconds");
      if (result != null)
      {
         System.out.println("Result: " + result.getKey());
      }
      else
      {
         System.out.println("No Result!");
      }

      return result;
   }


   private List<CallGraphMethod> getMethodsFromTraceDefinition(
         List<Method> methods, CallGraph callGraph)
   {
      System.out.println("TraceDef Methods:");
      List<CallGraphMethod> methodsList = new ArrayList<CallGraphMethod>();
      for (Method method : methods)
      {
         IMethod iMethod = getIMethodFromAMethodDeclaration(method);
         methodsList.add(callGraph.createCallGraphMethod(iMethod));
         System.out.println(iMethod.getElementName());
      }
      return methodsList;
   }


   public IMethod findSymbolicMethod(CallGraphMethod callGraphMethod,
         List<CallGraphMethod> methodsFromTraceDefinition)
   {
      Set<CallGraphMethod> tempSymbolicMethods = callGraphMethod.getCallers();
      for (CallGraphMethod method : tempSymbolicMethods)
      {
         if (invokesAllMethods(method, methodsFromTraceDefinition))
         {
            // if all methods from the trace definition are invoked by the given method, it can
            // be used as symbolic method
            return method.getIMethod();
         }
         else
         {
            // if the current method is not the right method to be the symbolic method, do recursion
            IMethod symbolicMethod = findSymbolicMethod(method,
                  methodsFromTraceDefinition);
            if (symbolicMethod != null)
            {
               return symbolicMethod;
            }
         }
      }

      return null;
   }


   /**
    * Checks if the given method invokes all methods from the trace definition that are declared as
    * to be considered.
    * 
    * @param method
    * @param traceDefinition
    * @return
    */
   private boolean invokesAllMethods(CallGraphMethod method,
         List<CallGraphMethod> methodsFromTraceDefinition)
   {
      for (CallGraphMethod methodFromTD : methodsFromTraceDefinition)
      {
         // for all considerMethods in the trace definition, check if they are invoked by the
         // given method
         if (!isInvoked(methodFromTD, method, new CallGraphPath()))
         {
            // if not, the given method can not be used as symbolic method
            return false;
         }
      }
      // all methods from the trace definition are invoked by the given method, so it can be used as
      // symbolic method
      return true;
   }


   /**
    * Returns true if the considerMethod from the trace definition is (indirectly) invoked by the
    * given method.
    * 
    * @param considerMethod
    * @param method
    * @return
    */
   private boolean isInvoked(CallGraphMethod considerMethod,
         CallGraphMethod method, CallGraphPath path)
   {
      if (isInvokedDirectly(considerMethod, method))
      {
         return true;
      }
      Stack<Iterator<CallGraphMethod>> stack = new Stack<Iterator<CallGraphMethod>>();
      path.addMethod(considerMethod);
      if (!path.isCyclic())
      {
         stack.push(considerMethod.getCallers().iterator());
      }
      while (!stack.empty())
      {
         Iterator<CallGraphMethod> iterator = stack.peek();
         if (iterator.hasNext())
         {
            CallGraphMethod callerMethod = iterator.next();
            path = path.getCopy();
            path.addMethod(callerMethod);
            if (isInvokedDirectly(callerMethod, method))
            {
               return true;
            }
            else
            {
               if (!path.isCyclic())
               {
                  stack.push(callerMethod.getCallers().iterator());
               }
            }
         }
         else
         {
            stack.pop();
         }
      }
      return false;


      // recursive version:
      // path.addMethod(considerMethod);
      // if (!path.isCyclic())
      // {
      // if (isInvokedDirectly(considerMethod, method))
      // {
      // return true;
      // }
      // else
      // {
      // Set<CallGraphMethod> callers = considerMethod.getCallers();
      // for (CallGraphMethod caller : callers)
      // {
      // if (isInvoked(caller, method, path.getCopy()))
      // {
      // return true;
      // }
      // }
      // }
      // }
      // return false;
   }


   private boolean isInvokedDirectly(CallGraphMethod considerMethod,
         CallGraphMethod method)
   {
      Set<CallGraphMethod> callers = considerMethod.getCallers();
      for (CallGraphMethod callGraphMethod : callers)
      {
         if (method == callGraphMethod)
         {
            return true;
         }
      }
      return false;
   }


   private IMethod getIMethodFromAMethodDeclaration(Method method)
   {
      GASTClass parentClass = ((GASTClass) method.eContainer());
      try
      {
         // search pattern
         String pattern = getMethodSearchPattern(method);
         SearchPattern searchPattern = SearchPattern.createPattern(pattern,
               IJavaSearchConstants.METHOD, IJavaSearchConstants.DECLARATIONS,
               SearchPattern.R_PATTERN_MATCH);
         // search scope
         SearchRequestor requestor = new MethodSearchRequestor();
         // search engine
         SearchEngine searchEngine = new SearchEngine();
         searchEngine.search(searchPattern,
               new SearchParticipant[] { SearchEngine
                     .getDefaultSearchParticipant() }, this.searchScope,
               requestor, new NullProgressMonitor());

         IMethod iMethod = ((MethodSearchRequestor) requestor).getIMethod();
         return iMethod;
      }
      catch (JavaModelException e)
      {
         e.printStackTrace();
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
      return null;
   }


   private String getMethodSearchPattern(Method method)
   {
      StringBuffer s = new StringBuffer();
      s.append(method.getSimpleName());
      s.append("(");
      for (Iterator<FormalParameter> iterator = method.getFormalParameters()
            .iterator(); iterator.hasNext();)
      {
         FormalParameter param = (FormalParameter) iterator.next();
         s.append(param.getType().getSimpleName());
         if (iterator.hasNext())
         {
            s.append(",");
         }
      }
      s.append(") ");
      s.append(method.getReturnTypeDeclaration().getTargetType()
            .getSimpleName());
      return s.toString();
   }
}
