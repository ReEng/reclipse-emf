package org.reclipse.behavior.inference.automaton;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.Trace;
import org.reclipse.tracer.model.tracegraph.TGObject;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *        0..n   tokens   1
 * Token ------------------- DFAState
 *        tokens      state
 * 
 *        0..n   annotation    1
 * Token ------------------------ StructuralAnnotation
 *        tokens      annotation
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4650 $ $Date: 2011-02-28 13:30:50 +0100 (Mo, 28 Feb 2011) $
 */
public class Token extends Trace
{

   private static int counter = 0;


   public Token(StructuralAnnotation annotation)
   {
      setId(counter++);
      setAnnotation(annotation);
   }

   // set of sets for comparison with real set in order to check forEach-Transition
   private HashMap<String, Set<TGObject>> setsForEach = new HashMap<String, Set<TGObject>>();


   public void addToForEach(String name, TGObject object)
   {
      Set<TGObject> set = this.setsForEach.get(name);

      if (set == null)
      {
         set = new HashSet<TGObject>();
         this.setsForEach.put(name, set);
      }

      if (!set.contains(object))
      {
         set.add(object);
      }

   }


   public Set<TGObject> getSetForEach(String key)
   {
      Set<TGObject> setForEach = this.setsForEach.get(key);

      return setForEach != null ? setForEach : EMPTY_SET;
   }


   public boolean checkForEach(String setName)
   {
      Set<TGObject> set1 = getSet(setName); // methodCalls that should be called
      Set<TGObject> set2 = getSetForEach(setName); // actual methodCalls from trace
      if (!setsAreCompatible(set1, set2))
      {
         set2.clear();
         return false;
      }
      set2.clear();
      return true;
   }


   private boolean setsAreCompatible(Set<TGObject> set1, Set<TGObject> set2)
   {
      for (TGObject tgObject : set1)
      {
         boolean compatible = false;
         for (TGObject tgObject2 : set2)
         {
            if(tgObject==tgObject2){
               compatible = true;
            }
         }
         if(!compatible){
            return false;
         }
      }
      return true;
   }

   private HashMap<String, Set<TGObject>> setOfSets = new HashMap<String, Set<TGObject>>();


   public void addToSet(String name, TGObject object)
   {
      Set<TGObject> set = this.setOfSets.get(name);

      if (set == null)
      {
         set = new HashSet<TGObject>();
         this.setOfSets.put(name, set);
      }

      if (!set.contains(object))
      {
         set.add(object);
      }

   }


   public Iterator<TGObject> iteratorOfSets(String name)
   {
      Set<TGObject> set = this.setOfSets.get(name);

      return (set == null) ? Collections.<TGObject>emptyList().iterator() : set.iterator();
   }


   public Set<TGObject> getSet(String key)
   {
      Set<TGObject> set = this.setOfSets.get(key);

      return set != null ? set : EMPTY_SET;
   }


   /**
    * Just for testing purposes.
    */
   /* package */void removeFromSetOfSets(String name)
   {
      this.setOfSets.remove(name);
   }


   public void incrementPassedAcceptingState()
   {
      setPassedAcceptingState(getPassedAcceptingState() + 1);
   }


   private boolean moved = false;


   public boolean isMoved()
   {
      return this.moved;
   }


   public void setMoved(boolean moved)
   {
      this.moved = moved;
   }


   /**
    * <pre>
    *              annotation    1 
    * Token -----------------------> StructuralAnnotation
    *                   annotation 
    * </pre>
    */
   private StructuralAnnotation annotation;


   public StructuralAnnotation getAnnotation()
   {
      return this.annotation;
   }


   public boolean setAnnotation(StructuralAnnotation value)
   {
      boolean changed = false;
      if (this.annotation != value)
      {
         this.annotation = value;
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *        0..n   tokens   1 
    * Token ------------------- DFAState
    *        tokens      state 
    * </pre>
    */
   private DFAState state;


   public DFAState getState()
   {
      return this.state;
   }


   public boolean setState(DFAState value)
   {
      boolean changed = false;
      if (this.state != value)
      {
         DFAState oldValue = this.state;
         if (this.state != null)
         {
            this.state = null;
            oldValue.removeFromTokens(this);
         }
         this.state = value;
         if (value != null)
         {
            value.addToTokens(this);
         }
         changed = true;
      }
      return changed;
   }


   private Map<String, Set<TGObject>> possibleBindings = new HashMap<String, Set<TGObject>>();


   public void addToPossibleBindings(String name, TGObject object)
   {
      Set<TGObject> set = this.possibleBindings.get(name);

      if (set == null)
      {
         set = new HashSet<TGObject>();
         this.possibleBindings.put(name, set);
      }

      set.add(object);
   }


   public Iterator<TGObject> iteratorOfPossibleBindings(String name)
   {
      Set<TGObject> set = this.possibleBindings.get(name);

      return (set == null) ? Collections.<TGObject>emptyList().iterator() : set.iterator();
   }


   private static final Set<TGObject> EMPTY_SET = new HashSet<TGObject>();


   public Set<TGObject> getPossibleBindings(String key)
   {
      Set<TGObject> possibleBindings = this.possibleBindings.get(key);

      return possibleBindings != null ? possibleBindings : EMPTY_SET;
   }


   /* package */void removeFromPossibleBindings(String name)
   {
      this.possibleBindings.remove(name);
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return Integer.toString(getId());
   }


   /**
    * @see org.reclipse.behavior.inference.Trace#removeYou()
    */
   @Override
   public void removeYou()
   {
      DFAState tmpState = getState();
      if (tmpState != null)
      {
         setState(null);
      }

      this.possibleBindings.clear();

      super.removeYou();
   }

   private int setCounter = 0;


   public int getSetCounter()
   {
      return setCounter;
   }
   
   public void incrSetCounter(){
      setCounter++;
   }


   /*
    * changes a binding from oldInstance into newInstance (e.g. because of an assignment)  
    */
   public void changeBinding(String oldName, String newName, String newID)
   {
      TGObject oldInstance = getFromBindings(oldName);
      TGObject newInstance = new TGObject();
      newInstance.setId(newID);
      newInstance.setOwningTracePath(oldInstance.getOwningTracePath());
      newInstance.setType(oldInstance.getType());
      
      removeFromBindings(oldName);
      addToBindings(newName, newInstance);
      
   }

}
