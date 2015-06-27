package org.reclipse.behavior.inference;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.Token;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                              traces        0..* 
 * BehavioralAnnotation ---------------------------> Trace
 *                                          traces 
 * </pre>
 * 
 * @author Lothar
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
public class BehavioralAnnotation
{

   private int acceptedTraces;


   public int getAcceptedTraces()
   {
      return this.acceptedTraces;
   }


   public void setAcceptedTraces(int value)
   {
      if (this.acceptedTraces != value)
      {
         this.acceptedTraces = value;
      }
   }


   private float avgLengthAcceptedTraces;


   public float getAvgLengthAcceptedTraces()
   {
      return this.avgLengthAcceptedTraces;
   }


   public void setAvgLengthAcceptedTraces(float value)
   {
      if (this.avgLengthAcceptedTraces != value)
      {
         this.avgLengthAcceptedTraces = value;
      }
   }


   private int notAcceptedTraces;


   public int getNotAcceptedTraces()
   {
      return this.notAcceptedTraces;
   }


   public void setNotAcceptedTraces(int value)
   {
      if (this.notAcceptedTraces != value)
      {
         this.notAcceptedTraces = value;
      }
   }


   private int passedAcceptingStateTraces;


   public int getPassedAcceptingStateTraces()
   {
      return this.passedAcceptingStateTraces;
   }


   public void setPassedAcceptingStateTraces(int value)
   {
      if (this.passedAcceptingStateTraces != value)
      {
         this.passedAcceptingStateTraces = value;
      }
   }


   private int rejectedTraces;


   public int getRejectedTraces()
   {
      return this.rejectedTraces;
   }


   public void setRejectedTraces(int value)
   {
      if (this.rejectedTraces != value)
      {
         this.rejectedTraces = value;
      }
   }

   /**
    * Annotation created by analyzing traces created by a symbolic execution
    */
   private boolean symbolicExecution = false;


   public boolean getSymbolicExecution()
   {
      return this.symbolicExecution;
   }


   public void setSymbolicExecution(boolean symbolicExecution)
   {
      this.symbolicExecution = symbolicExecution;
   }

   public static boolean TRUE_POSITIVE = true;

   public static boolean FALSE_POSITIVE = false;


   public boolean interpretSymbolicTracePaths()
   {
      if (this.rejectedTraces > 0)
         return FALSE_POSITIVE;
      else if (this.acceptedTraces > 0)
         return TRUE_POSITIVE;
      else
         return FALSE_POSITIVE;
   }


   /**
    * <pre>
    *                              traces        0..* 
    * BehavioralAnnotation ---------------------------> Trace
    *                                          traces 
    * </pre>
    */
   private HashSet<Trace> traces;


   public boolean addToTraces(Trace value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.traces == null)
         {
            this.traces = new HashSet<Trace>();
         }
         changed = this.traces.add(value);
      }
      return changed;
   }


   public boolean hasInTraces(Trace value)
   {
      return ((this.traces != null) && (value != null) && this.traces
            .contains(value));
   }


   public Iterator<Trace> iteratorOfTraces()
   {
      return ((this.traces == null) ? Collections.<Trace>emptyList().iterator()
            : this.traces.iterator());
   }


   public void removeAllFromTraces()
   {
      if (this.traces != null && this.traces.size() > 0)
      {
         this.traces.clear();
      }
   }


   public boolean removeFromTraces(Trace value)
   {
      boolean changed = false;
      if ((this.traces != null) && (value != null))
      {
         changed = this.traces.remove(value);
      }
      return changed;
   }


   public int sizeOfTraces()
   {
      return ((this.traces == null) ? 0 : this.traces.size());
   }


   public void evaluate()
   {
      this.acceptedTraces = 0;
      this.passedAcceptingStateTraces = 0;
      this.rejectedTraces = 0;
      this.notAcceptedTraces = 0;
      int sumLengthOfAcceptedTraces = 0;
      int numberOfAcceptedTraces = 0;

      Iterator<Trace> tracesIter = iteratorOfTraces();
      while (tracesIter.hasNext())
      {
         Token token = (Token) tracesIter.next();

         DFAState state = token.getState();
         if (state.getType() == AbstractState.NONE)
         {
            token.setResult(Trace.NOT_ACCEPTED);
            this.notAcceptedTraces++;
         }
         else if (state.getType() == AbstractState.ACCEPT)
         {
            token.setResult(Trace.ACCEPTED);
            this.acceptedTraces++;
         }
         else if (state.getType() == AbstractState.REJECT)
         {
            token.setResult(Trace.REJECTED);
            this.rejectedTraces++;
         }

         if (token.getPassedAcceptingState() > 0)
         {
            if (state.getType() != AbstractState.ACCEPT)
            {
               this.passedAcceptingStateTraces++;
            }
            sumLengthOfAcceptedTraces += token.getLengthOfAcceptedTrace();
            numberOfAcceptedTraces++;
         }
      }

      this.avgLengthAcceptedTraces = 0.0f;
      if (numberOfAcceptedTraces != 0)
      {
         this.avgLengthAcceptedTraces = ((float) sumLengthOfAcceptedTraces)
               / ((float) numberOfAcceptedTraces);
      }
   }


   public void removeYou()
   {
      removeAllFromTraces();
   }

}
