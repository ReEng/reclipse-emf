package org.reclipse.behavior.inference.automaton.symbols;


import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class ProhibitedMethodCallSymbol extends AbstractMethodCallSymbol
{

   /**
    * @see org.reclipse.behavior.inference.automaton.symbols.AbstractMethodCallSymbol#accept(org.reclipse.tracer.model.tracegraph.TGMethodCall,
    *      org.reclipse.behavior.inference.automaton.Token)
    */
   @Override
   public boolean accept(TGMethodCall methodCall, Token token)
   {
      // check if the correct method is called
      if (!super.accept(methodCall, token))
      {
         return false;
      }

      // check the callee object binding
      TGObject actualCallee = methodCall.getCallee();
      String calleeName = getCallee().getName();
      TGObject expectedCallee = token.getFromBindings(calleeName);
      if (expectedCallee != null)
      {
         if (actualCallee == expectedCallee)
         {
            // callee is already bound and the actualCallee is the
            // expected callee, so this is a prohibited method call
            return true;
         }
      }
      else
      {
         // callee is not yet bound
         // remember this possible binding to reject later,
         // if the callee will be bound
         token.addToPossibleBindings(calleeName, actualCallee);
      }

      return false;
   }


   private String symbol;


   /**
    * @see org.reclipse.behavior.inference.automaton.AbstractSymbol#getSymbolText()
    */
   @Override
   public String getSymbolText()
   {
      if (this.symbol == null)
      {
         StringBuffer buffer = new StringBuffer();
         buffer.append("*->");
         buffer.append(getCallee());
         buffer.append(".");
         buffer.append(getMethodName());

         this.symbol = buffer.toString();
      }

      return this.symbol;
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
