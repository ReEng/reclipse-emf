package org.reclipse.structure.generator.steps;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EParameter;
import org.reclipse.structure.generator.util.Constants;
import org.reclipse.structure.generator.util.ExpressionsUtil;
import org.reclipse.structure.generator.util.IGenerator;
import org.reclipse.structure.generator.util.NameUtil;
import org.reclipse.structure.generator.util.StorydrivenUtil;
import org.reclipse.structure.generator.util.more.Counter;
import org.reclipse.structure.generator.util.more.SDMUtil;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.util.ModelHelper;
import org.storydriven.core.expressions.Expression;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.InitialNode;
import org.storydriven.storydiagrams.activities.StoryNode;
import org.storydriven.storydiagrams.patterns.AbstractVariable;
import org.storydriven.storydiagrams.patterns.BindingOperator;
import org.storydriven.storydiagrams.patterns.BindingSemantics;
import org.storydriven.storydiagrams.patterns.BindingState;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.StoryPattern;


/**
 * The findSubGraphInSetFragmentMethod fills an empty set with it's elements (instances). Each matching sub graph is an
 * element/instance.
 */
public class FindSetSubGraphStep implements Constants
{
	private final IGenerator generator;

	private Map<PSNode, ObjectVariable> variables;


	private Activity container;

	private PSCombinedFragment fragment;

	private PSNode trigger;


	private Counter counter;


	public FindSetSubGraphStep(IGenerator generator)
	{
		this.generator = generator;

		variables = new HashMap<PSNode, ObjectVariable>();
	}


	public void generate(Activity container, PSCombinedFragment fragment, PSNode trigger, Counter counter)
	{
		generator.debug("Generating '%1s'...", NameUtil.getName(container));

		this.container = container;
		this.fragment = fragment;
		this.trigger = trigger;

		this.counter = counter;

		// create the start activity
		InitialNode initialNode = StorydrivenUtil.addInitialNode(container);

		// create the each activity that binds set instances
		ActivityNode checkPatternNode = addCheckPatternNode();

		// transit from 'start' to 'check pattern'
		StorydrivenUtil.addTransition(initialNode, checkPatternNode);

		// create the activity that creates a SetInstanceAnnotation
		ActivityNode createInstanceNode = addCreateInstanceAnnotationNode();

		// transit [EACH_TIME] from 'check pattern' to 'create instance'
		StorydrivenUtil.addTransition(checkPatternNode, createInstanceNode, EdgeGuard.EACH_TIME);

		// transit from 'create instance' back to 'check pattern'
		StorydrivenUtil.addTransition(createInstanceNode, checkPatternNode);

		// create final node
		ActivityNode finalNode = StorydrivenUtil.addActivityFinalNode(container, VAR_RESULT_SET);

		// transit from 'check pattern'
		StorydrivenUtil.addTransition(checkPatternNode, finalNode, EdgeGuard.END);

		// clear cached variables
		variables.clear();
	}


	private StoryNode addCheckPatternNode()
	{
		// create the story node
		StoryNode storyActivity = StorydrivenUtil.addStoryNode(container,
				"Apply graph grammar rule for the subgraph in the set fragment.");
		storyActivity.setForEach(true);
		StoryPattern storyPattern = storyActivity.getStoryPattern();

		// mapping PSDiagramItems to ASGElements to store the objects already created
		variables.clear();

		// first create story items for nodes in set fragment...
		for (PSCombinedFragmentItem item : fragment.getChildren())
		{
			// first, only consider non-optional elements
			if (item instanceof PSNode)
			{
				PSNode node = (PSNode) item;

				SDMUtil.addNodeToStoryPattern(storyPattern, node, variables, counter, generator);
			}
			else if (item instanceof PSSpecificationConstraint
					&& !ModelHelper.isSearchForThisOptional((PSSpecificationConstraint) item))
			{
				SDMUtil.createConstraint(((PSSpecificationConstraint) item).getExpression(), storyPattern);
			}
		}

		// ...then for links
		for (PSCombinedFragmentItem combinedFragmentItem : fragment.getChildren())
		{
			PSNode node = null;
			if (combinedFragmentItem instanceof PSNode)
			{
				node = (PSNode) combinedFragmentItem;
				// add connections between elements inside the set and connections to
				// elements that are contained in no set
				for (PSConnection connection : node.getOutgoing())
				{
					PSNode targetNode = connection.getTarget();
					if (ModelHelper.nodeIsInSet(targetNode, fragment) || ModelHelper.nodeIsInNoSet(targetNode))
					{
						if (ModelHelper.nodeIsInNoSet(targetNode) && variables.get(targetNode) == null)
						{
							SDMUtil.addNodeToStoryPattern(storyPattern, targetNode, variables, counter, generator);
						}

						ObjectVariable source = variables.get(connection.getSource());
						ObjectVariable target = variables.get(connection.getTarget());
						if (connection instanceof PSLink)
						{
							PSLink link = (PSLink) connection;
							SDMUtil.createLink(source, target, link.getInstanceOf(), link.getQualifier(), false,
									BindingSemantics.MANDATORY, storyPattern);
						}
						else if (connection instanceof PSPath)
						{
							SDMUtil.createPath((PSPath) connection, source, target, storyPattern);
						}
					}
				}

				// add connections from elements that are contained in no set
				for (PSConnection connection : node.getIncoming())
				{
					PSNode sourceNode = connection.getSource();
					if (!(sourceNode instanceof PSAnnotation && ModelHelper.isCreate((PSAnnotation) sourceNode)))
					{
						if (ModelHelper.nodeIsInNoSet(sourceNode))
						{
							if (variables.get(sourceNode) == null)
							{
								SDMUtil.addNodeToStoryPattern(storyPattern, sourceNode, variables, counter, generator);
							}

							ObjectVariable source = variables.get(connection.getSource());
							ObjectVariable target = variables.get(connection.getTarget());

							if (connection instanceof PSLink)
							{
								PSLink link = (PSLink) connection;
								SDMUtil.createLink(source, target, link.getInstanceOf(), link.getQualifier(), false,
										BindingSemantics.MANDATORY, storyPattern);
							}
							else if (connection instanceof PSPath)
							{
								SDMUtil.createPath((PSPath) connection, source, target, storyPattern);
							}
						}
					}
				}
			}
		}

		// ObjectVariable triggerObject = SDMUtil.createObject(this.trigger, true, false, storyPattern, storyItems,
		// counter, annotationsPackage);
		ObjectVariable triggerObject = (ObjectVariable) SDMUtil.findVariableByName(storyPattern, trigger.getName());
		if (triggerObject == null)
		{
			triggerObject = SDMUtil.createObject(trigger, true, false, storyPattern, variables, counter, generator);
		}

		// configure trigger
		SDMUtil.configureTrigger(triggerObject, trigger, storyPattern);
		Expression expr = ExpressionsUtil
				.createParameterExpression(StorydrivenUtil.getEParameter(container, VAR_CONTEXT));
		triggerObject.setBindingExpression(expr);

		for (PSConnection con : trigger.getOutgoing())
		{
			PSNode target = con.getTarget();

			// determine trigger connection to the set
			if (fragment.getChildren().contains(target))
			{
				boolean done = false;
				// create a link binding
				for (AbstractVariable var : storyPattern.getVariables())
				{
					if (var.getName().equals(target.getName()))
					{
						SDMUtil.createLink(triggerObject, (ObjectVariable) var, ((PSLink) con).getInstanceOf(),
								((PSLink) con).getQualifier(), false, BindingSemantics.MANDATORY, storyPattern);
						done = true;
					}
				}
				if (done)
				{
					break;
				}
			}
		}

		return storyActivity;
	}


