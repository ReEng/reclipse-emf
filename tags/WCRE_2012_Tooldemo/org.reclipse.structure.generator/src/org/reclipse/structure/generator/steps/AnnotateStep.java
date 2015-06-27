package org.reclipse.structure.generator.steps;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;
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
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;
import org.reclipse.structure.specification.util.SpecificationUtil;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityCallNode;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.StoryNode;
import org.storydriven.storydiagrams.patterns.BindingOperator;
import org.storydriven.storydiagrams.patterns.BindingSemantics;
import org.storydriven.storydiagrams.patterns.BindingState;
import org.storydriven.storydiagrams.patterns.LinkVariable;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.PrimitiveVariable;


public class AnnotateStep
{
	private static final String START = "start"; //$NON-NLS-1$

	private static final String MATCH_CORE_NODES = "match_core_nodes"; //$NON-NLS-1$

	private static final String MATCH_NEGATIVE_FRAGMENT = "match_negative_fragment"; //$NON-NLS-1$

	private static final String MATCH_REQUIRED_SET_NODES = "match_required_set_nodes"; //$NON-NLS-1$

	private static final String BIND_MATCHED_NODES = "bind_matched_nodes"; //$NON-NLS-1$

	private static final String PREPARE_SET_FRAGMENT_FINDING = "prepare_set_fragment_finding"; //$NON-NLS-1$

	private static final String FIND_SET_FRAGMENTS = "find_set_fragments"; //$NON-NLS-1$

	private static final String CHECK_SET_FRAGMENT_RESULTS = "check_set_fragment_results"; //$NON-NLS-1$

	private static final String CHECK_FOR_EXISTING_ANNOTATION = "check for existing annotation"; //$NON-NLS-1$

	private static final String ADD_ANNOTATION = "add_annotation"; //$NON-NLS-1$

	private static final String STORE_ANTECEDENTS = "store_antecedents"; //$NON-NLS-1$

	private static final String FIND_ADDITIONAL_ELEMENTS = "find_additional_elements"; //$NON-NLS-1$

	private static final String DESTROY_INVALID_ANNOTATION = "destroy_invalid_annotation"; //$NON-NLS-1$

	private static final String END = "end"; //$NON-NLS-1$


	private final IGenerator generator;

	private Activity activity;

	private PSPatternSpecification pattern;

	private ObjectVariable createdAnnotationVariable;

	private PrimitiveVariable createdSetResultCheckVariable;


	public AnnotateStep(IGenerator generator)
	{
		this.generator = generator;
	}


