package org.reclipse.behavior.inference.automaton.symbols;


import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.automaton.AbstractSymbol;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
public abstract class AbstractMethodCallSymbol extends AbstractSymbol
{

   private String methodName;


   public String getMethodName()
   {
      return this.methodName;
   }


   public void setMethodName(String value)
   {
      if (this.methodName != value)
      {
         this.methodName = value;
      }
   }


   /**
    * <pre>
    *                                 callee       1 
    *  AbstractMethodCallSymbol --------------------> MethodCallObject
    *                                         callee 
    * </pre>
    */
   private MethodCallObject callee;


   public MethodCallObject getCallee()
   {
      return this.callee;
   }


   public boolean setCallee(MethodCallObject value)
   {
      boolean changed = false;
      if (this.callee != value)
      {
         this.callee = value;
         changed = true;
      }
      return changed;
   }


   /**
    * Checks if the correct method is called.
    * 
    * @see org.reclipse.behavior.inference.automaton.AbstractSymbol#accept(org.reclipse.tracer.model.tracegraph.TGMethodCall,
    *      org.reclipse.behavior.inference.automaton.Token)
    */
   @Override
   public boolean accept(TGMethodCall methodCall, Token token)
   {
      StructuralAnnotation annotation = token.getAnnotation();

      // check if methodCall matches the symbol
      // check the method signature first
      String expectedSignature = annotation.getFromNodes(getMethodName().split("\\(")[0]);
      String actualSignature = methodCall.getSignature().split("\\(")[0];

      // Fixme: This is a workaround to correctly consider occurrences of the type
      // java.lang.Throwable in method signatures. The expected signature lacks the package name in
      // front of native Java classes like "Throwable" or "String". This leads to false rejection of
      // method calls at this point.
      if (expectedSignature.contains("Throwable") && !expectedSignature.contains("java.lang.Throwable"))
      {
         expectedSignature = expectedSignature.replace("Throwable", "java.lang.Throwable");
      }

      if (!actualSignature.equals(expectedSignature))
      {
         return false;
      }

      // check the callee's type
      String expectedCalleeType = annotation.getFromNodes(getCallee().getTypeName());
      TGType actualCalleeType = methodCall.getCallee().getType();
      if (!actualCalleeType.isCompatibleTo(expectedCalleeType))
      {
         return false;
      }

      return true;
   }


   /**
    * @see org.reclipse.behavior.inference.automaton.AbstractSymbol#removeYou()
    */
   @Override
   public void removeYou()
   {
      MethodCallObject tmpCallee = getCallee();
      if (tmpCallee != null)
      {
         setCallee(null);
      }

      super.removeYou();
   }

}
