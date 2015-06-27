package org.reclipse.structure.specification.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.fujaba.commons.console.IReportListener;
import org.fujaba.commons.identifier.Identifier;
import org.reclipse.metamodel.ITriggerChooser;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;


public class TriggerManager
{
	private static final Comparator<Identifier> ID_COMPARATOR = new Comparator<Identifier>()
	{
		@Override
		public int compare(Identifier one, Identifier two)
		{
			return one.getId().compareTo(two.getId());
		}
	};


	private final IReportListener reporter;

	private final Collection<PSPatternSpecification> patterns;


	private final Map<EClass, ASTNode> astNodes;

	private final Map<PSPatternSpecification, PatternNode> patternNodes;


	public TriggerManager(IReportListener reporter, Collection<PSPatternSpecification> patterns)
	{
		this.reporter = reporter;
		this.patterns = patterns;

		astNodes = new HashMap<EClass, TriggerManager.ASTNode>();
		patternNodes = new HashMap<PSPatternSpecification, PatternNode>();

		initialize();
	}


	public TriggerManager(Collection<PSPatternSpecification> patterns)
	{
		this(null, patterns);
	}


	private void initialize()
	{
		// create PatternNode for each pattern
		for (PSPatternSpecification pattern : patterns)
		{
			PatternNode node = new PatternNode(pattern);

			patternNodes.put(pattern, node);
		}

		// add required patterns or axioms to them
		for (PSPatternSpecification pattern : patterns)
		{
			// cache it
			PatternNode patternNode = patternNodes.get(pattern);

			// add all triggering patterns
			for (PSPatternSpecification referencedPattern : SpecificationUtil.getReferencedPatterns(pattern))
			{
				// add only non-abstract
				if (!referencedPattern.isAbstract())
				{
					PatternNode pX = patternNodes.get(referencedPattern);
					patternNode.triggeredBy.add(pX);
					pX.triggering.add(patternNode);
				}
			}

			// add axioms when required
			if (patternNode.triggeredBy.isEmpty())
			{
				// get axiom
				Collection<EClass> types = new HashSet<EClass>();
				for (PSObject object : SpecificationUtil.getObjects(pattern))
				{
					types.add(object.getInstanceOf());
				}

				// get the type with the potentially fewest occurrences on the graph
				ITriggerChooser chooser = SpecificationUtil.getMetamodel(pattern).getTriggerChooser();
				// FIXME: use trigger chooser from extension registry
				chooser = new ContainmentWeightedTriggerChooser();
				EClass axiom = chooser.getTrigger(types);

				// check if axiom is already inserted
				ASTNode node = astNodes.get(axiom);

				if (node == null)
				{
					node = new ASTNode(axiom);

					astNodes.put(axiom, node);
				}
				node.triggering.add(patternNode);
				patternNode.triggeredBy.add(node);
			}
		}

		// calculate ranks
		calculateRanks();
	}


	private void calculateRanks()
	{
		for (PatternNode node : patternNodes.values())
		{
			if (!node.pattern.isAbstract())
			{
				// calculate rank
				node.rank = calculateRank(node);

				StringBuilder message = new StringBuilder();

				message.append("Pattern '");
				message.append(node.pattern.getName());
				message.append("' [rank ");
				message.append(node.rank);
				message.append("] will be triggered by ");
				int i = 1;
				for (DependencyNode req : node.triggeredBy)
				{
					if (req instanceof PatternNode)
					{
						message.append("pattern '");
						message.append(((PatternNode) req).pattern.getName());
						message.append("'");
					}
					else if (req instanceof ASTNode)
					{
						message.append("AST elements of type '");
						message.append(((ASTNode) req).type.getName());
						message.append("'");
					}

					if (i < node.triggeredBy.size())
					{
						message.append(", ");
					}
					i++;
				}
				message.append(".");

				if (reporter != null)
				{
					reporter.debug(message.toString());
				}
			}
		}
	}


	private int calculateRank(PatternNode node)
	{
		// find maximum path to an axiom
		int max = 0;
		for (DependencyNode req : node.triggeredBy)
		{
			// triggered by axiom
			if (req instanceof ASTNode)
			{
				return max;
			}

			// triggered by pattern
			if (req instanceof PatternNode)
			{
				if (canBeRanked(node, (PatternNode) req))
				{
					// calculate rank of triggering pattern
					int rank = calculateRank((PatternNode) req);

					if (rank > max)
					{
						max = rank;
					}
				}
			}
		}

		return max + 1;
	}


	private boolean canBeRanked(PatternNode node, PatternNode req)
	{
		// indirect circular dependency
		for (PSPatternSpecification sub : SpecificationUtil.getInstancePatterns(node.pattern))
		{
			if (req.triggeredBy.contains(patternNodes.get(sub)))
			{
				return false;
			}
		}

		return true;
	}


