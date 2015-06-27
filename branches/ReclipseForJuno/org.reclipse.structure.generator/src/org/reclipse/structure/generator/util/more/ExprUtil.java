/**
 * 
 */
package org.reclipse.structure.generator.util.more;


import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;
import org.storydriven.core.expressions.Expression;
import org.storydriven.core.expressions.ExpressionsFactory;
import org.storydriven.core.expressions.TextualExpression;
import org.storydriven.core.expressions.common.ArithmeticExpression;
import org.storydriven.core.expressions.common.ArithmeticOperator;
import org.storydriven.core.expressions.common.CommonExpressionsFactory;
import org.storydriven.core.expressions.common.ComparingOperator;
import org.storydriven.core.expressions.common.ComparisonExpression;
import org.storydriven.core.expressions.common.LiteralExpression;
import org.storydriven.core.expressions.common.LogicOperator;
import org.storydriven.core.expressions.common.LogicalExpression;
import org.storydriven.core.expressions.common.UnaryExpression;
import org.storydriven.core.expressions.common.UnaryOperator;


/**
 * A utility class for expression creation.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class ExprUtil
{
	public static LogicalExpression equivalent(Expression left, Expression right)
	{
		return createBLE(left, right, LogicOperator.EQUIVALENT);
	}


	public static LogicalExpression and(Expression left, Expression right)
	{
		return createBLE(left, right, LogicOperator.AND);
	}


	public static LogicalExpression imply(Expression left, Expression right)
	{
		return createBLE(left, right, LogicOperator.IMPLY);
	}


	public static LogicalExpression or(Expression left, Expression right)
	{
		return createBLE(left, right, LogicOperator.OR);
	}


	public static LogicalExpression xor(Expression left, Expression right)
	{
		return createBLE(left, right, LogicOperator.XOR);
	}


	private static LogicalExpression createBLE(Expression left, Expression right, LogicOperator operator)
	{
	   LogicalExpression expression = CommonExpressionsFactory.eINSTANCE.createLogicalExpression();

		expression.setLeftExpression(left);
		expression.setRightExpression(right);
		expression.setOperator(operator);

		return expression;
	}


	/*
	 * ********************************************************************************************************************
	 */
	public static ComparisonExpression equal(Expression left, Expression right)
	{
		return createCE(left, right, ComparingOperator.EQUAL);
	}


	public static ComparisonExpression greater(Expression left, Expression right)
	{
		return createCE(left, right, ComparingOperator.GREATER);
	}


	public static ComparisonExpression greaterOrEqual(Expression left, Expression right)
	{
		return createCE(left, right, ComparingOperator.GREATER_OR_EQUAL);
	}


	public static ComparisonExpression less(Expression left, Expression right)
	{
		return createCE(left, right, ComparingOperator.LESS);
	}


	public static ComparisonExpression lessOrEqual(Expression left, Expression right)
	{
		return createCE(left, right, ComparingOperator.LESS_OR_EQUAL);
	}


	public static ComparisonExpression unequal(Expression left, Expression right)
	{
		return createCE(left, right, ComparingOperator.UNEQUAL);
	}


	public static ComparisonExpression regularExpression(Expression left, Expression right)
	{
		return createCE(left, right, ComparingOperator.REGULAR_EXPRESSION);
	}


	private static ComparisonExpression createCE(Expression left, Expression right, ComparingOperator operator)
	{
		ComparisonExpression expression = CommonExpressionsFactory.eINSTANCE.createComparisonExpression();

		expression.setLeftExpression(left);
		expression.setRightExpression(right);
		expression.setOperator(operator);

		return expression;
	}


	/*
	 * ********************************************************************************************************************
	 */

	public static ArithmeticExpression plus(Expression left, Expression right)
	{
		return createAE(left, right, ArithmeticOperator.PLUS);
	}


	public static ArithmeticExpression divide(Expression left, Expression right)
	{
		return createAE(left, right, ArithmeticOperator.DIVIDE);
	}


	public static ArithmeticExpression minus(Expression left, Expression right)
	{
		return createAE(left, right, ArithmeticOperator.MINUS);
	}


	public static ArithmeticExpression modulo(Expression left, Expression right)
	{
		return createAE(left, right, ArithmeticOperator.MODULO);
	}


	public static ArithmeticExpression times(Expression left, Expression right)
	{
		return createAE(left, right, ArithmeticOperator.TIMES);
	}


	private static ArithmeticExpression createAE(Expression left, Expression right, ArithmeticOperator operator)
	{
		ArithmeticExpression expression = CommonExpressionsFactory.eINSTANCE.createArithmeticExpression();

		expression.setLeftExpression(left);
		expression.setRightExpression(right);
		expression.setOperator(operator);

		return expression;
	}


	/*
	 * ********************************************************************************************************************
	 */

	public static LiteralExpression eBoolean(boolean value)
	{
		return createLE(EcorePackage.Literals.EBOOLEAN, String.valueOf(value));
	}


	public static LiteralExpression eDouble(double value)
	{
		return createLE(EcorePackage.Literals.EDOUBLE, String.valueOf(value));
	}


	public static LiteralExpression eString(String value)
	{
		return createLE(EcorePackage.Literals.ESTRING, value);
	}


	public static LiteralExpression eBigDecimal(BigDecimal value)
	{
		return createLE(EcorePackage.Literals.EBIG_DECIMAL, String.valueOf(value));
	}


	public static LiteralExpression eBigInteger(BigInteger value)
	{
		return createLE(EcorePackage.Literals.EBIG_INTEGER, String.valueOf(value));
	}


	public static LiteralExpression eByte(byte value)
	{
		return createLE(EcorePackage.Literals.EBYTE, String.valueOf(value));
	}


	public static LiteralExpression eChar(char value)
	{
		return createLE(EcorePackage.Literals.ECHAR, String.valueOf(value));
	}


	public static LiteralExpression eFloat(float value)
	{
		return createLE(EcorePackage.Literals.EFLOAT, String.valueOf(value));
	}


	public static LiteralExpression eInt(int value)
	{
		return createLE(EcorePackage.Literals.EINT, String.valueOf(value));
	}


	public static LiteralExpression eLong(long value)
	{
		return createLE(EcorePackage.Literals.ELONG, String.valueOf(value));
	}


	public static LiteralExpression eShort(short value)
	{
		return createLE(EcorePackage.Literals.ESHORT, String.valueOf(value));
	}


	private static LiteralExpression createLE(EDataType type, String value)
	{
		LiteralExpression expression = CommonExpressionsFactory.eINSTANCE.createLiteralExpression();

		expression.setValue(value);

		return expression;
	}


	public static TextualExpression textual(String expressionText, String language, String languageVersion)
	{
		TextualExpression expr = ExpressionsFactory.eINSTANCE.createTextualExpression();

		expr.setExpressionText(expressionText);
		expr.setLanguage(language);
		expr.setLanguageVersion(languageVersion);

		return expr;
	}


	public static UnaryExpression not(Expression expression)
	{
	   UnaryExpression notExpression = CommonExpressionsFactory.eINSTANCE.createUnaryExpression();

		notExpression.setEnclosedExpression(expression);
		notExpression.setOperator(UnaryOperator.NOT);

		return notExpression;
	}
}
