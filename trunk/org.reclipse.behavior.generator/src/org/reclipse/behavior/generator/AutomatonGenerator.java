package org.reclipse.behavior.generator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.NFA;
import org.reclipse.behavior.inference.automaton.Transition;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallSetObject;
import org.reclipse.behavior.inference.automaton.symbols.PermittedMethodCallSymbol;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedCallerSymbol;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedMethodCallSymbol;
import org.reclipse.behavior.inference.automaton.symbols.SymbolFactory;
import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.structure.specification.PSObject;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;
import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;


/**
 * @author lowende
 * @author $LastChangedBy: mcp $
 * @version $Revision: 4344 $ $Date: 2010-05-17 11:35:32 +0200 (Mo, 17 Mai 2010) $
 */
public class AutomatonGenerator
{

   private final BehavioralPattern diagram;


   public AutomatonGenerator(BehavioralPattern diagram)
   {
      this.diagram = diagram;
   }


   public DFA generateAutomaton()
   {
      SymbolFactory symbolFactory = new SymbolFactory();

      // create automaton
      NFAGenerator nfaGenerator = new NFAGenerator(symbolFactory);
      NFA nfa = (NFA) nfaGenerator.visit(this.diagram, (Object[]) null);
      DFA dfa = nfa.convertToDFA();

      // create rejecting state
      DFAState rejectingState = new DFAState();
      rejectingState.setType(AbstractState.REJECT);
      rejectingState.setName("R");
      dfa.addToStates(rejectingState);
      dfa.setRejectingState(rejectingState);

      addProhibitedMethodCallTransitions(dfa, rejectingState, symbolFactory);
      addProhibitedCallerTransitions(dfa, rejectingState, symbolFactory);

      return dfa;
   }


   private void addProhibitedMethodCallTransitions(DFA dfa, DFAState rejectingState, SymbolFactory symbolFactory)
   {
      Set<MethodCall> consideredMethods = collectConsideredMethodCalls(symbolFactory);

      // iterate through all states except start state, accepting states and
      // rejecting state
      // and add transitions to the rejecting state
      Iterator statesIter = dfa.iteratorOfStates();
      while (statesIter.hasNext())
      {
         DFAState state = (DFAState) statesIter.next();

         if (state != dfa.getStartState() && state.getType() != AbstractState.ACCEPT
               && state.getType() != AbstractState.REJECT)
         {
            // add a rejecting transition for each considered method
            // for which no transition with PermittedMethodCallSymbol exist

            // iterate through all considered methods
            Iterator<MethodCall> methodsIter = consideredMethods.iterator();
            while (methodsIter.hasNext())
            {
               MethodCall consideredMethod = methodsIter.next();
               boolean transitionFound = false;

               // iterate through all transitions with
               // PermittedMethodCallSymbol
               Iterator transitionsIter = state.iteratorOfOutgoingTransitions();
               while (transitionsIter.hasNext() && !transitionFound)
               {
                  Transition transition = (Transition) transitionsIter.next();
                  if (transition.getSymbol() instanceof PermittedMethodCallSymbol)
                  {
                     PermittedMethodCallSymbol symbol = (PermittedMethodCallSymbol) transition.getSymbol();
                     MethodCallObject callee = symbol.getCallee();
                     if (consideredMethod.object.toString().equals(callee.toString())
                           && ((consideredMethod.methodName).equals(symbol.getMethodName())))
                     {
                        transitionFound = true;
                     }
                     transitionFound = (transitionFound || checkForAssignment(consideredMethod, symbol));
                  }
               }

               // if no transition was found, add a transition with a
               // ProhibitedMethodCallSymbol
               if (!transitionFound)
               {
                  ProhibitedMethodCallSymbol symbol = symbolFactory.provideProhibitedMethodCallSymbol(
                        consideredMethod.object, consideredMethod.methodName);

                  if (symbol != null)
                  {
                     dfa.addToSymbols(symbol);
                     Transition transition = new Transition();
                     transition.setSymbol(symbol);
                     transition.setPreviousState(state);
                     transition.setNextState(rejectingState);

                  }

               }
            }
         }
      }
   }


   private boolean checkForAssignment(MethodCall consideredMethod, PermittedMethodCallSymbol symbol)
   {
      if ((symbol.getCaller() instanceof MethodCallSetObject) || (symbol.getCallee() instanceof MethodCallSetObject))
      {
         if ((consideredMethod.methodName).contains(" = "))
         {
            // do not create transition for assignments
            return false;
         }
      }
      return true;
   }