	public void generate(Activity activity, PSPatternSpecification pattern)
	{
		generator.debug("Generating '%1s'...", NameUtil.getName(activity));

		// prepare
		Map<String, ActivityNode> nodeCache = new HashMap<String, ActivityNode>();
		createdAnnotationVariable = null;
		createdSetResultCheckVariable = null;

		this.activity = activity;
		this.pattern = pattern;

		// get pattern settings
		boolean hasNegativeFragments = SpecificationUtil.hasNegativeFragments(pattern);
		boolean hasSetFragments = SpecificationUtil.hasSetFragments(pattern);
		boolean hasAntecedentPatterns = SpecificationUtil.hasAntecedentPatterns(pattern);
		boolean hasAdditionalElements = SpecificationUtil.isAdditionalElements(pattern);

		// create 'start'
		nodeCache.put(START, createStartNode());

		// create 'match core nodes'
		nodeCache.put(MATCH_CORE_NODES, createMatchCoreNodes());

		// create 'match negative fragment' nodes
		if (hasNegativeFragments)
		{
			for (PSCombinedFragment fragment : pattern.getCombinedFragments())
			{
				if (ModifierType.NEGATIVE.equals(fragment.getKind()))
				{
					ActivityNode activityNode = createMatchNegativeFragment(fragment);

					if (nodeCache.containsKey(MATCH_NEGATIVE_FRAGMENT))
					{
						// transit from last negative matching
						StorydrivenUtil
								.addTransition(nodeCache.get(MATCH_NEGATIVE_FRAGMENT), activityNode, EdgeGuard.FAILURE);
					}
					else
					{
						// transit from core matching
						StorydrivenUtil.addTransition(nodeCache.get(MATCH_CORE_NODES), activityNode, EdgeGuard.EACH_TIME);
					}

					// transit back to core matching
					StorydrivenUtil.addTransition(activityNode, nodeCache.get(MATCH_CORE_NODES), EdgeGuard.SUCCESS);

					nodeCache.put(MATCH_NEGATIVE_FRAGMENT, activityNode);
				}
			}
		}

		// create 'match required set nodes'
		if (hasSetFragments)
		{
			nodeCache.put(MATCH_REQUIRED_SET_NODES, createMatchRequiredSetNodes());
		}

		// create 'bind matched nodes'
		nodeCache.put(BIND_MATCHED_NODES, createBindMatchedNodes());

		// create the other set relevant nodes
		if (hasSetFragments)
		{
			// create 'prepare set fragment finding'
			nodeCache.put(PREPARE_SET_FRAGMENT_FINDING, createPrepareSetFragmentFinding());

			// create 'find set fragments'
			nodeCache.put(FIND_SET_FRAGMENTS, createFindSetFragments());

			// create 'check set fragment results'
			nodeCache.put(CHECK_SET_FRAGMENT_RESULTS, createCheckSetFragmentResults());
		}

		// create 'check for existing annotation'
		nodeCache.put(CHECK_FOR_EXISTING_ANNOTATION, createCheckForExistingAnnotation());

		// create 'add annotation'
		nodeCache.put(ADD_ANNOTATION, createAddAnnotation());

		// create 'store antecedents'
		if (hasAntecedentPatterns)
		{
			nodeCache.put(STORE_ANTECEDENTS, createStoreAntecedents());
		}

		// create 'find additional elements'
		if (hasAdditionalElements)
		{
			nodeCache.put(FIND_ADDITIONAL_ELEMENTS, createFindAdditionalElements());
		}

		// create 'delete invalid annotation'
		nodeCache.put(DESTROY_INVALID_ANNOTATION, createDestroyInvalidAnnotation());

		// create 'end'
		nodeCache.put(END, createEnd());

		// /////////////////////////////////////////////////////////////////////////////////////////// //

		// transit from 'start'
		ActivityNode source = nodeCache.get(START);
		ActivityNode target = nodeCache.get(MATCH_CORE_NODES);
		StorydrivenUtil.addTransition(source, target);

		StorydrivenUtil.addTransition(nodeCache.get(CHECK_FOR_EXISTING_ANNOTATION),
				nodeCache.get(DESTROY_INVALID_ANNOTATION), EdgeGuard.SUCCESS);
		StorydrivenUtil.addTransition(nodeCache.get(CHECK_FOR_EXISTING_ANNOTATION), nodeCache.get(ADD_ANNOTATION),
				EdgeGuard.FAILURE);

		// transitions for sets
		if (hasSetFragments)
		{
			if (hasNegativeFragments)
			{
				// transit from (last) 'match negative fragment'
				source = nodeCache.get(MATCH_NEGATIVE_FRAGMENT);
				target = nodeCache.get(MATCH_REQUIRED_SET_NODES);
				StorydrivenUtil.addTransition(source, target, EdgeGuard.FAILURE);
			}
			else
			{
				// transit from 'match core nodes'
				source = nodeCache.get(MATCH_CORE_NODES);
				target = nodeCache.get(MATCH_REQUIRED_SET_NODES);
				StorydrivenUtil.addTransition(source, target, EdgeGuard.EACH_TIME);
			}

			// transit from 'match required set nodes' to 'bind matched nodes'
			source = nodeCache.get(MATCH_REQUIRED_SET_NODES);
			target = nodeCache.get(BIND_MATCHED_NODES);
			StorydrivenUtil.addTransition(source, target, EdgeGuard.SUCCESS);

			// transit from 'match required set nodes' back to 'match core nodes'
			source = nodeCache.get(MATCH_REQUIRED_SET_NODES);
			target = nodeCache.get(DESTROY_INVALID_ANNOTATION);
			StorydrivenUtil.addTransition(source, target, EdgeGuard.FAILURE);

			// transit from create annotation
			StorydrivenUtil.addTransition(nodeCache.get(BIND_MATCHED_NODES), nodeCache.get(PREPARE_SET_FRAGMENT_FINDING));
			StorydrivenUtil.addTransition(nodeCache.get(PREPARE_SET_FRAGMENT_FINDING), nodeCache.get(FIND_SET_FRAGMENTS));
			StorydrivenUtil.addTransition(nodeCache.get(FIND_SET_FRAGMENTS), nodeCache.get(CHECK_SET_FRAGMENT_RESULTS));

			StorydrivenUtil.addTransition(nodeCache.get(CHECK_SET_FRAGMENT_RESULTS),
					nodeCache.get(CHECK_FOR_EXISTING_ANNOTATION), EdgeGuard.SUCCESS);
			StorydrivenUtil.addTransition(nodeCache.get(CHECK_SET_FRAGMENT_RESULTS),
					nodeCache.get(DESTROY_INVALID_ANNOTATION), EdgeGuard.FAILURE);
		}
		else
		{
			if (hasNegativeFragments)
			{
				// transit from negative fragment matching
				StorydrivenUtil.addTransition(nodeCache.get(MATCH_NEGATIVE_FRAGMENT), nodeCache.get(BIND_MATCHED_NODES),
						EdgeGuard.FAILURE);
			}
			else
			{
				// transit from core matching
				StorydrivenUtil.addTransition(nodeCache.get(MATCH_CORE_NODES), nodeCache.get(BIND_MATCHED_NODES),
						EdgeGuard.EACH_TIME);
			}

			// transit from create annotation
			StorydrivenUtil.addTransition(nodeCache.get(BIND_MATCHED_NODES), nodeCache.get(CHECK_FOR_EXISTING_ANNOTATION));
		}

		// add 'store antecedents' part
		if (hasAntecedentPatterns)
		{
			// transit from annotate core
			StorydrivenUtil.addTransition(nodeCache.get(ADD_ANNOTATION), nodeCache.get(STORE_ANTECEDENTS));

			// transit back to core match
			StorydrivenUtil.addTransition(nodeCache.get(STORE_ANTECEDENTS), nodeCache.get(DESTROY_INVALID_ANNOTATION));
		}
		else
		{
			// transit back to core match
			StorydrivenUtil.addTransition(nodeCache.get(ADD_ANNOTATION), nodeCache.get(DESTROY_INVALID_ANNOTATION));
		}

		// add 'find additional elements' part
		if (hasAdditionalElements)
		{
			if (hasAntecedentPatterns)
			{
				StorydrivenUtil.addTransition(nodeCache.get(STORE_ANTECEDENTS), nodeCache.get(FIND_ADDITIONAL_ELEMENTS));
			}
			else
			{
				StorydrivenUtil.addTransition(nodeCache.get(ADD_ANNOTATION), nodeCache.get(FIND_ADDITIONAL_ELEMENTS));
			}

			// transit back to core match
			StorydrivenUtil.addTransition(nodeCache.get(FIND_ADDITIONAL_ELEMENTS),
					nodeCache.get(DESTROY_INVALID_ANNOTATION));
		}

		// transit back to core match
		StorydrivenUtil.addTransition(nodeCache.get(DESTROY_INVALID_ANNOTATION), nodeCache.get(MATCH_CORE_NODES));

		// transit to 'stop'
		StorydrivenUtil.addTransition(nodeCache.get(MATCH_CORE_NODES), nodeCache.get(END), EdgeGuard.END);
	}


