package org.reclipse.structure.specification.descriptor;


import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.metrics.extensionpoints.Metric;
import org.reclipse.metrics.extensionpoints.MetricUtil;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;


/**
 * This class provides the property descriptor for the {@link PSMetricConstraint#getMetricAcronym()
 * metric acronym} feature.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSMetricConstraintAcronymPropertyDescriptor extends
      ItemPropertyDescriptor
{

   /**
    * The default constructor.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor
    */
   public PSMetricConstraintAcronymPropertyDescriptor(
         AdapterFactory adapterFactory, ResourceLocator resourceLocator,
         String displayName, String description, EStructuralFeature feature,
         boolean isSettable, boolean multiLine, boolean sortChoices,
         Object staticImage, String category, String[] filterFlags)
   {
      super(adapterFactory, resourceLocator, displayName, description, feature,
            isSettable, multiLine, sortChoices, staticImage, category,
            filterFlags);
   }


   @Override
   public boolean canSetProperty(Object object)
   {
      // disallow editing of set fragments and nodes of type 'set' when its the SIZE metric
      if (isSet(object)
            && ((PSMetricConstraint) object).getMetricAcronym() != null
            && ((PSMetricConstraint) object).getMetricAcronym().equals("SIZE"))
      {
         return false;
      }

      return super.canSetProperty(object);
   }


   private boolean isSet(Object object)
   {
      // get constrained object
      PSNode node = ((PSNodeConstraint) object).getNode();

      // fragment is always a set
      if (node == null)
      {
         return true;
      }

      // check node type
      if (node.getModifier() == ModifierType.SET)
      {
         return true;
      }

      return false;
   }


   /**
    * Filters the values for the property descriptor. It collects all metric acronyms applicable for
    * the current selected constraint.
    * 
    * @param object The object for which to filter.
    * @return Returns the filtered choices.
    */
   @Override
   public Collection<String> getComboBoxObjects(Object object)
   {
      PSNode node = ((PSNodeConstraint) object).getNode();

      // prepare value set
      HashSet<String> filtered = new HashSet<String>();

      // it is a set fragment or a set node
      if (isSet(object))
      {
         filtered.add("SIZE");
      }

      if (node instanceof PSObject && node != null)
      {
         // catch registered metrics for the specific type
         for (Metric con : MetricUtil.getMetricsFor(((PSObject) node)
               .getInstanceOf()))
         {
            filtered.add(con.getAcronym());
         }
      }

      // remove SIZE when not a set
      if (!isSet(object))
      {
         filtered.remove("SIZE");
      }

      return filtered;
   }
}
