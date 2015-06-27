package org.reclipse.structure.inference.ui.matching.util;


import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Color;
import org.fujaba.commons.figures.LineBorderedFigure;
import org.reclipse.structure.inference.ui.matching.Constants;
import org.reclipse.structure.inference.ui.matching.edit.parts.pattern.MatchingPSCombinedFragmentEditPart;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSSpecificationConstraint;



public class SatisfactionVisualizationUtil
{

   public static boolean isParentDissatisfied(EditPart parent)
   {
      return parent instanceof MatchingPSCombinedFragmentEditPart
            && !((MatchingPSCombinedFragmentEditPart) parent).isSatisfied();
   }


   private static void setChildrensColor(IFigure figure)
   {
      for (Object child : figure.getChildren())
      {
         if (child instanceof IFigure)
         {
            setColorDissatisfied((IFigure) child);
         }
      }
   }


   public static void setColor(PSCombinedFragment model, IFigure figure,
         EditPart parent, String satisfaction)
   {
      if (satisfaction.equals(Constants.UNSATISFIED_TEXT)
            || isParentDissatisfied(parent))
      {
         setColorDissatisfied(figure);

         setChildrensColor(figure);
      }
   }


   public static void setColor(PSNode model, IFigure figure, EditPart parent,
         String satisfaction)
   {
      if (model.getModifier() == ModifierType.ADDITIONAL
            || isParentDissatisfied(parent))
      {
         if (satisfaction.equals(Constants.UNSATISFIED_TEXT)
               || isParentDissatisfied(parent))
         {
            setColorDissatisfied(figure);
         }
      }
   }


   public static void setColor(PSNodeConstraint model, IFigure figure,
         EditPart parent, String satisfaction)
   {
      if (model instanceof PSBooleanConstraint
            && ((PSBooleanConstraint) model).isAdditional())
      {
         if (satisfaction.equals(Constants.UNSATISFIED_TEXT))
         {
            setColorDissatisfied(figure);
         }
      }
   }


   public static void setColor(PSSpecificationConstraint model, IFigure figure,
         EditPart parent, String satisfaction)
   {
      if (model instanceof PSSpecificationConstraint
            && ((PSSpecificationConstraint) model).isAdditional())
      {
         if (satisfaction.equals(Constants.UNSATISFIED_TEXT))
         {
            setColorDissatisfied(figure);
         }
      }
   }


   private static void setColorDissatisfied(IFigure figure)
   {
      setColorDissatisfied(figure, Constants.DISSATISFIED_COLOR_FG,
            Constants.DISSATISFIED_COLOR_BG);
   }


   private static void setColorDissatisfied(IFigure figure, Color foreground,
         Color background)
   {
      figure.setForegroundColor(foreground);
      figure.setBackgroundColor(background);

      if (figure instanceof LineBorderedFigure)
      {
         ((LineBorderedFigure) figure).setBorderColor(foreground);
      }
   }
}
