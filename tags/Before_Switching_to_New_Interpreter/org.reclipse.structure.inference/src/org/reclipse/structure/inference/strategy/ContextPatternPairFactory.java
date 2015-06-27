/**
 * 
 */
package org.reclipse.structure.inference.strategy;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.specification.PSPatternSpecification;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class ContextPatternPairFactory
{
   private final Map<EObject, Map<PSPatternSpecification, ContextPatternPair>> contextRulePairs;


   public ContextPatternPairFactory()
   {
      this.contextRulePairs = new HashMap<EObject, Map<PSPatternSpecification, ContextPatternPair>>();
   }


   public ContextPatternPair get(EObject context, PSPatternSpecification rule)
   {
      Map<PSPatternSpecification, ContextPatternPair> predicateMap = this.contextRulePairs
            .get(context);
      if (predicateMap == null)
      {
         predicateMap = new HashMap<PSPatternSpecification, ContextPatternPair>();
         this.contextRulePairs.put(context, predicateMap);
      }

      return predicateMap.get(rule);
   }


   public ContextPatternPair create(EObject context, PSPatternSpecification rule)
   {
      if (contains(context, rule))
      {
         throw new IllegalArgumentException("There is already a context/rule"
               + " pair for (" + context + ", " + rule + ")!");
      }

      Map<PSPatternSpecification, ContextPatternPair> predicateMap = this.contextRulePairs
            .get(context);
      if (predicateMap == null)
      {
         predicateMap = new HashMap<PSPatternSpecification, ContextPatternPair>();
         this.contextRulePairs.put(context, predicateMap);
      }

      ContextPatternPair contextRulePair = new ContextPatternPair(context, rule);
      predicateMap.put(rule, contextRulePair);

      return contextRulePair;
   }


   public boolean contains(EObject context, PSPatternSpecification rule)
   {
      Map<PSPatternSpecification, ContextPatternPair> predicateMap = this.contextRulePairs
            .get(context);

      return predicateMap != null ? predicateMap.containsKey(rule) : false;
   }


   public void clear()
   {
      this.contextRulePairs.clear();
   }
}
