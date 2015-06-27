package org.reclipse.structure.generator.util.more;


import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.reclipse.structure.generator.util.Constants;
import org.reclipse.structure.generator.util.IGenerator;
import org.reclipse.structure.generator.util.StorydrivenUtil;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.util.ModelHelper;
import org.storydriven.core.expressions.Expression;
import org.storydriven.core.expressions.TextualExpression;
import org.storydriven.core.expressions.common.ComparisonExpression;
import org.storydriven.core.expressions.common.LiteralExpression;
import org.storydriven.storydiagrams.activities.ActivitiesFactory;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.ActivityCallNode;
import org.storydriven.storydiagrams.activities.ActivityEdge;
import org.storydriven.storydiagrams.activities.ActivityNode;
import org.storydriven.storydiagrams.activities.EdgeGuard;
import org.storydriven.storydiagrams.activities.JunctionNode;
import org.storydriven.storydiagrams.calls.CallsFactory;
import org.storydriven.storydiagrams.calls.Invocation;
import org.storydriven.storydiagrams.calls.ParameterBinding;
import org.storydriven.storydiagrams.expressions.pathExpressions.ImplicitPathDescription;
import org.storydriven.storydiagrams.expressions.pathExpressions.ImplicitPathKind;
import org.storydriven.storydiagrams.expressions.pathExpressions.PathExpression;
import org.storydriven.storydiagrams.expressions.pathExpressions.PathExpressionsFactory;
import org.storydriven.storydiagrams.expressions.pathExpressions.PathSegment;
import org.storydriven.storydiagrams.expressions.pathExpressions.RepeatOperator;
import org.storydriven.storydiagrams.expressions.pathExpressions.RestrictionList;
import org.storydriven.storydiagrams.expressions.pathExpressions.TypeRestriction;
import org.storydriven.storydiagrams.expressions.pathbridge.PathExpressionBridge;
import org.storydriven.storydiagrams.expressions.pathbridge.PathbridgeFactory;
import org.storydriven.storydiagrams.patterns.AbstractLinkVariable;
import org.storydriven.storydiagrams.patterns.AbstractVariable;
import org.storydriven.storydiagrams.patterns.AttributeAssignment;
import org.storydriven.storydiagrams.patterns.BindingOperator;
import org.storydriven.storydiagrams.patterns.BindingSemantics;
import org.storydriven.storydiagrams.patterns.BindingState;
import org.storydriven.storydiagrams.patterns.CollectionVariable;
import org.storydriven.storydiagrams.patterns.Constraint;
import org.storydriven.storydiagrams.patterns.LinkVariable;
import org.storydriven.storydiagrams.patterns.ObjectVariable;
import org.storydriven.storydiagrams.patterns.Path;
import org.storydriven.storydiagrams.patterns.PatternsFactory;
import org.storydriven.storydiagrams.patterns.PrimitiveVariable;
import org.storydriven.storydiagrams.patterns.StoryPattern;
import org.storydriven.storydiagrams.patterns.expressions.AttributeValueExpression;
import org.storydriven.storydiagrams.patterns.expressions.CollectionSizeExpression;
import org.storydriven.storydiagrams.patterns.expressions.ObjectVariableExpression;
import org.storydriven.storydiagrams.patterns.expressions.PatternsExpressionsFactory;


public abstract class SDMUtil
{
	public static LinkVariable createLink(ObjectVariable source, ObjectVariable target, EReference instanceOf,
			String role, boolean create, BindingSemantics type, StoryPattern storyPattern)
	{
		LinkVariable link = PatternsFactory.eINSTANCE.createLinkVariable();
		link.setTargetEnd(instanceOf);

		if (instanceOf.getEOpposite() != null)
		{
			link.setSourceEnd(instanceOf.getEOpposite());
		}

		link = (LinkVariable) configureAbstractLinkVariable(source, target, role, create, type, storyPattern, link);

		if (role != null)
		{
			link.setQualifierExpression(createQualifierExpression(role));
		}
		return link;
	}


