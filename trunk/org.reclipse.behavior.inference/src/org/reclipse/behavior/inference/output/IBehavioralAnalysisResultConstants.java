package org.reclipse.behavior.inference.output;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
public interface IBehavioralAnalysisResultConstants
{

   /**
    * URL used in TraceDefinition documents (XML-files) to identify the DTD. DTD given as "SYSTEM"
    * (non-public) definition.
    */
   public static final String SYSTEM_ID = "http://wwwcs.uni-paderborn.de/cs/fujaba/DTDs/BehavioralAnalysisResult.dtd";

   public final static String BEHAVIORAL_ANALYSIS_RESULT_TAG = "BehavioralAnalysisResult";

   public final static String ANNOTATION_TAG = "Annotation";

   public final static String STRUCTURAL_ANNOTATION_TAG = "StructuralAnnotation";

   public final static String BOUND_OBJECT_TAG = "BoundObject";

   public final static String BEHAVIORAL_ANNOTATION_TAG = "BehavioralAnnotation";

   public final static String TRACE_TAG = "Trace";

   public final static String METHOD_CALL_TAG = "MethodCall";

   public final static String CALLER_TAG = "Caller";

   public final static String CALLEE_TAG = "Callee";

   public final static String ARGUMENT_TAG = "Argument";

   public final static String DATE_ATTRIBUTE = "date";

   public final static String SIZE_ATTRIBUTE = "size";

   public final static String TYPE_ATTRIBUTE = "type";

   public final static String TYPE_NAME_ATTRIBUTE = "typeName";

   public final static String FUZZY_BELIEF_ATTRIBUTE = "fuzzyBelief";

   public final static String KEY_ATTRIBUTE = "key";

   public final static String NAME_ATTRIBUTE = "name";

   public final static String TRACES_ATTRIBUTE = "traces";

   public final static String ACCEPTED_TRACES_ATTRIBUTE = "acceptedTraces";

   public final static String NOT_ACCEPTED_TRACES_ATTRIBUTE = "notAcceptedTraces";

   public final static String REJECTED_TRACES_ATTRIBUTE = "rejectedTraces";

   public final static String PASSED_ACCEPTING_STATE_TRACES_ATTRIBUTE = "passedAcceptingStateTraces";

   public final static String AVG_LENGTH_ACCEPTED_TRACES_ATTRIBUTE = "avgLengthAcceptedTraces";

   public final static String ID_ATTRIBUTE = "id";

   public final static String RESULT_ATTRIBUTE = "result";

   public final static String PASSED_ACCEPTING_STATE_ATTRIBUTE = "passedAcceptingState";

   public final static String LENGTH_OF_ACCEPTED_TRACE_ATTRIBUTE = "lengthOfAcceptedTrace";

   public final static String VALUE_ATTRIBUTE = "value";

   public final static String SYMBOLIC_EXECUTION_ATTRIBUTE = "symbolicExecution";
}
