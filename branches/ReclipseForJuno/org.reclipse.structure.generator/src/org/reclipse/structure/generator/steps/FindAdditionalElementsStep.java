package org.reclipse.structure.generator.steps;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.reclipse.structure.generator.util.Constants;
import org.reclipse.structure.generator.util.ExpressionsUtil;
import org.reclipse.structure.generator.util.IGenerator;
import org.reclipse.structure.generator.util.NameUtil;
import org.reclipse.structure.generator.util.StorydrivenUtil;
import org.reclipse.structure.generator.util.more.Counter;
import org.reclipse.structure.generator.util.more.ExprUtil;
import org.reclipse.structure.generator.util.more.SDMUtil;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.util.ModelHelper;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityEdge;
import org.storydriven.storydiagrams.activities.ActivityFinalNode;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.InitialNode;
import org.storydriven.storydiagrams.activities.JunctionNode;
import org.storydriven.storydiagrams.activities.StoryNode;
import org.storydriven.storydiagrams.patterns.BindingSemantics;
import org.storydriven.storydiagrams.patterns.CollectionVariable;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.StoryPattern;


public class FindAdditionalElementsStep implements Constants
{
	private static enum AnnotationStoryType
	{
		CHECK, CREATE, REBIND;
	}

	private final static String OBJECT_IN_SET_PREFIX = "object_in_set__";

	private final static String SATISFIED_CONSTRAINT_NAME = "satisfiedConstraint";


	private final IGenerator generator;


	private Map<PSNode, ObjectVariable> storyItems;

	private Counter counter;

	private Activity activity;


	public FindAdditionalElementsStep(IGenerator generator)
	{
		this.generator = generator;
	}


	public void generate(Activity activity, PSPatternSpecification pattern)
	{
		generator.debug("Generating '%1s'...", NameUtil.getName(activity));

		this.activity = activity;
		PSNode trigger = generator.getTrigger(pattern);
		this.storyItems = new HashMap<PSNode, ObjectVariable>();
		this.counter = new Counter();


		// create the start activity
		InitialNode initialNode = StorydrivenUtil.addInitialNode(activity);

		// create the activity that re-binds the objects connected to the annotation via boundObjects link
		StoryNode storyNode = createAnnotationStoryActivity(trigger, AnnotationStoryType.REBIND, activity, false,
				storyItems, counter);
		ActivityNode lastActivity = storyNode;

		ObjectVariable annoObject = (ObjectVariable) SDMUtil.findVariableByName(storyNode.getStoryPattern(),
				VAR_ANNOTATION);
		EParameter param = StorydrivenUtil.getEParameter(activity, VAR_ANNOTATION);
		annoObject.setBindingExpression(ExpressionsUtil.createParameterExpression(param));

		// create transition between startActivity and lastActivity
		StorydrivenUtil.addTransition(initialNode, lastActivity);

		// prepare container
		Set<PSNode> nodesOfBoundObjects = new HashSet<PSNode>();
		Set<PSNode> nodesNotInFragments = new HashSet<PSNode>();
		Set<PSCombinedFragment> optionalFragments = new HashSet<PSCombinedFragment>();
		Set<PSSpecificationConstraint> constraintNotInFragments = new HashSet<PSSpecificationConstraint>();
		fillContainer(pattern, trigger, nodesOfBoundObjects, nodesNotInFragments, optionalFragments,
				constraintNotInFragments);

		/*
		 * Run through all non-optional PSNodes in the structural pattern rule and add the stories for checking the
		 * optional AttrExprPairs of these nodes.
		 */
		lastActivity = createCheckOptionalAttrExprPairStoriesForNodes(nodesOfBoundObjects, lastActivity);

		/*
		 * Run through all optional PSNodes in the structural pattern rule, add the stories for finding the objects for
		 * these nodes and for checking their optional AttrExprPairs.
		 */
		lastActivity = createFindOptionalNodesAndCheckAttrExprPairsStoriesForNodes(nodesNotInFragments,
				nodesOfBoundObjects, lastActivity);

		/*
		 * Run through all optional fragments in the structural pattern rule, add the stories for finding the objects for
		 * the contained nodes and for checking their optional AttrExprPairs.
		 */
		lastActivity = createFindNodesInOptionalFragmentsStoriesForFragments(optionalFragments, nodesOfBoundObjects,
				lastActivity);

		lastActivity = createCheckOptionalConstraintStoriesForConstraints(constraintNotInFragments, lastActivity);


		// create stop activity
		ActivityFinalNode finalNode = StorydrivenUtil.addSuccessFinalNode(activity);

		// create transition between lastActivity and stopActivity
		createTransitionFromLastActivityToNextActivity(lastActivity, finalNode);
	}


