package org.reclipse.behavior.inference;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *             matchedCalls     0..n 
 * Trace ----------------------------> TGMethodCall
 *          {ordered}   matchedCalls 
 * </pre>
 * 
 * @author Lothar
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4345 $ $Date: 2010-05-17 11:55:02 +0200 (Mo, 17 Mai 2010) $
 */
public class Trace
{

   public static final int NOT_ACCEPTED = 0;

   public static final int ACCEPTED = 1;

   public static final int REJECTED = 2;


   private int id;


   public int getId()
   {
      return this.id;
   }


   public void setId(int value)
   {
      if (this.id != value)
      {
         this.id = value;
      }
   }


   private int passedAcceptingState;


   public int getPassedAcceptingState()
   {
      return this.passedAcceptingState;
   }


   public void setPassedAcceptingState(int value)
   {
      if (this.passedAcceptingState != value)
      {
         this.passedAcceptingState = value;
      }
   }


   private int lengthOfAcceptedTrace = 0;


   public int getLengthOfAcceptedTrace()
   {
      return this.lengthOfAcceptedTrace;
   }


   public void setLengthOfAcceptedTrace(int length)
   {
      this.lengthOfAcceptedTrace = length;
   }


   private int result = NOT_ACCEPTED;


   public int getResult()
   {
      return this.result;
   }


   public void setResult(int value)
   {
      if (this.result != value)
      {
         this.result = value;
      }
   }


   private Map<String, TGObject> bindings = new HashMap<String, TGObject>();


   public void addToBindings(String name, TGObject object)
   {
      this.bindings.put(name, object);
   }


   /**
    * Just for testing purposes.
    */
   public void removeFromBindings(String name)
   {
      this.bindings.remove(name);
   }


   public TGObject getFromBindings(String name)
   {
      return this.bindings.get(name);
   }


   public Set<String> keysOfBindings()
   {
      return this.bindings.keySet();
   }


   public Set<Entry<String, TGObject>> entrySetOfBindings()
   {
      return this.bindings.entrySet();
   }


   /**
    * <pre>
    *             matchedCalls     0..n 
    * Trace ----------------------------> TGMethodCall
    *          {ordered}   matchedCalls 
    * </pre>
    */
   private LinkedList<TGMethodCall> matchedCalls;


   public boolean addToMatchedCalls(TGMethodCall value)
   {
      boolean changed = false;
      if (value != null && !hasInMatchedCalls(value))
      {
         if (this.matchedCalls == null)
         {
            this.matchedCalls = new LinkedList<TGMethodCall>();
         }
         changed = this.matchedCalls.add(value);
      }
      return changed;
   }


   public boolean hasInMatchedCalls(TGMethodCall value)
   {
      return ((this.matchedCalls != null) && (value != null) && this.matchedCalls
            .contains(value));
   }


   public Iterator<TGMethodCall> iteratorOfMatchedCalls()
   {
      return ((this.matchedCalls == null) ? Collections.<TGMethodCall>emptyList().iterator()
            : this.matchedCalls.listIterator());
   }


   public int sizeOfMatchedCalls()
   {
      return ((this.matchedCalls == null) ? 0 : this.matchedCalls.size()+1);
   }


   public boolean removeFromMatchedCalls(TGMethodCall value)
   {
      boolean changed = false;
      if ((this.matchedCalls != null) && (value != null))
      {
         changed = this.matchedCalls.remove(value);
      }
      return changed;
   }


   public void removeAllFromMatchedCalls()
   {
      if (this.matchedCalls != null && this.matchedCalls.size() > 0)
      {
         this.matchedCalls.clear();
      }
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Trace " + getId();
   }


   public void removeYou()
   {
      this.bindings.clear();

      removeAllFromMatchedCalls();
   }

}
