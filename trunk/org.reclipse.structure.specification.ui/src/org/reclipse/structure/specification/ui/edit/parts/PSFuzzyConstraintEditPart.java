package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.emf.common.notify.Notification;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.math.functions.Lim0EFunction;
import org.reclipse.math.functions.Lim1EFunction;
import org.reclipse.math.functions.Lim1EFunctionNOA;
import org.reclipse.math.functions.Lim1EFunctionNOM;
import org.reclipse.math.functions.Lim1EFunctionWLOC;
import org.reclipse.math.functions.LinearFunction;
import org.reclipse.math.functions.MathematicalFunction;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.figures.FuzzyFunctionFigure;
import org.reclipse.structure.specification.ui.figures.Lim0EFunctionFigure;
import org.reclipse.structure.specification.ui.figures.Lim1EFunctionFigure;
import org.reclipse.structure.specification.ui.figures.LinearFunctionFigure;
import org.reclipse.structure.specification.ui.utils.MathFunctionHelper;



/**
 * This is the edit part for the model object {@link PSFuzzyMetricConstraint}.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSFuzzyConstraintEditPart extends AbstractPSNodeConstraintEditPart
{

   @Override
   public PSFuzzyConstraint getRealModel()
   {
      return (PSFuzzyConstraint) super.getRealModel();
   }


   @Override
   public void notifyChanged(Notification notification)
   {
      // shorthand for the specification package
      SpecificationPackage p = SpecificationPackage.eINSTANCE;

      // get affected feature
      Object feature = notification.getFeature();

      // need to refresh visually?
      if (p.getPSFuzzyMetricConstraint_MetricAcronym().equals(feature)
            || p.getPSItem_Weight().equals(feature))
      {
         refreshVisuals();
      }

      // need to refresh the tool tip?
      if (p.getPSFunctionParameter_Value().equals(feature)
            || p.getPSFuzzyConstraint_MathFunctionID().equals(feature)
            || p.getPSFuzzyConstraint_Parameters().equals(feature))
      {
         configureToolTip();
      }

      super.notifyChanged(notification);
   }


   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      LabelFigure figure = (LabelFigure) getFigure();
      StringBuilder text = new StringBuilder();


      if (getRealModel() instanceof PSFuzzyMetricConstraint)
      {
         text.append("fuzzy constraint: ");
         PSFuzzyMetricConstraint expr = (PSFuzzyMetricConstraint) getRealModel();
         text.append(expr.getMetricAcronym());
      }
      else if (getRealModel() instanceof PSFuzzySetRatingConstraint)
      {
         text.append("set rating function");
      }

      figure.setText(text.toString());
   }


   protected void configureToolTip()
   {
      LabelFigure figure = (LabelFigure) getFigure();

      if (figure.getToolTip() != null
            && ((FuzzyFunctionFigure) getFigure().getToolTip()).getFunction() == MathFunctionHelper
                  .getMathematicalFunction(getRealModel()))
      {
         return;
      }

      figure.setToolTip(getToolTipFigure());
   }


   protected FuzzyFunctionFigure getToolTipFigure()
   {
      MathematicalFunction function = MathFunctionHelper
            .getMathematicalFunction(getRealModel());
      FuzzyFunctionFigure figure = null;
      String xName = "";
      if (getRealModel() instanceof PSFuzzyMetricConstraint)
      {
         PSFuzzyMetricConstraint expr = (PSFuzzyMetricConstraint) getRealModel();
         xName = expr.getMetricAcronym();
      }

      if (function instanceof Lim0EFunction)
      {
         figure = new Lim0EFunctionFigure(xName);
      }
      else if (function instanceof Lim1EFunction
            || function instanceof Lim1EFunctionNOA
            || function instanceof Lim1EFunctionNOM
            || function instanceof Lim1EFunctionWLOC)
      {
         figure = new Lim1EFunctionFigure(xName);
      }
      else if (function instanceof LinearFunction)
      {
         figure = new LinearFunctionFigure(xName);
      }

      if (figure != null)
      {
         figure.setPreferredSize(300, 150);
         figure.setFunction(function);
      }

      return figure;
   }
}