	private StoryNode createAnnotationStoryActivity(PSNode trigger, AnnotationStoryType purpose, Activity activity,
			boolean addSetResults, Map<PSNode, ObjectVariable> storyItems, Counter counter)
	{
		PSPatternSpecification pattern = trigger.getPatternSpecification();
		PSAnnotation createAnnotation = ModelHelper.getCreateAnnotation(pattern);

		// create story pattern
		String storyPatternName = "";
		switch (purpose)
		{
			case CHECK:
				storyPatternName = "Check if the matched pattern is already annotated and add it to the set";
				break;
			case CREATE:
				storyPatternName = "Create a new annotation for the matched pattern";
				break;
			case REBIND:
				storyPatternName = "Reconstruct the matched object structure";
				break;
		}
		StoryNode storyActivity = StorydrivenUtil.addStoryNode(activity, storyPatternName);
		StoryPattern storyPattern = storyActivity.getStoryPattern();
		EClass createAnnotationType = generator.getAnnotationClass(createAnnotation.getType());

		// add the annotation object
		ObjectVariable annotationObject = SDMUtil.createAnnotationObject(createAnnotation, createAnnotationType,
				(AnnotationStoryType.REBIND.equals(purpose)), (AnnotationStoryType.CREATE.equals(purpose)), storyPattern,
				storyItems, counter);
		if (AnnotationStoryType.REBIND.equals(purpose))
		{
			annotationObject.setName(VAR_ANNOTATION);
		}

		// provide classification information
		if (AnnotationStoryType.CREATE.equals(purpose))
		{
			// TODO: provide this information in future, for now this will work this way
		}

		// save annotated Objects for reuse
		HashMap<PSNode, ObjectVariable> annotatedObjects = new HashMap<PSNode, ObjectVariable>();

		if (!AnnotationStoryType.REBIND.equals(purpose))
		{
			// add all annotated objects and their links
			Iterator<PSConnection> iter = createAnnotation.getOutgoing().iterator();
			while (iter.hasNext())
			{
				PSConnection connection = iter.next();
				if (connection instanceof PSLink)
				{
					PSLink psLink = (PSLink) connection;
					PSNode targetNode = psLink.getTarget();
					// add the annotated object
					ObjectVariable annotatedObject = annotatedObjects.get(targetNode);
					if (annotatedObject == null)
					{
						if (!AnnotationStoryType.CHECK.equals(purpose)
								|| (targetNode.getParents().isEmpty() && !targetNode.getModifier().equals(ModifierType.SET)))
						{
							if (targetNode instanceof PSAnnotation)
							{
								EClass annoType = generator.getAnnotationClass(((PSAnnotation) targetNode).getType());
								annotatedObject = SDMUtil.createAnnotationObject((PSAnnotation) targetNode, annoType, true,
										false, storyPattern, storyItems, counter);
							}
							else
							{
								annotatedObject = SDMUtil.createObject((PSObject) targetNode, true, false, storyPattern,
										storyItems, counter);
							}

							if (ModelHelper.isSearchForThisOptional(targetNode) && targetNode != trigger)
							{
								annotatedObject.setBindingSemantics(BindingSemantics.OPTIONAL);
							}
						}
						annotatedObjects.put(targetNode, annotatedObject);
					}

					// add the annotations link
					ObjectVariable source = storyItems.get(psLink.getSource());
					ObjectVariable target = storyItems.get(psLink.getTarget());

					boolean addSetToResult = false;
					if ((source instanceof CollectionVariable || target instanceof CollectionVariable)
							&& psLink.getInstanceOf() == AnnotationsPackage.Literals.ASG_ANNOTATION__ANNOTATED_ELEMENTS)
					{
						// FIXME: little hack in here.
						// Actually this should only check, if there is already an annotation for these arguments
						// but in case of set-objects, we want to collect them
						addSetToResult = true;
					}

					if (!AnnotationStoryType.CHECK.equals(purpose)
							|| (targetNode.getParents().isEmpty() && !targetNode.getModifier().equals(ModifierType.SET)))
					{
						SDMUtil.createLink(source, target, psLink.getInstanceOf(), psLink.getQualifier(),
								(AnnotationStoryType.CREATE.equals(purpose) || addSetToResult), BindingSemantics.MANDATORY,
								storyPattern);
					}
				}
			}
		}

		// add "maybe ..." constraints
		Iterator<PSSpecificationConstraint> iter = pattern.getConstraints().iterator();
		while (iter.hasNext())
		{
			PSSpecificationConstraint psConstraint = iter.next();

			if (psConstraint.getExpression().toLowerCase().startsWith(PREFIX_ADDITIONAL_CONSTRAINT))
			{
				SDMUtil.createConstraint(psConstraint.getExpression(), storyPattern);
			}
		}

		if (AnnotationStoryType.CREATE.equals(purpose) || AnnotationStoryType.REBIND.equals(purpose))
		{
			linkBoundObjects(trigger, storyPattern, annotationObject, annotatedObjects,
					(AnnotationStoryType.CREATE.equals(purpose)), (!AnnotationStoryType.REBIND.equals(purpose)),
					(!AnnotationStoryType.REBIND.equals(purpose)), storyItems, counter);
		}

		if (AnnotationStoryType.CREATE.equals(purpose))
		{
			// link to annotation result set
			ObjectVariable annotationResultSet = SDMUtil.createAnnotationResultSetObject(storyPattern, true, false);
			SDMUtil.createAnnotationResultSetLink(annotationResultSet, annotationObject, true, storyPattern);
		}
		else if (AnnotationStoryType.CHECK.equals(purpose))
		{
			// link to annotation result set
			ObjectVariable annotationResultSet = SDMUtil.createAnnotationResultSetObject(storyPattern, true, false);
			SDMUtil.createAnnotationResultSetLink(annotationResultSet, annotationObject, false, storyPattern);
		}
		if (addSetResults)
		{
			ObjectVariable setResult = SDMUtil.createSetResultSetObject(storyPattern, true, false);
			SDMUtil.createLink(annotationObject, setResult, AnnotationsPackage.Literals.ASG_ANNOTATION__SET_RESULT_SET,
					null, true, BindingSemantics.MANDATORY, storyPattern);
		}
		return storyActivity;
	}


