package org.reclipse.tracer.ui.views;


import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.reclipse.tracer.ui.actions.CollapseExecutionViewAction;
import org.reclipse.tracer.ui.actions.ExpandExecutionViewAction;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4060 $ $Date: 2009-05-18 17:38:33 +0200 (Mo, 18 Mai 2009) $
 */
public class ExecutionMonitorView extends ViewPart
{
   private TreeViewer viewer;


   /**
    * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createPartControl(Composite parent)
   {
      this.viewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
      this.viewer.setContentProvider(new ExecutionMonitorViewContentProvider());
      this.viewer.setLabelProvider(new ExecutionMonitorViewLabelProvider());
      this.viewer.setInput(this.viewer);
      contributeToActionBars();
   }


   /**
    * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
    */
   @Override
   public void setFocus()
   {
      this.viewer.getControl().setFocus();
   }


   private void contributeToActionBars()
   {
      IActionBars bars = getViewSite().getActionBars();
      IToolBarManager manager = bars.getToolBarManager();
      manager.add(new ExpandExecutionViewAction(this.viewer));
      manager.add(new CollapseExecutionViewAction(this.viewer));
   }

}