	public static Path createPath(PSPath psPath, ObjectVariable source, ObjectVariable target, StoryPattern storyPattern)
	{
		Path path = PatternsFactory.eINSTANCE.createPath();
		psPath.getTabooClasses();
		PathExpressionBridge bridge = PathbridgeFactory.eINSTANCE.createPathExpressionBridge();
		PathExpression expr = PathExpressionsFactory.eINSTANCE.createPathExpression();
		org.storydriven.storydiagrams.expressions.pathExpressions.Path pathAlternative = PathExpressionsFactory.eINSTANCE
				.createPath();
		PathSegment segment = PathExpressionsFactory.eINSTANCE.createPathSegment();
		ImplicitPathDescription desc = PathExpressionsFactory.eINSTANCE.createImplicitPathDescription();

		// <>-->*
		path.setPathExpression(bridge);
		bridge.setPathExpression(expr);
		expr.getPathAlternatives().add(pathAlternative);
		pathAlternative.getSegments().add(segment);
		segment.setRepeatOperator(RepeatOperator.ARBITRARY);
		segment.getAlternatives().add(desc);
		desc.setKind(ImplicitPathKind.CONTAINMENT_SOURCE);

		// <>-->*[!type1, !type2,...]
		RestrictionList restrictions = PathExpressionsFactory.eINSTANCE.createRestrictionList();
		for (EClass taboo : psPath.getTabooClasses())
		{
			TypeRestriction restriction = PathExpressionsFactory.eINSTANCE.createTypeRestriction();
			restriction.setTypeName(taboo.getName());
			restriction.setForbidden(true);
			restrictions.getRestrictions().add(restriction);
		}
		desc.setRestrictionList(restrictions);

		return (Path) configureAbstractLinkVariable(source, target, null, false, BindingSemantics.MANDATORY,
				storyPattern, path);
	}


	private static AbstractLinkVariable configureAbstractLinkVariable(ObjectVariable source, ObjectVariable target,
			String role, boolean create, BindingSemantics type, StoryPattern storyPattern, AbstractLinkVariable link)
	{
		link.setSource(source);
		link.setTarget(target);
		if (!"".equals(role))
		{
			link.setName(role);
		}

		if (create)
		{
			link.setBindingOperator(BindingOperator.CREATE);
		}

		link.setBindingSemantics(type);

		storyPattern.getLinkVariables().add(link);
		return link;
	}


	private static Expression createQualifierExpression(String qualifier)
	{
		LiteralExpression expression = org.storydriven.core.expressions.common.CommonExpressionsFactory.eINSTANCE
				.createLiteralExpression();
		expression.setValue(qualifier);
		return expression;
	}


	public static Constraint createConstraint(String constraintText, StoryPattern storyPattern)
	{
		return createConstraint(createTextualExpression(constraintText), storyPattern);
	}


	private static Constraint createConstraint(Expression constraintExpression, StoryPattern storyPattern)
	{
		Constraint cons = PatternsFactory.eINSTANCE.createConstraint();
		cons.setPattern(storyPattern);
		cons.setConstraintExpression(constraintExpression);
		return cons;
	}


	private static TextualExpression createTextualExpression(String constraintText)
	{
		return ExprUtil.textual(constraintText, "OCL", null);
	}


	public static ParameterBinding createParameterBinding(EParameter methodParam, ObjectVariable objectValue,
			Invocation invocation)
	{
		ParameterBinding binding = createParameterBinding(methodParam, objectValue);
		binding.setInvocation(invocation);
		return binding;
	}


	private static ParameterBinding createParameterBinding(EParameter methodParam, ObjectVariable objectValue)
	{
		ObjectVariableExpression oExpr = PatternsExpressionsFactory.eINSTANCE.createObjectVariableExpression();
		oExpr.setObject(objectValue);
		return createParameterBinding(methodParam, oExpr);
	}


