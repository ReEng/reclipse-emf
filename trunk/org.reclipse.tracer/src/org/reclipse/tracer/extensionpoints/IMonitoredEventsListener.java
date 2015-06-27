package org.reclipse.tracer.extensionpoints;


import java.util.List;
import java.util.Map;

import org.reclipse.tracer.Tracer;

import com.sun.jdi.Location;
import com.sun.jdi.Method;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.ThreadReference;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2356 $ $Date: 2006-06-01 14:04:30 +0200 (Do, 01 Jun 2006) $
 */
public interface IMonitoredEventsListener
{

   public boolean initialize(Tracer tracer, Map<String, String> properties);


   public void vmStateChanged(int newVMState);


   public void vmStart(ThreadReference thread);


   public void classLoaded(ReferenceType type, List<ReferenceType> superTypes);


   public void methodEventRequestCreated(Method method);


   public void methodEntry(ThreadReference thread);


   public void methodExit(ThreadReference thread);


   public void exceptionThrown(ThreadReference thread, Location location,
         ObjectReference object);


   public void threadDeath(ThreadReference thread);


   public void vmDisconnect();


   public void vmDeath();


   public void tracerException(Exception exception);

}
