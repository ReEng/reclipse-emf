package org.reclipse.tracer.symbolicexecution.jpf.listener;

import org.eclipse.core.runtime.IProgressMonitor;

import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.search.SearchListener;

public class ProgressMonitorListener implements SearchListener 
{
	IProgressMonitor monitor = null;
	
	public ProgressMonitorListener(IProgressMonitor monitor)
	{
		this.monitor = monitor;
	}

	public void propertyViolated(Search search) 
	{
	}
	
	public void searchConstraintHit(Search search) 
	{
	}
	
	public void searchFinished(Search search) 
	{
	}
	
	public void searchStarted(Search search) 
	{
		if(this.monitor.isCanceled())
			search.terminate();	
	}

	public void stateAdvanced(Search search) 
	{
		monitor.subTask("State " + search.getStateId());
		
		if(this.monitor.isCanceled())
			search.terminate();
	}

	public void stateBacktracked(Search search) 
	{
		if(this.monitor.isCanceled())
			search.terminate();
	}

	public void stateProcessed(Search search) 
	{
		if(this.monitor.isCanceled())
			search.terminate();
	}

	public void statePurged(Search search) 
	{
	}

	public void stateRestored(Search search) 
	{
	}

	public void stateStored(Search search) 
	{
		
	}

}
