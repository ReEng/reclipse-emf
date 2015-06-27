/**
 * 
 */
package org.reclipse.math.functions;

import java.util.ArrayList;

/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class MathFunctionsManager
{
   private final ArrayList<Class<? extends MathematicalFunction>> allFunctions = new ArrayList<Class<? extends MathematicalFunction>>();

   private final ArrayList<String> allFunctionsNames = new ArrayList<String>();

   private static MathFunctionsManager instance;


   private MathFunctionsManager()
   {
      this.registerMathFunction(LinearFunction.getFunctionName(), LinearFunction.class);
      this.registerMathFunction(Lim0EFunction.getFunctionName(), Lim0EFunction.class);
      this.registerMathFunction(Lim1EFunction.getFunctionName(), Lim1EFunction.class);
      this.registerMathFunction(Lim1EFunctionNOA.getFunctionName(), Lim1EFunctionNOA.class);
      this.registerMathFunction(Lim1EFunctionNOM.getFunctionName(), Lim1EFunctionNOM.class);
      this.registerMathFunction(Lim1EFunctionWLOC.getFunctionName(), Lim1EFunctionWLOC.class);
   }


   public static MathFunctionsManager get()
   {
      if (instance == null)
      {
         instance = new MathFunctionsManager();
      }
      return instance;
   }


   public synchronized void registerMathFunction(String name,
         Class<? extends MathematicalFunction> function)
   {
      if (!this.allFunctions.contains(function))
      {
         this.allFunctions.add(function);
         this.allFunctionsNames.add(name);
      }
   }


   public synchronized ArrayList<Class<? extends MathematicalFunction>> getAllKnownMathFunctions()
   {
      // copy entries to prevent ConcurrentModificationExceptions
      ArrayList<Class<? extends MathematicalFunction>> result =
            new ArrayList<Class<? extends MathematicalFunction>>(this.allFunctions);
      return result;
   }


   public synchronized ArrayList<String> getAllKnownMathFunctionsNames()
   {
      // copy entries to prevent ConcurrentModificationExceptions
      ArrayList<String> result =
            new ArrayList<String>(this.allFunctionsNames);
      return result;
   }
}
