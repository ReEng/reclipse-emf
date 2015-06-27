package org.reclipse.tracer.model.tracegraph;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *           0..1   type    0..n 
 *   TGType --------------------- TGArgument
 *           type      arguments 
 *
 *           1     type     1..n 
 *   TGType --------------------- TGObject
 *           type      instances 
 *
 *           0..n   superTypes   0..n 
 *   TGType -------------------------- TGType
 *           superTypes      subTypes 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TGType
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
    *           0..1   type    0..n 
    *   TGType --------------------- TGArgument
    *           type      arguments 
    * </pre>
    */
   private HashSet<TGArgument> arguments;


   public boolean addToArguments(TGArgument value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.arguments == null)
         {
            this.arguments = new HashSet<TGArgument>();
         }
         changed = this.arguments.add(value);
         if (changed)
         {
            value.setType(this);
         }
      }
      return changed;
   }


   public boolean hasInArguments(TGArgument value)
   {
      return ((this.arguments != null) && (value != null) && this.arguments.contains(value));
   }


   public Iterator<TGArgument> iteratorOfArguments()
   {
      return ((this.arguments == null) ? Collections.<TGArgument> emptyList().iterator() : this.arguments.iterator());
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


   public boolean removeFromArguments(TGArgument value)
   {
      boolean changed = false;
      if ((this.arguments != null) && (value != null))
      {
         changed = this.arguments.remove(value);
         if (changed)
         {
            value.setType(null);
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
    *           1     type     1..n 
    *   TGType --------------------- TGObject
    *           type      instances 
    * </pre>
    */
   private HashSet<TGObject> instances;


   public boolean addToInstances(TGObject value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.instances == null)
         {
            this.instances = new HashSet<TGObject>();
         }
         changed = this.instances.add(value);
         if (changed)
         {
            value.setType(this);
         }
      }
      return changed;
   }


   public boolean hasInInstances(TGObject value)
   {
      return ((this.instances != null) && (value != null) && this.instances.contains(value));
   }


   public Iterator<TGObject> iteratorOfInstances()
   {
      return ((this.instances == null) ? Collections.<TGObject> emptyList().iterator() : this.instances.iterator());
   }


   public void removeAllFromInstances()
   {
      TGObject tmpValue;
      Iterator<TGObject> iter = iteratorOfInstances();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         removeFromInstances(tmpValue);
      }
   }


   public boolean removeFromInstances(TGObject value)
   {
      boolean changed = false;
      if ((this.instances != null) && (value != null))
      {
         changed = this.instances.remove(value);
         if (changed)
         {
            value.setType(null);
         }
      }
      return changed;
   }


   public int sizeOfInstances()
   {
      return ((this.instances == null) ? 0 : this.instances.size());
   }


   /**
    * <pre>
    *           0..n   superTypes   0..n 
    *   TGType -------------------------- TGType
    *           superTypes      subTypes 
    * </pre>
    */
   private HashSet<TGType> subTypes;


   public boolean addToSubTypes(TGType value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.subTypes == null)
         {
            this.subTypes = new HashSet<TGType>();
         }
         changed = this.subTypes.add(value);
         if (changed)
         {
            value.addToSuperTypes(this);
         }
      }
      return changed;
   }


   public boolean hasInSubTypes(TGType value)
   {
      return ((this.subTypes != null) && (value != null) && this.subTypes.contains(value));
   }


   public Iterator<TGType> iteratorOfSubTypes()
   {
      return ((this.subTypes == null) ? Collections.<TGType> emptyList().iterator() : this.subTypes.iterator());
   }


   public void removeAllFromSubTypes()
   {
      TGType tmpValue;
      Iterator<TGType> iter = iteratorOfSubTypes();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         removeFromSubTypes(tmpValue);
      }
   }


   public boolean removeFromSubTypes(TGType value)
   {
      boolean changed = false;
      if ((this.subTypes != null) && (value != null))
      {
         changed = this.subTypes.remove(value);
         if (changed)
         {
            value.removeFromSuperTypes(this);
         }
      }
      return changed;
   }


   public int sizeOfSubTypes()
   {
      return ((this.subTypes == null) ? 0 : this.subTypes.size());
   }


   /**
    * <pre>
    *           0..n   superTypes   0..n 
    *   TGType -------------------------- TGType
    *           subTypes      superTypes 
    * </pre>
    */
   private HashSet<TGType> superTypes;


   public boolean addToSuperTypes(TGType value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.superTypes == null)
         {
            this.superTypes = new HashSet<TGType>();
         }
         changed = this.superTypes.add(value);
         if (changed)
         {
            value.addToSubTypes(this);
         }
      }
      return changed;
   }


   public boolean hasInSuperTypes(TGType value)
   {
      return ((this.superTypes != null) && (value != null) && this.superTypes.contains(value));
   }


   public Iterator<TGType> iteratorOfSuperTypes()
   {
      return ((this.superTypes == null) ? Collections.<TGType> emptyList().iterator() : this.superTypes.iterator());
   }


   public void removeAllFromSuperTypes()
   {
      TGType tmpValue;
      Iterator<TGType> iter = iteratorOfSuperTypes();
      while (iter.hasNext())
      {
         tmpValue = iter.next();
         removeFromSuperTypes(tmpValue);
      }
   }


   public boolean removeFromSuperTypes(TGType value)
   {
      boolean changed = false;
      if ((this.superTypes != null) && (value != null))
      {
         changed = this.superTypes.remove(value);
         if (changed)
         {
            value.removeFromSubTypes(this);
         }
      }
      return changed;
   }


   public int sizeOfSuperTypes()
   {
      return ((this.superTypes == null) ? 0 : this.superTypes.size());
   }


   public boolean isCompatibleTo(String className)
   {
      String otherClassName = getName();
      String[] tempArr = otherClassName.split("\\.");
      if (tempArr.length > 0)
      {
         otherClassName = tempArr[tempArr.length - 1];
      }
      if (className.equals(otherClassName))
      {
         return true;
      }

      Iterator<TGType> iter = this.iteratorOfSuperTypes();
      while (iter.hasNext())
      {
         TGType superType = iter.next();
         if (superType.isCompatibleTo(className))
         {
            return true;
         }
      }

      return false;
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return getName();
   }


   public void removeYou()
   {
      removeAllFromInstances();
      removeAllFromSuperTypes();
      removeAllFromSubTypes();
      removeAllFromArguments();
   }

}
