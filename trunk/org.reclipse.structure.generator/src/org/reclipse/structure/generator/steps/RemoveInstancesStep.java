package org.reclipse.structure.generator.steps;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.reclipse.structure.generator.util.Constants;
import org.reclipse.structure.generator.util.ExpressionsUtil;
import org.reclipse.structure.generator.util.IGenerator;
import org.reclipse.structure.generator.util.NameUtil;
import org.reclipse.structure.generator.util.StorydrivenUtil;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityFinalNode;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.InitialNode;
import org.storydriven.storydiagrams.activities.StoryNode;
import org.storydriven.storydiagrams.patterns.BindingOperator;
import org.storydriven.storydiagrams.patterns.BindingState;
import org.storydriven.storydiagrams.patterns.LinkVariable;
import org.storydriven.storydiagrams.patterns.ObjectVariable;


/**
 * Creates the activity that removes all instances from the annotationSets for set and overlapping set that are not in
 * the merged set because they do not match the specification.
 */
public class RemoveInstancesStep implements Constants
{
	private final IGenerator generator;


	public RemoveInstancesStep(IGenerator generator)
	{
		this.generator = generator;
	}


	public void generate(Activity container, PSPatternSpecification pattern, String setName, String mergedSetName)
	{
		generator.debug("Generating '%1s'...", NameUtil.getName(container));

		// add 'start' node
		InitialNode initialNode = StorydrivenUtil.addInitialNode(container);

		// add 'match annotations to annotation set': activity for annotations in annotationSet of set
		ActivityNode matchAnnotationsToAnnotationSetNode = addMatchAnnotationsToAnnotationSetNode(container, setName);

		// transit from 'start' to 'match annotations to annotation set'
		StorydrivenUtil.addTransition(initialNode, matchAnnotationsToAnnotationSetNode);

		// add 'check merged set' node: checks if the annotation is in the merged annotation set
		ActivityNode checkMergedSetNode = addCheckMergedSetNode(container, mergedSetName);

		// transit from 'match annotations to annotation set' to 'check merged set'
		StorydrivenUtil.addTransition(matchAnnotationsToAnnotationSetNode, checkMergedSetNode, EdgeGuard.EACH_TIME);

		// transit from 'check merged set' to 'match annotations to annotation set'
		StorydrivenUtil.addTransition(checkMergedSetNode, matchAnnotationsToAnnotationSetNode, EdgeGuard.SUCCESS);

		// add 'delete' node: deletes wrong annotations from the annotation set
		ActivityNode deleteNode = addDeleteNode(container, setName);

		// transit from 'check merged set' to 'delete'
		StorydrivenUtil.addTransition(checkMergedSetNode, deleteNode, EdgeGuard.FAILURE);

		// transit from 'delete' to 'match annotations to annotation set'
		StorydrivenUtil.addTransition(deleteNode, matchAnnotationsToAnnotationSetNode);

		// add 'stop' node
		ActivityFinalNode finalNode = StorydrivenUtil.addActivityFinalNode(container, setName);

		// transit from 'match annotations to set' to 'stop'
		StorydrivenUtil.addTransition(matchAnnotationsToAnnotationSetNode, finalNode, EdgeGuard.END);
	}


	private static StoryNode addMatchAnnotationsToAnnotationSetNode(Activity container, String setName)
	{
		// get data
		EParameter parameter = StorydrivenUtil.getEParameter(container, setName);
		EReference linkType = AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS;

		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Match annotation to '%1s'", setName);
		element.setForEach(true);

		// add 'result set' variable
		EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable resultSet = StorydrivenUtil.addObjectVariable(element, setName, classifier);
		// XXX: @type: this seems wrong to me, but SetInstanceAnnotationResultSet does not exist any longer
		resultSet.setBindingState(BindingState.BOUND);
		resultSet.setBindingExpression(ExpressionsUtil.createParameterExpression(parameter));

		// add 'set instance' variable
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable setInstance = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION, classifier);

		// link from 'set instance' to 'result set'
		StorydrivenUtil.addLinkVariable(element, resultSet, setInstance, linkType);

		return element;
	}


	private static ActivityNode addCheckMergedSetNode(Activity container, String mergedSetName)
	{
		// get data
		EReference linkType = AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS;
		EParameter parameter = StorydrivenUtil.getEParameter(container, mergedSetName);

		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Match annotation to '%1s'", mergedSetName);

		// add 'result set' variable
		EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable resultSet = StorydrivenUtil.addObjectVariable(element, mergedSetName, classifier);
		resultSet.setBindingState(BindingState.BOUND);
		resultSet.setBindingExpression(ExpressionsUtil.createParameterExpression(parameter));

		// add 'set instance' variable
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable setInstance = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION, classifier);
		setInstance.setBindingState(BindingState.BOUND);

		// link from 'set instance' to 'result set'
		StorydrivenUtil.addLinkVariable(element, resultSet, setInstance, linkType);

		return element;
	}


	private static ActivityNode addDeleteNode(Activity container, String setName)
	{
		// get data
		EReference linkType = AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS;
		EParameter parameter = StorydrivenUtil.getEParameter(container, setName);

		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(container, "Delete annotation from '%1s'", setName);

		// add 'result set' variable
		EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable resultSet = StorydrivenUtil.addObjectVariable(element, setName, classifier);
		resultSet.setBindingState(BindingState.BOUND);
		resultSet.setBindingExpression(ExpressionsUtil.createParameterExpression(parameter));

		// add 'set instance' variable
		classifier = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;
		ObjectVariable setInstance = StorydrivenUtil.addObjectVariable(element, VAR_ANNOTATION, classifier);
		setInstance.setBindingState(BindingState.BOUND);
		setInstance.setBindingOperator(BindingOperator.DESTROY);

		// add link
		LinkVariable link = StorydrivenUtil.addLinkVariable(element, resultSet, setInstance, linkType);
		link.setBindingOperator(BindingOperator.DESTROY);

		return element;
	}
}
