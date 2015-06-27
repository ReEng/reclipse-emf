/**
 * 
 */
package org.reclipse.behavior.specification.ui.commands;

import org.eclipse.gef.commands.Command;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;

/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class DeleteBehavioralPatternCommand extends Command
{
   private BPCatalog catalog;
   private BehavioralPattern pattern;
   
   public DeleteBehavioralPatternCommand(BehavioralPattern pattern)
   {
      super("delete pattern specification");
      this.catalog = pattern.getCatalog();
      this.pattern = pattern;
   }

   @Override
   public void execute()
   {
      redo();
   }

   @Override
   public void redo()
   {
      this.catalog.getBehavioralPatterns().remove(this.pattern);
   }

   @Override
   public void undo()
   {
      this.catalog.getBehavioralPatterns().add(this.pattern);
   }

}
