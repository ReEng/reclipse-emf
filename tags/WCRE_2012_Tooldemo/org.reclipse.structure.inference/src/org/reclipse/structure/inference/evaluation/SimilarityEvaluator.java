package org.reclipse.structure.inference.evaluation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.reclipse.math.functions.MathematicalFunction;
import org.reclipse.metrics.extensionpoints.MetricUtil;
import org.reclipse.structure.inference.IAnnotationEvaluator;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.SatisfiedAttributeConstraint;
import org.reclipse.structure.inference.annotations.SatisfiedConstraint;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.ui.utils.MathFunctionHelper;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * @author Dietrich Travkin (travkin)
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 */
public class SimilarityEvaluator implements IAnnotationEvaluator
{
   @Override
   public double evaluate(ASGAnnotation asg)
   {
      return evaluate(asg, asg.getPattern()) * 100d;
   }


   private double evaluate(ASGAnnotation asg, PSPatternSpecification pattern)
   {
      // return rank/weight
      return rank(pattern, asg) / weight(pattern);
   }


   public double rank(PSPatternSpecification pattern, ASGAnnotation asg)
   {
      // create rank result
      double rank = 0;

      // go through the patterns' fragments
      for (PSCombinedFragment frag : pattern.getCombinedFragments())
      {
         if (ModifierType.ADDITIONAL.equals(frag.getKind()))
         {
            rank += rank(frag, asg);
         }
      }

      // go through the patterns' nodes
      for (PSNode node : pattern.getNodes())
      {
         // only for normal nodes
         if (!node.equals(ModelHelper.getCreateAnnotation(pattern)))
         {
            if (!ModelHelper.isContainedInAdditionalFragment(node))
            {
               if (node instanceof PSAnnotation)
               {
                  rank += rank((PSAnnotation) node, asg);
               }
               else if (node instanceof PSObject)
               {
                  rank += rank((PSObject) node, asg);
               }
               else if (node instanceof PSSpecificationConstraint)
               {
                  rank += rank((PSSpecificationConstraint) node, asg);
               }
            }
         }
      }

      return rank;
   }


   public double rank(PSCombinedFragment fragment, ASGAnnotation asg)
   {
      // create rank result
      double rank = 0;

      // go through the fragments' children
      for (PSCombinedFragmentItem child : fragment.getChildren())
      {
         if (child instanceof PSAnnotation)
         {
            rank += rank((PSAnnotation) child, asg);
         }
         else if (child instanceof PSObject)
         {
            rank += rank((PSObject) child, asg);
         }
         else if (child instanceof PSSpecificationConstraint)
         {
            rank += rank((PSSpecificationConstraint) child, asg);
         }
      }

      return rank * fragment.getWeight();
   }


   public double rank(PSAnnotation annotation, ASGAnnotation asg)
   {
      if (ModifierType.NEGATIVE.equals(annotation.getModifier()))
      {
         // this cannot be unsatisfied
         return annotation.getWeight();
      }

      // get bound objects
      List<EObject> boundObjects = asg.getBoundObjects().get(
            annotation.getName());
      if (boundObjects == null || boundObjects.isEmpty())
      {
         // no bound objects => rank 0
         return 0;
      }

      double rank = annotation.getWeight();

      // for annotation sets
      if (ModifierType.SET.equals(annotation.getModifier()))
      {
         // determine weight of one annotation
         double annotationWeight = annotation.getWeight();

         double boundObjectsRank = 0;
         for (EObject bound : boundObjects)
         {
            PSPatternSpecification pattern = ((ASGAnnotation) bound)
                  .getPattern();
            boundObjectsRank += evaluate((ASGAnnotation) bound, pattern);
         }

         rank = scale(boundObjectsRank, annotation) * annotationWeight;

         // check size conditions
         for (PSNodeConstraint constraint : annotation.getNodeConstraints())
         {
            if (constraint instanceof PSMetricConstraint)
            {
               rank += rank((PSMetricConstraint) constraint, asg);
            }
            else if (constraint instanceof PSFuzzyMetricConstraint)
            {
               rank += rank((PSFuzzyMetricConstraint) constraint, asg);
            }
         }
      }
      else
      {
         // get the bounded annotation
         ASGAnnotation bounded = (ASGAnnotation) boundObjects.get(0);

         PSPatternSpecification pattern = bounded.getPattern();
         rank *= evaluate(bounded, pattern);
      }

      return rank;
   }


