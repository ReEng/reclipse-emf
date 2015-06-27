package org.reclipse.tracer.symbolicexecution.jpf.listener;

import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.ExceptionInfo;
import gov.nasa.jpf.jvm.JVM;
import gov.nasa.jpf.jvm.MethodInfo;
import gov.nasa.jpf.search.Search;

import java.util.Calendar;

import org.reclipse.tracer.symbolicexecution.ui.util.TracerUIModelCreator;
import org.reclipse.tracer.symbolicexecution.util.TraceDefinitionFilter;
import org.reclipse.tracer.ui.models.EventsModel;
import org.reclipse.tracer.ui.models.MonitoredClass;
import org.reclipse.tracer.ui.models.VMEvent;

/**
 * Populates the org.reclipse.tracer.ui.views.TracerView
 * @author avolk 
 * @author Last editor: $Author: avolk $
 * @version $Revision: 174 $ $Date: 2010-10-05 21:50:14 +0200 (Di, 05 Okt 2010) $
 *
 */
public class TracerViewListener extends ListenerAdapter 
{
	private EventsModel eventsModel = null;
	private TraceDefinitionFilter traceDefFilter = null;
	private int tracePathCounter = 1;
	
	private TracerUIModelCreator modelCreator = new TracerUIModelCreator();
	
	/**
	 * Constructor
	 * @param eventsModel
	 * @param traceDefFilter
	 */
	public TracerViewListener(EventsModel eventsModel, TraceDefinitionFilter traceDefFilter)
	{
		this.eventsModel = eventsModel;
		this.eventsModel.removeAllFromEvents();
		
		VMEvent event = new VMEvent();
		event.setMessage("[" + Calendar.getInstance().getTime() +  "] JPF started.");
	    event.setType(VMEvent.VM_START);	
	    
	    this.eventsModel.addToEvents(event);
	    
	    this.traceDefFilter = traceDefFilter;
	}
	
	@Override
	public void classLoaded(JVM vm)
	{
		if(!this.traceDefFilter.accept(vm.getLastClassInfo()))
			return;
				
		ClassInfo classInfo = vm.getLastClassInfo();
		//Record class
		
		MonitoredClass monitoredClass = this.modelCreator.createMonitoredClass(classInfo);
		this.eventsModel.addToEvents(monitoredClass);	
		
		//Record monitored methods
		for(MethodInfo method: classInfo.getDeclaredMethodInfos())
			if(!method.isAbstract() && this.traceDefFilter.accept(method))
				this.eventsModel.addToEvents(this.modelCreator.createMonitoredMethod(method));
	}
	
	@Override
	public void exceptionThrown(JVM vm)
	{	
		ExceptionInfo ei = vm.getLastThreadInfo().getPendingException(); 
		VMEvent event = new VMEvent();
		event.setMessage("[" + Calendar.getInstance().getTime() +  "] " + ei.getExceptionClassname() + " thrown: " + ei.getCauseDetails());
		event.setType(VMEvent.EXCEPTION_THROWN);
		this.eventsModel.addToEvents(event);		
	}
	

	@Override
	public void threadTerminated(JVM vm)
	{
		VMEvent event = new VMEvent();
		event.setMessage("[" + Calendar.getInstance().getTime() +  "] TracePath #" + this.tracePathCounter++ +  " created.");
		event.setType(VMEvent.THREAD_DEATH);
		this.eventsModel.addToEvents(event);		
	}
	
	@Override
	public void searchFinished(Search search)
	{
		VMEvent event = new VMEvent();
		event.setMessage("[" + Calendar.getInstance().getTime() +  "] JPF finished.");
		event.setType(VMEvent.VM_DEATH);
		this.eventsModel.addToEvents(event);
	}
	

}
