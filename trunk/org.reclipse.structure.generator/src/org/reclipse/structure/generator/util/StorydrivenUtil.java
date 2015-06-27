package org.reclipse.structure.generator.util;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.reclipse.structure.generator.util.more.SDMUtil;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;
import org.reclipse.structure.specification.util.SpecificationUtil;
import org.storydriven.core.expressions.Expression;
import org.storydriven.storydiagrams.activities.ActivitiesFactory;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityCallNode;
import org.storydriven.storydiagrams.activities.ActivityEdge;
import org.storydriven.storydiagrams.activities.ActivityFinalNode;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.InitialNode;
import org.storydriven.storydiagrams.activities.ModifyingStoryNode;
import org.storydriven.storydiagrams.activities.StoryNode;
import org.storydriven.storydiagrams.calls.Callable;
import org.storydriven.storydiagrams.calls.CallsFactory;
import org.storydriven.storydiagrams.calls.Invocation;
import org.storydriven.storydiagrams.calls.ParameterBinding;
import org.storydriven.storydiagrams.patterns.BindingOperator;
import org.storydriven.storydiagrams.patterns.BindingSemantics;
import org.storydriven.storydiagrams.patterns.BindingState;
import org.storydriven.storydiagrams.patterns.Constraint;
import org.storydriven.storydiagrams.patterns.LinkVariable;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.PatternsFactory;
import org.storydriven.storydiagrams.patterns.PrimitiveVariable;
import org.storydriven.storydiagrams.patterns.StoryPattern;
import org.storydriven.storydiagrams.patterns.expressions.ObjectVariableExpression;
import org.storydriven.storydiagrams.patterns.expressions.PatternsExpressionsFactory;
import org.storydriven.storydiagrams.patterns.expressions.PrimitiveVariableExpression;


public final class StorydrivenUtil
{
	private static final PatternsFactory PATTERNS = PatternsFactory.eINSTANCE;

	private static final ActivitiesFactory ACTIVITIES = ActivitiesFactory.eINSTANCE;

	private static final CallsFactory CALLS = CallsFactory.eINSTANCE;


	public static void connectAllVariables(PSPatternSpecification pattern, StoryNode storyNode,
			Map<PSNode, ObjectVariable> variables)
	{
		// add link variables for all links
		for (PSLink link : SpecificationUtil.getLinks(pattern))
		{
			ObjectVariable source = variables.get(link.getSource());
			ObjectVariable target = variables.get(link.getTarget());
			if (source != null && target != null)
			{
				LinkVariable linkVariable = StorydrivenUtil
						.addLinkVariable(storyNode, source, target, link.getInstanceOf());
				if (link.getSource() instanceof PSAnnotation)
				{
					linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(link));
				}
			}
		}

