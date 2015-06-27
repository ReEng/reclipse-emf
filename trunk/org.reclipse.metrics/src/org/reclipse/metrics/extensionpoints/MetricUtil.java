package org.reclipse.metrics.extensionpoints;


import java.io.InvalidClassException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.reclipse.metrics.MetricsPlugin;


/**
 * This utility class can be used to get the registered extensions for the defined extension points.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * @noextend This class is not intended to be extended by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class MetricUtil
{

   private static final String EPID_METRICS = "org.reclipse.metrics.metrics"; //$NON-NLS-1$

   private static final String EPID_CALCULATORS = "org.reclipse.metrics.calculators"; //$NON-NLS-1$

   private static final String ID = "id"; //$NON-NLS-1$

   private static final String NAME = "name"; //$NON-NLS-1$

   private static final String ACRONYM = "acronym"; //$NON-NLS-1$

   private static final String DESC = "description"; //$NON-NLS-1$

   private static final String METAMODEL = "metamodel_id"; //$NON-NLS-1$

   private static final String METRIC = "metric_id"; //$NON-NLS-1$

   private static final String CLASS = "class"; //$NON-NLS-1$

   private static Set<Metric> metrics;

   private static Set<MetricCalculator> metricCalculators;


   private MetricUtil()
   {
      // hide the constructor
   }


   public static Set<Metric> getAllMetrics()
   {
      // lazy initialization
      if (metrics == null)
      {
         // prepare list
         metrics = new HashSet<Metric>();

         // fetch the extension point configurations
         for (IConfigurationElement ce : Platform.getExtensionRegistry()
               .getConfigurationElementsFor(EPID_METRICS))
         {
            // deliver simple attributes
            String id = ce.getAttribute(ID);
            String name = ce.getAttribute(NAME);
            String acronym = ce.getAttribute(ACRONYM);

            // deliver description when possible
            String desc = null;
            IConfigurationElement[] descContainer = ce.getChildren(DESC);
            if (descContainer.length == 1)
            {
               desc = descContainer[0].getValue();
            }

            // create metric
            Metric metric = new Metric(id, acronym, name, desc);

            // add the model
            if (metrics.contains(metric))
            {
               MetricsPlugin
                     .getInstance()
                     .logWarning(
                           "Two metrics with the same ID wanted to be registered: duplicate ignored.");
            }
            else
            {
               metrics.add(metric);
            }
         }

         // log when no metric registered
         if (metrics.isEmpty())
         {
            MetricsPlugin.getInstance().logInfo("No metrics registered.");
         }
      }

      return metrics;
   }


   /**
    * Delivers all currently registered metric calculators.
    * 
    * @return Returns the set of all registered <code>{@link MetricCalculator}</code>s.
    */
   public static Set<MetricCalculator> getAllCalculators()
   {
      // lazy initialization
      if (metricCalculators == null)
      {
         // deliver extension point
         IExtensionPoint point = Platform.getExtensionRegistry()
               .getExtensionPoint(EPID_CALCULATORS);
         if (point == null || getAllMetrics().isEmpty())
         {
            MetricsPlugin.getInstance().logInfo(
                  "No metric calculators registered.");
            metricCalculators = Collections.emptySet();
            return metricCalculators;
         }

         // prepare list
         metricCalculators = new HashSet<MetricCalculator>();

         // go through all registered extensions
         for (IConfigurationElement ce : Platform.getExtensionRegistry()
               .getConfigurationElementsFor(EPID_CALCULATORS))
         {
            try
            {
               // deliver simple attributes
               String id = ce.getAttribute(ID);
               String metricId = ce.getAttribute(METRIC);
               String metamodel = ce.getAttribute(METAMODEL);

               // get class
               Object calculator = ce.createExecutableExtension(CLASS);
               if (!(calculator instanceof IMetricCalculator))
               {
                  throw new InvalidClassException(
                        "The class has to implement IMetricCalculator.");
               }

               // create calculator
               MetricCalculator calc = new MetricCalculator(id, metamodel,
                     getMetric(metricId), (IMetricCalculator) calculator);

               // add the calculator when no duplicate
               if (metricCalculators.contains(calc))
               {
                  MetricsPlugin
                        .getInstance()
                        .logInfo(
                              "Two metric calculators with the same ID wanted to be registered: duplicate ignored.");
               }
               else
               {
                  metricCalculators.add(calc);
               }
            }
            catch (Exception e)
            {
               MetricsPlugin.getInstance().logError(
                     "Error reading registered metrics calculators", e);
            }
         }
      }

      return metricCalculators;
   }


   /**
    * Searches all registered metrics which can be handled by a calculator for the given type.
    * 
    * @param type The type.
    * @return Returns the set of {@link Metric}s.
    */
   public static Set<Metric> getMetricsFor(EClass type)
   {
      Set<Metric> result = new HashSet<Metric>();

      for (MetricCalculator c : getAllCalculators())
      {
         if (c.getCalculator().isApplicableTo(type))
         {
            result.add(c.getMetric());
         }
      }

      return result;
   }


   /**
    * Searches the metric with the given id.
    * 
    * @param id The identifier of the metric.
    * @return Returns the metric or <code>null</code>;
    */
   public static Metric getMetric(String id)
   {
      for (Metric m : getAllMetrics())
      {
         if (m.getId().equals(id))
         {
            return m;
         }
      }

      return null;
   }


   public static Metric getMetricByAcronym(String acronym)
   {
      for (Metric m : getAllMetrics())
      {
         if (m.getAcronym().equals(acronym))
         {
            return m;
         }
      }

      return null;
   }


   public static double getMetricValue(EObject object, String acronym)
   {
      if (object != null)
      {
         Iterator<MetricCalculator> i = getAllCalculators().iterator();
         while (i.hasNext())
         {
            MetricCalculator calc = i.next();
            if (acronym.equals(calc.getMetric().getAcronym())
                  && calc.getCalculator().isApplicableTo(object.eClass()))
            {
               return calc.getCalculator().calculate(object);
            }
         }
      }

      return 0.0d;
   }
}
