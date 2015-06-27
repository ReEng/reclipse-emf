package org.reclipse.tracer.symbolicexecution.ui.util;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.reclipse.tracer.ui.TracerUIPlugin;
import org.reclipse.tracer.ui.models.EventsModel;
import org.reclipse.tracer.ui.models.VMEvent;


/**
 * Handler for JPFs logging mechanism. Publishes jpfs log messages
 * to the TracerView.
 * @author avolk
 * @author Last editor: $Author: avolk $
 * @version $Revision: 174 $ $Date: 2010-10-05 21:50:14 +0200 (Di, 05 Okt 2010) $
 *
 */
public class EventsModelLogHandler extends Handler 
{
	EventsModel events = null;
	
	public EventsModelLogHandler()
	{
		this.events = (EventsModel)TracerUIPlugin.getDefault().getEventsModel();
		
	}

	@Override
	public void close() throws SecurityException 
	{
	}

	@Override
	public void flush() 
	{		
	}

	@Override
	public void publish(LogRecord record) 
	{
		VMEvent event = new VMEvent();
		event.setType(VMEvent.EXCEPTION_THROWN);
		event.setMessage(record.getMessage());
		//record.getMessage()
		this.events.addToEvents(event);
	}

}
