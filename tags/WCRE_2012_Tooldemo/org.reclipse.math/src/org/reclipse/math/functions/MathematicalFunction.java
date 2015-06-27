/**
 * 
 */
package org.reclipse.math.functions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class MathematicalFunction
{   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public abstract double value(double x);
   
   /**
    * <pre>
    *                      ------------- 1    params     0..1 
    * MathematicalFunction | paramName |---------------------- FunctionParameter
    *                      ------------- function      params 
    * </pre>
    */
   private HashMap<String, FunctionParameter> params = new HashMap<String, FunctionParameter>();
   
   public static String PROPERTY_PARAMS = "params";

   public boolean addToParams(Map.Entry<String, FunctionParameter> entry)
   {
      return addToParams ((String) entry.getKey (), (FunctionParameter) entry.getValue ());
   }

   public boolean addToParams(String key, FunctionParameter value)
   {
      boolean changed = false;
      if (key != null)
      {
         if (this.params == null)
         {
            this.params = new HashMap <String, FunctionParameter>();
         }
         
         FunctionParameter oldValue = this.params.put (key, value);
         if (oldValue != value)
         {
            if (oldValue != null)
            {
               oldValue.setFunction (null, null);
            }
            if (value != null)
            {
               value.setFunction (key, this);
            }
            changed = true;
         }
         
      }
      return changed;
   }

   public Iterator<Map.Entry<String, FunctionParameter>> entriesOfParams()
   {
      return this.params.entrySet().iterator ();
   }

   public FunctionParameter getFromParams(String key)
   {
      return ((key == null)
              ? null
              : this.params.get (key));
   }

   public boolean hasInParams(String key, FunctionParameter value)
   {
      return ((value != null || this.params.containsKey (key)) && 
              (key != null) &&
              (this.params.get (key) == value));
   }

   public boolean hasInParams(FunctionParameter value)
   {
      return this.params.containsValue(value);
   }

   public boolean hasKeyInParams(String key)
   {
      return ((key != null) &&
              this.params.containsKey(key));
   }

   public Iterator<? extends Object> iteratorOfParams()
   {
      return this.params.values().iterator();
   }

   public Iterator<? extends Object> keysOfParams()
   {
      return this.params.keySet().iterator();
   }

   public void removeAllFromParams()
   {
      Iterator<Map.Entry<String, FunctionParameter>> iter = entriesOfParams ();
      Map.Entry<String, FunctionParameter> entry;
      while (iter.hasNext ())
      {
         entry = iter.next();
         removeFromParams ((String) entry.getKey (), (FunctionParameter) entry.getValue ());
      }
   }

   public boolean removeFromParams(String key, FunctionParameter value)
   {
      boolean changed = false;
      if ((this.params != null) && (key != null))
      {
         FunctionParameter oldValue = this.params.get (key);
         if (oldValue == value && 
             (oldValue != null || this.params.containsKey (key)))
         {
            
            this.params.remove (key);
            if (value != null)
            {
               value.setFunction (null, null);
            }
            changed = true;
            
         }
      }
      return changed;
   }

   public boolean removeFromParams(FunctionParameter value)
   {
      boolean changed = false;
      if (this.params != null)
      {
         Iterator<Map.Entry<String, FunctionParameter>> iter = this.entriesOfParams ();
         Map.Entry<String, FunctionParameter> entry;
         while (iter.hasNext ())
         {
            entry = iter.next ();
            if (entry.getValue () == value)
            {
               
               if (this.removeFromParams ((String) entry.getKey (), value))
               {
                  changed = true;
               }
               
            }
         }
      }
      return changed;
   }

   public boolean removeKeyFromParams(String key)
   {
      boolean changed = false;
      if ((this.params != null) && (key != null))
      {
         changed = this.params.containsKey (key);
         if (changed)
         {
            
            FunctionParameter tmpValue = this.params.remove (key);
            if (tmpValue != null)
            {
               tmpValue.setFunction (null, null);
            }
            
         }
      }
      return changed;
   }

   public int sizeOfParams()
   {
      return ((this.params == null)
              ? 0
              : this.params.size ());
   }
}
