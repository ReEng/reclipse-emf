package org.reclipse.structure.specification.ui.edit.commands;


import org.fujaba.commons.edit.commands.AbstractDeleteEdgeCommand;
import org.fujaba.commons.notation.Edge;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;



/**
 * TODO: describe type
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class DeletePSConnectionCommand extends AbstractDeleteEdgeCommand
{

   private PSPatternSpecification pattern;

   private PSNode source;

   private PSNode target;


   /**
    * The default constructor.
    * 
    * @param edge The (view) edge to delete.
    */
   public DeletePSConnectionCommand(Edge edge)
   {
      super("delete connection", edge);

      viewElement = edge;
      modelElement = edge.getModel();

      source = getModel().getSource();
      target = getModel().getTarget();
      pattern = getModel().getPatternSpecification();
   }


   @Override
   protected void redoModel()
   {
      target.getIncoming().remove(getModel());
      source.getOutgoing().remove(getModel());
      pattern.getConnections().remove(getModel());
   }


   @Override
   protected void undoModel()
   {
      pattern.getConnections().add(getModel());
      source.getOutgoing().add(getModel());
      target.getIncoming().add(getModel());
   }


   @Override
   protected PSConnection getModel()
   {
      return (PSConnection) modelElement;
   }
}