	private void linkBoundObjects(PSNode trigger, StoryPattern storyPattern, ObjectVariable annotationObject,
			Map<PSNode, ObjectVariable> annotatedObjects, boolean bound, boolean addNodeExpressions,
			boolean linksAreCreate, Map<PSNode, ObjectVariable> storyItems, Counter counter)
	{
		PSPatternSpecification pattern = trigger.getPatternSpecification();

		// run through all PSNodes in the structural pattern rule
		for (PSNode psNode : pattern.getNodes())
		{
			if (psNode != ModelHelper.getCreateAnnotation(pattern) && psNode.getModifier() != ModifierType.NEGATIVE)
			{
				if (psNode.getName() == null || psNode.getName().equals(""))
				{
					throw new IllegalStateException("PSNode object \"" + psNode + "\" has no name.");
				}

				ObjectVariable boundObject = annotatedObjects.get(psNode);
				if (boundObject == null)
				{
					if (ModelHelper.isMatchingRequired(psNode, trigger))
					{
						/*
						 * Do not re-bind optional annotated nodes that are not trigger, these will be checked later.
						 */
						if (addNodeExpressions || // !addNodeExpressions ==> (purpose == REBIND)
								!ModelHelper.isAnnotatedOptionalNodeToBeCheckedLater(psNode, trigger))
						{
							if (psNode instanceof PSObject)
							{
								boundObject = SDMUtil.createObject((PSObject) psNode, bound, addNodeExpressions, storyPattern,
										storyItems, counter);
							}
							else if (psNode instanceof PSAnnotation && !ModelHelper.isCreate((PSAnnotation) psNode))
							{
								EClass annoType = generator.getAnnotationClass(((PSAnnotation) psNode).getType());
								boundObject = SDMUtil.createAnnotationObject((PSAnnotation) psNode, annoType, bound, false,
										storyPattern, storyItems, counter);
							}

							if (psNode.equals(trigger) && ModifierType.ADDITIONAL.equals(psNode.getModifier())
									&& boundObject != null)
							{
								// trigger object should not be optional
								boundObject.setBindingSemantics(BindingSemantics.MANDATORY);
							}
						}
					}
				}

				// add the boundObjects link
				if (boundObject != null)
				{
					SDMUtil.createBoundObjectsLink(annotationObject, boundObject, psNode, linksAreCreate, storyPattern);
				}
			}
		}
	}


	private ActivityNode createCheckOptionalAttrExprPairStoriesForNodes(Set<PSNode> nodes, ActivityNode lastActivity)
	{
		ActivityNode tmpLastActivity = lastActivity;
		ActivityNode forEachActivity = null;

		for (PSNode node : nodes)
		{
			if (node.getModifier() != ModifierType.NEGATIVE)
			{
				boolean isASetWithOptionalAttrExprPairs = (node.getModifier() == ModifierType.SET)
						&& hasOptionalAttrExprPairs(node);
				if (isASetWithOptionalAttrExprPairs)
				{
					forEachActivity = createBindObjectsInSetActivity(node, tmpLastActivity, activity);
					tmpLastActivity = forEachActivity;
				}

				int index = 0;
				boolean firstExpr = true;

				for (PSNodeConstraint expr : node.getNodeConstraints())
				{
					if (expr instanceof PSAttributeConstraint && ((PSAttributeConstraint) expr).isAdditional())
					{
						EdgeGuard transitionGuard = EdgeGuard.NONE;
						if (isASetWithOptionalAttrExprPairs && firstExpr)
						{
							transitionGuard = EdgeGuard.EACH_TIME;
						}
						else if (isForEachActivity(tmpLastActivity)) // last activity was a "for
						// each"-activity
						{
							transitionGuard = EdgeGuard.END;
						}

						tmpLastActivity = this.createCheckOptionalAttrExprPairStory((PSAttributeConstraint) expr, index,
								tmpLastActivity, transitionGuard, activity);

						firstExpr = false;
					}

					index++;
				}

				if (isASetWithOptionalAttrExprPairs)
				{
					// create transition between tmpLastActivity and forEachActivity
					SDMUtil.createTransition(tmpLastActivity, forEachActivity, EdgeGuard.NONE, activity);

					tmpLastActivity = forEachActivity;
					forEachActivity = null;
				}
			}
		}

		return tmpLastActivity;
	}


	private static boolean hasOptionalAttrExprPairs(PSNode node)
	{
		Iterator<PSNodeConstraint> exprIter = node.getNodeConstraints().iterator();
		while (exprIter.hasNext())
		{
			PSNodeConstraint expr = exprIter.next();

			if (expr instanceof PSAttributeConstraint && ((PSAttributeConstraint) expr).isAdditional())
			{
				return true;
			}
		}
		return false;
	}


	private ActivityEdge createTransitionFromLastActivityToNextActivity(ActivityNode lastActivity,
			ActivityNode nextActivity)
	{
		EdgeGuard transitionGuard = EdgeGuard.NONE;
		if (isForEachActivity(lastActivity))
		{
			transitionGuard = EdgeGuard.END;
		}

		// create transition between tmpLastActivity and forEachActivity
		return SDMUtil.createTransition(lastActivity, nextActivity, transitionGuard, activity);
	}


	private static boolean isForEachActivity(ActivityNode activity)
	{
		if (activity instanceof StoryNode && ((StoryNode) activity).isForEach())
		{
			return true;
		}
		return false;
	}


