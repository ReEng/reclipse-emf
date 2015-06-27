package org.reclipse.tracer.symbolicexecution.jpf.listener;


import org.reclipse.tracer.symbolicexecution.ui.util.TracerUIModelCreator;
import org.reclipse.tracer.symbolicexecution.util.TraceDefinitionFilter;
import org.reclipse.tracer.ui.models.ExecutionMonitorModel;
import org.reclipse.tracer.ui.models.MonitoredClass;
import org.reclipse.tracer.ui.models.MonitoredMethod;

import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.JVM;
import gov.nasa.jpf.jvm.MethodInfo;


/**
 * Populates org.reclipse.tracer.ui.views.ExecutionMonitorView
 * 
 * @author avolk
 * @author Last editor: $Author: avolk $
 * @version $Revision: 174 $ $Date: 2010-10-05 21:50:14 +0200 (Di, 05 Okt 2010) $
 * 
 */
public class ExecutionMonitorListener extends ListenerAdapter
{
   private ExecutionMonitorModel executionModel = null;

   private TraceDefinitionFilter traceDefFilter = null;

   private TracerUIModelCreator modelCreator = new TracerUIModelCreator();


   public ExecutionMonitorListener(ExecutionMonitorModel executionModel,
         TraceDefinitionFilter traceDefFilter)
   {
      this.executionModel = executionModel;
      this.executionModel.removeAllFromClasses();

      this.traceDefFilter = traceDefFilter;
   }


   @Override
   public void classLoaded(JVM vm)
   {
      ClassInfo ci = vm.getLastClassInfo();

      if (!this.traceDefFilter.accept(ci))
         return;

      // Record class
      MonitoredClass monitoredClass = this.modelCreator
            .createMonitoredClass(ci);
      this.executionModel.addToClasses(monitoredClass);

      // Record methods
      for (MethodInfo method : ci.getDeclaredMethodInfos())
         if (!method.isAbstract() && this.traceDefFilter.accept(method))
            monitoredClass.addToMethods(this.modelCreator
                  .createMonitoredMethod(method));

   }


   @Override
   public void methodEntered(JVM vm)
   {
      MethodInfo mi = vm.getLastMethodInfo();
      ClassInfo ci = mi.getClassInfo();

      if (!this.traceDefFilter.accept(mi))
         return;

      MonitoredClass containingClass = this.executionModel.getFromClasses(ci
            .getName());

      if (containingClass != null)
      {
         MonitoredMethod method = containingClass
               .getFromMethods(TracerUIModelCreator.createSignature(mi));
         method.setExecutions(method.getExecutions() + 1);
      }
   }
}
