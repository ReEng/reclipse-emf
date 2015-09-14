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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.reclipse.metamodel.ITriggerChooser;


public class ContainmentWeightedTriggerChooser implements ITriggerChooser
{

	private static final int WEIGHT_RECURSION = 2;


	private final Map<EClass, Integer> weights;

	private final Map<EClass, Set<EClass>> subTypes;


	public ContainmentWeightedTriggerChooser()
	{
		weights = new HashMap<EClass, Integer>();
		subTypes = new HashMap<EClass, Set<EClass>>();
	}


	@Override
	public EClass getTrigger(Collection<EClass> possibilities)
	{
		// collect sub type references
		collectSubTypes(possibilities);

		// calculate the weights
		calculateWeights(possibilities);

		// get maximum weight
		int max = getMaximum(possibilities);

		// get all types with that weight
		List<EClass> results = getTypes(possibilities, max);

		// when empty, return null
		if (results.isEmpty())
		{
			return null;
		}

		// when only one element exist with that weight, return it
		if (results.size() == 1)
		{
			return results.get(0);
		}

		// on multiple types, sort by name and take first
		Collections.sort(results, new Comparator<EClass>()
		{
			@Override
			public int compare(EClass first, EClass second)
			{
				if (first != null && second != null && first.getName() != null && second.getName() != null )
				{
					return first.getName().compareTo(second.getName());
				}
				else
				{
					return 0;
				}
			}
		});

		return results.get(0);
	}


	private void collectSubTypes(Collection<EClass> types)
	{
		for (EClass type : types)
		{
			// add for all super types
			for (EClass superType : type.getESuperTypes())
			{
				addSuperType(superType, type);
			}

			// add for all cross referenced types
			for (EObject object : type.eCrossReferences())
			{
				if (object instanceof EClass)
				{
					EClass crossType = (EClass) object;

					for (EClass superType : crossType.getESuperTypes())
					{
						addSuperType(superType, crossType);
					}
				}
			}
		}
	}


	private void addSuperType(EClass parent, EClass child)
	{
		Set<EClass> result = subTypes.get(parent);
		if (result == null)
		{
			result = new HashSet<EClass>();
			subTypes.put(parent, result);
		}

		result.add(child);
	}


	private void calculateWeights(Collection<EClass> types)
	{
		// calculate weights
		for (EClass type : types)
		{
			// prepare weight
			int weight = 1;

			// check all containments
			for (EReference containment : type.getEAllContainments())
			{
				EClass containmentType = containment.getEReferenceType();

				// add weight for all sub types (when existing)
				Set<EClass> children = subTypes.get(containmentType);
				if (children != null)
				{
					for (EClass subType : children)
					{
						weight += getWeight(subType);
					}
				}
				else
				{
					Set<EClass> visited = new HashSet<EClass>();
					visited.add(type);
					if (isRecursive(visited, containmentType))
					{
						// add weight representing a recursion
						weight += WEIGHT_RECURSION;
					}
					else
					{
						// add weight of the containment type
						weight += getWeight(containmentType);
					}
				}
			}

			weights.put(type, weight);
		}
	}


	private int getWeight(EClass type)
	{
		int weight = 1;
		for (EReference reference : type.getEAllContainments())
		{
			EClass referenceType = reference.getEReferenceType();

			Set<EClass> visited = new HashSet<EClass>();
			visited.add(type);
			if (isRecursive(visited, referenceType))
			{
				weight += 5;
				// System.out.println("found recursion!");
			}
			else
			{
				weight += getWeight(referenceType);
			}
		}

		return weight;
	}


	private boolean isRecursive(Set<EClass> visited, EClass type)
	{
		if (visited.contains(type))
		{
			return true;
		}

		for (EReference reference : type.getEAllContainments())
		{
			EClass referenceType = reference.getEReferenceType();
			visited.add(referenceType);
			if (isRecursive(visited, referenceType))
			{
				return true;
			}
			visited.remove(referenceType);
		}

		return false;
	}


	private int getMaximum(Collection<EClass> types)
	{
		int max = 0;
		for (EClass type : types)
		{
			int current = weights.get(type);
			if (current > max)
			{
				max = current;
			}
		}

		return max;
	}


	private List<EClass> getTypes(Collection<EClass> types, int weight)
	{
		List<EClass> results = new ArrayList<EClass>();
		for (EClass type : types)
		{
			if (weights.get(type).equals(weight))
			{
				results.add(type);
			}
		}
		return results;
	}
}