	private void fillContainer(PSPatternSpecification pattern, PSNode trigger, Set<PSNode> nodesOfBoundObjects,
			Set<PSNode> nodesNotInFragments, Set<PSCombinedFragment> optionalFragments,
			Set<PSSpecificationConstraint> constraintNotInFragments)
	{
		Iterator<PSNode> iter = pattern.getNodes().iterator();
		while (iter.hasNext())
		{
			PSNode node = iter.next();
			if (node != ModelHelper.getCreateAnnotation(pattern))
			{
				if (ModelHelper.isMatchingRequired(node, trigger))
				{
					// check optional annotated nodes that are not trigger separately
					if (ModelHelper.isAnnotatedOptionalNodeToBeCheckedLater(node, trigger))
					{
						nodesNotInFragments.add(node);
					}
					else
					{
						nodesOfBoundObjects.add(node);
					}
				}
				else if (!ModelHelper.isWithinOptionalFragment(node))
				{
					nodesNotInFragments.add(node);
				}
			}
		}

		Iterator<PSCombinedFragment> fragIter = pattern.getCombinedFragments().iterator();
		while (fragIter.hasNext())
		{
			PSCombinedFragment fragment = fragIter.next();
			if (ModifierType.ADDITIONAL.equals(fragment.getKind()))
			{
				optionalFragments.add(fragment);
			}
		}

		Iterator<PSSpecificationConstraint> constraintIter = pattern.getConstraints().iterator();
		while (constraintIter.hasNext())
		{
			PSSpecificationConstraint constraint = constraintIter.next();
			if (!ModelHelper.isWithinOptionalFragment(constraint))
			{
				constraintNotInFragments.add(constraint);
			}
		}
	}


	private ActivityNode createCheckOptionalConstraintStoriesForConstraints(Set<PSSpecificationConstraint> constraints,
			ActivityNode lastActivity)
	{
		ActivityNode tmpLastActivity = lastActivity;

		PSSpecificationConstraint constraint;
		Iterator<PSSpecificationConstraint> iter = constraints.iterator();
		while (iter.hasNext())
		{
			constraint = iter.next();
			if (constraint.isAdditional())
			{
				tmpLastActivity = createCheckOptionalConstraintStory(constraint, tmpLastActivity, activity);
			}
		}

		return tmpLastActivity;
	}


	private ActivityNode createBindObjectsInSetActivity(PSNode setNode, ActivityNode lastActivity, Activity activity)
	{
		if (setNode.getModifier() != ModifierType.SET)
		{
			throw new IllegalArgumentException("The node argument has to represent a set.");
		}

		// create "for each"-activity that successively binds each of the objects in a set
		StoryNode bindObjectsInSetStoryActivity = StorydrivenUtil.addStoryNode(activity, "Bind an object of a set");
		StoryPattern bindObjectsInSetStoryPattern = bindObjectsInSetStoryActivity.getStoryPattern();
		bindObjectsInSetStoryActivity.setForEach(true);

		// create transition between lastActivity and bindObjectsInSetStoryActivity
		createTransitionFromLastActivityToNextActivity(lastActivity, bindObjectsInSetStoryActivity);

		// add the annotation object
		PSAnnotation createAnnotation = ModelHelper.getCreateAnnotation(setNode.getPatternSpecification());
		EClass annoType = generator.getAnnotationClass(createAnnotation.getType());
		ObjectVariable annotationObject = SDMUtil.createAnnotationObject(createAnnotation, annoType, true, false,
				bindObjectsInSetStoryPattern, null, counter);
		annotationObject.setName(VAR_ANNOTATION);

		// add the bound object
		ObjectVariable boundObject = SDMUtil.createObject(setNode, false, false, bindObjectsInSetStoryPattern,
				storyItems, counter, generator);

		if (boundObject != null)
		{
			// this is a temporary object, not the original one
			boundObject.setName(OBJECT_IN_SET_PREFIX + boundObject.getName());
			// boundObject.setType(UMLObject.NORM); // ensure the UMLObject is not a set
			// link bound object to annotation object
			SDMUtil.createBoundObjectsLink(annotationObject, boundObject, setNode, false, bindObjectsInSetStoryPattern);
		}
		return bindObjectsInSetStoryActivity;
	}


	private ActivityNode createFindOptionalNodesAndCheckAttrExprPairsStoriesForNodes(Set<PSNode> nodesToFind,
			Set<PSNode> nodesOfBoundObjects, ActivityNode lastActivity)
	{
		ActivityNode tmpLastActivity = lastActivity;

		PSNode psNode;
		Iterator<PSNode> iter = nodesToFind.iterator();
		while (iter.hasNext())
		{
			psNode = iter.next();
			if (psNode.getModifier() == ModifierType.ADDITIONAL)
			{
				// create activity that tries to find a matching object for the optional node
				StoryNode findObjectStoryActivity = createFindObjectStoryActivity(psNode, nodesOfBoundObjects);

				// create transition between tmpLastActivity and findObjectStoryActivity
				createTransitionFromLastActivityToNextActivity(tmpLastActivity, findObjectStoryActivity);


				// create activity that remembers the matching object via boundObjects link
				Set<PSNode> nodesSet = new HashSet<PSNode>(1);
				nodesSet.add(psNode);
				StoryNode rememberObjectStoryActivity = createRememberFoundObjectsStoryActivity(nodesSet);

				// create transition between findObjectStoryActivity and rememberObjectStoryActivity
				SDMUtil.createTransition(findObjectStoryActivity, rememberObjectStoryActivity, EdgeGuard.SUCCESS, activity);


				// create nop activity
				JunctionNode nopActivity = SDMUtil.createNopActivity(activity);

				// create transition between findObjectStoryActivity and nopActivity
				SDMUtil.createTransition(findObjectStoryActivity, nopActivity, EdgeGuard.FAILURE, activity);


				ActivityNode nextActivity = rememberObjectStoryActivity;
				if (psNode instanceof PSObject)
				{
					nodesSet = new HashSet<PSNode>(1);
					nodesSet.add(psNode);

					nextActivity = createCheckOptionalAttrExprPairStoriesForNodes(nodesSet, nextActivity);
				}

				// create transition between nextActivity and nopActivity
				createTransitionFromLastActivityToNextActivity(nextActivity, nopActivity);

				tmpLastActivity = nopActivity;
			}
		}

		return tmpLastActivity;
	}


