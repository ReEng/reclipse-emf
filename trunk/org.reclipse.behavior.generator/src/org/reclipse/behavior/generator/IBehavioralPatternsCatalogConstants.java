package org.reclipse.behavior.generator;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4139 $ $Date: 2009-09-08 09:53:32 +0200 (Di, 08 Sep 2009) $
 */
public interface IBehavioralPatternsCatalogConstants
{

   public static final String SYSTEM_ID = "http://wwwcs.uni-paderborn.de/cs/fujaba/DTDs/BehavioralPatternsCatalog.dtd";

   public static final String BEHAVIORAL_PATTERNS_CATALOG_TAG = "BehavioralPatternsCatalog";

   public static final String BEHAVIORAL_PATTERN_ENTRY_TAG = "BehavioralPatternEntry";

   public static final String DFA_TAG = "DFA";

   public static final String DFA_STATE_TAG = "State";

   public static final String TRANSITION_TAG = "Transition";

   public static final String ASSIGNMENT_TAG = "Assignment";

   public static final String START_STATE_TAG = "StartState";

   public static final String REJECTING_STATE_TAG = "RejectingState";

   public static final String PERMITTED_METHOD_CALL_SYMBOL_TAG = "PermittedMethodCallSymbol";

   public static final String PROHIBITED_METHOD_CALL_SYMBOL_TAG = "ProhibitedMethodCallSymbol";

   public static final String PROHIBITED_CALLER_SYMBOL_TAG = "ProhibitedCallerSymbol";

   public static final String CALLER_TAG = "Caller";

   public static final String CALLEE_TAG = "Callee";

   public static final String PERMITTED_CALLER_TAG = "PermittedCaller";

   public static final String TRIGGER_TAG = "Trigger";


   public static final String NAME_ATTRIBUTE = "name";

   public static final String NEGATIVE_ATTRIBUTE = "negative";

   public static final String ID_ATTRIBUTE = "id";

   public static final String TYPE_ATTRIBUTE = "type";

   public static final String TYPE_NAME_ATTRIBUTE = "typeName";

   public static final String CALLER_NAME_ATTRIBUTE = "callerName";

   public static final String CALLER_TYPE_NAME_ATTRIBUTE = "callerTypeName";

   public static final String CALLEE_NAME_ATTRIBUTE = "calleeName";

   public static final String CALLEE_TYPE_NAME_ATTRIBUTE = "calleeTypeName";

   public static final String PREVIOUS_STATE_ID_ATTRIBUTE = "previousStateId";

   public static final String NEXT_STATE_ID_ATTRIBUTE = "nextStateId";

   public static final String METHOD_NAME_ATTRIBUTE = "methodName";

   public static final String SYMBOL_ID_ATTRIBUTE = "symbolId";

   public static final String LEFT_SIDE_ATTRIBUTE = "leftSide";

   public static final String RIGHT_SIDE_ATTRIBUTE = "rightSide";

   public static final String ASSIGNMENT_ID_ATTRIBUTE = "assignmentId";

   public static final String SET_ATTRIBUTE = "set";

   public static final String FOREACH_ATTRIBUTE = "forEach";

}
