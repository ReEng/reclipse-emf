package org.reclipse.structure.specification.ui.utils;


import org.reclipse.math.functions.FunctionParameter;
import org.reclipse.math.functions.MathematicalFunction;
import org.reclipse.structure.specification.PSFunctionParameter;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.ui.PSPlugin;


/**
 * Simply helps to recreate some function corresponding to a fuzzy constraint.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class MathFunctionHelper
{
   public static MathematicalFunction getMathematicalFunction(
         PSFuzzyConstraint constraint)
   {
      if (constraint == null || constraint.getMathFunctionID() == null
            || constraint.getMathFunctionID().length() < 1)
      {
         return null;
      }

      MathematicalFunction function = null;
      String fClassName = constraint.getMathFunctionID();
      try
      {
         Class<?> fClass = Class.forName(fClassName);

         Object obj = fClass.newInstance();
         if (obj != null && obj instanceof MathematicalFunction)
         {
            function = (MathematicalFunction) obj;
         }
      }
      catch (ClassNotFoundException e)
      {
         PSPlugin.getDefault()
               .logError(
                     "Could not load mathematical function class " + fClassName
                           + ".", e);
      }
      catch (InstantiationException e)
      {
         PSPlugin.getDefault().logError(
               "Could not instantiate mathematical function class "
                     + fClassName + ".", e);
      }
      catch (IllegalAccessException e)
      {
         PSPlugin.getDefault().logError(
               "Access denied during instantiation of mathematical function class "
                     + fClassName + ".", e);
      }

      if (function != null)
      {
         // set parameters
         for (PSFunctionParameter param : constraint.getParameters())
         {
            FunctionParameter p = new FunctionParameter();
            p.setName(param.getName());
            p.setValue(param.getValue());
            function.addToParams(param.getName(), p);
         }
      }
      return function;
   }
}