	private static ParameterBinding createParameterBinding(EParameter methodParam, Expression valueExpression)
	{
		ParameterBinding paramBinding = CallsFactory.eINSTANCE.createParameterBinding();
		paramBinding.setValueExpression(valueExpression);
		paramBinding.setParameter(methodParam);
		return paramBinding;
	}


	public static ObjectVariable createAnnotationResultSetObject(StoryPattern pattern, boolean bound, boolean create)
	{
		ObjectVariable setObject = PatternsFactory.eINSTANCE.createObjectVariable();
		setObject.setClassifier(getAnnotationSetClass());
		setObject.setName(Constants.VAR_RESULTS);

		if (bound)
		{
			setObject.setBindingState(BindingState.BOUND);
		}
		else
		{
			setObject.setBindingState(BindingState.UNBOUND);
		}

		if (create)
		{
			setObject.setBindingOperator(BindingOperator.CREATE);
		}

		pattern.getVariables().add(setObject);

		return setObject;
	}


	public static EClass getAnnotationSetClass()
	{
		return AnnotationsPackage.eINSTANCE.getAnnotationSet();
	}


	public static ActivityEdge createTransitionNoneGuarded(ActivityNode sourceActivity, ActivityNode targetActivity,
			Activity activity)
	{
		return createTransition(sourceActivity, targetActivity, EdgeGuard.NONE, null, activity);
	}


	public static ActivityEdge createTransition(ActivityNode sourceActivity, ActivityNode targetActivity,
			EdgeGuard type, Activity activity)
	{
		return createTransition(sourceActivity, targetActivity, type, null, activity);
	}


	private static ActivityEdge createTransition(ActivityNode sourceActivity, ActivityNode targetActivity,
			EdgeGuard type, Expression expression, Activity activity)
	{
		ActivityEdge edge = ActivitiesFactory.eINSTANCE.createActivityEdge();
		edge.setSource(sourceActivity);
		edge.setTarget(targetActivity);
		edge.setGuardExpression(expression);

		if (type == null)
		{
			edge.setGuard(EdgeGuard.NONE);
		}
		else
		{
			edge.setGuard(type);
		}

		activity.getOwnedActivityEdges().add(edge);

		return edge;
	}


	public static ObjectVariable createAnnotationObject(PSAnnotation annotation, EClass objectType, boolean bound,
			boolean create, StoryPattern storyPattern, Map<PSNode, ObjectVariable> storyItems, Counter counter)
	{
		ObjectVariable object = createTypedObject(objectType, annotation);


		if (ModelHelper.isCreate(annotation))
		{
			object.setName(StorydrivenUtil.getValidName(annotation.getPatternSpecification(), Constants.VAR_ANNOTATION));
		}
		else if (bound)
		{
			object.setName(annotation.getName());
		}
		else
		{
			String annoName = annotation.getName();
			if (annoName == null || annoName.trim().length() == 0)
			{
				object.setName(StorydrivenUtil.getValidName(annotation.getPatternSpecification(), Constants.VAR_ANNOTATION));
			}
			else
			{
				object.setName(annoName);
			}
		}

		if (create)
		{
			object.setBindingOperator(BindingOperator.CREATE);
		}
		if (bound)
		{
			object.setBindingState(BindingState.BOUND);
		}

		// save the mapping
		if (storyItems != null)
		{
			storyItems.put(annotation, object);
		}

		storyPattern.getVariables().add(object);

		return object;
	}


