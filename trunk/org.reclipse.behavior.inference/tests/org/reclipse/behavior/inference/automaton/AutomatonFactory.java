package org.reclipse.behavior.inference.automaton;


import java.util.ArrayList;
import java.util.List;

import org.reclipse.behavior.inference.automaton.AbstractAutomaton;
import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.AbstractSymbol;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.NFA;
import org.reclipse.behavior.inference.automaton.NFAState;
import org.reclipse.behavior.inference.automaton.Transition;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.SymbolFactory;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4126 $ $Date: 2009-08-06 15:19:27 +0200 (Do, 06 Aug 2009) $
 */
public class AutomatonFactory
{
   public static final String TEST_NFA = "Create Test NFA";

   public static final String TEST_DFA = "Create Test DFA";

   public static final String STRATEGY_NFA = "Create NFA for Strategy";

   public static final String STATE_NFA = "Create NFA for State";

   public static final String ECLIPSE_STRATEGY_NFA = "Create NFA for Eclipse Strategy";


   private static AutomatonFactory singleton;


   private AutomatonFactory()
   {
   }


   public static AutomatonFactory get()
   {
      if (singleton == null)
      {
         singleton = new AutomatonFactory();
      }

      return singleton;
   }


   public AbstractAutomaton createAutomaton(String name)
   {
      if (TEST_NFA.equals(name))
      {
         return createTestNFA();
      }
      else if (TEST_DFA.equals(name))
      {
         return createTestDFA();
      }
      else if (STRATEGY_NFA.equals(name))
      {
         return createStrategyNFA();
      }
      else if (STATE_NFA.equals(name))
      {
         return createStateNFA();
      }
      else if (ECLIPSE_STRATEGY_NFA.equals(name))
      {
         return createEclipseStrategyNFA();
      }

      return new DFA();
   }


   private AbstractAutomaton createTestNFA()
   {
      // construct NFA that accepts (a|b)*abb
      NFA nfa = new NFA();

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject caller = factory.provideMethodCallObject("caller",
            "Caller");
      MethodCallObject callee = factory.provideMethodCallObject("caller",
            "Caller");

      AbstractSymbol symbolA = factory.providePermittedMethodCallSymbol(caller,
            callee, "a()");
      nfa.addToSymbols(symbolA);

      AbstractSymbol symbolB = factory.providePermittedMethodCallSymbol(caller,
            callee, "b()");
      nfa.addToSymbols(symbolB);

      NFAState state0 = createNFAState(nfa, "0");
      nfa.setStartState(state0);

      NFAState state1 = createNFAState(nfa, "1");
      createTransition(state0, state1, SymbolFactory.EPSILON);

      NFAState state2 = createNFAState(nfa, "2");
      createTransition(state1, state2, SymbolFactory.EPSILON);

      NFAState state3 = createNFAState(nfa, "3");
      createTransition(state2, state3, symbolA);

      NFAState state4 = createNFAState(nfa, "4");
      createTransition(state1, state4, SymbolFactory.EPSILON);

      NFAState state5 = createNFAState(nfa, "5");
      createTransition(state4, state5, symbolB);

      NFAState state6 = createNFAState(nfa, "6");
      createTransition(state3, state6, SymbolFactory.EPSILON);
      createTransition(state5, state6, SymbolFactory.EPSILON);
      createTransition(state6, state1, SymbolFactory.EPSILON);

      NFAState state7 = createNFAState(nfa, "7");
      createTransition(state6, state7, SymbolFactory.EPSILON);
      createTransition(state0, state7, SymbolFactory.EPSILON);

      NFAState state8 = createNFAState(nfa, "8");
      createTransition(state7, state8, symbolA);

      NFAState state9 = createNFAState(nfa, "9");
      createTransition(state8, state9, symbolB);

      NFAState state10 = createNFAState(nfa, "10");
      state10.setType(AbstractState.ACCEPT);
      createTransition(state9, state10, symbolB);

      return nfa;
   }


