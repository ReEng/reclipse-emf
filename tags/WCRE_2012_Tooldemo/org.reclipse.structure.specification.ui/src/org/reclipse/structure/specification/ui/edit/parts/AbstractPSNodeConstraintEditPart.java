package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.ui.edit.policies.PSDeleteEditPolicy;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * This is the edit part for model objects of {@link PSNodeConstraint}.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class AbstractPSNodeConstraintEditPart extends AbstractNodeEditPart
{

   @Override
   public PSNodeConstraint getRealModel()
   {
      return (PSNodeConstraint) super.getRealModel();
   }


   @Override
   protected IFigure createFigure()
   {
      MarginBorder border = new MarginBorder(2, 5, 2, 5);
      LabelFigure figure = new LabelFigure();

      figure.setLabelAlignment(PositionConstants.LEFT);
      figure.setBorder(border);

      return figure;
   }


   @Override
   public LabelFigure getFigure()
   {
      return (LabelFigure) super.getFigure();
   }


   @Override
   protected void createEditPolicies()
   {
      NonResizableEditPolicy nonResizable = new NonResizableEditPolicy();
      nonResizable.setDragAllowed(false);

      installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, nonResizable);
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new PSDeleteEditPolicy());
   }


   /**
    * @return Returns the formatted suffix for boolean constraints.
    */
   protected String getBooleanConstraintSuffix()
   {
      if (getRealModel() instanceof PSBooleanConstraint)
      {
         PSBooleanConstraint model = (PSBooleanConstraint) getRealModel();
         StringBuffer text = new StringBuffer();
         if (model.isAdditional()
               || model.getWeight() != PSConstants.DEFAULT_WEIGHT)
         {
            text.append(" {");
            if (model.isAdditional())
            {
               text.append(PSConstants.LABEL_ADDITIONAL);
            }
            if (model.getWeight() != PSConstants.DEFAULT_WEIGHT)
            {
               if (model.isAdditional())
               {
                  text.append(", ");
               }
               text.append("w=" + model.getWeight());
            }
            text.append("}");
         }

         return text.toString();
      }
      else
      {
         return null;
      }
   }


   /**
    * Creates a formatted string representation for given the operator.
    * 
    * @param operator The operator to translate.
    * @return Returns the formatted string.
    */
   protected String getOperatorString(OperatorType operator)
   {
      switch (operator)
      {
         case EQUAL:
            return "=";
         case UNEQUAL:
            return "!=";
         case LESS:
            return "<";
         case LESS_OR_EQUAL:
            return "\u2264";
         case GREATER:
            return ">";
         case GREATER_OR_EQUAL:
            return "\u2265";
         case REGULAR_EXPRESSION:
            return "= " + PSConstants.REG_EXPRESSION_STRING + ":";
         default:
            throw new IllegalArgumentException("Unknown operator.");
      }
   }
}