   public double rank(PSObject object, ASGAnnotation asg)
   {
      if (ModifierType.NEGATIVE.equals(object.getModifier()))
      {
         // this cannot be unsatisfied
         return object.getWeight();
      }

      // get bound objects
      List<EObject> boundObjects = asg.getBoundObjects().get(object.getName());

      // nothing bound
      if (boundObjects == null || boundObjects.isEmpty())
      {
         return 0;
      }

      double rank = 0;
      if (ModifierType.SET.equals(object.getModifier()))
      {

         // determine weight of one object
         double weightOfOneObject = object.getWeight();

         for (PSNodeConstraint constraint : object.getNodeConstraints())
         {
            if (constraint instanceof PSBooleanConstraint)
            {
               weightOfOneObject += weight(constraint);
            }
         }

         // determine rank of all objects (without set size conditions)
         double allObjectsTmpRank = 0;
         for (EObject boundObject : boundObjects)
         {
            allObjectsTmpRank += object.getWeight();

            for (PSNodeConstraint constraint : object.getNodeConstraints())
            {
               if (constraint instanceof PSAttributeConstraint)
               {
                  allObjectsTmpRank += rank((PSAttributeConstraint) constraint,
                        boundObject, asg);
               }
               else if (constraint instanceof PSMetricConstraint)
               {
                  allObjectsTmpRank += rank((PSMetricConstraint) constraint,
                        boundObject);
               }
               else if (constraint instanceof PSFuzzyMetricConstraint)
               {
                  allObjectsTmpRank += rank(
                        (PSFuzzyMetricConstraint) constraint, boundObject);
               }
               else if (constraint instanceof PSFuzzySetRatingConstraint)
               {
                  // TODO: implement handling of fuzzy set rating
                  // constraints
                  throw new UnsupportedOperationException(
                        "The rating of fuzzy set rating constraints is not yet supported.");
               }
            }
         }

         if (weightOfOneObject > 0)
         {
            rank = scale(allObjectsTmpRank / weightOfOneObject, object)
                  * weightOfOneObject;
         }

         // check all set size conditions either
         for (PSNodeConstraint constraint : object.getNodeConstraints())
         {
            if (constraint instanceof PSMetricConstraint
                  && "SIZE".equals(((PSMetricConstraint) constraint)
                        .getMetricAcronym()))
            {
               rank += rank((PSMetricConstraint) constraint, asg);
            }
            else if (constraint instanceof PSFuzzyMetricConstraint
                  && "SIZE".equals(((PSFuzzyMetricConstraint) constraint)
                        .getMetricAcronym()))
            {
               rank += rank((PSFuzzyMetricConstraint) constraint, asg);
            }
         }
      }
      else
      {
         rank = object.getWeight();
         EObject boundObject = boundObjects.get(0);
         for (PSNodeConstraint constraint : object.getNodeConstraints())
         {
            if (constraint instanceof PSAttributeConstraint)
            {
               rank += rank((PSAttributeConstraint) constraint, boundObject,
                     asg);
            }
            else if (constraint instanceof PSMetricConstraint)
            {
               rank += rank((PSMetricConstraint) constraint, boundObject);
            }
            else if (constraint instanceof PSFuzzyMetricConstraint)
            {
               rank += rank((PSFuzzyMetricConstraint) constraint, boundObject);
            }
         }
      }

      return rank;
   }


   public double rank(PSSpecificationConstraint constraint, ASGAnnotation asg)
   {
      // TODO: 'maybe' - this got changed due to new expressions?
      if (constraint.isAdditional())
      {
         // maybe constraints have no rank
         return 0;
      }

      if (!constraint.isAdditional()
            || asg.getSatisfiedConstraints().contains(constraint.getName()))
      {
         return constraint.getWeight();
      }

      return 0;
   }


   public double rank(PSAttributeConstraint constraint, EObject bounded,
         ASGAnnotation asg)
   {
      if (!constraint.isAdditional())
      {
         return constraint.getWeight();
      }
      else
      {
         // determine the index of PSAttributeConstraint in its node
         int index = -1;
         int currentIndex = 0;
         Iterator<PSNodeConstraint> constraintIter = constraint.getNode()
               .getNodeConstraints().iterator();
         while (index == -1 && constraintIter.hasNext())
         {
            PSNodeConstraint other = constraintIter.next();
            if (other.equals(constraint))
            {
               index = currentIndex;
            }
            currentIndex++;
         }

         if (hasInSatisfiedConstraints(asg, bounded, constraint.getNode()
               .getName(), index))
         {
            return constraint.getWeight();
         }
         else
         {
            return 0;
         }
      }
   }


