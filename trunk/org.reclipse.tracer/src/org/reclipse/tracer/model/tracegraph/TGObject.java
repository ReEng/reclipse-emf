package org.reclipse.tracer.model.tracegraph;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.reclipse.tracer.model.definition.CallerClass;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *            0..1   object    0..n 
 *  TGObject ----------------------- TGObjectArgument
 *            object      arguments 
 *
 *            1    callee    0..* 
 *  TGObject --------------------- TGMethodCall
 *            callee      callees 
 *
 *            1    caller    0..* 
 *  TGObject --------------------- TGMethodCall
 *            caller      callers
 *             
 *            0..*      contains       1 ------
 *  TGObject ----------------------------| id | TGTracePath
 *            objects      owningTracePath ------
 *
 *            1..n     type     1 
 *  TGObject --------------------- TGType
 *            instances      type 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TGObject
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


   /**
    * <pre>
    *            0..1   object    0..n 
    *  TGObject ----------------------- TGObjectArgument
    *            object      arguments 
    * </pre>
    */
   private HashSet<TGObjectArgument> arguments;


   public boolean addToArguments(TGObjectArgument value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.arguments == null)
         {
            this.arguments = new HashSet<TGObjectArgument>();
         }
         changed = this.arguments.add(value);
         if (changed)
         {
            value.setObject(this);
         }
      }
      return changed;
   }


   public boolean hasInArguments(TGObjectArgument value)
   {
      return ((this.arguments != null) && (value != null) && this.arguments
            .contains(value));
   }


   public Iterator<TGObjectArgument> iteratorOfArguments()
   {
      return ((this.arguments == null) ? Collections.<TGObjectArgument>emptySet().iterator() : this.arguments
            .iterator());
   }


   public void removeAllFromArguments()
   {
      TGObjectArgument tmpValue;
      Iterator<TGObjectArgument> iter = this.iteratorOfArguments();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         this.removeFromArguments(tmpValue);
      }
   }


   public boolean removeFromArguments(TGObjectArgument value)
   {
      boolean changed = false;
      if ((this.arguments != null) && (value != null))
      {
         changed = this.arguments.remove(value);
         if (changed)
         {
            value.setObject(null);
         }
      }
      return changed;
   }


   public int sizeOfArguments()
   {
      return ((this.arguments == null) ? 0 : this.arguments.size());
   }


   /**
    * <pre>
    *            1    callee    0..* 
    *  TGObject --------------------- TGMethodCall
    *            callee      callees 
    * </pre>
    */
   private HashSet<TGMethodCall> callees;


   public boolean addToCallees(TGMethodCall value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.callees == null)
         {
            this.callees = new HashSet<TGMethodCall>();
         }
         changed = this.callees.add(value);
         if (changed)
         {
            value.setCallee(this);
         }
      }
      return changed;
   }


   public boolean hasInCallees(TGMethodCall value)
   {
      return ((this.callees != null) && (value != null) && this.callees
            .contains(value));
   }


   public Iterator<TGMethodCall> iteratorOfCallees()
   {
      return ((this.callees == null) ? Collections.<TGMethodCall>emptyList().iterator() : this.callees
            .iterator());
   }


   public void removeAllFromCallees()
   {
      TGMethodCall tmpValue;
      Iterator<TGMethodCall> iter = this.iteratorOfCallees();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         this.removeFromCallees(tmpValue);
      }
   }


   public boolean removeFromCallees(TGMethodCall value)
   {
      boolean changed = false;
      if ((this.callees != null) && (value != null))
      {
         changed = this.callees.remove(value);
         if (changed)
         {
            value.setCallee(null);
         }
      }
      return changed;
   }


   public int sizeOfCallees()
   {
      return ((this.callees == null) ? 0 : this.callees.size());
   }


   /**
    * <pre>
    *            1    caller    0..* 
    *  TGObject --------------------- TGMethodCall
    *            caller      callers 
    * </pre>
    */
   private HashSet<TGMethodCall> callers;


   public boolean addToCallers(TGMethodCall value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.callers == null)
         {
            this.callers = new HashSet<TGMethodCall>();
         }
         changed = this.callers.add(value);
         if (changed)
         {
            value.setCaller(this);
         }
      }
      return changed;
   }


   public boolean hasInCallers(TGMethodCall value)
   {
      return ((this.callers != null) && (value != null) && this.callers
            .contains(value));
   }


   public Iterator<TGMethodCall> iteratorOfCallers()
   {
      return ((this.callers == null) ? Collections.<TGMethodCall>emptyList().iterator() : this.callers
            .iterator());
   }


   public void removeAllFromCallers()
   {
      TGMethodCall tmpValue;
      Iterator<TGMethodCall> iter = this.iteratorOfCallers();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         this.removeFromCallers(tmpValue);
      }
   }


   public boolean removeFromCallers(TGMethodCall value)
   {
      boolean changed = false;
      if ((this.callers != null) && (value != null))
      {
         changed = this.callers.remove(value);
         if (changed)
         {
            value.setCaller(null);
         }
      }
      return changed;
   }


   public int sizeOfCallers()
   {
      return ((this.callers == null) ? 0 : this.callers.size());
   }


   /**
    * <pre>
    *            0..*      contains       1 ------
    *  TGObject ----------------------------| id | TGTracePath
    *            objects      owningTracePath ------
    * </pre>
    */
   private TGTracePath owningTracePath;


   public TGTracePath getOwningTracePath()
   {
      return this.owningTracePath;
   }


   public boolean setOwningTracePath(TGTracePath value)
   {
      boolean changed = false;
      if (this.owningTracePath != value)
      {
         TGTracePath oldValue = this.owningTracePath;
         if (this.owningTracePath != null)
         {
            this.owningTracePath = null;
            oldValue.removeFromObjects(this);
         }
         this.owningTracePath = value;
         if (value != null)
         {
            value.addToObjects(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *            1..n     type     1 
    *  TGObject --------------------- TGType
    *            instances      type 
    * </pre>
    */
   private TGType type;


   public TGType getType()
   {
      return this.type;
   }


   public boolean setType(TGType value)
   {
      boolean changed = false;
      if (this.type != value)
      {
         TGType oldValue = this.type;
         if (this.type != null)
         {
            this.type = null;
            oldValue.removeFromInstances(this);
         }
         this.type = value;
         if (value != null)
         {
            value.addToInstances(this);
         }
         changed = true;
      }
      return changed;
   }


   public boolean isInstanceOf(String className)
   {
      return this.getType().isCompatibleTo(className);
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append(getId());
      buffer.append(":");
      buffer.append(getType().getName());

      return buffer.toString();
   }


   public void removeYou()
   {
      removeAllFromArguments();
      removeAllFromCallees();
      removeAllFromCallers();

      TGTracePath tmpOwningTracePath = getOwningTracePath();
      if (tmpOwningTracePath != null)
      {
         setOwningTracePath(null);
      }

      TGType tmpType = getType();
      if (tmpType != null)
      {
         setType(null);
      }
   }

}
