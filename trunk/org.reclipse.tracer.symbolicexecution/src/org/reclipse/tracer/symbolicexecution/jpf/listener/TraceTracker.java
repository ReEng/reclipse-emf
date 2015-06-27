package org.reclipse.tracer.symbolicexecution.jpf.listener;


import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.ElementInfo;
import gov.nasa.jpf.jvm.JVM;
import gov.nasa.jpf.jvm.MethodInfo;
import gov.nasa.jpf.jvm.ThreadInfo;
import gov.nasa.jpf.jvm.bytecode.Instruction;
import gov.nasa.jpf.jvm.bytecode.InvokeInstruction;
import gov.nasa.jpf.search.Search;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Status;
import org.reclipse.tracer.model.tracegraph.TGTrace;
import org.reclipse.tracer.model.tracegraph.xml.ITraceConstants;
import org.reclipse.tracer.model.tracegraph.xml.TraceGraphWriter;
import org.reclipse.tracer.symbolicexecution.SymbolicExecutionTracerPlugin;
import org.reclipse.tracer.symbolicexecution.jpf.MethodEntry;
import org.reclipse.tracer.symbolicexecution.jpf.StateNode;
import org.reclipse.tracer.symbolicexecution.jpf.TGCreator;
import org.reclipse.tracer.symbolicexecution.util.TraceDefinitionFilter;


/**
 * Creates a Trace containing multiple TracePaths by using symbolic execution
 * 
 * @author avolk
 * @author Last editor: $Author: avolk $
 * @version $Revision: 231 $ $Date: 2010-10-23 11:02:10 +0200 (Sa, 23 Okt 2010) $
 * 
 */
public class TraceTracker extends ListenerAdapter
{
   private StateNode stateGraph = null;

   private StateNode currentStateNode = null;

   private String filename = "";

   private int numEndStates = 0;

   private TraceDefinitionFilter traceDefFilter = null;

   private boolean traceCompressed = false;

   private boolean timeStamps = false;

   private boolean argumentValues = false;

   private Set<ClassInfo> classes;


   /**
    * @see gov.nasa.jpf.ListenerAdapter#instructionExecuted(gov.nasa.jpf.jvm.JVM)
    */
   public void instructionExecuted(JVM vm)
   {
      Instruction instr = vm.getLastInstruction();
      ThreadInfo ti = vm.getLastThreadInfo();

      if (instr instanceof InvokeInstruction)
      {
         InvokeInstruction invInstr = ((InvokeInstruction) instr);

         String method = invInstr.toString();
         // if(method.contains("Test"))
         // System.out.println(method);

         MethodInfo mi = ((InvokeInstruction) instr).getInvokedMethod();
         if (this.traceDefFilter != null && this.traceDefFilter.accept(mi))
         {
            // Callee
            ElementInfo calleeElement = ti.getThisElementInfo();
            MethodInfo calleeMethod = invInstr.getInvokedMethod();
            ClassInfo calleeClass = null;
            int calleeID = -1;
            if (calleeMethod.isStatic())
            {
               // static method: method declared in class
               calleeClass = calleeMethod.getClassInfo();
               // static methods do not have a instance
               calleeID = -1;
            }
            else
            {
               // non static method: method is maybe overridden. get the current class
               // instead of the class the method is declared in.
               calleeClass = calleeElement.getClassInfo();
               // get the current instance
               calleeID = calleeElement.getThisReference();
            }
            assert (calleeClass != null);

            // Caller
            ElementInfo callerElement = ti.getElementInfo(ti
                  .getCallerStackFrame().getThis());
            if (callerElement != null)
            {
               MethodInfo callerMethod = invInstr.getMethodInfo();
               ClassInfo callerClass = null;
               int callerID = -1;
               if (callerMethod.isStatic())
               {
                  // see above
                  callerClass = callerMethod.getClassInfo();
                  callerID = -1;
               }
               else
               {
                  // see above
                  callerClass = callerElement.getClassInfo();
                  callerID = callerElement.getThisReference();
               }

               // Arguments
               Object[] argumentValues = invInstr.getArgumentValues(ti);
               Object[] arguments = new Object[argumentValues.length];
               for (int i = 0; i < arguments.length; i++)
               {
                  if (this.argumentValues)
                  {
                     if (argumentValues[i] instanceof ElementInfo)
                        arguments[i] = ((ElementInfo) argumentValues[i])
                              .getThisReference();
                     else
                        arguments[i] = argumentValues[i];
                  }
                  else
                     arguments[i] = null;
               }

               MethodEntry methodEntry = new MethodEntry(callerID,
                     callerMethod, callerClass, calleeID, calleeMethod,
                     calleeClass, arguments, ti.getName());

               this.currentStateNode.methods.add(methodEntry);

               if (this.timeStamps)
                  methodEntry.time = new Date().getTime();
            }
         }
      }
   }


