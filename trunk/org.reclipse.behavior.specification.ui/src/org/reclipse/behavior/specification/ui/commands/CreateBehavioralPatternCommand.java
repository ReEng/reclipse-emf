/**
 * 
 */
package org.reclipse.behavior.specification.ui.commands;


import org.eclipse.gef.commands.Command;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.structure.specification.PSPatternSpecification;

import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramFactory;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.RootFragment;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class CreateBehavioralPatternCommand extends Command
{
   private BPCatalog catalog;

   private BehavioralPattern newPattern;

   private PSPatternSpecification structuralPattern;


   /**
    * @param catalog the PSCatalog that will contain the pattern specification
    * @param diagramResource Resource that will contain the diagram
    */
   public CreateBehavioralPatternCommand(BPCatalog catalog,
         PSPatternSpecification structuralPattern)
   {
      super("create new pattern specification");
      this.catalog = catalog;
      this.structuralPattern = structuralPattern;
   }


   @Override
   public void execute()
   {
      redo();
   }


   @Override
   public void redo()
   {
      BehavioralPattern newPattern = createBehavioralPattern();

      newPattern.setCatalog(this.catalog);
      
      
      //fixme: load GAST explicitely here?
//      this.catalog.eResource().getResourceSet().getResource(
//            this.structuralPattern.eResource().getURI(), true);
//      
//      MetaModel mm = ModelHelper.getMetaModel(this.structuralPattern);
//      if (mm != null)
//      {
//         this.catalog.eResource().getResourceSet().getResource(
//               mm.getResourceURI(), true);
//      }

      newPattern.setPsPatternSpecification(this.structuralPattern);
      newPattern.setName(newPattern.getPsPatternSpecification().getName());
   }


   @Override
   public void undo()
   {
      this.catalog.getBehavioralPatterns().remove(this.newPattern);
   }


   private BehavioralPattern createBehavioralPattern()
   {
      BehavioralPattern newPattern = BehavioralpatternFactory.eINSTANCE
            .createBehavioralPattern();
      RootFragment rootFragment = BasicSequenceDiagramFactory.eINSTANCE
            .createRootFragment();
      rootFragment.setDiagram(newPattern);
      InteractionOperand rootOperand = BasicSequenceDiagramFactory.eINSTANCE
            .createInteractionOperand();
      rootOperand.setParentFragment(rootFragment);
      rootFragment.getOperands().add(rootOperand);
      newPattern.setRootFragment(rootFragment);
      return newPattern;
   }
}