	private ActivityNode createEnd()
	{
		return StorydrivenUtil.addActivityFinalNode(activity, "this.foundAnnotations");
	}


	private ActivityNode createStartNode()
	{
		return StorydrivenUtil.addInitialNode(activity);
	}


	private ActivityNode createMatchCoreNodes()
	{
		// get special nodes
		PSNode triggerNode = generator.getTrigger(pattern);

		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Match Core Nodes");
		storyNode.setForEach(true);

		// add object variables for all normal nodes
		Map<PSNode, ObjectVariable> cache = new HashMap<PSNode, ObjectVariable>();
		for (PSNode node : pattern.getNodes())
		{
			if (SpecificationUtil.isCore(node))
			{
				// prepare data
				String name = node.getName();
				EClass classifier = null;
				if (node instanceof PSAnnotation)
				{
					classifier = generator.getAnnotationClass(((PSAnnotation) node).getType());
				}
				else if (node instanceof PSObject)
				{
					classifier = ((PSObject) node).getInstanceOf();
				}

				// create variable
				ObjectVariable variable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);

				// set to negative
				if (ModifierType.NEGATIVE.equals(node.getModifier()))
				{
					variable.setBindingSemantics(BindingSemantics.NEGATIVE);
				}

				// bind context
				if (node.equals(triggerNode))
				{
					variable.setBindingExpression(ExpressionsUtil.getContextBindingExpression(activity));
					variable.setBindingState(BindingState.BOUND);
				}

				// add node constraints
				for (PSNodeConstraint constraint : node.getNodeConstraints())
				{
					if (constraint instanceof PSBooleanConstraint && !((PSBooleanConstraint) constraint).isAdditional())
					{
						StorydrivenUtil.addConstraint(variable, (PSBooleanConstraint) constraint);
					}
					else
					{
						// FIXME: do some fuzzy stuff
					}
				}

				cache.put(node, variable);
			}
		}

