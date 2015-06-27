package org.reclipse.tracer.ui;


import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3330 $ $Date: 2007-01-26 10:56:07 +0100 (Fr, 26 Jan 2007) $
 */
public class TracerPerspectiveFactory implements IPerspectiveFactory
{
   private static final String TRACER_VIEW_ID = "org.reclipse.tracer.ui.views.TracerView";

   private static final String EXECUTION_MONITOR_VIEW_ID = "org.reclipse.tracer.ui.views.ExecutionMonitorView";

   private static final String TRACE_DEFINITION_VIEW_ID = "org.reclipse.tracer.ui.views.TraceDefinitionView";

   private static final String RUN_TRACER_ACTION_SET_ID = "org.reclipse.tracer.ui.actionset";


   /**
    * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
    */
   public void createInitialLayout(IPageLayout layout)
   {
      String editorArea = layout.getEditorArea();
      
      IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.4f,
            editorArea);
      left.addView(EXECUTION_MONITOR_VIEW_ID);
      left.addView(IPageLayout.ID_RES_NAV);

      IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM,
            0.66f, editorArea);
      bottom.addView(TRACER_VIEW_ID);
      bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
      
      layout.addActionSet(RUN_TRACER_ACTION_SET_ID);
      layout.addShowViewShortcut(EXECUTION_MONITOR_VIEW_ID);
      layout.addShowViewShortcut(TRACER_VIEW_ID);
      layout.addShowViewShortcut(TRACE_DEFINITION_VIEW_ID);
   }

}
