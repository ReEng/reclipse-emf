package org.reclipse.behavior.inference.automaton.symbols;


import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * <h2>Associations</h2>
 * 
 * <pre>
 *                                  caller      1 
 * PermittedMethodCallSymbol --------------------> MethodCallObject
 *                                         caller 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class PermittedMethodCallSymbol extends AbstractMethodCallSymbol
{

   /**
    * <pre>
    *                                  caller      1 
    * PermittedMethodCallSymbol --------------------> MethodCallObject
    *                                         caller 
    * </pre>
    */
   private MethodCallObject caller;


   public MethodCallObject getCaller()
   {
      return this.caller;
   }


   public boolean setCaller(MethodCallObject value)
   {
      boolean changed = false;
      if (this.caller != value)
      {
         this.caller = value;
         changed = true;
      }
      return changed;
   }


   /**
    * @see org.reclipse.behavior.inference.automaton.symbols.AbstractMethodCallSymbol#accept(org.reclipse.tracer.model.tracegraph.TGMethodCall,
    *      org.reclipse.behavior.inference.automaton.Token)
    */
   @Override
   public boolean accept(TGMethodCall methodCall, Token token)
   {
      StructuralAnnotation annotation = token.getAnnotation();

      // check if the correct method is called
      if (!super.accept(methodCall, token))
      {
         return false;
      }
      
      TGObject actualCallee = methodCall.getCallee();
      String calleeName = getCallee().getName();
      TGObject actualCaller = methodCall.getCaller();
      String callerName = getCaller().getName();
      
      //check self call property
      if(calleeName.equals("other") || callerName.equals("other")){
         if(actualCallee.equals(actualCaller)){
            return false; 
         }
      }
      
      // check the caller's type
      // if the caller type's mapping is null, the caller's type can be ignored
      if (getCaller().getTypeName() != null)
      {
         String expectedCallerType = annotation.getFromNodes(getCaller()
               .getTypeName());
         TGType actualCallerType = methodCall.getCaller().getType();
         if (!actualCallerType.isCompatibleTo(expectedCallerType))
         {
            return false;
         }
      }

      // check if the callee is already bound
      TGObject expectedCallee = token.getFromBindings(calleeName);
      if (expectedCallee != null)
      {
         // callee already bound
         if (actualCallee != expectedCallee)
         {
            if (getCallee() instanceof MethodCallSetObject)
            {
               if (!((MethodCallSetObject) getCallee()).isForeach())
               {
                  calleeName = calleeName + token.getSetCounter();
                  token.incrSetCounter();
                  expectedCallee = null;
               }
            }
            else
            {
               return false;
            }
         }
      }

      // check if the caller is already bound
      TGObject expectedCaller = token.getFromBindings(callerName);
      if (expectedCaller != null)
      {
         // caller already bound
         if (actualCaller != expectedCaller)
         {
            if (getCaller() instanceof MethodCallSetObject)
            {
               if (!((MethodCallSetObject) getCaller()).isForeach())
               {
                  callerName = callerName + token.getSetCounter();
                  token.incrSetCounter();
                  expectedCaller = null;
               }
            }
            else
            {
               return false;
            }
         }
      }

      // check isomorphic binding
      if (!calleeName.equals(callerName) && actualCallee == actualCaller)
      {
         // no isomorphic binding
         return false;
      }

      // everything's fine, bind unbound variables
      if (expectedCallee == null)
      {
         // check isomorphic binding
         for (String key : token.keysOfBindings())
         {
            if (!key.equals(calleeName)
                  && token.getFromBindings(key) == actualCallee)
            {
               // actualCallee is already bound to a different variable
               return false;
            }
         }
         token.addToBindings(calleeName, actualCallee);
      }
      if (expectedCaller == null)
      {
         // check isomorphic binding
         for (String key : token.keysOfBindings())
         {
            if (!key.equals(callerName)
                  && token.getFromBindings(key) == actualCaller)
            {
               // actualCaller is already bound to a different variable
               return false;
            }
         }
         token.addToBindings(callerName, actualCaller);
      }

      return true;
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
         buffer.append(getCaller());
         buffer.append("->");
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


   /**
    * @see org.reclipse.behavior.inference.automaton.AbstractSymbol#removeYou()
    */
   @Override
   public void removeYou()
   {
      MethodCallObject tmpCaller = getCaller();
      if (tmpCaller != null)
      {
         setCaller(null);
      }

      super.removeYou();
   }


}
