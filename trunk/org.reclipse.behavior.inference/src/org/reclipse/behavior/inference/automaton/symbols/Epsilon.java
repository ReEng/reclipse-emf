package org.reclipse.behavior.inference.automaton.symbols;


import org.reclipse.behavior.inference.automaton.AbstractSymbol;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class Epsilon extends AbstractSymbol
{

   /**
    * @see org.reclipse.behavior.inference.automaton.AbstractSymbol#accept(org.reclipse.tracer.model.tracegraph.TGMethodCall,
    *      org.reclipse.behavior.inference.automaton.Token)
    */
   @Override
   public boolean accept(TGMethodCall methodCall, Token token)
   {
      throw new UnsupportedOperationException(
            "Epsilon symbol can not accept any input.");
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String getSymbolText()
   {
      return "\u03B5";
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return getSymbolText();
   }

}
