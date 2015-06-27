package org.reclipse.tracer.symbolicexecution.jpf;

import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.MethodInfo;

public class MethodEntry
{
   public int callee = -1;
   public MethodInfo calleeMethod = null;
   public ClassInfo calleeClass = null;

   public int caller = -1;
   public MethodInfo callerMethod = null;
   public ClassInfo callerClass = null;

   public String thread = null;
   
   public Object[] arguments = null;
   
   public long time = 0;

   public MethodEntry(int caller, MethodInfo callerMethod, 
		   				ClassInfo callerClass,
		   				int callee, MethodInfo calleeMethod, 
		   				ClassInfo calleeClass,
		   				Object[] arguments, String thread)
   {      
      this.caller = caller;
      this.callerMethod = callerMethod;
      this.callerClass = callerClass;
      this.callee = callee;
      this.calleeMethod = calleeMethod;
      this.calleeClass = calleeClass;
      this.arguments = arguments;
      this.thread = thread;
   }
}