	private ActivityNode addCreateInstanceAnnotationNode()
	{
		// create the story pattern
		StoryNode storyActivity = StorydrivenUtil.addStoryNode(container,
				"Create a new annotation for the matched pattern");

		// create 'result set' variable
		EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable annotationResultSet = StorydrivenUtil.addObjectVariable(storyActivity, VAR_RESULT_SET, classifier);
		annotationResultSet.setBindingState(BindingState.BOUND);

		EParameter param = StorydrivenUtil.getEParameter(container, VAR_RESULT_SET);
		Expression annoationSetParam = ExpressionsUtil.createParameterExpression(param);
		annotationResultSet.setBindingExpression(annoationSetParam);

		// add 'set instance' variable
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable setInstanceVariable = StorydrivenUtil.addObjectVariable(storyActivity, VAR_SET_ANNOTATION,
				classifier);
		setInstanceVariable.setBindingOperator(BindingOperator.CREATE);

		StoryPattern storyPattern = storyActivity.getStoryPattern();

		// add constraints
		for (PSCombinedFragmentItem item : fragment.getChildren())
		{
			if (item instanceof PSSpecificationConstraint)
			{
				PSSpecificationConstraint constraint = (PSSpecificationConstraint) item;

				if (constraint.getExpression().toLowerCase().startsWith(PREFIX_ADDITIONAL_CONSTRAINT))
				{
					SDMUtil.createConstraint(constraint.getExpression(), storyPattern);
				}
			}
		}

		linkBoundObjects(storyActivity, setInstanceVariable);


		SDMUtil.createLink(annotationResultSet, setInstanceVariable,
				AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS, null, true, BindingSemantics.MANDATORY,
				storyPattern);

		return storyActivity;
	}


	private void linkBoundObjects(StoryNode storyNode, ObjectVariable setAnnotationVariable)
	{
		// run through all PSNodes in the set fragment
		for (PSCombinedFragmentItem item : fragment.getChildren())
		{
			if (item instanceof PSNode)
			{
				PSNode psNode = (PSNode) item;
				if (!ModifierType.NEGATIVE.equals(psNode.getModifier()))
				{
					ObjectVariable boundObject = null;
					if (psNode instanceof PSObject)
					{
						boundObject = SDMUtil.createObject((PSObject) psNode, true, true, storyNode.getStoryPattern(),
								variables, counter);
					}
					else if (psNode instanceof PSAnnotation && !ModelHelper.isCreate((PSAnnotation) psNode))
					{
						EClass annoType = generator.getAnnotationClass(((PSAnnotation) psNode).getType());
						boundObject = SDMUtil.createAnnotationObject((PSAnnotation) psNode, annoType, true, false,
								storyNode.getStoryPattern(), variables, counter);
					}

					if (ModifierType.ADDITIONAL.equals(psNode.getModifier()) && boundObject != null)
					{
						// trigger object should not be optional
						boundObject.setBindingSemantics(BindingSemantics.MANDATORY);
					}

					// add the boundObjects link
					if (boundObject != null)
					{
						SDMUtil.createBoundObjectsLink(setAnnotationVariable, boundObject, psNode, true,
								storyNode.getStoryPattern());
					}
				}
			}
		}
	}
}