		StorydrivenUtil.connectAllVariables(pattern, storyNode, cache);

		return storyNode;
	}


	private ActivityNode createMatchNegativeFragment(PSCombinedFragment fragment)
	{
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Match Negative Fragment '%1s'",
				NameUtil.getName(fragment));

		// prepare variables cache
		Map<PSNode, ObjectVariable> items = new HashMap<PSNode, ObjectVariable>();

		Counter counter = new Counter();
		// create negative nodes
		for (PSNode item : ModelHelper.getNodes(fragment))
		{
			// creating negative unbound object here, to know later all object to create are bound
			SDMUtil.createObject((PSNode) item, false, false, storyNode.getStoryPattern(), items, counter, generator);
		}

		// go through all relevant connections
		for (PSConnection con : ModelHelper.getConnections(items.keySet()))
		{
			// create source object variable
			ObjectVariable source = items.get(con.getSource());
			if (source == null)
			{
				source = SDMUtil.createObject(con.getSource(), true, false, storyNode.getStoryPattern(), items, counter,
						generator);
			}

			// create target object variable
			ObjectVariable target = items.get(con.getTarget());
			if (target == null)
			{
				target = SDMUtil.createObject(con.getTarget(), true, false, storyNode.getStoryPattern(), items, counter,
						generator);
			}

			if (con instanceof PSLink)
			{
				PSLink link = (PSLink) con;
				SDMUtil.createLink(source, target, link.getInstanceOf(), link.getQualifier(), false,
						BindingSemantics.MANDATORY, storyNode.getStoryPattern());
			}
			else if (con instanceof PSPath)
			{
				SDMUtil.createPath((PSPath) con, source, target, storyNode.getStoryPattern());
			}
		}

		return storyNode;
	}


	private ActivityNode createMatchRequiredSetNodes()
	{
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Match Required Set Nodes");

		// add object variables for all normal nodes
		Map<PSNode, ObjectVariable> coreCache = new HashMap<PSNode, ObjectVariable>();
		Map<PSNode, ObjectVariable> setCache = new HashMap<PSNode, ObjectVariable>();
		for (PSNode node : pattern.getNodes())
		{
			// prepare data
			String name = node.getName();
			EClass classifier = null;
			if (node instanceof PSAnnotation)
			{
				classifier = generator.getAnnotationClass(((PSAnnotation) node).getType());
			}
			else if (node instanceof PSObject)
			{
				classifier = ((PSObject) node).getInstanceOf();
			}

			if (SpecificationUtil.isCore(node) && SpecificationUtil.isConnectedToSet(node))
			{
				// create variable
				ObjectVariable variable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
				variable.setBindingState(BindingState.BOUND);

				coreCache.put(node, variable);
			}
			else if (SpecificationUtil.isInSet(node))
			{
				// create variable
				ObjectVariable variable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);

				// set to negative
				if (ModifierType.NEGATIVE.equals(node.getModifier()))
				{
					variable.setBindingSemantics(BindingSemantics.NEGATIVE);
				}

				// add node constraints
				for (PSNodeConstraint constraint : node.getNodeConstraints())
				{
					if (constraint instanceof PSBooleanConstraint && !((PSBooleanConstraint) constraint).isAdditional())
					{
						StorydrivenUtil.addConstraint(variable, (PSBooleanConstraint) constraint);
					}
					else
					{
						// FIXME: do some fuzzy stuff
					}
				}

				setCache.put(node, variable);
			}
		}

		StorydrivenUtil.connectVariables(pattern, storyNode, coreCache, setCache);

		return storyNode;
	}


	private ActivityNode createBindMatchedNodes()
	{
		boolean hasSetFragments = SpecificationUtil.hasSetFragments(pattern);

		// get special nodes
		PSAnnotation createAnnotation = SpecificationUtil.getCreateAnnotation(pattern);

		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Bind Matched Nodes To New Annotation");

		// create annotation variable
		String name = StorydrivenUtil.getValidName(pattern, Constants.VAR_ANNOTATION);
		EClass classifier = generator.getAnnotationClass(pattern);
		createdAnnotationVariable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
		createdAnnotationVariable.setBindingOperator(BindingOperator.CREATE);

		// go through nodes
		for (PSNode node : pattern.getNodes())
		{
			// prepare data
			name = node.getName();
			classifier = null;
			if (node instanceof PSAnnotation)
			{
				classifier = generator.getAnnotationClass(((PSAnnotation) node).getType());
			}
			else if (node instanceof PSObject)
			{
				classifier = ((PSObject) node).getInstanceOf();
			}

			if (SpecificationUtil.isNormal(node) && !createAnnotation.equals(node))
			{
				// create core variable
				ObjectVariable var = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
				var.setBindingState(BindingState.BOUND);

				// add bound link
				LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyNode, createdAnnotationVariable, var,
						AnnotationsPackage.Literals.ASG_ANNOTATION__BOUND_OBJECTS);
				linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(node.getName()));
				linkVariable.setBindingOperator(BindingOperator.CREATE);
			}
			else if (hasSetFragments && SpecificationUtil.isInSet(node))
			{
				// create set variable
				ObjectVariable var = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
				var.setBindingState(BindingState.BOUND);

				// add bound link
				LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyNode, createdAnnotationVariable, var,
						AnnotationsPackage.Literals.ASG_ANNOTATION__BOUND_OBJECTS);
				linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(node.getName()));
				linkVariable.setBindingOperator(BindingOperator.CREATE);
			}
		}

		return storyNode;
	}


	private ActivityNode createPrepareSetFragmentFinding()
	{
		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Prepare Set Fragments Finding");

		// re-bind created annotation
		String name = createdAnnotationVariable.getName();
		EClass classifier = generator.getAnnotationClass(pattern);
		ObjectVariable annotationVar = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
		annotationVar.setBindingState(BindingState.BOUND);

		// prepare return value
		name = StorydrivenUtil.getValidName(pattern, Constants.VAR_SET_RESULT_CHECK);
		createdSetResultCheckVariable = StorydrivenUtil.addPrimitiveVariable(storyNode, name,
				EcorePackage.Literals.EBOOLEAN);

		return storyNode;
	}


	private ActivityNode createFindSetFragments()
	{
		Activity callable = generator.getActivity(pattern, Constants.METHOD_FIND_SETS);
		ActivityCallNode callNode = StorydrivenUtil.addActivityCallNode(activity, callable);

		// bind 'this' parameter
		EParameter operationParameter = StorydrivenUtil.getEParameter(callable, Constants.VAR_THIS);
		EParameter callableParameter = StorydrivenUtil.getEParameter(activity, Constants.VAR_THIS);
		StorydrivenUtil.createParameterBindingForwarding(operationParameter, callableParameter, callNode);

		// bind 'annotation' parameter
		operationParameter = StorydrivenUtil.getEParameter(callable, Constants.VAR_ANNOTATION);
		StorydrivenUtil.createParameterBinding(operationParameter, createdAnnotationVariable, callNode);

		// bind return
		for (EParameter out : callable.getOwningOperation().getOutParameters())
		{
			StorydrivenUtil.createParameterBinding(out, createdSetResultCheckVariable, callNode);
		}

		return callNode;
	}


	private ActivityNode createCheckSetFragmentResults()
	{
		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Check Set Fragment Results");

		// add return value check expression
		StorydrivenUtil.addSetResultsExpression(pattern, storyNode);

		return storyNode;
	}


	private ActivityNode createCheckForExistingAnnotation()
	{
		// get setting
		PSAnnotation createAnnotation = SpecificationUtil.getCreateAnnotation(pattern);

		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Check For Existing Annotation");

		// create annotation variable
		String name = StorydrivenUtil.getValidName(pattern, Constants.VAR_ANY);
		EClass classifier = generator.getAnnotationClass(pattern);
		ObjectVariable annotationVar = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);

		for (PSNode node : SpecificationUtil.getAnnotatedElements(pattern))
		{
			if (SpecificationUtil.isNormal(node) || SpecificationUtil.isInSet(node))
			{
				// prepare data
				name = node.getName();
				classifier = null;
				if (node instanceof PSAnnotation)
				{
					classifier = generator.getAnnotationClass(((PSAnnotation) node).getType());
				}
				else if (node instanceof PSObject)
				{
					classifier = ((PSObject) node).getInstanceOf();
				}

				// create variable
				ObjectVariable variable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
				variable.setBindingState(BindingState.BOUND);

				// add bound link
				String qualifier = null;
				for (PSConnection in : node.getIncoming())
				{
					if (createAnnotation.equals(in.getSource()) && in instanceof PSLink)
					{
						qualifier = ((PSLink) in).getQualifier();
					}
				}

				// link them
				LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyNode, annotationVar, variable,
						AnnotationsPackage.Literals.ASG_ANNOTATION__ANNOTATED_ELEMENTS);
				linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(qualifier));
			}
		}

		return storyNode;
	}


	private ActivityNode createAddAnnotation()
	{
		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Add Annotation To Results");
		storyNode.setForEach(true);

		// create this variable
		String name = Constants.VAR_THIS;
		EClass classifier = generator.getEngineClass(pattern);
		ObjectVariable thisVariable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
		thisVariable.setBindingState(BindingState.BOUND);

		// create annotation result set variable
		name = StorydrivenUtil.getValidName(pattern, Constants.VAR_RESULTS);
		classifier = AnnotationsPackage.Literals.ANNOTATION_SET;
		ObjectVariable resultVariable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);

		// link them
		StorydrivenUtil.addLinkVariable(storyNode, thisVariable, resultVariable,
				AnnotationsPackage.Literals.ANNOTATION_ENGINE__FOUND_ANNOTATIONS);

		// create annotation variable
		name = createdAnnotationVariable.getName();
		classifier = generator.getAnnotationClass(pattern);
		ObjectVariable annotationVar = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
		annotationVar.setBindingState(BindingState.BOUND);

		// link them
		LinkVariable annotationLinkVariable = StorydrivenUtil.addLinkVariable(storyNode, resultVariable, annotationVar,
				AnnotationsPackage.Literals.ANNOTATION_SET__ANNOTATIONS);
		annotationLinkVariable.setBindingOperator(BindingOperator.CREATE);

		// create annotated nodes and links
		for (PSNode node : SpecificationUtil.getAnnotatedElements(pattern))
		{
			if (SpecificationUtil.isNormal(node) || SpecificationUtil.isInSet(node))
			{
				// prepare data
				name = StorydrivenUtil.getValidName(pattern, "bound_" + node.getName());
				classifier = EcorePackage.Literals.EOBJECT;

				// create variable
				ObjectVariable variable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);

				// link them
				LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyNode, annotationVar, variable,
						AnnotationsPackage.Literals.ASG_ANNOTATION__BOUND_OBJECTS);
				linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(node.getName()));
			}
		}

		StoryNode bindNode = createBindNode();

		StorydrivenUtil.addTransition(storyNode, bindNode, EdgeGuard.EACH_TIME);
		StorydrivenUtil.addTransition(bindNode, storyNode);

		return storyNode;
	}


	private StoryNode createBindNode()
	{
		// get data
		PSAnnotation createAnnotation = SpecificationUtil.getCreateAnnotation(pattern);

		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Annotate Elements");

		// create annotation variable
		String name = createdAnnotationVariable.getName();
		EClass classifier = generator.getAnnotationClass(pattern);
		ObjectVariable annotationVar = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
		annotationVar.setBindingState(BindingState.BOUND);

		// create annotated nodes and links
		for (PSNode node : SpecificationUtil.getAnnotatedElements(pattern))
		{
			if (SpecificationUtil.isNormal(node) || SpecificationUtil.isInSet(node))
			{
				// prepare data
				name = StorydrivenUtil.getValidName(pattern, "bound_" + node.getName());
				classifier = EcorePackage.Literals.EOBJECT;

				// create variable
				ObjectVariable variable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
				variable.setBindingState(BindingState.BOUND);

				// add bound link
				String qualifier = null;
				for (PSConnection in : node.getIncoming())
				{
					if (createAnnotation.equals(in.getSource()) && in instanceof PSLink)
					{
						qualifier = ((PSLink) in).getQualifier();
					}
				}

				// link them
				LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyNode, annotationVar, variable,
						AnnotationsPackage.Literals.ASG_ANNOTATION__ANNOTATED_ELEMENTS);
				linkVariable.setQualifierExpression(ExpressionsUtil.createQualifierExpression(qualifier));
				linkVariable.setBindingOperator(BindingOperator.CREATE);
			}
		}

		return storyNode;
	}


	private ActivityNode createStoreAntecedents()
	{
		// get pattern data
		PSAnnotation createAnnotation = SpecificationUtil.getCreateAnnotation(pattern);

		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Store Antecedent Relations");

		// create annotation variable
		String name = createdAnnotationVariable.getName();
		EClass classifier = generator.getAnnotationClass(pattern);
		ObjectVariable annotationVar = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
		annotationVar.setBindingState(BindingState.BOUND);

		// create nodes
		for (PSNode node : SpecificationUtil.getAnnotations(pattern))
		{
			if (!createAnnotation.equals(node) && node instanceof PSAnnotation && SpecificationUtil.isNormal(node))
			{
				// prepare data
				name = node.getName();
				classifier = generator.getAnnotationClass(((PSAnnotation) node).getType());

				// create variable
				ObjectVariable variable = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
				variable.setBindingState(BindingState.BOUND);

				// add antecedent links
				LinkVariable linkVariable = StorydrivenUtil.addLinkVariable(storyNode, annotationVar, variable,
						AnnotationsPackage.Literals.ASG_ANNOTATION__ANTECEDENT_ANNOS);
				linkVariable.setBindingOperator(BindingOperator.CREATE);
			}
		}

		return storyNode;
	}


	private ActivityNode createDestroyInvalidAnnotation()
	{
		// create story node
		StoryNode storyNode = StorydrivenUtil.addStoryNode(activity, "Destroy Invalid Annotation");

		// create annotation variable
		String name = createdAnnotationVariable.getName();
		EClass classifier = generator.getAnnotationClass(pattern);
		ObjectVariable annotationVar = StorydrivenUtil.addObjectVariable(storyNode, name, classifier);
		annotationVar.setBindingOperator(BindingOperator.DESTROY);

		return storyNode;
	}


	private ActivityNode createFindAdditionalElements()
	{
		Activity callable = generator.getActivity(pattern, Constants.METHOD_FIND_ADDITIONALS);
		ActivityCallNode callNode = StorydrivenUtil.addActivityCallNode(activity, callable);

		// bind 'this' parameter
		EParameter operationParameter = StorydrivenUtil.getEParameter(callable, Constants.VAR_THIS);
		EParameter callableParameter = StorydrivenUtil.getEParameter(activity, Constants.VAR_THIS);
		StorydrivenUtil.createParameterBindingForwarding(operationParameter, callableParameter, callNode);

		// bind 'annotation' parameter
		operationParameter = StorydrivenUtil.getEParameter(callable, Constants.VAR_ANNOTATION);
		StorydrivenUtil.createParameterBinding(operationParameter, createdAnnotationVariable, callNode);

		return callNode;
	}
}