   public double rank(PSMetricConstraint constraint, EObject boundObject)
   {
      double actual = MetricUtil.getMetricValue(boundObject,
            constraint.getMetricAcronym());
      double expected = Double.parseDouble(constraint.getValueExpression());
      OperatorType operator = constraint.getOperator();

      if (!constraint.isAdditional()
            || checkExpression(actual, operator, expected))
      {
         return constraint.getWeight();
      }

      return 0;
   }


   public double rank(PSMetricConstraint constraint, ASGAnnotation asg)
   {
      if (!constraint.getMetricAcronym().equals("SIZE"))
      {
         return 0;
      }

      if (!constraint.isAdditional())
      {
         return constraint.getWeight();
      }

      // check set size expression
      int desiredSize = Integer.valueOf(constraint.getValueExpression());
      OperatorType operator = constraint.getOperator();
      int realSize = getObjectCount(constraint, asg);

      if (checkExpression(realSize, operator, desiredSize))
      {
         return constraint.getWeight();
      }

      return 0;
   }


   public double rank(PSFuzzyMetricConstraint constraint, EObject boundObject)
   {
      String acronym = constraint.getMetricAcronym();
      double metricValue = MetricUtil.getMetricValue(boundObject, acronym);
      double result = getMembershipValue(constraint, metricValue);

      result *= constraint.getWeight();

      return result;
   }


   public double rank(PSFuzzyMetricConstraint constraint, ASGAnnotation asg)
   {
      if (!constraint.getMetricAcronym().equals("SIZE"))
      {
         return 0;
      }

      int setSize = getObjectCount(constraint, asg);

      return getMembershipValue(constraint, setSize);
   }


   public double weightPattern(PSPatternSpecification pattern)
   {
      double weight = 0;
      PSAnnotation specifiedPatternAnno = ModelHelper
            .getCreateAnnotation(pattern);

      // go through (additional) fragments
      for (PSCombinedFragment fragment : pattern.getCombinedFragments())
      {
         if (ModifierType.ADDITIONAL.equals(fragment.getKind()))
         {
            weight += weight(fragment);
         }
      }

      // go through nodes
      for (PSNode node : pattern.getNodes())
      {
         if (!node.equals(specifiedPatternAnno)
               && !ModelHelper.isContainedInAdditionalFragment(node))
         {
            if (node instanceof PSAnnotation)
            {
               weight += weight((PSAnnotation) node);
            }
            else if (node instanceof PSObject)
            {
               weight += weight((PSObject) node);
            }
         }
      }

      // go through constraints
      for (PSSpecificationConstraint constraint : pattern.getConstraints())
      {
         weight += weight(constraint);
      }

      return weight;
   }


   public double weight(PSPatternSpecification pattern)
   {
      // check for recursion
      if (isRecursive(pattern))
      {
         return Double.MAX_VALUE;
      }

      return weightPattern(pattern);
   }


   public double weight(PSCombinedFragment fragment)
   {
      double weight = 0;
      for (PSCombinedFragmentItem item : fragment.getChildren())
      {
         if (item instanceof PSAnnotation)
         {
            weight += weight((PSAnnotation) item);
         }
         else if (item instanceof PSObject)
         {
            weight += weight((PSObject) item);
         }
         else if (item instanceof PSCombinedFragment
               && ModifierType.ADDITIONAL.equals(((PSCombinedFragment) item)
                     .getKind()))
         {
            // add weight for additional fragment
            weight += weight((PSCombinedFragment) item);
         }
      }

      if (fragment.getConstraint() != null)
      {
         weight += weight(fragment.getConstraint());
      }

      return weight * fragment.getWeight();
   }


   public double weight(PSAnnotation annotation)
   {
      switch (annotation.getModifier())
      {
         default:
            return annotation.getWeight();

         case SET:
            // set annotations have constraint weights
            double weight = annotation.getWeight();
            for (PSNodeConstraint constraint : annotation.getNodeConstraints())
            {
               weight += weight(constraint);
            }

            return weight;
      }
   }