	private ActivityNode createFindNodesInOptionalFragmentsStoriesForFragments(Set<PSCombinedFragment> fragments,
			Set<PSNode> nodesOfBoundObjects, ActivityNode lastActivity)
	{
		ActivityNode tmpLastActivity = lastActivity;

		PSCombinedFragment fragment;
		Set<PSNode> notOptionalChildNodes;
		Set<PSNode> optionalChildNodes;
		Set<PSSpecificationConstraint> constraints;
		Iterator<PSCombinedFragmentItem> childIter;
		Iterator<PSCombinedFragment> fragmentIter = fragments.iterator();
		while (fragmentIter.hasNext())
		{
			fragment = fragmentIter.next();

			// collect child nodes and constraints
			notOptionalChildNodes = new HashSet<PSNode>();
			optionalChildNodes = new HashSet<PSNode>();
			constraints = new HashSet<PSSpecificationConstraint>();
			childIter = fragment.getChildren().iterator();
			while (childIter.hasNext())
			{
				PSCombinedFragmentItem child = childIter.next();
				if (child instanceof PSNode)
				{
					if (((PSNode) child).getModifier() == ModifierType.ADDITIONAL)
					{
						optionalChildNodes.add((PSNode) child);
					}
					else
					{
						notOptionalChildNodes.add((PSNode) child);
					}
				}
				else if (child instanceof PSSpecificationConstraint)
				{
					constraints.add((PSSpecificationConstraint) child);
				}
			}

			// create activity that tries to find objects for all non-optional nodes in the fragment
			ActivityNode findObjectsActivity = createFindObjectsInFragmentStoryActivity(constraints,
					notOptionalChildNodes, nodesOfBoundObjects, activity, storyItems, counter);

			// create transition between tmpLastActivity and findObjectsActivity
			createTransitionFromLastActivityToNextActivity(tmpLastActivity, findObjectsActivity);


			// create activity that remembers all objects found
			ActivityNode rememberObjectsActivity = createRememberFoundObjectsStoryActivity(notOptionalChildNodes);

			// create transition between findObjectsActivity and rememberObjectsActivity
			SDMUtil.createTransition(findObjectsActivity, rememberObjectsActivity, EdgeGuard.SUCCESS, activity);


			// check the optional expressions of non-optional nodes in the fragment
			ActivityNode nextActivity = rememberObjectsActivity;
			nextActivity = this.createCheckOptionalAttrExprPairStoriesForNodes(notOptionalChildNodes, nextActivity);

			// create a set of the nodes previously bound or bound in the findObjectsActivity
			Set<PSNode> newSetOfNodesOfBoundObjects = new HashSet<PSNode>(nodesOfBoundObjects);

			Iterator<PSNode> iter = notOptionalChildNodes.iterator();
			while (iter.hasNext())
			{
				PSNode node = iter.next();
				if (node.getModifier() != ModifierType.NEGATIVE)
				{
					newSetOfNodesOfBoundObjects.add(node);
				}
			}

			// try to find objects for optional nodes in the fragment and check their optional
			// expressions
			nextActivity = createFindOptionalNodesAndCheckAttrExprPairsStoriesForNodes(optionalChildNodes,
					newSetOfNodesOfBoundObjects, nextActivity);

			// check fragment's optional constraints
			nextActivity = createCheckOptionalConstraintStoriesForConstraints(constraints, nextActivity);


			// create nop activity
			JunctionNode nopActivity = SDMUtil.createNopActivity(activity);

			// create transition between findObjectStoryActivity and nopActivity
			SDMUtil.createTransition(findObjectsActivity, nopActivity, EdgeGuard.FAILURE, activity);

			// create transition between nextActivity and nopActivity
			createTransitionFromLastActivityToNextActivity(nextActivity, nopActivity);

			tmpLastActivity = nopActivity;
		}

		return tmpLastActivity;
	}


	private ActivityNode createCheckOptionalConstraintStory(PSSpecificationConstraint constraint,
			ActivityNode lastActivity, Activity activity)
	{
		if (!constraint.isAdditional())
		{
			throw new IllegalArgumentException("Constraint must be optional.");
		}

		if (constraint.getName() == null || constraint.getName().equals(""))
		{
			throw new IllegalStateException("PSConstraint object \"" + constraint + "\" has no name.");
		}

		// create activity that checks the constraint
		StoryNode checkConstraintStoryActivity = StorydrivenUtil.addStoryNode(activity, "Check constraint");
		StoryPattern checkConstraintStoryPattern = checkConstraintStoryActivity.getStoryPattern();

		// add the constraint to the activity
		SDMUtil.createConstraint(constraint.getExpression(), checkConstraintStoryPattern);

		// create transition between lastActivity and checkConstraintStoryActivity
		createTransitionFromLastActivityToNextActivity(lastActivity, checkConstraintStoryActivity);


		// create the story activity that remembers, that the constraint was satisfied
		StoryNode constraintSatisfiedStoryActivity = StorydrivenUtil.addStoryNode(activity,
				"Remember constraint is satisfied");
		StoryPattern constraintSatisfiedStoryPattern = constraintSatisfiedStoryActivity.getStoryPattern();

		// create the annotation object
		PSAnnotation anno = ModelHelper.getCreateAnnotation(constraint.getPatternSpecification());
		ObjectVariable annotationObject = SDMUtil.createObject(anno, true, false, constraintSatisfiedStoryPattern,
				storyItems, counter, generator);
		annotationObject.setName(VAR_ANNOTATION);

		// TODO: what is the new equivalent feature/reference to satisfiedConstraints?? replace or remove this
		// create collaboration statement calling the method
		// StringBuffer callText = new StringBuffer();
		// callText.append("addToSatisfiedConstraints(\"");
		// callText.append(constraint.getName());
		// callText.append("\")");
		// StoryDiagramFactory.createCollabStatWithMaster(1, null, annotationObject, callText.toString(), null,
		// constraintSatisfiedStoryPattern);

		// create transition between checkConstraintStoryActivity and constraintSatisfiedStoryActivity
		SDMUtil.createTransition(checkConstraintStoryActivity, constraintSatisfiedStoryActivity, EdgeGuard.SUCCESS,
				activity);


		// create nop activity
		JunctionNode nopActivity = SDMUtil.createNopActivity(activity);

		// create transition between checkConstraintStoryActivity and nopActivity
		SDMUtil.createTransition(checkConstraintStoryActivity, nopActivity, EdgeGuard.FAILURE, activity);

		// create transition between constraintSatisfiedStoryActivity and nopActivity
		SDMUtil.createTransition(constraintSatisfiedStoryActivity, nopActivity, EdgeGuard.NONE, activity);

		return nopActivity;
	}


