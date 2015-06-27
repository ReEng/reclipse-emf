package org.reclipse.structure.inference.ui.matching.edit.parts.object;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.fujaba.commons.figures.LabelFigure;
import org.reclipse.structure.inference.ui.matching.Constants;
import org.reclipse.structure.inference.ui.matching.edit.policies.MatchingSelectionEditPolicy;
import org.reclipse.structure.inference.ui.matching.util.BoundingUtil;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.ui.edit.parts.PSAttributeConstraintEditPart;



public class MatchingPSAttributeConstraintEditPart extends PSAttributeConstraintEditPart
{

   @Override
   protected void refreshVisuals()
   {
      super.refreshVisuals();

      PSAttributeConstraint model = getRealModel();
      LabelFigure figure = (LabelFigure) getFigure();
      if (isName())
      {
         figure.setText("name = \"" + model.getValueExpression() + "\"");
      }
      else
      {
         // get value
         Object value = getValue();

         StringBuilder text = new StringBuilder();

         text.append(model.getAttribute().getName());
         text.append(" = ");
         text.append(value);

         figure.setText(text.toString());
      }
   }


   private Object getValue()
   {
      EObject bounded = BoundingUtil.getFirstBound(getRealModel().getNode());
      if (bounded != null)
      {
         for (EAttribute attr : bounded.eClass().getEAllAttributes())
         {
            if (attr.equals(getRealModel().getAttribute()))
            {
               return bounded.eGet(attr);
            }
         }
      }

      return null;
   }


   private boolean isName()
   {
      return getRealModel().getName() != null && getRealModel().getName().equals(Constants.NAME_ATTRIBUTE);
   }


   @Override
   protected void createEditPolicies()
   {
      if (!isName())
      {
         installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new MatchingSelectionEditPolicy(getFigure()));
      }
   }
}
