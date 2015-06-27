package org.reclipse.structure.specification.ui.properties.sections;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.SelectionEvent;
import org.fujaba.commons.properties.section.AbstractObjectSelectionComboSection;
import org.reclipse.metrics.extensionpoints.Metric;
import org.reclipse.metrics.extensionpoints.MetricUtil;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.properties.util.SingleAcronymSelectionDialog;


public class PSFuzzyMetricConstraintAcronymSection extends
      AbstractObjectSelectionComboSection
{
   private SingleAcronymSelectionDialog dialog;


   public PSFuzzyMetricConstraintAcronymSection()
   {
      dialog = new SingleAcronymSelectionDialog();
      dialog.setTitle("Metric Selection");
   }


   @Override
   protected Metric getCurrentValue()
   {
      return MetricUtil.getMetricByAcronym(getElement().getMetricAcronym());
   }


   @Override
   protected PSFuzzyMetricConstraint getElement()
   {
      return (PSFuzzyMetricConstraint) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_FUZZY_METRIC_CONSTRAINT__METRIC_ACRONYM;
   }


   @Override
   protected String getLabelText()
   {
      return "Acronym";
   }


   @Override
   protected List<Metric> getPossibilities()
   {
      // remove 'SIZE' on non-set element
      boolean removeSize = true;
      if (ModifierType.SET.equals(getElement().getNode().getModifier()))
      {
         removeSize = false;
      }

      // create list
      List<Metric> available = new ArrayList<Metric>();
      for (Metric metric : MetricUtil.getMetricsFor(((PSObject) getElement()
            .getNode()).getInstanceOf()))
      {
         if (!removeSize || (removeSize && !metric.getAcronym().equals("SIZE")))
         {
            available.add(metric);
         }
      }

      // sort them
      Collections.sort(available, new Comparator<Metric>()
      {
         @Override
         public int compare(Metric one, Metric two)
         {
            return one.getAcronym().compareTo(two.getAcronym());
         }
      });

      return available;
   }


   @Override
   protected String getValueText(Object element)
   {
      Metric metric = (Metric) element;

      StringBuilder text = new StringBuilder();

      text.append(metric.getAcronym());
      text.append(" - "); //$NON-NLS-1$
      text.append(metric.getName());

      return text.toString();
   }


   @Override
   protected void handleAdvancedButtonClicked()
   {
      // set input
      dialog.setInput(getPossibilities(), getElement().getMetricAcronym());

      // open it
      if (dialog.open() == IDialogConstants.OK_ID)
      {
         list.select(list.indexOf(getValueText(dialog.getSelected())));
         createCommand(getCurrentValue().getAcronym(), dialog.getSelected()
               .getAcronym());
      }
   }


   protected void handleSelection(SelectionEvent e)
   {
      if (e.getSource().equals(list))
      {
         Metric selected = (Metric) getObject(list.getText());

         createCommand(getCurrentValue().getAcronym(), selected.getAcronym());
      }
   }


   @Override
   protected boolean showAdvancedButton()
   {
      return true;
   }
}