   /**
    * @see gov.nasa.jpf.ListenerAdapter#classLoaded(gov.nasa.jpf.jvm.JVM)
    */
   public void classLoaded(JVM vm)
   {
      if (this.traceDefFilter != null)
      {
         ClassInfo lastClassInfo = vm.getLastClassInfo();
         if (this.currentStateNode != null)
         {
            if (acceptedByTraceDef(lastClassInfo))
            {
               this.currentStateNode.classes.add(lastClassInfo);
            }
            if (this.classes != null && !this.classes.isEmpty())
            {
               this.currentStateNode.classes.addAll(this.classes);
               this.classes.clear();
            }
         }
         else
         {
            if (acceptedByTraceDef(lastClassInfo))
            {
               if (this.classes == null)
               {
                  this.classes = new HashSet<ClassInfo>();
               }
               this.classes.add(lastClassInfo);
            }
         }
      }
   }


   private boolean acceptedByTraceDef(ClassInfo lastClassInfo)
   {
      if (this.traceDefFilter.accept(lastClassInfo))
      {
         return true;
      }
      ClassInfo superClass = lastClassInfo.getSuperClass();
      if (superClass != null)
      {
         if (acceptedByTraceDef(superClass))
         {
            return true;
         }
      }
      return false;

   }


   /**
    * @see gov.nasa.jpf.ListenerAdapter#searchStarted(gov.nasa.jpf.search.Search)
    */
   public void searchStarted(Search search)
   {
      this.stateGraph = new StateNode(search.getStateId(), null);
      this.currentStateNode = this.stateGraph;
      if (this.timeStamps)
         this.currentStateNode.time = new Date().getTime();
   }


   /**
    * Writes the trace if there were no errors
    * 
    * @see gov.nasa.jpf.ListenerAdapter#searchFinished(gov.nasa.jpf.search.Search)
    */
   public void searchFinished(Search search)
   {
      if (this.filename.equals(""))
         return;
      this.writeTrace();
   }


   /**
    * @see gov.nasa.jpf.ListenerAdapter#stateAdvanced(gov.nasa.jpf.search.Search)
    */
   public void stateAdvanced(Search search)
   {
      if (search.isEndState())
      {
         this.currentStateNode.isend = true;
         this.numEndStates++;
      }
   }


   /**
    * @see gov.nasa.jpf.ListenerAdapter#choiceGeneratorSet(gov.nasa.jpf.jvm.JVM)
    */
   public void choiceGeneratorSet(JVM vm)
   {
      this.currentStateNode = new StateNode(vm.getStateId(),
            this.currentStateNode);
      if (this.timeStamps)
         this.currentStateNode.time = new Date().getTime();
   }


   /**
    * @see gov.nasa.jpf.ListenerAdapter#choiceGeneratorAdvanced(gov.nasa.jpf.jvm.JVM)
    */
   public void choiceGeneratorAdvanced(JVM vm)
   {
      if (this.currentStateNode.parent.isnew)
         this.currentStateNode.parent.isnew = false;
      else
         this.currentStateNode = new StateNode(vm.getStateId(),
               this.currentStateNode.parent);
   }


   /**
    * @see gov.nasa.jpf.ListenerAdapter#choiceGeneratorProcessed(gov.nasa.jpf.jvm.JVM)
    */
   public void choiceGeneratorProcessed(JVM vm)
   {
      if (this.currentStateNode.parent != null)
      {
         this.currentStateNode = this.currentStateNode.parent;
      }
   }


   /**
    * Writes the trace xml file
    */
   private void writeTrace()
   {
      TGCreator creator = new TGCreator();
      TGTrace trace = creator.createTrace(JVM.getVM(), this.stateGraph);
      TraceGraphWriter tgWriter = new TraceGraphWriter(trace);

      if (this.traceCompressed)
      {
         this.filename += ITraceConstants.ZIP_FILE_SUFFIX;
         tgWriter.saveCompressed(new File(this.filename));
      }
      else
      {
         this.filename += ITraceConstants.XML_FILE_SUFFIX;
         tgWriter.save(new File(this.filename));
      }

      SymbolicExecutionTracerPlugin.log(Status.INFO,
            "Symbolic Execution Tracer wrote " + this.numEndStates
                  + " traces to " + this.filename);
   }


   public void setTraceDefinitionFilter(TraceDefinitionFilter traceDefFilter)
   {
      this.traceDefFilter = traceDefFilter;
   }


   /**
    * Save the trace to the given file
    * 
    * @param fileName
    */
   public void setTraceFile(String fileName)
   {
      this.filename = fileName;
   }


   /**
    * Compress the trace file
    * 
    * @param compressed
    */
   public void setTraceCompressed(boolean compressed)
   {
      this.traceCompressed = compressed;
   }


   /**
    * Append timestamps to MethodEntry/TracePath
    * 
    * @param timeStamps
    */
   public void setTimeStamps(boolean timeStamps)
   {
      this.timeStamps = timeStamps;
   }


   /**
    * Save values/ids from arguments. Needed for patterns with assignments. For primitive types the
    * value is saved, for objects their id.
    * 
    * @param argumentValues
    */
   public void setArgumentValues(boolean argumentValues)
   {
      this.argumentValues = argumentValues;
   }

}
