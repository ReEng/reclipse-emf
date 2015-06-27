/**
 * 
 */
package org.reclipse.structure.specification.ui.edit.commands;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.commands.Command;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;


/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class DeletePSPatternSpecificationCommand extends Command
{
   private PSCatalog catalog;
   private PSPatternSpecification pattern;
   private Resource diagramsResource;
   private HierarchicalNode patternDiagram;
   
   public DeletePSPatternSpecificationCommand(PSPatternSpecification pattern, Resource diagramsResource)
   {
      super("delete pattern specification");
      this.catalog = pattern.getCatalog();
      this.pattern = pattern;
      this.diagramsResource = diagramsResource;
      Iterator<EObject> i = diagramsResource.getContents().iterator();
      while(i.hasNext())
      {
         EObject o = i.next();
         if(o instanceof HierarchicalNode)
         {
            HierarchicalNode node = (HierarchicalNode) o;
            if(node.getModel() == pattern)
            {
               this.patternDiagram = node;
               break;
            }
         }
      }
   }

   @Override
   public void execute()
   {
      redo();
   }

   @Override
   public void redo()
   {
      this.catalog.getPatternSpecifications().remove(pattern);
      this.diagramsResource.getContents().remove(patternDiagram);
   }

   @Override
   public void undo()
   {
      this.catalog.getPatternSpecifications().add(pattern);
      this.diagramsResource.getContents().add(patternDiagram);
   }

}
