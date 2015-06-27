/**
 * 
 */
package org.reclipse.structure.inference.strategy;

import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.specification.PSPatternSpecification;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class ContextPatternPair
{
   private final EObject context;

   private final PSPatternSpecification pattern;


   public ContextPatternPair(EObject context, PSPatternSpecification rule)
   {
      this.context = context;
      this.pattern = rule;
      this.marked = false;
   }


   public EObject getContext()
   {
      return this.context;
   }


   public PSPatternSpecification getPattern()
   {
      return this.pattern;
   }


   private boolean marked;


   public boolean isMarked()
   {
      return this.marked;
   }


   public void setMarked(boolean marked)
   {
      this.marked = marked;
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "(" + getContext() + ", " + getPattern().getName() + ")";
   }
}
