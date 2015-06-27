package org.reclipse.structure.specification.ui.edit.commands;


import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * This command is used to create {@link PSSpecificationConstraint}s.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreatePSSpecificationConstraintCommand extends
      AbstractCreatePSCombinedFragmentItemCommand
{

   public CreatePSSpecificationConstraintCommand(AbstractDiagramEditPart host,
         Rectangle bound)
   {
      super(host, bound, "create pattern specification constraint", false);
   }


   @Override
   public void redoModel()
   {
      // create model element
      if (modelElement == null)
      {
         PSSpecificationConstraint constraint = SpecificationFactory.eINSTANCE
               .createPSSpecificationConstraint();

         constraint.setName(PSConstants.DEFAULT__MODEL_NAME_PREFIX__CONSTRAINT
               + (pattern.getConstraints().size() + 1));
         constraint.setExpression("true");
         constraint.setWeight(PSConstants.DEFAULT_WEIGHT);
         modelElement = constraint;
      }

      // resize
      setMinimumSize();

      // add to pattern and fragment
      pattern.getConstraints().add(getModel());
      if (fragment != null)
      {
         fragment.getChildren().add(getModel());
      }
   }


   @Override
   public void undoModel()
   {
      if (fragment != null)
      {
         fragment.getChildren().remove(getModel());
      }
      pattern.getConstraints().remove(getModel());
   }


   @Override
   protected PSSpecificationConstraint getModel()
   {
      return (PSSpecificationConstraint) this.modelElement;
   }


   /**
    * This method sets the minimum size for the created {@link PSSpecificationConstraint} by
    * calculating the estimated text size.
    */
   private void setMinimumSize()
   {
      StringBuffer text = new StringBuffer("{ ");
      text.append(getModel().getName());
      text.append(": ");
      text.append(getModel().getExpression());
      text.append(" }");
      Dimension min = TextUtilities.INSTANCE.getStringExtents(text.toString(),
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_NORMAL));
      setMinHeight(min.height);
      setMinWidth(min.width + 2);
   }
}
