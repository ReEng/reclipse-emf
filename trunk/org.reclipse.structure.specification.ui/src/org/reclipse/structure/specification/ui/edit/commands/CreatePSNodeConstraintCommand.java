package org.reclipse.structure.specification.ui.edit.commands;


import org.eclipse.emf.ecore.EClass;
import org.fujaba.commons.edit.commands.AbstractCreateNodeCommand;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This command is used to create new node boolean constraints.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreatePSNodeConstraintCommand extends AbstractCreateNodeCommand
      implements IPSFuzzyConstraintCommand
{
   private PSCombinedFragmentItem modelParent;

   private EClass type;

   private String acronym;


   /**
    * The default constructor.
    * 
    * @param host The host edit part.
    * @param request The create request.
    */
   public CreatePSNodeConstraintCommand(HierarchicalNode parent, EClass type,
         String acronym)
   {
      super("create node constraint", parent, false, null);

      modelParent = (PSCombinedFragmentItem) parent.getModel();
      this.type = type;
      this.acronym = acronym;
   }


   @Override
   protected PSBooleanConstraint getModel()
   {
      return (PSBooleanConstraint) modelElement;
   }


   @Override
   public void redoModel()
   {
      if (modelElement == null)
      {
         if (type.equals(SpecificationPackage.eINSTANCE
               .getPSAttributeConstraint()))
         {
            modelElement = SpecificationFactory.eINSTANCE
                  .createPSAttributeConstraint();
            ((PSAttributeConstraint) modelElement).setAttribute(ModelHelper
                  .getFirstAttribute((PSObject) modelParent));
         }
         else if (type.equals(SpecificationPackage.eINSTANCE
               .getPSMetricConstraint()))
         {
            modelElement = SpecificationFactory.eINSTANCE
                  .createPSMetricConstraint();
            ((PSMetricConstraint) modelElement).setMetricAcronym(acronym);
         }

         getModel().setWeight(PSConstants.DEFAULT_WEIGHT);
         getModel().setOperator(OperatorType.EQUAL);
      }

      if (modelParent instanceof PSNode)
      {
         ((PSNode) modelParent).getNodeConstraints().add(getModel());
      }
      else if (modelParent instanceof PSCombinedFragment)
      {
         ((PSCombinedFragment) modelParent).setConstraint(getModel());
      }
   }


   @Override
   public void undoModel()
   {
      if (modelParent instanceof PSNode)
      {
         ((PSNode) modelParent).getNodeConstraints().remove(getModel());
      }
      else if (modelParent instanceof PSCombinedFragment)
      {
         ((PSCombinedFragment) modelParent).setConstraint(null);
      }
   }


   @Override
   public PSCombinedFragmentItem getHost()
   {
      return modelParent;
   }
}