	public static ObjectVariable createObject(PSObject psObject, boolean bound, boolean addNodeExpressions,
			StoryPattern storyPattern, Map<PSNode, ObjectVariable> storyItems, Counter counter)
	{
		EClass eClass = psObject.getInstanceOf();

		ObjectVariable object = createTypedObject(eClass, psObject);

		// set the objects name
		String name = psObject.getName();
		if (name == null || name.trim().equals(""))
		{
			if (bound)
			{
				ObjectVariable lastObject = storyItems.get(psObject);
				if (lastObject != null)
				{
					name = lastObject.getName();
				}
			}
			else
			{
				name = psObject.getInstanceOf().getName() + counter.incCounter();

				if (name.length() != 0)
				{
					StringBuffer newBuf = new StringBuffer(name);
					newBuf.setCharAt(0, Character.toLowerCase(newBuf.charAt(0)));

					name = newBuf.toString();
				}
			}
		}
		object.setName(name);

		if (bound)
		{
			object.setBindingState(BindingState.BOUND);
		}

		// save the mapping
		storyItems.put(psObject, object);

		// add node constraints that are not optional
		if (!bound && addNodeExpressions)
		{
			Iterator<PSNodeConstraint> iter = psObject.getNodeConstraints().iterator();
			while (iter.hasNext())
			{
				PSNodeConstraint psNodeConstraint = iter.next();

				// TODO generate code for other node expressions
				// FIXME: what are other expressions/constraints???

				if (psNodeConstraint instanceof PSBooleanConstraint)
				{
					if (!((PSBooleanConstraint) psNodeConstraint).isAdditional())
					{
						if (psNodeConstraint instanceof PSAttributeConstraint)
						{
							SDMUtil.createAttributeExpression((PSAttributeConstraint) psNodeConstraint, object);
						}
						else if (psNodeConstraint instanceof PSMetricConstraint)
						{
							SDMUtil.createMetricExpression((PSMetricConstraint) psNodeConstraint, object);
						}

						// do nothing for PSSetSizeExpression, constraints realizing the check for
						// this expression are generated at another point
					}
				}
			}
		}

		storyPattern.getVariables().add(object);

		return object;
	}


	private static ObjectVariable createTypedObject(EClass type, PSNode node)
	{
		ObjectVariable object = null;
		if (node.getModifier() == ModifierType.SET)
		{
			object = PatternsFactory.eINSTANCE.createCollectionVariable();
		}
		else
		{
			object = PatternsFactory.eINSTANCE.createObjectVariable();
		}
		object.setClassifier(type);

		if (node.getModifier() == ModifierType.ADDITIONAL)
		{
			object.setBindingSemantics(BindingSemantics.OPTIONAL);
		}
		else if (node.getModifier() == ModifierType.NEGATIVE)
		{
			object.setBindingSemantics(BindingSemantics.NEGATIVE);
		}

		return object;
	}


	public static ObjectVariable createObject(EClass type, String name, boolean bound, boolean create,
			StoryPattern parent)
	{
		ObjectVariable object = PatternsFactory.eINSTANCE.createObjectVariable();
		// if(isSet)
		// {
		// object = PatternsFactory.eINSTANCE.createObjectSetVariable();
		// }
		// else
		// {
		// object = PatternsFactory.eINSTANCE.createObjectVariable();
		// }

		object.setClassifier(type);
		if (create)
		{
			object.setBindingOperator(BindingOperator.CREATE);
		}
		else
		{
			object.setBindingOperator(BindingOperator.CHECK_ONLY);
		}

		return (ObjectVariable) configureAbstractVariable(name, bound, parent, object);
	}


	public static PrimitiveVariable createPrimitiveObject(EDataType type, String name, boolean create,
			StoryPattern parent)
	{
		PrimitiveVariable var = PatternsFactory.eINSTANCE.createPrimitiveVariable();
		var.setClassifier(type);
		return (PrimitiveVariable) configureAbstractVariable(name, false, parent, var);
	}


	private static AbstractVariable configureAbstractVariable(String name, boolean bound, StoryPattern parent,
			AbstractVariable object)
	{
		object.setName(name);

		if (bound)
		{
			object.setBindingState(BindingState.BOUND);
		}
		parent.getVariables().add(object);
		return object;
	}


