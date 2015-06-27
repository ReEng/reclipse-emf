package org.reclipse.behavior.inference.output;


import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.ACCEPTED_TRACES_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.ANNOTATION_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.ARGUMENT_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.AVG_LENGTH_ACCEPTED_TRACES_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.BEHAVIORAL_ANALYSIS_RESULT_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.BEHAVIORAL_ANNOTATION_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.BOUND_OBJECT_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.CALLEE_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.CALLER_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.DATE_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.FUZZY_BELIEF_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.ID_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.KEY_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.LENGTH_OF_ACCEPTED_TRACE_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.METHOD_CALL_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.NAME_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.NOT_ACCEPTED_TRACES_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.PASSED_ACCEPTING_STATE_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.PASSED_ACCEPTING_STATE_TRACES_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.REJECTED_TRACES_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.RESULT_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.SIZE_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.STRUCTURAL_ANNOTATION_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.SYMBOLIC_EXECUTION_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.SYSTEM_ID;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.TRACES_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.TRACE_TAG;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.TYPE_ATTRIBUTE;
import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.TYPE_NAME_ATTRIBUTE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Iterator;

import org.fujaba.commons.utils.Visitor;
import org.reclipse.behavior.inference.Annotation;
import org.reclipse.behavior.inference.BehavioralAnalysis;
import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.reclipse.behavior.inference.BehavioralAnnotation;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.Trace;
import org.reclipse.tracer.model.tracegraph.TGArgument;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
public class BehavioralAnalysisResultWriter extends Visitor
{

   private final BehavioralAnalysis behavioralAnalysis;

   private final boolean logTraces;


   public BehavioralAnalysisResultWriter(
         final BehavioralAnalysis behavioralAnalysis, final boolean logTraces)
   {
      this.behavioralAnalysis = behavioralAnalysis;
      this.logTraces = logTraces;
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
      catch (final IOException exception)
      {
         BehavioralAnalysisPlugin.logError(
               "Error writing behavioral analysis results to file '"
                     + file.getAbsolutePath() + "': ", exception);
         return false;
      }

      return true;
   }


   public void generateXMLDocument(final Writer writer) throws IOException
   {
      writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n");
      writer.write("<!DOCTYPE ");
      writer.write(BEHAVIORAL_ANALYSIS_RESULT_TAG);
      writer.write(" SYSTEM \"");
      writer.write(SYSTEM_ID);
      writer.write("\">\n");

      visit(this.behavioralAnalysis, writer);
   }


   public void visitBehavioralAnalysis(
         final BehavioralAnalysis behaviorRecognition,
         final Object... arguments) throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n<");
      writer.write(BEHAVIORAL_ANALYSIS_RESULT_TAG);
      writer.write(" ");
      writer.write(DATE_ATTRIBUTE);
      writer.write("=\"");
      writer.write((new Date(System.currentTimeMillis())).toString());
      writer.write("\" ");
      writer.write(SIZE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(behaviorRecognition.sizeOfAnnotations()));
      writer.write("\">\n");

      final Iterator<Annotation> annotationsIter = behaviorRecognition
            .iteratorOfAnnotations();
      while (annotationsIter.hasNext())
      {
         final Annotation annotation = annotationsIter.next();

         final BehavioralAnnotation behavioralAnnotation = annotation
               .getBehavioralAnnotation();
         if (behavioralAnnotation != null)
         {
            visit(annotation, writer);
         }
      }

