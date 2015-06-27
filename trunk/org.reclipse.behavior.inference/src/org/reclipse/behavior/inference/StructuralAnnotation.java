package org.reclipse.behavior.inference;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3772 $ $Date: 2007-09-06 19:35:23 +0200 (Do, 06 Sep 2007) $
 */
public class StructuralAnnotation implements Comparable<StructuralAnnotation>
{

   private String type;


   public String getType()
   {
      return this.type;
   }


   public void setType(final String type)
   {
      this.type = type;
   }


   private double fuzzyBelief = 0;


   public double getFuzzyBelief()
   {
      return this.fuzzyBelief;
   }


   public void setFuzzyBelief(final double fuzzyBelief)
   {
      this.fuzzyBelief = fuzzyBelief;
   }


   private Map<String, String> nodes = new HashMap<String, String>();


   public void addToNodes(final String key, final String name)
   {
      this.nodes.put(key, name);
   }


   public String getFromNodes(final String key)
   {
      return this.nodes.get(key);
   }


   public Set<String> keysOfNodes()
   {
      return this.nodes.keySet();
   }


   public Set<Entry<String, String>> entrySetOfNodes()
   {
      return this.nodes.entrySet();
   }


   private String nodesToString()
   {
      final StringBuffer buffer = new StringBuffer();
      buffer.append("(");

      final Iterator<String> keysIter = keysOfNodes().iterator();
      while (keysIter.hasNext())
      {
         final String key = keysIter.next();
         final String value = getFromNodes(key);
         buffer.append(key);
         buffer.append("=");
         buffer.append(value);

         if (keysIter.hasNext())
         {
            buffer.append("; ");
         }
      }
      buffer.append(")");

      return buffer.toString();
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      final StringBuffer buffer = new StringBuffer();
      buffer.append(getType());
      buffer.append(" ");
      buffer.append(String.format("%1$.2f", getFuzzyBelief()));
      buffer.append("% ");

      buffer.append(nodesToString());

      return buffer.toString();
   }


   /**
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(final StructuralAnnotation annotation2)
   {
      if (getType().equals(annotation2.getType()))
      {
         if (getFuzzyBelief() == annotation2.getFuzzyBelief())
         {
            return nodesToString().compareTo(annotation2.nodesToString());
         }
         else
         {
            return Double.compare(annotation2.getFuzzyBelief(),
                  getFuzzyBelief());
         }
      }
      else
      {
         return toString().compareTo(annotation2.toString());
      }
   }

}