	public static Constraint createAttributeExpression(PSAttributeConstraint constraint, ObjectVariable object)
	{
		Constraint cons = PatternsFactory.eINSTANCE.createConstraint();
		cons.setObjectVariable(object);

		AttributeValueExpression attrExpr = PatternsExpressionsFactory.eINSTANCE.createAttributeValueExpression();
		attrExpr.setObject(object);
		attrExpr.setAttribute(constraint.getAttribute());


		String valueExpr = constraint.getValueExpression();
		LiteralExpression litExpr = org.storydriven.core.expressions.common.CommonExpressionsFactory.eINSTANCE
				.createLiteralExpression();
		litExpr.setValue(valueExpr);

		OperatorType op = constraint.getOperator();
		ComparisonExpression compExpr = createComparingExpression(attrExpr, op, litExpr);
		cons.setConstraintExpression(compExpr);
		object.getConstraints().add(cons);

		return cons;
	}


	public static Constraint createMetricExpression(PSMetricConstraint metricConstraint, ObjectVariable object)
	{
		if ("SIZE".equals(metricConstraint.getMetricAcronym()) && object instanceof CollectionVariable)
		{
			return createSetConstraint(metricConstraint, (CollectionVariable) object);
		}
		// FIXME: How do we model Metric constraints with the new model???
		// FFactory<UMLMetricExpression> factory = umlObject.getProject()
		// .getFromFactories(UMLMetricExpression.class);
		// UMLMetricExpression umlMetricExpression = factory.create(false);
		// umlMetricExpression.setName(psMetricExpression.getName());
		// umlMetricExpression.setExpression(psMetricExpression.getExpression());
		//
		// umlObject.addToAttrs(umlMetricExpression);

		return null;
	}


	private static Constraint createSetConstraint(PSMetricConstraint nodeConstraint, CollectionVariable theSet)
	{
		Constraint cons = PatternsFactory.eINSTANCE.createConstraint();
		cons.setObjectVariable(theSet);

		CollectionSizeExpression setSizeExr = PatternsExpressionsFactory.eINSTANCE.createCollectionSizeExpression();
		setSizeExr.setSet(theSet);

		String valueExpr = nodeConstraint.getValueExpression();
		LiteralExpression litExpr = org.storydriven.core.expressions.common.CommonExpressionsFactory.eINSTANCE
				.createLiteralExpression();
		litExpr.setValue(valueExpr);

		ComparisonExpression compExpr = createComparingExpression(setSizeExr, nodeConstraint.getOperator(), litExpr);
		cons.setConstraintExpression(compExpr);
		theSet.getConstraints().add(cons);

		return cons;
	}


	public static ComparisonExpression createComparingExpression(Expression left, OperatorType op, Expression right)
	{
		if (op.equals(OperatorType.EQUAL))
		{
			return ExprUtil.equal(left, right);
		}
		else if (op.equals(OperatorType.UNEQUAL))
		{
			return ExprUtil.unequal(left, right);
		}
		else if (op.equals(OperatorType.GREATER))
		{
			return ExprUtil.greater(left, right);
		}
		else if (op.equals(OperatorType.GREATER_OR_EQUAL))
		{
			return ExprUtil.greaterOrEqual(left, right);
		}
		else if (op.equals(OperatorType.LESS))
		{
			return ExprUtil.less(left, right);
		}
		else if (op.equals(OperatorType.LESS_OR_EQUAL))
		{
			return ExprUtil.lessOrEqual(left, right);
		}
		else if (op.equals(OperatorType.REGULAR_EXPRESSION))
		{
			return ExprUtil.regularExpression(left, right);
		}
		return null;
	}


	public static LinkVariable createBoundObjectsLink(ObjectVariable annotationObject, ObjectVariable boundObject,
			PSNode boundObjectsNode, boolean create, StoryPattern storyPattern)
	{
		EReference boundObjectsRef = AnnotationsPackage.eINSTANCE.getASGAnnotation_BoundObjects();

		String name = boundObjectsNode.getName();

		// add the boundObjects link
		LinkVariable link = SDMUtil.createLink(annotationObject, boundObject, boundObjectsRef, name, create,
				BindingSemantics.MANDATORY, storyPattern);
		return link;
	}


	public static LinkVariable createAnnotationResultSetLink(ObjectVariable source, ObjectVariable target,
			boolean create, StoryPattern storyPattern)
	{
		EReference ref = AnnotationsPackage.eINSTANCE.getAnnotationSet_Annotations();
		return SDMUtil.createLink(source, target, ref, null, create, BindingSemantics.MANDATORY, storyPattern);
	}