	private ActivityNode createCheckOptionalAttrExprPairStory(PSAttributeConstraint optionalAttrExprPair, int exprID,
			ActivityNode lastActivity, EdgeGuard transitionGuard, Activity activity)
	{
		if (!optionalAttrExprPair.isAdditional())
		{
			throw new IllegalArgumentException("Expression must be optional.");
		}

		// create activity that checks the expression
		StoryNode checkExprStoryActivity = StorydrivenUtil.addStoryNode(activity, "Check AttrExprPair " + exprID);
		StoryPattern checkExprStoryPattern = checkExprStoryActivity.getStoryPattern();

		// add the object to the activity
		PSObject psObject = (PSObject) optionalAttrExprPair.getNode();
		ObjectVariable object = SDMUtil.createObject(psObject, true, false, checkExprStoryPattern, storyItems, counter);

		if (psObject.getModifier() == ModifierType.SET)
		{
			object.setName(OBJECT_IN_SET_PREFIX + object.getName());
		}

		// add the AttrExprPair to be checked to the object
		SDMUtil.createAttributeExpression(optionalAttrExprPair, object);

		// create transition between lastActivity and checkExprStoryActivity
		SDMUtil.createTransition(lastActivity, checkExprStoryActivity, transitionGuard, activity);


		// create the story activity that remembers, that the expression was satisfied
		StoryNode exprSatisfiedStoryActivity = StorydrivenUtil.addStoryNode(activity, "Remember expression is satisfied");
		StoryPattern exprSatisfiedStoryPattern = exprSatisfiedStoryActivity.getStoryPattern();

		// create the annotation object
		PSAnnotation createAnnotation = ModelHelper.getCreateAnnotation(optionalAttrExprPair.getNode()
				.getPatternSpecification());
		ObjectVariable annotationObject = SDMUtil.createObject(createAnnotation, true, false, exprSatisfiedStoryPattern,
				storyItems, counter, generator);
		annotationObject.setName(VAR_ANNOTATION);

		// create satisfied constraint object
		ObjectVariable satisfiedConstraint = SDMUtil.createObject(
				AnnotationsPackage.eINSTANCE.getSatisfiedAttributeConstraint(), SATISFIED_CONSTRAINT_NAME, false, true,
				exprSatisfiedStoryPattern);

		// set attribute index
		SDMUtil.createAttributeAssignment(satisfiedConstraint,
				AnnotationsPackage.eINSTANCE.getSatisfiedAttributeConstraint_AttributeIndex(), ExprUtil.eInt(exprID));
		// set node id, which is the name of the psObject
		SDMUtil.createAttributeAssignment(satisfiedConstraint,
				AnnotationsPackage.eINSTANCE.getSatisfiedAttributeConstraint_NodeID(),
				ExprUtil.eString(optionalAttrExprPair.getNode().getName()));
		// set reference to the attribute owner
		ObjectVariable ownerNodeObject = SDMUtil.createObject(psObject.getInstanceOf(), psObject.getName(), true, false,
				exprSatisfiedStoryPattern);
		SDMUtil.createLink(satisfiedConstraint, ownerNodeObject,
				AnnotationsPackage.eINSTANCE.getSatisfiedAttributeConstraint_Context(), null, true,
				BindingSemantics.MANDATORY, exprSatisfiedStoryPattern);
		// add the satisfied object to the annotation
		SDMUtil.createLink(annotationObject, satisfiedConstraint,
				AnnotationsPackage.eINSTANCE.getASGAnnotation_SatisfiedConstraints(), null, true,
				BindingSemantics.MANDATORY, exprSatisfiedStoryPattern);

		// create transition between checkExprStoryActivity and exprSatisfiedStoryActivity
		SDMUtil.createTransition(checkExprStoryActivity, exprSatisfiedStoryActivity, EdgeGuard.SUCCESS, activity);


		// create nop activity
		JunctionNode nopActivity = SDMUtil.createNopActivity(activity);

		// create transition between checkExprStoryActivity and nopActivity
		SDMUtil.createTransition(checkExprStoryActivity, nopActivity, EdgeGuard.FAILURE, activity);

		// create transition between exprSatisfiedStoryActivity and nopActivity
		SDMUtil.createTransition(exprSatisfiedStoryActivity, nopActivity, EdgeGuard.NONE, activity);

		return nopActivity;
	}


