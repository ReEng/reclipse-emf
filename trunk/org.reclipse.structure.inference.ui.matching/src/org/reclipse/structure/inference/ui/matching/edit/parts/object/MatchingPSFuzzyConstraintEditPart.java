package org.reclipse.structure.inference.ui.matching.edit.parts.object;


import java.text.NumberFormat;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.math.functions.MathematicalFunction;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.views.ObjectMatchingView;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.ui.edit.parts.PSFuzzyConstraintEditPart;
import org.reclipse.structure.specification.ui.figures.FuzzyFunctionFigure;
import org.reclipse.structure.specification.ui.utils.MathFunctionHelper;



public class MatchingPSFuzzyConstraintEditPart extends
      PSFuzzyConstraintEditPart
{

   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
            new MatchingSelectionEditPolicy(getFigure()));
   }


   @Override
   protected void configureToolTip()
   {
      super.configureToolTip();
      IFigure f = getFigure().getToolTip();
      if (f != null && f instanceof FuzzyFunctionFigure)
      {
         FuzzyFunctionFigure figure = (FuzzyFunctionFigure) f;
         PSFuzzyMetricConstraint fuzzyExpr = getRealModel();

         System.out.println(fuzzyExpr);
         // FIXME: metrics integration
         // MetricsEngine metricsEngine = MetricsEngine
         // .get(fuzzyExpr.getProject());
         // if (metricsEngine.getSettings() == null)
         // {
         // MetricsSettings settings = new MetricsSettings();
         // settings.setConfiguration(MetricsConfigurationManager.get()
         // .getFromConfigurations("org.reclipse.metrics.uml"));
         // metricsEngine.setSettings(settings);
         // }

         double xValue = computeFunctionValueX();
         MathematicalFunction function = MathFunctionHelper
               .getMathematicalFunction(getRealModel());
         double yValue;
         try
         {
            yValue = function.value(xValue);
         }
         catch (ArithmeticException e)
         {
            yValue = 0d;
         }
         figure.setValues(xValue, yValue);
      }
   }


   @Override
   protected void refreshVisuals()
   {
      PSFuzzyMetricConstraint fuzzyExpr = (PSFuzzyMetricConstraint) getRealModel();

      LabelFigure figure = (LabelFigure) getFigure();
      String text = "";
      text += fuzzyExpr.getMetricAcronym();
      text += " = ";

      double xValue = computeFunctionValueX();
      NumberFormat.getNumberInstance().setMaximumFractionDigits(2);
      text += NumberFormat.getNumberInstance().format(xValue);
      figure.setText(text);

      configureToolTip();
   }


   @Override
   public PSFuzzyMetricConstraint getRealModel()
   {
      return (PSFuzzyMetricConstraint) super.getRealModel();
   }


   protected double computeFunctionValueX()
   {
      String key = getRealModel().getNode().getName();
      for (EObject bounded : ObjectMatchingView.getCurrent().getBoundObjects()
            .get(key))
      {
         System.out.println(bounded);
         // FIXME: metrics integration
         // return Metrics.getValue(bounded,
         // getRealModel().getMetricAcronym());
      }

      return 0d;
   }
}
