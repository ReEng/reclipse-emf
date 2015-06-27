package org.reclipse.structure.generator.steps;


import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.reclipse.structure.generator.util.Constants;
import org.reclipse.structure.generator.util.ExpressionsUtil;
import org.reclipse.structure.generator.util.IGenerator;
import org.reclipse.structure.generator.util.NameUtil;
import org.reclipse.structure.generator.util.StorydrivenUtil;
import org.reclipse.structure.generator.util.more.Counter;
import org.reclipse.structure.generator.util.more.SDMUtil;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityFinalNode;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.InitialNode;
import org.storydriven.storydiagrams.activities.StoryNode;
import org.storydriven.storydiagrams.patterns.BindingOperator;
import org.storydriven.storydiagrams.patterns.BindingState;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.StoryPattern;


/**
 * Creates the matchPairsMethod that is responsible for matching overlapping set fragments.
 */
public class MatchPairsStep implements Constants
{
	private final IGenerator generator;


	private PSPatternSpecification pattern;

	private Map<PSNode, ObjectVariable> storyItems;

	private Counter counter;

	private PSCombinedFragment set1;

	private PSCombinedFragment set2;


	public MatchPairsStep(IGenerator generator)
	{
		this.generator = generator;
	}


	public void generate(Activity container, PSPatternSpecification pattern, PSCombinedFragment set1, String paramName1,
			PSCombinedFragment set2, String paramName2, Counter counter, Map<PSNode, ObjectVariable> storyItems)
	{
		generator.debug("Generating '%1s'...", NameUtil.getName(container));

		this.pattern = pattern;
		this.set1 = set1;
		this.set2 = set2;
		this.counter = counter;
		this.storyItems = storyItems;

		// add 'start' node
		InitialNode initialNode = StorydrivenUtil.addInitialNode(container);

		// add 'create result' node: create the new annotation set for the merged sets
		ActivityNode createResultNode = addCreateResultNode(container);

		// transit from 'start' to 'create result'
		StorydrivenUtil.addTransition(initialNode, createResultNode);

		// add 'match pairs' node: executes the pair matching
		ActivityNode matchPairsNode = addMatchPairsNode(container, paramName1, paramName2);

		// transit from 'create result' to 'match pairs'
		StorydrivenUtil.addTransition(createResultNode, matchPairsNode);

		// add 'add annotation' node
		ActivityNode addAnnotationNode = addAddAnnotationNode(container, paramName1);

		// transit from 'match pairs' to 'add annotation'
		StorydrivenUtil.addTransition(matchPairsNode, addAnnotationNode, EdgeGuard.EACH_TIME);

		// transit from 'add annotation' to 'match pairs'
		StorydrivenUtil.addTransition(addAnnotationNode, matchPairsNode);

		// add 'end' node
		ActivityFinalNode finalNode = StorydrivenUtil.addActivityFinalNode(container, VAR_RESULT_SET);

		// transit from 'match pairs' to 'end'
		StorydrivenUtil.addTransition(matchPairsNode, finalNode, EdgeGuard.END);
	}


	private static StoryNode addCreateResultNode(Activity container)
	{
		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Create annotation result set container");

		// add 'result set' node
		ObjectVariable resultSet = StorydrivenUtil.addObjectVariable(element, VAR_RESULT_SET,
				AnnotationsPackage.Literals.ANNOTATION_SET);
		resultSet.setBindingOperator(BindingOperator.CREATE);

		return element;
	}


	/**
	 * Creates the activity that executes the pair matching.
	 */
	private StoryNode addMatchPairsNode(Activity container, String paramName1, String paramName2)
	{
		// get data
		EReference annotationSetAnnotationsType = AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS;
		EReference annotationBoundObjectType = AnnotationsPackage.Literals.ASG_ANNOTATION__BOUND_OBJECTS;
		EParameter param1 = StorydrivenUtil.getEParameter(container, paramName1);
		EParameter param2 = StorydrivenUtil.getEParameter(container, paramName2);

		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Match pairs");
		element.setForEach(true);

		// create first AnnotationSet
		EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable annoSet1 = StorydrivenUtil.addObjectVariable(element, paramName1, classifier);
		annoSet1.setBindingState(BindingState.BOUND);
		annoSet1.setBindingExpression(ExpressionsUtil.createParameterExpression(param1));

		// create first SetInstanceAnnotation
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable anno1 = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION_FIRST, classifier);

		// link from first AnnotationSet to SetInstanceAnnotation
		StorydrivenUtil.addLinkVariable(element, annoSet1, anno1, annotationSetAnnotationsType);