		// add path variables for all path
		for (PSPath path : SpecificationUtil.getPaths(pattern))
		{
			ObjectVariable source = variables.get(path.getSource());
			ObjectVariable target = variables.get(path.getTarget());
			if (source != null && target != null)
			{
				// FIXME: move utility method
				SDMUtil.createPath(path, source, target, storyNode.getStoryPattern());
			}
		}
	}


	public static void connectVariables(PSPatternSpecification pattern, StoryNode storyNode,
			Map<PSNode, ObjectVariable> coreVariables, Map<PSNode, ObjectVariable> setVariables)
	{
		// add link variables for all links
		for (PSLink link : SpecificationUtil.getLinks(pattern))
		{
			PSNode sourceNode = link.getSource();
			PSNode targetNode = link.getTarget();

			// only connect set variables
			if (setVariables.containsKey(sourceNode) || setVariables.containsKey(targetNode))
			{
				ObjectVariable source = setVariables.get(sourceNode);
				if (source == null)
				{
					source = coreVariables.get(sourceNode);
				}

				ObjectVariable target = setVariables.get(targetNode);
				if (target == null)
				{
					target = coreVariables.get(targetNode);
				}

				if (source != null && target != null)
				{
					LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyNode, source, target,
							link.getInstanceOf());
					if (link.getSource() instanceof PSAnnotation)
					{
						linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(link));
					}
				}
			}
		}

		// add path variables for all path
		for (PSPath path : SpecificationUtil.getPaths(pattern))
		{
			PSNode sourceNode = path.getSource();
			PSNode targetNode = path.getTarget();

			// only connect set variables
			if (setVariables.containsKey(sourceNode) || setVariables.containsKey(targetNode))
			{
				ObjectVariable source = setVariables.get(sourceNode);
				if (source == null)
				{
					source = coreVariables.get(sourceNode);
				}

				ObjectVariable target = setVariables.get(targetNode);
				if (target == null)
				{
					target = coreVariables.get(targetNode);
				}
				if (source != null && target != null)
				{
					// FIXME: move utility method
					SDMUtil.createPath(path, source, target, storyNode.getStoryPattern());
				}
			}
		}
	}


	public static String getValidName(PSPatternSpecification pattern, String initial)
	{
		// get not available names
		Collection<String> forbidden = SpecificationUtil.getAllNames(pattern);

		// try initial name at first
		String name = initial;

		// append an index when forbidden
		int i = 1;
		while (forbidden.contains(name))
		{
			name = initial + Constants.INFIX + i;
			i++;
		}

		return name;
	}


	public static ActivityEdge addTransition(ActivityNode source, ActivityNode target, EdgeGuard guard)
	{
		ActivityEdge element = ACTIVITIES.createActivityEdge();

		element.setGuard(guard);
		element.setSource(source);
		element.setTarget(target);

		element.setOwningActivity(source.getOwningActivity());

		return element;
	}


	public static ActivityFinalNode addActivityFinalNode(Activity container, String expressionText)
	{
		Expression expression = ExpressionsUtil.createOCLExpression(expressionText);

		return addActivityFinalNode(container, expression);
	}
	
	public static ActivityFinalNode addSuccessFinalNode(Activity container) {
		ActivityFinalNode element = ACTIVITIES.createActivityFinalNode();
		element.setOwningActivity(container);
		element.setSuccess(true);
		element.getReturnValues().add(ExpressionsUtil.createOCLExpression("true"));
		return element;
	}


	public static ActivityFinalNode addFailureFinalNode(Activity container)
	{
	   ActivityFinalNode element = ACTIVITIES.createActivityFinalNode();
		element.setOwningActivity(container);
		element.setSuccess(false);
		element.getReturnValues().add(ExpressionsUtil.createOCLExpression("false"));
		return element;
	}


	private static ActivityFinalNode addActivityFinalNode(Activity container, Expression expression)
	{
	   ActivityFinalNode element = ACTIVITIES.createActivityFinalNode();

		element.setOwningActivity(container);
		element.getReturnValues().add(expression);

		return element;
	}


	public static EParameter getEParameter(Activity activity, String name)
	{
		return EcoreUtil.getEParameter(activity.getOwningOperation().getOperation(), name);
	}


	public static StoryNode addStoryNode(Activity container, String name, Object... args)
	{
		return addStoryNode(container, String.format(name, args));
	}


	public static StoryNode addStoryNode(Activity container, String name)
	{
		ModifyingStoryNode element = ACTIVITIES.createModifyingStoryNode();

		element.setName(name);

		StorydrivenUtil.addStoryPattern(element);

		container.getOwnedActivityNodes().add(element);

		return element;
	}


	public static ActivityCallNode addActivityCallNode(Activity container, Callable callable)
	{
		ActivityCallNode activity = ACTIVITIES.createActivityCallNode();

		activity.setOwningActivity(container);
		activity.setCallee(container);
		activity.getCalledActivities().add((Activity)callable);

		return activity;
	}


	public static InitialNode addInitialNode(Activity container)
	{
	   InitialNode element = ACTIVITIES.createInitialNode();

		element.setOwningActivity(container);
		element.setName(NameUtil.getName(container.getOwningOperation().getOperation()));

		return element;
	}


	public static ActivityEdge addTransition(ActivityNode source, ActivityNode target)
	{
		return addTransition(source, target, EdgeGuard.NONE);
	}


	private static ObjectVariable addObjectVariable(StoryPattern container, String name, EClass type,
			BindingState state, BindingOperator operator, BindingSemantics semantics)
	{
		ObjectVariable element = PATTERNS.createObjectVariable();

		element.setName(name);
		element.setClassifier(type);

		element.setBindingOperator(operator);
		element.setBindingState(state);
		element.setBindingSemantics(semantics);

		element.setPattern(container);

		return element;
	}


	public static LinkVariable addLinkVariable(StoryNode container, ObjectVariable source, ObjectVariable target,
			EReference type)
	{
		LinkVariable element = PATTERNS.createLinkVariable();

		element.setSource(source);
		element.setTarget(target);
		element.setTargetEnd(type);
		element.setName(type.getName());

		element.setPattern(container.getStoryPattern());

		return element;
	}


	private static StoryPattern addStoryPattern(ModifyingStoryNode container)
	{
		StoryPattern element = PATTERNS.createStoryPattern();

		container.setOwnedRule(element);

		return element;
	}


	public static ParameterBinding createParameterBindingForwarding(EParameter parameter, EParameter forwarded,
			Invocation invocation)
	{
		Expression expression = ExpressionsUtil.createParameterExpression(forwarded);

		ParameterBinding binding = createParameterBinding(parameter, expression);
		binding.setInvocation(invocation);

		return binding;
	}


	public static ParameterBinding createParameterBinding(EParameter parameter, PrimitiveVariable variable,
			Invocation invocation)
	{
		// create expression
		// FIXME: OUTSOURCE
		PrimitiveVariableExpression expression = PatternsExpressionsFactory.eINSTANCE.createPrimitiveVariableExpression();
		expression.setPrimitiveVariable(variable);

		// create binding
		ParameterBinding binding = createParameterBinding(parameter, expression);
		binding.setInvocation(invocation);

		return binding;
	}


	public static ParameterBinding createParameterBinding(EParameter parameter, ObjectVariable variable,
			Invocation invocation)
	{
		ParameterBinding element = createParameterBinding(parameter, variable);

		element.setInvocation(invocation);

		return element;
	}


	private static ParameterBinding createParameterBinding(EParameter parameter, ObjectVariable variable)
	{
		// FIXME: OUTSOURCE
		ObjectVariableExpression expression = PatternsExpressionsFactory.eINSTANCE.createObjectVariableExpression();

		expression.setObject(variable);

		return createParameterBinding(parameter, expression);
	}


	private static ParameterBinding createParameterBinding(EParameter parameter, Expression expression)
	{
		ParameterBinding element = CALLS.createParameterBinding();

		element.setValueExpression(expression);
		element.setParameter(parameter);

		return element;
	}


	public static ObjectVariable addObjectVariable(StoryNode container, String name, EClass classifier)
	{
		return addObjectVariable(container.getStoryPattern(), name, classifier, BindingState.UNBOUND,
				BindingOperator.CHECK_ONLY, BindingSemantics.MANDATORY);
	}


	public static Constraint addConstraint(ObjectVariable variable, PSBooleanConstraint nodeConstraint)
	{
		Constraint constraint = PATTERNS.createConstraint();

		Expression expression = null;
		if (nodeConstraint instanceof PSAttributeConstraint)
		{
			expression = ExpressionsUtil.createAttributeExpression(variable, (PSAttributeConstraint) nodeConstraint);
		}
		else if (nodeConstraint instanceof PSMetricConstraint)
		{
			expression = ExpressionsUtil.createMetricExpression((PSMetricConstraint) nodeConstraint);
			return null;
		}
		constraint.setConstraintExpression(expression);

		constraint.setObjectVariable(variable);


		return constraint;
	}


	public static PrimitiveVariable addPrimitiveVariable(StoryNode storyNode, String name, EDataType classifier)
	{
		PrimitiveVariable variable = PATTERNS.createPrimitiveVariable();

		variable.setName(name);
		variable.setClassifier(classifier);

		variable.setPattern(storyNode.getStoryPattern());

		return variable;
	}


	public static Constraint addSetResultsExpression(PSPatternSpecification pattern, StoryNode storyNode)
	{
		// create expression
		String name = getValidName(pattern, Constants.VAR_SET_RESULT_CHECK);
		Expression expression = ExpressionsUtil.createOCLExpression(name + "=true"); //$NON-NLS-1$

		// create constraint
		Constraint constraint = PATTERNS.createConstraint();
		constraint.setConstraintExpression(expression);
		constraint.setPattern(storyNode.getStoryPattern());
		return constraint;
	}


	public static Map<String, PSCombinedFragment> getSetFragmentsMap(PSPatternSpecification pattern)
	{
		Map<String, PSCombinedFragment> result = new HashMap<String, PSCombinedFragment>();
		int i = 0;
		for (PSCombinedFragment fragment : pattern.getCombinedFragments())
		{
			if (ModifierType.SET.equals(fragment.getKind()) && !ModelHelper.isContainedInSetFragment(fragment))
			{
				/*
				 * Set fragments that are contained in other set fragments can be ignored here. Their setSizeExpression will
				 * be handled later separately.
				 */
				result.put(Constants.PREFIX_SET + i, fragment);
				i++;
			}
		}

		return result;
	}
}
