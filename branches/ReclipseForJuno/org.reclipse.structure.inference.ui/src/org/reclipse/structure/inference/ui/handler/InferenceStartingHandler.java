package org.reclipse.structure.inference.ui.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.inference.ui.wizards.StartInferenceWizard;


/**
 * This class handles the start the structural patterns detection via a wizard.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public class InferenceStartingHandler extends AbstractHandler
{

   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      StartInferenceWizard wizard = new StartInferenceWizard();
      WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event),
            wizard);
      dialog.open();
      return null;
   }
}