	public static ObjectVariable createObject(PSNode node, boolean bound, boolean addNodeExpressions,
			StoryPattern storyPattern, Map<PSNode, ObjectVariable> storyItems, Counter counter, IGenerator generator)
	{
		ObjectVariable object = null;
		if (node instanceof PSAnnotation)
		{
			PSAnnotation anno = (PSAnnotation) node;

			EClass annoType = generator.getAnnotationClass(anno.getType());

			object = createAnnotationObject(anno, annoType, bound, addNodeExpressions, storyPattern, storyItems, counter);
		}
		else
		{
			object = createObject((PSObject) node, bound, addNodeExpressions, storyPattern, storyItems, counter);
		}
		return object;
	}


	public static void configureTrigger(ObjectVariable triggerObject, PSNode trigger, StoryPattern storyPattern)
	{
		if (!(triggerObject instanceof CollectionVariable))
		{
			// triggerObject.setCheckTypeCast(true);
			triggerObject.setBindingState(BindingState.BOUND);
			// triggerObject.setTypeCastSource("element");
		}
		else
		{
			// If the trigger object is a set, an additional trigger object which is not the set has to
			// be created and used as starting point for the pattern matching instead of the original
			// one. For anything else, the original trigger object is used.

			// create a new trigger object which is not a set
			ObjectVariable nonSetTriggerObject = SDMUtil.createObject(triggerObject.getClassifier(),
					triggerObject.getName(), false, true, triggerObject.getPattern());

			nonSetTriggerObject.setBindingSemantics(BindingSemantics.MANDATORY);
			// nonSetTriggerObject.setCheckTypeCast(true);
			nonSetTriggerObject.setBindingState(BindingState.BOUND);
			// nonSetTriggerObject.setTypeCastSource("element");
			if (nonSetTriggerObject.getName().equals(triggerObject.getName()))
			{
				nonSetTriggerObject.setName(nonSetTriggerObject.getName() + "NonSetTrigger");
			}

			for (PSConnection connection : trigger.getOutgoing())
			{
				if (connection instanceof PSLink)
				{
					PSLink psLink = (PSLink) connection;

					if (!ModelHelper.isCreate(psLink))
					{
						SDMUtil.createLink(nonSetTriggerObject, triggerObject, psLink.getInstanceOf(), psLink.getName(),
								false, BindingSemantics.MANDATORY, storyPattern);
					}
				}
			}

			for (PSConnection connection : trigger.getOutgoing())
			{
				if (connection instanceof PSLink)
				{
					PSLink psLink = (PSLink) connection;

					if (!ModelHelper.isCreate(psLink))
					{
						SDMUtil.createLink(triggerObject, nonSetTriggerObject, psLink.getInstanceOf(), psLink.getName(),
								false, BindingSemantics.MANDATORY, storyPattern);
					}
				}
			}

			// Add maybe constraint since the original trigger object is a set and must be allowed to
			// contain the non-set trigger object. Otherwise, patterns making use of the set's size
			// won't work correctly, since one object (the non-set trigger object) would always be
			// missing in the set.
			SDMUtil.createConstraint("maybe " + triggerObject.getName() + " == " + nonSetTriggerObject.getName(),
					storyPattern);
		}
	}


	public static ObjectVariable createSetResultSetObject(StoryPattern pattern, boolean bound, boolean create)
	{
		ObjectVariable object = PatternsFactory.eINSTANCE.createObjectVariable();
		object.setClassifier(AnnotationsPackage.Literals.SET_RESULT_SET);
		object.setName(Constants.VAR_SET_FRAGMENTS_RESULT);
		if (bound)
		{
			object.setBindingState(BindingState.BOUND);
		}
		if (create)
		{
			object.setBindingOperator(BindingOperator.CREATE);
		}
		pattern.getVariables().add(object);

		return object;
	}


