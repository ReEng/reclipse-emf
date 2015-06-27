package org.reclipse.tracer.ui.views;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.reclipse.tracer.Tracer;
import org.reclipse.tracer.ui.TracerUIPlugin;
import org.reclipse.tracer.ui.actions.ClearTracerViewAction;
import org.reclipse.tracer.ui.actions.ResumeTracerAction;
import org.reclipse.tracer.ui.actions.SuspendTracerAction;
import org.reclipse.tracer.ui.actions.TerminateTracerAction;
import org.reclipse.tracer.ui.models.EventsModel;
import org.reclipse.tracer.ui.models.VMStateModel;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4060 $
 */
public class TracerView extends ViewPart implements PropertyChangeListener
{
   private TableViewer viewer;

   private ResumeTracerAction resumeAction;
   
   private SuspendTracerAction suspendAction;
   
   private TerminateTracerAction terminateAction;
   
   
   public TracerView()
   {
      VMStateModel vmStateModel = (VMStateModel) TracerUIPlugin.getDefault()
            .getVMStateModel();
      vmStateModel.addPropertyChangeListener(this);
   }
   
   
   /**
    * This is a callback that will allow us to create the viewer and initialize it.
    * 
    * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createPartControl(Composite parent)
   {
      this.viewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
      this.viewer.setContentProvider(new TracerViewContentProvider());
      this.viewer.setLabelProvider(new TracerViewLabelProvider());
      this.viewer.setInput(this.viewer);
      contributeToActionBars();
   }


   /**
    * Passing the focus request to the viewer's control.
    * 
    * @see org.eclipse.ui.IWorkbenchPart#setFocus()
    */
   @Override
   public void setFocus()
   {
      this.viewer.getControl().setFocus();
   }


   public void clear()
   {
      EventsModel eventsModel = (EventsModel) TracerUIPlugin.getDefault()
            .getEventsModel();
      eventsModel.removeAllFromEvents();
   }


   private void contributeToActionBars()
   {
      IActionBars actionBars = getViewSite().getActionBars();
      IToolBarManager toolBarManager = actionBars.getToolBarManager();

      this.resumeAction = new ResumeTracerAction();
      toolBarManager.add(this.resumeAction);
      
      this.suspendAction = new SuspendTracerAction();
      toolBarManager.add(this.suspendAction);
      
      this.terminateAction = new TerminateTracerAction();
      toolBarManager.add(this.terminateAction);

      toolBarManager.add(new ClearTracerViewAction(this));
   }


   /**
    * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
    */
   public void propertyChange(PropertyChangeEvent event)
   {
      if (event.getPropertyName().equals(VMStateModel.PROPERTY_VM_STATE))
      {
         Integer vmState = (Integer) event.getNewValue();
         switch (vmState.intValue())
         {
         case Tracer.VM_NONE:
         case Tracer.VM_INITIALIZED:
         case Tracer.VM_STOPPED:
            this.suspendAction.setEnabled(false);
            this.resumeAction.setEnabled(false);
            this.terminateAction.setEnabled(false);
            break;
         case Tracer.VM_RUNNING:
            this.suspendAction.setEnabled(true);
            this.resumeAction.setEnabled(false);
            this.terminateAction.setEnabled(true);
            break;
         case Tracer.VM_SUSPENDED:
            this.suspendAction.setEnabled(false);
            this.resumeAction.setEnabled(true);
            this.terminateAction.setEnabled(true);
            break;
         default:
            this.suspendAction.setEnabled(false);
            this.resumeAction.setEnabled(false);
            this.terminateAction.setEnabled(false);
            break;
         }
      }
   }

}
