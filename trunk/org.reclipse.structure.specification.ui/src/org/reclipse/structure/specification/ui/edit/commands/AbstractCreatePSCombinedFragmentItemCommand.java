package org.reclipse.structure.specification.ui.edit.commands;


import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.fujaba.commons.edit.commands.AbstractCreateNodeCommand;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;



/**
 * This command is used to create new combined fragment items.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class AbstractCreatePSCombinedFragmentItemCommand extends
      AbstractCreateNodeCommand
{

   protected PSCombinedFragment fragment;

   protected PSPatternSpecification pattern;


   /**
    * Default constructor.
    * 
    * @param host The host for the creation.
    * @param request The create request.
    * @param label The label for the command.
    */
   public AbstractCreatePSCombinedFragmentItemCommand(
         AbstractDiagramEditPart host, Rectangle bound, String label, boolean hierarchical, List<Node> children)
   {
      super(label, host.getModel(), hierarchical, children);

      // cache relevant elements
      if (host.getRealModel() instanceof PSPatternSpecification)
      {
         pattern = (PSPatternSpecification) host.getRealModel();
      }
      else if (host.getRealModel() instanceof PSCombinedFragment)
      {
         fragment = (PSCombinedFragment) host.getRealModel();
         pattern = fragment.getPatternSpecification();
      }

      setBounds(bound);
   }


   public AbstractCreatePSCombinedFragmentItemCommand(
         AbstractDiagramEditPart host, Rectangle bound, String label,
         boolean hierarchical)
   {
      this(host, bound, label, hierarchical, null);
   }


   /**
    * Counts the number of annotations or objects of the pattern specification. Used for the name of
    * new nodes.
    * 
    * @param object Whether to count objects or annotations.
    * @return Returns the number of annotations or objects on the host.
    */
   protected int getNumberOfNodes(boolean object)
   {
      int count = 0;
      for (PSNode node : pattern.getNodes())
      {
         if (object && node instanceof PSObject)
         {
            count++;
         }
         else if (!object && node instanceof PSAnnotation)
         {
            count++;
         }
      }
      return count;
   }
}
