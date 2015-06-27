package org.reclipse.behavior.inference.input;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.fujaba.commons.utils.Visitor;
import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.reclipse.behavior.inference.BehavioralPatternEntry;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.reclipse.behavior.inference.Trigger;
import org.reclipse.behavior.inference.automaton.Assignment;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.Transition;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallSetObject;
import org.reclipse.behavior.inference.automaton.symbols.PermittedMethodCallSymbol;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedCallerSymbol;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedMethodCallSymbol;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4139 $ $Date: 2009-09-08 09:53:32 +0200 (Di, 08 Sep 2009) $
 */
public class BehavioralPatternsCatalogWriter extends Visitor
{

   private final BehavioralPatternsCatalog catalog;

   private HashMap symbols = new HashMap();

   private HashMap states = new HashMap();

   private HashSet transitions = new HashSet();

   private int symbolIdCounter = 0;

   private int statesIdCounter = 0;


   public BehavioralPatternsCatalogWriter(
         final BehavioralPatternsCatalog catalog)
   {
      this.catalog = catalog;
   }


   /**
    * @param file The file to save the document in.
    * @return true, if file has been written
    */
   public boolean save(final File file)
   {
      try
      {
         FileWriter fileWriter;
         fileWriter = new FileWriter(file);
         final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         generateXMLDocument(bufferedWriter);
         bufferedWriter.flush();
         bufferedWriter.close();
      }
      catch (final IOException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Error saving Behavioral Patterns Catalog.", e);
         return false;
      }

      return true;
   }


   public void generateXMLDocument(final Writer writer) throws IOException
   {
      writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n\n");
      writer.write("<!DOCTYPE ");
      writer
            .write(IBehavioralPatternsCatalogConstants.BEHAVIORAL_PATTERNS_CATALOG_TAG);
      writer.write(" SYSTEM \"");
      writer.write(IBehavioralPatternsCatalogConstants.SYSTEM_ID);
      writer.write("\">");

      visit(this.catalog, writer);
   }


   public void visitBehavioralPatternsCatalog(
         final BehavioralPatternsCatalog catalog, final Object... arguments)
         throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n<");
      writer
            .write(IBehavioralPatternsCatalogConstants.BEHAVIORAL_PATTERNS_CATALOG_TAG);
      writer.write(">\n\n");

      final Iterator iter = catalog.iteratorOfEntries();
      while (iter.hasNext())
      {
         final BehavioralPatternEntry entry = (BehavioralPatternEntry) iter
               .next();
         visit(entry, writer);
      }

