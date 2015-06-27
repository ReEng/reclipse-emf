package org.reclipse.tracer.ui.launching;


import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3756 $ $Date: 2007-09-05 19:00:00 +0200 (Mi, 05 Sep 2007) $
 */
public class AttachingModeLaunchConfigurationTabGroup extends
      AbstractLaunchConfigurationTabGroup
{

   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
    *      java.lang.String)
    */
   public void createTabs(final ILaunchConfigurationDialog dialog,
         final String mode)
   {
      final ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
            new AttachingModeMainTab(), new OptionsTab(),
            new ListenersConfigurationTab(), new CommonTab() };
      setTabs(tabs);
   }

}
