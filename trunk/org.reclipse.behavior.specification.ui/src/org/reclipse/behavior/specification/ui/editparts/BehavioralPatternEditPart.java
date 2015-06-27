package org.reclipse.behavior.specification.ui.editparts;


import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.reclipse.behavior.specification.BPAssignment;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.editpolicies.BehavioralPatternLayoutEditPolicy;
import org.reclipse.behavior.specification.ui.layouts.BehavioralPatternLayout;

import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.sequencediagram.viewer.editparts.SequenceDiagramEditPart;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BehavioralPatternEditPart extends SequenceDiagramEditPart
{

   @Override
   protected void addElementsOfCombinedFragment(List<EObject> list, CombinedFragment combinedFragment)
   {
      List<InteractionOperand> operands = combinedFragment.getOperands();
      for (InteractionOperand operand : operands)
      {
         if (operand == null)
            return;

         list.add(operand);

         EList<Fragment> fragments = operand.getFragments();
         for (Fragment fragment : fragments)
         {
            if (fragment instanceof CombinedFragment)
            {
               list.add(fragment);
               addElementsOfCombinedFragment(list, (CombinedFragment) fragment);
            }
            else if (fragment instanceof BPAssignment)
            {
               list.add(fragment);
            }
         }
      }
   }


   @Override
   public BehavioralPattern getModel()
   {
      return (BehavioralPattern) super.getModel();
   }


   protected String getFragmentLabel()
   {
      String label;
      if (getModel() != null && getModel().isNegative())
      {
         label = "neg bp ";
      }
      else
      {
         label = "bp ";
      }

      return label;
   }


   public void notifyChanged(Notification notification)
   {
      // if(BehavioralpatternPackage.BEHAVIORAL_PATTERN__OBJECTS ==
      // notification.getFeatureID(BehavioralPattern.class))
      // {
      // refresh();
      // }
      int featureId = notification.getFeatureID(BehavioralPattern.class);
      if (NotationPackage.DIAGRAM__PERSISTED_CHILDREN == featureId
            || NotationPackage.DIAGRAM__PERSISTED_EDGES == featureId)
      {
         refresh();
      }
   }


   @Override
   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.LAYOUT_ROLE, new BehavioralPatternLayoutEditPolicy(this));
      super.createEditPolicies();
   }


   protected void layout()
   {
      this.layout = new BehavioralPatternLayout(this);
   }
}
