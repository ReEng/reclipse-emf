package org.reclipse.behavior.generator;


import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.AbstractSymbol;
import org.reclipse.behavior.inference.automaton.Assignment;
import org.reclipse.behavior.inference.automaton.NFA;
import org.reclipse.behavior.inference.automaton.NFAState;
import org.reclipse.behavior.inference.automaton.Transition;
import org.reclipse.behavior.inference.automaton.symbols.Epsilon;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.SymbolFactory;
import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BPEachFragment;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BPObject;

import de.uni_paderborn.basicSequenceDiagram.AlternativeFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.LoopFragment;
import de.uni_paderborn.basicSequenceDiagram.OptionalFragment;
import de.uni_paderborn.basicSequenceDiagram.ReceiveEvent;
import de.uni_paderborn.basicSequenceDiagram.RootFragment;
import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4534 $ $Date: 2010-09-17 12:48:20 +0200 (Fr, 17 Sep 2010) $
 */
public class NFAGenerator extends Visitor
{

   private SymbolFactory symbolFactory;

   private int stateCounter;

   private int assignmentCounter;

   public static int SENDER = 0;

   public static int RECEIVER = 1;


   public NFAGenerator(SymbolFactory symbolFactory)
   {
      this.symbolFactory = symbolFactory;
      this.stateCounter = 0;
      this.assignmentCounter = 0;
   }


   public Object visitBehavioralPattern(BehavioralPattern diagram, Object... arguments)
   {
      // System.out.println("visiting BehavioralPattern: " + diagram);

      NFA nfa = new NFA();

      NFAState startState = new NFAState();
      startState.setName(Integer.toString(this.stateCounter++));

      nfa.addToStates(startState);
      nfa.setStartState(startState);

      NFAState endState = (NFAState) visit(diagram.getRootFragment(), nfa, startState);
      endState.setType(AbstractState.ACCEPT);

      return nfa;
   }


   /**
    * @param arguments argument[0]:NFA, argument[1]:lastState
    */
   public Object visitRootFragment(RootFragment rootFragment, Object... arguments)
   {
      // System.out
      // .println("visiting UMLRootFragment: " + rootFragment.toString());

      NFA nfa = (NFA) arguments[0];
      NFAState lastState = (NFAState) arguments[1];

      List<Fragment> fragments = rootFragment.getOperands().get(0).getFragments();
      for (Fragment fragment : fragments)
      {
         if (fragment instanceof SendEvent)
         {
            lastState = (NFAState) visit(((SendEvent) fragment).getMessage(), nfa, lastState);
         }
         else if (fragment instanceof ReceiveEvent)
         {
            // do nothing
         }
         else
         {
            lastState = (NFAState) visit(fragment, nfa, lastState);
         }
      }

      return lastState;
   }


   public Object visitBPAssignment(BPAssignment bpAssignment, Object... arguments)
   {
      Assignment assignment = new Assignment();
      assignment.setLeftSide(bpAssignment.getLeftSide().getName());
      assignment.setRightSide(bpAssignment.getRightSide().getName());
      assignment.setId(this.assignmentCounter++);

      NFA nfa = (NFA) arguments[0];
      NFAState startState = (NFAState) arguments[1];

      nfa.addToAssignments(assignment);

      // Assignment der Transition zuweisen
      Iterator transIter = startState.iteratorOfIncomingTransitions();
      while (transIter.hasNext())
      {
         Transition transition = (Transition) transIter.next();
         assignAssignmentToTransition(assignment, nfa, transition);
      }

      return startState;
   }


   private void assignAssignmentToTransition(Assignment assignment, NFA nfa, Transition transition)
   {
      if (transition.getSymbol() instanceof Epsilon)
      {
         Iterator stateIter = nfa.iteratorOfStates();
         NFAState state;
         while (stateIter.hasNext())
         {
            state = (NFAState) stateIter.next();
            if (state.equals(transition.getPreviousState()))
            {
               Iterator transIter2 = state.iteratorOfIncomingTransitions();
               while (transIter2.hasNext())
               {
                  transition = (Transition) transIter2.next();
                  transition.getSymbol().setAssignment(assignment);
               }
            }
         }
      }
      else
      {
         transition.getSymbol().setAssignment(assignment);
      }
   }


