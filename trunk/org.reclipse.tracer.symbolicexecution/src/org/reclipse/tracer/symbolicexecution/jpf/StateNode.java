package org.reclipse.tracer.symbolicexecution.jpf;


import gov.nasa.jpf.jvm.ClassInfo;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;


public class StateNode
{
   public int id = 0;

   public StateNode parent = null;

   public List<StateNode> children = new LinkedList<StateNode>();

   public List<ClassInfo> classes = new LinkedList<ClassInfo>();

   public LinkedList<MethodEntry> methods = new LinkedList<MethodEntry>();

   public boolean isnew = true;

   public boolean isend = false;
   
   public long time = 0;


   public StateNode(int id, StateNode parent)
   {
      this.id = id;
      this.parent = parent;
      if (this.parent != null)
         this.parent.children.add(this);
   }


   public void print(PrintWriter out)
   {
      this.print(0, out);
   }


   private void print(int depth, PrintWriter out)
   {
      out.println(this.stringNTimes("\t", depth) + "<StateNode id=\"" + this.id
            + "\" " + (this.isend ? "end=\"end\"" : "") + ">");

      for (ClassInfo className : this.classes)
         out.println(this.stringNTimes("\t", depth + 1)
               + "<ClassLoaded name=\"" + className.getName() + "\" />");

      for (MethodEntry method : this.methods)
         out.println(this.stringNTimes("\t", depth + 2) + "<MethodCall name=\""
               + method.calleeMethod.getFullName() + method.calleeMethod.getClassName()
               + "\" />");

      for (StateNode children : this.children)
         children.print(depth + 1, out);
      out.println(this.stringNTimes("\t", depth) + "</StateNode>");
   }


   private String stringNTimes(String string, int n)
   {
      String retval = "";
      for (int i = 0; i < n; i++)
         retval += string;
      return retval;
   }
}
