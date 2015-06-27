package org.reclipse.behavior.inference.input;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.reclipse.behavior.inference.BehavioralPatternEntry;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.reclipse.behavior.inference.Trigger;
import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.AbstractSymbol;
import org.reclipse.behavior.inference.automaton.Assignment;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.Transition;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.PermittedMethodCallSymbol;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedCallerSymbol;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedMethodCallSymbol;
import org.reclipse.behavior.inference.automaton.symbols.SymbolFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4140 $ $Date: 2009-09-10 13:13:56 +0200 (Do, 10 Sep 2009) $
 */
public class BehavioralPatternsCatalogSaxHandler extends DefaultHandler
{

   private static final String SYSTEM_ID_RESOURCE = "org/reclipse/patterns/behavior/inference/dtds/BehavioralPatternsCatalog.dtd";


   private SymbolFactory symbolFactory;

   private BehavioralPatternEntry currentBehavioralPattern;

   private DFA currentDFA;

   private PermittedMethodCallSymbol currentPermittedMethodCallSymbol;

   private ProhibitedMethodCallSymbol currentProhibitedMethodCallSymbol;

   private ProhibitedCallerSymbol currentProhibitedCallerSymbol;

   private HashMap symbols;

   private HashMap states;

   private HashMap assignments;


   private BehavioralPatternsCatalog catalog;


   public BehavioralPatternsCatalog getCatalog()
   {
      return this.catalog;
   }


   /**
    * @throws IOException
    * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
    */
   @Override
   public InputSource resolveEntity(final String publicId, final String systemId)
         throws SAXException,
            IOException
   {
      if (IBehavioralPatternsCatalogConstants.SYSTEM_ID.equals(systemId))
      {
         ClassLoader classLoader = getClass().getClassLoader();
         if (classLoader == null)
         {
            classLoader = ClassLoader.getSystemClassLoader();
         }

         final URL systemIdURL = classLoader.getResource(SYSTEM_ID_RESOURCE);

         if (systemIdURL != null)
         {
            try
            {
               return new InputSource(systemIdURL.openStream());
            }
            catch (final Exception e)
            {
               BehavioralAnalysisPlugin
                     .logError(
                           "Could not resolve SYSTEM or PUBLIC reference for DTD.",
                           e);
               throw new SAXException(e);
            }
         }
      }

      return super.resolveEntity(publicId, systemId);
   }


   /**
    * @see org.xml.sax.ContentHandler#startDocument()
    */
   @Override
   public void startDocument() throws SAXException
   {
      this.symbols = new HashMap();
      this.states = new HashMap();
      this.assignments = new HashMap();
   }


