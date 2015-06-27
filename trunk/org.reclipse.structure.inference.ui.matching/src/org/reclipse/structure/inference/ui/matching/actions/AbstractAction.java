package org.reclipse.structure.inference.ui.matching.actions;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;


/**
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author fklein
 */
public abstract class AbstractAction implements IActionDelegate
{
   /**
    * The current selection
    */
   protected ISelection selection;

   /**
    * The current action
    */
   protected IAction action;


   /**
    * Get the currentSelection attribute of the AbstractASGDiagramEditorAction object
    * 
    * 
    * @return The currentSelection value
    */
   protected ISelection getSelection()
   {
      return selection;
   }


   /**
    * @return the action
    */
   public IAction getAction()
   {
      return action;
   }


   /**
    * Get the selectedEditParts attribute of the AbstractASGDiagramEditorAction object
    * 
    * @return The selectedEditParts value
    */
   protected List<EditPart> getSelectedEditParts()
   {
      LinkedList<EditPart> result = new LinkedList<EditPart>();

      if (!getSelection().isEmpty()
            && getSelection() instanceof StructuredSelection)
      {
         StructuredSelection sel = (StructuredSelection) getSelection();
         Iterator<?> selected = sel.iterator();
         while (selected.hasNext())
         {
            Object obj = selected.next();
            if (obj instanceof EditPart)
            {
               result.add((EditPart) obj);
            }
         }
      }

      return result;
   }


   /**
    * No comment provided by developer, please add a comment to improve documentation.
    * 
    * @param action No description provided
    * @param selection No description provided
    */
   public void selectionChanged(IAction action, ISelection selection)
   {
      this.action = action;
      this.selection = selection;
      handleSelectionChanged();
   }


   /**
    * Called when the selection is changed.
    */
   protected void handleSelectionChanged()
   {
      refresh();
   }


   /**
    * Refreshes the properties of this action.
    */
   protected void refresh()
   {
      this.action.setEnabled(calculateEnabled());
   }


   /**
    * Get the CommandStack from the selected element's editor.
    * 
    * @return the GEF command stack
    */
   protected CommandStack getCommandStack()
   {
      List<EditPart> selected = getSelectedEditParts();

      if (!selected.isEmpty())
      {
         return selected.get(0).getRoot().getViewer().getEditDomain()
               .getCommandStack();
      }
      return null;
   }


   /**
    * Decides if this action is enabled for the current selection.
    * 
    * @return <code>true</code> if this action is enabled for the current selection,
    *         <code>false</code> otherwise.
    */
   protected boolean calculateEnabled()
   {
      return checkEnabledFor(getSelectedEditParts());
   }


   /**
    * Check if the action should be enabled for this list of editparts.
    * 
    * @param editparts
    * @return if the action should be enabled for this list of editparts
    */
   protected boolean checkEnabledFor(List<EditPart> editparts)
   {
      for (EditPart editPart : editparts)
      {
         if (!checkEnabledFor(editPart))
         {
            return false;
         }
      }
      return true;
   }


   /**
    * Check if the action should be enabled for this editpart.
    * 
    * @param editpart
    * @return if the action should be enabled for this editpart
    */
   protected boolean checkEnabledFor(EditPart editpart)
   {
      return true;
   }
}