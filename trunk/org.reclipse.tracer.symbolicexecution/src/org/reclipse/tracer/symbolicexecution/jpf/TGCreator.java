package org.reclipse.tracer.symbolicexecution.jpf;


import java.util.Date;
import java.util.LinkedList;

import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.JVM;

import org.reclipse.tracer.model.tracegraph.TGArgument;
import org.reclipse.tracer.model.tracegraph.TGFactory;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGObjectArgument;
import org.reclipse.tracer.model.tracegraph.TGPrimitiveArgument;
import org.reclipse.tracer.model.tracegraph.TGTracePath;
import org.reclipse.tracer.model.tracegraph.TGTrace;
import org.reclipse.tracer.model.tracegraph.TGType;


public class TGCreator
{
   private TGFactory factory = null;

   private int tracePathId = 1;

   private int methodId = 1;


   public TGTrace createTrace(JVM vm, StateNode stateNodeTree)
   {
      TGTrace trace = new TGTrace();
      trace.setSymbolicExecution(true);
      trace.setDate(new Date().toString());
      trace.setMainClass(vm.getMainClassName());
      this.visitStateNode(stateNodeTree, trace);
      return trace;
   }


   private void visitStateNode(StateNode node, TGTrace trace)
   {
      for (StateNode child : node.children)
         this.visitStateNode(child, trace);
      if (node.isend)
      {
         // Reset factory. The factory caches specific objects. If
         // we don't reset we change objects from other tracepaths
         this.factory = new TGFactory();
         TGTracePath tracePath = this.createTracePath(node);
         trace.addToTracePaths(tracePath);
      }

   }


   public TGTracePath createTracePath(StateNode node)
   {
      TGTracePath tracePath = new TGTracePath();
      tracePath.setName("path" + Integer.toString(this.tracePathId++));
      tracePath.setEndTime(node.time); // we are in an end-node

      LinkedList<MethodEntry> entries = new LinkedList<MethodEntry>();
      LinkedList<ClassInfo> classes = new LinkedList<ClassInfo>();
      StateNode current = node;
      while (current.parent != null)
      {
         entries.addAll(0, current.methods);
         classes.addAll(0, current.classes);
         current = current.parent;
      }
      tracePath.setStartTime(current.time); // current is now the root

      while (!classes.isEmpty())
      {
         // Register classes
         TGType classLoaded = this.createType(classes.pop());
         tracePath.addToLoadedClasses(classLoaded);
      }

      while (!entries.isEmpty())
      {
         MethodEntry me = entries.pop();
         tracePath.addToMethodCalls(this.createMethodCall(me));
      }

      return tracePath;
   }


   public TGMethodCall createMethodCall(MethodEntry me)
   {
      TGMethodCall methodCall = new TGMethodCall();
      methodCall.setName(me.calleeMethod.getName());
      methodCall.setId(Integer.toString(this.methodId++));
      methodCall.setTime(me.time);

      methodCall.setCallee(this.createObject(me.calleeClass, me.callee));
      methodCall.setCaller(this.createObject(me.callerClass, me.caller));

      String[] argumentTypes = me.calleeMethod.getArgumentTypeNames();
      for (int i = 0; i < argumentTypes.length; i++)
      {
         ClassInfo argumentType = ClassInfo
               .getResolvedClassInfo(argumentTypes[i]);
         TGArgument argument = null;

         if (argumentType.isPrimitive())
         {
            argument = new TGPrimitiveArgument();
            if (me.arguments[i] != null)
               ((TGPrimitiveArgument) argument).setValue(me.arguments[i]
                     .toString());
         }
         else
         {
            argument = new TGObjectArgument();
            if (me.arguments[i] != null)
               ((TGObjectArgument) argument).setObject(this.createObject(
                     argumentType, (Integer) me.arguments[i]));
         }

         if (argument != null)
         {
            argument.setType(this.createType(argumentType));
            methodCall.addToArguments(argument);
         }
      }

      return methodCall;
   }


   public TGObject createObject(ClassInfo ci, int id)
   {
      String objectId = Integer.toString(id);
      if (id == -1)
         objectId = "static";
      TGObject object = factory.getTGObject(objectId);
      if (object == null)
         object = factory.createTGObject(objectId);
      object.setType(this.createType(ci));
      return object;
   }


   public TGType createType(ClassInfo ci)
   {
      String name = ci.getName();
      String[] tempArr = name.split("\\.");
      if (tempArr.length > 0)
      {
         name = tempArr[tempArr.length - 1];
      }
      TGType type = factory.getTGType(name);
      if (type == null)
      {
         // Add superclass
         type = factory.createTGType(name);
         ClassInfo superClass = ci.getSuperClass();
         if (!ci.isPrimitive() && !superClass.isSystemClass())
         {
            type.addToSuperTypes(this.createType(superClass));
         }

         // Add interface
         for (ClassInfo interf : ci.getAllInterfaceClassInfos())
            type.addToSuperTypes(this.createType(interf));
      }
      return type;
   }

}