   /**
    * @param arguments argument[0]:NFA, argument[1]:lastState
    */
   public Object visitBPMessage(BPMessage message, Object... arguments)
   {
      // System.out.println("visiting BPMessage: " + message.getName());

      NFA nfa = (NFA) arguments[0];
      NFAState startState = (NFAState) arguments[1];

      NFAState endState = new NFAState();
      endState.setName(Integer.toString(this.stateCounter++));
      nfa.addToStates(endState);

      Transition transition = new Transition();
      transition.setPreviousState(startState);
      transition.setNextState(endState);

      // caller
      AbstractSequenceDiagramObject caller = message.getSendEvent().getLifeline().getObject();
      String type = "";
      boolean isSet = false;
      if (caller instanceof BPSetObject)
      {
         isSet = true;
         type = ((BPSetObject) caller).getTypeReference().getName();
      }
      else if (caller instanceof BPObject)
      {
         type = ((BPObject) caller).getTypeReference().getName();
      }
      MethodCallObject mcoCaller = this.symbolFactory.provideMethodCallObject(caller.getName(), type, isSet);

      // callee
      AbstractSequenceDiagramObject callee = message.getReceiveEvent().getLifeline().getObject();
      type = "";
      isSet = false;
      if (callee instanceof BPSetObject)
      {
         isSet = true;
         type = ((BPSetObject) callee).getTypeReference().getName();
      }
      else if (callee instanceof BPObject)
      {
         type = ((BPObject) callee).getTypeReference().getName();
      }
      MethodCallObject mcoCallee = this.symbolFactory.provideMethodCallObject(callee.getName(), type, isSet, false,
            (!message.isSelfCall() && (caller == callee)));

      // symbol
      AbstractSymbol symbol = this.symbolFactory.providePermittedMethodCallSymbol(mcoCaller, mcoCallee,
            getMethodSignature(message));
      transition.setSymbol(symbol);
      nfa.addToSymbols(symbol);

      return endState;
   }


   private String getMethodSignature(BPMessage message)
   {
      return message.getName() + "(" + getArgumentsString(message) + ")";
   }


   /**
    * @param arguments argument[0]:NFA, argument[1]:lastState
    */
   public Object visitBPMessageForEach(BPMessage message, Object[] arguments)
   {
      // System.out.println("visiting BPMessage: " + message.getName());

      NFA nfa = (NFA) arguments[0];
      NFAState startState = (NFAState) arguments[1];
      int foreach = (Integer) arguments[2];

      NFAState endState = new NFAState();
      endState.setName(Integer.toString(this.stateCounter++));
      nfa.addToStates(endState);

      Transition transition = new Transition();
      transition.setPreviousState(startState);
      transition.setNextState(endState);

      AbstractSequenceDiagramObject caller = message.getSendEvent().getLifeline().getObject();
      AbstractSequenceDiagramObject callee = message.getReceiveEvent().getLifeline().getObject();

      MethodCallObject mcoCaller;
      MethodCallObject mcoCallee;

      String type = "";
      boolean isSet = false;
      if (caller instanceof BPSetObject)
      {
         isSet = true;
         type = ((BPSetObject) caller).getTypeReference().getName();
      }
      else if (caller instanceof BPObject)
      {
         type = ((BPObject) caller).getTypeReference().getName();
      }

      mcoCaller = this.symbolFactory.provideMethodCallObject(caller.getName(), type, isSet, foreach == SENDER);

      isSet = false;
      if (callee instanceof BPSetObject)
      {
         isSet = true;
         type = ((BPSetObject) callee).getTypeReference().getName();
      }
      else if (callee instanceof BPObject)
      {
         type = ((BPObject) callee).getTypeReference().getName();
      }

      mcoCallee = this.symbolFactory.provideMethodCallObject(callee.getName(), type, isSet, foreach == RECEIVER);

      AbstractSymbol symbol = this.symbolFactory.providePermittedMethodCallSymbol(mcoCaller, mcoCallee,
            getMethodSignature(message));
      transition.setSymbol(symbol);
      nfa.addToSymbols(symbol);

      return endState;
   }


