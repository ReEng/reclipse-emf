package org.reclipse.structure.generator;


import org.eclipse.core.runtime.jobs.ISchedulingRule;


/**
 * Simple mutex rule to prevent the {@link PrepareDetectionEnginesJob} and the
 * {@link org.reclipse.structure.inference.DetectPatternsJob DetectPatternsJob} from running in
 * parallel (the engines have to be prepared before they can be used in the detection).
 * 
 * @see ISchedulingRule
 * 
 * @author mvdetten
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class Mutex implements ISchedulingRule
{
   private static final Mutex instance = new Mutex();


   private Mutex()
   {
   }


   public static Mutex get()
   {
      return instance;
   }


   @Override
   public boolean contains(ISchedulingRule rule)
   {
      return rule == this;
   }


   @Override
   public boolean isConflicting(ISchedulingRule rule)
   {
      return rule == this;
   }

}
