package org.reclipse.structure.specification.ui;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.expressions.PropertyTester;
import org.fujaba.commons.edit.parts.AbstractNodeViewEditPart;
import org.reclipse.metrics.extensionpoints.Metric;
import org.reclipse.metrics.extensionpoints.MetricUtil;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.ui.edit.parts.PSAnnotationEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSCombinedFragmentEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSObjectEditPart;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This class can be used to test various properties.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSCatalogPropertyTester extends PropertyTester
{

   /**
    * This identifier (specified by the plugin.xml) is used to test if a {@link PSAnnotation} is a
    * 'create' annotation.
    */
   private static final String PROP__ANNOTATION_IS_CREATE = "AnnotationIsCreate";

   /**
    * This identifier (specified by the plugin.xml) is used to test if a {@link PSNode} is a 'set'.
    */
   private static final String PROP__NODE_IS_SET = "NodeIsSet";

   /**
    * This identifier (specified by the plugin.xml) is used to test if a {@link PSAnnotation} has a
    * type.
    */
   private static final String PROP__ANNOTATION_HAS_TYPE = "AnnotationHasType";

   /**
    * This identifier (specified by the plugin.xml) is used to test if a {@link PSCombinedFragment}
    * can have a constraint.
    */
   private static final String PROP__FRAGMENT_CAN_HAVE_CONSTRAINT = "FragmentCanHaveConstraint";

   /**
    * This identifier (specified by the plugin.xml) is used to test if a {@link PSNode} is a 'set'.
    */
   private static final String PROP__NODE_HAS_SET_RATING = "NodeHasSetRating";

   /**
    * This identifier (specified by the plugin.xml) is used to test if a {@link PSObject} has
    * attributes.
    */
   private static final String PROP__OBJECT_HAS_ATTRIBUTES = "ObjectHasAttributes";

   /**
    * This identifier (specified by the plugin.xml) is used to test if any normal metrics are
    * registered.
    */
   private static final String PROP__METRICS_INSTALLED = "MetricsInstalled";

   /**
    * This identifier (specified by the plugin.xml) is used to test if a size metric is registered.
    */
   private static final String PROP__SIZE_METRIC_INSTALLED = "SizeMetricInstalled";


   /**
    * This method is called when an expression in the plugin.xml uses the defined property tester.
    * It currently supports the following tests (definition title is the identifying String):
    * <dl>
    * <dt>{@link #PROP__ANNOTATION_IS_CREATE}</dt>
    * <dd>Checks if the selected PSAnnotation is a 'create' annotation</dd>
    * <dt>{@link #PROP__NODE_IS_SET}</dt>
    * <dd>Checks if the selected PSNode is a 'set' node</dd>
    * </dl>
    * 
    * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String,
    *      java.lang.Object[], java.lang.Object)
    */
   @Override
   public boolean test(Object receiver, String property, Object[] args,
         Object expectedValue)
   {
      // annotation: create?
      if (property.equals(PROP__ANNOTATION_IS_CREATE)
            && receiver instanceof PSAnnotationEditPart)
      {
         PSAnnotationEditPart part = (PSAnnotationEditPart) receiver;

         return ModelHelper.isCreate(part.getRealModel());
      }

      // node: set?
      if (property.equals(PROP__NODE_IS_SET)
            && receiver instanceof AbstractNodeViewEditPart)
      {
         AbstractNodeViewEditPart part = (AbstractNodeViewEditPart) receiver;

         if (part.getRealModel() instanceof PSNode)
         {
            PSNode model = (PSNode) part.getRealModel();

            return model.getModifier() == ModifierType.SET;
         }
      }

      // annotation: has type?
      if (property.equals(PROP__ANNOTATION_HAS_TYPE)
            && receiver instanceof PSAnnotationEditPart)
      {
         PSAnnotationEditPart part = (PSAnnotationEditPart) receiver;

         return part.getRealModel().getType() != null;
      }

      // fragment: can have constraint?
      if (property.equals(PROP__FRAGMENT_CAN_HAVE_CONSTRAINT)
            && receiver instanceof PSCombinedFragmentEditPart)
      {
         PSCombinedFragment model = ((PSCombinedFragmentEditPart) receiver)
               .getRealModel();

         return model.getKind() == ModifierType.SET
               && model.getConstraint() == null;
      }

      // node: has set rating?
      if (property.equals(PROP__NODE_HAS_SET_RATING)
            && receiver instanceof AbstractNodeViewEditPart)
      {
         AbstractNodeViewEditPart part = (AbstractNodeViewEditPart) receiver;
         if (part.getRealModel() instanceof PSNode)
         {
            PSNode model = (PSNode) part.getRealModel();
            boolean result = false;
            Iterator<PSNodeConstraint> i = model.getNodeConstraints()
                  .iterator();
            while (i.hasNext())
            {
               if (i.next() instanceof PSFuzzySetRatingConstraint)
               {
                  result = true;
                  break;
               }
            }
            result &= model.getModifier() == ModifierType.SET;
            return result;
         }
      }

      // object: has attributes?
      if (property.equals(PROP__OBJECT_HAS_ATTRIBUTES)
            && receiver instanceof PSObjectEditPart)
      {
         PSObject model = ((PSObjectEditPart) receiver).getRealModel();

         if (model.getInstanceOf() != null)
         {
            return !model.getInstanceOf().getEAllAttributes().isEmpty();
         }
      }

      // which metrics are installed?
      if (property.equals(PROP__METRICS_INSTALLED)
            || property.equals(PROP__SIZE_METRIC_INSTALLED))
      {
         Collection<Metric> metrics = MetricUtil.getAllMetrics();

         Metric size = null;
         Metric normal = null;

         for (Metric metric : metrics)
         {
            if (metric.getAcronym().equals(
                  PSConstants.CONSTRAINT_TEXT_SIZE_ATTR))
            {
               size = metric;
            }
            else
            {
               normal = metric;
            }

            // break when both have been found
            if (size != null && normal != null)
            {
               break;
            }
         }

         // any normal metric installed?
         if (property.equals(PROP__METRICS_INSTALLED))
         {
            return normal != null;
         }

         // size metric installed?
         if (property.equals(PROP__SIZE_METRIC_INSTALLED))
         {
            return size != null;
         }
      }

      return false;
   }
}