   /**
    * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String,
    *      java.lang.String, org.xml.sax.Attributes)
    */
   @Override
   public void startElement(final String uri, final String name,
         final String qName, final Attributes attrs)
   {
      try
      {
         if (IBehavioralPatternsCatalogConstants.BEHAVIORAL_PATTERNS_CATALOG_TAG
               .equals(name))
         {
            this.catalog = createBehavioralPatternsCatalog(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.BEHAVIORAL_PATTERN_ENTRY_TAG
               .equals(name))
         {
            this.symbols.clear();
            this.states.clear();
            this.assignments.clear();
            this.currentBehavioralPattern = createBehavioralPatternEntry(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.DFA_TAG.equals(name))
         {
            this.currentDFA = createDFA(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.PERMITTED_METHOD_CALL_SYMBOL_TAG
               .equals(name))
         {
            this.currentPermittedMethodCallSymbol = createPermittedMethodCallSymbol(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.PROHIBITED_METHOD_CALL_SYMBOL_TAG
               .equals(name))
         {
            this.currentProhibitedMethodCallSymbol = createProhibitedMethodCallSymbol(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.PROHIBITED_CALLER_SYMBOL_TAG
               .equals(name))
         {
            this.currentProhibitedCallerSymbol = createProhibitedCallersSymbol(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.CALLER_TAG.equals(name))
         {
            createCaller(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.CALLEE_TAG.equals(name))
         {
            createCallee(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.PERMITTED_CALLER_TAG
               .equals(name))
         {
            createPermittedCaller(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.DFA_STATE_TAG
               .equals(name))
         {
            createDFAState(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.ASSIGNMENT_TAG
               .equals(name))
         {
            createAssignment(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.TRANSITION_TAG
               .equals(name))
         {
            createTransition(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.START_STATE_TAG
               .equals(name))
         {
            createStartState(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.REJECTING_STATE_TAG
               .equals(name))
         {
            createRejectingState(attrs);
         }
         else if (IBehavioralPatternsCatalogConstants.TRIGGER_TAG.equals(name))
         {
            createTrigger(attrs);
         }
      }
      catch (final Exception e)
      {
         BehavioralAnalysisPlugin.logError(
               "Unexpected exception in parsing tracer input.", e);
      }
   }


   /**
    * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String,
    *      java.lang.String)
    */
   @Override
   public void endElement(final String uri, final String name,
         final String qName)
   {
      if (IBehavioralPatternsCatalogConstants.BEHAVIORAL_PATTERN_ENTRY_TAG
            .equals(name))
      {
         if (BehavioralAnalysisPlugin.LOG_INFO)
         {
            BehavioralAnalysisPlugin.logInfo("Behavioral Pattern loaded: "
                  + this.currentBehavioralPattern.getName());
         }
         this.currentBehavioralPattern = null;
      }
      else if (IBehavioralPatternsCatalogConstants.DFA_TAG.equals(name))
      {
         this.currentDFA = null;
      }
      else if (IBehavioralPatternsCatalogConstants.PERMITTED_METHOD_CALL_SYMBOL_TAG
            .equals(name))
      {
         this.currentDFA.addToSymbols(this.currentPermittedMethodCallSymbol);
         this.currentPermittedMethodCallSymbol = null;
      }
      else if (IBehavioralPatternsCatalogConstants.PROHIBITED_METHOD_CALL_SYMBOL_TAG
            .equals(name))
      {
         this.currentDFA.addToSymbols(this.currentProhibitedMethodCallSymbol);
         this.currentProhibitedMethodCallSymbol = null;
      }
      else if (IBehavioralPatternsCatalogConstants.PROHIBITED_CALLER_SYMBOL_TAG
            .equals(name))
      {
         this.currentDFA.addToSymbols(this.currentProhibitedCallerSymbol);
         this.currentProhibitedCallerSymbol = null;
      }
   }


   /**
    * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
    */
   @Override
   public void warning(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("XML Parse warning in line "
            + exception.getLineNumber() + ":", exception);
   }


   /**
    * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
    */
   @Override
   public void error(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("XML Parse Error in line "
            + exception.getLineNumber() + ":", exception);
   }


   /**
    * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
    */
   @Override
   public void fatalError(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("Fatal XML Parse Error in line "
            + exception.getLineNumber() + ":", exception);
   }


   private BehavioralPatternsCatalog createBehavioralPatternsCatalog(
         final Attributes attrs)
   {
      final BehavioralPatternsCatalog catalog = new BehavioralPatternsCatalog();

      return catalog;
   }


   private BehavioralPatternEntry createBehavioralPatternEntry(
         final Attributes attrs)
   {
      final BehavioralPatternEntry pattern = new BehavioralPatternEntry();
      pattern.setName(attrs
            .getValue(IBehavioralPatternsCatalogConstants.NAME_ATTRIBUTE));
      final boolean negative = Boolean
            .valueOf(
                  attrs
                        .getValue(IBehavioralPatternsCatalogConstants.NEGATIVE_ATTRIBUTE))
            .booleanValue();
      pattern.setNegative(negative);

      this.catalog.addToEntries(pattern);

      return pattern;
   }


   private DFA createDFA(final Attributes attrs)
   {
      // create a new SymbolFactory
      this.symbolFactory = new SymbolFactory();

      final DFA dfa = new DFA();
      this.currentBehavioralPattern.setAutomaton(dfa);

      return dfa;
   }


   private PermittedMethodCallSymbol createPermittedMethodCallSymbol(
         final Attributes attrs)
   {
      final PermittedMethodCallSymbol symbol = new PermittedMethodCallSymbol();
      symbol
            .setMethodName(attrs
                  .getValue(IBehavioralPatternsCatalogConstants.METHOD_NAME_ATTRIBUTE));
      final String id = attrs
            .getValue(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);

      this.symbols.put(id, symbol);

      return symbol;
   }


   private ProhibitedMethodCallSymbol createProhibitedMethodCallSymbol(
         final Attributes attrs)
   {
      final ProhibitedMethodCallSymbol symbol = new ProhibitedMethodCallSymbol();
      symbol
            .setMethodName(attrs
                  .getValue(IBehavioralPatternsCatalogConstants.METHOD_NAME_ATTRIBUTE));
      final String id = attrs
            .getValue(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);

      this.symbols.put(id, symbol);

      return symbol;
   }


   private ProhibitedCallerSymbol createProhibitedCallersSymbol(
         final Attributes attrs)
   {
      final ProhibitedCallerSymbol symbol = new ProhibitedCallerSymbol();
      symbol
            .setMethodName(attrs
                  .getValue(IBehavioralPatternsCatalogConstants.METHOD_NAME_ATTRIBUTE));
      final String id = attrs
            .getValue(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);

      this.symbols.put(id, symbol);

      return symbol;
   }


   private void createCaller(final Attributes attrs)
   {
      final MethodCallObject object = createMethodCallObject(attrs);
      this.currentPermittedMethodCallSymbol.setCaller(object);
   }


   private void createCallee(final Attributes attrs)
   {
      final MethodCallObject object = createMethodCallObject(attrs);

      if (this.currentPermittedMethodCallSymbol != null)
      {
         this.currentPermittedMethodCallSymbol.setCallee(object);
      }
      else if (this.currentProhibitedMethodCallSymbol != null)
      {
         this.currentProhibitedMethodCallSymbol.setCallee(object);
      }
      else if (this.currentProhibitedCallerSymbol != null)
      {
         this.currentProhibitedCallerSymbol.setCallee(object);
      }
   }


   private void createPermittedCaller(final Attributes attrs)
   {
      final MethodCallObject object = createMethodCallObject(attrs);
      this.currentProhibitedCallerSymbol.addToPermittedCallers(object);
   }


   private MethodCallObject createMethodCallObject(final Attributes attrs)
   {
      boolean isSet = false;
      boolean forEach = false;
      if (attrs.getValue(IBehavioralPatternsCatalogConstants.SET_ATTRIBUTE) != null)
      {
         isSet = true;
      }
      if (attrs.getValue(IBehavioralPatternsCatalogConstants.FOREACH_ATTRIBUTE) == null)
      {
         forEach = false;
      }
      else
      {
         forEach = Boolean
               .parseBoolean(attrs
                     .getValue(IBehavioralPatternsCatalogConstants.FOREACH_ATTRIBUTE));
      }
      final MethodCallObject object = this.symbolFactory
            .provideMethodCallObject(
                  attrs
                        .getValue(IBehavioralPatternsCatalogConstants.NAME_ATTRIBUTE),
                  attrs
                        .getValue(IBehavioralPatternsCatalogConstants.TYPE_NAME_ATTRIBUTE),
                  isSet, forEach);

      return object;
   }


   private void createDFAState(final Attributes attrs)
   {
      final DFAState dfaState = new DFAState();

      final String id = attrs
            .getValue(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);

      final String name = attrs
            .getValue(IBehavioralPatternsCatalogConstants.NAME_ATTRIBUTE);
      if (name != null)
      {
         dfaState.setName(name);
      }

      final int type = Integer.valueOf(
            attrs.getValue(IBehavioralPatternsCatalogConstants.TYPE_ATTRIBUTE))
            .intValue();
      switch (type)
      {
         case 0:
            dfaState.setType(AbstractState.NONE);
            break;
         case 1:
            dfaState.setType(AbstractState.ACCEPT);
            break;
         case 2:
            dfaState.setType(AbstractState.REJECT);
            break;
      }

      this.currentDFA.addToStates(dfaState);
      this.states.put(id, dfaState);
   }


   private void createAssignment(final Attributes attrs)
   {
      final Assignment assignment = new Assignment();

      final String id = attrs
            .getValue(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);

      final String leftSide = attrs
            .getValue(IBehavioralPatternsCatalogConstants.LEFT_SIDE_ATTRIBUTE);

      final String rightSide = attrs
            .getValue(IBehavioralPatternsCatalogConstants.RIGHT_SIDE_ATTRIBUTE);

      if (leftSide != null)
      {
         assignment.setLeftSide(leftSide);
      }
      if (rightSide != null)
      {
         assignment.setRightSide(rightSide);
      }

      this.currentDFA.addToAssignments(assignment);
      this.assignments.put(id, assignment);
   }


   private void createTransition(final Attributes attrs)
   {
      final Transition transition = new Transition();

      final String previousStateId = attrs
            .getValue(IBehavioralPatternsCatalogConstants.PREVIOUS_STATE_ID_ATTRIBUTE);
      final DFAState previousState = (DFAState) this.states
            .get(previousStateId);
      transition.setPreviousState(previousState);

      final String nextStateId = attrs
            .getValue(IBehavioralPatternsCatalogConstants.NEXT_STATE_ID_ATTRIBUTE);
      final DFAState nextState = (DFAState) this.states.get(nextStateId);
      transition.setNextState(nextState);

      final String symbolId = attrs
            .getValue(IBehavioralPatternsCatalogConstants.SYMBOL_ID_ATTRIBUTE);
      final AbstractSymbol symbol = (AbstractSymbol) this.symbols.get(symbolId);
      transition.setSymbol(symbol);

      final String assignmentId = attrs
            .getValue(IBehavioralPatternsCatalogConstants.ASSIGNMENT_ID_ATTRIBUTE);
      final Assignment assignment = (Assignment) this.assignments
            .get(assignmentId);
      transition.setAssignment(assignment);
   }


   private void createStartState(final Attributes attrs)
   {
      final String id = attrs
            .getValue(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      final DFAState startState = (DFAState) this.states.get(id);

      this.currentDFA.setStartState(startState);
   }


   private void createRejectingState(final Attributes attrs)
   {
      final String id = attrs
            .getValue(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      final DFAState rejectingState = (DFAState) this.states.get(id);

      this.currentDFA.setRejectingState(rejectingState);
   }


   private void createTrigger(final Attributes attrs)
   {
      final Trigger trigger = new Trigger();

      trigger
            .setCallerName(attrs
                  .getValue(IBehavioralPatternsCatalogConstants.CALLER_NAME_ATTRIBUTE));

      final String callerType = attrs
            .getValue(IBehavioralPatternsCatalogConstants.CALLER_TYPE_NAME_ATTRIBUTE);
      if (callerType != null)
      {
         trigger.setCallerTypeName(callerType);
      }

      trigger
            .setCalleeName(attrs
                  .getValue(IBehavioralPatternsCatalogConstants.CALLEE_NAME_ATTRIBUTE));

      trigger
            .setCalleeTypeName(attrs
                  .getValue(IBehavioralPatternsCatalogConstants.CALLEE_TYPE_NAME_ATTRIBUTE));

      trigger
            .setMethodName(attrs
                  .getValue(IBehavioralPatternsCatalogConstants.METHOD_NAME_ATTRIBUTE));

      this.currentBehavioralPattern.addToTriggers(trigger);
   }

}
