package org.reclipse.metrics.extensionpoints;


/**
 * This class describes a metric with all relevant attributes.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class Metric
{
   private String id;

   private String acronym;

   private String name;

   private String description;


   /**
    * The default constructor for all attributes.
    * 
    * @param id The identifier for the metric.
    * @param acronym The acronym for the metric.
    * @param name The name for the metric.
    * @param description The (optional) description for the metric.
    */
   public Metric(String id, String acronym, String name, String description)
   {
      this.id = id;
      this.acronym = acronym;
      this.name = name;
      this.description = description;
   }


   public String getAcronym()
   {
      return this.acronym;
   }


   public String getDescription()
   {
      return this.description;
   }


   public String getId()
   {
      return this.id;
   }


   public String getName()
   {
      return this.name;
   }


   @Override
   public boolean equals(Object other)
   {
      if (other != null && other instanceof Metric)
      {
         return ((Metric) other).getId().equals(getId());
      }

      return super.equals(other);
   }
}
