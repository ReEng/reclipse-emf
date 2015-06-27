package org.reclipse.structure.generator.util;


import org.eclipse.emf.ecore.EParameter;
import org.reclipse.structure.generator.util.more.SDMUtil;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.storydriven.core.expressions.Expression;
import org.storydriven.core.expressions.ExpressionsFactory;
import org.storydriven.core.expressions.TextualExpression;
import org.storydriven.core.expressions.common.CommonExpressionsFactory;
import org.storydriven.core.expressions.common.LiteralExpression;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.calls.CallsFactory;
import org.storydriven.storydiagrams.calls.ParameterExtension;
import org.storydriven.storydiagrams.calls.expressions.CallsExpressionsFactory;
import org.storydriven.storydiagrams.calls.expressions.ParameterExpression;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.PrimitiveVariable;
import org.storydriven.storydiagrams.patterns.expressions.AttributeValueExpression;
import org.storydriven.storydiagrams.patterns.expressions.PatternsExpressionsFactory;
import org.storydriven.storydiagrams.patterns.expressions.PrimitiveVariableExpression;


public final class ExpressionsUtil
{
   private static final PatternsExpressionsFactory PATTERNS_FACTORY = PatternsExpressionsFactory.eINSTANCE;

   private static final CallsExpressionsFactory CALLS_FACTORY = CallsExpressionsFactory.eINSTANCE;

   private static final ExpressionsFactory BASE_FACTORY = ExpressionsFactory.eINSTANCE;

   private static final CommonExpressionsFactory COMMON_FACTORY = CommonExpressionsFactory.eINSTANCE;

   /**
    * This variable determines if only OCL expressions are generated by the util or if expressions
    * using the specialized expressions metamodel of story diagrams are generated. As of the release
    * of the techreport "Story Diagrams - Syntax and Semantics v0.2" only OCL expressions are
    * supported by the SD Interpreter.
    */
   private static final boolean USE_ONLY_OCL_EXPRESSIONS = true;


   private ExpressionsUtil()
   {
      // hide constructor
   }


   public static Expression getContextBindingExpression(Activity activity)
   {
      for (EParameter param : activity.getInParameters())
      {
         if (Constants.VAR_CONTEXT.equals(param.getName()))
         {
            ParameterExtension extension = CallsFactory.eINSTANCE.createParameterExtension();
            extension.setParameter(param);

            ParameterExpression element = CallsExpressionsFactory.eINSTANCE.createParameterExpression();
            element.setParameter(extension);

            return element;
         }
      }

      return null;
   }


   public static TextualExpression createOCLExpression(String expressionText)
   {
      TextualExpression expr = BASE_FACTORY.createTextualExpression();

      expr.setLanguage("OCL");
      expr.setLanguageVersion("1.0");
      expr.setExpressionText(expressionText);

      return expr;
   }


   public static Expression createParameterExpression(EParameter parameter)
   {
      if (USE_ONLY_OCL_EXPRESSIONS)
      {
         return createOCLParameterExpression(parameter);
      }
      else
      {
         ParameterExtension extension = CallsFactory.eINSTANCE.createParameterExtension();
         extension.setParameter(parameter);

         ParameterExpression element = CALLS_FACTORY.createParameterExpression();
         element.setParameter(extension);

         return element;
      }
   }


   public static TextualExpression createOCLParameterExpression(EParameter parameter)
   {
      return createOCLExpression(parameter.getName());
   }


   public static Expression createAttributeExpression(ObjectVariable variable, PSAttributeConstraint constraint)
   {
      if (USE_ONLY_OCL_EXPRESSIONS)
      {
         return createOCLAttributeExpression(variable, constraint);
      }
      else
      {
         AttributeValueExpression attributeValueExpression = PATTERNS_FACTORY.createAttributeValueExpression();
         attributeValueExpression.setObject(variable);
         attributeValueExpression.setAttribute(constraint.getAttribute());

         LiteralExpression literalExpression = COMMON_FACTORY.createLiteralExpression();
         literalExpression.setValue(constraint.getValueExpression());

         return SDMUtil
               .createComparingExpression(attributeValueExpression, constraint.getOperator(), literalExpression);
      }
   }


   public static TextualExpression createOCLAttributeExpression(ObjectVariable variable,
         PSAttributeConstraint constraint)
   {
      String attribute = variable.getName() + "." + constraint.getAttribute().getName();

      String operator = "";
      switch (constraint.getOperator().getValue())
      {
         case OperatorType.LESS_VALUE:
            operator = "<";
            break;
         case OperatorType.LESS_OR_EQUAL_VALUE:
            operator = "<=";
            break;
         case OperatorType.GREATER_VALUE:
            operator = ">";
            break;
         case OperatorType.GREATER_OR_EQUAL_VALUE:
            operator = ">=";
            break;
         case OperatorType.EQUAL_VALUE:
            operator = "=";
            break;
         case OperatorType.UNEQUAL_VALUE:
            operator = "<>";
            break;
         default:
            assert false : "Unexpected operator type" + constraint.getOperator().getName();
      }

      String value = constraint.getValueExpression();

      return createOCLExpression(attribute + operator + value);
   }


   public static Expression createMetricExpression(PSMetricConstraint constraint)
   {
      // FIXME: implement metric expression
      throw new UnsupportedOperationException("Metric Expressions are not yet implemented!");
   }


   public static Expression createQualifierExpression(PSLink link)
   {
      if (USE_ONLY_OCL_EXPRESSIONS)
      {
         return createOCLQualifierExpression(link);
      }
      else
      {
         LiteralExpression expression = COMMON_FACTORY.createLiteralExpression();
         expression.setValue(link.getQualifier());
         return expression;
      }
   }


   public static Expression createQualifierExpression(String qualifier)
   {
      if (USE_ONLY_OCL_EXPRESSIONS)
      {
         return createOCLQualifierExpression(qualifier);
      }
      else
      {
         LiteralExpression expression = COMMON_FACTORY.createLiteralExpression();
         expression.setValue(qualifier);
         return expression;
      }
   }


   public static TextualExpression createOCLQualifierExpression(PSLink link)
   {
      return createOCLExpression("'"+link.getQualifier()+"'");
   }


   public static TextualExpression createOCLQualifierExpression(String qualifier)
   {
      return createOCLExpression("'"+qualifier+"'");
   }


   public static Expression createEBooleanExpression(boolean value)
   {
      if (USE_ONLY_OCL_EXPRESSIONS)
      {
         return createOCLBooleanExpression(value);
      }
      else
      {
         LiteralExpression expression = COMMON_FACTORY.createLiteralExpression();
         expression.setValue(String.valueOf(value));
         return expression;
      }
   }


   public static TextualExpression createOCLBooleanExpression(boolean value)
   {
      return createOCLExpression(String.valueOf(value));
   }


   public static Expression createPrimitiveVariableExpression(PrimitiveVariable variable)
   {
      if (USE_ONLY_OCL_EXPRESSIONS)
      {
         return createOCLPrimitiveVariableExpression(variable);
      }
      else
      {
         PrimitiveVariableExpression expression = PATTERNS_FACTORY.createPrimitiveVariableExpression();
         expression.setPrimitiveVariable(variable);
         return expression;
      }
   }


   public static TextualExpression createOCLPrimitiveVariableExpression(PrimitiveVariable variable)
   {
      return createOCLExpression(variable.getVariableName());
   }
}
