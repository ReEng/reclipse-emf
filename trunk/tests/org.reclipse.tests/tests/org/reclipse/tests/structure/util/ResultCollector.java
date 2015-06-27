package org.reclipse.tests.structure.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.fujaba.commons.console.ProcessConsoleState;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.notification.InferenceProgressListener;
import org.reclipse.structure.specification.PSPatternSpecification;


public class ResultCollector implements InferenceProgressListener
{

	private Map<PSPatternSpecification, Collection<ASGAnnotation>> annotations;

	private boolean done;


	public ResultCollector()
	{
		annotations = new HashMap<PSPatternSpecification, Collection<ASGAnnotation>>();
		done = false;
	}


	@Override
	public void newValues(int current, int maximum)
	{
		System.out.println("progressed " + current + " of " + maximum);
	}


	@Override
	public void newAnnotation(ASGAnnotation annotation)
	{
		Collection<ASGAnnotation> list = annotations.get(annotation.getPattern());

		if (list == null)
		{
			list = new ArrayList<ASGAnnotation>();
			annotations.put(annotation.getPattern(), list);
		}

		list.add(annotation);
	}


	@Override
	public void newState(ProcessConsoleState state)
	{
		switch (state)
		{
			case FINISHED:
			case ABORTED:
				done = true;
				break;
			default:
				break;
		}
	}


	public Map<PSPatternSpecification, Collection<ASGAnnotation>> getCurrentResults()
	{
		return annotations;
	}


	public synchronized boolean isDone()
	{
		return done;
	}


	@Override
	public void init()
	{
		annotations.clear();
	}
}
