package org.reclipse.behavior.inference.automaton.symbols;


import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * This symbol accepts method calls of the given method on the given callee by a caller that is not
 * a member of the given callers.
 * 
 * <h2>Associations</h2>
 * 
 * <pre>
 *                                permittedCallers      n 
 * ProhibitedCallerSymbol -------------------------------> MethodCallObject
 *                                       permittedCallers 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class ProhibitedCallerSymbol extends AbstractMethodCallSymbol
{

   /**
    * <pre>
    *                                permittedCallers      n 
    * ProhibitedCallerSymbol -------------------------------> MethodCallObject
    *                                       permittedCallers 
    * </pre>
    */
   private HashSet<MethodCallObject> permittedCallers;


   public boolean addToPermittedCallers(MethodCallObject value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.permittedCallers == null)
         {
            this.permittedCallers = new HashSet<MethodCallObject>();
         }
         changed = this.permittedCallers.add(value);
      }
      return changed;
   }


   public boolean hasInPermittedCallers(MethodCallObject value)
   {
      return ((this.permittedCallers != null) && (value != null) && this.permittedCallers
            .contains(value));
   }


   public Iterator<MethodCallObject> iteratorOfPermittedCallers()
   {
      return ((this.permittedCallers == null) ? Collections.<MethodCallObject>emptyList().iterator()
            : this.permittedCallers.iterator());
   }


   public int sizeOfPermittedCallers()
   {
      return ((this.permittedCallers == null) ? 0 : this.permittedCallers
            .size());
   }


   public boolean removeFromPermittedCallers(MethodCallObject value)
   {
      boolean changed = false;
      if ((this.permittedCallers != null) && (value != null))
      {
         changed = this.permittedCallers.remove(value);
      }
      return changed;
   }


   public void removeAllFromPermittedCallers()
   {
      if (this.permittedCallers != null && this.permittedCallers.size() > 0)
      {
         this.permittedCallers.clear();
      }
   }


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
         if (actualCallee != expectedCallee)
         {
            // callee is bound but not the same
            return false;
         }
      }

      StructuralAnnotation annotation = token.getAnnotation();

      Iterator<MethodCallObject> iter = iteratorOfPermittedCallers();
      while (iter.hasNext())
      {
         MethodCallObject permittedCaller = iter.next();
         TGObject actualCaller = methodCall.getCaller();
         TGObject expectedCaller = token
               .getFromBindings(permittedCaller.getName());

         // check the caller's type
         // if the caller type's mapping is null, the caller type can be ignored
         if (permittedCaller.getTypeName() != null)
         {
            String expectedCallerTypeName = annotation
                  .getFromNodes(permittedCaller.getTypeName());
            TGType actualCallerType = methodCall.getCaller().getType();
            if (actualCallerType.isCompatibleTo(expectedCallerTypeName))
            {
               // check the caller object binding
               if (expectedCaller == null)
               {
                  return false;
               }
               else if (expectedCaller == actualCaller)
               {
                  // the method was called from a permitted caller
                  return false;
               }
            }
         }
         else
         {
            // check the caller object binding
            if (expectedCaller == null)
            {
               return false;
            }
            else if (expectedCaller == actualCaller)
            {
               // the method was called from a permitted caller
               return false;
            }
         }
      }

      if (expectedCallee == null)
      {
         // callee is not yet bound
         // remember this possible binding to reject later,
         // if the callee will be bound
         token.addToPossibleBindings(calleeName, actualCallee);
         return false;
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
         buffer.append("*/{");

         Iterator<MethodCallObject> iter = iteratorOfPermittedCallers();
         while (iter.hasNext())
         {
            MethodCallObject object = iter.next();
            buffer.append(object);

            if (iter.hasNext())
            {
               buffer.append(", ");
            }
         }
         buffer.append("}->");
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
      removeAllFromPermittedCallers();
      super.removeYou();
   }

}
