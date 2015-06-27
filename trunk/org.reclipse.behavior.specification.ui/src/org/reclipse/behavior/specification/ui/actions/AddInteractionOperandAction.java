package org.reclipse.behavior.specification.ui.actions;


import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IEditorPart;

import de.uni_paderborn.basicSequenceDiagram.AlternativeFragment;


/**
 * @author Dietrich Travkin
 * @author Last Editor: $Author: lowende $
 * @version $Revision: 361 $ $Date: 2006-10-06 17:38:18 +0200 (Fr, 06 Okt 2006) $
 */
public class AddInteractionOperandAction extends SelectionAction 
{

   private Request addRequest;

   public final static String REQUEST_NAME = "Add Operand";

   public final static String ACTION_ID = "AddInteractionOperandActionID";

   
   public AddInteractionOperandAction (IEditorPart editor)
   {
      super (editor);
      addRequest = new Request (REQUEST_NAME);
      setText (REQUEST_NAME);
      setId (ACTION_ID);
   }


   protected boolean enabledForElement(Object modelElement)
   {
      if (modelElement instanceof AlternativeFragment)
      {
         return true;
      }
      return false;
   }

   /**
    * @return   No description provided
    * @see      org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
    */
   @Override
   protected final boolean calculateEnabled()
   {
      if (getSelectedObjects().isEmpty())
      {
         return false;
      }
      List<?> parts = getSelectedObjects();
      for (int i = 0; i < parts.size(); i++)
      {
         Object o = parts.get (i);
         if (! (o instanceof EditPart))
         {
            return false;
         }
         EditPart part = (EditPart) o;
         if (!this.enabledForElement (part.getModel()))
         {
            return false;
         }
      }
      return true;
   }


   /**
    * Get the command attribute of the AbstractAddToSelectionAction object
    *
    * @param aRequest  No description provided
    * @return          The command value
    */
   @SuppressWarnings("unchecked")
   private Command getCommand (Request aRequest)
   {
      CompoundCommand cc = new CompoundCommand();
      cc.setDebugLabel ("Add Action"); //$NON-NLS-1$
      List<EditPart> editparts = getSelectedObjects();
      for (EditPart part : editparts)
      {
         cc.add (part.getCommand (aRequest));
      }

      return cc.unwrap();
   }


   /**
    * Get the command attribute of the AbstractAddToSelectionAction object
    *
    * @return   The command value
    */
   protected final Command getCommand()
   {
      return this.getCommand (this.addRequest);
   }


   /**
    * @see   org.eclipse.jface.action.IAction#run()
    */
   @Override
   public void run()
   {
      execute (this.getCommand());
   }

}
