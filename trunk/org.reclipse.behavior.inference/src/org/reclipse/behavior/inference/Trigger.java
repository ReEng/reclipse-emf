package org.reclipse.behavior.inference;


import java.util.Iterator;

import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *          0..n   triggers   0..1 
 * Trigger ------------------------ BehavioralAnalysis
 *          triggers      analysis 
 *          
 *          1     trigger      1 
 * Trigger ---------------------- BehavioralPatternEntry
 *          trigger      pattern 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class Trigger
{

   private String callerName;


   public void setCallerName(String callerName)
   {
      this.callerName = callerName;
   }


   public String getCallerName()
   {
      return this.callerName;
   }


   private String callerTypeName;


   public String getCallerTypeName()
   {
      return this.callerTypeName;
   }


   public void setCallerTypeName(String value)
   {
      if (this.callerTypeName != value)
      {
         this.callerTypeName = value;
      }
   }


   private String calleeName;


   public void setCalleeName(String calleeName)
   {
      this.calleeName = calleeName;
   }


   public String getCalleeName()
   {
      return this.calleeName;
   }


   private String calleeTypeName;


   public String getCalleeTypeName()
   {
      return this.calleeTypeName;
   }


   public void setCalleeTypeName(String value)
   {
      if (this.calleeTypeName != value)
      {
         this.calleeTypeName = value;
      }
   }


   private String methodName;


   public String getMethodName()
   {
      return (this.methodName.split("\\("))[0]; // only compare methodname (cut parameters off);
   }


   public void setMethodName(String value)
   {
      if (this.methodName != value)
      {
         this.methodName = value;
      }
   }


   /**
    * <pre>
    *          0..n   triggers    0..1 
    * Trigger ------------------------- BehavioralAnalysis
    *          triggers       analysis 
    * </pre>
    */
   private BehavioralAnalysis analysis;


   public BehavioralAnalysis getAnalysis()
   {
      return this.analysis;
   }


   public boolean setAnalysis(BehavioralAnalysis value)
   {
      boolean changed = false;
      if (this.analysis != value)
      {
         BehavioralAnalysis oldValue = this.analysis;
         if (this.analysis != null)
         {
            this.analysis = null;
            oldValue.removeFromTriggers(this);
         }
         this.analysis = value;
         if (value != null)
         {
            value.addToTriggers(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *          1     trigger      1 
    * Trigger ---------------------- BehavioralPatternEntry
    *          trigger      pattern 
    * </pre>
    */
   private BehavioralPatternEntry pattern;


   public BehavioralPatternEntry getPattern()
   {
      return this.pattern;
   }


   public boolean setPattern(BehavioralPatternEntry value)
   {
      boolean changed = false;
      if (this.pattern != value)
      {
         BehavioralPatternEntry oldValue = this.pattern;
         if (this.pattern != null)
         {
            this.pattern = null;
            oldValue.removeFromTriggers(this);
         }
         this.pattern = value;
         if (value != null)
         {
            value.addToTriggers(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * Checks for all annotations of the pattern if the given method call is a trigger. If the given
    * method call is a trigger, a new state token is created and added to the DFA of the pattern. If
    * the DFA is not within the running DFA's of the dynamic inference, it is added.
    * 
    * @param methodCall
    */
   public void methodCalled(TGMethodCall methodCall)
   {
      // check all annotations of the given pattern
      Iterator iter = getAnalysis().iteratorOfAnnotations(getPattern().getName());
      while (iter.hasNext())
      {
         Annotation annotation = (Annotation) iter.next();
         StructuralAnnotation structuralAnnotation = annotation.getStructuralAnnotation();

         // check if method signature is the same

         String expectedSignature = structuralAnnotation.getFromNodes(getMethodName());
         String actualSignature = methodCall.getSignature().split("\\(")[0];
         if (!actualSignature.equals(expectedSignature))
         {
            continue;
         }

         // check if the callee's class conforms to the trigger's callee type
         String expectedType = structuralAnnotation.getFromNodes(getCalleeTypeName());
         TGType actualType = methodCall.getCallee().getType();
         if (!actualType.isCompatibleTo(expectedType))
         {
            continue;
         }

         // check if the caller's class conforms to the trigger's caller type
         if (getCallerTypeName() != null)
         {
            expectedType = structuralAnnotation.getFromNodes(getCallerTypeName());
            actualType = methodCall.getCaller().getType();
            if (expectedType == null || actualType == null || !actualType.isCompatibleTo(expectedType))
            {
               continue;
            }
         }

         // check isomorphism
         if (!getCallerName().equals(getCalleeName()) && methodCall.getCaller() == methodCall.getCallee())
         {
            continue;
         }

         DFA dfa = getPattern().getAutomaton();

         // check if the DFA is added to the active automatons of the inference
         BehavioralAnalysis inference = getPattern().getCatalog().getAnalysis();
         if (!inference.hasInActiveAutomatons(dfa))
         {
            inference.addToActiveAutomatons(dfa);
         }

         // trigger is activated, create token for the
         // annotation and add it to the DFA's start state
         DFAState startState = (DFAState) dfa.getStartState();
         Token token = new Token(structuralAnnotation);
         startState.addToTokens(token);

         // add token as trace to behavioral annotation
         BehavioralAnnotation behavioralAnnotation = annotation.getBehavioralAnnotation();
         if (behavioralAnnotation == null)
         {
            behavioralAnnotation = new BehavioralAnnotation();
            annotation.setBehavioralAnnotation(behavioralAnnotation);
         }

         behavioralAnnotation.addToTraces(token);
      }
   }


   public void removeYou()
   {
      BehavioralAnalysis tmpInference = getAnalysis();
      if (tmpInference != null)
      {
         setAnalysis(null);
      }

      BehavioralPatternEntry tmpPattern = getPattern();
      if (tmpPattern != null)
      {
         setPattern(null);
      }
   }

}