   private AbstractAutomaton createTestDFA()
   {
      DFA dfa = new DFA();

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject caller = factory.provideMethodCallObject("caller",
            "Caller");
      MethodCallObject callee = factory.provideMethodCallObject("caller",
            "Caller");

      AbstractSymbol symbolA = factory.providePermittedMethodCallSymbol(caller,
            callee, "a()");
      dfa.addToSymbols(symbolA);

      AbstractSymbol symbolB = factory.providePermittedMethodCallSymbol(caller,
            callee, "b()");
      dfa.addToSymbols(symbolB);

      DFAState stateA = createDFAState(dfa, "A");
      dfa.setStartState(stateA);

      DFAState stateB = createDFAState(dfa, "B");
      createTransition(stateA, stateB, symbolA);
      createTransition(stateB, stateB, symbolA);

      DFAState stateC = createDFAState(dfa, "C");
      createTransition(stateA, stateC, symbolB);
      createTransition(stateC, stateC, symbolB);
      createTransition(stateC, stateB, symbolA);

      DFAState stateD = createDFAState(dfa, "D");
      createTransition(stateB, stateD, symbolB);
      createTransition(stateD, stateB, symbolA);

      DFAState stateE = createDFAState(dfa, "E");
      stateE.setType(AbstractState.ACCEPT);
      createTransition(stateD, stateE, symbolB);
      createTransition(stateE, stateB, symbolA);
      createTransition(stateE, stateC, symbolB);

      return dfa;
   }


   private AbstractAutomaton createStrategyNFA()
   {
      NFA nfa = new NFA();

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject client = factory.provideMethodCallObject("cl", "client");
      MethodCallObject context = factory.provideMethodCallObject("co",
            "context");
      MethodCallObject abstractStrategyA = factory.provideMethodCallObject("a",
            "abstractStrategy");
      MethodCallObject abstractStrategyB = factory.provideMethodCallObject("b",
            "abstractStrategy");

      // create all symbols
      // permitted method calls
      AbstractSymbol cl_co_setStrategy = factory
            .providePermittedMethodCallSymbol(client, context, "setStrategy");
      nfa.addToSymbols(cl_co_setStrategy);

      AbstractSymbol cl_co_request = factory.providePermittedMethodCallSymbol(
            client, context, "request");
      nfa.addToSymbols(cl_co_request);

      AbstractSymbol co_a_algorithm = factory.providePermittedMethodCallSymbol(
            context, abstractStrategyA, "algorithm");
      nfa.addToSymbols(co_a_algorithm);

      AbstractSymbol co_b_algorithm = factory.providePermittedMethodCallSymbol(
            context, abstractStrategyB, "algorithm");
      nfa.addToSymbols(co_b_algorithm);

      // prohibited method calls
      AbstractSymbol pmc_co_setStrategy = factory
            .provideProhibitedMethodCallSymbol(context, "setStrategy");
      nfa.addToSymbols(pmc_co_setStrategy);

      AbstractSymbol pmc_co_request = factory
            .provideProhibitedMethodCallSymbol(context, "request");
      nfa.addToSymbols(pmc_co_request);

      AbstractSymbol pmc_a_algorithm = factory
            .provideProhibitedMethodCallSymbol(abstractStrategyA, "algorithm");
      nfa.addToSymbols(pmc_a_algorithm);

      AbstractSymbol pmc_b_algorithm = factory
            .provideProhibitedMethodCallSymbol(abstractStrategyB, "algorithm");
      nfa.addToSymbols(pmc_b_algorithm);

      // prohibited callers
      List<MethodCallObject> callers = new ArrayList<MethodCallObject>();
      callers.add(client);
      AbstractSymbol pc_cl_co_request = factory.provideProhibitedCallerSymbol(
            callers, context, "request");
      nfa.addToSymbols(pc_cl_co_request);

      callers = new ArrayList<MethodCallObject>();
      callers.add(context);
      AbstractSymbol pc_co_a_algorithm = factory.provideProhibitedCallerSymbol(
            callers, abstractStrategyA, "algorithm");
      nfa.addToSymbols(pc_co_a_algorithm);

      AbstractSymbol pc_co_b_algorithm = factory.provideProhibitedCallerSymbol(
            callers, abstractStrategyB, "algorithm");
      nfa.addToSymbols(pc_co_b_algorithm);

      // create states
      NFAState state0 = createNFAState(nfa, "0");
      nfa.setStartState(state0);

      NFAState state1 = createNFAState(nfa, "1");
      createTransition(state0, state1, cl_co_setStrategy);

      NFAState state2 = createNFAState(nfa, "2");
      createTransition(state1, state2, cl_co_request);

      NFAState state3 = createNFAState(nfa, "3");
      createTransition(state2, state3, co_a_algorithm);
      createTransition(state3, state1, SymbolFactory.EPSILON);

      NFAState state4 = createNFAState(nfa, "4");
      createTransition(state3, state4, cl_co_setStrategy);

      NFAState state5 = createNFAState(nfa, "5");
      createTransition(state4, state5, cl_co_request);

      NFAState state6 = createNFAState(nfa, "6");
      state6.setType(AbstractState.ACCEPT);
      createTransition(state5, state6, co_b_algorithm);
      createTransition(state3, state6, SymbolFactory.EPSILON);
      createTransition(state6, state4, SymbolFactory.EPSILON);

      return nfa;
   }


