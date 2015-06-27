package org.reclipse.tests;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.reclipse.metamodel.AbstractElementLabeler;


public class TestLabeler extends AbstractElementLabeler
{

	@Override
	public String getText(EObject element)
	{
		if (element instanceof Named)
		{
			return ((Named) element).getName();
		}

		return String.valueOf(element);
	}


	@Override
	public String getFullText(EObject element)
	{
		return getText(element);
	}


	@Override
	public Image getImage(EObject element)
	{
		return getImage(Element.UNKNOWN);
	}
}
