package org.reclipse.metrics.extensionpoints;


/**
 * This class represents metric calculators with all its attributes.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class MetricCalculator
{

   private String id;

   private String metamodel;

   private Metric metric;

   private IMetricCalculator calculator;


   /**
    * The default constructor with all basic attributes.
    * 
    * @param calculator The calculator object.
    * @param id The identifier.
    * @param metamodel The meta model identifier.
    * @param metric The supported metric.
    */
   public MetricCalculator(String id, String metamodel, Metric metric,
         IMetricCalculator calculator)
   {
      this.id = id;
      this.metamodel = metamodel;
      this.metric = metric;
      this.calculator = calculator;
   }


   /**
    * Getter of the {@link #id}.
    * 
    * @return Returns the identifier.
    */
   public String getID()
   {
      return this.id;
   }


   /**
    * Getter of the meta model identifier.
    * 
    * @return Returns the meta model ID.
    */
   public String getMetamodelID()
   {
      return metamodel;
   }


   /**
    * Getter of the metric.
    * 
    * @return Returns the {@link Metric}.
    */
   public Metric getMetric()
   {
      return this.metric;
   }


   /**
    * Getter of the calculator.
    * 
    * @return Returns the {@link IMetricCalculator} object.
    */
   public IMetricCalculator getCalculator()
   {
      return this.calculator;
   }


   @Override
   public boolean equals(Object other)
   {
      if (other != null && other instanceof MetricCalculator)
      {
         return ((MetricCalculator) other).getID().equals(getID());
      }

      return super.equals(other);
   }
}
