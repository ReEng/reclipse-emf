package org.reclipse.structure.specification.ui.edit.commands;


import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * This command is used to create a new {@link PSPath} between two elements.
 * 
 * @author mm
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreatePSPathCommand extends AbstractCreatePSConnectionCommand
{

   /**
    * Default constructor.
    * 
    * @param source The source node from which the path starts.
    */
   public CreatePSPathCommand(Node source)
   {
      super("create path", source);
   }


   @Override
   protected PSPath getModel()
   {
      if (modelElement == null)
      {
         PSPath path = SpecificationFactory.eINSTANCE.createPSPath();

         path.setName(PSConstants.DEFAULT__MODEL_NAME_PREFIX__PATH
               + (getNumberOfPaths(true) + 1));
         path.setWeight(PSConstants.DEFAULT_WEIGHT);

         modelElement = path;
      }

      return (PSPath) modelElement;
   }
}