   public double weight(PSObject object)
   {
      switch (object.getModifier())
      {
         default:
            double weight = object.getWeight();

            for (PSNodeConstraint constraint : object.getNodeConstraints())
            {
               weight += weight(constraint);
            }

            return weight;

         case NEGATIVE:
            // negative objects have no constraint weights
            return object.getWeight();
      }
   }


   public double weight(PSSpecificationConstraint constraint)
   {
      if (constraint.isAdditional())
      {
         // additional constraints have no weight
         return 0;
      }

      return constraint.getWeight();
   }


   public double weight(PSNodeConstraint constraint)
   {
      return constraint.getWeight();
   }


   private boolean isRecursive(PSPatternSpecification specification)
   {
      ArrayList<PSPatternSpecification> list = new ArrayList<PSPatternSpecification>();
      list.add(specification);
      return findRecursion(specification, list);
   }


   private boolean findRecursion(PSPatternSpecification specification,
         ArrayList<PSPatternSpecification> list)
   {
      for (PSNode node : specification.getNodes())
      {
         if (node instanceof PSAnnotation
               && !ModelHelper.isCreate((PSAnnotation) node))
         {
            PSAnnotation anno = (PSAnnotation) node;
            PSPatternSpecification newPattern = anno.getType();

            if (list.contains(newPattern))
            {
               return true;
            }

            list.add(newPattern);

            if (findRecursion(newPattern, list))
            {
               return true;
            }

            list.remove(newPattern);
         }
      }

      return false;
   }


   private static int getObjectCount(PSNodeConstraint constraint,
         ASGAnnotation asg)
   {
      String name = constraint.getNode().getName();

      return asg.getBoundObjects().get(name).size();
   }


   private static double getMembershipValue(PSFuzzyMetricConstraint constraint,
         double value)
   {
      MathematicalFunction function = MathFunctionHelper
            .getMathematicalFunction(constraint);
      double result;

      result = function.value(value);
      if (result > 1)
      {
         result = 1.0d;
      }
      else if (result < 0)
      {
         result = 0.0d;
      }

      return result;
   }


   private static double scale(double value, PSNode node)
   {
      PSFuzzySetRatingConstraint setRatingFunction = null;
      for (PSNodeConstraint constraint : node.getNodeConstraints())
      {
         if (constraint instanceof PSFuzzySetRatingConstraint)
         {
            setRatingFunction = (PSFuzzySetRatingConstraint) constraint;
            break;
         }
      }

      if (setRatingFunction != null)
      {
         // compute with the specified set rating function
         MathematicalFunction function = MathFunctionHelper
               .getMathematicalFunction(setRatingFunction);
         return function.value(value);
      }

      // else use this, because no set rating function was specified
      return 2.0d / (1.0d + Math.exp(-value / 5)) - 1.0d;
   }


   private static boolean checkExpression(double number1,
         OperatorType operator, double number2)
   {
      if (OperatorType.EQUAL.equals(operator))
      {
         return number1 == number2;
      }
      else if (OperatorType.LESS.equals(operator))
      {
         return number1 < number2;
      }
      else if (OperatorType.LESS_OR_EQUAL.equals(operator))
      {
         return number1 <= number2;
      }
      else if (OperatorType.GREATER.equals(operator))
      {
         return number1 > number2;
      }
      else if (OperatorType.GREATER_OR_EQUAL.equals(operator))
      {
         return number1 >= number2;
      }
      else if (OperatorType.UNEQUAL.equals(operator))
      {
         return number1 != number2;
      }
      else
      {
         throw new IllegalArgumentException(
               "Unsupported operator found in an expression. Supported operators are =, <, >, <=, >=, and !=.");
      }
   }


   private static boolean hasInSatisfiedConstraints(ASGAnnotation asg,
         EObject context, String nodeID, int attributeIndex)
   {
      for (SatisfiedConstraint constraint : asg.getSatisfiedConstraints())
      {
         if (constraint instanceof SatisfiedAttributeConstraint)
         {
            SatisfiedAttributeConstraint attrConstraint = (SatisfiedAttributeConstraint) constraint;
            if (attrConstraint.getContext().equals(context)
                  && attrConstraint.getNodeID().equals(nodeID)
                  && attrConstraint.getAttributeIndex() == attributeIndex)
            {
               return true;
            }
         }
      }

      return false;
   }
}
