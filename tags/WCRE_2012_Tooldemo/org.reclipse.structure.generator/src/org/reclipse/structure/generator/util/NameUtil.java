package org.reclipse.structure.generator.util;


import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypeParameter;
import org.fujaba.commons.identifier.Identifier;
import org.storydriven.core.NamedElement;


public final class NameUtil
{
	private static void append(StringBuilder result, EClass eClass)
	{
		if (eClass.getName() != null)
		{
			result.append(eClass.getName());
		}

		if (!eClass.getETypeParameters().isEmpty())
		{
			result.append("<");
			int i = 1;
			int count = eClass.getETypeParameters().size();
			for (ETypeParameter eTypeParameter : eClass.getETypeParameters())
			{
				append(result, eTypeParameter);
				if (i < count)
				{
					result.append(", ");
				}
			}
			result.append(">");
		}

		if (!eClass.getEGenericSuperTypes().isEmpty())
		{
			result.append(" -> ");
			int i = 1;
			int count = eClass.getEGenericSuperTypes().size();
			for (EGenericType eGenericSuperType : eClass.getEGenericSuperTypes())
			{
				append(result, eGenericSuperType);
				if (i < count)
				{
					result.append(", ");
				}
			}
		}
		if (eClass.getInstanceTypeName() != null)
		{
			result.append(" [");
			result.append(eClass.getInstanceTypeName());
			result.append("]");
		}
	}


	private static void append(StringBuilder builder, EGenericType element)
	{
		EGenericType eGenericType = (EGenericType) element;
		ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
		if (eTypeParameter != null)
		{
			builder.append(eTypeParameter.getName());
			return;
		}
		else
		{
			EClassifier eClassifier = eGenericType.getEClassifier();
			if (eClassifier != null)
			{
				Collection<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
				if (eTypeArguments.isEmpty())
				{
					builder.append(eClassifier.getName());
					return;
				}
				else
				{
					builder.append(eClassifier.getName());
					builder.append('<');
					int index = 1;
					int count = eTypeArguments.size();
					for (EGenericType eGenericType2 : eTypeArguments)
					{
						append(builder, eGenericType2);
						if (index < count)
						{
							builder.append(", ");
						}
						else
						{
							break;
						}
					}
					builder.append('>');
				}
			}
			else
			{
				EGenericType eUpperBound = eGenericType.getEUpperBound();
				if (eUpperBound != null)
				{
					builder.append("? extends ");
					append(builder, eUpperBound);
					return;
				}
				else
				{
					EGenericType eLowerBound = eGenericType.getELowerBound();
					if (eLowerBound != null)
					{
						builder.append("? super ");
						append(builder, eLowerBound);
						return;
					}
					else
					{
						builder.append("?");
						return;
					}
				}
			}
		}
	}


	private static void append(StringBuilder result, EOperation eOperation)
	{
		// prepare counters
		int index;
		int count;

		result.append(eOperation.getName());
		if (!eOperation.getETypeParameters().isEmpty())
		{
			index = 1;
			count = eOperation.getETypeParameters().size();
			result.append("<");
			for (ETypeParameter parameter : eOperation.getETypeParameters())
			{
				append(result, parameter);
				if (index < count)
				{
					result.append(", ");
				}
				index++;
			}
			result.append(">");

		}
		result.append("(");
		index = 1;
		count = eOperation.getEParameters().size();
		for (EParameter parameter : eOperation.getEParameters())
		{
			if (parameter.getEGenericType() != null)
			{
				append(result, parameter.getEGenericType());
				if (index < count)
				{
					result.append(", ");
				}
				index++;
			}
		}
		result.append(")");
		if (eOperation.getEGenericType() != null)
		{
			result.append(" : ");
			append(result, eOperation.getEGenericType());
		}

		if (!eOperation.getEGenericExceptions().isEmpty())
		{
			result.append(" throws ");
			index = 1;
			count = eOperation.getEGenericExceptions().size();
			for (EGenericType eException : eOperation.getEGenericExceptions())
			{
				append(result, eException);
				if (index < count)
				{
					result.append(", ");
				}
			}
		}
	}


	private static void append(StringBuilder result, ETypeParameter parameter)
	{
		result.append(parameter.getName());
	}


	public static String getName(EClass element)
	{
		StringBuilder result = new StringBuilder();
		append(result, element);
		return result.toString();
	}


	public static String getName(ENamedElement element)
	{
		return element.getName();
	}


	public static String getName(EOperation element)
	{
		StringBuilder result = new StringBuilder();
		append(result, element);
		return result.toString();
	}


	public static String getName(Identifier element)
	{
		return element.getName();
	}


	public static String getName(NamedElement element)
	{
		return element.getName();
	}


	public static String getName(Object element)
	{
		return String.valueOf(element);
	}


	private NameUtil()
	{
		// hide constructor
	}
}