	private StoryNode createFindObjectsInFragmentStoryActivity(Set<PSSpecificationConstraint> constraints,
			Set<PSNode> notOptionalChildNodes, Set<PSNode> nodesOfBoundObjects, Activity activity,
			Map<PSNode, ObjectVariable> storyItems, Counter counter)
	{
		// create activity that tries to find a matching object for the optional node
		StoryNode findObjectsInFragmentStoryActivity = StorydrivenUtil.addStoryNode(activity,
				"Try finding object(s) in optional fragment");
		StoryPattern findObjectsInFragmentStoryPattern = findObjectsInFragmentStoryActivity.getStoryPattern();

		// add all nodes first
		Map<PSNode, ObjectVariable> nodesOfAddedObjects = new HashMap<PSNode, ObjectVariable>();
		PSNode psNode;
		ObjectVariable theObjectToFind;
		Iterator<PSNode> nodeIter = notOptionalChildNodes.iterator();
		while (nodeIter.hasNext())
		{
			psNode = nodeIter.next();

			theObjectToFind = null;
			if (!nodesOfAddedObjects.containsKey(psNode))
			{
				// create the object to be found
				if (psNode instanceof PSObject)
				{
					theObjectToFind = SDMUtil.createObject((PSObject) psNode, false, true,
							findObjectsInFragmentStoryPattern, storyItems, counter);
				}
				else if (psNode instanceof PSAnnotation)
				{
					theObjectToFind = SDMUtil.createObject(psNode, false, false, findObjectsInFragmentStoryPattern,
							storyItems, counter, generator);
				}
				if (psNode.getModifier() == ModifierType.ADDITIONAL && theObjectToFind != null)
				{
					// ensure the UMLObject to be found is not optional
					theObjectToFind.setBindingSemantics(BindingSemantics.MANDATORY);
				}
				nodesOfAddedObjects.put(psNode, theObjectToFind);

				// create the bound objects linked to the object to be found
				addLinkedBoundObjects(psNode, true, nodesOfAddedObjects, nodesOfBoundObjects,
						findObjectsInFragmentStoryPattern, storyItems, counter);
				addLinkedBoundObjects(psNode, false, nodesOfAddedObjects, nodesOfBoundObjects,
						findObjectsInFragmentStoryPattern, storyItems, counter);
			}
		}

		// add all links now
		Set<PSConnection> addedConnections = new HashSet<PSConnection>();
		nodeIter = notOptionalChildNodes.iterator();
		while (nodeIter.hasNext())
		{
			psNode = nodeIter.next();

			this.connectObject(psNode, true, nodesOfAddedObjects, addedConnections, findObjectsInFragmentStoryPattern);
			this.connectObject(psNode, false, nodesOfAddedObjects, addedConnections, findObjectsInFragmentStoryPattern);
		}


		// add non-optional constraints of the fragment
		PSSpecificationConstraint constraint;
		Iterator<PSSpecificationConstraint> iter = constraints.iterator();
		while (iter.hasNext())
		{
			constraint = iter.next();
			if (!constraint.isAdditional())
			{
				SDMUtil.createConstraint(constraint.getExpression(), findObjectsInFragmentStoryPattern);
			}
		}

		return findObjectsInFragmentStoryActivity;
	}


	private StoryNode createFindObjectStoryActivity(PSNode psNode, Set<PSNode> nodesOfBoundObjects)
	{
		// create activity that tries to find a matching object for the optional node
		StoryNode findObjectStoryActivity = StorydrivenUtil.addStoryNode(activity, "Try finding object");
		StoryPattern findObjectStoryPattern = findObjectStoryActivity.getStoryPattern();

		// create the object to be found
		Map<PSNode, ObjectVariable> nodesOfAddedObjects = new HashMap<PSNode, ObjectVariable>();

		ObjectVariable theObjectToFind = null;
		if (psNode instanceof PSObject)
		{
			theObjectToFind = SDMUtil.createObject((PSObject) psNode, false, true, findObjectStoryPattern, storyItems,
					counter);
		}
		else if (psNode instanceof PSAnnotation)
		{
			PSAnnotation anno = (PSAnnotation) psNode;
			EClass annoType = generator.getAnnotationClass(anno.getType());
			theObjectToFind = SDMUtil.createAnnotationObject(anno, annoType, false, false, findObjectStoryPattern,
					storyItems, counter);
		}

		if (psNode.getModifier() == ModifierType.ADDITIONAL && theObjectToFind != null)
		{
			// ensure the UMLObject to be found is not optional
			theObjectToFind.setBindingSemantics(BindingSemantics.MANDATORY);
		}
		nodesOfAddedObjects.put(psNode, theObjectToFind);

		// create the bound objects linked to the object to be found
		addLinkedBoundObjects(psNode, true, nodesOfAddedObjects, nodesOfBoundObjects, findObjectStoryPattern, storyItems,
				counter);
		addLinkedBoundObjects(psNode, false, nodesOfAddedObjects, nodesOfBoundObjects, findObjectStoryPattern,
				storyItems, counter);

		// link the objects
		this.connectObject(psNode, true, nodesOfAddedObjects, null, findObjectStoryPattern);
		this.connectObject(psNode, false, nodesOfAddedObjects, null, findObjectStoryPattern);

		return findObjectStoryActivity;
	}