   private AbstractAutomaton createEclipseStrategyNFA()
   {
      NFA nfa = new NFA();

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject client1 = factory.provideMethodCallObject("cl1",
            "client");
      MethodCallObject client2 = factory.provideMethodCallObject("cl2",
            "client");
      MethodCallObject context = factory.provideMethodCallObject("co",
            "context");
      MethodCallObject abstractStrategyA = factory.provideMethodCallObject("a",
            "abstractStrategy");
      MethodCallObject abstractStrategyB = factory.provideMethodCallObject("b",
            "abstractStrategy");

      // create all symbols
      // permitted method calls
      AbstractSymbol cl_co_setStrategy = factory
            .providePermittedMethodCallSymbol(client1, context, "setStrategy");
      nfa.addToSymbols(cl_co_setStrategy);

      AbstractSymbol cl_co_request = factory.providePermittedMethodCallSymbol(
            client2, context, "request");
      nfa.addToSymbols(cl_co_request);

      AbstractSymbol co_a_algorithm = factory.providePermittedMethodCallSymbol(
            context, abstractStrategyA, "algorithm");
      nfa.addToSymbols(co_a_algorithm);

      AbstractSymbol co_b_algorithm = factory.providePermittedMethodCallSymbol(
            context, abstractStrategyB, "algorithm");
      nfa.addToSymbols(co_b_algorithm);

      // prohibited method calls
      AbstractSymbol pmc_co_setStrategy = factory
            .provideProhibitedMethodCallSymbol(context, "setStrategy");
      nfa.addToSymbols(pmc_co_setStrategy);

      AbstractSymbol pmc_co_request = factory
            .provideProhibitedMethodCallSymbol(context, "request");
      nfa.addToSymbols(pmc_co_request);

      AbstractSymbol pmc_a_algorithm = factory
            .provideProhibitedMethodCallSymbol(abstractStrategyA, "algorithm");
      nfa.addToSymbols(pmc_a_algorithm);

      AbstractSymbol pmc_b_algorithm = factory
            .provideProhibitedMethodCallSymbol(abstractStrategyB, "algorithm");
      nfa.addToSymbols(pmc_b_algorithm);

      // prohibited callers
      List<MethodCallObject> callers = new ArrayList<MethodCallObject>();
      callers.add(client2);
      AbstractSymbol pc_cl_co_request = factory.provideProhibitedCallerSymbol(
            callers, context, "request");
      nfa.addToSymbols(pc_cl_co_request);

      callers = new ArrayList<MethodCallObject>();
      callers.add(context);
      AbstractSymbol pc_co_a_algorithm = factory.provideProhibitedCallerSymbol(
            callers, abstractStrategyA, "algorithm");
      nfa.addToSymbols(pc_co_a_algorithm);

      AbstractSymbol pc_co_b_algorithm = factory.provideProhibitedCallerSymbol(
            callers, abstractStrategyB, "algorithm");
      nfa.addToSymbols(pc_co_b_algorithm);

      // create states
      NFAState state0 = createNFAState(nfa, "0");
      nfa.setStartState(state0);

      NFAState state1 = createNFAState(nfa, "1");
      createTransition(state0, state1, cl_co_setStrategy);

      NFAState state2 = createNFAState(nfa, "2");
      createTransition(state1, state2, cl_co_request);
      createTransition(state2, state1, SymbolFactory.EPSILON);

      NFAState state3 = createNFAState(nfa, "3");
      createTransition(state2, state3, co_a_algorithm);
      createTransition(state3, state2, SymbolFactory.EPSILON);
      createTransition(state3, state1, SymbolFactory.EPSILON);

      NFAState state4 = createNFAState(nfa, "4");
      createTransition(state3, state4, cl_co_setStrategy);

      NFAState state5 = createNFAState(nfa, "5");
      createTransition(state4, state5, cl_co_request);
      createTransition(state5, state4, SymbolFactory.EPSILON);

      NFAState state6 = createNFAState(nfa, "6");
      state6.setType(AbstractState.ACCEPT);
      createTransition(state5, state6, co_b_algorithm);
      createTransition(state3, state6, SymbolFactory.EPSILON);
      createTransition(state6, state5, SymbolFactory.EPSILON);
      createTransition(state6, state4, SymbolFactory.EPSILON);

      return nfa;
   }


