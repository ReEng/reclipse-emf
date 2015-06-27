package org.reclipse.behavior.inference;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


/**
 * This class is a catalog of Behavioral Patterns that can be saved and loaded.
 * 
 * <h2>Associations</h2>
 * 
 * <pre>
 *                            0..1   catalog   0..1 
 * BehavioralPatternsCatalog ----------------------- BehavioralAnalysis
 *                            catalog      analysis
 *                             
 *                            0..1   entries   1..n 
 * BehavioralPatternsCatalog ----------------------- BehavioralPatternEntry
 *                            catalog       entries 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4126 $ $Date: 2009-08-06 15:19:27 +0200 (Do, 06 Aug 2009) $
 */
public class BehavioralPatternsCatalog
{

   /**
    * <pre>
    *                            0..1   catalog   0..1 
    * BehavioralPatternsCatalog ----------------------- BehavioralAnalysis
    *                            catalog      analysis 
    * </pre>
    */
   private BehavioralAnalysis analysis;


   public BehavioralAnalysis getAnalysis()
   {
      return this.analysis;
   }


   public boolean setAnalysis(BehavioralAnalysis value)
   {
      boolean changed = false;
      if (this.analysis != value)
      {
         BehavioralAnalysis oldValue = this.analysis;

         if (this.analysis != null)
         {
            this.analysis = null;
            oldValue.setCatalog(null);
         }
         this.analysis = value;
         if (value != null)
         {
            value.setCatalog(this);
         }
         changed = true;

      }
      return changed;
   }


   /**
    * <pre>
    *                            0..1   entries   1..n 
    * BehavioralPatternsCatalog ----------------------- BehavioralPatternEntry
    *                            catalog       entries 
    * </pre>
    */
   private HashSet<BehavioralPatternEntry> entries;


   public boolean addToEntries(BehavioralPatternEntry value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.entries == null)
         {
            this.entries = new HashSet<BehavioralPatternEntry>();
         }
         changed = this.entries.add(value);
         if (changed)
         {
            value.setCatalog(this);
         }
      }
      return changed;
   }


   public boolean hasInEntries(BehavioralPatternEntry value)
   {
      return ((this.entries != null) && (value != null) && this.entries
            .contains(value));
   }


   public Iterator<BehavioralPatternEntry> iteratorOfEntries()
   {
      return ((this.entries == null) ? Collections.<BehavioralPatternEntry>emptyList().iterator() : this.entries
            .iterator());
   }


   public void removeAllFromEntries()
   {
      this.entries.clear();
//      BehavioralPatternEntry tmpValue;
//      Iterator<BehavioralPatternEntry> iter = iteratorOfEntries();
//      while (iter.hasNext())
//      {
//         tmpValue = (BehavioralPatternEntry) iter.next();
//         removeFromEntries(tmpValue);
//      }
   }


   public boolean removeFromEntries(BehavioralPatternEntry value)
   {
      boolean changed = false;
      if ((this.entries != null) && (value != null))
      {
         changed = this.entries.remove(value);
         if (changed)
         {
            value.setCatalog(null);
         }
      }
      return changed;
   }


   public int sizeOfEntries()
   {
      return ((this.entries == null) ? 0 : this.entries.size());
   }


   public void removeYou()
   {
      removeAllFromEntries();

      BehavioralAnalysis tmpInference = getAnalysis();
      if (tmpInference != null)
      {
         setAnalysis(null);
      }
   }

}
