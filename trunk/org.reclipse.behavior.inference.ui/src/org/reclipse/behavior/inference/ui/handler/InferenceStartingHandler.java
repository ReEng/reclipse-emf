package org.reclipse.behavior.inference.ui.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.behavior.inference.ui.wizard.BehaviorRecognitionWizard;


public class InferenceStartingHandler extends AbstractHandler
{

   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      BehaviorRecognitionWizard wizard = new BehaviorRecognitionWizard();
      WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
      dialog.open();
      return null;
   }
}
