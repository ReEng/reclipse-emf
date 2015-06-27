package org.reclipse.structure.generator.steps;


import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.reclipse.structure.generator.util.Constants;
import org.reclipse.structure.generator.util.IGenerator;
import org.reclipse.structure.generator.util.NameUtil;
import org.reclipse.structure.generator.util.StorydrivenUtil;
import org.reclipse.structure.generator.util.more.Counter;
import org.reclipse.structure.generator.util.more.ExprUtil;
import org.reclipse.structure.generator.util.more.SDMUtil;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.util.ModelHelper;
import org.storydriven.core.expressions.common.ArithmeticExpression;
import org.storydriven.core.expressions.common.LiteralExpression;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityFinalNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.InitialNode;
import org.storydriven.storydiagrams.activities.StoryNode;
import org.storydriven.storydiagrams.patterns.BindingSemantics;
import org.storydriven.storydiagrams.patterns.BindingState;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.PrimitiveVariable;
import org.storydriven.storydiagrams.patterns.StoryPattern;
import org.storydriven.storydiagrams.patterns.expressions.PatternsExpressionsFactory;
import org.storydriven.storydiagrams.patterns.expressions.PrimitiveVariableExpression;


public class GetSizeOfNestedSetStep implements Constants
{
	private final IGenerator generator;


	private Map<PSNode, ObjectVariable> variables;

	private Counter counter;


	public GetSizeOfNestedSetStep(IGenerator generator)
	{
		this.generator = generator;
	}


	public void generate(Activity container, PSCombinedFragment outer, PSCombinedFragment inner, String paramName2,
			Map<PSNode, ObjectVariable> variables, Counter counter)
	{
		generator.debug("Generating '%1s'...", NameUtil.getName(container));

		this.variables = variables;
		this.counter = counter;

		// add 'start' node
		InitialNode initialNode = StorydrivenUtil.addInitialNode(container);

		// add 'initialize size' node: initializes the size variable
		StoryNode initializeSizeNode = addInitializeSizeNode(container);

		// transit from 'start' to 'initialize size'
		StorydrivenUtil.addTransition(initialNode, initializeSizeNode);

		// add 'iterate over annotation set' node
		StoryNode iterateOverAnnotationSetActivity = addIterateOverAnnotationSetNode(container, paramName2);

		// transit from 'initialize size' to 'iterate over annotation set'
		StorydrivenUtil.addTransition(initializeSizeNode, iterateOverAnnotationSetActivity);

		// add 'reset size' node: resets the size variable
		StoryNode resetSizeNode = addResetSizeNode(container);

		// transit [EACH_TIME] from 'iterate over annotation set' to 'reset size'
		StorydrivenUtil.addTransition(iterateOverAnnotationSetActivity, resetSizeNode, EdgeGuard.EACH_TIME);

		// add 'iterate over inner' node: iterates through the inner set instances
		StoryNode iterateOverInnerNode = addIterateOverInnerNode(container, outer, inner);

		// transit from 'reset size' to 'iterate over inner'
		StorydrivenUtil.addTransition(resetSizeNode, iterateOverInnerNode);

		// add 'iterate over matched annotations' node: iterates over all annotations that contain the bound objects
		StoryNode iterateOverMatchedAnnotationsNode = addIterateOverMatchedAnnotationsNode(container, outer, inner,
				paramName2);

		// add 'increment counter' node
		StoryNode incrementCounterNode = addIncrementCounterNode(container);

		// transit [EACH_TIME] from 'iterate over matched annotations' to 'increment counter'
		StorydrivenUtil.addTransition(iterateOverMatchedAnnotationsNode, incrementCounterNode, EdgeGuard.EACH_TIME);

		// transit from 'increment counter' to 'iterate over matched annotations'
		StorydrivenUtil.addTransition(incrementCounterNode, iterateOverMatchedAnnotationsNode);

		// transit from 'iterate over inner' to 'iterate over matched annotations'
		StorydrivenUtil.addTransition(iterateOverInnerNode, iterateOverMatchedAnnotationsNode);

		// add 'check size' node
		StoryNode checkSizeNode = addCheckSizeNode(container, inner);

		// transit from 'reset size' to 'check size'
		StorydrivenUtil.addTransition(iterateOverMatchedAnnotationsNode, checkSizeNode, EdgeGuard.END);

		// add 'size check failed stop' node: size check failed
		ActivityFinalNode sizeCheckFailedFinalNode = StorydrivenUtil.addFailureFinalNode(container);

		// transit from 'check size' to 'iterate over annotation set'
		StorydrivenUtil.addTransition(checkSizeNode, iterateOverAnnotationSetActivity, EdgeGuard.SUCCESS);

		// transit from 'check size' to 'size check failed stop'
		StorydrivenUtil.addTransition(checkSizeNode, sizeCheckFailedFinalNode, EdgeGuard.FAILURE);

		// add 'size check succeeded stop' node
		ActivityFinalNode sizeCheckSucceededFinalNode = StorydrivenUtil.addSuccessFinalNode(container);

		// transit from 'check size' to 'size check succeeded stop'
		StorydrivenUtil.addTransition(iterateOverAnnotationSetActivity, sizeCheckSucceededFinalNode, EdgeGuard.END);
	}


