package org.reclipse.structure.specification.ui.edit.commands;


import org.fujaba.commons.edit.commands.AbstractCreateEdgeCommand;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;



/**
 * This command is used to create new connections between two elements.
 * 
 * @author mm
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class AbstractCreatePSConnectionCommand extends
      AbstractCreateEdgeCommand
{

   protected PSPatternSpecification modelParent;

   protected PSNode modelSource;

   protected PSNode modelTarget;


   /**
    * The default constructor.
    * 
    * @param label A label for the command.
    * @param source The source node from which the connection starts.
    */
   public AbstractCreatePSConnectionCommand(String label, Node source)
   {
      super(label, getSubParent(source));
      setSource(source);

      modelSource = (PSNode) source.getModel();

      modelParent = modelSource.getPatternSpecification();
   }


   /**
    * Searches the direct child of a pattern specification.
    * @param source The node to start the search from.
    * @return Returns the direct child of a pattern specification.
    */
   private static Node getSubParent(Node source)
   {
      Node result = source;
      
      while (!(result.getParent().getModel() instanceof PSPatternSpecification))
      {
         result = result.getParent();
      }
      
      return result;
   }


   @Override
   protected void redoModel()
   {
      modelParent.getConnections().add(getModel());
      modelSource.getOutgoing().add(getModel());
      modelTarget.getIncoming().add(getModel());
   }


   @Override
   protected void undoModel()
   {
      modelParent.getConnections().remove(getModel());
      modelSource.getOutgoing().remove(getModel());
      modelTarget.getIncoming().remove(getModel());
   }


   /**
    * Counts the number of path or connections in the pattern specification.
    * 
    * @param path Whether the number of path or links.
    * @return Returns the number of connections.
    */
   protected int getNumberOfPaths(boolean path)
   {
      int count = 0;
      for (PSConnection connection : modelParent.getConnections())
      {
         if (path && connection instanceof PSPath)
         {
            count++;
         }
         else if (!path && connection instanceof PSLink)
         {
            count++;
         }
      }
      return count;
   }


   @Override
   protected abstract PSConnection getModel();


   @Override
   public void setSource(Node source)
   {
      super.setSource(source);
      modelSource = (PSNode) source.getModel();
   }


   @Override
   public void setTarget(Node target)
   {
      super.setTarget(target);
      modelTarget = (PSNode) target.getModel();
   }
}