      writer.write("\n</");
      writer.write(BEHAVIORAL_ANALYSIS_RESULT_TAG);
      writer.write(">\n");
   }


   public void visitAnnotation(final Annotation annotation,
         final Object... arguments) throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n  <");
      writer.write(ANNOTATION_TAG);
      writer.write(" ");
      writer.write(TYPE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(annotation.getType());
      writer.write("\">\n");

      visit(annotation.getStructuralAnnotation(), writer);
      visit(annotation.getBehavioralAnnotation(), writer);

      writer.write("\n  </");
      writer.write(ANNOTATION_TAG);
      writer.write(">\n");
   }


   public void visitStructuralAnnotation(
         final StructuralAnnotation structuralAnnotation,
         final Object... arguments) throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n    <");
      writer.write(STRUCTURAL_ANNOTATION_TAG);
      writer.write(" ");
      writer.write(FUZZY_BELIEF_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Double.toString(structuralAnnotation.getFuzzyBelief()));
      writer.write("\">");

      for (final String key : structuralAnnotation.keysOfNodes())
      {
         final String name = structuralAnnotation.getFromNodes(key);
         generateBoundObjectTag(key, name, "      ", writer);
      }

      writer.write("\n    </");
      writer.write(STRUCTURAL_ANNOTATION_TAG);
      writer.write(">\n");
   }


   public void visitBehavioralAnnotation(
         final BehavioralAnnotation behavioralAnnotation,
         final Object... arguments) throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n    <");
      writer.write(BEHAVIORAL_ANNOTATION_TAG);
      writer.write(" ");

      writer.write(SYMBOLIC_EXECUTION_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Boolean
            .toString(behavioralAnnotation.getSymbolicExecution()));
      writer.write("\" ");

      writer.write(TRACES_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(behavioralAnnotation.sizeOfTraces()));
      writer.write("\" ");
      writer.write(ACCEPTED_TRACES_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(behavioralAnnotation.getAcceptedTraces()));
      writer.write("\" ");
      writer.write(NOT_ACCEPTED_TRACES_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer
            .toString(behavioralAnnotation.getNotAcceptedTraces()));
      writer.write("\" ");
      writer.write(REJECTED_TRACES_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(behavioralAnnotation.getRejectedTraces()));
      writer.write("\"");

      writer.write("\n                          ");
      writer.write(PASSED_ACCEPTING_STATE_TRACES_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(behavioralAnnotation
            .getPassedAcceptingStateTraces()));
      writer.write("\" ");
      writer.write(AVG_LENGTH_ACCEPTED_TRACES_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Float.toString(behavioralAnnotation
            .getAvgLengthAcceptedTraces()));
      writer.write("\">\n");

      if (this.logTraces)
      {
         final Iterator<Trace> tokensIter = behavioralAnnotation
               .iteratorOfTraces();
         while (tokensIter.hasNext())
         {
            final Trace trace = tokensIter.next();
            visit(trace, writer);
         }
      }

      writer.write("\n    </");
      writer.write(BEHAVIORAL_ANNOTATION_TAG);
      writer.write(">\n");
   }


   public void visitTrace(final Trace trace, final Object... arguments)
         throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n      <");
      writer.write(TRACE_TAG);
      writer.write(" ");
      writer.write(ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(trace.getId()));
      writer.write("\" ");
      writer.write(RESULT_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(trace.getResult()));
      writer.write("\" ");
      writer.write(PASSED_ACCEPTING_STATE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(trace.getPassedAcceptingState()));
      writer.write("\" ");
      writer.write(LENGTH_OF_ACCEPTED_TRACE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(trace.getLengthOfAcceptedTrace()));
      writer.write("\">\n");

      for (final String key : trace.keysOfBindings())
      {
         generateBoundObjectTag(key, trace.getFromBindings(key).getId(),
               "        ", writer);
      }

      writer.write("\n");

      final Iterator<TGMethodCall> methodCallsIter = trace
            .iteratorOfMatchedCalls();
      while (methodCallsIter.hasNext())
      {
         final TGMethodCall methodCall = methodCallsIter.next();
         visit(methodCall, writer);
      }

      writer.write("\n      </");
      writer.write(TRACE_TAG);
      writer.write(">\n");
   }


   public void visitTGMethodCall(final TGMethodCall methodCall,
         final Object... arguments) throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n        <");
      writer.write(METHOD_CALL_TAG);
      writer.write(" ");
      writer.write(ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(methodCall.getId());
      writer.write("\" ");
      writer.write(NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(methodCall.getName());
      writer.write("\">");

      visit(methodCall.getCaller(), writer, CALLER_TAG);
      visit(methodCall.getCallee(), writer, CALLEE_TAG);

      final Iterator<TGArgument> argumentsIter = methodCall
            .iteratorOfArguments();
      while (argumentsIter.hasNext())
      {
         final TGArgument argument = argumentsIter.next();
         visit(argument, writer);
      }

      writer.write("\n        </");
      writer.write(METHOD_CALL_TAG);
      writer.write(">\n");
   }


   public void visitTGObject(final TGObject object, final Object... arguments)
         throws IOException
   {
      final Writer writer = (Writer) arguments[0];
      final String tag = (String) arguments[1];

      writer.write("\n          <");
      writer.write(tag);
      writer.write(" ");
      writer.write(ID_ATTRIBUTE);
      writer.write("=\"");
      writer.write(object.getId());
      writer.write("\" ");
      writer.write(TYPE_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(object.getType().getName());
      writer.write("\"/>");
   }


   public void visitTGArgument(final TGArgument argument,
         final Object... arguments) throws IOException
   {
      final Writer writer = (Writer) arguments[0];

      writer.write("\n          <");
      writer.write(ARGUMENT_TAG);
      writer.write(" ");
      writer.write(TYPE_NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(argument.getType().getName());
      writer.write("\"/>");
   }


   private void generateBoundObjectTag(final String key, final String name,
         final String indent, final Writer writer) throws IOException
   {
      writer.write("\n");
      writer.write(indent);
      writer.write("<");
      writer.write(BOUND_OBJECT_TAG);
      writer.write(" ");
      writer.write(KEY_ATTRIBUTE);
      writer.write("=\"");
      writer.write(key);
      writer.write("\" ");
      writer.write(NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(name);
      writer.write("\"/>");
   }

}