   private void addProhibitedCallerTransitions(DFA dfa, DFAState rejectingState, SymbolFactory symbolFactory)
   {
      // iterate through all states except start state, accepting states and rejecting state
      // and add transitions to rejecting state
      Iterator statesIter = dfa.iteratorOfStates();
      while (statesIter.hasNext())
      {
         DFAState state = (DFAState) statesIter.next();

         if (state != dfa.getStartState() && state.getType() != AbstractState.ACCEPT
               && state.getType() != AbstractState.REJECT)
         {
            Map<MethodCall, List<MethodCallObject>> methodCallsMap = new HashMap<MethodCall, List<MethodCallObject>>();

            // add a rejecting transition for the complementary callers
            // of every PermittedMethodCall transition
            Iterator transitionsIter = state.iteratorOfOutgoingTransitions();
            while (transitionsIter.hasNext())
            {
               Transition transition = (Transition) transitionsIter.next();
               if (transition.getSymbol() instanceof PermittedMethodCallSymbol)
               {
                  PermittedMethodCallSymbol symbol = (PermittedMethodCallSymbol) transition.getSymbol();
                  MethodCall methodCall = provideMethodCall(symbol.getCallee(), symbol.getMethodName());

                  List<MethodCallObject> callerList = methodCallsMap.get(methodCall);
                  if (callerList == null)
                  {
                     callerList = new ArrayList<MethodCallObject>();
                     methodCallsMap.put(methodCall, callerList);
                  }

                  callerList.add(symbol.getCaller());
               }
            }

            // add transitions with ProhibitedMethodCallSymbols to rejecting state
            Iterator<MethodCall> iter = methodCallsMap.keySet().iterator();
            while (iter.hasNext())
            {
               MethodCall methodCall = iter.next();
               List<MethodCallObject> callerList = methodCallsMap.get(methodCall);
               ProhibitedCallerSymbol prohibitedCallerSymbol = symbolFactory.provideProhibitedCallerSymbol(callerList,
                     methodCall.object, methodCall.methodName);
               dfa.addToSymbols(prohibitedCallerSymbol);
               Transition transition = new Transition();
               transition.setSymbol(prohibitedCallerSymbol);
               transition.setPreviousState(state);
               transition.setNextState(rejectingState);
            }
         }
      }
   }


   /**
    * Collect all methods used in the diagram
    */
   private Set<MethodCall> collectConsideredMethodCalls(SymbolFactory symbolFactory)
   {
      HashMap<String, MethodCall> consideredMethodCalls = new HashMap<String, MethodCall>();

      Iterator<AbstractMessage> messagesIter = this.diagram.getMessages().iterator();
      while (messagesIter.hasNext())
      {
         AbstractMessage abstractMessage = messagesIter.next();
         if (abstractMessage instanceof BPMessage)
         {
            BPMessage bpMessage = (BPMessage) abstractMessage;
            AbstractSequenceDiagramObject bpObject = bpMessage.getReceiveEvent().getLifeline().getObject();
            String objectName = bpObject.getName();
            String typeName = "";
            if (bpObject instanceof BPObject)
            {
               typeName = getNameOfTypeReference(((BPObject) bpObject).getTypeReference());
            }
            else if (bpObject instanceof BPSetObject)
            {
               typeName = getNameOfTypeReference(((BPSetObject) bpObject).getTypeReference());
            }
            String methodName = bpMessage.getName();
            String signature = methodName + "(" + getArgumentsString(bpMessage) + ")";
            // String signature = objectName + ":" + typeName + "." + methodName;

            MethodCall consideredMethodCall = consideredMethodCalls.get(signature);
            if (consideredMethodCall == null)
            {
               MethodCallObject callee = symbolFactory.provideMethodCallObject(objectName, typeName);
               consideredMethodCall = provideMethodCall(callee, signature);
               consideredMethodCalls.put(signature, consideredMethodCall);
            }
         }
      }

      return new HashSet(consideredMethodCalls.values());
   }


   private String getArgumentsString(BPMessage bpMessage)
   {
      EList<BPArgument> arguments = bpMessage.getArguments();
      if (arguments.size() == 0)
      {
         return "";
      }
      StringBuffer sb = new StringBuffer();
      Iterator<BPArgument> iter = arguments.iterator();
      while (iter.hasNext())
      {
         BPArgument argument = iter.next();
         sb.append(argument.getName());
         sb.append(":");
         sb.append(argument.getType().getName());
         if (iter.hasNext())
         {
            sb.append(",");
         }
      }
      return sb.toString();
   }


   private String getNameOfTypeReference(PSObject psObject)
   {
      EcoreUtil.resolveAll(psObject);
      return psObject.getName();
   }


   private Map<String, MethodCall> methodCalls = new HashMap<String, MethodCall>();


   private MethodCall provideMethodCall(MethodCallObject object, String methodName)
   {
      String key = object + "." + methodName;
      MethodCall methodCall = this.methodCalls.get(key);
      if (methodCall == null)
      {
         methodCall = new MethodCall(object, methodName);
         this.methodCalls.put(key, methodCall);
      }

      return methodCall;
   }


   private static class MethodCall
   {

      final MethodCallObject object;

      final String methodName;


      public MethodCall(MethodCallObject object, String methodName)
      {
         this.object = object;
         this.methodName = methodName;
      }

   }

}