		// create second AnnotationSet
		classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable annoSet2 = StorydrivenUtil.addObjectVariable(element, paramName2, classifier);
		annoSet2.setBindingState(BindingState.BOUND);
		annoSet2.setBindingExpression(ExpressionsUtil.createParameterExpression(param2));

		// create second SetInstanceAnnotation
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable anno2 = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION_SECOND, classifier);

		// link from second AnnotationSet to SetInstanceAnnotation
		StorydrivenUtil.addLinkVariable(element, annoSet2, anno2, annotationSetAnnotationsType);
		StoryPattern storyPattern = element.getStoryPattern();

		// add object variables for all overlapping nodes
		for (PSNode node : ModelHelper.getOverlappingNodes(set1, set2))
		{
			// create an object for each node in the overlapping part of the sets
			ObjectVariable boundObject = SDMUtil.addNodeToStoryPattern(storyPattern, node, this.storyItems, counter,
					generator);

			// bind from first SetInstanceAnnotation
			StorydrivenUtil.addLinkVariable(element, anno1, boundObject, annotationBoundObjectType);

			// bind from second SetInstanceAnnotation
			StorydrivenUtil.addLinkVariable(element, anno2, boundObject, annotationBoundObjectType);
		}

		return element;
	}


	/**
	 * Creates the activity that create a new annotation, binds all elements to it and adds to the new set.
	 */
	private StoryNode addAddAnnotationNode(Activity container, String paramName)
	{
		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Create and fill annotation and add it to new set");

		// create first 'SetInstanceAnnotation'
		EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable annotationObject = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION_FIRST, classifier);
		annotationObject.setClassifier(AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION);
		annotationObject.setBindingState(BindingState.BOUND);

		// create second 'SetInstanceAnnotation'
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable annotationObject2 = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION_SECOND, classifier);
		annotationObject.setBindingState(BindingState.BOUND);

		// create new 'SetInstanceAnnotation' for the merged elements
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable newInstanceAnnotationObject = StorydrivenUtil.addObjectVariable(element, VAR_NEW_ANNOTATION,
				classifier);
		newInstanceAnnotationObject.setBindingOperator(BindingOperator.CREATE);

		// create 'result set'
		classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable annotationResultSet = StorydrivenUtil.addObjectVariable(element, VAR_RESULT_SET, classifier);
		annotationResultSet.setBindingState(BindingState.BOUND);

		// link from 'result set' to 'new SetInstanceAnnotation'
		StorydrivenUtil.addLinkVariable(element, annotationResultSet, newInstanceAnnotationObject,
				AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS);

		// TODO: is it necessary to add the new annotation with all bound objects?
		addElementsFromBothSets(element.getStoryPattern(), newInstanceAnnotationObject, annotationObject,
				annotationObject2);

		// add annotations links from annotation set to anno0 and anno1
		SDMUtil.createSetInstanceAnnotationResultSetLink(annotationResultSet, annotationObject, true,
				element.getStoryPattern());
		SDMUtil.createSetInstanceAnnotationResultSetLink(annotationResultSet, annotationObject2, true,
				element.getStoryPattern());

		return element;
	}


	/**
	 * Adds all elements to the story pattern that are contained in set1 oder in set2 and creates links to their
	 * setInstanceAnnotations and the new setInstanceAnnotation.
	 */
	private void addElementsFromBothSets(StoryPattern storyPattern, ObjectVariable newAnno, ObjectVariable anno1,
			ObjectVariable anno2)
	{
		Iterator<PSNode> diagramElements = pattern.getNodes().iterator();
		while (diagramElements.hasNext())
		{
			PSNode node = diagramElements.next();
			if (node.getParents().contains(set1))
			{
				// item is in one of the set fragments, so it has to be added to the new merged set
				addNodeAndLinks(storyPattern, newAnno, anno1, node);
			}
			else if (node.getParents().contains(set2))
			{
				// item is in one of the set fragments, so it has to be added to the new merged set
				addNodeAndLinks(storyPattern, newAnno, anno2, node);
			}
		}
	}


	private void addNodeAndLinks(StoryPattern storyPattern, ObjectVariable newAnno, ObjectVariable anno1, PSNode node)
	{
		ObjectVariable boundObject = SDMUtil.addNodeToStoryPattern(storyPattern, node, storyItems, counter, generator);
		SDMUtil.createBoundObjectsLink(anno1, boundObject, node, false, storyPattern);
		SDMUtil.createBoundObjectsLink(newAnno, boundObject, node, true, storyPattern);
	}
}
