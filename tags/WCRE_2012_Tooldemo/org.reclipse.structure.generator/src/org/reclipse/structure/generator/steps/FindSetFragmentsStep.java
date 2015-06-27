package org.reclipse.structure.generator.steps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.reclipse.structure.generator.util.Constants;
import org.reclipse.structure.generator.util.EcoreUtil;
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
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;
import org.storydriven.core.expressions.TextualExpression;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityCallNode;
import org.storydriven.storydiagrams.activities.ActivityFinalNode;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.InitialNode;
import org.storydriven.storydiagrams.activities.StoryNode;
import org.storydriven.storydiagrams.patterns.BindingOperator;
import org.storydriven.storydiagrams.patterns.BindingSemantics;
import org.storydriven.storydiagrams.patterns.BindingState;
import org.storydriven.storydiagrams.patterns.Constraint;
import org.storydriven.storydiagrams.patterns.LinkVariable;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.PatternsFactory;
import org.storydriven.storydiagrams.patterns.StoryPattern;

public class FindSetFragmentsStep implements Constants {
	private final IGenerator generator;

	private final FindSetSubGraphStep findSubGraphMethodGenerator;

	private PSPatternSpecification pattern;

	private Map<PSNode, ObjectVariable> storyItems;

	private Counter counter;

	private Collection<String> annotationSets;

	private Activity activity;

	private EOperation findSetFragmentMethod;

	private Map<String, PSCombinedFragment> setFragments;

	public FindSetFragmentsStep(IGenerator generator) {
		this.generator = generator;

		findSubGraphMethodGenerator = new FindSetSubGraphStep(generator);
	}

	public void generate(Activity activity, PSPatternSpecification pattern) {
		generator.debug("Generating '%1s'...", NameUtil.getName(activity));

		this.pattern = pattern;
		this.storyItems = new HashMap<PSNode, ObjectVariable>();
		this.counter = new Counter();
		this.annotationSets = new ArrayList<String>();

		this.activity = activity;
		this.findSetFragmentMethod = activity.getOwningOperation().getOperation();

		// add 'start' node
		InitialNode initialNode = StorydrivenUtil.addInitialNode(activity);

		// add 'initialize' node: initializes all simple sets, nested sets and
		// overlapping sets will be transformed into
		// simple sets
		ActivityNode initializeSetsNode = addInitializeSetsNode();

		// transit from 'start' to 'initialize'
		StorydrivenUtil.addTransition(initialNode, initializeSetsNode);

		// make some
		ActivityNode lastNode = initializeSetsNode;
		Set<PSCombinedFragment> boundSets = new HashSet<PSCombinedFragment>();

		// add nodes for all sets
		for (String setName : setFragments.keySet()) {
			PSCombinedFragment set = setFragments.get(setName);
			PSNode connectedElement = ModelHelper.getConnectedElementInNoFragment(set);
			if (connectedElement != null) {
				// create the activity that binds a sets' elements to a set
				ActivityNode bindSetNode = addBindSetNode(setName, set, connectedElement, lastNode);
				lastNode = bindSetNode;
				boundSets.add(set);

				Collection<PSCombinedFragment> overlappingSets = getOverlappingSetFragments(set);
				for (PSCombinedFragment overlappingSet : overlappingSets) {
					if (boundSets.contains(overlappingSet)) {
						// create the activity that executes the pair matching
						// between to sets
						// (all set instances that cannot be matched will be
						// neglected)
						ActivityNode matchPairsNode = addMatchPairsNode(set, overlappingSet, lastNode);

						// create the activity that removes all instances from
						// the annotationSets for set
						// and overlapping set that are not in the merged set
						// because they do not match
						// the specification
						ActivityNode removeWrongInstancesNode = addRemoveWrongInstancesNode(set, overlappingSet,
								matchPairsNode);

						lastNode = removeWrongInstancesNode;
					}
				}
			}
		}

		// add 'check set size' node
		ActivityNode checkSetSizeNode = addCheckSetSizeNode();

		// transit from [last] to 'check set size' node
		StorydrivenUtil.addTransition(lastNode, checkSetSizeNode);

		// add 'remove sets' node: removes the created SetInstanceAnnotationSet
		// objects
		ActivityNode removeSetsNode = addRemoveSetsNode();

		// transit from 'check set size' to 'remove sets'
		StorydrivenUtil.addTransition(checkSetSizeNode, removeSetsNode, EdgeGuard.FAILURE);

		// add 'create result' node
		ActivityNode createResultNode = createCreateResultActivity();

		// transit from 'check set size' to 'create result'
		StorydrivenUtil.addTransition(checkSetSizeNode, createResultNode, EdgeGuard.SUCCESS);

		// add 'failed stop' node
		ActivityFinalNode failureFinalNode = StorydrivenUtil.addFailureFinalNode(activity);

		// transit from 'remove sets' to 'failed stop'
		StorydrivenUtil.addTransition(removeSetsNode, failureFinalNode);

		// add 'bind elements' node
		ActivityNode bindElementsNode = createBindElements(createResultNode);

		// add 'success stop' node
		ActivityFinalNode successFinalNode = StorydrivenUtil.addSuccessFinalNode(activity);

		// transit from 'bind elements' to 'success stop'
		StorydrivenUtil.addTransition(bindElementsNode, successFinalNode, EdgeGuard.END);
	}