      writer.write("\n</");
      writer
            .write(IBehavioralPatternsCatalogConstants.BEHAVIORAL_PATTERNS_CATALOG_TAG);
      writer.write(">\n");
   }


   public void visitBehavioralPatternEntry(final BehavioralPatternEntry entry,
         final Object... arguments) throws IOException
   {
      this.symbols.clear();
      this.states.clear();
      this.transitions.clear();

      final Writer writer = (Writer) arguments[0];

      writer.write("\n  <");
      writer
            .write(IBehavioralPatternsCatalogConstants.BEHAVIORAL_PATTERN_ENTRY_TAG);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(entry.getName());
      writer.write("\" ");
      writer.write(IBehavioralPatternsCatalogConstants.NEGATIVE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Boolean.toString(entry.isNegative()));
      writer.write("\">\n");

      visit(entry.getAutomaton(), writer);

      writer.write("\n");
      final Iterator iter = entry.iteratorOfTriggers();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("\n  </");
      writer
            .write(IBehavioralPatternsCatalogConstants.BEHAVIORAL_PATTERN_ENTRY_TAG);
      writer.write(">\n");
   }


   public void visitDFA(final DFA dfa, final Object... arguments)
         throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n    <");
      writer.write(IBehavioralPatternsCatalogConstants.DFA_TAG);
      writer.write(">\n");

      Iterator iter = dfa.iteratorOfSymbols();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("\n");
      iter = dfa.iteratorOfStates();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("\n");
      iter = dfa.iteratorOfAssignments();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("\n");
      iter = this.transitions.iterator();
      while (iter.hasNext())
      {
         visit(iter.next(), writer);
      }

      writer.write("\n      <");
      writer.write(IBehavioralPatternsCatalogConstants.START_STATE_TAG);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write((String) this.states.get(dfa.getStartState()));
      writer.write("\"/>\n");

      writer.write("\n      <");
      writer.write(IBehavioralPatternsCatalogConstants.REJECTING_STATE_TAG);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write((String) this.states.get(dfa.getRejectingState()));
      writer.write("\"/>\n");

      writer.write("\n    </");
      writer.write(IBehavioralPatternsCatalogConstants.DFA_TAG);
      writer.write(">\n");
   }


   public void visitDFAState(final DFAState dfaState, final Object... arguments)
         throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      final String id = "state" + this.statesIdCounter++;
      this.states.put(dfaState, id);

      writer.write("      <");
      writer.write(IBehavioralPatternsCatalogConstants.DFA_STATE_TAG);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(id);
      writer.write("\" ");

      if (dfaState.getName() != null && !dfaState.getName().trim().equals(""))
      {
         writer.write(IBehavioralPatternsCatalogConstants.NAME_ATTRIBUTE);
         writer.write("=\"");
         writer.write(dfaState.getName());
         writer.write("\" ");
      }

      writer.write(IBehavioralPatternsCatalogConstants.TYPE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(dfaState.getType()));
      writer.write("\"/>\n");

      final Iterator iter = dfaState.iteratorOfOutgoingTransitions();
      while (iter.hasNext())
      {
         this.transitions.add(iter.next());
      }
   }


   public void visitTransition(final Transition transition,
         final Object... arguments) throws IOException
   {

      final String previousStateId = (String) this.states.get(transition
            .getPreviousState());
      final String nextStateId = (String) this.states.get(transition
            .getNextState());
      final String symbolId = (String) this.symbols.get(transition.getSymbol());

      final Writer writer = (Writer) arguments[0];

      writer.write("      <");
      writer.write(IBehavioralPatternsCatalogConstants.TRANSITION_TAG);
      writer.write(" ");
      writer
            .write(IBehavioralPatternsCatalogConstants.PREVIOUS_STATE_ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(previousStateId);
      writer.write("\" ");
      writer.write(IBehavioralPatternsCatalogConstants.NEXT_STATE_ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(nextStateId);
      writer.write("\" ");
      writer.write(IBehavioralPatternsCatalogConstants.SYMBOL_ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(symbolId);
      if (transition.getAssignment() != null)
      {
         writer.write("\" ");
         writer
               .write(IBehavioralPatternsCatalogConstants.ASSIGNMENT_ID_ATTRIBUTE);
         writer.write("=\"");
         writer.write(transition.getAssignment().getId());
      }
      writer.write("\"/>\n");
   }


   public void visitAssignment(final Assignment assignment,
         final Object... arguments) throws IOException
   {
      final String leftSide = assignment.getLeftSide();
      final String rightSide = assignment.getRightSide();
      final String id = assignment.getId();

      final Writer writer = (Writer) arguments[0];

      writer.write("      <");
      writer.write(IBehavioralPatternsCatalogConstants.ASSIGNMENT_TAG);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(id);
      writer.write("\" ");
      writer.write(IBehavioralPatternsCatalogConstants.LEFT_SIDE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(leftSide);
      writer.write("\" ");
      writer.write(IBehavioralPatternsCatalogConstants.RIGHT_SIDE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(rightSide);
      writer.write("\"/>\n");
   }


   public void visitPermittedMethodCallSymbol(
         final PermittedMethodCallSymbol symbol, final Object... arguments)
         throws IOException
   {
      final String symbolId = "symbol" + this.symbolIdCounter++;
      this.symbols.put(symbol, symbolId);

      final Writer writer = (Writer) arguments[0];

      writer.write("\n      <");
      writer
            .write(IBehavioralPatternsCatalogConstants.PERMITTED_METHOD_CALL_SYMBOL_TAG);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(symbolId);
      writer.write("\" ");
      writer.write(IBehavioralPatternsCatalogConstants.METHOD_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(symbol.getMethodName());
      writer.write("\">\n");

      visit(symbol.getCaller(), writer,
            IBehavioralPatternsCatalogConstants.CALLER_TAG);

      visit(symbol.getCallee(), writer,
            IBehavioralPatternsCatalogConstants.CALLEE_TAG);

      writer.write("      </");
      writer
            .write(IBehavioralPatternsCatalogConstants.PERMITTED_METHOD_CALL_SYMBOL_TAG);
      writer.write(">\n");
   }


   public void visitProhibitedMethodCallSymbol(
         final ProhibitedMethodCallSymbol symbol, final Object... arguments)
         throws IOException
   {
      final String symbolId = "symbol" + this.symbolIdCounter++;
      this.symbols.put(symbol, symbolId);

      final Writer writer = (Writer) arguments[0];

      writer.write("\n      <");
      writer
            .write(IBehavioralPatternsCatalogConstants.PROHIBITED_METHOD_CALL_SYMBOL_TAG);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(symbolId);
      writer.write("\" ");
      writer.write(IBehavioralPatternsCatalogConstants.METHOD_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(symbol.getMethodName());
      writer.write("\">\n");

      visit(symbol.getCallee(), writer,
            IBehavioralPatternsCatalogConstants.CALLEE_TAG);

      writer.write("      </");
      writer
            .write(IBehavioralPatternsCatalogConstants.PROHIBITED_METHOD_CALL_SYMBOL_TAG);
      writer.write(">\n");
   }


   public void visitProhibitedCallerSymbol(final ProhibitedCallerSymbol symbol,
         final Object... arguments) throws IOException
   {
      final String symbolId = "symbol" + this.symbolIdCounter++;
      this.symbols.put(symbol, symbolId);

      final Writer writer = (Writer) arguments[0];

      writer.write("\n      <");
      writer
            .write(IBehavioralPatternsCatalogConstants.PROHIBITED_CALLER_SYMBOL_TAG);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(symbolId);
      writer.write("\" ");
      writer.write(IBehavioralPatternsCatalogConstants.METHOD_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(symbol.getMethodName());
      writer.write("\">\n");

      final Iterator iter = symbol.iteratorOfPermittedCallers();
      while (iter.hasNext())
      {
         visit(iter.next(), writer,
               IBehavioralPatternsCatalogConstants.PERMITTED_CALLER_TAG);
      }

      visit(symbol.getCallee(), writer,
            IBehavioralPatternsCatalogConstants.CALLEE_TAG);

      writer.write("      </");
      writer
            .write(IBehavioralPatternsCatalogConstants.PROHIBITED_CALLER_SYMBOL_TAG);
      writer.write(">\n");
   }


   public void visitMethodCallObject(final MethodCallObject object,
         final Object... arguments) throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("        <");
      writer.write((String) arguments[1]);
      writer.write(" ");
      writer.write(IBehavioralPatternsCatalogConstants.NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(object.getName());
      if (object.getTypeName() != null)
      {
         writer.write("\" ");
         writer.write(IBehavioralPatternsCatalogConstants.TYPE_NAME_ATTRIBUTE);
         writer.write("=\"");
         writer.write(object.getTypeName());
      }
      if (object instanceof MethodCallSetObject)
      {
         writer.write("\" ");
         writer.write(IBehavioralPatternsCatalogConstants.SET_ATTRIBUTE);
         writer.write("=\"");
         writer.write(((MethodCallSetObject) object).getSet());
         writer.write("\" ");
         writer.write(IBehavioralPatternsCatalogConstants.FOREACH_ATTRIBUTE);
         writer.write("=\"");
         writer.write(Boolean.toString(((MethodCallSetObject) object)
               .isForeach()));
      }
      writer.write("\"/>\n");
   }


   public void visitTrigger(final Trigger trigger, final Object... arguments)
         throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("    <");
      writer.write(IBehavioralPatternsCatalogConstants.TRIGGER_TAG);
      writer.write(" ");

      writer.write(IBehavioralPatternsCatalogConstants.CALLER_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(trigger.getCallerName());
      writer.write("\" ");

      if (trigger.getCallerTypeName() != null)
      {
         writer
               .write(IBehavioralPatternsCatalogConstants.CALLER_TYPE_NAME_ATTRIBUTE);
         writer.write("=\"");
         writer.write(trigger.getCallerTypeName());
         writer.write("\" ");
      }

      writer.write(IBehavioralPatternsCatalogConstants.CALLEE_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(trigger.getCalleeName());
      writer.write("\" ");

      writer
            .write(IBehavioralPatternsCatalogConstants.CALLEE_TYPE_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(trigger.getCalleeTypeName());
      writer.write("\" ");

      writer.write(IBehavioralPatternsCatalogConstants.METHOD_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(trigger.getMethodName());
      writer.write("\"/>\n");
   }

}
