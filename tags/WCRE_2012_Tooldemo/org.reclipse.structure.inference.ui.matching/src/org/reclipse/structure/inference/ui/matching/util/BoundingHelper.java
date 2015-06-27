package org.reclipse.structure.inference.ui.matching.util;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.reclipse.metamodel.AbstractElementLabeler;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.views.AbstractMatchingView;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;


public class BoundingHelper
{

	private static AbstractElementLabeler labeler;


	public static Image getBoundImage(PSNode node)
	{
		return getImage(BoundingUtil.getFirstBound(node));
	}


	public static String getBoundName(PSNode node)
	{
		return getName(BoundingUtil.getFirstBound(node));
	}


	public static String getBoundType(PSNode node)
	{
		return BoundingUtil.getFirstBound(node).eClass().getName();
	}


	public static String getName(EObject element)
	{
		if (getLabeler() != null)
		{
			return getLabeler().getText(element);
		}

		return String.valueOf(element);
	}


	private static Image getImage(EObject bound)
	{
		if (getLabeler() != null)
		{
			return getLabeler().getImage(bound);
		}

		return null;
	}


	private static AbstractElementLabeler getLabeler()
	{
		ASGAnnotation anno = AbstractMatchingView.getCurrent();
		if (anno != null)
		{
			PSPatternSpecification pattern = anno.getPattern();
			if (pattern != null)
			{
				MetaModel mm = ModelHelper.getMetaModel(pattern);
				labeler = mm.getLabeler();
			}
		}

		return labeler;
	}
}
