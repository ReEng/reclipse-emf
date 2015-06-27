package org.reclipse.tracer.ui.launching;


import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaJRETab;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3319 $ $Date: 2007-01-24 10:35:22 +0100 (Mi, 24 Jan 2007) $
 */
public class LaunchConfigurationTabGroup extends
      AbstractLaunchConfigurationTabGroup
{

   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
    *      java.lang.String)
    */
   public void createTabs(ILaunchConfigurationDialog dialog, String mode)
   {
      ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
            new MainTab(), new ClassPathTab(), new JavaJRETab(),
            new OptionsTab(), new ListenersConfigurationTab(), new CommonTab() };
      setTabs(tabs);
   }

}
