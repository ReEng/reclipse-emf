package org.reclipse.structure.inference.ui.matching.util;


import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.inference.ui.matching.views.AbstractMatchingView;
import org.reclipse.structure.specification.PSNode;


public final class BoundingUtil
{
	private BoundingUtil()
	{
		// hide constructor
	}


	public static boolean isBound(PSNode node)
	{
		return !getBound(node).isEmpty();
	}


	public static List<EObject> getBound(PSNode node)
	{
		List<EObject> bounds = AbstractMatchingView.getCurrent().getBoundObjects().get(node.getName());
		if (bounds == null || bounds.isEmpty())
		{
			return Collections.emptyList();
		}

		return bounds;
	}


	public static EObject getFirstBound(PSNode node)
	{
		for (EObject element : getBound(node))
		{
			return element;
		}

		return null;
	}
}