   private AbstractAutomaton createStateNFA()
   {
      NFA nfa = new NFA();

      SymbolFactory factory = new SymbolFactory();

      MethodCallObject client = factory.provideMethodCallObject("cl", "client");
      MethodCallObject context = factory.provideMethodCallObject("co",
            "context");
      MethodCallObject abstractStateA = factory.provideMethodCallObject("a",
            "abstractState");
      MethodCallObject abstractStateB = factory.provideMethodCallObject("b",
            "abstractState");

      // create all symbols
      // permitted method calls
      AbstractSymbol cl_co_setState = factory.providePermittedMethodCallSymbol(
            client, context, "setState");
      nfa.addToSymbols(cl_co_setState);

      AbstractSymbol cl_co_request = factory.providePermittedMethodCallSymbol(
            client, context, "request");
      nfa.addToSymbols(cl_co_request);

      AbstractSymbol co_a_handle = factory.providePermittedMethodCallSymbol(
            context, abstractStateA, "handle");
      nfa.addToSymbols(co_a_handle);

      AbstractSymbol co_b_handle = factory.providePermittedMethodCallSymbol(
            context, abstractStateB, "handle");
      nfa.addToSymbols(co_b_handle);

      AbstractSymbol co_co_setState = factory.providePermittedMethodCallSymbol(
            context, context, "setState");
      nfa.addToSymbols(co_co_setState);

      AbstractSymbol a_co_setState = factory.providePermittedMethodCallSymbol(
            abstractStateA, context, "setState");
      nfa.addToSymbols(a_co_setState);

      // prohibited method calls
      AbstractSymbol pmc_co_setState = factory
            .provideProhibitedMethodCallSymbol(context, "setState");
      nfa.addToSymbols(pmc_co_setState);

      AbstractSymbol pmc_co_request = factory
            .provideProhibitedMethodCallSymbol(context, "request");
      nfa.addToSymbols(pmc_co_request);

      AbstractSymbol pmc_a_handle = factory.provideProhibitedMethodCallSymbol(
            abstractStateA, "handle");
      nfa.addToSymbols(pmc_a_handle);

      AbstractSymbol pmc_b_handle = factory.provideProhibitedMethodCallSymbol(
            abstractStateB, "handle");
      nfa.addToSymbols(pmc_b_handle);

      // prohibited callers
      List<MethodCallObject> callers = new ArrayList<MethodCallObject>();
      callers.add(client);
      AbstractSymbol pc_cl_co_request = factory.provideProhibitedCallerSymbol(
            callers, context, "request");
      nfa.addToSymbols(pc_cl_co_request);

      callers = new ArrayList<MethodCallObject>();
      callers.add(context);
      AbstractSymbol pc_co_a_handle = factory.provideProhibitedCallerSymbol(
            callers, abstractStateA, "handle");
      nfa.addToSymbols(pc_co_a_handle);

      AbstractSymbol pc_co_b_handle = factory.provideProhibitedCallerSymbol(
            callers, abstractStateB, "handle");
      nfa.addToSymbols(pc_co_b_handle);

      callers.add(abstractStateA);
      AbstractSymbol pc_aco_co_setState = factory
            .provideProhibitedCallerSymbol(callers, context, "setState");
      nfa.addToSymbols(pc_aco_co_setState);

      // create states
      NFAState state0 = createNFAState(nfa, "0");
      nfa.setStartState(state0);

      NFAState state1 = createNFAState(nfa, "1");
      createTransition(state0, state1, cl_co_setState);
      createTransition(state0, state1, SymbolFactory.EPSILON);

      NFAState state2 = createNFAState(nfa, "2");
      createTransition(state1, state2, cl_co_request);

      NFAState state3 = createNFAState(nfa, "3");
      createTransition(state2, state3, co_a_handle);
      createTransition(state3, state1, SymbolFactory.EPSILON);

      NFAState state4 = createNFAState(nfa, "4");
      createTransition(state3, state4, a_co_setState);
      createTransition(state3, state4, co_co_setState);

      NFAState state5 = createNFAState(nfa, "5");
      createTransition(state4, state5, cl_co_request);

      NFAState state6 = createNFAState(nfa, "6");
      state6.setType(AbstractState.ACCEPT);
      createTransition(state5, state6, co_b_handle);
      createTransition(state6, state4, SymbolFactory.EPSILON);

      return nfa;
   }


   private NFAState createNFAState(NFA nfa, String name)
   {
      NFAState state = new NFAState();
      state.setName(name);
      nfa.addToStates(state);

      return state;
   }


   private DFAState createDFAState(DFA dfa, String name)
   {
      DFAState state = new DFAState();
      state.setName(name);
      dfa.addToStates(state);

      return state;
   }


   private Transition createTransition(AbstractState previousState,
         AbstractState nextState, AbstractSymbol symbol)
   {
      Transition transition = new Transition();
      transition.setSymbol(symbol);
      transition.setPreviousState(previousState);
      transition.setNextState(nextState);

      return transition;
   }

}
