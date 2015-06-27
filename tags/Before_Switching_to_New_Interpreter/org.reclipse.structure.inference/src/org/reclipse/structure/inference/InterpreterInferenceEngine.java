package org.reclipse.structure.inference;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;
import org.fujaba.commons.console.IControllableReportListener;
import org.reclipse.interpreter.adapter.extension.ExtendedSDMainAdapterFactory;
import org.reclipse.structure.generator.GeneratorConstants;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.OperationExtension;

import de.mdelab.sdm.interpreter.common.MainInterpreterFactory;
import de.mdelab.sdm.interpreter.common.SDMInterpreter;
import de.mdelab.sdm.interpreter.common.SDMInterpreterException;
import de.mdelab.sdm.interpreter.common.expressions.EclipseBasedExpressionInterpreterFactory;
import de.mdelab.sdm.interpreter.common.tasks.notifying.NotifyingMainInterpreterFactory;
import de.mdelab.sdm.interpreter.common.tasks.notifying.OutputStreamNotificationReceiver;
import de.mdelab.sdm.interpreter.common.variables.Parameter;
import de.mdelab.sdm.interpreter.common.variables.Variable;


public class InterpreterInferenceEngine extends InferenceEngine
{
   private SDMInterpreter interpreter = null;


   public InterpreterInferenceEngine(IControllableReportListener reporter)
   {
      this(reporter, false);
   }


   public InterpreterInferenceEngine(IControllableReportListener reporter, boolean isDebug)
   {
      super(reporter, isDebug);
   }


   @Override
   protected Collection<ASGAnnotation> applyPatternSearch(PSPatternSpecification pattern, EObject context)
   {
      // get engine
      EObject engine = engines.get(pattern);
      if (engine == null)
      {
         String message = "Exception occurred on search for '%1s': Could not find an engine class.";
         warn(String.format(message, pattern.getName()));

         return Collections.emptyList();
      }

      // prepare parameters
      ArrayList<Parameter> params = new ArrayList<Parameter>();
      params.add(new Parameter(GeneratorConstants.PARAMETER_THIS, engine.eClass(), engine));
      params.add(new Parameter(GeneratorConstants.PARAMETER_CONTEXT, context.eClass(), context));
      params.add(new Parameter(GeneratorConstants.PARAMETER_SEARCH_ADDITIONALS, EcorePackage.Literals.EBOOLEAN,
            searchForAdditionalElements));

      Activity activity = getAnnotateActivity(pattern);
      if (activity != null)
      {
         try
         {
            if (isDebug())
            {
               interpreter = new SDMInterpreter(new NotifyingMainInterpreterFactory(
                     new EclipseBasedExpressionInterpreterFactory(), new OutputStreamNotificationReceiver()),
                     new ExtendedSDMainAdapterFactory(), getClass().getClassLoader());
            }
            else
            {
               interpreter = new SDMInterpreter(new MainInterpreterFactory(
                     new EclipseBasedExpressionInterpreterFactory()), new ExtendedSDMainAdapterFactory(), getClass()
                     .getClassLoader());
            }

            Map<String, Variable> executionResult = interpreter.executeActivity(activity, params, 1);
            if (executionResult != null)
            {
               Variable v = executionResult.get("##RETURN_VALUE");

               if (v.getValueAdapter().getValue() instanceof AnnotationSet)
               {
                  return ((AnnotationSet) v.getValueAdapter().getValue()).getAnnotations();
               }
            }
         }
         catch (SDMInterpreterException e)
         {
            String message = "Exception occurred on search for '%1s'!";
            warn(String.format(message, pattern.getName()));
         }
      }

      return Collections.emptyList();
   }


   /**
    * Fetches the activity for the AnnotationEngine's annotate operation.
    * 
    * @param pattern, the pattern specification, for which the activity is needed
    * @return the annotate activity or null, if not found
    */
   private Activity getAnnotateActivity(PSPatternSpecification pattern)
   {
      EObject engine = engines.get(pattern);
      EOperation annotateOperation = null;
      for (EOperation operation : engine.eClass().getEOperations())
      {
         if (GeneratorConstants.METHOD.equals(operation.getName()))
         {
            annotateOperation = operation;
            break;
         }
      }

      if (annotateOperation != null)
      {
         OperationExtension operExt = null;
         for (EAnnotation anno : annotateOperation.getEAnnotations())
         {
            for (EObject o : anno.getContents())
            {
               if (o instanceof OperationExtension)
               {
                  operExt = (OperationExtension) o;
                  break;
               }
            }
         }

         if (operExt != null)
         {
            return operExt.getOwnedActivity();
         }
      }

      String message = "Exception occurred on search for '%1s': Could not find annotate operation!";
      warn(String.format(message, pattern.getName()));

      return null;
   }
}
