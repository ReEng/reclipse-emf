package org.reclipse.tracer.model.tracegraph;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import org.reclipse.tracer.model.definition.CallerClass;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                1          arguments           0..* 
 *  TGMethodCall ------------------------------------- TGArgument
 *                methodCall   {ordered}    arguments 
 *
 *                0..*    callee    1 
 *  TGMethodCall --------------------- TGObject
 *                callees      callee 
 *
 *                0..*    caller    1 
 *  TGMethodCall --------------------- TGObject
 *                callers      caller 
 *
 *                0..1   next   0..1 
 *  TGMethodCall -------------------- TGMethodCall
 *                previous      next 
 *
 *                0..*             contains             1 ------
 *  TGMethodCall -----------------------------------------| id | TGThread
 *                methodCalls   {ordered}    owningThread ------
 *
 *                0..*          contains           1 
 *  TGMethodCall ------------------------------------ TGTracePath
 *                methodCalls   {ordered}    tracePath 
 *
 *                0..n        methodCalls         0..n 
 *  TGMethodCall -------------------------------------- TGSequenceAnnotation
 *                methodCalls   {ordered}    sequences 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TGMethodCall
{

   private String id;


   public String getId()
   {
      return this.id;
   }


   public void setId(String value)
   {
      if (this.id != value)
      {
         this.id = value;
      }
   }


   private String name;


   public String getName()
   {
      return this.name;
   }


   public void setName(String value)
   {
      if (this.name != value)
      {
         this.name = value;
      }
   }

   private long time;


   public long getTime()
   {
      return this.time;
   }


   public void setTime(long value)
   {
      if (this.time != value)
      {
         this.time = value;
      }
   }


   /**
    * <pre>
    *                1          arguments           0..* 
    *  TGMethodCall ------------------------------------- TGArgument
    *                methodCall   {ordered}    arguments 
    * </pre>
    */
   private LinkedList<TGArgument> arguments;


   public boolean addToArguments(TGArgument value)
   {
      boolean changed = false;
      if (value != null && !hasInArguments(value))
      {
         if (this.arguments == null)
         {
            this.arguments = new LinkedList<TGArgument>();
         }
         changed = this.arguments.add(value);
         if (changed)
         {
            value.setMethodCall(this);
         }
      }
      return changed;
   }


   public boolean hasInArguments(TGArgument value)
   {
      return ((this.arguments != null) && (value != null) && this.arguments
            .contains(value));
   }


   public Iterator<TGArgument> iteratorOfArguments()
   {
      return ((this.arguments == null) ? Collections.<TGArgument>emptyList().iterator()
            : this.arguments.listIterator());
   }


   public int sizeOfArguments()
   {
      return ((this.arguments == null) ? 0 : this.arguments.size());
   }


   public boolean removeFromArguments(TGArgument value)
   {
      boolean changed = false;
      if ((this.arguments != null) && (value != null))
      {
         changed = this.arguments.remove(value);
         if (changed)
         {
            value.setMethodCall(null);
         }
      }
      return changed;
   }


   public void removeAllFromArguments()
   {
      TGArgument tmpValue;
      Iterator<TGArgument> iter = iteratorOfArguments();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         removeFromArguments(tmpValue);
      }
   }


   /**
    * <pre>
    *                0..*    callee    1 
    *  TGMethodCall --------------------- TGObject
    *                callees      callee 
    * </pre>
    */
   private TGObject callee;


   public TGObject getCallee()
   {
      return this.callee;
   }


   public boolean setCallee(TGObject value)
   {
      boolean changed = false;
      if (this.callee != value)
      {
         TGObject oldValue = this.callee;
         if (this.callee != null)
         {
            this.callee = null;
            oldValue.removeFromCallees(this);
         }
         this.callee = value;
         if (value != null)
         {
            value.addToCallees(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                0..*    caller    1 
    *  TGMethodCall --------------------- TGObject
    *                callers      caller 
    * </pre>
    */
   private TGObject caller;


   public TGObject getCaller()
   {
      return this.caller;
   }


   public boolean setCaller(TGObject value)
   {
      boolean changed = false;
      if (this.caller != value)
      {
         TGObject oldValue = this.caller;
         if (this.caller != null)
         {
            this.caller = null;
            oldValue.removeFromCallers(this);
         }
         this.caller = value;
         if (value != null)
         {
            value.addToCallers(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                0..1   next   0..1 
    *  TGMethodCall -------------------- TGMethodCall
    *                previous      next 
    * </pre>
    */
   private TGMethodCall next;


   public TGMethodCall getNext()
   {
      return this.next;
   }


   public boolean setNext(TGMethodCall value)
   {
      boolean changed = false;
      if (this.next != value)
      {
         TGMethodCall oldValue = this.next;
         if (this.next != null)
         {
            this.next = null;
            oldValue.setPrevious(null);
         }
         this.next = value;
         if (value != null)
         {
            value.setPrevious(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                0..*             contains             1 ------
    *  TGMethodCall -----------------------------------------| id | TGThread
    *                methodCalls   {ordered}    owningThread ------
    * </pre>
    */
   private TGThread owningThread;


   public TGThread getOwningThread()
   {
      return this.owningThread;
   }


   public boolean setOwningThread(TGThread value)
   {
      boolean changed = false;
      if (this.owningThread != value)
      {
         TGThread oldValue = this.owningThread;
         if (this.owningThread != null)
         {
            this.owningThread = null;
            oldValue.removeFromMethodCalls(this);
         }
         this.owningThread = value;
         if (value != null)
         {
            value.addToMethodCalls(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                0..1   next   0..1 
    *  TGMethodCall -------------------- TGMethodCall
    *                next      previous 
    * </pre>
    */
   private TGMethodCall previous;


   public TGMethodCall getPrevious()
   {
      return this.previous;
   }


   public boolean setPrevious(TGMethodCall value)
   {
      boolean changed = false;
      if (this.previous != value)
      {
         TGMethodCall oldValue = this.previous;
         if (this.previous != null)
         {
            this.previous = null;
            oldValue.setNext(null);
         }
         this.previous = value;
         if (value != null)
         {
            value.setNext(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                0..*          contains           1 
    *  TGMethodCall ------------------------------------ TGTracePath
    *                methodCalls   {ordered}    tracePath 
    * </pre>
    */
   private TGTracePath tracePath;


   public TGTracePath getTracePath()
   {
      return this.tracePath;
   }


   public boolean setTracePath(TGTracePath value)
   {
      boolean changed = false;
      if (this.tracePath != value)
      {
         TGTracePath oldValue = this.tracePath;
         if (this.tracePath != null)
         {
            this.tracePath = null;
            oldValue.removeFromMethodCalls(this);
         }
         this.tracePath = value;
         if (value != null)
         {
            value.addToMethodCalls(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                0..n        methodCalls         0..n 
    *  TGMethodCall -------------------------------------- TGSequenceAnnotation
    *                methodCalls   {ordered}    sequences 
    * </pre>
    */
   private LinkedList<TGSequenceAnnotation> sequences;


   public boolean addToSequences(TGSequenceAnnotation value)
   {
      boolean changed = false;
      if (value != null && !hasInSequences(value))
      {
         if (this.sequences == null)
         {
            this.sequences = new LinkedList<TGSequenceAnnotation>();
         }
         changed = this.sequences.add(value);
         if (changed)
         {
            value.addToMethodCalls(this);
         }
      }
      return changed;
   }


   public boolean hasInSequences(TGSequenceAnnotation value)
   {
      return ((this.sequences != null) && (value != null) && this.sequences
            .contains(value));
   }


   public Iterator<TGSequenceAnnotation> iteratorOfSequences()
   {
      return ((this.sequences == null) ? Collections.<TGSequenceAnnotation>emptyList().iterator()
            : this.sequences.listIterator());
   }


   public int sizeOfSequences()
   {
      return ((this.sequences == null) ? 0 : this.sequences.size());
   }


   public boolean removeFromSequences(TGSequenceAnnotation value)
   {
      boolean changed = false;
      if ((this.sequences != null) && (value != null))
      {
         changed = this.sequences.remove(value);
         if (changed)
         {
            value.removeFromMethodCalls(this);
         }
      }
      return changed;
   }


   public void removeAllFromSequences()
   {
      TGSequenceAnnotation tmpValue;
      Iterator<TGSequenceAnnotation> iter = iteratorOfSequences();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         removeFromSequences(tmpValue);
      }
   }


   private String signature;


   public String getSignature()
   {
      if (this.signature == null)
      {
         StringBuffer buffer = new StringBuffer();
         buffer.append(getName());
         buffer.append("(");

         Iterator<TGArgument> iter = iteratorOfArguments();
         while (iter.hasNext())
         {
            TGArgument argument = iter.next();
            buffer.append(argument.getType().getName());

            if (iter.hasNext())
            {
               buffer.append(", ");
            }
         }

         buffer.append(")");
         this.signature = buffer.toString();
      }

      return this.signature;
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append("(");
      buffer.append(getCaller().toString());
      buffer.append(")->(");
      buffer.append(getCallee().toString());
      buffer.append(").");
      buffer.append(getSignature());

      return buffer.toString();
   }


   public void removeYou()
   {
      TGThread tmpOwningThread = getOwningThread();
      if (tmpOwningThread != null)
      {
         setOwningThread(null);
      }

      TGObject tmpCallee = getCallee();
      if (tmpCallee != null)
      {
         setCallee(null);
      }

      TGObject tmpCaller = getCaller();
      if (tmpCaller != null)
      {
         setCaller(null);
      }

      TGMethodCall tmpPrev = getPrevious();
      if (tmpPrev != null)
      {
         setPrevious(null);
      }

      TGMethodCall tmpNext = getNext();
      if (tmpNext != null)
      {
         setNext(null);
      }

      TGTracePath tmpTracePath = getTracePath();
      if (tmpTracePath != null)
      {
         setTracePath(null);
      }

      removeAllFromArguments();
      removeAllFromSequences();
   }

}