	private StoryNode addInitializeSizeNode(Activity container)
	{
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Initialize size variable");

		StoryPattern storyPattern = element.getStoryPattern();

		PrimitiveVariable size = SDMUtil.createPrimitiveObject(EcorePackage.eINSTANCE.getEDouble(), VAR_SIZE, false,
				storyPattern);
		LiteralExpression expr = ExprUtil.eInt(0);
		size.setBindingExpression(expr);

		return element;
	}


	private StoryNode addIterateOverAnnotationSetNode(Activity container, String setName)
	{
		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Match annotation");
		element.setForEach(true);

		// create result set variable
		EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable resultSet = StorydrivenUtil.addObjectVariable(element, setName, classifier);
		resultSet.setBindingState(BindingState.BOUND);

		// create annotation variable
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable annotation = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION, classifier);

		// link them
		EReference type = AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS;
		StorydrivenUtil.addLinkVariable(element, resultSet, annotation, type);

		return element;
	}


	private StoryNode addResetSizeNode(Activity container)
	{
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Create size variable");

		StoryPattern storyPattern = element.getStoryPattern();

		PrimitiveVariable size = SDMUtil.createPrimitiveObject(EcorePackage.eINSTANCE.getEDouble(), VAR_SIZE, false,
				storyPattern);
		PrimitiveVariableExpression varExpression = PatternsExpressionsFactory.eINSTANCE.createPrimitiveVariableExpression();
		varExpression.setPrimitiveVariable(size);
		size.setBindingExpression(ExprUtil.eDouble(0d));

		return element;
	}


	private StoryNode addIterateOverInnerNode(Activity container, PSCombinedFragment parent, PSCombinedFragment child)
	{
		// create the story node
		StoryNode storyActivity = StorydrivenUtil.addStoryNode(container, "Match bound objects");

		// add 'annotation' variable
		EClass classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable annotation = StorydrivenUtil.addObjectVariable(storyActivity, VAR_ANNOTATION, classifier);
		annotation.setBindingState(BindingState.BOUND);

		// link the objects
		linkOuterBoundObjects(storyActivity.getStoryPattern(), annotation, parent, child, false);

		return storyActivity;
	}


	private StoryNode addIterateOverMatchedAnnotationsNode(Activity container, PSCombinedFragment parentFragment,
			PSCombinedFragment childFragment, String setName)
	{
		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(container,
				"Iterate over annotations that contain the bound objects");
		element.setForEach(true);

		// add 'result set' node
		EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable resultSet = StorydrivenUtil.addObjectVariable(element, setName, classifier);
		resultSet.setBindingState(BindingState.BOUND);

		// add 'SetInstanceAnnotation' node
		// FIXME: please use constants!
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable annotation = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION + "2", classifier);

		StoryPattern storyPattern = element.getStoryPattern();

		linkBoundObjects(storyPattern, annotation, childFragment, false);
		linkOuterBoundObjects(storyPattern, annotation, parentFragment, childFragment, true);

		// link from 'result set' to 'annotation'
		SDMUtil.createSetInstanceAnnotationResultSetLink(resultSet, annotation, false, storyPattern);

		return element;
	}


	private void linkOuterBoundObjects(StoryPattern container, ObjectVariable element, PSCombinedFragment outer,
			PSCombinedFragment inner, boolean bound)
	{
		// run through all PSNodes in the set fragment
		Iterator<PSCombinedFragmentItem> iter = outer.getChildren().iterator();
		while (iter.hasNext())
		{
			PSCombinedFragmentItem item = iter.next();
			if (item instanceof PSNode)
			{
				PSNode psNode = (PSNode) item;
				if (!inner.getChildren().contains(psNode))
				{
					addNode(container, element, bound, psNode);
				}
			}
		}

		addConnections(container, outer, inner);
	}


	private void linkBoundObjects(StoryPattern container, ObjectVariable element, PSCombinedFragment fragment,
			boolean bound)
	{
		// run through all PSNodes in the set fragment
		Iterator<PSCombinedFragmentItem> iter = fragment.getChildren().iterator();
		while (iter.hasNext())
		{
			PSCombinedFragmentItem item = iter.next();
			if (item instanceof PSNode)
			{
				PSNode psNode = (PSNode) item;
				addNode(container, element, bound, psNode);
			}
		}

		addConnections(container, fragment, null);
	}


	private void addNode(StoryPattern container, ObjectVariable element, boolean bound, PSNode psNode)
	{
		if (psNode.getModifier() != ModifierType.NEGATIVE)
		{
			if (psNode.getName() == null || psNode.getName().equals(""))
			{
				throw new IllegalStateException("PSNode object \"" + psNode + "\" has no name.");
			}

			ObjectVariable boundObject = null;
			if (psNode instanceof PSObject)
			{
				boundObject = SDMUtil.createObject((PSObject) psNode, bound, true, container, variables, counter);
			}
			else if (psNode instanceof PSAnnotation && !ModelHelper.isCreate((PSAnnotation) psNode))
			{
				generator.getAnnotationClass(((PSAnnotation) psNode).getType());
				EClass annoType = generator.getAnnotationClass(((PSAnnotation) psNode).getType());
				boundObject = SDMUtil.createAnnotationObject((PSAnnotation) psNode, annoType, bound, false, container,
						variables, counter);
			}
			if (psNode.getModifier() == ModifierType.ADDITIONAL && boundObject != null)
			{
				// trigger object should not be optional
				boundObject.setBindingSemantics(BindingSemantics.MANDATORY);
			}

			// add the boundObjects link
			if (boundObject != null)
			{
				SDMUtil.createBoundObjectsLink(element, boundObject, psNode, false, container);
			}
		}
	}


	private void addConnections(StoryPattern container, PSCombinedFragment outer, PSCombinedFragment inner)
	{
		for (PSCombinedFragmentItem combinedFragmentItem : outer.getChildren())
		{
			PSNode node = null;
			if (combinedFragmentItem instanceof PSNode)
			{
				node = (PSNode) combinedFragmentItem;
				// add connections between elements inside the set
				for (PSConnection connection : node.getOutgoing())
				{
					PSNode targetNode = connection.getTarget();
					if (ModelHelper.nodeIsInSet(targetNode, outer))
					{
						// if the parentFragment contains a childFragment, the connections to or from
						// elements that are contained in the childFragment have to be ignored here
						if (!(inner != null && (ModelHelper.nodeIsInSet(targetNode, inner) || ModelHelper.nodeIsInSet(node,
								inner))))
						{
							ObjectVariable source = variables.get(connection.getSource());
							ObjectVariable target = variables.get(connection.getTarget());
							if (connection instanceof PSLink)
							{
								PSLink link = (PSLink) connection;
								SDMUtil.createLink(source, target, link.getInstanceOf(), link.getQualifier(), false,
										BindingSemantics.MANDATORY, container);
							}
							else if (connection instanceof PSPath)
							{
								SDMUtil.createPath((PSPath) connection, source, target, container);
							}
						}
					}
				}
			}
		}
	}


	private StoryNode addIncrementCounterNode(Activity container)
	{
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Increment SIZE variable");

		StoryPattern storyPattern = element.getStoryPattern();
		PrimitiveVariable size = SDMUtil.createPrimitiveObject(EcorePackage.eINSTANCE.getEInt(), VAR_SIZE, false,
				storyPattern);
		PrimitiveVariableExpression varExpression = PatternsExpressionsFactory.eINSTANCE.createPrimitiveVariableExpression();
		varExpression.setPrimitiveVariable(size);
		ArithmeticExpression incrementation = ExprUtil.plus(varExpression, ExprUtil.eInt(1));
		size.setBindingExpression(incrementation);

		return element;
	}


	private StoryNode addCheckSizeNode(Activity container, PSCombinedFragment set)
	{
		StoryNode element = StorydrivenUtil.addStoryNode(container,
				"Check size expression of nested set for current set instance.");
		StoryPattern storyPattern = element.getStoryPattern();

		PSNodeConstraint nodeConstraint = set.getConstraint();
		if (nodeConstraint instanceof PSMetricConstraint
				&& "SIZE".equals(((PSMetricConstraint) nodeConstraint).getMetricAcronym()))
		{
			PSMetricConstraint metricConstraint = (PSMetricConstraint) nodeConstraint;
			StringBuffer expression = new StringBuffer();

			expression.append(VAR_SIZE + ModelHelper.getReadable(metricConstraint.getOperator()) + " "
					+ metricConstraint.getValueExpression());
			SDMUtil.createConstraint(expression.toString(), storyPattern);
		}

		return element;
	}
}
