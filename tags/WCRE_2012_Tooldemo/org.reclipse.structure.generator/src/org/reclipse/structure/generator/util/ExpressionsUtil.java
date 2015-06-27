package org.reclipse.structure.generator.util;


import org.eclipse.emf.ecore.EParameter;
import org.reclipse.structure.generator.util.more.SDMUtil;
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
            ParameterExtension extension = CallsFactory.eINSTANCE
                  .createParameterExtension();
            extension.setParameter(param);

            ParameterExpression element = CallsExpressionsFactory.eINSTANCE
                  .createParameterExpression();
            element.setParameter(extension);

            return element;
         }
      }

      return null;
   }


   public static TextualExpression createTextualExpression(String expressionText)
   {
      TextualExpression expr = BASE_FACTORY.createTextualExpression();

      expr.setLanguage("OCL");
      expr.setExpressionText(expressionText);

      return expr;
   }


   public static ParameterExpression createParameterExpression(
         EParameter parameter)
   {
      ParameterExtension extension = CallsFactory.eINSTANCE
            .createParameterExtension();
      extension.setParameter(parameter);

      ParameterExpression element = CALLS_FACTORY.createParameterExpression();
      element.setParameter(extension);

      return element;
   }


   public static Expression createAttributeExpression(ObjectVariable variable,
         PSAttributeConstraint constraint)
   {
      AttributeValueExpression attributeValueExpression = PATTERNS_FACTORY
            .createAttributeValueExpression();
      attributeValueExpression.setObject(variable);
      attributeValueExpression.setAttribute(constraint.getAttribute());

      LiteralExpression literalExpression = COMMON_FACTORY
            .createLiteralExpression();
      literalExpression.setValue(constraint.getValueExpression());

      return SDMUtil.createComparingExpression(attributeValueExpression,
            constraint.getOperator(), literalExpression);
   }


   public static Expression createMetricExpression(PSMetricConstraint constraint)
   {
      // FIXME: implement metric expression
      return null;
   }


   public static Expression createQualifierExpression(PSLink link)
   {
      LiteralExpression expression = COMMON_FACTORY.createLiteralExpression();
      expression.setValue(link.getQualifier());
      return expression;
   }


   public static Expression createQualifierExpression(String qualifer)
   {
      LiteralExpression expression = COMMON_FACTORY.createLiteralExpression();
      expression.setValue(qualifer);
      return expression;
   }


   public static Expression createEBooleanExpression(boolean value)
   {
      LiteralExpression expression = COMMON_FACTORY.createLiteralExpression();
      expression.setValue(String.valueOf(value));
      return expression;
   }


   public static Expression createPrimitiveVariableExpression(
         PrimitiveVariable variable)
   {
      PrimitiveVariableExpression expression = PATTERNS_FACTORY
            .createPrimitiveVariableExpression();
      expression.setPrimitiveVariable(variable);
      return expression;
   }
}
