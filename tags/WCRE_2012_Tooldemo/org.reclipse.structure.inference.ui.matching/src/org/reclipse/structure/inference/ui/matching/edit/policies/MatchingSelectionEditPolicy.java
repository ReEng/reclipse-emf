package org.reclipse.structure.inference.ui.matching.edit.policies;


import org.eclipse.draw2d.Figure;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.swt.graphics.Color;
import org.reclipse.structure.inference.ui.matching.Constants;


public class MatchingSelectionEditPolicy extends SelectionEditPolicy
{

   private final Figure figure;

   private final Color revert;


   public MatchingSelectionEditPolicy(Figure figure)
   {
      this.figure = figure;
      revert = figure.getBackgroundColor();
   }


   @Override
   protected void hideSelection()
   {
      figure.setBackgroundColor(revert);
      figure.setOpaque(false);
   }


   @Override
   protected void showSelection()
   {
      figure.setBackgroundColor(Constants.COLOR_SELECTED);
      figure.setOpaque(true);
   }
}
