package org.reclipse.structure.inference.ui.matching.edit.parts.object;


import java.text.DecimalFormat;

import org.eclipse.gef.EditPolicy;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.views.ObjectMatchingView;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.ui.edit.parts.PSAnnotationEditPart;
import org.reclipse.structure.specification.ui.figures.PSAnnotationFigure;
import org.reclipse.structure.specification.ui.utils.PSConstants;


public class MatchingPSAnnotationEditPart extends PSAnnotationEditPart
{

   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new MatchingSelectionEditPolicy(getFigure()));
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSAnnotation model = getRealModel();
      PSAnnotationFigure figure = getFigure();
      figure.setCreate(false);
      ASGAnnotation anno = ObjectMatchingView.getCurrent();

      if (model.getModifier() != ModifierType.SET)
      {
         figure.setWeightText(new DecimalFormat("0.00").format(anno.getAnnotationRanking()) + " %");
      }
      else
      {
         // create weight text
         StringBuilder text = new StringBuilder();
         if (model.getWeight() != PSConstants.DEFAULT_WEIGHT)
         {
            text.append("{w="); //$NON-NLS-1$
            text.append(model.getWeight());
            text.append("}"); //$NON-NLS-1$
         }

         figure.setWeightText(text.toString());
      }
   }


   @Override
   public void addCreateProperties(PSAnnotationFigure figure)
   {
      // no create properties in matched asg view
   }
}