	public static ActivityCallNode createActivityCallNode(Activity owner, Activity callActivity)
	{
		ActivityCallNode activity = ActivitiesFactory.eINSTANCE.createActivityCallNode();
		activity.setOwningActivity(owner);
		activity.setCallee(callActivity);
		return activity;
	}


	public static ObjectVariable createTemporaryAnnotationObject(StoryPattern storyPattern, boolean bound, boolean create)
	{

		ObjectVariable tempObject = PatternsFactory.eINSTANCE.createObjectVariable();
		tempObject.setName(Constants.VAR_ANNOTATION);
		tempObject.setClassifier(AnnotationsPackage.Literals.ASG_ANNOTATION);
		if (bound)
		{
			tempObject.setBindingState(BindingState.BOUND);
		}
		if (create)
		{
			tempObject.setBindingOperator(BindingOperator.CREATE);
		}

		storyPattern.getVariables().add(tempObject);
		return tempObject;
	}


	public static JunctionNode createNopActivity(Activity activity)
	{
		JunctionNode node = ActivitiesFactory.eINSTANCE.createJunctionNode();
		activity.getOwnedActivityNodes().add(node);
		return node;
	}


	public static LinkVariable createSetInstanceAnnotationResultSetLink(ObjectVariable annotationResultSet,
			ObjectVariable annotationObject, boolean create, StoryPattern storyPattern)
	{
		return SDMUtil.createLink(annotationResultSet, annotationObject,
				AnnotationsPackage.eINSTANCE.getAnnotationSet_Annotations(), null, create, BindingSemantics.MANDATORY,
				storyPattern);
	}


	public static ObjectVariable addNodeToStoryPattern(StoryPattern storyPattern, PSNode node,
			Map<PSNode, ObjectVariable> storyItems, Counter counter, IGenerator generator)
	{
		ObjectVariable object = null;

		if (node instanceof PSAnnotation)
		{
			PSAnnotation anno = (PSAnnotation) node;
			EClass annoType = generator.getAnnotationClass(anno.getType());
			object = SDMUtil.createAnnotationObject((PSAnnotation) node, annoType, false, false, storyPattern, storyItems,
					counter);
		}
		else if (node instanceof PSObject)
		{
			object = SDMUtil.createObject((PSObject) node, false, true, storyPattern, storyItems, counter);
		}

		if (ModelHelper.isAnnotated(node) && ModelHelper.isSearchForThisOptional(node)
				&& node.getModifier() != ModifierType.SET && object != null)
		{
			object.setBindingSemantics(BindingSemantics.OPTIONAL);
		}

		if (object != null && object instanceof CollectionVariable)
		{
			Iterator<PSNodeConstraint> exprIter = node.getNodeConstraints().iterator();
			while (exprIter.hasNext())
			{
				PSNodeConstraint psNodeConstraint = exprIter.next();
				if (psNodeConstraint instanceof PSMetricConstraint
						&& !((PSMetricConstraint) psNodeConstraint).isAdditional()
						&& "SIZE".equals(((PSMetricConstraint) psNodeConstraint).getMetricAcronym()))
				{
					Constraint constraint = SDMUtil.createMetricExpression((PSMetricConstraint) psNodeConstraint, object);
					storyPattern.getConstraints().add(constraint);
				}
			}
		}
		return object;
	}


	public static AbstractVariable findVariableByName(StoryPattern pattern, String name)
	{
		if (name == null || pattern == null)
		{
			return null;
		}
		for (AbstractVariable variable : pattern.getVariables())
		{
			if (name.equals(variable.getName()))
			{
				return variable;
			}
		}
		return null;
	}


	public static AttributeAssignment createAttributeAssignment(ObjectVariable owner, EAttribute attribute,
			Expression value)
	{
		AttributeAssignment assignment = PatternsFactory.eINSTANCE.createAttributeAssignment();
		assignment.setObjectVariable(owner);
		assignment.setAttribute(attribute);
		assignment.setValueExpression(value);
		return assignment;
	}
}
