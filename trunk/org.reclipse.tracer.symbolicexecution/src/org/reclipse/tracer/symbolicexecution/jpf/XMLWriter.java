package org.reclipse.tracer.symbolicexecution.jpf;


import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;

import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.JVM;


public class XMLWriter
{
   private int methodEntryId = 1;

   private PrintWriter out = new PrintWriter(System.out);


   public void setOut(PrintWriter out)
   {
      this.out = out;
   }


   public void writeClassLoaded(ClassInfo ci)
   {
      this.out.println("<ClassLoaded name=\"" + ci.getName() + "\">");
      if (!ci.getSuperClass().isSystemClass())
         this.out.println("\t<SuperType name=\"" + ci.getSuperClass().getName()
               + "\"/>");
      this.out.println("</ClassLoaded>\n");
   }


   public void writeMethodEntry(MethodEntry me)
   {
      if (me.calleeMethod.isClinit() || me.calleeMethod.isCtor())
         return;

      this.out.println("<MethodEntry id=\"" + this.methodEntryId++ + "\" name=\"" + me.calleeMethod.getName() + "\" thread=\"" + me.thread + "\">");
      this.out.println("\t<Caller " + (me.caller!=-1?" id=\"" + me.caller
            + "\" ":"") + " type=\"" + me.callerClass.getName() + "\"/>");
      this.out.println("\t<Callee " + (me.callee!=-1?" id=\"" + me.callee
            + "\" ":"") + " type=\"" +me.calleeClass.getName() + "\"/>");
      
      String[] argumentTypes = me.calleeMethod.getArgumentTypeNames();
      for(int i = 0; i < argumentTypes.length; i++)
         this.out.println("\t<Argument type=\"" + argumentTypes[i] + "\" id=\"" + me.arguments[i] + "\" />");

      this.out.println("</MethodEntry>\n");
   }


   public void writeProcessStart(String name)
   {
      this.out.println("<ProcessStart name=\"" + name + "\"/>\n");
   }


   public void writeProcessEnd()
   {
      this.out.println("<ProcessEnd/>");
   }


   public void writeTraceStart(String mainClass)
   {
      this.out.println("<?xml version=\"1.0\" standalone=\"no\"?>");
      this.out
            .println("<!DOCTYPE Trace SYSTEM \"http://wwwcs.uni-paderborn.de/cs/fujaba/DTDs/Trace.dtd\">");
      this.out.println("<Trace mainclass=\"" + mainClass + "\" date=\""
            + new Date() + "\">\n");
   }


   public void writeTraceEnd()
   {
      this.out.println("</Trace>");
   }


   public void writeTraces(StateNode stateGraph)
   {
      this.writeTraceStart(JVM.getVM().getMainClassName());
      this.visitStateNode(stateGraph);
      this.writeTraceEnd();
   }


   private void visitStateNode(StateNode node)
   {
      if (node.isend)
      {
         this.printProcess(node);
         return;
      }
      for (StateNode child : node.children)
         this.visitStateNode(child);

   }


   private void printProcess(StateNode node)
   {
      LinkedList<MethodEntry> entries = new LinkedList<MethodEntry>();
      LinkedList<ClassInfo> classes = new LinkedList<ClassInfo>();
      StateNode current = node;
      while (current.parent != null)
      {
         entries.addAll(0, current.methods);
         classes.addAll(0, current.classes);
         current = current.parent;
      }

      this.writeProcessStart("main");
      while (!classes.isEmpty())
         this.writeClassLoaded(classes.pop());

      while (!entries.isEmpty())
      {
         MethodEntry me = entries.pop();
         this.writeMethodEntry(me);
      }
      this.writeProcessEnd();
   }
}