	/**
	 * Resolves the {@link PSNode node} of the {@link PSPatternSpecification pattern} from which the pattern detection
	 * should be started.
	 * 
	 * @param pattern The pattern.
	 * @return Returns the trigger node for the pattern.
	 */
	public PSNode getTrigger(PSPatternSpecification pattern)
	{
		// try to resolve the trigger annotation
		PSNode result = getTriggerAnnotation(pattern);
		if (result != null)
		{
			return result;
		}

		// try to resolve the trigger object
		result = getTriggerObject(pattern);
		if (result != null)
		{
			return result;
		}

		System.err.println(String.format("The pattern %1s cannot be triggered!", pattern.getName()));
		return null;
	}


	private PSObject getTriggerObject(PSPatternSpecification pattern)
	{
		// collect types of all normal object nodes
		Collection<EClass> types = new ArrayList<EClass>();
		for (PSObject object : SpecificationUtil.getObjects(pattern))
		{
			if (SpecificationUtil.isNormal(object))
			{
				types.add(object.getInstanceOf());
			}
		}

		// get the type with the potentially fewest occurrences on the graph
		ITriggerChooser chooser = SpecificationUtil.getMetamodel(pattern).getTriggerChooser();
		// FIXME: use trigger chooser from extension registry
		chooser = new ContainmentWeightedTriggerChooser();
		EClass triggerType = chooser.getTrigger(types);

		// collect all nodes with the found type
		List<PSObject> possibilities = new ArrayList<PSObject>(types.size());
		for (PSObject object : SpecificationUtil.getObjects(pattern))
		{
			if (SpecificationUtil.isNormal(object) && triggerType.equals(object.getInstanceOf()))
			{
				possibilities.add(object);
			}
		}

		// return early
		if (possibilities.isEmpty())
		{
			return null;
		}

		// sort by ID to return the same each time
		Collections.sort(possibilities, ID_COMPARATOR);

		// return first object
		return possibilities.get(0);
	}


	private PSAnnotation getTriggerAnnotation(PSPatternSpecification pattern)
	{
		// get all annotations
		Collection<PSAnnotation> annotations = SpecificationUtil.getAnnotations(pattern);

		// collect all normal annotation nodes
		Collection<PSAnnotation> possibilities = new ArrayList<PSAnnotation>(annotations.size());
		for (PSAnnotation annotation : annotations)
		{
			if (SpecificationUtil.isReferencing(annotation) && SpecificationUtil.isNormal(annotation))
			{
				possibilities.add(annotation);
			}
		}

		// search highest rank
		int maximum = -1;
		for (PSAnnotation annotation : possibilities)
		{
			for (PSPatternSpecification instance : SpecificationUtil.getInstancePatterns(annotation))
			{
				int currentRank = getRank(instance);
				if (currentRank > maximum)
				{
					maximum = currentRank;
				}
			}
		}

		// collect all nodes with the highest rank
		List<PSAnnotation> highest = new ArrayList<PSAnnotation>(possibilities.size());
		for (PSAnnotation annotation : possibilities)
		{
			for (PSPatternSpecification instance : SpecificationUtil.getInstancePatterns(annotation))
			{
				if (getRank(instance) == maximum && !highest.contains(annotation))
				{
					highest.add(annotation);
				}
			}
		}

		// return early
		if (highest.isEmpty())
		{
			return null;
		}

		// return single element
		if (highest.size() == 1)
		{
			for (PSAnnotation annotation : highest)
			{
				return annotation;
			}
		}

		// sort by ID to return the same each time
		Collections.sort(highest, ID_COMPARATOR);

		// return first annotation
		return highest.get(0);
	}


	/**
	 * Resolves the {@link PSPatternSpecification patterns} that should be triggered when a new annotation of the pattern
	 * has been found.
	 * 
	 * @param context The pattern that has been found.
	 * @return Returns the patterns that should be triggered.
	 */
	public Collection<PSPatternSpecification> getPatternThatShouldBeTriggeredOnFoundAnnotation(
			PSPatternSpecification context)
	{
		Collection<PSPatternSpecification> result = new HashSet<PSPatternSpecification>();

		PatternNode patternNode = patternNodes.get(context);
		for (PatternNode triggeringNode : patternNode.triggering)
		{
			PSNode otherNode = getTrigger(triggeringNode.pattern);
			if (otherNode instanceof PSAnnotation)
			{
				if (SpecificationUtil.getInstancePatterns((PSAnnotation) otherNode).contains(context))
				{
					result.add(triggeringNode.pattern);
				}
			}
		}

		return result;
	}


	public int getRank(PSPatternSpecification pattern)
	{
		return patternNodes.get(pattern).rank;
	}

	protected static abstract class DependencyNode
	{
		protected final Set<PatternNode> triggering;

		protected int rank;


		public DependencyNode()
		{
			triggering = new HashSet<PatternNode>();
			rank = -1;
		}
	}

	protected static class PatternNode extends DependencyNode
	{
		protected final PSPatternSpecification pattern;

		protected final Set<DependencyNode> triggeredBy;


		public PatternNode(PSPatternSpecification pattern)
		{
			super();

			this.triggeredBy = new HashSet<DependencyNode>();

			this.pattern = pattern;
		}
	}

	protected static class ASTNode extends DependencyNode
	{
		private final EClass type;


		public ASTNode(EClass type)
		{
			super();

			this.type = type;
		}
	}
}
