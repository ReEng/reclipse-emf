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
 *                        0..n        methodCalls         0..n 
 *  TGSequenceAnnotation -------------------------------------- TGMethodCall
 *                        sequences   {ordered}    methodCalls 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TGSequenceAnnotation
{
   
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


   /**
    * <pre>
    *                        0..n        methodCalls         0..n 
    *  TGSequenceAnnotation -------------------------------------- TGMethodCall
    *                        sequences   {ordered}    methodCalls 
    * </pre>
    */
   private LinkedList<TGMethodCall> methodCalls;


   public boolean addToMethodCalls(TGMethodCall value)
   {
      boolean changed = false;
      if (value != null && !hasInMethodCalls(value))
      {
         if (this.methodCalls == null)
         {
            this.methodCalls = new LinkedList<TGMethodCall>();
         }
         changed = this.methodCalls.add(value);
         if (changed)
         {
            value.addToSequences(this);
         }
      }
      return changed;
   }


   public boolean hasInMethodCalls(TGMethodCall value)
   {
      return ((this.methodCalls != null) && (value != null) && this.methodCalls
            .contains(value));
   }


   public Iterator<TGMethodCall> iteratorOfMethodCalls()
   {
      return ((this.methodCalls == null) ? Collections.<TGMethodCall>emptyList().iterator()
            : this.methodCalls.listIterator());
   }


   public int sizeOfMethodCalls()
   {
      return ((this.methodCalls == null) ? 0 : this.methodCalls.size());
   }


   public boolean removeFromMethodCalls(TGMethodCall value)
   {
      boolean changed = false;
      if ((this.methodCalls != null) && (value != null))
      {

         changed = this.methodCalls.remove(value);
         if (changed)
         {
            value.removeFromSequences(this);
         }

      }
      return changed;
   }


   public void removeAllFromMethodCalls()
   {
      TGMethodCall tmpValue;
      Iterator<TGMethodCall> iter = this.iteratorOfMethodCalls();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         this.removeFromMethodCalls(tmpValue);
      }
   }


   public void removeYou()
   {
      removeAllFromMethodCalls();
   }

}
