package org.reclipse.structure.inference.strategy;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author travkin
 */
public class BottomUpQueue
{

   private final SortedMap<Integer, List<ContextPatternPair>> queue;

   private BottomUpStrategy strategy;


   public BottomUpQueue(BottomUpStrategy strategy, boolean ascending)
   {
      this.strategy = strategy;
      this.queue = new TreeMap<Integer, List<ContextPatternPair>>(
            new IntegerComparator(ascending));
   }


   public void enqueue(ContextPatternPair pair)
   {
      int rank = strategy.getRank(pair.getPattern());

      List<ContextPatternPair> pairs = this.queue.get(rank);
      if (pairs == null)
      {
         pairs = new LinkedList<ContextPatternPair>();
         this.queue.put(rank, pairs);
      }
      pairs.add(pair);
   }


   public ContextPatternPair dequeue()
   {
      Integer lowestRank = this.queue.firstKey();
      List<ContextPatternPair> contextRulePairsList = this.queue
            .get(lowestRank);
      ContextPatternPair contextRulePair = contextRulePairsList.remove(0);

      if (contextRulePairsList.isEmpty())
      {
         this.queue.remove(lowestRank);
      }

      return contextRulePair;
   }


   public boolean isEmpty()
   {
      return this.queue.isEmpty();
   }


   public int size()
   {
      int size = 0;
      for (Integer rank : this.queue.keySet())
      {
         List<ContextPatternPair> list = this.queue.get(rank);
         size += list.size();
      }

      return size;
   }


   public void clear()
   {
      this.queue.clear();
   }


   class IntegerComparator implements Comparator<Integer>
   {

      private final boolean ascending;


      public IntegerComparator(boolean ascending)
      {
         this.ascending = ascending;
      }


      @Override
      public int compare(Integer a, Integer b)
      {
         if (this.ascending)
         {
            return a.compareTo(b);
         }
         else
         {
            return b.compareTo(a);
         }
      }
   }
}