	private ActivityNode createBindElements(ActivityNode sourceNode) {
		StoryNode lastNode = null;

		for (String key : setFragments.keySet()) {
			// create story node
			StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Find Bounded Elements From '%1s'", key);
			storyNode.setForEach(true);

			// get fragment
			PSCombinedFragment fragment = setFragments.get(key);

			// re-bind set annotation
			String name = PREFIX_ANNOTATION_SET + key;
			EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
			ObjectVariable setAnnotationVar = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
			setAnnotationVar.setBindingState(BindingState.BOUND);

			// match annotations
			name = StorydrivenUtil.getValidName(pattern, VAR_ANNOTATION + key);
			classifier = AnnotationsPackage.Literals.ASG_ANNOTATION;
			ObjectVariable setInstanceAnnotationVar = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);

			// link them
			EReference type = AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS;
			StorydrivenUtil.addLinkVariable(storyNode, setAnnotationVar, setInstanceAnnotationVar, type);

			// go through children nodes
			for (PSCombinedFragmentItem item : fragment.getChildren()) {
				if (item instanceof PSNode && !ModifierType.ADDITIONAL.equals(((PSNode) item).getModifier())) {
					name = item.getName();
					classifier = EcorePackage.Literals.EOBJECT;

					ObjectVariable variable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
					// variable.setBindingOperator(BindingOperator.CREATE);
					// variable.setBindingState(BindingState.BOUND);

					// link to set annotation
					type = AnnotationsPackage.Literals.ASG_ANNOTATION__BOUND_OBJECTS;
					LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyNode, setInstanceAnnotationVar,
							variable, type);
					linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(name));
				}
			}

			// create story node that binds the element
			StoryNode bindNode = StorydrivenUtil.addStoryNode(activity, "Bind The Elements");

			// make it
			StorydrivenUtil.addTransition(storyNode, bindNode, EdgeGuard.EACH_TIME);
			StorydrivenUtil.addTransition(bindNode, storyNode);

			// create given annotation variable bound by parameter
			name = StorydrivenUtil.getValidName(pattern, VAR_ANNOTATION);
			classifier = AnnotationsPackage.Literals.ASG_ANNOTATION;
			ObjectVariable annotationVar = StorydrivenUtil.addObjectVariable(bindNode, name, classifier);
			annotationVar.setBindingState(BindingState.BOUND);

			// go through children nodes
			for (PSCombinedFragmentItem item : fragment.getChildren()) {
				if (item instanceof PSNode && !ModifierType.ADDITIONAL.equals(((PSNode) item).getModifier())) {
					name = item.getName();
					classifier = EcorePackage.Literals.EOBJECT;
					ObjectVariable variable = StorydrivenUtil.addObjectVariable(bindNode, name, classifier);
					variable.setBindingState(BindingState.BOUND);

					// link to real annotation
					LinkVariable linkVariable = StorydrivenUtil
							.addLinkVariable(bindNode, annotationVar, variable, type);
					linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(name));
					linkVariable.setBindingOperator(BindingOperator.CREATE);
					classifier = EcorePackage.Literals.EOBJECT;
				}
			}

			if (lastNode == null) {
				StorydrivenUtil.addTransition(sourceNode, storyNode);
			} else {
				StorydrivenUtil.addTransition(lastNode, storyNode, EdgeGuard.END);
			}
			lastNode = storyNode;
		}

		return lastNode;
	}

	/**
	 * Creates the activity that initializes all sets by creating one
	 * SetInstanceAnnotationSet for each set. In the process nested sets and
	 * overlapping sets will be handled as simple sets.
	 */
	private ActivityNode addInitializeSetsNode() {
		// create the story node
		StoryNode element = StorydrivenUtil.addStoryNode(activity, "Create empty set containers");

		// cache set fragments
		setFragments = StorydrivenUtil.getSetFragmentsMap(pattern);

		// add result sets
		for (String setName : setFragments.keySet()) {
			createAnnotationResultSetObject(element.getStoryPattern(), false, true, setName);
		}

		return element;
	}

	private ActivityNode addMatchPairsNode(PSCombinedFragment set1, PSCombinedFragment set2, ActivityNode lastActivity) {
		String setName1 = getNameForSet(set1);
		String setName2 = getNameForSet(set2);
		String methodName = "matchPairs_" + setName1 + "_" + setName2;
		String paramName1 = PREFIX_ANNOTATION_SET + setName1;
		String paramName2 = PREFIX_ANNOTATION_SET + setName2;
		String resultSuffix = setName1 + "_" + setName2;
		// String resultName = PREFIX_ANNOTATION_SET + resultSuffix;

		EClass engineClass = generator.getEngineClass(pattern);
		EClass resultType = AnnotationsPackage.Literals.ANNOTATION_SET;

		// create operation
		EOperation method = EcoreUtil.addEOperation(engineClass, methodName);
		method.setEType(resultType);

		// add 'paramName1' parameter
		EcoreUtil.addEParameter(method, paramName1, resultType);

		// add 'paramName2' parameter
		EcoreUtil.addEParameter(method, paramName2, resultType);

		// add 'this' parameter
		EcoreUtil.addEParameter(method, VAR_THIS, engineClass);

		Activity MPMactivity = generator.createActivity(method, pattern);

		MatchPairsStep matchPairsMethodGenerator = new MatchPairsStep(generator);

		matchPairsMethodGenerator.generate(MPMactivity, pattern, set1, paramName1, set2, paramName2, counter,
				storyItems);

		StoryNode storyActivity = StorydrivenUtil.addStoryNode(activity, "Match pairs");
		StoryPattern storyPattern = storyActivity.getStoryPattern();

		// create transition between lastActivity and storyActivity
		SDMUtil.createTransitionNoneGuarded(lastActivity, storyActivity, this.activity);

		ObjectVariable resultObject = createAnnotationResultSetObject(storyPattern, false, true, resultSuffix);

		ObjectVariable set1Obj = SDMUtil.createObject(AnnotationsPackage.eINSTANCE.getAnnotationSet(), paramName1,
				true, false, storyPattern);
		ObjectVariable set2Obj = SDMUtil.createObject(AnnotationsPackage.eINSTANCE.getAnnotationSet(), paramName2,
				true, false, storyPattern);

		ActivityCallNode call = SDMUtil.createActivityCallNode(activity, MPMactivity);
		call.setName("Match pairs");

		for (EParameter eParameter : MPMactivity.getOwningOperation().getOperation().getEParameters()) {
			if (paramName1.equals(eParameter.getName())) {
				SDMUtil.createParameterBinding(eParameter, set1Obj, call);
			} else if (paramName2.equals(eParameter.getName())) {
				SDMUtil.createParameterBinding(eParameter, set2Obj, call);
			} else if (VAR_THIS.equals(eParameter.getName())) {
				EParameter forwarded = EcoreUtil.getEParameter(findSetFragmentMethod, VAR_THIS);
				StorydrivenUtil.createParameterBindingForwarding(eParameter, forwarded, call);
			}
		}

		// configure out parameter (should be ONLY ONE)
		for (EParameter outParam : MPMactivity.getOutParameters()) {
			SDMUtil.createParameterBinding(outParam, resultObject, call);
		}

		// StoryDiagramFactory.createCollabStatWithMaster(1, thisObject,
		// thisObject,
		// methodName + "(" + paramName1 + ", " + paramName2 + ")",
		// resultName, storyPattern);

		annotationSets.add(resultSuffix);

		// create transition between storyActivity and call
		SDMUtil.createTransitionNoneGuarded(storyActivity, call, activity);

		return call;
	}

	private ActivityNode addRemoveWrongInstancesNode(PSCombinedFragment set1, PSCombinedFragment set2,
			ActivityNode lastNode) {
		String setName1 = getNameForSet(set1);
		String setName2 = getNameForSet(set2);
		String paramName1 = PREFIX_ANNOTATION_SET + setName1;
		String paramName2 = PREFIX_ANNOTATION_SET + setName2;
		String resultSuffix = setName1 + "_" + setName2;
		String paramName3 = PREFIX_ANNOTATION_SET + resultSuffix;
		String methodName1 = PREFIX_REMOVE_INSTANCES + setName1;
		String methodName2 = PREFIX_REMOVE_INSTANCES + setName2;

		EClass engineClass = generator.getEngineClass(pattern);

		// create first method
		EClassifier resultType = AnnotationsPackage.Literals.SET_INSTANCE_ANNOTATION;

		// create operation
		EOperation method1 = EcoreUtil.addEOperation(engineClass, methodName1);
		method1.setEType(resultType);

		// add 'set' parameter
		EcoreUtil.addEParameter(method1, paramName1, resultType);

		// add 'mergedSet' parameter
		EcoreUtil.addEParameter(method1, paramName3, resultType);

		// add 'this' parameter
		EcoreUtil.addEParameter(method1, VAR_THIS, engineClass);

		Activity activity1 = generator.createActivity(method1, pattern);

		RemoveInstancesStep removeInstancesGenerator1 = new RemoveInstancesStep(generator);
		removeInstancesGenerator1.generate(activity1, pattern, paramName1, paramName3);

		// create second operation
		EOperation method2 = EcoreUtil.addEOperation(engineClass, methodName2);
		method2.setEType(resultType);

		// add 'set' parameter
		EcoreUtil.addEParameter(method2, paramName2, resultType);

		// add 'mergedSet' parameter
		EcoreUtil.addEParameter(method2, paramName3, resultType);

		// add 'this' parameter
		EcoreUtil.addEParameter(method2, VAR_THIS, engineClass);

		Activity activity2 = generator.createActivity(method2, pattern);
		RemoveInstancesStep removeInstancesGenerator2 = new RemoveInstancesStep(generator);
		removeInstancesGenerator2.generate(activity2, pattern, paramName2, paramName3);

		StoryNode storyActivity = StorydrivenUtil.addStoryNode(activity,
				"Remove wrong set instances from '%1s' and '%2s'", paramName1, paramName2);
		StoryPattern storyPattern = storyActivity.getStoryPattern();

		// create transition between matchPairsActivity and
		// removeWrongInstancesActivity
		SDMUtil.createTransitionNoneGuarded(lastNode, storyActivity, activity);

		ObjectVariable set1Obj = createAnnotationResultSetObject(storyPattern, true, false, setName1);
		ObjectVariable set2Obj = createAnnotationResultSetObject(storyPattern, true, false, setName2);
		ObjectVariable resultObj = createAnnotationResultSetObject(storyPattern, true, false, resultSuffix);

		ActivityCallNode call1 = SDMUtil.createActivityCallNode(activity, activity1);

		for (EParameter eParameter : method1.getEParameters()) {
			if (paramName1.equals(eParameter.getName())) {
				SDMUtil.createParameterBinding(eParameter, set1Obj, call1);
			} else if (paramName3.equals(eParameter.getName())) {
				SDMUtil.createParameterBinding(eParameter, resultObj, call1);
			} else if (VAR_THIS.equals(eParameter.getName())) {
				EParameter forwarded = EcoreUtil.getEParameter(findSetFragmentMethod, VAR_THIS);
				StorydrivenUtil.createParameterBindingForwarding(eParameter, forwarded, call1);
			}
		}

		// configure out parameter (should be ONLY ONE)
		for (EParameter outParam : activity1.getOwningOperation().getOutParameters()) {
			SDMUtil.createParameterBinding(outParam, set1Obj, call1);
		}

		SDMUtil.createTransitionNoneGuarded(storyActivity, call1, activity);

		ActivityCallNode call2 = SDMUtil.createActivityCallNode(activity, activity2);

		for (EParameter eParameter : method2.getEParameters()) {
			if (paramName2.equals(eParameter.getName())) {
				SDMUtil.createParameterBinding(eParameter, set2Obj, call2);
			} else if (paramName3.equals(eParameter.getName())) {
				SDMUtil.createParameterBinding(eParameter, resultObj, call2);
			} else if (VAR_THIS.equals(eParameter.getName())) {
				EParameter forwarded = EcoreUtil.getEParameter(findSetFragmentMethod, VAR_THIS);
				StorydrivenUtil.createParameterBindingForwarding(eParameter, forwarded, call2);
			}
		}
		EParameter call2Result = activity2.getOwningOperation().getOutParameters().get(0);
		SDMUtil.createParameterBinding(call2Result, set2Obj, call2);

		SDMUtil.createTransitionNoneGuarded(call1, call2, activity);

		return call2;
	}

	private ObjectVariable createAnnotationResultSetObject(StoryPattern pattern, boolean bound, boolean create,
			String setName) {
		ObjectVariable setObject = PatternsFactory.eINSTANCE.createObjectVariable();
		// TODO: find out if this the new corresponding class to
		// SetInstanceAnnotationSet
		setObject.setClassifier(AnnotationsPackage.Literals.ANNOTATION_SET);
		setObject.setName(PREFIX_ANNOTATION_SET + setName);
		if (bound) {
			setObject.setBindingState(BindingState.BOUND);
		}
		if (create) {
			setObject.setBindingOperator(BindingOperator.CREATE);
		}
		pattern.getVariables().add(setObject);

		return setObject;
	}

	/**
	 * Creates and calls the method that binds the found unbound set. That means
	 * nextUnboundSet will be filled with correct instances. A correct instance
	 * is a construct that matches the sub-graph within the set fragment.
	 */
	private ActivityNode addBindSetNode(String setName, PSCombinedFragment fragment, PSNode connectedElement,
			ActivityNode lastActivity) {
		String elementName = PREFIX_ELEMENT_CONNECTED_TO + setName;

		// create activity
		Activity findSubGraphActivity = generator.createActivity(createFindSubGraphMethod(setName), pattern);

		findSubGraphMethodGenerator.generate(findSubGraphActivity, fragment, connectedElement, counter);

		// add 'bind set' node
		StoryNode bindSetNode = StorydrivenUtil.addStoryNode(activity, "Bind '%1s'", setName);
		StoryPattern storyPattern = bindSetNode.getStoryPattern();

		// transit from [last] to 'bind set'
		StorydrivenUtil.addTransition(lastActivity, bindSetNode);

		// bind to created annotation
		ObjectVariable annotationObject = SDMUtil.createTemporaryAnnotationObject(storyPattern, true, false);
		EParameter tempAnnoParam = StorydrivenUtil.getEParameter(activity, VAR_ANNOTATION);
		annotationObject.setBindingExpression(ExpressionsUtil.createParameterExpression(tempAnnoParam));

		ObjectVariable element = null;

		if (connectedElement instanceof PSAnnotation) {
			PSAnnotation anno = (PSAnnotation) connectedElement;
			EClass annoType = generator.getAnnotationClass(anno.getType());
			element = SDMUtil.createAnnotationObject(anno, annoType, false, false, storyPattern, storyItems, counter);
		} else {
			// must be a PSObject
			element = SDMUtil
					.createObject((PSObject) connectedElement, false, false, storyPattern, storyItems, counter);
		}
		element.setName(elementName);

		SDMUtil.createBoundObjectsLink(annotationObject, element, connectedElement, false, storyPattern);

		String paramName1 = PREFIX_ANNOTATION_SET + setName;

		ActivityCallNode call = SDMUtil.createActivityCallNode(activity, findSubGraphActivity);

		ObjectVariable annotationsSetObject = SDMUtil.createObject(SDMUtil.getAnnotationSetClass(), paramName1, true,
				false, storyPattern);

		// bind 'this' parameter
		EParameter callParameter = StorydrivenUtil.getEParameter(findSubGraphActivity, VAR_THIS);
		EParameter operationParameter = StorydrivenUtil.getEParameter(activity, VAR_THIS);
		StorydrivenUtil.createParameterBindingForwarding(callParameter, operationParameter, call);

		// bind 'annotation' parameter
		callParameter = StorydrivenUtil.getEParameter(findSubGraphActivity, VAR_ANNOTATION);
		operationParameter = StorydrivenUtil.getEParameter(activity, VAR_ANNOTATION);
		StorydrivenUtil.createParameterBindingForwarding(callParameter, operationParameter, call);

		// bind 'context' parameter
		operationParameter = StorydrivenUtil.getEParameter(findSubGraphActivity, VAR_CONTEXT);
		SDMUtil.createParameterBinding(operationParameter, element, call);

		// bind 'results' parameter
		operationParameter = StorydrivenUtil.getEParameter(findSubGraphActivity, VAR_RESULT_SET);
		SDMUtil.createParameterBinding(operationParameter, annotationsSetObject, call);

		// bind return value
		for (EParameter outParam : findSubGraphActivity.getOwningOperation().getOutParameters()) {
			SDMUtil.createParameterBinding(outParam, annotationsSetObject, call);
		}

		//
		// StoryDiagramFactory.createCollabStatWithMaster(1, thisObject,
		// thisObject, methodName + "("
		// + paramName1 + ", "
		// + paramName2 + ")", paramName1, storyPattern);

		annotationSets.add(setName);

		SDMUtil.createTransitionNoneGuarded(bindSetNode, call, activity);

		return call;
	}

	private EOperation createFindSubGraphMethod(String name) {
		// create operation
		EOperation findSubGraphMethod = EcoreUtil.addEOperation(generator.getEngineClass(pattern),
				PREFIX_METHOD_FIND_SUBGRAPH + name);
		findSubGraphMethod.setEType(AnnotationsPackage.Literals.ANNOTATION_SET);

		// add 'this' parameter
		EcoreUtil.addEParameter(findSubGraphMethod, VAR_THIS, generator.getEngineClass(pattern));

		// add 'annotation' parameter
		EcoreUtil.addEParameter(findSubGraphMethod, VAR_ANNOTATION, AnnotationsPackage.Literals.ASG_ANNOTATION);

		// add 'result set' parameter
		EcoreUtil.addEParameter(findSubGraphMethod, VAR_RESULT_SET, AnnotationsPackage.Literals.ANNOTATION_SET);

		// add 'context' parameter
		EcoreUtil.addEParameter(findSubGraphMethod, VAR_CONTEXT, EcorePackage.Literals.EOBJECT);

		return findSubGraphMethod;
	}

	/**
	 * Returns a list of set fragments that overlap the given fragment.
	 * 
	 * @param fragment
	 * @return overlapping set fragments
	 */
	private Collection<PSCombinedFragment> getOverlappingSetFragments(PSCombinedFragment fragment) {
		Collection<PSCombinedFragment> overlappingSets = new ArrayList<PSCombinedFragment>();
		for (PSCombinedFragment setFragment : setFragments.values()) {
			if (ModelHelper.overlaps(fragment, setFragment)) {
				overlappingSets.add(setFragment);
			}
		}
		return overlappingSets;
	}

	private static Constraint createSetConstraint(PSMetricConstraint nodeConstraint, ObjectVariable theSet) {
		StringBuilder text = new StringBuilder();
		text.append("self.annotations->size()");
		switch (nodeConstraint.getOperator()) {
		case EQUAL:
			text.append("=");
			break;
		case GREATER:
			text.append(">");
			break;
		case GREATER_OR_EQUAL:
			text.append(">=");
			break;
		case LESS:
			text.append("<");
			break;
		case LESS_OR_EQUAL:
			text.append("<=");
			break;
		case UNEQUAL:
			text.append("<>");
			break;
		default:
			text.append("!REG!");
			break;
		}
		text.append(nodeConstraint.getValueExpression());

		TextualExpression exp = org.storydriven.core.expressions.ExpressionsFactory.eINSTANCE
				.createTextualExpression();
		exp.setExpressionText(text.toString());
		exp.setLanguage("OCL");

		Constraint cons = PatternsFactory.eINSTANCE.createConstraint();
		cons.setConstraintExpression(exp);
		cons.setObjectVariable(theSet);

		return cons;
	}

	private ActivityNode addCheckSetSizeNode() {
		StoryNode storyActivity = StorydrivenUtil.addStoryNode(activity, "Check set size expression");
		StoryPattern storyPattern = storyActivity.getStoryPattern();

		int i = 0;

		Set<String> sets = setFragments.keySet();
		for (String setName : sets) {
			String setInstanceAnnotationSetName = VAR_RESULT_SET + "_" + setName;
			PSCombinedFragment setFragment = setFragments.get(setName);
			PSNodeConstraint constraint = setFragment.getConstraint();

			ObjectVariable setObject = SDMUtil.createObject(SDMUtil.getAnnotationSetClass(),
					setInstanceAnnotationSetName, true, false, storyPattern);
			if (constraint instanceof PSMetricConstraint
					&& "SIZE".equals(((PSMetricConstraint) constraint).getMetricAcronym())) {

				// TODO: externalize the SIZE string! GLOBALLY!!
				createSetConstraint((PSMetricConstraint) constraint, setObject);
			}

			// TODO: do this soon, but currently it looks like the
			// findSetFragments method never
			// contains any content
			// PSSetFragmentSizeExpression sizeExpression = setFragment
			// .getSizeExpression();
			//
			// // create constraint
			// StringBuffer expression = new StringBuffer();
			// expression.append(setInstanceAnnotationSetName
			// + ".sizeOfAnnotations() "
			// + PSSetFragmentSizeExpression
			// .getStringRepresentation(sizeExpression.getOperator())
			// + " " + sizeExpression.getValueExpression());
			// StoryDiagramFactory.createConstraint(expression.toString(),
			// storyPattern);

			i = addExpressionForNestedSet(storyPattern, i, setFragment);
		}

		return storyActivity;
	}

	private int addExpressionForNestedSet(StoryPattern storyPattern, int i, PSCombinedFragment setFragment) {
		// TODO rework. the whole method seems to be wrong with the new model
		StringBuffer expression;

		for (PSCombinedFragmentItem child : setFragment.getChildren()) {
			if (child instanceof PSCombinedFragment) {
				// String varName = "nestedSet_" + i;
				// String result = varName + "_Result";
				String setName = PREFIX_ANNOTATION_SET + getNameForSet(setFragment);
				String methodName = PREFIX_METHOD_GET_SIZE_OF_NESTED_SET + i + INFIX + getNameForSet(setFragment);

				// NEW
				EClass engineClass = generator.getEngineClass(pattern);
				EOperation _GSONSoperation = EcoreUtil.addEOperation(engineClass, methodName);
				_GSONSoperation.setEType(EcorePackage.Literals.EBOOLEAN);

				// add 'annotation set' parameter
				EcoreUtil.addEParameter(_GSONSoperation, setName, AnnotationsPackage.Literals.ANNOTATION_SET);

				// add 'this' parameter
				EcoreUtil.addEParameter(_GSONSoperation, VAR_THIS, engineClass);

				Activity _GSONSactivity = generator.createActivity(_GSONSoperation, pattern);

				GetSizeOfNestedSetStep getSizeOfNestedSetGenerator = new GetSizeOfNestedSetStep(generator);
				getSizeOfNestedSetGenerator.generate(_GSONSactivity, setFragment, (PSCombinedFragment) child, setName,
						storyItems, counter);

				expression = new StringBuffer();
				expression.append(methodName + "(" + setName + ")");
				SDMUtil.createConstraint(expression.toString(), storyPattern);

				i++;

				i = addExpressionForNestedSet(storyPattern, i, (PSCombinedFragment) child);
			}
		}
		return i;
	}

	private String getNameForSet(PSCombinedFragment set) {
		for (String key : setFragments.keySet()) {
			if (setFragments.get(key).equals(set)) {
				return key;
			}
		}
		return "";
	}

	/**
	 * Creates the activity that removes all annotation sets. This will be
	 * executed if the set matching was not successful.
	 */
	private ActivityNode addRemoveSetsNode() {
		StoryNode storyActivity = StorydrivenUtil.addStoryNode(activity, "Remove annotation sets");

		for (String setName : annotationSets) {
			EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
			ObjectVariable var = StorydrivenUtil.addObjectVariable(storyActivity, PREFIX_ANNOTATION_SET + setName,
					classifier);
			var.setBindingState(BindingState.BOUND);
			var.setBindingOperator(BindingOperator.DESTROY);
		}

		return storyActivity;
	}

	/**
	 * Creates the activity that adds the bound setInstanceAnnotationSets to the
	 * set result that will be returned and added to the GFRNAnnotation. In
	 * addition this activity deletes temporary setInstanceAnnotationSet objects
	 * that will not be needed any more.
	 */
	private ActivityNode createCreateResultActivity() {
		StoryNode storyActivity = StorydrivenUtil.addStoryNode(activity, "Create Result Set");
		StoryPattern storyPattern = storyActivity.getStoryPattern();

		ObjectVariable resultSet = SDMUtil.createSetResultSetObject(storyPattern, false, true);

		for (String setName : annotationSets) {
			if (setName.contains("_")) {
				// The SetInstanceAnnotationSet this setName is associated with
				// represents a merged Set.
				// Delete this set because it's not needed any more.
				EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
				ObjectVariable var = StorydrivenUtil.addObjectVariable(storyActivity, PREFIX_ANNOTATION_SET + setName,
						classifier);
				var.setBindingState(BindingState.BOUND);
				var.setBindingOperator(BindingOperator.DESTROY);
			} else {
				// The SetInstanceAnnotationSet this setName is associated with
				// represents a
				// PSSetFragment from the specification. Link it to
				// setResultSet.
				EClass classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
				ObjectVariable var = StorydrivenUtil.addObjectVariable(storyActivity, PREFIX_ANNOTATION_SET + setName,
						classifier);
				var.setBindingState(BindingState.BOUND);
				var.setBindingOperator(BindingOperator.CHECK_ONLY);

				SDMUtil.createLink(var, resultSet, AnnotationsPackage.eINSTANCE.getAnnotationSet_SetResultSet(), null,
						true, BindingSemantics.MANDATORY, storyPattern);
			}
		}

		// create given annotation variable bound by parameter
		ObjectVariable annotationVar = StorydrivenUtil.addObjectVariable(storyActivity, VAR_ANNOTATION,
				AnnotationsPackage.Literals.ASG_ANNOTATION);
		annotationVar.setBindingState(BindingState.BOUND);

		// link to set result set
		LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyActivity, annotationVar, resultSet,
				AnnotationsPackage.Literals.ASG_ANNOTATION__SET_RESULT_SET);
		linkVariable.setBindingOperator(BindingOperator.CREATE);

		return storyActivity;
	}
}