   /**
    * @param arguments argument[0]:NFA, argument[1]:NFAStatesPair
    */
   public Object visitAlternativeFragment(AlternativeFragment alternativeFragment, Object... arguments)
   {
      // System.out.println("visiting UMLAlternativeFragment: " +
      // fragment.getType());

      NFA nfa = (NFA) arguments[0];
      NFAState startState = (NFAState) arguments[1];

      NFAState alternativeEndState = new NFAState();
      alternativeEndState.setName(Integer.toString(this.stateCounter++));
      nfa.addToStates(alternativeEndState);

      Iterator<InteractionOperand> operandsIter = alternativeFragment.getOperands().iterator();
      while (operandsIter.hasNext())
      {
         InteractionOperand operand = operandsIter.next();

         NFAState lastState = startState;

         List<Fragment> fragments = operand.getFragments();
         for (Fragment fragment : fragments)
         {
            if (fragment instanceof SendEvent)
            {
               lastState = (NFAState) visit(((SendEvent) fragment).getMessage(), nfa, lastState);
            }
            else if (fragment instanceof ReceiveEvent)
            {
               // do nothing
            }
            else
            {
               lastState = (NFAState) visit(fragment, nfa, lastState);
            }
         }

         // create epsilon transition from the last state of the operand to
         // the overall alternative end state
         Transition transition = new Transition();
         transition.setPreviousState(lastState);
         transition.setNextState(alternativeEndState);
         transition.setSymbol(SymbolFactory.EPSILON);
      }

      return alternativeEndState;
   }


   /**
    * @param arguments argument[0]:NFA, argument[1]:NFAStatesPair
    */
   public Object visitLoopFragment(LoopFragment loopFragment, Object... arguments)
   {
      // System.out.println("visiting UMLLoopFragment: " +
      // fragment.getType());

      NFA nfa = (NFA) arguments[0];
      NFAState startState = (NFAState) arguments[1];
      NFAState lastState = startState;

      List<Fragment> fragments = loopFragment.getOperands().get(0).getFragments();
      for (Fragment fragment : fragments)
      {
         if (fragment instanceof SendEvent)
         {
            lastState = (NFAState) visit(((SendEvent) fragment).getMessage(), nfa, lastState);
         }
         else if (fragment instanceof ReceiveEvent)
         {
            // do nothing
         }
         else
         {
            lastState = (NFAState) visit(fragment, nfa, lastState);
         }
      }

      // create epsilon transition from last state to start state
      Transition transition = new Transition();
      transition.setPreviousState(lastState);
      transition.setNextState(startState);
      transition.setSymbol(SymbolFactory.EPSILON);

      // create another end state for the loop fragment
      // so that only the loop may jump back to the first state of the loop
      NFAState endState = new NFAState();
      endState.setName(Integer.toString(this.stateCounter++));
      nfa.addToStates(endState);

      // create epsilon transition from last state to end state
      transition = new Transition();
      transition.setPreviousState(lastState);
      transition.setNextState(endState);
      transition.setSymbol(SymbolFactory.EPSILON);

      return endState;
   }


   /**
    * @param arguments argument[0]:NFA, argument[1]:NFAStatesPair
    */
   public Object visitBPEachFragment(BPEachFragment eachFragment, Object... arguments)
   {
      // System.out.println("visiting BPEachFragment: " + fragment.getType());

      NFA nfa = (NFA) arguments[0];
      NFAState startState = (NFAState) arguments[1];
      NFAState lastState = startState;

      List<Fragment> fragments = eachFragment.getOperands().get(0).getFragments();
      for (Fragment fragment : fragments)
      {
         if (fragment instanceof SendEvent)
         {
            int foreach = RECEIVER;
            BPMessage message = (BPMessage) ((SendEvent) fragment).getMessage();
            if (message.getSendEvent().getLifeline().getObject() instanceof BPSetObject)
            {
               foreach = SENDER;
            }

            Object[] args = { nfa, lastState, foreach };
            lastState = (NFAState) visitBPMessageForEach(message, args);
         }
      }

      return lastState;
   }


   /**
    * @param arguments argument[0]:NFA, argument[1]:NFAStatesPair
    */
   public Object visitOptionalFragment(OptionalFragment optionalFragment, Object... arguments)
   {
      // System.out.println("visiting UMLOptionalFragment: " +
      // fragment.getType());

      NFA nfa = (NFA) arguments[0];
      NFAState startState = (NFAState) arguments[1];
      NFAState lastState = startState;

      List<Fragment> fragments = optionalFragment.getOperands().get(0).getFragments();
      for (Fragment fragment : fragments)
      {
         if (fragment instanceof SendEvent)
         {
            lastState = (NFAState) visit(((SendEvent) fragment).getMessage(), nfa, lastState);
         }
         else if (fragment instanceof ReceiveEvent)
         {
            // do nothing
         }
         else
         {
            lastState = (NFAState) visit(fragment, nfa, lastState);
         }
      }

      // create epsilon transition from start state to last state
      Transition transition = new Transition();
      transition.setPreviousState(startState);
      transition.setNextState(lastState);
      transition.setSymbol(SymbolFactory.EPSILON);

      return lastState;
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

}