	private void addLinkedBoundObjects(PSNode psNode, boolean targetObjects,
			Map<PSNode, ObjectVariable> nodesOfAddedObjects, Set<PSNode> nodesOfBoundObjects, StoryPattern storyPattern,
			Map<PSNode, ObjectVariable> storyItems, Counter counter)
	{
		// create the bound objects linked to the object to be found
		PSConnection connection;
		PSNode connectedNode;
		ObjectVariable connectedObject = null;
		Iterator<PSConnection> connectionIter = targetObjects ? psNode.getOutgoing().iterator() : psNode.getIncoming()
				.iterator();
		while (connectionIter.hasNext())
		{
			connection = connectionIter.next();
			connectedNode = targetObjects ? connection.getTarget() : connection.getSource();

			// only add bound objects
			if (nodesOfBoundObjects.contains(connectedNode))
			{
				if (nodesOfAddedObjects == null || !nodesOfAddedObjects.containsKey(connectedNode))
				{
					// create the connected object
					connectedObject = SDMUtil.createObject(connectedNode, true, false, storyPattern, storyItems, counter,
							generator);

					if (psNode.getModifier() == ModifierType.ADDITIONAL && connectedObject != null)
					{
						// ensure the connected object is not optional
						connectedObject.setBindingSemantics(BindingSemantics.MANDATORY);
					}
					if (nodesOfAddedObjects != null)
					{
						nodesOfAddedObjects.put(connectedNode, connectedObject);
					}
				}
			}
		} // while
	}


	private void connectObject(PSNode psNode, boolean targetObjects, Map<PSNode, ObjectVariable> nodesOfAddedObjects,
			Set<PSConnection> addedConnections, StoryPattern storyPattern)
	{
		PSConnection connection;
		PSNode connectedNode;
		ObjectVariable object, connectedObject;
		Iterator<PSConnection> connectionIter = targetObjects ? psNode.getOutgoing().iterator() : psNode.getIncoming()
				.iterator();
		while (connectionIter.hasNext())
		{
			connection = connectionIter.next();
			connectedNode = targetObjects ? connection.getTarget() : connection.getSource();

			object = nodesOfAddedObjects.get(psNode);
			connectedObject = nodesOfAddedObjects.get(connectedNode);

			// link the objects
			if (object != null && connectedObject != null)
			{
				if (addedConnections == null || !addedConnections.contains(connection))
				{
					if (connection instanceof PSLink)
					{
						PSLink psLink = (PSLink) connection;
						String role = null;
						if (psNode instanceof PSAnnotation)
						{
							role = psLink.getQualifier();
						}
						if (targetObjects)
						{
							SDMUtil.createLink(object, connectedObject, psLink.getInstanceOf(), role, false,
									BindingSemantics.MANDATORY, storyPattern);
						}
						else
						{
							SDMUtil.createLink(connectedObject, object, psLink.getInstanceOf(), role, false,
									BindingSemantics.MANDATORY, storyPattern);
						}
					}
					else if (connection instanceof PSPath)
					{
						if (targetObjects)
						{
							SDMUtil.createPath((PSPath) connection, object, connectedObject, storyPattern);
						}
						else
						{
							SDMUtil.createPath((PSPath) connection, connectedObject, object, storyPattern);
						}
					}

					if (addedConnections != null)
					{
						addedConnections.add(connection);
					}
				}
			}
		} // while
	}


	private StoryNode createRememberFoundObjectsStoryActivity(Set<PSNode> nodesOfObjectsFound)
	{
		// create activity that remembers the matching object via boundObjects link
		StoryNode rememberObjectStoryActivity = StorydrivenUtil.addStoryNode(activity, "Remember the object(s) found");
		StoryPattern rememberObjectStoryPattern = rememberObjectStoryActivity.getStoryPattern();

		if (!nodesOfObjectsFound.isEmpty())
		{
			// add the annotation object
			PSNode aNode = nodesOfObjectsFound.iterator().next();
			PSAnnotation createAnnotation = ModelHelper.getCreateAnnotation(aNode.getPatternSpecification());
			EClass annoType = generator.getAnnotationClass(createAnnotation.getType());
			ObjectVariable annotationObject = SDMUtil.createAnnotationObject(createAnnotation, annoType, true, false,
					rememberObjectStoryPattern, null, counter);
			annotationObject.setName(VAR_ANNOTATION);

			Iterator<PSNode> nodeIter = nodesOfObjectsFound.iterator();
			while (nodeIter.hasNext())
			{
				PSNode psNode = nodeIter.next();

				if (psNode.getModifier() != ModifierType.NEGATIVE)
				{
					// add the bound object
					ObjectVariable boundObject = SDMUtil.createObject(psNode, true, false, rememberObjectStoryPattern,
							storyItems, counter, generator);


					if (psNode.getModifier() == ModifierType.ADDITIONAL && boundObject != null)
					{
						// ensure the UMLObject to be linked is not optional
						boundObject.setBindingSemantics(BindingSemantics.MANDATORY);
					}

					// link bound object to annotation object
					SDMUtil.createBoundObjectsLink(annotationObject, boundObject, psNode, true, rememberObjectStoryPattern);

					// also create antecedentAnnos link
					if (psNode instanceof PSAnnotation)
					{
						this.createAntecedentAnnosLink(annotationObject, boundObject, rememberObjectStoryPattern);
					}
				}
			}
		}

		return rememberObjectStoryActivity;
	}


	private void createAntecedentAnnosLink(ObjectVariable annotationObject, ObjectVariable annotatedObject,
			StoryPattern storyPattern)
	{
		// search for assoc
		EReference assoc = AnnotationsPackage.Literals.ASG_ANNOTATION__ANTECEDENT_ANNOS;

		// add the antecedentAnnos link
		SDMUtil
				.createLink(annotationObject, annotatedObject, assoc, null, true, BindingSemantics.MANDATORY, storyPattern);
	}
}
