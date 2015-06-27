/**
 * 
 */
package org.reclipse.behavior.specification.ui.commands;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gef.commands.Command;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class SetNameCommand extends Command
{
   String newName, oldName;
   ENamedElement model;
   

   public SetNameCommand(ENamedElement model, String newName)
   {
      super();
      this.model = model;
      this.newName = newName;
      this.oldName = model.getName();
   }

   @Override
   public void execute()
   {
      redo();
   }

   @Override
   public void redo()
   {
      model.setName(newName);
   }

   @Override
   public void undo()
   {
      model.setName(oldName);
   }

}
