package org.reclipse.structure.inference.ui.matching.util;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.evaluation.SimilarityEvaluator;
import org.reclipse.structure.inference.ui.matching.Constants;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.util.ModelHelper;


public class SatisfactionUtil
{

   private static SimilarityEvaluator evaluator = new SimilarityEvaluator();


   public static String extend(String weightText, String text)
   {
      StringBuffer buf = new StringBuffer(weightText);

      if (buf.length() > 0)
      {
         buf.append("\n");
      }

      buf.append(text);

      return buf.toString();
   }


   private static String getSatisfaction(double rank)
   {
      if (rank == 0)
      {
         return Constants.UNSATISFIED_TEXT;
      }
      else if (rank == 1)
      {
         return Constants.SATISFIED_TEXT;
      }
      else
      {
         return Constants.SATISFACTION_PREFIX + roundedSatisfaction(rank);
      }
   }


   public static String getSatisfaction(PSAnnotation psAnnotation,
         ASGAnnotation annotation)
   {
      double rank;
      if (!ModelHelper.isCreate(psAnnotation))
      {
         rank = evaluator.rank(psAnnotation, annotation);
      }
      else
      {
         rank = evaluator.rank(psAnnotation.getType(), annotation);
      }

      return getSatisfaction(rank);
   }


   public static String getSatisfaction(PSAttributeConstraint attrExprPair,
         ASGAnnotation annotation)
   {
      String key = attrExprPair.getNode().getName();
      if (annotation.getBoundObjects().containsKey(key))
      {
         for (EObject element : annotation.getBoundObjects().get(key))
         {
            double rank = evaluator.rank(attrExprPair, element, annotation);
            return getSatisfaction(rank);
         }
      }

      return Constants.UNSATISFIED_TEXT;
   }


   public static String getSatisfaction(PSCombinedFragment fragment,
         ASGAnnotation annotation)
   {
      double rank = evaluator.rank(fragment, annotation);
      return getSatisfaction(rank);
   }


   public static String getSatisfaction(PSFuzzyMetricConstraint fuzzyExpr,
         ASGAnnotation annotation)
   {
      double rank = evaluator.rank(fuzzyExpr, annotation);
      return getSatisfaction(rank);
   }


   public static String getSatisfaction(PSFuzzySetRatingConstraint fuzzyExpr,
         ASGAnnotation annotation)
   {
      // TODO: missing method to rank fuzzy set rating constraints
      // double rank = evaluator.rank(fuzzyExpr, annotation);
      return getSatisfaction(0);
   }


   public static String getSatisfaction(PSMetricConstraint metricExpr,
         ASGAnnotation annotation)
   {
      double rank = evaluator.rank(metricExpr, annotation);
      return getSatisfaction(rank);
   }


   public static String getSatisfaction(PSObject psObject,
         ASGAnnotation annotation)
   {
      double rank = evaluator.rank(psObject, annotation);
      return getSatisfaction(rank);
   }


   public static String getSatisfaction(PSSpecificationConstraint constraint,
         ASGAnnotation annotation)
   {
      double rank = evaluator.rank(constraint, annotation);
      return getSatisfaction(rank);
   }


   public static String getWeight(PSAnnotation psAnnotation)
   {
      double weight = evaluator.weight(psAnnotation);
      return Double.toString(weight);
   }


   public static String getWeight(PSPatternSpecification diagram)
   {
      double weight = evaluator.weight(diagram);
      return Double.toString(weight);
   }


   private static String roundedSatisfaction(double value)
   {
      return String.format("%1$#.2f", value);
   }
}
