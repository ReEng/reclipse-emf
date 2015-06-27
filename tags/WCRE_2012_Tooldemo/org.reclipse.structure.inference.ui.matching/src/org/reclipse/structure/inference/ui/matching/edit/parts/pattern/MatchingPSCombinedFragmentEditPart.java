package org.reclipse.structure.inference.ui.matching.edit.parts.pattern;


import org.fujaba.commons.figures.UMLFragmentFigure;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.matching.Constants;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionUtil;
import org.reclipse.structure.inference.ui.matching.util.SatisfactionVisualizationUtil;
import org.reclipse.structure.inference.ui.matching.views.PatternMatchingView;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.ui.edit.parts.PSCombinedFragmentEditPart;
import org.reclipse.structure.specification.ui.utils.PSConstants;



public class MatchingPSCombinedFragmentEditPart extends PSCombinedFragmentEditPart
{

   private String satisfaction = null;


   public String getSatisfaction()
   {
      if (satisfaction == null)
      {
         ASGAnnotation annotation = PatternMatchingView.getCurrent();
         this.satisfaction = SatisfactionUtil.getSatisfaction(getRealModel(), annotation);
      }

      return satisfaction;
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSCombinedFragment model = getRealModel();
      UMLFragmentFigure figure = (UMLFragmentFigure) getFigure();

      SatisfactionVisualizationUtil.setColor(model, figure, getParent(), getSatisfaction());


      StringBuilder text = new StringBuilder();

      switch (model.getKind())
      {
         case ADDITIONAL:
            text.append(PSConstants.LABEL_ADDITIONAL);
            break;
         case NEGATIVE:
            text.append(PSConstants.LABEL_NEGATIVE);
            break;
         case SET:
            text.append(PSConstants.LABEL_SET);
            figure.setOpaque(false);
            break;
         default:
            text.append(PSConstants.LABEL_NONE);
            break;
      }


      if (model.getWeight() != PSConstants.DEFAULT_WEIGHT)
      {
         figure.setText(text + " {" + getSatisfaction() + ", w=" + model.getWeight() + "}");
      }
      else
      {
         figure.setText(text + " {" + getSatisfaction() + "}");
      }

   }


   public boolean isSatisfied()
   {
      return !getSatisfaction().equals(Constants.UNSATISFIED_TEXT);
   }
}
